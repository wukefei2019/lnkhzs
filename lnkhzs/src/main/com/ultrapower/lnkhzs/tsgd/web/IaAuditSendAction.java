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

import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserSession;
//import com.ultrapower.ftpinterface.service.FtpDataSendService;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.omcs.base.web.BaseAction;

public class IaAuditSendAction  extends BaseAction{


	//接口属性
	public static String formDetail;
	//	String(50)	是	流程标识GUID
	public static String processID;
	//ws命名空间
	public static String namespace = "http://tempuri.org/";
	
	//测试
	public static String FTPIP = "10.64.93.12";
	//生产
	//public static String FTPIP = "10.64.93.10";
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
	
	//起草流程
	public static Map<String,Object> draftWorkFlow(WFresend wFresend)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/BusinessNotice/"+RandomUtils.getRandomN(6);
		String result="";
		try{
			StringBuffer sb =new StringBuffer();
			sb.append("<detail>");
			sb.append("<fieldRef>");
			sb.append("<fieldInfo><fieldChName>派单主题</fieldChName><fieldEnName>ApplyTitle</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getApplytitle() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>问题分类</fieldChName><fieldEnName>MatterCategory</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getMattercategory() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>标签分类</fieldChName><fieldEnName>LabelCategory</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getLabelcategory() + "</fieldValue></fieldInfo>");
			/*sb.append("<fieldInfo><fieldChName>主责部门</fieldChName><fieldEnName>MainDepart</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(URLEncoder.encode("辽宁移动/"+wFresend.getMaindepart(), "UTF-8") + "</fieldValue></fieldInfo>");*/
			sb.append("<fieldInfo><fieldChName>主责部门</fieldChName><fieldEnName>MainDepart</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append("辽宁移动/"+wFresend.getMaindepart() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>主责部门正职登录名</fieldChName><fieldEnName>MainDepartID</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getMaindepartid() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>截止日期</fieldChName><fieldEnName>ExpiryDate</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getExpirydate() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>派单内容</fieldChName><fieldEnName>Content</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getContent() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>验证方式</fieldChName><fieldEnName>ValidMethod</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getValidmethod() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>验证值</fieldChName><fieldEnName>ValidValue</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getValidvalue() + "</fieldValue></fieldInfo>");
			sb.append("<fieldInfo><fieldChName>目标</fieldChName><fieldEnName>Content</fieldEnName><fieldType>string</fieldType><fieldValue>")
			.append(wFresend.getTarget() + "</fieldValue></fieldInfo>");
			sb.append("</fieldRef>");
			sb.append("<attachRef><attachInfo><path></path></attachInfo></attachRef>");
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
			result = (String)m_Call.invoke(new Object[] {userSession.getLoginName(),"ServiceQualityManagement",formDetail,wFresend.getMaindepartid(),"主办部门"});
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
