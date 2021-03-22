package com.ultrapower.lnkhzs.survey.model;

import java.util.List;
import java.util.Map;

import com.ultrapower.omcs.common.model.ICommonModel;

public class KhzsQuestionBean implements ICommonModel {

	private static final long serialVersionUID = -5058750862254132095L;

	/**
	 * 题号_字段
	 */
	private String id;
	/**
	 * 标签，分类_字段
	 */
	private String category;
	/**
	 * 题目_字段
	 */
	private String question;

	/**
	 * 题型（单选，多选，判断，简答）_字段
	 */
	private String type;
	/**
	 * 选项A
	 */
	private String optionA;
	/**
	 * 选项B
	 */
	private String optionB;
	/**
	 * 选项C
	 */
	private String optionC;
	/**
	 * 选项D
	 */
	private String optionD;
	/**
	 * 选项E
	 */
	private String optionE;
	/**
	 * 选项F
	 */
	private String optionF;
	/**
	 * 选项G
	 */
	private String optionG;
	/**
	 * 选项H
	 */
	private String optionH;
	
	private String optionI;
	private String optionJ;
	private String optionK;
	private String optionL;
	private String optionM;
	private String optionN;
	private String optionO;
	private String optionP;
	
	private List<Map<Object, Object>> list;
	
	

	/**
	 * 注意
	 */
	private String remark;
	/**
	 * 排序
	 */
	private String orders;
	
	private String isanswer;

	public String getIsanswer() {
		return isanswer;
	}

	public void setIsanswer(String isanswer) {
		this.isanswer = isanswer;
	}

	public KhzsQuestionBean() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getOptionE() {
		return optionE;
	}

	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}

	public String getOptionF() {
		return optionF;
	}

	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}

	public String getOptionG() {
		return optionG;
	}

	public void setOptionG(String optionG) {
		this.optionG = optionG;
	}

	public String getOptionH() {
		return optionH;
	}

	public void setOptionH(String optionH) {
		this.optionH = optionH;
	}
	
	public String getOptionI() {
		return optionI;
	}

	public void setOptionI(String optionI) {
		this.optionI = optionI;
	}

	public String getOptionJ() {
		return optionJ;
	}

	public void setOptionJ(String optionJ) {
		this.optionJ = optionJ;
	}

	public String getOptionK() {
		return optionK;
	}

	public void setOptionK(String optionK) {
		this.optionK = optionK;
	}

	public String getOptionL() {
		return optionL;
	}

	public void setOptionL(String optionL) {
		this.optionL = optionL;
	}

	public String getOptionM() {
		return optionM;
	}

	public void setOptionM(String optionM) {
		this.optionM = optionM;
	}

	public String getOptionN() {
		return optionN;
	}

	public void setOptionN(String optionN) {
		this.optionN = optionN;
	}

	public String getOptionO() {
		return optionO;
	}

	public void setOptionO(String optionO) {
		this.optionO = optionO;
	}

	public String getOptionP() {
		return optionP;
	}

	public void setOptionP(String optionP) {
		this.optionP = optionP;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}
	
	

	public List<Map<Object, Object>> getList() {
		return list;
	}

	public void setList(List<Map<Object, Object>> list) {
		this.list = list;
	}
	
	
	@Override
	public String toString() {
		return "KhzsQuestionBean [id=" + id + ", category=" + category + ", question=" + question + ", type=" + type
				+ ", optionA=" + optionA + ", optionB=" + optionB + ", optionC=" + optionC + ", optionD=" + optionD
				+ ", optionE=" + optionE + ", optionF=" + optionF + ", optionG=" + optionG + ", optionH=" + optionH
				+ ", remark=" + remark + ", orders=" + orders + "]";
	}

}
