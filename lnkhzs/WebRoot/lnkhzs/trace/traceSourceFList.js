$(document).ready(function(){
});
function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}
function submit() {
	if(confirm("确认是否提交？")){
		var array = $('#table1').bootstrapTable('getSelections');
		var data=[];
		for(var i=0;i<array.length;i++){
			data.push(array[i].PID);
		}
		$.post($ctx + '/trace/flow/doAction.action',{data:JSON.stringify( array ),actionStr:'提交'}).done(function(result){
			if (result == 'success') {
				alert("操作成功");
				$(document).find(".btn-link.icon-refresh2").click();
			} else {
				alert(result);
			}
		});
	}
}
function remove() {
	var array = $('#table1').bootstrapTable('getSelections');
	var data=[];
	for(var i=0;i<array.length;i++){
		data.push(array[i].PID);
	}
	$.post($ctx + '/trace/traceSource/remove.action',{data:JSON.stringify( array )}).done(function(result){
		if (result == 'success') {
				 alert("操作成功");
				 $(document).find(".btn-link.icon-refresh2").click();
		} else {
			 alert("操作失败");
		}
	});
}
