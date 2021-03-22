function fmt_operate(value, row, index) {
		var html=[];
		html.push("<a title='删除' class='delete btn-link fontsize14' >删除</a>");
		return html.join("");
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
		openwindow($ctx + '/lnkhzs/survey/addDywj.jsp?id='+row.ID+"&msg="+1,'');
   }
}

var fn_operate_events = {
    'click a.delete': function (e, value, row, index) {
    	if (confirm("您确认删除吗？")) {
    	$.post($ctx + '/khzs/surveyRe/deleteDywj.action?khzsRelationship.id='+row.ID).done(function(result){
    		if (result == 'success') {
    			alert("删除成功");
    			$.bs.table.refresh("table0");
                
    		}
    	});
    	};
    },
    
    'click a.update': function (e, value, row, index) {
    	openwindow($ctx + '/lnkhzs/survey/updateDywj.jsp?id='+row.ID,'');
    },
    
    'click a.wjwh': function (e, value, row, index) {
    	openwindow($ctx + '/lnkhzs/survey/wjwh.jsp?id='+row.ID,'');
    }
};



