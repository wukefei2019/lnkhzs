package com.ultrapower.eoms.ultrasm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "BS_T_SM_INTERCEPTCONFIG_LOG")
public class InterceptConfigLog {
	private String pid;
	private String urlpath;
	private String functionname;
	private String interceptpid;
	private Long operatetime;
	private String loginname;
	private String ip;
	private String param1;
	private String param2;
	private String param3;
	
	public InterceptConfigLog(){
		
	}
	
	public InterceptConfigLog(String pid,String urlpath,String functionname,String interceptpid,Long operatetime,String loginname,String ip,String param1,String param2,String param3){
		this.pid = pid;
		this.urlpath = urlpath;
		this.functionname = functionname;
		this.interceptpid = interceptpid;
		this.operatetime = operatetime;
		this.loginname = loginname;
		this.ip = ip;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "PID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name="URLPATH", length=1000)
	public String getUrlpath() {
		return urlpath;
	}

	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	@Column(name="FUNCTIONNAME", length=50)
	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	@Column(name="INTERCEPTPID", length=50)
	public String getInterceptpid() {
		return interceptpid;
	}

	public void setInterceptpid(String interceptpid) {
		this.interceptpid = interceptpid;
	}

	@Column(name = "OPERATETIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Long operatetime) {
		this.operatetime = operatetime;
	}

	@Column(name="LOGINNAME", length=100)
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name="IP", length=100)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="PARAM1", length=100)
	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	@Column(name="PARAM2", length=100)
	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	@Column(name="PARAM3", length=100)
	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}
}
