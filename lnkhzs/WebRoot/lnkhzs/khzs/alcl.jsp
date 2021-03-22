
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户之声信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/alcl.js"></script>

 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="${param.sqlname}" >
            <dg:lefttoolbar>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
            </dg:lefttoolbar>
            <dg:condition>
                <input type="hidden" name="NEXTUSER" value="${userid}"/>
                <input type="hidden" name="FLOWSTATUS" value="部门处理,部门处理（挂起）,专员处理,专员处理（挂起 ）,部门复核"/>
                <input type="hidden" name="laststatus" value="部门处理,部门处理（挂起）,专员处理,专员处理（挂起 ）,部门复核"/>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table data-height='auto' data-fixed-columns='false' data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
                            <th data-field="IDX" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" data-sortable='true'>序号</th>
                            <th data-field="TYPE" data-sortable='true'>案例类型</th>
                            <th data-field="CREATETIME" data-sortable='true'>提交时间</th>
                            <th data-field="THEME" data-sortable='true'>主题</th>
                            <th data-field="QUESTIONTYPE" data-sortable='true'>问题类型</th>
                            <th data-field="DESCRIPTION" data-sortable='true'>问题描述</th>
                        	<th data-field="SOLUTION" data-sortable='true'>建议解决措施</th>
                        	<!-- <th data-field="REMARK">备注</th>  -->                     	
                        	<th data-field="FLOWSTATUS" data-sortable='true'>流程状态</th>
                            <th data-field="FULLNAME" data-sortable='true'>发起人</th>                            
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
