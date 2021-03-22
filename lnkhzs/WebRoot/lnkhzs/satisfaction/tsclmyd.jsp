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
<title>10086投诉处理满意度统计表</title>
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/tsclmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_TSCLMYD.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/import.jsp',width=500,height=300)">导入</button>
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
                        	<th rowspan="2" data-field="MONTH" data-sortable='true'>调研月份</th>
                        	<th rowspan="2" data-field="RESP_DEPT" data-sortable='true'>地市/单位</th>
                            <th rowspan="2" data-field="SATIS" data-sortable='true'>投诉处理满意度</th>
                            <th colspan="2" >问题是否解决</th>
                            <th colspan="3" >投诉处理时长</th>
                            <th rowspan="2" data-field="SAMPLE_NUM" data-sortable='true'>样本量</th>
                        </tr>
                        <tr>
                        
                        	<th data-field="QUESTIONY" data-sortable='true'>是</th>
                            <th data-field="QUESTIONN" data-sortable='true'>否</th>
                        	
                            <th data-field="THREEAGO" data-sortable='true'>3天之内</th>
                            <th data-field="THREEFIVE" data-sortable='true'>3-5天</th>
                            <th data-field="FIVELATER" data-sortable='true'>5天以上</th>

                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>