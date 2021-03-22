<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户之声信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/flowList.js"></script>

    <% 
        Map inmap = new HashMap();
        inmap.put("userCityName",cityName);
        request.setAttribute("inmap", inmap);
    %>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_KHZS.query" inmap="${inmap }">
            <dg:ajax-table>
                <table data-height='auto' data-detail-view="true"  data-detail-formatter="detailFormatter" class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-formatter="fmt_operate" data-report-col-mode="none" data-events="fn_operate_events">操作</th>
                            <th data-field="PID" data-visible='false'>主键</th>
                        	<th data-field="IDX">序号</th>
                        	<th data-field="TYPE">类型</th>
                            <th data-field="THEME" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look">主题</th>
                            <th data-field="QUESTIONTYPE">问题类型</th>
                            <th data-field="SOURCE">问题来源</th>
                            <th data-field="DESCRIPTION">问题描述</th>
                            <th data-field="SOLUTION">解决方案</th>
                            <th data-field="FLOWSTATUS" data-dictype='khzsstatus' >流程状态</th>
                            <th data-field="FULLNAME" >发起人全名</th>
                            <th data-field="LOGINNAME" data-visible='false'>发起人登入名</th>
                            <th data-field="CREATETIME" data-visible='false'>发起时间</th>
                            <th data-field="NEXTFULLNAME">下一级处理人全名</th>
                            <th data-field="NEXTLOGINNAME" data-visible='false'>下一级处理人登入名</th>
                            <th data-field="NEXTDEPNAME">处理人机构</th>
                            <th data-field="DEPNAME">处理人机构</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
