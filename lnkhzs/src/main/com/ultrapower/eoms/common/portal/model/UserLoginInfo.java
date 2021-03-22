package com.ultrapower.eoms.common.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BS_T_SM_USER_LOGIN_HISTORY")	
public class UserLoginInfo {
	private String pid;
	private String loginname;
	private String fullname;
	private Long logintime;
	private String loginip;
	private String logintype;
	private int status;
	
	public UserLoginInfo()
	{
		
	}
	
	public UserLoginInfo(String pid)
	{
		this.pid = pid;
	}
	
	public UserLoginInfo(String pid, String loginname, String fullname, Long logintime, String loginip, String logintype) 
	{
		this.pid = pid;
		this.loginname = loginname;
		this.fullname = fullname;
		this.logintime = logintime;
		this.loginip = loginip;
		this.logintype = logintype;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")
	@Column(name = "PID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "LOGINNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "FULLNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "LOGINTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 15, scale = 0)
	public Long getLogintime() {
		return this.logintime;
	}

	public void setLogintime(Long logintime) {
		if (logintime == null)
			return;
		this.logintime = logintime;
	}

	@Column(name = "LOGINIP", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	@Column(name = "LOGINTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	
	@Transient
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
