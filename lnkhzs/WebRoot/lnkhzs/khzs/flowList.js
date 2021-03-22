$(document).ready(function(){
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
});

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/khzs/addKhzs.jsp?model.status=0','');
}

function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='修改' class='edit btn-link fontsize14' >修改</a>");
	html.push("&nbsp;&nbsp;");
	html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
	return html.join("");
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/khzs/flow/khzsDetail.action?khzsTMain.pid='+row.PID,'');
   }
}

/*function fn_load_success(data){
    for (var i = 0; i < data.rows.length; i++) {
        _OC.GRID_TABLE["table0"].bootstrapTable('expandRow', i);
    }
//    $(".fixed-table-header").remove();
}*/

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}

var fn_operate_events = {
    'click a.edit': function (e, value, row, index) {
        openwindow($ctx + '/khzs/flow/editKhzsTMain.action?khzsTMain.pid=' + row.PID ,'');
    },
    'click a.delete': function (e, value, row, index) {
    	$.post($ctx + '/khzs/flow/deleteKhzsTMain.action?khzsTMain.pid='+row.PID).done(function(result){
    		if (result == 'success') {
    			 $.bs.tips("操作成功",{ level:"success"},function(){
    				 //刷新父页面
    				 $.bs.tips("操作成功",{ level:"success"});
    				 $.bs.table.refresh("table0");
                 });
    		}
    	});
    }
};


