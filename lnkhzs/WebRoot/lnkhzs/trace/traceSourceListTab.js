$(document).ready(function(){
//	$('#table1').removeClass("table-striped");
});
fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/trace/traceSourceTDetail.jsp?id='+row.CODE,'');
   }
}
