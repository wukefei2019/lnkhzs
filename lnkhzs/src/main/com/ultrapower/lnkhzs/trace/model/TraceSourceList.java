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
 * <p>Description: [溯源问题清单_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "ZL_TRACE_SOURCE_LIST")
public class TraceSourceList implements ICommonModel {
    /**
     *验收全量投诉量_字段 
     */
    private String acceptanceComplaints;

    /**
     *验证万投比_字段 
     */
    private String acceptanceComplaintsRate;

    /**
     *验收通过时间_字段 
     */
    private String acceptanceTime;

    /**
     *附件_字段 
     */
    private String attachment;

    /**
     *影响范围_字段 
     */
    private String auxiliaryDepartment;

    /**
     *2019-10-001 月份序号_字段 
     */
    private String code;

    /**
     *完成情况_字段 
     */
    private String completionStatus;

    /**
     *具体问题点_字段 
     */
    private String concreteProblems;

    /**
     *入库时间_字段 
     */
    private String createTime;

    /**
     *处理时限_字段 
     */
    private String dealtime;

    /**
     *当前处理人姓名_字段 
     */
    private String fullname;

    /**
     *ID_字段 
     */
    private String id;

    /**
     *key_字段 
     */
    private String key;

    /**
     *当前处理人_字段 
     */
    private String loginname;

    /**
     *回头看发起时间_字段 
     */
    private String lookBackTime;

    /**
     *市场三级网格ID_字段 
     */
    private String network3Id;

    /**
     *市场三级网格名称_字段 
     */
    private String network3Name;

    /**
     *pid_字段 
     */
    private String pid;

    /**
     *问题小类_字段 
     */
    private String questionSubcategory;

    /**
     *主责部门_字段 
     */
    private String respDept;

    /**
     *编号_字段 
     */
    private String serialno;

    /**
     *处理中，已完成，（未发起状态为空值）_字段 
     */
    private String status;

    /**
     *溯源时间_字段 
     */
    private String traceSourceTime;

    /**
     *溯源分类_字段 
     */
    private String traceSourceType;

    /**
     *触发投诉量_字段 
     */
    private String triggerComplaintsNum;

    /**
     *触发办理量_字段 
     */
    private String triggerHandlingVolume;

    /**
     *触发万投比_字段 
     */
    private String triggerWantouRatio;
    
    /**
     *问题原因_字段 
     */
    private String problem;

    /**
     *整改目标_字段 
     */
    private String rectificationGoal;
    
    /**
     * [验收全量投诉量_Get方法]
     * @author:
     */
    @Column(name = "ACCEPTANCE_COMPLAINTS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAcceptanceComplaints() {
        return acceptanceComplaints;
    }
    /**
    * [验收全量投诉量_Set方法]
    * @author:
    * @param args
    */
    public void setAcceptanceComplaints(String acceptanceComplaints) {
        this.acceptanceComplaints = acceptanceComplaints;
    }


    /**
     * [验证万投比_Get方法]
     * @author:
     */
    @Column(name = "ACCEPTANCE_COMPLAINTS_RATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAcceptanceComplaintsRate() {
        return acceptanceComplaintsRate;
    }
    /**
    * [验证万投比_Set方法]
    * @author:
    * @param args
    */
    public void setAcceptanceComplaintsRate(String acceptanceComplaintsRate) {
        this.acceptanceComplaintsRate = acceptanceComplaintsRate;
    }


    /**
     * [验收通过时间_Get方法]
     * @author:
     */
    @Column(name = "ACCEPTANCE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAcceptanceTime() {
        return acceptanceTime;
    }
    /**
    * [验收通过时间_Set方法]
    * @author:
    * @param args
    */
    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }


    /**
     * [附件_Get方法]
     * @author:
     */
    @Column(name = "ATTACHMENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAttachment() {
        return attachment;
    }
    /**
    * [附件_Set方法]
    * @author:
    * @param args
    */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }


    /**
     * [影响范围_Get方法]
     * @author:
     */
    @Column(name = "AUXILIARY_DEPARTMENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getAuxiliaryDepartment() {
        return auxiliaryDepartment;
    }
    /**
    * [影响范围_Set方法]
    * @author:
    * @param args
    */
    public void setAuxiliaryDepartment(String auxiliaryDepartment) {
        this.auxiliaryDepartment = auxiliaryDepartment;
    }


    /**
     * [2019-10-001 月份序号_Get方法]
     * @author:
     */
    @Column(name = "CODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCode() {
        return code;
    }
    /**
    * [2019-10-001 月份序号_Set方法]
    * @author:
    * @param args
    */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * [完成情况_Get方法]
     * @author:
     */
    @Column(name = "COMPLETION_STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCompletionStatus() {
        return completionStatus;
    }
    /**
    * [完成情况_Set方法]
    * @author:
    * @param args
    */
    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }


    /**
     * [具体问题点_Get方法]
     * @author:
     */
    @Column(name = "CONCRETE_PROBLEMS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getConcreteProblems() {
        return concreteProblems;
    }
    /**
    * [具体问题点_Set方法]
    * @author:
    * @param args
    */
    public void setConcreteProblems(String concreteProblems) {
        this.concreteProblems = concreteProblems;
    }


    /**
     * [入库时间_Get方法]
     * @author:
     */
    @Column(name = "CREATE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getCreateTime() {
        return createTime;
    }
    /**
    * [入库时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    /**
     * [处理时限_Get方法]
     * @author:
     */
    @Column(name = "DEALTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getDealtime() {
        return dealtime;
    }
    /**
    * [处理时限_Set方法]
    * @author:
    * @param args
    */
    public void setDealtime(String dealtime) {
        this.dealtime = dealtime;
    }


    /**
     * [当前处理人姓名_Get方法]
     * @author:
     */
    @Column(name = "FULLNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getFullname() {
        return fullname;
    }
    /**
    * [当前处理人姓名_Set方法]
    * @author:
    * @param args
    */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    /**
     * [ID_Get方法]
     * @author:
     */
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
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
     * [key_Get方法]
     * @author:
     */
    @Column(name = "KEY", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getKey() {
        return key;
    }
    /**
    * [key_Set方法]
    * @author:
    * @param args
    */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * [当前处理人_Get方法]
     * @author:
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getLoginname() {
        return loginname;
    }
    /**
    * [当前处理人_Set方法]
    * @author:
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }


    /**
     * [回头看发起时间_Get方法]
     * @author:
     */
    @Column(name = "LOOK_BACK_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getLookBackTime() {
        return lookBackTime;
    }
    /**
    * [回头看发起时间_Set方法]
    * @author:
    * @param args
    */
    public void setLookBackTime(String lookBackTime) {
        this.lookBackTime = lookBackTime;
    }


    /**
     * [市场三级网格ID_Get方法]
     * @author:
     */
    @Column(name = "NETWORK3_ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getNetwork3Id() {
        return network3Id;
    }
    /**
    * [市场三级网格ID_Set方法]
    * @author:
    * @param args
    */
    public void setNetwork3Id(String network3Id) {
        this.network3Id = network3Id;
    }


    /**
     * [市场三级网格名称_Get方法]
     * @author:
     */
    @Column(name = "NETWORK3_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getNetwork3Name() {
        return network3Name;
    }
    /**
    * [市场三级网格名称_Set方法]
    * @author:
    * @param args
    */
    public void setNetwork3Name(String network3Name) {
        this.network3Name = network3Name;
    }


    /**
     * [pid_Get方法]
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
    * [pid_Set方法]
    * @author:
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }


    /**
     * [问题小类_Get方法]
     * @author:
     */
    @Column(name = "QUESTION_SUBCATEGORY", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getQuestionSubcategory() {
        return questionSubcategory;
    }
    /**
    * [问题小类_Set方法]
    * @author:
    * @param args
    */
    public void setQuestionSubcategory(String questionSubcategory) {
        this.questionSubcategory = questionSubcategory;
    }


    /**
     * [主责部门_Get方法]
     * @author:
     */
    @Column(name = "RESP_DEPT", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getRespDept() {
        return respDept;
    }
    /**
    * [主责部门_Set方法]
    * @author:
    * @param args
    */
    public void setRespDept(String respDept) {
        this.respDept = respDept;
    }


    /**
     * [编号_Get方法]
     * @author:
     */
    @Column(name = "SERIALNO", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getSerialno() {
        return serialno;
    }
    /**
    * [编号_Set方法]
    * @author:
    * @param args
    */
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }


    /**
     * [处理中，已完成，（未发起状态为空值）_Get方法]
     * @author:
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getStatus() {
        return status;
    }
    /**
    * [处理中，已完成，（未发起状态为空值）_Set方法]
    * @author:
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * [溯源时间_Get方法]
     * @author:
     */
    @Column(name = "TRACE_SOURCE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getTraceSourceTime() {
        return traceSourceTime;
    }
    /**
    * [溯源时间_Set方法]
    * @author:
    * @param args
    */
    public void setTraceSourceTime(String traceSourceTime) {
        this.traceSourceTime = traceSourceTime;
    }


    /**
     * [溯源分类_Get方法]
     * @author:
     */
    @Column(name = "TRACE_SOURCE_TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getTraceSourceType() {
        return traceSourceType;
    }
    /**
    * [溯源分类_Set方法]
    * @author:
    * @param args
    */
    public void setTraceSourceType(String traceSourceType) {
        this.traceSourceType = traceSourceType;
    }


    /**
     * [触发投诉量_Get方法]
     * @author:
     */
    @Column(name = "TRIGGER_COMPLAINTS_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getTriggerComplaintsNum() {
        return triggerComplaintsNum;
    }
    /**
    * [触发投诉量_Set方法]
    * @author:
    * @param args
    */
    public void setTriggerComplaintsNum(String triggerComplaintsNum) {
        this.triggerComplaintsNum = triggerComplaintsNum;
    }


    /**
     * [触发办理量_Get方法]
     * @author:
     */
    @Column(name = "TRIGGER_HANDLING_VOLUME", unique=false ,nullable=true, insertable=true, updatable=true ,length=255 )
    public String getTriggerHandlingVolume() {
        return triggerHandlingVolume;
    }
    /**
    * [触发办理量_Set方法]
    * @author:
    * @param args
    */
    public void setTriggerHandlingVolume(String triggerHandlingVolume) {
        this.triggerHandlingVolume = triggerHandlingVolume;
    }


    /**
     * [触发万投比_Get方法]
     * @author:
     */
    @Column(name = "TRIGGER_WANTOU_RATIO", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getTriggerWantouRatio() {
        return triggerWantouRatio;
    }
    /**
    * [触发万投比_Set方法]
    * @author:
    * @param args
    */
    public void setTriggerWantouRatio(String triggerWantouRatio) {
        this.triggerWantouRatio = triggerWantouRatio;
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

}
