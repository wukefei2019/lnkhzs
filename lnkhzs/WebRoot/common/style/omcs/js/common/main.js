function reszieframe(iframeId){
	var iframe = $("#"+iframeId);
	var winheight = $(window).height();
	if(winheight<=600){
		winheight = 600;
	}
	//alert(winheight)
	iframe.css("height",(winheight-130)+'px');
}