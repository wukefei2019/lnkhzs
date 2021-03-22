function fmt_operate(value, row, index) {
	var html = [];
	if(row.APPROVALSTATUS=="未审批"){
		html.push("<a title='审批' class='update btn-link fontsize14' >审批</a>");
	}
	return html.join("");
}

var fn_operate_events = {

	'click a.update' : function(e, value, row, index) {
		//openwindow($ctx + '/khzs/surveys/toEditDytk.action?khzsQuestion.id=' + row.ID, '');
		openwindow($ctx + '/lnkhzs/quality/tagMainEdit.jsp?id=' + row.ID, '');
	},
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