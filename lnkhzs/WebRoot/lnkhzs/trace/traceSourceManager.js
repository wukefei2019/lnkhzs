$(document).ready(function(){
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
});

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/trace/addManager.jsp','');
}
//<th data-formatter="fmt_operate" data-events="fn_operate_events">操作</th>
var fn_operate_events = {
	'click a.delete': function (e, value, row, index) {
	    $.post($ctx + '/trace/traceManager/deleteManager.action?traceManager.pid='+row.PID).done(function(result){
    		if (result == 'success') {
    			 $.bs.tips("操作成功",{ level:"success"},function(){
    				 //刷新父页面
    				 $.bs.tips("操作成功",{ level:"success"});
//    				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
    				 $.bs.table.refresh("table0");
                 });
    		}
    	});
	}
};

function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
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
