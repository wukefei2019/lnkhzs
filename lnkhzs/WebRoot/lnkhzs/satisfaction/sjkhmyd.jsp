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
<script type="text/javascript" src="${ctx }/lnkhzs/satisfaction/sjkhmyd.js"></script>			
</head>
<body>
	<div class="Ct Dialog">
		<dg:ajaxgrid id="table0" sqlname="SQL_TS_SJKHMYD.query">
			<dg:lefttoolbar>
				<button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		 		<button class="btn-link iconfont fontsize14 icon-upload1" onclick="$.bs.modal.open('${ctx}/lnkhzs/satisfaction/import_sjkhmyd.jsp',width=500,height=300)">导入</button>
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
                            <th rowspan="2" data-field="SATIS" data-sortable='true'>手机客户满意度</th>
                            <th rowspan="2" data-field="TOTALSATIS" data-sortable='true'>整体满意度</th>
                            <th colspan="6" >资费套餐</th>
                            <th colspan="3" >网络质量</th>
                            <th rowspan="2" data-field="BUSINESSPUBLICITY" data-sortable='true'>业务宣传</th>
                            <th rowspan="2" data-field="REMINDSERVICE" data-sortable='true'>提醒服务</th>
                        </tr>
                        <tr>
                        	
                            <th data-field="TG_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="TG_CLEAR" data-sortable='true'>资费规则清晰度</th>
                            <th data-field="TG_CONVENIENT" data-sortable='true'>套餐办理便捷性</th>
                            <th data-field="TG_STANDARD" data-sortable='true'>套餐办理规范性</th>
                            <th data-field="TG_FIT" data-sortable='true'>套餐内容适配度</th>
                            <th data-field="TG_SERVICE" data-sortable='true'>账单服务</th>
                            
                            <th data-field="NQ_TOTAL" data-sortable='true'>整体</th>
                            <th data-field="NQ_NET" data-sortable='true'>手机上网质量</th>
                            <th data-field="NQ_TEL" data-sortable='true'>语音通话质量</th>
                            
                            

                        </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>