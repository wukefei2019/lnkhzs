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
        <script type="text/javascript" src="${ctx}/lnkhzs/situation/situationFGList.js"></script>
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
<body >
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_FG.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<label for="inputCala" class="radioY "
					style="display: inline;margin-bottom: 0px;"><span>周</span></label>
				<label for="inputCala" class="radioM selected"
					style="display: inline;margin-bottom: 0px;"><span>月</span></label>
				<input type="text" name="inputCala" autocomplete="off"
					class="textInput inputCala" id="calendar"
					onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:getTotalDetail});"
					value="" style="padding-bottom: 1px;"> <!-- <input type="text" class="textInput inputCala" id="calendar"  onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:getTotalDetail})" placeholder="请输入查询日期" style="padding-bottom: 1px;" />  -->

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
				<!--  <input name="threeMonth" id="threeMonth"/>  -->
				<!-- <input name="timeweek" id="timeweek" /> -->
			</dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-page-size='20'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false' >
					<thead>
						<tr class="tr1">
							<th data-field="AREA_NAME" rowspan="3"
								style="vertical-align: middle;" data-sortable='true'>地市</th>
							<!-- <th data-field="ACPT_TIME" rowspan="3" style="vertical-align: middle;" data-sortable='true'>日期</th> -->

							<th colspan="5">5G广义投诉情况</th>

							<th colspan="5">5G在线抱怨情况</th>

							<th colspan="7">5G狭义投诉情况</th>

						</tr>
						<tr class='tr1'>

							<th rowspan="2" data-field="BROAD_TOTAL" data-sortable='true'>5G投诉总量</th>
							<!-- <th data-field="BROAD_MILLION_CUSTOMER" >百万客户投诉比</th> -->
							<th rowspan="2" data-sortable='true' data-field="BROAD_MARKET">其中：市场类</th>
							<th rowspan="2" data-sortable='true'
								data-field="BROAD_MARKET_RAT">占比</th>
							<th rowspan="2" data-sortable='true' data-field="BROAD_NET">其中：网络类</th>
							<th rowspan="2" data-sortable='true' data-field="BROAD_NET_RAT">占比</th>


							<th rowspan="2" data-sortable='true' data-field="ONLINE_TOTAL">抱怨总量</th>
							<th rowspan="2" data-sortable='true' data-field="ONLINE_MARKET">其中：市场类</th>
							<th rowspan="2" data-sortable='true'
								data-field="ONLINE_MARKET_RAT">占比</th>
							<th rowspan="2" data-sortable='true' data-field="ONLINE_NET">其中：网络类</th>
							<th rowspan="2" data-sortable='true' data-field="ONLINE_NET_RAT">占比</th>


							<th rowspan="2" data-sortable='true' data-field="NARROW_TOTAL">5G狭义投诉量</th>
							<!-- <th  data-field="NARROW_MILLION_CUSTOMER">百万客户投诉比</th> -->
							<th rowspan="2" data-sortable='true' data-field="NARROW_MARKET">其中：市场类</th>
							<th rowspan="2" data-sortable='true'
								data-field="NARROW_MARKET_RAT">占比</th>
							<th rowspan="2" data-sortable='true' data-field="NARROW_NET">其中：网络类</th>
							<th rowspan="2" data-sortable='true' data-field="NARROW_NET_RAT">占比</th>
							<th colspan="2">其中：升级投诉量</th>


						</tr>
						<tr class='tr1'>

							<th data-field="NARROW_MARKET_SJ">市场类</th>
							<th data-field="NARROW_NET_SJ">网络类</th>
						</tr>

					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
</body>