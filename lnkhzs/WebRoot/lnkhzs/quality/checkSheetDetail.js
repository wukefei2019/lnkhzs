var i=0;
function fmt_operate1(value, row, index) {
	var html = [];
	if(row.INROWNUM!=row.CHKROWNUM){
		html.push("<span style='background-color: lightcoral'>"+value+"</span>");
	}else{
		html.push(value);
	}
	return html.join("");
}

function fmt_operate2(value, row, index) {
	var html = [];
	if(row.INROWNUM!=row.CHKROWNUM){
		html.push("<span style='background-color: lightcoral'>"+value+"</span>");
	}else{
		html.push(value);
	}
	return html.join("");
}
