package com.ultrapower.omcs.utils;

public class MathUtils {

    // 平均数
    public static Double getAvg(Double[] valueList) {
        double sum = 0D;
        for (int i = 0; i < valueList.length; i++) {
            sum += valueList[i];
        }
        return sum / valueList.length;
    }

    public static Double getStandardDevition(Double[] valueList) {
        double avg = getAvg(valueList);
        return getStandardDevition(valueList, avg);
    }

    public static Double getStandardDevition(Double[] valueList, double avg) {
        double sum = 0D;
        if (valueList.length == 1) {
            return 0D;
        }
        for (int i = 0; i < valueList.length; i++) {
            sum += Math.sqrt(((double) valueList[i] - avg) * (valueList[i] - avg));
        }
        return (sum / (valueList.length - 1));
    }
}
