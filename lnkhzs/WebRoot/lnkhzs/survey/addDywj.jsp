<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ultrapower.eoms.common.core.util.UUIDGenerator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/common/core/taglib_omcs.jsp"%>

<head>
<meta charset="UTF-8">
	<title>客户之声信息</title> <script type="text/javascript"
		src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>

	<script type="text/javascript"
		src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/json2.js"></script>
	<script type="text/javascript"
		src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.js"></script>
	<script type="text/javascript"
		src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/handlers.js"></script>
	<script type="text/javascript"
		src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/ultraswfupload.js"></script>
	<script type="text/javascript"
		src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField.js"></script>


	<link
		href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/ultraupload.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload.css"
		rel="stylesheet" type="text/css" />
	<link
		href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload_new.css"
		rel="stylesheet" type="text/css" />
	<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css"
		rel="stylesheet" type="text/css" />
</head>
<script type='text/javascript'>
     
    $(document).ready(function(){ 
		$(".handOut").on("click", function(event) {
	    	$("#opinion").val("提交");
	    	fn_save();
		});
		var volid="${param.id}";
		var msg="${param.msg}";
		if(msg==1){
		$.post($ctx + "/khzs/survey/ajaxGetDywj.action?khzsQuestionnaire.id="+volid).done(function(result) {
				$("#name").val(result.name);
				$("#status").val(result.status);
				$(".handOut").hide();
				$('input[type="text"]').attr('disabled','disabled');
				$("#statuss").attr("disabled", true);
				 $("#id").val(volid);
			 });
		}
		
		
		if(volid.length != 0 && msg != 1 ){
		//修改
			 $.post($ctx + "/khzs/survey/ajaxGetDywj.action?khzsQuestionnaire.id="+volid).done(function(result) {
				$("#name").val(result.name);
				$("#status").val(result.status);
				//$(".handOut").hide();
				//$('input[type="text"]').attr('disabled','disabled');
				//$("#statuss").attr("disabled", true);
				 $("#id").val(volid);
			 });
		}
		//添加		
    	//$(".handOut").show();
    });
    
    function fn_save() {

       	 var url =encodeURI($ctx + "/khzs/survey/doAction.action");
     $.post(url,$("form").serialize()).done(function(result) {
    		if (result == 'success') {
    			alert("操作成功");
    				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
    				 window.close();
    		} else if(result == 'error'){
    			alert("操作失败,不能为空!");
    		}else {
    			alert("操作失败");
    		}
    	}, "json"); 
    	
    }

    </script>

<body>
	<div class="Ct Dialog contract form-page">

		<div id="formTable" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息 <a data-toggle="collapse" href="#form"
						style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
					<a class='btn floatRight10 handOut' style='display: inline;'>提交</a>
				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">
			 <input id="id" type="hidden" name="khzsQuestionnaire.id" value="${khzsQuestionnaire.id}" /> 
			<%--  <input	id="name" type="hidden" name="khzsQuestionnaire.name"
					value="${khzsQuestionnaire.name}" />  --%>
					<input id="creattime"
					type='hidden' name='khzsQuestionnaire.creattime'
					value='${khzsQuestionnaire.creattime}' /> 
					<input id="status"
					type="hidden" name="khzsQuestionnaire.status"
					value="${khzsQuestionnaire.status}" />

				<div class="form-table">
					<div class='col-xs-3'>
						<label class="required">问卷名：</label>
					</div>
					<div class='col-xs-9'>
						<input id="name" type='text' style="width:100%"
							class='form-control' name='khzsQuestionnaire.name'
							value='${khzsQuestionnaire.name}' />
					</div>

				</div>
			</form>
		</div>

	</div>
</body>
</html>
