
$(document).ready(function() {
	getMaxTime();
}); 


function getTablename() {
	var tablename = "";
 	return tablename;
}

function deleteList(){
	var rows = $("#table1").bootstrapTable('getSelections');
	if (rows.length== 0) {
        alert("请先选择要删除的记录!");
        return;
    }
	
    var ids = '';
    for (var i = 0; i < rows.length; i++) {
        ids += rows[i]['ID'] + ",";
    }
    
    if (confirm("您确认删除吗？")) {
		$.post($ctx + '/satisfation/zqkh/deleteList.action?ids=' + ids).done(function(result) {
			if (result == 'success') {
				alert("删除成功")
				$.bs.table.refresh("table0");
			}
		});
	}
}

//获取当前数据最大月份并刷新列表
function getMaxTime(){
	$.post($ctx + '/satisfation/zqkh/getMaxTime.action').done(function(result) {
		if(result !=''){
			$('input[name="time"]').val(result);
			$.bs.table.refresh("table0");
		}
	});
}

