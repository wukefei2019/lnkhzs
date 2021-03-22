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
	var nowLoginUserFullName1="<%=userName%>";
	var deptlevel2id='<%=deptlevel2id%>';
	var id="";
	$(document).ready(function() {
		$(".handOut").on("click", function(event) {
			fn_save(1);
		});
		var name = "${param.name}";
		id = "${param.id}";
		$("#labelcategory").val(name);
		$("#mattercategory").val("投诉问题单");
		
		getDep();
		getuserdept();
		
		
	});
	function fn_save(optinon) {
		if ($("#applytitle").val() == "" || $("#maindepart").val() == ""|| $("#validvalue").val() == ""|| $("#target").val() == "") {
			alert("请添加必填项");
		}else if( $("#maindepartid").val()==""){
			alert("该部门没有负责人 ");
		} else {
			$("body").append('<div id="showDemo" style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" style="margin-top: 25%;" class="add"/></div></div>');
			
			var url = encodeURI($ctx + "/tsgd/resend/addSend.action");

			$.post(url, $("form").serialize()).done(function(result) {
				$("#showDemo").remove();
				if (result == 'success') {
					alert("操作成功");
					$((opener || parent).document).find(".btn-link.icon-refresh2").click();
					window.close();
				} else if (result == 'error') {
					alert("操作失败!");
				} else {
					alert("操作失败");
				}

			}, "json");
		}


	}
	;
	function getDep() {
		var url = $ctx + "/tsgd/resend/getDept.action";
		$.post(url).done(function(data) {

			$("#maindepart").append("<option value=''></option>")
			for (var i = 0; i < data.length; i++) {
				$("#maindepart").append("<option value='" + data[i].depname + "' myid='"+data[i].pid+"'>" + data[i].depname + "</option>")
			}
			getDetail(id);
		});
	}
	;
	function channgeoption(obj) {
		var url = $ctx + "/tsgd/resend/getTopUserByDep.action?depid=" + $(obj).find("option:selected").attr("myid");
		$.post(url).done(function(data) {
			if (data != null)
				$("#maindepartid").val(data.administratorid)
		});
	};
	function getuserdept(){
		var url = $ctx + "/tsgd/resend/getUserDept.action";
		$.post(url).done(function(data) {
			$("#resendpeo").val(data.username);
			$("#resenddep").val(data.deptname);
		});
	};
	function getDetail(id) {
		var url = $ctx + "/tsgd/resend/getDetailById.action?wFresend.id="+id;
		$.post(url).done(function(data) {
			$("#maindepart").val(data.maindepart);
			$("#applytitle").val(data.applytitle);
			$("#content").val(data.content);
			$("#expirydate").val(data.expirydate);
			$("#validmethod").val(data.validmethod);
			$("#validvalue").val(data.validvalue);
			$("#target").val(data.target);
		});
	};
	function clickFL(){
		openwindow($ctx + '/lnkhzs/quality/seleTag2.jsp');
	};
	function getTags(a) {
		$("#labelcategory").val(a);
	};
	function getMaindep(str) {
	var strRe = str.replace("省公司","");
		$("#maindepart").val(strRe);
	};
	
</script>
<meta charset="UTF-8">
<title>派发表单</title>
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
				<input id="maindepartid" style="width: 100%"
							class='form-control' name='wFresend.maindepartid'
							value='${wFresend.maindepartid}'  type="hidden"/>
				<div class="form-table">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>问题分类：</label>
					</div>
					<div class='col-xs-9'>
						<input id="mattercategory" type='text' style="width: 100%"
							class='form-control' name='wFresend.mattercategory'
							value='${wFresend.mattercategory}' readonly="readonly" />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>标签分类：</label>
					</div>
					<div class='col-xs-9'>
							<input id="labelcategory" type='text'  style="width: 90%;float: left;"
								class='form-control' name='wFresend.labelcategory'
								value='${wFresend.labelcategory}' readonly="readonly"/>
								<span class="glyphicon glyphicon-plus" onclick="clickFL()" style="float: left;margin-top: 5px;margin-left: 10px"></span>
					</div>
					
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label class="required">主责部门：</label>
					</div>
					<div class='ygts col-xs-9'>
						<select id="maindepart" style="width: 100%" class='form-control'
							name='wFresend.maindepart' onchange="channgeoption(this)">
						</select>
					</div>
					</div>

					<%-- <div class='col-xs-3'>
						<label class="required">主责部门正职登录名：</label>
					</div>
					<div class='col-xs-9'>
						<input id="maindepartid" type='text' style="width: 100%"
							class='form-control' name='wFresend.maindepartid'
							value='${wFresend.maindepartid}' readonly="readonly" type="hidden"/>
					</div> --%>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">派单主题：</label>
					</div>
					<div class='col-xs-9'>
						<input id="applytitle" type='text' style="width: 100%"
							class='form-control' name='wFresend.applytitle'
							value='${wFresend.applytitle}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>派单内容：</label>
					</div>
					<div class='col-xs-9'>
						<input id="content" type='text' style="width: 100%"
							class='form-control' name='wFresend.content'
							value='${wFresend.content}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>派单部门：</label>
					</div>
					<div class='col-xs-9'>
						<input id="resenddep" type='text' style="width: 100%"
							class='form-control' readonly="readonly" />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>派单人：</label>
					</div>
					<div class='col-xs-9'>
						<input id="resendpeo" type='text' style="width: 100%"
							class='form-control' readonly="readonly" />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label>截止日期：</label>
					</div>
					<div class='col-xs-9'>
						<input id="expirydate" type='text' style="width: 100%"
							class='form-control' name='wFresend.expirydate'
							value='${wFresend.expirydate}' onclick="WdatePicker()" />
					</div>
					</div>

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label>验证方式：</label>
					</div>
					<div class='ygts col-xs-9'>
						<select id="validmethod" style="width: 100%" class='form-control'
							name='wFresend.validmethod'>
							<option value="投诉量">投诉量</option>
							<option value="投诉率">投诉率</option>
						</select>
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">验证值：</label>
					</div>
					<div class='col-xs-9'>
						<input id="validvalue" type="number" style="width: 100%"
							class='form-control' name='wFresend.validvalue'
							value='${wFresend.validvalue}' />
					</div>
					</div>
					
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">目标：</label>
					</div>
					<div class='col-xs-9'>
						<input id="target" type="text" style="width: 100%"
							class='form-control' name='wFresend.target'
							value='${wFresend.target}' />
					</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>