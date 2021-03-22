function fmt_operate(value, row, index) {
	var html = [];
	
	html.push("<a title='编辑' class='update btn-link fontsize14' >编辑</a>");
	html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
	return html.join("");
}

var fn_operate_events = {
	'click a.update' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/oversee/editDbManager.jsp?id=' + row.ID, '');
	},
	'click a.delete' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
			$.post($ctx + '/tsgd/resendmanager/deleteManager.action?dbmanager.id=' + row.ID ).done(function(result) {
				if (result == 'success') {
					alert("删除成功")
					$.bs.table.refresh("table0");
				}
			});
		}
		;
	},
};