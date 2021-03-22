package com.ultrapower.eoms.common.constants;

import java.io.IOException;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtils {
	public static String getProperty(String name) {
		String result = null;
		java.util.Properties props;
		try {
			props = PropertiesLoaderUtils.loadAllProperties("security.properties");
			result = props.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 加载邮件配置参数信息
	 * @param name
	 * @return
	 */
	public static String getPropertyMail(String name){
		String result = null;
		java.util.Properties props;
		try {
			props = PropertiesLoaderUtils.loadAllProperties("mail.properties");
			result = props.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * [异常信息处理]
	 * @author:佟广恩 tongguangen@ultrapower.com.cn
	 * @param name
	 * @return
	 */
	public static String getPropertyException(String name){
	    String result = null;
	    java.util.Properties props;
	    try {
	        props = PropertiesLoaderUtils.loadAllProperties("messages_exceptions.properties");
	        result = props.getProperty(name);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	
	/**
	 * 加载短信配置参数信息
	 * @param name
	 * @return
	 */
	public static String getPropertySm(String name){
		String result = null;
		java.util.Properties props;
		try {
			props = PropertiesLoaderUtils.loadAllProperties("smsproxy.properties");
			result = props.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
