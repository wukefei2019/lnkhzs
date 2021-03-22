<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8"></meta>
<title>问题列表</title>
<style type="text/css">
	.bootstrap-table table tbody td {
	    white-space: pre-wrap;
	}
</style>
<script type="text/javascript">

	$(document).ready(function() {
		$(".xiala_menu-backdrop", window.parent.document).trigger("click");
	});

	function operate_formatter(value, row, index) {
	    return [
	       "<a class='btn btn-link' style='font-size: 12px;' href='javascript:void(0)' title='详细信息'>详细信息</a>"
	    ].join('');
	}
	
	var operateEvents ={
		'click a:eq(0)' : function(e, value, row, index) {
			openwindow($ctx + "/lnkhzs/issueTrack/track.jsp?parentid=" + row.RETURNTEXT);
		}
	};
</script>
</head>
<body>
<eoms:menuway id="${navid }"></eoms:menuway>
	<div class="Ct" style="width: 100%; height: 100%;">
		<div style="width: 100%; height: 100%;"> <!--  class="panel panel-primary bill-unit" -->
			<dg:ajaxgrid id="table0" sqlname="SQL_PF_TRACK_LIST.query" >
				<dg:lefttoolbar>
				</dg:lefttoolbar>

				<dg:ajax-table>
					<table id="btable" data-height='auto' data-page-size='10' data-page-list='[10, 20, 50,100]'   class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
						<thead>
							<tr>
								<th data-formatter="operate_formatter" data-events="operateEvents">操作</th>  
								<th data-field="APPLYTITLE" data-sortable='true'>派发问题标题</th>
								<th data-field="MATTERCATEGORY" data-sortable='true'>问题分类</th>
								<th data-field="LABELCATEGORY" data-sortable='true'>标签分类</th>
								<!-- <th data-field="MAINDEPART" data-sortable='true'>主责部门</th> -->
								<th data-field="EXPIRYDATE" data-sortable='true'>截止日期</th>
								<th data-field="SUBJECT" data-sortable='true'>派单主题</th>
								<th data-field="CONTENT" data-sortable='true'>派单内容</th>
								<th data-field="VALIDMETHOD" data-sortable='true'>验证方式</th>
								<th data-field="VALIDVALUE" data-sortable='true'>验证值</th>
								<th data-field="REPORTSTATUS" data-sortable='true'>状态</th>
								<!-- <th data-field="RETURNTEXT" data-sortable='true' visible="false">返回内容</th> -->
							</tr>
						</thead>
					</table>
				</dg:ajax-table>
			</dg:ajaxgrid>
		</div>
	</div>
</body>
</html>