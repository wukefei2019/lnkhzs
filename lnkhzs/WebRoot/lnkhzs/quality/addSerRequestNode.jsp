<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>新增信息</title>
        <script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>
        <script type="text/javascript" src="${ctx }/lnkhzs/quality/addSerRequestNode.js"></script>
    </head>
    <body>
        <div class="Ct Dialog contract form-page" >
            <div class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>新增辽宁公司服务请求节点</h3>
                    <a class='btn floatRight10' onclick='saveAdd()'>保存</a>
                </div>
                    <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
                    <form id='form' name='form' action='${ctx}/quality/tagMaintain/saveSerReqNodeBak.action' method='post' target="frm">
	                    <div class="form-table">
	                        <input type="hidden" name="viceRequestNode.pid" value="${viceRequestNode.pid }"/>
	                        <input type="hidden" name="viceRequestNode.code01" value="${viceRequestNode.code01 }"/>
	                        <input type="hidden" name="viceRequestNode.code02" value="${viceRequestNode.code02 }"/>
	                        <input type="hidden" name="viceRequestNode.code03" value="${viceRequestNode.code03 }"/>
	                        <input type="hidden" name="viceRequestNode.code04" value="${viceRequestNode.code04 }"/>
	                        <input type="hidden" name="viceRequestNode.code05" value="${viceRequestNode.code05 }"/>
	                        <input type="hidden" name="viceRequestNode.code06" value="${viceRequestNode.code06 }"/>
	                        <input type="hidden" name="viceRequestNode.code07" value="${viceRequestNode.code07 }"/>
	                        <input type="hidden" name="viceRequestNode.businessAttribution" value="${viceRequestNode.businessAttribution }"/>
	                        <input type="hidden" name="viceRequestNode.businessType" value="${viceRequestNode.businessType }"/>
	                        <input type="hidden" name="viceRequestNode.remarks" value="${viceRequestNode.remarks }"/>
	                        <input type="hidden" name="viceRequestNode.sheetName" value="${viceRequestNode.sheetName }"/>
	                        <input type="hidden" name="viceRequestNode.typeAdjustExplanation" value="${viceRequestNode.typeAdjustExplanation }"/>
	                        <input type="hidden" name="viceRequestNode.a12" value="${viceRequestNode.a12 }"/>
	                        <input type="hidden" name="viceRequestNodeBak.status" value="${param.status}"/>
	                        <div class="row">
		                       	<div class='col-xs-3'>
									<label class="required">业务分类1级：</label>
								</div>
								<div class='col-xs-3'>
									<select id="name01" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name01' >
										<option ></option>
										<option value="移动业务">移动业务</option>
										<option value="家庭业务">家庭业务</option>
										<option value="集团业务">集团业务</option>
										<option value="增值业务">增值业务</option>
									</select>
								</div>
								<div class='col-xs-3'>
									<label class="required">业务分类2级：</label>
								</div>
								<div class='col-xs-3'>
									<select id="name02" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name02' >
										<option ></option>
									</select>
								</div>
							</div>
							<div class="row">
		                       	<div class='col-xs-3'>
									<label class="required">业务分类3级：</label>
								</div>
								<div class='col-xs-3'>
									<select id="name03" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name03' >
										<option ></option>
									</select>
								</div>
								<div class='col-xs-3'>
									<label class="required">业务分类4级：</label>
								</div>
								<div class='col-xs-3'>
									<input id="name04" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name04' >
								</div>
							</div>
							<div class="row">
		                       	<div class='col-xs-3'>
									<label class="required">业务分类5级：</label>
								</div>
								<div class='col-xs-3'>
									<select id="name05" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name05' >
										<option ></option>
									</select>
								</div>
								<div class='col-xs-3'>
									<label class="required">业务分类6级：</label>
								</div>
								<div class='col-xs-3'>
									<select id="name06" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name06' >
										<option ></option>
									</select>
								</div>		
							</div>
							<div class="row">
								<div class='col-xs-3'>
									<label class="required">业务分类7级：</label>
								</div>
								<div class='col-xs-3'>
									<input id="name07" style="width:100%" class='form-control viceRequestNode' name='viceRequestNode.name07' >
								</div>
							</div>
						</div>					
                    </form>
            </div>
        </div>
    </body>
</html>
