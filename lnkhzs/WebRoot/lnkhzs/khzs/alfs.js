function fmt_operate(value, row, index) {
	if(row.FLOWSTATUS=='审核员复核'){
		if("金点子"==row.TYPE){
			var html=[];
			html.push("<a title='评价' class='approve btn-link fontsize14' >评价</a>");
			return html.join("");
		}else{
			var html=[];
			html.push("<a title='复核' class='approve btn-link fontsize14' >复核</a>");
			return html.join("");
		}
	}
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		if("金点子"==row.TYPE){
			openwindow($ctx + '/lnkhzs/khzs/approveJdz2.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}else{
			openwindow($ctx + '/lnkhzs/khzs/approveKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}
   }
}

var fn_operate_events = {
    'click a.approve': function (e, value, row, index) {
		if("金点子"==row.TYPE){
			openwindow($ctx + '/lnkhzs/khzs/approveJdz2.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}else{
			openwindow($ctx + '/lnkhzs/khzs/approveKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
		}
    }
};

function exportSelVal() {
	var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr").find("th");
	var titles =[];
	var columns =[];
	$th.each(function(i,t){
		if(i!=0){
			titles[i-1]=$(this).find(".th-inner").text();
			columns[i-1]=$(this).attr("data-field")
		}
		
	});
	
	var rQueryName= $("#table0").attr("sqlname");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	
	exportExcelNew(rQueryName,titles,columns,sheetTitle);

}
