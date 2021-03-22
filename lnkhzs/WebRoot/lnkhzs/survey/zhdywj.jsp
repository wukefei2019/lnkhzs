
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>

	$(document).ready(function() {
		$.bs.table.refresh("table0");
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/survey/dist/index.html?ispublic=公有&isreward=无奖励');
		});
		
		$(".btn-link.icon-roundad").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/survey/appointNum.jsp');
		});
	});
</script>
<html>
<head>
<meta charset="UTF-8">
	<title>客户之声信息</title> <script type="text/javascript"
		src="${ctx }/lnkhzs/survey/zhdywj.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_DYWJ01.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
				<!-- <button class="btn-link iconfont fontsize14 icon-roundad">指定号码发起</button> -->
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
							<th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th>
							<th data-field="NAME" data-sortable='true'>问卷名</th>
							<th data-field="NAMESUB" data-sortable='true'>副标题</th>
							<th data-field="CREATTIME" data-sortable='true'>问卷时间</th>
							<th data-field="ISPUBLIC" data-sortable='true'>所属状态</th>
							<th data-field="CREATEBYNAME" data-sortable='true'>所属人</th>
							<th data-field="STATUS" data-sortable='true'>状态</th> </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>
</html>