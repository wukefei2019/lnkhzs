
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/comStyle.css" rel="stylesheet" type="text/css" />

<script type='text/javascript'>
	$(document).ready(function() {
		var store = "A";
		//alert(year+'年'+month+'月')
		var date = new Date().Format("yyyy-MM");
		$("#calendar").val(date);
	});
</script>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
	<title>服务质量标准报表</title> 
	<script type="text/javascript" src="${ctx }/lnkhzs/standards/comAnaly3.js"></script>
	<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
</head>
<body class="bodyArea">
	<div class="titleArea">
		<div>
			<span class="titleSpan"> 总体服务质量展示首页 </span>
			<span class="titleUsername"> 用户名 </span>
		</div>
	</div>
	<div class="bodyDiv"> 
		<div class="divsele"> 
			<select id='selectItem' class="seleclass">
				<option value ="全省" style="background-color:rgb(255, 255, 255);">全省</option>
				<option value ="中国移动（沈阳）虎石台数据中心" style="background-color:rgb(255, 255, 255);">中国移动（沈阳）虎石台数据中心</option>
			    <option value ="中国移动（辽宁沈阳）数据中心 " style="background-color:rgb(255, 255, 255);">中国移动（辽宁沈阳）数据中心</option>
			    <option value ="中国移动（沈阳）南十数据中心" style="background-color:rgb(255, 255, 255)">中国移动（沈阳）南十数据中心</option>
			    <option value ="中国移动（大连）甘井子数据中心" style="background-color:rgb(255, 255, 255)">中国移动（大连）甘井子数据中心</option>
			    <option value ="中国移动（大连）风景街数据中心" style="background-color:rgb(255, 255, 255)">中国移动（大连）风景街数据中心</option>
			    <option value ="中国移动（本溪）石桥子数据中心" style="background-color:rgb(255, 255, 255)">中国移动（本溪）石桥子数据中心</option>
			    <option value ="中国移动（抚顺）沈抚数据中心" style="background-color:rgb(255, 255, 255)">中国移动（抚顺）沈抚数据中心</option>
			    <option value ="中国移动（丹东）临港数据中心" style="background-color:rgb(255, 255, 255)">中国移动（丹东）临港数据中心</option>
			    <option value ="中国移动（铁岭）柴河数据中心" style="background-color:rgb(255, 255, 255)">中国移动（铁岭）柴河数据中心</option>
			    <option value ="中国移动（辽阳）南环数据中心" style="background-color:rgb(255, 255, 255)">中国移动（辽阳）南环数据中心</option>
			    <option value ="中国移动（鞍山）铁西数据中心" style="background-color:rgb(255, 255, 255)">中国移动（鞍山）铁西数据中心</option>
			    <option value ="中国移动（锦州）太和数据中心" style="background-color:rgb(255, 255, 255)">中国移动（锦州）太和数据中心</option>
			    <option value ="中国移动（阜新）矿工大街数据中心" style="background-color:rgb(255, 255, 255)">中国移动（阜新）矿工大街数据中心</option>
			    <option value ="中国移动（朝阳）朝阳县数据中心" style="background-color:rgb(255, 255, 255)">中国移动（朝阳）朝阳县数据中心</option>

			</select>
		
			<label for="inputCala" class="radioY"><span>年</span></label>
			<label for="inputCala" class="radioM selected"><span>月</span></label>
			<input type="text" name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});" value="" style="padding-bottom: 1px;">
		</div>
		<div class="midDiv1">
			<div class="midTitDiv">
				<span class="midSpan"> 重点指标情况 </span>
			</div>
			<div id="chartStore" style="width: calc(100% - 100px);  height: 80%; color: #fff;float: left; margin-left: 50px; margin-right: 50px;">
			</div>
		</div>
		
	</div>
</body>
</html>