function fmt_operate(value, row, index) {
	var html = [];
	if(row.STATUS=="已保存"){
		html.push("<a title='编辑' class='update btn-link fontsize14' >编辑</a>");
		html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
	}else if(row.STATUS=="已发起"){
		html.push("<a title='查看附件' class='fjlocallist btn-link fontsize14' >查看附件</a>");
	}else if(row.STATUS=="已完成"){
		html.push("<a title='查看附件' class='fjlist btn-link fontsize14' >查看附件</a>");
		if(row.PID==null||row.PID==""){
			html.push("<a title='发起' class='resend btn-link fontsize14' >发起</a>");
		}
	}
	return html.join("");
}


var fn_operate_events = {
	'click a.delete' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
			$.post($ctx + '/tsgd/swork/delProcess.action?sWork.id=' + row.ID).done(function(result) {
				if (result == 'success') {
					alert("删除成功")
					$.bs.table.refresh("table0");
				}
			});
		}
	},

	'click a.update' : function(e, value, row, index) {
		//openwindow($ctx + '/khzs/surveys/toEditDytk.action?khzsQuestion.id=' + row.ID, '');
		openwindow($ctx + '/lnkhzs/oversee/editSwork.jsp?id=' + row.ID+'&fileid='+row.APPENDIX_PID, '');
	},
	
	'click a.resend' : function(e , value , row , index){
		openwindow($ctx + '/lnkhzs/oversee/editResendSwork.jsp?id=' + row.ID, '');
	},
	
	'click a.fjlist' : function(e , value , row , index){
		openwindow($ctx + '/lnkhzs/oversee/fjlist.jsp?id=' + row.BACKGUID, '');
	},
	
	'click a.fjlocallist' : function(e , value , row , index){
		openwindow($ctx + '/lnkhzs/oversee/fjlocallist.jsp?fileid=' + row.APPENDIX_PID, '');
	},
	
};