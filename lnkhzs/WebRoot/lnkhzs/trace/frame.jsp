<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>溯源问题处理</title>
</head>
	<%
        HashMap<String,String> inmap = new HashMap<String,String>();
        inmap.put("username",user.getLoginName());
        request.setAttribute("inmap", inmap);
     %>
<script type="text/javascript">
$(document).ready(function(){
    var frameHeight = $(document).height() - 87;if(frameHeight < 645) {$(".navbar").css("margin-bottom","0");frameHeight += 10;}
    var url = [
            $ctx + "/lnkhzs/trace/traceSourceTDetail3.jsp?loginname=${inmap.username}",
            $ctx + "/lnkhzs/trace/traceSourceListTab2.jsp?sqlname=SQL_ZL_TRACE_SOURCE_DONE.query&loginname=${inmap.username}",
            $ctx + "/lnkhzs/trace/traceSourceListTab.jsp?sqlname=SQL_ZL_TRACE_SOURCE_REMAIN.query&nextloginname=${inmap.username}"
        ];
    $("nav").bs("navigation", {url: url, label:["我的待办","我的已办","我的待办(批次)" ],show_tips_mode : 'CUSTOM'});
});
</script>
<body>
    <div class="Ct">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <nav role="navigation"></nav>
    </div>
</body>
</html>