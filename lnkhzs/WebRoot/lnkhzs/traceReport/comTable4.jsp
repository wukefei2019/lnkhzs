<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link rel="stylesheet"
	href="${ctx }/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<head>
<meta charset="UTF-8"></meta>
<title></title>
<style type="text/css">
.inputCala{
	font: normal 12px/18px 'microsoft yahei' !important;
	background: #fff url(../image/arrow_bottom.png) no-repeat;
	background-position: 96% center;
	display: inline-block;
	width: 30px !important;
	border-radius: 2px;
	color: #666;
}
.radioY{
	border: 1px solid rgb(100, 200, 240);
    width: 25px;
    height: 23px;
    padding-left: 4px;
    color: rgb(166, 166, 166);
}

.radioM{
	border: 1px solid rgb(100, 200, 240);
    width: 25px;
    height: 23px;
    padding-left: 4px;
    margin-left: -4px;
    color: rgb(166, 166, 166);
}
.radioR{
	border: 1px solid rgb(100, 200, 240);
    width: 25px;
    height: 23px;
    padding-left: 4px;
    margin-left: -4px;
    color: rgb(166, 166, 166);
}
.selected{
	background-color: rgb(100, 200, 240);
	color: rgb(243, 250, 251);
}
.midSpan{
	font-size: 16px;
	font-family: Microsoft YaHei;
	font-weight: 600;
	color: #207bbb;
    height: 40px;
    padding-left: 35px;
    padding-top: 9px;
    margin-left: 10px;
    display: inline-block;
    background: url(basic.png) no-repeat left center;
}

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
.chartA {
	/* width: 40%; */
	width: 28%;
	height: 90%;
	float: left;
	/* margin-left: 8%;
	margin-right: 2%; */
	margin-left: 5%;
	margin-right: 1%;
}

.chartB {
	width: 28%;
	height: 90%;
	float: left;
	margin-right: 1%;
	margin-left: 1%;
}

.chartC {
	width: 28%;
	height: 90%;
	float: left;
	margin-right: 5%;
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
		getPieJsonById(urlParam); 
	}

	function getPieJsonById(param) {
		var url = $ctx + "/trace/traceReport/comLinePie.action?sqlName=SQL_TRACE_SITUATION_CITY.query&"+ encodeURI(param);
		$.post(url).done(function(data) {
			var d = new Date();
			var m = d.getMonth() + 1;
			m = m < 10 ? "0" + m : m;
			var date = $('input[name="inputCala"]').val();
			if (date == null || date == undefined || date == '') { 
				date = m;
			}
 			for (var i = 0; i < data.length; i++) { 
				var name = data[i].rowHashMap.CITY;
				if(name == '合计'){
					$("#market").html();
					$("#mobile").html();
					$("#home").html();
					var market = "<p>到"+date+"月的问题量:"+ (!!data[i].rowHashMap.市场类总量?data[i].rowHashMap.市场类总量:0) +"项</p>";
					market+="<p>"+date+"月的问题量:"+ (!!data[i].rowHashMap.市场类新发起?data[i].rowHashMap.市场类新发起:0) +"项</p>";
					market+="<p>验收量:"+ (!!data[i].rowHashMap.市场类已解决?data[i].rowHashMap.市场类已解决:0) +"项</p>";
					market+="<p>解决率:"+ (!!data[i].rowHashMap.市场类解决率?data[i].rowHashMap.市场类解决率:0) +"%</p>";
					$("#market").html(market);
					var mobile = "<p>到"+date+"月的问题量:"+ (!!data[i].rowHashMap.移动总量?data[i].rowHashMap.移动总量:0) +"项</p>";
					mobile+="<p>"+date+"月的问题量:"+ (!!data[i].rowHashMap.移动新发起?data[i].rowHashMap.移动新发起:0) +"项</p>";
					mobile+="<p>验收量:"+ (!!data[i].rowHashMap.移动已解决?data[i].rowHashMap.移动已解决:0) +"项</p>";
					mobile+="<p>解决率:"+ (!!data[i].rowHashMap.移动解决率?data[i].rowHashMap.移动解决率:0) +"%</p>";
					$("#mobile").html(mobile);
					var home = "<p>到"+date+"月的问题量:"+ (!!data[i].rowHashMap.家宽总量?data[i].rowHashMap.家宽总量:0) +"项</p>";
					home+="<p>"+date+"月的问题量:"+ (!!data[i].rowHashMap.家宽新发起?data[i].rowHashMap.家宽新发起:0) +"项</p>";
					home+="<p>验收量:"+ (!!data[i].rowHashMap.家宽已解决?data[i].rowHashMap.家宽已解决:0) +"项</p>";
					home+="<p>解决率:"+ (!!data[i].rowHashMap.家宽解决率?data[i].rowHashMap.家宽解决率:0) +"%</p>";
					$("#home").html(home);
				}
			} 
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

</script>
<!-- ******************************************************************************** -->
</head>
	<body style="width: 98%; height: 95%;background-size:100% 100%;">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<div style="width: 100%;height: 100%;">
		<div>
 			<!-- <label for="inputCala"  class="radioY "><span>年</span></label> -->
			<label for="inputCala" class="radioS selected" ><span>时间</span></label> 
 			<input  type="text"   name="inputCala" autocomplete="off" class="textInput inputCala" id="calendar"
				 onclick="var dateFormat=changeTime();WdatePicker({dateFmt:dateFormat,onpicked:changeDateVal});"   value="" style="padding-bottom: 1px;"> 
		</div>
		<div class="midDiv">
			<div class="midTitDiv">
				<span class="midSpan"> 分析 </span>
			</div>
			<div id="chartA" class="chartA">
				<div class="panel panel-primary" style="margin-top: 20px;height: 90%;">
				    <div class="panel-heading">
				        <h3 class="panel-title">
				           	市场类问题
				        </h3>
				    </div>
				    <div id="market" class="panel-body">
				    </div>
				</div>
			</div>
			<div id="chartB" class="chartB">
				<div class="panel panel-primary" style="margin-top: 20px;height: 90%;">
				    <div class="panel-heading">
				        <h3 class="panel-title">
				           	移动网络质量
				        </h3>
				    </div>
				    <div id="mobile" class="panel-body">
				    </div>
				</div>
			</div>
			<div id="chartC" class="chartC">
				<div class="panel panel-primary" style="margin-top: 20px;height: 90%;">
				    <div class="panel-heading">
				        <h3 class="panel-title">
				           	家宽网络质量
				        </h3>
				    </div>
				    <div id="home"  class="panel-body">
				    </div>
				</div>
			</div>
		</div>


		</div>
	</body>
	</html>