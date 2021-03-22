package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;

@Entity
@Table(name = "ZL_TS_STKHMYD")
public class SatisStkhmyd implements ICommonModel{
	private static final long serialVersionUID = -243079311191118188L;

	/**
	 * ID 字段
	 */
	private String id;
	
	/**
	 * 调研期数
	 */
	private String dynum;
	
	/**
	 * 地市
	 */
	private String resp_dept;
	
	/**
	 * 手厅客户满意度
	 */
	private String cust_satis;
	
	/**
	 * 业务丰富度（50%）
	 */
	private String business;
	
	/**
	 * 促销优惠体验（50%）
	 */
	private String discount;

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

	@Column(name = "BUSINESS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	@Column(name = "DISCOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	
	
}
