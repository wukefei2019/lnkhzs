<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>设置部门溯源管理员</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/trace/traceSourceManager.js"></script>

    <% 
        Map inmap = new HashMap();
        inmap.put("userCityName",cityName);
        request.setAttribute("inmap", inmap);
    %>
 </head>
<body>
<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_ZL_TRACE_SOURCE_DEPADMIN.query" inmap="${inmap }">
			  <dg:lefttoolbar>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
                <button class="btn-link iconfont fontsize14 icon-roundadd" >新增</button>
                <button class="btn-link icon iconfont fontsize14 icon-sousuo1 query" onclick="showsearch('table0')">搜索</button>
            </dg:lefttoolbar>
            <dg:condition>
                <input type="hidden" name="data_status" value="2"/>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
                <table data-height='auto' data-fixed-columns='false' data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<!-- <th data-checkbox="true" data-report-col-mode="none"></th> -->
                            <th data-field="PID" data-visible='false' >主键</th>
                            <th data-field="DEPLEVEL2ID" data-visible='false' >二级部门id</th>
                            <th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
                            <th data-field="DEPNAME" data-visible='false' >机构名称</th>
                            <th data-field="DEPLEVEL2NAME" data-sortable='true'>二级部门名称</th>
                            <th data-field="LOGINNAME" data-visible='false' data-sortable='true'>登录名唯一</th>
                            <th data-field="FULLNAME" data-sortable='true'>管理员</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>

	</body>
</html>