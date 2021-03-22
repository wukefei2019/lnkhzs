package com.ultrapower.lnkhzs.satisfaction.model;

import com.ultrapower.omcs.common.model.ICommonModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZL_TS_KDDSMYD")
public class SatisKddsmyd implements ICommonModel{
	private static final long serialVersionUID = -243079311191118188L;
	
	
	/**
	 * ID 字段
	 */
	private String id;

	/**
	 * 月份_字段
	 */
	private String dynum;

	/**
	 * 地市_字段
	 */
	private String resp_dept;
	
	/**
	 * 宽带电视满意度
	 */
	private String satis;
	
	/**
	 * 整体满意度
	 */
	private String totalsatis;
	
	/**
	 * 终端感知质量-整体
	 */
	private String tpq_total;
	
	/**
	 * 终端感知质量-盒子器感知质量
	 */
	private String tpq_box;
	
	/**
	 * 终端感知质量-遥控器感知质量
	 */
	private String tpq_rcontrol;
	
	/**
	 * 内容体验-整体
	 */
	private String ce_total;
	
	/**
	 * 内容体验-视频及电视频道资源丰富度
	 */
	private String ce_rich;
	
	/**
	 * 内容体验-视频资源更新速度
	 */
	private String ce_speed;
	
	/**
	 * 使用体验-整体
	 */
	private String ue_total;
	
	/**
	 * 使用体验-视频流畅度
	 */
	private String ue_smooth;
	
	/**
	 * 使用体验-操作界面
	 */
	private String ue_oper;
	
	/**
	 * 业务办理退订体验-整体
	 */
	private String bhue_total;
	
	/**
	 * 业务办理退订体验-业务办理
	 */
	private String bhue_handle;
	
	/**
	 * 业务办理退订体验-业务退订
	 */
	private String bhue_cancel;
	
	/**
	 * 售后服务-故障维修服务
	 */
	private String aftersaleservice;
	

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

	@Column(name = "TPQ_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTpq_total() {
		return tpq_total;
	}

	public void setTpq_total(String tpq_total) {
		this.tpq_total = tpq_total;
	}

	@Column(name = "TPQ_BOX", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTpq_box() {
		return tpq_box;
	}

	public void setTpq_box(String tpq_box) {
		this.tpq_box = tpq_box;
	}

	@Column(name = "TPQ_RCONTROL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getTpq_rcontrol() {
		return tpq_rcontrol;
	}

	public void setTpq_rcontrol(String tpq_rcontrol) {
		this.tpq_rcontrol = tpq_rcontrol;
	}

	@Column(name = "CE_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCe_total() {
		return ce_total;
	}

	public void setCe_total(String ce_total) {
		this.ce_total = ce_total;
	}

	@Column(name = "CE_RICH", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCe_rich() {
		return ce_rich;
	}

	public void setCe_rich(String ce_rich) {
		this.ce_rich = ce_rich;
	}

	@Column(name = "CE_SPEED", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCe_speed() {
		return ce_speed;
	}

	public void setCe_speed(String ce_speed) {
		this.ce_speed = ce_speed;
	}

	@Column(name = "UE_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getUe_total() {
		return ue_total;
	}

	public void setUe_total(String ue_total) {
		this.ue_total = ue_total;
	}

	@Column(name = "UE_SMOOTH", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getUe_smooth() {
		return ue_smooth;
	}

	public void setUe_smooth(String ue_smooth) {
		this.ue_smooth = ue_smooth;
	}
	
	@Column(name = "UE_OPER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getUe_oper() {
		return ue_oper;
	}

	public void setUe_oper(String ue_oper) {
		this.ue_oper = ue_oper;
	}

	@Column(name = "BHUE_TOTAL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBhue_total() {
		return bhue_total;
	}

	public void setBhue_total(String bhue_total) {
		this.bhue_total = bhue_total;
	}

	@Column(name = "BHUE_HANDLE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBhue_handle() {
		return bhue_handle;
	}

	public void setBhue_handle(String bhue_handle) {
		this.bhue_handle = bhue_handle;
	}

	@Column(name = "BHUE_CANCEL", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getBhue_cancel() {
		return bhue_cancel;
	}

	public void setBhue_cancel(String bhue_cancel) {
		this.bhue_cancel = bhue_cancel;
	}

	@Column(name = "AFTERSALESERVICE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getAftersaleservice() {
		return aftersaleservice;
	}

	public void setAftersaleservice(String aftersaleservice) {
		this.aftersaleservice = aftersaleservice;
	}

	
	
	
	
	

	
}
