package com.ultrapower.eoms.ultrasm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "BS_T_SM_INTERCEPTCONFIG")
public class InterceptConfig {
	private String pid;
	private String urlpath;
	private String functionname;
	private String param1;
	private String param2;
	private String param3;
	
	public InterceptConfig(){
		
	}
	
	public InterceptConfig(String pid,String urlpath,String functionname,String param1,String param2,String param3){
		this.pid = pid;
		this.urlpath = urlpath;
		this.functionname = functionname;
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

	@Column(name="FUNCTIONNAME", length=100)
	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
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
