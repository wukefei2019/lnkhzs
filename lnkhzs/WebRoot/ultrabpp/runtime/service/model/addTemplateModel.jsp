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
        init();
	}

	window.onresize = function(){
		setCenter(0,61);
	}
	
	function init()
	{
		var url = new Url(location.search);
		baseSchema = url.getvalue("baseSchema");
		baseStatus = url.getvalue("baseStatus");
		stepNo = url.getvalue("stepNo");
	}
	function formSubmit()
	{
		var bpp_TemplateName = document.getElementById("bpp_TemplateName").value;
		var bpp_TemplateAtt = document.getElementById("bpp_TemplateAtt").checked;
		var bpp_TemplateDesc = document.getElementById("bpp_TemplateDesc").value;
		
		var bl = Validator.Validate(fieldForm,2,false); 
		if(bl)
		{
			var ajaxUrl = "${ctx}/ultrabpp/model/checkTemplateName.action";
			var ajaxParas = "?baseSchema="+baseSchema+"&baseStatus="+encodeURI(encodeURI(baseStatus))+"&stepNo="+stepNo+"&templateName="+encodeURI(encodeURI(bpp_TemplateName));
			ajaxUrl += ajaxParas;
			var bus = new AjaxBus();
            bus.onComplete = function(responseText, responseXml)
            {
            	if(responseText == "true")
            	{
            		//校验
        			opener.addTemplate(bpp_TemplateName, bpp_TemplateAtt, bpp_TemplateDesc);
        			window.close();
            	}else{
            		alert("模版名称已经存在，请修改模版名称！");
            	}
            }
            bus.callBackPost(ajaxUrl,null);
		}
	}
</script>
</head>
<body>
<form action="" name="fieldForm" method="post" id="fieldForm">
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
									    <input type="text" name="bpp_TemplateName" id="bpp_TemplateName" class="textInput" maxlength="50"/>
									    <validation id="bpp_TemplateNameV" require="true" dataType="Require" Max="100" msg="模版名称不能为空，长度不能超过50" />
									</td>
								</tr>
								<tr>
									<td class="texttd">
									包含附件：<span class="must">*</span>
									</td>
									<td>	
									<input type="checkbox" id="bpp_TemplateAtt" name="bpp_TemplateAtt"   checked/>
									</td>
								</tr>
								<tr>
									<td class="texttd">
									模版描述：<span class="must">*</span>
									</td>
									<td>	
									<textarea name="bpp_TemplateDesc" id="bpp_TemplateDesc" cols="60" rows="4"></textarea>
									<validation id="bpp_TemplateDescV" require="true" dataType="Require" Max="1000" msg="模版描述不能为空，长度不能超过1000" />
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