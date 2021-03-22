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
<title>集团专线开通满意度统计表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/qykdktmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_QYKDKTMYD.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/import_qykdktmyd.jsp',width=500,height=300)">导入</button>
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
                        	<th class="col-xs-l" data-field="state" data-checkbox='true'></th>
                        	<th data-field="MONTH" data-sortable='true'>月份</th>
                        	<th data-field="RESP_DEPT" data-sortable='true'>地市</th>
                            <th data-field="SATIS" data-sortable='true'>企业宽带开通满意度</th>
                            <th data-field="TOTALSATIS" data-sortable='true'>开通整体满意度(10%)</th>
                            <th data-field="TIMELINESS" data-sortable='true'>开通时效性(30%) </th>
                            <th data-field="SPECIALITY" data-sortable='true'>施工专业性(30%) </th>
                            <th data-field="PERSONSERVICE" data-sortable='true'>施工人员服务(30%)</th>
                            <th data-field="SAMPLE_NUM" data-sortable='true'>样本量</th>
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>