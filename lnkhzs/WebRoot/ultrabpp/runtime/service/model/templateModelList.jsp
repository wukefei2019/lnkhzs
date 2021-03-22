<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%! String templateID = "" ;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/common/core/taglibs.jsp"%>
		<%@ include file="/common/plugin/jquery/jquery.jsp"%>
		<script type="text/javascript" src="${ctx}/common/plugin/jquery/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="${ctx}/common/javascript/AjaxBus.js"></script>
		<script type="text/javascript" src="${ctx}/workflow/js/util.js"></script>
<script language="javascript">
	var templateID = "";
	window.onresize = function() 
	{
		setCenter(0,0);
	}
	window.onload = function() 
	{
		setCenter(0,0);changeRow_color("tab");
	}
	function loadModel(templateID)
	{
		parent.opener.loadTemplateFieldValue(templateID);
		parent.close();
	}
	
	function viewTemplateField(templateID)
	{
		parent.document.getElementById("viewPage").src="${ctx}/ultrabpp/model/templateModelView.action?templateID="+templateID;	
	}
	function delModel(obj,templateID)
	{
		//删除模版
		if(window.confirm("您确定要删除该模版吗？")){
           	var ajaxUrl = "${ctx}/ultrabpp/model/removeTemplateModel.action?templateID="+templateID;
        	var bus = new AjaxBus();
            bus.onComplete = function(responseText, responseXml)
            {
            	if(responseText == "true")
            	{
            		var tr=obj.parentNode.parentNode;
            	    var tbody=tr.parentNode;
            	    tbody.removeChild(tr);
            	    alert("删除模版成功!");
            	}else{
            		alert("删除模版失败!");
            	}
            }
            bus.callBackPost(ajaxUrl,null);
        } 
	}
</script>
</head>
<body>
   <dg:datagrid  var="model" items="${templateModelList}">
    	<dg:gridtitle>
	    	<tr>
				<th>模版名称</th>
				<th>操作</th>
				<th>操作</th>
			</tr>
    	</dg:gridtitle> 
		<dg:gridrow>
			<tr>
				<td>
					<a href="javascript:viewTemplateField('${model.pid}')">
					${model.templateName}
					</a>
				</td>
				<td style="width:20%">
					<a href="javascript:;"  onclick="delModel(this,'${model.pid}')">
						删除
					</a>
				</td>
				<td style="width:20%">
					<a href="javascript:;"  onclick="loadModel('${model.pid}');">
						选择
					</a>
                </td>
			</tr>
			<script language="javascript">
			if(templateID == "")
			{
				templateID = '${model.pid}';
				parent.document.getElementById("viewPage").src="${ctx}/ultrabpp/model/templateModelView.action?templateID="+templateID;	
			}
			</script>
		</dg:gridrow>
	</dg:datagrid>
</body>
</html>
