<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>一线员工建议</title>
        
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
    </head>
    <script type='text/javascript'>
    $(document).ready(function(){
    	
    	$('.uploadnew').iceuploader({
    		attachid:'${param.id}',
    		cachemode:true,
    		editable:true,
    		forcedel:!false,
/*     		savepath:'eoms_workSheet/WF4_EL_UVS_TSK/2021-01-18' */
    	});
    	
    	var volid="${param.pid}";
    	var type="一线员工建议";
	    if(volid.length!=0){
	    	$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetKhzsTMain.action?khzsTMain.pid="+volid).done(function(result) {
				for(var key in result){
					$("#"+key).val(result[key]);
				}				
				
			   if("公开（不可评论）" == $("#example").val()){
			    	$("#comment").hide();
			   }
				$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetKhzsTFlow.action?khzsTMain.pid="+volid).done(function(result) {
					for(var i=0;i<result.length;i++){
						var remark=result[i].remark==null?'':result[i].remark;
					    var laststatus=result[i].laststatus==null?'':result[i].laststatus;
					    var nextfullname=result[i].nextfullname==null?'':result[i].nextfullname;
					    var remark2=result[i].remark2==null?'':result[i].remark2;
						var tr ='<tr style="white-space:nowrap"><td style="white-space:nowrap">'+laststatus+
						'</td><td style="white-space:nowrap">'+result[i].fullname+
						'</td><td style="white-space:nowrap">'+result[i].createtime+
						'</td><td style="white-space:nowrap">'+result[i].opinion+
						'</td><td style="white-space:nowrap">'+remark+
						'</td><td style="width:100px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis" title='+remark2+'>'+remark2+
						'</td><td style="white-space:nowrap">'+result[i].status+
						'</td><td style="white-space:nowrap">'+nextfullname+
						'</td></tr>';
						$("#tbody1").append(tr);
					}
				});
				
				getKhzsTComment();
				
				var flowStatus = $("#flowstatus").val();
				if(flowStatus=='部门处理（挂起）'||flowStatus=='专员处理（挂起 ）'){
					$(".hangup").hide();
				}
				
				$('#formTable input').attr("readonly","readonly"); 
				$('#formTable select').attr("disabled","disabled");
				$("#formTable div").attr("readonly","readonly");
				$("#formTable textarea").prop("readonly","readonly");
				$(".button_selectfile").hide();
				$("img[title='删除']").hide();
				
			});
	    }
	    $("#type").val(type);
	    if(type=='一线员工建议'){
	    	$(".ygts").hide();
	    }
	    
	    $('.comment').on('click',function(){
	    	$.post($ctx + "/khzs/flow/khzsTFlowAction/saveKhzsTComment.action?khzsTComment.vocpid="+volid+"&khzsTComment.content="+$('#content').val()).done(function(result) {
				alert("保存成功");
				getKhzsTComment();
				$("#content").val("");
			});
	    });

		$('.glyphicon').click(
				function(){
					$(this).toggleClass('glyphicon-my-minus glyphicon-my-plus');
				}
		)
	    
		
    });

    function getKhzsTComment(){
    	var volid="${param.pid}";
    	$("#tbody2").html("");
		$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetKhzsTComment.action?khzsTMain.pid="+volid).done(function(result) {
			for(var i=0;i<result.length;i++){
				var tr ='<tr style="white-space:nowrap"><td style="white-space:nowrap">'+result[i].fullname+
				'</td><td style="white-space:nowrap">'+result[i].content+
				'</td><td style="white-space:nowrap">'+result[i].createtime+
				'</td></tr>';
				$("#tbody2").append(tr);
			}
		});
    }
    
    function fn_save() {
        if(!validateForm($("form"))){
            return false;
        }
        if($("#actionStr").val()==1&&$("#folwremark2").val().length==0){
        	alert("请填写处理办法");
        	return false;
        }
        if($("#actionStr").val()==3&&$("#folwuser").val().length==0){
        	alert("请填写要转派的人");
        	return false;
        }
        var url =encodeURI($ctx + "/khzs/flow/khzsTFlowAction/doAction.action?khzsTFlow.remark2="+$("#folwremark2").val()
        		+"&khzsTFlow.remark="+$("#folwremark").val()
        		+"&selectUser.depFullName="+$("#folwdeptname").val()
        		+"&selectUser.fullName="+$("#folwuser").val()
        		+"&selectUser.loginName="+$("#folwuserid").val()
        		+"&actionStr="+$("#actionStr").val()+"&khzsTFlow.opinion="+$("#opinion").val());
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
    
    
    function setUser(all_selection) {
		var userName = all_selection[0].FULLNAME;
		var userId = all_selection[0].LOGINNAME;
		
		$("#folwuser").val(userName);
		$("#folwuserid").val(userId);
	}

    </script>
    
    <body>
        <div class="Ct Dialog contract form-page" >
            <div id="formTable" class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>
                    	表单信息
                    	<a data-toggle="collapse" href="#form" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
					</h3> 
                </div>
                   <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
                   <form id='form' name='form'target="frm" class="collapse in">
						<input id="opinion" type="hidden"/>
                   	    <input id="actionStr" type="hidden"/>
                       
						<input id="type" type="hidden" name="khzsTMain.type" value="${khzsTMain.type }"/>
						<input id="pid" type="hidden" name="khzsTMain.pid" value="${khzsTMain.pid }"/>
						<input id="flowstatus" type='hidden' name='khzsTMain.flowstatus' value='${khzsTMain.flowstatus }'/>
						
					   <input id="fullname" type="hidden" name="khzsTMain.fullname" value="${khzsTMain.fullname }"/>
                       <input id="loginname" type="hidden" name="khzsTMain.loginname" value="${khzsTMain.loginname }"/>
                       <input id="nextfullname" type="hidden" name="khzsTMain.nextfullname" value="${khzsTMain.nextfullname }"/>
                       <input id="nextloginname" type="hidden" name="khzsTMain.nextloginname" value="${khzsTMain.nextloginname }"/>
                       <input id="nextdepname" type="hidden" name="khzsTMain.nextdepname" value="${khzsTMain.nextdepname }"/>
                       <input id="depname" type="hidden" name="khzsTMain.depname" value="${khzsTMain.depname }"/>
                       <input id="example" type="hidden" name="khzsTMain.example" value="${khzsTMain.example }"/>
                    
                    

					<div class="form-table">
					
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">序号：</label>
						</div>
						<div class='col-xs-3'>
							<input id="idx" type='text' style="width:100%" class='form-control' name='khzsTMain.idx' value='${khzsTMain.idx}' />
						</div>
						<div class='col-xs-3'>
							<label>提交时间：</label>
						</div>
						<div class='col-xs-3'>
							<input id="createtime" type='text' style="width:100%" class='form-control' name='khzsTMain.createtime' value='${khzsTMain.createtime}' readonly="true" />
						</div>	
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">			
						<div class='col-xs-3'>
							<label class="required">主题：</label>
						</div>
						<div class='col-xs-3'>
							<input id="theme" type='text' style="width:100%" class='form-control'	name='khzsTMain.theme' value='${khzsTMain.theme}' />
						</div>
										
						<div class='col-xs-3'>
							<label class="required">类型：</label>
						</div>
						<div class='col-xs-3'>
							<input id="questiontype" type='text' style="width:100%" class='form-control'	name='khzsTMain.questiontype' value='${khzsTMain.questiontype}' />
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">问题描述：</label>
						</div>
						<div class='col-xs-9'>
							<textarea id="description" class='txtarea1'	name='khzsTMain.description' >${khzsTMain.description}</textarea>
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='ygts col-xs-3'>
							<label>投诉来源：</label>
						</div>
						<div class='ygts col-xs-9'><select id="source" style="width:100%" class='form-control' name='khzsTMain.source' >
								<option ></option>
								<option value="10086">10086</option>
								<option value="10080">10080</option>
								<option value="工信部">工信部</option>
								<option value="省管局">省管局</option>
								<option value="民心网">民心网</option>
								<option value="其他">其他</option>
							</select></div>
							</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						
						<div class='col-xs-3'>
							<label>解决方案：</label>
						</div>
						<div class='col-xs-9'>
							<textarea id="solution" class='txtarea1' name='khzsTMain.solution' >${khzsTMain.solution}</textarea>
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>责任单位：</label>
						</div>
						<div class='col-xs-9'>
							<input id="dutydept" type='text' class='form-control' name='khzsTMain.dutydept' value="${khzsTMain.dutydept}"/>
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>反馈人：</label>
						</div>
						<div class='col-xs-9'>
							<input id="dutyuser" class='txtarea1' type='text' class='form-control' name='khzsTMain.dutyuser' value="${khzsTMain.dutyuser}" />
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>备注：</label>
						</div>
						<div class='col-xs-9'>
							<textarea id="remark" class='txtarea1' name='khzsTMain.remark' >${khzsTMain.remark}</textarea>
						</div>
						</div>
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label>附件：</label>
						</div>
						<div class='col-xs-9'>
						<div id="${param.id}" class="uploadnew"></div>
							<%-- <atta:fileupload id="attachment_main_info" uploadAction="/attachment/upload.action" uploadDestination="addKhzs"
							uploadBtnIsVisible="false" progressIsVisible="true"
							uploadTableVisible="true" isMultiDownload="true"
							isAutoUpload="true" downTableVisible="true" isMultiUpload="true"
							attchmentGroupId="${param.id}" operationParams="0,1,1"
							displayAllAtt="self" templateID="" 
							flashUrl="${ctx}/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf">
						</atta:fileupload>
	                  		<input type='hidden' id="attachment" name='khzsTMain.attachment' value='${khzsTMain.attachment}'/>  --%>
						</div>
						</div>
					</div>
				</form>
           	</div>
           	
           	<div id="flow" class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>
                    	审批意见
                    	<a data-toggle="collapse" href="#flowTable" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
					</h3> 
                </div>
                <div id="flowTable" class="collapse in">
					<table width='100%' style="white-space:nowrap;table-layout:fixed;"
						class='form-table table-bordered table-marginTop'>
						<thead>
							<tr>
								<th>阶段</th>
								<th>处理人</th>
								<th width="15%">处理时间</th>
								<th>处理动作</th>
								<th>审批意见</th>
								<th>解决方案/评价</th>
								<th>下一阶段</th>
								<th>下一处理人</th>
							</tr>
						</thead>
						<tbody id="tbody1" width='100%' align="center">
				
						</tbody>
					</table>
				</div>
           </div>
           <div id="comment" class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>
                    	评论
                    	<a data-toggle="collapse" href="#commentPart" style="text-decoration: none" class="glyphicon glyphicon-my-plus"></a>
					</h3> 
                </div>
                <div id="commentPart" class="collapse">
					<table width='100%' style="white-space:nowrap;"
						class='form-table table-bordered table-marginTop'>
						<thead>
							<tr>
								<th>评论人</th>
								<th>评论内容</th>
								<th>时间</th>
							</tr>
						</thead>
						<tbody id="tbody2" width='100%' align="center">
				
						</tbody>
					</table>
				
					<div class='form-table'>
		           		   <div class='col-xs-12'>
								<textarea id="content" class="txtarea1" placeholder="说俩句儿吧..."></textarea>	
				           </div>   
		            </div>
		            <button type="button" class="btn btn-primary floatRight10 comment">发表评论</button>
	           </div>
			   
		   </div>
        </div>
   	</body>
</html>
