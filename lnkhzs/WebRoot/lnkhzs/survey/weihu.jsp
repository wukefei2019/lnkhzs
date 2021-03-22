<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	var volid = "${param.id}";
</script>
<meta charset="UTF-8">
<title>题库维护</title>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/survey/addTiku.js"></script>
<script type="text/javascript">



	function handClick() {
		var cheID = "";
		var checktion = $("#table1").bootstrapTable('getSelections');
		if (checktion.length == 0) {
			alert("请选择数据");
			return false;
		} else {
			if ($("#belongto").val() == "") {
				alert("请选择所属状态");
				return false;
			}
			for (var i = 0; i < checktion.length; i++) {
				var row = checktion[i];
				cheID += row.ID + ",,";
			}
			if (confirm("确认提交？")) {
				var url = encodeURI($ctx + "/khzs/surveys/addManyDytk.action");
				$.post(url, "cheID=" + cheID + "&khzsQuestion.belongto=" + $("#belongto").val()).done(function(result) {
					if (result == 'success') {
						alert("操作成功");
						window.close();

					} else {
						alert("操作失败");
					}
				}, "json");
			}

		}


	}
</script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_QUESTIONALL.query">

			<div class='panel-heading'>
				<h3 class='panel-title'>
					<a class='btn floatRight10 handOut' onclick="handClick()"
						style='display: inline;float: right;'>提交</a> <select id="belongto"
						style="float: right;height: 27px;margin-top: -4px;width: 75px;"
						class='form-control'>
						
						<option value="公有">公有</option>
						<option selected="selected" value="私有">私有</option>
					</select>

				</h3>
			</div>

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
				<input hidden="hidden" name="questionnaireid" value="${param.id}">
			</dg:condition>
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
							<th data-checkbox="true"></th>
							<th data-visible="false" data-field="ID">ID</th>
							<th data-visible="false" data-field="CATEGORY">标签，分类</th>
							<th data-field="QUESTION">题目</th>
							<th data-field="TYPE">题型（单选，多选，判断，简答）</th>
							<!-- <th data-field="QUESTION">题目</th> -->
							<th data-field="OPTIONA">选项(A)</th>
							<th data-field="OPTIONB">选项(B)</th>
							<th data-field="OPTIONC">选项(C)</th>
							<th data-field="OPTIOND">选项(D)</th>
							<th data-field="OPTIONE">选项(E)</th>
							<th data-field="OPTIONF">选项(F)</th>
							<th data-field="OPTIONG">选项(G)</th>
							<th data-field="OPTIONH">选项(H)</th>
							<th data-field="OPTIONI">选项(I)</th>
							<th data-field="OPTIONJ">选项(J)</th>
							<th data-field="OPTIONK">选项(K)</th>
							<th data-field="OPTIONL">选项(L)</th>
							<th data-field="OPTIONM">选项(M)</th>
							<th data-field="OPTIONN">选项(N)</th>
							<th data-field="OPTIONO">选项(O)</th>
							<th data-field="OPTIONP">选项(P)</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>

		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>