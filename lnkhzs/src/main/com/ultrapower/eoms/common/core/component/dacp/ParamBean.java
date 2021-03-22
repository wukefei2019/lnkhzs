package com.ultrapower.eoms.common.core.component.dacp;

import java.util.LinkedHashMap;

public class ParamBean {
	
	private String sql;
	private LinkedHashMap<String, Object> paramMap;
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public LinkedHashMap<String, Object> getParamMap() {
		return paramMap;
	}
	
	public void setParamMap(LinkedHashMap<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
