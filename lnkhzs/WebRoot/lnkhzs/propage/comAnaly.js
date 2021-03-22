
$(document).ready(function() {
	var store = "A";
	$(".xiala_menu-backdrop", window.parent.document).trigger("click");
	$("label").on("click",function(){

		if (this.innerText == "年") {
			$(".radioY").addClass("selected");
			$(".radioM").removeClass("selected");
			$(".radioR").removeClass("selected");
			if (store == "B") {
				$("#calendar").val("");
			}
			store = "A";
		} else if(this.innerText == "月") {
			$(".radioY").removeClass("selected");
			$(".radioR").removeClass("selected");
			$(".radioM").addClass("selected");
			if (store == "A") {
				$("#calendar").val("");
			}
			store = "B";
		} else if(this.innerText == "日") {
			$(".radioY").removeClass("selected");
			$(".radioM").removeClass("selected");
			$(".radioR").addClass("selected");
			if (store == "A") {
				$("#calendar").val("");
			}
			store = "B";
		}
	})
	if(!!_USER_CITY_NAME && typeof(_USER_CITY_NAME)!="undefined" && _USER_CITY_NAME!=0){
		$("#selectItem").val(_USER_CITY_NAME+'市');
		$("#selectItem").attr("disabled",true);
	}

	initSelEvent();
	
	initCharts();
	
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



function initSelEvent() {
	$("#selectItem").change(function(){
		initCharts();
	});
}

function changeDateVal() {
	initCharts();
}

function getParams() {

	var cityName = $("#selectItem").find("option:selected").val();
	var dateVal = $("#calendar").val();
	
	var params = "&area_name=" + cityName + "&time=" + dateVal;
	return params;
}

function initCharts() {

	var urlParam = getParams();
	var urla = $ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=D" + encodeURI(urlParam);
	$.post(urla).done(function(dataa) {

		var len = dataa.length;
		var less = 7 - len;
		
		$("#chartStore").html("");
		for (var q = 0; q < dataa.length; q++) {
			
			var id = dataa[q].id;
			var ht = "<div id='" + id + "' class='myECharts mychartDiv horizontal-div'>" +
					"<div class='addDiv'><img src='../style/image/loading_0.gif' class='add' style=' width: 40px; margin: 50px 0 0 60px;'/></div></div>";
			$("#chartStore").append(ht);
			
			var url = $ctx + "/tsgd/tsgd/ajaxGetPiesJson.action?menuId=C&id=" + dataa[q].id + encodeURI(urlParam);
			$.post(url).done(function(data) {
				setChart(data);
			});
		}
		
		for (var k = 0; k < less;k++) {
			var et =  "<div id='addPic' class='mychartDiv' >"
					+ "    <img class='addPicTwo' src='../style/image/addPic.png' onclick='addInd()'></img>"
					+ "    <p class='midPicSpan' style='position: inherit;' > 添加 </p>"
					+ "</div>"
			$("#chartStore").append(et);
		}
		initMove();
	});
}


function setChart(data) {

	var colorArr = ['#f1868d', '#22de91', '#3bbadf', '#ecab6b', 'rgb(192, 120, 221)', 'rgb(255, 127, 80)', 'rgb(199, 237, 204)'];
	var coArr = [colorArr[data.vieworder-1], '#e9eced'];
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
		option_pie.series.label.normal.formatter=" {c}";
		if (value == 0) {
			dataPieMap.value = 1;
			dataPieMapSpa.value = 0;
			option_pie.series.label.normal.formatter=" 0";
		}
	} else if (type == "率") { // "客户投诉量" == titleArr[j]
		if (value == "" || value == 0) {
			value = 1;
			option_pie.series.label.normal.formatter=" 0%";
		} else {
			option_pie.series.label.normal.formatter=" {c}%";
		}
		dataPieMap.value = value;
		dataPieMapSpa.value = 0;
		
	} else if (type == "比") { // "客户投诉量" == titleArr[j]
		if (value == "" || value == 0) {
			value = 1;
			option_pie.series.label.normal.formatter=" 0";
		} else {
			option_pie.series.label.normal.formatter=" {c}";
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
		echertPie.on('click', function (param){  
			var paramDe = getParamForDetail();
            var childUrl=$ctx + "/lnkhzs/propage/comCust.jsp?id="+paramDe;
    		openwindow2(childUrl,'',1200, 600);
        });
	} else {
		echertPie.on('click', function (e){  
			// var childUrl=$ctx + "/lnkhzs/propage/comTable.jsp";
			var paramDe = getParamForDetail();
			var sqlname = e.data.sqlname;
            var childUrl=$ctx + "/tsgd/tsgd/comTable.action?sqlNameB="+ sqlname.split(".")[0].replace("_LV","")+ "_CLD_B." + sqlname.split(".")[1] +"&sqlNameA="+ sqlname.split(".")[0].replace("_LV","") + "_CLD_A." + sqlname.split(".")[1] +"&sqlName=" + sqlname.split(".")[0].replace("_LV","") + "_CLD." + sqlname.split(".")[1] + paramDe; 
            openwindow2(childUrl,'',1200, 600);
        });
	}
}

function getParamForDetail() {

	var params = "";
	var cityName = $("#selectItem").find("option:selected").val();
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
	}else if ($(".radioR").hasClass("selected")) {
		dateFormat='yyyy-MM-dd';
	}
	return dateFormat;
}

function addInd(){
	openwindow($ctx + "/lnkhzs/propage/indAdd.jsp");
}

function deleteInd(id) {
	if (confirm("确认要取消当前指标？")) {
		$.post($ctx + "/tsgd/tsgd/deleteInd.action?id=" + id).done(function(data) {
			if (data == "1") {
				$("#chartStore").html("");
				initCharts();
				initMove();
			} else {
				alert("关闭失败");
			}
		});
	}
}

function resetOrder() {
	var paMap = {};
	$(".horizontal-div").each(function(index){
		var id = $(this)[0].id;
		paMap[id] = index + 1;
	});
	var params = JSON.stringify(paMap);
	$.post($ctx + "/tsgd/tsgd/resetIndOrder.action",{paramMap:params}).done(function(data) {
		if (data == "1") {
		} else {
			console.log("顺序调整失败");
		}
	});
	/*$.ajax({
        type:"POST", 
        contentType : 'application/json;charset=utf-8',
        url: $ctx + "/tsgd/tsgd/resetIndOrder.action",
        data:{
        	paramMap:JSON.stringify(paMap)
        },     
        processData : false,
        dataType : 'json',
        success:function(data){
        	if (data == "1") {
    		} else {
    			console.log("顺序调整失败");
    		}
        }
    });*/
}

function initMove() {
	var range = { x: 0, y: 0 };//鼠标元素偏移量  
    var lastPos = { x: 0, y: 0, x1: 0, y1: 0 }; //拖拽对象的四个坐标  
    var tarPos = { x: 0, y: 0, x1: 0, y1: 0 }; //目标元素对象的坐标初始化  
    var theDiv = null, move = false; choose = false; //拖拽对象 拖拽状态 选中状态 
    var theDivId =0, theDivHeight = 0, theDivHalf = 0; tarFirstY = 0; theDivWidth = 0; //拖拽对象的索引、高度、的初始化。  
    var tarDiv = null, tarFirst, tempDiv; //要插入的目标元素的对象, 临时的虚线对象  
    var initPos = {x: 0, y: 0};  
    $(".horizontal-div").each(function(){  
//    	if () {
//    		$(this).mousedown
//    	}
    	
        $(this).mousedown(function (event){  
                
                //拖拽对象  
                theDiv = $(this);  
                //记录拖拽元素初始位置 
                initPos.x = theDiv.offset().left; 
                initPos.y = theDiv.offset().top;
                //鼠标元素相对偏移量  
                range.x = event.pageX - theDiv.offset().left;  
                range.y = event.pageY - theDiv.offset().top;
                if (range.y > 125) {
                	choose = true; 
                } else {
                	choose = false; 
                	return false;
                }
                
                theDivId = theDiv.index();
                theDivWidth = theDiv.width();  
                theDivHalf = theDivWidth/2;  
                theDiv.attr("class","horizontal-div-dash");  
                theDiv.css({left: initPos.x + 'px',top: initPos.y + 'px'});  
                 // 创建新元素 插入拖拽元素之前的位置(虚线框)  
                $("<div class='dash'></div>").insertBefore(theDiv);  
                tempDiv = $(".dash"); 
        }); 
        
        $(this).mouseup(function(event) {
            if(!choose){
            	return false;
            } 
            if(!move){ 
                theDiv.attr("class", "horizontal-div"); 
                tempDiv.remove(); // 删除新建的虚线div 
                choose = false; 
                return false; 
            }
            theDiv.insertBefore(tempDiv); // 拖拽元素插入到 虚线div的位置上  
            theDiv.attr("class", "myECharts mychartDiv horizontal-div"); //恢复对象的初始样式  
            tempDiv.remove(); // 删除新建的虚线div  
            move = false; 
            choose = false; 
            resetOrder();
        }).mousemove(function(event) {
        	if(!choose) return false; 
        	move = true; 
            lastPos.x = event.pageX - range.x;  
            lastPos.y = event.pageY - range.y;  
            lastPos.x1 = lastPos.x + theDivWidth;  
            // 拖拽元素随鼠标移动  
            theDiv.css({left: lastPos.x + 'px',top: lastPos.y + 'px'});  
            // 拖拽元素随鼠标移动 查找插入目标元素  
            var $main = $('.horizontal-div'); // 局部变量：按照重新排列过的顺序 再次获取 各个元素的坐标，  
            $main.each(function () {  
                tarDiv = $(this);  
                tarPos.x = tarDiv.offset().left;  
                tarPos.y = tarDiv.offset().top;  
                tarPos.x1 = tarPos.x + tarDiv.width()/2;  
                tarFirst = $main.eq(0); // 获得第一个元素  
                tarFirstX = tarFirst.offset().left + theDivHalf ; // 第一个元素对象的中心纵坐标  
                //拖拽对象 移动到第一个位置  
                if (lastPos.x <= tarFirstX) {  
                	tempDiv.insertBefore(tarFirst);  
                }  
                //判断要插入目标元素的 坐标后， 直接插入  
                if (lastPos.x >= tarPos.x - theDivHalf && lastPos.x1 >= tarPos.x1 ) {  
                	tempDiv.insertAfter(tarDiv);  
                }  
            });  
        });
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
		legend:{
			textStyle:{color:'#fff'},
			right:'38px',
			itemHeight: 9,
			itemWidth: 16
		},
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
                    show: false,
                    position: 'center',
                    formatter: " {c}" //{@[1]}
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