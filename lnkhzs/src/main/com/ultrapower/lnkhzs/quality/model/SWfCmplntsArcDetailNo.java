package com.ultrapower.lnkhzs.quality.model;

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
 * <p>Description: [非投诉结单归档量明细(10059)_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "NGCS_WF_CMPLNTS_ARC_DETAIL_NO")
public class SWfCmplntsArcDetailNo implements ICommonModel {
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
     *受理渠道_字段 
     */
    private String acptChnlNm;

    /**
     *受理号码_字段 
     */
    private String acptNum;

    /**
     *立单部门_字段 
     */
    private String acptOrgaName;

    /**
     *立单工号_字段 
     */
    private String acptStaffNum;

    /**
     *受理时间_字段 
     */
    private String acptTime;

    /**
     *用户姓名_字段 
     */
    private String acptUserName;

    /**
     *结单日期_字段 
     */
    private String arcTime;

    /**
     *用户归属地_字段 
     */
    private String areaName;

    /**
     *业务内容_字段 
     */
    private String bizCntt;

    /**
     *用户参加的营销活动_字段 
     */
    private String cmpgnNm;

    /**
     *声称升级_字段 
     */
    private String cmplnUpgd;

    /**
     *联系方式_字段 
     */
    private String cntway;

    /**
     *更新时间_字段 
     */
    private String createTime;

    /**
     *用户星级_字段 
     */
    private String custStargrdNm;

    /**
     *疑难客户类型_字段 
     */
    private String difcltCmplntsTypeCd;

    /**
     *处理意见_字段 
     */
    private String dspsOpinCntt;

    /**
     *整体时限时间_字段 
     */
    private String exprTime;

    /**
     *反馈方式_字段 
     */
    private String fdbkMode;

    /**
     *处理工作组_字段 
     */
    private String groupName0;

    /**
     *首处理工作组_字段 
     */
    private String groupName1;

    /**
     *协办工作组_字段 
     */
    private String groupName2;

    /**
     *反馈工作组_字段 
     */
    private String groupName3;

    /**
     *结束工作组_字段 
     */
    private String groupName4;

    /**
     *工单满意度 _字段 
     */
    private String lastCustSatis;

    /**
     *不满意原因_字段 
     */
    private String nSatisRsnDesc;

    /**
     *目标机构_字段 
     */
    private String orgBrnch;

    /**
     *null_字段 
     */
    private String otherSystem;

    /**
     *外部系统_字段 
     */
    private String otherSystem2;

    /**
     *立单环节评价_字段 
     */
    private String preWorkitemEval;

    /**
     *责任部门_字段 
     */
    private String respDept;

    /**
     *责任原因_字段 
     */
    private String respRsnNm;

    /**
     *null_字段 
     */
    private String rptldGroup;

    /**
     *解决程度_字段 
     */
    private String rslvExtent;

    /**
     *工单回访标志_字段 
     */
    private String satisDgrRevstMode;

    /**
     *客户回访满意度_字段 
     */
    private String smsFdbkSatisDgr;

    /**
     *服务请求类别 _字段 
     */
    private String srvReqstTypeFullNm;

    /**
     *处理人_字段 
     */
    private String staffId0;

    /**
     *首处理处理人_字段 
     */
    private String staffId1;

    /**
     *协办处理人_字段 
     */
    private String staffId2;

    /**
     *反馈处理人_字段 
     */
    private String staffId3;

    /**
     *结束处理人_字段 
     */
    private String staffId4;

    /**
     *首次咨询响应时长_字段 
     */
    private Double startScDuration;

    /**
     *统计日期_字段 
     */
    private String statisDate;

    /**
     *null_字段 
     */
    private Double totalTime;

    /**
     *处理时长(含夜间)_字段 
     */
    private Double totalTime2;

    /**
     *处理时长_字段 
     */
    private Double totalTime3;

    /**
     *工单流水号_字段 
     */
    private String wrkfmShowSwftno;

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
     * [受理渠道_Get方法]
     * @author:
     */
    @Column(name = "ACPT_CHNL_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAcptChnlNm() {
        return acptChnlNm;
    }
    /**
    * [受理渠道_Set方法]
    * @author:
    * @param args
    */
    public void setAcptChnlNm(String acptChnlNm) {
        this.acptChnlNm = acptChnlNm;
    }


    /**
     * [受理号码_Get方法]
     * @author:
     */
    @Column(name = "ACPT_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAcptNum() {
        return acptNum;
    }
    /**
    * [受理号码_Set方法]
    * @author:
    * @param args
    */
    public void setAcptNum(String acptNum) {
        this.acptNum = acptNum;
    }


    /**
     * [立单部门_Get方法]
     * @author:
     */
    @Column(name = "ACPT_ORGA_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAcptOrgaName() {
        return acptOrgaName;
    }
    /**
    * [立单部门_Set方法]
    * @author:
    * @param args
    */
    public void setAcptOrgaName(String acptOrgaName) {
        this.acptOrgaName = acptOrgaName;
    }


    /**
     * [立单工号_Get方法]
     * @author:
     */
    @Column(name = "ACPT_STAFF_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAcptStaffNum() {
        return acptStaffNum;
    }
    /**
    * [立单工号_Set方法]
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
    @Column(name = "ACPT_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
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
     * [用户姓名_Get方法]
     * @author:
     */
    @Column(name = "ACPT_USER_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAcptUserName() {
        return acptUserName;
    }
    /**
    * [用户姓名_Set方法]
    * @author:
    * @param args
    */
    public void setAcptUserName(String acptUserName) {
        this.acptUserName = acptUserName;
    }


    /**
     * [结单日期_Get方法]
     * @author:
     */
    @Column(name = "ARC_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getArcTime() {
        return arcTime;
    }
    /**
    * [结单日期_Set方法]
    * @author:
    * @param args
    */
    public void setArcTime(String arcTime) {
        this.arcTime = arcTime;
    }


    /**
     * [用户归属地_Get方法]
     * @author:
     */
    @Column(name = "AREA_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getAreaName() {
        return areaName;
    }
    /**
    * [用户归属地_Set方法]
    * @author:
    * @param args
    */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    /**
     * [业务内容_Get方法]
     * @author:
     */
    @Column(name = "BIZ_CNTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getBizCntt() {
        return bizCntt;
    }
    /**
    * [业务内容_Set方法]
    * @author:
    * @param args
    */
    public void setBizCntt(String bizCntt) {
        this.bizCntt = bizCntt;
    }


    /**
     * [用户参加的营销活动_Get方法]
     * @author:
     */
    @Column(name = "CMPGN_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
    public String getCmpgnNm() {
        return cmpgnNm;
    }
    /**
    * [用户参加的营销活动_Set方法]
    * @author:
    * @param args
    */
    public void setCmpgnNm(String cmpgnNm) {
        this.cmpgnNm = cmpgnNm;
    }


    /**
     * [声称升级_Get方法]
     * @author:
     */
    @Column(name = "CMPLN_UPGD", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCmplnUpgd() {
        return cmplnUpgd;
    }
    /**
    * [声称升级_Set方法]
    * @author:
    * @param args
    */
    public void setCmplnUpgd(String cmplnUpgd) {
        this.cmplnUpgd = cmplnUpgd;
    }


    /**
     * [联系方式_Get方法]
     * @author:
     */
    @Column(name = "CNTWAY", unique=false ,nullable=true, insertable=true, updatable=true ,length=60 )
    public String getCntway() {
        return cntway;
    }
    /**
    * [联系方式_Set方法]
    * @author:
    * @param args
    */
    public void setCntway(String cntway) {
        this.cntway = cntway;
    }


    /**
     * [更新时间_Get方法]
     * @author:
     */
    @Column(name = "CREATE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=50 )
    public String getCreateTime() {
        return createTime;
    }
    /**
    * [更新时间_Set方法]
    * @author:
    * @param args
    */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    /**
     * [用户星级_Get方法]
     * @author:
     */
    @Column(name = "CUST_STARGRD_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getCustStargrdNm() {
        return custStargrdNm;
    }
    /**
    * [用户星级_Set方法]
    * @author:
    * @param args
    */
    public void setCustStargrdNm(String custStargrdNm) {
        this.custStargrdNm = custStargrdNm;
    }


    /**
     * [疑难客户类型_Get方法]
     * @author:
     */
    @Column(name = "DIFCLT_CMPLNTS_TYPE_CD", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getDifcltCmplntsTypeCd() {
        return difcltCmplntsTypeCd;
    }
    /**
    * [疑难客户类型_Set方法]
    * @author:
    * @param args
    */
    public void setDifcltCmplntsTypeCd(String difcltCmplntsTypeCd) {
        this.difcltCmplntsTypeCd = difcltCmplntsTypeCd;
    }


    /**
     * [处理意见_Get方法]
     * @author:
     */
    @Column(name = "DSPS_OPIN_CNTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getDspsOpinCntt() {
        return dspsOpinCntt;
    }
    /**
    * [处理意见_Set方法]
    * @author:
    * @param args
    */
    public void setDspsOpinCntt(String dspsOpinCntt) {
        this.dspsOpinCntt = dspsOpinCntt;
    }


    /**
     * [整体时限时间_Get方法]
     * @author:
     */
    @Column(name = "EXPR_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getExprTime() {
        return exprTime;
    }
    /**
    * [整体时限时间_Set方法]
    * @author:
    * @param args
    */
    public void setExprTime(String exprTime) {
        this.exprTime = exprTime;
    }


    /**
     * [反馈方式_Get方法]
     * @author:
     */
    @Column(name = "FDBK_MODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getFdbkMode() {
        return fdbkMode;
    }
    /**
    * [反馈方式_Set方法]
    * @author:
    * @param args
    */
    public void setFdbkMode(String fdbkMode) {
        this.fdbkMode = fdbkMode;
    }


    /**
     * [处理工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME0", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getGroupName0() {
        return groupName0;
    }
    /**
    * [处理工作组_Set方法]
    * @author:
    * @param args
    */
    public void setGroupName0(String groupName0) {
        this.groupName0 = groupName0;
    }


    /**
     * [首处理工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME1", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
    public String getGroupName1() {
        return groupName1;
    }
    /**
    * [首处理工作组_Set方法]
    * @author:
    * @param args
    */
    public void setGroupName1(String groupName1) {
        this.groupName1 = groupName1;
    }


    /**
     * [协办工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME2", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
    public String getGroupName2() {
        return groupName2;
    }
    /**
    * [协办工作组_Set方法]
    * @author:
    * @param args
    */
    public void setGroupName2(String groupName2) {
        this.groupName2 = groupName2;
    }


    /**
     * [反馈工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME3", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
    public String getGroupName3() {
        return groupName3;
    }
    /**
    * [反馈工作组_Set方法]
    * @author:
    * @param args
    */
    public void setGroupName3(String groupName3) {
        this.groupName3 = groupName3;
    }


    /**
     * [结束工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME4", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
    public String getGroupName4() {
        return groupName4;
    }
    /**
    * [结束工作组_Set方法]
    * @author:
    * @param args
    */
    public void setGroupName4(String groupName4) {
        this.groupName4 = groupName4;
    }


    /**
     * [工单满意度 _Get方法]
     * @author:
     */
    @Column(name = "LAST_CUST_SATIS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getLastCustSatis() {
        return lastCustSatis;
    }
    /**
    * [工单满意度 _Set方法]
    * @author:
    * @param args
    */
    public void setLastCustSatis(String lastCustSatis) {
        this.lastCustSatis = lastCustSatis;
    }


    /**
     * [不满意原因_Get方法]
     * @author:
     */
    @Column(name = "N_SATIS_RSN_DESC", unique=false ,nullable=true, insertable=true, updatable=true ,length=200 )
    public String getnSatisRsnDesc() {
        return nSatisRsnDesc;
    }
    /**
    * [不满意原因_Set方法]
    * @author:
    * @param args
    */
    public void setnSatisRsnDesc(String nSatisRsnDesc) {
        this.nSatisRsnDesc = nSatisRsnDesc;
    }


    /**
     * [目标机构_Get方法]
     * @author:
     */
    @Column(name = "ORG_BRNCH", unique=false ,nullable=true, insertable=true, updatable=true ,length=500 )
    public String getOrgBrnch() {
        return orgBrnch;
    }
    /**
    * [目标机构_Set方法]
    * @author:
    * @param args
    */
    public void setOrgBrnch(String orgBrnch) {
        this.orgBrnch = orgBrnch;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "OTHER_SYSTEM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getOtherSystem() {
        return otherSystem;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setOtherSystem(String otherSystem) {
        this.otherSystem = otherSystem;
    }


    /**
     * [外部系统_Get方法]
     * @author:
     */
    @Column(name = "OTHER_SYSTEM2", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
    public String getOtherSystem2() {
        return otherSystem2;
    }
    /**
    * [外部系统_Set方法]
    * @author:
    * @param args
    */
    public void setOtherSystem2(String otherSystem2) {
        this.otherSystem2 = otherSystem2;
    }


    /**
     * [立单环节评价_Get方法]
     * @author:
     */
    @Column(name = "PRE_WORKITEM_EVAL", unique=false ,nullable=true, insertable=true, updatable=true ,length=250 )
    public String getPreWorkitemEval() {
        return preWorkitemEval;
    }
    /**
    * [立单环节评价_Set方法]
    * @author:
    * @param args
    */
    public void setPreWorkitemEval(String preWorkitemEval) {
        this.preWorkitemEval = preWorkitemEval;
    }


    /**
     * [责任部门_Get方法]
     * @author:
     */
    @Column(name = "RESP_DEPT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRespDept() {
        return respDept;
    }
    /**
    * [责任部门_Set方法]
    * @author:
    * @param args
    */
    public void setRespDept(String respDept) {
        this.respDept = respDept;
    }


    /**
     * [责任原因_Get方法]
     * @author:
     */
    @Column(name = "RESP_RSN_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRespRsnNm() {
        return respRsnNm;
    }
    /**
    * [责任原因_Set方法]
    * @author:
    * @param args
    */
    public void setRespRsnNm(String respRsnNm) {
        this.respRsnNm = respRsnNm;
    }


    /**
     * [null_Get方法]
     * @author:
     */
    @Column(name = "RPTLD_GROUP", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRptldGroup() {
        return rptldGroup;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setRptldGroup(String rptldGroup) {
        this.rptldGroup = rptldGroup;
    }


    /**
     * [解决程度_Get方法]
     * @author:
     */
    @Column(name = "RSLV_EXTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRslvExtent() {
        return rslvExtent;
    }
    /**
    * [解决程度_Set方法]
    * @author:
    * @param args
    */
    public void setRslvExtent(String rslvExtent) {
        this.rslvExtent = rslvExtent;
    }


    /**
     * [工单回访标志_Get方法]
     * @author:
     */
    @Column(name = "SATIS_DGR_REVST_MODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getSatisDgrRevstMode() {
        return satisDgrRevstMode;
    }
    /**
    * [工单回访标志_Set方法]
    * @author:
    * @param args
    */
    public void setSatisDgrRevstMode(String satisDgrRevstMode) {
        this.satisDgrRevstMode = satisDgrRevstMode;
    }


    /**
     * [客户回访满意度_Get方法]
     * @author:
     */
    @Column(name = "SMS_FDBK_SATIS_DGR", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getSmsFdbkSatisDgr() {
        return smsFdbkSatisDgr;
    }
    /**
    * [客户回访满意度_Set方法]
    * @author:
    * @param args
    */
    public void setSmsFdbkSatisDgr(String smsFdbkSatisDgr) {
        this.smsFdbkSatisDgr = smsFdbkSatisDgr;
    }


    /**
     * [服务请求类别 _Get方法]
     * @author:
     */
    @Column(name = "SRV_REQST_TYPE_FULL_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getSrvReqstTypeFullNm() {
        return srvReqstTypeFullNm;
    }
    /**
    * [服务请求类别 _Set方法]
    * @author:
    * @param args
    */
    public void setSrvReqstTypeFullNm(String srvReqstTypeFullNm) {
        this.srvReqstTypeFullNm = srvReqstTypeFullNm;
    }


    /**
     * [处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID0", unique=false ,nullable=true, insertable=true, updatable=true ,length=18 )
    public String getStaffId0() {
        return staffId0;
    }
    /**
    * [处理人_Set方法]
    * @author:
    * @param args
    */
    public void setStaffId0(String staffId0) {
        this.staffId0 = staffId0;
    }


    /**
     * [首处理处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID1", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
    public String getStaffId1() {
        return staffId1;
    }
    /**
    * [首处理处理人_Set方法]
    * @author:
    * @param args
    */
    public void setStaffId1(String staffId1) {
        this.staffId1 = staffId1;
    }


    /**
     * [协办处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID2", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
    public String getStaffId2() {
        return staffId2;
    }
    /**
    * [协办处理人_Set方法]
    * @author:
    * @param args
    */
    public void setStaffId2(String staffId2) {
        this.staffId2 = staffId2;
    }


    /**
     * [反馈处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID3", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
    public String getStaffId3() {
        return staffId3;
    }
    /**
    * [反馈处理人_Set方法]
    * @author:
    * @param args
    */
    public void setStaffId3(String staffId3) {
        this.staffId3 = staffId3;
    }


    /**
     * [结束处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID4", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
    public String getStaffId4() {
        return staffId4;
    }
    /**
    * [结束处理人_Set方法]
    * @author:
    * @param args
    */
    public void setStaffId4(String staffId4) {
        this.staffId4 = staffId4;
    }


    /**
     * [首次咨询响应时长_Get方法]
     * @author:
     */
    @Column(name = "START_SC_DURATION", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Double getStartScDuration() {
        return startScDuration;
    }
    /**
    * [首次咨询响应时长_Set方法]
    * @author:
    * @param args
    */
    public void setStartScDuration(Double startScDuration) {
        this.startScDuration = startScDuration;
    }


    /**
     * [统计日期_Get方法]
     * @author:
     */
    @Column(name = "STATIS_DATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=20 )
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
     * [null_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Double getTotalTime() {
        return totalTime;
    }
    /**
    * [null_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }


    /**
     * [处理时长(含夜间)_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME2", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Double getTotalTime2() {
        return totalTime2;
    }
    /**
    * [处理时长(含夜间)_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime2(Double totalTime2) {
        this.totalTime2 = totalTime2;
    }


    /**
     * [处理时长_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME3", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Double getTotalTime3() {
        return totalTime3;
    }
    /**
    * [处理时长_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime3(Double totalTime3) {
        this.totalTime3 = totalTime3;
    }


    /**
     * [工单流水号_Get方法]
     * @author:
     */
    @Id
    @Column(name = "WRKFM_SHOW_SWFTNO", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getWrkfmShowSwftno() {
        return wrkfmShowSwftno;
    }
    /**
    * [工单流水号_Set方法]
    * @author:
    * @param args
    */
    public void setWrkfmShowSwftno(String wrkfmShowSwftno) {
        this.wrkfmShowSwftno = wrkfmShowSwftno;
    }


}
