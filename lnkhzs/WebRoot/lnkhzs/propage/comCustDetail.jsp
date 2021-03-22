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
	
	function getParentId(){
		var parentId ="${param.parentId}";
		
		return parentId;
	}
	
	$(document).ready(function() {
		var colorStore = new Array();
		colorStore[0] = ['#ace2fe', '#87cbfb'];
		colorStore[1] = ['#88e9e3', '#39e5db'];
		colorStore[2] = ['#f4d2ff', '#c6b6fd'];
		colorStore[3] = ['#feff9f', '#dbe588'];
		
		var url = $ctx + "/tsgd/tsgd/ajaxGetLineJsonByParentId.action?id=" + getParentId();
		$.post(url).done(function(data) {
			var tree = data.treeList;
			
			for (var k=0;k<tree.length;k++) {
				var id = tree[k].id;
				var name = tree[k].name.replace("投诉量", "");
				var ht = "<div class='sedivMidd'>"
					 + "    <span class='sedivMidSpan sespan" + k%6  + "'>"
					 +        name
					 + "    </span>"
					 + "    <div id='myEChart" + k + "' style='height: calc(100% - 40px); width: 100%;cursor:pointer'>"
					 + "    </div>"
					 + "</div>";
				$(".sediv1").append(ht);
			}
		
			for (var i=0;i<tree.length;i++) {
				var id = tree[i].id;
				var indSql = tree[i].indicatorsql;
				var myChart = echarts.init(document.getElementById('myEChart' + i));
				myChart.clear();
				getLineJsonById(myChart, ["12%", "12%"], id, colorStore[i%4], 'myEChart' + i, indSql);
			}
		});
	});

	function getLineJsonById(myChart, placeArr, id, colorArr, chartId, indSql) {
		var url = encodeURI($ctx + "/tsgd/tsgd/ajaxGetLineJson.action?id=" + id+"&year="+year+"&area_name="+area_name);
		$.post(url).done(function(dataArr) {
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
			optionTop.aria.description=indSql;
			optionTop.series[0].data = valueTemp;
			myChart.setOption(optionTop);
			
			$("#" + chartId).on('click', function (param){  
		        var sqlname = myChart.getOption().aria.description;
	            	
	            var childUrl = $ctx + "/tsgd/tsgd/comTable.action?sqlNameB="+ sqlname.split(".")[0] + "_CLD_B." + sqlname.split(".")[1] 
	            	+ "&sqlNameA="+ sqlname.split(".")[0] + "_CLD_A." + sqlname.split(".")[1] 
	            	+ "&sqlName=" + sqlname.split(".")[0] + "_CLD." + sqlname.split(".")[1]
	            	+"&area_name="+"${param.area_name}"+"&year="+"${param.year}"; 
	            
//		        var childUrl = $ctx + "/tsgd/tsgd/comTable.action?sqlName=" + sqlname.split(".")[0] + "_CLD." + sqlname.split(".")[1]; 
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
	    tooltip : {
	        /*trigger: 'axis' */
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

	<div class="sediv1"> 
		<!-- <div class="sedivMidd"> 
			<span class="sedivMidSpan sespan1"> 移动业务 </span>
			<div id="myEChart0" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
		</div>

		<div class="sedivMidd"> 
			<span class="sedivMidSpan sespan2"> 家庭业务 </span>
			<div id="myEChart1" style="height: calc(100% - 40px); width: 100%;cursor:pointer">
			</div>
		</div> -->
	</div>
</body>
</html>