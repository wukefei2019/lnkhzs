<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>


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
<link type="text/css" rel="Stylesheet"
	href="${ctx}/common/plugin/JTable/JTable.css" />

<script src="${ctx}/common/javascript/main.js"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/validate.js" defer="defer"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/datagrid.js" defer="defer"></script>
<script language="JavaScript" src='${ctx}/common/plugin/ajaxupload/ajaxfileupload.js' type='text/javascript'></script>

<script type='text/javascript'>

	$(document).ready(function() {
	
		var id = "${param.id}";
		var isreward = "${param.isreward}";
		if(isreward=="有奖励"){
			$("#is_reward").val("是");
			$("#message_info").val("【客户感知调研】尊敬的客户，您好！为了提升客户使用中国移动XXX业务的服务体验，邀请您参与XXXX调研，本次调研预计将占用您XX分钟时间，请在X月XX日之前点击http://dx.10086.cn/XXXX，进入问卷参与调研，提交问卷后将跳转至领取权益页面，可领取2GB国内（不含港澳台）套外流量或30分钟国内（不含港澳台）主叫通话时长权益。感谢您的参与。我们全心全意，为您“10”分满意。【中国移动】");
		}else{
			$("#is_reward").val("否");
			$("#message_info").val("【客户感知调研】尊敬的客户，您好！为了提升客户使用中国移动XXX业务的服务体验，邀请您参与XXXX调研，本次调研预计将占用您XX分钟时间，请在X月XX日之前点击http://dx.10086.cn/XXXX，进入问卷参与调研，感谢您的参与。我们全心全意，为您“10”分满意。【中国移动】");
		}

	});


 function checked() {
            if (!$("#xls").val()) {
                alert("请选择待导入的文件");
                return false;
            }
            var file =$("#fileName").val();
            var AllImgExt=".xls|.xlsx|";
                var extName = file.substring(file.lastIndexOf(".")).toLowerCase();//（把路径中的所有字母全部转换为小写）        
                if(AllImgExt.indexOf(extName+"|")==-1)        
                {
                    var ErrMsg="该文件类型不允许上传。请上传 "+AllImgExt+" 类型的文件，当前文件类型为"+extName;
                    alert(ErrMsg);
                    return false;
                }

            return true;
        }
        
         function upload_s() {
              $("#dism").attr("style","display:block");
	}

	
	//点击提交
	 function upload() {
				
			var isnullDay = $("#daynum").val();//有效时长
			var isnullInfo = $("#message_info").val();//短信内容
			var ruleVal = $("#rule").val();//规则
			var numVal = $("#num").val();//数量
		
			var r = /^\+?[1-9][0-9]*$/;　　//正整数 
			var re = new RegExp("^[ ]+$"); //全部空格
			
			if(!(r.test(isnullDay))){
			alert("请填写时长(时长为整数)");
			return false;
			}
			
			if(isnullInfo=="" || re.test(isnullInfo)){
			alert("请填写短信内容(不为空)");
			return false;
			}
			if(ruleVal==""){
			alert("请填写规则(不为空)");
			return false;
			}
			
			if(numVal=="" || numVal==0){
			alert("请上传正确的文件");
			return false;
			}
	 
	 		var reqdata =  "message_info=" + $("#message_info").val();
			reqdata = reqdata + "&daynum=" + $("#daynum").val();
			reqdata = reqdata + "&rule=" + $("#rule").val();
			reqdata = reqdata + "&num=" + $("#num").val();
			reqdata = reqdata + "&khzsQuestionnaireId=" + $("#khzsQuestionnaireid").val();
			
	 
	 
	 	if(numVal!=null && numVal!=0){
				if(confirm("您将发送"+numVal+"条智慧调研问卷短信，是否确认？")){
					pushTep1(numVal,reqdata);
				}else{
					//$("#showDemo").remove();
				}
			}
	 
	 
	          /*   $("#fileName").val($("#xls").val());
	            var msg = "确定导入文件吗？=>" ;
	            if (checked() && confirm(msg)) {
	             $.bs.tips("数据正在导入,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
	                var form = $("form")[0];
	                form.action = $ctx + "/khzs/survey/getExcelUp.action";
	                form.enctype = "multipart/form-data";
	                form.submit();
	            } */
	        }
	        
	//短信群发接口 获取projectId
	function pushTep1(people_num,reqdata){
	
			if(people_num!=null && people_num!=0){
			 countNum=people_num;
			 var url = encodeURI($ctx + "/khzs/survey/getInterface.action");
					$.post(url, reqdata).done(function(result) {
						//$("#showDemo").remove();
						if (result == 'error') {
							alert("提交失败");
						} else {
							pushNow(result,reqdata);
							/* alert("提交成功");
							$((opener || parent).document).find(".btn-link.icon-refresh2").click();
							window.close(); */
						}
					}, "json");
			}
	
		
	}
	
	//发布
	function pushNow(result,reqdata){
			$("#projectId").val(result);
			   $("#fileName").val($("#xls").val());
	             $.bs.tips("数据正在处理,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:false});
	                var form = $("form")[0];
	                form.action = $ctx + "/khzs/survey/getExcelUp.action";
	                form.enctype = "multipart/form-data";
	                form.submit();
	}


	function import_callback_new(level,msg,other_param){
           $.bs.tips(msg,{level:level,$target:$("form"),clear:true,auto_close:false});
            if(level=="success"){
                /* alert("上传成功!") */
            	window.close();
            }else{
            	alert("上传失败!")
            }
	}

	//上传的文件数量
	function myFunction() {
		$("#fileName").val($("#xls").val());
		$.ajaxFileUpload({
			url : $ctx + "/khzs/survey/getFileNum.action",
			secureuri : false,
			async : false,
			//name:'inputFile',
			fileElementId : 'xls', //file标签的id
			dataType : 'json', //返回数据的类型
			success : function(result) {
				/* console.log(11111111111);
				console.log(result); */
				/* if (result == 'error') {
					alert("上传文件号码格式错误");
					$("#xls").val("");
					return false;
				} */
				if (result.indexOf('error')!=-1) {
					alert("上传文件号码格式错误："+result.substring(5));
					$("#xls").val("");
					return false;
				}else{
					alert("成功导入"+result+"条");
				}
				$("#fileName").val("");
				$("#num").val(result);
			}
		});

	}
</script>

<meta charset="UTF-8">
<title>提交</title>
</head>
<body>

	<div class="Ct Dialog contract form-page">
	<iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
			<form id="importForm" enctype="multipart/form-data"
                method="post" target="frm">
				
				<input id="khzsQuestionnaireid" type="hidden"
						name="khzsQuestionnaireid" value="${param.id}" />
						
				<input id="projectId" type="hidden"
						name="projectId" value="" />
				
			<div id="formTable" class='panel panel-primary' style="margin-bottom: 0px;">
				<div class='panel-heading'>
					<h3 class='panel-title'>
						短信内容
						<input type="button" class="btn floatRight10 handSubmit" value="提交" onclick="upload()">
						<!-- <input type="button" class="btn floatRight10 handOut" value="保存" onclick="handOut()"> -->
					</h3>
				</div>
				<div id="flowTable" class="collapse in">
				<div class="form-table">
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
						<label class="required">问卷有效时长：</label>
					</div>
					<div class='col-xs-9'>
						<input id="daynum" min="0" type="number" style="width: 20%;float: left;"
								class='form-control' name='daynum'
								value='${bindDicSelectInfo.daynum}' required="required" />&nbsp;&nbsp;&nbsp;<span>天</span>
					
						<br><br><span style="color: red;">注：调研用户有效答题时间=【审批结束时间】+【问卷有效时长】</span>
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label  class="required">请填写规则：</label>
					</div>
					<div class='col-xs-9'>
								<textarea id="rule" name="rule" style="width: 80%;float: left;"
								class= 'txtarea1' required="required"></textarea>
								
						<br><br><br><br><br><br><span style="color: red;">注：审批流程中的【目标客户甄选规则】</span>
						
					</div>
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">短信内容：</label>
					</div>
					<div class='col-xs-9'>
						<textarea id="message_info" class='txtarea1'
							name='message_info' required="required">${bindDicSelectInfo.message_info}</textarea>
							
							<!-- <input style="width: 70%;float: left;background:#CCCCCC;"
								class='form-control'  readonly="true" 
								value="尊敬的客户：请在X月XX日之前点击（http://dx.10086.cn/XXXX）填写问卷。感谢您的支持与配合！【中国移动】" />
					 -->
						<br><span style="color: red;">注：审批流程中的【拟定短信语】</span>
						
					</div>
					
					</div>
					
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label >数量：</label>
					</div>
					<div class='col-xs-9'>
								<input id="num" name="num" style="width: 25%;float: left;background:#CCCCCC;"
								class='form-control'  readonly="true"  />
								
						<br><br><span style="color: red;">注：手机号导入成功的数量</span>
					</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="flow_reword" class='panel panel-primary' style="margin-bottom: 0px;">
			<div class='panel-heading'>
				<h3 class='panel-title'>
					奖品领取 <a data-toggle="collapse" href="#flowTable_s"
						style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
				</h3>
			</div>
			<div id="flowTable_s" class="collapse in">
				<div class="form-table">
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
						<label >是否有奖品领取：</label>
					</div>
					<div class='col-xs-9'>
						<input readonly="readonly" id="is_reward" type="text" style="width: 20%;float: left;"
								class='form-control' name='bindDicSelectInfo.is_reward'
								value='${bindDicSelectInfo.is_reward}' />
					</div>
					</div>
				</div>
			</div>
			</div>
			
		<div id="flow_s" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					数据导入 
				</h3>
				<a href="${ctx }/lnkhzs/survey/phoneNum.xls" style="float: right;COLOR: #1EB2FC;"><u>导入模板下载</u></a>
			</div>
		<div class="Ct Dialog">
        <div class="bg_gray2">
                <input type="hidden" name="callback" value="import_callback_new"/>
                <input type="hidden" id='fileName' name="fileName" value="" />
                <div class="padding20 border1 borderTop1">
                    <table width="100%" class="table_body2">
                        <tr>
                            <td width="12%" class="textalignRight paddingRight10"><label class="color_red">*</label></td>
                            <td width="13%" colspan="3">
                            	<td width="13%" colspan="3"><input type="file" id="xls" name="xls" value="" onchange="myFunction()"
                                accept="*.xls;*.xlsx" /></td>
                        </tr>
                    </table>
                </div>
            </form>
            
            
        </div>
    </div>
    </div>
</div>
</body>
</html>