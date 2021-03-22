
function Radio_check(event,RadioBoxName){
	var ev = event || window.event;
    var target = ev.target || ev.srcElement;
	if($(target).prop("checked")){
		$(target).attr("checked","checked");			
		var chk =window.document.getElementsByName(RadioBoxName);
		for (var i = 0; i < chk.length; i++) {
	  		$(chk[i].parentElement).removeClass("on_check");
	  		$(chk[i])[0].removeAttribute("checked");
	 		}			
		$(target).parent().addClass("on_check");
	}
	var spTr=$(target).parents("table").next("table").find("tr.js-jsgs");
	if(!$(target).hasClass("spBj")){
		spTr.hide();
	}else{
		spTr.show();
	}
}



function Radio_check1(event,RadioBoxName){
	var ev = event || window.event;
    var target = ev.target || ev.srcElement;
	if($(target).prop("checked")){
		$(target).attr("checked","checked");			
		var chk =window.document.getElementsByName(RadioBoxName);
		for (var i = 0; i < chk.length; i++) {
	  		$(chk[i].parentElement).removeClass("on_check");
	  		$(chk[i])[0].removeAttribute("checked");
	 		}			
		$(target).parent().addClass("on_check");
	}
}