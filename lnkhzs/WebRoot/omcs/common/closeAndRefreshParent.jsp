<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/core/taglib_omcs.jsp"%>
		<script type="text/javascript">
		alert("保存成功");
		window.opener.location.href=window.opener.location.href; 
		window.close(); 
		</script>
	</head>
</html>
