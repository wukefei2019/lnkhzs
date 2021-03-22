$(document).ready(function(){
    $("[name='khzsTMain.type']").val($("[name='type']").val());
    $("[name='khzsTMain.source']").val($("[name='source']").val());
	var flowStatus = $("[name='khzsTMain.flowstatus']").val().length==0?0:$("[name='khzsTMain.flowstatus']").val();
	$("[name='khzsTMain.flowstatus']").val(flowStatus);
	is_disabled("show");
    isComplaint();
});

function is_disabled(operation){
    if(operation == "show"){
        //隐藏按钮
        $(".btn.btn-draft,.btn.btn-wf,.btn-link,.btn.floatRight10").hide();
        $("select").attr("disabled","disabled").off();
        $("input,textarea").attr("readonly","readonly").off();
        $("input[type='date']").removeAttr("onclick").removeAttr("onfocus");
        $(".btn-link,.btn").off();
    }
}

function isComplaint(){
    if("典型投诉案例"==$("[name='khzsTMain.type']").val()){
    	$("[name='khzsTMain.source']").parent("div").removeClass("invisible");
    	$("[name='khzsTMain.source']").parent("div").prev("div").find("label").addClass("required");
    	$("[name='khzsTMain.source']").parent("div").prev("div").removeClass("invisible");
    	$("[name='khzsTMain.question']").parent("div").removeClass("hidden");
    	$("[name='khzsTMain.question']").parent("div").prev("div").find("label").addClass("required");
    	$("[name='khzsTMain.question']").parent("div").prev("div").removeClass("hidden");
    }else{
    	$("[name='khzsTMain.source']").val("");
    	$("[name='khzsTMain.source']").parent("div").prev("div").find("label").removeClass("required");
    	$("[name='khzsTMain.question']").val("");
    	$("[name='khzsTMain.question']").parent("div").prev("div").find("label").removeClass("required");
    	$("[name='khzsTMain.source']").parent("div").addClass("invisible");
    	$("[name='khzsTMain.source']").parent("div").prev("div").addClass("invisible");
    	$("[name='khzsTMain.question']").parent("div").addClass("hidden");
    	$("[name='khzsTMain.question']").parent("div").prev("div").addClass("hidden");
    }
}
