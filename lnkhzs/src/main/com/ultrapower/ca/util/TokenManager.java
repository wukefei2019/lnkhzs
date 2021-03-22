package com.ultrapower.ca.util;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultrapower.ca.constant.RequestState;
import com.ultrapower.ca.model.BootToken;
import com.ultrapower.ca.model.SubToken;


@SuppressWarnings("unchecked")
public class TokenManager {
    
   
    /**
     * @param account
     * @param appKey
     * @param bootTokenCode
     * @return
     * @throws Exception
     */
    public String getSubTokenCode(String appKey, String bootTokenCode, String systemKay, String appName)
            throws Exception {
        return "";
//        SubTokenBean subTokenBean = null;
//        if (bootTokenCode == null || "".equals(bootTokenCode.trim())
//                || bootTokenCode.length() > bootTokenCode.trim().length()) {
//            throw new Exception("参数非法");
//        }
//
//        String getTokenUrl = "http" + "://" +  MeContants.meServerIp+":" +MeContants.caServerPort + "/OpenCA/getSubToken?appCode="
//                + appKey + "&bootToken=" + bootTokenCode + "&systemKey=" + systemKay + "&appName=" + appName;
//        subTokenBean = parseSubTokenBean(Des3.decode(HttpUtil.getMsg(getTokenUrl)));
//        //Log.i("dz", "subtoken  state code = " + subTokenBean.getStateCode());
//        //Log.i("gwy", "获取tokenUrl = " + getTokenUrl);
//        if (subTokenBean.getStateCode() == 0) {
//            //Log.i("dz", "subtoken = " + subTokenBean.getSubTokenCode());
//            return subTokenBean.getSubTokenCode();
//        } else if (subTokenBean.getStateCode() == 5) {
//            return String.valueOf(subTokenBean.getStateCode());
//        } else if (subTokenBean.getStateCode() == 1 
//                && onCaBootTokenAfterTimeListener != null) {
//            onCaBootTokenAfterTimeListener.onCaBootTokenAfterTime();
//            
//            throw new Exception(subTokenBean.getErrorMessage());
//        } else {
//            throw new Exception(subTokenBean.getErrorMessage());
//        }

    }
    
    public SubToken parseSubTokenBean(String json) {
        SubToken token = new SubToken();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;  
        try {
            Map<String,String> map = mapper.readValue(json, Map.class);  
            token.setCode(map.get("code"));
            token.setMessage(map.get("message"));
            token.setState(map.get("state"));
            token.setType(map.get("type"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    
    public BootToken parseTokenBean(String json) {
        BootToken token = new BootToken();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;  
        try {
            Map<String,String> map = mapper.readValue(json, Map.class);  
            token.setTokenCode(map.get("state"));
            token.setAccount(map.get("account"));
            token.setMobile(map.get("mobile"));
            token.setUsername(map.get("username"));
            token.setTokenCode(map.get("tokenCode"));
            token.setAddress(map.get("address"));
            token.setDeptname(map.get("deptname"));
            token.setEmail(map.get("email"));
            token.setPortralurl(map.get("portralurl"));
            token.setTelephone(map.get("telephone"));
            token.setDeptid(map.get("deptid"));
            token.setState(RequestState.map.get(map.get("state")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

}
