<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>“全局流转”标签投诉量监控报表</title>
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
            $ctx + "/lnkhzs/quality/complaintFour.jsp",
            $ctx + "/lnkhzs/quality/complaintSeven.jsp"
        ];
    $("nav").bs("navigation", {url: url, label:["1-4级(标签含“全局流转”)" ,"1-7级(标签含“全局流转”)"],show_tips_mode : 'CUSTOM'});
});
</script>
<body>
    <div class="Ct">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <nav role="navigation"></nav>
    </div>
</body>
</html>