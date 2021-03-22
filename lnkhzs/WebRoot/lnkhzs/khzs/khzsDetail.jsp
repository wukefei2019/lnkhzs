<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>新增信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/khzsDetail.js"></script>
        <script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>
    </head>
    <body>
        <div class="Ct Dialog contract form-page" >
            <div class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>客户之声信息</h3> 
                </div>
                   <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
                   <form id='form' name='form' action='${ctx}/khzs/flow/khzsTFlowAction/doAction.action' method='post' target="frm">
                   	   <input type="hidden" name="khzsTFlow.opinion" value=""/>
                   	   <input type="hidden" name="actionStr" value=""/>
                       <input type="hidden" name="khzsTMain.pid" value="${khzsTMain.pid }"/>
                       <input type='hidden' name='khzsTMain.flowstatus' value='${khzsTMain.flowstatus }'/>
                       <input type="hidden" name="khzsTMain.fullname" value="${khzsTMain.fullname }"/>
                       <input type="hidden" name="khzsTMain.loginname" value="${khzsTMain.loginname }"/>
                       <input type="hidden" name="khzsTMain.createtime" value="${khzsTMain.createtime }"/>
                       <input type="hidden" name="khzsTMain.nextfulln
                       ame" value="${khzsTMain.nextfullname }"/>
                       <input type="hidden" name="khzsTMain.nextloginname" value="${khzsTMain.nextloginname }"/>
                       <input type="hidden" name="khzsTMain.nextdepname" value="${khzsTMain.nextdepname }"/>
                       <input type="hidden" name="khzsTMain.depname" value="${khzsTMain.depname }"/>
                       <input type="hidden" name="type" value="${khzsTMain.type }"/>                       
                       <input type="hidden" name="source" value="${khzsTMain.source }"/>
					<div class="form-table">
						<div class='col-xs-3'>
							<label class="required">主题：</label>
						</div>
						<div class='col-xs-9'>
							<input type='text' class='form-control'	name='khzsTMain.theme' value='${khzsTMain.theme}' />
						</div>
						<div class='col-xs-3'>
							<label class="required">序号：</label>
						</div>
						<div class='col-xs-3'>
							<input type='text' class='form-control' name='khzsTMain.idx' value='${khzsTMain.idx	}' />
						</div>
						<div class='col-xs-3'>
							<label class="required">类型：</label>
						</div>
						<div class='col-xs-3'>
							<select class='form-control' name='khzsTMain.type'  onchange="isComplaint();">
								<option value=""></option>
								<option value="典型投诉案例">典型投诉案例</option>
								<option value="一线员工建议">一线员工建议</option>
							</select>
						</div>
						<div class='col-xs-3'>
							<label class="required">问题类型：</label>
						</div>
						<div class='col-xs-3'>
							<input type='text' class='form-control'	name='khzsTMain.questiontype' value='${khzsTMain.questiontype}' />
						</div>
						<div class='col-xs-3'>
							<label class="required">问题来源：</label>
						</div>
						<div class='col-xs-3'>
							<select class='form-control' name='khzsTMain.source' >
								<option ></option>
								<option value="10086">10086</option>
								<option value="10080">10080</option>
								<option value="工信部">工信部</option>
								<option value="省管局">省管局</option>
								<option value="民心网">民心网</option>
							</select>
						</div>
						<div class='col-xs-3'>
							<label class="required">问题描述：</label>
						</div>
						<div class='col-xs-9'>
							<textarea class='txtarea1'	name='khzsTMain.description' >${khzsTMain.description}</textarea>
						</div>
						<div class='col-xs-3'>
							<label class="required">解决方案：</label>
						</div>
						<div class='col-xs-9'>
							<textarea class='txtarea1' name='khzsTMain.solution' >${khzsTMain.solution}</textarea>
						</div>
						<div class='col-xs-3'>
							<label class="required">待解决问题：</label>
						</div>
						<div class='col-xs-9'>
							<textarea class='txtarea1'	name='khzsTMain.question' >${khzsTMain.question}</textarea>
						</div>
						<div class='col-xs-3'>
							<label class="required">备注：</label>
						</div>
						<div class='col-xs-9'>
							<textarea class='txtarea1' name='khzsTMain.remark' >${khzsTMain.remark}</textarea>
						</div>
					</div>
				</form>
           	</div>
        </div>
   	</body>
</html>
