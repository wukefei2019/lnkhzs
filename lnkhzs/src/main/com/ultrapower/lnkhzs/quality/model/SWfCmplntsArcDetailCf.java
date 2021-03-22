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
@Table(name = "NGCS_WF_CMPLNTS_ARC_DETAIL_CF")
public class SWfCmplntsArcDetailCf implements ICommonModel {
    
    /**
     *受理渠道_字段 
     */
    private String acptChnlNm;

    /**
     *受理号码_字段 
     */
    private String acptNum;

    /**
     *受理时间_字段 
     */
    private String acptTime;

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
     *疑难客户类型_字段 
     */
    private String difcltCmplntsTypeCd;

    /**
     *处理意见_字段 
     */
    private String dspsOpinCntt;

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
     *责任部门_字段 
     */
    private String respDept;

    /**
     *责任原因_字段 
     */
    private String respRsnNm;

    /**
     *解决程度_字段 
     */
    private String rslvExtent;

    /**
     *服务请求类别 _字段 
     */
    private String srvReqstTypeFullNm;

    /**
     *统计日期_字段 
     */
    private String statisDate;
    
    /**
     *工单流水号_字段 
     */
    private String wrkfmShowSwftno;




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
