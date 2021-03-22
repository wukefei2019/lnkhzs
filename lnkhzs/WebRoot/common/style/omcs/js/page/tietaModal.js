$(function(){
	/**下拉选择***/
	$(".tt_select").select2({
	  minimumResultsForSearch: Infinity
 	});
 	$(".tt-tanchuang-table1").eq(1).hide();
	$(".tt-tanchuang-table1").eq(2).hide();
});

window.onload=function(){
	$(".tt-tanchuang-ul li").click(function() {
		var i = $(".tt-tanchuang-ul li").index($(this));
		$(".tt-tanchuang-ul li").removeClass("tt-selected2");
		$(this).addClass("tt-selected2");
		$(".tt-tanchuang-table1").hide();
		$(".tt-tanchuang-table1").eq(i).show();
	});
}
