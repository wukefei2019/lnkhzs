$(document).ready(function(){
	getEndTime();
})
function getTablename() {
	var tablename = "NGCS_WF_CMPLNTS_REQST_DETAIL";
	return tablename;
}

fn_detail = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/quality/reqst/reqstDetail.action?sWfCmplntsReqstDetail.srvReqstId='+row.SRV_REQST_ID,'');
   }
}

function getEndTime(){
	$.post($ctx + "/quality/reqst/getEndTime.action").done(function(result) {
		$("#showEndTime").text(result.codeNm);
		
	});
}


//受理内容截取前10字符
function fmt_operate_slnr(value, row, index) {
	var slnr = row.A13;
	//var slnr = A13.length>10 ? A13.substring(0, 10) : A13;
	 slnr = slnr.substring(0, 10);
		var html=[];
			html.push(slnr);
		return html.join("");
}