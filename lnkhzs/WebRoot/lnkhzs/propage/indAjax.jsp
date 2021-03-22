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

	function getUrlParam(){
		var paramStr ="${param}";
		var params = paramStr.substring(1,paramStr.length-1);
	    var param = params.split(",");
	    var url = "";
	    for(var k=0;k<param.length;k++){
			url += param[k].trim()+"&";
		}
	    return url;
	}
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
		/* myChart.showLoading({  // 此处需要传入一个对象
            text:'加载中',
            color:'#3385ff',
  			zlevel: 0
        	}); */
		var url = $ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=C&"+encodeURI(getUrlParam());
		$.post(url).done(function(data) {
//			var obj = eval('(' + data.json + ')');
	        var impMap = {show: true, fontSize: '14', fontWeight: 'bold'};
			var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
			var h = getColorParam();
			var coArr = [colorArr[h], '#e9eced'];
			
	        var sqlname = data.sqlname;
			var value = data.value;
			var name = data.name;
			var id = data.id;
			var childid = data.childid;
			var type = data.type;
			var titleArr = name;
			var dataArr="";
			var dataArrAll="";
			var dataPieMap = {};
			var dataPieMapSpa = {};
			
			if (type == "率") {
				if (value == "" || value == 0) {
					value = 1;
				}
			}
			
			if (type == "量") {
				dataArr = value;
				dataArrAll = 0;
				if (value == 0) {
					dataArr=1;
					dataArrAll=0;
				}
			} else if (type == "率" && value<=100 && value>=0) {
				dataArr = value;
				dataArrAll = 0;
			} else {
				dataArr = value;
				dataArrAll = 0;
			}
			
//			resizeWorldMapContainer();
	        myChart.setOption(obj);
			
			var dataTemp =new Array();
			dataPieMap.value = dataArr;
			dataPieMap.label = impMap;
			dataPieMapSpa.value = dataArrAll;
			dataTemp[0]=dataPieMap;
			dataTemp[1]=dataPieMapSpa;

			optionOne.color=coArr;
			
			optionOne.series.data=dataTemp;
			optionOne.title.text=titleArr;
			if ("客户投诉量" == titleArr) {
				optionOne.series.label.normal.formatter=" {c} \n ———— \n {d}% ";
			} else {
				optionOne.series.label.normal.formatter=" {c}";
			}
			if (type == "量") {
				if (value == 0) {
					dataArr=1;
					dataArrAll=0;
					optionOne.series.label.normal.formatter=" 0";
				}
			} else if (type == "率") {
				if (data.value == "" || data.value == 0) {
					optionOne.series.label.normal.formatter=" 0%";
				} else {
					optionOne.series.label.normal.formatter=" {c}%";
				} 
			} else if (type == "比") {
				if (data.value == "" || data.value == 0) {
					optionOne.series.label.normal.formatter=" 0";
				} else {
					optionOne.series.label.normal.formatter=" {c}";
				} 
			}
			
			myChart.setOption(optionOne);
			/* myChart.hideLoading(); */
			
			
			var urlid="${param.id}";
			$("#"+urlid,window.parent.document).find("img[src='../style/image/loading_0.gif']").parent().parent().remove();
	      
		});
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