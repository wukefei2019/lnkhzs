$(document).ready(function(){
  /* $("td.dep").on("click",fn_to_dep).show();*/
    $("#showUsers").on("click",fn_to_user).show();
    
    
});

/*function fn_to_dep(){
    openwindow($ctx + '/org/dept/selectWin.jsp?callback=setDutyDep&single=1','');
}*/

var setDutyDep = function(depid,depname){
    /*$("[name='khzsTDepadmin.depid']").val(depid);*/
    $("[name='khzsTDepadmin.depname']").val(depname);
}

function fn_to_user(){
    openwindow($ctx + '/org/person/selectWin2.jsp?callback=setDutyUser&single=1','');
}

var setDutyUser = function(rows,fn){
	
    /*$("[name='khzsTDepadmin.loginname']").val($.map(rows,function(t,i){ return t.LOGINNAME}).join(","));*/
    $("[name='traceManager.fullname']").val($.map(rows,function(t,i){return t.FULLNAME}).join(","));
    
    $("[name='traceManager.depname']").val($.map(rows,function(t,i){return t.DEPNAME}).join(","));
    
    $("[name='traceManager.loginname']").val($.map(rows,function(t,i){return t.LOGINNAME}).join(","));
    
  
    

}
