
function fmt_operate(value, row, index) {
	var html = [];
	html.push("<a title='"+row.ATTACHHTTPURL+"' class='resend btn-link fontsize14' >"+row.ATTACHHTTPURL+"</a>");
	return html.join("");
}


function table_check(row) {
}




var fn_operate_events = {
	'click a.resend' : function(e, value, row, index) {
		window.location.href=row.ATTACHHTTPURL;
	},
};