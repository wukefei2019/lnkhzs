<%@ page import="com.ultrapower.eoms.common.portal.model.UserSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/atreerule.tld" prefix="atrule"%>
<%@ taglib uri="/WEB-INF/atttemplate.tld" prefix="atttemplate"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms"%>
<%@ taglib uri="/WEB-INF/attachment.tld" prefix="atta"%>
<%@ taglib uri="/WEB-INF/datagrid" prefix="dg"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setBundle basename="i18n.Messages" var="i18Bundle"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=8,9,10,11" />



<script type="text/javascript">
    var $ctx="${ctx}";
    function pageReload(){
  	  window.location.reload();
    }
</script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/base/jquery-1.9.1.min.js"></script>

<script src="${ctx}/common/javascript/main.js"></script>
<%
UserSession user = (UserSession)session.getAttribute("userSession");
session.setAttribute("user", user);
String userCityId = user.getCityId();
String userName = user.getLoginName();
String cityName = user.getCityName()==null?"":user.getCityName();

%>


