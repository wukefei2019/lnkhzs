<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8"></meta>
<title>弹出组织机构树，选择部门</title>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/ztree.custom.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/org/dept/selectWin.js"></script>
<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css" rel="stylesheet" type="text/css" />
        
<script>
var depid = "<%= request.getParameter("depid")%>".split(",");
var single = "${param.single}";
var depname = [];
function ok(row, $element){
	var depid = $("#depid").val();
	var depname = $("#depname").val();
    window.opener.<%= request.getParameter("callback")%>(depid,depname);
    window.close();
}
</script>
</head>
<body>
    <div class="Ct Dialog form-page">
    	<input type='hidden' id='depid' value=''/>
    	<input type='hidden' id='depname' value=''/>
    	<div class='panel panel-primary'>
    		<div class='panel-heading'>
                    <h3 class='panel-title'>组织机构树，选择部门</h3>
                    <a class='btn save floatRight10' onclick="ok()">确定</a>
            </div>
	        <div class="content_wrap tree" >
	            <div class="zTreeDemoBackground2">
	                <ul id="treeDemo" class="ztree"></ul>
	            </div>
	
	        </div>
        
        </div>
    </div>
</body>
</html>
