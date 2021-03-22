
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/popStyle.css" rel="stylesheet" type="text/css" />
<script type='text/javascript'>
	

</script>
<html style="height: 100%;">
<head>
	<meta charset="UTF-8">
	<title>投诉详单</title> 
</head>
<body class="bodyArea">
	<div class="titleArea">
		<div>
			<span class="titleSpan"> 投诉分析展示 </span>
			<span class="titleUsername"> 用户名 </span>
		</div>
	</div>
	<div class="bodyDiv"> 
		<div class="tableDiv">
			<div class="underTitDiv" style="">
				<span class="underSpan"> 投诉详单 </span>
				<!-- <div class="downloadClass" onclick="">
				</div>
				<div class="selectClass" onclick="">
				</div>
				<input id='selectTable' type="text" class="inputTable"></input>
				<label class="checkBoxClass">
					<input type="checkbox" name="voice" id="voice" class="boxClass" value="0"/>&nbsp;&nbsp;&nbsp;&nbsp;有录音投诉
				</label> -->
			</div>
			<dg:ajaxgrid id="table0" sqlname="${param.mynum}"><!-- SQL_KHZS.query -->
				<dg:condition>
              		<input type="hidden" name="name" value="${param.name}"  id="name"/>
              		<input type="hidden" name="nname" value="${param.nname}"  id="nname"/>
					<%-- <input type="hidden" name="a12" value="${param.name}" id="a12"/> --%>
					<input type="hidden" name="area_name" value="${param.area_name}" id="area_name"/>
					<input type="hidden" name="acpt_time" value="${param.year}" id="acpt_time"/>
					<input type="hidden" name="acpt_time1" value="${param.acpt_time1}" id="acpt_time1"/>
            	</dg:condition>
				<dg:ajax-table>
					<!--  data-show-search="true" -->
					<table data-height='auto' data-fixed-columns='false' 
						data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
						<thead>
							<tr>
								<!-- <th data-field="IDX" data-formatter="$.bs.table.fmt.link"
									data-events="fn_evnt_name_look">序号</th> -->
								 <th data-field="GDLS">工单流水号</th>
								<th data-field="AREA_NAME">客户归属</th>
								<th data-field="NAME">投诉节点类型</th>
								<th data-field="SOURCE">投诉内容</th>
								<th data-field="RESP_DEPT">责任归属部门</th>
								<th data-field="QUESTION">首问部门</th>
								<th data-field="FLOWSTATUS">最后处理部门</th>
								<th data-field="FULLNAME">解决措施</th>
								<th data-field="FLOWSTATUS">投诉处理时长</th>
								<th data-field="FULLNAME">录音内容</th>
							</tr>
						</thead>
					</table>
				</dg:ajax-table>
			</dg:ajaxgrid>
			<iframe name="download" src="" style="display: none"></iframe>
		</div>
	</div>
</body>

</html>