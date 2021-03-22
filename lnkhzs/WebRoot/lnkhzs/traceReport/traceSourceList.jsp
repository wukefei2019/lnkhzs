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
<title>溯源清单表（溯源问题明细）</title>
<script type="text/javascript" src="${ctx }/lnkhzs/traceReport/traceSourceList.js"></script>

<link href="${ctx}/lnkhzs/trace/toolbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/lnkhzs/trace/zCommon.js"></script>
<script type='text/javascript' src='${ctx }/common/style/omcs/js/common/select2.full.min.js'></script>
<link rel="stylesheet" href="${ctx }/common/style/omcs/css/common/select2.css" type="text/css"></link>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_ZL_TRACE_SOURCE_LIST.query" inmap="${inmap }">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<!-- <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button> -->
			</dg:lefttoolbar>
			<dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
		    <div class="t-wrap">
		 		<div class="t-toolbar">
		 			<div class="t-panels">
		 				<div class="t-panel tbar-panel-cart">
			 				<div id="replacepl" >
			 				</div>

			 				<ul id="tree1" class="ztree" style="width:100%; height:inherit;margin-top: 0px;border: inherit"></ul>
		                </div>
		            </div>
		            <div class="t-tabs">
						<div class="icon-open t-tab-cart ">
							<i class="icon iconfont fontsize14"></i>
						</div>
					</div>
		        </div>
		    </div>
				<table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' 
				 class="table table-hover text_align_center table-no-bordered"  data-show-columns='false' data-row-attributes="rowStyle">
					<thead>
						<tr>
							<th data-visible="false" data-field="PID">PID</th>
							<th data-field="SERIALNO" data-sortable="true">编号</th>
							<th data-field="TRACE_SOURCE_TIME" data-sortable="true">溯源时间</th>
                        	<th data-field="TRACE_SOURCE_TYPE" data-sortable="true">溯源分类</th>
                            <th data-field="QUESTION_SUBCATEGORY" data-sortable="true">问题小类</th>
                            <th data-field="CONCRETE_PROBLEMS" data-sortable="true">具体问题点</th>
                            <th data-field="AUXILIARY_DEPARTMENT" data-sortable="true">影响范围</th>
                            <th data-field="RESP_DEPT" data-sortable="true">主责部门</th>
                            <th data-field="PROBLEM" data-sortable="true">问题原因</th>
                            <th data-field="RECTIFICATION_GOAL" data-sortable="true">整改目标</th>
                            <th data-field="NOT_FINISHED_REASON" data-sortable="true">解决进度-未完成具体原因</th>
                            <th data-field="NOT_COMPLETED_DESCRIPTION" data-sortable="true">未完成详细描述</th>
							<th data-field="RESPONSIBLE_UNIT" data-sortable="true">落实责任单位</th>
                        	<th data-field="RESPONSIBLE_DEPARTMENT" data-sortable="true">落实责任部门</th>
                            <th data-field="RESPONSIBLE_PERSON" data-sortable="true">落实责任人</th>
                            <th data-field="TRIGGER_COMPLAINTS_NUM" data-sortable="true">触发投诉量</th>
                            <th data-field="TRIGGER_WANTOU_RATIO" data-sortable="true">触发万投比</th>
                            <th data-field="TRIGGER_HANDLING_VOLUME" data-sortable="true">触发办理量</th>
                            <th data-field="ACCEPTANCE_COMPLAINTS" data-sortable="true">验收全量投诉量</th>
                            <th data-field="ACCEPTANCE_COMPLAINTS_RATE" data-sortable="true">验证万投比</th>
                            <th data-field="COMPLETION_STATUS" data-sortable="true">完成情况</th>
                            <th data-field="ACCEPTANCE_TIME" data-sortable="true">完成时间</th>
                            <th data-field="REMARK" data-sortable="true">备注</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>