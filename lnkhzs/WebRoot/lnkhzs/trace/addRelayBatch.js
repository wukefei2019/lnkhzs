$(document).ready(function(){
  /* $("td.dep").on("click",fn_to_dep).show();*/
    $("#showUsers").on("click",fn_to_user).show();
    
    $('.uploadnew').iceuploader({
  		attachid:$('.uploadnew').attr("id"),
  		cachemode:true,
  		editable:true,
  		forcedel:!false,
  /*     		savepath:'eoms_workSheet/WF4_EL_UVS_TSK/2021-01-18' */
  	});
});

/*function fn_to_dep(){
    openwindow($ctx + '/org/dept/selectWin.jsp?callback=setDutyDep&single=1','');
}*/

//var setDutyDep = function(depid,depname){
//    /*$("[name='khzsTDepadmin.depid']").val(depid);*/
//    $("[name='khzsTDepadmin.depname']").val(depname);
//}

function fn_to_user(){
    openwindow($ctx + '/org/person/selectWin2.jsp?callback=setDutyUser&single=1','');
}

var setDutyUser = function(rows,fn){
	
    $("[name='selectUser.fullName']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    
    $("[name='selectUser.loginName']").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));

}

//function relay(){
//	var url = $ctx + '/trace/flow/doAction.action?traceSourceList.pid='+row.PID+'&actionStr=转派'
//    $.post(encodeURI(url)).done(function(result){
//		if (result == 'success') {
//			 $.bs.tips("操作成功",{ level:"success"},function(){
//				 //刷新父页面
//				 $.bs.tips("操作成功",{ level:"success"});
////				 $((opener || parent).document).find(".btn-link.icon-refresh2").click();
//				 $.bs.table.refresh("table0");
//             });
//		}
//	});
//}