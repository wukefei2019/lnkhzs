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
	<script type="text/javascript"
		src="${ctx }/lnkhzs/situation/situationCHBNOutList.js"></script>
	<script type="text/javascript" language="javascript"
		src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>
	<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
	<style>
	.bootstrap-table .table.table-no-bordered > thead > tr > th{
    	border-right: 2px solid rgb(221, 221, 221);
	}
	</style>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TS_CHBN.query">
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
					data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						
						<tr>
							<th data-field="CHBN" rowspan="2" data-sortable='true'>客户分类</th>
							<th colspan="7">广义投诉量</th>
							<th colspan="7">在线抱怨量</th>
							<th colspan="12">狭义抱怨量</th>
						</tr>
						<tr>

							<th data-field="BROAD_TOTAL">总量</th>
							<th data-field="BROAD_MARKET">其中：市场类</th>
							<th data-field="BROAD_MARKET_RAT">市场类占比</th>
							<th data-field="BROAD_NET">其中：网络类</th>
							<th data-field="BROAD_NET_RAT">网络类占比</th>
							<th data-field="BROAD_XA">其中：信安类</th>
							<th data-field="BROAD_XA_RAT">信安类占比</th>
							
							<th data-field="ONLINE_TOTAL">总量</th>
							<th data-field="ONLINE_MARKET">其中：市场类</th>
							<th data-field="ONLINE_MARKET_RAT">市场类占比</th>
							<th data-field="ONLINE_NET">其中：网络类</th>
							<th data-field="ONLINE_NET_RAT">网络类占比</th>
							<th data-field="ONLINE_XA">其中：信安类</th>
							<th data-field="ONLINE_XA_RAT">信安类占比</th>

							<th data-field="NARROW_TOTAL">总量</th>
							<th data-field="NARROW_SJ">升级投诉量</th>
							<th data-field="NARROW_SJ_RAT">升级投诉占比</th>
							<th data-field="NARROW_MARKET">其中：市场类</th>
							<th data-field="NARROW_MARKET_RAT">市场类占比</th>
							<th data-field="NARROW_MARKET_SJ">市场类中升级投诉量</th>
							<th data-field="NARROW_NET">其中：网络类</th>
							<th data-field="NARROW_NET_RAT">网络类占比</th>
							<th data-field="NARROW_NET_SJ">网络类中升级投诉量</th>
							<th data-field="NARROW_XA">其中：信安类</th>
							<th data-field="NARROW_XA_RAT">信安类占比</th>
							<th data-field="NARROW_XA_SJ">信安类中升级投诉量</th>

						</tr>
						
						
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>

		<%-- <div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<div style="overflow-x : scroll;">
		<!-- <input type="text" name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar" onclick="WdatePicker({dateFmt:'yyyyMMdd',onpicked:changeDateVal});" value="" style="padding-bottom: 1px;">
		 --><input type="text" class="textInput inputCala" id="calendar"  onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:getTotalDetail})" placeholder="请输入查询日期" style="padding-bottom: 1px;float: right;" /> 
		<table class="tableMid" ">
			<thead>
				<tr class="tr1">
					<td rowspan="2">客户分类</td>
					
					<td colspan="7">广义投诉量</td>
					
					<td colspan="7">在线抱怨量</td>
					
					<td colspan="12">狭义抱怨量</td>
				</tr>
				<tr class='tr1'>
					
					<td>总量</td>
					<td>其中：市场类</td>
					<td>市场类占比</td>
					<td>其中：网络类</td>
					<td>网络类占比</td>
					<td>其中：信安类</td>
					<td>信安类占比</td>
					
					<td>总量</td>
					<td>其中：市场类</td>
					<td>市场类占比</td>
					<td>其中：网络类</td>
					<td>网络类占比</td>
					<td>其中：信安类</td>
					<td>信安类占比</td>
					
					<td>总量</td>
					<td>升级投诉量</td>
					<td>升级投诉量占比</td>
					<td>其中：市场类</td>
					<td>市场类占比</td>
					<td>市场类中升级投诉量</td>
					<td>其中：网络类</td>
					<td>网络类占比</td>
					<td>网络类中升级投诉量</td>
					<td>其中：信安类</td>
					<td>信安类占比</td>
					<td>信安类中升级投诉量</td>
					
				</tr>
			</thead>
			<tbody id="ttable">
				<tr class='trOther' style='background:rgb(253, 253, 253)'>
					<td colspan="35">加载中，请稍等</td>
				</tr>
				<tr class='trOther' style='background:rgb(253, 253, 253)'>
					<td>地市</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
				</tr>
				<tr class='trOther' style='background:rgb(253, 253, 253)'>
					<td>地市</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					
				</tr>
			</tbody>
		</table>
		</div>
	</div> --%>
</body>