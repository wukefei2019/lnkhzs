package com.ultrapower.omcs.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;

/**
解密
 */
public class DES {
    private static String Algorithm = "DESede";// 加密算法的名称
    private static Cipher c;// 密码器
    private static byte[] cipherByte;
    private static SecretKey deskey;// 密钥
    private static String keyString = "A3F2569DESJEIWBCJOTY45DYQWF68H1Y";// 获得密钥的参数
    // 对base64编码的string解码成byte数组

    public byte[] deBase64(String parm) throws IOException {
        BASE64Decoder dec = new BASE64Decoder();
        byte[] dnParm = dec.decodeBuffer(parm);
        System.out.println(dnParm.length);
        System.out.println(dnParm);
        return dnParm;
    }

    // 把密钥参数转为byte数组
    public byte[] dBase64(String parm) throws IOException {
        BASE64Decoder dec = new BASE64Decoder();
        byte[] dnParm = dec.decodeBuffer(parm);
        return dnParm;
    }

    /**
     * 对 Byte 数组进行解密
     * @param buff 要解密的数据
     * @return 返回加密后的 String
     */
    public static String createDecryptor(byte[] buff)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            c.init(Cipher.DECRYPT_MODE, deskey);// 初始化密码器，用密钥deskey,进入解密模式
            cipherByte = c.doFinal(buff);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } 
        return (new String(cipherByte, "UTF-8"));
    }

    public void getKey(String key) throws IOException, InvalidKeyException, InvalidKeySpecException {
        byte[] dKey = dBase64(key);
        try {
            deskey = new javax.crypto.spec.SecretKeySpec(dKey, Algorithm);
            c = Cipher.getInstance(Algorithm);
        } catch (NoSuchPaddingException ex) {
        } catch (NoSuchAlgorithmException ex) {
        }
    }

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeySpecException, InvalidKeyException, IOException {
        DES des = new DES();
        des.getKey(keyString);// 获取密钥
        byte[] dBy = des.deBase64("Yrgr+ppQ+N/crhQQ1D3XjQ==");// 获取需要解密的字符串
        String dStr = des.createDecryptor(dBy);
        System.out.println("解密：" + dStr);
    }
    //解密
	public String deBase64ForLogin(String userNamr) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException,
	        InvalidKeySpecException, InvalidKeyException, IOException {
		DES des = new DES(); 
		des.getKey(keyString);// 获取密钥
		byte[] dBy = des.deBase64(userNamr);// 获取需要解密的字符串
		String dStr = DES.createDecryptor(dBy);
		System.out.println("解密：" + dStr);
		return dStr;
	}
}
