
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>

<link href="${ctx}/lnkhzs/style/css/comStyle.css" rel="stylesheet" type="text/css" />
<style>
body{
	overflow-y:auto;
}
</style>
<script type='text/javascript'>
	$(document).ready(function() {
	var store = "A";
	//alert(year+'年'+month+'月')
	var date = new Date().Format("yyyy-MM");
	$("#calendar").val(date);
		$(".btn-link.icon-roundadd").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/khzs/addKhzs.jsp?type=典型投诉案例', '');
		});
	});
	
	
</script>
<html style="height: 100%;;overflow:auto">
<head>
<meta charset="UTF-8">
	<title>总体服务质量展示首页</title> 
	<script type="text/javascript" src="${ctx }/lnkhzs/propage/comAnaly.js"></script>
	<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
</head>
<body class="bodyArea" style="height: 100%">
	<div class="titleArea">
		<div>
			<span class="titleSpan"> 总体服务质量展示首页 </span>
			<span class="titleUsername"> 用户名 </span>
		</div>
	</div>
	<div class="bodyDiv"> 
		<div class="divsele" style="margin:5px";> 
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
	
			<label for="inputCala"  class="radioY "><span>年</span></label>
			<label for="inputCala" class="radioM selected" ><span>月</span></label>
			<!-- <label for="inputCala" class="radioR " ><span>日</span></label> -->
				<input  type="text"   name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});"  value="" style="padding-bottom: 1px;">	   
		</div>

		
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 重点指标情况 </span>
			</div>
			<div id="chartStore" style="width: calc(100% - 100px);  height: 80%; color: #fff;float: left; margin-left: 50px; margin-right: 50px;">
			</div>
		</div>
		<div class="tableDiv">
			<div class="underTitDiv" style="">
				<span class="underSpan"> 问题跟踪解决情况监控 </span>
				<div class="downloadClass" onclick="">
				</div>
				<select id='selectTable' class="seleTable" style="visibility:hidden">
					<option value ="142843050" style="background-color:rgb(255, 255, 255);" >市场部</option>
				    <option value ="142843052" style="background-color:rgb(255, 255, 255);">网络部</option>
				    <option value ="125057534" style="background-color:rgb(255, 255, 255)">财务部</option>
				</select>
			</div>
			<dg:ajaxgrid id="table0" sqlname=""><!-- SQL_KHZS.query -->
				<%-- <dg:lefttoolbar>
					<button
						class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
						onclick="$.bs.table.refresh('table0')">刷新</button>
					<button class="btn-link iconfont fontsize14 icon-roundadd">新增</button>
				</dg:lefttoolbar> --%>
				<%-- <dg:condition>
					<input type="text" name="LOGINNAME" value="${userid}" />
					<input type="hidden" name="TYPE" value="典型投诉案例" />
					<div class="btn_box overflow">
						<div
							class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
						<div
							class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
					</div>
				</dg:condition> --%>
				<dg:ajax-table>
					<!--  data-show-search="true" -->
					<!-- <table data-height='auto' data-fixed-columns='false' data-on-load-success="table_load_success" 
						data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'> -->
						<table data-height='auto' data-fixed-columns='false' 
						data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
						<thead>
							<tr>
								<!-- <th data-field="IDX" data-formatter="$.bs.table.fmt.link"
									data-events="fn_evnt_name_look">序号</th> -->
								<th data-field="CREATETIME">责任部门</th>
								<th data-field="QUESTIONTYPE">主要问题</th>
								<th data-field="DESCRIPTION">衡量指标</th>
								<th data-field="SOURCE">创建部门</th>
								<th data-field="SOLUTION">1月</th>
								<th data-field="QUESTION">2月</th>
								<th data-field="FLOWSTATUS">3月</th>
								<th data-field="FULLNAME">4月</th>
								<th data-field="FLOWSTATUS">5月</th>
								<th data-field="FULLNAME">6月</th>
								<th data-field="FULLNAME">7月</th>
								<th data-field="FULLNAME">8月</th>
								<th data-field="FULLNAME">9月</th>
								<th data-field="FULLNAME">10月</th>
								<th data-field="FULLNAME">11月</th>
								<th data-field="FULLNAME">12月</th>
								<th data-formatter="fmt_operate" data-report-col-mode="none"
									data-events="fn_operate_events">操作</th>
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