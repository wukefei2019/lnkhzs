
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>

function handClick() {
	var cheID="";
	var checktion= $("#table1").bootstrapTable('getSelections');
	 if(checktion.length==0){
		 alert("请选择数据");
		 return false;
	 }else{
		 for (var i = 0; i < checktion.length; i++) {
			 var row = checktion[i];
			 cheID+=row.ID+",,";
		 }
	 }
	 $.ajax({
		url :  $ctx + "/khzs/vue/surveys/receiveDataWJ.action",
		data:{
			cheID : cheID,
		},
		async : false,
		dataType:'jsonp',
		jsonp:'callback',
		success: function(result){	
			window.opener.putlist2WJ(result);
			window.close();
		},
		error:function(){}
	 });
}
	
</script>
<html>
<head>
<meta charset="UTF-8">
	<title>问卷列表</title>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_DYWJ02.query">
			<div class='panel-heading'>
				<h3 class='panel-title'>
					<a class='btn floatRight10 handOut' onclick="handClick()"
						style='display: inline;'>确定</a>
				</h3>
			</div>
			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
			</dg:lefttoolbar>
			<dg:condition>
				<input type="hidden" name="createby" value="${userid}" />
				<!-- <input type="hidden" name="TYPE" value="调研问卷" /> -->
				<div class="btn_box overflow">
					<div
						class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
					<div
						class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
				</div>
			</dg:condition>
			<dg:ajax-table>
				<table id="table1" data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-click-to-select="true"
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<th data-checkbox="true"></th>
							<th data-visible="false" data-field="ID">ID</th>
							<th data-field="NAME">问卷名</th>
							<th data-field="CREATTIME">问卷时间</th>
							<th data-field="ISPUBLIC">所属状态</th>
							<th data-field="CREATEBYNAME">所属人</th>
							<th data-field="STATUS">状态</th> </tr>
					</thead>
				</table>
			</dg:ajax-table>
		</dg:ajaxgrid>
		<iframe name="download" src="" style="display:none"></iframe>
	</div>
</body>
</html>