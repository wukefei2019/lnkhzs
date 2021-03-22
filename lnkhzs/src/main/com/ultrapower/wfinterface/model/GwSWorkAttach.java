package com.ultrapower.wfinterface.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.ultrapower.omcs.base.model.IOmcsModel;


public class GwSWorkAttach {
	
	GwSWorkAttach model;
	
	private String backguid;
	
	private String attachhttpurl;
	
	private String attachftpurl;
	
	private String attachtype;
	
	private String attachname;
	
	private String attachsize;
	
	private String attachuploadlabel;
	
	private String attachfileid;
	
	private String attachcreater;
	
	private String attachcreatername;
	
	private String attachcreatetime;
	
	private String attachactivityid;
	
	
	

	public String getBackguid() {
		return backguid;
	}

	public void setBackguid(String backguid) {
		this.backguid = backguid;
	}

	public String getAttachhttpurl() {
		return attachhttpurl;
	}

	public void setAttachhttpurl(String attachhttpurl) {
		this.attachhttpurl = attachhttpurl;
	}

	public String getAttachftpurl() {
		return attachftpurl;
	}

	public void setAttachftpurl(String attachftpurl) {
		this.attachftpurl = attachftpurl;
	}

	public String getAttachtype() {
		return attachtype;
	}

	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}

	public String getAttachname() {
		return attachname;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public String getAttachsize() {
		return attachsize;
	}

	public void setAttachsize(String attachsize) {
		this.attachsize = attachsize;
	}

	public String getAttachuploadlabel() {
		return attachuploadlabel;
	}

	public void setAttachuploadlabel(String attachuploadlabel) {
		this.attachuploadlabel = attachuploadlabel;
	}

	public String getAttachfileid() {
		return attachfileid;
	}

	public void setAttachfileid(String attachfileid) {
		this.attachfileid = attachfileid;
	}

	public String getAttachcreater() {
		return attachcreater;
	}

	public void setAttachcreater(String attachcreater) {
		this.attachcreater = attachcreater;
	}

	public String getAttachcreatername() {
		return attachcreatername;
	}

	public void setAttachcreatername(String attachcreatername) {
		this.attachcreatername = attachcreatername;
	}

	public String getAttachcreatetime() {
		return attachcreatetime;
	}

	public void setAttachcreatetime(String attachcreatetime) {
		this.attachcreatetime = attachcreatetime;
	}

	public String getAttachactivityid() {
		return attachactivityid;
	}

	public void setAttachactivityid(String attachactivityid) {
		this.attachactivityid = attachactivityid;
	}

	public GwSWorkAttach getModel() {
		return model;
	}

	public void setModel(GwSWorkAttach model) {
		this.model = model;
	}
	
	
	
	
	
	

}
