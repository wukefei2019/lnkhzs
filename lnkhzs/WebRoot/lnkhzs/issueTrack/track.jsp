<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/common/plugin/track/css/ystep.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/lnkhzs/issueTrack/track.js"></script>
<style type='text/css'>
	.ystep-lg li {
	    height: 65px;
	}
	.bootstrap-table table tbody td {
	    white-space: pre-wrap;
	}
</style>
<script type='text/javascript'>
	$(document).ready(function() {
		var UIDS = "${param.parentid}";
		var url = $ctx + "/track/trackSource/ajaxGetMaxStepData.action?parentid=" + UIDS;
		$.post(url).done(function(data) {
		
			var step1=new SetStep({
				content:'.stepCont1',
				showBtn:false,
				selStep:data,
				selPID:UIDS
			})
		});
		$(".xiala_menu-backdrop", window.parent.document).trigger("click");
		
	});

	function operate_formatter(value, row, index) {
	
		if (row.FORM.length>3) {
		    return [
		       "<a class='btn btn-link' style='font-size: 12px;' href='javascript:void(0)' title='详细信息'>详细信息</a>"
		    ].join('');
		} else {
			return "";
		}
	}
	
	var operateEvents ={
		'click a:eq(0)' : function(e, value, row, index) {
			openPlan(row.ID);
		}
	};
	
</script>
<html style="height: 100%;">
<head>
	<meta charset="UTF-8">
	<title>派发问题跟踪</title> 
</head>
<body class="bodyArea">
	<div class="stepCont stepCont1">
	<!-- 菜单导航显示-->
	<div class='ystep-container ystep-lg ystep-blue'></div>
	<!-- 
	<div class="pageCont">
		<div id="page1" class="stepPage">
		</div>
		<div id="page2" class="stepPage">
		</div>
		<div id="page3" class="stepPage">
		</div>
		<div id="page4" class="stepPage">
		</div>
		<div id="page5" class="stepPage">
		</div>
	</div> -->
	
	<div style="width: 100%; height: 100%;display: none" id="showif"> <!--  class="panel panel-primary bill-unit" -->
		<dg:ajaxgrid id="table0" sqlname="SQL_PF_TRACK_ONE.query" >
			<dg:lefttoolbar>
			</dg:lefttoolbar>
			<dg:condition>
			</dg:condition>
			<dg:ajax-table>
				<table id="btable" data-height='auto' data-page-size='10' data-page-list='[10, 20, 50,100]' data-out-height='none' 
				class="table table-hover text_align_center table-no-bordered table-striped" data-show-columns='false'>
					<thead>
						<tr>
							<th data-formatter="operate_formatter" data-events="operateEvents">操作</th>  
							<th data-field="DETAIL" data-sortable='true'>环节概述</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
	</div>
</div>
</body>

</html>