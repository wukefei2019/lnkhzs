$(document).ready(function(){
	$('#table1').removeClass("table-striped");
});
function table_load_success() {
	var rows = $('#table1').bootstrapTable('getData');
	var nextloginname = $("#nextloginname").val();
	for(var i=0;i<rows.length;i++){
			if('处理中'==rows[i].STATUS ){
				if(nextloginname==rows[i].LOGINNAME){
					$('#table1 tbody tr').eq(i).addClass('ts-bg-red');
				}else{
					$('#table1 tbody tr').eq(i).addClass('ts-bg-orange');
				}
			}else{
				$('#table1 tbody tr').eq(i).addClass('ts-bg-green');
			}
	}
}
function exportd() {
	var array = $('#table1').bootstrapTable('getAllSelections');
	var nextloginname = $("#nextloginname").val();
	var data=[];
	for(var i=0;i<array.length;i++){
		data.push(array[i].PID);
	}
	downloadExcel('/trace/traceSource/exportExcelDone.action',{pid:data,nextloginname:nextloginname});
}
function fmt_operate(value, row, index) {
	var nextloginname = $("#nextloginname").val();
	if(nextloginname==row.LOGINNAME&&('处理中'==row.STATUS||'整改中'==row.STATUS)){
		var html=[];
		html.push("<a title='转派' class='relay btn-link fontsize14' >转派</a>");
		html.push("<a title='完成' class='finish btn-link fontsize14' >完成</a>");
		return html.join("");
	}
}

var setDutyUser = function(rows,fn){
	
    /*$("[name='khzsTDepadmin.loginname']").val($.map(rows,function(t,i){ return t.LOGINNAME}).join(","));*/
    $("[name='selectUser.fullName']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    
    $("[name='selectUser.loginName']").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));
    
    var loginName = $("[name='selectUser.loginName']").val();
    var fullName = $("[name='selectUser.fullName']").val();
    
    var array = $('#table1').bootstrapTable('getAllSelections');
	var data=[];
	for(var i=0;i<array.length;i++){
		data.push(array[i].PID);
	}
    
	$.post($ctx + '/trace/flow/batchRelay.action', {loginName:loginName,fullName:fullName,data:JSON.stringify( array ),actionStr:'转派'}).done(function(result) {
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

var fn_operate_events = {
    'click a.relay': function (e, value, row, index) {
    	openwindow($ctx + '/trace/flow/addRelay.jsp?data='+row.PID,'');
    },
    'click a.finish': function (e, value, row, index) {
    	openwindow($ctx + '/trace/flow/addDone.jsp?data='+row.PID,'');
    }
};
fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/trace/traceSourceFList.jsp?pid='+row.PID,'');
   }
}