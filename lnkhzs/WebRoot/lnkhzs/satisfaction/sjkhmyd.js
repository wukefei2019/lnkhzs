
$(document).ready(function() {
	$("select[name='time']").get(0).selectedIndex=1;
	$.bs.table.refresh('table0');
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
		$.post($ctx + '/satisfation/sjkhmyd/deleteList.action?ids=' + ids).done(function(result) {
			if (result == 'success') {
				alert("删除成功")
				$.bs.table.refresh("table0");
			}
		});
	}
}

