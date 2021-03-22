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

<link href="${ctx}/common/plugin/bootstrap-3.3.7/dist/css/bootstrap.css" rel="stylesheet">
<link href="${ctx}/common/plugin/bootstrap-table-develop/dist/bootstrap-table.css" rel="stylesheet">
<link href="${ctx}/common/plugin/bootstrap-3.3.7/dist/css/bootstrap-switch.css" rel="stylesheet">
<link href="${ctx}/common/plugin/bootstrap-3.3.7/dist/css/bootstrap.custom.css" rel="stylesheet">
<link href="${ctx}/common/style/omcs/css/base/MyReset.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/common/common.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/common/nav.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/common/ui-select.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/page/main2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/page/form.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/style/omcs/css/page/step2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/common/plugin/fonticon/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
    var $ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/base/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/common/plugin/bootstrap-3.3.7/dist/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/common/plugin/bootstrap-table-develop/dist/bootstrap-table.js"></script>
<script type="text/javascript" src="${ctx}/common/plugin/bootstrap-table-develop/dist/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="${ctx}/common/plugin/bootstrap-3.3.7/dist/js/bootstrap.autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/common/plugin/bootstrap-3.3.7/dist/js/bootstrap.custom.js"></script>
<%-- <script type="text/javascript" src="${ctx}/common/plugin/bootstrap-3.3.7/dist/js/bootstrap-switch.js"></script> --%>
<script type="text/javascript" src="${ctx}/common/javascript/selectTag.js"></script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/placeholder.js"></script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/Event.js" ></script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/layer/layer.js" ></script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/changeTab.js" ></script>
<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/ui-select.js" ></script>
<%-- <script type="text/javascript" src="${ctx}/common/style/omcs/js/common/main.js" ></script> --%>
<script type="text/javascript" src="${ctx}/common/javascript/datagrid_omcs.js"  defer="defer"></script>
<script src="${ctx}/common/javascript/main.js"></script>

<script type="text/javascript" language="javascript" src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>

<%
UserSession user = (UserSession)session.getAttribute("userSession");
session.setAttribute("user", user);
String userCityId = user.getCityId();
String userName = user.getLoginName();
String cityName = user.getCityName();

%>


