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
<title>溯源问题处理-待办</title>
<script type="text/javascript" src="${ctx }/lnkhzs/trace/traceSourceTDetail.js"></script>

<link href="${ctx}/lnkhzs/trace/toolbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/lnkhzs/trace/zCommon.js"></script>
<script type='text/javascript' src='${ctx }/common/style/omcs/js/common/select2.full.min.js'></script>
<link rel="stylesheet" href="${ctx }/common/style/omcs/css/common/select2.css" type="text/css"></link>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<input type="hidden" name="selectUser.loginName" value="${selectUser.loginName }" />
		<input type="hidden" name="selectUser.fullName" value="${selectUser.fullName }" />
		<dg:ajaxgrid id="table0" sqlname="SQL_ZL_TRACE_SOURCE_TDETAIL3.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-download" onclick="exportd()">批量完成导出</button>
	            <button class="btn-link iconfont fontsize14 icon-upload1" onclick="openwindow('${ctx}/lnkhzs/trace/import3.jsp')">批量完成导入</button>
	            <button class="btn-link iconfont fontsize14 icon-share1" onclick="openwindow('${ctx}/org/person/selectWin2.jsp?callback=setDutyUser&single=1','')">批量转派</button>
			</dg:lefttoolbar>
			<dg:condition>
				<input type="hidden" id="nextloginname" name="nextloginname" value="<%=user.getLoginName() %>"/>
				<input type="hidden" id="code" name="code" value="${param.id}"/>
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
				<table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success" 
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false' >
					<thead>
						<tr>
							<th data-checkbox="true"  data-report-col-mode="none"></th>
							<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
							<th data-visible="false" data-field="PID">PID</th>
							<th data-field="CODE">批次号</th>
                            <th data-field="SERIALNO" >编号</th>
							<th data-field="LINK_PROGRESS" >环节进展</th>
							<th data-field="TRACE_SOURCE_TIME" >溯源时间</th>
                        	<th data-field="TRACE_SOURCE_TYPE" >溯源分类</th>
                            <th data-field="QUESTION_SUBCATEGORY" >问题小类</th>
                            <th data-field="CONCRETE_PROBLEMS" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" >具体问题点</th>
                            <th data-field="AUXILIARY_DEPARTMENT">影响范围</th>
                            <th data-field="RESP_DEPT" >主责部门</th>
                            <th data-field="PROBLEM" >问题原因</th>
                            <th data-field="RECTIFICATION_GOAL" >整改目标</th>
                            <th data-field="NOT_FINISHED_REASON" >解决进度-未完成具体原因</th>
                            <th data-field="NOT_COMPLETED_DESCRIPTION" >未完成详细描述</th>
							<th data-field="RESPONSIBLE_UNIT" >落实责任单位</th>
                        	<th data-field="RESPONSIBLE_DEPARTMENT" >落实责任部门</th>
                            <th data-field="RESPONSIBLE_PERSON" >落实责任人</th>
                            <th data-field="TRIGGER_COMPLAINTS_NUM" >触发投诉量</th>
                            <th data-field="TRIGGER_WANTOU_RATIO" >触发万投比</th>
                            <th data-field="TRIGGER_HANDLING_VOLUME" >触发办理量</th>
                            <th data-field="ACCEPTANCE_COMPLAINTS" >验收全量投诉量</th>
                            <th data-field="ACCEPTANCE_COMPLAINTS_RATE" >验证万投比</th>
                            <th data-field="COMPLETION_STATUS" >完成情况</th>
                            <th data-field="ACCEPTANCE_TIME" >完成时间</th>
                            <th data-field="CREATE_TIME" >入库时间</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>