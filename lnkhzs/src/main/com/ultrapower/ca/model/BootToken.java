package com.ultrapower.ca.model;

import java.io.Serializable;
import java.security.interfaces.RSAPublicKey;

import com.ultrapower.ca.constant.RequestState;

/**
 * * @ClassName: BootToken
 * 
 * @Description: TODO
 * @author zhangzongbao
 * @date 2013-9-12 下午06:20:34
 * @version 1.0 *
 */
public class BootToken implements Serializable {
	private static final long serialVersionUID = 1L;
	// 公钥
	private RSAPublicKey publicKey;
	// 干扰码
	private String interferenceCode;
	// 类型，1 OpenCA根票据 2 4A根票据
	private String type = "1";
	// 根票据
	private String tokenCode;
   //手机号
	private String mobile;
	//账号
	private String account;
	//密码
	private String encodepassword;
	//用户名
	private String username;
	//部门名
	private String deptname;
	//头像地址url
	private String portralurl;
	//邮箱
	private String email;
	//用户地址
	private String address;
	//固话
	private String telephone;
	//个性签名(备注)
	private String note;
	//组织机构id
	private String deptid;
	
	private RequestState state = RequestState.NORMAL;
	
//	public RSAPublicKey getPublicKey() {
//		return publicKey;
//	}
//
//	public void setPublicKey(RSAPublicKey publicKey) {
//		this.publicKey = publicKey;
//	}
//
//	public String getInterferenceCode() {
//		return interferenceCode;
//	}
//
//	public void setInterferenceCode(String interferenceCode) {
//		this.interferenceCode = interferenceCode;
//	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getPortralurl() {
		return portralurl;
	}

	public void setPortralurl(String portralurl) {
		this.portralurl = portralurl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}
		
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	
	
	public String getEncodepassword() {
		return encodepassword;
	}

	public void setEncodepassword(String encodepassword) {
		this.encodepassword = encodepassword;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "account:"+account+"\tmobile:"+mobile+"\tpassword:"+encodepassword+"\ttype:"+type+"\ttokenCode:"+tokenCode;
	}
}
