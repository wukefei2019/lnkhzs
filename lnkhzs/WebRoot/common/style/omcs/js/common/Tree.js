$(document).ready(function() {

	$('.inactive').click(function(){
		if($(this).siblings('ul').css('display')=='none'){
			$(this).siblings('ul').slideDown(100);
			$(this).removeClass('inactive').addClass('inactives');
			$('.active').removeClass('active');
			$(this).parent('li').addClass('active');
			$(this).siblings('ul').addClass('bg_f');
		}else{
			$(this).siblings('ul').slideUp(100);
			$(this).removeClass('inactives').addClass('inactive');
		}
	})
	
	$('.js_active_last').click(function(){
		$('.active').removeClass('active')
		$(this).parent('li').addClass('active')
	});
	
	
});