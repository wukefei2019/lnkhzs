var setting = {
	view : {
		showIcon : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	check : {
		enable : true,
		chkboxType : {
			"Y" : "ps",
			"N" : "ps"
		}
	},
	callback : {
		onCheck : onCheck,
		onClick : onClick
	}
};

var ifload = 0;

$(document).ready(function() {

	if(deptId==""){
		deptId="0";
	}
	$(".btn.search").on("click", function(event) {
		searchFullName();
	});

	$("#table1").on('load-success.bs.table', function(data) {
		if(selectPersonIds.length>0){
			if (ifload == 0) {
				$("input[name='loginname']").val(selectPersonIds);
				$.bs.table.reload('table0');
			}if (ifload == 1) {
				$('#table1').bootstrapTable('checkAll');
				$("input[name='loginname']").val("");
			}
		}else if(ifload == 0){
			searchFullName();
		}
		
		ifload++;
	});
	//绑定删除搜索框文字事件
	$(".select-win-remove").on("click", function(event) {
		removeSearch();
	});

	addCheckBox();
	
	$.post($ctx + "/deptree/ajaxGetDeptTree.action", {
		// "bean.name":"中国移动通信集团辽宁有限公司"
		"bean.pId" : deptId,
		"bean.parent" : true
	}).done(function(entities) {

		$.fn.zTree.init($("#treeDemo"), setting, entities);
		$.bs.table.init('table0');
	});

	$(".selectperson").delegate(".delete", "click", remove_selected_person);
	

});


function onCheck(e, treeId, node) {
	var zTree = $.fn.zTree.getZTreeObj(treeId), checkNodes = zTree.getCheckedNodes(true);
	var depid = [];
	for ( var i in checkNodes) {
		depid.push(checkNodes[i].id);
	}

	$("[name='depid']").val(depid.join(","));
	$.bs.table.reload("table0");
}
0
function onClick(e, treeId, node) {
	var $A = $(e.target);
	if (e.target.tagName != "A") {
		$A = $A.parents("A");
	}
	$A.prev(".button.chk").click()
}
function table_check(row) {
	var all_selection = $("#table1").bootstrapTable("getAllSelections");
	var htmls = [];
	for (var i = 0; i < all_selection.length; i++) {
		var row = all_selection[i];
		var $html = $("<span class='person'><span class='label' title='" + row.FULLNAME + "'>" + row.FULLNAME + "</span><a class='iconfont delete icon-delete2'></a></span>");
		$html.data("entity", row);
		htmls.push($html);
	}
	$(".selectperson").html(htmls)
}

function remove_selected_person(event) {
	var $person = $(event.target).parents(".person");
	var all_selection = $("#table1").bootstrapTable().data("bootstrap.table").options.all_data;
	var stateField = $("#table1").bootstrapTable().data("bootstrap.table").header.stateField;
	var entity = $person.data("entity");
	var all_selection = $.map(all_selection, function(t, i) {
		if (t.PID == entity.PID) {
			t[stateField] = false;
		}
		return t;
	});
	$("#table1").bootstrapTable().data("bootstrap.table").options.all_data = all_selection;
	$.bs.table.reload('table0');
	$person.remove();
}

//换成动态加载选框
function addCheckBox() {
	var tdHtml = "";
	//判断是否是单选，根据参数不同，动态渲染
	if (single == "1") {
		tdHtml = "<th data-field='state' data-radio='true'></th>";
	} else {
		tdHtml = "<th data-field='state' data-checkbox='true'></th>";
	}
	$('#table1 tr').each(function() {
		$(this).children().eq(0).before(tdHtml);
	});
}

//根据名字全局搜索
function searchFullName() {
/*	var fullName = $('#searchFullName').val();
	$("input[name='fullname']").val(fullName);
	$("input[name='loginname']").val(fullName);
	$.bs.table.reload('table0');
	*/
	var fullName = $('#searchFullName').val();
	$.post($ctx + "/deptree/ajaxGetDeptPerson.action", {
		"fullName" : fullName
	}).done(function(result) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		if(deptId2!=""){
			var node = zTree.getNodeByParam("id", deptId2, null);
			zTree.checkNode(node,true,true);
			zTree.expandNode(node);
		}
		//勾选并且展开全局搜索后的部门
		for (var i = 0; i < result.length; i++) {
			var node = zTree.getNodeByParam("id", result[i], null);
			zTree.checkNode(node, true, true);
		}
		//将勾选的全部内容重置会depid
		checkNodes = zTree.getCheckedNodes(true);
		var depid = [];
		for ( var i in checkNodes) {
			depid.push(checkNodes[i].id);
		}
		$("[name='depid']").val(depid.join(","));

		//重新加载右侧列表
		$("input[name='fullname']").val(fullName);
		/*$("input[name='loginname']").val(fullName);*/
		$.bs.table.reload('table0');
	});
}
//删除搜索框
function removeSearch() {
	$("#searchFullName").val("");
}
