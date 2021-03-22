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
<title>调研题库</title>
<script type="text/javascript" src="${ctx }/lnkhzs/survey/dytk.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_QYESTION01.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
				<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
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
							<th data-visible="false" data-field="CATEGORY">标签，分类</th>
							<th data-field="QUESTION"data-sortable='true'>题目</th>
							<th data-field="PS"data-sortable='true'>备注</th>
							<th data-field="TYPE" data-formatter="fmt_operate_type" data-sortable='true' >题型</th>
							<th data-field="OPTIONA" data-sortable='true' data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" >选项(A)</th>
							<th data-field="OPTIONB" data-sortable='true' data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look">选项(B)</th>
							<th data-field="OPTIONC" data-sortable='true'>选项(C)</th>
							<th data-field="OPTIOND" data-sortable='true'>选项(D)</th>
							<!-- <th data-field="OPTIONE">选项(E)</th>
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
							<th data-field="OPTIONP">选项(P)</th> -->
							<th data-field="BELONGTO" data-sortable='true'>属性</th>
							<th data-field="CREATEBYNAME">所属人员</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>