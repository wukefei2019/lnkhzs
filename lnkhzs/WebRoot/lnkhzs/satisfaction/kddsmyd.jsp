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
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/kddsmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_KDDSMYD.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/import_kddsmyd.jsp',width=500,height=300)">导入</button>
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
                        	<th rowspan="2" data-field="RESP_DEPT" data-sortable='true'>地市</th>
                            <th rowspan="2" data-field="SATIS" data-sortable='true'>宽带电视满意度</th>
                            <th rowspan="2" data-field="TOTALSATIS" data-sortable='true'>整体满意度</th>
                            <th colspan="3" >终端感知质量</th>
                            <th colspan="3" >内容体验</th>
                            <th colspan="3" >使用体验</th>
                            <th colspan="3" >业务办理退订体验</th>
                            <th >售后服务</th>
                            
                        </tr>
                        <tr>
                        	
                            <th data-field="TPQ_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="TPQ_BOX" data-sortable='true'>盒子感知质量</th>
                            <th data-field="TPQ_RCONTROL" data-sortable='true'>遥控器感知质量</th>
                            
                            <th data-field="CE_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="CE_RICH" data-sortable='true'>视频及电视频道资源丰富度</th>
                            <th data-field="CE_SPEED" data-sortable='true'>视频资源更新速度</th>
                            
                            <th data-field="UE_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="UE_SMOOTH" data-sortable='true'>视频流畅度</th>
                            <th data-field="UE_OPER" data-sortable='true'>操作界面</th>
                            
                            <th data-field="BHUE_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="BHUE_HANDLE" data-sortable='true'>业务办理</th>
                            <th data-field="BHUE_CANCEL" data-sortable='true'>业务退订</th>
                            
                            <th data-field="AFTERSALESERVICE" data-sortable='true'>故障维修服务</th>
                            

                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>