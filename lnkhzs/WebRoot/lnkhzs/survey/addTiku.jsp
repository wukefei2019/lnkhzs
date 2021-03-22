<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type='text/javascript'>
var volid="${param.id}";
</script>
<meta charset="UTF-8">
<title>调研题库</title>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/survey/addTiku.js"></script>
<script type="text/javascript">

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
		url :  $ctx + "/khzs/vue/surveys/receiveData.action",
		data:{
			cheID : cheID,
		},
		async : false,
		dataType:'jsonp',
		jsonp:'callback',
		success: function(result){		
			window.opener.putlist2(result);
			window.close();
		},
		error:function(){}
	 });
}
</script>
</head>
<body>
	<div class="Ct Dialog">
		<eoms:menuway id="${navid }"></eoms:menuway>
		<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_QYESTION01.query">

			<div class='panel-heading'>
				<h3 class='panel-title'>
					<!-- 表单信息 <a data-toggle="collapse" href="#form"
						style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a> -->
					<a class='btn floatRight10 handOut' onclick="handClick()"
						style='display: inline;'>确定</a>
				</h3>
			</div>

			<dg:lefttoolbar>
				<button
					class="btn-link icon iconfont fontsize14 icon-refresh2 refresh"
					onclick="$.bs.table.refresh('table0')">刷新</button>
					<button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
			</dg:lefttoolbar>
			<dg:condition>
            	<input type="hidden" name="createby" value="${userid}" />
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
			<dg:ajax-table>
				<table id="table1" data-height='auto' data-fixed-columns='false'
					data-fixed-number='3' data-click-to-select="true"
					data-maintain-selected="true" 
					data-on-check="table_check"	data-on-check-all="table_check"
					 data-on-uncheck="table_check"		data-on-uncheck-all="table_check"
					class="table table-hover text_align_center table-no-bordered"
					data-show-columns='false'>
					<thead>
						<tr>
							<th data-checkbox="true"></th>
							<th data-visible="false" data-field="ID">ID</th>
							<!-- <th data-field="CATEGORY">标签，分类</th>
							<th data-field="TYPE">题型（单选，多选，判断，简答）</th>
							<th data-field="QUESTION">题目</th>
							<th data-field="OPTIONS">选项（A，|aaa;B，bbb;C,ccc;，D,ddd;）</th> -->
							<th data-visible="false" data-field="CATEGORY">标签，分类</th>
							<th data-field="QUESTION">题目</th>
							<th data-field="TYPE">题型（单选，多选，判断，简答）</th>
							<!-- <th data-field="QUESTION">题目</th> -->
							<th data-field="OPTIONA">选项(A)</th>
							<th data-field="OPTIONB">选项(B)</th>
							<th data-field="OPTIONC">选项(C)</th>
							<th data-field="OPTIOND">选项(D)</th>
							<th data-field="OPTIONE">选项(E)</th>
							<th data-field="OPTIONF">选项(F)</th>
							<th data-field="OPTIONG">选项(G)</th>
							<th data-field="OPTIONH">选项(H)</th>
						</tr>
					</thead>
				</table>
			</dg:ajax-table>

		</dg:ajaxgrid>
		<iframe name="download" src="" style="display: none"></iframe>
	</div>
</body>
</html>