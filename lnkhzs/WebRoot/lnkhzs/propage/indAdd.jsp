<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/comStyle.css" rel="stylesheet" type="text/css" />

<html style="height: 100%;">
<head>
<script type="text/javascript">
	var DEFAULT_VERSION = 8.0;
	var ua = navigator.userAgent.toLowerCase();
	var isIE = ua.indexOf("msie") > -1;
	var safariVersion;
	if (isIE) {
		safariVersion = ua.match(/msie ([\d.]+)/)[1];
	}

	if (safariVersion <= DEFAULT_VERSION) {
		// 进行你所要的操作
		alert("系统检测到您正在使用ie8以下内核的浏览器，不能实现完美体验，请及时更新浏览器版本！建议使用ie11，谷歌等浏览器！");
	};
	
	

</script>
<meta charset="UTF-8">
	<title>首页</title> <script
		src="${ctx }/common/plugin/echart/echarts.min.js"></script>
		<script
		src="${ctx }/common/assets/js/echo.js"></script>


	<style type="text/css">
html {
	/*隐藏滚动条，当IE下溢出，仍然可以滚动*/
	-ms-overflow-style: none;
}

::-webkit-scrollbar {
	/*滚动条整体样式*/
	width: 4px; /*高宽分别对应横竖滚动条的尺寸*/
	height: 4px;
}

::-webkit-scrollbar-thumb {
	/*滚动条里面小方块样式*/
	border-radius: 5px;
	-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
	background: #06c5d2;
}

::-webkit-scrollbar-track {
	/*滚动条里面轨道样式*/
	-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
	border-radius: 0;
	background: rgba(0, 0, 0, 0.1);
}

.backgroundPic {
	width: 100%;
	height: 100%;
	min-width: 1200px;
	overflow-x: auto;
	position: fixed;
	left: 0;
	top: 0;
	z-index: -1;
}

.backgroundTitle {
	width: 100%;
	height: 70px;
	min-width: 1200px;
	overflow-x: auto;
	position: fixed;
	left: 0;
	top: 0;
}

.contentDiv {
	width: 100%;
	height: 100%;
	display: table;
	text-align: center;
	position:relative;
	border: 0.5px solid rgb(111, 122, 133);
	background: rgb(246, 246, 246);
}

.addDiv {
	vertical-align: middle;
	display: table;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
    padding-top: 15px;
}

.paddingR5 {
	padding-right: 5px;
	padding-top: 3px
}

.paddingL5 {
	padding-left: 5px
}

.titleDiv {
	width: 100%;
	height: 20px;
	color: #fff;
	background-color: rgba(0, 20, 40, 0.55);
}

.allContentDiv {
	position: fixed;
	top: 70px;
	left: 0;
	bottom: 0;
	right: 0;
	padding: 0 10px 10px 10px;
}

.divcontent {
	position:relative;
    width: 16.6%;
    height: 30%;
	float: left;
	padding: 1% 2% 0 2%;
	margin-bottom: 25px;
}

.divChartStore{
	width: 100%;
	height:auto;
	overflow:auto;
	border: 0.5px solid rgb(158, 162, 189);
	margin-bottom: 10px;
	display: none;
}

.fontStyle{
	color: white;
	text-align: center;
}

</style>

	<script type="text/javascript">
		
		// 移动业务
		var ydyw = ['移动业务投诉量', '移动业务万投诉率',
					'移动业务营销投诉量', '移动业务营销万投诉率',
					'移动业务资费投诉量', '移动业务资费万投诉率',
					'4G网络质量投诉量', '4G网络质量万投诉率',
					'4G语音通话投诉量', '4G语音通话万投诉率',
					'4G手机上网投诉量', '4G手机上网万投诉率',
					'5G投诉量', '5G网络质量投诉量',
					'5G套餐（基础、流量）'];
		// 家庭业务
		var jtingyw = ['家庭业务投诉量', '家庭业务万投诉率',
					'家庭宽带投诉量', '家庭宽带万投诉率',
					'家庭宽带网络质量投诉量', '家庭宽带网络质量万投诉率',
					'家庭宽带装维投诉量', '家庭宽带装维万投诉率',
					'家庭宽带营销投诉量', '家庭宽带营销万投诉率',
					'魔百和投诉量', '魔百和万投诉率'];
		// 增值业务
		var zzyw = ['增值业务投诉量', '增值业务万投诉率',
					'移动增值业务投诉量', '移动增值业务万投诉率',
					'家庭增值业务投诉量', '家庭增值业务万投诉率'];
		// 集团业务
		var jtuanyw = ['集团业务投诉量', '集团业务万投诉率'];

		// 红线
		var hx = ['服务触点投诉量', '服务触点万投诉率', '不知情定制投诉量', '不知情定制万投诉率',
					'移动业务不知情定制投诉量', '移动业务不知情定制万投诉率', '家庭业务不知情定制投诉量', '家庭业务不知情定制万投诉率',
					'增值业务不知情定制投诉量', '增值业务不知情定制万投诉率', '集团业务不知情定制投诉量', '集团业务不知情定制万投诉率',
					'限制客户更改套餐投诉量', '限制客户更改套餐万投诉率', '无法停机销户投诉量', '无法停机销户万投诉率',
					'携号转网投诉量', '携号转网万投诉率'];
		// 其他		
		var qt = ['互联网渠道客户投诉量', '家庭宽带投诉处理及时率',
					'客户投诉量', '跨省投诉量',
					'批量普通投诉量', '投诉处理平均时长',
					'万客户投诉率', '重复投诉量', '重复投诉率',
					'总体投诉处理及时率', '总体投诉处理解决率', '总体投诉处理满意率'];
							
		
		
		function contentDivbtn(obj){
			var id = $(obj).attr("id");
			if (confirm("确认要添加当前图表？")) {
				var addurl = $ctx + "/tsgd/tsgd/ajaxAddPieById.action?id=" + id;
				$.post(addurl).done(function(data) {
					if (data == 1) {
						window.close();
						window.opener.location.reload();
					} else if (data == 2) {
						alert("最多添加七个图表");
					} else {
						 alert("失败");
					}
				});
			}
		}
		
		$(document).ready(function() {
	
			var url = $ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=B";
			$.post(url).done(function(data) {
				var html = "";
				var arra=[],arrb=[],arrc=[],arrd=[],arre=[],arrf=[];
				for (var i = 0; i < data.length; i++) {
					/* for(var i=0;i<mynum;i++){  */
					var id = data[i].id;
					var name = data[i].name;
					var sql = data[i].sql;
					var type = data[i].type;
					var colorSplit = i % 7;
//					var url = $ctx + "/lnkhzs/propage/indAjax.jsp?id=" + id + "&sql=" + sql + "&type=" + type+ "&name=" + name + "&colorSplit=" + colorSplit;
					html = '<div class="titleDiv"><span class="paddingL5">' + name + '</span></div>'
						+ '<div onclick="contentDivbtn(this)" class="contentDiv" id="' + id + '"><div id="zhezhao' + id + '" style="z-index:99;width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0);display:block;text-align:center;"></div>'
						+ '<div id="myECharts' + i + '" style="width: 100%; height: 100%;cursor:pointer"></div>'
						+ '</div></div>';
						
					if (ydyw.indexOf(name)>-1) {
						var ind = ydyw.indexOf(name);
						html = '<div class ="divcontent" id="ydyw' + ind + '">' + html;
						arra.push(ind);
						arra.sort(function(a,b){ return a - b;});
						var rearra = arra.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "ydyw" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arra.length>1){
							var befId = "ydyw" + arra[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentA").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
						
					} else if (jtingyw.indexOf(name)>-1){
						var ind = jtingyw.indexOf(name);
						html = '<div class ="divcontent" id="jtingyw' + ind + '">' + html;
						arrb.push(ind);
						arrb.sort(function(a,b){ return a - b;});
						var rearra = arrb.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "jtingyw" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arrb.length>1){
							var befId = "jtingyw" + arrb[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentB").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
					} else if (zzyw.indexOf(name)>-1){
						var ind = zzyw.indexOf(name);
						html = '<div class ="divcontent" id="zzyw' + ind + '">' + html;
						arrc.push(ind);
						arrc.sort(function(a,b){ return a - b;});
						var rearra = arrc.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "zzyw" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arrc.length>1){
							var befId = "zzyw" + arrc[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentC").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
					} else if (jtuanyw.indexOf(name)>-1){
						var ind = jtuanyw.indexOf(name);
						html = '<div class ="divcontent" id="jtuanyw' + ind + '">' + html;
						arrd.push(ind);
						arrd.sort(function(a,b){ return a - b;});
						var rearra = arrd.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "jtuanyw" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arrd.length>1){
							var befId = "jtuanyw" + arrd[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentD").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
					} else if (hx.indexOf(name)>-1){
						var ind = hx.indexOf(name);
						html = '<div class ="divcontent" id="hx' + ind + '">' + html;
						arre.push(ind);
						arre.sort(function(a,b){ return a - b;});
						var rearra = arre.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "hx" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arre.length>1){
							var befId = "hx" + arre[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentE").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
					} else if (qt.indexOf(name)>-1){
						var ind = qt.indexOf(name);
						html = '<div class ="divcontent" id="qt' + ind + '">' + html;
						arrf.push(ind);
						arrf.sort(function(a,b){ return a - b;});
						var rearra = arrf.filter(function (x){return x < ind});
						if (rearra.length>0) {
							var befId = "qt" + rearra[rearra.length-1];
							$("#"+befId ).after(html);
						} else if (arrf.length>1){
							var befId = "qt" + arrf[1];
							$("#"+befId ).before(html);
						} else {
							$("#divcontentF").append(html).show();
						}
						setChartByID(i, colorSplit, name, id);
					}
					
				}
				for (var i = 0; i < data.length; i++) {
					var id = data[i].id;
					var vieworder = data[i].vieworder;
					if (vieworder != null && vieworder > 0) {
						$("#" + id).removeAttr("onclick");
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
						$("#zhezhao" + id).append('<div class="addDiv"><img src="../style/image/finish.png" class="add"/></div>');
					} else {
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
					}
				}
				$('html,body').animate({
					scrollTop : '0px'
				}, 10);
	
			}, "json");
	
		});
		
		function setChartByID(i, colorSplit, name, id) {
			var myChart = echarts.init(document.getElementById('myECharts'+ i));
	        var impMap = {show: true, fontSize: '14', fontWeight: 'bold'};
			var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
			var h = colorSplit;
			var coArr = [colorArr[h], '#e9eced'];
			var data = new Array();
			var dataMap = {value:0};
			data[0]=dataMap;
			optionOne.color=coArr;
			optionOne.series.data=data;
			optionOne.title.text = name;
			optionOne.series.label.normal.formatter="";
			
			myChart.setOption(optionOne);
			$("#"+id).find("img[src='../style/image/loading_0.gif']").remove();
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
	            center: ['50%', '40%'],
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
<body style="height: 100%;">
	<img alt="" class="backgroundPic" />
		<div style="width: 100%; height: 100%;" id="divcontentAll">
			<div class="divChartStore" id="divcontentF">
				<div class="fontStyle">总体</div>
			</div>
			<div class="divChartStore" id="divcontentA">
				<div class="fontStyle">移动业务</div>
			</div>
			<div class="divChartStore" id="divcontentB">
				<div class="fontStyle">家庭业务</div>
			</div>
			<div class="divChartStore" id="divcontentC">
				<div class="fontStyle">增值业务</div>
			</div>
			<div class="divChartStore" id="divcontentD">
				<div class="fontStyle">集团业务</div>
			</div>
			<div class="divChartStore" id="divcontentE">
				<div class="fontStyle">红线</div>
			</div>
		</div>
</body>
</html>