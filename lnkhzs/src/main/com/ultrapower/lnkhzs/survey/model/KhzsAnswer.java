package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [答卷_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_ANSWER")
public class KhzsAnswer implements ICommonModel {
    /**
     *答案_字段 
     */
    private String answer;

    /**
     *回答日期_字段 
     */
    private String answertime;

    /**
     *ID_字段 
     */
    private String id;

    /**
     *题号_字段 
     */
    private String questionid;

    /**
     *问卷号_字段 
     */
    private String questionnaireid;

    /**
     *userid_字段 
     */
    private String userid;
    
    private String relationid; 
    
    private String remark;
    
    


    @Column(name = "REMARK")
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "RELATIONID", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getRelationid() {
		return relationid;
	}
	public void setRelationid(String relationid) {
		this.relationid = relationid;
	}
	/**
     * [答案_Get方法]
     * @author:
     */
    @Column(name = "ANSWER", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getAnswer() {
        return answer;
    }
    /**
    * [答案_Set方法]
    * @author:
    * @param args
    */
    public void setAnswer(String answer) {
        this.answer = answer;
    }


    /**
     * [回答日期_Get方法]
     * @author:
     */
    @Column(name = "ANSWERTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAnswertime() {
        return answertime;
    }
    /**
    * [回答日期_Set方法]
    * @author:
    * @param args
    */
    public void setAnswertime(String answertime) {
        this.answertime = answertime;
    }


    /**
     * [ID_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }
    /**
    * [ID_Set方法]
    * @author:
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * [题号_Get方法]
     * @author:
     */
    @Column(name = "QUESTIONID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getQuestionid() {
        return questionid;
    }
    /**
    * [题号_Set方法]
    * @author:
    * @param args
    */
    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }


    /**
     * [问卷号_Get方法]
     * @author:
     */
    @Column(name = "QUESTIONNAIREID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getQuestionnaireid() {
        return questionnaireid;
    }
    /**
    * [问卷号_Set方法]
    * @author:
    * @param args
    */
    public void setQuestionnaireid(String questionnaireid) {
        this.questionnaireid = questionnaireid;
    }


    /**
     * [userid_Get方法]
     * @author:
     */
    @Column(name = "USERID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getUserid() {
        return userid;
    }
    /**
    * [userid_Set方法]
    * @author:
    * @param args
    */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    

}
