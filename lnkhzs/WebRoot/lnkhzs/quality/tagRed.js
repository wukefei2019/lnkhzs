$(document).ready(function() {
	//$(".btn-link.icon-roundadd").on("click", fn_to_editpage).show();
});

function fn_to_editpage() {
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status=' + $(this).text(), '');
}
function fmt_operate(value, row, index) {
	var html = [];
	if(row.REPORTSTATUS=="待提交"){
		html.push("<a title='修改' class='edit btn-link fontsize14' >修改</a>");
		html.push("<a title='提交' class='sub btn-link fontsize14' >提交</a>");
		html.push("<a title='删除' class='del btn-link fontsize14' >删除</a>");
	}
	
	return html.join("");
}
var fn_operate_events = {
	'click a.edit' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/quality/addTag.jsp?id=' + row.ID, '');
	},
	'click a.del' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
			$.post($ctx + '/quality/tagapproval/deleteTag.action?tAGApproval.id=' + row.ID).done(function(result) {
				if (result == 'success') {
					alert("删除成功");
					$.bs.table.refresh("table0");
				}
			});
		}
	},
	'click a.sub' : function(e, value, row, index) {
		if (confirm("您确认提交吗？")) {
			$.post($ctx + '/quality/tagapproval/submitTag.action?tAGApproval.id=' + row.ID).done(function(result) {
				if (result == 'success') {
					alert("提交成功");
					$.bs.table.refresh("table0");
				}
			});
		}
	}
};

fn_detail = {
		'click a': function (e, value, row, index) {
			openwindow($ctx + '/lnkhzs/quality/tagRedShow.jsp?id=' + row.ID, '');
	   }
	}


//业务内容截取前10字符
function fmt_operate_ywnr(value, row, index) {
	var slnr = row.WORKINFO;
	//var slnr = A13.length>10 ? A13.substring(0, 10) : A13;
	 slnr = slnr.substring(0, 10);
		var html=[];
			html.push(slnr);
		return html.join("");
}
