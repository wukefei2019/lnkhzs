
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>

	 $(document).ready(function() {
		$.bs.table.refresh("table0");
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/oversee/addSwork.jsp');
		});
		
		/* $(".btn-link.icon-roundad").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/survey/appointNum.jsp');
		}); */
	}); 
	

	
	
	
</script>
<html>
<head>
<meta charset="UTF-8">
	<title>流程发起详情</title> <script type="text/javascript"
		src="${ctx }/lnkhzs/oversee/swork.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_SWORK.query">
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-roundadd">发起</button>
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
					<table data-height='auto' data-fixed-columns='false' data-fixed-number='3'
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
					<thead>
						<tr>
							<!-- <th data-field="ID" data-sortable='true'> 编号</th>  -->
						<!-- 	<th data-field="PID" data-sortable='true'>编号</th> -->
							<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
							<th data-field="SERIAL_NUMBER" data-sortable='true'>编号</th>
							<th data-field="SUPERVISION_MATTERS" data-sortable='true'>督办事项</th>
							<th data-field="DEPARTMENT" data-sortable='true'>派单部门</th>
							<th data-field="DISPATCH" data-sortable='true'>派单人</th>
							<th data-field="DISPATCH_PHONE" data-sortable='true'>派单人电话</th>
							<th data-field="CREATETIME" data-sortable='true'>起草时间</th>
							<th data-field="TIME_LIMIT" data-sortable='true'>督办时限</th>
							<th data-field="TYPE" data-sortable='true'>督办类别</th>
							<th data-field="MAINDEPART" data-sortable='true'>主责部门</th>
							<th data-field="DESCRIPTION" data-sortable='true'>督办问题描述</th>
							<th data-field="SOURCE" data-sortable='true'>督办问题来源</th>
							<th data-field="TARGET" data-sortable='true'>督办目标</th>
							<th data-field="APPROVOR" data-sortable='true'>接口人</th>
							<th data-field="SENDTO" data-sortable='true'>抄送人</th>
							<th data-field="BACKHANDLINGTIME" data-sortable='true'>处理时间</th>
							<th data-field="BACKHANDLINGPERSON" data-sortable='true'>处理人中文名</th>
							<th data-field="BACKHANDLINGPERSONLOGIN" data-sortable='true'>处理人oa登录名</th>
							<th data-field="BACKRESOLUTION" data-sortable='true'>办理情况</th>
						 </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>
</html>