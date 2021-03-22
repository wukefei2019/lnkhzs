$(document).ready(function(){
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
});

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/trace/addManager.jsp','');
}
//<th data-formatter="fmt_operate" data-events="fn_operate_events">操作</th>
var fn_operate_events = {
	'click a.select': function (e, value, row, index) {
	    window.opener.setDutyUser(row);
	    window.close();
	}
};

function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='选择' class='select btn-link fontsize14' >选择</a>");
	return html.join("");
}
	
	
function fn_list_checkbox_fmt(value, row, index) {
    if (row.DATA_STATUS != '1' || row.AUDIT_STATUS == '1')
        return {
            disabled : true,//设置是否可用
            checked : false//设置选中
        };
    return  value;
}
