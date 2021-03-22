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

		//点击保存 
		$(".handOut").on("click", function(event) {
			fn_save();
		});
		//点击确定(查询工单号)
		$(".handOuts").on("click", function(event) {
			fn_saves();
		});

		//修改七级标签
		$("#editTag").on("click", function(event) {
			openwindow($ctx + '/lnkhzs/quality/seleTag.jsp');
		});

		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/quality/tagapproval/getTagDetail.action?tAGApproval.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#question").val(result.worknumber);
				$("#srvReqstTypeFullNm").val(result.worktag);
				$("#bizCntt").val(result.workinfo);
				$("#editTag").val(result.tag);
				$("#changeview").val(result.changeview);
			});
		} else {
			//隐藏保存按钮
			$(".handOut").hide();
		}

	});


	function fn_save() {
		var url = encodeURI($ctx + "/quality/tagapproval/addTag.action");
		$.post(url, $("form").serialize()).done(function(result) {
			if (result == 'success') {
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			} else {
				alert("操作失败");
			}

		}, "json");
	}
	;

	function fn_saves() {
		var url = encodeURI($ctx + "/quality/tagapproval/selectGDH.action");
		$.post(url, $("form").serialize()).done(function(result) {
			if (result == 'error') {
				alert("操作失败,请输入正确工单号");
			} else {
				$("#srvReqstTypeFullNm").val(result.srvReqstTypeFullNm)
				$("#bizCntt").val(result.bizCntt)
				//显示保存按钮
				$(".handOut").show();
			}
		}, "json");
	}

	function getTags(a) {
		$("#editTag").val(a)
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
				<div class="form-table">
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label class="required">工单：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="question" type='text' style="float: left;"
							class='form-control' name='tAGApproval.worknumber' />
						<div class='panel-heading' style="padding-top: 3px;">
							<a class="btn floatRight10 handOuts"
								style="display: inline;float: left;margin-left: 10px;">确定</a>
						</div>
					</div>
					</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>问题分类：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="srvReqstTypeFullNm" type='text' style="float: left;"
							class='form-control' name='tAGApproval.worktag'
							value='${tAGApproval.worktag}' />
						<!-- <div class='panel-heading' style="padding-top: 3px;">
							<a class="btn floatRight10 editTag"
								style="display: inline;float: left;margin-left: 10px;">修改</a>
						</div> -->
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
							value='${tAGApproval.tag}' />
						<!-- <div class='panel-heading' style="padding-top: 3px;">
							<a class="btn floatRight10 editTag"
								style="display: inline;float: left;margin-left: 10px;">修改</a>
						</div> -->
					</div>
					</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">

					<div class='ygts col-xs-3'>
						<label>调整建议：</label>

					</div>
					<div class='ygts col-xs-9'>
						<textarea id="changeview" class='txtarea1'
							name='tAGApproval.changeview'></textarea>

					</div>
					</div>

				</div>
			</form>
		</div>
	</div>

</body>
</html>