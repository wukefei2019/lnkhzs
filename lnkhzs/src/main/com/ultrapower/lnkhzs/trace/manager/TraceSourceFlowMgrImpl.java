package com.ultrapower.lnkhzs.trace.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;
import org.hibernate.Query;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpDataSendManager;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.lnkhzs.trace.model.TraceSourceFlow;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.lnkhzs.trace.model.TraceSourceSendFlow;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceFlowService;
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
public class TraceSourceFlowMgrImpl implements ITraceSourceFlowService {

    Properties prop = new Properties();
    private static String namespace;
	
	private UserManagerService userManagerService;
    
    private IDao<TraceSourceFlow> traceSourceFlowDao;
    
    private IDao<TraceSourceList> traceSourceListDao;
    
    private IDao<TraceSourceSendFlow> traceSourceSendFlowDao;
    
	public IDao<TraceSourceSendFlow> getTraceSourceSendFlowDao() {
		return traceSourceSendFlowDao;
	}

	public void setTraceSourceSendFlowDao(IDao<TraceSourceSendFlow> traceSourceSendFlowDao) {
		this.traceSourceSendFlowDao = traceSourceSendFlowDao;
	}

	public void setTraceSourceFlowDao(IDao<TraceSourceFlow> traceSourceFlowDao) {
		this.traceSourceFlowDao = traceSourceFlowDao;
	}

	public void setTraceSourceListDao(IDao<TraceSourceList> traceSourceListDao) {
		this.traceSourceListDao = traceSourceListDao;
	}

	public void saveTraceSourceList(TraceSourceList traceSourceList) {
		traceSourceListDao.saveOrUpdate(traceSourceList);
    }

    public void saveFlow(TraceSourceFlow traceSourceFlow) {
    	traceSourceFlowDao.save(traceSourceFlow);
    }
    
    public void saveMain(TraceSourceList traceSourceList) {
    	traceSourceListDao.save(traceSourceList);
    }

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	public UserSession getDepAdmin(String deplevel2name) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPNAME FROM ZL_TRACE_SOURCE_DEPADMIN WHERE deplevel2name ='"+deplevel2name+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
	public UserSession getDepAdminByLoginName(String loginName) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPNAME FROM ZL_TRACE_SOURCE_DEPADMIN WHERE LOGINNAME ='"+loginName+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    public String getIndex() {
    	String indexType="SY_";
    	String index="";
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append("select '"+indexType+"'||to_char(sysdate,'yyyyMM')||'_' || replace(lpad("+indexType+"SEQ.nextval,4,'0'),'','0') from dual" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	if(datatable.getRowList().size()>0) {
    		index=datatable.getRowList().get(0).getString(0);
    	}
    	return index;
    }
    
    @SuppressWarnings("unchecked")
	public List<TraceSourceFlow> getFlowList(String pid){
    	return traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ? order by createtime", pid);
    }



	public void reSetIndex() {
		QueryAdapter q = new QueryAdapter();
    	try {
			q.executeUpdate("DROP SEQUENCE SY_SEQ");

	    	q.executeUpdate(" CREATE sequence SY_SEQ" + 
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
	public void sendSms(String loginName, String msg) {
		QueryAdapter q = new QueryAdapter();
		UserInfo userInfo = userManagerService.getUserByLoginName(loginName, false);
		String phone = userInfo == null ? "" : userInfo.getMobile();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataTable dt = q.executeQuery("select * from ZL_TRACE_SOURCE_SEND_FLOW where sendPhone = ? AND to_date(to_char(createtime),'yyyy-mm-dd hh24:mi:ss')  > (sysdate - 1/24 ) and to_date(to_char(createtime),'yyyy-mm-dd hh24:mi:ss')  <  sysdate ",phone);
		if(dt.getRowList().size()>0){
			System.out.println(loginName+"一小时内短信重复，重复内容："+msg);
		}else{
			TraceSourceSendFlow t = new TraceSourceSendFlow();
			t.setCreatetime(sdf.format(new Date()));
			t.setPid(UUIDGenerator.getUUIDoffSpace());
			t.setSendmsg(msg);
			t.setSendname(loginName);
			t.setSendphone(phone);
			traceSourceSendFlowDao.save(t);
			SmsSendUtils.smsSend(UUIDGenerator.getUUIDoffSpace(), phone,msg);
		}
	}

	@Override
	public UserSession getRectification(String data) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" select * from  (select t.loginname,t.fullname from zl_trace_source_flow t where t.sourcepid ='"+data+"' order by t.createtime Desc) where  rownum =1" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
    	}
    	return user;
	}

	@Override
	public UserSession getDoBacker(String data, String loginname) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" select * from  (select t.loginname,t.fullname from zl_trace_source_flow t where t.sourcepid ='"+data+"' and nextLoginname = '"+loginname+"' order by t.createtime ) where  rownum =1" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
    	}
    	return user;
	}

	@Override
	public TraceSourceFlow getLastFlow(String sourcepid) {
    	List<TraceSourceFlow> list = traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ? and status <> '整改中' order by createtime Desc",sourcepid);
		if(list.isEmpty())return null;
		return list.get(0);
	}

	@Override
	public TraceSourceFlow getLastFlow2(String sourcepid) {
    	List<TraceSourceFlow> list = traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ?  order by createtime Desc",sourcepid);
		if(list.isEmpty())return null;
		return list.get(0);
	}
	
	public String getMessage(List<TraceSourceList> list){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
    	String time = sdf.format(new Date());
    	Set<String> set = new HashSet<String>();
    	String str = "";
//    	int count = 0;
    	for(TraceSourceList st : list){
    		set.add(st.getTraceSourceType());
    	}
    	String msg = set.toString();
    	msg = msg.substring(1,msg.length()-1);
    	str = time +msg;//+count+"";
		return str;
    }
	
	public void init(){
		try {
			prop = PropertiesLoaderUtils.loadAllProperties("lnfwzlftp.properties");
			namespace=prop.getProperty("namespace");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void DraftWorkFlowAuto(String sql, String respDept,String str,UserSession userSession) throws Exception{
    	init();
		String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/TraceToTheSourceFlow/"+RandomUtils.getRandomN(6);
    	FtpDataSendManager.sendDataFile(prop.getProperty("ftpIp"), null, prop.getProperty("userName"), prop.getProperty("passWord"), 
    			path, "lnfwzlsy", sql, "lnfwzlsy.xlsx", 2, 1, null);
		StringBuffer sb =new StringBuffer();
		sb.append("<detail>");
		sb.append("<fieldRef>");
		sb.append("<fieldInfo><fieldChName>标题</fieldChName><fieldEnName>ApplyTitle</fieldEnName>")
		.append("<fieldType>string</fieldType><fieldValue>"+str+"</fieldValue></fieldInfo>");
		sb.append("<fieldInfo><fieldChName>说明</fieldChName><fieldEnName>Instruction</fieldEnName>")
		.append("<fieldType>string</fieldType><fieldValue>"+str+"，详情请查看附件</fieldValue></fieldInfo>");
		sb.append("</fieldRef>");
		sb.append("<attachRef><attachInfo><path>"+path+"</path></attachInfo></attachRef>");
		sb.append("</detail>");
		System.out.println("报文："+sb.toString());
		Service   service   =   new   Service();   
		Call call = null;
		try 
		{
			call = (Call)service.createCall();
			call.setTargetEndpointAddress(prop.getProperty("url"));
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace+"DraftWorkFlowAuto");
			call.setOperationName(new QName(namespace, "DraftWorkFlowAuto"));
			
			call.setReturnType(XMLType.XSD_STRING);   
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}	
		
		String result="";
		OperationDesc opDesc = new OperationDesc();
		opDesc.addParameter(new QName(namespace, "draftLoginName"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
		opDesc.addParameter(new QName(namespace, "alias"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
		opDesc.addParameter(new QName(namespace, "formDetail"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
		opDesc.addParameter(new QName(namespace, "approveMan"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
		opDesc.addParameter(new QName(namespace, "activityName"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
		opDesc.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
		call.setOperation(opDesc);
		result = (String)call.invoke(new Object[] {userSession.getLoginName(),"TraceToTheSourceFlow",sb.toString(),getApproveMan(respDept),"部门领导审批"});
		System.out.println("----------------------------------------TraceToTheSourceFlow:"+result);
    }
    
    private String getApproveMan(String respDept){
    	QueryAdapter qa = new QueryAdapter();
    	DataTable dt = qa.executeQuery("SELECT * FROM (SELECT  b.loginname FROM BS_T_SM_DEP a INNER JOIN BS_T_SM_USER b ON a.pid = b.DEPID WHERE (a.depname = '"+respDept+"' OR a.depfullname = '"+respDept+"') AND a.parentid = '31000000' AND b.loginname IS NOT NULL ORDER BY a.ORDERNUM, b.ORDERNUM ) WHERE ROWNUM =1");
    	List<DataRow> rowList = dt.getRowList();
    	Iterator<DataRow> it = rowList.iterator();
    	String loginname = "";
    	while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			loginname = dr.getString("loginname");
		}
    	return loginname;
    }
}
