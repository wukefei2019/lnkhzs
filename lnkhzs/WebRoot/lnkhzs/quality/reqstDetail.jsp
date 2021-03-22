<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/core/taglib_omcs.jsp"%>
    <head>
        <meta charset="UTF-8">
        <title>服务请求明细日报表</title>
        <%-- <script type="text/javascript" src="${ctx }/lnkhzs/khzs/addKhzs.js"></script> --%>
        <%-- <script type="text/javascript" src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script> --%>
        
    
	</head>
    <body>
        <div class="Ct Dialog contract form-page" >
            <div class='panel panel-primary'>
                <div class='panel-heading'>
                    <h3 class='panel-title'>服务请求明细日报表</h3> 
                </div>
                   <iframe name="frm" src="" style="display:none" id="frm"  ></iframe>
					<div class="form-table">
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >统计日期：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.statisDate}
						</div>
						<div class='col-xs-3'>
							<label >服务请求流水：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.srvReqstId	}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >工单流水号：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.wkfmShowSwftno}
						</div>
						<div class='col-xs-3'>
							<label >呼叫流水号：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.callId}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >接触时长：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a01}
						</div>
						<%-- <div class='col-xs-3'>
							<label >受理号码：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.callingNum	}
						</div>
						<div class='col-xs-3'>
							<label >主叫号码：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.callingNumZ}
						</div> --%>
						<div class='col-xs-3'>
							<label >被叫号码：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.calledNum}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >受理号码归属地市：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.areaName}
						</div>
						<div class='col-xs-3'>
							<label >用户星级：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.codeNm	}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >受理渠道：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.acptChnlIdName}
						</div>
						<div class='col-xs-3'>
							<label >受理时间：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.acptTime}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >受理工号：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.acptStaffNum}
						</div>
						<div class='col-xs-3'>
							<label >服务请求1级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a02	}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求2级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a03}
						</div>
						<div class='col-xs-3'>
							<label >服务请求3级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a04}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求4级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a05}
						</div>
						<div class='col-xs-3'>
							<label >服务请求5级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a06}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求6级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a07}
						</div>
						<div class='col-xs-3'>
							<label >服务请求7级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a08}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求8级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a09}
						</div>
						<div class='col-xs-3'>
							<label >服务请求9级节点类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a10}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求类型名称：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.a11}
						</div>
						<div class='col-xs-3'>
							<label >是否创建工单：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.ifLd}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >是否现场解决：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.ifXcJj}
						</div>
						<div class='col-xs-3'>
							<label >是否投诉：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.ifTs}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >是否重复投诉：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.ifCfts}
						</div>
						<div class='col-xs-3'>
							<label >是否升级投诉：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.ifSjtt}
						</div>
						</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >满意度结果：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.userSatisfy}
						</div>
						<div class='col-xs-3'>
							<label >受理员工：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.staffName}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >受理部门：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.teamName}
						</div>
						<div class='col-xs-3'>
							<label >部室名称：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.deptName}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >人员类型：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.staffTypeKind}
						</div>
						<div class='col-xs-3'>
							<label >众包公司名称：</label>
						</div>
						<div class='col-xs-3'>
							${sWfCmplntsReqstDetail.className}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >服务请求全路径：</label>
						</div>
						<div class='col-xs-9' style='white-space:normal;overflow-wrap: break-word;'>
							${sWfCmplntsReqstDetail.a12}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
						<div class='col-xs-3'>
							<label >受理内容：</label>
						</div>
						<div class='col-xs-9' style='white-space:normal;overflow-wrap: break-word;'>
							<%-- ${sWfCmplntsReqstDetail.a13}  --%>${strA13}
						</div>
					</div>
					<div class="row" style="margin-left: 0px;margin-right: 0px">
					</div>
           	</div>
        </div>
   	</body>
</html>
