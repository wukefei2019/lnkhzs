package com.ultrapower.omcs.utils;

public class NumberUtils extends com.ultrapower.eoms.common.core.util.NumberUtils {
    
    public static Double nvl(Object o){
        if(o == null){
            return 0D;
        }
        return Double.valueOf(o.toString());
    }
    public static Double nvl(Object o,Object o1){
        if(o == null){
            return Double.valueOf(o1.toString());
        }
        return Double.valueOf(o.toString());
    }
}
