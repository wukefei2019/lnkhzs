<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/approval.js"></script>

<html>
	<head>
		<meta charset="UTF-8">
		<title>辽宁公司服务请求节点</title>
	<%
        HashMap<String,String> inmap = new HashMap<String,String>();
        inmap.put("userid",user.getLoginName());
        request.setAttribute("inmap", inmap);
     %>
        <script type="text/javascript" >
        
	        function getTablename() {
	        	var tablename = "ZL_SERVICE_REQUEST_NODE";
	        	return tablename;
	        }
        </script>
 </head>
<body>
<div class="Ct Dialog">
 	<dg:ajaxgrid id="table0" sqlname="${param.sqlname}" inmap="${inmap }">
 	<input type='hidden' id='userid' name='userid' value='${inmap.get("userid") }'/>
           	
           	<dg:lefttoolbar>
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
            	<button class="btn floatRight10" style="float:right" onclick="reject()">驳回</button>  
            	<button class="btn floatRight10" style="float:right" onclick="agree()">通过</button> 
                    <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' 
					class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
					<thead>
                        <tr>
                        	<th data-checkbox="true" data-report-col-mode="none"></th>
                        	<th data-field="PID" data-visible="false">pid</th>
                        	<th data-field="BATCHNO">批次</th>
                        	<th data-field="CODE01" data-visible="false" >编码1级</th>
                        	<th data-field="NAME01" data-sortable='true'>业务分类1级</th>
                            <th data-field="CODE02" data-visible="false" >编码2级</th>
                            <th data-field="NAME02" data-sortable='true'>业务分类2级</th>
                            <th data-field="CODE03" data-visible="false" >编码3级</th>
                            <th data-field="NAME03" data-sortable='true'>业务分类3级</th>
                            <th data-field="CODE04" data-visible="false" >编码4级</th>
                            <th data-field="NAME04" data-sortable='true'>业务分类4级</th>
                            <th data-field="CODE05" data-visible="false" >编码5级</th>
                            <th data-field="NAME05" data-sortable='true'>问题分类5级</th>
                            <th data-field="CODE06" data-visible="false" >编码6级</th>
                            <th data-field="NAME06" data-sortable='true'>问题分类6级</th>
                            <th data-field="CODE07" data-visible="false" >编码7级</th>
                            <th data-field="NAME07" data-sortable='true'>问题分类7级</th>
                            <th data-field="REMARKS" data-sortable='true'>备注</th>
                            <th data-field="TYPE ADJUST EXPLANATION" data-sortable='true'>分类调整说明</th>
                            <th data-field="BUSINESS ATTRIBUTION" data-sortable='true'>业务归属部门</th>
                            <th data-field="BUSINESS TYPE" data-sortable='true'>业务类型</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
