var flag="11";
var setting = {
    view : {
        showIcon : false
    },
    data : {
        simpleData : {
            enable : true
        }
    },
    check : {
    	enable : true,
    	chkboxType : { 
    		"Y" : "ps",
    		"N" : "ps" 
    	}
    },
    callback : {
    	onCheck : onClick,
        onClick : onTreeClick
    }
};


$(document).ready(function(){
	
/*	var tablename = getTablename();
	//获取列表
	if(tablename!=""){
		$.post($ctx + "/tsgd/tsgd/ajaxGetSearchTree.action?tableName=" + tablename, {}).done(function(entities) {
	    	$.fn.zTree.init($("#tree1"), setting, entities);
	    });
	}*/
    
    
	$(".icon-open").click(function(){
		if (flag == "11") {
			$(".icon-open").css("background-image","url(../style/image/jiantou22.png)");
			$(".icon-open").css("background-size","60%");
			$(".icon-open").css("height","70%");
			$(".icon-open").css("width","90%");
			/*$(".icon-open").css("width","70%");
			$(".icon-open").css("height","95%");*/
			$(".icon-open").css("margin-left","7px");
			flag="22";
		} else if (flag == "22") {
			$(".icon-open").css("background-image","url(../style/image/jiantou11.png)");
			$(".icon-open").css("margin-left","-5px");
			flag="11";
		}
	});
	
	$('.t-tab-cart').click(function (){ 
		if($('.t-wrap').hasClass('t-open')){
			if($(this).find('.tab-text').length > 0){
				$(this).addClass('t-tab-click-selected'); 
				$(this).find('.tab-text').remove();
				
			}else{
				$('.t-wrap').removeClass('t-open');
				$(this).removeClass('t-tab-click-selected');
			}
		}else{ 
			$(this).addClass('t-tab-click-selected'); 
			$(this).find('.tab-text').remove();
			$('.t-wrap').addClass('t-open'); 
		}
	});
});

function onClick(e, treeId, node) {
   	var zTree = $.fn.zTree.getZTreeObj(treeId);
}
function onTreeClick(event, treeId, treeNode, clickFlag) {
   	var treeObj = $.fn.zTree.getZTreeObj("tree1");    
    // 获取当前勾选数据的值
    var checkNodes = treeObj.getCheckedNodes(true);
    // 获取当前选中数据的值
    var nodes = treeObj.getSelectedNodes();
    for (var i=0; i < nodes.length; i++) {
	    if (nodes[i].checked==false) {
	    	treeObj.checkNode(nodes[i], true, true)//勾选
	    } else {
	    	treeObj.checkNode(nodes[i], false, true);//取消勾选
	    }
	}
}

function checkState(node) {
	var arrList = [];
	var str="";
	if (node.checked) {
		if (node.check_Child_State == -1 || node.check_Child_State == 2) {
			arrList.push(node.name);
		} else if (node.check_Child_State == 0) {
			console.log("请确认节点状态是否正确（父节点名称："+ node.name + "，当前check_Child_State状态:0");
		} else if (node.check_Child_State == 1) {
			for (var i=0, l=node.children.length; i < l; i++) {
	        	var recheck = checkState(node.children[i]);
	        	if (recheck != "") {
	        		for (var j=0, k=recheck.length; j < k; j++) {
	        			arrList.push(node.name + "→" + recheck[j]);
	        		}
	        	}
	      	}
		}
	} else {
		return "";
	}
	return arrList;
}

function changeSelVal() {
	var arrList = [];
	var treeObj = $.fn.zTree.getZTreeObj("tree1");
	if (treeObj != null) {
		//获取全部根节点数据
		var nodes = treeObj.getNodes();
		for (var i = 0, l = nodes.length; i < l; i++) {
			var nodeId = nodes[i].id; // 树节点编码
			var nodeName = nodes[i].name; // 树节点名称
			var arrListSon = checkState(nodes[i]);
			if (arrListSon != "") {
				arrList = arrList.concat(arrListSon);
			}
		}
		$("input[name='types']").val(arrList);

		$.bs.table.reload('table0');
	}

}


function exportSelVal() {
	var arrList = [];
   	var treeObj = $.fn.zTree.getZTreeObj("tree1");
   	if(treeObj!=null){
   		var nodes = treeObj.getNodes();
   	  	for (var i=0, l=nodes.length; i < l; i++) {
   	   		var nodeId = nodes[i].id; // 树节点编码
   	    	var nodeName = nodes[i].name; // 树节点名称
   	    	var arrListSon = checkState(nodes[i]);
   	    	if (arrListSon != "") {
   	    		arrList = arrList.concat(arrListSon);
   	    	}
   	  	}
   		$("input[name='types']").val(arrList);
   	}
  	//获取全部根节点数据
  	
	var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr").find("th");
	var titles =[];
	var columns =[];
	$th.each(function(i,t){
		titles[i]=$(this).find(".th-inner").text();
		columns[i]=$(this).attr("data-field")
	});
	
	var rQueryName= $("#table0").attr("sqlname");
	var rQueryTableName= $("#tablename").attr("sqltablename");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	if(rQueryTableName==undefined){
		exportExcelNew(rQueryName,titles,columns,sheetTitle);
	}else{
		exportExcelNew2(rQueryName,titles,columns,sheetTitle,"",rQueryTableName);
	}
}



function resetSelVal() {
   	var treeObj = $.fn.zTree.getZTreeObj("tree1"); 
   	if(treeObj!=null){
   		treeObj.checkAllNodes(false);
   	}
	
	
	$("input[type='checkbox']:checked").removeAttr("checked");
	checkboxOnclick();
	
	$('.select2-selection__rendered').html("<span class='select2-selection__placeholder'>请输入</span>");
}



var loadindex =0;
function table_load_success(data) {
	if(loadindex ==0){
		var middle = $(".fixed-table-searchfield").clone(true);
		$(".fixed-table-searchfield").remove();
		$('#replacepl').append(middle);
		$(".fixed-table-searchfield").children().show();
		
		var middle2 = $(".btn_box.overflow").clone(true);
		$(".btn_box.overflow").remove();
		$(".serachdivTable").before(middle2);

		$('.iconbtn.icon-searchlist').css("float", "left");
		$('.iconbtn.icon-searchlist').css("margin", "8px 0px 0px 20px");
		
		$('.iconbtn.icon-down3').css("float", "left");
		$('.iconbtn.icon-down3').css("margin", "8px 0px 0px 20px");
		
		$('.iconbtn.reset').css("float", "left");
		$('.iconbtn.reset').css("margin", "8px 0px 0px 20px");

	}
	$('#table0_serachdiv').show()
	loadindex++;
	
	if ($('#complainText').length > 0) {
		addSelect2();
	}
}

function addSelect2() {
	var tablename = getTablename();// 后台暂时未使用
	$('#complainText').addClass("form-control");
	$('#complainText').select2({
		placeholder: '请输入',
		ajax: {
			url: $ctx + "/tsgd/tsgd/ajaxGetSearchCon.action?tableName=" + tablename,
			dataType: 'json',
			delay: 250,
			data: function (params) {
				return {
					dataField: params.term,
				};
			},
			processResults: function (data) {
				var sdata = [];
				var returnsdata="";
				var araback = {
						id: "",
						text: "请输入"
					}
				sdata.push(araback);
				for (var a=0; a<=data.length; a++) {
					var ara = {
						id: data[a],
						text: data[a]
					}
					sdata.push(ara);
				}
	    	  
				return {
					results: sdata
				};
			},
			cache: true
		},
		minimumInputLength: 2
	});
	$('.select2-selection__rendered').css("width","150");
	$("#cleanstore").click(function(){
		$("#complainText").select2("val", "请输入"); 
	});
}



$(document).ready(function() {
	var myDate = new Date();
	//获取当前年
	var year = myDate.getFullYear();
	//获取当前月
	var month = myDate.getMonth() + 1;  
	if(month<10){
		month="0"+month;
	}
	//$('input[name="begintime"]').val(year+'-'+month+'-01');
	$('input[name="begintime"]').val(year+month+'01');
	$('input[name="ntime"]').val(year+'-'+month);
	$.bs.table.init('table0');
});
 

