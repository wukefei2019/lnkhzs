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
		src="${ctx }/lnkhzs/situation/situationSJList.js"></script>
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
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TS_SJA.query">
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
							<!-- <th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th> -->
							<th data-field="AREA_NAME" rowspan="3" data-sortable='true'>地市</th>
							<!-- <th data-field="AREA_NAME" rowspan="3" data-sortable='true'>省/XX市/XX区县/XX网格</th> -->
							<!-- <th data-field="ACPT_TIME" rowspan="3" data-sortable='true'>日期</th> -->
							
							<th colspan="11">升级投诉总量</th>
							<th colspan="11">96180</th>
							<th colspan="11">工信部渠道</th>
							<th colspan="11">10080渠道</th>
							<th colspan="11">民心网渠道</th>
							<th colspan="11">客户来访</th>
							<th colspan="11">315平台</th>
							<th colspan="11">总部督办</th>
							<th colspan="11">12300</th>
							<th colspan="11">纠风</th>
						</tr>
						<tr>
							<th rowspan="2" data-field="SJTOTAL_TOTAL" data-sortable='true'>升级投诉总量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="SJTOTAL_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="SJTOTAL_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="SJTOTAL_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="SJTOTAL_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="NINE_TOTAL" data-sortable='true'>96180工单量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="NINE_MARKET_RAT" data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="NINE_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="NINE_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="NINE_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="MIIT_TOTAL" data-sortable='true'>工信部申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="MIIT_MARKET_RAT" data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="MIIT_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="MIIT_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="MIIT_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="ONEZ_TOTAL" data-sortable='true'>10080申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="ONEZ_MARKET_RAT" data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="ONEZ_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="ONEZ_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="ONEZ_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="PHNET_TOTAL" data-sortable='true'>民心网申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="PHNET_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="PHNET_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="PHNET_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="PHNET_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="CUSTOME_TOTAL" data-sortable='true'>客户来访申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="CUSTOME_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="CUSTOME_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="CUSTOME_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="CUSTOME_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="THREEO_TOTAL" data-sortable='true'>315平台申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="THREEO_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="THREEO_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="THREEO_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="THREEO_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="HEADQ_TOTAL" data-sortable='true'>总部督办申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="HEADQ_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="HEADQ_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="HEADQ_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="HEADQ_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="ONET_TOTAL" data-sortable='true'>12300申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="ONET_MARKET_RAT" data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="ONET_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="ONET_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="ONET_XA_RAT" data-sortable='true'>信安类占比</th>

							<th rowspan="2" data-field="RESTORE_TOTAL" data-sortable='true'>纠风申诉量</th>
							<th colspan="3">其中：市场类</th>
							<th rowspan="2" data-field="RESTORE_MARKET_RAT"
								data-sortable='true'>市场类占比</th>
							<th colspan="3">其中：网络类</th>
							<th rowspan="2" data-field="RESTORE_NET_RAT" data-sortable='true'>网络类占比</th>
							<th rowspan="2" data-field="RESTORE_XA" data-sortable='true'>其中：信安类</th>
							<th rowspan="2" data-field="RESTORE_XA_RAT" data-sortable='true'>信安类占比</th>
						</tr>
						<tr>
							<th data-field="SJTOTAL_MARKET_PERSONAL">个人市场</th>
							<th data-field="SJTOTAL_MARKET_FAMILY">家庭市场</th>
							<th data-field="SJTOTAL_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="SJTOTAL_NET_PERSONAL">个人网络</th>
							<th data-field="SJTOTAL_NET_FAMILY">家庭网络</th>
							<th data-field="SJTOTAL_NET_ENTERPRISE">政企网络</th>

							<th data-field="NINE_MARKET_PERSONAL">个人市场</th>
							<th data-field="NINE_MARKET_FAMILY">家庭市场</th>
							<th data-field="NINE_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="NINE_NET_PERSONAL">个人网络</th>
							<th data-field="NINE_NET_FAMILY">家庭网络</th>
							<th data-field="NINE_NET_ENTERPRISE">政企网络</th>

							<th data-field="MIIT_MARKET_PERSONAL">个人市场</th>
							<th data-field="MIIT_MARKET_FAMILY">家庭市场</th>
							<th data-field="MIIT_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="MIIT_NET_PERSONAL">个人网络</th>
							<th data-field="MIIT_NET_FAMILY">家庭网络</th>
							<th data-field="MIIT_NET_ENTERPRISE">政企网络</th>

							<th data-field="ONEZ_MARKET_PERSONAL">个人市场</th>
							<th data-field="ONEZ_MARKET_FAMILY">家庭市场</th>
							<th data-field="ONEZ_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="ONEZ_NET_PERSONAL">个人网络</th>
							<th data-field="ONEZ_NET_FAMILY">家庭网络</th>
							<th data-field="ONEZ_NET_ENTERPRISE">政企网络</th>

							<th data-field="PHNET_MARKET_PERSONAL">个人市场</th>
							<th data-field="PHNET_MARKET_FAMILY">家庭市场</th>
							<th data-field="PHNET_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="PHNET_NET_PERSONAL">个人网络</th>
							<th data-field="PHNET_NET_FAMILY">家庭网络</th>
							<th data-field="PHNET_NET_ENTERPRISE">政企网络</th>

							<th data-field="CUSTOME_MARKET_PERSONAL">个人市场</th>
							<th data-field="CUSTOME_MARKET_FAMILY">家庭市场</th>
							<th data-field="CUSTOME_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="CUSTOME_NET_PERSONAL">个人网络</th>
							<th data-field="CUSTOME_NET_FAMILY">家庭网络</th>
							<th data-field="CUSTOME_NET_ENTERPRISE">政企网络</th>

							<th data-field="THREEO_MARKET_PERSONAL">个人市场</th>
							<th data-field="THREEO_MARKET_FAMILY">家庭市场</th>
							<th data-field="THREEO_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="THREEO_NET_PERSONAL">个人网络</th>
							<th data-field="THREEO_NET_FAMILY">家庭网络</th>
							<th data-field="THREEO_NET_ENTERPRISE">政企网络</th>

							<th data-field="HEADQ_MARKET_PERSONAL">个人市场</th>
							<th data-field="HEADQ_MARKET_FAMILY">家庭市场</th>
							<th data-field="HEADQ_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="HEADQ_NET_PERSONAL">个人网络</th>
							<th data-field="HEADQ_NET_FAMILY">家庭网络</th>
							<th data-field="HEADQ_NET_ENTERPRISE">政企网络</th>

							<th data-field="ONET_MARKET_PERSONAL">个人市场</th>
							<th data-field="ONET_MARKET_FAMILY">家庭市场</th>
							<th data-field="ONET_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="ONET_NET_PERSONAL">个人网络</th>
							<th data-field="ONET_NET_FAMILY">家庭网络</th>
							<th data-field="ONET_NET_ENTERPRISE">政企网络</th>

							<th data-field="RESTORE_MARKET_PERSONAL">个人市场</th>
							<th data-field="RESTORE_MARKET_FAMILY">家庭市场</th>
							<th data-field="RESTORE_MARKET_ENTERPRISE">政企市场</th>
							<th data-field="RESTORE_NET_PERSONAL">个人网络</th>
							<th data-field="RESTORE_NET_FAMILY">家庭网络</th>
							<th data-field="RESTORE_NET_ENTERPRISE">政企网络</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>