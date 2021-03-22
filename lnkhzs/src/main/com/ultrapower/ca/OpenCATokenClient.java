package com.ultrapower.ca;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.ultrapower.ca.model.BootToken;
import com.ultrapower.ca.model.SubToken;
import com.ultrapower.ca.util.DES3CipherUtil;
import com.ultrapower.ca.util.RSACipherUtil;
import com.ultrapower.ca.util.TokenManager;


public class OpenCATokenClient {
    
    private String URL = "";
    
    private String appCode="fileMgr";
    
    private String systemKey="WEB";
    
    
    private OpenCATokenClient() {
        try {
            java.util.Properties props;
            props = PropertiesLoaderUtils.loadAllProperties("security.properties");
            URL = props.getProperty("openca.url");
            if(URL == null || URL.length() == 0){
                props = PropertiesLoaderUtils.loadAllProperties("security.properties");
                URL = props.getProperty("openca_client.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    

    private static OpenCATokenClient instince = new OpenCATokenClient();

    public static OpenCATokenClient getInstince() {
        return instince;
    }

    public BootToken getBootToken(String username, String pwd) {
        BootToken bootToken = null;
        String result;
        try {
            HttpPost post = new HttpPost(URL+"/getBootToken");
            HttpClient client = new DefaultHttpClient();
            String encrypt = RSACipherUtil.encrypt(RSACipherUtil.getPublickey(), pwd);
            StringEntity entity = new StringEntity("touchId=otherSys&clientId="+username+"&encodePass="+encrypt+"&isPwdEncode=true");
            entity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(entity);
            HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity());
            result = DES3CipherUtil.decode(result);
            TokenManager manager = new TokenManager();
            bootToken = manager.parseTokenBean(result);
            
        } catch (Exception e) {
            result =  e.getMessage();
        }
        
        return bootToken;
    }

    public SubToken getSubToken(String bootToken,String appCode) {
        if(appCode != null && appCode.length() > 0 ){
            this.appCode = appCode;
        }
        SubToken token = null;
        String result;
        try {
            HttpPost post = new HttpPost(URL+"/getSubToken");
            HttpClient client = new DefaultHttpClient();
            StringEntity entity = new StringEntity("appCode="+this.appCode+"&systemKey="+systemKey+"&bootToken=" + bootToken);
            entity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(entity);
            HttpResponse httpResponse = client.execute(post);
            result = EntityUtils.toString(httpResponse.getEntity());
            result = DES3CipherUtil.decode(result);
            TokenManager manager = new TokenManager();
            token = manager.parseSubTokenBean(result);
        } catch (Exception e) {
            result =  e.getMessage();
        }
        return token;
    }

    public SubToken getSubToken(BootToken bootToken, String appCode) {
        return getSubToken(bootToken.getTokenCode(), appCode);
    }

}
