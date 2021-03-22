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
	/*
	*控制附件列表显示
	*/
	function viewAttachmentList(pid)
	{
		parent.document.getElementById('rightFrame2').src="${ctx}/ultrabpp/atttemplate/attachModelList.action?pid="+pid;	
	}
	function addModel()
	{
		window.open($ctx + "/ultrabpp/atttemplate/attachTemplateEdit.action?baseSchema=${baseSchema}","","width=500px,height=100px,top=300px,left=400px,Location=no,Toolbar=no,Resizable=yes,scrollbars=no");
	}
	
	function editModel(pid)
	{
		window.open($ctx + "/ultrabpp/atttemplate/attachTemplateEdit.action?baseSchema=${baseSchema}&pid="+pid,"","width=500px,height=100px,top=300px,left=400px,Location=no,Toolbar=no,Resizable=yes,scrollbars=no");
	}
	
	function delModel(obj,templateID)
	{
		//删除模版
		if(window.confirm("删除模版的同时，如果存在与该模版关联的附件，附件也一并删除，您确定要删除该模版吗？")){
           	var ajaxUrl = "${ctx}/ultrabpp/atttemplate/removeAttacheTemplate.action?templateID="+templateID;
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
<div class='page_div_bg'>
	<div class='page_div'>
		<li>
			<input type="button" value="新建模版" onclick="addModel()" />
		</li>
	</div>
</div>
<dg:datagrid  var="model" items="${attachModelList}">
   	<dg:gridtitle>
    	<tr>
			<th>附件模版名称</th>
			<th>操作</th>
		</tr>
   	</dg:gridtitle> 
	<dg:gridrow>
		<tr>
			<td>
				<a href="javascript:viewAttachmentList('${model.pid}')">
				${model.templateName}
				</a>
			</td>
			<td >
				<a href="javascript:;"  onclick="editModel('${model.pid}')">
					编辑
				</a>
				<a href="javascript:;"  onclick="delModel(this,'${model.pid}')">
					删除
				</a>
			</td>
		</tr>
		<script language="javascript">
		if(templateID == "")
		{
			templateID = '${model.pid}';
			//parent.document.getElementById("viewPage").src="${ctx}/ultrabpp/model/templateModelView.action?templateID="+templateID;	
		}
		</script>
	</dg:gridrow>
</dg:datagrid>
</body>
</html>
