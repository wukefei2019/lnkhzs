package com.ultrapower.lnkhzs.track.model;

import com.ultrapower.omcs.base.model.IOmcsModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2018年1月4日
 * <p>Title      : 辽宁移动质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_实体]</p>
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "NGCS_WF_CMPLNTS_RESEND_DEAL")
public class NgcsWfCmplntsResendDeal implements IOmcsModel{
    /**
     *null_字段 
     */
    private String id;

    /**
     *当前处理人节点所在阶段_字段 
     */
    private String step;

    /**
     *当前处理人节点名_字段 
     */
    private String activity;

    /**
     *当前处理人_字段 
     */
    private String executor;

    /**
     *当前处理人的处理意见_字段 
     */
    private String opinion;

    /**
     *当前处理人所在部门_字段 
     */
    private String depart;

    /**
     *当前处理人动作：0：审批通过；1：退回_字段 
     */
    private String operate;

    /**
     *当前处理人的处理时间_字段 
     */
    private String time;

    /**
     *当前处理人填写的表单内容_字段 
     */
    private String form;

    /**
     *报文_字段 
     */
    private String reporttext;

    /**
     *公务流程id标识，即起草公务成功后返回值_字段 
     */
    private String uids;

    /**
     * [null_Get方法]
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=false, insertable=true, updatable=true ,length=50 )
    public String getId() {
        return id;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * [当前处理人节点所在阶段_Get方法]
     */
    @Column(name = "STEP", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getStep() {
        return step;
    }

    /**
    * [当前处理人节点所在阶段_Set方法]
    * @param args
    */
    public void setStep(String step) {
        this.step = step;
    }

    /**
     * [当前处理人节点名_Get方法]
     */
    @Column(name = "ACTIVITY", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getActivity() {
        return activity;
    }

    /**
    * [当前处理人节点名_Set方法]
    * @param args
    */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * [当前处理人_Get方法]
     */
    @Column(name = "EXECUTOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getExecutor() {
        return executor;
    }

    /**
    * [当前处理人_Set方法]
    * @param args
    */
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    /**
     * [当前处理人的处理意见_Get方法]
     */
    @Column(name = "OPINION", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getOpinion() {
        return opinion;
    }

    /**
    * [当前处理人的处理意见_Set方法]
    * @param args
    */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * [当前处理人所在部门_Get方法]
     */
    @Column(name = "DEPART", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getDepart() {
        return depart;
    }

    /**
    * [当前处理人所在部门_Set方法]
    * @param args
    */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * [当前处理人动作：0：审批通过；1：退回_Get方法]
     */
    @Column(name = "OPERATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getOperate() {
        return operate;
    }

    /**
    * [当前处理人动作：0：审批通过；1：退回_Set方法]
    * @param args
    */
    public void setOperate(String operate) {
        this.operate = operate;
    }

    /**
     * [当前处理人的处理时间_Get方法]
     */
    @Column(name = "TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getTime() {
        return time;
    }

    /**
    * [当前处理人的处理时间_Set方法]
    * @param args
    */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * [当前处理人填写的表单内容_Get方法]
     */
    @Column(name = "FORM", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getForm() {
        return form;
    }

    /**
    * [当前处理人填写的表单内容_Set方法]
    * @param args
    */
    public void setForm(String form) {
        this.form = form;
    }

    /**
     * [报文_Get方法]
     */
    @Column(name = "REPORTTEXT", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getReporttext() {
        return reporttext;
    }

    /**
    * [报文_Set方法]
    * @param args
    */
    public void setReporttext(String reporttext) {
        this.reporttext = reporttext;
    }

    /**
     * [公务流程id标识，即起草公务成功后返回值_Get方法]
     */
    @Column(name = "UIDS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getUids() {
        return uids;
    }

    /**
    * [公务流程id标识，即起草公务成功后返回值_Set方法]
    * @param args
    */
    public void setUids(String uids) {
        this.uids = uids;
    }

}
