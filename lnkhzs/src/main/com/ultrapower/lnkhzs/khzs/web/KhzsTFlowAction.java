package com.ultrapower.lnkhzs.khzs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bmc.thirdparty.org.apache.commons.beanutils.BeanUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.khzs.model.KhzsTComment;
import com.ultrapower.lnkhzs.khzs.model.KhzsTFlow;
import com.ultrapower.lnkhzs.khzs.model.KhzsTMain;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTFlowService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.utils.DateUtils;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsTFlowAction extends BaseAction {

    private static final long serialVersionUID = -1L;
    
    private UserSession selectUser;
    private KhzsTMain khzsTMain;
    //0保存，1提交，2退回，3转派，4挂起 5金点子评价
  	private String actionStr;
  	//0员工提交 ，1内容审核，2部门处理，3部门处理（挂起），4专员处理，5专员处理（挂起 ），6部门复核，7审核员复核，8员工评价，9关闭
  	
  	private KhzsTFlow khzsTFlow;
  	
  	private KhzsTComment khzsTComment;


	public KhzsTFlow getKhzsTFlow() {
		return khzsTFlow;
	}

	public void setKhzsTFlow(KhzsTFlow khzsTFlow) {
		this.khzsTFlow = khzsTFlow;
	}

	public void setKhzsTFlowService(IKhzsTFlowService khzsTFlowService) {
		this.khzsTFlowService = khzsTFlowService;
	}

	private IKhzsTFlowService khzsTFlowService;

    public UserSession getSelectUser() {
		return selectUser;
	}

	public void setSelectUser(UserSession selectUser) {
		this.selectUser = selectUser;
	}

	public KhzsTMain getKhzsTMain() {
		return khzsTMain;
	}

	public void setKhzsTMain(KhzsTMain khzsTMain) {
		this.khzsTMain = khzsTMain;
	}

	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}


	public void setKhzstflowservice(IKhzsTFlowService khzsTFlowService) {
        this.khzsTFlowService = khzsTFlowService;
    }
	
    public KhzsTComment getKhzsTComment() {
		return khzsTComment;
	}

	public void setKhzsTComment(KhzsTComment khzsTComment) {
		this.khzsTComment = khzsTComment;
	}

    public void doAction() {
    	
    	UserSession user = super.getUserSession();
    	UserSession userAdmin = khzsTFlowService.getUserAdmin(khzsTMain.getType());
    	String mainId=khzsTMain.getPid();

    	UserSession createUser =new UserSession();
    	createUser.setDepFullName(khzsTMain.getDepname());
    	createUser.setFullName(khzsTMain.getFullname());
    	createUser.setLoginName(khzsTMain.getLoginname());
    	
    	String status =khzsTMain.getFlowstatus();
    	if(status==null||status.length()==0) {
    		status="员工提交";
    		khzsTMain.setFlowstatus("员工提交");
    		khzsTMain.setDepname(user.getDepFullName());
			khzsTMain.setLoginname(user.getLoginName());
			khzsTMain.setFullname(user.getFullName());
    	}
    	
    	UserSession depAdmin = khzsTFlowService.getDepAdmin(khzsTMain.getDutydeptid());
    	khzsTFlow.setLaststatus(status);
    	if(status.equals("员工提交")) {//员工提交
    		if(actionStr.equals("0")) {//保存
    			khzsTFlowService.saveMain(khzsTMain);
    		}else if(actionStr.equals("1")) {//提交
    			khzsTFlowService.saveMainBak(khzsTMain);
    			khzsTMain.setCreatetime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
    			String index = khzsTFlowService.getIndex(khzsTMain.getType());
    			khzsTMain.setIdx(index);
    			doFlow("内容审核", user, userAdmin, mainId);//1内容审核
    		}
    	}else if(status.equals("内容审核")) {//1内容审核
    		if(actionStr.equals("0")) {//保存
    			khzsTFlowService.saveMain(khzsTMain);
    		}else if(actionStr.equals("1")) {//提交
    			if(depAdmin==null||depAdmin.getLoginName()==null||depAdmin.getLoginName().equals("")) {
    				renderText("您选择的部门没有对应的管理员，请核对后重新输入！");
    				return;
    			}else {
    				khzsTMain.setDutyuser(depAdmin.getLoginName());
    				khzsTMain.setDutyuserid(depAdmin.getLoginName());
    				doFlow("部门处理", user, depAdmin, mainId);//2主管部门处理
    			}
    		}else if(actionStr.equals("2")) {//退回
    			doFlow("员工提交", user, createUser, mainId);//0草稿
    		}
    		
    	}else if(status.equals("部门处理")) {//2主管部门处理
    		if(actionStr.equals("1")) {//提交
    			doFlow("审核员复核", user, userAdmin, mainId);//7审核员复核
    		}else if(actionStr.equals("3")) {//转派
    			doFlow("专员处理", user, selectUser, mainId);//4专员处理
    		}else if(actionStr.equals("4")) {//挂起
    			doFlow("部门处理（挂起）", user, user, mainId);//3部门处理（挂起）
    		}
    	}else if(status.equals("部门处理（挂起）")) {//3部门处理（挂起）
    		if(actionStr.equals("1")) {//提交
    			doFlow("审核员复核", user, userAdmin, mainId);//7审核员复核
    		}else if(actionStr.equals("3")) {//转派
    			doFlow("专员处理", user, selectUser, mainId);//4专员处理
    		}
    	}else if(status.equals("专员处理")) {//4专员处理
    		if(actionStr.equals("1")) {//提交
    			doFlow("部门复核", user, depAdmin, mainId);//部门复核
    		}else if(actionStr.equals("3")) {//转派
    			doFlow("专员处理", user, selectUser, mainId);//4专员处理
    		}else if(actionStr.equals("4")) {//4挂起
    			doFlow("专员处理（挂起 ）", user, user, mainId);//5专员处理（挂起 ）
    		}
    		
    	}else if(status.equals("专员处理（挂起 ）")) {//5专员处理（挂起 ）
    		if(actionStr.equals("1")) {//提交
    			doFlow("部门复核", user, depAdmin, mainId);//部门复核
    		}else if(actionStr.equals("3")) {//转派
    			doFlow("专员处理", user, selectUser, mainId);//4专员处理
    		}
    		
    	}else if(status.equals("部门复核")) {//6部门复核
    		if(actionStr.equals("1")) {//提交
    			doFlow("审核员复核", user, userAdmin, mainId);//7审核员复核
    		}else if(actionStr.equals("3")) {//转派		
    			doFlow("专员处理", user, selectUser, mainId);
    		}else if(actionStr.equals("4")) {//挂起
    			doFlow("部门处理（挂起）", user, user, mainId);//3部门处理（挂起）
    		}
    		
    	}else if(status.equals("审核员复核")) {//7审核员复核
    		if(actionStr.equals("1")) {//提交
    			doFlow("员工评价", user, createUser, mainId);//8员工评价
    		}else if(actionStr.equals("2")) {//退回
    			doFlow("部门处理", user, depAdmin, mainId);//2主管部门处理
    		}else if(actionStr.equals("5")) {//评价
    			khzsTMain.setExample("公开（可评论）");
    			doFlow("关闭", user, null, mainId);//2主管部门处理
    		}
    	}else if(status.equals("员工评价")) {//8员工评价
    		if(actionStr.equals("1")) {//提交
    			khzsTMain.setExample("公开（可评论）");
    			doFlow("关闭", user, null, mainId);//关闭
    		}
    	}
    	renderText("success");
    	
    }
    
    public void doFlow(String status,UserSession user,UserSession nextuser,String mainId) {
    	khzsTMain.setFlowstatus(status);
    	if(nextuser !=null) {
			khzsTMain.setNextdepname(nextuser.getDepFullName());
			khzsTMain.setNextfullname(nextuser.getFullName());
			khzsTMain.setNextloginname(nextuser.getLoginName());
    	}
		khzsTFlowService.saveMain(khzsTMain);
		khzsTFlow.setStatus(status);
		khzsTFlow=getFlowContent(khzsTFlow,user,nextuser,mainId);
		khzsTFlowService.saveFlow(khzsTFlow);
		if(!status.contains("审核")) {
			try {
				String msg = "您好,主题为:"+khzsTMain.getTheme()+"编号为:"+khzsTMain.getIdx()+"的"+khzsTMain.getType()+"请您处理,当前状态为:"+khzsTMain.getFlowstatus();
				khzsTFlowService.sendSms(nextuser, msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }

    public KhzsTFlow getFlowContent(KhzsTFlow khzsTFlow,UserSession user, UserSession nextuser,String vocpid) {
    	khzsTFlow.setPid(UUIDGenerator.getUUIDoffSpace());
    	khzsTFlow.setCreatetime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
    	khzsTFlow.setLoginname(user.getLoginName());
    	khzsTFlow.setFullname(user.getFullName());
    	if(nextuser!=null) {
    		khzsTFlow.setNextdepname(nextuser.getDepFullName());
        	khzsTFlow.setNextfullname(nextuser.getFullName());
        	khzsTFlow.setNextloginname(nextuser.getLoginName());
    	}
    	
    	khzsTFlow.setDepname(user.getDepFullName());
    	khzsTFlow.setVocpid(vocpid);
    	return khzsTFlow;
    }
    
    
    public void ajaxGetKhzsTMain(){
    	khzsTMain = khzsTFlowService.getKhzsTMain(khzsTMain.getPid());
		renderJson(khzsTMain);
    }
    
    public void ajaxGetKhzsTFlow(){
    	List<KhzsTFlow> khzsTFlowList = khzsTFlowService.getKhzsTFlowList(khzsTMain.getPid());
		renderJson(khzsTFlowList);
    }
    
    public void ajaxGetKhzsTComment(){
    	List<KhzsTComment> khzsTCommentList = khzsTFlowService.getKhzsTCommentList(khzsTMain.getPid());
		renderJson(khzsTCommentList);
    }
    
    public void deleteKhzsTMain(){
    	khzsTFlowService.deleteKhzsTMain(khzsTMain.getPid());
    	this.renderText("success");
    }
    
    public void saveKhzsTComment(){
    	UserSession user = super.getUserSession();
    	khzsTComment.setPid(UUIDGenerator.getUUIDoffSpace());
    	khzsTComment.setCreatetime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
    	khzsTComment.setLoginname(user.getLoginName());
    	khzsTComment.setFullname(user.getFullName());
    	khzsTFlowService.saveKhzsTComment(khzsTComment);
    }
    
    public void reSetIndex(){
    	khzsTFlowService.reSetIndex();
    }
    
    public void szalk(){
    	KhzsTMain khzsTMain1 = khzsTFlowService.getKhzsTMain(khzsTMain.getPid());
    	khzsTMain1.setExample(khzsTMain.getExample());
    	khzsTFlowService.saveMain(khzsTMain1);
    	this.renderText("success");
    }
    
    public void ajaxGetRanking1(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter rq = new QueryAdapter();
		DataTable dt = rq.executeQuery("select u.fullname ,d.depname,t.cnt from (select loginname,count(1) cnt from zl_khzs_t_main  where type = '金点子' group by loginname) t ,bs_t_sm_user u,bs_t_sm_dep d where t.loginname = u.loginname and u.dleveltwoid = d.pid order by t.cnt Desc");
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("fullname", dr.getString("fullname"));
			map.put("depname", dr.getString("depname"));
			map.put("cnt", dr.getString("cnt"));
			result.add(map);
		}
		renderJson(result);
    }
    
    public void ajaxGetRanking2(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter rq = new QueryAdapter();
		DataTable dt = rq.executeQuery("select d.depname,count(1) cnt from zl_khzs_t_main m,bs_t_sm_user u,bs_t_sm_dep d where m.loginname = u.loginname and u.dleveltwoid = d.pid and  m.type = '金点子' group by d.depname order by cnt Desc");
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("depname", dr.getString("depname"));
			map.put("cnt", dr.getString("cnt"));
			result.add(map);
		}
		renderJson(result);
    }
    
}
