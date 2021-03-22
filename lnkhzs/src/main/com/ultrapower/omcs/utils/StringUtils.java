package com.ultrapower.omcs.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {

    public static String join(Collection<?> arg0, String arg1) {
        return join(arg0.iterator(), arg1);
    }
    public static String join(Object []arg0, String arg1) {
        return org.apache.commons.lang3.StringUtils.join(arg0, arg1);
    }

    public static String join(Iterator<?> arg0, String arg1) {
        return org.apache.commons.lang3.StringUtils.join(arg0, arg1);
    }

    public static String capitalize(String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    public static String uncapitalize(String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    public static boolean isBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

    public static String CAMEL_CASE_SP = "_";
    
    public static String toCamelCase(String str) {
        return toCamelCase(str, CAMEL_CASE_SP);
    }

    public static String toCamelCase(String str, String sp) {
        if (isBlank(str)) {
            return "";
        }
        str = str.toLowerCase();
        String[] arr = str.split(sp);
        String result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result += org.apache.commons.lang3.StringUtils.capitalize(arr[i]);
        }
        return result;
    }

    public static String toUnCamelCase(String str) {
        return toUnCamelCase(str, CAMEL_CASE_SP);
    }

    public static String toUnCamelCase(String str, String sp) {
        return "";
    }
    
    
    /**
     * @author 佟广恩  tongguangen@ultrapower.com.cn
     * @param str0
     * @param str1
     * @return
     */
    public static String nvl(String str0, String str1) {
        if(str0 == null || str0.length()==0){
            return str1;
        }
        return str0;
    }
    /**
     * 
     * [类似于Oracle中nvl2方法  字符串a为空的话返回b，否则返回c,校验空使用isBlank方法]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param a 字符串
     * @param b 参数
     * @param c 参数 
     * @return 如果参数不够是默认返回null
     */
    public static String nvl2(String a, String b, String c) {
        if (org.apache.commons.lang3.StringUtils.isBlank(a)) {
            return b;
        }
        return c;
    }

    /**
     * 
     * [类似于Oracle中decode方法，当 s与args中的第一个参数相等的话，返回args中第二个参数，否则判断args的长度，
     *  如果长度大于等于三那么返回第三个参数，否则判断是否等于第四个参数...]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param s 字符串
     * @param args 参数 参数个数为奇数
     * @return 如果参数不够是默认返回null
     */
    public static String decode(String s, String... args) {
        int length = args.length;
        if (length <= 1) {
            throw new RuntimeException("方法没有足够的值");
        }
        while ((length = args.length) >= 1) {
            if (length == 1) {
                return args[0];
            } else if (length >= 3 && s.equals(args[0])) {
                return args[1];
            } else {
                args = org.apache.commons.lang3.ArrayUtils.subarray(args, 2, args.length);
            }
        }
        return null;
    }

}
