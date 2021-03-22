$(function  ($) {
	$(".fyjh-account_cont table").eq(1).hide();
	$(".fyjh-check_ul li").click(function  () {
		var i = $(".fyjh-check_ul li").index($(this));
		$(".fyjh-check_ul li").removeClass("fyjh-selected1");
		$(this).addClass("fyjh-selected1");
		$(".fyjh-account_cont table").hide();
		$(".fyjh-account_cont table").eq(i).show();
	});
	$(".fyjh-account_select").select2({
		minimumResultsForSearch: -1
	});
});