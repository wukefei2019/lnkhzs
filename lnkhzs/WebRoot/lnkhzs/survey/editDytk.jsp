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
		var msg = "${param.msg}";
		
		 if(id.length!=0){
			
			if(msg!="" && msg.length!=0){
				//点击查看详情页面
				$("#clickSave").attr("style","display:none");//隐藏选择按钮
				$("input").attr("readonly","readonly");//让所有input标签不能编辑
				$("select").attr("disabled","true");//让所有select标签不能编辑
			}
			//修改页面
			$.post($ctx + "/khzs/surveys/toEditDytk.action?khzsQuestion.id=" + id).done(function(data) {
				var optionaMap = data[0];//矩阵填空行
				var optionbMap = data[1];//矩阵填空列
				var result = data[2].khzsQuestion;//获取对象
			
			if(result.type == '矩阵填空'){
				$("#id").val(result.id);
				$("#category").val(result.category);
				$("#question").val(result.question);
				$("#ps").val(result.ps);
				$("#type").val(result.type);
				$("#belongto").val(result.belongto);
				
				for(var i = 0;i<16;i++){
						var par =i+1;
					$("#optionL"+par).val(optionaMap["optionL"+i]);
				}
				
				for(var i = 0;i<16;i++){
						var par =i+1;
					$("#optionC"+par).val(optionbMap["optionC"+i]);
				}
				
				/* $("#optionL1").val(optionaMap.optionL0);
				$("#optionL2").val(optionaMap.optionL1);
				$("#optionL3").val(optionaMap.optionL2);
				$("#optionL4").val(optionaMap.optionL3);
				$("#optionL5").val(optionaMap.optionL4);
				$("#optionL6").val(optionaMap.optionL5);
				$("#optionL7").val(optionaMap.optionL6);
				$("#optionL8").val(optionaMap.optionL7); */
				//$("#optionL1").val(optionaMap.1) ;
				
			}else{
				$("#id").val(result.id); 
				$("#category").val(result.category);
				$("#question").val(result.question);
				$("#ps").val(result.ps);
				$("#type").val(result.type);
				$("#optiona").val(result.optiona);
				$("#optionb").val(result.optionb);
				$("#optionc").val(result.optionc);
				$("#optiond").val(result.optiond);
				$("#optione").val(result.optione);
				$("#optionf").val(result.optionf);
				$("#optiong").val(result.optiong);
				$("#optionh").val(result.optionh);
				$("#optioni").val(result.optioni);
				$("#optionj").val(result.optionj);
				$("#optionk").val(result.optionk);
				$("#optionl").val(result.optionl);
				$("#optionm").val(result.optionm);
				$("#optionn").val(result.optionn);
				$("#optiono").val(result.optiono);
				$("#optionp").val(result.optionp);
				$("#belongto").val(result.belongto);
			}
			
			
				if ($("#type").val() == "判断") {
					$("[name=divc]").attr("hidden", "hidden");
					$("[name=divd]").attr("hidden", "hidden");
					$("[name=dive]").attr("hidden", "hidden");
					$("[name=divf]").attr("hidden", "hidden");
					$("[name=divg]").attr("hidden", "hidden");
					$("[name=divh]").attr("hidden", "hidden");
					$("[name=divi]").attr("hidden", "hidden");
					$("[name=divj]").attr("hidden", "hidden");
					$("[name=divk]").attr("hidden", "hidden");
					$("[name=divl]").attr("hidden", "hidden");
					$("[name=divm]").attr("hidden", "hidden");
					$("[name=divn]").attr("hidden", "hidden");
					$("[name=divo]").attr("hidden", "hidden");
					$("[name=divp]").attr("hidden", "hidden");
				} else if ($("#type").val() == "简答") {
					$("[name=divb]").attr("hidden", "hidden");
					$("[name=divc]").attr("hidden", "hidden");
					$("[name=divd]").attr("hidden", "hidden");
					$("[name=dive]").attr("hidden", "hidden");
					$("[name=divf]").attr("hidden", "hidden");
					$("[name=divg]").attr("hidden", "hidden");
					$("[name=divh]").attr("hidden", "hidden");
					$("[name=divi]").attr("hidden", "hidden");
					$("[name=divj]").attr("hidden", "hidden");
					$("[name=divk]").attr("hidden", "hidden");
					$("[name=divl]").attr("hidden", "hidden");
					$("[name=divm]").attr("hidden", "hidden");
					$("[name=divn]").attr("hidden", "hidden");
					$("[name=divo]").attr("hidden", "hidden");
					$("[name=divp]").attr("hidden", "hidden");
				}else if ($("#type").val() == "矩阵填空") {
				$("#hiddenDivJZTK").attr("style","display:block");
			$("#hiddenDiv").attr("style","display:none");//隐藏矩阵填空选择
				} else {
					$("[name=diva]").removeAttr("hidden");
					$("[name=divb]").removeAttr("hidden");
					$("[name=divc]").removeAttr("hidden");
					$("[name=divd]").removeAttr("hidden");
					$("[name=dive]").removeAttr("hidden");
					$("[name=divf]").removeAttr("hidden");
					$("[name=divg]").removeAttr("hidden");
					$("[name=divh]").removeAttr("hidden");
					$("[name=divi]").removeAttr("hidden");
					$("[name=divj]").removeAttr("hidden");
					$("[name=divk]").removeAttr("hidden");
					$("[name=divl]").removeAttr("hidden");
					$("[name=divm]").removeAttr("hidden");
					$("[name=divn]").removeAttr("hidden");
					$("[name=divo]").removeAttr("hidden");
					$("[name=divp]").removeAttr("hidden");
				}
			});
		}

	});

	function fn_save(optinon) {
	if($("#type").val() == '矩阵填空'){
		var JZTKline = "";
		var JZTKColumn = "";
		//获取矩阵填空行
        $('input[name="JZTK"]').each(function(){
        	if($(this).val()!="" && $(this).val().length!=0){
        	 	JZTKline += $(this).val() + ",,,,";
        	}
        });
        //获取矩阵填空列
        $('input[name="JZTKXX"]').each(function(){
        	if($(this).val()!="" && $(this).val().length!=0){
        	 	JZTKColumn += $(this).val() + ",,,,";
        	}
        });
				//var url = encodeURI($ctx + "/khzs/surveys/editDytk.action?khzsQuestion.optiona=" +  JZTKline+"&khzsQuestion.optionb=" +  JZTKColumn);
				var url = encodeURI($ctx + "/khzs/surveys/editDytk.action?optionL=" +  JZTKline+"&optionC=" +  JZTKColumn);
	}else{
	   var url = encodeURI($ctx + "/khzs/surveys/editDytk.action");
	}
		
		
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

	function channgeoption(obj) {
		if ($(obj).val() == "判断") {
		$("#hiddenDiv").attr("style","display:block");
		$("#hiddenDivJZTK").attr("style","display:none");//隐藏矩阵填空选择
		
			$("[name=divc]").attr("hidden", "hidden");
			$("[name=divd]").attr("hidden", "hidden");
			$("[name=dive]").attr("hidden", "hidden");
			$("[name=divf]").attr("hidden", "hidden");
			$("[name=divg]").attr("hidden", "hidden");
			$("[name=divh]").attr("hidden", "hidden");
			$("[name=divi]").attr("hidden", "hidden");
			$("[name=divj]").attr("hidden", "hidden");
			$("[name=divk]").attr("hidden", "hidden");
			$("[name=divl]").attr("hidden", "hidden");
			$("[name=divm]").attr("hidden", "hidden");
			$("[name=divn]").attr("hidden", "hidden");
			$("[name=divo]").attr("hidden", "hidden");
			$("[name=divp]").attr("hidden", "hidden");
		} else if ($(obj).val() == "简答") {
		$("#hiddenDiv").attr("style","display:block");
		$("#hiddenDivJZTK").attr("style","display:none");//隐藏矩阵填空选择
			$("[name=divb]").attr("hidden", "hidden");
			$("[name=divc]").attr("hidden", "hidden");
			$("[name=divd]").attr("hidden", "hidden");
			$("[name=dive]").attr("hidden", "hidden");
			$("[name=divf]").attr("hidden", "hidden");
			$("[name=divg]").attr("hidden", "hidden");
			$("[name=divh]").attr("hidden", "hidden");
			$("[name=divi]").attr("hidden", "hidden");
			$("[name=divj]").attr("hidden", "hidden");
			$("[name=divk]").attr("hidden", "hidden");
			$("[name=divl]").attr("hidden", "hidden");
			$("[name=divm]").attr("hidden", "hidden");
			$("[name=divn]").attr("hidden", "hidden");
			$("[name=divo]").attr("hidden", "hidden");
			$("[name=divp]").attr("hidden", "hidden");
		}else if ($(obj).val() == "矩阵填空") {
			$("#hiddenDivJZTK").attr("style","display:block");
			$("#hiddenDiv").attr("style","display:none");//隐藏矩阵填空选择
		
			/* $("[name=divb]").attr("hidden", "hidden");
			$("[name=divc]").attr("hidden", "hidden");
			$("[name=divd]").attr("hidden", "hidden");
			$("[name=dive]").attr("hidden", "hidden");
			$("[name=divf]").attr("hidden", "hidden");
			$("[name=divg]").attr("hidden", "hidden");
			$("[name=divh]").attr("hidden", "hidden");
			$("[name=divi]").attr("hidden", "hidden");
			$("[name=divj]").attr("hidden", "hidden");
			$("[name=divk]").attr("hidden", "hidden");
			$("[name=divl]").attr("hidden", "hidden");
			$("[name=divm]").attr("hidden", "hidden");
			$("[name=divn]").attr("hidden", "hidden");
			$("[name=divo]").attr("hidden", "hidden");
			$("[name=divp]").attr("hidden", "hidden"); */
		} else {
		$("#hiddenDiv").attr("style","display:block");
		$("#hiddenDivJZTK").attr("style","display:none");//隐藏矩阵填空选择
			$("[name=diva]").removeAttr("hidden");
			$("[name=divb]").removeAttr("hidden");
			$("[name=divc]").removeAttr("hidden");
			$("[name=divd]").removeAttr("hidden");
			$("[name=dive]").removeAttr("hidden");
			$("[name=divf]").removeAttr("hidden");
			$("[name=divg]").removeAttr("hidden");
			$("[name=divh]").removeAttr("hidden");
			$("[name=divi]").removeAttr("hidden");
			$("[name=divj]").removeAttr("hidden");
			$("[name=divk]").removeAttr("hidden");
			$("[name=divl]").removeAttr("hidden");
			$("[name=divm]").removeAttr("hidden");
			$("[name=divn]").removeAttr("hidden");
			$("[name=divo]").removeAttr("hidden");
			$("[name=divp]").removeAttr("hidden");
		}
	}
</script>
<meta charset="UTF-8">
<title>修改调研题库</title>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div id="formTable" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息 <a class='btn floatRight10 handOut' id='clickSave' style='display: inline;'>保存</a>
				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">
				<input id="id" type="hidden" name="khzsQuestion.id"
					value="${khzsQuestion.id }" />
				<div class="form-table">
					<%-- <div class='col-xs-3'>
						<label class="required">标签，分类：</label>
					</div>
					<div class='col-xs-9'>
						<input id="category" type='text' style="width: 100%" class='form-control' name='khzsQuestion.category' value='${khzsQuestion.category}' />
					</div> --%>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">题目：</label>
					</div>
					<div class='col-xs-9'>
						<input  id="question" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.question'
							value='${khzsQuestion.question}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label>备注：</label>
					</div>
					<div class='ygts col-xs-9'>
						<input id="ps" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.ps'
							value='${khzsQuestion.ps}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='ygts col-xs-3'>
						<label>题型：</label>
					</div>
					<div class='ygts col-xs-9'>
						<select id="type" style="width: 100%" class='form-control'
							name='khzsQuestion.type' onchange="channgeoption(this)">
							<option value=""></option>
							<option value="单选">单选</option>
							<option value="多选">多选</option>
							<option value="判断">判断</option>
							<option value="简答">简答</option>
							<option value="单项打分">单项打分</option>
							<option value="填空简答">填空简答</option>
							<option value="矩阵填空">矩阵填空</option>
							<!-- <option value="单选(其他)">单选(其他)</option>
							<option value="多选(其他)">多选(其他)</option> -->
						</select>
					</div>
					</div>
					<div id="hiddenDiv">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="diva">
						<label>选项A：</label>
					</div>
					<div class='col-xs-9' name="diva">
						<input id="optiona" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optiona'
							value='${khzsQuestion.optiona}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divb">
						<label>选项B：</label>
					</div>
					<div class='col-xs-9' name="divb">
						<input id="optionb" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionb'
							value='${khzsQuestion.optionb}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divc">
						<label>选项C：</label>
					</div>
					<div class='col-xs-9' name="divc">
						<input id="optionc" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionc'
							value='${khzsQuestion.optionc}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divd">
						<label>选项D：</label>
					</div>
					<div class='col-xs-9' name="divd">
						<input id="optiond" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optiond'
							value='${khzsQuestion.optiond}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="dive">
						<label>选项E：</label>
					</div>
					<div class='col-xs-9' name="dive">
						<input id="optione" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optione'
							value='${khzsQuestion.optione}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divf">
						<label>选项F：</label>
					</div>
					<div class='col-xs-9' name="divf">
						<input id="optionf" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionf'
							value='${khzsQuestion.optionf}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divg">
						<label>选项G：</label>
					</div>
					<div class='col-xs-9' name="divg">
						<input id="optiong" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optiong'
							value='${khzsQuestion.optiong}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divh">
						<label>选项H：</label>
					</div>
					<div class='col-xs-9' name="divh">
						<input id="optionh" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionh'
							value='${khzsQuestion.optionh}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divi">
						<label>选项I：</label>
					</div>
					<div class='col-xs-9' name="divi">
						<input id="optioni" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optioni'
							value='${khzsQuestion.optioni}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divj">
						<label>选项J：</label>
					</div>
					<div class='col-xs-9' name="divj">
						<input id="optionj" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionj'
							value='${khzsQuestion.optionj}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divk">
						<label>选项K：</label>
					</div>
					<div class='col-xs-9' name="divk">
						<input id="optionk" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionk'
							value='${khzsQuestion.optionk}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divl">
						<label>选项L：</label>
					</div>
					<div class='col-xs-9' name="divl">
						<input id="optionl" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionl'
							value='${khzsQuestion.optionl}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divm">
						<label>选项M：</label>
					</div>
					<div class='col-xs-9' name="divm">
						<input id="optionm" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionm'
							value='${khzsQuestion.optionm}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divn">
						<label>选项N：</label>
					</div>
					<div class='col-xs-9' name="divn">
						<input id="optionn" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionn'
							value='${khzsQuestion.optionn}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>选项O：</label>
					</div>
					<div class='col-xs-9' name="divo">
						<input id="optiono" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optiono'
							value='${khzsQuestion.optiono}' />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divp">
						<label>选项P：</label>
					</div>
					<div class='col-xs-9' name="divp">
						<input id="optionp" type='text' style="width: 100%"
							class='form-control' name='khzsQuestion.optionp'
							value='${khzsQuestion.optionp}' />
					</div>
					</div>
					</div>
					<!-- 题型为矩阵填空,选项为下 -->
					<div id="hiddenDivJZTK" style="display: none;">
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="diva">
						<label>矩阵行1：</label>
					</div>
					<div class='col-xs-4' name="diva">
						<input id="optionL1" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divb">
						<label>选项1：</label>
					</div>
					<div class='col-xs-4' name="divb">
						<input id="optionC1" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divc">
						<label>矩阵行2：</label>
					</div>
					<div class='col-xs-4' name="divc">
						<input id="optionL2" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							/>
					</div>
					<div class='col-xs-1' name="divd">
						<label>选项2：</label>
					</div>
					<div class='col-xs-4' name="divd">
						<input id="optionC2" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="dive">
						<label>矩阵行3：</label>
					</div>
					<div class='col-xs-4' name="dive">
						<input id="optionL3" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divf">
						<label>选项3：</label>
					</div>
					<div class='col-xs-4' name="divf">
						<input id="optionC3" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divg">
						<label>矩阵行4：</label>
					</div>
					<div class='col-xs-4' name="divg">
						<input id="optionL4" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divh">
						<label>选项4：</label>
					</div>
					<div class='col-xs-4' name="divh">
						<input id="optionC4" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divi">
						<label>矩阵行5：</label>
					</div>
					<div class='col-xs-4' name="divi">
						<input id="optionL5" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divj">
						<label>选项5：</label>
					</div>
					<div class='col-xs-4' name="divj">
						<input id="optionC5" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
						 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divk">
						<label>矩阵行6：</label>
					</div>
					<div class='col-xs-4' name="divk">
						<input id="optionL6" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divl">
						<label>选项6：</label>
					</div>
					<div class='col-xs-4' name="divl">
						<input id="optionC6" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
						 />
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divm">
						<label>矩阵行7：</label>
					</div>
					<div class='col-xs-4' name="divm">
						<input id="optionL7" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divn">
						<label>选项7：</label>
					</div>
					<div class='col-xs-4' name="divn">
						<input id="optionC7" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
						/>
					</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行8：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL8" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项8：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC8" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行9：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL9" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项9：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC9" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							/>
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行10：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL10" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项10：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC10" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
						 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行11：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL11" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项11：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC11" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行12：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL12" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项12：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC12" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行13：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL13" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项13：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC13" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							/>
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行14：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL14" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项14：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC14" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
							 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行15：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL15" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项15：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC15" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
						 />
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' name="divo">
						<label>矩阵行16：</label>
					</div>
					<div class='col-xs-4' name="divo">
						<input id="optionL16" type='text' style="width: 100%"
							class='form-control' name='JZTK'
							 />
					</div>
					<div class='col-xs-1' name="divp">
						<label>选项16：</label>
					</div>
					<div class='col-xs-4' name="divp">
						<input id="optionC16" type='text' style="width: 100%"
							class='form-control' name='JZTKXX'
					 />
					</div>
					</div>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					</div>
					
					
					
					
					
					
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3' >
						<label>属性：</label>
					</div>
					<div class='col-xs-9' >
						<select id="belongto" style="width: 100%" class='form-control'
							name='khzsQuestion.belongto'>
							<option value="公有">公有</option>
							<option value="私有">私有</option>
						</select>
					</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>