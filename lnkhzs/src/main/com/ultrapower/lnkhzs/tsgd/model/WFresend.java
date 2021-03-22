package com.ultrapower.lnkhzs.tsgd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.base.model.IOmcsModel;

@Entity
@Table(name = "NGCS_WF_CMPLNTS_RESEND_DETAIL")
public class WFresend implements IOmcsModel{

	/**
     *id_字段 
     */
    private String id;
    
    /**
     *标题 字段 
     */
    private String applytitle;
    
    /**
     *问题分类 字段 
     */
    private String mattercategory;
    
    /**
     *标签分类 字段 
     */
    private String labelcategory;
    
    /**
     *主责部门 字段 
     */
    private String maindepart;
    
    /**
     *主责部门正职登录名 字段 
     */
    private String maindepartid;
    
    /**
     *截止日期 字段 
     */
    private String expirydate;
    
    /**
     *派单主题 字段 
     */
    private String subject;
    
    /**
     *派单内容 字段 
     */
    private String content;
    
    /**
     *验证方式 字段 
     */
    private String validmethod;
    
    /**
     *验证值 字段 
     */
    private String validvalue;
    
    /**
     *报文 字段 
     */
    private String reportext;
    
    /**
     *返回内容 字段 
     */
    private String returntext;
    
    /**
     * 状态:保存,已派发
     */
    private String reportstatus;
    
    /**
     * 状态:目标
     */
    private String target;
    
    /**
     * 发布日期
     */
    private String createtime;

    @Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "APPLYTITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getApplytitle() {
		return applytitle;
	}
	
	public void setApplytitle(String applytitle) {
		this.applytitle = applytitle;
	}

	@Column(name = "MATTERCATEGORY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMattercategory() {
		return mattercategory;
	}

	public void setMattercategory(String mattercategory) {
		this.mattercategory = mattercategory;
	}

	@Column(name = "LABELCATEGORY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getLabelcategory() {
		return labelcategory;
	}

	public void setLabelcategory(String labelcategory) {
		this.labelcategory = labelcategory;
	}

	@Column(name = "MAINDEPART", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMaindepart() {
		return maindepart;
	}

	public void setMaindepart(String maindepart) {
		this.maindepart = maindepart;
	}

	@Column(name = "MAINDEPARTID", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMaindepartid() {
		return maindepartid;
	}

	public void setMaindepartid(String maindepartid) {
		this.maindepartid = maindepartid;
	}

	@Column(name = "EXPIRYDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	@Column(name = "SUBJECT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "CONTENT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "VALIDMETHOD", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getValidmethod() {
		return validmethod;
	}

	public void setValidmethod(String validmethod) {
		this.validmethod = validmethod;
	}

	@Column(name = "VALIDVALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getValidvalue() {
		return validvalue;
	}

	public void setValidvalue(String validvalue) {
		this.validvalue = validvalue;
	}

	@Column(name = "REPORTEXT", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getReportext() {
		return reportext;
	}

	public void setReportext(String reportext) {
		this.reportext = reportext;
	}

	@Column(name = "RETURNTEXT", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getReturntext() {
		return returntext;
	}

	public void setReturntext(String returntext) {
		this.returntext = returntext;
	}

	@Column(name = "REPORTSTATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getReportstatus() {
		return reportstatus;
	}

	public void setReportstatus(String reportstatus) {
		this.reportstatus = reportstatus;
	}

	@Column(name = "TARGET", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCreatetime() {
		return createtime;
	}

	@Column(name = "CREATETIME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
	
    
    
	
}
