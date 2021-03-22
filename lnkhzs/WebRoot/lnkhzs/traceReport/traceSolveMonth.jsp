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
<title>溯源问题解决率统计表-月</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/kddsmyd.js"></script>	
	<!-- <style>
table {
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
}
</style> -->		
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" conditionIsOpen="true" sqlname="SQL_TRACE_SOLVE_MONTH.query">
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
				<table id="table1" data-height='auto' data-fixed-columns='true' data-fixed-number='1' data-show-footer='false'
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
					<thead>
                        <tr>
                            <th data-field="TYPE">溯源范围</th>
                            <th data-field="一月问题量" >截至一月问题量合计</th>
                            <th data-field="一月验收量合计" >一月验收量合计</th>
                            <th data-field="一月解决率" >一月解决率（%）</th>
                            <th data-field="一月环比" >解决率环比（pp）</th>
                            <th data-field="二月问题量" >截至二月问题量合计</th>
                            <th data-field="二月验收量合计" >二月验收量合计</th>
                            <th data-field="二月解决率" >二月解决率（%）</th>
                            <th data-field="二月环比" >解决率环比（pp）</th>
                            <th data-field="三月问题量" >截至三月问题量合计</th>
                            <th data-field="三月验收量合计" >三月验收量合计</th>
                            <th data-field="三月解决率" >三月解决率（%）</th>
                            <th data-field="三月环比" >解决率环比（pp）</th>
                            <th data-field="四月问题量" >截至四月问题量合计</th>
                            <th data-field="四月验收量合计" >四月验收量合计</th>
                            <th data-field="四月解决率" >四月解决率（%）</th>
                            <th data-field="四月环比" >解决率环比（pp）</th>
                            <th data-field="五月问题量" >截至五月问题量合计</th>
                            <th data-field="五月验收量合计" >五月验收量合计</th>
                            <th data-field="五月解决率" >五月解决率（%）</th>
                            <th data-field="五月环比" >解决率环比（pp）</th>
                            <th data-field="六月问题量" >截至六月问题量合计</th>
                            <th data-field="六月验收量合计" >六月验收量合计</th>
                            <th data-field="六月解决率" >六月解决率（%）</th>
                            <th data-field="六月环比" >解决率环比（pp）</th>
                            <th data-field="七月问题量" >截至七月问题量合计</th>
                            <th data-field="七月验收量合计" >七月验收量合计</th>
                            <th data-field="七月解决率" >七月解决率（%）</th>
                            <th data-field="七月环比" >解决率环比（pp）</th>
                            <th data-field="八月问题量" >截至八月问题量合计</th>
                            <th data-field="八月验收量合计" >八月验收量合计</th>
                            <th data-field="八月解决率" >八月解决率（%）</th>
                            <th data-field="八月环比" >解决率环比（pp）</th>
                            <th data-field="九月问题量" >截至九月问题量合计</th>
                            <th data-field="九月验收量合计" >九月验收量合计</th>
                            <th data-field="九月解决率" >九月解决率（%）</th>
                            <th data-field="九月环比" >解决率环比（pp）</th>
                            <th data-field="十月问题量" >截至十月问题量合计</th>
                            <th data-field="十月验收量合计" >十月验收量合计</th>
                            <th data-field="十月解决率" >十月解决率（%）</th>
                            <th data-field="十月环比" >解决率环比（pp）</th>
                            <th data-field="十一月问题量" >截至十一月问题量合计</th>
                            <th data-field="十一月验收量合计" >十一月验收量合计</th>
                            <th data-field="十一月解决率" >十一月解决率（%）</th>
                            <th data-field="十一月环比" >解决率环比（pp）</th>
                            <th data-field="十二月问题量" >截至十二月问题量合计</th>
                            <th data-field="十二月验收量合计" >十二月验收量合计</th>
                            <th data-field="十二月解决率" >十二月解决率（%）</th>
                            <th data-field="十二月环比" >解决率环比（pp）</th>
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>