package com.ultrapower.ca.model;

import java.io.Serializable;

/**
 * * @ClassName: SubToken
 * 
 * @Description: TODO
 * @author zhangzongbao
 * @date 2013-9-12 下午06:20:44
 * @version 1.0 *
 */
public class SubToken implements Serializable {

    private static final long serialVersionUID = 1L;
    // 请求token码,生成格式为 定长的客户端key-32位干扰码-公钥加密字符串
    private String code;
    private String type;
    private String state;
    private String message;
    private long timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
