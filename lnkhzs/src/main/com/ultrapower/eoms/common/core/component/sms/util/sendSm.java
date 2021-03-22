package com.ultrapower.eoms.common.core.component.sms.util;

import java.rmi.RemoteException;

public class sendSm {

	public static void main(String[] args) {
		String url = "http://10.64.27.133:81/SMS_M/SendSMSMsgService.asmx?wsdl";
		SendSMSMsgServiceSoapProxy soap = new SendSMSMsgServiceSoapProxy(url);
		try {
			/*String xml = soap.sendMessage("18842469972", "测试短信调用接口数据1234567890",
					"lnfwzl", "18842469972");
			System.out.println(xml);*/
			String xml = "<dataSet><M_CALL_STATUS><CALLSTATUS>1</CALLSTATUS><MSG>接口数据信息</MSG></M_CALL_STATUS></dataSet>";
			
			if (xml.indexOf("<CALLSTATUS>") < 1 || xml.indexOf("</CALLSTATUS>") < 1) {
				System.out.println("短信接口返回值有误：" + xml);
			} else {
				String callStatus = xml.substring(xml.indexOf("<CALLSTATUS>")+12, xml.indexOf("</CALLSTATUS>"));
				String msg = xml.substring(xml.indexOf("<MSG>")+5, xml.indexOf("</MSG>"));
				if (callStatus != null && "1".equals(callStatus)) {
					System.out.println("短信发送成功：" + msg);
				} else {
					System.out.println("短信发送失败：" + msg);
				}
			}
			
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

}
