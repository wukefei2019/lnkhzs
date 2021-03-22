package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;

@Entity
@Table(name = "ZL_TS_JTGJRMYD")
public class SatisJtgjrmyd implements ICommonModel{
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
	 * 履约情况
	 */
	private String performance;
	
	/**
	 * 产品可靠性
	 */
	private String reliable;
	
	/**
	 * 整体(客户经理服务)
	 */
	private String whole;
	
	/**
	 * 服务技能(客户经理服务)
	 */
	private String skill;
	
	/**
	 * 服务态度(客户经理服务)
	 */
	private String attitude;
	
	/**
	 * 响应及时性(客户经理服务)
	 */
	private String timely;

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

	@Column(name = "PERFORMANCE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Column(name = "RELIABLE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getReliable() {
		return reliable;
	}

	public void setReliable(String reliable) {
		this.reliable = reliable;
	}

	@Column(name = "WHOLE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWhole() {
		return whole;
	}

	public void setWhole(String whole) {
		this.whole = whole;
	}

	@Column(name = "SKILL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
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
	
	
}
