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

		$("#changepeoplename").on("click", function(event) {
			openwindow($ctx + '/org/person/selectWin.jsp?single=1&callback=setUser');
		});

		$(".handOut").on("click", function(event) {
			fn_save(1);
		});

		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/quality/tagadmin/getDetail.action?tAGAdmin.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#changepeople").val(result.changepeople);
				$("#changepeoplename").val(result.changepeoplename);

			});
		}

	});

	function fn_save(optinon) {
		var url = encodeURI($ctx + "/quality/tagadmin/updateTagApproval.action");
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
	
	 function setUser(all_selection) {
		var userName = all_selection[0].FULLNAME;
		var userId = all_selection[0].LOGINNAME;
		
		$("#changepeoplename").val(userName);
		$("#changepeople").val(userId);
	}
</script>
<meta charset="UTF-8">
<title>标签修改审批</title>
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
				<input id="id" type="hidden" name="tAGAdmin.id"
					value="${tAGAdmin.id }" /> 
				<input id="changepeople" type="hidden" name="tAGAdmin.changepeople"
					value="${tAGAdmin.changepeople }" /> 
					
					<div class="form-table">
					<div class='col-xs-3'>
						<label class="required">修改人员：</label>
					</div>
					<div class='col-xs-9'>
						<input id="changepeoplename" type='text' style="width: 100%"
							class='form-control' name='tAGAdmin.changepeoplename' />
					</div>
				</div>


			</form>
		</div>
	</div>
</body>
</html>