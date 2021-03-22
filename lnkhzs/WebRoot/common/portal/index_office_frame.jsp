<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<title>辽宁移动服务质量管理平台</title>
		<link href="${ctx}/common/style/omcs/tree/css/base/MyReset.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/common/style/omcs/tree/css/common/common.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/common/style/omcs/tree/css/page/main2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/common/style/omcs/tree/js/base/jquery-1.9.1.min.js" ></spript>
		<script type="text/javascript" src="${ctx}/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
			});
			$(window).resize(function(){
			});
		</script>
	</head>
	<body class="bg_gray">
		<div class="banner">
			<div class="logo"></div>
		</div>
		<div class="container">
			<div class="leftMenuTree">
				<div class="left_title icon_jiankong">
					搜索
				</div>
				<div class="left_search_box">
					<input type="text" value="输入查询" class="ipt_search" />
				</div>
				<div class="list">
					<ul class="yiji">
						
					</ul>		
				</div>
			</div>
			<div class="right_container">
				
			</div>
		</div>
		
		
	</body>
</html>
