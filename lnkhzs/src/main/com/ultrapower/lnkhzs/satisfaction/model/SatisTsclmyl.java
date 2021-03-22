package com.ultrapower.lnkhzs.satisfaction.model;

import com.ultrapower.omcs.common.model.ICommonModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZL_TS_TSCLMYD")
public class SatisTsclmyl implements ICommonModel{
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
	 * 投诉处理整体满意度
	 */
	private String satis;
	
	/**
	 * 问题是否解决-是
	 */
	private String questiony;
	
	/**
	 * 问题是否解决-否
	 */
	private String questionn;
	
	
	/**
	 * 投诉处理时长(3天之内)
	 */
	private String threeago;
	
	
	/**
	 * 投诉处理时长(3-5天)
	 */
	private String threefive;
	
	/**
	 * 投诉处理时长(5天以上)
	 */
	private String fivelater;
	
	
	/**
	 * 样本量
	 */
	private String sample_num;
	
	
	
	
	
	
	

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


	@Column(name = "SAMPLE_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getSample_num() {
		return sample_num;
	}

	public void setSample_num(String sample_num) {
		this.sample_num = sample_num;
	}
	

	@Column(name = "THREEFIVE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getThreefive() {
		return threefive;
	}

	public void setThreefive(String threefive) {
		this.threefive = threefive;
	}

	@Column(name = "FIVELATER", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getFivelater() {
		return fivelater;
	}

	public void setFivelater(String fivelater) {
		this.fivelater = fivelater;
	}

	@Column(name = "THREEAGO", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getThreeago() {
		return threeago;
	}

	public void setThreeago(String threeago) {
		this.threeago = threeago;
	}

	@Column(name = "QUESTIONY", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getQuestiony() {
		return questiony;
	}

	public void setQuestiony(String questiony) {
		this.questiony = questiony;
	}

	@Column(name = "QUESTIONN", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getQuestionn() {
		return questionn;
	}

	public void setQuestionn(String questionn) {
		this.questionn = questionn;
	}
	
	
	

	
}
