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
<title>集团关键人满意度调研结果统计表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/jtgjrmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_JTGJRMYD_TJ.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/importJtgjrmyd.jsp',width=500,height=300)">导入</button>
				<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
				<button class="btn-link iconfont fontsize14 icon-delete1" onclick="deleteList()">批量删除</button>
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
                        	<th rowspan="2" class="col-xs-l" data-field="state" data-checkbox='true'></th>
                        	<th rowspan="2" data-field="DYNUM" data-sortable='true'>调研期数</th>
                        	<th rowspan="2" data-field="RESP_DEPT" data-sortable='true'>集团归属地市</th>
                            <th rowspan="2" data-field="POLICY_SATIS" data-sortable='true'>集团决策人满意度</th>
                            <th rowspan="2" data-field="WHOLE_SATIS" data-sortable='true'>整体满意度</th>
                            <th rowspan="2" data-field="PERFORMANCE" data-sortable='true'>履约情况</th>
                            <th rowspan="2" data-field="RELIABLE" data-sortable='true'>产品可靠性</th>
                            <th colspan="4">客户经理服务</th>
                        </tr>
                         <tr>
                            <th data-field="WHOLE" data-sortable='true'>整体(客户经理服务)</th>
                            <th data-field="SKILL" data-sortable='true'>服务技能(客户经理服务)</th>
                            <th data-field="ATTITUDE" data-sortable='true'>服务态度(客户经理服务)</th>
                            <th data-field="TIMELY" data-sortable='true'>响应及时性(客户经理服务)</th>
                            
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>