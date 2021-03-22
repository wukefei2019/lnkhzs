
function fmt_operate(value, row, index) {
	var html = [];
	html.push("<input data-field='ID' class='Oncheckbox' name='checkid' type='checkbox'>");
	return html.join("");

}


function getCheckValue(checkName) {
	var selvalues = "";
	var m = document.getElementsByName(checkName);
	var len = m.length;
	for (var i = 0; i < len; i++) {
		if (m[i].disabled) {
			continue;
		}
		if (m[i].checked) {
			if (selvalues == "") {
				selvalues = m[i].value;
			} else {
				selvalues += "," + m[i].value;
			}
		}
	}
	document.getElementsByName("var_selectvalues").value = selvalues;
//alert(document.getElementsByName('selectvalues').value);
}

function table_check(row) {
	var all_selection = $("#table1").bootstrapTable("getAllSelections");
	var htmls = [];
	for (var i = 0; i < all_selection.length; i++) {
		var row = all_selection[i];
		var $html = $("<span class='person'><span class='label' title='" + row.FULLNAME + "'>" + row.FULLNAME + "</span><a class='iconfont delete icon-delete2'></a></span>");
		$html.data("entity", row);
		htmls.push($html);
	}
	$(".selectperson").html(htmls)
}

function handClick() {
	var a= $("#table1").bootstrapTable('getSelections');
}


var fn_operate_events = {
	'click a.delete' : function(e, value, row, index) {
		if (confirm("您确认删除吗？")) {
		}
	},
};