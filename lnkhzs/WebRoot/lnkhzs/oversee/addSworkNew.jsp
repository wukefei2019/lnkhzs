<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript"
	src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>
	
<script type="text/javascript" src="${ctx }/lnkhzs/oversee/addSworkNew.js"></script>

<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css" rel="stylesheet" type="text/css" />

<meta charset="UTF-8">
<title>督办表单</title>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div id="formTable" style="margin-top: 10px;"
			class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					表单信息 <a class='btn floatRight10 handOut' style='display: inline;'>发起</a>
					<a class='btn floatRight10 handOutSave' style='display: inline;'>保存</a>
				</h3>
			</div>
			<form id='form' name='form' target="frm" class="collapse in">


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
				

				<div class="form-table">

					<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">编号：</label>
						</div>
						<div class='col-xs-9'>
							<input type="number" style="width: 30%" id="serial_number"
								class='form-control' name='sWork.serial_number' value='${sWork.serial_number }' />
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

					<%-- <div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label class="required">督办问题描述：</label>
						</div>
						<div class='col-xs-9'>
							<input id="description" type="text" style="width: 100%"
								class='form-control' name='sWork.description'
								value='${sWork.description}' />
						</div>
					</div> --%>
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
		
		
		
		<div id="flow_s" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					数据导入 
				</h3>
				
			</div>
		<div class="Ct Dialog">
        <div class="bg_gray2">
                <input type="hidden" name="callback" value="import_callback_new"/>
                <input type="hidden" id='fileName' name="fileName" value="" />
                <div class="padding20 border1 borderTop1">
                    <table width="100%" class="table_body2">
                        <tr>
                            <td width="12%" class="textalignRight paddingRight10"></td>
                            <td width="13%" colspan="3">
                            	<form id="pushFiles" action="" method="post" >
    								<input type="file" id="files" name="files" multiple="multiple"  value="" onchange="fileChanged()">
								</form>
                            </td>
                        </tr>
                        
                        <tr >
                            <td width="12%" class="textalignRight paddingRight10"></td>
                            <td width="13%" colspan="3"  id="showAllSelectedFiles">
                            	<div> aaa </div>
                            	<div>  bbb  </div>
                            </td>
                        </tr>
                        
                    </table>
                </div>
            </form>
	</div>
</body>
</html>