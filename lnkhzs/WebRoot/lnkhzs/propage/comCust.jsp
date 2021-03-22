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
	var area_name="${param.area_name}";
	var year="${param.year}";
	$(document).ready(function() {
		$(".divleftS1").on('click',function(e) {
			var id = this.id;
			$("#" + id).addClass("divleftS2");
			$("#" + id).siblings().removeClass("divleftS2");
			
			getLineJsonById("myECharts", ["7%", "7%"], id.replace("left", ""), ['#87cbfb', '#ace2fe']);
		});
		
		$("#left51").click();
		
		var colorStore = new Array();
		colorStore[0] = ['#ace2fe', '#87cbfb'];
		colorStore[1] = ['#88e9e3', '#39e5db'];
		colorStore[2] = ['#f4d2ff', '#c6b6fd'];
		colorStore[3] = ['#feff9f', '#dbe588'];
		for (var i=0;i<4;i++) {
			getLineJsonById("myEChart" + i, ["12%", "12%"], 1 + i, colorStore[i]);
		}
	});

	function getLineJsonById(myChartId, placeArr, id, colorArr) {
	
		var url = encodeURI( $ctx + "/tsgd/tsgd/ajaxGetLineJson.action?id=" + id+"&year="+year+"&area_name="+area_name);
		$.post(url).done(function(dataArr) {
			
			var myChart = echarts.init(document.getElementById(myChartId));
			myChart.clear();
		
			var data = dataArr.value;
			var chartInfo = dataArr.indTree;
			
			var nameTemp = new Array();
			var valueTemp = new Array();
			for (var i = 0; i < data.length; i++) {
				var value = data[i].value;
				var name = data[i].name;
				nameTemp[i] = name.split("-")[1] + "月";
				valueTemp[i] = value;
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
			
			$("#" + myChartId).on('click', function (param){  
		        var parid = myChart.getOption().aria.description;
	            var childUrl = $ctx + "/lnkhzs/propage/comCustDetail.jsp?parentId=" + parid+"&year="+year+"&area_name="+area_name;
	            
				if (id!=51 && id!=53 && id!=54 && id!=55 && id!=4) {
					openwindow2(childUrl,'',1200, 600);
				}
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
	    tooltip : {
	        /*trigger: 'axis' */
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
	    series: [{
	            label: {
                normal: {
                    show: true,
                }
            },
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

	<div class="dediv1"> 
		<div class="divleft"> 
		
			<div class="divleftS1" id="left51"> 
				<span class="fontStyle">红线-服务触点</span>
			</div>
			<div class="divleftS1" id="left52"> 
				<span class="fontStyle">红线-不知情定制</span>
			</div>
			<div class="divleftS1" id="left53"> 
				<span class="fontStyle">红线-限制客户更改套餐</span>
			</div>
			<div class="divleftS1" id="left54"> 
				<span class="fontStyle">红线-无法停机销户</span>
			</div>
			<div class="divleftS1" id="left55" style="border-bottom: 0;"> 
				<span class="fontStyle">红线-携号转网</span>
			</div>
		</div>
		<div class="divright"> 
			<div id="myECharts" style="width: 100%; height: 100%;cursor:pointer">
				
			</div>
		</div>
	</div>
	<div class="dediv2"> 
		<div class="divMidd"> 
			<span class="divMidSpan span1"> 移动业务 </span>
			<div id="myEChart0" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
		</div>
		<div class="divMidSpace"></div>
		<div class="divMidd"> 
			<span class="divMidSpan span2"> 家庭业务 </span>
			<div id="myEChart1" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
			
		</div>
		<div class="divMidd"> 
			<span class="divMidSpan span3"> 增值业务 </span>
			<div id="myEChart2" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
			
		</div>
		<div class="divMidSpace"></div>
		<div class="divMidd"> 
			<span class="divMidSpan span4"> 集团业务 </span>
			<div id="myEChart3" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
			
		</div>
	</div>
</body>
</html>