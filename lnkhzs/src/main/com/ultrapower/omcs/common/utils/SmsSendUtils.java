package com.ultrapower.omcs.common.utils;

import com.ultrapower.eoms.common.core.component.sms.manager.SendSmsForSqlServer;
import com.ultrapower.eoms.common.core.util.StringUtils;

public class SmsSendUtils {
	

	public static String smsSend(String uuid, String mobile, String content) {
		SendSmsForSqlServer sendsm = new SendSmsForSqlServer();
		if ("".equals(StringUtils.checkNullString(mobile))) {
			return "号码为空,发送失败！";
		}
		if ("".equals(StringUtils.checkNullString(content))) {
			return "短信内容为空,发送失败！";
		}
		String result = sendsm.SendSm(StringUtils.checkNullString(uuid), mobile, content);
		if ("true".equals(result)) {
			System.out.println("短信:" + content + ",手机号码：" + mobile + ",发送成功！");
		} else {
			System.out.println("短信:" + content + ",手机号码：" + mobile + ",发送失败！");
		}
		return result;
	}
}
