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
	display: table;
	vertical-align: middle;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.4);
    padding-top: 55px;
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
}


</style>

	<script type="text/javascript">
	
	function contentDivbtn(obj){
		var id = $(obj).attr("name");
		if (confirm("确认要添加当前图表？")) {
			var addurl = encodeURI($ctx + "/standards/fwzl/ajaxAddPieById.action?id=" + id + "&type=${param.type}");
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
	
		var mydata;
	
		var mynum = 4;
	
		$(document).ready(function() {
			//var params = "&area_name=${param.area_name}&time=${param.time}&type=${param.type}";
			var params = "&type=${param.type}";
			var url = $ctx + "/standards/fwzl/ajaxGetPiesJsonGroup.action?menuId=B" + encodeURI(params);
			$.post(url).done(function(data) {
				mydata = data;
				var html = "";
				for (var i = 0; i < data.length; i++) {
					 var id = data[i].id; 
					var name = data[i].name;
					//var value = data[i].value==""?0:data[i].value;
					if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
						value = data[i].value;
					}
					//var sql = data[i].sql;
					//var type = data[i].type;
					var colorSplit = i % 7;
					var url = $ctx + "/lnkhzs/standards/indAjax.jsp?id=" + id + "&name=" + name +  "&value=" + value +  "&colorSplit=" + colorSplit;
					html += '<div class ="divcontent" ><div class="titleDiv"><span class="paddingL5">' + name + '</span></div>'
						+ '<div onclick="contentDivbtn(this)" class="contentDiv" id="' + id + '" name="' + name + '"><div id="zhezhao' + id + '" style="z-index:99;width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0);display:block;text-align:center;"></div><iframe src=' + encodeURI(url) + ' frameborder="0"  scrolling="no"  class="width100p" style="height: 100%;"></iframe></div></div>';
	
				/* $("#divcontentAll").append('<div class ="divcontent" ><div class="titleDiv"><span class="paddingL5">'+name+'</span></div>'
					+'<div class="contentDiv" id="'+id+'"><iframe src='+encodeURI(url)+' frameborder="0"  scrolling="no"  class="width100p" style="height: 100%;"></iframe></div></div>');
				$("#"+id).append('<div id="zhezhao'+id+'" style="z-index:99;width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0);display:table;text-align:center;"></div>'); */
				}
				$("#divcontentAll").append(html);
				for (var i = 0; i < data.length; i++) {
					var id = data[i].id;
					//var value = data[i].value==""?0:data[i].value;
					if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
					value = data[i].value;
					}
					
					var vieworder = data[i].vieworder;
					if (vieworder != null && vieworder > 0) {
						//$("#" + id).unbind("click");
						$("#" + id).removeAttr("onclick");
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
						$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv"><img src="../style/image/finish.png" class="add"/></div></div>');
					}else if(value==0){
						$("#" + id).removeAttr("onclick");
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
						$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv" style="color: cyan;"><span style="line-height: 260%">&nbsp;&nbsp;数据未接入</span></div></div>');
					}else {
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
					}
				}
				$('html,body').animate({
					scrollTop : '0px'
				}, 10);
	
			}, "json");
			
			
	
		});
	
		/* //获取滚动条当前的位置
		function getScrollTop() {
			var scrollTop = 0;
			if (document.documentElement && document.documentElement.scrollTop) {
				scrollTop = document.documentElement.scrollTop;
			} else if (document.body) {
				scrollTop = document.body.scrollTop;
			}
			return scrollTop;
		}
	
		//获取当前可视范围的高度  
		function getClientHeight() {
			var clientHeight = 0;
			if (document.body.clientHeight && document.documentElement.clientHeight) {
				clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
			} else {
				clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
			}
			return clientHeight;
	
	
		}
	
	
		//获取文档完整的高度 
		function getScrollHeight() {
			return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
		}
	
		//滚动事件触发
		window.onscroll = function() {
			if (getScrollTop() + getClientHeight() == getScrollHeight()) {

	
				var data = mydata;
				var maxlength = mynum + 4;
	
				//如果已经是最大长度，不在执行下拉；
				if (mynum == data.length)
					return;
	
				if (maxlength > data.length)
					maxlength = data.length;
	
				var html = "";
				for (var i = mynum; i < maxlength; i++) {
					var id = data[i].id;
					var name = data[i].name;
					
					var value = "";
					if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
					value = data[i].value;
					}
					//var value = data[i].value==""?0:data[i].value;
					//var sql = data[i].sql;
					//var type = data[i].type;
					var colorSplit = i % 7;
					var url = $ctx + "/lnkhzs/standards/indAjax.jsp?id=" + id + "&name=" + name +  "&value=" + value + "&colorSplit=" + colorSplit;
					html += '<div class ="divcontent" ><div class="titleDiv"><span class="paddingL5">' + name + '</span></div>'
						+ '<div onclick="contentDivbtn(this)" class="contentDiv" id="' + id + '" name="' + name + '" >'
						+ '<div id="zhezhao' + id + '" style="z-index:99;width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0);display:block;text-align:center;"></div>'
						+ '<iframe src=' + encodeURI(url) + ' frameborder="0"  scrolling="no"  class="width100p" style="height: 100%;"></iframe></div></div>';
	
	
				}
				$("#divcontentAll").append(html);
				//此处发起AJAX请求
				for (var i = mynum; i < maxlength; i++) {
					var id = data[i].id;
					//var value = data[i].value==""?0:data[i].value;
					if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
					value = data[i].value;
					}
					var vieworder = data[i].vieworder;
					
					if  (value!=0) {
						//$("#" + id).unbind("click");
						$("#" + id).removeAttr("onclick");
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
						$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv"><img src="../style/image/finish.png" class="add"/></div></div>');
					}else if (value==0 || data[i].value==null){
						$("#" + id).removeAttr("onclick");
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
						$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv"><span style="line-height: 260%">&nbsp;&nbsp;数据未接入</span></div></div>');
					}else {
						//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
					}
				}
				mynum = maxlength;
				Echo.init({
					offset : 0,
					throttle : 0
				});
			}
		}
	
	
		fnSearch();
		var interval3 = setInterval(function() {
			fnSearch();
		}, 3000);
	
		function fnSearch() {
			if(mynum>8){
				clearInterval(interval3);
				console.log("定时器停止")
			}
			if(mydata!=undefined&&mynum<=8){
			
			 var data = mydata;
			var maxlength = mynum + 4;
	
			//如果已经是最大长度，不在执行下拉；
			if (mynum == data.length)
				return;
	
			if (maxlength > data.length)
				maxlength = data.length;
	
			var html = "";
			for (var i = mynum; i < maxlength; i++) {
				var id = data[i].id;
				var name = data[i].name;
				//var value = data[i].value==""?0:data[i].value;
				if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
					value = data[i].value;
					}
				//var sql = data[i].sql;
				//var type = data[i].type;
				var colorSplit = i % 7;
				var url = $ctx + "/lnkhzs/standards/indAjax.jsp?id=" + id + "&name=" + name +  "&value=" + value + "&colorSplit=" + colorSplit;
				html += '<div class ="divcontent" ><div class="titleDiv"><span class="paddingL5">' + name + '</span></div>'
					+ '<div onclick="contentDivbtn(this)" class="contentDiv" id="' + id + '" name="' + name + '">'
					+ '<div id="zhezhao' + id + '" style="z-index:99;width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0);display:block;text-align:center;"></div>'
					+ '<iframe src=' + encodeURI(url) + ' frameborder="0"  scrolling="no"  class="width100p" style="height: 100%;"></iframe></div></div>';
	
	
			}
			$("#divcontentAll").append(html);
			//此处发起AJAX请求
			for (var i = mynum; i < maxlength; i++) {
				var id = data[i].id;
				//var value = data[i].value==""?0:data[i].value;
				if(data[i].value==""||data[i].value==null){
						value = 0;
					}else{
					value = data[i].value;
					}
				var vieworder = data[i].vieworder;
				if (vieworder != null && vieworder > 0) {
					//$("#" + id).unbind("click");
					$("#" + id).removeAttr("onclick");
					//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
					$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv"><img src="../style/image/finish.png" class="add"/></div></div>');
				}else if(value==0 || data[i].value ==null){
					$("#" + id).removeAttr("onclick");
					//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
					$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.0);display:block;text-align:center;"><div class="addDiv" style="color: cyan;"><span style="line-height: 260%">&nbsp;&nbsp;数据未接入</span></div></div>');
				}else {
					//$("#" + id).append('<div style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;background-color: rgba(0,0,0,0.4);display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" class="add"/></div></div>');
				}
			}
			mynum = maxlength;
			Echo.init({
				offset : 0,
				throttle : 0
			});
			
			}
		} */

	</script>
</head>
<body style="height: 100%;">
	<img alt="" class="backgroundPic" />
		<div style="width: 100%; height: 100%;" >
			<div class="divChartStore" id="divcontentAll">
			</div>
		</div>
</body>
</html>