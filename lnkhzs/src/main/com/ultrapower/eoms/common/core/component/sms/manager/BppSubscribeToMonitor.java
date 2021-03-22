package com.ultrapower.eoms.common.core.component.sms.manager;

import com.ultrapower.eoms.common.core.component.sms.business.SmsBppSubscribeToMoniter;
import com.ultrapower.eoms.common.core.component.sms.business.SmsSubscribeToMoniter;
import com.ultrapower.eoms.common.core.component.sms.service.SmsSubscribeToMoniterService;

/**
 * @author zhuzhaohui E-mail:zhuzhaohui@ultrapower.com.cn
 * @version 2011-10-25 上午11:03:26
 * @descibe 查询工单短信进行订阅匹配并插入短信发送表
 */
public class BppSubscribeToMonitor {

	private SmsSubscribeToMoniterService  subscribeToMoniter  = null;
	public void run() 
	{
		//BPP版工单
		subscribeToMoniter = new SmsBppSubscribeToMoniter();
		subscribeToMoniter.call();
	}
	
	public static void main(String[] args){
		System.out.println(System.currentTimeMillis()/1000);
	}
}
