<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	$(document).ready(function() {
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/survey/editDytk.jsp');
		});
	});
	
</script>
<meta charset="UTF-8">
<title>溯源问题流程明细</title>
<script type="text/javascript" src="${ctx }/lnkhzs/trace/traceSourceFList.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_ZL_TRACE_SOURCE_FLIST.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
			</dg:lefttoolbar>
			<dg:condition>
            	<input type="hidden" id="sourcepid" name="sourcepid" value="${param.pid}"/>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
				<table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3'  
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false' >
					<thead>
						<tr>
							<th data-visible="false" data-field="PID">PID</th>
							<th data-field="CREATETIME">时间</th>
                        	<th data-field="FULLNAME">负责人</th>
                            <th data-field="NEXTFULLNAME">下一环节负责人</th>
                            <th data-field="LINK_PROGRESS" >环节进展</th>
                            <!-- <th data-field="RECTIFICATION_GOAL" >整改目标</th> -->
                            <th data-field="NOT_FINISHED_REASON" >解决进度-未完成具体原因</th>
                            <th data-field="NOT_COMPLETED_DESCRIPTION" >未完成详细描述</th>
							<th data-field="RESPONSIBLE_UNIT" >落实责任单位</th>
                        	<th data-field="RESPONSIBLE_DEPARTMENT" >落实责任部门</th>
                            <th data-field="RESPONSIBLE_PERSON" >落实责任人</th>
                            <th data-field="STATUS" >工单状态</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>