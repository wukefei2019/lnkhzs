<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/common/core/taglibs.jsp"%>
		<title>事件规则添加修改</title>
		<script type="text/javascript" src="${ctx}/common/plugin/jquery/jquery-1.3.2.js"></script>
		<script language="javascript">
		window.onresize = function() 
		{
			setCenter(0, LAYOUT_FORM_OPEN);
		}
		window.onload = function() 
		{
			setCenter(0, LAYOUT_FORM_OPEN);
		}
		function formSubmit()
		{   		    
			var myform = document.forms[0];				
			if(Validator.Validate(myform, 2)) {
				myform.submit();
			}
		}
	</script>
	</head>

	<body>
	 	<form name="euform" action="${ctx}/interceptConfig/saveInterceptConfig.action" method="post" onsubmit="return Validator.Validate(this,2);">
	 		<input type="hidden" id="pid" name="interceptConfig.pid" value="${interceptConfig.pid }"/>
			<div class="content">
				<div class="title_right">
					<div class="title_left">
						<span class="title_bg"> <span class="title_icon2">URL日志拦截</span>
						</span>
						<span class="title_xieline"></span>
					</div>
				</div>
				<div class="add_scroll_div_x" id="center">
					<fieldset class="fieldset_style">
						<legend>URL日志拦截</legend>
						<div class="blank_tr"></div>
						<div class="tabContent_top">
							<table class="add_user">
								<tr>
									<td>
										  功能名称 ：<span class="must">*</span>
									</td>
									<td colspan="3">
										<input type="text" name="interceptConfig.functionname" id="functionname" class="textInput" style="width:98.5%;" value="${interceptConfig.functionname}"/>
									    <validation id="interceptConfig.functionnameV" dataType="Limit" min="1" max="33" msg="功能名称 不能为空且不可以超过33个字！" />
									</td>
								</tr>
								
								<tr>
									<td>
										  URL地址 ：<span class="must">*</span>
									</td>
									<td colspan="3">
										<input type="text" name="interceptConfig.urlpath" id="urlpath" class="textInput" style="width:98.5%;" value="${interceptConfig.urlpath}"/>
									    <validation id="interceptConfig.urlpathV" dataType="Limit" min="1" max="300" msg="URL地址 不能为空且不可以超过333个字！" />
									</td>
								</tr>
								
								<tr>
									<td style="width:15%">
										参数1：
									</td>
									<td style="width:35%">
										<input type="text" id="param1" name="interceptConfig.param1" class="textInput" style="width:98.5%;" value="${interceptConfig.param1 }"/>
										<validation id="interceptConfig.param1V" dataType="Limit" min="0" max="300" msg="参数1不能超过33个字！" />
									</td>
									<td style="width:15%">
										参数2：
									</td>
									<td style="width:35%">
										<input type="text" id="param2" name="interceptConfig.param2" class="textInput" style="width:98.5%;" value="${interceptConfig.param2 }"/>
										<validation id="interceptConfig.param2V" dataType="Limit" min="0" max="300" msg="参数2不能超过33个字！" />
									</td>
								</tr>
								<tr>
									<td style="width:15%">
										参数3：
									</td>
									<td style="width:35%">
										<input type="text" id="param3" name="interceptConfig.param3" class="textInput" style="width:98.5%;" value="${interceptConfig.param3 }"/>
										<validation id="interceptConfig.param3V" dataType="Limit" min="0" max="300" msg="参数3不能超过33个字！" />
									</td>
								</tr>
							</table>
						</div>
					</fieldset>
				</div>
			</div>
			<div class="add_bottom">
				<input type="button" value="<eoms:lable name="com_btn_save"/>" 
					class="save_button" onmouseover="this.className='save_button_hover'"
					onmouseout="this.className='save_button'" onclick="formSubmit();"/>
				<input type="button" value="<eoms:lable name="com_btn_cancel"/>"
					class="cancel_button"
					onmouseover="this.className='cancel_button_hover'"
					onmouseout="this.className='cancel_button'"
					onclick="window.close();" />
			</div>
		</form>
	</body>
</html>