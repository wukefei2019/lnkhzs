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
<%-- 
<script type='text/javascript' src='${ctx }/common/style/omcs/js/common/jquery-3.3.1.min.js'></script> --%>
<script type='text/javascript' src='${ctx }/common/style/omcs/js/common/select2.full.min.js'></script>
<link rel="stylesheet" href="${ctx }/common/style/omcs/css/common/select2.css" type="text/css"></link>

<html>
	<head>
		<meta charset="UTF-8">
		<title>服务请求明细日报表</title>
		<script type="text/javascript" src="${ctx }/lnkhzs/quality/reqstList.js"></script>
 	</head>
	<body>
		<div class="Ct Dialog">
			<eoms:menuway id="${navid }"></eoms:menuway>
			<dg:ajaxgrid id="table0" sqlname="SQL_QUALITY.query" autoLoad="false"> <!-- conditionIsOpen="true" -->
	 			<dg:condition>
	                <div class="btn_box overflow">
	                    <div id="selectaa" class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14" onclick="changeSelVal()">查找</div>
	                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
	                    <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div>
	                </div>
	            </dg:condition>
	            <%-- <dg:lefttoolbar>
	                <button class="btn-link icon iconfont fontsize14 icon-sousuo1 query" onclick="showsearch('table0')">搜索</button>
	                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
	            </dg:lefttoolbar> --%>
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

	                <table   data-show-columns="true" id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
	                	 class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
	                    <thead>
	                        <tr>
	                        	<th data-field="STATIS_DATE" data-sortable='true'>统计日期</th>
	                        	<th data-field="SRV_REQST_ID" data-formatter="$.bs.table.fmt.link" data-events="fn_detail" data-switchable="false" data-sortable='true'>服务请求流水</th>
	                            <th data-field="WKFM_SHOW_SWFTNO"data-sortable='true'>工单流水</th>
	                            <th data-field="CALL_ID" data-sortable='true'>呼叫流水号</th>
	                            <th data-field="A01" data-sortable='true'>接触时长</th>
	             <!--               <th data-field="CALLING_NUM">受理号码</th>
	                            <th data-field="CALLING_NUM_Z">主叫号码</th> -->
	                            <th data-field="CALLED_NUM" data-sortable='true'>被叫号码</th>
	                            <th data-field="AREA_NAME" data-sortable='true'>受理号码归属地市</th>
	                            <th data-field="CODE_NM" data-sortable='true'>用户星级</th>
	                            <th data-field="ACPT_CHNL_ID_NAME" data-sortable='true'>受理渠道</th>
	                            <th data-field="ACPT_TIME" data-sortable='true'>受理时间</th>
	                            <th data-field="ACPT_STAFF_NUM" data-sortable='true'>受理工号</th>
	                            <th data-field="A02" data-sortable='true'>服务请求1级节点类型</th>
	                            <th data-field="A03" data-sortable='true'>服务请求2级节点类型</th>
	                            <th data-field="A04" data-sortable='true'>服务请求3级节点类型</th>
	                            <th data-field="A05" data-sortable='true'>服务请求4级节点类型</th>
	                            <th data-field="A06" data-sortable='true'>服务请求5级节点类型</th>
	                            <th data-field="A07" data-sortable='true'>服务请求6级节点类型</th>
	                            <th data-field="A08" data-sortable='true'>服务请求7级节点类型</th>
	                            <th data-field="A09" data-sortable='true'>服务请求8级节点类型</th>
	                            <th data-field="A10" data-sortable='true'>服务请求9级节点类型</th>
	                            <th data-field="A11" data-sortable='true'>服务请求类型名称</th>
	                            <th data-field="A12" data-sortable='true'>服务请求全路径</th>
	                            <th data-field="A13" data-formatter="fmt_operate_slnr" data-sortable='true'>受理内容</th>
	                            <!-- <th data-formatter="fmt_operate">受理内容</th> -->
	                            <th data-field="IF_LD" data-sortable='true'>是否创建工单</th>
	                            <th data-field="IF_XC_JJ" data-sortable='true'>是否现场解决</th>
	                            <th data-field="IF_TS"data-sortable='true'>是否投诉</th>
	                            <th data-field="IF_CFTS" data-sortable='true'>是否重复投诉</th>
	                            <th data-field="IF_SJTT" data-sortable='true'>是否升级投诉</th>
	                            <th data-field="USER_SATISFY" data-sortable='true'>满意度结果</th>
	                            <th data-field="STAFF_NAME" data-sortable='true'>受理员工</th>
	                            <th data-field="TEAM_NAME" data-sortable='true'>受理部门</th>
	                            <th data-field="DEPT_NAME" data-sortable='true'>部室名称</th>
	                            <th data-field="STAFF_TYPE_KIND" data-sortable='true'>人员类型</th>
	                            <th data-field="CLASS_NAME" data-sortable='true'>众包公司名称</th>
	                        </tr>
	                    </thead>
	                </table>
	            </dg:ajax-table>
		    </dg:ajaxgrid>
		    <iframe name="download" src="" style="display:none"></iframe>
		</div>
	</body>
</html>
