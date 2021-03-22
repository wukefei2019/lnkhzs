var chartLen  = 0;
function initCharts() {

	// 广告滚动
	initNotice();

	// 溯源问题总量
	//initSY();
	initSYNew();
 
	// 客户之声
	initKHZS();

	// 服务质量标准报表
	initFWZL();

	// 重点指标情况
	initZZZB();

	// 溯源问题总量
	initZHDY();

	// 标签 督办
	initBQDB('移动业务投诉量');
	
	//跳转
	$("#sywtzl").click(function(){
		window.location.href=$ctx + "/lnkhzs/trace/traceSourceList.jsp";
	});
	$("#zdzbqk").click(function(){
		window.location.href=$ctx + "/lnkhzs/propage/comAnaly.jsp";
	});
	$("#zhdyhfl").click(function(){
		window.location.href=$ctx + "/lnkhzs/survey/zhdywj.jsp";
	});
	$("#fwzlbzbb").click(function(){
		window.location.href=$ctx + "/lnkhzs/standards/frame.jsp";
	});
	$("#khzs").click(function(){
		//window.location.href=$ctx + "/lnkhzs/khzs/jdz.jsp";
		window.location.href=$ctx + "/lnkhzs/khzs/ygjy.jsp";
	});
	$("#bqdb").click(function(){
		window.location.href=$ctx + "/lnkhzs/issueTrack/trackList.jsp";
	});
	$("#qstsl").click(function(){
		window.location.href=$ctx + "/lnkhzs/propage/popService.jsp";
	});
	
	
	
	
	
}



function initNotice() {
	// 广告滚动
	var width = 0;
	$("#noticeInfo .aaa").each(function() {
        width += parseInt($(this).width());
    });
	$("#noticeInfoPage").width("1000px");
	
	/**startmarquee(一次滚动高度,速度,停留时间);**/
	startmarquee(188, 20, 0);
}

function initSY() {
	// 溯源问题总量
	var myChartSy = echarts.init(document.getElementById('chartSy'));
	myChartSy.clear();
	setSYWTZL(myChartSy);
	//myChartSy.setOption(optionSy);
}

function initSYNew(){
	var myChartA = echarts.init(document.getElementById('chartA'));
	myChartA.clear();
	getPieJsonById(myChartA);
}

function getPieJsonById(myChart) {
	
	//var url = $ctx + "/tsgd/tsgd/ajaxGetTsfxFocus.action";
	var url = $ctx + "/homePage/dataSource/ajaxGetSourceAmountNew.action";
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

		for (var i = 0; i < num; i++) {
			var value = data[i].value1; // + "%"
			var value2 = data[i].value2; // + "%"
			var preName = data[i].name
			var than = data[i].value3;
			var name = preName;
			
			var dataPieMap = {};
			//var status = data[i].status;
			//var  id=data[i].id;

			//var lablen = name.length > 5 ? 5 : name.length;
			//var lablen = name.length > 10 ? 10 : name.length;
			if( name.length > 5){
				name=name.substring(0, 5)+"...";
			}
			//name=name.substring(0, lablen)+"...";
			dataPieMap.value = value;
			dataPieMap.value2 = value2;
			dataPieMap.name = name;
			//dataPieMap.name = preName;
			dataPieMap.preName = preName;
			dataTemp[i] = dataPieMap;
			dataLable[i] = name;
			//dataLable[i]=preName;

		}
		
		option_pie_chartA.color = coArr;

		option_pie_chartA.series.data = dataTemp;
		//option_pie_chartA.title.text = "溯源问题占比";
		option_pie_chartA.series.name = "溯源问题占比";

		option_pie_chartA.legend.data = dataLable;
		myChart.setOption(option_pie_chartA);
	});
}



var option_pie_chartA = {
		tooltip : {
			trigger : 'item',
			formatter: function(v) {
                //return v.data.name + '：<br/>问题量：'+v.data.value+'<br/>验收量：'+v.data.value2;
				return v.data.preName + '：<br/>问题量：'+v.data.value+'<br/>验收量：'+v.data.value2;
            }
		},
		legend: {
	        orient: 'vertical',
	        x: 'right',
	        data:[],
	        itemHeight : 8,
			itemWidth : 8,
			textStyle : {
				fontSize : '10',
				fontWeight : 100
			},
			top:'5%'
	    },
		series: 
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['70%', '95%'],
	            center : [ '25%', '50%' ],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '10',
	                        fontWeight: 'bold'
	                    },
	                    formatter : "{c} \n ———— \n {d}%"
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[]
	        }
	    
	};

function initKHZS() {
	// 客户之声
	var url = $ctx + "/homePage/dataSource/ajaxGetKHZSAmount.action";
	$.post(url).done(function(dataArr) {
		var data = dataArr;
		var nameTemp = new Array();
		var valueTemp = new Array();
		var valueTemp1 = new Array();
		for (var i = 0; i < data.length; i++) {
			if(data[i].name=="员工建议库总量")
				$("#khjy").html(data[i].value);
			if(data[i].name=="投诉案例库总量")
				$("#khal").html(data[i].value);
			if(data[i].name=="金点子例库总量")
				$("#khjdz").html(data[i].value);
		}
	});
	
}

function initFWZL() {
	// 服务质量标准报表
	var hta="";
	hta+='<span style="font-size: 15px;padding-left: 75px;">装移机预约时长</span>';
	hta+='<span style="float: right;font-size: 15px;">0.32</span>'
	$(".fContentA").append(hta);
	
	var htb="";
	htb+='<span style="font-size: 15px;padding-left: 75px;">魔百和启动成功率</span>';
	htb+='<span style="float: right;font-size: 15px;">99.8297</span>'
	$(".fContentB").append(htb);
	
	var htc="";
	htc+='<span style="font-size: 15px;padding-left: 75px;">网页打开成功率</span>';
	htc+='<span style="float: right;font-size: 15px;">99.01</span>'
	$(".fContentC").append(htc);
	
	var htd="";
	htd+='<span style="font-size: 15px;padding-left: 75px;">播放响应时长</span>';
	htd+='<span style="float: right;font-size: 15px;">99.991</span>'
	$(".fContentD").append(htd);
	
	var hte="";
	hte+='<span style="font-size: 15px;padding-left: 75px;">投诉回访及时率</span>';
	hte+='<span style="float: right;font-size: 15px;">100</span>'
	$(".fContentE").append(hte);
	
	resetSize();
}

function initZZZB() {
	
	var date = new Date();
	var myyear=date.getFullYear();
	var mymon=date.getMonth()+1;
	var atime=myyear+"-"+mymon;
	if(mymon<10)
		atime=myyear+"-0"+mymon;
	

	
	var dataa=[{"id": "1","name": "客户投诉量","sql": "SQL_IND_1.query","childid": "1_CLD","type": "量","vieworder": "1"},
		{"id": "9","name": "移动业务投诉量","sql": "SQL_IND_9.query","childid": "9_CLD","type": "量","vieworder": "2"},
		{"id": "23","name": "家庭宽带投诉量","sql": "SQL_IND_23.query","childid": "23_CLD","type": "量","vieworder": "3"},
		{"id": "39","name": "集团业务投诉量","sql": "SQL_IND_39.query","childid": "39_CLD","type": "量","vieworder": "4"},
		{"id": "33","name": "增值业务投诉量","sql": "SQL_IND_33.query","childid": "33_CLD","type": "量","vieworder": "5"}];
	
	chartLen = dataa.length > 5 ? 5 : dataa.length;
	
/*	var width = $("#zdCharts")[0].getBoundingClientRect().width - 1;
	var height = $("#zdCharts")[0].getBoundingClientRect().height - 1;
	var size = width/chartLen > height ? height : width;
	var margin = (width - size*chartLen) / (chartLen*2);*/
	var map = new Map();
	$("#zdCharts").html("");
	
	
	/*var datab=[{"id": "1","sqlname": "SQL_IND_1.query","name": "客户投诉量","value": "201226","childid": "1_CLD","vieworder": "1","type": "量"},
		{"id": "9","sqlname": "SQL_IND_9.query","name": "移动业务投诉量","value": "15000","childid": "9_CLD","vieworder": "2","type": "量"},	
		{"id": "23","sqlname": "SQL_IND_23.query","name": "家庭宽带投诉量","value": "57024","childid": "23_CLD","vieworder": "3","type": "量"},
		{"id": "39","sqlname": "SQL_IND_39.query","name": "集团业务投诉量","value": "2516","childid": "39_CLD","vieworder": "4","type": "量"},
		{"id": "33","sqlname": "SQL_IND_33.query","name": "增值业务投诉量","value": "15000","childid": "33_CLD","vieworder": "5","type": "量"}
		];*/
	
	
	for (var q = 0; q < chartLen; q++) {
		
		var id = dataa[q].id;
		var ht = "<div id='" + id + "' class='myECharts mychartDiv horizontal-div' style= 'width:18%;height:100%;margin-right:10px'>" + 
				"<div class='addDiv'><img src='../style/image/loading_0.gif' class='add' style=' width: 30px; margin-top: calc(50% - 15px);'/></div></div>";
		$("#zdCharts").append(ht);
		//setChart(datab[q]);
		
		var url = encodeURI($ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=C&id=" + dataa[q].id+"&time="+atime);
		
		
		$.post(url).done(function(data) {
			setChart(data);
			
		});
	}
	
	
	// 重点指标情况
	/*var urla = $ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=D";
	$.post(urla).done(function(dataa) {
		chartLen = dataa.length > 4 ? 4 : dataa.length;
		
		var width = $("#zdCharts")[0].getBoundingClientRect().width - 1;
		var height = $("#zdCharts")[0].getBoundingClientRect().height - 1;
		var size = width/chartLen > height ? height : width;
		var margin = (width - size*chartLen) / (chartLen*2);
		var map = new Map();
		$("#zdCharts").html("");
		
		for (var q = 0; q < chartLen; q++) {
			
			var id = dataa[q].id;
			var ht = "<div id='" + id + "' class='myECharts mychartDiv horizontal-div' style= 'width:" + size + "px;height:" + size + "px;margin-left:" + margin + "px;margin-right:" + margin + "px'>" + 
					"<div class='addDiv'><img src='../style/image/loading_0.gif' class='add' style=' width: 30px; margin-top: calc(50% - 15px);'/></div></div>";
			$("#zdCharts").append(ht);
			
			var url = encodeURI($ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=C&id=" + dataa[q].id+"&time="+atime);
			
			
			$.post(url).done(function(data) {
				setChart(data);
			});
		}
		
	});*/
}

function initZHDY() {
	// 智慧调研回复量
	var myChartZh = echarts.init(document.getElementById('chartZhdy'));
	myChartZh.clear();
	setZHDY(myChartZh);
	//myChartZh.setOption(optionZh);
}

function initBQDB(att) {
	
	//标签
	/*var result=[{"title": "移动业务投诉量",
		"name": "移动业务→网络质量→手机上网（4G）→全局流转→功能使用→有信号全部网站、游戏、视频、APP等软件上网慢、卡顿→全局流转",
		"value": "2833"},
		{"title": "移动业务投诉量",
			"name": "移动业务→基础服务→流量套餐→全局流转→费用质疑→未使用产生费用→全局流转",
			"value": "4097"},
			{"title": "移动业务投诉量",
				"name": "移动业务→业务营销→本省业务营销→全局流转→业务规则→本省问题→全局流转",
				"value": "5250"},
				{"title": "移动业务投诉量",
					"name": "移动业务→网络质量→短彩信→全局流转→功能使用→短彩信不能接收→全局流转",
					"value": "6372"	
				},
				{"title": "移动业务投诉量",
					"name": "移动业务→基础服务→其他→信息安全→功能使用→其他→短信功能被限",
					"value": "7078"	
				}];
	initPie_BQ(result);*/
	/*if(att=="移动业务投诉量"){
		var result=[{"title": "移动业务投诉量",
			"name": "移动业务→网络质量→手机上网（4G）→全局流转→功能使用→有信号全部网站、游戏、视频、APP等软件上网慢、卡顿→全局流转",
			"value": "2833"},
			{"title": "移动业务投诉量",
				"name": "移动业务→基础服务→流量套餐→全局流转→费用质疑→未使用产生费用→全局流转",
				"value": "4097"},
				{"title": "移动业务投诉量",
					"name": "移动业务→业务营销→本省业务营销→全局流转→业务规则→本省问题→全局流转",
					"value": "5250"},
					{"title": "移动业务投诉量",
						"name": "移动业务→网络质量→短彩信→全局流转→功能使用→短彩信不能接收→全局流转",
						"value": "6372"	
					},
					{"title": "移动业务投诉量",
						"name": "移动业务→基础服务→其他→信息安全→功能使用→其他→短信功能被限",
						"value": "7078"	
					}];
		initPie_BQ(result);
	}else if(att=="家庭业务投诉量"){
		var result=[{"title": "家庭业务投诉量",
			"name": "家庭业务→网络质量→家庭宽带→全局流转→功能使用→网络连接掉线→全局流转",
			"value": "49"},
			{"title": "家庭业务投诉量",
				"name": "家庭业务→产品质量→互联网电视→全局流转→功能使用→栏目点播、电视看点、电视[直播]无法观看→全局流转",
				"value": "73"},
				{"title": "家庭业务投诉量",
					"name": "家庭业务→网络质量→家庭宽带→全局流转→功能使用→所有网页速度慢→全局流转",
					"value": "78"},
					{"title": "家庭业务投诉量",
						"name": "家庭业务→网络质量→家庭宽带→全局流转→功能使用→LOS红灯，无法上网→全局流转",
						"value": "108"	
					},
					{"title": "家庭业务投诉量",
						"name": "家庭业务→网络质量→家庭宽带→全局流转→功能使用→网络无法连接→全局流转",
						"value": "986"	
					}];
		initPie_BQ(result);
	}else if(att=="增值业务投诉量"){
		var result=[{"title": "增值业务投诉量",
			"name": "增值业务→业务营销→MobileMarket→MobileMarket→费用质疑→信息费/流量费扣费疑问→全局流转",
			"value": "8"},
			{"title": "增值业务投诉量",
				"name": "增值业务→业务营销→梦网短、彩信→全局流转→费用质疑→账单不清晰→全局流转",
				"value": "9"},
				{"title": "增值业务投诉量",
					"name": "增值业务→业务营销→MobileMarket→MobileMarket→费用质疑→用户误操作产生扣费→全局流转",
					"value": "10"},
					{"title": "增值业务投诉量",
						"name": "增值业务→业务营销→梦网短、彩信→全局流转→费用质疑→没有订购关系，但被扣费→全局流转",
						"value": "14"	
					},
					{"title": "增值业务投诉量",
						"name": "增值业务→业务营销→MobileMarket→MobileMarket→费用质疑→计费有误→全局流转",
						"value": "17"	
					}];
		initPie_BQ(result);
	}else if(att=="集团业务投诉量"){
		var result=[{"title": "集团业务投诉量",
			"name": "集团业务→网络质量→专线专网→互联网专线→功能使用→专线中断→全局流转",
			"value": "4"},
			{"title": "集团业务投诉量",
				"name": "集团业务→产品质量→和校园→校讯通→功能使用→无法使用→不能正常使用",
				"value": "5"},
				{"title": "集团业务投诉量",
					"name": "集团业务→服务触点→自有营业厅→沈阳→售后服务→维修质量→维修质量差/未修好",
					"value": "16"},
					{"title": "集团业务投诉量",
						"name": "集团业务→网络质量→集团V网→全局流转→功能使用→短号不能接通→全局流转",
						"value": "43"	
					},
					{"title": "集团业务投诉量",
						"name": "集团业务→业务营销→集团V网→全局流转→办理规范→办理不成功→全局流转",
						"value": "54"	
					}];
		initPie_BQ(result);
	}*/
	var url = encodeURI($ctx + "/homePage/dataSource/ajaxGetTagTOP5Amount.action?title="+att);
	$.post(url).done(function(result) {
		initPie_BQ(result);
	});
	
	//督办
	var url = $ctx + "/homePage/dataSource/ajaxGetResendList.action";
	$.post(url).done(function(result) {
		html = [];
		var length = result.length < 5 ? result.length : 5;
		for (var i = 0; i < length; i++) {
			/*html.push('<tr><td align="center">' + (i + 1) + '</td><td align="center" style="cellStyle:formatTableUnit; formatter:paramsMatter"><span id="pop">' + result[i].LABELCATEGORY + '</span></td></tr>');*/
			html.push('<li class="list-group-item" style="height: calc(20% - 1px);padding: 5% 10px 2% 10px;" data-toggle="tooltip" data-placement="top" title="' + result[i].LABELCATEGORY + '"><span class="badge" style="float:left">' + (i + 1) + '</span>' + result[i].LABELCATEGORY.substring(0, 15) + '</li>');
		}
		$('#ranking2').html(html.join(""));
	});
}

var myChartB =null;
//标签柱形图赋值111
function initPie_BQ(data) {
	var nameTemps = [];
	var indexTemp = new Array();
	var valueTemp = new Array();
	var nameTemp = new Array();
	//var valueTemps = new Array();
	if (myChartB != null && myChartB != "" && myChartB != undefined) {
		 myChartB.dispose();
	    }
	  myChartB = echarts.init(document.getElementById('chartB'));
		myChartB.clear();
	
	for (var i = 0; i < data.length; i++) {
		var index = i+1;
		var value = data[i].value;
		var name = data[i].name;
		indexTemp[i] = index;
		valueTemp[i]=value;
		nameTemps[i] = name;
		//nameTemp[i] = name.length>20?(data.length-i)+'.'+ name.substring(0, 25): (data.length-i)+'.'+name;
		nameTemp[i] = name.length>20 ? name.substring(0, 25) : name;
		
	}
	//valueTemps = valueTemp;
	//nameTemps = nameTemp;
	
	//option_BQ.series[0].data = valueTemps;
	//option_BQ.series[1].data = nameTemp;
	 
	 //**************************************************************************************
		var  option_BQ = {
			    title : {
			        text: '',
			        subtext: ''
			       // top:-10
			    },
			    tooltip : {
			        trigger: 'axis',  //悬浮提示框不显示,
			        formatter: "{b} : {c}"
			    },
			    grid:{   //绘图区调整
			    		x:10,
            	    	y:10,
            	    	x2:0,
            	    	y2:15
			        },
			    xAxis : 
			        {
			            show:false,
			            type : 'value',
			            boundaryGap : [0,0]
			            /*axisLabel:{interval:20}*/
			            /*position: 'top'*/
			           
			        }
			    ,
			    yAxis : 
			        {	
			    		show:false,
			            type : 'category',
			           // data : ['a','b','c','d','e'],
			            axisLine:{show:false},     //坐标轴
			            //max:'50%',
			            axisTick:[{    //坐标轴小标记
			                show:false
			            }],
			            
			            boundaryGap : [0,0]
			        }
			    ,
			    series : 
			    	//数据
			        {
			    		show:true,
			            name:'',
			            type:'bar',
			            tooltip:{show:true,formatter: "{b} : {c}"},
			            barWidth: '20%',  //柱宽度
			            itemStyle:{
			                normal:{    //柱状图颜色
			                	color: function(params) {
		                            var colorList = ["#dd4656", "#f6bc43","#2aba66","#1865c4","#1b5b86"];
		                            return colorList[params.dataIndex]
		                        },
		                        label:{
			                        show: true,   //显示文本
			                        position: [0,'-15'],  //数据值位置
			                        textStyle:{
			                            color:'#000',
			                            fontSize:'95%'
			                        },
			                        formatter: "{b} : {c}"
			                        /*formatter:function(data){
			                            return nameTemp[data.dataIndex];
			                         }*/
			                    }
			                }
			            },
			            data:valueTemp
			        }
			        //上面的标题
			        
			    
			};
		//*************************************************************************************
	 
	option_BQ.yAxis.data = nameTemps;//悬浮显示名称为全称
	
	myChartB.setOption(option_BQ);
	
}
//标签点击
/*function submitSerReqNode(att){
	var url = $ctx + "/homePage/dataSource/ajaxGetTagTOP5Amount.action?title="+att;
	$.post(url).done(function(result) {
		initPie_BQ(result);
		html = [];
		var length = result.length < 5 ? result.length : 5;
		for (var i = 0; i < length; i++) {
			html.push('<tr><td align="center">' + (i + 1) + '</td><td align="center">' + result[i].LABELCATEGORY + '</td></tr>');
		}
		$('#ranking1').html(html.join(""));
	});
}*/

function resetSize() {
	$('div[class^="form"]').css("width", $(".formA")[0].getBoundingClientRect().height);
	$('div[class^="fContent"]').css("width", $(".service")[0].getBoundingClientRect().width-$(".formA")[0].getBoundingClientRect().width);
}

function resetChartsSize() {
	var width = $("#zdCharts")[0].getBoundingClientRect().width - 1;
	var height = $("#zdCharts")[0].getBoundingClientRect().height - 1;
	var size = width/chartLen > height ? height : width;
	var margin = (width - size*chartLen) / (chartLen*2);
	
	$('.mychartDiv').css("width", size);
	$('.mychartDiv').css("height", size);
	$('.mychartDiv').css("margin-left", margin);
	$('.mychartDiv').css("margin-right", margin);
}
function startmarquee(lh, speed, delay) {  
    var t;  
    var val = 1;
	var times;
    var oWidth = $("#noticeInfo")[0].offsetWidth; /** div的宽度 **/　  
    var p = false;  
    var area = $("#noticeInfo")[0];  
    var preTop = 0;  
    area.scrollLeft = 0;  
    function start() {  
        t = setInterval(scrolling, speed);  
        area.scrollLeft += val;  
    }  
    function scrolling() {
    	if (area.scrollLeft == 0 || area.scrollLeft == area.scrollWidth - oWidth) {
    		delay = 2000;
    		val = val * -1;
    	} else {
    		delay = 0;
    	}
    	
        if (area.scrollLeft % lh != 0 && area.scrollLeft % (area.scrollWidth - oWidth) != 0) {  
            preTop = area.scrollLeft;  
            area.scrollLeft += val;  
            if (preTop >= area.scrollWidth || preTop == area.scrollLeft) {  
                // area.scrollLeft = 0;  
                val = val * -1;
            }
        } else {
            clearInterval(t);
            times = setTimeout(start, delay);  
        }  
    }  
    setTimeout(start, delay);  
    
    area.onmouseover=function(){
		clearInterval(t);
		clearTimeout(times);
	}
	area.onmouseout=function(){
		clearInterval(t);
		clearTimeout(times);
		t=setInterval(scrolling, speed);
	}
}
var colori=0;
function setChart(data) {
	

	var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
	var coArr = [colorArr[colori], '#e9eced'];
	colori++;
	var impMap = {show: true, fontSize: '14', fontWeight: 'bold'};
	
	var sqlname = data.sqlname;
	var value = data.value;
	var name = data.name;
	var id = data.id;
	var childid = data.childid;
	var type = data.type;

	var option_pie = option_pieStr;
	var echertPie = echarts.init(document.getElementById(id));
	echertPie.clear();

	var dataTemp =new Array();
	var dataPieMap = {};
	var dataPieMapSpa = {};

	dataPieMap.sqlname = sqlname;
	dataPieMap.label = impMap;
	
/*	if ("客户投诉量" == name) {
	//	option_pie.series.label.normal.formatter=" {c} \n ———— \n {d}% ";
		option_pie.series.label.normal.formatter=" {c}";
	} else {
		option_pie.series.label.normal.formatter=" {c}";
	}*/
	if (type == "量") {
		dataPieMap.value = value;
		dataPieMapSpa.value = 0;
		option_pie.series.label.normal.formatter="{c}";
		if (value == 0) {
			dataPieMap.value = 1;
			dataPieMapSpa.value = 0;
			option_pie.series.label.normal.formatter=" 0";
		}
	} else if (type == "率") { // "客户投诉量" == titleArr[j]
		if (value == "" || value == 0) {
			value = 1;
			option_pie.series.label.normal.formatter="0%";
		} else {
			option_pie.series.label.normal.formatter="{c}%";
		}
		dataPieMap.value = value;
		dataPieMapSpa.value = 0;
		
	} else if (type == "比") { // "客户投诉量" == titleArr[j]
		if (value == "" || value == 0) {
			value = 1;
			option_pie.series.label.normal.formatter="0";
		} else {
			option_pie.series.label.normal.formatter="{c}";
		}
		dataPieMap.value = value;
		dataPieMapSpa.value = 0;
		
	} else {
		dataPieMap.value = value;
		dataPieMapSpa.value = 0;
	}
	/////////////////////////////

	dataTemp[0] = dataPieMap;
	dataTemp[1] = dataPieMapSpa;

	option_pie.color = coArr;
	option_pie.series.data = dataTemp;
	option_pie.title.text = name;
	option_pie.aria.description = id;
	
	echertPie.setOption(option_pie);
	
	if ("客户投诉量" == name || "客户投诉率" == name) {
		/*echertPie.on('click', function (param){  
			var paramDe = getParamForDetail();
            var childUrl=$ctx + "/lnkhzs/propage/comCust.jsp?id="+paramDe;
    		openwindow2(childUrl,'',1200, 600);
        });*/
	} else {
		/*echertPie.on('click', function (e){  
			// var childUrl=$ctx + "/lnkhzs/propage/comTable.jsp";
			var paramDe = getParamForDetail();
			var sqlname = e.data.sqlname;
            var childUrl=$ctx + "/tsgd/tsgd/comTable.action?sqlNameB="+ sqlname.split(".")[0].replace("_LV","")+ "_CLD_B." + sqlname.split(".")[1] +"&sqlNameA="+ sqlname.split(".")[0].replace("_LV","") + "_CLD_A." + sqlname.split(".")[1] +"&sqlName=" + sqlname.split(".")[0].replace("_LV","") + "_CLD." + sqlname.split(".")[1] + paramDe; 
            openwindow2(childUrl,'',1200, 600);
        });*/
	}
}


var optionSy = {
    xAxis: {
        type: 'category',
        boundaryGap: false,
        show:false,
        data : []
    },
    yAxis: {
        type: 'value',
        show:false,
        splitLine:{
            show:false
        }
    },
    grid: {
    	top: '2%',
        left: '3%',
        right: '3%',
        bottom: '4%',
        containLabel: false
    },
    tooltip:{
        show: true,
        formatter: "{b} : {c}个",
        trigger: 'axis'/*,
        axisPointer: {
            type: 'none',
            label: {
                backgroundColor: '#6a7985'
            }
        }*/
    },
    series: [{
        data: [520, 932, 901, 934, 1290, 330, 1320],
        type: 'line',
        symbol: "none",
        areaStyle: {},
        itemStyle: {
            color: 'rgb(255, 232, 232)'
        }
    },{
        data: [820, 132, 401, 634, 890, 1330, 320],
        type: 'line',
        symbol: "none",
        areaStyle: {},
        itemStyle: {
            color: 'rgb(192, 214, 247)'
        }
    }]
};


function setSYWTZL(myChartSy){
	var url = $ctx + "/homePage/dataSource/ajaxGetSourceAmount.action";
	$.post(url).done(function(dataArr) {
		var data = dataArr;
		var nameTemp = new Array();
		var valueTemp = new Array();
		var valueTemp1 = new Array();
		for (var i = 0; i < data.length; i++) {
			var value = data[i].comValue;
			var value1 = data[i].totalValue;
			var name = data[i].mon;
			nameTemp[i] = name ;
			valueTemp[i] = value;
			valueTemp1[i] = value1;
		}
		var optionSy = {
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        show:false,
			        data : nameTemp
			    },
			    yAxis: {
			        type: 'value',
			        show:false,
			        splitLine:{
			            show:false
			        }
			    },
			    grid: {
			    	top: '2%',
			        left: '3%',
			        right: '3%',
			        bottom: '4%',
			        containLabel: false
			    },
			    tooltip:{
			        show: true,
			        formatter: "{b} : <br>溯源问题总量:{c0}<br>溯源问题派发量:{c1} ",
			        trigger: 'axis'/*,
			        axisPointer: {
			            type: 'none',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        }*/
			    },
			    series: [{
			        data: valueTemp1,
			        type: 'line',
			        symbol: "none",
			        smooth: true,
			        areaStyle: {},
			        itemStyle: {
			            color: 'rgb(255, 232, 232)'
			        }
			    },{
			        data: valueTemp,
			        type: 'line',
			        symbol: "none",
			        areaStyle: {},
			        smooth: true,
			        itemStyle: {
			            color: 'rgb(192, 214, 247)'
			        }
			    }]
			};
		myChartSy.setOption(optionSy);
		
	});
}


function setZHDY(myChartZh){
	var url = $ctx + "/homePage/dataSource/ajaxGetAnswerAmount.action";
	$.post(url).done(function(dataArr) {
		var data = dataArr;
		var nameTemp = new Array();
		var valueTemp = new Array();
		for (var i = 0; i < data.length; i++) {
			var value = data[i].value;
			var name = data[i].mon;
			nameTemp[i] = name ;
			valueTemp[i] = value;
		}
		var optionZh = {
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        show: true,
			        data : nameTemp
			    },
			    yAxis: {
			        type: 'value',
			        show: true,
			        splitLine:{
			            show: true
			        }
			    },
			    grid: {
			    	top: '12%',
			        left: '10%',
			        right: '9%',
			        bottom: '12%',
			        containLabel: false
			    },
			    tooltip:{
			        show: true,
			        formatter: "{b} : {c}个",
			        trigger: 'axis'/*,
			        axisPointer: {
			            type: 'none',
			            label: {
			                backgroundColor: '#6a7985'
			            }
			        }*/
			    },
			    series: [{
			        data: valueTemp,
			        type: 'line',
			        smooth: true,
			        areaStyle: {},
			        itemStyle: {
			        	normal :{
			        		label : {
			        			color: 'rgb(166, 217, 250)',
			        			show: true
			        		},
			        		lineStyle: {
				                color: 'rgb(166, 217, 250)'
				            },
				            color: { 
				            	type: 'linear',
				                x: 0,
				                y: 0,
				                x2: 0,
				                y2: 1,
				                colorStops: [
				                    {
				                        offset: 0, color: 'rgb(163, 215, 250)' // 0% 处的颜色
				                    },
					                {
					                    offset: 0.4, color: 'rgb(203, 233, 252)' // 40% 处的颜色
					                }, 
					                {
					                    offset: 1, color: '#fff' // 100% 处的颜色
					                }
					            ],
				                global: false
				            }
			        	}
			        }
			    }]
			};
		myChartZh.setOption(optionZh);
		
	});
}


var option_pieStr = {
		title: {
	        text: '',
	        bottom: '2px',
	        show: true,
	        left: 'center',
	        textStyle: {
	        	color: 'rgb(122, 122, 122)',
	        	fontStyle: 'normal',
	        	fontWeight: 'normal',
	        	fontFamily: 'Microsoft YaHei',
	        	fontSize: 10
	        }
		},
		legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},
		grid:{
			left:'0%',
			right:'0%',
			top:'0%',
			bottom:'0%'
		},
	    series: {
	    	type:'pie',
            radius: ['59%', '68%'],
            center: ['50%', '40%'],
            avoidLabelOverlap: false,
	        label: {
                normal: {
                    show: false,
                    position: 'center',
                    formatter: " {c}..." //{@[1]}
                }
	        }
	    },
	    toolbox: {  
	        /*feature: {  
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
	        }*/
	    },
	    aria: {
	        show: false
	    }
	};





