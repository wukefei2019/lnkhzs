$(document).ready(function(){
	$('#table0').on('click-row.bs.table', function (e,row,$element,field){
		var areaName = field=="TOTAL"?"":field;
		if($element.context.children.length>0){
			openwindow($ctx + '/lnkhzs/quality/assistList.jsp?b02='+row.B02+'&b03='+row.B03
					+'&b04='+row.B04+'&b05='+row.B05+'&b06='+row.B06
					+'&b07='+row.B07+'&b08='+row.B08+'&areaName='+areaName,'');
		}
	}); 
});

fn_detail = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/quality/assist/arcXcDetail.action?sWfCmplntsArcDetailXc.wrkfmShowSwftno='+row.WRKFM_SHOW_SWFTNO,'');
   }
}

function getTablename() {
	//var tablename = "NGCS_WF_CMPLNTS_ARC_DETAIL_XC";
	var tablename = "";
	return tablename;
}
