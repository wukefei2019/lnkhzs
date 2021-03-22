<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	var num = "${param.num}";
	$(document).ready(function() {
	var id = "${param.id}";
	$("#inpa").val(id);
	
	});

	function fmt_operate(value, row, index) {
		var answer = row.ANSWER;
		var html = [];
		if (num == -1) {
			html.push(answer);
		} else {
			var stranswer = answer.split(",,,,");
			console.log(stranswer);
			
			html.push(stranswer[num]);
		}


		return html.join("");
	}


</script>
<meta charset="UTF-8">
<title>调研题库</title>
<%-- <script type="text/javascript" src="${ctx }/lnkhzs/quality/seleTag.js"></script> --%>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_seleJD.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
			</dg:lefttoolbar>
			<dg:condition>

				<div class="btn_box overflow">
					<div
						class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
					<div
						class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
				</div>
				<input type="hidden" id="inpa" name="RELATIONID" value="${param.id}" />
			</dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false'
					data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<!-- <th data-field="ANSWER">简答答案</th> -->
							<th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">简答答案</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>