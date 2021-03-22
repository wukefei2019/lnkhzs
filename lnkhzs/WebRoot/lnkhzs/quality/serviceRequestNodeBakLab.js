$(document).ready(function(){
//    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
});

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}
function submitSerReqNode() {
	var array = $('#table1').bootstrapTable('getSelections');
	var data=[];
	for(var i=0;i<array.length;i++){
		data.push(array[i].ID);
	}
	$.post($ctx + '/quality/tagMaintain/submitSerReqNode.action',{data:data.join(",")}).done(function(result){
		if (result[0].result == 'success') {
			/*$.bs.tips("操作成功",{ level:"success"},function(){
				 //刷新父页面
				  $.bs.tips("操作成功",{ level:"success"}); */
				 alert(result[0].msg);
				 $(document).find(".btn-link.icon-refresh2").click();
             //});
		} else {
			 /* $.bs.tips("操作失败",{ level:"danger"}); */ 
			 alert("操作失败");
		}
	});
}

