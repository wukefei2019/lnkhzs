<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>移动客户满意度调研</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<meta name="viewport"
	content="width=device-width; initial-scale=1.4; minimum-scale=1.0; maximum-scale=2.0" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">

	//getAlert();


	function getQueryVariable(variable) {
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			if (pair[0] == variable) {
				return pair[1];
			}
		}
		return "";
	}

	if (confirm("【尊敬的用户，您好，感谢您参与调研，若您的问卷显示出现问题，请进行浏览器升级或换成其他浏览器，多谢配合！】")) {

		window.location.href='/lnfwzl/lnkhzs/survey/dist_show/index.html?id=' + getQueryVariable("id") + '&userid=' + getQueryVariable("userid");
		  //openwindow($ctx + '/lnkhzs/survey/dist_show/index.html?id=' + getQueryVariable(id) + '&userid=' + getQueryVariable(userid), '');
	} 
</script>

</head>

<body>









</body>
</html>
