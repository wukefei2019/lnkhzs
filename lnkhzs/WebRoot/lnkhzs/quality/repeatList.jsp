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
		<title>重复投诉工单明细</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/quality/repeatList.js"></script>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_REPEAT.query" autoLoad="false">
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
                <table data-show-columns="true" id="table1" data-height='auto' data-fixed-columns="false" data-fixed-number="3" data-on-load-success="table_load_success"
	                class="table table-hover text_align_center table-no-bordered" data-show-columns="false">
	                <thead>
                        <tr>
                        	<th data-field="STATIS_DATE" data-sortable='true'>统计日期</th>
                        	<th data-field="WRKFM_SHOW_SWFTNO" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false" data-sortable='true'>工单流水号</th>
                            <th data-field="ACPT_CHNL_NM" data-sortable='true'>受理渠道</th>
                        	<th data-field="ACPT_TIME" data-sortable='true'>受理时间</th>
                        	<th data-field="ACPT_NUM">受理号码</th>
                        	<th data-field="AREA_NAME" data-sortable='true'>用户归属地</th>
                        	<th data-field="SRV_REQST_TYPE_FULL_NM" data-sortable='true'>服务请求类别</th>
                        	<th data-field="BIZ_CNTT" data-formatter="fmt_operate" data-sortable='true'>业务内容</th>
                        	<th data-field="DSPS_OPIN_CNTT" data-formatter="fmt_operate_clyj" data-sortable='true'>处理意见</th>
                        	<th data-field="ARC_TIME" data-sortable='true'>结单日期</th>
                        	<th data-field="RSLV_EXTENT" data-sortable='true'>解决程度</th>
                        	<th data-field="LAST_CUST_SATIS" data-sortable='true'>工单满意度 </th>
                        	<th data-field="RESP_DEPT" data-sortable='true'>责任部门</th>
                            <th data-field="RESP_RSN_NM"data-sortable='true'>责任原因</th>
                            <th data-field="GROUP_NAME0" data-sortable='true'>处理工作组</th>
                            <th data-field="GROUP_NAME1" data-sortable='true'>首处理工作组</th>
                            <th data-field="GROUP_NAME2" data-sortable='true'>协办工作组</th>
                            <th data-field="GROUP_NAME3" data-sortable='true'>反馈工作组</th>
                            <th data-field="GROUP_NAME4" data-sortable='true'>结束工作组</th>
                            <th data-field="DIFCLT_CMPLNTS_TYPE_CD" data-sortable='true'>疑难客户类型</th>
                        	
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
