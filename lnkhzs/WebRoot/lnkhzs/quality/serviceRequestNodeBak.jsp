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
<script type="text/javascript" src="${ctx }/lnkhzs/quality/serviceRequestNodeBak.js"></script>

<html>
	<head>
		<meta charset="UTF-8">
		<title>辽宁公司服务请求节点</title>
        <script type="text/javascript" >
	        function getTablename() {
	        	var tablename = "ZL_SERVICE_REQUEST_NODE";
	        	return tablename;
	        }
        </script>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 	<dg:ajaxgrid id="table0" sqlname="SQL_SERVICE_REQUEST_NODE_BAK.query" inmap="${inmap }" autoLoad="false">
           	<dg:lefttoolbar>
<!--                 <button class="btn-link icon iconfont fontsize14 icon-sousuo1 query" onclick="showsearch('table0')">搜索</button>-->
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button> 
            </dg:lefttoolbar> 
            <dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14"onclick="changeSelVal()">查找</div>
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
                    <!-- <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div> -->
                </div>
            </dg:condition>
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
            	<!-- <button class="btn floatRight10" style="float:right" onclick="reject()">驳回</button>  -->
            	<button class="btn floatRight10" style="float:right" onclick="submitSerReqNode()">提交</button> 
                <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
                	 class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-checkbox="true" data-report-col-mode="none"></th>
                        	<th data-field="PID" data-visible="false">pid</th>
                        	<th data-field="BATCHNO" data-sortable='true'>批次</th>
                        	<th data-field="CODE01" data-visible="false" >编码1级</th>
                        	<th data-field="NAME01" data-sortable='true'>业务分类1级</th>
                            <th data-field="CODE02"  data-visible="false" >编码2级</th>
                            <th data-field="NAME02" data-sortable='true'>业务分类2级</th>
                            <th data-field="CODE03" data-visible="false" >编码3级</th>
                            <th data-field="NAME03" data-sortable='true'>业务分类3级</th>
                            <th data-field="CODE04" data-visible="false" >编码4级</th>
                            <th data-field="NAME04" data-sortable='true'>业务分类4级</th>
                            <th data-field="CODE05"  data-visible="false" >编码5级</th>
                            <th data-field="NAME05" data-sortable='true'>问题分类5级</th>
                            <th data-field="CODE06" data-visible="false" >编码6级</th>
                            <th data-field="NAME06" data-sortable='true'>问题分类6级</th>
                            <th data-field="CODE07" data-visible="false" >编码7级</th>
                            <th data-field="NAME07" data-sortable='true'>问题分类7级</th>
                            <th data-field="REMARKS" data-sortable='true'>备注</th>
                            <th data-field="TYPE_ADJUST_EXPLANATION" data-sortable='true'>分类调整说明</th>
                            <th data-field="BUSINESS_ATTRIBUTION" data-sortable='true'>业务归属部门</th>
                            <th data-field="BUSINESS_TYPE" data-sortable='true'>业务类型</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
