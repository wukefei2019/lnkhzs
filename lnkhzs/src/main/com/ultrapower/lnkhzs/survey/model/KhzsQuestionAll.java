package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_实体]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_QUESTION_ALL")
public class KhzsQuestionAll implements ICommonModel {
	private static final long serialVersionUID = -243079311191118188L;

	/**
	 * 标签，分类_字段
	 */
	private String category;

	/**
	 * 题号_字段
	 */
	private String id;

	/**
	 * 选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）_字段
	 */
	//private String options;

	/**
	 * 题目_字段
	 */
	private String question;
	
	/**
	 * 选项A
	 */
	private String optiona;
	
	/**
	 * 选项B
	 */
	private String optionb;
	
	/**
	 * 选项C
	 */
	private String optionc;
	
	/**
	 * 选项D
	 */
	private String optiond;
	
	/**
	 * 选项E
	 */
	private String optione;
	
	/**
	 * 选项F
	 */
	private String optionf;
	
	/**
	 * 选项G
	 */
	private String optiong;
	
	/**
	 * 选项H
	 */
	private String optionh;
	
	/**
	 * 选项I
	 */
	private String optioni;
	
	
	/**
	 * 选项J
	 */
	private String optionj;
	
	/**
	 * 选项K
	 */
	private String optionk;
	
	/**
	 * 选项L
	 */
	private String optionl;
	
	/**
	 * 选项M
	 */
	private String optionm;
	
	/**
	 * 选项N
	 */
	private String optionn;
	
	/**
	 * 选项O
	 */
	private String optiono;
	
	/**
	 * 选项P
	 */
	private String optionp;

	/**
	 * 题型（单选，多选，判断，简答）_字段
	 */
	private String type;
	
	/**
	 * 预留
	 */
	private String remark;
	
	
	/**
	 * 必答,非必答
	 */
	private String isanswer;
	
	
	/**
	 * 私有人员或者公共
	 */
	private String belongto;
	
	/**
	 * 创建人
	 */
	private String createby;
	
	/**
	 * 创建人姓名
	 */
	private String createbyname;
	
	/**
	 * 题目备注
	 */
	private String ps;
	
	/**
	 * 问答题答案最小数字
	 */
	private String minnumber;
	
	
	/**
	 * 问答题答案最大数字
	 */
	private String maxnumber;
	
	

	public KhzsQuestionAll() {
	}

	public KhzsQuestionAll(String category, String id, String options, String question, String type) {
		this.category = category;
		this.id = id;
		//this.options = options;
		this.question = question;
		this.type = type;
	}
	
	
	@Column(name = "ISANSWER", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getIsanswer() {
		return isanswer;
	}

	public void setIsanswer(String isanswer) {
		this.isanswer = isanswer;
	}

	@Column(name = "BELONGTO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getBelongto() {
		return belongto;
	}

	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}

	/**
	 * [标签，分类_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "CATEGORY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCategory() {
		return category;
	}

	/**
	 * [标签，分类_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * [题号_Get方法]
	 * 
	 * @author:
	 */
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	/**
	 * [题号_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * [选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）_Get方法]
	 * 
	 * @author:
	 */
	/*@Column(name = "OPTIONS", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptions() {
		return options;
	}
*/
	/**
	 * [选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	/*public void setOptions(String options) {
		this.options = options;
	}*/

	/**
	 * [题目_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "QUESTION", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getQuestion() {
		return question;
	}

	/**
	 * [题目_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * [题型（单选，多选，判断，简答）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getType() {
		return type;
	}

	/**
	 * [题型（单选，多选，判断，简答）_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * [选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "OPTIONA", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptiona() {
		return optiona;
	}

	/**
	 * [选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
	
	

	@Column(name = "OPTIONB", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptionb() {
		return optionb;
	}

	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}

	@Column(name = "OPTIONC", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptionc() {
		return optionc;
	}

	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}

	@Column(name = "OPTIOND", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptiond() {
		return optiond;
	}

	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}

	@Column(name = "OPTIONE", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptione() {
		return optione;
	}

	public void setOptione(String optione) {
		this.optione = optione;
	}

	@Column(name = "OPTIONF", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptionf() {
		return optionf;
	}

	public void setOptionf(String optionf) {
		this.optionf = optionf;
	}

	@Column(name = "OPTIONG", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptiong() {
		return optiong;
	}

	public void setOptiong(String optiong) {
		this.optiong = optiong;
	}

	@Column(name = "OPTIONH", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getOptionh() {
		return optionh;
	}

	public void setOptionh(String optionh) {
		this.optionh = optionh;
	}

	@Column(name = "REMARK", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	@Column(name = "OPTIONI", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptioni() {
		return optioni;
	}

	public void setOptioni(String optioni) {
		this.optioni = optioni;
	}

	@Column(name = "OPTIONJ", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionj() {
		return optionj;
	}

	public void setOptionj(String optionj) {
		this.optionj = optionj;
	}

	@Column(name = "OPTIONk", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionk() {
		return optionk;
	}

	public void setOptionk(String optionk) {
		this.optionk = optionk;
	}

	@Column(name = "OPTIONL", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionl() {
		return optionl;
	}

	public void setOptionl(String optionl) {
		this.optionl = optionl;
	}

	@Column(name = "OPTIONM", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionm() {
		return optionm;
	}

	public void setOptionm(String optionm) {
		this.optionm = optionm;
	}

	@Column(name = "OPTIONN", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionn() {
		return optionn;
	}

	public void setOptionn(String optionn) {
		this.optionn = optionn;
	}

	@Column(name = "OPTIONO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptiono() {
		return optiono;
	}

	public void setOptiono(String optiono) {
		this.optiono = optiono;
	}

	@Column(name = "OPTIONP", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptionp() {
		return optionp;
	}

	public void setOptionp(String optionp) {
		this.optionp = optionp;
	}

	@Column(name = "CREATEBY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	@Column(name = "CREATEBYNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreatebyname() {
		return createbyname;
	}

	public void setCreatebyname(String createbyname) {
		this.createbyname = createbyname;
	}
	
	@Column(name = "PS", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	@Column(name = "MINNUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getMinnumber() {
		return minnumber;
	}

	public void setMinnumber(String minnumber) {
		this.minnumber = minnumber;
	}

	@Column(name = "MAXNUMBER", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getMaxnumber() {
		return maxnumber;
	}

	public void setMaxnumber(String maxnumber) {
		this.maxnumber = maxnumber;
	}

	@Override
	public String toString() {
		return "KhzsQuestion [category=" + category + ", id=" + id +  ", question=" + question
				+ ", type=" + type + "]";
	}

}
