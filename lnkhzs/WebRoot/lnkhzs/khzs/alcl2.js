

function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='处理' class='deal btn-link fontsize14' >处理</a>");
	return html.join("");
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		if("金点子"==row.TYPE){
			openwindow($ctx + '/lnkhzs/khzs/dealJdz.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}else{
			openwindow($ctx + '/lnkhzs/khzs/dealKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}
   }
}

var fn_operate_events = {
    'click a.deal': function (e, value, row, index) {
		if("金点子"==row.TYPE){
			openwindow($ctx + '/lnkhzs/khzs/dealJdz.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}else{
			openwindow($ctx + '/lnkhzs/khzs/dealKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}
    }
};



