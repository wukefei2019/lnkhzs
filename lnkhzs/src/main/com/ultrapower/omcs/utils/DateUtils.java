package com.ultrapower.omcs.utils;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    
    
    /**
     * [获取当前日期的开始时间]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @return
     */
    public static Calendar getFirstOfDate(){
        return getFirstOfDate(Calendar.getInstance().getTime());
    }
    /**
     * [获取指定日期的开始时间]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param date
     * @return
     */
    public static Calendar getFirstOfDate(Date date){
        Calendar eom = Calendar.getInstance();
        eom.setTime(date);
        eom.set(Calendar.HOUR_OF_DAY,0);
        eom.set(Calendar.MINUTE,0);
        eom.set(Calendar.SECOND,0);
        eom.getTime();
        return eom;
    }
    /**
     * [获取当前月的最后一天]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @return
     */
    public static Calendar getEndOfMonth(){
        return getEndOfMonth(Calendar.getInstance().getTime());
    }
    /**
     * [获取指定日期月的最后一天]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param date
     * @return
     */
    public static Calendar getEndOfMonth(Date date){
        Calendar eom = Calendar.getInstance();
        eom.setTime(date);
        eom.set(Calendar.DAY_OF_MONTH, 1);
        eom.set(Calendar.HOUR_OF_DAY,23);
        eom.set(Calendar.MINUTE,59);
        eom.set(Calendar.SECOND,59);
        eom.add(Calendar.MONTH, 1);
        eom.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println();
        return eom;
    }
    /**
     * [获取当前月的第一天]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @return
     */
    public static Calendar getFirstOfMonth(){
        return getFirstOfMonth(Calendar.getInstance().getTime());
    }
    /**
     * [获取指定日期月的第一天]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param date
     * @return
     */
    public static Calendar getFirstOfMonth(Date date){
        Calendar fom = Calendar.getInstance();
        fom.setTime(date);
        fom.set(Calendar.HOUR_OF_DAY,0);
        fom.set(Calendar.MINUTE,0);
        fom.set(Calendar.SECOND,0);
        fom.set(Calendar.DAY_OF_MONTH, 1);
        return fom;
    }
    

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

	public static Date addDate(Date date,int amount){
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, amount);
	    return cal.getTime(); 
	}
	
	public static String addDateStr(Date date,int amount,String patrn){
	    return getDateStr(addDate(date, amount), patrn); 
	}
	
	public static String addDateStr(Date date,int amount){
	    return getDateStr(addDate(date, amount), "yyyy-MM-dd"); 
	}
	
    public static String getYesterdayStr(String patrn) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return getDateStr(c.getTime(), patrn);
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