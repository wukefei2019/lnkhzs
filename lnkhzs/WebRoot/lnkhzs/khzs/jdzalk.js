$(function() {
	$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetRanking1.action").done(function(result) {
		html = [];
		var length = result.length < 5 ? result.length : 5;
		for (var i = 0; i < length; i++) {
			html.push('<tr><td align="center">' + (i + 1) + '</td><td align="center">' + result[i].fullname + '</td><td align="center">' + result[i].cnt + '</td></tr>');
		}
		$('#ranking1').html(html.join(""));
	});
	$.post($ctx + "/khzs/flow/khzsTFlowAction/ajaxGetRanking2.action").done(function(result) {
		html = [];
		var length = result.length < 5 ? result.length : 5;
		for (var i = 0; i < length; i++) {
			html.push('<tr><td align="center">' + (i + 1) + '</td><td align="center">' + result[i].depname + '</td><td align="center">' + result[i].cnt + '</td></tr>');
		}
		$('#ranking2').html(html.join(""));
	});
})

function is_sketch(value, row, index){
	var html=[];
	if(row.IS_SKETCH == '是'){
		html.push('<img src="'+$ctx+'/lnkhzs/icon_sketch.gif" width="12" height="14" ><a href="javascript:void(0)"></a>');
	}else{
		html.push('<a href="javascript:void(0)"></a>');
	}
	return html.join("");
}

fn_evnt_name_look = {
	'click a' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/khzs/jdzalkDetail.jsp?pid=' + row.PID, '');
	}
}

var fn_operate_events = {
	'click a.deal' : function(e, value, row, index) {
		openwindow($ctx + '/lnkhzs/khzs/dealJdz.jsp?type=' + row.TYPE + '&id=' + row.PID, '');
	}
};

function exportSelVal() {
	var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr").find("th");
	var titles = [];
	var columns = [];
	$th.each(function(i, t) {
		titles[i] = $(this).find(".th-inner").text();
		columns[i] = $(this).attr("data-field")
	});
	var rQueryName = $("#table0").attr("sqlname");
	var lujing = $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle = lujings[lujings.length - 1];

	exportExcelNew(rQueryName, titles, columns, sheetTitle);

}