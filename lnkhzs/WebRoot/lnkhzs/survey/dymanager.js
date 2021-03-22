function fmt_operate(value, row, index) {
	var html = [];
	
	html.push("<a title='编辑' class='update btn-link fontsize14' >编辑</a>");
	return html.join("");
}

var fn_operate_events = {
	'click a.update' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/editManager.jsp?id=' + row.ID, '');
	},
};