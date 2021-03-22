package com.ultrapower.eoms.common.core.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.Manager;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class SafeRequestWrapper extends HttpServletRequestWrapper {
	
	
	public SafeRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override  
    public String getHeader(String name) {  
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));  
    }  
	
	@Override  
    public String getQueryString() {  
        return StringEscapeUtils.escapeHtml4(super.getQueryString());  
    }  
	
	@Override  
	public String[] getParameterValues(String name) {
		String values[] = super.getParameterValues(name);
		if (values != null) {
			String value = null;
			for (int i = 0; i < values.length; i++) {
				value = URLDecoder(values[i]);
                value=HTMLEncode(value,"JSON");
				values[i] = value;
			}
		}
		return values;
	}
	
	@Override
	public Map getParameterMap() {
		Map map = super.getParameterMap();
		if (map != null) {
			Map<String, String[]> nmap = new HashMap<String, String[]>(map.size());
			Set<String> keys = map.keySet();
			for (String key : keys) {
			    nmap.put(key.replaceAll("\\[[0-9]*\\]", ""), this.getParameterValues(key));
			}
			return nmap;
		}
		return map;
	}

	public String getParameter(String name) {
		String value = super.getParameter(name);
		value=HTMLEncode(value,"JSON");
		return value;
	}	
	
	
	@SuppressWarnings("unchecked")
	public static String HTMLEncode(String aTagFragment,String mode){
	    if("null".equals(aTagFragment) || aTagFragment == null){
	        return HTMLEncode(aTagFragment);
	    }
	    if("JSON".equals(mode) && aTagFragment.startsWith("{") && aTagFragment.endsWith("}")){
	        try {
	            JSONObject jo = JSONObject.fromObject(aTagFragment);
                Iterator<String> it = jo.keys();
	            while (it.hasNext()) {
	                String key = (String) it.next();
	                Object value = jo.get(key);
	                if (value instanceof String) {
                        String str = (String) value;
                        value = HTMLEncode(str, mode);
                    }
	                jo.put(key, value);
	            }
	            return jo.toString();
            } catch (Exception e) {
                return HTMLEncode(aTagFragment);
            }
	    } else if("JSON".equals(mode) && aTagFragment.startsWith("[{") && aTagFragment.endsWith("}]")){
            try {
                List<JSONObject> result = new ArrayList<JSONObject>();
                JSONArray ja = JSONArray.fromObject(aTagFragment);
                for (int i = 0; i < ja.size(); i++) {
                    String value =  HTMLEncode(ja.get(i).toString(), mode);
                    result.add(JSONObject.fromObject(value));
                }
                return result.toString();
            } catch (Exception e) {
                System.out.println();
                
                return HTMLEncode(aTagFragment);
            }
	    } else if("JSON".equals(mode) && aTagFragment.startsWith("[[{") && aTagFragment.endsWith("}]]")){
	        try {
	            List<JSONArray> result = new ArrayList<JSONArray>();
	            JSONArray ja = JSONArray.fromObject(aTagFragment);
	            for (int i = 0; i < ja.size(); i++) {
	                String value =  HTMLEncode(ja.get(i).toString(), mode);
	                result.add(JSONArray.fromObject(value));
	            }
	            return result.toString();
	        } catch (Exception e) {
	            System.out.println();
	            
	            return HTMLEncode(aTagFragment);
	        }
        } else if (aTagFragment.startsWith("<?xml ")) {
	        return aTagFragment;
	    } else {
	        return HTMLEncode(aTagFragment);
	    }
	}
	
	
	public static String HTMLEncode(String aTagFragment)
	{
		if(aTagFragment==null)
			return null;
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE )
		{
			if (character == '<'){ result.append("&lt;"); } 
			else if (character == '>') { result.append("&gt;"); }
			else if (character == '&') { result.append("&amp;"); } 
			else if (character == '\\') { result.append(""); } 
			else if (character == '\"') { result.append(""); }
			else if (character == '\'') { result.append(""); } 
			else {result.append(character);}
			character = iterator.next(); 
		}	
	  return result.toString().replaceAll("script", "").replaceAll("alert", "").replaceAll("prompt", ""); 
	}

    public static String HTMLDecode(String aTagFragment) {
        if (aTagFragment == null)
            return null;
        try {
            aTagFragment = URLDecoder.decode(aTagFragment,"UTF-8");
        } catch (UnsupportedEncodingException e) {
        } catch (java.lang.IllegalArgumentException e) {
        }
        return aTagFragment.replaceAll("&amp;", "&").replaceAll("&gt;", ">").replaceAll("&lt;", "<");
    }

  //中文统一解码
  	private String URLDecoder(String param){
  		try {
  				param = param.replaceAll("%(?![0-9a-fA-F]{2})", "%25").replaceAll("\\+", "%2b");  
  				param = URLDecoder.decode(param,"UTF-8");
  		} catch (UnsupportedEncodingException e) {
  			e.printStackTrace();
  		}
  		return param;
  	}
  	
	public static void main(String[] args)
	{
		String str="ABCD<script>E\"'3";
		System.out.println(HTMLEncode(str));
	}
}
