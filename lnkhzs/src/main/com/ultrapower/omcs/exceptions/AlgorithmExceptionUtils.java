package com.ultrapower.omcs.exceptions;

import com.ultrapower.eoms.common.constants.PropertiesUtils;

public class AlgorithmExceptionUtils {

    public static AlgorithmException newInstance(Throwable cause) {
        String exceptionMsg = null;
        StackTraceElement[] stackTrace = cause.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement ste = stackTrace[i];
            String fileName = ste.getFileName();
            String className = ste.getClassName();
            String methodName = ste.getMethodName();
            if (className.startsWith("com.ultrapower.omcs.utils.algorithm.")) {
                String exceptionKey = "Exception." + fileName.replaceAll("java", "") + methodName.substring(3);
                exceptionMsg = PropertiesUtils.getPropertyException(exceptionKey);
                break;
            }
        }
        return new AlgorithmException(exceptionMsg,cause);
    }

}
