function showInfo(event,id,className1,className2){
			var classname1=(className1?className1:"special_color");
			var classname2=(className2?className2:"special_color3");
			if(event.target){
				$(event.target).addClass(classname1);
				$(event.target).siblings().removeClass(classname1);
				$(event.target).siblings().addClass(classname2);
				if(id && id !=""){
					$("#"+id).css("display","");
					$("#"+id).siblings().css("display","none");	
				}
			}else if(event.srcElement){
					$(event.srcElement).addClass(classname1);
					$(event.srcElement).siblings().removeClass(classname1);
					$(event.srcElement).siblings().addClass(classname2);
					if(id && id!=""){
						$("#"+id).css("display","");
						$("#"+id).siblings().css("display","none");
					}
			}
}


function showInfo1(event,className1,id1){
    var delay = 10;
	var ev = event || window.event;
	$(".xiala_menu-backdrop").show();
    var target = ev.target || ev.srcElement;
	if(target){
		$(target).addClass(className1);
		$(target).siblings().removeClass(className1);
//		$(target).siblings("a").removeClass(className1);
		$("#"+id1).show();
		$("#"+id1).siblings().hide();
	}
}

function showInfo2(event,className1,id1){
	var ev = event || window.event;
    var target = ev.target || ev.srcElement;
	if(target){
		$(target).addClass(className1);
		$(target).parent().siblings().find("a").removeClass(className1);
		$("#"+id1).show();
		$("#"+id1).siblings().hide();
	}
}

$(document).ready(function(){
//    var h = function(){$(".xiala_ul").hide()};
//    var t;
//	$(".xiala_ul").mouseover(function(){
//	    clearTimeout(t);
//		$(this).show();
//	});
//	$(".xiala_ul").mouseout(function(){
//		t = setTimeout(h,1500);
//	});
//	$(".xiala_ul a").click(function(){
//		$(".xiala_ul").slipdown();
//	});
});
