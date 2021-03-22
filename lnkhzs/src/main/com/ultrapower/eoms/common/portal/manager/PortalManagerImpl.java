package com.ultrapower.eoms.common.portal.manager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.data.DataAdapter;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.sms.service.InsideSmsService;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.NumberUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.common.core.util.WebUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserLoginInfo;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.common.portal.service.PortalManagerService;
import com.ultrapower.eoms.common.portal.service.UserSessionExtService;
import com.ultrapower.eoms.common.portal.util.UserSessionExtType;
import com.ultrapower.eoms.ultrasm.model.Attachment;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.AttachmentManagerService;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.DicManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;

@Transactional
public class PortalManagerImpl implements PortalManagerService
{
	private InsideSmsService insidesm;
	private UserManagerService userManagerService;
	private DepManagerService depManagerService;
	private DicManagerService dicManagerService;
	private AttachmentManagerService attachmentManagerService;
	private IDao<UserInfo> userManagerDao;
	private IDao<UserLoginInfo> loginInfoDao;
	/*private IDao<JkBsRoleuser> jkBsRoleuserDao;*/

	/**
	 * 通过短信方式获取用户登录命名密码
	 * @param loginName
	 */
	public String findpwd(String loginName)
	{
		String result="SUCCESS";
		UserInfo user = userManagerService.getUserByLoginName(loginName, false);
		if(user!=null)
		{
			String pwd=userManagerService.decodePwd(StringUtils.checkNullString(user.getPwd()));
			if(pwd.equals(""))
			{
				pwd="空";
			}
			String mobile=StringUtils.checkNullString(user.getMobile());
			if(mobile.equals(""))
			{
				result="用户手机号为空";
			}
			else if(!insidesm.sendsm(user.getMobile(), "密码为： "+pwd, "common",3))
			{
				result="获取密码失败";
			}
		}
		else
		{
			result="用户登录名不存在";
		}
		return result;
	}
	
	public UserSession login(String loginName, String pwd, boolean isVerify)
	{
		UserSession userSession = null;
		UserInfo user = userManagerService.getUserByLoginName(loginName);
		if(user == null)
		{
			return userSession;
		}
		if("".equals(StringUtils.checkNullString(user.getPwd())))
		{
			return userSession;
		}
		if(!isVerify || StringUtils.checkNullString(pwd).equals(userManagerService.decodePwd(StringUtils.checkNullString(user.getPwd()))))
		{
			userSession = new UserSession();
			if("1".equals(StringUtils.checkNullString(user.getStatus())))
			{
				userSession = this.getUserSession(loginName, UserSessionExtType.ALL); // 登录时获取所有userSession信息
				long currentTime = System.currentTimeMillis() / 1000;
				user.setLastlogintime(currentTime);
				this.saveUserLoginInfo(loginName, "login");
				userSession.setLoginDate(TimeUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				
				userManagerDao.saveOrUpdate(user);
			}
		}
		if (userSession != null && userSession.getSearchCondition() == null) {
			// 将用户配置的搜索条件加入userSession
			Map<String, String> searchCondition = userManagerService.getUserSearchCondition(loginName);
			userSession.setSearchCondition(searchCondition);
			userSession.setDeptLevel2Id(getUserLevel2DepId(userSession.getLoginName()));
			
		}
		return userSession;
	}
	
    public String getUserLevel2DepId(String loginName) {
        String depId = "";
        QueryAdapter q = new QueryAdapter(Constants.DATABASE_ALIAS);
        DataTable dt = q.executeQuery("SELECT dleveltwoid FROM BS_T_SM_USER where LOGINNAME='" + loginName + "'");
        List<DataRow> rows = dt.getRowList();
        if (rows.size() > 0) {
            DataRow dr = rows.get(0);
            depId = dr.getString("dleveltwoid");
        }
        return depId;
    }
	
	public UserSession getUserSession(String loginName, String... beanNames)
	{
		UserInfo userInfo = userManagerService.getUserByLoginName(loginName);
		return this.getUserSession(userInfo, beanNames);
	}
	
	public UserSession getUserSession(UserInfo userInfo, String [] beanNames)
	{
		UserSession userSession = null;
		if(userInfo == null)
		{
			return userSession;
		}
		userSession = this.getUserSession(userInfo); // 获取最基本的用户信息
		if(userSession == null)
		{
			return userSession;
		}
		int beanLen = 0;
		if(beanNames != null)
		{
			beanLen = beanNames.length;
		}
		for(int i=0 ; i<beanLen ; i++)
		{
			Object obj = WebApplicationManager.getBean(beanNames[i]);
			if(obj == null)
			{
				continue;
			}
			UserSessionExtService userSessionService = (UserSessionExtService) obj;
			userSession = userSessionService.buildExtendUserSession(userSession);
		}
		return userSession;
	}
	
	/**
	 * 获取最基本的用户信息
	 * @param userInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private UserSession getUserSession(UserInfo userInfo)
	{
		UserSession userSession = null;
		if(userInfo == null)
		{
			return userSession;
		}
		userSession = new UserSession();
		String userId = StringUtils.checkNullString(userInfo.getPid());
		userSession.setPid(userId);
		userSession.setLoginName(userInfo.getLoginname());
		userSession.setFullName(StringUtils.checkNullString(userInfo.getFullname()));
		userSession.setPwd(StringUtils.checkNullString(userInfo.getPwd()));
		userSession.setPosition(dicManagerService.getTextByValue("userposition", StringUtils.checkNullString(userInfo.getPosition())));
		userSession.setType(dicManagerService.getTextByValue("usertype", StringUtils.checkNullString(userInfo.getType())));
		userSession.setMobile(StringUtils.checkNullString(userInfo.getMobile()));
		userSession.setPhone(StringUtils.checkNullString(userInfo.getPhone()));
		userSession.setFax(StringUtils.checkNullString(userInfo.getFax()));
		userSession.setEmail(StringUtils.checkNullString(userInfo.getEmail()));
		String image = StringUtils.checkNullString(userInfo.getImage());
		if("".equals(image))
		{
			image = "default.png";
		}
		else
		{
			List<Attachment> attachmentList = attachmentManagerService.getAttachmentByRelation(image);
			if(attachmentList != null && attachmentList.size() > 0)
			{
				image = StringUtils.checkNullString(attachmentList.get(attachmentList.size()-1).getRealname());
			}
		}
		userSession.setImage(image);
		userSession.setOrderNum(StringUtils.checkNullString(userInfo.getOrdernum()+""));
		String depid = StringUtils.checkNullString(userInfo.getDepid());
		DepInfo dep = depManagerService.getDepByID(depid);
		if(dep != null)
		{
		    //判断部门类型
            userSession.setDeptType(checkDept(dep.getDepdns()));
            //
			userSession.setDepDns(StringUtils.checkNullString(dep.getDepdns()));
			userSession.setDepFullName(StringUtils.checkNullString(dep.getDepfullname()));
		}
		userSession.setDepId(depid);
		userSession.setDepName(StringUtils.checkNullString(userInfo.getDepname()));
		userSession.setCompanyId(StringUtils.checkNullString(userInfo.getCompanyId()));
		userSession.setCompanyName(StringUtils.checkNullString(userInfo.getCompanyName()));
		userSession.setGroupId(StringUtils.checkNullString(userInfo.getGroupid()));
		userSession.setGroupName(StringUtils.checkNullString(userInfo.getGroupname()));
		userSession.setPtdepId(StringUtils.checkNullString(userInfo.getPtdepid()));
		userSession.setPtdepName(StringUtils.checkNullString(userInfo.getPtdepname()));
		String skin = StringUtils.checkNullString(userInfo.getSystemskin());
		if("".equals(skin))
		{
			skin = PropertiesUtils.getProperty("eoms.default.skin");
		}
		userSession.setSkinType(skin);
		userSession.setCreater(StringUtils.checkNullString(userInfo.getCreater()));
		userSession.setCreateTime(TimeUtils.formatIntToDateString(userInfo.getCreatetime()));
		userSession.setLastModifier(StringUtils.checkNullString(userInfo.getLastmodifier()));
		/*userSession.setLastModifyTime(TimeUtils.formatIntToDateString(userInfo.getLastmodifytime()));*/
		userSession.setLocationZone(userInfo.getLocationzone());
        //判断 用户数据地市公司通过dns获取地市信息
        if(userSession.getDeptType() == 1){
            userSession.setCityId(dep.getParentid());
//            userSession.setCityId(getCityIdByDNS(userSession.getDepDns()));
        }
        // userSession.getDeptType() == 2 用户属于  代维公司
        else {
        	userSession.setCityId(dep.getParentid());
//            userSession.setCityId(userInfo.getLocationzone());
        }
		String lastLoginTime = "";
		if(NumberUtils.formatToInt(userInfo.getLastlogintime())>0)
		{
			lastLoginTime = TimeUtils.formatIntToDateString(userInfo.getLastlogintime());
		}
		userSession.setLastLoginTime(lastLoginTime);
		//补全dwCityName字段
        if(org.apache.commons.lang3.StringUtils.isNotBlank(userSession.getCityId())){
            QueryAdapter mams = new QueryAdapter(Constants.DATABASE_ALIAS);
            if(!"0".equals(userSession.getCityId())){
            	DataTable dt = mams.executeQuery("select diname as zh_label from bs_t_sm_dicitem t where dtcode = 'city_id' and t.dicdns like ?", new Object[]{userSession.getCityId().substring(0, 4)+"%"});
//            DataTable dt = mams.executeQuery("select zh_label from rms_city t where t.int_id = ?", new Object[]{userSession.getCityId()});
            	List<DataRow> rows = dt.getRowList();
            	if(rows.size()>=1){
            		DataRow dr = rows.get(0);
            		userSession.setCityName(dr.getString("zh_label"));
            	}
            }
        }
        
		//补全JK监控系统相关字段
        /*List<JkBsRoleuser> JkBSRoleusers = jkBsRoleuserDao.find("from JkBsRoleuser t where t.status = '1' and t.loginname = ?", userSession.getLoginName());*/
       /* userSession.setJkBsRoleusers(JkBSRoleusers);*/
		return userSession;
	}
	
	public UserSession loginSimple(String loginName, String pwd, boolean isVerify)
	{
		UserSession userSession = null;
		UserInfo user = userManagerService.getUserByLoginName(loginName, false);
		if(user == null)
		{
			return userSession;
		}
		if("".equals(StringUtils.checkNullString(user.getPwd())))
		{
			return userSession;
		}
		if(!isVerify || StringUtils.checkNullString(pwd).equals(userManagerService.decodePwd(StringUtils.checkNullString(user.getPwd()))))
		{
			userSession = new UserSession();
			if("1".equals(StringUtils.checkNullString(user.getStatus())))
			{
				userSession = this.getUserSession(loginName); // 登录时获取所有userSession信息
				long currentTime = System.currentTimeMillis() / 1000;
				user.setLastlogintime(currentTime);
				this.saveUserLoginInfo(loginName, "login");
				userSession.setLoginDate(TimeUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				userManagerDao.saveOrUpdate(user);
			}
		}
		return userSession;
	}
	
	public String getCheckCode(String loginName)
	{
		String checkcode = "";
		UserInfo userInfo = userManagerService.getUserByLoginName(loginName, false);
		String phone = userInfo == null ? "" : userInfo.getMobile();
		int code = (int) (Math.random() * 999999);
		checkcode = String.format("%1$06d", code);
		DataAdapter dataAdapter = new DataAdapter();
		String sql = "insert into bs_t_sm_smsmonitor (pid,content,systemtype,goal,sendflag,relateid,pri,infoinputtime) values (?,?,?,?,0,SMSRELAEID.Nextval,?,?)";
		Object[] values = {UUIDGenerator.getUUIDoffSpace(), "您的eoms系统登录验证码为：" + checkcode, "登录短信验证", phone, "0", TimeUtils.getCurrentTime()};
		int n = 0;
		try {
			if(!"".equals(phone))
				n = dataAdapter.execute(sql, values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkcode;
	}

	public boolean saveUserLoginInfo(String loginname, String type)
	{
		boolean result = false;
		String loginLog = StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.loginlog"));
		if(!"true".equals(loginLog))
			return true;
		if("".equals(StringUtils.checkNullString(loginname)) || "".equals(StringUtils.checkNullString(type)))
		{
			return result;
		}
		try
		{
			String ip = "";
			HttpServletRequest request = ActionContext.getRequest();
			if(request != null){
				ip = StringUtils.checkNullString(WebUtils.getIpAddr(ActionContext.getRequest()));
			}
			UserLoginInfo userLoginInfo = null;
			userLoginInfo = new UserLoginInfo(UUIDGenerator.getUUIDoffSpace());
			userLoginInfo.setLoginname(loginname);
			userLoginInfo.setLogintime(TimeUtils.getCurrentTime());
			userLoginInfo.setLoginip(ip);
			userLoginInfo.setLogintype(type);
			loginInfoDao.save(userLoginInfo);
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isValidateLoginInfo(String loginName, String pwd)
	{
		boolean tag = false;
		if(!"".equals(StringUtils.checkNullString(loginName)))
		{
			tag = userManagerService.canLogin(loginName, StringUtils.checkNullString(pwd));
		}
		return tag;
	}
	
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	public void setDepManagerService(DepManagerService depManagerService) {
		this.depManagerService = depManagerService;
	}
	public void setDicManagerService(DicManagerService dicManagerService) {
		this.dicManagerService = dicManagerService;
	}
	public void setAttachmentManagerService(
			AttachmentManagerService attachmentManagerService) {
		this.attachmentManagerService = attachmentManagerService;
	}
	public void setUserManagerDao(IDao<UserInfo> userManagerDao) {
		this.userManagerDao = userManagerDao;
	}
	public void setLoginInfoDao(IDao<UserLoginInfo> loginInfoDao) {
		this.loginInfoDao = loginInfoDao;
	}
	public InsideSmsService getInsidesm() {
		return insidesm;
	}
	public void setInsidesm(InsideSmsService insidesm) {
		this.insidesm = insidesm;
	}
	/**
     * 校验省公司 地市公司 代维公司
     * @param depdns
     * @return 0 省公司  1 地市公司 2 代维
     */
    public int checkDept(String depdns){
        int ret = 2;
        try{
            Map<String,String> ha = Constants.CityDeptDNS;
            if(depdns != null && depdns.length() > 0){
                if(depdns.matches(Constants.ProvinceDeptDNS+".*")){
                    ret = 0;
                    Iterator<Entry<String, String>> it = ha.entrySet().iterator();
                    while(it.hasNext()){
                        Entry<String, String> entry = (Entry<String, String>)it.next();
                        
                        String [] vals = entry.getValue().split(";");
                        for(String tmp:vals){
                            if(depdns.matches(tmp+".*")){
                                ret = 1;
                                break;
                            }
                        }
                        if(ret == 1){
                            break;
                        }
                    } 
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ret;
    }
    
    public String getCityIdByDNS(String depDns){
        String city = "";
        int ret = 2;
        try{
            Map<String,String> ha = Constants.CityDeptDNS;
            if(depDns != null && depDns.length() > 0){
                if(depDns.matches(Constants.ProvinceDeptDNS+".*")){
                    ret = 0;
                    Iterator<Entry<String, String>> it = ha.entrySet().iterator();
                    while(it.hasNext()){
                        Entry<String, String> entry = (Entry<String, String>)it.next();
                        
                        String [] vals = entry.getValue().split(";");
                        for(String tmp:vals){
                            if(depDns.matches(tmp+".*")){
                                ret = 1;
                                city = entry.getKey();
                                break;
                            }
                        }
                        if(ret == 1){
                            break;
                        }
                    } 
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return city;
    }

/*	public void setJkBsRoleuserDao(IDao<JkBsRoleuser> jkBsRoleuserDao) {
		this.jkBsRoleuserDao = jkBsRoleuserDao;
	}*/
}
