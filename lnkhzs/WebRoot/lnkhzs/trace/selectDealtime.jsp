<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>新增信息</title> 
	<script type="text/javascript" language="javascript" src="${ctx}/common/javascript/date/WdatePicker.js" defer="defer"></script>
	<script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>
	<script type='text/javascript'>
		function getDep() {
			var url = $ctx + "/tsgd/resend/getDept.action";
			$.post(url).done(function(data) {
				$("#maindepart").append("<option value=''></option>")
				for (var i = 0; i < data.length; i++) {
					$("#maindepart").append("<option value='" + data[i].pid + "'>" + data[i].depname + "</option>")
				}
				
			});
		}
		;
	
		$(document).ready(function() {
			getDep();
			 $("#showUsers").on("click",fn_to_user).show();
	
		});
	</script>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>处理时限</h3>
				<a class='btn floatRight10' onclick='saveAdd()'>保存</a>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
			<form id='form' name='form'
				action='${ctx}/trace/traceManager/saveManager.action' method='post'
				target="frm">
					<div class="form-table">
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<input type="hidden" name="traceManager.pid" value="${traceManager.pid }" />
						<input type="hidden" name="traceManager.depname" value="${traceManager.depname }" />	
							<div class='col-xs-3' style="margin-bottom: -10px;">
								<label class="required" >处理时限：</label>
							</div>
							<div class='col-xs-3'>
								<input type="text" class="textInput inputCala" id="dealtime"  onclick="WdatePicker({dateFmt:'yyyyMMdd'})" placeholder="请输入处理时限" style="padding-bottom: 1px;" /> 
							</div>
							<div class='col-xs-3'>
								<label class="required">人员：</label>
							</div>
							<div class='col-xs-3' id="showUsers">
								<input type='text' class='inputText1 width160' name='traceManager.fullname'
									value='${traceManager.fullname}' style="width: 70%;"  /><span
									class="glyphicon glyphicon-plus" ></span>
								<input type="hidden" name="traceManager.loginname"
									value="${traceManager.loginname }" />		
							</div>
						</div>
					</div>

			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function fn_to_user(){
	    openwindow($ctx + '/lnkhzs/trace/selectManager.jsp?callback=setDutyUser&single=1','');
	}
	
	function setDutyUser(row){
		
	    /*$("[name='khzsTDepadmin.loginname']").val($.map(rows,function(t,i){ return t.LOGINNAME}).join(","));*/
	    $("[name='traceManager.fullname']").val(row.FULLNAME);
	    
	    $("[name='traceManager.depname']").val(row.DEPNAME);
	    
	    $("[name='traceManager.loginname']").val(row.LOGINNAME);

	}
	
	function saveAdd() {
		if(confirm("确认是否提交？")){
	        if(!validateForm($("form"))){
	            return false;
	        }
		    var all_selection = $("#dealtime").val();
		    var adminLoginname = $("[name='traceManager.loginname']").val();
		    window.opener.<%= request.getParameter("callback")%>(all_selection,adminLoginname);
		    window.close();
		}
	}
</script>
</html>