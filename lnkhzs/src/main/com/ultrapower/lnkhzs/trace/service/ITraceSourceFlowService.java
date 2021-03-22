package com.ultrapower.lnkhzs.trace.service;

import java.util.List;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.trace.model.TraceSourceFlow;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;

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
public interface ITraceSourceFlowService {

	void saveMain(TraceSourceList traceSourceList);

	void saveFlow(TraceSourceFlow traceSourceFlow);

	UserSession getDepAdmin(String depid);

	String getIndex();

	void reSetIndex();

	List<TraceSourceFlow> getFlowList(String pid);

	void sendSms(String loginName, String string);

	UserSession getRectification(String data);

	UserSession getDoBacker(String data, String loginname);

	UserSession getDepAdminByLoginName(String loginName);

	TraceSourceFlow getLastFlow(String sourcepid);

	String getMessage(List<TraceSourceList> sList);

	void DraftWorkFlowAuto(String sql, String str, String string, UserSession user) throws Exception;

	TraceSourceFlow getLastFlow2(String sourcepid);

}
