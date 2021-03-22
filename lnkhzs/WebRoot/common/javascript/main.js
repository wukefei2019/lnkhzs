// JavaScript Document
/**
 * 以下变量为公用常量，请勿随意改动！
 */
var LAYOUT_ZERO = 0;
var LAYOUT_LIST_RIGHT = 26 + 29;		//左树右列表的“列表”
var LAYOUT_FORM_RIGHT = 26 + 35;		//左树右信息页面的“信息页面”
var LAYOUT_LIST_OPEN = 26 + 29;		//工具条在“上端”所使用
var LAYOUT_FORM_OPEN = 26 + 35;		//工具条在“下端”所使用（保存、取消）
var LAYOUT_LIST_TAB = 26;		//列表页面最上端有tab页的情况
var LAYOUT_FRAME_RIGHT = 26;	//页面是左中右的总页面的配置
//由于按钮在上面，所以使用list的工具条的高度
function check_browser() {
   Opera = (navigator.userAgent.indexOf("Opera",0) != -1)?1:0;
   MSIE = (navigator.userAgent.indexOf("Microsoft",0) != -1)?1:0;
   FX = (navigator.userAgent.indexOf("Mozilla",0) != -1)?1:0;
   if ( Opera ) brow_type = "Opera";
   else if ( FX )brow_type = "Firefox";
   else if ( MSIE )brow_type = "MSIE";
   return brow_type;
}
function getWindowHeight() {
	var windowHeight = 0;
	if (typeof(window.innerHeight) == 'number') {
		windowHeight = window.innerHeight;
	}
	else {
		if (document.documentElement && document.documentElement.clientHeight) {
			windowHeight = document.documentElement.clientHeight;
		}
		else {
			if (document.body && document.body.clientHeight) {
				windowHeight = document.body.clientHeight;
			}
		}
	}
	return windowHeight;
}
function getWindowWidth() {
	var windowWidth = 0;
	if (typeof(window.innerWidth) == 'number') {
		windowWidth = window.innerWidth;
	}
	else {
		if (document.documentElement && document.documentElement.clientWidth) {
			windowWidth = document.documentElement.clientWidth;
		}
		else {
			if (document.body && document.body.clientWidth) {
				windowWidth = document.body.clientWidth;
			}
		}
	}
	return windowWidth;
}

function setCenter(x,y) {
	if (document.getElementById) {
		var windowHeight = getWindowHeight();
		var windowWidth = getWindowWidth();
		var brow_type = check_browser(); 
		if (windowHeight > 0) {
			var centerElement = document.getElementById('center');
			if(centerElement != null){
				if (windowHeight - (88) >= 0) {
					if (brow_type == "MSIE") {						
						centerElement.style.height = (windowHeight - y) + 'px';
						centerElement.style.width = (windowWidth - x) + 'px';
					}
					else
					{
						centerElement.style.height = (windowHeight - y) + 'px';
						centerElement.style.width = (windowWidth - x) + 'px';
					}
				}
			}
		}
	}
}
function changeRow_color(obj) {
var Ptr=document.getElementById(obj).getElementsByTagName("tr");
	
		for (var i=1;i<Ptr.length+1;i++) 
		{ 
		if(i%2>0)
		 { Ptr[i-1].className = "t2";}
		else
		 {Ptr[i-1].className = "t1";}
		}
	for(var i=0;i<Ptr.length;i++) {
		Ptr[i].onmouseover=function(){
		this.tmpClass=this.className;
		this.className = "t3";    
		};
		Ptr[i].onmouseout=function(){
		this.className=this.tmpClass;
		};
	}
}
function changeRow_color_custom(obj, group) {
	var Ptr=document.getElementById(obj).getElementsByTagName("tr");
		
	for (var i=1;i<Ptr.length;i++) 
	{
		if(Math.floor((i-1)/group)%2>0)
			{Ptr[i].className = "t2";}
		else
			{Ptr[i].className = "t1";}
	}
	for(var i=1;i<Ptr.length;i++)
	{
		var si = 0;
		if((i%group) > 0)
		{
			si = (i%group) - 1;
		}
		else
		{
			si = group - 1;
		}
		//var si = group-1-(i%group);
		//var trarr = '';
		var trarr = new Array();
		for(var j = i-si; j <= group; j++)
		{
			//trarr += ', Ptr[' + j + ']';
			trarr.push(Ptr[j]);
		}
		
		//var mouseOverEvalStr = 'EventHandler.attachEvent(Ptr[i], EventType.mouseover, EventHandler.createEvent(mouseOverEvent' + trarr + '))';
		//eval(mouseOverEvalStr);
		EventHandler.attachEvent(Ptr[i], EventType.mouseover, EventHandler.createEvent(changeRow_color_custom_mouseOverEvent, Ptr[i], si, group-si-1));
		
		
		//var mouseOutEvalStr = 'EventHandler.attachEvent(Ptr[i], EventType.mouseout, EventHandler.createEvent(mouseOutEvent' + trarr + '))';
		//eval(mouseOutEvalStr);
		EventHandler.attachEvent(Ptr[i], EventType.mouseout, EventHandler.createEvent(changeRow_color_custom_mouseOutEvent, Ptr[i], si, group-si-1));
	}
}
function changeRow_color_custom_mouseOverEvent(trobj, s1, s2)
{
	trobj.tmpClass=trobj.className;
	trobj.className = "t3";
	var preObj = trobj.previousSibling;
	for(var i = 0; i < s1; i++)
	{
		preObj.tmpClass=preObj.className;
		preObj.className = "t3";
		preObj = preObj.previousSibling;
	}
	preObj = trobj.nextSibling;
	for(i = 0; i < s2; i++)
	{
		preObj.tmpClass=preObj.className;
		preObj.className = "t3";
		preObj = preObj.nextSibling;
	}
}
function changeRow_color_custom_mouseOutEvent(trobj, s1, s2)
{
	trobj.className=trobj.tmpClass;
	var preObj = trobj.previousSibling;
	for(var i = 0; i < s1; i++)
	{
		preObj.className=preObj.tmpClass;
		preObj = preObj.previousSibling;
	}
	preObj = trobj.nextSibling;
	for(i = 0; i < s2; i++)
	{
		preObj.className=preObj.tmpClass;
		preObj = preObj.nextSibling;
	}
}
function getPageMenu(menuName,divName)
{
	activePageMenu = menuName;
	activePageDiv = divName;
}

function PageMenuActive(objName,divName)
{
	document.getElementById(activePageMenu).className = 'tab_hide';
	if(activePageDiv != '')
	{
		document.getElementById(activePageDiv).style.display = 'none';
	}
	document.getElementById(objName).className = 'tab_show';
	document.getElementById(divName).style.display = '';
	activePageMenu = objName;
	activePageDiv = divName;
}
function checkAll(){
			var chk = document.forms["form1"].elements["chkAll"];
			var inputObj =  document.forms["form1"].getElementsByTagName("input");
			for (var i = 0; i < inputObj.length; i++) {
				var temp = inputObj[i];
				if(temp.type == "checkbox"){
					temp.checked = chk.checked;
				}
			}
		}
/*ban particular character in text field*/
function clearSpecialChar(event)
{
	//               [", <, >,  &, ']
	var specialArr = [34,60,62,38,39];//add the ascii value of particular character
	var srcCode = event.keyCode;
	if(srcCode==undefined)
	{
		srcCode = event.which;
	}
	var b = true;
	for(var i=0;i<specialArr.length;i++)
	{
		if(srcCode==specialArr[i])
		{
			b = false;
			alert('输入字符不能是下列字符之一：\n'+'\"  -英文双引号\n\'  -英文单引号\n<  -小于符号\n>  -大于符号\n&  -&符号');
			break;
		}
	}
	return b;
}
/*open window at the middle&center of screen*/
function openwindow(url,name,iWidth,iHeight)
{
	var resurl = encodeURI(url); 
	var name=null;
	name = name || new Date().getTime();
	iWidth = iWidth || 1200; 
	iHeight = iHeight || 800; 
	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	var iLeft = (window.screen.availWidth-10-iWidth)/2; 
	return window.open(resurl,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
}

function windowLocation(url){
	location.href =encodeURI(url);
}

function downloadFileurl(url){
	var manurl = "http://office.ln.cmcc/OAArchMoa/OAAttach/"+url;
	location.href =encodeURI(manurl);
}

/*
 function: compare old_str and new_str, then output add_str and del_str
 example_1:	old_str=1,2,3,5;new_str=1,3,4,6;compareStr(old_str,new_str)=4,6;2,5
 notice: under any condition, ';' exists all the same!
*/
function compareStr(oldStr,newStr)
{
	if(oldStr==newStr)
	{
		return ';';
	}
	if(oldStr=='')
	{
		return newStr+';';
	}
	if(newStr=='')
	{
		return ';'+oldStr;
	}
	var oldArr = oldStr.split(',');
	var newArr = newStr.split(',');
	var dels = '';
	var adds = '';
	for(var i=0;i<oldArr.length;i++)
	{	
		var tag = 0;
		for(var j=0;j<newArr.length;j++)
		{
			if(oldArr[i] == newArr[j])
			{
				tag = 1;
				break;
			}
		}
		if(tag == 0)
		{
			dels = dels + oldArr[i] + ',';
		}
	}
	for(var i=0;i<newArr.length;i++)
	{	
		var tag = 0;
		for(var j=0;j<oldArr.length;j++)
		{
			if(newArr[i] == oldArr[j])
			{
				tag = 1;
				break;
			}
		}
		if(tag == 0)
		{
			adds = adds + newArr[i] + ',';
		}
	}
	if(dels=='' && adds!='')
	{
		return adds.substring(0,adds.lastIndexOf(',')) + ';';
	}
	else if(adds=='' && dels!='')
	{
		return ';' + dels.substring(0,dels.lastIndexOf(','));
	}
	else if(adds=='' && dels=='')
	{
		return ';';
	}
	else
	{
		return adds.substring(0,adds.lastIndexOf(',')) + ';' + dels.substring(0,dels.lastIndexOf(','));
	}
}
function setValueToInput(data_name, user_id, user_name, dep_id, dep_name)
{
	setValueToInputAll(data_name, user_id, user_name, dep_id, dep_name, '', '');
}
function setValueToInputAll(data_name, user_id, user_name, dep_id, dep_name, role_id, role_name)
{
	//datainfos：U:id1:name1;U:id2:name2;D:id1:name1;D:id2:name2;R:id1:name1;R:id2:name2...
	var datainfos = document.getElementById(data_name).value;
	var dataArray = datainfos.split(';');
	var u_id = '';
	var u_text = '';
	var d_id = '';
	var d_text = '';
	var r_id = '';
	var r_text = '';
	var type = '';
	for(var i=0 ; i<dataArray.length ; i++)
	{
		var subdata = dataArray[i].split(':');
		type = subdata[0];
		if(subdata.length < 3)
			continue;
		if(type == 'U')
		{
			u_id += ',' + subdata[1];
			u_text += ',' + subdata[2];
		}
		else if(type == 'D')
		{
			d_id += ',' + subdata[1];
			d_text += ',' + subdata[2];
		}
		else if(type == 'R')
		{
			r_id += ',' + subdata[1];
			r_text += ',' + subdata[2];
		}
	}
	if(user_id != '' && user_name != '')
	{
		if(u_id != '')
		{
			u_id = u_id.substring(1);
			u_text = u_text.substring(1);
		}
		document.getElementById(user_id).value = u_id;
		document.getElementById(user_name).value = u_text;
	}
	if(dep_id != '' && dep_name != '')
	{
		if(d_id != '')
		{
			d_id = d_id.substring(1);
			d_text = d_text.substring(1);
		}
		document.getElementById(dep_id).value = d_id;
		document.getElementById(dep_name).value = d_text;
	}
	if(role_id != '' && role_name != '')
	{
		if(r_id != '')
		{
			r_id = r_id.substring(1);
			r_text = r_text.substring(1);
		}
		document.getElementById(role_id).value = r_id;
		document.getElementById(role_name).value = r_text;
	}
}
/*JS高精度计算*/
//除法
function accDiv(arg1,arg2,arg3){ 
	var t1=0,t2=0,r1,r2; 
	try{t1=arg1.toString().split(".")[1].length;}catch(e){} 
	try{t2=arg2.toString().split(".")[1].length;}catch(e){} 
	with(Math){ 
		r1=Number(arg1.toString().replace(".","")); 
		r2=Number(arg2.toString().replace(".","")); 
		if(arg3==undefined)
			return (r1/r2)*pow(10,t2-t1); 
		else
			return accRound((r1/r2)*pow(10,t2-t1),arg3);
	} 
}
//乘法
function accMul(arg1,arg2,arg3) 
{ 
	var m=0,s1=arg1.toString(),s2=arg2.toString(); 
	try{m+=s1.split(".")[1].length;}catch(e){} 
	try{m+=s2.split(".")[1].length;}catch(e){} 
	if(arg3==undefined)
		return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m); 
	else
		return accRound(Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m),arg3);
}
//加法
function accAdd(arg1,arg2,arg3){ 
	var r1,r2,m; 
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;} 
	m=Math.pow(10,Math.max(r1,r2));
	if(arg3==undefined)
		return (arg1*m+arg2*m)/m;
	else
		return accRound((arg1*m+arg2*m)/m,arg3);
}
//减法
function accSubtr(arg1,arg2,arg3){
     var r1,r2,m,n;
     try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
     try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
     m=Math.pow(10,Math.max(r1,r2));
     //last modify by deeka
     //动态控制精度长度
     n=(r1>=r2)?r1:r2;
     if(arg3==undefined)
     	return ((arg1*m-arg2*m)/m).toFixed(n);
     else 
     	return accRound(((arg1*m-arg2*m)/m).toFixed(n),arg3);
}
//保留小数位数，进行四舍五入
function accRound(arg1,arg2){
	try{
		var temp = arg1+"";
		var poi = temp.indexOf(".");
		if(poi==-1)
			return arg1;
		var left = temp.split(".")[0];
		var right = temp.split(".")[1];
		var len = right.length;
		if(len<=arg2)
			return arg1;
		var ritemp = '0.';
		var ritemp2 = '0.';
		var riarr = right.split("");
		for(var i=0;i<arg2;i++){
			ritemp += riarr[i];
			if(i<(arg2-1))
				ritemp2 += '0';
		}
		ritemp = parseFloat(ritemp);
		if(riarr[arg2]>=5){
			ritemp += parseFloat(ritemp2+'1');
		}
		return accAdd(parseFloat(left),ritemp);
	}catch(e){
		alert(e);
		return arg1;
	}
}
// 获取鼠标在浏览器中的坐标
function mousePos(e) {
	var x = null;
	var y = null;
	var e = e||window.event;
	return {
		x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
	};
}

function checkFieldName(fieldName)
{
	 var key = ',ACCESS,ADD,ALL,ALTER,AND,ANY,AS,ASC,AUDIT,BETWEEN,BY,CHAR,CHECK,CLUSTER,COLUMN,COMMENT,COMPRESS,CONNECT,CREATE,CURRENT,DATE,DECIMAL,DEFAULT,DELETE,DESC,DISTINCT,DROP,ELSE,EXCLUSIVE,EXISTS,FILE,FLOAT,FOR,FROM,GRANT,GROUP,HAVING,IDENTIFIED,IMMEDIATE,IN,INCREMENT,INDEX,INITIAL,INSERT,INTEGER,INTERSECT,INTO,IS,LEVEL,LIKE,LOCK,LONG,MAXEXTENTS,MINUS,MLSLABEL,MODE,MODIFY,NOAUDIT,NOCOMPRESS,NOT,NOWAIT,NULL,NUMBER,OF,OFFLINE,ON,ONLINE,OPTION,OR,ORDER,P,CTFREE,PRIOR,PRIVILEGES,PUBLIC,RAW,RENAME,RESOURCE,REVOKE,ROW,ROWID,ROWNUM,ROWS,SELECT,SESSION,SET,SHARE,SIZE,SMALLINT,START,SUCCESSFUL,SYNONYM,SYSDATE,TABLE,THEN,TO,TRIGGER,UID,UNION,UNIQUE,UPDATE,USER,VALIDATE,VALUES,VARCHAR,VARCHAR2,VIEW,WHENEVER,WHERE,WITH,';
	 var result = fieldName.match(/^[a-zA-Z][a-zA-Z0-9_]{0,29}$/); 
	 if(result)
	 {
	 	var includeKeyIndex = key.indexOf(','+fieldName+',');
	 	if(includeKeyIndex>-1){
	 		return false;
	 	}else{
	 		return true;
	 	}
	 }else{
	 	return false;
	 }
}

Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  
        "d+": this.getDate(),  
        "h+": this.getHours(),  
        "m+": this.getMinutes(),  
        "s+": this.getSeconds(),  
        "q+": Math.floor((this.getMonth() + 3) / 3),  
        "S": this.getMilliseconds()  
    };  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}; 

function longToDate(time){
	if(time == null || time == ""){
		return "-";
	}
	
	d = new Date();
    offset =  d.getTimezoneOffset() * 60000  + 8 * 3600000;
    return new Date(parseInt(time*1000)+offset).format("yyyy/MM/dd");    
} 

function longToTime(time){
	if(time == null || time == ""){
		return "-";
	}
	
	d = new Date();
    offset =  d.getTimezoneOffset() * 60000  + 8 * 3600000;
    return new Date(parseInt(time*1000)+offset).format("yyyy/MM/dd hh:mm:ss");    
} 

function exportExcelNew(queryName,titles,columns,sheetName,codeDetail){
 	var url = $ctx + '/omcs/baseExport/exportModel.action?sheetTitle='+sheetName+'&rQueryName='+queryName;
 	$(".serachdiv:first").find("input,select").each(function(){
 		if($(this).val()!=null){
 			url+="&params."+ $(this).attr("name")+"="+ $(this).val();
 		}
 	});

	var tableLength = $('#table1').bootstrapTable('getOptions').totalRows;
	if(tableLength > 5000){
		alert("您需要导出的数据条数过多或过慢，请稍后到附件管理中查看！")	
		$.post(encodeURI(url), {"titles":titles,"columns":columns,"codeDetail":codeDetail});
	}else{
		$.bs.dropLayer.show();
	 	$.post(encodeURI(url), {"titles":titles,"columns":columns,"codeDetail":codeDetail}, function(path) {
	 		$.bs.dropLayer.hide();
	 		var exporurl = $ctx + "/omcs/baseExport/exportExcel.action?path="+path;
			openwindow(exporurl,'','600','600');
	 	});
	}
}

function exportExcelNew2(queryName,titles,columns,sheetTitle,codeDetail,queryTableName){
	var url = $ctx + '/quality/reqst/getMaxMinTime.action?queryTable='+queryTableName;
 	$(".serachdiv:first").find("input,select").each(function(){
 		if($(this).val()!=null){
 			url+="&params."+ $(this).attr("name")+"="+ $(this).val();
 		}
 		
 	});
 	$.post(url).done(function(result) {
		//console.log(result);
		var sheetName=result;
		var url = $ctx + '/omcs/baseExport/exportModel.action?sheetTitle='+sheetTitle+'&rQueryName='+queryName+'&sheetName='+sheetName;
	 	$(".serachdiv:first").find("input,select").each(function(){
	 		url+="&params."+ $(this).attr("name")+"="+ $(this).val();
	 	});
	 	console.log(url);
		var tableLength = $('#table1').bootstrapTable('getOptions').totalRows;
		if(tableLength > 5000){
			alert("您需要导出的数据条数过多或过慢，请稍后到附件管理中查看！")	
			$.post(encodeURI(url), {"titles":titles,"columns":columns,"codeDetail":codeDetail});
		}else{
			$.bs.dropLayer.show();
		 	$.post(encodeURI(url), {"titles":titles,"columns":columns,"codeDetail":codeDetail}, function(path) {
		 		$.bs.dropLayer.hide();
		 		var exporurl = $ctx + "/omcs/baseExport/exportExcel.action?path="+path;
				openwindow(exporurl,'','600','600');
		 	});
		}
	});
}

function Map(){ 
	  this.elements = new Array(); 
	   
	  //获取Map元素个数 
	  this.size = function() { 
	    return this.elements.length; 
	  }, 
	   
	  //判断Map是否为空 
	  this.isEmpty = function() { 
	    return (this.elements.length < 1); 
	  }, 
	   
	  //删除Map所有元素 
	  this.clear = function() { 
	    this.elements = new Array(); 
	  }, 
	   
	  //向Map中增加元素（key, value)  
	  this.put = function(_key, _value) { 
	    if (this.containsKey(_key) == true) { 
	      if(this.containsValue(_value)){ 
	        if(this.remove(_key) == true){ 
	          this.elements.push( { 
	            key : _key, 
	            value : _value 
	          }); 
	        } 
	      }else{ 
	        this.elements.push( { 
	          key : _key, 
	          value : _value 
	        }); 
	      } 
	    } else { 
	      this.elements.push( { 
	        key : _key, 
	        value : _value 
	      }); 
	    } 
	  }, 
	   
	  //删除指定key的元素，成功返回true，失败返回false 
	  this.remove = function(_key) { 
	    var bln = false; 
	    try {  
	      for (i = 0; i < this.elements.length; i++) {  
	        if (this.elements[i].key == _key){ 
	          this.elements.splice(i, 1); 
	          return true; 
	        } 
	      } 
	    }catch(e){ 
	      bln = false;  
	    } 
	    return bln; 
	  }, 
	   
	  //获取指定key的元素值value，失败返回null 
	  this.get = function(_key) { 
	    try{  
	      for (i = 0; i < this.elements.length; i++) { 
	        if (this.elements[i].key == _key) { 
	          return this.elements[i].value; 
	        } 
	      } 
	    }catch(e) { 
	      return null;  
	    } 
	  }, 
	   
	  //获取指定索引的元素（使用element.key，element.value获取key和value），失败返回null 
	  this.element = function(_index) { 
	    if (_index < 0 || _index >= this.elements.length){ 
	      return null; 
	    } 
	    return this.elements[_index]; 
	  }, 
	   
	  //判断Map中是否含有指定key的元素 
	  this.containsKey = function(_key) { 
	    var bln = false; 
	    try { 
	      for (i = 0; i < this.elements.length; i++) {  
	        if (this.elements[i].key == _key){ 
	          bln = true; 
	        } 
	      } 
	    }catch(e) { 
	      bln = false;  
	    } 
	    return bln; 
	  }, 
	    
	  //判断Map中是否含有指定value的元素 
	  this.containsValue = function(_value) { 
	    var bln = false; 
	    try { 
	      for (i = 0; i < this.elements.length; i++) {  
	        if (this.elements[i].value == _value){ 
	          bln = true; 
	        } 
	      } 
	    }catch(e) { 
	      bln = false;  
	    } 
	    return bln; 
	  }, 
	   
	  //获取Map中所有key的数组（array） 
	  this.keys = function() { 
	    var arr = new Array(); 
	    for (i = 0; i < this.elements.length; i++) {  
	      arr.push(this.elements[i].key); 
	    } 
	    return arr; 
	  }, 
	  
	  //获取Map中所有value的数组（array） 
	  this.values = function() { 
	    var arr = new Array(); 
	    for (i = 0; i < this.elements.length; i++) {  
	      arr.push(this.elements[i].value); 
	    } 
	    return arr; 
	  }; 
	}
