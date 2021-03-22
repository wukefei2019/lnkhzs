<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/common/core/taglibs.jsp"%>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
		var baseSchema = "";
	    var baseStatus = "";
	    var stepNo = "";
	    window.onload = function(){
	        init();
		}
	    function init()
		{
			var url = new Url(location.search);
			baseSchema = url.getvalue("baseSchema");
			baseStatus = url.getvalue("baseStatus");
			stepNo = url.getvalue("stepNo");
			document.getElementById("listPage").src="${ctx}/ultrabpp/model/templateModelList.action?baseSchema="+baseSchema+"&baseStatus="+encodeURI(encodeURI(baseStatus))+"&stepNo="+stepNo;
		}
		</script>
	</head>
<frameset cols="70%,*" border="1" > 
   <frame   name="viewPage" id="viewPage"  scrolling="auto"  src=""/>  
   <frame   name="listPage" id="listPage"  scrolling="auto"   src=""/>  
</frameset>
<body>
</div>
</body>
</html>