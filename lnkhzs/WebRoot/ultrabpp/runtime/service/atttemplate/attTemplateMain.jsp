<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<FRAMESET  COLS="25%,2,*" border="0" framespacing="0">
    <FRAME src="<%=request.getContextPath()%>/ultrabpp/atttemplate/attachTemplateList.action?baseSchema=<%=request.getParameter("baseSchema")%>" id="leftFrame2" name="leftFrame2" scrolling="no" frameborder="0" noresize>
    <FRAME src="<%=request.getContextPath()%>/ultrabpp/runtime/service/atttemplate/attTemplate_center.jsp" scrolling="auto" name="centerFrame2" id="centerFrame2" frameborder="0" noresize>
    <FRAME src="<%=request.getContextPath()%>/ultrabpp/atttemplate/attachModelList.action" scrolling="auto" name="rightFrame2"   id="rightFrame2" frameborder="0">
</FRAMESET>
<noframes> <body>Your browser dosen't support frames!</body></noframes>
</HTML>