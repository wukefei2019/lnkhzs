<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>新增信息</title>
        
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
		
		$(".handOut").on("click", function(event) {
	    	$("#actionStr").val("1");
	    	$("#opinion").val("提交");
	    	fn_save();
		});
		
		$(".back").on("click", function(event) {
	    	$("#actionStr").val("2");
	    	$("#opinion").val($(".back").text());
	    	fn_save();
		});
		
		$(".save").on("click", function(event) {
			$("#actionStr").val("0");
	    	$("#opinion").val("保存");
	    	fn_save();
		});
		
		$('.glyphicon').click(
				function(){
					$(this).toggleClass('glyphicon-my-minus glyphicon-my-plus');
				}
		)
		
		$("#folwdept").on("click", function(event) {
			openwindow($ctx + '/org/dept/selectWin.jsp?single=1&callback=setDept');
		});

    	var volid="${param.id}";
    	var type="${param.type}";
	    if(volid.length!=0){
	    	$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetKhzsTMain.action?khzsTMain.pid="+volid).done(function(result) {
				for(var key in result){
					$("#"+key).val(result[key]);
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
						'</td><td style="white-space:nowrap text-overflow:ellipsis" title='+remark2+'>'+remark2+
						'</td><td style="white-space:nowrap">'+result[i].status+
						'</td><td style="white-space:nowrap">'+nextfullname+
						'</td></tr>';
						$("#tbody1").append(tr);
					}
				});
				var flowStatus = $("#flowstatus").val();

				if(flowStatus!='内容审核'){
					$('#formTable input').attr("readonly","readonly"); 
					$('#formTable select').attr("disabled","disabled");
					$("#formTable div").attr("readonly","readonly");
					$("#formTable textarea").prop("readonly","readonly");
					
					$(".button_selectfile").hide();
					$("img[title='删除']").hide();
					
				}else{
					$(".save").show();
				}
				
				if(flowStatus!='内容审核'&&flowStatus!='审核员复核'){
					$("#approve").hide();
				}else{
					$(".back").show();
					$(".handOut").show();
				}
 				if(flowStatus=='审核员复核'){
					$(".dept").hide();
				}
			});
	    }
	    $("#type").val(type);
	    if(type=='一线员工建议'){
	    	$(".ygts").hide();
	    }

    });

    function fn_save() {
        if(!validateForm($("form"))){
            return false;
        }

        if($("#flowstatus").val()=="内容审核"&&$("#actionStr").val()==1&&$("#folwdeptid").val().length==0){
        	alert("请填写提交到的部门");
        	return false;
        }

        var url =encodeURI($ctx + "/khzs/flow/khzsTFlowAction/doAction.action?khzsTFlow.remark="
        		+$("#folwremark").val()+"&khzsTFlow.deptname="
        		+$("#folwdept").val()
        		+"&actionStr="+$("#actionStr").val()
        		+"&khzsTFlow.opinion="+$("#opinion").val());
    	$.post(url,$("form").serialize()).done(function(result) {
    		if (result == 'success') {
    			alert("操作成功");
    				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
    				 window.close();
                 
    		} else {
    			alert(result);
    		}
    	}, "json");
    	
    }
    
	function setDept(depid,depname) {
		$("#folwdeptid").val(depid);
		$("#folwdept").val(depname);
		$("#dutydeptid").val(depid);
		$("#dutydept").val(depname);
		
	}


    </script>
    
    <body>
        <div class="Ct Dialog contract form-page" >
        		<div id="approve" class='panel panel-primary'>
	                <div class='panel-heading'>
	                    <h3 class='panel-title'>
	                    	审批信息
	                    	<a data-toggle="collapse" href="#approvePart" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
	                    	<a class='btn floatRight10 back' style='display: none'>退回</a>
	                    	<a class='btn floatRight10 handOut' style='display: none'>提交</a>
						</h3> 
	                </div>
	                <div id="approvePart" class="collapse in">
	                <input id='folwdeptid' type='hidden'/>
	                <div class="form-table">
		                <div class='dept col-xs-3'>
							<label>选择负责部门：</label>
						</div>
						<div class='dept col-xs-9'>
							<input id='folwdept' type='text' style='width:100%' class='form-control' readonly='true'/>
						</div>
						<div class='col-xs-3'>
							<label>审批意见：</label>
						</div>
						<div class='col-xs-9'>
							<input id="folwremark" type='text' style="width:100%" class='form-control' />
						</div>
					</div>
	                </div>
	
                </div>
        
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
                    
                       <input id="dutydept" type="hidden" name="khzsTMain.dutydept" value="${khzsTMain.dutydept}"/>
                        <input id="dutydeptid" type="hidden" name="khzsTMain.dutydeptid" value="${khzsTMain.dutydeptid }"/>
                        <input id="dutyuser" type="hidden" name="khzsTMain.dutyuser" value="${khzsTMain.dutyuser }"/>
                        <input id="dutyuserid" type="hidden" name="khzsTMain.dutyuserid" value="${khzsTMain.dutyuserid }"/>
                       

					<div class="form-table">
						<div class='col-xs-3'>
							<label class="required">序号：</label>
						</div>
						<div class='col-xs-3'>
							<input id="idx" type='text' style="width:100%" class='form-control' name='khzsTMain.idx' value='${khzsTMain.idx}' readonly="true"/>
						</div>
						<div class='col-xs-3'>
							<label>提交时间：</label>
						</div>
						<div class='col-xs-3'>
							<input id="createtime" type='text' style="width:100%" class='form-control' name='khzsTMain.createtime' value='${khzsTMain.createtime}' readonly="true" />
						</div>				
						<div class='col-xs-3'>
							<label class="required">主题：</label>
						</div>
						<div class='col-xs-9'>
							<input id="theme" type='text' style="width:100%" class='form-control'	name='khzsTMain.theme' value='${khzsTMain.theme}' readonly="true" />
						</div>
										
						<div class='col-xs-3'>
							<label>备注：</label>
						</div>
						<div class='col-xs-9'>
							<textarea id="remark" class='txtarea1' name='khzsTMain.remark' readonly="true">${khzsTMain.remark}</textarea>
						</div>
						<div class='col-xs-3'>
							<label>附件：</label>
						</div>
						<div class='col-xs-9'>
							<div id="${param.id}" class="uploadnew">
							</div>
						<%-- <atta:fileupload id="attachment_main_info" uploadAction="/attachment/upload.action" uploadDestination="addKhzs"
							uploadable="false"
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
               

        </div>
   	</body>
</html>
