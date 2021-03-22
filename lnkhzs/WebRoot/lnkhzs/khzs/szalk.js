function fmt_operate(value, row, index) {
	var html=[];
	if(row.EXAMPLE == null || row.EXAMPLE == undefined || row.EXAMPLE == ''){
		html.push("<a title='设置' class='setting btn-link fontsize14' >设置</a>");
	}else{
		html.push("<a title='设置' class='setting btn-link fontsize14' >"+row.EXAMPLE+"</a>");
	}
	
	return html.join("");
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		if("金点子"==row.TYPE){
			openwindow($ctx + '/lnkhzs/khzs/approveJdz.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}else{
			openwindow($ctx + '/lnkhzs/khzs/approveKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}
   }
}

var fn_operate_events = {
    'click a.setting': function (e, value, row, index) {
    	$.bs.modal.open($ctx+"/lnkhzs/khzs/setting.jsp?id="+row.PID+"&status="+row.EXAMPLE,{height:100,width:400});
    }
};


