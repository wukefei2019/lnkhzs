<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ultrapower.eoms.common.core.util.UUIDGenerator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="/common/core/taglib_omcs.jsp"%>

    <head>
        <meta charset="UTF-8">
        <title>客户之声信息</title>
        
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
    <%
    	String id =request.getParameter("id");
    	if(id==null){
    		id=UUIDGenerator.getUUIDoffSpace();
    	}
    %>
     
    $(document).ready(function(){
		$('.uploadnew').iceuploader({
    		attachid:'<%=id%>',
    		cachemode:true,
    		editable:true,
    		forcedel:!false,
/*     		savepath:'eoms_workSheet/WF4_EL_UVS_TSK/2021-01-18' */
    	});
		
		$(".handOut").on("click", function(event) {
	    	$("#actionStr").val("1");
	    	$("#opinion").val("提交");
	    	fn_save(1);
		});
		
		$(".saveAdd").on("click", function(event) {
			$("#actionStr").val("0");
	    	$("#opinion").val("保存");
	    	fn_save(0);
		});
		
		$(".evaluate").on("click", function(event) {
			$("#actionStr").val("1");
	    	$("#opinion").val("评价");
	    	fn_save(1);
		});

		var volid="<%=id%>";

    	var type="${param.type}";
    	 $("#type").val(type);
		 $("#pid").val(volid);
    	
	    if(volid.length!=0){
	    	$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetKhzsTMain.action?khzsTMain.pid="+volid).done(function(result) {
				for(var key in result){
					$("#"+key).val(result[key]);
				}				
				
				var flowStatus = $("#flowstatus").val();
				if(flowStatus=='员工评价'){
					$("#evaluate").show();
				}
			    $("#type").val(type);
			    $("#pid").val(volid);
				if(flowStatus!=='员工提交'&&flowStatus.length>0){
					$('#formTable input').attr("readonly","readonly"); 
					$('#formTable select').attr("disabled","disabled");
					$("#formTable div").attr("readonly","readonly");
					$("#formTable textarea").prop("readonly","readonly");
			
					$(".handOut").hide();
					$(".saveAdd").hide();
					$(".button_selectfile").hide();
					$("img[title='删除']").hide();

				}else{
					$(".handOut").show();
					$(".saveAdd").show();
					$(".ind").hide();
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

			});
	    }

	    //$("#pid").val(volid);

	    
		$('.glyphicon').click(
			function(){
				$(this).toggleClass('glyphicon-my-minus glyphicon-my-plus');
			}
		)
    });
    
    function fn_save(optinon) {
        if(optinon==1&&!validateForm($("form"))){
            return false;
        }
        
        if($("#flowstatus").val()=="员工评价"&&$("#actionStr").val()==1&&$("#folwremark2").val().length==0){
        	alert("请填写评价");
        	return false;
        }
        var url =encodeURI($ctx + "/khzs/flow/khzsTFlowAction/doAction.action?khzsTFlow.remark2="+$("#folwremark2").val()+"&actionStr="+$("#actionStr").val()+"&khzsTFlow.opinion="+$("#opinion").val());
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
        <div class="Ct Dialog contract form-page" >
               <div id="evaluate" class='panel panel-primary' style="display: none">
	                <div class='panel-heading'>
	                    <h3 class='panel-title'>
	                    	评价
	                    	<a data-toggle="collapse" href="#evaluatePart" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
	                    	<a class='btn floatRight10 evaluate'>评价</a>
						</h3> 
	                </div>
	                <div id="evaluatePart" class="collapse in">
		                <input id='folwdeptid' type='hidden'/>
		                <div class="form-table">	
		              
						 <div class="row" style="margin-left: 0px;margin-right: 0px;">	                
							<div class='col-xs-3'>
								<label>评价：</label>
							</div>
							<div class='col-xs-9'>
								<input id="folwremark2" type='text' style="width:100%" class='form-control' />
							</div>
							</div>
						</div>
	                </div>
               </div>
        
            <div id="formTable" class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>
                    	表单信息
                    	<a data-toggle="collapse" href="#form" style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
                    	<a class='btn floatRight10 handOut' style='display: none'>提交</a>
						<a class='btn floatRight10 saveAdd' style='display: none'>保存</a>
					</h3> 
                </div>
                   <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
                   <form id='form' name='form'target="frm" class="collapse in">
						<input id="opinion" type="hidden"/>
                   	    <input id="actionStr" type="hidden"/>
                       
						<input id="type" type="hidden" name="khzsTMain.type" value="${khzsTMain.type }"/>
						<input id="pid" type="hidden" name="khzsTMain.pid" value="${khzsTMain.pid }"/>
						<input id="flowstatus" type='hidden' name='khzsTMain.flowstatus' value='${khzsTMain.flowstatus }'/>
						<input id="isSketch" type="hidden" name="khzsTMain.isSketch" value="${khzsTMain.isSketch }"/>
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
					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='ind col-xs-3'>
							<label class="required">序号：</label>
						</div>
						<div class='ind col-xs-3'>
							<input id="idx" type='text' style="width:100%" class='form-control' name='khzsTMain.idx' value='${khzsTMain.idx}' />
						</div>
						<div class='ind col-xs-3'>
							<label>提交时间：</label>
						</div>
						<div class='ind col-xs-3'>
							<input id="createtime" type='text' style="width:100%" class='form-control' name='khzsTMain.createtime' value='${khzsTMain.createtime}' readonly="true" />
						</div>		
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">		
						<div class='col-xs-3'>
							<label class="required">主题：</label>
						</div>
						<div class='col-xs-9'>
							<input id="theme" type='text' style="width:100%" class='form-control'	name='khzsTMain.theme' value='${khzsTMain.theme}' />
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
							<div id="<%=id%>" class="uploadnew">
							</div>
							<%-- <atta:fileupload id="attachment_main_info" uploadAction="/attachment/upload.action" uploadDestination="addKhzs"
							uploadBtnIsVisible="false" progressIsVisible="true"
							uploadTableVisible="true" isMultiDownload="true"
							isAutoUpload="true" downTableVisible="true" isMultiUpload="true"
							attchmentGroupId="<%=id%>" operationParams="0,1,1"
							displayAllAtt="self" templateID="" 
							flashUrl="${ctx}/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf">
							</atta:fileupload>
	                  		<input type='hidden' id="attachment" name='khzsTMain.attachment' value='${khzsTMain.attachment}'/>  --%>
						</div>
						</div>

					</div>
				</form>
           	</div>
           	
           	  <div id="flow" class='panel panel-primary' >
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
