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
		
		$('.uploadnew').iceuploader({
    		attachid:'${param.fileid}',
    		cachemode:true,
    		editable:true,
    		forcedel:!false,
    		savepath:'swork'
    	});
		getDep();			
		
		//保存
		$(".handOut").on("click", function(event) {
			$("#sworkstatus").val("已保存");
			fn_save(0);
		});
		
		//发起
 		$(".submit").on("click", function(event) {
			$("#sworkstatus").val("已发起");
			fn_save(1);			
		}); 
		
		$("#selectusers1").on("click",fn_to_user1).show();	
		$("#selectusers2").on("click",fn_to_user2).show();	

	});
	
	
	//跳转至选人树形页面
	function fn_to_user1(){
    	openwindow($ctx + '/org/person/selectWin.jsp?callback=setDutyUser1','');
	}
	function fn_to_user2(){
    	openwindow($ctx + '/org/person/selectWin.jsp?callback=setDutyUser2','');
	}
	
	//返回内容给页面赋值
	var setDutyUser1 = function(rows,fn){
		$("[name='sWork.approvor']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    	$("[name='sWork.approvorlogin']").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));
    }
    
    var setDutyUser2 = function(rows,fn){
		$("[name='sWork.sendto']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    	$("[name='sWork.sendtologin']").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));
    }
	
	
	function getDep() {

		var url = $ctx + "/tsgd/swork/getDept.action";
		$.post(url).done(function(data) {
			$("#maindepart").append("<option value=''></option>")
			for (var i = 0; i < data.length; i++) {
				$("#maindepart").append("<option value='" + data[i].depname + "' myid='"+data[i].pid+"'>" + data[i].depname + "</option>")
			}
			getDetail(id);
		});
	};
	
	function getDetail(){		
		var id = "${param.id}";
		if (id.length != 0) {
			$.post($ctx + "/tsgd/swork/getEditProcess.action?sWork.id=" + id).done(function(result) {
				$("#id").val(result.id);
				$("#selID").val(result.supervision_matters);
				if(result.supervision_matters=="巡视整改"){
					ShowselMeu(1);
				}else if(result.supervision_matters=="集团服务督办"){
					ShowselMeu(2);
				}else if(result.supervision_matters=="上级部门交办事项/通报"){
					ShowselMeu(3);
				}else if(result.supervision_matters=="溯源整改"){
					ShowselMeu(4);
				}else if(result.supervision_matters=="其他"){
					ShowselMeu(5);
				}else{
					console.log(6);
				}
				$("#resenddep").val(result.department);
				$("#resendpeo").val(result.dispatch);
				$("#resendnum").val(result.dispatch_phone);
				$("#createtime").val(result.createtime); 
				$("#expirydate").val(result.time_limit);
				$("#subID").val(result.type);
				$("#maindepart").val(result.maindepart);
				$("#description").val(result.description);
				$("#source").val(result.source);
				$("#target").val(result.target);
				$("#serial_number").val(result.serial_number);
				
				$("#sworkapprovorlogin").val(result.approvorlogin);
				$("#sworksendtologin").val(result.sendtologin);
				$("#sworkmaindepartid").val(result.maindepartid);
				$("#approvor").val(result.approvor);
				$("#sendto").val(result.sendto);
				
			});
			
		}
	}

	
	function fn_save(optinon) {
		if ( $("#maindepart").val() == ""|| $("#description").val() == ""|| $("#expirydate").val() == ""||$("#soure").val() == ""||$("#target").val() == ""
		||$("#selID").val() == ""||$("#subID").val() == ""||$("#subID").val() == null||$("#selID").val() == null||$("#selID").val() == "--选择事项--"||$("#subID").val() == "--选择类别--"
		||$("#serial_number").val() == ""||$("#approvor").val() == ""){
			alert("请添加必填项");
		}/* else if( $("#maindepartid").val()==""){
			alert("该部门没有负责人 ");
		} */ else {
			$("body").append('<div id="showDemo" style="width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:table;text-align:center;"><div class="addDiv"><img src="../style/image/loading_0.gif" style="margin-top: 25%;" class="add"/></div></div>');
			
			var url = encodeURI($ctx + "/tsgd/swork/addSend.action");
			var returnPath="";		
			var myDate = new Date();
    		var tYear = myDate.getFullYear();
    		var tMonth = myDate.getMonth();
			var m = tMonth + 1;
    		if (m.toString().length == 1) {
        		m = "0" + m;
        	}
    		var timestamp = myDate.getTime();
			returnPath="/"+tYear+m+"/ServiceSupervisor/"+timestamp;
			if (optinon == 1) {

					console.log("发起") ;
					$("#appendix_address").val(returnPath);
					console.log(returnPath);
					$.ajax({
						type : 'POST',
						url : $ctx + "/tsgd/swork/addFilesNew.action",
						data : {
							"sWork.appendix_pid" : $("#appendix_pid").val(),
							"path" : returnPath
						},
						success : function(data) {
							if (data == "success") {
								//上传成功
								getGWPublish(url);
							} else {
								alert("附件上传失败！");
							}
						},
						error : function(data) {}
					});

				/* console.log("发起");
				$("#appendix_address").val(returnPath);
				console.log(returnPath);
				//发起
				//上传附件
				if ($("#files")[0].files.length == 0) {
					//调公务
					getGWPublish(url);
				} else {
				for (var i = 0; i < $("#files")[0].files.length; i++) {
					var formData = new FormData();
					formData.append("files", $("#files")[0].files[i]);
					var filefix=$("#files")[0].files[i].name;
					filefix=filefix.substring(filefix.indexOf("."), filefix.length);
					formData.append("filefix", filefix);
					formData.append("path", returnPath);
					$.ajax({
						type : 'POST',
						url : $ctx + "/tsgd/swork/addFiles.action",
						data : formData,
						processData : false, // 使数据不做处理
						contentType : false, // 不要设置Content-Type请求头
						dataType : "json",
						async : false,
						mimeType : "multipart/form-data",
						success : function(data) {
								console.log(11);
								if (i == $("#files")[0].files.length-1) {
									//最后一个上传完毕,调公务
									getGWPublish(url);
								}
							},
						error : function(data) {
							console.log(22);
							if (i == $("#files")[0].files.length-1) {
									//最后一个上传完毕,调公务
									getGWPublish(url);
								}
								
						//console.log(data);
						}
					});


				} } */
				}else{
				//只保存
				console.log("保存");
				getGWPublish(url);
			}
		}
	};
	
	function selMeu(){
		var arr = [["--选择类别--"],["案件报告"],["集团服务督办单"],
		           ["全网投诉工作情况通报","5G投诉情况通报","全网携号转网投诉情况通报","“阳光行动”工作情况通报","集团客户满意度和产品体验评测情况的通报",
				"互联网电视及家庭产品质量评测情况的通报","邮件：工信部申诉受理中心重点调查案件","邮件：携号转网代客申诉情况分析","服务管理处微信群","质量标准处微信群",
				"质量评测处微信群","客户服务协调中心微信群","10080重点核查单","辽宁省通信管理局：协助用户反映问题"],["问题溯源"],["其他"]];
		var index = document.getElementById("selID").selectedIndex;
		var subNode = document.getElementById("subID");
		var i = arr[index];		 		
		subNode.options.length = 0;   //清空之前选项
		for(var x=0;x<i.length;x++){
			var optNode = document.createElement("option");
			optNode.innerText = i[x];
			subNode.appendChild(optNode);  //选择添加到子选项中
		}
	}
	
	function ShowselMeu(index){
		var arr = [["--选择类别--"],["案件报告"],["集团服务督办单"],
		           ["全网投诉工作情况通报","5G投诉情况通报","全网携号转网投诉情况通报","“阳光行动”工作情况通报","集团客户满意度和产品体验评测情况的通报",
				"互联网电视及家庭产品质量评测情况的通报","邮件：工信部申诉受理中心重点调查案件","邮件：携号转网代客申诉情况分析","服务管理处微信群","质量标准处微信群",
				"质量评测处微信群","客户服务协调中心微信群","10080重点核查单","辽宁省通信管理局：协助用户反映问题"],["问题溯源"],["其他"]];
		//var index = document.getElementById("selID").selectedIndex;
		var subNode = document.getElementById("subID");
		var i = arr[index];		 		
		subNode.options.length = 0;   //清空之前选项
		for(var x=0;x<i.length;x++){
			var optNode = document.createElement("option");
			optNode.innerText = i[x];
			subNode.appendChild(optNode);  //选择添加到子选项中 
		}
	}
	
	function channgeoption(obj) {
		$("#sworkmaindepartid").val($(obj).find("option:selected").attr("myid"));
	};
	
	
	function myFunction() {
		$("#fileName").val($("#files").val());
	}
	
	function myPushfiles(returnPath) {
		//$("#fileName").val($("#files").val());
		/* $.bs.tips("数据正在处理,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
		var form = $("#pushFiles")[0];
		console.log(form);
		form.action = $ctx + "/tsgd/swork/addFiles.action";
		form.enctype = "multipart/form-data";
		form.submit(); */

		
		for (var i = 0; i < $("#files")[0].files.length; i++) {
			var formData = new FormData();
			formData.append("files", $("#files")[0].files[i]);
			formData.append("path", returnPath);
			console.log($("#files")[0].files[i]);
			$.ajax({
				type : 'POST',
				url : $ctx + "/tsgd/swork/addFiles.action",
				data : formData,
				processData : false, // 使数据不做处理
				contentType : false, // 不要设置Content-Type请求头
				dataType : "json",
				async:false,
				mimeType : "multipart/form-data",
				success : function(data) {
					alert(1)
				},
				error : function(data) {
					console.log(111);
					console.log(data);
				}
			});


		}



		}
		
		
	function getGWPublish(url) {
		$.post(url, $("form").serialize()).done(function(result) {
			$("#showDemo").remove();
			if (result != null) {
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			} else if (result == 'error') {
				alert("操作失败!");
			} else {
				alert("操作失败");
			}


		/* if (result == 'success') {
			alert("操作成功");
			$((opener || parent).document).find(".btn-link.icon-refresh2").click();
			window.close();
		} else if (result == 'error') {
			alert("操作失败!");
		} else {
			alert("操作失败");
		} */
		}, "json"); 
	}
	
		
</script>
<meta charset="UTF-8">
<title>督办表单</title>
</head>
<body>

	<div class="Ct Dialog contract form-page">
		<div id="formTable" style="margin-top: 10px;"
			class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					<a class='btn floatRight10 submit' style='display: inline;'>发起</a>
					表单信息 <a class='btn floatRight10 handOut' style='display: inline;'>保存</a>

				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">

				<input id="id" type="hidden" name="sWork.id" value='${sWork.id }' />
				
				<input id="sworkstatus" type="hidden" name="sWork.status"
					value='${sWork.status }' />
					
				<input id="sworkapprovorlogin" type="hidden" name="sWork.approvorlogin"
					value='${sWork.approvorlogin }' />
					
				<input id="sworksendtologin" type="hidden" name="sWork.sendtologin"
					value='${sWork.sendtologin }' />
					
				<input id="sworkmaindepartid" type="hidden" name="sWork.maindepartid"
					value='${sWork.maindepartid }' />
					
				<input id="appendix_address" type="hidden" name="sWork.appendix_address"
					value='${sWork.appendix_address }' />
				
				<input id="appendix_name" type="hidden" name="sWork.appendix_name"
					value='${sWork.appendix_name }' />
					
				<input id="appendix_pid" type="hidden" name="sWork.appendix_pid"
					value='${param.fileid}' />
				
				<div class="form-table">
						
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">编号：</label>
						</div>
						<div class='col-xs-9'>
							<input id="serial_number" type="number" style="width: 30%"
								class='form-control' name='sWork.serial_number' value='${sWork.serial_number}' />
						</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='ygts col-xs-3'>
							<label class="required">督办事项：</label>
						</div>
						<div class='ygts col-xs-9'>
							<select id="selID" style="width: 30%" class='form-control'
								name='sWork.supervision_matters' onchange="selMeu()">
								<option>--选择事项--</option>
								<option>巡视整改</option>
								<option>集团服务督办</option>
								<option>上级部门交办事项/通报</option>
								<option>溯源整改</option>
								<option>其他</option>
							</select>
						</div>
					</div>

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>派单部门：</label>
						</div>
						<div class='col-xs-9'>
							<input id="resenddep" type='text' style="width: 30%"
								class='form-control' readonly="readonly" name='sWork.department' />
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>派单人：</label>
						</div>
						<div class='col-xs-9'>
							<input id="resendpeo" type='text' style="width: 30%"
								class='form-control' readonly="readonly" name='sWork.dispatch' />
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>派单人电话：</label>
						</div>
						<div class='col-xs-9'>
							<input id="resendnum" type='text' style="width: 30%"
								class='form-control' readonly="readonly"
								name='sWork.dispatch_phone' />
						</div>
					</div>

					<div class="row"
						style="margin-left: 0px;margin-right: 0px; display:none">
						<div class='col-xs-3'>
							<label>起草时间：</label>
						</div>
						<div class='col-xs-9'>
							<input id="createtime" type='text' style="width: 30%"
								name='sWork.createtime' class='form-control' readonly="readonly"
								value='${sWork.createtime}' />
						</div>
					</div>


					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">督办时限：</label>
						</div>
						<div class='col-xs-9'>
							<input id="expirydate" type='text' style="width: 30%"
								class='form-control' name='sWork.time_limit'
								value='${sWork.time_limit}' onclick="WdatePicker()" />

						</div>
					</div>


					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='ygts col-xs-3'>
							<label class="required">督办类别：</label>
						</div>
						<div class='ygts col-xs-9'>
							<!-- <select id="pro2" style="width: 30%" class='form-control'  name='sWork.type'>
							<option value="-1"  ></option>
						</select> -->

							<select id="subID" style="width: 30%" class='form-control'
								name='sWork.type'>
								<option>--选择类别--</option>
							</select>
						</div>
					</div>


					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='ygts col-xs-3'>
							<label class="required">主责部门：</label>
						</div>
						<div class='ygts col-xs-9'>
							<select id="maindepart" style="width: 30%" class='form-control'
								name='sWork.maindepart' onchange="channgeoption(this)">
							</select>
						</div>
					</div>

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">督办问题描述：</label>
						</div>
						<div class='col-xs-9'>
							<textarea id="description" type="text" style="width: 100% ; height:100px" 
								class='form-control' name='sWork.description'
								value='${sWork.description}' ></textarea>
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">督办问题来源：</label>
						</div>
						<div class='col-xs-9'>
							<input id="source" type="text" style="width: 100%"
								class='form-control' name='sWork.source' value='${sWork.source}' />
						</div>
					</div>

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">督办目标：</label>
						</div>
						<div class='col-xs-9'>
							<input id="target" type="text" style="width: 100%"
								class='form-control' name='sWork.target' value='${sWork.target}' />
						</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">接口人：</label>
						</div>
						<div class='col-xs-9' id="selectusers1">

							<input type='text' id="approvor"
								class='inputText1 width160'
								name='sWork.approvor'
								value='${khzssurvermanager.administratorname}'
								style="width:70%;" /><span class="glyphicon glyphicon-plus"
								style="margin-left: 13px;"></span>
						</div>
					</div>

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label >抄送人：</label>
						</div>
						<div class='col-xs-9' id="selectusers2">

							<input type='text' id="sendto"
								class='inputText1 width160'
								name='sWork.sendto'
								value='${khzssurvermanager.administratorname}'
								style="width:70%;" /><span class="glyphicon glyphicon-plus"
								style="margin-left: 13px;"></span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	
	<div id="flow_s" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					数据导入 
				</h3>
				
			</div>
		<div class="Ct Dialog">
        <div class="bg_gray2" style="text-align: center;margin-top: 10px;margin-bottom: 10px;">
                <div id='${param.fileid}'  class="uploadnew" > </div>
                <!-- <input type="hidden" name="callback" value="import_callback_new"/>
                <input type="hidden" id='fileName' name="fileName" value="" />
                <div class="padding20 border1 borderTop1">
                    <table width="100%" class="table_body2">
                        <tr>
                            <td width="12%" class="textalignRight paddingRight10"></td>
                            <td width="13%" colspan="3">
                            	<input type="file" id="xls" name="xls" value="" onchange="myFunction()" accept="*.xls;*.xlsx" />
                            	<form id="pushFiles" action="" method="post" >
    								<input type="file" id="files" name="files" multiple="multiple"  value="" onchange="myFunction()">
    								<input type="file" id="file" name="filename" multiple="multiple" value="">
    								<input type="submit" value="上传">
								</form>
                            </td>
                        </tr>
                    </table>
                </div>
            </form> -->
		
		
	</div>
</body>
</html>