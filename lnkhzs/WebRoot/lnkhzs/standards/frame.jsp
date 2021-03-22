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
            $ctx + "/lnkhzs/standards/comAnaly.jsp",
            $ctx + "/lnkhzs/standards/comAnaly2.jsp",
            $ctx + "/lnkhzs/standards/comAnaly3.jsp",
            $ctx + "/lnkhzs/standards/comAnaly4.jsp",
            $ctx + "/lnkhzs/standards/comAnaly5.jsp",
            $ctx + "/lnkhzs/standards/comAnaly6.jsp",
            $ctx + "/lnkhzs/standards/comAnaly7.jsp",
            
        ];
    $("nav").bs("navigation", {url: url, label:["家宽" ,"魔百和","IDC","集团专线","物联网卡","云","ICT"],show_tips_mode : 'CUSTOM'});
});
</script>
<body>
    <div class="Ct">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <nav role="navigation"></nav>
    </div>
</body>
</html>