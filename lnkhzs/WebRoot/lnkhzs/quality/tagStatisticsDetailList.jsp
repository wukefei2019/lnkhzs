<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/toolbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/ztree.custom.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/zCommon.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/serviceRequestNode.js"></script>

<html>
	<head>
		<meta charset="UTF-8">
		<title>标签统计</title>
        <script type="text/javascript" >
	        function getTablename() {
	        	var tablename = "ZL_SERVICE_REQUEST_NODE";
	        	return tablename;
	        }
	        var city = "${param.city}";
	        
        </script>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 	<dg:ajaxgrid id="table0" sqlname="SQL_TAG_STATISTICS_LIST.query" inmap="${inmap }" autoLoad="false">
           	<dg:lefttoolbar>
<!--                 <button class="btn-link icon iconfont fontsize14 icon-sousuo1 query" onclick="showsearch('table0')">搜索</button>
                <button class="btn-link iconfont fontsize14 icon-roundadd" >新增</button>-->
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button> 
            </dg:lefttoolbar> 
            <dg:condition>
                <div class="btn_box overflow">
<!--                     <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14"onclick="changeSelVal()">查找</div>
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div> -->
		 			<input type='hidden' name='citys'  value='${param.city}'/>
		 			<input type='hidden' name='begintime'  value='${param.time}'/>
		 			<input type='hidden' name='types'  value='${param.a12}'/>
                    <!-- <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div> -->
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
                	 class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
							<th data-field="RPTLD_GROUP">上报集团</th>
                        	<th data-field="ACPT_CHNL_NM">受理渠道</th>
                            <th data-field="WRKFM_SHOW_SWFTNO" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false">工单流水号</th>
                            <th data-field="ACPT_STAFF_NUM" >立单工号</th>
                            <th data-field="ACPT_ORGA_NAME">立单部门</th>
                            <th data-field="ACPT_TIME">受理时间</th>
                           <!--  <th data-field="ACPT_NUM">受理号码</th>
                            <th data-field="ACPT_USER_NAME">用户姓名</th> -->
                            <th data-field="AREA_NAME" >用户归属地</th>
                            <th data-field="SRV_REQST_TYPE_FULL_NM" >服务请求类别</th>
                            <th data-field="CUST_STARGRD_NM" >用户星级</th>
                            <th data-field="BIZ_CNTT">业务内容</th>
                            <th data-field="SMS_FDBK_SATIS_DGR">客户回访满意度</th>
                            <th data-field="DSPS_OPIN_CNTT">短信意见</th>
                            <th data-field="STAFF_ID4">结束处理人</th>
                            <th data-field="GROUP_NAME4">结束工作组</th>
                            <th data-field="FDBK_MODE">反馈方式</th>
                            <th data-field="ARC_TIME">结单日期</th>
                            <th data-field="LAST_CUST_SATIS">工单满意度 </th>
                            <th data-field="RESP_DEPT">责任部门</th>
                            <th data-field="RESP_RNS_NM">责任原因</th>
                            <th data-field="OTHER_SYSTEM">外部系统</th>
                            <th data-field="N_SATIS_RSN_DESC">不满意原因</th>
                            <th data-field="CMPGN_NM">用户参加的营销活动</th>
                            <th data-field="TOTAL_TIME">处理时长</th>
                            <th data-field="CNTWAY">联系方式</th>
                            <th data-field="RSLV_EXTENT">解决程度</th>
                            <th data-field="STAFF_ID0">处理人</th>
                            <th data-field="STAFF_ID1">首处理处理人</th>
                            <th data-field="STAFF_ID2">协办处理人</th>
                            <th data-field="EXPR_TIME">整体时限时间</th>
                            <th data-field="DIFCLT_CMPLNTS_TYPE_CD">疑难客户类型</th>
                            <th data-field="STATIS_DATE">统计日期</th>
                            <th data-field="STAFF_ID3">反馈处理人</th>
                            <th data-field="GROUP_NAME1">首处理工作组</th>
                            <th data-field="SATIS_DGR_REVST_MODE">工单回访标志</th>
                            <th data-field="GROUP_NAME2">协办工作组</th>
                            <th data-field="GROUP_NAME3">反馈工作组</th>
                            <th data-field="CMPLN_UPGD">声称升级</th>
                            <th data-field="GROUP_NAME0">处理工作组</th>
                            <th data-field="ORG_BRNCH">目标机构</th>
                            <th data-field="PRE_WORKITEM_EVAL">立单环节评价</th>
                            <th data-field="OTHER_SYSTEM2">外部系统加长版</th>
                            <th data-field="TOTAL_TIME2">处理时长_含夜间</th>
                            <th data-field="TOTAL_TIME3">处理时长</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
