<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/zCommon.js"></script>
<script type='text/javascript'>
	
</script>
<meta charset="UTF-8">
<title>溯源问题统计表-月</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/kddsmyd.js"></script>	
	<style>
table {
	border-right: 0.5px solid rgb(221, 221, 221);
	border-top: 0.5px solid rgb(221, 221, 221);
	border-right: 0.5px solid rgb(221, 221, 221);
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
}
</style>
<script type="text/javascript">
	var table_load_success = function(){
		$('#table1').find('td:contains("合计")').parent().children('td:even:not(:contains("合计"))').text('-');
	}
</script>		
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" conditionIsOpen="true" sqlname="SQL_TRACE_STATISTICS_MONTH.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
				<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
			</dg:lefttoolbar>
			<dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
				<table id="table1" data-height='auto' data-fixed-columns='true' data-fixed-number='1' data-on-load-success="table_load_success"
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false' data-show-footer='false'>
					<thead>
                        <tr>
                            <th data-field="TYPE" colspan="1" rowspan="2">溯源时间</th>
                            <th  colspan="2" >截止1月</th>
                            <th  colspan="2" >截止2月</th>
                            <th  colspan="2" >截止3月</th>
                            <th  colspan="2" >截止4月</th>
                            <th  colspan="2" >截止5月</th>
                            <th  colspan="2" >截止6月</th>
                            <th  colspan="2" >截止7月</th>
                            <th  colspan="2" >截止8月</th>
                            <th  colspan="2" >截止9月</th>
                            <th  colspan="2" >截止10月</th>
                            <th  colspan="2" >截止11月</th>
                            <th  colspan="2" >截止12月</th>
                        </tr>
                        <tr>
                        	
                            <th data-field="一月问题量" >一月问题量</th>
                            <th data-field="一月占比" >一月占比（%）</th>
                            <th data-field="二月问题量" >二月问题量</th>
                            <th data-field="二月占比" >二月占比（%）</th>
                            <th data-field="三月问题量" >三月问题量</th>
                            <th data-field="三月占比" >三月占比（%）</th>
                            <th data-field="四月问题量" >四月问题量</th>
                            <th data-field="四月占比" >四月占比（%）</th>
                            <th data-field="五月问题量" >五月问题量</th>
                            <th data-field="五月占比" >五月占比（%）</th>
                            <th data-field="六月问题量" >六月问题量</th>
                            <th data-field="六月占比" >六月占比（%）</th>
                            <th data-field="七月问题量" >七月问题量</th>
                            <th data-field="七月占比" >七月占比（%）</th>
                            <th data-field="八月问题量" >八月问题量</th>
                            <th data-field="八月占比" >八月占比（%）</th>
                            <th data-field="九月问题量" >九月问题量</th>
                            <th data-field="九月占比" >九月占比（%）</th>
                            <th data-field="十月问题量" >十月问题量</th>
                            <th data-field="十月占比" >十月占比（%）</th>
                            <th data-field="十一月问题量" >十一月问题量</th>
                            <th data-field="十一月占比" >十一月占比（%）</th>
                            <th data-field="十二月问题量" >十二月问题量</th>
                            <th data-field="十二月占比" >十二月占比（%）</th>
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>