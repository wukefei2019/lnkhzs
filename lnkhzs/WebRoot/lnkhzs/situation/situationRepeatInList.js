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
	});
	
	$('#table0').on('post-header.bs.table', function (data) {
		$("div.fixed-table-header").attr("style","margin-right: 17px;");
	});
	

});


/*function getTotalDetail() {
	//$("#time").val($("#calendar").val());
	//$("#timeweek").val($("#calendar").val());
	if(store=="A"){
		//周
		var rweek=makeDate(new Date($("#calendar").val()));
		var myweek="'"+rweek[0]+"','"+rweek[1]+"','"+rweek[2]+"','"+rweek[3]+"','"+rweek[4]+"','"+rweek[5]+"','"+rweek[6]+"'";
		myweek=myweek.replace(/\//g, '-');
		myweek="'"+dealTime(1,$("#calendar").val())+"','"+
		dealTime(2,$("#calendar").val())+"','"+dealTime(3,$("#calendar").val())+
		"','"+dealTime(4,$("#calendar").val())+"','"+dealTime(5,$("#calendar").val())+
		"','"+dealTime(6,$("#calendar").val())+"','"+dealTime(7,$("#calendar").val())+"'";
		
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
		$.bs.table.refresh("table0");
		
		//日期显示
		$("#showSelectDate").text("");
	}
	
	
	$(".fixed-table-header").attr("style","margin-right: 17px;");
	
}*/



function getTotalDetail() {
	
	if(store=="A"){
		//周
		var rweek=makeDate(new Date($("#calendar").val()));
		var myweek="'"+rweek[0]+"','"+rweek[1]+"','"+rweek[2]+"','"+rweek[3]+"','"+rweek[4]+"','"+rweek[5]+"','"+rweek[6]+"'";
		myweek=myweek.replace(/\//g, '-');
		
		myweek="'"+dealTime(-3,$("#calendar").val())+"','"+
		dealTime(-2,$("#calendar").val())+"','"+dealTime(-1,$("#calendar").val())+
		"','"+dealTime(0,$("#calendar").val())+"','"+dealTime(1,$("#calendar").val())+
		"','"+dealTime(2,$("#calendar").val())+"','"+dealTime(3,$("#calendar").val())+"'";
		
		console.log(myweek);
		$("#time").val(myweek);
		
		$.bs.table.refresh("table0");
	}else{
		
	
		console.log($("#calendar").val())
		
    
	    var now = new Date($("#calendar").val());
		now.setMonth(now.getMonth()-1);
	    var year = now.getFullYear();
	    var month = now.getMonth() + 1;//0-11表示1-12月
	    var dateObj1 = "";

	    if (parseInt(month) < 10) {
	        month = "0" + month;
	    }
	    
	    dateObj1= year + '-' + month ;
	    
	    if (parseInt(now.getMonth) == 1) {//如果是1月份，则取上一年的11月份
	        dateObj1 = (parseInt(year) - 1) + '-11' ;
	        return dateObj1;
	    }
	    if (parseInt(now.getMonth) == 2) {//如果是2月份，则取上一年的12月份
	        dateObj1 = (parseInt(year) - 1) + '-12';
	        return dateObj1;
	    }
	    console.log(dateObj1)
	    
	    
	    
	    var now = new Date($("#calendar").val());
		now.setMonth(now.getMonth()-2);
	    var year = now.getFullYear();
	    var month = now.getMonth() + 1;//0-11表示1-12月	    
	    var dateObj = "";
	    if (parseInt(month) < 10) {
	        month = "0" + month;
	    }
	 
	    dateObj = year + '-' + month ;
	    
	    if (parseInt(now.getMonth) == 1) {//如果是1月份，则取上一年的11月份
	        dateObj = (parseInt(year) - 1) + '-11' ;
	        return dateObj;
	    }
	    if (parseInt(now.getMonth) == 2) {//如果是2月份，则取上一年的12月份
	        dateObj = (parseInt(year) - 1) + '-12';
	        return dateObj;
	    }
	    
	    console.log(dateObj)
		$("#threeMonth").val("'"+ $("#calendar").val()+"','" +dateObj1+"','" + dateObj+"'");
		console.log($("#threeMonth").val())
		
		$("#time").val($("#calendar").val());
	
		$.bs.table.refresh("table0");
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




