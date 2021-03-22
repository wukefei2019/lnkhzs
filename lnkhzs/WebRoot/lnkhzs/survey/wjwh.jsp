
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>


var volid="${param.id}";

$(document).ready(function(){
	$("#inpu").attr("value",volid);

	$(".btn-link.icon-roundadd").on("click", function(event) {
		/* openwindow($ctx + '/lnkhzs/survey/addDygxtk.jsp?id='+volid); */
		openwindow($ctx + '/lnkhzs/survey/dist/index.html');
	});
});




</script>
<html>
<head>
<meta charset="UTF-8">
	<title>客户之声信息</title> <script type="text/javascript"
		src="${ctx }/lnkhzs/survey/wjwh.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_WJWH.query">
			<dg:lefttoolbar>

				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<button  class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
			</dg:lefttoolbar>
			<dg:condition>
			<input id="inpu" name="QUESTIONNAIREID" type="hidden" value="${param.id}"  />
			</dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false' 
					data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<!-- <th style="display: none" data-field="QUESTIONNAIREID">问卷号</th> -->
							<th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th>
							<th data-field="QUESTIONID" data-formatter="$.bs.table.fmt.link"
								data-events="fn_evnt_name_look">题号</th>
							<th data-field="NAME">问卷名</th>
							<th data-field="CREATTIME">问卷时间</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>
</html>