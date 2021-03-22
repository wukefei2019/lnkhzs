var store = "B";
$(document).ready(function() {
	$("#calendar").val(doHandleDate());
	getTotalDetail();
	
	store = "B";
	$("label").on("click", function() {
		if (this.innerText == "周") {
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
});

function getTotalDetail() {
	//$("#time").val($("#calendar").val());
	//$("#timeweek").val($("#calendar").val());
	if(store=="A"){
		//周
		var rweek=makeDate(new Date($("#calendar").val()));
		var myweek="'"+rweek[0]+"','"+rweek[1]+"','"+rweek[2]+"','"+rweek[3]+"','"+rweek[4]+"','"+rweek[5]+"','"+rweek[6]+"'";
		myweek=myweek.replace(/\//g, '-');
		/*myweek="'"+dealTime(1,$("#calendar").val())+"','"+
		dealTime(2,$("#calendar").val())+"','"+dealTime(3,$("#calendar").val())+
		"','"+dealTime(4,$("#calendar").val())+"','"+dealTime(5,$("#calendar").val())+
		"','"+dealTime(6,$("#calendar").val())+"','"+dealTime(7,$("#calendar").val())+"'";*/
		
		myweek="'"+dealTime(-3,$("#calendar").val())+"','"+
		dealTime(-2,$("#calendar").val())+"','"+dealTime(-1,$("#calendar").val())+
		"','"+dealTime(0,$("#calendar").val())+"','"+dealTime(1,$("#calendar").val())+
		"','"+dealTime(2,$("#calendar").val())+"','"+dealTime(3,$("#calendar").val())+"'";
		
		console.log(myweek);
		$("#time").val(myweek);
		//$("#table0").attr("sqlname","SQL_TS_TOTAL_SJA_WEEK.query");
		$.bs.table.refresh("table0");
		
		//日期显示
		$("#showSelectDate").text(dealTime(-3,$("#calendar").val())+"至"+dealTime(3,$("#calendar").val()));
		
	}else{
		//月
		//$("#table0").attr("sqlname","SQL_TS_TOTAL_SJA.query");
		$("#time").val($("#calendar").val());
		$.bs.table.refresh("table0");
		
		//日期显示
		$("#showSelectDate").text("");
	}
	
}

function doHandleDate() {
    var myDate = new Date();
    var tYear = myDate.getFullYear();
    var tMonth = myDate.getMonth();
 
    var m = tMonth + 1;
    if (m.toString().length == 1) {
        m = "0" + m;
    }
    return tYear +'-'+ m;

}

function changeTime() {
	var dateFormat = '';
	if ($(".radioY").hasClass("selected")) {
		dateFormat = 'yyyy-MM-dd';
	} else if ($(".radioM").hasClass("selected")) {
		dateFormat = 'yyyy-MM';
	}
	return dateFormat;
}


function makeDate(mydate) {
	var new_Date = mydate;
    var timesStamp = new_Date.getTime();
    var currenDay = new_Date.getDay();
    var dates = [];
    for(var i = 0; i < 7; i++) {
        dates.push(new Date(timesStamp + 24 * 60 * 60 * 1000 * (i - (currenDay + 6) % 7)).toLocaleDateString().replace(/[年月]/g, '-').replace(/[日上下午]/g, ''));
    }
    return dates
}

//处理时间方法
function dealTime(dayNum, dat) {
	//2020-04-15
if (dayNum == "0") {
	dayNum = 7;
}
var uom = new Date(), dateStr = '', fday = '';
fday = dat.substring(8, 10);
uom.setYear(dat.substring(0, 4));
uom.setMonth(parseInt(dat.substring(5, 7)) - 1);
uom.setDate(fday);
if(uom.getDay() == 0){
	uom.setDate(uom.getDate() - (7 - dayNum));
}else{
	uom.setDate(uom.getDate() - (uom.getDay() - dayNum));
}
var mon = (uom.getMonth() + 1) + '';
if (mon.length != 2) {
	mon = '0' + mon;
}
var day = uom.getDate() + '';
if (day.length != 2) {
	day = '0' + day;
}
dateStr = '' + uom.getFullYear() +"-"+ mon +"-"+ day;
return dateStr;
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


function exportSelVal() {
	/*var $th = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr").find("th");
	var titles =[];
	var columns =[];
	$th.each(function(i,t){
		if(i!=0){
			titles[i-1]=$(this).find(".th-inner").text();
			console.log("titles"+[i-1]);
			console.log(titles[i-1]);
			columns[i-1]=$(this).attr("data-field");
			console.log("columns"+[i-1]);
			console.log(columns[i-1]);
		}
		
	});
	
	var rQueryName= $("#table0").attr("sqlname");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	
	exportExcelNew(rQueryName,titles,columns,sheetTitle);*/
	
	
	
	/*var $tr = $(".fixed-table-body").find(".table.table-hover.text_align_center.table-no-bordered.table-striped").find("thead").find("tr");
	var titles = new Array();
	var columns = new Array();
	var titles1 =[];
	var columns1 =[];
	$tr.each(function(i,t){
		var $th = $(this).find("th");
		$th.each(function(i1,t1){
			console.log(i1);
			titles1 =[];
			columns1 =[];
			if(i1!=0){
				//titles[i-1][i1-1]=$(this).find(".th-inner").text();
				titles1[i1-1]=$(this).find(".th-inner").text();
				titles.push(i-1,titles1[i1-1]);
				console.log("titles"+[i-1]);
				console.log(titles);
			}
		});
		
		
	});*/
	
	/*var rQueryName= $("#table0").attr("sqlname");
	var lujing= $(".lujing").text();
	var lujings = lujing.split(">");
	var sheetTitle =  lujings[lujings.length-1];
	
	exportExcelNew(rQueryName,titles,columns,sheetTitle);*/

}

/*function getTotalDetail() {
	$("#ttable").empty();
	$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='39'>加载中，请稍等</td></tr>");
	//alert($("#calendar").val());
	var url = $ctx + "/situation/sTotal/getTotalOut.action?selecttime="+$("#calendar").val();
	$.post(url).done(function(dataArr) {
		var html = "";
		if (dataArr == null) {
			$("#ttable").empty();
			$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='39'>暂无数据</td></tr>");
		} else if(dataArr.length==0){
			$("#ttable").empty();
			$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='39'>暂无数据</td></tr>");
		}else {
			for (var i = 0; i < dataArr.length; i++) {
				html += "<tr class='trOther' style='background:rgb(253, 253, 253)'>";
				html += "<td>" + dataArr[i].RESP_ACPT + "</td>";
				html += "<td>" + dataArr[i].BROAD_TOTAL + "</td>";
				html += "<td>" + dataArr[i].BROAD_MARKET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].BROAD_MARKET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].BROAD_MARKET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].BROAD_MARKET_RAT + "</td>";
				html += "<td>" + dataArr[i].BROAD_NET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].BROAD_NET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].BROAD_NET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].BROAD_NET_RAT + "</td>";
				html += "<td>" + dataArr[i].BROAD_XA + "</td>";
				html += "<td>" + dataArr[i].BROAD_XA_RAT + "</td>";
				html += "<td>" + dataArr[i].ONLINE_TOTAL + "</td>";
				html += "<td>" + dataArr[i].ONLINE_MARKET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].ONLINE_MARKET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].ONLINE_MARKET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].ONLINE_MARKET_RAT + "</td>";
				html += "<td>" + dataArr[i].ONLINE_NET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].ONLINE_NET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].ONLINE_NET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].ONLINE_NET_RAT + "</td>";
				html += "<td>" + dataArr[i].ONLINE_XA + "</td>";
				html += "<td>" + dataArr[i].ONLINE_XA_RAT + "</td>";
				html += "<td>" + dataArr[i].NARROW_TOTAL + "</td>";
				html += "<td>" + dataArr[i].NARROW_SJ + "</td>";
				html += "<td>" + dataArr[i].NARROW_SJ_RAT + "</td>";
				html += "<td>" + dataArr[i].NARROW_MARKET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].NARROW_MARKET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].NARROW_MARKET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].NARROW_MARKET_RAT + "</td>";
				html += "<td>" + dataArr[i].NARROW_MARKET_SJ + "</td>";
				html += "<td>" + dataArr[i].NARROW_NET_PERSONAL + "</td>";
				html += "<td>" + dataArr[i].NARROW_NET_FAMILY + "</td>";
				html += "<td>" + dataArr[i].NARROW_NET_ENTERPRISE + "</td>";
				html += "<td>" + dataArr[i].NARROW_NET_RAT + "</td>";
				html += "<td>" + dataArr[i].NARROW_NET_SJ + "</td>";
				html += "<td>" + dataArr[i].NARROW_XA + "</td>";
				html += "<td>" + dataArr[i].NARROW_XA_RAT + "</td>";
				html += "<td>" + dataArr[i].NARROW_XA_SJ + "</td>";
				
				html += "</tr>";
			}
			$("#ttable").empty();
			$("#ttable").append(html);

		}

	});
}*/

