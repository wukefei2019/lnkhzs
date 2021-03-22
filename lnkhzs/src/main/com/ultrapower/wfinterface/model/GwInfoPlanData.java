package com.ultrapower.wfinterface.model;

import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ultrapower.eoms.common.core.util.TimeUtils;

public class GwInfoPlanData {
	GwInfoPlanData model;
	private String id;
	private String pid;
	private String plandepart;
	private String planperson;
	private String plantarget;
	private String plancontent;
	private String plandate;
	
	
	
	
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPlandepart() {
		return plandepart;
	}
	public void setPlandepart(String plandepart) {
		this.plandepart = plandepart;
	}
	public String getPlanperson() {
		return planperson;
	}
	public void setPlanperson(String planperson) {
		this.planperson = planperson;
	}
	public String getPlantarget() {
		return plantarget;
	}
	public void setPlantarget(String plantarget) {
		this.plantarget = plantarget;
	}
	public String getPlancontent() {
		return plancontent;
	}
	public void setPlancontent(String plancontent) {
		this.plancontent = plancontent;
	}
	public String getPlandate() {
		return plandate;
	}
	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public GwInfoPlanData getModel() {
		return model;
	}

	public void setModel(GwInfoPlanData model) {
		this.model = model;
	}
	

}
