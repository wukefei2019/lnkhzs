<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>验收</title> <!--<style type='text/css'>
</style> -->
	<script type="text/javascript" src="${ctx }/lnkhzs/trace/addDone.js"></script>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>完成信息</h3>
				<a class='btn floatRight10' onclick='finish()'>验收</a>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
				<form id='form' name='form' target="frm" class="collapse in">
					<input type="hidden" name="traceSourceList.pid"	value="${param.pid}" />
					<input type="hidden" name="traceSourceList.code"	value="${param.code}" />
					<input type="hidden" name="actionStr"	value="验收" />
					<input type="hidden" id="completionStatus" value="${traceSourceList.completionStatus} " />
					<div class="form-table">
						<div class="row">
<!-- 							<div class='col-xs-3'>
								<label>完成情况：</label>
							</div>
							<div class='col-xs-3'>
								<select  class='form-control' name="traceSourceList.completionStatus" >
									<option ></option>
									<option value="待验证">待验证</option>
									<option value="已完成">已完成</option>
								</select>
							</div> -->
							<div class='col-xs-3'>
								<label>验收全量投诉量：</label>
							</div>
							<div class='col-xs-3'>
								<input id="acceptanceComplaints" type='text'  class='form-control' name='traceSourceList.acceptanceComplaints' value='${traceSourceList.acceptanceComplaints}' />
							</div>
							<div class='col-xs-3'>
								<label>验证万投比：</label>
							</div>
							<div class='col-xs-3'>
								<input id="acceptanceComplaintsRate" type='text'  class='form-control' name='traceSourceList.acceptanceComplaintsRate' value='${traceSourceList.acceptanceComplaintsRate}' />
							</div>
							<div class='col-xs-3'>
								<label>验收通过时间：</label>
							</div>
							<div class='col-xs-3'>
								<input type='date' id='acceptanceTime' name='traceSourceList.acceptanceTime' value="${fn:substring(traceSourceList.acceptanceTime,0,10)}" class='form-control width160 beginDate' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:func_my97_picked });"  />
							</div>
							<div class='col-xs-6'>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
</body>
<script type="text/javascript">
	$(function(){
		$('[name="traceSourceList.completionStatus"] option[value='+$('#completionStatus').val()+']').attr('selected','true');
	})
	function finish() {
		//var msg=checkNull();
		/* if(msg!=""){
		    $.bs.tips(msg,{level:"danger",$target:$("form"),auto_close:false});
		    return;
		} */
		/*  $.bs.tips("正在保存,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:true}); */
		/* $.post($ctx + '/trace/traceSource/check.action', $("form").serializeArray()).done(function(result) { */
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
				alert("操作失败");
			}
		});
	}
</script>
</html>