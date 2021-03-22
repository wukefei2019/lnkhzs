
function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}
function fmt_operate(value, row, index) {
	var html=[];
	html.push("<a title='修改' class='edit btn-link fontsize14' >修改</a>");
	html.push("<a title='删除' class='del btn-link fontsize14' >删除</a>");
	return html.join("");
}
var fn_operate_events = {
    'click a.edit': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/quality/editSerRequestNode.jsp?pid='+row.PID+'&status='+$(this).text(),'');
    },
    'click a.del': function (e, value, row, index) {
//		openwindow($ctx + '/lnkhzs/quality/editSerRequestNode.jsp?pid='+row.PID+'&status='+$(this).text(),'');
	    $.post($ctx + '/khzs/flow/saveSerReqNodeBak.action?viceRequestNode.pid='+row.PID+'&viceRequestNodeBak.status='+$(this).text()).done(function(result){
			if (result == 'success') {
				/*$.bs.tips("操作成功",{ level:"success"},function(){
					 //刷新父页面
					  $.bs.tips("操作成功",{ level:"success"}); */
					 alert("操作成功");
					 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
					 window.close();
	             //});
			} else {
				 /* $.bs.tips("操作失败",{ level:"danger"}); */ 
				 alert("操作失败");
			}
		});
    }
};