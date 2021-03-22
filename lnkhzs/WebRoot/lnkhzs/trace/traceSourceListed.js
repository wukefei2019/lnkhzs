$(document).ready(function(){
//	$('#table1').removeClass("table-striped");
});
fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/trace/traceSourceDList.jsp?id='+row.CODE,'');
   }
}
function fmt_operate(value, row, index) {
	var html=[];
//	html.push("<a title='验收' class='edit btn-link fontsize14' >验收</a>");
	html.push("<a title='导出' class='export btn-link fontsize14' >导出</a>");
//	html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
	return html.join("");
}
var fn_operate_events = {
    'click a.edit': function (e, value, row, index) {
        openwindow($ctx + '/lnkhzs/trace/addCheck.jsp?code=' + row.CODE ,'');
    },
    'click a.export': function (e, value, row, index) {
	    $.bs.grid.tips("正在为您导出数据，请稍后... ...",{level:'info'});
	    downloadExcel('/trace/traceSource/exportExcel.action',{code:row.CODE});
    },
    'click a.delete': function (e, value, row, index) {
    	$.post($ctx + '/trace/traceSource/resetFlow.action',{code:row.CODE}).done(function(result){
    		if (result == 'success') {
    				 alert("操作成功");
    				 $(document).find(".btn-link.icon-refresh2").click();
    		} else {
    			 alert("操作失败");
    		}
    	});
    }
};
