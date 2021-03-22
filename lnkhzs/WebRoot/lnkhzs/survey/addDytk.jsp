<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ultrapower.eoms.common.core.util.UUIDGenerator"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
<title>新增题库</title>

<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/ultraupload.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload_new.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css" rel="stylesheet" type="text/css" />

</head>

<script type='text/javascript'>

	$(document).ready(function(){
		$(".handOut").on("click", function(event) {
	    	$("#actionStr").val("1");
	    	$("#opinion").val("提交");
	    	fn_save(1);
		});
	});

    function fn_save(optinon) {
        var url = encodeURI($ctx + "/khzs/surveys/doAction.action");
    	$.post(url,$("form").serialize()).done(function(result) {
    		if (result == 'success') {
    			alert("操作成功");
    				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
    				 window.close();
                 
    		} else {
    			alert("操作失败");
    		}
    	}, "json");
    	
    }
</script>
<body>
	<div class="Ct Dialog contract form-page">
		<div id="formTable" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>表单信息 <a data-toggle="collapse" href="#form" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
					<a class='btn floatRight10 handOut' style='display: inline;'>提交</a> 
				</h3>
			</div>
			<iframe name="frm" src="" style="display: none" id="frm"></iframe>
			<form id='form' name='form' target="frm" class="collapse in">
			
<%-- 				<input id="id" type="hidden" name="khzsQuestion.id" value="${khzsQuestion.id }"/>
				<input id="category" type="hidden" name="khzsQuestion.category" value="${khzsQuestion.category }"/>
				<input id="question" type='hidden' name='khzsQuestion.question' value='${khzsQuestion.question }'/>
			   	<input id="type" type="hidden" name="khzsQuestion.type" value="${khzsQuestion.type }"/>
	            <input id="options" type="hidden" name="khzsQuestion.options" value="${khzsQuestion.options }"/> --%>
                       
				<div class="form-table">
					<div class='ind col-xs-3'>
						<label class="required">题号：</label>
					</div>
					<div class='ind col-xs-3'>
						<input id="idx" type='text' style="width: 100%" class='form-control' name='khzsQuestion.id' value='${khzsQuestion.id}' />
					</div>
					<div class='col-xs-3'>
						<label class="required">标签，分类：</label>
					</div>
					<div class='col-xs-3'>
						<input id="theme" type='text' style="width: 100%" class='form-control' name='khzsQuestion.category' value='${khzsQuestion.category}' />
					</div>
					<div class='col-xs-3'>
						<label class="required">题目：</label>
					</div>
					<div class='col-xs-9'>
						<input id="theme" type='text' style="width: 100%" class='form-control' name='khzsQuestion.question' value='${khzsQuestion.question}' />
					</div>
					<div class='ygts col-xs-3'>
						<label>题型：</label>
					</div>
					<div class='ygts col-xs-9'>
						<select id="type" style="width: 100%" class='form-control' name='khzsQuestion.type'>
							<option value=""></option>
							<option value="单选">单选</option>
							<option value="多选">多选</option>
							<option value="判断">判断</option>
							<option value="简答">简答</option>
						</select>
					</div>
					<div class='col-xs-3'>
						<label>选项：</label>
					</div>
					<div class='col-xs-9'>
						<input id="theme" type='text' style="width: 100%" class='form-control' name='khzsQuestion.options' value='${khzsQuestion.options}' />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
