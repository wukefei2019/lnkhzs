<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
<head>
<meta charset="UTF-8"></meta>
<title>弹出组织机构树，选择人员</title>
<link rel="stylesheet"
	href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
<link rel="stylesheet"
	href="${ctx }/common/plugin/ztree/css/omcsStyle/ztree.custom.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/org/person/selectWin.js"></script>

<script>

var single = "${param.single}";
var selectPersonIds = "${param.selectPersonIds}";
var selectPersonNames = "${param.selectPersonNames}";

var deptId = "${param.deptId}";
var deptId2 = "${sessionScope.deptlevel2id}";
var paramMap="${param.paramMap}";
function ok(row, $element){
    //var all_selection = $("#table1").bootstrapTable("getAllSelections");
    var all_selection=all_selection_my;
    window.opener.<%= request.getParameter("callback")%>(all_selection,paramMap);
    window.close();
}
</script>
</head>
<body>
	<div class="Ct Dialog">
		<div class="content_wrap tree">
			<div class="zTreeDemoBackground left">
				<div class="input-group  form-page select-win-search">
					<input type='search' id='searchFullName' class='form-control'
						style='width: 98%' placeholder='请输入全名或用户名搜索'
						data-pure-clear-button="true" /> <i
						class="select-win-remove icon iconfont"></i> <span
						class="input-group-btn"><button
							class="btn search btn-default" type="button" style="color: #333;">搜</button></span>

				</div>
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right">
				<div class="panel panel-primary bill-unit">
					<div class="panel-heading">
						<h4 class="panel-title">
							<span>人员列表</span>
							<button class="btn floatRight10 marginTop6" onclick="ok()">确定</button>
						</h4>
					</div>
					<div class="bill-unit-data">
						<dg:ajaxgrid id="table0" sqlname="SQL_IA_SM_DEPUSERLIST.QUERY"
							conditionIsOpen="true" autoLoad="false">
							<dg:condition>
								<input type="hidden" name="fullname" value="" />
								<input type="hidden" name="loginname" value="" />
							</dg:condition>
							<dg:ajax-table>
								<table id="table1" data-height='auto' data-page-size='10'
									data-page-list='[10, 20, 50,100]'
									data-click-to-select="true" data-maintain-selected="true"
									data-on-check="table_check" data-on-check-all="table_check"
									data-on-uncheck="table_check" data-on-uncheck-all="table_check"
									class="table table-hover text_align_center table-no-bordered"
									data-show-columns='false'>
									<thead>
										<tr>
											<th data-field="LOGINNAME">用户名</th>
											<th data-field="FULLNAME">全名</th>
											<th data-field="STATUS" data-dictype='status'>状态</th>
											<th data-field="DEPNAME" visible="false">部门</th>
										</tr>
									</thead>
								</table>
							</dg:ajax-table>
						</dg:ajaxgrid>
					</div>
				</div>
				<div class="selectperson"></div>
			</div>
		</div>
	</div>
</body>
</html>
