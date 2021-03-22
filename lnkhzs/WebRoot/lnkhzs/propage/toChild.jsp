<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript"
	src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>

<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/json2.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/handlers.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/ultraswfupload.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField.js"></script>

<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/ultraupload.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload_new.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css"
	rel="stylesheet" type="text/css" />

<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'>

	$(document).ready(function() {
		
		getLineJsonById();
		
	});
	
	function getLineJsonById() {
		var placeArr = ["12%", "12%"];
		var colorArr = ['#ace2fe', '#87cbfb'];
		var myChart = echarts.init(document.getElementById('chartA'));
		var param = window.opener.getParams();
		var cname = getQueryString("name");
		var url = $ctx + "/tsgd/tsgd/ajaxGetTsfxAmount.action?" + param + "&cname=" + cname;
		$.post(encodeURI(url)).done(function(dataArr) {
			var data = dataArr;
			
			var nameTemp = new Array();
			var valueTemp = new Array();
			for (var i = 0; i < data.length; i++) {
				var value = data[i].value;
				var name = data[i].name;
				nameTemp[i] = name + "月";
				valueTemp[i] = value;
			}
			option_Line.yAxis.name = '投诉量';
			option_Line.xAxis.data = nameTemp;
			option_Line.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
			option_Line.series[0].color = colorArr[1];
			option_Line.grid.left = placeArr[0];
			option_Line.grid.right = placeArr[1];
	//		option_Line.aria.description=id;
			option_Line.series[0].data = valueTemp;
			myChart.setOption(option_Line);
			
			/* myChart.on('click', function (param){  
		        var parid = myChart.getOption().aria.description;
	            var childUrl = $ctx + "/lnkhzs/propage/comCustDetail.jsp?id=" + parid;
	    		openwindow2(childUrl,'',1200, 600);
	        }); */
		});
	}
	
	function getQueryString(name) {   
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");   
      var r = window.location.search.substr(1).match(reg);   
      if (r != null) return decodeURI(r[2]); return null;   
    }
    
	var option_Line = {
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
	        nameTextStyle: {
	        	fontSize : '14',
	        	color : '#333',
	        	fontWeight: 600
	        },
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
</script>
<meta charset="UTF-8">
<title>投诉量子页面</title>
</head>
<body style="height: 95%;">
	<div class="bodyDiv"> 
		<div class="midDiv" style="margin: 0 auto;height: 95%;margin-top: 15px;">
			<!-- <div class="midTitDiv">
				<span class="midSpan"> 基础投诉分析 </span>
			</div> -->
			<div id="chartA" class="chartA" style="margin: 0 auto;width: 90%;height: 95%; float: inherit;">
				
			</div>
		</div>
	</div>
</body>
</html>