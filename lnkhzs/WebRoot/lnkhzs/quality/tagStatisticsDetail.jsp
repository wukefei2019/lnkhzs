<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
<link href="${ctx}/lnkhzs/style/css/detStyle.css" rel="stylesheet" type="text/css" />
<html style="width: 100%; height: 100%;color: #000">
<head>
<meta charset="UTF-8"></meta>
<title></title>
 <style type="text/css">
 </style>
<script type="text/javascript">

	$(document).ready(function() {
		
			var id = this.id;
			$("#" + id).addClass("divleftS2");
			$("#" + id).siblings().removeClass("divleftS2");
			
			getLineJsonById("myECharts", ["7%", "7%"], 1, ['#87cbfb', '#ace2fe']);
		
		
		
		var colorStore =['#ace2fe', '#87cbfb'];
		
	});

	function getLineJsonById(myChartId, placeArr, id, colorArr) {
		var a12="${param.a12}";
		var city="${param.city}";
		var url = encodeURI($ctx + "/quality/tagMaintain/ajaxGetTagStatistics.action?a12=" + a12+"&city="+city);
		$.post(url).done(function(valueTemp) {
			
			var myChart = echarts.init(document.getElementById(myChartId));
			myChart.clear();
			
			var nameTemp = new Array();
			for (var i = 0; i < 12; i++) {
				nameTemp[i] = i + 1 + "月";
			}
			optionTop.yAxis.name = '投诉量';
			optionTop.xAxis.data = nameTemp;
			optionTop.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
			optionTop.series[0].color = colorArr[1];
			optionTop.grid.left = placeArr[0];
			optionTop.grid.right = placeArr[1];
			optionTop.aria.description=id;
			optionTop.series[0].data = valueTemp;
			myChart.setOption(optionTop);
			$("#" + myChartId).attr('onclick','').unbind('click');
			var myDate = new Date();
			$("#" + myChartId).on('click', function (param){  
		        var mon = param.currentTarget.innerText.split(":")[0].replace("月","");
		        mon = parseInt(mon)<10?0+mon:mon;
		        var time = myDate.getFullYear() + mon;
	            var childUrl = $ctx + "/lnkhzs/quality/tagStatisticsDetailList.jsp?a12=" + a12+"&city="+city+"&time="+time;
	    		openwindow2(childUrl,'',1200, 600);
	        });
		});
	}

	var optionTop = {
		legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},
		grid:{
			/* left:'5%',
			right:'5%', */
			top:'18%',
			bottom:'15%'
		},
	    xAxis: {
	        type: 'category',
	        boundaryGap: false
	    },
	    yAxis: {
	        type: 'value',
	        splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgb(255,255,255)','rgba(253,253,253)']
                }
            }
	    },
	    tooltip: {
	        trigger: 'item',           // 触发类型，默认数据触发，见下图，可选为：'item' ¦ 'axis'
	        showDelay: 20,             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
	        hideDelay: 100,            // 隐藏延迟，单位ms
	        transitionDuration : 0.4,  // 动画变换时间，单位s
	        backgroundColor: 'rgba(0,0,0,0.7)',     // 提示背景颜色，默认为透明度为0.7的黑色
	        borderColor: '#333',       // 提示边框颜色
	        borderRadius: 4,           // 提示边框圆角，单位px，默认为4
	        borderWidth: 0,            // 提示边框线宽，单位px，默认为0（无边框）
	        padding: 5,                // 提示内边距，单位px，默认各方向内边距为5，
	                                   // 接受数组分别设定上右下左边距，同css
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'line',         // 默认为直线，可选为：'line' | 'shadow'
	            lineStyle : {          // 直线指示器样式设置
	                color: '#48b',
	                width: 2,
	                type: 'solid'
	            },
	            shadowStyle : {                       // 阴影指示器样式设置
	                width: 'auto',                   // 阴影大小
	                color: 'rgba(150,150,150,0.3)'  // 阴影颜色
	            }
	        },
	        textStyle: {
	            color: '#fff'
	        }
	    },
	    series: [{
	        type: 'line',
	        smooth: true,
	        areaStyle: {
		        color: {
				    type: 'linear',
				    x: 0,
				    y: 0,
				    x2: 0,
				    y2: 1,
				    colorStops: [{
				        offset: 0, color: '#87cbfb' // 0% 处的颜色
				    }, {
				        offset: 1, color: '#eee' // 100% 处的颜色
				    }],
				    global: false // 缺省为 false
				}
	        },
	        color: '#87cbfb'
	    }],
	    aria: {
	        show: false
	    }
	};
	function openwindow2(url,name,iWidth,iHeight)
	{
		var resurl = encodeURI(url); 
		var name=null;
		name = name || new Date().getTime();
		
		var w = $(window).width()-160;
		var h = $(window).height()-81;
		
		iWidth = iWidth || w; 
		iHeight = iHeight || h; 
		var iTop = (window.screen.availHeight-30-iHeight)/2; 
		var iLeft = (window.screen.availWidth-10-iWidth)/2; 
		return window.open(resurl,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
	}
</script>
</head>
<body style="width: 90%; height: 90%;color: #fff;background-color: rgb(246, 246, 246); background-size:100% 100%; margin: 0 auto;" class="oneDivclass">

		<div class="divright"> 
			<div id="myECharts" style="width: 100%; height: 100%;cursor:pointer">
				
			</div>
		</div>
	
</body>
</html>