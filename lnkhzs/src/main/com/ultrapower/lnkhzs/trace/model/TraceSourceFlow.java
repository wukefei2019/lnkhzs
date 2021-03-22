package com.ultrapower.lnkhzs.trace.model;

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
 * <p>Description: [null_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_TRACE_SOURCE_FLOW")
public class TraceSourceFlow implements ICommonModel {
    /**
     *null_字段 
     */
    private String createtime;

    /**
     *null_字段 
     */
    private String depname;

    /**
     *null_字段 
     */
    private String deptname;

    /**
     *null_字段 
     */
    private String fullname;

    /**
     *null_字段 
     */
    private String laststatus;

    /**
     *环节进展_字段 
     */
    private String linkProgress;

    /**
     *null_字段 
     */
    private String loginname;

    /**
     *null_字段 
     */
    private String nextdepname;

    /**
     *null_字段 
     */
    private String nextfullname;

    /**
     *null_字段 
     */
    private String nextloginname;

    /**
     *未完成详细描述_字段 
     */
    private String notCompletedDescription;

    /**
     *解决进度-未完成具体原因_字段 
     */
    private String notFinishedReason;

    /**
     *null_字段 
     */
    private String opinion;

    /**
     *null_字段 
     */
    private String pid;

    /**
     *问题原因_字段 
     */
    private String problem;

    /**
     *整改目标_字段 
     */
    private String rectificationGoal;

    /**
     *备注_字段 
     */
    private String remark;

    /**
     *落实责任部门_字段 
     */
    private String responsibleDepartment;

    /**
     *落实责任人_字段 
     */
    private String responsiblePerson;

    /**
     *落实责任单位_字段 
     */
    private String responsibleUnit;

    /**
     *null_字段 
     */
    private String sourcepid;

    /**
     *null_字段 
     */
    private String status;

    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "CREATETIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getCreatetime() {
        return createtime;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "DEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getDepname() {
        return depname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setDepname(String depname) {
        this.depname = depname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "DEPTNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getDeptname() {
        return deptname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getFullname() {
        return fullname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "LASTSTATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getLaststatus() {
        return laststatus;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setLaststatus(String laststatus) {
        this.laststatus = laststatus;
    }


    /**
     * [环节进展_Get方法]
     * @author:
     */
    @Column(name = "LINK_PROGRESS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getLinkProgress() {
        return linkProgress;
    }
    /**
    * [环节进展_Set方法]
    * @author:
    * @param args
    */
    public void setLinkProgress(String linkProgress) {
        this.linkProgress = linkProgress;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getLoginname() {
        return loginname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "NEXTDEPNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getNextdepname() {
        return nextdepname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setNextdepname(String nextdepname) {
        this.nextdepname = nextdepname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "NEXTFULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextfullname() {
        return nextfullname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setNextfullname(String nextfullname) {
        this.nextfullname = nextfullname;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "NEXTLOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getNextloginname() {
        return nextloginname;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setNextloginname(String nextloginname) {
        this.nextloginname = nextloginname;
    }


    /**
     * [未完成详细描述_Get方法]
     * @author:
     */
    @Column(name = "NOT_COMPLETED_DESCRIPTION", unique=false ,nullable=true, insertable=true, updatable=true ,length=2000 )
    public String getNotCompletedDescription() {
        return notCompletedDescription;
    }
    /**
    * [未完成详细描述_Set方法]
    * @author:
    * @param args
    */
    public void setNotCompletedDescription(String notCompletedDescription) {
        this.notCompletedDescription = notCompletedDescription;
    }


    /**
     * [解决进度-未完成具体原因_Get方法]
     * @author:
     */
    @Column(name = "NOT_FINISHED_REASON", unique=false ,nullable=true, insertable=true, updatable=true ,length=2000 )
    public String getNotFinishedReason() {
        return notFinishedReason;
    }
    /**
    * [解决进度-未完成具体原因_Set方法]
    * @author:
    * @param args
    */
    public void setNotFinishedReason(String notFinishedReason) {
        this.notFinishedReason = notFinishedReason;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "OPINION", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getOpinion() {
        return opinion;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "PID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
    public String getPid() {
        return pid;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * [问题原因_Get方法]
     * @author:
     */
    @Column(name = "PROBLEM", unique=false ,nullable=true, insertable=true, updatable=true ,length=2000 )
    public String getProblem() {
        return problem;
    }
    /**
    * [问题原因_Set方法]
    * @author:
    * @param args
    */
    public void setProblem(String problem) {
        this.problem = problem;
    }


    /**
     * [整改目标_Get方法]
     * @author:
     */
    @Column(name = "RECTIFICATION_GOAL", unique=false ,nullable=true, insertable=true, updatable=true ,length=2000 )
    public String getRectificationGoal() {
        return rectificationGoal;
    }
    /**
    * [整改目标_Set方法]
    * @author:
    * @param args
    */
    public void setRectificationGoal(String rectificationGoal) {
        this.rectificationGoal = rectificationGoal;
    }


    /**
     * [备注_Get方法]
     * @author:
     */
    @Column(name = "REMARK", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getRemark() {
        return remark;
    }
    /**
    * [备注_Set方法]
    * @author:
    * @param args
    */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * [落实责任部门_Get方法]
     * @author:
     */
    @Column(name = "RESPONSIBLE_DEPARTMENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getResponsibleDepartment() {
        return responsibleDepartment;
    }
    /**
    * [落实责任部门_Set方法]
    * @author:
    * @param args
    */
    public void setResponsibleDepartment(String responsibleDepartment) {
        this.responsibleDepartment = responsibleDepartment;
    }


    /**
     * [落实责任人_Get方法]
     * @author:
     */
    @Column(name = "RESPONSIBLE_PERSON", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getResponsiblePerson() {
        return responsiblePerson;
    }
    /**
    * [落实责任人_Set方法]
    * @author:
    * @param args
    */
    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }


    /**
     * [落实责任单位_Get方法]
     * @author:
     */
    @Column(name = "RESPONSIBLE_UNIT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getResponsibleUnit() {
        return responsibleUnit;
    }
    /**
    * [落实责任单位_Set方法]
    * @author:
    * @param args
    */
    public void setResponsibleUnit(String responsibleUnit) {
        this.responsibleUnit = responsibleUnit;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "SOURCEPID", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getSourcepid() {
        return sourcepid;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setSourcepid(String sourcepid) {
        this.sourcepid = sourcepid;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getStatus() {
        return status;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }


}
