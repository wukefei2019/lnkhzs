$(document).ready(function(){
//	$(".js-example-basic-hide-search").select2({
//	  minimumResultsForSearch: Infinity
// 	});
	$(".changeTrColor_Table").find("tbody tr:odd").addClass("bgTrColor2");
	$(".tt-content-table").find("tr:even").addClass("tt-special-tr-Color");
	$("input[name='b']").click(function() {
		//判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
		if($(this).is(":checked")) {
			$("input[name='a']").prop("checked", true);
		} else {
			$("input[name='a']").prop("checked", false);
		}
	});
	$(".tt-tab-ul li").click(function() {
		var i = $(".tt-tab-ul li").index($(this));
		$(".tt-tab-ul li").removeClass("tt-selected1");
		$(this).addClass("tt-selected1");
		$(".tt-tab-table").hide();
		$(".tt-tab-table").eq(i).show();
	});
	$(".tt-tiaozhuan-td").click(function() {
		$(".tt-cont").hide();
		$(".tt-cont1").show();
		$(".js-example-basic-hide-search").select2({
			minimumResultsForSearch: -1
		});
	})
	$(".tt-details-btn").click(function() {
		$(".tt-tanchuang").show();
		$(".tt-tanchuang-wrap").show();
	})
	$(".tt-close-icon").click(function() {
		$(".tt-tanchuang").hide();
		$(".tt-tanchuang-wrap").hide();
	})
	/*$(".tt-tanchuang-table1").eq(1).hide();
	$(".tt-tanchuang-table1").eq(2).hide()*/
	/*$(".tt-tanchuang-ul li").click(function() {
		var i = $(".tt-tanchuang-ul li").index($(this));
		$(".tt-tanchuang-ul li").removeClass("tt-selected2");
		$(this).addClass("tt-selected2");
		$(".tt-tanchuang-table1").hide();
		$(".tt-tanchuang-table1").eq(i).show();
	});*/
	$(document).on("click",'.tt-add-icon',function(){
		$('.tt-details-table1').append('<tr><td><select class="js-example-basic-hide-search"><option value="">期末铁塔共享后基准价格1+2+3</option></select></td><td><input type="text" /></td><td><input type="text" /></td><td><input type="text" /></td><td><i class="tt-add-icon"></i><i class="tt-reduce-icon"></i></td></tr>');
		$(".js-example-basic-hide-search").select2({
			minimumResultsForSearch: -1
		});
	})
    $(document).on("click",'.tt-reduce-icon',function(){
		$(this).parent("td").parent("tr").remove();
	})
});
