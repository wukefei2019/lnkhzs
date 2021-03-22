<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	$(document).ready(function() {
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/oversee/editDbManager.jsp');
		});
	});
	
</script>
<meta charset="UTF-8">
<title>管理员配置</title>
<script type="text/javascript" src="${ctx }/lnkhzs/oversee/dbmanager.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_DB_MANAGER.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
			</dg:lefttoolbar>
			<dg:condition>
            	<input type="hidden" name="createby" value="${userid}" />
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
				<table data-height='auto' data-fixed-columns='false' data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
					<thead>
						<tr>
							<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
						    <!-- <th   data-field="ID">id</th> -->
							<th data-field="ADMINISTRATORNAME" data-sortable='true'>管理员</th>
							<!-- <th data-field="ADMINISTRATORID">管理员ID</th> -->
							<th data-field="CITYNAME" data-sortable='true'>地市</th>
							<!-- <th data-field="CITYID">地市ID</th> -->
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>