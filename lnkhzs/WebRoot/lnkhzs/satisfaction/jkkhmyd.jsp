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
<title>家宽客户满意度调研结果统计表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/jkkhmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_JKKHMYD_TJ.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/importjkkhmyd.jsp',width=500,height=300)">导入</button>
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
                        	<th rowspan="2" data-field="INDEXTYPE" data-sortable='true'>指标类型</th>
                        	<th rowspan="2" data-field="RESP_DEPT" data-sortable='true'>地市</th>
                        	<th rowspan="2" data-field="CUSTOMER_SATIS" data-sortable='true'>家宽客户满意度</th>
                        	<th rowspan="2" data-field="WHOLE_SATIS" data-sortable='true'>整体满意度</th>
                        	<th rowspan="2" data-field="PACKAGEFEE" data-sortable='true'>资费套餐</th>
                        	<th rowspan="2" data-field="QUALITY" data-sortable='true'>上网质量</th>
                        	<th colspan="3" >业务宣传办理</th>
                        	<th colspan="4" >服务水平</th>
                        	
                        </tr>
                         <tr>
                        	<th data-field="WHOLE_BUSINESS" data-sortable='true'>整体(业务宣传办理)</th>
                        	<th data-field="PROPAGANDA" data-sortable='true'>业务宣传(业务宣传办理)</th>
                            <th data-field="HANDLE" data-sortable='true'>业务办理(业务宣传办理)</th>
                            <th data-field="WHOLE_SERVICE" data-sortable='true'>整体(服务水平)</th>
                            <th data-field="INSTALL" data-sortable='true'>装机服务(服务水平)</th>
                            <th data-field="FAULT" data-sortable='true'>故障维修(服务水平)</th>
                            <th data-field="CONSULTATION" data-sortable='true'>咨询投诉(服务水平)</th>
                            
                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>