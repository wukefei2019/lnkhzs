<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/zCommon.js"></script>
<script type='text/javascript'>
	
</script>
<meta charset="UTF-8">
<title>集团专线开通满意度统计表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/kddsmyd.js"></script>	
	<style>
/* table {
	border-right: 0.5px solid rgb(221, 221, 221);
	border-top: 0.5px solid rgb(221, 221, 221);
	border-right: 0.5px solid rgb(221, 221, 221);
	min-width: 1500px
}

table td {
	border-left: 0.5px solid rgb(221, 221, 221);
	border-bottom: 0.5px solid rgb(221, 221, 221);
	border-right: 0.5px solid rgb(221, 221, 221);
}

table tbody td {
	height: 30px
}

.bootstrap-table .table.table-no-bordered > thead > tr > th{
    border-right: 2px solid rgb(221, 221, 221);
} */
</style>		
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TRACE_STATISTICS_CITY.query">
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
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
					<thead>
                        <tr>
                            <th data-field="MONTH" colspan="28" >溯源时间</th>
                        </tr>
                        <tr>
                        	
                            <th data-field="CITY" data-sortable='true'>地市</th>
                            <th data-field="一月问题量" data-sortable='true'>一月问题量</th>
                            <th data-field="一月占比" data-sortable='true'>一月占比</th>
                            <th data-field="二月问题量" data-sortable='true'>二月问题量</th>
                            <th data-field="二月占比" data-sortable='true'>二月占比</th>
                            <th data-field="三月问题量" data-sortable='true'>三月问题量</th>
                            <th data-field="三月占比" data-sortable='true'>三月占比</th>
                            <th data-field="四月问题量" data-sortable='true'>四月问题量</th>
                            <th data-field="四月占比" data-sortable='true'>四月占比</th>
                            <th data-field="五月问题量" data-sortable='true'>五月问题量</th>
                            <th data-field="五月占比" data-sortable='true'>五月占比</th>
                            <th data-field="六月问题量" data-sortable='true'>六月问题量</th>
                            <th data-field="六月占比" data-sortable='true'>六月占比</th>
                            <th data-field="七月问题量" data-sortable='true'>七月问题量</th>
                            <th data-field="七月占比" data-sortable='true'>七月占比</th>
                            <th data-field="八月问题量" data-sortable='true'>八月问题量</th>
                            <th data-field="八月占比" data-sortable='true'>八月占比</th>
                            <th data-field="九月问题量" data-sortable='true'>九月问题量</th>
                            <th data-field="九月占比" data-sortable='true'>九月占比</th>
                            <th data-field="十月问题量" data-sortable='true'>十月问题量</th>
                            <th data-field="十月占比" data-sortable='true'>十月占比</th>
                            <th data-field="十一月问题量" data-sortable='true'>十一月问题量</th>
                            <th data-field="十一月占比" data-sortable='true'>十一月占比</th>
                            <th data-field="十二月问题量" data-sortable='true'>十二月问题量</th>
                            <th data-field="十二月占比" data-sortable='true'>十二月占比</th>
                            <th data-field="TOTAL" data-sortable='true' data-formatter="$.bs.table.fmt.footer_sum">合计</th>
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>