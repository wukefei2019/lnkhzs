<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8">
    <title></title>
</head>
<script type="text/javascript">
function operate_formatter(value, row, index) {
	var icon="<a class='btn btn-link fontsize12' href='javascript:void(0)'>下载</a>";		
    return icon;
}

var operateEvents = {
	    'click a:eq(0)': function (e, value, row, index) {
				var exporurl = $ctx + "/omcs/baseExport/exportExcel.action?path="+row.PATH;
				openwindow(exporurl,'','600','600');
	    }
};
</script>
<body>
    <div class="Ct">
        <eoms:menuway id="${navid }"></eoms:menuway>
    <dg:ajaxgrid id="table0" sqlname="SQL_DOC_DOWNLOAD.QUERY" >
            <dg:lefttoolbar>
                <button class="btn-link search iconfont icon-sousuo1 fontsize14" onclick="showsearch('table0')" >搜索</button>
                <button class="btn-link refresh iconfont icon-refresh2 fontsize14" onclick="$.bs.table.refresh('table0')" >刷新</button>
            </dg:lefttoolbar>
            <dg:condition>
            	<input type="hidden" name="loginName" value="${user.loginName}" />
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset"><i class="icon iconfont icon-loading2 fontsize14"></i>重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh" onclick="$.bs.table.reload('table0');"><i class="icon iconfont icon-searchlist fontsize14"></i>查找</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table data-height='auto' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-field="FILENAME" data-sortable="true">文件名</th>  
                       	    <th data-field="EXPORTTIME" data-sortable="true" data-formatter="longToTime">导出时间</th>
                       	    <th data-formatter="operate_formatter" data-events="operateEvents">操作</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
    </div>
    <iframe name="download" src="" style="display: none"></iframe>
</body>
</html>