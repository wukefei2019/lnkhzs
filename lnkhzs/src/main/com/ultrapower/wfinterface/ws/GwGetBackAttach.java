package com.ultrapower.wfinterface.ws;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;

import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserSession;

public class GwGetBackAttach {
	// 接口属性
	public static String formDetail;
	// String(50) 是 流程标识GUID
	public static String processID;
	// ws命名空间
	public static String namespace = "http://tempuri.org/";

	// 测试
	public static String url = "10.64.93.11/Workflow/SearchWorkFlow.asmx";
	// 生产
	// public static String url = "10.64.93.10/Workflow/MobileWorkSheet.asmx";
	public static String FTPPORT = "21";
	public static String FTPUSER = "gwpt";
	public static String FTPPWD = "Gw!2013#";
	
	
	/*生产接口url：http://10.64.93.10/Workflow/MobileWorkSheet.asmx
	测试接口url：http://10.64.93.12/Workflow/SearchWorkFlow. asmx
	生产附件ftp服务器：ftp://10.64.93.15/
	用户名：gwpt
	密码：Gw!2013#
	测试附件ftp服务器：ftp://10.64.93.11/
	用户名：gwpt
	密码：Gw!2013#*/

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

	// 起草流程
	public static String draftWorkFlow(String ProcessID) {
		String result = "";
		try {
			System.out.println("ProcessID:"+ProcessID+"-------------------------------");
			String webServerAddress = "http://" + url;
			Call m_Call = initWebServerInfo(webServerAddress, "SearchWFAttach");//地址,方法名
			OperationDesc opDesc = new OperationDesc();
			opDesc.addParameter(new QName(namespace, "ProcessID"), new QName(namespace, "string"), String.class,
					ParameterDesc.IN, false, false);
			opDesc.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
			m_Call.setOperation(opDesc);
			result = (String) m_Call.invoke(new Object[] { ProcessID });
			// result = (String)m_Call.invoke(new Object[]
			// {"jinchangjia","SmsGroupApplyV5",formDetail,"jinchangjia","满意度调查申请人"});
			System.out.println("----------------------------------------DraftWorkFlowAuto:" + result);
			/*if (result == null) {
				map.put("result", "");
			} else {
				map.put("result", result);
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static Call initWebServerInfo(String webServerAddress, String p_CallOperationName) {
		Service service = new Service();
		Call call = null;
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(webServerAddress);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(namespace + p_CallOperationName);
			call.setOperationName(new QName(namespace, p_CallOperationName));

			call.setReturnType(XMLType.XSD_STRING);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return call;
	}
}
