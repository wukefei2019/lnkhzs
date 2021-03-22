<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>新增信息</title> <!--<style type='text/css'>
.inputText1{
 height: 24px;
   width:800px;
 line-height: 24px;
 border: 1px solid #ddd;
 border-radius: 2px;
 padding-left: 10px;
}
</style> -->
	<script type="text/javascript" src="${ctx }/lnkhzs/trace/addManager.js"></script>
	<script type='text/javascript'>
		function getDep() {
			var url = $ctx + "/tsgd/resend/getDept.action";
			$.post(url).done(function(data) {
				$("#maindepart").append("<option value=''></option>")
				for (var i = 0; i < data.length; i++) {
					$("#maindepart").append("<option value='" + data[i].pid + "'>" + data[i].depname + "</option>")
				}
				
			});
		}
		;
	
		$(document).ready(function() {
			getDep();
	
	
		});
	</script>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>新增配置信息</h3>
				<a class='btn floatRight10' onclick='saveAdd()'>保存</a>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
			<form id='form' name='form'
				action='${ctx}/trace/traceManager/saveManager.action' method='post'
				target="frm">
				<input type="hidden" name="traceManager.pid"
					value="${traceManager.pid }" />
				<!-- <table class="form-table table-bordered"> -->

						<input id="maindepartid" style="width: 100%" class='form-control'
							name='wFresend.maindepartid' value='${wFresend.maindepartid}'
							type="hidden" />
							<input type="hidden" name="traceManager.depname"
								value="${traceManager.depname }" />	

					<div class="form-table">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3' style="margin-bottom: -10px;">
							<label class="required" >机构名称：</label>
						</div>
						<div class='col-xs-9'>
							<select id="maindepart" 
								class='form-control' name='traceManager.depevel2id'></select>
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">人员：</label>
						</div>
						<div class='col-xs-9' id="showUsers">
							<input type='text' class='inputText1 width160' name='traceManager.fullname'
								value='${traceManager.fullname}' style="width: 70%;"  /><span
								class="glyphicon glyphicon-plus" ></span>
							<input type="hidden" name="traceManager.loginname"
								value="${traceManager.loginname }" />		
						</div>
					</div>
					</div>

			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function saveAdd() {

		$.post($ctx + '/trace/traceManager/saveManager.action', $("form").serializeArray()).done(function(result) {
			if (result == 'success') {
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			} else {
				alert("二级部门管理员已存在");
			}
		});
	}
</script>
</html>