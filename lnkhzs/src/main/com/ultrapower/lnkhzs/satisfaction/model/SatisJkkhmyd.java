package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;

@Entity
@Table(name = "ZL_TS_JKKHMYD")
public class SatisJkkhmyd implements ICommonModel{
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
	 * 指标类型
	 */
	private String indextype;

	/**
	 * 地市
	 */
	private String resp_dept;
	
	/**
	 * 家宽客户满意度
	 */
	private String customer_satis;
	
	/**
	 * 整体满意度
	 */
	private String whole_satis;
	
	
	/**
	 * 资费套餐
	 */
	private String packagefee;
	
	/**
	 * 上网质量
	 */
	private String quality;
	
	/**
	 * 整体(业务宣传办理)
	 */
	private String whole_business;
	
	/**
	 * 业务宣传(业务宣传办理)
	 */
	private String propaganda;
	
	/**
	 * 业务办理(业务宣传办理)
	 */
	private String handle;
	
	/**
	 * 整体(服务水平)
	 */
	private String whole_service;
	
	/**
	 * 装机服务(服务水平)
	 */
	private String install;
	
	/**
	 * 故障维修(服务水平)
	 */
	private String fault;
	
	/**
	 * 咨询投诉(服务水平)
	 */
	private String consultation;

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "DYNUM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getDynum() {
		return dynum;
	}

	public void setDynum(String dynum) {
		this.dynum = dynum;
	}

	@Column(name = "INDEXTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getIndextype() {
		return indextype;
	}

	public void setIndextype(String indextype) {
		this.indextype = indextype;
	}

	@Column(name = "RESP_DEPT", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getResp_dept() {
		return resp_dept;
	}

	public void setResp_dept(String resp_dept) {
		this.resp_dept = resp_dept;
	}

	@Column(name = "CUSTOMER_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCustomer_satis() {
		return customer_satis;
	}

	public void setCustomer_satis(String customer_satis) {
		this.customer_satis = customer_satis;
	}

	@Column(name = "WHOLE_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWhole_satis() {
		return whole_satis;
	}

	public void setWhole_satis(String whole_satis) {
		this.whole_satis = whole_satis;
	}

	@Column(name = "PACKAGEFEE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPackagefee() {
		return packagefee;
	}

	public void setPackagefee(String packagefee) {
		this.packagefee = packagefee;
	}

	@Column(name = "QUALITY", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "WHOLE_BUSINESS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWhole_business() {
		return whole_business;
	}

	public void setWhole_business(String whole_business) {
		this.whole_business = whole_business;
	}

	@Column(name = "PROPAGANDA", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getPropaganda() {
		return propaganda;
	}

	public void setPropaganda(String propaganda) {
		this.propaganda = propaganda;
	}

	@Column(name = "HANDLE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	@Column(name = "WHOLE_SERVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getWhole_service() {
		return whole_service;
	}

	public void setWhole_service(String whole_service) {
		this.whole_service = whole_service;
	}

	@Column(name = "INSTALL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getInstall() {
		return install;
	}

	public void setInstall(String install) {
		this.install = install;
	}

	@Column(name = "FAULT", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	@Column(name = "CONSULTATION", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getConsultation() {
		return consultation;
	}

	public void setConsultation(String consultation) {
		this.consultation = consultation;
	}

}
