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
		$(".handOut").on("click", function(event) {
			fn_save(1);
		});

		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/quality/tagapproval/getDetail.action?tAGApproval.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#reporter").val(result.reporter);
				$("#reportername").val(result.reportername);
				$("#reporttime").val(result.reporttime);
				$("#reportstatus").val(result.reportstatus);
				$("#worknumber").val(result.worknumber);
				$("#worktag").val(result.worktag);
				$("#workinfo").val(result.workinfo);
				$("#tag").val(result.tag);
				$("#changeview").val(result.changeview);
				$("#approver").val(result.approver);
			});
		}

	});

	function fn_save(optinon) {
		var url = encodeURI($ctx + "/quality/tagapproval/updateTagApproval.action");
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
				<input id="id" type="hidden" name="tAGApproval.id"
					value="${tAGApproval.id }" />
				<input id="reporter" type="hidden" name="tAGApproval.reporter"
					value="${tAGApproval.reporter }" />
					<input id="reportername" type="hidden" name="tAGApproval.reportername"
					value="${tAGApproval.reportername }" />
				<input id="reporttime" type="hidden" name="tAGApproval.reporttime"
					value="${tAGApproval.reporttime }" />
				<input id="reportstatus" type="hidden" name="tAGApproval.reportstatus"
					value="${tAGApproval.reportstatus }" />
				<input id="worknumber" type="hidden" name="tAGApproval.worknumber"
					value="${tAGApproval.worknumber }" />
				<input id="worktag" type="hidden" name="tAGApproval.worktag"
					value="${tAGApproval.worktag }" />
				<input id="workinfo" type="hidden" name="tAGApproval.workinfo"
					value="${tAGApproval.workinfo }" />
				<input id="tag" type="hidden" name="tAGApproval.tag"
					value="${tAGApproval.tag }" />
				<input id="changeview" type="hidden" name="tAGApproval.changeview"
					value="${tAGApproval.changeview }" />	
				<input id="approver" type="hidden" name="tAGApproval.approver"
					value="${tAGApproval.approver }" />	
	
				<div class="form-table">
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label>审批结果：</label>
					</div>
					<div class='ygts col-xs-9'>
						<select id="approvalstatus" style="width: 100%" class='form-control'
							name='tAGApproval.approvalstatus'>
							<option value="通过">通过</option>
							<option value="拒绝">拒绝</option>
						</select>
					</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">备注：</label>
					</div>
					<div class='col-xs-9'>
						<input id="approvalinfo" type='text' style="width: 100%"
							class='form-control' name='tAGApproval.approvalinfo' />
					</div>
				</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>