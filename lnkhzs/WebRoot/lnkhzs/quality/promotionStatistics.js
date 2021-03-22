$(document).ready(function(){
	$('#table0').on('click-row.bs.table', function (e,row,$element,field){
		var areaName = field=="TOTAL"?"":field;
		if($element.context.children.length>0){
			openwindow($ctx + '/lnkhzs/quality/promotionList.jsp?a02='+row.A02+'&a03='+row.A03
					+'&a04='+row.A04+'&a05='+row.A05+'&a06='+row.A06
					+'&a07='+row.A07+'&a08='+row.A08+'&areaName='+areaName,'');
		}
	}); 
	
	
	
	
});

//受理渠道
function getTablenameSLQD() {
	var tablename = "NGCS_WF_CMPLNTS_ARC_DETAIL_SJ_NM";
	return tablename;
}

//用户星级
function getTablenameYHXJ() {
	var tablename = "NGCS_WF_CMPLNTS_ARC_DETAIL_SJ_STAR";
	return tablename;
}

