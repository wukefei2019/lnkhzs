package com.ultrapower.wfinterface.model;

import java.lang.reflect.Method;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ultrapower.eoms.common.core.util.TimeUtils;

public class GwInfoJobData {
	GwInfoJobData model;
	private String id;
	private String pid;
	private String jobdepart;
	private String jobperson;
	private String jobcontent;
	private String jobdate;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getJobdepart() {
		return jobdepart;
	}
	public void setJobdepart(String jobdepart) {
		this.jobdepart = jobdepart;
	}
	public String getJobperson() {
		return jobperson;
	}
	public void setJobperson(String jobperson) {
		this.jobperson = jobperson;
	}
	public String getJobcontent() {
		return jobcontent;
	}
	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}
	public String getJobdate() {
		return jobdate;
	}
	public void setJobdate(String jobdate) {
		this.jobdate = jobdate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public GwInfoJobData getModel() {
		return model;
	}

	public void setModel(GwInfoJobData model) {
		this.model = model;
	}
	

}
