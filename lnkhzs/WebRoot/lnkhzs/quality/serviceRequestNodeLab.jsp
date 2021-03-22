<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/serviceRequestNodeLab.js"></script>

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
 	<dg:ajaxgrid id="table0" sqlname="SQL_SERVICE_REQUEST_NODE.query" inmap="${inmap }">
           	<dg:lefttoolbar>
<!--                 <button class="btn-link icon iconfont fontsize14 icon-sousuo1 query" onclick="showsearch('table0')">搜索</button>-->
                <button class="btn-link iconfont fontsize14 icon-roundadd" >新增</button>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button> 
                <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
            </dg:lefttoolbar> 
            <dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14"onclick="changeSelVal()">查找</div>
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' 
                	 class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
                        	<th data-field="PID" data-visible="false">pid</th>
                        	<th data-field="CODE01" data-visible="false" data-sortable='true'>编码1级</th>
                        	<th data-field="NAME01" data-sortable='true'>业务分类1级</th>
                            <th data-field="CODE02"  data-visible="false" data-sortable='true'>编码2级</th>
                            <th data-field="NAME02" data-sortable='true'>业务分类2级</th>
                            <th data-field="CODE03" data-visible="false" data-sortable='true'>编码3级</th>
                            <th data-field="NAME03" data-sortable='true'>业务分类3级</th>
                            <th data-field="CODE04" data-visible="false" data-sortable='true'>编码4级</th>
                            <th data-field="NAME04" data-sortable='true'>业务分类4级</th>
                            <th data-field="CODE05"  data-visible="false" data-sortable='true'>编码5级</th>
                            <th data-field="NAME05" data-sortable='true' >问题分类5级</th>
                            <th data-field="CODE06" data-visible="false" data-sortable='true'>编码6级</th>
                            <th data-field="NAME06" data-sortable='true'>问题分类6级</th>
                            <th data-field="CODE07" data-visible="false" data-sortable='true'>编码7级</th>
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
