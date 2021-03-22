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
<title>10086投诉处理满意度调研明细表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/tsclmydmxb.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TSCLMYD_DETALL.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<%-- <button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/import_tsclmydmxb.jsp',width=500,height=300)">导入</button> --%>
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
                        	<th data-field="MONTH" data-sortable='true'>调研日期</th>
                        	<th data-field="RESP_DEPT" data-sortable='true'>地市</th>
                        	<th data-field="AREA_NAME" data-sortable='true'>县区</th>
                        	<th data-field="RESEAU" data-sortable='true'>网格</th>
                        	<th  data-field="PHONE_NUMBER" data-sortable='true'>手机号码</th>
                        	<th  data-field="ORDER_NUMBER" data-sortable='true'>投诉工单号</th>
                        	<th  data-field="ORDER_STIME" data-sortable='true'>投诉立单时间</th>
                        	<th  data-field="ORDER_TYPE" data-sortable='true'>投诉类型</th>
                        	<th  data-field="R_DEPARTMENT" data-sortable='true'>责任部门</th>
                        	<th  data-field="ORDER_ETIME" data-sortable='true'>投诉结单时间</th>
                        	<th  data-field="QUESTION" data-sortable='true'>是否解决</th>
                        	<th  data-field="WORK_TEAM" data-sortable='true'>处理工作组</th>	
                        	<th  data-field="FWORK_TEAM" data-sortable='true'>首处理工作组</th>
                        	<th  data-field="AWORK_TEAM" data-sortable='true'>协办工作组</th>
                        	<th  data-field="OVERALL_TIME" data-sortable='true'>整体时限时间</th>
                        	<th  data-field="CL_TIME" data-sortable='true'>处理时长</th>
                            <th  data-field="SATIS" data-sortable='true'>投诉处理满意度</th>
                            <th  data-field="NQUESTION" data-sortable='true'>投诉问题是否解决</th>
                            <th  data-field="NTIME" data-sortable='true'>营业厅受理投诉服务满意度（只涉及营业厅投诉）</th>
                            
                        </tr>
                        
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>