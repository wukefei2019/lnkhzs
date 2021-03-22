<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	$(document).ready(function() {
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/quality/addTag.jsp');
		});
	});
	
</script>
<meta charset="UTF-8">
<title>调研题库</title>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/tagRed.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_SERVICE_REQUEST_Red.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
				<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
			</dg:lefttoolbar>
			<dg:condition>
            	
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
							<th data-formatter="fmt_operate" data-report-col-mode="none"
								data-events="fn_operate_events">操作</th>
							<th data-field="ID" data-visible="false">id</th>
							<th data-field="REPORTERNAME" data-sortable='true'>申报人</th>
							<th data-field="REPORTTIME" data-sortable='true'>申报时间</th>
							<th data-field="REPORTSTATUS" data-sortable='true'>申报状态</th>
							<th data-field="WORKNUMBER" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false" data-sortable='true'>工单号</th>
							<th data-field="WORKTAG" data-sortable='true'>工单号绑定的七级标签</th>
							<th data-field="WORKINFO" data-formatter="fmt_operate_ywnr" data-sortable='true'>业务内容</th>
							<th data-field="TAG" data-sortable='true'>七级标签</th>
							<th data-field="CHANGEVIEW" data-sortable='true'>调整建议</th>
							<th data-field="APPROVERNAME" data-sortable='true'>审批人</th>
							<th data-field="APPROVALTIME" data-sortable='true'>审批时间</th>
							<!-- <th data-field="APPROVALINFO">审批备注</th> -->
							<th data-field="APPROVALSTATUS" data-sortable='true'>审批状态</th>
							
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>