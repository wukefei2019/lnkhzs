<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>服务请求明细日报表</title> <%-- <script type="text/javascript" src="${ctx }/lnkhzs/khzs/addKhzs.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script> --%>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>服务请求明细日报表</h3>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
			<div class="form-table">
			 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>上报集团：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.rptldGroup}</div>
				<div class='col-xs-3'>
					<label>受理渠道：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.acptChnlNm}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>立单工号：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.acptStaffNum}</div>
				<div class='col-xs-3'>
					<label>立单部门：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.acptOrgaName}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>受理时间：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.acptTime}</div>
				<%-- <div class='col-xs-3'>
							<label >受理号码：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsArcDetailNo.acptNum}
						</div>
						<div class='col-xs-3'>
							<label >用户姓名：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsArcDetailNo.acptUserName}
						</div> --%>
				<div class='col-xs-3'>
					<label>用户归属地：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.areaName}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">

				<div class='col-xs-3'>
					<label>用户星级：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.custStargrdNm}</div>
				<div class='col-xs-3'>
					<label>客户回访满意度：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailNo.smsFdbkSatisDgr}</div>
					</div>
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>结束处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.staffId4}</div>
				<div class='col-xs-3'>
					<label>结束工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.groupName4}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>反馈方式：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.fdbkMode}</div>
				<div class='col-xs-3'>
					<label>结单日期：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.arcTime}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>工单满意度 ：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.lastCustSatis}</div>
				<div class='col-xs-3'>
					<label>负责部门：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.respDept}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>责任原因：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.respRsnNm}</div>
				<div class='col-xs-3'>
					<label>外部系统：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.otherSystem}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>不满意原因：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.nSatisRsnDesc}</div>

				<div class='col-xs-3'>
					<label>用户参加的营销活动：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.cmpgnNm}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理时长：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.totalTime}</div>
				<div class='col-xs-3'>
					<label>联系方式：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.cntway}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>解决程度：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.rslvExtent}</div>
				<div class='col-xs-3'>
					<label>处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.staffId0}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>首处理处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.staffId1}</div>
				<div class='col-xs-3'>
					<label>协办处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.staffId2}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>整体时限时间：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.exprTime}</div>
				<div class='col-xs-3'>
					<label>疑难客户类型：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailNo.difcltCmplntsTypeCd}</div>
					</div>
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>统计日期：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.statisDate}</div>
				<div class='col-xs-3'>
					<label>反馈处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.staffId3}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>首处理工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.groupName1}</div>
				<div class='col-xs-3'>
					<label>工单回访标志：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailNo.satisDgrRevstMode}</div>
					</div>
					
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>协办工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.groupName2}</div>
				<div class='col-xs-3'>
					<label>工单流水号：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailNo.wrkfmShowSwftno}</div>
					</div>
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>反馈工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.groupName3}</div>
				<div class='col-xs-3'>
					<label>声称升级：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.cmplnUpgd}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.groupName0}</div>
				<div class='col-xs-3'>
					<label>目标机构：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.orgBrnch}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>立单环节评价：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailNo.preWorkitemEval}</div>
				<div class='col-xs-3'>
					<label>外部系统：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.otherSystem2}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理时长(含夜间)：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.totalTime2}</div>
				<div class='col-xs-3'>
					<label>处理时长：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailNo.totalTime3}</div>
				</div>
				 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>服务请求类别：</label>
				</div>
				<div class='col-xs-9'
					style='white-space:normal;overflow-wrap: break-word;'>
					${sWfCmplntsArcDetailNo.srvReqstTypeFullNm}</div>
					</div>
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>业务内容：</label>
				</div>
				<div class='col-xs-9'
					style='white-space:normal;overflow-wrap: break-word;'>
					${sWfCmplntsArcDetailNo.bizCntt}</div>
					</div>
					 <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理意见：</label>
				</div>
				<div class='col-xs-9'
					style='white-space:normal;overflow-wrap: break-word;'>
					${sWfCmplntsArcDetailNo.dspsOpinCntt}</div>
					</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>首次咨询响应时长：</label>
				</div>
				<div class='col-xs-9'
					style='white-space:normal;overflow-wrap: break-word;'>
					${sWfCmplntsArcDetailNo.startScDuration}</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>
