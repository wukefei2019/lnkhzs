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
 * <p>Description: [协查工单结单归档量明细_实体]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "NGCS_WF_CMPLNTS_ARC_DETAIL_XC")
public class SWfCmplntsArcDetailXc implements ICommonModel {
    /**
     *服务请求类别_字段 
     */
    private String a01;

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
     *服务请求1级节点类型_字段 
     */
    private String b02;

    /**
     *服务请求2级节点类型_字段 
     */
    private String b03;

    /**
     *服务请求3级节点类型_字段 
     */
    private String b04;

    /**
     *服务请求4级节点类型_字段 
     */
    private String b05;

    /**
     *服务请求5级节点类型_字段 
     */
    private String b06;

    /**
     *服务请求6级节点类型_字段 
     */
    private String b07;

    /**
     *服务请求7级节点类型_字段 
     */
    private String b08;

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
     *用户星级_字段 
     */
    private String custStargrdNm;

    /**
     *短信意见_字段 
     */
    private String dspsOpinCntt;

    /**
     *整体时限时间_字段 
     */
    private String exprTime;

    /**
     *回复方式_字段 
     */
    private String fdbkMode;

    /**
     *真实处理工作组_字段 
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
     *回复满意度_字段 
     */
    private String lastCustSatis;

    /**
     *No1_字段 
     */
    private String no1;

    /**
     *No2_字段 
     */
    private String no2;

    /**
     *No3_字段 
     */
    private String no3;

    /**
     *No4_字段 
     */
    private Integer no4;

    /**
     *No5_字段 
     */
    private Integer no5;

    /**
     *No6_字段 
     */
    private Integer no6;

    /**
     *No7_字段 
     */
    private Integer no7;

    /**
     *No8_字段 
     */
    private Integer no8;

    /**
     *No9_字段 
     */
    private Integer no9;

    /**
     *不满意原因_字段 
     */
    private String nSatisRsnDesc;

    /**
     *目标机构_字段 
     */
    private String orgBrnch;

    /**
     *外部系统_字段 
     */
    private String otherSystem;

    /**
     *外部系统加长版_字段 
     */
    private String otherSystem2;

    /**
     *首处理上环节评价_字段 
     */
    private String preWorkitemEval;

    /**
     *负责部门_字段 
     */
    private String respDept;

    /**
     *负责原因_字段 
     */
    private String respRnsNm;

    /**
     *上报集团_字段 
     */
    private String rptldGroup;

    /**
     *解决程度_字段 
     */
    private String rslvExtent;

    /**
     *真实处理人_字段 
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
     *统计日期_字段 
     */
    private String statisDate;

    /**
     *处理时长_字段 
     */
    private Integer totalTime;

    /**
     *处理时长_含夜间_字段 
     */
    private Integer totalTime2;

    /**
     *total_time_字段 
     */
    private Integer totalTime3;

    /**
     *工单流水号_字段 
     */
    private String wrkfmShowSwftno;

    /**
     * [服务请求类别_Get方法]
     * @author:
     */
    @Column(name = "A01", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
    public String getA01() {
        return a01;
    }
    /**
    * [服务请求类别_Set方法]
    * @author:
    * @param args
    */
    public void setA01(String a01) {
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
     * [服务请求1级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B02", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB02() {
        return b02;
    }
    /**
    * [服务请求1级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB02(String b02) {
        this.b02 = b02;
    }


    /**
     * [服务请求2级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B03", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB03() {
        return b03;
    }
    /**
    * [服务请求2级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB03(String b03) {
        this.b03 = b03;
    }


    /**
     * [服务请求3级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B04", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB04() {
        return b04;
    }
    /**
    * [服务请求3级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB04(String b04) {
        this.b04 = b04;
    }


    /**
     * [服务请求4级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B05", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB05() {
        return b05;
    }
    /**
    * [服务请求4级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB05(String b05) {
        this.b05 = b05;
    }


    /**
     * [服务请求5级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B06", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB06() {
        return b06;
    }
    /**
    * [服务请求5级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB06(String b06) {
        this.b06 = b06;
    }


    /**
     * [服务请求6级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B07", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB07() {
        return b07;
    }
    /**
    * [服务请求6级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB07(String b07) {
        this.b07 = b07;
    }


    /**
     * [服务请求7级节点类型_Get方法]
     * @author:
     */
    @Column(name = "B08", unique=false ,nullable=true, insertable=true, updatable=true ,length=400 )
    public String getB08() {
        return b08;
    }
    /**
    * [服务请求7级节点类型_Set方法]
    * @author:
    * @param args
    */
    public void setB08(String b08) {
        this.b08 = b08;
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
     * [短信意见_Get方法]
     * @author:
     */
    @Column(name = "DSPS_OPIN_CNTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
    public String getDspsOpinCntt() {
        return dspsOpinCntt;
    }
    /**
    * [短信意见_Set方法]
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
     * [回复方式_Get方法]
     * @author:
     */
    @Column(name = "FDBK_MODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getFdbkMode() {
        return fdbkMode;
    }
    /**
    * [回复方式_Set方法]
    * @author:
    * @param args
    */
    public void setFdbkMode(String fdbkMode) {
        this.fdbkMode = fdbkMode;
    }


    /**
     * [真实处理工作组_Get方法]
     * @author:
     */
    @Column(name = "GROUP_NAME0", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getGroupName0() {
        return groupName0;
    }
    /**
    * [真实处理工作组_Set方法]
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
     * [回复满意度_Get方法]
     * @author:
     */
    @Column(name = "LAST_CUST_SATIS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getLastCustSatis() {
        return lastCustSatis;
    }
    /**
    * [回复满意度_Set方法]
    * @author:
    * @param args
    */
    public void setLastCustSatis(String lastCustSatis) {
        this.lastCustSatis = lastCustSatis;
    }


    /**
     * [No1_Get方法]
     * @author:
     */
    @Column(name = "NO1", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
    public String getNo1() {
        return no1;
    }
    /**
    * [No1_Set方法]
    * @author:
    * @param args
    */
    public void setNo1(String no1) {
        this.no1 = no1;
    }


    /**
     * [No2_Get方法]
     * @author:
     */
    @Column(name = "NO2", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
    public String getNo2() {
        return no2;
    }
    /**
    * [No2_Set方法]
    * @author:
    * @param args
    */
    public void setNo2(String no2) {
        this.no2 = no2;
    }


    /**
     * [No3_Get方法]
     * @author:
     */
    @Column(name = "NO3", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
    public String getNo3() {
        return no3;
    }
    /**
    * [No3_Set方法]
    * @author:
    * @param args
    */
    public void setNo3(String no3) {
        this.no3 = no3;
    }


    /**
     * [No4_Get方法]
     * @author:
     */
    @Column(name = "NO4", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo4() {
        return no4;
    }
    /**
    * [No4_Set方法]
    * @author:
    * @param args
    */
    public void setNo4(Integer no4) {
        this.no4 = no4;
    }


    /**
     * [No5_Get方法]
     * @author:
     */
    @Column(name = "NO5", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo5() {
        return no5;
    }
    /**
    * [No5_Set方法]
    * @author:
    * @param args
    */
    public void setNo5(Integer no5) {
        this.no5 = no5;
    }


    /**
     * [No6_Get方法]
     * @author:
     */
    @Column(name = "NO6", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo6() {
        return no6;
    }
    /**
    * [No6_Set方法]
    * @author:
    * @param args
    */
    public void setNo6(Integer no6) {
        this.no6 = no6;
    }


    /**
     * [No7_Get方法]
     * @author:
     */
    @Column(name = "NO7", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo7() {
        return no7;
    }
    /**
    * [No7_Set方法]
    * @author:
    * @param args
    */
    public void setNo7(Integer no7) {
        this.no7 = no7;
    }


    /**
     * [No8_Get方法]
     * @author:
     */
    @Column(name = "NO8", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo8() {
        return no8;
    }
    /**
    * [No8_Set方法]
    * @author:
    * @param args
    */
    public void setNo8(Integer no8) {
        this.no8 = no8;
    }


    /**
     * [No9_Get方法]
     * @author:
     */
    @Column(name = "NO9", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getNo9() {
        return no9;
    }
    /**
    * [No9_Set方法]
    * @author:
    * @param args
    */
    public void setNo9(Integer no9) {
        this.no9 = no9;
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
     * [外部系统_Get方法]
     * @author:
     */
    @Column(name = "OTHER_SYSTEM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getOtherSystem() {
        return otherSystem;
    }
    /**
    * [外部系统_Set方法]
    * @author:
    * @param args
    */
    public void setOtherSystem(String otherSystem) {
        this.otherSystem = otherSystem;
    }


    /**
     * [外部系统加长版_Get方法]
     * @author:
     */
    @Column(name = "OTHER_SYSTEM2", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
    public String getOtherSystem2() {
        return otherSystem2;
    }
    /**
    * [外部系统加长版_Set方法]
    * @author:
    * @param args
    */
    public void setOtherSystem2(String otherSystem2) {
        this.otherSystem2 = otherSystem2;
    }


    /**
     * [首处理上环节评价_Get方法]
     * @author:
     */
    @Column(name = "PRE_WORKITEM_EVAL", unique=false ,nullable=true, insertable=true, updatable=true ,length=250 )
    public String getPreWorkitemEval() {
        return preWorkitemEval;
    }
    /**
    * [首处理上环节评价_Set方法]
    * @author:
    * @param args
    */
    public void setPreWorkitemEval(String preWorkitemEval) {
        this.preWorkitemEval = preWorkitemEval;
    }


    /**
     * [负责部门_Get方法]
     * @author:
     */
    @Column(name = "RESP_DEPT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRespDept() {
        return respDept;
    }
    /**
    * [负责部门_Set方法]
    * @author:
    * @param args
    */
    public void setRespDept(String respDept) {
        this.respDept = respDept;
    }


    /**
     * [负责原因_Get方法]
     * @author:
     */
    @Column(name = "RESP_RNS_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRespRnsNm() {
        return respRnsNm;
    }
    /**
    * [负责原因_Set方法]
    * @author:
    * @param args
    */
    public void setRespRnsNm(String respRnsNm) {
        this.respRnsNm = respRnsNm;
    }


    /**
     * [上报集团_Get方法]
     * @author:
     */
    @Column(name = "RPTLD_GROUP", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
    public String getRptldGroup() {
        return rptldGroup;
    }
    /**
    * [上报集团_Set方法]
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
     * [真实处理人_Get方法]
     * @author:
     */
    @Column(name = "STAFF_ID0", unique=false ,nullable=true, insertable=true, updatable=true ,length=18 )
    public String getStaffId0() {
        return staffId0;
    }
    /**
    * [真实处理人_Set方法]
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
     * [处理时长_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getTotalTime() {
        return totalTime;
    }
    /**
    * [处理时长_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }


    /**
     * [处理时长_含夜间_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME2", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getTotalTime2() {
        return totalTime2;
    }
    /**
    * [处理时长_含夜间_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime2(Integer totalTime2) {
        this.totalTime2 = totalTime2;
    }


    /**
     * [total_time_Get方法]
     * @author:
     */
    @Column(name = "TOTAL_TIME3", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Integer getTotalTime3() {
        return totalTime3;
    }
    /**
    * [total_time_Set方法]
    * @author:
    * @param args
    */
    public void setTotalTime3(Integer totalTime3) {
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
