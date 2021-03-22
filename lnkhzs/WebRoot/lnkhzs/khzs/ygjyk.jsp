
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户之声信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/ygjyk.js"></script>

 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_KHZS_YGJYK.query">
            <dg:lefttoolbar>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
                <button class="btn-link icon iconfont fontsize14 icon-download" onclick="exportSelVal()">导出</button>
                <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
            </dg:lefttoolbar>
            <dg:condition>
                <input type="hidden" name="NEXTUSER" value="${userid}"/>
                <input type="hidden" name="FLOWSTATUS" value="部门处理,部门处理（挂起）,专员处理,专员处理（挂起 ）,部门复核"/>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table data-height='auto' data-fixed-columns='false' data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-field="PID" data-visible="false" >PID</th>
                            <th data-field="TYPE" data-visible="false" >案例类型</th>
                            <th data-field="IDX" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" data-sortable='true'>序号</th>
                            <th data-field="CREATETIME" data-sortable='true'>提交时间</th>
                            <th data-field="THEME" data-sortable='true'>主题</th>
                            <th data-field="QUESTIONTYPE" data-sortable='true'>问题类型</th>
                            <th data-field="DESCRIPTION" data-sortable='true'>问题描述</th>
                        	<th data-field="DEPTNAME" data-sortable='true'>责任单位</th>
                        	<th data-field="REMARK2" data-sortable='true'>整改方案或处理意见</th>                       	
                            <th data-field="FULLNAME" data-sortable='true'>反馈人</th>                            
                        	<th data-field="FEEDTIME" data-sortable='true'>反馈时间</th>
                        	<th data-field="FEEDAPPRAISE" data-sortable='true'>提交人评价反馈</th>                            
                        	<th data-field="REMARK" data-sortable='true'>备注</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
