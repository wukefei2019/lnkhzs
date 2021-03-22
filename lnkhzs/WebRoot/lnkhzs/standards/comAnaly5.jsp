
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
	<script type="text/javascript" src="${ctx }/lnkhzs/standards/comAnaly5.js"></script>
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
				<option value ="辽宁省中心" style="background-color:rgb(255, 255, 255);">辽宁省中心</option>
			    <option value ="沈阳" style="background-color:rgb(255, 255, 255);">沈阳</option>
			    <option value ="大连" style="background-color:rgb(255, 255, 255)">大连</option>
			    <option value ="鞍山" style="background-color:rgb(255, 255, 255)">鞍山</option>
			    <option value ="抚顺" style="background-color:rgb(255, 255, 255)">抚顺</option>
			    <option value ="本溪" style="background-color:rgb(255, 255, 255)">本溪</option>
			    <option value ="丹东" style="background-color:rgb(255, 255, 255)">丹东</option>
			    <option value ="锦州" style="background-color:rgb(255, 255, 255)">锦州</option>
			    <option value ="营口" style="background-color:rgb(255, 255, 255)">营口</option>
			    <option value ="阜新" style="background-color:rgb(255, 255, 255)">阜新</option>
			    <option value ="辽阳" style="background-color:rgb(255, 255, 255)">辽阳</option>
			    <option value ="盘锦" style="background-color:rgb(255, 255, 255)">盘锦</option>
			    <option value ="铁岭" style="background-color:rgb(255, 255, 255)">铁岭</option>
			    <option value ="朝阳" style="background-color:rgb(255, 255, 255)">朝阳</option>
			    <option value ="葫芦岛" style="background-color:rgb(255, 255, 255)">葫芦岛</option>
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