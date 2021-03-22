<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title>客户之声信息</title>
</head>

<script type="text/javascript">
$(document).ready(function(){
    var frameHeight = $(document).height() - 87;if(frameHeight < 645) {$(".navbar").css("margin-bottom","0");frameHeight += 10;}
    var url = [
            $ctx + "/lnkhzs/khzs/alcl.jsp?sqlname=SQL_KHZS.query",
            $ctx + "/lnkhzs/khzs/alcl2.jsp?sqlname=SQL_KHZSED.query"
        ];
    $("nav").bs("navigation", {url: url, label:["我的待办" ,"我的已办"],show_tips_mode : 'CUSTOM'});
});
</script>
<body>
    <div class="Ct Dialog">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <nav role="navigation"></nav>
    </div>
</body>
</html>