package com.ultrapower.lnkhzs.tsgd.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.ibm.db2.jcc.am.s;
import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.tsgd.model.SWork;
//import com.ultrapower.ftpinterface.service.FtpDataSendService;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.omcs.base.web.BaseAction;

public class IaSworkSendAction  extends BaseAction{


	//接口属性
	public static String formDetail;
	//	String(50)	是	流程标识GUID
	public static String processID;
	//ws命名空间
	public static String namespace = "http://tempuri.org/";
	
	//测试
	//public static String FTPIP = "10.64.93.11";
	//生产
	public static String FTPIP = "10.64.93.10";
	
	public static String FTPPORT = "21";
	public static String FTPUSER = "gwpt";
	public static String FTPPWD = "Gw!2013#";
	/*
	 * 
	 生产接口url：http://10.64.93.10/Workflow/WorkFlowOperate. asmx
	测试接口url： http://10.64.93.11/Workflow/WorkFlowOperate. asmx
				http://10.64.93.11:8089/Workflow/WorkFlowOperate. asmx
				http://10.64.93.12/Workflow/WorkFlowOperate. asmx
					
	生产附件ftp服务器：ftp://10.64.93.15/
	用户名：gwptxtnas
	密码：Gw!2013#
	测试附件ftp服务器：ftp://10.64.93.11/
	用户名：gwptxtnas
	密码：Gw!2013#
	*
	*/

	
	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getFormDetail() {
		return formDetail;
	}

	public void setFormDetail(String formDetail) {
		this.formDetail = formDetail;
	}
	
	//起草流程
	public static Map<String,Object> draftWorkFlow(SWork swork)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		//String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/ServiceSupervisor/"+RandomUtils.getRandomN(6);
		String result="";
		try{
			String sendto=swork.getSendto();
			sendto=sendto.replaceAll(",", ";");
			StringBuffer sb =new StringBuffer();
			sb.append("<detail>");
			sb.append("<fieldRef>");
			sb.append("<fieldInfo><fieldChName>督办事项</fieldChName><fieldEnName>ApplyTitle</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getSupervision_matters() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>派单人</fieldChName><fieldEnName>ApplyPerson</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getDispatch() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>派单部门</fieldChName><fieldEnName>ApplyDepartment</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getDepartment() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>申请时间</fieldChName><fieldEnName>ApplyTime</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getCreatetime() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>派单人电话</fieldChName><fieldEnName>ApplyPhone</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getDispatch_phone() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>编号</fieldChName><fieldEnName>ApplyNumber</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getSerial_number() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>督办时限</fieldChName><fieldEnName>LimitTime</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getTime_limit() + " 00:00:00</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>督办类别</fieldChName><fieldEnName>TaskType</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getType() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>主责部门</fieldChName><fieldEnName>LordDepartment</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getMaindepartfull() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>抄送</fieldChName><fieldEnName>CopySend</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getSendto() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>抄送</fieldChName><fieldEnName>CopySendID</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getSendtologin() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>督办问题描述</fieldChName><fieldEnName>ProblemDesc</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getDescription() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>督办问题来源</fieldChName><fieldEnName>ProblemSource</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getSource() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>督办目标</fieldChName><fieldEnName>Target</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(swork.getTarget() + "</fieldValue></fieldInfo>");
			sb.append("</fieldRef>");
			sb.append("<attachRef><attachInfo>") 
			.append("<path><![CDATA["+swork.getAppendix_address()+"]]></path></attachInfo></attachRef>");
			sb.append("</detail>");
			//System.out.println(sb.toString());
			map.put("formDetail", sb.toString());
			formDetail=sb.toString();
			UserSession userSession = ActionContext.getUserSession();
			//System.out.println("BusinessNotice"+"-------------------------------formDetail:"+formDetail);
			System.out.println("userLoginName:"+userSession.getLoginName()+"BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
			//System.out.println("userLoginName:Demo---------------BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
			String webServerAddress="http://"+FTPIP+"/Workflow/WorkFlowOperate.asmx";
			Call m_Call = initWebServerInfo(webServerAddress,"DraftWorkFlowAuto");
			OperationDesc opDesc = new OperationDesc();
			opDesc.addParameter(new QName(namespace, "draftLoginName"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
			opDesc.addParameter(new QName(namespace, "alias"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
			opDesc.addParameter(new QName(namespace, "formDetail"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
			opDesc.addParameter(new QName(namespace, "approveMan"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
			opDesc.addParameter(new QName(namespace, "activityName"), new QName(namespace, "string"), String.class, ParameterDesc.IN, false, false);
			opDesc.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			m_Call.setOperation(opDesc);
			result = (String)m_Call.invoke(new Object[] {userSession.getLoginName(),"ServiceSupervisor",formDetail,swork.getApprovorlogin(),"责任部门接口人"});
			//result = (String)m_Call.invoke(new Object[] {"jinchangjia","ServiceQualityManagement",formDetail,"jinchangjia","主办部门"});
			System.out.println("----------------------------------------DraftWorkFlowAuto:"+result);
			if(result == null){
				map.put("result", "");
			}else{
				map.put("result", result);
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return map;
	}
	
	private static Call initWebServerInfo(String webServerAddress,String p_CallOperationName) 
	{
		Service   service   =   new   Service();   
		Call call = null;
		try 
		{
			call = (Call)service.createCall();
			call.setTargetEndpointAddress(webServerAddress);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace+p_CallOperationName);
			call.setOperationName(new QName(namespace, p_CallOperationName));
			
			call.setReturnType(XMLType.XSD_STRING);   
		} 
		catch (ServiceException e) {
			e.printStackTrace();
		}					
		return call;
	}
	
	
}
