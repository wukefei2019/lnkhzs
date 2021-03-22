
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>

<html>
<head>
<meta charset="UTF-8">
	<title>数据入库校验报表</title>
	<script type="text/javascript"
		src="${ctx }/lnkhzs/quality/checkSheetDetail.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_CheckSheet.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<!-- <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button> -->
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
							<!-- <th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th> -->
							<th data-field="CHECKDATE" data-sortable='true' >比对日期</th>
							<th data-field="SHEETNAME" data-sortable='true'>报表名称</th>
							<th data-field="TABLENAME" data-sortable='true'>表说明</th>
							<th data-field="INROWNUM" data-sortable='true' data-formatter="fmt_operate1">入库条数</th>
							<th data-field="CHKROWNUM" data-sortable='true' data-formatter="fmt_operate2">校验文件条数</th>
							<!-- <th data-field="STATUS" data-sortable='true'>状态</th> -->
							<!-- <th data-field="SYSTIME" data-sortable='true'>日期</th>  -->
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>
</html>
