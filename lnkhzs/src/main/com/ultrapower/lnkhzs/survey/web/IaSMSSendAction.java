package com.ultrapower.lnkhzs.survey.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.junit.Test;
import org.xml.sax.InputSource;

import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.survey.model.KhzsResendDetail;
//import com.ultrapower.ftpinterface.service.FtpDataSendService;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.omcs.base.web.BaseAction;

public class IaSMSSendAction  extends BaseAction{


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
	
	//起草流程1
	public static Map<String,Object> draftWorkFlow(String naireId,String daynum,String countNum,String messageInfo,String sqlWhere)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/BusinessNotice/"+RandomUtils.getRandomN(6);
		String result="";
		try{
			StringBuffer sb =new StringBuffer();
			sb.append("<detail>");
			sb.append("<fieldRef>");
			sb.append("<fieldInfo><fieldChName>标题</fieldChName><fieldEnName>ApplyTitle</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("智慧调研短信群发" + "</fieldValue></fieldInfo>");
			/*sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(URLEncoder.encode("http://10.63.144.70:81/lnfwzl/lnkhzs/survey/question.jsp?id="+naireId+"&userid=", "UTF-8") + "</fieldValue></fieldInfo>");*/
			/*sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(URLEncoder.encode("http://dx.10086.cn/mCtbAw?id="+naireId+"&userid=", "UTF-8") + "</fieldValue></fieldInfo>");*/
			sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("<![CDATA[http://dx.10086.cn/Y85lAw?id="+naireId+"&userid=" + "]]></fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>时长</fieldChName><fieldEnName>DurationLength</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(daynum + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>客户数量</fieldChName><fieldEnName>CustomerCounts</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(countNum + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>拟定短信语</fieldChName><fieldEnName>DrafShortMessage</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(messageInfo + "</fieldValue></fieldInfo>");
			/*.append(messageInfo + "请在X月XX日之前点击（http://dx.10086.cn/XXXX）填写问卷。感谢您的支持与配合！【中国移动】</fieldValue></fieldInfo>");*/
			sb.append("<fieldInfo><fieldChName>目标客户甄选规则及数量</fieldChName><fieldEnName>SelectionRuleNumber</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("<![CDATA["+sqlWhere + "]]></fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>申请类型</fieldChName><fieldEnName>ApplicationType</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("群发" + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>信息类型</fieldChName><fieldEnName>MessageType</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("服务" + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>起草类型</fieldChName><fieldEnName>DraftType</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("2" + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>业务类型</fieldChName><fieldEnName>businessType</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("1" + "</fieldValue></fieldInfo>");
			sb.append("</fieldRef>");
			sb.append("<attachRef><attachInfo><path></path></attachInfo></attachRef>");
			sb.append("</detail>");
			//System.out.println(sb.toString());
			map.put("formDetail", sb.toString());
			formDetail=sb.toString();
			UserSession userSession = ActionContext.getUserSession();
			System.out.println("userLoginName:"+userSession.getLoginName()+"BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
			//System.out.println("userLoginName:Demo---------------BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
			//10.63.144.11:81生产
			/*HttpServletRequest request = ServletActionContext.getRequest();
			String myip=request.getServerName();
			String myftpip="";
			if(myip.equals("10.63.144.11")){
				myftpip="10.64.93.10";
			}else{
				myftpip="10.64.93.11";
			}
			String webServerAddress="http://"+myftpip+"/Workflow/WorkFlowOperate.asmx";*/
			HttpServletRequest request = ServletActionContext.getRequest();
			System.out.println("-----------------------------------");
			System.out.println("当前IP地址为："+request.getServerName());
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
			result = (String)m_Call.invoke(new Object[] {userSession.getLoginName(),"SmsGroupApplyV5",formDetail,userSession.getLoginName(),"满意度调查申请人"});
			//result = (String)m_Call.invoke(new Object[] {"jinchangjia","SmsGroupApplyV5",formDetail,"jinchangjia","满意度调查申请人"});
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
	
	//起草流程2
		public static Map<String,Object> draftWorkFlowFile(String naireId,String daynum,String countNum,String messageInfo,String sqlWhere)
		{
			Map<String,Object> map=new HashMap<String, Object>();
			String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/BusinessNotice/"+RandomUtils.getRandomN(6);
			String result="";
			try{
				StringBuffer sb =new StringBuffer();
				sb.append("<detail>");
				sb.append("<fieldRef>");
				sb.append("<fieldInfo><fieldChName>标题</fieldChName><fieldEnName>ApplyTitle</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("智慧调研短信群发" + "</fieldValue></fieldInfo>");
				/*sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append(URLEncoder.encode("http://10.63.144.70:81/lnfwzl/lnkhzs/survey/question.jsp?id="+naireId+"&userid=", "UTF-8") + "</fieldValue></fieldInfo>");*/
				/*sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append(URLEncoder.encode("http://dx.10086.cn/mCtbAw?id="+naireId+"&userid=", "UTF-8") + "</fieldValue></fieldInfo>");*/
				sb.append("<fieldInfo><fieldChName>URL</fieldChName><fieldEnName>MassURL</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("<![CDATA[http://dx.10086.cn/Y85lAw?id="+naireId+"&userid=" + "]]></fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>时长</fieldChName><fieldEnName>DurationLength</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append(daynum + "</fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>客户数量</fieldChName><fieldEnName>CustomerCounts</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append(countNum + "</fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>拟定短信语</fieldChName><fieldEnName>DrafShortMessage</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append(messageInfo + "</fieldValue></fieldInfo>");
				/*.append(messageInfo + "请在X月XX日之前点击（http://dx.10086.cn/XXXX）填写问卷。感谢您的支持与配合！【中国移动】</fieldValue></fieldInfo>");*/
				sb.append("<fieldInfo><fieldChName>目标客户甄选规则及数量</fieldChName><fieldEnName>SelectionRuleNumber</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("<![CDATA["+sqlWhere + "]]></fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>申请类型</fieldChName><fieldEnName>ApplicationType</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("群发" + "</fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>信息类型</fieldChName><fieldEnName>MessageType</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("服务" + "</fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>起草类型</fieldChName><fieldEnName>DraftType</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("2" + "</fieldValue></fieldInfo>");
				sb.append("<fieldInfo><fieldChName>业务类型</fieldChName><fieldEnName>businessType</fieldEnName><fieldType>string</fieldType><fieldValue>")
				.append("2" + "</fieldValue></fieldInfo>");
				sb.append("</fieldRef>");
				sb.append("<attachRef><attachInfo><path></path></attachInfo></attachRef>");
				sb.append("</detail>");
				//System.out.println(sb.toString());
				map.put("formDetail", sb.toString());
				formDetail=sb.toString();
				UserSession userSession = ActionContext.getUserSession();
				System.out.println("userLoginName:"+userSession.getLoginName()+"BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
				//System.out.println("userLoginName:Demo---------------BusinessNotice:"+"BusinessNotice"+"-------------------------------formDetail:"+formDetail);
				//10.63.144.11:81生产
				/*HttpServletRequest request = ServletActionContext.getRequest();
				String myip=request.getServerName();
				String myftpip="";
				if(myip.equals("10.63.144.11")){
					myftpip="10.64.93.10";
				}else{
					myftpip="10.64.93.11";
				}
				String webServerAddress="http://"+myftpip+"/Workflow/WorkFlowOperate.asmx";*/
				HttpServletRequest request = ServletActionContext.getRequest();
				System.out.println("-----------------------------------");
				System.out.println("当前IP地址为："+request.getServerName());
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
				result = (String)m_Call.invoke(new Object[] {userSession.getLoginName(),"SmsGroupApplyV5",formDetail,userSession.getLoginName(),"满意度调查申请人"});
				//result = (String)m_Call.invoke(new Object[] {"jinchangjia","SmsGroupApplyV5",formDetail,"jinchangjia","满意度调查申请人"});
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

}
