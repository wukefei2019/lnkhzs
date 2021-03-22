$(document).ready(function(){
	$('#table1').removeClass("table-striped");
    $(".btn-link.icon-roundadd").on("click",fn_to_editpage).show();
    
    $('#table1').on('sort.bs.table', function (e,name,order) {
        if("SERIALNO"==name)(
        	$('#table1').bootstrapTable('getOptions').sortName = "to_number(SERIALNO)"
        );
    });
    $('#table1').on('load-success.bs.table', function (data) {
    	table_load_success();
    	rowStyle();
    });
});

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
	});
}
var fn_checkbox_disable = function(value,row,index){
	/*if(!!row.LINK_PROGRESS || row.STATUS != ''){
		return {
			disabled : true
		};
	}
	return value;*/
	var date = new Date();
	var today = date.format("yyyy-MM-dd");
	date.setMonth(date.getMonth()-3);
	var deadline = date.format("yyyyMMdd");
	html = [];
	if(deadline > row.TRACE_SOURCE_TIME){
		html.push('<span name="largeOverdue" style="background-image:url(../big.png);background-position:center;padding:20px;background-size:40px 40px;background-repeat:no-repeat;">'+value+'</span>');
/*		$($('#table1')[0].rows[index]).attr("style", "background-color:#de3d39");*/
	}else if(!!row.DEALTIME && today > row.DEALTIME){
		html.push('<span style="background-image:url(../small.png);background-position:center;padding:20px;background-size:40px 40px;background-repeat:no-repeat;">'+value+'</span>');
	}else{
		html.push(value);
	}
	return html.join("");
}

function rowStyle(row,index) {
/*	var tableId = document.getElementById("table1");

    var y = 0;
    for (var i = 0; i < row.rows.length; i++) {
        //将已标记的数据改变背景颜色
        y++;
    }*/
	$('#table1').find("tr [name='largeOverdue']").parents('tr').attr("style", "background-color:#de3d39");
	
}
var fn_show = function(value,row,index){
	if(!row.LINK_PROGRESS){
			value="新发起";
	}
	var classes = [
	    'bg-blue',
	    'bg-green',
	    'bg-orange',
	    'bg-yellow',
	    'bg-red'
	  ]

	  if("已完成"==row.LINK_PROGRESS||"完成"==row.LINK_PROGRESS){
		  value='<span style="color:green">'+value+'</span>';
	  }else if("处理中"==row.LINK_PROGRESS){
		  value='<span style="color:HotPink">'+value+'</span>';
	  }else if("待验证"==row.LINK_PROGRESS){
//		  value='<span class="backfround-color:orange">'+value+'</span>';
		  value='<span style="color:orange">'+value+'</span>';
	  }else if("整改中"==row.LINK_PROGRESS){
		  value='<span style="color:red">'+value+'</span>';
	  }else{
		  value='<span style="color:blue">新发起</span>';
	  }
	return value;
}

function fn_to_editpage(){
	openwindow($ctx + '/lnkhzs/quality/addSerRequestNode.jsp?status='+$(this).text(),'');
}

function singleSubmit() {
	openwindow($ctx + '/lnkhzs/trace/selectDealtime.jsp?callback=submit','');
}

var submit = function(value){
	if(confirm("确认是否提交？")){
		var array = $('#table1').bootstrapTable('getAllSelections');
		var data=[];
		for(var i=0;i<array.length;i++){
			data.push(array[i].PID);
		}
		$.post($ctx + '/trace/flow/doAction.action',{data:JSON.stringify( array ),actionStr:'提交',dealtime:value}).done(function(result){
			if (result == 'success') {
				alert("操作成功");
				$(document).find(".btn-link.icon-refresh2").click();
			} else {
				alert(result);
			}
		});
	}
}
function allSubmit() {
	openwindow($ctx + '/lnkhzs/trace/selectDealtime.jsp?callback=submitAll','');
}


var submitAll = function(data){
	if(confirm("确认是否提交？")){
		$.post($ctx + '/trace/flow/selectAll.action',{data:data}).done(function(result){
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
	$.post($ctx + '/trace/traceSource/remove.action',{data:JSON.stringify( array )}).done(function(result){
		if (result == 'success') {
				 alert("操作成功");
				 $(document).find(".btn-link.icon-refresh2").click();
		} else {
			 alert("操作失败");
		}
	});
}
function exportVal() {
	var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered").find("thead").find("tr").find("th");
	var titles =[];
	var columns =[];
	$th.splice(0,1);
	$th.each(function(i,t){
		
			titles[i]=$(this).find(".th-inner").text();
			columns[i]=$(this).attr("data-field")
		
		
	});
	var rQueryName= $("#table0").attr("sqlname");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	
	exportExcelNew(rQueryName,titles,columns,sheetTitle);
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