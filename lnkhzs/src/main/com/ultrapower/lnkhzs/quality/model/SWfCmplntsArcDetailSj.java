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
@Table(name = "NGCS_WF_CMPLNTS_ARC_DETAIL_SJ")
public class SWfCmplntsArcDetailSj implements ICommonModel {
    /**
     *受理渠道_字段 
     */
    private String acptChnlNm;

    /**
     *立单工号 _字段 
     */
    private String acptStaffNum;

    /**
     *立单部门_字段 
     */
    private String acptOrgaName;

  
    /**
     *受理时间_字段 
     */
    private String acptTime;
    
    /**
     *受理号码_字段 
     */
    private String acptNum;


    /**
     *用户姓名_字段 
     */
    private String acptUserName;

    /**
     *用户归属地_字段 
     */
    private String areaName;

    /**
     *服务请求类型_字段 
     */
    private String  srvReqstTypeFullNm;
     
    /**
     *用户星级_字段 
     */
    private String custStargrdNm;

    /**
     *业务内容_字段 
     */
    private String bizCntt;
 
    /**
     *处理意见 _字段 
     */
    private String  dspsOpinCntt;
 
    /**
     *结束处理人_字段 
     */
    private String staffId4;

    /**
     *结束工作组_字段 
     */
    private String groupName4;

    /**
     *反馈方式 _字段 
     */
    private String  fdbkMode;
    
    /**
     *结单日期 _字段 
     */
    private String  arcTime;
    
    /**
     *工单满意度 _字段 
     */
    private String  lastCustSatis;
    
    /**
     *责任部门  _字段 
     */
    private String   respDept;
    
    /**
     *责任原因   _字段 
     */
    private String   respRsnNm;
    
    /**
     *用户参加的营销活动    _字段 
     */
    private String  cmpgnNm;
    
    
    /**
     *联系方式     _字段 
     */
    private String   cntway;
    
    
    /**
     *解决程度  _字段 
     */
    private String   rslvExtent;
    
    /**
     *处理人   _字段 
     */
    private String   staffId0;
    
    /**
     *首处理处理人    _字段 
     */
    private String   staffId1;
    /**
     *协办处理人    _字段 
     */
    private String   staffId2;
    
    /**
     *整体时限时间    _字段 
     */
    private String  exprTime;
    
    /**
     *疑难客户类型  _字段 
     */
    private String  difcltCmplntsTypeCd;
    
    
    /**
     *统计日期   _字段 
     */
    private String  statisDate;
    
    /**
     *反馈处理人    _字段 
     */
    private String  staffId3;
    
    /**
     *首处理工作组    _字段 
     */
    private String  groupName1;
    
    /**
     *协办工作组     _字段 
     */
    private String  groupName2;
    
    
    /**
     *工单流水号      _字段 
     */
    private String  wrkfmShowSwftno;
    
    /**
     *反馈工作组     _字段 
     */
    private String  groupName3;
    
    /**
     *声称升级    _字段 
     */
    private String  cmplnUpgd;
    
    /**
     *处理工作组     _字段 
     */
    private String  groupName0;
    
    /**
     *目标机构     _字段 
     */
    private String  orgBrnch;
    
    /**
     *立单环节评价      _字段 
     */
    private String  preWorkitemEval;
    
    /**
     *外部系统       _字段 
     */
    private String  otherSystem2;
    
    /**
     *处理时长(含夜间)_字段 
     */
    private String  totalTime2;
    
    /**
     *处理时长_字段 
     */
    private String  totalTime3;
    
    /**
     *前期10086投诉次数 _字段 
     */
    private String  no1;
    
    /**
     *前期涉及10086渠道工单流水 _字段 
     */
    private String  no2;
    
    /**
     *前期10086渠道处理人员  _字段 
     */
    private String  no3;
    
    /**
     *是否省内结单  _字段  
     */
    private String  no5;
    
    /**
     *入库时间   _字段 
     */
    private String  createTime;

    
    @Column(name = "ACPT_CHNL_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptChnlNm() {
		return acptChnlNm;
	}

	public void setAcptChnlNm(String acptChnlNm) {
		this.acptChnlNm = acptChnlNm;
	}

	@Column(name = "ACPT_STAFF_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptStaffNum() {
		return acptStaffNum;
	}

	public void setAcptStaffNum(String acptStaffNum) {
		this.acptStaffNum = acptStaffNum;
	}

	@Column(name = "ACPT_ORGA_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptOrgaName() {
		return acptOrgaName;
	}

	public void setAcptOrgaName(String acptOrgaName) {
		this.acptOrgaName = acptOrgaName;
	}

	@Column(name = "ACPT_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptTime() {
		return acptTime;
	}

	public void setAcptTime(String acptTime) {
		this.acptTime = acptTime;
	}

	@Column(name = "ACPT_NUM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptNum() {
		return acptNum;
	}

	public void setAcptNum(String acptNum) {
		this.acptNum = acptNum;
	}

	@Column(name = "ACPT_USER_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAcptUserName() {
		return acptUserName;
	}

	public void setAcptUserName(String acptUserName) {
		this.acptUserName = acptUserName;
	}

	@Column(name = "AREA_NAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "SRV_REQST_TYPE_FULL_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=1000 )
	public String getSrvReqstTypeFullNm() {
		return srvReqstTypeFullNm;
	}

	public void setSrvReqstTypeFullNm(String srvReqstTypeFullNm) {
		this.srvReqstTypeFullNm = srvReqstTypeFullNm;
	}

	@Column(name = "CUST_STARGRD_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getCustStargrdNm() {
		return custStargrdNm;
	}

	public void setCustStargrdNm(String custStargrdNm) {
		this.custStargrdNm = custStargrdNm;
	}

	@Column(name = "BIZ_CNTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getBizCntt() {
		return bizCntt;
	}

	public void setBizCntt(String bizCntt) {
		this.bizCntt = bizCntt;
	}

	@Column(name = "DSPS_OPIN_CNTT", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getDspsOpinCntt() {
		return dspsOpinCntt;
	}

	public void setDspsOpinCntt(String dspsOpinCntt) {
		this.dspsOpinCntt = dspsOpinCntt;
	}

	@Column(name = "STAFF_ID4", unique=false ,nullable=true, insertable=true, updatable=true ,length=1400 )
	public String getStaffId4() {
		return staffId4;
	}

	public void setStaffId4(String staffId4) {
		this.staffId4 = staffId4;
	}

	@Column(name = "GROUP_NAME4", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
	public String getGroupName4() {
		return groupName4;
	}

	public void setGroupName4(String groupName4) {
		this.groupName4 = groupName4;
	}

	@Column(name = "FDBK_MODE", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getFdbkMode() {
		return fdbkMode;
	}

	public void setFdbkMode(String fdbkMode) {
		this.fdbkMode = fdbkMode;
	}

	@Column(name = "ARC_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getArcTime() {
		return arcTime;
	}

	public void setArcTime(String arcTime) {
		this.arcTime = arcTime;
	}

	
    @Column(name = "LAST_CUST_SATIS", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getLastCustSatis() {
		return lastCustSatis;
	}

	public void setLastCustSatis(String lastCustSatis) {
		this.lastCustSatis = lastCustSatis;
	}

	@Column(name = "RESP_DEPT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getRespDept() {
		return respDept;
	}

	public void setRespDept(String respDept) {
		this.respDept = respDept;
	}

	@Column(name = "RESP_RSN_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getRespRsnNm() {
		return respRsnNm;
	}

	public void setRespRsnNm(String respRsnNm) {
		this.respRsnNm = respRsnNm;
	}

	@Column(name = "CMPGN_NM", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
	public String getCmpgnNm() {
		return cmpgnNm;
	}

	public void setCmpgnNm(String cmpgnNm) {
		this.cmpgnNm = cmpgnNm;
	}

	@Column(name = "CNTWAY", unique=false ,nullable=true, insertable=true, updatable=true ,length=600 )
	public String getCntway() {
		return cntway;
	}

	public void setCntway(String cntway) {
		this.cntway = cntway;
	}

	@Column(name = "RSLV_EXTENT", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getRslvExtent() {
		return rslvExtent;
	}

	public void setRslvExtent(String rslvExtent) {
		this.rslvExtent = rslvExtent;
	}

	@Column(name = "STAFF_ID0", unique=false ,nullable=true, insertable=true, updatable=true ,length=800 )
	public String getStaffId0() {
		return staffId0;
	}

	public void setStaffId0(String staffId0) {
		this.staffId0 = staffId0;
	}

	@Column(name = "STAFF_ID1", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
	public String getStaffId1() {
		return staffId1;
	}

	public void setStaffId1(String staffId1) {
		this.staffId1 = staffId1;
	}

	@Column(name = "STAFF_ID2", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
	public String getStaffId2() {
		return staffId2;
	}

	public void setStaffId2(String staffId2) {
		this.staffId2 = staffId2;
	}

	@Column(name = "EXPR_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getExprTime() {
		return exprTime;
	}

	public void setExprTime(String exprTime) {
		this.exprTime = exprTime;
	}

	@Column(name = "DIFCLT_CMPLNTS_TYPE_CD", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
	public String getDifcltCmplntsTypeCd() {
		return difcltCmplntsTypeCd;
	}

	public void setDifcltCmplntsTypeCd(String difcltCmplntsTypeCd) {
		this.difcltCmplntsTypeCd = difcltCmplntsTypeCd;
	}

	@Column(name = "STATIS_DATE", unique=false ,nullable=true, insertable=true, updatable=true ,length=200 )
	public String getStatisDate() {
		return statisDate;
	}

	public void setStatisDate(String statisDate) {
		this.statisDate = statisDate;
	}

	@Column(name = "STAFF_ID3", unique=false ,nullable=true, insertable=true, updatable=true ,length=1500 )
	public String getStaffId3() {
		return staffId3;
	}

	public void setStaffId3(String staffId3) {
		this.staffId3 = staffId3;
	}

	@Column(name = "GROUP_NAME1", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
	public String getGroupName1() {
		return groupName1;
	}

	public void setGroupName1(String groupName1) {
		this.groupName1 = groupName1;
	}

	@Column(name = "GROUP_NAME2", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
	public String getGroupName2() {
		return groupName2;
	}

	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}

	@Id
	@Column(name = "WRKFM_SHOW_SWFTNO", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getWrkfmShowSwftno() {
		return wrkfmShowSwftno;
	}

	public void setWrkfmShowSwftno(String wrkfmShowSwftno) {
		this.wrkfmShowSwftno = wrkfmShowSwftno;
	}

	@Column(name = "GROUP_NAME3", unique=false ,nullable=true, insertable=true, updatable=true ,length=1800 )
	public String getGroupName3() {
		return groupName3;
	}

	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
	}

	@Column(name = "CMPLN_UPGD", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
	public String getCmplnUpgd() {
		return cmplnUpgd;
	}

	public void setCmplnUpgd(String cmplnUpgd) {
		this.cmplnUpgd = cmplnUpgd;
	}

	@Column(name = "GROUP_NAME0", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getGroupName0() {
		return groupName0;
	}

	public void setGroupName0(String groupName0) {
		this.groupName0 = groupName0;
	}

	@Column(name = "ORG_BRNCH", unique=false ,nullable=true, insertable=true, updatable=true ,length=500 )
	public String getOrgBrnch() {
		return orgBrnch;
	}

	public void setOrgBrnch(String orgBrnch) {
		this.orgBrnch = orgBrnch;
	}

	@Column(name = "PRE_WORKITEM_EVAL", unique=false ,nullable=true, insertable=true, updatable=true ,length=250 )
	public String getPreWorkitemEval() {
		return preWorkitemEval;
	}

	public void setPreWorkitemEval(String preWorkitemEval) {
		this.preWorkitemEval = preWorkitemEval;
	}

	@Column(name = "OTHER_SYSTEM2", unique=false ,nullable=true, insertable=true, updatable=true ,length=300 )
	public String getOtherSystem2() {
		return otherSystem2;
	}

	public void setOtherSystem2(String otherSystem2) {
		this.otherSystem2 = otherSystem2;
	}

	@Column(name = "TOTAL_TIME2", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getTotalTime2() {
		return totalTime2;
	}

	public void setTotalTime2(String totalTime2) {
		this.totalTime2 = totalTime2;
	}

	@Column(name = "TOTAL_TIME3", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getTotalTime3() {
		return totalTime3;
	}

	public void setTotalTime3(String totalTime3) {
		this.totalTime3 = totalTime3;
	}

	@Column(name = "NO1", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
	public String getNo1() {
		return no1;
	}

	public void setNo1(String no1) {
		this.no1 = no1;
	}

	@Column(name = "NO2", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	@Column(name = "NO3", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getNo3() {
		return no3;
	}

	public void setNo3(String no3) {
		this.no3 = no3;
	}

	@Column(name = "NO5", unique=false ,nullable=true, insertable=true, updatable=true ,length=4000 )
	public String getNo5() {
		return no5;
	}

	public void setNo5(String no5) {
		this.no5 = no5;
	}

	@Column(name = "CREATE_TIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=100 )
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
   
    
    
    
}