fn_detail = {
	'click a': function (e, value, row, index) {
		if(row.TABLENAME=='tablearc'){
			openwindow($ctx + '/quality/complaint/arcDetail.action?sWfCmplntsArcDetail.wrkfmShowSwftno='+row.GDLS,'');
		}else if(row.TABLENAME=='tablereqst'){
			openwindow($ctx + '/quality/reqst/reqstDetail.action?sWfCmplntsReqstDetail.srvReqstId='+row.SRV_REQST_ID,'');
		}
   }
}


$(document).ready(function() {
	var store = "A";
	$("label").on("click", function() {

		if (this.innerText == "年") {
			$(".radioY").addClass("selected");
			$(".radioM").removeClass("selected");
			if (store == "B") {
				$("#calendar").val("");
			}
			store = "A";
		} else if (this.innerText == "月") {
			$(".radioY").removeClass("selected");
			$(".radioM").addClass("selected");
			if (store == "A") {
				$("#calendar").val("");
			}
			store = "B";
		}
	})

	if(!!_USER_CITY_NAME && typeof(_USER_CITY_NAME)!="undefined" && _USER_CITY_NAME!=0){
		$("#selectItem").val(_USER_CITY_NAME+"市");
		$("#selectItem").attr("disabled",true);
	}
	
	initSelCon();

	initSelEvent()

	initCharts();
	oncli();
	
	//$("#srvreqsttypefullnm").val("→chunk-0020ab85.c07b6e8b");
	$("#a12").val("→chunk-0020ab85.c07b6e8b");
	$.bs.table.refresh("table0");

});

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

function initSelCon() {
	var url = $ctx + "/tsgd/tsgd/ajaxGetTsfxTree.action"; 
	$.ajaxSettings.async = false;
	$.post(url).done(function(data) {
		var ht = "";
		for (var i = 0; i < data.length; i++) {
			if (data[i].name != "红线") {
				ht = ht + "<option value ='" + data[i].id + "' class='opcss'>" + data[i].name + "</option>";
			}
		}
		$("#selectPop").html("");
		$("#selectPop").append(ht);
	});
	$.ajaxSettings.async = true;
}


function getParams() {
	var iniName = $("#selectPop").find("option:selected").text();
	var cityName = $("#selectItem").find("option:selected").val();
	var dateVal = $("#calendar").val();

	var params = "respDept=" + "" + "&a12=" + iniName + "&area_name=" + cityName + "&time=" + dateVal;
	return params;
}

function initSelEvent() {
	$("#selectPop, #selectItem").change(function() {
		initCharts();
	});
}

function changeDateVal() {
	initCharts();
}


function oncli(){
	var myChartB = echarts.init(document.getElementById('chartB'));
	//饼状图点击点击
	/*myChartB.on('click', function (params) {
		
		opChild(params.data.preName);
		
	});*/
}
//列表联动数据
function oncliTable(params){
	$("#showFullTitle").text(params);
	$("#showFullTitle").css("display","block");
	$("#srvreqsttypefullnm").val(params);
	$("#a12").val(params);
	var seleVal = $("#selectItem").val();
	$("#area_name").val(seleVal);
	var time = $("#calendar").val();
	$("#statis_date").val(time);
	$.bs.table.refresh("table0");
	
	
	var iniName = $("#selectPop").find("option:selected").text();
	var cityName = $("#selectItem").find("option:selected").val();
	var dateVal = $("#calendar").val();
	var param = "respDept=" + "" + "&a12=" + iniName + "&area_name=" + cityName + "&time=" + dateVal+ "&cname=" + params;
	
	var myChartA = echarts.init(document.getElementById('chartA'));
	myChartA.clear();
	var url = $ctx + "/tsgd/tsgd/ajaxGetLDLine.action?" + encodeURI(param);
	$.post(url).done(function(dataArr) {
		var data = dataArr;

		var nameTemp = new Array();
		var valueTemp = new Array();
		for (var i = 0; i < data.length; i++) {
			var value = data[i].value;
			var name = data[i].name;
			nameTemp[i] = name + "月";
			valueTemp[i] = value;
		}
		option_Line.yAxis.name = '投诉量';
		option_Line.xAxis.data = nameTemp;
		option_Line.series[0].areaStyle.color.colorStops[0].color = "#ace2fe";
		option_Line.series[0].color = "#87cbfb";

		option_Line.grid.left = "12%";
		option_Line.grid.right = "12%";
		//		option_Line.aria.description=id;
		option_Line.series[0].data = valueTemp;
		myChartA.setOption(option_Line);

	/* myChart.on('click', function (param){  
	        var parid = myChart.getOption().aria.description;
            var childUrl = $ctx + "/lnkhzs/propage/comCustDetail.jsp?id=" + parid;
    		openwindow2(childUrl,'',1200, 600);
        }); */
	});
	
}

function initCharts() {
	var urlParam = getParams();

	var myChartA = echarts.init(document.getElementById('chartA'));
	myChartA.clear();
	getLineJsonById(myChartA, urlParam, [ "12%", "12%" ], [ '#ace2fe', '#87cbfb' ]);


	var myChartB = echarts.init(document.getElementById('chartB'));
	myChartB.clear();
	getPieJsonById(myChartB, urlParam);
	/*$("#srvreqsttypefullnm").val("→chunk-0020ab85.c07b6e8b");
	$("#a12").val("→chunk-0020ab85.c07b6e8b");
	$.bs.table.refresh("table0");*/
	
	
}

function getPieJsonById(myChart, param) {
	$("#showFullTitle").text("");
	var url = $ctx + "/tsgd/tsgd/ajaxGetTsfxFocus.action?" + encodeURI(param);
	$.post(url).done(function(data) {
		var impMap = {
			show : true,
			fontSize : '14',
			fontWeight : 'bold'
		};
		var coArr = [ '#6cbdf7', '#fdb275', '#f1868d', '#3fcdcf' ];

		var dataTemp = new Array();
		var dataLable = new Array();
		/*var num = data.length > 5 ? 5 : data.length;*/
		var num = data.length > 10 ? 10 : data.length;
		var ht = "<tr class='tr1'>"
			+ "<td style='width:45%'>焦点问题名称</td>"
			+ "<td style='width:20%'>数量</td>"
			+ "<td style='width:20%'>环比</td>"
			+ "<td>操作</td>"
			+ "</tr>";
		if (num < 1) {
			ht = ht + "<tr class='trOther' style='background:rgb(253, 253, 253)'> <td></td><td></td><td></td><td></td></tr>";
		}

		for (var i = 0; i < num; i++) {
			var value = data[i].value; // + "%"
			var preName = data[i].name;
			var than = data[i].than;
			var a = preName.lastIndexOf("→");
			var name = preName.substr(preName.lastIndexOf("→",a-1) + 1, preName.lenght).replace("→全局流转", "");
			
			var dataPieMap = {};
			var status = data[i].status;
			var  id=data[i].id;

			var lablen = name.length > 10 ? 10 : name.length;
			name.substring(0, lablen);
			dataPieMap.value = value;
			//dataPieMap.name = name;
			dataPieMap.name = preName;
			dataPieMap.preName = preName;
			dataTemp[i] = dataPieMap;

			//dataLable[i] = name;
			dataLable[i]=preName;

			if (i % 2 == 1) {

				ht = ht + "<tr class='trOther' style='background:rgb(253, 253, 253)'>"
					+ "<td data-toggle='tooltip' data-placement='top' title='"+preName+"'><a onclick='oncliTable(\""+preName+"\")' class='resend btn-link ' >" + name + "</a></td>"
					+ "<td>" + value + "</td>"
					+ "<td cname='"+preName+"'>" +  "</td>";
				if (status != "已派发") {
					ht = ht + "<td class='tbtd'>" + "<a title='派发' onclick='resend(\"" + preName + "\",\"" + id + "\")' class='resend btn-link fontsize14' >派发</a>" + "</td>"
				} else {
					ht = ht + "<td class='tbtd'></td>"
				}
				ht = ht + "</tr>";
			} else {
				/*ht = ht + "<tr class='trOther'>" 
					+ "<td>" + "<a onclick='opChild(\""+preName+"\")' class='resend btn-link fontsize14' >" + name + "</a>" + "</td>" 
					+ "<td>" + value + "</td>" 
					+ "<td class='tbtd'>" + "<a title='派发' onclick='resend(\""+name+"\",\""+value+"\")'  class='resend btn-link fontsize14' >派发</a>" + "</td>" 
					+ "</tr>";*/
				ht = ht + "<tr class='trOther' >"
					+ "<td  data-toggle='tooltip' data-placement='top' title='"+preName+"'><a onclick='oncliTable(\""+preName+"\")' class='resend btn-link ' >" + name + "</a></td>"
					+ "<td>" + value + "</td>"
					+ "<td cname='"+preName+"'>" +  "</td>";
				if (status != "已派发") {
					ht = ht + "<td class='tbtd'>" + "<a title='派发' onclick='resend(\"" + preName + "\",\"" + id + "\")' class='resend btn-link fontsize14' >派发</a>" + "</td>"
				} else {
					ht = ht + "<td class='tbtd'></td>"
				}
				ht = ht + "</tr>";
			}

		}
		$(".tableMid").html("");
		$(".tableMid").append(ht);
		

		option_pie.color = coArr;

		option_pie.series.data = dataTemp;
		option_pie.title.text = "投诉占比";
		option_pie.series.name = "投诉占比";

		option_pie.legend.data = dataLable;
		myChart.setOption(option_pie);
		
		
		for(var nn=1;nn<$(".tableMid").find("tr").length;nn++){
			var cname=$(".tableMid").find("tr").eq(nn).find("td").eq(2).attr("cname");
			if(cname==undefined)
				continue;
			var url = $ctx + "/tsgd/tsgd/ajaxGetTshbFocus.action?" + encodeURI(param+ "&cname=" + cname+"&type="+nn);
			$.post(url).done(function(data1) {
				$(".tableMid").find("tr").eq(data1[0].trnum).find("td").eq(2).text(data1[0].than);
			});
		}
	});
}


function resend(name, value) {
	openwindow($ctx + '/lnkhzs/propage/resendForm.jsp?name=' + name+"&id="+value, '');

}




function opChild(name){
	openwindow2($ctx + '/lnkhzs/propage/toChild.jsp?name=' + name, '', 1000, 518);
}

function getLineJsonById(myChart, param, placeArr, colorArr) {
	var url = $ctx + "/tsgd/tsgd/ajaxGetTsfxAmount.action?" + encodeURI(param);
	$.post(url).done(function(dataArr) {
		var data = dataArr;

		var nameTemp = new Array();
		var valueTemp = new Array();
		for (var i = 0; i < data.length; i++) {
			var value = data[i].value;
			var name = data[i].name;
			nameTemp[i] = name.substring(5,7) + "月";
			valueTemp[i] = value;
		}
		option_Line.yAxis.name = '投诉量';
		option_Line.xAxis.data = nameTemp;
		option_Line.series[0].areaStyle.color.colorStops[0].color = colorArr[0];
		option_Line.series[0].color = colorArr[1];

		option_Line.grid.left = placeArr[0];
		option_Line.grid.right = placeArr[1];
		//		option_Line.aria.description=id;
		option_Line.series[0].data = valueTemp;
		myChart.setOption(option_Line);

	/* myChart.on('click', function (param){  
	        var parid = myChart.getOption().aria.description;
            var childUrl = $ctx + "/lnkhzs/propage/comCustDetail.jsp?id=" + parid;
    		openwindow2(childUrl,'',1200, 600);
        }); */
	});
}

function changeTime() {
	var dateFormat = '';
	if ($(".radioY").hasClass("selected")) {
		dateFormat = 'yyyy';
	} else if ($(".radioM").hasClass("selected")) {
		dateFormat = 'yyyy-MM';
	}
	return dateFormat;
}




var option_pie = {
	title : {
		text : '',
		bottom : '5px',
		show : true,
		top : '5%',
		left : '7%',
		textStyle : {
			color : 'rgb(122, 122, 122)',
			fontFamily : 'Microsoft YaHei',
			fontSize : '14',
			color : '#333',
			fontWeight : 600
		}
	},
	legend : {
		formatter : function(name) {
			var a = name.lastIndexOf("→");
			var name1 = name.substr(name.lastIndexOf("→",a-1) + 1, name.lenght).replace("→全局流转", "");
			var lablen = name1.length > 10 ? 10 : name1.length;
			var labname = name1.substring(0, lablen);
			return labname;
		},
		textStyle : {
			color : '#000'
		},
		top : 'bottom',
		left : 'center',
		itemHeight : 9,
		itemWidth : 26,
		icon : 'rect'
	},
	grid : {
		left : '8%',
		right : '5%',
		top : '8%',
		bottom : '12%'
	},
	tooltip : {
		trigger : 'item',
		formatter : "{a} <br/>{b}: {c} ({d}%)"
	},
	series : {
		type : 'pie',
		radius : [ '39%', '65%' ],
		center : [ '50%', '40%' ],
		avoidLabelOverlap : false,
		label : {
			normal : {
				show : false,
				position : 'center'
			},
			emphasis : {
				show : true,
				textStyle : {
					fontSize : '15',
					fontWeight : 'bold'
				},
				formatter : "{c} \n ———— \n {d}%"
			}
		},
		labelLine : {
			normal : {
				show : false
			}
		}
	}
};
/*折线图*/
var option_Line = {
		legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},
		grid:{
			/* left:'5%',
			right:'5%', */
			top:'18%',
			bottom:'15%'
		},
	    tooltip : {
	        /*trigger: 'axis' */
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        axisLabel:{
	    		interval: 0
	    	}
	    },
	    yAxis: {
	        type: 'value',
	        nameTextStyle: {
	        	fontSize : '14',
	        	color : '#333',
	        	fontWeight: 600
	        },
	        splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgb(255,255,255)','rgba(253,253,253)']
                }
            }
	    },
	    series: [{
	    	label: {
                normal: {
                    show: true,
                }
            },
	        type: 'line',
	        smooth: true,
	        areaStyle: {
		        color: {
				    type: 'linear',
				    x: 0,
				    y: 0,
				    x2: 0,
				    y2: 1,
				    colorStops: [{
				        offset: 0, color: '#87cbfb' // 0% 处的颜色
				    }, {
				        offset: 1, color: '#eee' // 100% 处的颜色
				    }],
				    global: false // 缺省为 false
				}
	        },
	        color: '#87cbfb'
	    }],
	    aria: {
	        show: false
	    }
	};


function openwindow2(url, name, iWidth, iHeight) {
	var resurl = encodeURI(url);
	var name = null;
	name = name || new Date().getTime();

	var w = $(window).width() - 160;
	var h = $(window).height() - 81;

	iWidth = iWidth || w;
	iHeight = iHeight || h;
	var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
	return window.open(resurl, name, 'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes');
}