$(document).ready(function(){
	$('#table1').removeClass("table-striped");
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
    
    $('#table1').on('sort.bs.table', function (e,name,order) {
        if("SERIALNO"==name)(
        	$('#table1').bootstrapTable('getOptions').sortName = "to_number(SERIALNO)"
        );
    })
});

function fmt_operate(value, row, index) {
	var html=[];
	if(row.LINK_PROGRESS=="待验证"){
		html.push("<a title='验收' class='edit btn-link fontsize14' >验收</a>");
		html.push("<a title='整改' class='rectification btn-link fontsize14' >整改</a>");
	}
	html.push("<a title='导出' class='export btn-link fontsize14' >导出</a>");
	return html.join("");
}

var fn_operate_events = {
	    'click a.edit': function (e, value, row, index) {
	        openwindow($ctx +'/trace/traceSource/addCheck.action?pid=' + row.PID ,'');
	    },
	    'click a.export': function (e, value, row, index) {
		    $.bs.grid.tips("正在为您导出数据，请稍后... ...",{level:'info'});
		    downloadExcel('/trace/traceSource/exportExcel.action',{pid:row.PID});
	    },
	    'click a.rectification': function (e, value, row, index) {
	    	if(confirm("确认是否整改？")){
		    	$.post($ctx + '/trace/flow/doAction.action',{data:row.PID,actionStr:'整改'}).done(function(result){
					if (result == 'success') {
						alert("操作成功");
						$(document).find(".btn-link.icon-refresh2").click();
					} else {
						alert(result);
					}
				});	
	    	}
	    }
};

function getTablename() {
	var tablename = "ZL_TRACE_SOURCE_LIST";
	return tablename;
}
function table_load_success() {
	$('.table-hover tbody tr').on('mouseenter',function(){
		$( "tr" ).hover(
			  function() {
				$('#table1').bootstrapTable('hover',(this.rowIndex-1));
			  }, function() {
//				$('#table1').bootstrapTable('hover',(this.rowIndex));
			  }
		);
	})
}

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}
function exportd() {
		var array = $('#table1').bootstrapTable('getAllSelections');
		var nextloginname = $("#nextloginname").val();
		var data=[];
		for(var i=0;i<array.length;i++){
			data.push(array[i].PID);
		}
		var params = {};
		var url = '/trace/traceSource/exportExcelCheck.action?nextloginname'+nextloginname;
		$(".serachdiv:first").find("input,select").each(function(){
			if($(this).val()!=null){
				params[$(this).attr("name")] = $(this).val();
			}
		});
		downloadExcel(encodeURI(url),{pid:data,params:params});
}
function submitAll() {
	if(confirm("确认是否提交？")){
		$.post($ctx + '/trace/flow/selectAll.action').done(function(result){
			if (result == 'success') {
				alert("操作成功");
				$(document).find(".btn-link.icon-refresh2").click();
			} else {
				alert(result);
			}
		});
	}
}
function remove() {
	var array = $('#table1').bootstrapTable('getSelections');
	var data=[];
	for(var i=0;i<array.length;i++){
		data.push(array[i].PID);
	}
/*	$.post($ctx + '/trace/traceSource/remove.action',{data:JSON.stringify( array )}).done(function(result){
		if (result == 'success') {
				 alert("操作成功");
				 $(document).find(".btn-link.icon-refresh2").click();
		} else {
			 alert("操作失败");
		}
	});*/
	
	$.post($ctx + '/trace/traceSource/resetFlow.action',{data:JSON.stringify( array )}).done(function(result){
		if (result == 'success') {
				 alert("操作成功");
				 $(document).find(".btn-link.icon-refresh2").click();
		} else {
			 alert("操作失败");
		}
	});
}

function rectify() {
	if(confirm("确认是否批量整改？")){
		var array = $('#table1').bootstrapTable('getSelections');
		var data=[];
		for(var i=0;i<array.length;i++){
			data.push(array[i].PID);
		}
		/*	$.post($ctx + '/trace/traceSource/remove.action',{data:JSON.stringify( array )}).done(function(result){
		if (result == 'success') {
				 alert("操作成功");
				 $(document).find(".btn-link.icon-refresh2").click();
		} else {
			 alert("操作失败");
		}
	});*/
		
		$.post($ctx + '/trace/flow/batchRectify.action',{data:JSON.stringify( array )}).done(function(result){
			if (result == 'success') {
				alert("操作成功");
				$(document).find(".btn-link.icon-refresh2").click();
			} else {
				alert("操作失败");
				$(document).find(".btn-link.icon-refresh2").click();
			}
		});
	}
}

fn_evnt_name_look = {
	'click a': function (e, value, row, index) {
				openwindow($ctx + '/lnkhzs/trace/traceSourceFList.jsp?pid='+row.PID,'');
			   }
}

function deleteAll() {
	if(confirm("确认是否删除全部数据？")){
		$.post($ctx + '/trace/traceSource/deleteAll.action').done(function(result){
			if (result == 'success') {
					 alert("操作成功");
					 $(document).find(".btn-link.icon-refresh2").click();
			} else {
				 alert("操作失败");
			}
		});
	}
}