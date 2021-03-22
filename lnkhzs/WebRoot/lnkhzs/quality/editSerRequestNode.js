$(document).ready(function(){
    $(".viceRequestNode").change(ajaxGetSerReqNode);
    var pid = $('[name="viceRequestNode.pid"]').val();
    if(pid.length!=0){
    	$.post($ctx + "/quality/tagMaintain/ajaxGetViceRequestNode.action?viceRequestNode.pid="+pid).done(function(result) {
    		for(var key in result){
				$('[name="viceRequestNode.'+key+'"]').val(result[key]);
			}	
    	});
    }
});
function ajaxGetSerReqNode(){
	var level = $(this).attr("id").substr(0,5)+(parseInt(($(this).attr("id").substr(5,6)),0)+1);
	$("#"+level).children().remove();
	$("#"+level).parent().nextAll().find("select").children().remove();
	var a12 ="";
	for(var i=1;i<=$(".viceRequestNode").length;i++){
		if(!!$("#name0"+i).val()){
			a12+=$("#name0"+i).val()+'→';
		}
	}
//	var code = a12.substr(0,1);
	a12=a12.substr(0,a12.length-1);
	var html="<option></option>";
	$.postSync($ctx + '/quality/tagMaintain/ajaxGetSerReqNode.action',{a12:a12,level:level}).done(function(result){
    	for(var l=0;l<result.length;l++){
    		html+="<option value="+result[l].text+">"+result[l].text+"</option>";
    	}
    	$("#"+result[0].level).append(html);
    })
}

function saveAdd() {
    if(!validateForm($("form"))  ){
    	var msg=checkNull();
        $.bs.tips(msg,{level:"danger",$target:$("form"),auto_close:false});
        return;
    }
   /*  $.bs.tips("正在保存,请稍后 ... ...",{type:"info",$target:$("form"),auto_close:true}); */
	var a12 ="";
	for(var i=1;i<=$(".viceRequestNode").length;i++){
		if(!!$("#name0"+i).val()){
			a12+=$("#name0"+i).val()+'→';
		}
	}
	$('[name="viceRequestNode.a12"]').val(a12);
    $.post($ctx + '/quality/tagMaintain/saveSerReqNodeBak.action',$("form").serializeArray()).done(function(result){
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