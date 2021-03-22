
function tquerysubmit()
{
	var tName = document.getElementById("tqueryname");
	var tValue = document.getElementById("tquerytext");
	if(tName!=null && tValue!=null)
	{
		var setVar=document.getElementById(tName.value);
		if(setVar!=null)
		{
			setVar.value=tValue.value;
		} 
	}
}

function querysubmit()
{
	var tName = document.getElementById("tqueryname");
	var tValue = document.getElementById("tquerytext");
	if(tName!=null && tValue!=null)
	{
		var setVar=document.getElementById(tName.value);
		if(setVar!=null)
		{
			tValue.value=setVar.value;   
		}
	}
}

function showsearch(name) {
	var objdiv;
	
	if (name == null || name == "") {
		objdiv = document.getElementById("serachdiv");


	} else {
		objdiv = document.getElementById(name + "_serachdiv");
	}
	if (objdiv != null) {
		var display = objdiv.style.display;
		if (display == "block") {
			objdiv.style.display = "none";
		} else {
			objdiv.style.display = "block";
		}
	}
}
function showsearchcondition(ctx, sqlname){
	var src = ctx + "/userManager/configSearchCondition.action?sqlname=" + sqlname;
	 window.open(src,'','width=600px,height=350px,top=250px,left=300px,Location=no,Toolbar=no,Resizable=yes,scrollbars=no');
}
var _ischeck = false;
function checkAll(checkName) {  
	var m = document.getElementsByName(checkName);
	var len = m.length;
	for (var i = 0; i < len; i++) {
		if (m[i].disabled) {
			continue;
		}
		if (_ischeck) {
			m[i].checked = false;
		} else {
			m[i].checked = true;
		}
	}
	if (_ischeck) {
		_ischeck = false;
	} else {
		_ischeck = true;
	}
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
}
function setSelectValue(objSel, objValue)
{
	if(objSel.length>0)
	{
		objSel.options[objValue - 1].selected = true;
	}
}
function tranferPage(type) 
{
	document.getElementById("var_istranfer").value = "1";
	var objCurpage = document.getElementById("var_currentpage");
	var pageCount = document.getElementById("var_totalpages").value;
	var pageNumber = objCurpage.value;
	if (pageNumber == "") {
		pageNumber = "1";
	}
	if (type == "frist") {
		if (pageNumber != "1") {
			objCurpage.value = 1;
			document.forms[0].submit();
		}
	} else {
		if (type == "previous") {
			var intpageNumber = parseInt(pageNumber);
			if (intpageNumber > 1) {
				intpageNumber--;
				objCurpage.value = intpageNumber;
				document.forms[0].submit();
			}
		} else {
			if (type == "next") {
				var intpageNumber = parseInt(pageNumber);
				var intpageCount = parseInt(pageCount);
				if (intpageCount > intpageNumber) {
					intpageNumber++;
					objCurpage.value = intpageNumber;
					document.forms[0].submit();
				}
			} else {
				if (type == "last") {
					if (objCurpage.value != pageCount) {
						objCurpage.value = pageCount;
						document.forms[0].submit();
					}
				} else {
					if (type == "go") {
						document.forms[0].submit();
					} else {
						if (type == "setsize") {
							objCurpage.value = 1;
							document.forms[0].submit();
						}
					}
				}
			}
		}
	}
}
function searchSubmit(){
	document.forms[0].submit();
}
function searchReset(){
	document.forms[0].reset();
}
function checkNull(){
	var $required =$(".required");
	for(var i=0;i<$required.size();i++){
		var label =$required.eq(i).text().replace("：","");
		var value=$required.eq(i).next().find("select,input").val();
		if(!value){
			return label+"不能为空！";
		}
	}
	return "";
}

function editDataPublic(pid,action,location){
	var url =$ctx+action+pid;
	if(location=='1'){
		window.location=url;
	}else{
		openwindow(url,"");
	}
	
}
function addDataPublic(action){
	var url =$ctx+action;
	openwindow(url,"");
}
function saveDeletePublic(pid,action){
	if(window.confirm('您确定要删除吗？')){
		var url =$ctx+action+pid;
		window.location=url;
     };	
}

function exportExcel(ctx, sqlname, cfgmark) {
	var oldSrc = document.forms[0].action;
	document.forms[0].action = ctx + "/excelManager/exportExcel.action?sqlname=" + sqlname + "&cfgmark=" + cfgmark;
	document.forms[0].submit();
	document.forms[0].action = oldSrc;
}
function importExcel(imptype, xmlname) {
	if(imptype != 'remedy')
		imptype = '';
	window.open($ctx + "/ultrasm/common/commonImport.jsp?imptype=" + imptype + "&xmlname=" + xmlname, "", "location=no,toolbar=no,resizable=yes,scrollbars=no,width=500,height=300,top=100,left=300");
}

function downloadExcel(url,params){
    params = params || {};
    $.bs.grid.tips("正在导出，请稍候... ...",{level:"info",delay:5000});
    var kv = [];
    var $btn = $(event.srcElement || event.target);
    $table = $btn.data("target")? $("#" + $btn.data("target")) : $(event.srcElement || event.target).parents(".data-grid-omcs");
	kv.push('sqlname=' + $table.attr("sqlname"));
//	$table.find(".fixed-table-searchfield:first").find("input,select").each(function(){
//    	//par+="params."+ $(this).attr("name")+"="+ $(this).val()+"&";
//        kv.push("params."+ $(this).attr("name")+"="+ $(this).val())
//	 });
    for ( var p in params) {
        kv.push("params."+ p +"="+ params[p])
    }
    if($("iframe[name='download']").length>0){
    	$("iframe[name='download']").attr("src",encodeURI($ctx + url+ "?" +kv.join("&"))) ;
    }else{
    	$('body').html('<iframe name="download" src="" style="display:none"></iframe>');
    	$("iframe[name='download']").attr("src",encodeURI($ctx + url+ "?" +kv.join("&"))) ;
    }
}

function downloadExcel2(url,params){
    params = params || {};
    $.bs.grid.tips("正在导出，请稍候... ...",{level:"info",delay:5000});
    var kv = [];
    var $btn = $(event.srcElement || event.target);
    $table = $btn.data("target")? $("#" + $btn.data("target")) : $(event.srcElement || event.target).parents(".data-grid-omcs");
	kv.push('sqlname=' + $table.attr("sqlname"));
//	$table.find(".fixed-table-searchfield:first").find("input,select").each(function(){
//    	//par+="params."+ $(this).attr("name")+"="+ $(this).val()+"&";
//        kv.push("params."+ $(this).attr("name")+"="+ $(this).val())
//	 });
	var $form = $("<form id='form' class='hidden' method='post' target='frm'></form>");
	$form.attr("action",$ctx + url);
	$("body").append($form);
    for ( var p in params) {
    	var $input1 = $("<input name='params."+p+"' type='text'></input>");
		$input1.attr("value",params[p]);
		$("#form").append($input1);
//        kv.push("params."+ p +"="+ params[p])
    }
/*    if($("iframe[name='download']").length>0){
    	$("iframe[name='download']").attr("src",encodeURI($ctx + url+ "?" +kv.join("&"))) ;
    	var $form = $("<form id='download' class='hidden' method='post'></form>");
    	$form.attr("url",$ctx + url);
    	$("body").append($form);
    	for(var j;j<kv.length();j++){
    		var $input = $("<input name='param."+kv[j]+"' type='text'></input>");
    		$input1.attr("data",kv.join("&"));
    	}
    	$("#download").append($input1);*/
    	$("#form").submit();
/*    	$.post($ctx + url,$("form").serializeArray()).done(function(result){
    		alert(result);
    	});*/
    /*}else{
    	$('body').html('<iframe name="download" src="" style="display:none"></iframe>');
    	$.post($ctx + url,{params:kv.join("&")}).done(function(result){
    		var blob = new Blob([result]);
    		//对于Blob对象，我们可以创建出一个URL来访问它。使用URL对象的createObjectURL方法。
    		var a = document.createElement('a');
    		a.download = 'data.xls';
    		a.href=window.URL.createObjectURL(blob);
    		a.click()
		});*/
//    }
}

function beginWF4(baseSchema,params,openwindow) {
	if(openwindow=='openwindow'){
		window.open($ctx+"/ultrabpp/view.action?mode=NEW&baseSchema="
				+ baseSchema+params);
	}else{
		window.location = $ctx+"/ultrabpp/view.action?mode=NEW&baseSchema="
		+ baseSchema+params;
	}
	
}

function openFlowMap(baseSchema,baseId,version,entryId) {
	var url=$ctx+"/sheet/openFlowMap.action?baseSchema="+baseSchema+"&baseId="+baseId+"&version="+version+"&entryId="+entryId;
	window.open(url);
}

function getChildSelect(oldValue,childName,parentName,codeType){
	var subobj = document.getElementById(childName);
/*	$(subobj).next(".ui-select-input").text(oldValue);
	subobj.value = oldValue;*/
	var obj = document.getElementById(parentName);
	$.get($ctx+"/dicManager/getChildNode.action", {dictypecodePara:codeType,dicvaluePara:obj.value},function(result){
		if(result != null && result != ''){
			var ressplit = result.split(';');			
			subobj.options.length = 0;
			subobj.options.add(new Option('',''));
			for(var node=0;node<ressplit.length;node++){
				var childStr = ressplit[node];
				var childArr = childStr.split(',');		
				var opt = new Option(childArr[1],childArr[0]);
				opt.selected = oldValue==childArr[1];
				subobj.options.add(opt);
			}
		 }else{
			 subobj.options.length = 0;
			 subobj.options.add(new Option('',''));
		 }
	});
}

function sortQuery(sortFiled)
{
	document.getElementById("var_sortfield").value = sortFiled;
	if (document.getElementById("var_sorttype").value!= "1") 
	{
		document.getElementById("var_sorttype").value = "1";
	}
	else 
	{
		document.getElementById("var_sorttype").value = "0";
	}
	document.forms[0].submit();
}
function import_callback(level,msg){
    
    $.bs.tips(msg,{level:level,$target:$("form"),clear:true,auto_close:false});
}

$(document).ready(function(){

//    横向滚动宽度
    var th_width_total = 0;
    var table_frame_width = $(".ui-table-frame,.fixed-table-container").width();
    $(".ui-table-frame .ui-table th").each(function(i,th){
        th_width_total += Number($(th).attr("width") || (function(th) {
            return ($(th).text().length || 1) * 12 + 24;
        })(th));
    });
    if(table_frame_width!=0 && th_width_total > table_frame_width){
        $(".ui-table-frame .ui-table").width(th_width_total);
    }
    
});