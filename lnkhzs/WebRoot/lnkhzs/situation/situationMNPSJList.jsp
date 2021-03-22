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
		src="${ctx }/lnkhzs/situation/situationMNPSJList.js"></script>
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
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TS_MNPSJ.query">
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
							
							<th colspan="9">携号转网升级投诉总量</th>
							<th colspan="9">96180</th>
							<th colspan="9">工信部渠道</th>
							<th colspan="9">10080渠道</th>
							<th colspan="9">12300</th>
							<th colspan="9">其他</th>
						</tr>
						<tr>
							<th rowspan="2" data-field="SJTOTAL_TOTAL" data-sortable='true'>携号转网升级投诉总量</th>
							<th rowspan="2" data-field="SJTOTAL_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="SJTOTAL_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="SJTOTAL_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="SJTOTAL_INT_RAT" data-sortable='true'>携入升级投诉占比</th>
							
							<th rowspan="2" data-field="NINE_TOTAL" data-sortable='true'>96180携号转网升级投诉总量</th>
							<th rowspan="2" data-field="NINE_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="NINE_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="NINE_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="NINE_INT_RAT" data-sortable='true'>携入升级投诉占比</th>
							
							<th rowspan="2" data-field="MIIT_TOTAL" data-sortable='true'>工信部携号转网升级投诉总量</th>
							<th rowspan="2" data-field="MIIT_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="MIIT_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="MIIT_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="MIIT_INT_RAT" data-sortable='true'>携入升级投诉占比</th>
							
							<th rowspan="2" data-field="ONEZ_TOTAL" data-sortable='true'>10080携号转网升级投诉总量</th>
							<th rowspan="2" data-field="ONEZ_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="ONEZ_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="ONEZ_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="ONEZ_INT_RAT" data-sortable='true'>携入升级投诉占比</th>
							
							<th rowspan="2" data-field="ONET_TOTAL" data-sortable='true'>12300携号转网升级投诉总量</th>
							<th rowspan="2" data-field="ONET_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="ONET_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="ONET_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="ONET_INT_RAT" data-sortable='true'>携入升级投诉占比</th>
							
							<th rowspan="2" data-field="OTHERS_TOTAL" data-sortable='true'>其他转网升级投诉总量</th>
							<th rowspan="2" data-field="OTHERS_MARKET_TOTAL" data-sortable='true'>其中：市场类携号转网升级投诉量</th>
							<th rowspan="2" data-field="OTHERS_NET_TOTAL" data-sortable='true'>其中：网络类携号转网升级投诉量</th>
							<th colspan="2">携出升级投诉量</th>
							<th rowspan="2" data-field="OTHERS_OUT_RAT" data-sortable='true'>携出升级投诉占比</th>
							<th colspan="2">携入升级投诉量</th>
							<th rowspan="2" data-field="OTHERS_INT_RAT" data-sortable='true'>携入升级投诉占比</th>

						</tr>
						<tr>
							<th data-field="SJTOTAL_MARKET_OUT">市场类</th>
							<th data-field="SJTOTAL_NET_OUT">网络类</th>
							<th data-field="SJTOTAL_MARKET_IN">市场类</th>
							<th data-field="SJTOTAL_NET_IN">网络类</th>
							
							<th data-field="NINE_MARKET_OUT">市场类</th>
							<th data-field="NINE_NET_OUT">网络类</th>
							<th data-field="NINE_MARKET_IN">市场类</th>
							<th data-field="NINE_NET_IN">网络类</th>
							
							<th data-field="MIIT_MARKET_OUT">市场类</th>
							<th data-field="MIIT_NET_OUT">网络类</th>
							<th data-field="MIIT_MARKET_IN">市场类</th>
							<th data-field="MIIT_NET_IN">网络类</th>
							
							<th data-field="ONEZ_MARKET_OUT">市场类</th>
							<th data-field="ONEZ_NET_OUT">网络类</th>
							<th data-field="ONEZ_MARKET_IN">市场类</th>
							<th data-field="ONEZ_NET_IN">网络类</th>
							
							<th data-field="ONET_MARKET_OUT">市场类</th>
							<th data-field="ONET_NET_OUT">网络类</th>
							<th data-field="ONET_MARKET_IN">市场类</th>
							<th data-field="ONET_NET_IN">网络类</th>
							
							<th data-field="OTHERS_MARKET_OUT">市场类</th>
							<th data-field="OTHERS_NET_OUT">网络类</th>
							<th data-field="OTHERS_MARKET_IN">市场类</th>
							<th data-field="OTHERS_NET_IN">网络类</th>

						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>