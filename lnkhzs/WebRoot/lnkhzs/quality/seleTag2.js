$(document).ready(function(){
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
});

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}
function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='选择' class='edit btn-link fontsize14' >选择</a>");
	return html.join("");
}
var fn_operate_events = {
    'click a.edit': function (e, value, row, index) {
    	window.opener.getTags(row.A12);
    	window.opener.getMaindep(row.BUSINESS_ATTRIBUTION);
		window.close();
    },
    'click a.del': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/quality/editSerRequestNode.jsp?pid='+row.PID+'&status='+$(this).text(),'');
    }
};