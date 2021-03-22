package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;

@Entity
@Table(name = "ZL_TS_JTLXRMYD")
public class SatisJtlxrmyd implements ICommonModel{
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
	 * 集团决策人满意度
	 */
	private String policy_satis;
	
	/**
	 * 整体满意度
	 */
	private String whole_satis;
	
	/**
	 * 方案支撑
	 */
	private String plan;
	
	/**
	 * 履约情况
	 */
	private String performance;
	
	/**
	 * 整体(产品可靠性)
	 */
	private String whole_pro;
	
	/**
	 * 产品安全性
	 */
	private String security;
	
	/**
	 * 产品稳定性
	 */
	private String stability;
	
	/**
	 * 整体(客户经理服务)
	 */
	private String whole_ser;
	
	/**
	 * 服务态度
	 */
	private String attitude;
	
	/**
	 * 响应及时性
	 */
	private String timely;
	
	/**
	 * 账单发票服务
	 */
	private String bill;

	
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

	@Column(name = "POLICY_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPolicy_satis() {
		return policy_satis;
	}

	public void setPolicy_satis(String policy_satis) {
		this.policy_satis = policy_satis;
	}

	@Column(name = "WHOLE_SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWhole_satis() {
		return whole_satis;
	}

	public void setWhole_satis(String whole_satis) {
		this.whole_satis = whole_satis;
	}

	@Column(name = "PLAN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Column(name = "PERFORMANCE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Column(name = "WHOLE_PRO", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWhole_pro() {
		return whole_pro;
	}

	public void setWhole_pro(String whole_pro) {
		this.whole_pro = whole_pro;
	}

	@Column(name = "SECURITY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	@Column(name = "STABILITY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getStability() {
		return stability;
	}

	public void setStability(String stability) {
		this.stability = stability;
	}

	@Column(name = "WHOLE_SER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWhole_ser() {
		return whole_ser;
	}

	public void setWhole_ser(String whole_ser) {
		this.whole_ser = whole_ser;
	}

	@Column(name = "ATTITUDE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	@Column(name = "TIMELY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTimely() {
		return timely;
	}

	public void setTimely(String timely) {
		this.timely = timely;
	}

	@Column(name = "BILL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}
	
	
	
	
	
	
}
