package com.ultrapower.eoms.common.core.util;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

    /**
     * [获得字符串形式的当前时间]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param patrn
     * @return
     */
	public static String getDateStr(String patrn){
		DateFormat df = new SimpleDateFormat(patrn);
		String dateStr = df.format(new Date());
		return dateStr;
	}
	
	/**
     * [获得字符串形式的当前时间]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param date
     * @param patrn
     * @return
     */
    public static String getDateStr(java.util.Date date,String patrn){
        DateFormat df = new SimpleDateFormat(patrn);
        String dateStr = df.format(date);
        return dateStr;
    }
    /**
     * [获得字符串形式的当前时间]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param l
     * @param patrn
     * @return
     */
    public static String getDateStr(long l,String patrn){
        return  getDateStr(new Date(l), patrn);
    }
    /**
     * [方法功能中文描述]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param timestamp
     * @param patrn
     * @return
     */
    public static String getDateStr(oracle.sql.TIMESTAMP timestamp,String patrn){
        try {
            return getDateStr(timestamp.dateValue(), patrn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patrn;
    }
	
	/**
	 * [从字符串中分析出时间信息]
	 * @author:佟广恩 tongguangen@ultrapower.com.cn
	 * @param str
	 * @return
	 */
	public static String getDateFromString(String str){
	    String dateStr = null;
//	    String reg = "([0-9]{4}[年\\-\\.][0-9]{1,2}[月\\-\\.][0-9]{1,2}[日])";
	    String reg = "([0-9]{4}[年\\-\\.]?[0-9]{1,2}[月\\-\\.]?[0-9]{1,2}[日]?)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        if(m.find()){
            dateStr = m.group(0); 
        }
        return dateStr;
	}
}