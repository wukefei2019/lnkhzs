function fmt_operate(value, row, index) {
	var html = [];
	html.push("<a title='编辑' class='update btn-link fontsize14' >编辑</a>");
	html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
	return html.join("");
}

function fmt_operate_type(value, row, index) {
	if(value == '矩阵填空'){
		row.OPTIONA = '...';
		row.OPTIONB = '...';
		return "矩阵填空";
	}else{
		return value;
	}
}

fn_evnt_name_look = {
		'click a': function (e, value, row, index) {
			openwindow($ctx + '/lnkhzs/survey/editDytk.jsp?id=' + row.ID+'&msg='+1, '');
	   }
	}


var fn_operate_events = {
	'click a.delete' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
			$.post($ctx + '/khzs/surveys/delDytk.action?khzsQuestion.id=' + row.ID).done(function(result) {
				if (result == 'success') {
					alert("删除成功")
					$.bs.table.refresh("table0");
				}
			});
		}
	},

	'click a.update' : function(e, value, row, index) {
		//openwindow($ctx + '/khzs/surveys/toEditDytk.action?khzsQuestion.id=' + row.ID, '');
		openwindow($ctx + '/lnkhzs/survey/editDytk.jsp?id=' + row.ID, '');
	},
	
};