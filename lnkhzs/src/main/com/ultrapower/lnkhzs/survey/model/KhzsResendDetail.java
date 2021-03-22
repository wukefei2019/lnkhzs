package com.ultrapower.lnkhzs.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;


@Entity
@Table(name = "ZL_KHZS_RESEND_DETAIL")
public class KhzsResendDetail implements ICommonModel{
	
	private String id;
	
	private String applytitle;
	
	private String massurl;
	
	private String durationlength;
	
	private String customercounts;
	
	private String drafshortmessage;
	
	private String selectionrulenumber;
	
	private String applicationtype;
	
	private String messagetype;
	
	private String drafttype;
	
	private String reportext;
	
	private String returntext;
	
	private String reportstatus;
	
	@Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "APPLYTITLE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getApplytitle() {
		return applytitle;
	}

	public void setApplytitle(String applytitle) {
		this.applytitle = applytitle;
	}

	@Column(name = "MASSURL", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getMassurl() {
		return massurl;
	}

	public void setMassurl(String massurl) {
		this.massurl = massurl;
	}

	@Column(name = "DURATIONLENGTH", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getDurationlength() {
		return durationlength;
	}

	public void setDurationlength(String durationlength) {
		this.durationlength = durationlength;
	}

	@Column(name = "CUSTOMERCOUNTS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getCustomercounts() {
		return customercounts;
	}

	public void setCustomercounts(String customercounts) {
		this.customercounts = customercounts;
	}

	@Column(name = "DRAFSHORTMESSAGE", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getDrafshortmessage() {
		return drafshortmessage;
	}

	public void setDrafshortmessage(String drafshortmessage) {
		this.drafshortmessage = drafshortmessage;
	}

	@Column(name = "SELECTIONRULENUMBER", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getSelectionrulenumber() {
		return selectionrulenumber;
	}

	public void setSelectionrulenumber(String selectionrulenumber) {
		this.selectionrulenumber = selectionrulenumber;
	}

	@Column(name = "APPLICATIONTYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getApplicationtype() {
		return applicationtype;
	}

	public void setApplicationtype(String applicationtype) {
		this.applicationtype = applicationtype;
	}

	@Column(name = "MESSAGETYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	@Column(name = "DRAFTTYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
	public String getDrafttype() {
		return drafttype;
	}

	public void setDrafttype(String drafttype) {
		this.drafttype = drafttype;
	}

	@Column(name = "REPORTEXT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getReportext() {
		return reportext;
	}

	public void setReportext(String reportext) {
		this.reportext = reportext;
	}

	@Column(name = "RETURNTEXT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getReturntext() {
		return returntext;
	}

	public void setReturntext(String returntext) {
		this.returntext = returntext;
	}

	@Column(name = "REPORTSTATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=20 )
	public String getReportstatus() {
		return reportstatus;
	}

	public void setReportstatus(String reportstatus) {
		this.reportstatus = reportstatus;
	}
	
	
	
}
