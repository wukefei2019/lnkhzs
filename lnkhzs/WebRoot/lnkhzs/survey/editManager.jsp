<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript"
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

<script type='text/javascript'>


	$(document).ready(function() {
	
		getDep();
	
		$("#showUsers").on("click",fn_to_user).show();
		
		$(".handOut").on("click", function(event) {
			fn_save(1);
		});


	});
	
	function fn_to_user(){
    	openwindow($ctx + '/org/person/selectWin.jsp?callback=setDutyUser&single=1','');
	}
	
	function getDetail(){
		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/khzs/surveys/manager/getManagerDetail.action?khzssurvermanager.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#administratorid").val(result.administratorid);
				$("#administratorname").val(result.administratorname);
				$("#cityname").val(result.cityid);			
			});
		}
	}
	
	var setDutyUser = function(rows,fn){
		$("[name='khzssurvermanager.administratorname']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    
    	$("#administratorid").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));
    }

	function fn_save(optinon) {
		var url = encodeURI($ctx + "/khzs/surveys/manager/saveManager.action");
		$.post(url, $("form").serialize()).done(function(result) {
			if (result == 'success') {
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			} else if (result == 'error') {
				alert("操作失败,不能为空!");
			} else {
				alert("操作失败");
			}

		}, "json");

	}
	
	function getDep() {
			var url = $ctx + "/tsgd/resend/getDept.action";
			$.post(url).done(function(data) {
				
				$("#cityname").append("<option value=''></option>")
				for (var i = 0; i < data.length; i++) {
					$("#cityname").append("<option value='" + data[i].pid + "'>" + data[i].depname + "</option>")
				}
				getDetail();
			});
		}


</script>
<meta charset="UTF-8">
<title>修改管理员配置</title>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div id="formTable" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息 <a class='btn floatRight10 handOut' style='display: inline;'>保存</a>
				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">
				<input id="id" type="hidden" name="khzssurvermanager.id"
					value="${khzssurvermanager.id }" />
				<input id="administratorid" type="hidden" name="khzssurvermanager.administratorid"
					value="${khzssurvermanager.administratorid }" />
				<div class="form-table">
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">用户名：</label>
					</div>
					<div class='col-xs-9' id="showUsers">
					
							<input type='text' id="administratorname" class='inputText1 width160' name='khzssurvermanager.administratorname'
								value='${khzssurvermanager.administratorname}' style="width:70%;" /><span
								class="glyphicon glyphicon-plus" style="margin-left: 13px;"></span>
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">部门：</label>
					</div>
					<div class='col-xs-9'>
						<%-- <input id="cityname" type='text' style="width: 100%"
							class='form-control' name='khzssurvermanager.cityname'
							value='${khzssurvermanager.cityname}' /> --%>
							<select id="cityname" 
								class='form-control' name='khzssurvermanager.cityid'></select>
					</div>
					</div>
			</form>
		</div>
	</div>
</body>
</html>