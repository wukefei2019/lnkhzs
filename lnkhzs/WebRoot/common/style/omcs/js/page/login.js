$(document).ready(function(){
	$("#tab_ps").click(function(){
	    $(".2d").css('display','block'); 
		$(".ps").css('display','none'); 
	  });
	$("#tab_2d").click(function(){
	    $(".ps").css('display','block'); 
		$(".2d").css('display','none'); 
	 });
	$("#login").click(function(){
	    $(".error").css('display','block'); 
	  });
	$(".close").click(function(){
	    $(".error").css('display','none'); 
	  });
});

window.onload=function(){
	$("#login").on("click",function(){
		var userName=$("#userName").val(),
			pwd=$("#pwd").val();
		if(userName=="Demo" && pwd==""){
			window.location.href="index1.html";
			pwd=$("#pwd").val("");
		}else{
			alert("用户名或者密码错误！");
			$("#userName").val("");
			pwd=$("#pwd").val("");
		}
		
	});
}
