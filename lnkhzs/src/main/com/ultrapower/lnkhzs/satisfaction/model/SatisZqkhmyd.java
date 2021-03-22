package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;

@Entity
@Table(name = "ZL_TS_ZQKHTSMYD")
public class SatisZqkhmyd implements ICommonModel{
	private static final long serialVersionUID = -243079311191118188L;
	
	/**
	 * ID 字段
	 */
	private String id;

	/**
	 * 调研期数_字段
	 */
	private String dynum;

	/**
	 * 集团归属地市_字段
	 */
	private String resp_dept;
	
	/**
	 * 政企客户满意度
	 */
	private String cust_satis;
	
	/**
	 * 集团关键人满意度
	 */
	private String group_satis;
	
	/**
	 * 集团联系人满意度
	 */
	private String contacts;
	
	/**
	 * 集团专线开通满意度
	 */
	private String line_satis;
	
	/**
	 * 企业宽带开通满意度
	 */
	private String open_satis;

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DYNUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDynum() {
		return dynum;
	}

	public void setDynum(String dynum) {
		this.dynum = dynum;
	}

	@Column(name = "RESP_DEPT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getResp_dept() {
		return resp_dept;
	}

	public void setResp_dept(String resp_dept) {
		this.resp_dept = resp_dept;
	}

	@Column(name = "CUST_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCust_satis() {
		return cust_satis;
	}

	public void setCust_satis(String cust_satis) {
		this.cust_satis = cust_satis;
	}

	@Column(name = "GROUP_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getGroup_satis() {
		return group_satis;
	}

	public void setGroup_satis(String group_satis) {
		this.group_satis = group_satis;
	}

	@Column(name = "CONTACTS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "LINE_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getLine_satis() {
		return line_satis;
	}

	public void setLine_satis(String line_satis) {
		this.line_satis = line_satis;
	}

	@Column(name = "OPEN_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOpen_satis() {
		return open_satis;
	}

	public void setOpen_satis(String open_satis) {
		this.open_satis = open_satis;
	}
	
	
	
	
	
	
}
