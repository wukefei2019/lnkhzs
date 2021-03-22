package com.ultrapower.lnkhzs.satisfaction.model;

import com.ultrapower.omcs.common.model.ICommonModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZL_TS_SJKHMYD")
public class SatisSjkhmyd implements ICommonModel{
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
	 * 指标类型_字段
	 */
	private String indextype;
	
	/**
	 * 地市
	 */
	private String resp_dept;
	
	/**
	 * 手机客户满意度
	 */
	private String satis;
	
	/**
	 * 整体满意度
	 */
	private String totalsatis;
	
	/**
	 * 资费套餐-整体
	 */
	private String tg_total;
	
	/**
	 * 资费套餐-资费规则清晰度
	 */
	private String tg_clear;
	
	/**
	 *  资费套餐-套餐办理便捷性
	 */
	private String tg_convenient;
	
	/**
	 *  资费套餐-套餐办理规范性
	 */
	private String tg_standard;
	
	/**
	 *  资费套餐-套餐内容适配度
	 */
	private String tg_fit;
	
	/**
	 *  资费套餐-账单服务
	 */
	private String tg_service;
	
	/**
	 * 网络质量-整体
	 */
	private String nq_total;
	
	/**
	 * 网络质量-手机上网质量
	 */
	private String nq_net;
	
	/**
	 * 网络质量-语音通话质量
	 */
	private String nq_tel;
	
	/**
	 * 业务宣传
	 */
	private String businesspublicity;
	
	/**
	 * 提醒服务
	 */
	private String remindservice;
	
	

	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name = "DYNUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getDynum() {
		return dynum;
	}

	public void setDynum(String dynum) {
		this.dynum = dynum;
	}

	@Column(name = "INDEXTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getIndextype() {
		return indextype;
	}

	public void setIndextype(String indextype) {
		this.indextype = indextype;
	}

	@Column(name = "TG_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_total() {
		return tg_total;
	}

	public void setTg_total(String tg_total) {
		this.tg_total = tg_total;
	}

	@Column(name = "TG_CLEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_clear() {
		return tg_clear;
	}

	public void setTg_clear(String tg_clear) {
		this.tg_clear = tg_clear;
	}

	@Column(name = "TG_CONVENIENT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_convenient() {
		return tg_convenient;
	}

	public void setTg_convenient(String tg_convenient) {
		this.tg_convenient = tg_convenient;
	}

	@Column(name = "TG_STANDARD", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_standard() {
		return tg_standard;
	}

	public void setTg_standard(String tg_standard) {
		this.tg_standard = tg_standard;
	}

	@Column(name = "TG_FIT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_fit() {
		return tg_fit;
	}

	public void setTg_fit(String tg_fit) {
		this.tg_fit = tg_fit;
	}

	@Column(name = "TG_SERVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTg_service() {
		return tg_service;
	}

	public void setTg_service(String tg_service) {
		this.tg_service = tg_service;
	}

	@Column(name = "NQ_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getNq_total() {
		return nq_total;
	}

	public void setNq_total(String nq_total) {
		this.nq_total = nq_total;
	}

	@Column(name = "NQ_NET", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getNq_net() {
		return nq_net;
	}

	public void setNq_net(String nq_net) {
		this.nq_net = nq_net;
	}

	@Column(name = "NQ_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getNq_tel() {
		return nq_tel;
	}

	public void setNq_tel(String nq_tel) {
		this.nq_tel = nq_tel;
	}

	@Column(name = "BUSINESSPUBLICITY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBusinesspublicity() {
		return businesspublicity;
	}

	public void setBusinesspublicity(String businesspublicity) {
		this.businesspublicity = businesspublicity;
	}

	@Column(name = "REMINDSERVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getRemindservice() {
		return remindservice;
	}

	public void setRemindservice(String remindservice) {
		this.remindservice = remindservice;
	}

	

	
	
	
	
	

	
}
