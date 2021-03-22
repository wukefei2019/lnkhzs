<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>转派</title> <!--<style type='text/css'>
</style> -->
	<script type="text/javascript" src="${ctx }/lnkhzs/trace/addRelay.js"></script>
	<script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>
    <script type="text/javascript" src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/json2.js"></script>
	<script type="text/javascript" src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.js"></script>
	<script type="text/javascript" src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/handlers.js"></script>
	<script type="text/javascript" src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/ultraswfupload.js"></script>
	<script type="text/javascript" src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField.js"></script>
	
	<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/ultraupload.css"    rel="stylesheet" type="text/css" />
	<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload.css"    rel="stylesheet" type="text/css" />
	<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload_new.css"    rel="stylesheet" type="text/css" />
	<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript'>
/* 	$(document).ready(function(){
		$(".button_selectfile").hide();
	}) */
	</script>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>转派信息</h3>
				<a class='btn floatRight10' onclick='relay()'>转派</a>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
				<form id='form' name='form' target="frm" class="collapse in">
					<input type="hidden" name="traceSourceList.pid"	value="${traceSourceFlow.sourcepid}" />
					<input type="hidden" name="actionStr"	value="转派" />
					<input type="hidden" name="selectUser.loginName" value="${selectUser.loginName }" />	
					<div class="form-table">
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class='col-xs-3'>
								<label class="required">转派人员：</label>
							</div>
							<div class='col-xs-9' id="showUsers">
								<input type='text' class='inputText1 width160' name='selectUser.fullName'
									value='${selectUser.fullName}' style="width: 70%;"  /><span
									class="glyphicon glyphicon-plus" ></span>
							</div>
							<div class='col-xs-3'>
								<label class="required">问题原因：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="problem" class='txtarea1'	name='traceSourceList.problem' >${traceSourceList.problem}</textarea>
							</div>
							<div class='col-xs-3'>
								<label class="required">整改目标：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="rectificationGoal" class='txtarea1'	name='traceSourceList.rectificationGoal' >${traceSourceList.rectificationGoal}</textarea>
							</div>
							<div class='col-xs-3'>
								<label class="required">解决进度-未完成具体原因：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="notFinishedReason" class='txtarea1'	name='traceSourceFlow.notFinishedReason' >${traceSourceFlow.notFinishedReason}</textarea>
							</div>
							<div class='col-xs-3'>
								<label>未完成详细描述：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="notCompletedDescription" class='txtarea1'	name='traceSourceFlow.notCompletedDescription' >${traceSourceFlow.notCompletedDescription}</textarea>
							</div>
							<div class='col-xs-3'>
								<label class="required">落实责任单位：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="responsibleUnit" class='txtarea1'	name='traceSourceFlow.responsibleUnit' >${traceSourceFlow.responsibleUnit}</textarea>
							</div>
							<div class='col-xs-3'>
								<label class="required">落实责任部门：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="responsibleDepartment" class='txtarea1'	name='traceSourceFlow.responsibleDepartment' >${traceSourceFlow.responsibleDepartment}</textarea>
							</div>
							<div class='col-xs-3'>
								<label class="required">落实责任人：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="responsiblePerson" class='txtarea1'	name='traceSourceFlow.responsiblePerson' >${traceSourceFlow.responsiblePerson}</textarea>
							</div>
							<div class='col-xs-3'>
								<label>备注：</label>
							</div>
							<div class='col-xs-9'>
								<textarea id="remark" class='txtarea1'	name='traceSourceFlow.remark' >${traceSourceFlow.remark}</textarea>
							</div>
							<div class='col-xs-3'>
								<label>附件：</label>
							</div>
							<div class='col-xs-9'>
								<div id="${traceSourceFlow.sourcepid}" class="uploadnew"></div>
								<%-- <atta:fileupload id="attachment_main_info" uploadAction="/attachment/upload.action" uploadDestination="addRelay"
								uploadBtnIsVisible="false" progressIsVisible="true" 
								uploadTableVisible="true" isMultiDownload="true"
								isAutoUpload="true" downTableVisible="true" isMultiUpload="true"
								attchmentGroupId="${traceSourceFlow.sourcepid}" operationParams="0,1,1"
								displayAllAtt="self" templateID="" 
								flashUrl="${ctx}/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf" >
								</atta:fileupload> --%>
							</div>
							<%-- <input type='hidden' id="attachid" name='traceSourceList.attachment' value='${traceSourceFlow.sourcepid}'/>    --%>
						</div>
					</div>
				</form>
			</div>
		</div>
</body>
<script type="text/javascript">
	function relay() {
		//var msg=checkNull();
		/* if(msg!=""){
		    $.bs.tips(msg,{level:"danger",$target:$("form"),auto_close:false});
		    return;
		} */
		/*  $.bs.tips("正在保存,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:true}); */
        if(!validateForm($("form"))){
            return false;
        }
		$.post($ctx + '/trace/flow/doAction.action', $("form").serializeArray()).done(function(result) {
			if (result == 'success') {
				/*$.bs.tips("操作成功",{ level:"success"},function(){
					 //刷新父页面
					  $.bs.tips("操作成功",{ level:"success"}); */
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			//});
			} else {
				/* $.bs.tips("操作失败",{ level:"danger"}); */
				alert("操作失败 "+result);
			}
		});
	}
</script>
</html>