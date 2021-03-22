<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%! String templateID = "" ;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/common/core/taglibs.jsp"%>
		<%@ include file="/common/plugin/jquery/jquery.jsp"%>
		<%@ include file="/common/plugin/swfupload/import.jsp"%>
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
	
	function upload()
	{
		var pid = document.getElementById("pid").value;
		if(pid == "")
		{
			alert("请在左侧列表中选择需要关联的模版，上传附件必须关联模版信息，否则无效。");
		}
	}
</script>
</head>
<body>
<input type="hidden" value="${pid}" id="pid" name="pid"/>
<div class='page_div_bg'>
	<div class='page_div'>
		<li>
			<span class="bottom"> 
				<atta:fileupload id="uploadfile"
						attchmentGroupId="${pid}"
						uploadBtnIsVisible="false" fileTypes="*.*"
						uploadTableVisible="false" downTableVisible="false"
						progressIsVisible="false" isMultiUpload="false"
						createDirByDate="false" isMultiDownload="false"
						returnValue="6" operationParams="0,0,0"
						isAutoUpload="true" uploadDestination="attachment_template"
						>
				</atta:fileupload>
			</span>
		</li>
	</div>
</div>
<dg:datagrid  var="model" items="${attachmentList}">
   	<dg:gridtitle>
    	<tr>
			<th>序号</th>
			<th>附件名称</th>
			<th>附件大小</th>
			<th>上传时间</th>
			<th>操作</th>
		</tr>
   	</dg:gridtitle> 
	<dg:gridrow>
		<tr>
			<td>
				<a href="javascript:;">
				${rowindex}	
				</a>
			</td>
			<td>
				<a href="javascript:;">
					${model.name}
				</a>
			</td>
			<td>
				<a href="javascript:;">
					${model.attsize}
				</a>
			</td>
			<td>
				<a href="javascript:;">
					<eoms:date value="${model.createtime}"/>
				</a>
			</td>
			<td>
				<a href='javascript:downloadAttachment("${model.pid}");'>下载</a>&nbsp&nbsp;
				<a href='javascript:deleteAttachment(this,"${model.pid}");'>删除</a>
			</td>
		</tr>
	</dg:gridrow>
</dg:datagrid>
<script>
uploadfile.afterUploadSuccess = function(serverData)
{
	location.reload();
}
function downloadAttachment(pid)
{
 	window.open("${ctx}/attachment/download.action?downloadIds="+pid);
}

function deleteAttachment(obj,pid)
{
	if(confirm("确认删除该附件！"))
	{
		var deleteUrl = "${ctx}/attachment/deleteAttachment.action";
		if(deleteUrl.indexOf('?')==-1)
			deleteUrl += "?attachmentId="+pid;
		else
			deleteUrl += "&attachmentId="+pid;
		
		$.getJSON(deleteUrl,function(responseTest){
			if(responseTest)
			{
				alert("删除成功！");
			}
			else
			{
				alert("删除失败！");
			}
			window.location.reload();
		});
		
	}
}

</script>
</body>
</html>
