var myRowData="";
function fmt_operate(value, row, index) {
	var html = [];
	html.push("<a title='修改' class='update btn-link fontsize14' >修改</a>");
	return html.join("");
}

var fn_operate_events = {

	'click a.update' : function(e, value, row, index) {

		myRowData=row;

		openwindow($ctx + '/org/person/selectWin.jsp?single=1&callback=setUser');
	},
};


function setUser(all_selection) {
	
	var userName = all_selection[0].FULLNAME;
	var userId = all_selection[0].LOGINNAME;

	var url = encodeURI($ctx + "/quality/tagadmin/updateTagApproval.action?tAGAdmin.changepeoplename="+userName+
			"&tAGAdmin.changepeople="+userId+"&tAGAdmin.id="+myRowData.ID);
	
	$.post(url).done(function(result) {
		if (result == 'success') {
			alert("操作成功");

		} else if (result == 'error') {
			alert("操作失败,不能为空!");
		} else {
			alert("操作失败");
		}
		$.bs.table.refresh('table0');
	}, "json");

}

