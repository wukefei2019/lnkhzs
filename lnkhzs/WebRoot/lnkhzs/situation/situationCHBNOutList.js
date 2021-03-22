///situation/sTotal

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
	//alert(11);
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
		//$("#table0").attr("sqlname","SQL_TS_TS_SJA_WEEK.query");
		$.bs.table.refresh("table0");
		
		//日期显示
		$("#showSelectDate").text(dealTime(-3,$("#calendar").val())+"至"+dealTime(3,$("#calendar").val()));
		
	}else{
		
		//月
		//$("#table0").attr("sqlname","SQL_TS_TS_SJA.query");
		$("#time").val($("#calendar").val());
		console.log($("#time").val());
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



/*///situation/sTotal
$(document).ready(function() {
	$("#calendar").val(doHandleDate);
	getTotalDetail();
});

function getTotalDetail() {
	$("#ttable").empty();
	$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='35'>加载中，请稍等</td></tr>");
	//alert($("#calendar").val());
	var url = $ctx + "/situation/sTotal/getCHBNOut.action?selecttime="+$("#calendar").val();
	//var url = $ctx + "/situation/sTotal/getCHBNOut.action";
	$.post(url).done(function(dataArr) {
		console.log(dataArr);
		var html = "";
		if (dataArr == null) {
			$("#ttable").empty();
			$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='35'>暂无数据</td></tr>");
		} else if(dataArr.length==0){
			$("#ttable").empty();
			$("#ttable").append("<tr class='trOther' style='background:rgb(253, 253, 253)'><td colspan='35'>暂无数据</td></tr>");
		}else {
			for (var i = 0; i < dataArr.length; i++) {
				html += "<tr class='trOther' style='background:rgb(253, 253, 253)'>";
				html += "<td name='td0'>" + dataArr[i].CHBN + "</td>";
				html += "<td name='td1'>" + dataArr[i].BROAD_TOTAL + "</td>";
				html += "<td name='td2'>" + dataArr[i].BROAD_MARKET + "</td>";
				html += "<td name='td3'>" + dataArr[i].BROAD_MARKET_RAT + "</td>";
				html += "<td name='td4'>" + dataArr[i].BROAD_NET + "</td>";
				html += "<td name='td5'>" + dataArr[i].BROAD_NET_RAT + "</td>";
				html += "<td name='td6'>" + dataArr[i].BROAD_XA + "</td>";
				html += "<td name='td7'>" + dataArr[i].BROAD_XA_RAT + "</td>";

				html += "<td name='td8'>" + dataArr[i].ONLINE_TOTAL + "</td>";
				html += "<td name='td9'>" + dataArr[i].ONLINE_MARKET + "</td>";
				html += "<td name='td10'>" + dataArr[i].ONLINE_MARKET_RAT + "</td>";
				html += "<td name='td11'>" + dataArr[i].ONLINE_NET + "</td>";
				html += "<td name='td12'>" + dataArr[i].ONLINE_NET_RAT + "</td>";
				html += "<td name='td13'>" + dataArr[i].ONLINE_XA + "</td>";
				html += "<td name='td14'>" + dataArr[i].ONLINE_XA_RAT + "</td>";
				
				html += "<td name='td15'>" + dataArr[i].NARROW_TOTAL + "</td>";
				html += "<td name='td16'>" + dataArr[i].NARROW_SJ + "</td>";
				html += "<td name='td17'>" + dataArr[i].NARROW_SJ_RAT + "</td>";
				html += "<td name='td18'>" + dataArr[i].NARROW_MARKET + "</td>";
				html += "<td name='td19'>" + dataArr[i].NARROW_MARKET_RAT + "</td>";
				html += "<td name='td20'>" + dataArr[i].NARROW_MARKET_SJ + "</td>";
				html += "<td name='td21'>" + dataArr[i].NARROW_NET + "</td>";
				html += "<td name='td22'>" + dataArr[i].NARROW_NET_RAT + "</td>";
				html += "<td name='td23'>" + dataArr[i].NARROW_NET_SJ + "</td>";
				html += "<td name='td24'>" + dataArr[i].NARROW_XA + "</td>";
				html += "<td name='td25'>" + dataArr[i].NARROW_XA_RAT + "</td>";
				html += "<td name='td26'>" + dataArr[i].NARROW_XA_SJ + "</td>";
				html += "</tr>";
			}
			$("#ttable").empty();
			$("#ttable").append(html);
			
			var BROAD_TOTAL=0;
			$("td[name=td1]").each(function(j){				
				BROAD_TOTAL+=parseInt($(this).html());
			});
			var BROAD_MARKET=0;
			$("td[name=td2]").each(function(j){				
				BROAD_MARKET+=parseInt($(this).html());
			});
			var BROAD_MARKET_RAT=0;
			if(BROAD_TOTAL==0)
				BROAD_MARKET_RAT=0;
			else
				BROAD_MARKET_RAT=Math.round(parseFloat(BROAD_MARKET/BROAD_TOTAL)*100)/100;
			$("td[name=td3]").each(function(j){				
				BROAD_MARKET_RAT+=parseDouble($(this).html());
			});
			var BROAD_NET=0;
			$("td[name=td4]").each(function(j){				
				BROAD_NET+=parseInt($(this).html());
			});
			var BROAD_NET_RAT=0;
			if(BROAD_TOTAL==0)
				BROAD_NET_RAT=0;
			else
				BROAD_NET_RAT=Math.round(parseFloat(BROAD_NET/BROAD_TOTAL)*100)/100;
			$("td[name=td5]").each(function(j){				
				BROAD_NET_RAT+=parseDouble($(this).html());
			});
			var BROAD_XA=0;
			$("td[name=td6]").each(function(j){				
				BROAD_XA+=parseInt($(this).html());
			});
			var BROAD_XA_RAT=0;
			if(BROAD_TOTAL==0)
				BROAD_XA_RAT=0;
			else
				BROAD_XA_RAT=Math.round(parseFloat(BROAD_XA/BROAD_TOTAL)*100)/100;
			$("td[name=td7]").each(function(j){				
				BROAD_XA_RAT+=parseDouble($(this).html());
			});
			var ONLINE_TOTAL=0;
			$("td[name=td8]").each(function(j){				
				ONLINE_TOTAL+=parseInt($(this).html());
			});
			var ONLINE_MARKET=0;
			$("td[name=td9]").each(function(j){				
				ONLINE_MARKET+=parseInt($(this).html());
			});
			var ONLINE_MARKET_RAT=0;
			if(ONLINE_TOTAL==0)
				ONLINE_MARKET_RAT=0;
			else
				ONLINE_MARKET_RAT=Math.round(parseFloat(ONLINE_MARKET/ONLINE_TOTAL)*100)/100;
			$("td[name=td10]").each(function(j){				
				ONLINE_MARKET_RAT+=parseDouble($(this).html());
			});
			var ONLINE_NET=0;
			$("td[name=td11]").each(function(j){				
				ONLINE_NET+=parseInt($(this).html());
			});
			var ONLINE_NET_RAT=0;
			if(ONLINE_TOTAL==0)
				ONLINE_NET_RAT=0;
			else
				ONLINE_NET_RAT=Math.round(parseFloat(ONLINE_NET/ONLINE_TOTAL)*100)/100;
			$("td[name=td12]").each(function(j){				
				ONLINE_NET_RAT+=parseDouble($(this).html());
			});
			var ONLINE_XA=0;
			$("td[name=td13]").each(function(j){				
				ONLINE_XA+=parseInt($(this).html());
			});
			var ONLINE_XA_RAT=0;
			if(ONLINE_TOTAL==0)
				ONLINE_XA_RAT=0;
			else
				ONLINE_XA_RAT=Math.round(parseFloat(ONLINE_XA/ONLINE_TOTAL)*100)/100;
			$("td[name=td14]").each(function(j){				
				ONLINE_XA_RAT+=parseDouble($(this).html());
			});
			var NARROW_TOTAL=0;
			$("td[name=td15]").each(function(j){				
				NARROW_TOTAL+=parseInt($(this).html());
			});
			var NARROW_SJ=0;
			$("td[name=td16]").each(function(j){				
				NARROW_SJ+=parseInt($(this).html());
			});
			var NARROW_SJ_RAT=0;
			if(NARROW_TOTAL==0)
				NARROW_SJ_RAT=0;
			else
				NARROW_SJ_RAT=Math.round(parseFloat(NARROW_SJ/NARROW_TOTAL)*100)/100;
			$("td[name=td17]").each(function(j){				
				NARROW_SJ_RAT+=parseDouble($(this).html());
			});
			var NARROW_MARKET=0;
			$("td[name=td18]").each(function(j){				
				NARROW_MARKET+=parseInt($(this).html());
			});
			var NARROW_MARKET_RAT=0;
			if(NARROW_TOTAL==0)
				NARROW_MARKET_RAT=0;
			else
				NARROW_MARKET_RAT=Math.round(parseFloat(NARROW_MARKET/NARROW_TOTAL)*100)/100;
			$("td[name=td19]").each(function(j){				
				NARROW_MARKET_RAT+=parseDouble($(this).html());
			});
			var NARROW_MARKET_SJ=0;
			$("td[name=td20]").each(function(j){				
				NARROW_MARKET_SJ+=parseInt($(this).html());
			});
			var NARROW_NET=0;
			$("td[name=td21]").each(function(j){				
				NARROW_NET+=parseInt($(this).html());
			});
			var NARROW_NET_RAT=0;
			if(NARROW_TOTAL==0)
				NARROW_NET_RAT=0;
			else
				NARROW_NET_RAT=Math.round(parseFloat(NARROW_NET/NARROW_TOTAL)*100)/100;
			$("td[name=td22]").each(function(j){				
				NARROW_NET_RAT+=parseDouble($(this).html());
			});
			var NARROW_NET_SJ=0;
			$("td[name=td23]").each(function(j){				
				NARROW_NET_SJ+=parseInt($(this).html());
			});
			var NARROW_XA=0;
			$("td[name=td24]").each(function(j){				
				NARROW_XA+=parseInt($(this).html());
			});
			var NARROW_XA_RAT=0;
			if(NARROW_TOTAL==0)
				NARROW_XA_RAT=0;
			else
				NARROW_XA_RAT=Math.round(parseFloat(NARROW_XA/NARROW_TOTAL)*100)/100;
			$("td[name=td25]").each(function(j){				
				NARROW_XA_RAT+=parseDouble($(this).html());
			});
			var NARROW_XA_SJ=0;
			$("td[name=td26]").each(function(j){				
				NARROW_XA_SJ+=parseInt($(this).html());
			});
			html = "<tr class='trOther' style='background:rgb(253, 253, 253)'>";
			html += "<td>总量</td>";
			html += "<td>" + BROAD_TOTAL + "</td>";
			html += "<td>" + BROAD_MARKET + "</td>";
			html += "<td>" + BROAD_MARKET_RAT + "</td>";
			html += "<td>" + BROAD_NET + "</td>";
			html += "<td>" + BROAD_NET_RAT + "</td>";
			html += "<td>" + BROAD_XA + "</td>";
			html += "<td>" + BROAD_XA_RAT + "</td>";

			html += "<td>" + ONLINE_TOTAL + "</td>";
			html += "<td>" + ONLINE_MARKET + "</td>";
			html += "<td>" + ONLINE_MARKET_RAT + "</td>";
			html += "<td>" + ONLINE_NET + "</td>";
			html += "<td>" + ONLINE_NET_RAT + "</td>";
			html += "<td>" + ONLINE_XA + "</td>";
			html += "<td>" + ONLINE_XA_RAT + "</td>";
			
			html += "<td>" + NARROW_TOTAL + "</td>";
			html += "<td>" + NARROW_SJ + "</td>";
			html += "<td>" + NARROW_SJ_RAT + "</td>";
			html += "<td>" + NARROW_MARKET + "</td>";
			html += "<td>" + NARROW_MARKET_RAT + "</td>";
			html += "<td>" + NARROW_MARKET_SJ + "</td>";
			html += "<td>" + NARROW_NET + "</td>";
			html += "<td>" + NARROW_NET_RAT + "</td>";
			html += "<td>" + NARROW_NET_SJ + "</td>";
			html += "<td>" + NARROW_XA + "</td>";
			html += "<td>" + NARROW_XA_RAT + "</td>";
			html += "<td>" + NARROW_XA_SJ + "</td>";
			html += "</tr>";
			$("#ttable").append(html);

		}

		console.log("结束");
	});
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

}*/