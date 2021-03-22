$(document).ready(function(){
	getEndTime();
})

function getTablename() {
	var tablename = "NGCS_WF_CMPLNTS_ARC_DETAIL_NO";
	return tablename;
}

fn_detail = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/quality/noComplaint/arcDetailNo.action?sWfCmplntsArcDetailNo.wrkfmShowSwftno='+row.WRKFM_SHOW_SWFTNO,'');
   }
}

function getEndTime(){
	$.post($ctx + "/quality/noComplaint/getEndTime.action").done(function(result) {
		$("#showEndTime").text(result.acptTime);
		
	});
}


//业务内容截取前10字符
function fmt_operate(value, row, index) {
	var slnr = row.BIZ_CNTT;
	//var slnr = A13.length>10 ? A13.substring(0, 10) : A13;
	 slnr = slnr.substring(0, 10);
		var html=[];
			html.push(slnr);
		return html.join("");
}

//业务内容截取前10字符
function fmt_operate_clyj(value, row, index) {
	var slnr = row.DSPS_OPIN_CNTT;
	//var slnr = A13.length>10 ? A13.substring(0, 10) : A13;
	 slnr = slnr.substring(0, 10);
		var html=[];
			html.push(slnr);
		return html.join("");
}

