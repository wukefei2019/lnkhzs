package com.ultrapower.eoms.common.core.util;  
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
  
@SuppressWarnings("deprecation")
public class WordGeneratorUtils {
    private static Configuration configuration = null;  
    private static Map<String, Template> allTemplates = null;  

    private WordGeneratorUtils() {  
        throw new AssertionError();  
    }  
  
    public static File createDoc(Map<?, ?> dataMap, String type, String path) { 
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        configuration.setClassForTemplateLoading(WordGeneratorUtils.class, path);
        allTemplates = new HashMap<String, Template>();   // Java 7 钻石语法  
        try {  
            allTemplates.put(type, configuration.getTemplate(type+".ftl"));  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }
    	
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";  
        File f = new File(name);  
        Template t = allTemplates.get(type);  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }
    
    public static void downLoadWord(HttpServletResponse response,String fileName, String path,Map<String,String> dataMap) throws IOException {  
        File file = null;  
        InputStream fin = null;  
        OutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file = WordGeneratorUtils.createDoc(dataMap, fileName,path);  
            fin = new FileInputStream(file);  
              
            response.setCharacterEncoding("utf-8");  
            response.setContentType("application/msword");  
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
            response.addHeader("Content-Disposition", "attachment;filename="+fileName+".doc");  
              
            out = response.getOutputStream();  
            byte[] buffer = new byte[512];  // 缓冲区  
            int bytesToRead = -1;  
            // 通过循环将读入的Word文件的内容输出到浏览器中  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
        } finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(file != null) file.delete(); // 删除临时文件  
        }  
        
    }
  
}