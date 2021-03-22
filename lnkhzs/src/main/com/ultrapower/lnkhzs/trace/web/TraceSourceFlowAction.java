package com.ultrapower.lnkhzs.trace.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.trace.model.TraceSourceFlow;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceFlowService;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceListService;
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
public class TraceSourceFlowAction extends BaseAction {

    private static final long serialVersionUID = -1L;
    
    private UserSession selectUser;
    private TraceSourceList traceSourceList;
    
    private String loginName;
    
    private String fullName;
    
    private String dealtime;
    
    // 0转派  1完成
  	private String actionStr;
  	 	
  	private TraceSourceFlow traceSourceFlow;
  	
    public String  data;

    private HashMap<String,String> params;
    
    private String rQueryName;
    
    private ITraceSourceListService traceSourceListService;
  	
	private ITraceSourceFlowService traceSourceFlowService;

    public void setTraceSourceFlowService(ITraceSourceFlowService traceSourceFlowService) {
		this.traceSourceFlowService = traceSourceFlowService;
	}

	public UserSession getSelectUser() {
		return selectUser;
	}

	public void setSelectUser(UserSession selectUser) {
		this.selectUser = selectUser;
	}

	public TraceSourceList getTraceSourceList() {
		return traceSourceList;
	}

	public void setTraceSourceList(TraceSourceList traceSourceList) {
		this.traceSourceList = traceSourceList;
	}

	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	public String getrQueryName() {
		return rQueryName;
	}
	public void setrQueryName(String rQueryName) {
		this.rQueryName = rQueryName;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ITraceSourceListService getTraceSourceListService() {
		return traceSourceListService;
	}

	public void setTraceSourceListService(ITraceSourceListService traceSourceListService) {
		this.traceSourceListService = traceSourceListService;
	}

	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}

    public TraceSourceFlow getTraceSourceFlow() {
		return traceSourceFlow;
	}

	public void setTraceSourceFlow(TraceSourceFlow traceSourceFlow) {
		this.traceSourceFlow = traceSourceFlow;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDealtime() {
		return dealtime;
	}

	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}

	public void selectAll(){
		try{
			UserSession user = super.getUserSession();
			String code = traceSourceFlowService.getIndex();
			RQuery rq = new RQuery(rQueryName, params);
			List<TraceSourceList> List = rq.transformTuple(TraceSourceList.class);
//			List<TraceSourceList> List = traceSourceListService.getSelectAll();
			Map<String,String> map = new HashMap<String,String>();
			for(TraceSourceList t : List){
				String value = map.get(t.getRespDept())==null?t.getPid():map.get(t.getRespDept())+","+t.getPid();
				map.put(t.getRespDept(), value);
			}
			Set<String> keySet = map.keySet();
			UserSession depAdmin = null;
			for(String str : keySet){
				List<TraceSourceList> sList = traceSourceListService.getSelections(map.get(str));
				for(TraceSourceList st : sList){
					if(StringUtils.isBlank(loginName)){
						depAdmin = traceSourceFlowService.getDepAdmin(str);
					}else{
						depAdmin = traceSourceFlowService.getDepAdminByLoginName(loginName);
					}
					if(depAdmin==null||depAdmin.getLoginName()==null||depAdmin.getLoginName().equals("")) {
						renderText("您选择的"+(st.getRespDept()==null?"部门":st.getRespDept())+"没有对应的管理员，请核对后重新输入！");
						return;
					}else{
						st.setDealtime(data);
						st.setStatus("处理中");
						st.setCode(code);
						doFlow("处理中", user, depAdmin, st);
					}
				}
				String message = traceSourceFlowService.getMessage(sList)+"新发起溯源问题清单";
				String sql = "SELECT TRACE_SOURCE_TIME,TRACE_SOURCE_TYPE,QUESTION_SUBCATEGORY,CONCRETE_PROBLEMS,RESP_DEPT,AUXILIARY_DEPARTMENT,"
						+ "TRIGGER_COMPLAINTS_NUM,TRIGGER_WANTOU_RATIO FROM ZL_TRACE_SOURCE_LIST where code = ('"+code+"')";
				traceSourceFlowService.DraftWorkFlowAuto(sql,str,message,user);
				traceSourceFlowService.sendSms(depAdmin.getLoginName(), message+",请于2个工作日内处理完毕。");
			}
			renderText("success");
		}catch(Exception e){
			e.printStackTrace();
			renderText("error");
		}
	}
	
	public void batchRelay(){
		UserSession user = super.getUserSession();
		selectUser = new UserSession();
		selectUser.setLoginName(loginName);
		selectUser.setFullName(fullName);
		if(isNotAdmin(selectUser)){
			JSONArray json = new JSONArray();
			List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
			for(TraceSourceList trace :list){
				TraceSourceFlow lastFlow = traceSourceFlowService.getLastFlow2(trace.getPid());
				if(user.getLoginName().equals(lastFlow.getNextloginname())&&StringUtils.isNotBlank(loginName))doFlow("处理中", user, selectUser, trace);
			}
			String message = "您好，您有新的问题溯源待办，请您及时登录服务质量管理平台处理，请于2个工作日内处理完毕。";
			traceSourceFlowService.sendSms(selectUser.getLoginName(), message);
			renderText("success");
		}else{
			renderText("不能转派给管理员！");
		}
	}
	
	public void batchRectify(){
		UserSession user = super.getUserSession();
		JSONArray json = new JSONArray();
		List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
		Set<String> set = new HashSet<String>();
		for(TraceSourceList st:list){
			TraceSourceList trace = traceSourceListService.getByID(st.getPid());
			trace.setStatus("整改中");
			UserSession upper = traceSourceFlowService.getRectification(st.getPid());
			doFlow("整改中", user, upper, trace);
			set.add(trace.getPid());
		}
//		String ids = set.toString();
//		ids = ids.substring(1,ids.length()-1).replace(",", "','")+"'";
//		List<TraceSourceList> list2 = traceSourceListService.getByCode(trace.getCode());
//		System.out.println("list..............:"+list.isEmpty());
//		if(list.isEmpty()){
//			String sql = "SELECT l.TRACE_SOURCE_TIME,l.TRACE_SOURCE_TYPE,l.QUESTION_SUBCATEGORY,l.CONCRETE_PROBLEMS,l.RESP_DEPT,l.AUXILIARY_DEPARTMENT,"
//					+ "l.TRIGGER_COMPLAINTS_NUM,l.TRIGGER_WANTOU_RATIO,f.PROBLEM,f.RECTIFICATION_GOAL,f.NOT_FINISHED_REASON,f.NOT_COMPLETED_DESCRIPTION,"
//					+ "f.RESPONSIBLE_UNIT,f.RESPONSIBLE_DEPARTMENT,f.RESPONSIBLE_PERSON "
//					+ "FROM ZL_TRACE_SOURCE_LIST l , zl_trace_source_flow f  where l.pid = f.sourcepid and f.status = '整改中' and l.pid in ("+ids+")";
//			System.out.println("sql..............:"+sql);
//		}
//		String message = getMessage(list)+"遗留待解决溯源问题清单";
//		DraftWorkFlowAuto(sql,,message);
		renderText("success");
	}
	
	
	public void doAction(){
		try{
			UserSession user = super.getUserSession();
			if("提交".equals(actionStr)){
				JSONArray json = new JSONArray();
				List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
				Map<String,String> map = new HashMap<String,String>();
				for(TraceSourceList t : list){
					String value = map.get(t.getKey())==null?t.getPid():map.get(t.getKey())+","+t.getPid();
					map.put(t.getKey(), value);
				}
				Set<String> keySet = map.keySet();
				String code = traceSourceFlowService.getIndex();
				for(String str : keySet){
					UserSession depAdmin = null;
					List<TraceSourceList> sList = traceSourceListService.getSelections(map.get(str));
					if(StringUtils.isBlank(loginName)){
						depAdmin = traceSourceFlowService.getDepAdmin(str);
					}else{
						depAdmin = traceSourceFlowService.getDepAdminByLoginName(loginName);
					}
					for(TraceSourceList st : sList){
						if(StringUtils.isNotBlank(st.getStatus())){
							continue;
						}
						if(depAdmin==null||depAdmin.getLoginName()==null||depAdmin.getLoginName().equals("")) {
							renderText("您选择的"+(st.getRespDept()==null?"部门":st.getRespDept())+"没有对应的管理员，请核对后重新输入！");
							return;
						}else{
							st.setDealtime(dealtime);
							st.setStatus("处理中");
							st.setCode(code);
							doFlow("处理中", user, depAdmin, st);
						}
					}
					String message = traceSourceFlowService.getMessage(sList)+"新发起溯源问题清单";
					String param = "'"+map.get(str).replace(",", "','")+"'";
					String sql = "SELECT TRACE_SOURCE_TIME,TRACE_SOURCE_TYPE,QUESTION_SUBCATEGORY,CONCRETE_PROBLEMS,RESP_DEPT,AUXILIARY_DEPARTMENT,"
							+ "TRIGGER_COMPLAINTS_NUM,TRIGGER_WANTOU_RATIO FROM ZL_TRACE_SOURCE_LIST where pid in ("+param+")";
					traceSourceFlowService.DraftWorkFlowAuto(sql,str,message,user);
					traceSourceFlowService.sendSms(depAdmin.getLoginName(), message+",请于2个工作日内处理完毕。");
				}
			}
			else if("完成".equals(actionStr)) {
				TraceSourceList trace = traceSourceListService.getByID(traceSourceList.getPid());
				trace.setLookBackTime(traceSourceList.getLookBackTime());
				trace.setNetwork3Id(traceSourceList.getNetwork3Id());
				trace.setNetwork3Name(traceSourceList.getNetwork3Name());
				trace.setProblem(traceSourceList.getProblem());
				trace.setRectificationGoal(traceSourceList.getRectificationGoal());
				trace.setId(traceSourceList.getId());
				System.out.println("code..............:"+trace.getCode());
				UserSession upper = traceSourceFlowService.getDoBacker(traceSourceList.getPid(),user.getLoginName());
//				UserSession depAdmin = traceSourceFlowService.getDepAdmin(trace.getRespDept());
				if(isNotAdmin(upper)){
					trace.setStatus("处理中");
					doFlow("处理中", user, upper, trace);
				}else{
					trace.setStatus("待验证");
					doFlow("待验证", user, upper, trace);
				}
				String message = "您好，您有新的问题溯源待办，请您及时登录服务质量管理平台处理，请于2个工作日内处理完毕。";
				traceSourceFlowService.sendSms(upper.getLoginName(), message);
				List<TraceSourceList> list = traceSourceListService.getByCode(trace.getCode());
				System.out.println("list..............:"+list.isEmpty());
				if(list.isEmpty()){
					String sql = "SELECT l.TRACE_SOURCE_TIME,l.TRACE_SOURCE_TYPE,l.QUESTION_SUBCATEGORY,l.CONCRETE_PROBLEMS,l.RESP_DEPT,l.AUXILIARY_DEPARTMENT,"
							+ "l.TRIGGER_COMPLAINTS_NUM,l.TRIGGER_WANTOU_RATIO,f.PROBLEM,f.RECTIFICATION_GOAL,f.NOT_FINISHED_REASON,f.NOT_COMPLETED_DESCRIPTION,"
							+ "f.RESPONSIBLE_UNIT,f.RESPONSIBLE_DEPARTMENT,f.RESPONSIBLE_PERSON "
							+ "FROM ZL_TRACE_SOURCE_LIST l , zl_trace_source_flow f  where l.pid = f.sourcepid and f.status = '待验证' and l.code = '"+trace.getCode()+"'";
					System.out.println("sql..............:"+sql);
//    			DraftWorkFlowAuto(sql,"溯源问题【"+trace.getCode()+"】已完成");
				}
			}else if("整改".equals(actionStr)) {
				TraceSourceList trace = traceSourceListService.getByID(data);
				System.out.println("code..............:"+trace.getCode());
				trace.setStatus("整改中");
				trace.setProblem(traceSourceList.getProblem());
				trace.setRectificationGoal(traceSourceList.getRectificationGoal());
				UserSession upper = traceSourceFlowService.getRectification(data);
				doFlow("整改中", user, upper, trace);
				List<TraceSourceList> sList = new ArrayList<TraceSourceList>();
				sList.add(trace);
				String message = traceSourceFlowService.getMessage(sList)+"遗留待解决溯源问题清单";
				List<TraceSourceList> list = traceSourceListService.getByCode(trace.getCode());
				System.out.println("list..............:"+list.isEmpty());
				if(list.isEmpty()){   //回退是整个code里的都传到公务还是只这一条回传公务
					String sql = "SELECT l.TRACE_SOURCE_TIME,l.TRACE_SOURCE_TYPE,l.QUESTION_SUBCATEGORY,l.CONCRETE_PROBLEMS,l.RESP_DEPT,l.AUXILIARY_DEPARTMENT,"
							+ "l.TRIGGER_COMPLAINTS_NUM,l.TRIGGER_WANTOU_RATIO,f.PROBLEM,f.RECTIFICATION_GOAL,f.NOT_FINISHED_REASON,f.NOT_COMPLETED_DESCRIPTION,"
							+ "f.RESPONSIBLE_UNIT,f.RESPONSIBLE_DEPARTMENT,f.RESPONSIBLE_PERSON "
							+ "FROM ZL_TRACE_SOURCE_LIST l , zl_trace_source_flow f  where l.pid = f.sourcepid and f.status = '整改中' and l.pid = '"+trace.getPid()+"'";
					System.out.println("sql..............:"+sql);
					traceSourceFlowService.DraftWorkFlowAuto(sql,trace.getRespDept(),message,user);
					traceSourceFlowService.sendSms(upper.getLoginName(), message+"，需继续处理。");
				}
			}else if("验收".equals(actionStr)) {
				TraceSourceList trace = traceSourceListService.getByID(traceSourceList.getPid());
				System.out.println("code..............:"+trace.getCode());
				trace.setStatus("完成");
				trace.setCompletionStatus("已完成");
				trace.setProblem(traceSourceList.getProblem());
				trace.setRectificationGoal(traceSourceList.getRectificationGoal());
				doFlow("已完成", user, null, trace);
				List<TraceSourceList> list = traceSourceListService.getByCode(trace.getCode());
				System.out.println("list..............:"+list.isEmpty());
				if(list.isEmpty()){
					String sql = "SELECT l.TRACE_SOURCE_TIME,l.TRACE_SOURCE_TYPE,l.QUESTION_SUBCATEGORY,l.CONCRETE_PROBLEMS,l.RESP_DEPT,l.AUXILIARY_DEPARTMENT,"
							+ "l.TRIGGER_COMPLAINTS_NUM,l.TRIGGER_WANTOU_RATIO,f.PROBLEM,f.RECTIFICATION_GOAL,f.NOT_FINISHED_REASON,f.NOT_COMPLETED_DESCRIPTION,"
							+ "f.RESPONSIBLE_UNIT,f.RESPONSIBLE_DEPARTMENT,f.RESPONSIBLE_PERSON "
							+ "FROM ZL_TRACE_SOURCE_LIST l , zl_trace_source_flow f  where l.pid = f.sourcepid and f.status = '完成' and l.code = '"+trace.getCode()+"'";
					System.out.println("sql..............:"+sql);
//    			DraftWorkFlowAuto(sql,"溯源问题【"+trace.getCode()+"】已完成");
				}
			}else {
				TraceSourceList trace = traceSourceListService.getByID(traceSourceList.getPid());
				trace.setProblem(traceSourceList.getProblem());
				trace.setRectificationGoal(traceSourceList.getRectificationGoal());
				if(isNotAdmin(selectUser)){
					doFlow("处理中", user, selectUser, trace);
					String message = "您好，您有新的问题溯源待办，请您及时登录服务质量管理平台处理，请于2个工作日内处理完毕。";
					traceSourceFlowService.sendSms(selectUser.getLoginName(), message);
				}else{
					throw new Exception("不能转派给管理员！");
				}
			}
			renderText("success");
		}catch(Exception e){
			e.printStackTrace();
			renderText(e.getMessage());
		}
    }
    
    public void doFlow(String status,UserSession user,UserSession nextuser,TraceSourceList traceSourceList) {
        	if(nextuser !=null) {
    			traceSourceList.setLoginname(nextuser.getLoginName());
    			traceSourceList.setFullname(nextuser.getFullName());
        	}
        	traceSourceListService.saveOrUpdate(traceSourceList);
        	traceSourceFlow=getFlowContent(traceSourceList,user,nextuser,traceSourceList.getPid(),status);
        	traceSourceFlowService.saveFlow(traceSourceFlow);
    }

    private boolean isNotAdmin(UserSession nextuser) {
		// TODO Auto-generated method stub
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPNAME FROM ZL_TRACE_SOURCE_ADMIN WHERE LOGINNAME ='"+nextuser.getLoginName()+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	if(datatable.getRowList().size()>0)return false;
    	return true;
	}

	public TraceSourceFlow getFlowContent(TraceSourceList traceSourceList,UserSession user, UserSession nextuser,String sourcepid,String status) {
    	TraceSourceFlow traceSourceFlow2 = new TraceSourceFlow();
    	traceSourceFlow2.setPid(UUIDGenerator.getUUIDoffSpace());
    	if(traceSourceFlow!=null){
    		traceSourceFlow2.setNotCompletedDescription(traceSourceFlow.getNotCompletedDescription());
    		traceSourceFlow2.setNotFinishedReason(traceSourceFlow.getNotFinishedReason());
    		traceSourceFlow2.setProblem(traceSourceList.getProblem());
    		traceSourceFlow2.setRectificationGoal(traceSourceList.getRectificationGoal());
    		traceSourceFlow2.setResponsibleDepartment(traceSourceFlow.getResponsibleDepartment());
    		traceSourceFlow2.setResponsiblePerson(traceSourceFlow.getResponsiblePerson());
    		traceSourceFlow2.setResponsibleUnit(traceSourceFlow.getResponsibleUnit());
    		traceSourceFlow2.setRemark(traceSourceFlow.getRemark());
    	}
    	traceSourceFlow2.setLinkProgress(status);
    	traceSourceFlow2.setStatus(status);
    	traceSourceFlow2.setCreatetime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
    	traceSourceFlow2.setLoginname(user.getLoginName());
    	traceSourceFlow2.setFullname(user.getFullName());
    	if(nextuser!=null) {
    		traceSourceFlow2.setNextdepname(nextuser.getDepFullName());
    		traceSourceFlow2.setNextfullname(nextuser.getFullName());
    		traceSourceFlow2.setNextloginname(nextuser.getLoginName());
    	}
    	traceSourceFlow2.setDepname(user.getDeptLevel2Id());
    	traceSourceFlow2.setSourcepid(sourcepid);
    	return traceSourceFlow2;
    }
    
    public void ajaxGetFlow(){
    	List<TraceSourceFlow> flowList = traceSourceFlowService.getFlowList(traceSourceList.getPid());
		renderJson(flowList);
    }
    
    public void reSetIndex(){
    	traceSourceFlowService.reSetIndex();
    }
    
    public String addRelay(){
    	traceSourceFlow = traceSourceFlowService.getLastFlow(data);
    	traceSourceList = traceSourceListService.getByID(data);
		return SUCCESS;
	}
    
    public String addDone(){
    	traceSourceFlow = traceSourceFlowService.getLastFlow(data);
    	traceSourceList = traceSourceListService.getByID(data);
		return SUCCESS;
	}
    
}
