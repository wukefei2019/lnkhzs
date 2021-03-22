package com.ultrapower.lnkhzs.khzs.manager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.bmc.thirdparty.org.apache.commons.beanutils.BeanUtils;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.sms.service.InsideSmsService;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.lnkhzs.khzs.model.KhzsTComment;
import com.ultrapower.lnkhzs.khzs.model.KhzsTDepadmin;
import com.ultrapower.lnkhzs.khzs.model.KhzsTFlow;
import com.ultrapower.lnkhzs.khzs.model.KhzsTMain;
import com.ultrapower.lnkhzs.khzs.model.KhzsTMainBak;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTFlowService;
import com.ultrapower.omcs.common.utils.SmsSendUtils;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsTFlowMgrImpl implements IKhzsTFlowService {

	private UserManagerService userManagerService;
	
    private IDao<KhzsTFlow> khzsTFlowDao;
    
    private IDao<KhzsTComment> khzsTCommentDao;
    
    private IDao<KhzsTDepadmin> khzsTDepadminDao;
    
    private IDao<KhzsTMain> khzsTMainDao;
    
    private IDao<KhzsTMainBak> hzsTMainBakDao;
    
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	
	public void setKhzstflowdao(IDao<KhzsTFlow> khzsTFlowDao) {
        this.khzsTFlowDao = khzsTFlowDao;
    }

    public void saveMain(KhzsTMain khzsTMain) {
    	if(khzsTMainDao.get(khzsTMain.getPid())==null) {
    		khzsTMainDao.save(khzsTMain);
    	}else {
    		khzsTMainDao.saveOrUpdate(khzsTMain);
    	}
    	
    }

    public void saveFlow(KhzsTFlow khzsTFlow) {
    	khzsTFlowDao.save(khzsTFlow);
    }
    
    public UserSession getUserAdmin() {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPFULLNAME FROM ZL_KHZS_T_ADMIN" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    public UserSession getUserAdmin(String type) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPFULLNAME FROM ZL_KHZS_T_ADMIN where type like '%"+type+"%'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    public UserSession getDepAdmin(String depid) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPFULLNAME FROM ZL_KHZS_T_DEPADMIN WHERE DEPID ='"+depid+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    public void saveMainBak(KhzsTMain khzsTMain) {
    	KhzsTMainBak khzsTMainBak=new KhzsTMainBak();
    	try {
			BeanUtils.copyProperties(khzsTMainBak, khzsTMain);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	hzsTMainBakDao.save(khzsTMainBak);
    }
    
    public String getIndex(String type) {
    	String indexType="S_";
    	String index="";
    	if("典型投诉案例".equals(type)) {
    		indexType ="C_";
    	}else if("金点子".equals(type)) {
    		indexType ="G_";
    	}
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append("select '"+indexType+"'||to_char(sysdate,'yyyyMMdd')||'_' || replace(lpad("+indexType+"SEQ.nextval,4,'0'),'','0') from dual" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	if(datatable.getRowList().size()>0) {
    		index=datatable.getRowList().get(0).getString(0);
    	}
    	return index;
    }
    
    @SuppressWarnings("unchecked")
	public List<KhzsTFlow> getKhzsTFlowList(String pid){
    	return khzsTFlowDao.find("from KhzsTFlow where vocpid = ? order by createtime", pid);
    }

	public IDao<KhzsTFlow> getKhzsTFlowDao() {
		return khzsTFlowDao;
	}

	public void setKhzsTFlowDao(IDao<KhzsTFlow> khzsTFlowDao) {
		this.khzsTFlowDao = khzsTFlowDao;
	}

	public IDao<KhzsTComment> getKhzsTCommentDao() {
		return khzsTCommentDao;
	}

	public void setKhzsTCommentDao(IDao<KhzsTComment> khzsTCommentDao) {
		this.khzsTCommentDao = khzsTCommentDao;
	}

	public IDao<KhzsTDepadmin> getKhzsTDepadminDao() {
		return khzsTDepadminDao;
	}

	public void setKhzsTDepadminDao(IDao<KhzsTDepadmin> khzsTDepadminDao) {
		this.khzsTDepadminDao = khzsTDepadminDao;
	}

	public IDao<KhzsTMain> getKhzsTMainDao() {
		return khzsTMainDao;
	}

	public void setKhzsTMainDao(IDao<KhzsTMain> khzsTMainDao) {
		this.khzsTMainDao = khzsTMainDao;
	}

	public IDao<KhzsTMainBak> getHzsTMainBakDao() {
		return hzsTMainBakDao;
	}

	public void setHzsTMainBakDao(IDao<KhzsTMainBak> hzsTMainBakDao) {
		this.hzsTMainBakDao = hzsTMainBakDao;
	}

	@Override
	public KhzsTMain getKhzsTMain(String pid) {
		return khzsTMainDao.get(pid);
	}

	@Override
	public void deleteKhzsTMain(String pid) {
		khzsTMainDao.removeById(pid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KhzsTComment> getKhzsTCommentList(String pid) {
		return khzsTCommentDao.find("from KhzsTComment where vocpid = ? order by createtime", pid);
	}

	@Override
	public void saveKhzsTComment(KhzsTComment khzsTComment) {
		khzsTCommentDao.save(khzsTComment);
	}
	

	public void reSetIndex() {
		QueryAdapter q = new QueryAdapter();
    	try {
			q.executeUpdate("DROP SEQUENCE S_SEQ");
			q.executeUpdate("DROP SEQUENCE C_SEQ");
			q.executeUpdate("DROP SEQUENCE G_SEQ");
	    	q.executeUpdate(" CREATE sequence S_SEQ" + 
	    			" minvalue 1" + 
	    			" maxvalue 999999999" + 
	    			" start with 1" + 
	    			" increment by 1" + 
	    			" cache 20" + 
	    			" cycle");
	    	q.executeUpdate("create sequence C_SEQ" + 
	    			" minvalue 1" + 
	    			" maxvalue 999999999" + 
	    			" start with 1" + 
	    			" increment by 1" + 
	    			" cache 20" + 
	    			" cycle");
	    	q.executeUpdate("create sequence G_SEQ" + 
	    			" minvalue 1" + 
	    			" maxvalue 999999999" + 
	    			" start with 1" + 
	    			" increment by 1" + 
	    			" cache 20" + 
	    			" cycle");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSms(UserSession user, String msg) {
		UserInfo userInfo = userManagerService.getUserByLoginName(user.getLoginName(), false);
		String phone = userInfo == null ? "" : userInfo.getMobile();
		SmsSendUtils.smsSend(UUIDGenerator.getUUIDoffSpace(), phone,msg);
	}

}
