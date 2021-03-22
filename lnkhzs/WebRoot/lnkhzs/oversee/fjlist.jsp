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
<title>附件查看</title>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/oversee/fjlist.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_WORKATTACH.query">
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
				<input hidden="hidden" name="backguid" value="${param.id}">
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
							<!-- <th data-checkbox="true"></th> -->
							<th data-field="ATTACHNAME">附件名称</th>
							<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events" >地址</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>

		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>