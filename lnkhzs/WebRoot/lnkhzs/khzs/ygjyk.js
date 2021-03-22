


fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/khzs/ygjykDetail.jsp?pid='+row.PID,'');
   }
}

var fn_operate_events = {
    'click a.deal': function (e, value, row, index) {
    	openwindow($ctx + '/lnkhzs/khzs/dealKhzs.jsp?type='+row.TYPE+'&id='+row.PID,'');
    }
};


function exportSelVal() {
	var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr").find("th");
	var titles =[];
	var columns =[];
	$th.each(function(i,t){
		
			titles[i]=$(this).find(".th-inner").text();
			columns[i]=$(this).attr("data-field")
		
		
	});
	
	var rQueryName= $("#table0").attr("sqlname");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	
	exportExcelNew(rQueryName,titles,columns,sheetTitle);

}
