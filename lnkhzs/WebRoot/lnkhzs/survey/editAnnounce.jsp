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

//页面初始化
$(document).ready(function() {
	
		//getDep();
	
		//$("#showUsers").on("click",fn_to_user).show();
		
		$(".handOut").on("click", function(event) {
			fn_save(1);
		});
		
		getDetail();
				
	


	});
	
	
	function getDetail(){ 
		var id = "${param.id}";
		if (id.length != 0) {
		
			$.post($ctx + "/khzs/surveys/announce/getAnnounceDetail.action?khzsQuestionAnnounce.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#content").val(result.content);
				$("#time").val(result.time);
				$("#status").val(result.status);			
			});
		}
	}
	
	
	function getEdit(){
		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/khzs/surveys/announce/editAnnounce.action?khzsQuestionAnnounce.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#content").val(result.content);
				$("#time").val(result.time);
				$("#status").val(result.status);			
			});
		}
	}
	

	function fn_save() {
		var url = encodeURI($ctx + "/khzs/surveys/announce/saveAnnounce.action");
		/* console.log( $("form").serialize()); */
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
<title>公告信息</title>
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
				<input id="id" type="hidden" name="khzsQuestionAnnounce.id"
					value="${khzsQuestionAnnounce.id }" />
				<div class="form-table">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">公告内容：</label>
					</div>
					<div class='col-xs-9'>
						<input id="content" type='text' style="width: 100%"
							class='form-control' name='khzsQuestionAnnounce.content'
							value='${khzsQuestionAnnounce.content}' />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="diva">
						<label>有效时间：</label>
					</div>
					<div class='col-xs-9' name="diva">
						<input id="time" type='text' style="width: 100%"
							class='form-control' name='khzsQuestionAnnounce.time'
							value='${khzsQuestionAnnounce.time}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divb">
						<label>状态：</label>
					</div>
					<div class='col-xs-9' name="divb">
						<input id="status" type='text' style="width: 100%"
							class='form-control' name='khzsQuestionAnnounce.status'
							value='${khzsQuestionAnnounce.status}' />
					</div>
					</div>
					
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>