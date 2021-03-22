<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>

/* 	$(document).ready(function() {
		addCheckBox();
	}); */
</script>
<meta charset="UTF-8">
<title>调研题库</title>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/survey/addDygxtk.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_QYESTION.query">

			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息 <a data-toggle="collapse" href="#form"
						style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
					<a class='btn floatRight10 handOut' onclick="handClick()"
						style='display: inline;'>提交</a>
				</h3>
			</div>

			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
			</dg:lefttoolbar>
			<dg:ajax-table>
				<table id="table1" data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-click-to-select="true"
					data-maintain-selected="true" data-on-check="table_check"
					data-on-check-all="table_check" data-on-uncheck="table_check"
					data-on-uncheck-all="table_check"
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<!-- <th data-checkbox="true" data-formatter="fmt_operate"
								data-report-col-mode="none" data-events="fn_operate_events">  data-dictype='id'
							</th>-->
							<th data-visible="false" data-field="ID" >题号</th> 
							<th data-checkbox='true'></th>
							<th data-field="CATEGORY">标签，分类</th>
							<th data-field="TYPE">题型（单选，多选，判断，简答）</th>
							<th data-field="QUESTION">题目</th>
							<th data-field="OPTIONS">选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>

		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>