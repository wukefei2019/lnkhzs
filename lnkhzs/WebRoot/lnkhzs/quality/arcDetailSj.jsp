<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<head>
<meta charset="UTF-8">
	<title>升级工单明细日报表</title> <%-- <script type="text/javascript" src="${ctx }/lnkhzs/khzs/addKhzs.js"></script> --%>
	<%-- <script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script> --%>
</head>
<body>
	<div class="Ct Dialog contract form-page">
		<div class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>升级工单明细日报表</h3>
			</div>
			<iframe name="frm" src="" style="display:none" id="frm"></iframe>
			<div class="form-table">
			<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>受理渠道：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.acptChnlNm}</div>
				<div class='col-xs-3'>
					<label>工单流水号：</label>
				</div>
				
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailSj.wrkfmShowSwftno}</div>
                 </div>
                  <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>立单部门：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.acptOrgaName}</div>

				<div class='col-xs-3'>
					<label>受理时间：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.acptTime}</div>
              </div>
              <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>用户归属地：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.areaName}</div>

				<%-- <div class='col-xs-3'>
							<label >服务请求类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsArcDetailSj.srvReqstTypeFullNm}
						</div> --%>
				<div class='col-xs-3'>
					<label>用户星级：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.custStargrdNm}</div>
                </div>
                <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>结束处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.staffId4}</div>
				<div class='col-xs-3'>
					<label>结束工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.groupName4}</div>
                </div>
                <div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>反馈方式：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.fdbkMode}</div>
				<div class='col-xs-3'>
					<label>结单日期：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.arcTime}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>工单满意度：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.lastCustSatis}</div>
				<div class='col-xs-3'>
					<label>责任部门：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.respDept}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>责任原因 ：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.respRsnNm}</div>
				<div class='col-xs-3'>
					<label>用户参加的营销活动：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.cmpgnNm}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>联系方式：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.cntway}</div>
				<div class='col-xs-3'>
					<label>解决程度：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.rslvExtent}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.staffId0}</div>
				<div class='col-xs-3'>
					<label>首处理处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.staffId1}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>协办处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.staffId2}</div>
				<div class='col-xs-3'>
					<label>整体时限时间：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.exprTime}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>疑难客户类型：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailSj.difcltCmplntsTypeCd}</div>
				<div class='col-xs-3'>
					<label>统计日期：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.statisDate}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>反馈处理人：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.staffId3}</div>
				<div class='col-xs-3'>
					<label>首处理工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.groupName1}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>协办工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.groupName2}</div>
				<div class='col-xs-3'>
					<label>立单工号：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.acptStaffNum}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>反馈工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.groupName3}</div>
				<div class='col-xs-3'>
					<label>声称升级：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.cmplnUpgd}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理工作组：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.groupName0}</div>
				<div class='col-xs-3'>
					<label>目标机构：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.orgBrnch}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>立单环节评价：</label>
				</div>
				<div class='col-xs-3'>
					${sWfCmplntsArcDetailSj.preWorkitemEval}</div>
				<div class='col-xs-3'>
					<label>外部系统：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.otherSystem2}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>处理时长(含夜间)：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.totalTime2}</div>
				<div class='col-xs-3'>
					<label>处理时长：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.totalTime3}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>前期10086投诉次数：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.no1}</div>
				<div class='col-xs-3'>
					<label>前期涉及10086渠道工单流水：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.no2}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>前期10086渠道处理人员：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.no3}</div>

				<div class='col-xs-3'>
					<label>是否省内结单：</label>
				</div>
				<div class='col-xs-3'>${sWfCmplntsArcDetailSj.no5}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label>入库时间：</label>
				</div>
				<div class='col-xs-9'  style='white-space:normal;overflow-wrap: break-word;'>
				${sWfCmplntsArcDetailSj.createTime}</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
					<label >服务请求类别：</label>
						</div>
						<div class='col-xs-9'  style='white-space:normal;overflow-wrap: break-word;'>
							${sWfCmplntsArcDetailSj.srvReqstTypeFullNm}
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label >业务内容：</label>
						</div>
						<div class='col-xs-9'  style='white-space:normal;overflow-wrap: break-word;'>
							${sWfCmplntsArcDetailSj.bizCntt}
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label >处理意见：</label>
						</div>
						<div class='col-xs-9'  style='white-space:normal;overflow-wrap: break-word;'>
							${sWfCmplntsArcDetailSj.dspsOpinCntt}
						</div>
						</div>

			</div>
		</div>
	</div>
	</body>
</html>

