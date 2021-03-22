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
	/* width: 40%; */
	width: 28%;
	height: 90%;
	color: #fff;
	float: left;
	/* margin-left: 8%;
	margin-right: 2%; */
	margin-left: 4%;
	margin-right: 1%;
}

.chartB {
	width: 28%;
	height: 90%;
	color: #fff;
	float: left;
	margin-right: 4%;
	margin-left: 1%;
}

.chartC {
	width: 28%;
	height: 90%;
	color: #fff;
	float: left;
	margin-right: 4%;
	margin-left: 1%;
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
		

		initSelEvent();

		initCharts();

	});

	function getParams() {
		var cityName = "${param.area_name}";
		var time="${param.year}";

		var params = "area_name=" + cityName + "&month=" + time+"&year="+time;
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

		var myChartA = echarts.init(document.getElementById('chartA'));
		myChartA.clear();
		getLineJsonById(myChartA, urlParam, [ "12%", "12%" ], [ '#ace2fe', '#87cbfb' ]);


		var myChartB = echarts.init(document.getElementById('chartB'));
		myChartB.clear();
		getPieJsonById(myChartB, urlParam);
		
		
		var myChartC = echarts.init(document.getElementById('chartC'));
		myChartC.clear();
		getPillarJsonById(myChartC, urlParam);
	}

	function getPieJsonById(myChart, param) {	
		var url = $ctx + "/tsgd/tsgd/comLinePie.action?sqlName="+ sqlNameb + "&"+ encodeURI(param);
		$.post(url).done(function(data) {
			var impMap = {
				show : true,
				fontSize : '14',
				fontWeight : 'bold'
			};
			var coArr = [ '#6cbdf7', '#fdb275', '#f1868d', '#3fcdcf' ];

			var dataTemp = new Array();
			var dataLable = new Array();
			/*var num = data.length > 5 ? 5 : data.length;*/
			var num = data.length > 10 ? 10 : data.length;
			var ht = "<tr class='tr1'>"
				+ "<td style='width:45%'>焦点问题名称</td>"
				+ "<td style='width:20%'>数量</td>"
				+ "<td>操作</td>"
				+ "</tr>";
			if (num < 1) {
				ht = ht + "<tr class='trOther' style='background:rgb(253, 253, 253)'> <td></td><td></td><td></td></tr>";
			}
			for (var i = 0; i < num; i++) {
				//var value = data[i].rowHashMap.VALUE; // + "%"
				//var name = data[i].rowHashMap.NAME.replace("→全局流转", "");
				
				var value = data[i].rowHashMap.VALUE; // + "%"
				var preName = data[i].rowHashMap.NAME;
				var a = preName.lastIndexOf("→");
				var name = preName.substr(preName.lastIndexOf("→",a-1) + 1, preName.lenght).replace("→全局流转", "");
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
			option_pie.title.text = "投诉占比";
			option_pie.series.name = "投诉占比";
			option_pie.legend.data = dataLable;
			myChart.setOption(option_pie);
		});
	}

	function resend(name, value) {
		openwindow($ctx + '/lnkhzs/propage/resendForm.jsp?name=' + name + "&id=" + value, '');
	}


	function getLineJsonById(myChart, param, placeArr, colorArr) {
		var url = $ctx + "/tsgd/tsgd/comLinePie.action?sqlName="+ sqlNamea + "&"+encodeURI(param);
		$.post(url).done(function(dataArr) {
			var data = dataArr;
			var nameTemp = new Array();
			var valueTemp = new Array();
			
			for (var i = 0; i < data.length; i++) { 
				var value = data[i].rowHashMap.VALUE;
				var name = data[i].rowHashMap.MON.substring(5,7);
				nameTemp[i] = name;
				valueTemp[i] = value;
			}
			
			if(sqlNamea=='SQL_IND_6_CLD_A.query'){
				option_Line.xAxis.axisLabel.interval = 'auto';
			}
			
			option_Line.yAxis.name = '投诉量';
			option_Line.xAxis.data = nameTemp;
			option_Line.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
			option_Line.series[0].color = colorArr[1];
			option_Line.grid.left = placeArr[0];
			option_Line.grid.right = placeArr[1];
			option_Line.series[0].data = valueTemp;
			myChart.setOption(option_Line);

		});
	}
	
	
	function getPillarJsonById(myChart, param){
		var url = $ctx + "/tsgd/tsgd/comLinePie.action?sqlName="+ sqlNamec + "&"+encodeURI(param);
		$.post(url).done(function(dataArr) {
			var data = dataArr;
			var nameTemp = new Array();
			var valueTemp = new Array();
			for (var i = 0; i < data.length; i++) { 
				var value = data[i].rowHashMap.VALUE;
				var name = data[i].rowHashMap.AREA_NAME;
				nameTemp[i] = name;
				valueTemp[i] = value;
			}
			option_pillar.yAxis[0].name = '投诉量';
			option_pillar.xAxis[0].data = nameTemp;
			option_pillar.series[0].data = valueTemp;
			//option_pillar.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
			//option_pillar.series[0].color = colorArr[1];
			//option_pillar.grid.left = placeArr[0];
			//option_pillar.grid.right = placeArr[1];
			
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
			formatter : function(name) {
				var a = name.lastIndexOf("→");
				name = name.substr(name.lastIndexOf("→",a-1) + 1, name.lenght).replace("→全局流转", "");
				var lablen = name.length > 10 ? 10 : name.length;
				var labname = name.substring(0, lablen);
				return labname;
			},
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
				normal : {
					show : false,
					position : 'center'
				},
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
			}
		}
	};
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
			name : "投诉量",
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
        left: '3%',
        right: '4%',
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
            type: 'value'
        }
    ],
    series: [
        {
            name: '投诉量',
            type: 'bar',
            barWidth: '60%',
            data: []
        }
    ]
};
	


	function openwindow2(url, name, iWidth, iHeight) {
		var resurl = encodeURI(url);
		var name = null;
		name = name || new Date().getTime();

		var w = $(window).width() - 160;
		var h = $(window).height() - 81;

		iWidth = iWidth || w;
		iHeight = iHeight || h;
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		return window.open(resurl, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
	}
</script>
<!-- ******************************************************************************** -->
<script src="${ctx }/lnkhzs/propage/comTable.js"></script>
<script type="text/javascript">
function fmt_operate(value, row, index) {
	var html = [];
	/* html.push("<a title='派发' class='update btn-link fontsize14' onclick='resend(\"'+row.NAME+'\", "")' >派发</a>"); */
	var url = $ctx + "/tsgd/resend/getDetailByLABELCATEGORY.action?wFresend.labelcategory="+row.NAME;
	$.ajax({
		url:$ctx + "/tsgd/resend/getDetailByLABELCATEGORY.action?wFresend.labelcategory="+row.NAME,
		async:false,
		type:'get',
		dataType:'json',
		success:function(dataArr){
			/* if(dataArr==null)
				html.push("<a title='派发' class='update btn-link fontsize14' onclick='resend(\""+row.NAME+"\", \"\")' >派发</a>");
			else if(dataArr.reportstatus=="保存")
				html.push("<a title='派发' class='update btn-link fontsize14' onclick='resend(\""+row.NAME+"\", \""+dataArr.id+"\")' >派发</a>");
			else if(dataArr.reportstatus=="已派发")
				html.push(""); */
			if(dataArr==null)
				html.push("<a title='派发' class='update btn-link fontsize14' onclick='resend(\""+row.NAME+"\", \"\")' >派发</a>");
			else 
				html.push("<a title='派发' class='update btn-link fontsize14' onclick='resend(\""+row.NAME+"\", \""+dataArr.id+"\")' >派发</a>");
		}
	})
	
	return html.join("");
}

fn_detail = {
	'click a': function (e, value, row, index) {
		var mysqlName="${param.sqlName}";
   		var mynum=mysqlName.split("_")[2];
   		if(mynum==7||mynum==59||mynum==60||mynum==61||mynum==62||mynum==63){
   			openwindow($ctx + '/lnkhzs/propage/comTableDetail.jsp?name='+row.NAME+"&year="+row.ACPT_TIME+"&area_name="+"${param.area_name}"
   			+"&mynum="+"SQL_KHZS_ts_"+mynum+".query",'');
   		}else if(mynum==8){
   			openwindow($ctx + '/lnkhzs/propage/comTableDetail.jsp?nname='+row.NAME+"&year="+row.ACPT_TIME+"&area_name="+"${param.area_name}"
   			+"&mynum="+"SQL_KHZS_ts.query",'');
   		}else if(mynum==6){
   			openwindow($ctx + '/lnkhzs/propage/comTableDetail.jsp?name='+row.NAME+"&year="+row.ACPT_TIME+"&area_name="+"${param.area_name}"
   			+"&mynum="+"SQL_KHZS_ts_"+mynum+".query",'');
   		}else{
   			openwindow($ctx + '/lnkhzs/propage/comTableDetail.jsp?name='+row.NAME+"&year="+row.ACPT_TIME+"&area_name="+"${param.area_name}"
   			+"&mynum="+"SQL_KHZS_ts.query",'');
   		}

   }
}
/* var fn_operate_events = {
	'click a.update' : function(e, value, row, index) {
		resend(row.NAME, "");
	},
	
}; */
function getSendID(name){
	var url = $ctx + "/tsgd/resend/getDetailByLABELCATEGORY.action?wFresend.labelcategory="+encodeURI(name);
	var myid;
		$.post(url).done(function(dataArr) {
		});
}
</script>
	</head>
	<body style="width: 100%; height: 100%;background-size:100% 100%;">
		<div style="width: 100%;height: 50%;">
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 分析 </span>
			</div>
			<div id="chartA" class="chartA"></div>
			<div id="chartB" class="chartB"></div>
			<div id="chartC" class="chartC"></div>
		</div>


		</div>
		<div class="Ct Dialog" style="width: 100%;height: 50%;">
			<div class="" style="width: 100%; height: 100%;">
				<dg:ajaxgrid id="table0" sqlname="${sqlName}">
					<dg:lefttoolbar>
						<!-- 		            <button class="btn-link search iconfont icon-sousuo1 fontsize14" onclick="showsearch('table0')" >搜索</button>
		            <button class="btn-link refresh iconfont icon-refresh2 fontsize14" onclick="$.bs.table.refresh('table0')" >刷新</button> -->
					</dg:lefttoolbar>
					<dg:condition>
						<div class="btn_box overflow">
							<div class="iconbtn floatRight10 floatRight reset">
								<i class="icon iconfont icon-loading2 fontsize14"></i>重置
							</div>
							<div class="iconbtn floatRight10 floatRight refresh"
								onclick="$.bs.table.reload('table0');">
								<i class="icon iconfont icon-searchlist fontsize14"></i>查找
							</div>
						</div>
					</dg:condition>
					<dg:ajax-table>
						<!-- data-height = auto 的时候表格高度将撑满页面,分页将不受到pageSize/pageList控制；否则 还需要指定pageSize/pageList的值 -->
						<table id="btable" data-height='auto' data-page-size='10'
							data-page-list='[10, 20, 50,100]'
							class="table table-hover text_align_center table-no-bordered"
							data-show-columns='false'>
							<thead>
							
							${dataField}
							</thead>
						</table>
					</dg:ajax-table>
				</dg:ajaxgrid>
				<iframe name="download" src="" style="display:none"></iframe>
			</div>
		</div>
		
	</body>
	</html>