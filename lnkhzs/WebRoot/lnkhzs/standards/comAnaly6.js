$(document).ready(function() {
	
	//获取当前年份
	var date = new Date().getFullYear();
	$("#calendar").val(date);
	
	//获取当前是当年的第几周
	showWeek(getYearWeek());
	
	initCharts();
});

function getParams() {

	//var cityName = $("#selectItemWeek").find("option:selected").text();
	var dateVal = $("#calendar").val()+""+$("#selectItemWeek").find("option:selected").val();
	var params =  "&time=" + dateVal + "&type=云";
	
	return params;
}

function initCharts() {

	var urlParam = getParams();
	
	var url = $ctx + "/standards/fwzl/ajaxGetPiesJsonYun.action?menuId=A&unit=A" + encodeURI(urlParam);
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
			var et =  "<div id='addPic' class='mychartDiv2' >"
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
		var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)','#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
		for(var j=0; j<len; j++){
			var id = data[j].id;
//			var sqlnamedata = data[j].sqlname;
			var type = data[j].type;
			var value = data[j].value==''?0:data[j].value;
			var echertPie = echarts.init(document.getElementById(id));
			echertPie.clear();
			var coArr = [colorArr[j], '#e9eced'];
			var dataPieMap = {};
			var dataPieMapSpa = {};

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
			dataTemp[1]=dataPieMapSpa;

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
			
			echertPie.on('click', function (param){
				
				var mynames=param.data.names;
				console.log(mynames);
				if(mynames=="投诉处理及时率"||mynames=="投诉回访及时率"||mynames=="投诉首次响应及时率"){
					;
				}else{
					var childUrl=$ctx + "/lnkhzs/standards/comLine5.jsp?name="+param.data.names+"&params="+urlParam;
		            openwindow2(childUrl,'',1200, 600);
				}
				
	        });

		}
		
	});
}


var option_pieStr = {
		title: {
	        text: '',
	        bottom: '5px',
	        show: true,
	        left: 'center',
	        textStyle: {
	        	color: 'rgb(122, 122, 122)',
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




function addInd(){
	var urlParam = getParams();
	openwindow($ctx + "/lnkhzs/standards/indAddYun.jsp?menuId=B"+encodeURI(urlParam));
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
























function showWeek(nowWeek) {
	//console.log(nowWeek);
	var yearHtml = '';
	var ii=0;
	for (var i = 1; i <= 53; i++) {
		if(i<10){
			ii="0"+i;
		}else{
			ii=i;
		}
		if(i==nowWeek){
			yearHtml += '<option selected value ="'+ ii +'" style="background-color:rgb(255, 255, 255);">第'+ i +'周</option>';
		}else{
			yearHtml += '<option value ="'+ ii +'" style="background-color:rgb(255, 255, 255);">第'+ i +'周</option>';
		}
		
	}
	$('#selectItemWeek').append(yearHtml);
}

//修改年份
function changeDateVal() {
	initCharts();
}

//修改周
function changeWeekVal(){
	initCharts();
}

//获取当天为当年第几周
function getYearWeek() {
	var d1 = new Date();
	var d2 = new Date();
	d2.setMonth(0);
	d2.setDate(1);
	var rq = d1-d2;
	var s1 = Math.ceil(rq/(24*60*60*1000));
	var s2 = Math.ceil(s1/7);
	
	//alert("今天是本年第"+s1+"天，第"+s2+"周");//周日做为下周的开始计算
	return s2;
}

;