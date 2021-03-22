<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <%@ include file="/common/core/taglibs.jsp"%>
	<%@ include file="/common/plugin/jquery/jquery.jsp" %>
		<script type="text/javascript" src="${ctx}/common/plugin/jquery/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="${ctx}/common/javascript/AjaxBus.js"></script>
		<script type="text/javascript" src="${ctx}/workflow/js/util.js"></script>
	<title>
	</title>
<script language="javascript">
    
    var baseSchema = "";
    var baseStatus = "";
    var stepNo = "";
    
    window.onload = function(){
        setCenter(0,61); 
	}

	window.onresize = function(){
		setCenter(0,61);
	}
	
	function formSubmit()
	{
	     var templateName = document.getElementById("attachModel.templateName").value;
		 var pid = document.getElementById("attachModel.pid").value;
		 var bl = Validator.Validate(fieldForm,2,false);  
		 if(bl){
		 $.get("${ctx}/ultrabpp/atttemplate/checkTemplateName.action?stamp="+new Date().getTime()+"&baseSchema=${baseSchema}&templateName="+encodeURI(encodeURI(templateName))+"&pid="+pid,function(result)
			{
				if(result == 'false'){
					alert("模版名已经存在");
				}else{
					var s=document.getElementById('fieldForm').submit(); 
				}
			});
		} 
	}
</script>
</head>
<body>
<form action="${ctx}/ultrabpp/atttemplate/saveAttachTemplatModel.action" name="fieldForm" method="post" id="fieldForm">
<input type="hidden" name="attachModel.baseSchema" id="attachModel.baseSchema" value="${attachModel.baseSchema}"/>
<input type="hidden" name="baseSchema" id="baseSchema" value="${baseSchema}"/>
<input type="hidden" name="attachModel.pid" id="attachModel.pid" value="${attachModel.pid}"/>
<div class="content">
		<div class="title_right">
			<div class="title_left">
				<span class="title_bg">
						<span class="title_icon2">
							<eoms:lable name="bpp_develop_worksheet_additional_occupyingtext"/>
						</span>
				</span>
				<span class="title_xieline"></span>
			</div>
		</div>
        <div class="scroll_div" id="center">
				 <div class="tabContent_top">
					<table class="add_user">
						<tr>
							<td class="texttd">
							    模版名称：<span class="must">*</span>
							</td>
							<td>
							    <input type="text" name="attachModel.templateName" id="attachModel.templateName" value="${attachModel.templateName}"class="textInput" maxlength="50"/>
							    <validation id="attachModel.templateNameV" require="true" dataType="Require" Max="100" msg="模版名称不能为空，长度不能超过50" />
							</td>
						</tr>
					 </table>
				</div>
		</div>
		<div class="add_bottom">
			<input type="button" value="<eoms:lable name="com_btn_save"/>" class="save_button" onmouseover="this.className='save_button_hover'" onmouseout="this.className='save_button'" onclick="formSubmit();" />
			<input type="button" value="<eoms:lable name="com_btn_cancel"/>" class="cancel_button" onmouseover="this.className='cancel_button_hover'" onmouseout="this.className='cancel_button'" onclick="window.close();" />
		</div>
	</div>
</form>
</body>
</html>