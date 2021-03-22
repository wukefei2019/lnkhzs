<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link rel="stylesheet"
	href="${ctx }/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet"
		type="text/css" />

	<head>
<meta charset="UTF-8"></meta>
<title></title>
<style type="text/css">
table.gridtable {
	font-size: 12px;
	border-collapse: separate;
	border-spacing: 0px 4px;
}

table.gridtable th {
	height: 30px
}

tbody td {
	font-size: 14px;
	text-align: center;
}

thead td {
	font-size: 14px;
	text-align: center;
}

.pager-container {
	display: inline-block;
}

/* .pager-container>li {
	float: left;
} */

.num-pager-select {
	height: 34px;
	border: 1px solid #ddd;
}

.fixed-table-container {
	position: relative;
	clear: both;
	/* border: 1px solid #dddddd; */
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
}

.bootstrap-table table thead tr th {
	text-align: center;
}

.midDiv {
	width: 100%;
	height: 90%;
	border: 0.5px solid rgb(221, 221, 221);
	margin: 15px 0 15px 0;
}

.midTitDiv {
	height: 40px;
	border-bottom: 1px solid #e6e6e6;
}

.chartC {
	height: 90%;
	color: #fff;
} 
</style>
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>

<!-- ******************************************************************************** -->
<script type="text/javascript">

$(window).resize(function() {
    window.location.reload();
})

var sqlNameb="";
var sqlNamea="";
var sqlNamec="";
	function getUrlParam() {
		var paramStr = "${param}";
		var params = paramStr.substring(1, paramStr.length - 1);
		var param = params.split(",");
		var url = "";
		for (var k = 0; k < param.length; k++) {
			url += param[k].trim() + "&";
		}
		return url;
	}
	

	$(document).ready(function() {
		var b = "${param.sqlNameB}";
		var a = "${param.sqlNameA}";
		sqlNameb = b;
		sqlNamea = a;
		sqlNamec = b.replace("_B.query", "_C.query");

		var store = "A";
		$("label").on("click", function() {

			if (this.innerText == "年") {
				$(".radioY").addClass("selected");
				$(".radioS").removeClass("selected");
				if (store == "B") {
					$("#calendar").val("");
				}
				store = "A";
			} else if (this.innerText == "月") {
				$(".radioY").removeClass("selected");
				$(".radioS").addClass("selected");
				if (store == "A") {
					$("#calendar").val("");
				}
				store = "B";
			}
		})
		

		initSelEvent();

		initCharts();

	});

	function getParams() {
		var cityName = "${param.area_name}";
		var time=$("#calendar").val();
		var params = "&year="+time.split("-")[0]+"&month=" + time.split("-")[1];
		//var params = "area_name=" + cityName + "&month=" + time+"&year="+time;
		return params;
	}

	function initSelEvent() {
		$("#selectPop, #selectItem").change(function() {
			initCharts();
		});
	}

	function changeDateVal() {
		initCharts();
	}

	function initCharts() {
		var urlParam = getParams();

		
		
		var myChartC = echarts.init(document.getElementById('chartC'));
		myChartC.clear();
		getPieJsonById(myChartC, urlParam); 
	}

	function getPieJsonById(myChart, param) {	
		var url = $ctx + "/trace/traceReport/comLinePie.action?sqlName=SQL_TRACE_SITUATION_CITY.query&"+ encodeURI(param);
		$.post(url).done(function(data) {
			var nameTemp = new Array();
			var valueTemp = new Array();
			var lineTemp = new Array();
			var j=0;
			for (var i = 0; i < data.length; i++) { 
				var value = data[i].rowHashMap.家宽总量;
				var name = data[i].rowHashMap.CITY;
				var line = data[i].rowHashMap.家宽占比;
				if(name=='合计')continue;
				if(name=='全省')continue;
				nameTemp[j] = name;
				valueTemp[j] = value;
				lineTemp[j] = line;
				j++;
			}
			option_pie.yAxis[1].data = lineTemp;
			option_pie.xAxis[0].data = nameTemp;
			option_pie.series[0].data = valueTemp;
			option_pie.series[1].data = lineTemp;
			myChart.setOption(option_pie);
		});
	}

	function resend(name, value) {
		openwindow($ctx + '/lnkhzs/propage/resendForm.jsp?name=' + name + "&id=" + value, '');
	}


	function changeTime() {
 		var dateFormat = '';
 		if ($(".radioY").hasClass("selected")) {
			dateFormat = 'yyyy-MM';
		} else if ($(".radioS").hasClass("selected")) { 
			dateFormat = 'yyyy-MM';
		}
		return dateFormat;
	}
	/*柱线图*/
	var option_pie = {
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
		title : {
			text : '',
			bottom : '5px',
			show : true,
 			top : '5%',
			left : '7%', 
			textStyle : {
				color : 'rgb(122, 122, 122)',
				fontFamily : 'Microsoft YaHei',
				fontSize : '14',
				color : '#333',
				fontWeight : 600
			}
		},
		legend : {
			data: ['溯源问题量', '占比'],
		},
		xAxis: [
	        {
	            type: 'category',
	           // data: ['沈阳','大连','鞍山','抚顺','本溪','丹东','锦州','营口','阜新','辽阳','铁岭','朝阳','盘锦','葫芦岛'],
/* 	           	axisTick: {
                	alignWithLabel: true
	            }, */
	            axisLabel: { //设置x轴的字
	              show:true,
	              interval:0,//使x轴横坐标全部显示
	              rotate:50,//倾斜
	              textStyle: {//x轴字体样式
	                color: "rgba(219,225,255,1)",
	                margin: 15
	              }
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            splitLine: {
	                show: false
	            }, 
	            //name: '水量',
	  /*           min: 0,
	            max: 250,
	            interval: 50,*/
	         
	        },
	        {
	            type: 'value',
	            splitLine: {
	                show: false
	            }, 
	            //name: '水量',
	            min: 0,
	            max: 100,
	            interval: 5,
	            axisLabel: {
	                formatter: '{value} %'
	            }
	         
	        }
	    ],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
		series :[ 
			{
				name : '溯源问题量',
				type : 'bar',
				label : {
			        show: true,
			        position: "top"
				}
			},
			{
				name : '占比',
				type : 'line',
				yAxisIndex: 1,
				label : {
			        show: true,
			        position: "top"
				}
			}
		]
	};

</script>
<!-- ******************************************************************************** -->
<%-- <script src="${ctx }/lnkhzs/propage/comTable.js"></script> --%>

	</head>
	<body style="width: 100%; height: 100%;background-size:100% 100%;">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<div style="width: 100%;height: 100%;">
		<div>
			<label for="inputCala" class="radioS selected" ><span>时间</span></label> 
 			<input  type="text"   name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});"   value="" style="padding-bottom: 1px;"> 
		</div>
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 分析 </span>
			</div>
			<div id="chartC" class="chartC"></div> 
		</div>


		</div>
		
	</body>
	</html>