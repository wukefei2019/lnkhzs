package com.ultrapower.omcs.tools;

import java.io.File;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("rawtypes")
public class ToolsUtils {
    
    public static String getRealPath(Class clazz, String path) {
        String realPath = "";
        try {
            if (path.indexOf("/") == -1 || path.indexOf("\\") == -1) {
                realPath = java.net.URLDecoder.decode(clazz.getResource("").getPath(), "utf-8") + File.separator + path;
            } else if (path.startsWith("com")) {
                realPath = java.net.URLDecoder.decode(clazz.getResource("/").getPath(), "utf-8") + File.separator
                        + path;
            } else {
                realPath = path;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return realPath;
    }

}
