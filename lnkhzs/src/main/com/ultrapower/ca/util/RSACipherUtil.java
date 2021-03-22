package com.ultrapower.ca.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Decoder.BASE64Decoder;



/** * @ClassName: CipherUtil 
* @Description: TODO 
* @author zhangzongbao 
* @date 2013-9-12 下午06:26:34 
* @version 1.0 *  
*/ 
public class RSACipherUtil {
    
    private static final String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRdzZZ8EEb1OCxNPCYrrbAS2GE8yh6h4YVsUWFclPt/A//SppgIVRXiBGVhTHDsPRmr8BM4nmc9yQz7Ngrkuz0LgB6/hLtuT2vcybd+IpAUfji0Zmrs6yPtKrNpF2LtviddujlUyc7LFETs/3KBQKjsd8/pb7CXh3a0rRsn669MwIDAQAB";
    
    public static RSAPublicKey getPublickey() throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] buffer = base64Decoder.decodeBuffer(PUBLIC_KEY);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    
	 /** 
     * 加密 
     * @param publicKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public static String encrypt(RSAPublicKey publicKey,String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{  
        if(publicKey!=null){  
        	byte[] srcBytes=data.getBytes();
            //Cipher负责完成加密或解密工作，基于RSA  
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");  
            //根据公钥，对Cipher对象进行初始化  
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] resultBytes = cipher.doFinal(srcBytes);  
            return StringUtil.bcd2Str(resultBytes);  
        }  
        return null;  
    }  
      
    /** 
     * 解密  
     * @param privateKey 
     * @param srcBytes 
     * @return 
     * @throws NoSuchAlgorithmException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public static String decrypt(RSAPrivateKey privateKey,String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{  
        if(privateKey!=null){  
        	byte[] srcBytes=data.getBytes();
            //Cipher负责完成加密或解密工作，基于RSA  
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");  
            //根据公钥，对Cipher对象进行初始化  
            cipher.init(Cipher.DECRYPT_MODE, privateKey);  
            byte[] resultBytes = cipher.doFinal(StringUtil.ASCII_To_BCD(srcBytes));  
            return new String(resultBytes);  
        }  
        return null;  
    }  
    
    
}
