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

		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/quality/tagapproval/getTagDetail.action?tAGApproval.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#question").val(result.worknumber);
				$("#srvReqstTypeFullNm").val(result.worktag);
				$("#bizCntt").val(result.workinfo);
				$("#editTag").val(result.tag);
				$("#changeview").val(result.changeview);
				$("#reportername").val(result.reportername);
				$("#reportstatus").val(result.reportstatus);
				$("#reporttime").val(result.reporttime);
				$("#approvalinfo").val(result.approvalinfo);
				$("#approvalstatus").val(result.approvalstatus);
				$("#approvaltime").val(result.approvaltime);
				$("#approvername").val(result.approvername);
			});
		} else {
			//隐藏保存按钮
			$(".handOut").hide();
		}

	});


	function getTags(a) {
		$("#editTag").val(a)
	}
</script>
<meta charset="UTF-8">
<title>标签详情</title>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div id="formTable" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息
				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">
				<input id="id" type="hidden" name="tAGApproval.id"
					value="${tAGApproval.id }" />
				<div class="form-table">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >工单：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="question" type='text' style="float: left;"
							class='form-control' name='tAGApproval.worknumber' disabled="disabled" />
						
					</div>
					</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>问题分类：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="srvReqstTypeFullNm" type='text' style="float: left;"
							class='form-control' name='tAGApproval.worktag'
							value='${tAGApproval.worktag}' disabled="disabled" />
					</div>
					</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>业务内容：</label>

					</div>
					<div class='ygts col-xs-9'>
						<textarea id="bizCntt" class='txtarea1'
							name='tAGApproval.workinfo' readonly="readonly"></textarea>
					</div>
					</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>要修改的问题分类：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="editTag" type='text' style="float: left;"
							class='form-control' name='tAGApproval.tag'
							value='${tAGApproval.tag}' disabled="disabled"/>
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>调整建议：</label>

					</div>
					<div class='ygts col-xs-9'>
						<textarea id="changeview" class='txtarea1'
							name='tAGApproval.changeview'readonly="readonly"></textarea>

					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >申报人：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="reportername" type='text' style="float: left;"
							class='form-control' name='tAGApproval.reportername' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >申报时间：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="reporttime" type='text' style="float: left;"
							class='form-control' name='tAGApproval.reporttime' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >申报状态：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="reportstatus" type='text' style="float: left;"
							class='form-control' name='tAGApproval.reportstatus' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >审批人：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="approvername" type='text' style="float: left;"
							class='form-control' name='tAGApprovalapprovername' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >审批时间：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="approvaltime" type='text' style="float: left;"
							class='form-control' name='tAGApproval.approvaltime' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >审批状态：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="approvalstatus" type='text' style="float: left;"
							class='form-control' name='tAGApproval.approvalstatus' disabled="disabled" />
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label >审批备注：</label>
					</div>
					<div class='ygts col-xs-9'>
						<textarea id="approvalinfo" class='txtarea1'
							name='tAGApproval.approvalinfo'readonly="readonly"></textarea>
					</div>
					</div>

				</div>
			</form>
		</div>
	</div>

</body>
</html>