<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/toolbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/ztree.custom.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/zCommon.js"></script>
<script type='text/javascript' src='${ctx }/common/style/omcs/js/common/select2.full.min.js'></script>
<link rel="stylesheet" href="${ctx }/common/style/omcs/css/common/select2.css" type="text/css"></link>
<html>
	<head>
		<meta charset="UTF-8">
		<title>投诉结单归档量明细</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/quality/complaintList.js"></script>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_COMPLAINT.query" inmap="${inmap }" autoLoad="false">
            <dg:condition>
                <div class="btn_box overflow">
	                    <div id="selectaa" class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14" onclick="changeSelVal()">查找</div>
	                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
	                    <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div>
                </div>
            </dg:condition>
            <dg:lefttoolbar>
	                数据截止日期:<span id="showEndTime"></span>
	            </dg:lefttoolbar>
            <dg:ajax-table>
            	<div class="t-wrap">
			 		<div class="t-toolbar">
			 			<div class="t-panels">
			 				<div class="t-panel tbar-panel-cart">
				 				<div id="replacepl">
				 				</div>

				 				<ul id="tree1" class="ztree" style="width:100%; height:inherit;margin-top: 0px;border: inherit"></ul>
			                </div>
			            </div>
			            <div class="t-tabs">
							<div class="icon-open t-tab-cart ">
								<i class="icon iconfont fontsize14"></i>
							</div>
						</div>
			        </div>
			    </div>
                <table data-show-columns="true" id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
	                class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-field="RPTLD_GROUP" data-sortable='true'>上报集团</th>
                        	<th data-field="ACPT_CHNL_NM" data-sortable='true'>受理渠道</th>
                            <th data-field="WRKFM_SHOW_SWFTNO" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false" data-sortable='true'>工单流水号</th>
                            <th data-field="ACPT_STAFF_NUM" data-sortable='true'>立单工号</th>
                            <th data-field="ACPT_ORGA_NAME" data-sortable='true'>立单部门</th>
                            <th data-field="ACPT_TIME" data-sortable='true'>受理时间</th>
                           <!--  <th data-field="ACPT_NUM">受理号码</th>
                            <th data-field="ACPT_USER_NAME">用户姓名</th> -->
                            <th data-field="AREA_NAME" data-sortable='true'>用户归属地</th>
                            <th data-field="SRV_REQST_TYPE_FULL_NM" data-sortable='true'>服务请求类别</th>
                            <th data-field="CUST_STARGRD_NM" data-sortable='true'>用户星级</th>
                            <th data-field="BIZ_CNTT" data-formatter="fmt_operate" data-sortable='true'>业务内容</th>
                            <th data-field="SMS_FDBK_SATIS_DGR" data-sortable='true'>客户回访满意度</th>
                            <th data-field="DSPS_OPIN_CNTT" data-formatter="fmt_operate_dxyj" data-sortable='true'>处理意见</th>
                            <th data-field="STAFF_ID4" data-sortable='true'>结束处理人</th>
                            <th data-field="GROUP_NAME4" data-sortable='true'>结束工作组</th>
                            <th data-field="FDBK_MODE" data-sortable='true'>反馈方式</th>
                            <th data-field="ARC_TIME" data-sortable='true'>结单日期</th>
                            <th data-field="LAST_CUST_SATIS" data-sortable='true'>工单满意度 </th>
                            <th data-field="RESP_DEPT" data-sortable='true'>责任部门</th>
                            <th data-field="RESP_RSN_NM" data-sortable='true'>责任原因</th>
                            <th data-field="N_SATIS_RSN_DESC" data-sortable='true'>不满意原因</th>
                            <th data-field="CMPGN_NM" data-sortable='true'>用户参加的营销活动</th>
                            <th data-field="CNTWAY" data-sortable='true'>联系方式</th>
                            <th data-field="RSLV_EXTENT" data-sortable='true'>解决程度</th>
                            <th data-field="STAFF_ID0" data-sortable='true'>处理人</th>
                            <th data-field="STAFF_ID1" data-sortable='true'>首处理处理人</th>
                            <th data-field="STAFF_ID2" data-sortable='true'>协办处理人</th>
                            <th data-field="EXPR_TIME" data-sortable='true'>整体时限时间</th>
                            <th data-field="DIFCLT_CMPLNTS_TYPE_CD" data-sortable='true'>疑难客户类型</th>
                            <th data-field="STATIS_DATE" data-sortable='true'>统计日期</th>
                            <th data-field="STAFF_ID3" data-sortable='true'>反馈处理人</th>
                            <th data-field="GROUP_NAME1" data-sortable='true'>首处理工作组</th>
                            <th data-field="SATIS_DGR_REVST_MODE" data-sortable='true'>工单回访标志</th>
                            <th data-field="GROUP_NAME2" data-sortable='true'>协办工作组</th>
                            <th data-field="GROUP_NAME3" data-sortable='true'>反馈工作组</th>
                            <th data-field="CMPLN_UPGD" data-sortable='true'>声称升级</th>
                            <th data-field="GROUP_NAME0" data-sortable='true'>处理工作组</th>
                            <th data-field="ORG_BRNCH" data-sortable='true'>目标机构</th>
                            <th data-field="PRE_WORKITEM_EVAL" data-sortable='true'>立单环节评价</th>
                            <th data-field="OTHER_SYSTEM2" data-sortable='true'>外部系统</th>
                            <th data-field="TOTAL_TIME2" data-sortable='true'>处理时长_含夜间</th>
                            <th data-field="TOTAL_TIME3" data-sortable='true'>处理时长</th>
                            <th data-field="CMPGN_ID" data-sortable='true'>业务/活动编码</th>
                            <th data-field="DSPS_DEPT_ID" data-sortable='true'>办理部门</th>
                            <th data-field="TRNS_STAFF_NUM" data-sortable='true'>办理工号</th>
                            <th data-field="HD_KIND" data-sortable='true'>是否省内话单</th>
                            <th data-field="WRKFM_SLEEP_TIME" data-sortable='true'>工单休眠时长（含夜间）</th>
                            <th data-field="SRV_REQST_TYPE_ID" data-sortable='true'>服务请求id</th>
                            <th data-field="CON_DEAL" data-sortable='true'>集中自处理情况</th>
                            <th data-field="ASSIST_CASE" data-sortable='true'>协查情况</th>
                            <th data-field="NO_WRKFM_CASE" data-sortable='true'>非派单情况</th>
                            
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
