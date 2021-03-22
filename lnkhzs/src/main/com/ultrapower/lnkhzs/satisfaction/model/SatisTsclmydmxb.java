package com.ultrapower.lnkhzs.satisfaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ultrapower.omcs.common.model.ICommonModel;


@Entity
@Table(name = "ZL_TS_TSCLMYD_DETALL")
public class SatisTsclmydmxb implements ICommonModel {
	private static final long serialVersionUID = -243079311191118188L;
	

	/**
	 * ID 字段
	 */
	private String id;

	/**
	 * 调研月份_字段
	 */
	private String month;

	/**
	 * 地市/单位_字段
	 */
	private String resp_dept;
	
	/**
	 * 县区_字段
	 */
	private String area_name;
	
	/**
	 * 网格_字段
	 */
	private String reseau;
	/**
	 * 手机号码_字段
	 */
	private String phone_number;
	/**
	 * 投诉工单号_字段
	 */
	private String order_number;
	/**
	 *投诉立单时间_字段
	 */
	private String order_stime;
	/**
	 * 投诉类型_字段
	 */
	private String order_type;
	/**
	 *责任部门_字段
	 */
	private String r_department;
	/**
	 *投诉结单时间_字段
	 */
	private String order_etime;
	/**
	 * 是否解决_字段
	 */
	private String question;
	/**
	 * 处理工作组_字段
	 */
	private String work_team;
	/**
	 * 首处理工作组_字段
	 */
	private String fwork_team;
	/**
	 * 协办工作组_字段
	 */
	private String awork_team;
	/**
	 * 整体时限时间_字段
	 */
	private String overall_time;
	/**
	 * 处理时长_字段
	 */
	private String cl_time;
	/**
	 * 本次投诉处理满意度_字段
	 */
	private String satis;
	
	/**
	 * 您投诉的问题是否解决_字段
	 */
	private String nquestion;
	/**
	 * 营业厅受理投诉服务满意度（只涉及营业厅投诉）_字段
	 */
	private String ntime;
	
	

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
	
	@Column(name = "AREA_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	
	@Column(name = "RESEAU", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getReseau() {
		return reseau;
	}
	public void setReseau(String reseau) {
		this.reseau = reseau;
	}
	
	@Column(name = "PHONE_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	@Column(name = "ORDER_NUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	
	@Column(name = "ORDER_STIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOrder_stime() {
		return order_stime;
	}
	public void setOrder_stime(String order_stime) {
		this.order_stime = order_stime;
	}
	
	@Column(name = "ORDER_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	
	@Column(name = "R_DEPARTMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getR_department() {
		return r_department;
	}
	public void setR_department(String r_department) {
		this.r_department = r_department;
	}
	
	@Column(name = "ORDER_ETIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOrder_etime() {
		return order_etime;
	}
	public void setOrder_etime(String order_etime) {
		this.order_etime = order_etime;
	}
	
	@Column(name = "QUESTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name = "WORK_TEAM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getWork_team() {
		return work_team;
	}
	public void setWork_team(String work_team) {
		this.work_team = work_team;
	}
	
	@Column(name = "FWORK_TEAM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getFwork_team() {
		return fwork_team;
	}
	public void setFwork_team(String fwork_team) {
		this.fwork_team = fwork_team;
	}
	
	@Column(name = "AWORK_TEAM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getAwork_team() {
		return awork_team;
	}
	public void setAwork_team(String awork_team) {
		this.awork_team = awork_team;
	}
	
	@Column(name = "OVERALL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getOverall_time() {
		return overall_time;
	}
	public void setOverall_time(String overall_time) {
		this.overall_time = overall_time;
	}
	
	@Column(name = "CL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getCl_time() {
		return cl_time;
	}
	public void setCl_time(String cl_time) {
		this.cl_time = cl_time;
	}
	
	@Column(name = "SATIS", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSatis() {
		return satis;
	}
	public void setSatis(String satis) {
		this.satis = satis;
	}
	
	@Column(name = "NQUESTION", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getNquestion() {
		return nquestion;
	}
	public void setNquestion(String nquestion) {
		this.nquestion = nquestion;
	}
	
	@Column(name = "NTIME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getNtime() {
		return ntime;
	}
	public void setNtime(String ntime) {
		this.ntime = ntime;
	}

	
	
	

}
