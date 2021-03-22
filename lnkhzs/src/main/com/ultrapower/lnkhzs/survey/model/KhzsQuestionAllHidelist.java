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
@Table(name = "ZL_KHZS_QUESTION_ALL_HIDELIST")
public class KhzsQuestionAllHidelist implements ICommonModel {
	private static final long serialVersionUID = -243079311191118188L;

	/**
	 * ID_字段
	 */
	private String id;
	
	/**
	 * 选项ABCDEFGH_字段
	 */
	private String options;
	
	/**
	 * 隐藏题号ID_字段
	 */
	private String hidequestionid;
	
	/**
	 * 当前题ID_字段
	 */
	private String questionid;
	
	/**
	 * 问卷ID_字段
	 */
	private String questionnaireid;
	

	/**
	 * [ID_Get方法]
	 * 
	 * @author:
	 */
	@Id
	@Column(name = "ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getId() {
		return id;
	}

	/**
	 * [ID_Set方法]
	 * 
	 * @author:
	 * @param args
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * [options_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "OPTIONS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	
	/**
	 * [hidequestionid_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "HIDEQUESTIONID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getHidequestionid() {
		return hidequestionid;
	}

	public void setHidequestionid(String hidequestionid) {
		this.hidequestionid = hidequestionid;
	}

	/**
	 * [questionid_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "QUESTIONID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	/**
	 * [questionnaireid_Get方法]
	 * 
	 * @author:
	 */
	@Column(name = "QUESTIONNAIREID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getQuestionnaireid() {
		return questionnaireid;
	}

	public void setQuestionnaireid(String questionnaireid) {
		this.questionnaireid = questionnaireid;
	}
	
	
	


	
}
