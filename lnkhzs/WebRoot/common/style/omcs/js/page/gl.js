$(function(){
 	$(".changeTrColor_Table").find("tbody tr:odd").addClass("bgTrColor1");
});

window.onload=function(){
	$(".js-gl_dfzt").on("click",function(){
		parent.$(".js-nav_ul li").eq(6).addClass("activeLi").siblings().removeClass("activeLi");
		parent.$("#iframe5").show().siblings().hide();
	});
}
