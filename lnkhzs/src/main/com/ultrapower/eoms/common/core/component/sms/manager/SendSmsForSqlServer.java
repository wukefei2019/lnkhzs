package com.ultrapower.eoms.common.core.component.sms.manager;

import com.ultrapower.eoms.RecordLog;
import com.ultrapower.eoms.common.core.component.sms.service.SendService;
import com.ultrapower.eoms.common.core.component.sms.util.SendSMSMsgServiceSoapProxy;
import com.ultrapower.eoms.common.core.util.DBUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * 返回值：短信发正确>0,错误<0,没找到子系统=0
 */
public class SendSmsForSqlServer  implements SendService
{
//  public int sendMessage(String mobile, String context, HashMap parMap)
//  {
//    String loginName = "";
//    if (parMap != null)
//    {
//      loginName = StringUtils.checkNullString("loginname");
//    }
//    Connection conn = null;
//    CallableStatement cmd = null;
//    int result = 0;
//    try
//    {
//    	/*
//		 * [sp_Messages_Send] (@sMobile, @sContent，@sflag，@iTimes, @sStatus_Report, @sApplyCode, @iOrder1,@sSendUserID,@sAppID)
//		 * 输入参数：
//		 * @sMobile	varchar(20)	手机号	136********
//		 * @sContent	varchar(1024)	短信内容	
//		 * @sflag	Int	接收状态	默认给0
//		 * @iTimes	Int	重发次数	默认给0
//		 * @sStatus_Report	Int	是否需要状态报告 0 不需要 1 需要	默认给0
//		 * @sApplyCode	Varchar(10)	应用ID	如：WL01
//		 * @iOrder1	Int	领导排序	[sp_Messages_Info]输出参数@iOrder1（默认0）
//		 * @sSendUserID	Varchar(50)	发送人用户唯一登陆办公网ID	如：jinchangjia
//		 * @sAppID	Varchar(10)	应用系统内部标识ID	如需上行应用：传入如：123；不需上行应用：传入NULL或空值
//		 * 输出参数：
//		 * @Result	Int	执行结果	正确>0,错误<0,没找到子系统=0
//
//		 */
//      conn = DBUtils.getSmsDbConn();//ConnectionPool.getConn("smsdb");
//      cmd = conn.prepareCall("{call sp_Messages_Send(?,?,?,?,?,?,?,?,?,?)}");
//      cmd.setString(1, mobile);
//      cmd.setString(2, context);
//      cmd.setInt(3, 0);
//      cmd.setInt(4, 0);
//      cmd.setInt(5, 0);
//      cmd.setString(6, "UIPLOGIN");//辽宁企业质量监控系统标记
//      cmd.setInt(7, 0);
//      cmd.setString(8, loginName);//发送人用户唯一登陆办公网ID	如：jinchangjia
//      cmd.setString(9, null);
//      cmd.registerOutParameter(10, 4);
//      cmd.execute();
//      result = cmd.getInt(10);
//      RecordLog.printLog("短信记录:loginname：" + loginName + "mobile:" + mobile + ",content：" + context + ",result:" + result, 3);
//    }
//    catch (SQLException e)
//    {
//      e.printStackTrace();
//      result = -9998;
//      try
//      {
//        if (cmd != null) {
//          cmd.close();
//        }
//        if (conn != null)
//          conn.close();
//      }
//      catch (Exception ex)
//      {
//        ex.printStackTrace();
//      }
//    }
//    finally
//    {
//      try
//      {
//        if (cmd != null) {
//          cmd.close();
//        }
//        if (conn != null)
//          conn.close();
//      }
//      catch (Exception e)
//      {
//        e.printStackTrace();
//      }
//    }
//    return result;
//  }

  public String SendSm(String pid, String mobile, String content)
  {
    String strRe = "false";
    HashMap parMap = new HashMap();
    parMap.put("pid", pid);
    parMap.put("loginname", "uipadmin");
    int result = sendMessage(mobile, content, parMap);
    if (result > 0)
    {
      strRe = "true";
    }
    return strRe;
  }

  public int sendMessage(String mobile, String context, HashMap parMap)
  {
    String loginName = "";
    if (parMap != null)
    {
      loginName = StringUtils.checkNullString("loginname");
    }
    int result = 0;
    
    
    String url = "http://10.64.27.133:81/SMS_M/SendSMSMsgService.asmx?wsdl";
	SendSMSMsgServiceSoapProxy soap = new SendSMSMsgServiceSoapProxy(url);
	try {
		String xml = soap.sendMessage(mobile, context, "UIPLOGIN", null);
		
		if (xml == null || "".equals(xml) ||xml.indexOf("<CALLSTATUS>") < 1 || xml.indexOf("</CALLSTATUS>") < 1) {
			System.out.println("短信接口返回值有误：" + xml);
		} else {
			String callStatus = xml.substring(xml.indexOf("<CALLSTATUS>")+12, xml.indexOf("</CALLSTATUS>"));
			String msg = xml.substring(xml.indexOf("<MSG>")+5, xml.indexOf("</MSG>"));
			if (callStatus != null && "1".equals(callStatus)) {
				System.out.println("短信发送成功：" + msg);
				result = 1;
			} else {
				System.out.println("短信发送失败：" + msg);
				result = 0;
			}
		    RecordLog.printLog("短信记录:  loginname：" + loginName + ",mobile:" + mobile + ",content：" + context + ",result:" + result, 3);
		}
		
	} catch ( Exception e) {
		e.printStackTrace();
	}
	
    return result;
  }
  

}