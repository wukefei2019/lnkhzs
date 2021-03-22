
function fmt_operate(value, row, index) {
	if(row.FLOWSTATUS=='员工提交'){
		var html=[];
		html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
		return html.join("");
	}
	if(row.FLOWSTATUS=='员工评价'){
		var html=[];
		html.push("<a title='评价' class='evaluate btn-link fontsize14' >评价</a>");
		return html.join("");
	}
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/khzs/addKhzs.jsp?type=典型投诉案例&id='+row.PID,'');
   }
}

var fn_operate_events = {
    'click a.delete': function (e, value, row, index) {
    	$.post($ctx + '/khzs/flow/deleteKhzsTMain.action?khzsTMain.pid='+row.PID).done(function(result){
    		if (result == 'success') {
    			
    			alert("删除成功")
    			$.bs.table.refresh("table0");
                
    		}
    	});
    },
    'click a.evaluate': function (e, value, row, index) {
    	openwindow($ctx + '/lnkhzs/khzs/addKhzs.jsp?type=典型投诉案例&id='+row.PID,'');
    }
};



