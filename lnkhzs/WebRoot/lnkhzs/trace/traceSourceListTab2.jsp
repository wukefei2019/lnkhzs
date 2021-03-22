<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
	$(document).ready(function() {
		
	});
	
</script>
<meta charset="UTF-8">
<title>溯源问题清单</title>
<script type="text/javascript" src="${ctx }/lnkhzs/trace/traceSourceListTab2.js"></script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="${param.sqlname}">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
			</dg:lefttoolbar>
			<dg:condition>
            	
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
							<th data-field="CODE" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" data-sortable='true'>批次号</th>
							<!-- <th data-field="PROCESSING" data-sortable='true'>处理中</th> -->
                        	<th data-field="COMPLETED" data-sortable='true'>已完成</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>