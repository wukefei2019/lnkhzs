$(document).ready(function(){
	$('#table1').removeClass("table-striped");
});
function table_load_success() {
	var rows = $('#table1').bootstrapTable('getData');
	for(var i=0;i<rows.length;i++){
		if('待验证'==rows[i].COMPLETION_STATUS){
			$('#table1 tbody tr').eq(i).addClass('ts-bg-red');
		}else if('已完成'==rows[i].COMPLETION_STATUS){
			$('#table1 tbody tr').eq(i).addClass('ts-bg-green');
		}else{
			$('#table1 tbody tr').eq(i).addClass('ts-bg-orange');
		}
	}
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/trace/traceSourceFList.jsp?pid='+row.PID,'');
   }
}
function fmt_operate(value, row, index) {
	var html=[];
	if(row.STATUS=="待验证"){
		html.push("<a title='验收' class='edit btn-link fontsize14' >验收</a>");
		html.push("<a title='整改' class='rectification btn-link fontsize14' >整改</a>");
	}
	html.push("<a title='导出' class='export btn-link fontsize14' >导出</a>");
	return html.join("");
}
var fn_operate_events = {
    'click a.edit': function (e, value, row, index) {
        openwindow($ctx +'/trace/traceSource/addCheck.action?pid=' + row.PID ,'');
    },
    'click a.export': function (e, value, row, index) {
	    $.bs.grid.tips("正在为您导出数据，请稍后... ...",{level:'info'});
	    downloadExcel('/trace/traceSource/exportExcel.action',{pid:row.PID});
    },
    'click a.rectification': function (e, value, row, index) {
    	$.post($ctx + '/trace/flow/doAction.action',{data:row.PID,actionStr:'整改'}).done(function(result){
			if (result == 'success') {
				alert("操作成功");
				$(document).find(".btn-link.icon-refresh2").click();
			} else {
				alert(result);
			}
		});
    }
};