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
	<title>投诉数据总体情况报表</title> <script type="text/javascript"
		src="${ctx }/lnkhzs/situation/situationMNPList.js"></script>
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
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TS_MNP.query">
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
				<!-- <input name="timeweek" id="timeweek" /> -->
			</dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-page-size='20'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<!-- <th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th> -->
							<th data-field="AREA_NAME" rowspan="3" data-sortable='true'>地市</th>
							<!-- <th data-field="AREA_NAME" rowspan="3" data-sortable='true'>省/XX市/XX区县/XX网格</th> -->
							<!-- <th data-field="ACPT_TIME" rowspan="3" data-sortable='true'>日期</th> -->
							
							<th colspan="9">广义投诉量</th>
							<th colspan="9">在线抱怨量</th>
							<th colspan="18">狭义投诉量</th>
						</tr>
						<tr>
							<th rowspan="2" data-field="BROAD_TOTAL" data-sortable='true'>携号转网投诉总量</th>
							<th rowspan="2" data-field="BROAD_MARKET" data-sortable='true'>其中：市场类携号转网投诉量</th>
							<th rowspan="2" data-field="BROAD_NET" data-sortable='true'>其中：网络类携号转网投诉总量</th>
							<th colspan="2">携出投诉量</th>
							<th rowspan="2" data-field="BROAD_OUT_RAT" data-sortable='true'>携出占比</th>
							<th colspan="2">携入投诉量</th>
							<th rowspan="2" data-field="BROAD_IN_RAT" data-sortable='true'>携入占比</th>
							
							<th rowspan="2" data-field="ONLINE_TOTAL" data-sortable='true'>携号转网投诉总量</th>
							<th rowspan="2" data-field="ONLINE_MARKET" data-sortable='true'>其中：市场类携号转网投诉量</th>
							<th rowspan="2" data-field="ONLINE_NET" data-sortable='true'>其中：网络类携号转网投诉总量</th>
							<th colspan="2">携出投诉量</th>
							<th rowspan="2" data-field="ONLINE_OUT_RAT" data-sortable='true'>携出占比</th>
							<th colspan="2">携入投诉量</th>
							<th rowspan="2" data-field="ONLINE_IN_RAT" data-sortable='true'>携入占比</th>

							<th rowspan="2" data-field="NARROW_TOTAL" data-sortable='true'>携号转网投诉总量</th>
							<th rowspan="2" data-field="NARROW_MARKET" data-sortable='true'>其中：市场类携号转网投诉量</th>
							<th rowspan="2" data-field="NARROW_NET" data-sortable='true'>其中：网络类携号转网投诉总量</th>
							<th rowspan="2" data-field="NARROW_SJ" data-sortable='true'>携号转网升级投诉总量</th>
							<th rowspan="2" data-field="NARROW_MARKET_SJ" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="NARROW_NET_SJ" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出投诉量</th>
							<th rowspan="2" data-field="NARROW_OUT_RAT" data-sortable='true'>携出占比</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="NARROW_OUT_SJ_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入投诉量</th>
							<th rowspan="2" data-field="NARROW_IN_RAT" data-sortable='true'>携入占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="NARROW_IN_SJ_RAT" data-sortable='true'>携入升级投诉占比</th>
							
						</tr>
						<tr>
							<th data-field="BROAD_MARKET_OUT">市场类</th>
							<th data-field="BROAD_NET_OUT">网络类</th>
							<th data-field="BROAD_MARKET_IN">市场类</th>
							<th data-field="BROAD_NET_IN">网络类</th>
							
							<th data-field="ONLINE_MARKET_OUT">市场类</th>
							<th data-field="ONLINE_NET_OUT">网络类</th>
							<th data-field="ONLINE_MARKET_IN">市场类</th>
							<th data-field="ONLINE_NET_IN">网络类</th>
							
							<th data-field="NARROW_MARKET_OUT">市场类</th>
							<th data-field="NARROW_NET_OUT">网络类</th>
							<th data-field="NARROW_MARKET_OUT_SJ">市场类</th>
							<th data-field="NARROW_NET_OUT_SJ">网络类</th>
							<th data-field="NARROW_MARKET_IN">市场类</th>
							<th data-field="NARROW_NET_IN">网络类</th>
							<th data-field="NARROW_MARKET_IN_SJ">市场类</th>
							<th data-field="NARROW_NET_IN_SJ">网络类</th>
							
							
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>