package com.ultrapower.wfinterface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.ultrapower.omcs.base.model.IOmcsModel;


public class GwSWork {
	
	GwSWork model;
	
	private String status;
	
	/**
	 * 流程返回内容
	 */
	
	private String resultstr;
	
	private String backguid;
	
	private String backhandlingtime;
	
	private String backhandlingperson;
	
	private String backhandlingpersonlogin;
	
	private String backresolution;
	
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RESULTSTR", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getResultstr() {
		return resultstr;
	}

	public void setResultstr(String resultstr) {
		this.resultstr = resultstr;
	}

	@Column(name = "BACKGUID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackguid() {
		return backguid;
	}

	public void setBackguid(String backguid) {
		this.backguid = backguid;
	}

	@Column(name = "BACKHANDLINGTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingtime() {
		return backhandlingtime;
	}

	public void setBackhandlingtime(String backhandlingtime) {
		this.backhandlingtime = backhandlingtime;
	}

	@Column(name = "BACKHANDLINGPERSON", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingperson() {
		return backhandlingperson;
	}

	public void setBackhandlingperson(String backhandlingperson) {
		this.backhandlingperson = backhandlingperson;
	}

	@Column(name = "BACKHANDLINGPERSONLOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackhandlingpersonlogin() {
		return backhandlingpersonlogin;
	}

	public void setBackhandlingpersonlogin(String backhandlingpersonlogin) {
		this.backhandlingpersonlogin = backhandlingpersonlogin;
	}

	@Column(name = "BACKRESOLUTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBackresolution() {
		return backresolution;
	}

	public void setBackresolution(String backresolution) {
		this.backresolution = backresolution;
	}

	public GwSWork getModel() {
		return model;
	}

	public void setModel(GwSWork model) {
		this.model = model;
	}
	
	
	
	
	
	

}
