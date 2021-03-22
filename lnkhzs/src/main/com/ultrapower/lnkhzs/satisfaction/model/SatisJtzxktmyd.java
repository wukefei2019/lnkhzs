package com.ultrapower.lnkhzs.satisfaction.model;

import com.ultrapower.omcs.common.model.ICommonModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZL_TS_JTZXKTMYD")
public class SatisJtzxktmyd implements ICommonModel{
	private static final long serialVersionUID = -243079311191118188L;
	
	
	/**
	 * ID 字段
	 */
	private String id;

	/**
	 * 月份_字段
	 */
	private String month;

	/**
	 * 地市_字段
	 */
	private String resp_dept;
	
	/**
	 * 专线开通满意度
	 */
	private String satis;
	
	/**
	 * 开通整体满意度（10%）
	 */
	private String totalsatis;
	
	/**
	 * 开通时效性（30%）
	 */
	private String timeliness;
	
	/**
	 * 施工专业性(30%)
	 */
	private String speciality;
	
	/**
	 * 施工人员服务(30%)
	 */
	private String personservice;
	
	/**
	 * 样本量
	 */
	private String sample_num;
	
	
	/**
	 * 顺序
	 */
	/*private Integer row_num;*/
	
	
	
	
	
	
	

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "MONTH", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Column(name = "RESP_DEPT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getResp_dept() {
		return resp_dept;
	}

	public void setResp_dept(String resp_dept) {
		this.resp_dept = resp_dept;
	}

	@Column(name = "SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSatis() {
		return satis;
	}

	public void setSatis(String satis) {
		this.satis = satis;
	}
	
	@Column(name = "TOTALSATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTotalsatis() {
		return totalsatis;
	}

	public void setTotalsatis(String totalsatis) {
		this.totalsatis = totalsatis;
	}

	@Column(name = "TIMELINESS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	@Column(name = "SPECIALITY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Column(name = "PERSONSERVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPersonservice() {
		return personservice;
	}

	public void setPersonservice(String personservice) {
		this.personservice = personservice;
	}

	@Column(name = "SAMPLE_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSample_num() {
		return sample_num;
	}

	public void setSample_num(String sample_num) {
		this.sample_num = sample_num;
	}

	/*@Column(name = "ROW_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public Integer getRow_num() {
		return row_num;
	}

	public void setRow_num(Integer row_num) {
		this.row_num = row_num;
	}*/
	
	
	
	
	

	
}
