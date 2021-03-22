<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
<html>
	<head>
		<meta charset="UTF-8">
		<title>投诉数据总体情况报表</title>
        <script type="text/javascript" src="${ctx}/lnkhzs/situation/situationFGListIn.js"></script>
        <script type="text/javascript" language="javascript" src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>
        <script type="text/javascript" language="javascript"
		src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>
	<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
        	<style>
table {
	border-right: 0.5px solid rgb(221, 221, 221);
	border-top: 0.5px solid rgb(221, 221, 221);
	border-right: 0.5px solid rgb(221, 221, 221);
	min-width: 1500px
}

table td {
	border-left: 0.5px solid rgb(221, 221, 221);
	border-bottom: 0.5px solid rgb(221, 221, 221);
	border-right: 0.5px solid rgb(221, 221, 221);
}

table tbody td {
	height: 30px
}

.bootstrap-table .table.table-no-bordered > thead > tr > th{
    border-right: 2px solid rgb(221, 221, 221);
}
</style>
 </head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_FG_IN.query">
		<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
			<label for="inputCala" class="radioY " style="display: inline;margin-bottom: 0px;"><span>周</span></label>
			<label for="inputCala" class="radioM selected" style="display: inline;margin-bottom: 0px;" ><span>月</span></label>
			<input type="text" name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:getTotalDetail});" value="" style="padding-bottom: 1px;">
				<!-- <input type="text" class="textInput inputCala" id="calendar"  onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:getTotalDetail})" placeholder="请输入查询日期" style="padding-bottom: 1px;" />  -->
		
			<label id="showSelectDate"></label>
			</dg:lefttoolbar>
			<dg:condition>
				<input type="hidden" name="createby" value="${userid}" />
				<div class="btn_box overflow">
					<div
						class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
					<div
						class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
				</div>
				<input name="time" id="time" /> 
				 <input name="threeMonth" id="threeMonth"/> 
				<!-- <input name="timeweek" id="timeweek" /> -->
			</dg:condition>
		<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-page-size='20'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
			<thead>
				<tr class="tr1">
					<th data-field="AREA_NAME" rowspan="2" style="vertical-align: middle;" data-sortable='true'>地市</th>
					<!-- <th data-field="ACPT_TIME" rowspan="3" style="vertical-align: middle;" data-sortable='true'>日期</th> -->
					
					<th colspan="5">总体情况</th>
					<th colspan="5">答疑工单</th>
					<th colspan="11">体验需求工单一</th>
					<th colspan="5">体验需求工单二</th>
					<th colspan="5">体验感知协查工单一</th>
				</tr>
				<tr class='tr1'>
					
					<th data-field="TOTAL_TOTAL" >总量</th>
					<th data-field="TOTAL_MARKET" >其中：市场类</th>
					<th data-field="TOTAL_MARKET_RAT" >占比</th>
					<th data-field="TOTAL_NET" >其中：网络类</th>
					<th data-field="TOTAL_NET_RAT" >占比</th>
					
					<th data-field="ANSWER_TOTAL" >总量</th>
					<th data-field="ANSWER_MARKET" >其中：市场类</th>
					<th data-field="ANSWER_MARKET_RAT" >占比</th>
					<th data-field="ANSWER_NET" >其中：网络类</th>
					<th data-field="ANSWER_NET_RAT" >占比</th>
					
					<th data-field="NEED_FIRST_TOTAL" >总量</th>
					<th data-field="NEED_FIRST_MARKET" >其中：市场类</th>
					<th data-field="NEED_FIRST_MARKET_RAT" >占比</th>
					<th data-field="NEED_FIRST_NET" >其中：网络类</th>
					<th data-field="NEED_FIRST_NET_RAT" >占比</th>
					<th data-field="NEED_FIRST_TRE" >体验需求工单三数量</th>
					<th data-field="NEED_FIRST_TRE_RAT" >体验需求工单三占总量比</th>
					<th data-field="NEED_FIRST_NET_TRE" >其中：网络类</th>
					<th data-field="NEED_FIRST_NET_TRE_RAT" >占比</th>
					<th data-field="NEED_FIRST_MARKET_TRE" >其中：市场类</th>
					<th data-field="NEED_FIRST_MARKET_TRE_RAT" >占比</th>
					
					
					<th data-field="NEED_SECOND_TOTAL" >总量</th>
					<th data-field="NEED_SECOND_MARKET" >其中：市场类</th>
					<th data-field="NEED_SECOND_MARKET_RAT" >占比</th>
					<th data-field="NEED_SECOND_NET" >其中：网络类</th>
					<th data-field="NEED_SECOND_NET_RAT" >占比</th>
					
					<th data-field="ASSIST_FIRST_TOTAL" >总量</th>
					<th data-field="ASSIST_FIRST_MARKET" >其中：市场类</th>
					<th data-field="ASSIST_FIRST_MARKET_RAT" >占比</th>
					<th data-field="ASSIST_FIRST_NET" >其中：网络类</th>
					<th data-field="ASSIST_FIRST_NET_RAT" >占比</th>
					

					
				</tr>
				
			</thead>
		</table>
		</dg:ajax-table>
		</dg:ajaxgrid>
</body>