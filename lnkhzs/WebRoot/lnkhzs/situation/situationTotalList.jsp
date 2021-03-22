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
        <script type="text/javascript" src="${ctx }/lnkhzs/situation/situationTotalList.js"></script>
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
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TOTAL_SJA.query">
		<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
					<!-- <button class="btn-link icon iconfont fontsize14 icon-download" onclick="exportSelVal()">导出</button> -->
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
				<tr class="tr1">
					<th data-field="RESP_ACPT" rowspan="3" style="vertical-align: middle;" data-sortable='true'>地市</th>
					<!-- <th data-field="ACPT_TIME" rowspan="3" style="vertical-align: middle;" data-sortable='true'>日期</th> -->
					
					<th colspan="11">广义投诉量</th>
					
					<th colspan="11">在线抱怨量</th>
					
					<th colspan="16">狭义投诉量</th>
				</tr>
				<tr class='tr1'>
					
					<th rowspan="2" data-field="VALUEG">总量</th>
					<th colspan="3">其中：市场类</th>
					<th rowspan="2" data-field="VALUEG1">市场类占比</th>
					<th colspan="3">其中：网络类</th>
					<th rowspan="2" data-field="VALUEG2">网络类占比</th>
					<th rowspan="2" data-field="BROAD_XA">其中：信安类</th>
					<th rowspan="2" data-field="VALUEG3">信安类占比</th>
					
					<th rowspan="2" data-field="VALUEZ">总量</th>
					<th colspan="3">其中：市场类</th>
					<th rowspan="2" data-field="VALUEZ1">市场类占比</th>
					<th colspan="3">其中：网络类</th>
					<th rowspan="2" data-field="VALUEZ2">网络类占比</th>
					<th rowspan="2" data-field="ONLINE_XA">其中：信安类</th>
					<th rowspan="2" data-field="VALUEZ3">信安类占比</th>
					
					<th rowspan="2" data-field="VALUEX">总量</th>
					<th rowspan="2" data-field="NARROW_SJ" >升级投诉量</th>
					<th rowspan="2" data-field="VALUEX1">升级投诉占比</th>
					
					<th colspan="3">其中：市场类</th>
					<th rowspan="2" data-field="VALUEX2">市场类占比</th>
					
					<th rowspan="2" data-field="NARROW_MARKET_SJ">市场类中升级投诉量</th>
					
					<th colspan="3">其中：网络类</th>
					<th rowspan="2" data-field="VALUEX3">网络类占比</th>
					
					<th rowspan="2" data-field="NARROW_NET_SJ">网络类中升级投诉量</th>
					
					<th rowspan="2" data-field="NARROW_XA">其中：信安类</th>
					<th rowspan="2" data-field="VALUEX4">信安类占比</th>
					<th rowspan="2" data-field="NARROW_XA_SJ">信安类中升级投诉量</th>
					
				</tr>
				<tr class='tr1'>
					
					<th data-field="BROAD_MARKET_PERSONAL">个人市场</th>
					<th data-field="BROAD_MARKET_FAMILY">家庭市场</th>
					<th data-field="BROAD_MARKET_ENTERPRISE">政企市场</th>
					
					<th data-field="BROAD_NET_PERSONAL">个人网络</th>
					<th data-field="BROAD_NET_FAMILY">家庭网络</th>
					<th data-field="BROAD_NET_ENTERPRISE">政企网络</th>
					
					
					<th data-field="ONLINE_MARKET_PERSONAL">个人市场</th>
					<th data-field="ONLINE_MARKET_FAMILY">家庭市场</th>
					<th data-field="ONLINE_MARKET_ENTERPRISE">政企市场</th>
					
					<th data-field="ONLINE_NET_PERSONAL">个人网络</th>
					<th data-field="ONLINE_NET_FAMILY">家庭网络</th>
					<th data-field="ONLINE_NET_ENTERPRISE">政企网络</th>
					
					<th data-field="NARROW_MARKET_PERSONAL">个人市场</th>
					<th data-field="NARROW_MARKET_FAMILY">家庭市场</th>
					<th data-field="NARROW_MARKET_ENTERPRISE">政企市场</th>
					
					<th data-field="NARROW_NET_PERSONAL">个人网络</th>
					<th data-field="NARROW_NET_FAMILY">家庭网络</th>
					<th data-field="NARROW_NET_ENTERPRISE">政企网络</th>
				</tr>
			</thead>
		</table>
		</dg:ajax-table>
		</dg:ajaxgrid>
</body>