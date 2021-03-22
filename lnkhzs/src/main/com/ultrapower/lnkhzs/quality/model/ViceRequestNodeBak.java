package com.ultrapower.lnkhzs.quality.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

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
@Table(name = "ZL_SERVICE_REQUEST_NODE_BAK")
public class ViceRequestNodeBak implements ICommonModel {
    /**
     *服务请求全路径_字段 
     */
    private String a12;

    /**
     *批次号_字段 
     */
    private Integer batchno;

    /**
     *业务归属部门_字段 
     */
    private String businessAttribution;

    /**
     *业务类型_字段 
     */
    private String businessType;

    /**
     *编码1级_字段 
     */
    private String code01;

    /**
     *编码2级_字段 
     */
    private String code02;

    /**
     *编码3级_字段 
     */
    private String code03;

    /**
     *编码4级_字段 
     */
    private String code04;

    /**
     *编码5级_字段 
     */
    private String code05;

    /**
     *编码6级_字段 
     */
    private String code06;

    /**
     *编码7级_字段 
     */
    private String code07;

    /**
     *流程状态_字段 
     */
    private String flowstatus;

    /**
     *id_字段 
     */
    private String id;

    /**
     *是否删除_字段 
     */
    private String isDelete;

    /**
     *业务分类1级_字段 
     */
    private String name01;

    /**
     *业务分类2级_字段 
     */
    private String name02;

    /**
     *业务分类3级_字段 
     */
    private String name03;

    /**
     *业务分类4级_字段 
     */
    private String name04;

    /**
     *问题分类5级_字段 
     */
    private String name05;

    /**
     *问题分类6级_字段 
     */
    private String name06;

    /**
     *问题分类7级_字段 
     */
    private String name07;

    /**
     *操作人_字段 
     */
    private String operator;

    /**
     *pid_字段 
     */
    private String pid;

    /**
     *备注_字段 
     */
    private String remarks;

    /**
     *sheet页名_字段 
     */
    private String sheetName;

    /**
     *状态_字段 
     */
    private String status;

    /**
     *分类调整说明_字段 
     */
    private String typeAdjustExplanation;

    /**
     * [服务请求全路径_Get方法]
     * @author:
     */
    @Column(name = "A12", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getA12() {
        return a12;
    }
    /**
    * [服务请求全路径_Set方法]
    * @author:
    * @param args
    */
    public void setA12(String a12) {
        this.a12 = a12;
    }


    /**
     * [批次号_Get方法]
     * @author:
     */
    @Column(name = "BATCHNO", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getBatchno() {
        return batchno;
    }
    /**
    * [批次号_Set方法]
    * @author:
    * @param args
    */
    public void setBatchno(Integer batchno) {
        this.batchno = batchno;
    }


    /**
     * [业务归属部门_Get方法]
     * @author:
     */
    @Column(name = "BUSINESS_ATTRIBUTION", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getBusinessAttribution() {
        return businessAttribution;
    }
    /**
    * [业务归属部门_Set方法]
    * @author:
    * @param args
    */
    public void setBusinessAttribution(String businessAttribution) {
        this.businessAttribution = businessAttribution;
    }


    /**
     * [业务类型_Get方法]
     * @author:
     */
    @Column(name = "BUSINESS_TYPE", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getBusinessType() {
        return businessType;
    }
    /**
    * [业务类型_Set方法]
    * @author:
    * @param args
    */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }


    /**
     * [编码1级_Get方法]
     * @author:
     */
    @Column(name = "CODE01", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode01() {
        return code01;
    }
    /**
    * [编码1级_Set方法]
    * @author:
    * @param args
    */
    public void setCode01(String code01) {
        this.code01 = code01;
    }


    /**
     * [编码2级_Get方法]
     * @author:
     */
    @Column(name = "CODE02", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode02() {
        return code02;
    }
    /**
    * [编码2级_Set方法]
    * @author:
    * @param args
    */
    public void setCode02(String code02) {
        this.code02 = code02;
    }


    /**
     * [编码3级_Get方法]
     * @author:
     */
    @Column(name = "CODE03", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode03() {
        return code03;
    }
    /**
    * [编码3级_Set方法]
    * @author:
    * @param args
    */
    public void setCode03(String code03) {
        this.code03 = code03;
    }


    /**
     * [编码4级_Get方法]
     * @author:
     */
    @Column(name = "CODE04", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode04() {
        return code04;
    }
    /**
    * [编码4级_Set方法]
    * @author:
    * @param args
    */
    public void setCode04(String code04) {
        this.code04 = code04;
    }


    /**
     * [编码5级_Get方法]
     * @author:
     */
    @Column(name = "CODE05", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode05() {
        return code05;
    }
    /**
    * [编码5级_Set方法]
    * @author:
    * @param args
    */
    public void setCode05(String code05) {
        this.code05 = code05;
    }


    /**
     * [编码6级_Get方法]
     * @author:
     */
    @Column(name = "CODE06", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode06() {
        return code06;
    }
    /**
    * [编码6级_Set方法]
    * @author:
    * @param args
    */
    public void setCode06(String code06) {
        this.code06 = code06;
    }


    /**
     * [编码7级_Get方法]
     * @author:
     */
    @Column(name = "CODE07", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCode07() {
        return code07;
    }
    /**
    * [编码7级_Set方法]
    * @author:
    * @param args
    */
    public void setCode07(String code07) {
        this.code07 = code07;
    }


    /**
     * [流程状态_Get方法]
     * @author:
     */
    @Column(name = "FLOWSTATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getFlowstatus() {
        return flowstatus;
    }
    /**
    * [流程状态_Set方法]
    * @author:
    * @param args
    */
    public void setFlowstatus(String flowstatus) {
        this.flowstatus = flowstatus;
    }


    /**
     * [id_Get方法]
     * @author:
     */
    @Id
    @Column(name = "ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
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
     * [是否删除_Get方法]
     * @author:
     */
    @Column(name = "IS_DELETE", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getIsDelete() {
        return isDelete;
    }
    /**
    * [是否删除_Set方法]
    * @author:
    * @param args
    */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }


    /**
     * [业务分类1级_Get方法]
     * @author:
     */
    @Column(name = "NAME01", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName01() {
        return name01;
    }
    /**
    * [业务分类1级_Set方法]
    * @author:
    * @param args
    */
    public void setName01(String name01) {
        this.name01 = name01;
    }


    /**
     * [业务分类2级_Get方法]
     * @author:
     */
    @Column(name = "NAME02", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName02() {
        return name02;
    }
    /**
    * [业务分类2级_Set方法]
    * @author:
    * @param args
    */
    public void setName02(String name02) {
        this.name02 = name02;
    }


    /**
     * [业务分类3级_Get方法]
     * @author:
     */
    @Column(name = "NAME03", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName03() {
        return name03;
    }
    /**
    * [业务分类3级_Set方法]
    * @author:
    * @param args
    */
    public void setName03(String name03) {
        this.name03 = name03;
    }


    /**
     * [业务分类4级_Get方法]
     * @author:
     */
    @Column(name = "NAME04", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName04() {
        return name04;
    }
    /**
    * [业务分类4级_Set方法]
    * @author:
    * @param args
    */
    public void setName04(String name04) {
        this.name04 = name04;
    }


    /**
     * [问题分类5级_Get方法]
     * @author:
     */
    @Column(name = "NAME05", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName05() {
        return name05;
    }
    /**
    * [问题分类5级_Set方法]
    * @author:
    * @param args
    */
    public void setName05(String name05) {
        this.name05 = name05;
    }


    /**
     * [问题分类6级_Get方法]
     * @author:
     */
    @Column(name = "NAME06", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName06() {
        return name06;
    }
    /**
    * [问题分类6级_Set方法]
    * @author:
    * @param args
    */
    public void setName06(String name06) {
        this.name06 = name06;
    }


    /**
     * [问题分类7级_Get方法]
     * @author:
     */
    @Column(name = "NAME07", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getName07() {
        return name07;
    }
    /**
    * [问题分类7级_Set方法]
    * @author:
    * @param args
    */
    public void setName07(String name07) {
        this.name07 = name07;
    }


    /**
     * [操作人_Get方法]
     * @author:
     */
    @Column(name = "OPERATOR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getOperator() {
        return operator;
    }
    /**
    * [操作人_Set方法]
    * @author:
    * @param args
    */
    public void setOperator(String operator) {
        this.operator = operator;
    }


    /**
     * [pid_Get方法]
     * @author:
     */
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
     * [备注_Get方法]
     * @author:
     */
    @Column(name = "REMARKS", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getRemarks() {
        return remarks;
    }
    /**
    * [备注_Set方法]
    * @author:
    * @param args
    */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    /**
     * [sheet页名_Get方法]
     * @author:
     */
    @Column(name = "SHEET_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getSheetName() {
        return sheetName;
    }
    /**
    * [sheet页名_Set方法]
    * @author:
    * @param args
    */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }


    /**
     * [状态_Get方法]
     * @author:
     */
    @Column(name = "STATUS", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getStatus() {
        return status;
    }
    /**
    * [状态_Set方法]
    * @author:
    * @param args
    */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * [分类调整说明_Get方法]
     * @author:
     */
    @Column(name = "TYPE_ADJUST_EXPLANATION", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getTypeAdjustExplanation() {
        return typeAdjustExplanation;
    }
    /**
    * [分类调整说明_Set方法]
    * @author:
    * @param args
    */
    public void setTypeAdjustExplanation(String typeAdjustExplanation) {
        this.typeAdjustExplanation = typeAdjustExplanation;
    }


}
