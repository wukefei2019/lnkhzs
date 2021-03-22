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
		var url = $ctx + "/trace/traceReport/comLinePie.action?sqlName=SQL_TRACE_STATISTICS_MONTH2.query&"+ encodeURI(param);
		$.post(url).done(function(data) {
			var impMap = {
				show : true,
				fontSize : '14',
				fontWeight : 'bold'
			};
			var coArr = [ '#6cbdf7', '#fdb275', '#f1868d', '#3fcdcf' ];

			var dataTemp = new Array();
			var dataLable = new Array();
			var num = data.length > 10 ? 10 : data.length;
/* 			var ht = "<tr class='tr1'>"
				+ "<td style='width:45%'>焦点问题名称</td>"
				+ "<td style='width:20%'>数量</td>"
				+ "<td>操作</td>"
				+ "</tr>";
			if (num < 1) {
				ht = ht + "<tr class='trOther' style='background:rgb(253, 253, 253)'> <td></td><td></td><td></td></tr>";
			} */
			for (var i = 0; i < num; i++) {
				//var value = data[i].rowHashMap.VALUE; // + "%"
				//var name = data[i].rowHashMap.NAME.replace("→全局流转", "");
				
				var value = data[i].rowHashMap.NUM; // + "%"
				var preName = data[i].rowHashMap.TYPE;
				var a = preName.lastIndexOf("→");
				//var name = preName.substr(preName.lastIndexOf("→",a-1) + 1, preName.lenght).replace("→全局流转", "");
				var dataPieMap = {};
				var lablen = name.length > 10 ? 10 : name.length;
				name.substring(0, lablen);
				
				dataPieMap.value = value;
				dataPieMap.name = preName;
				//dataPieMap.name = name;
				dataPieMap.preName = preName;
				dataTemp[i] = dataPieMap;
				dataLable[i] = preName;
				
			}
			option_pie.color = coArr;
			option_pie.series.data = dataTemp;
			//option_pie.title.text = "溯源占比图";
			option_pie.series.name = "溯源占比图";
			option_pie.legend.data = dataLable;
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
	/*饼图*/
	var option_pie = {
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
			/* formatter : function(name) {
				var a = name.lastIndexOf("→");
				name = name.substr(name.lastIndexOf("→",a-1) + 1, name.lenght).replace("→全局流转", "");
				var lablen = name.length > 10 ? 10 : name.length;
				var labname = name.substring(0, lablen);
				return labname;
			}, */
			textStyle : {
				color : '#000'
			},
			top : 'bottom',
			left : 'center',
			itemHeight : 9,
			itemWidth : 26,
			icon : 'rect'
		},
		grid : {
			left : '8%',
			right : '5%',
			top : '8%',
			bottom : '12%'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c} ({d}%)"
		},
		series : {
			type : 'pie',
			radius : [ '39%', '65%' ],
			center : [ '50%', '40%' ],
			avoidLabelOverlap : false,
			label : {
				emphasis : {
					show : true,
					textStyle : {
						fontSize : '15',
						fontWeight : 'bold'
					},
					formatter : "{c} \n ———— \n {d}%"
				}
			},
			labelLine : {
				normal : {
					show : false
				}
			},
			itemStyle:{ 
		        normal:{ 
	           		label:{ 
			              show: true, 
			              formatter: '{b} : {c} ({d}%)' 
		            }, 
	                labelLine :{show:true} 
		        } 
		    } 

		}
	};

</script>
<!-- ******************************************************************************** -->


	</head>
	<body style="width: 100%; height: 100%;background-size:100% 100%;">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<div style="width: 100%;height: 100%;">
		<div>
 			<!-- <label for="inputCala"  class="radioY "><span>年</span></label> -->
			<label for="inputCala" class="radioS selected" ><span>时间</span></label> 
			 <input  type="text"   name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});"  value="" style="padding-bottom: 1px;"> 
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