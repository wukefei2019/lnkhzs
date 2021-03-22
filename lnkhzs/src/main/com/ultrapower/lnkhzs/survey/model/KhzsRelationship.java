package com.ultrapower.lnkhzs.survey.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [问卷题库关系表_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_KHZS_RELATIONSHIP")
public class KhzsRelationship implements ICommonModel {
    /**
     *问卷时间_字段 
     */
    private String creattime;

    /**
     *id_字段 
     */
    private String id;

    /**
     *问卷名_字段 
     */
    private String name;

    /**
     *排序号_字段 
     */
    private Double orders;

    /**
     *题号_字段 
     */
    private String questionid;

    /**
     *问卷号_字段 
     */
    private String questionnaireid;

    /**
     * [问卷时间_Get方法]
     * @author:
     */
    @Column(name = "CREATTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCreattime() {
        return creattime;
    }
    /**
    * [问卷时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }


    /**
     * [id_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }
    /**
    * [id_Set方法]
    * @author:
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * [问卷名_Get方法]
     * @author:
     */
    @Column(name = "NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName() {
        return name;
    }
    /**
    * [问卷名_Set方法]
    * @author:
    * @param args
    */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * [排序号_Get方法]
     * @author:
     */
    @Column(name = "ORDERS", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Double getOrders() {
        return orders;
    }
    /**
    * [排序号_Set方法]
    * @author:
    * @param args
    */
    public void setOrders(Double orders) {
        this.orders = orders;
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
	@Override
	public String toString() {
		return "KhzsRelationship [creattime=" + creattime + ", id=" + id + ", name=" + name + ", orders=" + orders
				+ ", questionid=" + questionid + ", questionnaireid=" + questionnaireid + "]";
	}
    


}
