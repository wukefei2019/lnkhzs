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

.pager-container>li {
	float: left;
}

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
	height: 405px;
	border: 0.5px solid rgb(221, 221, 221);
	margin: 15px 0 15px 0;
}

.midTitDiv {
	height: 40px;
	border-bottom: 1px solid #e6e6e6;
}
.chartA {
	width: 60%;
	height: 90%;
	color: #fff;
	float: left;
	margin-left: -3%;
	 /* margin-left: 2%;  
	margin-right: 2%;*/
}

.chartB {
	width: 40%;
	height: 90%;
	color: #fff;
	float: left;
	margin-left: -3%;
	 /* margin-left: 2%;  
	margin-right: 2%;*/
}


</style>
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>

<!-- ******************************************************************************** -->
<script type="text/javascript">

$(window).resize(function() {
    window.location.reload();
})

var name="";
var type="";
var time="";
var area_name="";
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
		 name = "${param.name}";
		 type = "${param.type}";
		 time = "${param.time}";
		 area_name = "${param.area_name}";
		 console.log(time);
		/* var time = "${param.time}";
		var type = "${param.type}"; */
		

		var store = "A";
		$("label").on("click", function() {

			if (this.innerText == "年") {
				$(".radioY").addClass("selected");
				$(".radioM").removeClass("selected");
				if (store == "B") {
					$("#calendar").val("");
				}
				store = "A";
			} else if (this.innerText == "月") {
				$(".radioY").removeClass("selected");
				$(".radioM").addClass("selected");
				if (store == "A") {
					$("#calendar").val("");
				}
				store = "B";
			}
		})
		


		initCharts();

	});

	function getParams() {
		var cityName = "${param.area_name}";
		var time="${param.year}";

		var params = "area_name=" + cityName + "&month=" + time+"&year="+time;
		return params;
	}



	function initCharts() {
		var urlParam = getParams();
		
		//折线图
		var myChartA = echarts.init(document.getElementById('chartA'));
		myChartA.clear();
		getLineJsonById(myChartA, urlParam, [ "12%", "12%" ], [ '#ace2fe', '#87cbfb' ]);

		//柱状图
		var myChart = echarts.init(document.getElementById("chartB"));
		myChart.clear();
		getColuJsonById(myChart, urlParam);

	}



	//折线图
	function getLineJsonById(myChart, param, placeArr, colorArr) {
		var url = $ctx + "/standards/fwzl/ajaxGetPiesJsonGroup.action?menuId=E" + "&name="+ encodeURI(name)+"&type="+ encodeURI(type)+"&area_name="+ encodeURI(area_name);
		$.post(url).done(function(dataArr) {
			var data = dataArr;
			var nameTemp = new Array();
			var valueTemp = new Array();
			
			for (var i = 0; i < data.length; i++) {
				var value = data[i].value;
				var mon = data[i].mon;
				nameTemp[i] = mon;
				valueTemp[i] = value;
			}
			
			/* if(sqlNamea=='SQL_IND_6_CLD_A.query'){
				option_Line.xAxis.axisLabel.interval = 'auto';
			} */
			
			option_Line.yAxis.name = '指标情况';
			option_Line.xAxis.data = nameTemp;
			option_Line.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
			option_Line.series[0].color = colorArr[1];
			option_Line.grid.left = placeArr[0];
			option_Line.grid.right = placeArr[1];
			option_Line.series[0].data = valueTemp;
			myChart.setOption(option_Line);

		});
	}

	//柱状图
	function getColuJsonById(myChart, params) {
		var url = $ctx + "/standards/fwzl/ajaxGetPiesJsonGroup.action?menuId=F" + "&name="+ encodeURI(name)+"&type="+ encodeURI(type)+"&time="+time;
		$.post(url).done(function(dataArr) {
			var data = dataArr;
			var nameTemp = new Array();
			var valueTemp = new Array();

			for (var i = 0; i < data.length; i++) {
				var value = data[i].value;
				var city = data[i].city;
				nameTemp[i] = city;
				valueTemp[i] = value;
			}

			//if(sqlNamea=='SQL_IND_6_CLD_A.query'){
			//	option_Line.xAxis.axisLabel.interval = 'auto';
			//}
			option_pillar.xAxis[0].data = nameTemp;
			option_pillar.series[0].data = valueTemp;

			myChart.setOption(option_pillar);

		});
	}


	function changeTime() {
		var dateFormat = '';
		if ($(".radioY").hasClass("selected")) {
			dateFormat = 'yyyy';
		} else if ($(".radioM").hasClass("selected")) {
			dateFormat = 'yyyy-MM';
		}
		return dateFormat;
	}

	/*折线图*/
	var option_Line = {
	
		legend : {
			textStyle : {
				color : '#fff'
			},
			right : '38px',
			itemHeight : 9,
			itemWidth : 16
		},
		grid : {
			/* left:'5%',
			right:'5%', */
			top : '18%',
			bottom : '15%'
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			  axisLabel:{
	    		interval: 0
	    	} 
		},
		yAxis : {
			type : 'value',
			nameTextStyle : {
				fontSize : '14',
				color : '#333',
				fontWeight : 600
			},
			splitArea : {
				show : true,
				areaStyle : {
					color : [ 'rgb(255,255,255)', 'rgba(253,253,253)' ]
				}
			}
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b}: {c}"
		},
		series : [ {
		     label: {
                normal: {
                    show: true,
                }
            },
			type : 'line',
			smooth : true,
			name : "指标情况",
			areaStyle : {
				color : {
					type : 'linear',
					x : 0,
					y : 0,
					x2 : 0,
					y2 : 1,
					colorStops : [ {
						offset : 0,
						color : '#87cbfb' // 0% 处的颜色
					}, {
						offset : 1,
						color : '#eee' // 100% 处的颜色
					} ],
					global : false // 缺省为 false
				}
			},
			color : '#87cbfb'
		} ],
		aria : {
			show : false
		}
	};
	
	/*柱状图*/
	var option_pillar = {
    color: ['#87cbfb'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '1%',
        right: '0%',
        /*bottom: '3%',*/
        top : '18%',
        bottom : '9%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            data: [],
            axisTick: {
                alignWithLabel: true
            },
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
        	name:'指标情况',
        	nameTextStyle : {
				fontSize : '10',
				color : '#333',
				fontWeight : 600
			},
            type: 'value'
        }
    ],
    series: [
        {
            name: '指标情况',
            type: 'bar',
            barWidth: '60%',
            data: [],
        }
    ]
};


</script>
<script type="text/javascript">

</script>
	</head>
	<body style="width: 100%; height: 100%;background-size:100% 100%;">
		<div style="width: 100%;height: 80%;">
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 分析 </span>
			</div>
			<div id="chartA" class="chartA"></div>
			<div id="chartB" class="chartB"></div>
			
		</div>

		</div>
		
		
	</body>
	</html>