<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
<html style="width: 100%; height: 100%;color: #000">
<head>
<meta charset="UTF-8"></meta>
<title></title>
 <style type="text/css">
  table.gridtable {
      font-size:12px;
      border-collapse:separate; 
      border-spacing:0px 4px;
 }
 table.gridtable th {
	 color:#fff;
     height:30px
 }
 
.tablebody {
  	background-color: rgba(0,186,255,0.2);
 }
 tbody td {
 	font-size:12px;
 	color:#aef6ff;
 	text-align:center;

 }
 thead td {
 	 font-size:12px;
 	 color:#fff;
 	 text-align:center;
 }
 </style>
<script type="text/javascript">

/* 	function getUrlParam(){
		var paramStr ="${param}";
		var params = paramStr.substring(1,paramStr.length-1);
	    var param = params.split(",");
	    var url = "";
	    for(var k=0;k<param.length;k++){
			url += param[k].trim()+"&";
		}
	    return url;
	} */

	function getColorParam(){
		var colorSplit ="${param.colorSplit}";
		
		return colorSplit;
	}
	
	var myChart;
	var resizeWorldMapContainer = function (){
		$("#myECharts").css("width",$(window).width()+'px');
		$("#myECharts").css("height",$(window).height()+'px');
	};

	$(document).ready(function(){
		var myChart = echarts.init(document.getElementById('myECharts'));

	        var impMap = {show: true, fontSize: '14', fontWeight: 'bold'};
			var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
			var h = getColorParam();
			var coArr = [colorArr[h], '#e9eced'];
			
			var name ="${param.name}";
			var value ="${param.value}";

			var titleArr = name;
			var dataArr="";
			var dataPieMap = {};
			var dataPieMapSpa = {};

				dataArr = value;
			
	        myChart.setOption(obj);
			
			var dataTemp =new Array();
			dataPieMap.value = dataArr;
			dataPieMap.label = impMap;
			dataTemp[0]=dataPieMap;

			optionOne.color=coArr;
			
			optionOne.series.data=dataTemp;
			optionOne.title.text=titleArr;
			optionOne.series.label.normal.formatter="";
			myChart.setOption(optionOne);
			var urlid="${param.id}";
			$("#"+urlid,window.parent.document).find("img[src='../style/image/loading_0.gif']").parent().parent().remove();
	});
	function orderclick(e) {
	}
	var optionOne = {
		title: {
	        text: '',
	        bottom: '5px',
	        show: true,
	        left: 'center',
	        textStyle: {
	        	color: 'rgb(122, 122, 122)',
	        	fontStyle: 'normal',
	        	fontWeight: 'normal',
	        	fontFamily: 'Microsoft YaHei',
	        	fontSize: 12
	        }
		},
		legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},
		grid:{
			left:'8%',
			right:'5%',
			top:'8%',
			bottom:'12%'
		},
	    series: {
	    	type:'pie',
            radius: ['49%', '68%'],
            center: ['50%', '50%'],
            avoidLabelOverlap: false,
	        label: {
                normal: {
                    show: false,
                    position: 'center',
                    formatter: " {c}" //{@[1]}
                }
	        }
	    }
	};
</script>
</head>
<body style="width: 90%; height: 90%;color: #fff;background-color:rgba(0,0,0,0); 
	/* background-image: url(../style/image/tablebg.png); */background-size:100% 100%;" class="oneDivclass">
	<div id="myECharts" style="width: 100%; height: 100%;cursor:pointer">
	</div>
</body>
</html>