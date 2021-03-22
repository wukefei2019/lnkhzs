<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet"
	type="text/css" />
<html>
<head>
<meta charset="UTF-8">
	<title>投诉数据总体情况报表</title> 
	<script type="text/javascript" language="javascript"
		src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>
	<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_Detail_Complain_Loc_Dw.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
			</dg:lefttoolbar>
			<dg:condition>
				<input type="hidden" name="createby" value="${userid}" />
				<div class="btn_box overflow">
					<div
						class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
					<div
						class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
				</div>
			</dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false'
					data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>					
						<tr>
							<th data-field="STATIS_DATE" data-sortable='true'>统计日期</th>
							<th data-field="WRKFM_SHOW_SWFTNO" data-sortable='true'>工单流水号</th>
							<th data-field="ACPT_CHNL_NM" data-sortable='true'>受理渠道</th>
							<th data-field="ACPT_TIME" data-sortable='true'>受理时间</th>
							<th data-field="ACPT_NUM" data-sortable='true'>受理号码</th>
							<th data-field="AREA_NAME" data-sortable='true'>用户归属地</th>
							<th data-field="SRV_REQST_TYPE_FULL_NM" data-sortable='true'>服务请求类别</th>
							<th data-field="BIZ_CNTT" data-sortable='true'>业务内容</th>
							<th data-field="DSPS_OPIN_CNTT" data-sortable='true'>处理意见</th>
							<th data-field="ARC_TIME" data-sortable='true'>结单日期</th>
							<th data-field="RSLV_EXTENT" data-sortable='true'>解决程度</th>
							<th data-field="LAST_CUST_SATIS" data-sortable='true'>工单满意度</th>
							<th data-field="RESP_DEPT" data-sortable='true'>责任部门</th>
							<th data-field="RESP_RSN_NM" data-sortable='true'>责任原因</th>
							<th data-field="GROUP_NAME0" data-sortable='true'>处理工作组</th>
							<th data-field="GROUP_NAME1" data-sortable='true'>首处理工作组</th>
							<th data-field="GROUP_NAME2" data-sortable='true'>协办工作组</th>
							<th data-field="GROUP_NAME3" data-sortable='true'>反馈工作组</th>
							<th data-field="GROUP_NAME4" data-sortable='true'>结束工作组</th>
							<th data-field="DIFCLT_CMPLNTS_TYPE_CD" data-sortable='true'>疑难客户类型</th>
							<th data-field="ORG_DEPARTMENT" data-sortable='true'>归属部门</th>
							<th data-field="ORG_BUSI" data-sortable='true'>所属业务</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>

		
</body>