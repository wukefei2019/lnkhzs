<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<%@ include file="/common/core/taglibs.jsp"%>
	<%@ include file="/common/plugin/jquery/jquery.jsp"%>
	<script type="text/javascript" src="${ctx}/common/plugin/jquery/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="${ctx}/common/javascript/AjaxBus.js"></script>
	<script type="text/javascript" src="${ctx}/workflow/js/util.js"></script>
	<script language="javascript">
	window.onresize = function() 
	{
		setCenter(0,0);
	}
	
	window.onload = function() 
	{
		setCenter(0,0);
	}
	</script>
	</head>
<body>
<div class='page_div_bg'>
		<div class='page_div'>
			模版描述：${templateDesc}
		</div>
</div>
<div class='scroll_div' id='center'>
	<table id='tab' class='tableborder'>
		<tr>
			<th colspan="4">主表单字段</th>
		</tr>
		<c:forEach var="mainField" items="${templateFieldMainList}">
		<tr>
			<td colspan="2">${mainField.fieldLabel}</td>
			<td colspan="2">${mainField.fieldValue}</td>
		</tr>
		</c:forEach>
		<tr>
			<th colspan="4">动作字段</th>
		</tr>
		<c:forEach var="actionField" items="${templateFieldActionList}">
		<tr>
			<td colspan="2">${actionField.fieldLabel}</td>
			<td colspan="2">${actionField.fieldValue}</td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>