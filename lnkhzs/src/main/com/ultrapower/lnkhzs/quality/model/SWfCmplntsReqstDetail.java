package com.ultrapower.lnkhzs.quality.model;

import com.ultrapower.omcs.common.model.ICommonModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;

import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "NGCS_WF_CMPLNTS_REQST_DETAIL")
public class SWfCmplntsReqstDetail implements ICommonModel {
    /**
     *接触时长_字段 
     */
    private Integer a01;

    /**
     *服务请求1级节点类型_字段 
     */
    private String a02;

    /**
     *服务请求2级节点类型_字段 
     */
    private String a03;

    /**
     *服务请求3级节点类型_字段 
     */
    private String a04;

    /**
     *服务请求4级节点类型_字段 
     */
    private String a05;

    /**
     *服务请求5级节点类型_字段 
     */
    private String a06;

    /**
     *服务请求6级节点类型_字段 
     */
    private String a07;

    /**
     *服务请求7级节点类型_字段 
     */
    private String a08;

    /**
     *服务请求8级节点类型_字段 
     */
    private String a09;

    /**
     *服务请求9级节点类型_字段 
     */
    private String a10;

    /**
     *服务请求类型名称_字段 
     */
    private String a11;

    /**
     *服务请求全路径_字段 
     */
    private String a12;

    /**
     *受理内容_字段 
     */
    private Clob a13;

    /**
     *受理渠道_字段 
     */
    private String acptChnlIdName;

    /**
     *受理工号_字段 
     */
    private String acptStaffNum;

    /**
     *受理时间_字段 
     */
    private String acptTime;

    /**
     *受理号码归属地市_字段 
     */
    private String areaName;

    /**
     *被叫号码_字段 
     */
    private String calledNum;

    /**
     *受理号码_字段 
     */
    private String callingNum;

    /**
     *主叫号码_字段 
     */
    private String callingNumZ;

    /**
     *呼叫流水号_字段 
     */
    private String callId;

    /**
     *众包公司名称_字段 
     */
    private String className;

    /**
     *用户星级_字段 
     */
    private String codeNm;

    /**
     *部室名称_字段 
     */
    private String deptName;

    /**
     *是否重复投诉_字段 
     */
    private Integer ifCfts;

    /**
     *是否创建工单_字段 
     */
    private Integer ifLd;

    /**
     *是否升级投诉_字段 
     */
    private Integer ifSjtt;

    /**
     *是否投诉_字段 
     */
    private Integer ifTs;

    /**
     *是否现场解决_字段 
     */
    private Integer ifXcJj;

    /**
     *服务请求流水_字段 
     */
    private Integer srvReqstId;

    /**
     *受理员工_字段 
     */
    private String staffName;

    /**
     *人员类型_字段 
     */
    private String staffTypeKind;

    /**
     *统计日期_字段 
     */
    private String statisDate;

    /**
     *受理部门_字段 
     */
    private String teamName;

    /**
     *满意度结果_字段 
     */
    private String userSatisfy;

    /**
     *工单流水_字段 
     */
    private String wkfmShowSwftno;

    /**
     * [接触时长_Get方法]
     * @author:
     */
    @Column(name = "A01", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getA01() {
        return a01;
    }
    /**
    * [接触时长_Set方法]
    * @author:
    * @param args
    */
    public void setA01(Integer a01) {
        this.a01 = a01;
    }


    /**
     * [服务请求1级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A02", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA02() {
        return a02;
    }
    /**
    * [服务请求1级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA02(String a02) {
        this.a02 = a02;
    }


    /**
     * [服务请求2级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A03", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA03() {
        return a03;
    }
    /**
    * [服务请求2级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA03(String a03) {
        this.a03 = a03;
    }


    /**
     * [服务请求3级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A04", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA04() {
        return a04;
    }
    /**
    * [服务请求3级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA04(String a04) {
        this.a04 = a04;
    }


    /**
     * [服务请求4级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A05", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA05() {
        return a05;
    }
    /**
    * [服务请求4级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA05(String a05) {
        this.a05 = a05;
    }


    /**
     * [服务请求5级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A06", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA06() {
        return a06;
    }
    /**
    * [服务请求5级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA06(String a06) {
        this.a06 = a06;
    }


    /**
     * [服务请求6级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A07", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA07() {
        return a07;
    }
    /**
    * [服务请求6级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA07(String a07) {
        this.a07 = a07;
    }


    /**
     * [服务请求7级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A08", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA08() {
        return a08;
    }
    /**
    * [服务请求7级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA08(String a08) {
        this.a08 = a08;
    }


    /**
     * [服务请求8级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A09", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA09() {
        return a09;
    }
    /**
    * [服务请求8级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA09(String a09) {
        this.a09 = a09;
    }


    /**
     * [服务请求9级节点类型_Get方法]
     * @author:
     */
    @Column(name = "A10", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA10() {
        return a10;
    }
    /**
    * [服务请求9级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setA10(String a10) {
        this.a10 = a10;
    }


    /**
     * [服务请求类型名称_Get方法]
     * @author:
     */
    @Column(name = "A11", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getA11() {
        return a11;
    }
    /**
    * [服务请求类型名称_Set方法]
    * @author:
    * @param args
    */
    public void setA11(String a11) {
        this.a11 = a11;
    }


    /**
     * [服务请求全路径_Get方法]
     * @author:
     */
    @Column(name = "A12", unique=false ,nullable=true, insertable=true, updatable=true ,length=390 )
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
     * [受理内容_Get方法]
     * @author:
     */
    @Column(name = "A13" )
    public Clob getA13() {
        return a13;
    }
    /**
    * [受理内容_Set方法]
    * @author:
    * @param args
    */
    public void setA13(Clob a13) {
        this.a13 = a13;
    }


    /**
     * [受理渠道_Get方法]
     * @author:
     */
    @Column(name = "ACPT_CHNL_ID_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getAcptChnlIdName() {
        return acptChnlIdName;
    }
    /**
    * [受理渠道_Set方法]
    * @author:
    * @param args
    */
    public void setAcptChnlIdName(String acptChnlIdName) {
        this.acptChnlIdName = acptChnlIdName;
    }


    /**
     * [受理工号_Get方法]
     * @author:
     */
    @Column(name = "ACPT_STAFF_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getAcptStaffNum() {
        return acptStaffNum;
    }
    /**
    * [受理工号_Set方法]
    * @author:
    * @param args
    */
    public void setAcptStaffNum(String acptStaffNum) {
        this.acptStaffNum = acptStaffNum;
    }


    /**
     * [受理时间_Get方法]
     * @author:
     */
    @Column(name = "ACPT_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=30 )
    public String getAcptTime() {
        return acptTime;
    }
    /**
    * [受理时间_Set方法]
    * @author:
    * @param args
    */
    public void setAcptTime(String acptTime) {
        this.acptTime = acptTime;
    }


    /**
     * [受理号码归属地市_Get方法]
     * @author:
     */
    @Column(name = "AREA_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getAreaName() {
        return areaName;
    }
    /**
    * [受理号码归属地市_Set方法]
    * @author:
    * @param args
    */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    /**
     * [被叫号码_Get方法]
     * @author:
     */
    @Column(name = "CALLED_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getCalledNum() {
        return calledNum;
    }
    /**
    * [被叫号码_Set方法]
    * @author:
    * @param args
    */
    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum;
    }


    /**
     * [受理号码_Get方法]
     * @author:
     */
    @Column(name = "CALLING_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getCallingNum() {
        return callingNum;
    }
    /**
    * [受理号码_Set方法]
    * @author:
    * @param args
    */
    public void setCallingNum(String callingNum) {
        this.callingNum = callingNum;
    }


    /**
     * [主叫号码_Get方法]
     * @author:
     */
    @Column(name = "CALLING_NUM_Z", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getCallingNumZ() {
        return callingNumZ;
    }
    /**
    * [主叫号码_Set方法]
    * @author:
    * @param args
    */
    public void setCallingNumZ(String callingNumZ) {
        this.callingNumZ = callingNumZ;
    }


    /**
     * [呼叫流水号_Get方法]
     * @author:
     */
    @Column(name = "CALL_ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=40 )
    public String getCallId() {
        return callId;
    }
    /**
    * [呼叫流水号_Set方法]
    * @author:
    * @param args
    */
    public void setCallId(String callId) {
        this.callId = callId;
    }


    /**
     * [众包公司名称_Get方法]
     * @author:
     */
    @Column(name = "CLASS_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=128 )
    public String getClassName() {
        return className;
    }
    /**
    * [众包公司名称_Set方法]
    * @author:
    * @param args
    */
    public void setClassName(String className) {
        this.className = className;
    }


    /**
     * [用户星级_Get方法]
     * @author:
     */
    @Column(name = "CODE_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getCodeNm() {
        return codeNm;
    }
    /**
    * [用户星级_Set方法]
    * @author:
    * @param args
    */
    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }


    /**
     * [部室名称_Get方法]
     * @author:
     */
    @Column(name = "DEPT_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=128 )
    public String getDeptName() {
        return deptName;
    }
    /**
    * [部室名称_Set方法]
    * @author:
    * @param args
    */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    /**
     * [是否重复投诉_Get方法]
     * @author:
     */
    @Column(name = "IF_CFTS", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getIfCfts() {
        return ifCfts;
    }
    /**
    * [是否重复投诉_Set方法]
    * @author:
    * @param args
    */
    public void setIfCfts(Integer ifCfts) {
        this.ifCfts = ifCfts;
    }


    /**
     * [是否创建工单_Get方法]
     * @author:
     */
    @Column(name = "IF_LD", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getIfLd() {
        return ifLd;
    }
    /**
    * [是否创建工单_Set方法]
    * @author:
    * @param args
    */
    public void setIfLd(Integer ifLd) {
        this.ifLd = ifLd;
    }


    /**
     * [是否升级投诉_Get方法]
     * @author:
     */
    @Column(name = "IF_SJTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getIfSjtt() {
        return ifSjtt;
    }
    /**
    * [是否升级投诉_Set方法]
    * @author:
    * @param args
    */
    public void setIfSjtt(Integer ifSjtt) {
        this.ifSjtt = ifSjtt;
    }


    /**
     * [是否投诉_Get方法]
     * @author:
     */
    @Column(name = "IF_TS", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getIfTs() {
        return ifTs;
    }
    /**
    * [是否投诉_Set方法]
    * @author:
    * @param args
    */
    public void setIfTs(Integer ifTs) {
        this.ifTs = ifTs;
    }


    /**
     * [是否现场解决_Get方法]
     * @author:
     */
    @Column(name = "IF_XC_JJ", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getIfXcJj() {
        return ifXcJj;
    }
    /**
    * [是否现场解决_Set方法]
    * @author:
    * @param args
    */
    public void setIfXcJj(Integer ifXcJj) {
        this.ifXcJj = ifXcJj;
    }


    /**
     * [服务请求流水_Get方法]
     * @author:
     */
    @Id
    @Column(name = "SRV_REQST_ID", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getSrvReqstId() {
        return srvReqstId;
    }
    /**
    * [服务请求流水_Set方法]
    * @author:
    * @param args
    */
    public void setSrvReqstId(Integer srvReqstId) {
        this.srvReqstId = srvReqstId;
    }


    /**
     * [受理员工_Get方法]
     * @author:
     */
    @Column(name = "STAFF_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
    public String getStaffName() {
        return staffName;
    }
    /**
    * [受理员工_Set方法]
    * @author:
    * @param args
    */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


    /**
     * [人员类型_Get方法]
     * @author:
     */
    @Column(name = "STAFF_TYPE_KIND", unique=false ,nullable=true, insertable=true, updatable=true ,length=128 )
    public String getStaffTypeKind() {
        return staffTypeKind;
    }
    /**
    * [人员类型_Set方法]
    * @author:
    * @param args
    */
    public void setStaffTypeKind(String staffTypeKind) {
        this.staffTypeKind = staffTypeKind;
    }


    /**
     * [统计日期_Get方法]
     * @author:
     */
    @Column(name = "STATIS_DATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=8 )
    public String getStatisDate() {
        return statisDate;
    }
    /**
    * [统计日期_Set方法]
    * @author:
    * @param args
    */
    public void setStatisDate(String statisDate) {
        this.statisDate = statisDate;
    }


    /**
     * [受理部门_Get方法]
     * @author:
     */
    @Column(name = "TEAM_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
    public String getTeamName() {
        return teamName;
    }
    /**
    * [受理部门_Set方法]
    * @author:
    * @param args
    */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    /**
     * [满意度结果_Get方法]
     * @author:
     */
    @Column(name = "USER_SATISFY", unique=false ,nullable=true, insertable=true, updatable=true ,length=200 )
    public String getUserSatisfy() {
        return userSatisfy;
    }
    /**
    * [满意度结果_Set方法]
    * @author:
    * @param args
    */
    public void setUserSatisfy(String userSatisfy) {
        this.userSatisfy = userSatisfy;
    }


    /**
     * [工单流水_Get方法]
     * @author:
     */
    @Column(name = "WKFM_SHOW_SWFTNO", unique=false ,nullable=true, insertable=true, updatable=true ,length=64 )
    public String getWkfmShowSwftno() {
        return wkfmShowSwftno;
    }
    /**
    * [工单流水_Set方法]
    * @author:
    * @param args
    */
    public void setWkfmShowSwftno(String wkfmShowSwftno) {
        this.wkfmShowSwftno = wkfmShowSwftno;
    }
    
    

    
}
