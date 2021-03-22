
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
<style>
body{
	overflow-y:auto;
}
</style>
<script type='text/javascript'>
	$(document).ready(function() {
	var store = "A";
	var date = new Date().Format("yyyy-MM");
	//alert(year+'年'+month+'月')
	$("#calendar").val(date);
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/khzs/addKhzs.jsp?type=典型投诉案例', '');
		});
	});
	
	
</script>
<html style="height: 100%;overflow:auto">
<head>
	<meta charset="UTF-8">
	<title>投诉分析展示</title> 
	<script type="text/javascript" src="${ctx }/lnkhzs/propage/popService.js"></script>
	<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
</head>
<body class="bodyArea" style="height: 100%">
	<div class="titleArea">
		<div>
			<span class="titleSpan"> 投诉分析展示 </span>
			<span class="titleUsername"> 用户名 </span>
		</div>
	</div>
	<div class="bodyDiv"> 
		<div class="divsele"> 
			<!--<select id='selectDepart' class="seleclass" style="visibility:hidden"; >
				<option value ="142843050" style="background-color:rgb(255, 255, 255);">市场部</option>
			    <option value ="142843052" style="background-color:rgb(255, 255, 255);">网络部</option>
			    <option value ="125057534" style="background-color:rgb(255, 255, 255)">财务部</option>
			</select>-->
			
			<select id='selectPop' class="seleclass" style="width: 160px;margin-left:15px";>
			</select>
			
			<select id='selectItem' class="seleclass">
				<option value ="" style="background-color:rgb(255, 255, 255);">全省</option>
			    <option value ="沈阳市" style="background-color:rgb(255, 255, 255);">沈阳市</option>
			    <option value ="大连市" style="background-color:rgb(255, 255, 255)">大连市</option>
			    <option value ="鞍山市" style="background-color:rgb(255, 255, 255)">鞍山市</option>
			    <option value ="抚顺市" style="background-color:rgb(255, 255, 255)">抚顺市</option>
			    <option value ="本溪市" style="background-color:rgb(255, 255, 255)">本溪市</option>
			    <option value ="丹东市" style="background-color:rgb(255, 255, 255)">丹东市</option>
			    <option value ="锦州市" style="background-color:rgb(255, 255, 255)">锦州市</option>
			    <option value ="营口市" style="background-color:rgb(255, 255, 255)">营口市</option>
			    <option value ="阜新市" style="background-color:rgb(255, 255, 255)">阜新市</option>
			    <option value ="辽阳市" style="background-color:rgb(255, 255, 255)">辽阳市</option>
			    <option value ="盘锦市" style="background-color:rgb(255, 255, 255)">盘锦市</option>
			    <option value ="铁岭市" style="background-color:rgb(255, 255, 255)">铁岭市</option>
			    <option value ="朝阳市" style="background-color:rgb(255, 255, 255)">朝阳市</option>
			    <option value ="葫芦岛市" style="background-color:rgb(255, 255, 255)">葫芦岛市</option>
			</select>
			<label for="inputCala" class="radioY "><span>年</span></label>
			<label for="inputCala" class="radioM selected"><span>月</span></label>
			<input type="text" name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});" value="" style="padding-bottom: 1px;">
		</div>
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 基础投诉分析 </span>
			</div>
			<div style="width: 100%;margin-bottom: -15px;"><span id="showFullTitle" class="midSpan" style="background: none;font-weight: 100;font-size: 15px;color: cadetblue;display: none;">全量名称 </span></div>
			<div id="chartA" class="chartA">
			</div>
			<div id="tableA" class="tableA">
				<table class="tableMid">
					<!-- <tr class="tr1">
						<td style="width:45%">焦点问题名称</td>
						<td style="width:20%">数量</td>
						<td >操作</td>
					</tr> -->
					<!-- <tr class="trOther">
						<td>网络不通</td>
						<td>2145</td>
						<td>派发</td>
					</tr>
					<tr class="trOther" style="background:rgb(253, 253, 253)">
						<td>无信号</td>
						<td>962</td>
						<td>派发</td>
					</tr>
					<tr class="trOther">
						<td>网络慢</td>
						<td>354</td>
						<td>派发</td>
					</tr> -->
				</table>
			</div>
			<div id="chartB" class="chartB">
			</div>
		</div>
		<div class="tableDiv">
			<div class="underTitDiv" style="">
				<span class="underSpan"> 投诉详单 </span>
				<div class="downloadClass" onclick="">
				</div>
				<div class="selectClass" onclick="">
				</div>
				<input id='selectTable' type="text" class="inputTable"></input>
				<label class="checkBoxClass">
					<input type="checkbox" name="voice" id="voice" class="boxClass" value="0"/>&nbsp;&nbsp;&nbsp;&nbsp;有录音投诉
				</label>
			</div>
			<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_ts.query"><!-- SQL_KHZS.query -->
				<dg:condition>
              		<!-- <input type="hidden" name="srvreqsttypefullnm" id="srvreqsttypefullnm"/>
					<input type="hidden" name="a12" id="a12"/>
					<input type="hidden" name="area_name" id="area_name"/>
					<input type="hidden" name="statis_date" id="statis_date"/>
					<input type="hidden" name="srvreqsttypefullnm" id="srvreqsttypefullnm"/> -->
					<input type="hidden" name="name" id="a12"/>
					<input type="hidden" name="area_name" id="area_name"/>
					<input type="hidden" name="statis_date" id="statis_date"/>
					
            	</dg:condition>
				<dg:ajax-table>
					<!--  data-show-search="true" -->
					
					<table data-height='auto' data-fixed-columns='false' 
						data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
						<thead>
							<tr>
								<!-- <th data-field="IDX" data-formatter="$.bs.table.fmt.link"
									data-events="fn_evnt_name_look">序号</th> -->
								<!--  <th data-field="GDLS">工单流水号</th>有 -->
								 <th data-field="GDLS" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false" data-sortable='true'>工单流水号</th>
								<th data-field="AREA_NAME" data-sortable='true'>客户归属</th><!-- 有 -->
								<th data-field="NAME" data-sortable='true'>投诉节点类型</th><!-- 有 -->
								<th data-field="BUSINESS_ATTRIBUTION" data-sortable='true'>责任归属部门</th><!-- 有 -->
								 <th data-field="NNAME" data-sortable='true'>受理渠道</th>
								<th data-field="STARGRO" data-sortable='true'>用户星级</th>
								<th data-field="GROUPNAME" data-sortable='true'>首处理工作组</th>
								<th data-field="TOTALTIME" data-sortable='true'>处理时长</th>
								<th data-field="SATISDGR" data-sortable='true'>客户回访满意度</th>
								<th data-field="GROUPNAME2" data-sortable='true'>协办工作组</th> 
								<th data-visible="false" data-field="TABLENAME" data-sortable='true'>表名</th>
								<th data-visible="false" data-field="SRV_REQST_ID" data-sortable='true'>服务请求流水</th>
							</tr>
						</thead>
					</table>
				</dg:ajax-table>
			</dg:ajaxgrid>
			<iframe name="download" src="" style="display: none"></iframe>
		</div>
	</div>
</body>

</html>