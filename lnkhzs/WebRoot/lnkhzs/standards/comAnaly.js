
$(document).ready(function() {
	var store = "A";
	$("label").on("click",function(){

		if (this.innerText == "年") {
			$(".radioY").addClass("selected");
			$(".radioM").removeClass("selected");
			if (store == "B") {
				$("#calendar").val("");
			}
			store = "A";
		} else if(this.innerText == "月") {
			$(".radioY").removeClass("selected");
			$(".radioM").addClass("selected");
			if (store == "A") {
				$("#calendar").val("");
			}
			store = "B";
		}
	})

	if(!!_USER_CITY_NAME && typeof(_USER_CITY_NAME)!="undefined" && _USER_CITY_NAME!=0){
		$("#selectItem").val(_USER_CITY_NAME);
		$("#selectItem").attr("disabled",true);
	}
	initSelEvent();
	
	initCharts();
	
});

function initSelEvent() {
	$("#selectItem").change(function(){
		initCharts();
	});
}

function changeDateVal() {
	initCharts();
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function getParams() {

	var cityName = $("#selectItem").find("option:selected").text();
	var dateVal = $("#calendar").val();
	
	var params = "&area_name=" + cityName + "&time=" + dateVal + "&type=宽带";
	return params;
}

function initCharts() {

	var urlParam = getParams();
	
	var url = $ctx + "/standards/fwzl/ajaxGetPiesJson.action?menuId=A&unit=A" + encodeURI(urlParam);
	$.post(url).done(function(data) {
		
		var len = data.length;
		var less = 14 - len;
		$("#chartStore").html("");
		for (var h = 0; h < len; h++) {
			var id = data[h].id;
			var ht = "<div id='" + id + "' class='myECharts mychartDiv'>" +
					"</div>";
			$("#chartStore").append(ht);
		}
		
		for (var k = 0; k < less;k++) {
			var et =  "<div id='addPic'  class='mychartDiv2' >"
					+ "    <img class='addPicThree' src='../style/image/addPic.png' onclick='addInd()'></img>"
					+ "    <p class='midPicSpan2' > 添加 </p>"
					+ "</div>"
			$("#chartStore").append(et);
		}
		
		var dataArr = new Array();
		var dataunit = new Array();
		var dataArrAll = new Array();
		var titleArr = new Array();
		var dataThreshold = new Array();
		for (var i = 0; i < len; i++) {
			var value = data[i].value==''?0:data[i].value;
			var name = data[i].name;
			var id = data[i].id;
			var type = data[i].type;
			var unit = data[i].unit;
			var threshold = data[i].threshold==''?0:data[i].threshold;
			
			if (type == "量") {
				dataArr[i] = value;
//				dataArrAll[i] = 0;
				if (value == 0) {
					dataArr[i]=1;
//					dataArrAll[i]=0;
				}
			} else if (type == "率") { // "客户投诉量" == titleArr[j]
				dataArr[i] = value;
//				dataArrAll[i] = 0;
//				dataArr[i] = value + "%";
				//dataArrAll[i] = (100-value) + "%";
			} else {
				dataArr[i] = value;
				dataunit[i] = unit;
				dataThreshold[i] = threshold;
//				dataArrAll[i] = 0;
			}
			
			titleArr[i] = name;
		}
		
		var impMap = {show: true, fontSize: '14', fontWeight: 'bold'};
		var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)',
			'#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
		for(var j=0; j<len; j++){
			var id = data[j].id;
//			var sqlnamedata = data[j].sqlname;
			var type = data[j].type;
			var value = data[j].value==''?0:data[j].value;
			var echertPie = echarts.init(document.getElementById(id));
			echertPie.clear();
			var coArr = [colorArr[j], '#e9eced'];
			var dataPieMap = {};
//			var dataPieMapSpa = {};

			var option_pie = option_pieStr;
			
			var dataTemp =new Array();
			dataPieMap.value = dataArr[j];
			dataPieMap.names = titleArr[j];
			dataPieMap.name = dataArr[j]+dataunit[j];
			//预警值
			/*if(dataArr[j]>dataThreshold[j]){
				option_pie.title.textStyle.color = "rgb(178,34,34)";
			}else{
				option_pie.title.textStyle.color = "rgb(122, 122, 122)";
			}*/

//			dataPieMap.sqlname = sqlnamedata;
			
//			dataPieMap.name = titles[i];
			dataPieMap.label = impMap;
//			dataPieMapSpa.value = dataArrAll[j];
			dataTemp[0]=dataPieMap;
//			dataTemp[1]=dataPieMapSpa;

			option_pie.color=coArr;
			
			option_pie.series.data=dataTemp;
			
			option_pie.title.text=titleArr[j];
//			if ("客户投诉量" == titleArr[j]) {
////				option_pie.series.label.normal.formatter=" {c} \n ———— \n {d}% ";
//				option_pie.series.label.normal.formatter=" {c}";
//			} else {
//				option_pie.series.label.normal.formatter=" {c}";
//			}  
//
			if (type == "量") {
				if (value == 0) {

					option_pie.series.label.normal.formatter=" 0";
				}
			} else if (type == "率") {
				option_pie.series.label.normal.formatter=" {c}%";
			}
			
			option_pie.aria.description = id;
			
			echertPie.setOption(option_pie);
//			
			echertPie.on('click', function (param){
	            //console.log(param);
				var childUrl=$ctx + "/lnkhzs/standards/comLine.jsp?name="+param.data.names+"&type='宽带'"+"&params="+urlParam;
	            openwindow2(childUrl,'',1200, 600);
	        });
//			if ("客户投诉量" == titleArr[j] || "客户投诉率" == titleArr[j]) {
//				echertPie.on('click', function (param){  
//					// var name=param.name;  
//		            var childUrl=$ctx + "/lnkhzs/propage/comCust.jsp";
//		    		openwindow2(childUrl,'',1200, 600);
//		        });
//			} else {
//				
//				echertPie.on('click', function (e){  
//					// var childUrl=$ctx + "/lnkhzs/propage/comTable.jsp";
//					var paramDe = getParamForDetail();
//					var sqlname = e.data.sqlname;
//		            var childUrl=$ctx + "/tsgd/tsgd/comTable.action?sqlName=" + sqlname.split(".")[0] + "_CLD." + sqlname.split(".")[1] + paramDe; 
//		    		openwindow2(childUrl,'',1200, 600);
//		        });
//			}
		}
		
	});
}

function getParamForDetail() {

	var params = "";
	var cityName = $("#selectItem").find("option:selected").text();
	var dateVal = $("#calendar").val();
	if(cityName != undefined && cityName != '') {
		params = params + "&area_name=" + cityName;
	}
	if (dateVal != undefined && dateVal != '') {
		params = params + "&year=" + dateVal;
	}
	return params;
}


function changeTime() {
	var dateFormat='';
	if ($(".radioY").hasClass("selected")) {
		dateFormat='yyyy';
	} else if ($(".radioM").hasClass("selected")) {
		dateFormat='yyyy-MM';
	}
	return dateFormat;
}

function addInd(){
	var urlParam = getParams();
	openwindow($ctx + "/lnkhzs/standards/indAdd.jsp?menuId=B"+encodeURI(urlParam));
}

function deleteInd(id) {
	if (confirm("确认要取消当前指标？")) {
		$.post($ctx + "/standards/fwzl/deleteInd.action?id=" + id).done(function(data) {
			if (data == "1") {
				$("#chartStore").html("");
				initCharts();
			} else {
				alert("关闭失败");
			}
		});
	}
}

var option_pieStr = {
		title: {
	        text: '',
	        bottom: '5px',
	        show: true,
	        left: 'center',
	        textStyle: {
	        	color: 'rgb(122, 122, 122)',
	        	//color: 'rgb(32, 255, 1)',
	        	fontStyle: 'normal',
	        	fontWeight: 'normal',
	        	fontFamily: 'Microsoft YaHei',
	        	fontSize: 12
	        }
		},
		/*legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},*/
		grid:{
			left:'8%',
			right:'5%',
			top:'8%',
			bottom:'12%'
		},
	    series: {
	    	type:'pie',
            radius: ['49%', '68%'],
            center: ['50%', '40%'],
            avoidLabelOverlap: false,
	        label: {
                normal: {
                    show: true,
                    position: 'center',
                    //formatter: " {c}" //{@[1]}
                }
	        },
            emphasis: {
                label: {
                    show: true,
                    
                }
            }
	    },
	    toolbox: {  
	        feature: {  
	            myTool1: {  
	                show: true,  
	                title: '关闭',  
	                icon: 'image://../style/image/x.png',  
	                itemSize:11,
	                onclick: function (e){  
	                	var id = e.option.aria.description;
//	                	$.post($ctx + "/tsgd/tsgd/deleteInd.action?id=" + id).done(function(data) {
//	                		
//	                	});
	                	deleteInd(id);
	                }  
	            }
	        }
	    },
	    aria: {
	        show: false
	    }
	    
	};
function openwindow2(url,name,iWidth,iHeight)
{
	var resurl = encodeURI(url); 
	var name=null;
	name = name || new Date().getTime();
	
	var w = $(window).width()-160;
	var h = $(window).height()-81;
	
	iWidth = iWidth || w; 
	iHeight = iHeight || h; 
	var iTop = (window.screen.availHeight-30-iHeight)/2; 
	var iLeft = (window.screen.availWidth-10-iWidth)/2; 
	return window.open(resurl,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
}


function downLoadExel(){
	/*var exporurl = $ctx + "/standards/export/exportExcel.action?path=d:/lnnsgl_attaches3100e96ece0c4345af29019776a11f8c.xlsx";
	openwindow(exporurl,'','600','600');*/
	var dateVal = $("#calendar").val();
	if(dateVal.length<5){
		alert("导出只能按月导出，请选择导出月份");
	}else{
		var urlParam = getParams();
		var url = $ctx + '/standards/export/exportModel.action?a=a'+encodeURI(urlParam);
		$.post(url).done(function(result) {
			/*console.log(result);*/
			var exporurl = $ctx + "/standards/export/exportExcel.action?path="+result;
			openwindow(exporurl,'','600','600');
		});
	}
	
}