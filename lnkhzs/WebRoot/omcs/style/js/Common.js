var _OC = _OC || {};
var _NUM = ['一','二','三','四','五','六','七','八','九','十'];
//清除两边的空格 
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, ''); 
};
//清除左侧的空格 
String.prototype.trimLeft = function() {
    return this.replace(/(^\s*)/g, ''); 
};
//清除右侧的空格 
String.prototype.trimRight = function() {
    return this.replace(/(\s*$)/g, ''); 
};
String.prototype.startWith = function (str) {
    return this.indexOf(str) == 0;
};
String.prototype.endWith = function (str) {
    return this.lastIndexOf(str) == this.length - str.length;
};

String.prototype.toCamelCase = function () {
    return this.toLowerCase().replace(/_(\w)/g,function ($0,$1){
        return $1.toUpperCase();
    })
}
if(!Math.log10){
    Math.log10 = function(n){
        return  Math.round(Math.log(n)/Math.log(10));
    }
}

var ArrayUtils = ArrayUtils || {};

ArrayUtils.equals = function (obj0, obj1) {
    return obj0 == obj1;
}
ArrayUtils.indexOf = function (/* Array */ array, obj, /* Function */ equals) {
    if (typeof equals != 'function') {
        equals = ArrayUtils.equals;
    }
    for (var i = 0; i < array.length; i++) {
        if (equals(array[i], obj)) {
            return i;
        }
    }
    return -1;
}
ArrayUtils.contains = function (/* Array */ array, obj, /* Function */ equals) {
    if (ArrayUtils.indexOf(array, obj, equals) >= 0) {
        return true;
    }
    return false;
}
ArrayUtils.distinct = function (/* Array */ array, /* Function */ equals) {
    var newArray = [];
    for (var i = 0; i < array.length; i++) {
        if (!ArrayUtils.contains(newArray, array[i], equals)) {
            newArray.push(array[i]);
        }
    }
    return newArray;
}

Date.prototype.format = function (format) {
    /*
     * 使用例子:format="yyyy-MM-dd hh:mm:ss";
     */
    var num = ["日","一","二","三","四","五","六"];
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds(),
        "w": this.getDay(),
        "W": num[this.getDay()]
        // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
                     - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                     ? o[k]
                     : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function showInfo2(url,lujing){
    location.href=url+"?path=" + $(lujing).text(); 
}

$(document).ready(function() {
	$("body").bs_style();
	if($.fn.multi_select){
	    $('.ui-select[multiple]').multi_select();
	}
	if($.fn.ui_select){
		var $selects = $('.ui-select:not([multiple])');
		for (var i = 0; i < $selects.size(); i++) {
			var $select = $selects.eq(i);
			var $el = $select.ui_select();
			if($select.is(".readonly")){
				$el.readonly();
			}
			if($select.is(".disable")){
				$el.disable();
			}
		}
	}
	// operation show
    formpage_operation_permission($(".form-page.permission"),$(".form-page.permission [name='operation']").val()||1023);
    formpage_operation_permission($(".form-page.show"),"show");
    // 添加 遮罩
    $(".drop_layer").remove();
    //清除时间控件输入历史
    $(".Wdate").attr("autocomplete","off");
});
/**
 * @param $pages
 * @param opt_state 见ajaxgrid_operation_permission 函数 opt_state 参数说明
 * @returns
 */
function formpage_operation_permission($pages, opt_state) {
    if ($pages.size() == 0) {
        return ;
    } 
    $pages.each(function(i,page){
        var $page = $(page);
        opt_state = opt_state === 'upd'? '1023' : opt_state;
        opt_state = opt_state === 'add'? '1023' : opt_state;
        if(opt_state === 'show'){
            $page.find(".btn").not(".show-flow-chart").remove();
            $page.find(".form-control").attr("readonly","readonly").off().removeAttr("onclick","onfoucs").css("background-color","transparent");
            $page.find(".form-control,.input-group").addClass("noborder");
            $page.find(".required").removeClass("required").addClass("unrequired");
            $page.find(".ui-select").each(function(i,t){
                $(t).ui_select().readonly();
            })
            $page.find("select").not(".ui-select").attr("disabled","disabled").attr("readonly","true").css("background-color","transparent");
            
        } else {
            with ($page.find(".btn,.btn-link").filter(fn_filter_non_searchfield)){
                // 1(1b)      新增
                fn_btn_permission(filter(".btn-1,.add,.draft"),(opt_state & 1) !== 1);
                // 2(10b)     修改
                fn_btn_permission(filter(".btn-2,.update,.upd"),(opt_state & 2) !== 2);
                // 4(100b)    删除
                fn_btn_permission(filter(".btn-4,.delete,.del"),(opt_state & 4) !== 4);
                // 8(1000b)    导入
                fn_btn_permission(filter(".btn-8,.imp"),(opt_state & 8) !== 8);
                // 64(1000000b) 查看流程按钮
                fn_btn_permission(filter(".btn-64,show-flow-chart"),(opt_state & 64) !== 64);
                // 128(10000000b) 发起流程按钮
                fn_btn_permission(filter(".btn-128,.flow"),(opt_state & 128) !== 128);
                // 256(100000000b)
                fn_btn_permission(filter(".btn-256"),(opt_state & 256) !== 256);
                // 512(1000000000b)
                fn_btn_permission(filter(".btn-512"),(opt_state & 512) !== 512);
                filter(":hidden").remove();
            }
            // 当新增和修改都不包含时 
            if ((opt_state & 1) !== 1 && (opt_state & 2) !== 2) {
                //禁用所有可编辑元素
                $page.find("input:not([readonly])").not(".bs-style,.ui-select").filter(fn_filter_non_searchfield).attr("readonly","true").addClass("noborder").off().removeAttr("onclick","onfous");
                $page.find("textarea").attr("readonly","true").removeAttr("onclick","onfous");
                $page.find(".form-control").filter(fn_filter_non_searchfield).attr("readonly","readonly").off().removeAttr("onclick","onfoucs").css("background-color","transparent");
                $page.find(".bs-style").filter(fn_filter_non_searchfield).each(function(i,t){
                	$(t).bs_style().readonly();
                });                
                $page.find(".required").filter(fn_filter_non_searchfield).removeClass("required").addClass("unrequired");
                $page.find(".ui-select").filter(fn_filter_non_searchfield).each(function(i,t){
                    $(t).ui_select().readonly();
                }).attr("readonly","true").addClass("noborder readonly")
                $page.find("select").not(".ui-select").filter(fn_filter_non_searchfield).attr("disabled","disabled").attr("readonly","true").css("background-color","transparent");
            }
        }
        function fn_btn_permission($el,flag){
            return flag ? $el.hide() : $el.show(); 
        }
        // 过滤掉非 搜索部分的元素
        function fn_filter_non_searchfield(i, t) {
            return $(t).parents(".fixed-table-searchfield").length == 0;
        }
    });
}
/**
* @param opt_state 为二进制权限状态位
*   0(0b) 默认没有任何操作, 和show等价
*   1(1b)      新增
*   2(10b)     修改
*   4(100b)    删除
*   8(1000b)   导入
*  16(10000b)  复制
*  32(100000b) 列表是否显示复选框
*  64(1000000b) 查看流程按钮
* 128(10000000b) 发起流程按钮
* 256(100000000b) 核对、稽核按钮
*/
function ajaxgrid_operation_permission(selector,opt_state){
   var $ajaxgrid = $(selector);
   if ($ajaxgrid.size() == 0) {
       return ;
   } 
   opt_state = opt_state === 'upd'? '1023' : opt_state;
   opt_state = opt_state === 'add'? '1023' : opt_state;
   $ajaxgrid.find(".icon-roundadd,.icon-add_light,.icon-close_light,.icon-close3,.icon-copy,.icon-upload1,.imp,.btn.upd,.btn-link.upd,.btn-link.check,.btn-link.to-formal").hide();
   $ajaxgrid.find(".btn-1,.btn-2,.btn-4,.btn-8,.btn-16,.btn-32,.btn-64,.btn-128,.btn-256,.btn-512").hide();
   // 1(1b)      新增
   if ((opt_state & 1) === 1) {
       $ajaxgrid.find(".btn-1,.icon-roundadd,.icon-add_light").show();
   }
   // 2(10b)     修改
   if ((opt_state & 2) === 2) {
        $ajaxgrid.find(".btn-2,.btn.upd,.btn-link.upd").show();
   }
   // 4(100b)    删除
   if ((opt_state & 4) === 4) {
       $ajaxgrid.find(".btn-4,.icon-close3,.icon-close_light").show();
   }
   // 8(1000b)   导入
   if ((opt_state & 8) === 8) {
       $ajaxgrid.find(".btn-8,.icon-upload1,.imp").show();
   }
   // 16(10000b)  复制
   if ((opt_state & 16) === 16) {
       $ajaxgrid.find(".btn-16,.icon-copy").show();
   }
   // 32(100000b) 列表是否显示复选框
   if ((opt_state & 32) !== 32) {
       $ajaxgrid.find(".btn-32,[data-checkbox='true']").remove();
   }
   // 64(1000000b) 查看流程按钮
   if ((opt_state & 64) === 64) {
       $ajaxgrid.find(".btn-64,.btn-link.show-flow-chart").show();
   }
   // 128(10000000b) 发起流程按钮
   if ((opt_state & 128) === 128) {
       $ajaxgrid.find(".btn-128,.btn-link.flow").show();
   }
   // 256(10000000b) 列表是核对、稽核
   if ((opt_state & 256) === 256) {
       $ajaxgrid.find(".btn-256,.btn-link.check").show();
   }
   
   // 512(10000000b) 列表是入网
   if ((opt_state & 512) === 512) {
       $ajaxgrid.find(".btn-512,.btn-link.to-formal").show();
   }
}
function tableTDOperationShow(html, opt_state) {
   var $html =  $(html);
   opt_state = opt_state === 'show'? '0' : opt_state;
   opt_state = opt_state === 'upd'? '1023' : opt_state;
   opt_state = opt_state === 'add'? '1023' : opt_state;
   $html.filter("[title='修改'],[title='编辑'],.btn-link.upd,.btn-link.add,.btn-link.del,[title='删除'],.btn-0,.btn-1,.btn-2,.btn-4,.btn-8,.btn-16,.btn-32,.btn-64,.btn-128,.btn-256,.btn-512").hide();
   // 1(1b)      新增
   if ((opt_state & 0) === 0) {
       $html.filter(".btn-0").show();
   }
   // 1(1b)      新增
   if ((opt_state & 1) === 1) {
       $html.filter(".btn-link.add,.btn-1").show();
   }
   // 2(10b)     修改
   if ((opt_state & 2) === 2) {
       $html.filter("[title='修改'],[title='编辑'],.btn-link.upd,.btn-2").show();
   }
   // 4(100b)    删除
   if ((opt_state & 4) === 4) {
       $html.filter("[title='删除'],.btn-link.del,.btn-4").show();
   }
   // 64(1000000b) 查看流程按钮
   if ((opt_state & 64) === 64) {
       $html.filter("[title='流程'],.btn-64").show();
   }
   // 256(10000000b) 列表是核对、稽核
   if ((opt_state & 256) === 256) {
       $html.filter("[title='核对'],[title='稽核'],.btn-256").show();
   }
   var arr_html = [];
   for (var i = 0; i < $html.length; i++) {
       arr_html.push($html[i].outerHTML);
   }
   return arr_html.join("");
}
/**
 * 格式化金额
 * @param s 需要格式化的数字
 * @param n 默认精度
 * @param flag 是否添加货币符号
 * @returns
 */
function fmoney(s, n,flag) {
    if(s == undefined){
        return "";
    }
    if(s == ""){
        s = 0;
    }
    var msg = "";
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
    t = "";
    for (i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    if(flag){
        msg = "￥";
    }
    return msg + t.split("").reverse().join("") + "." + r;
}
//添加百分号
function fpercent(s,n,flag){
	if(parseFloat((s + "").replace(/[^\d\.-]/g, "")).toString() == "NaN"){return ''};
    var msg = "";
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    if(flag){
        msg = "%";
    }
    return s+msg ;
}
//秒数格式化成日期 add by sk
function fdatetime(value,partten){
	if(value == 0){
    	return "";
    }else if(value > -1){
    	var datetime = new Date(value * 1000);
        return datetime.format(partten);
    }else{
    	return value;
    }
}
// 小数转百分数
function to_percent(s,n,flag){
	if(parseFloat((s + "").replace(/[^\d\.-]/g, "")).toString() == "NaN"){return ''};
    var msg = "";
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")*100).toFixed(n) + "";
    if(flag){
        msg = "%";
    }
    return s+msg ;
}
//计算合计金额
function sum_price_tax(s, n,flag) {
    
	if(s == "" || s=="NaN" ){
        s = 0;
    }
    var sum=0;
    var msg = "";
    t = "";
    n = n > 0 && n <= 20 ? n : 2;
    for(var i=0; i<s.length;i++){
    	sum = s[i].SUM_PRICE_TAX;	
    }
    if(sum>0){

        s = parseFloat((sum + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        if(flag){
            msg = "￥";
        }
        return msg + t.split("").reverse().join("") + "." + r;
    }else{
    	
    	return "0";
    }

    
    

}

function sum_money(s, n, flag) {
    
	if(s == "" || s=="NaN" ){
        s = 0;
    }
    var sum=0;
    var msg = "";
    t = "";
    n = n > 0 && n <= 20 ? n : 2;
    for(var i=0; i<s.length;i++){
    	sum = s[i].SUM_MONEY;	
    }	
    if(sum>0){

        s = parseFloat((sum + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        if(flag){
            msg = "￥";
        }
        return msg + t.split("").reverse().join("") + "." + r;
    }

    return "0";

}

function sum_tax(s, n,flag) {
    
	if(s == "" || s=="NaN" ){
        s = 0;
    }
    var sum=0;
    var msg = "";
    t = "";
    n = n > 0 && n <= 20 ? n : 2;
    for(var i=0; i<s.length;i++){
    	sum = s[i].SUM_TAX;	
    }
    if(sum>0){

        s = parseFloat((sum + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        if(flag){
            msg = "￥";
        }
        return msg + t.split("").reverse().join("") + "." + r;
    }

    return "0";

}
/**
 * 驼峰实体转换成 大写的 下划线分割
 * @param entity
 * @returns
 */
function entity2db(entity){
    var db = {};
    for(var key in entity){
        var dbKey = key.replace(/([A-Z])/g,"_$1").toUpperCase();
        db[dbKey] = entity[key];
    }
    return db;
}
/**
 * 数据库下划线分割实体转换成驼峰
 * @param entity
 * @returns
 */
function db2entity(db){
    var re=/_(\w)/g;
    if(db instanceof Array) {
    	var entities = [];
    	for (var i = 0; i < db.length; i++) {
    		entities.push(db2entity(db[i]));
		}
    	return entities;
    } else {
    	var entity = {};
        for(var key in db){
            var entityKey = key.toLowerCase().replace(re,function ($0,$1){
                return $1.toUpperCase();
            });
            entity[entityKey] = db[key];
        }
        for(var key in entity){
        	if(key.indexOf("[realName]")==0){
        		entity[key.substring(10)] = entity[key]
        		
        	}
        }
        return entity;
    }
}
//
var func_page_reload = function(){location.reload();};
var func_empty = fn_empty = function(){};
(function($){
    'use strict';
    $.bs = $.bs || {};
    $.bs.form = $.bs.form || {};
    $.bs.grid = $.bs.grid || {};
    $.bs.table = $.bs.table || {};

    $.extend($.bs, {
        /**
         * @param /String/ msg 提示信息
         * @param /JSON  or String/opts 当类型为JSON时,参数如下，说明见注释：;当类型为Stirng时，该参数为提示信息等级level
         * @param /Function/ fn 提示关闭回调函数
         */
        tips : function (msg, opts, fn) {
            if (typeof arguments[1] == 'string') {
                opts = {
                    level: arguments[1]
                };
            }
            var CFG = {
                "success": [2000,true],
                "info": [2000,true],
                "warning": [10000,false],
                "danger": [10000,false]
            };
            var options = {
                level: 'warning', // success/info/warning/danger
                auto_close: CFG['warning'][1],//告警是否自动关闭，即使选择false提示框也会在90s以后自动关闭，默认值为 level== 'warning'的值，即不自动关闭
                delay: CFG['warning'][0], //告警在延时关闭时间，默认值为 level== 'warning'的值，即10s
                id: new Date().getTime(),//系统默认ID
                $target: $("body"),// 提示框插入位置
                append: "prepend",//提示框插入方式
                clear : false//插入提示框之前是否清除之前存在的提示框
            }
            if (typeof fn != 'function') {
                fn = func_empty;
            }
            opts.delay = opts.delay || CFG[opts.level||options.level][0];
            opts.auto_close = opts.auto_close === undefined ? CFG[opts.level||options.level][1] : opts.auto_close;

            if(opts.auto_close === true) {
                options.clear = true;
            }
            if (opts.auto_close === false) {
                opts.delay = 90000;
            }
            
            opts = $.extend({}, options, opts);
           
            var $before = $(".alert");
            if(opts.clear === true && $before.size() > 0){
                close("fast",$before,init);
            } else {
                init();
            }
            return opts.id;
            
            /**
             * 初始化对象
             */
            function init(){
                var id = opts.id;
                var html = "<div class=\"alert alert-" + opts.level + " alert-dismissable\" id='" + id + "'>";
                if (!opts.auto_close) {
                    html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>";
                } else {
                    html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" style=\"display:none\" aria-hidden=\"true\">&times;</button>";
                }
                html += msg + "</div>";
                
                $("#" + id).remove();//移除同名对象
                eval("opts.$target." + opts.append + "(html)"); // 插入html
                $("#" + id + " button").on("click", fn);// 设置 关闭回调函数
                // tips 将在 指定时间后关闭 
                setTimeout(function(){
                    close("slow",$("#" + id));
                }, opts.delay);
            }
           
            /**
             * 关闭
             * @param speed 关闭速度
             * @param $item 被关闭对象
             * @param after 关闭后回调
             * @returns
             */
            function close(speed,$item,after) {
                after = after|| function (){};
                $item.slideUp(speed,function () {
                    $item.find("button").click(); // 关闭 对象
                    $item.remove(); //移除  对象
                    setTimeout(after, 200);
                });
            }
        },
        tip_tools : (function() {
            var LVL = ["success",'info','warning','danger'];
            function compare_with_lvl (lvl0, lvl1) {
                var index0 = ArrayUtils.indexOf(LVL,lvl0);
                var index1 = ArrayUtils.indexOf(LVL,lvl1);
                return index0 - index1;
            }
            return {
                compare_with_lvl : compare_with_lvl,
                max_lvl : function(lvl0, lvl1) {
                    return compare_with_lvl(lvl0, lvl1) > 0 ? lvl0 : lvl1;
                }
            }
        })()
    });
    
    $.extend($.bs.grid, {
        tips: function (msg, opts, fn) {
            opts = opts || {};
            if (typeof arguments[1] == 'string') {
                opts = {
                    level: arguments[1]
                };
            }
            opts.$target = $(".data-grid:eq(" + (opts.grid_index || 0) + ")"),
            opts.append = "prepend";

            $.bs.tips(msg, opts, fn);
        }
    });
    
    function FN_BS_TABLE(){
        _OC.GRID_TABLE = _OC.OC_GRID_TABLE || {};
        var ROW_HEIGHT = 33,THEAD_ROW_HEIGHT = 40,PAGINATION_HEIGHT = 40;
        var sqlname,$cond,$grid,$table,height,default_options = {
            locale : _BOOTSTRAP.LOCALE,
            url: $ctx + '/omcs/baseAction/ajaxGrid.action', //请求后台的URL（*）
            method: 'get', //请求方式（*）
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: true, //是否启用排序
            sortOrder: "asc", //排序方式
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            height: 310,
            pageSize: 15,
            pageList: [15, 25, 50,100],
            //            search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true, //是否显示所有的列
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: false, //是否启用点击选中行
            uniqueId: "PID", //每一行的唯一标识，一般为主键列
            //                 showToggle : true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表
            maintainSelected: true, // 保留翻页前数据
            onPageChange: function (number, size) {  
//                 $table.data("reload",true);
            }
        },param_dictype;
        //得到查询的参数
        function queryParams(params) {
            var temp = {};
            temp.sqlname = sqlname;
            $cond.find("input,select ").each(function (i, v) {
                var val = $(v).val();
                //数组 
                if($.type(val) == 'array'){
                    temp["param." + $(v).attr("name")] = val.join(",");
                }else {
                    temp["param." + $(v).attr("name")] = val;
                }
            });
            if(this.sortName!=undefined &&this.sortName!=null){
//              temp["param." + "orderby"] = " order by " + this.sortName + " "+this.sortOrder;
              temp["param." + "orderby"] =  this.sortName + " "+this.sortOrder;
          
            }
            //处理数据字典参数
            param_dictype = {};
            $table.find("thead th[data-dictype]").each(function(i,t){
                param_dictype["paramDictype." + $(t).data("field")] = $(t).data("dictype");
            });
            return $.extend({}, temp,param_dictype, params);
        }
        var fn_formatter = {
            date : function (value, row, index) {
                return value.substr(0,10);
            },
            datetime : function (value, row, index) {
                return value.substr(0,19);
            },
            link : function (value, row, index) {
                return '<a href="javascript:void(0)">'+value+'</a>';
            },
            link_num : function (value, row, index) {
                if(Number(value) == 0 ){
                    return value;
                }
                return '<a href="javascript:void(0)">'+value+'</a>';
            },
            idx : function (value, row, index) {
                return index + 1;
            },
            fmoney : function (value, row, index) {
                return fmoney(value, 2,true);
            },
            fpercent : function (value, row, index) {
                return fpercent(value, 0,true);
            },
            fdatetime : function (value, row, index) {
                return fdatetime(value,"yyyy-MM-dd hh:mm:ss");
            },
            to_percent : function (value, row, index) {
                return to_percent(value, 2,true);
            },sum_price_tax : function (value, row, index) {
                return sum_price_tax(value, 2,true);
            },
            sum_money : function (value, row, index) {
                return sum_money(value, this.decimal === 0? 0 : (this.decimal || 2),true);
            },
            tooltip_infofill :function (value, row, index) {
                return  value + '<a class="iconfont icon-infofill text-after" tabindex="0"></a>';
            },
            sum_tax : function (value, row, index) {
                return sum_tax(value, this.decimal === 0? 0 : (this.decimal || 2), true);
            },
            footer_total :function (row) {
            	if(!row || row.length == 0){
                    return;
                }
            	var $table0 = this.table;
            	var field = this.field;
            	var title = this.title;
            	var total = title +' : '+ row[0][field];
            	$table0['clearCells'].call($table0,{index:row.length ,field:field});//index需要清除的行数，field需要清楚的字段名
                return  '<span>' + total + '</span>';
            },
            footer_sum : function (row) {
//                var sum = 0;
//                for (var i in row) {
//                    sum += row[i][field || this.field] || 0;
//                }
//                return ''  + sum.toFixed(this.decimal === 0? 0 : (this.decimal || 2));
                if(!row || row.length == 0){
                    return;
                }
                var $table0 = this.table;
                var data =  $table0["getSumData"].call($table0, this, true);
                if(!!this.formatter){
                    return (eval(this.formatter)).call(this,data,'',this.field);
                } else {
                    return data;
                }
            },
            // 作废
            footer_sum_fmoney : function (row) {
                var value = $.bs.table.fmt.footer_sum.call(
                this, row, field);
                return $.bs.table.fmt.fmoney(value, 2,true);
            },
            footer_sum_with_db : function (row) {
                if(!row || row.length == 0){
                    return;
                }
                var $table0 = this.table;
                var data =  $table0["getSumData"].call($table0, this);
                if(!!this.formatter){
                    return (eval(this.formatter)).call(this,data,'',this.field);
                } else {
                    return data;
                }
            },
            
            
            
            footer_sum_title : function () {
                return '合计';
            },
            ellipsis:function(value, row, index){
                var html = '';
                if(value == null || value == '' || value.length <= 10){
                    html = '<a style="text-decoration:none;color:#ddd" href="javascript:void(0)">'+value+'</a>';
                }else{
                    html = '<a style="text-decoration:none;color:#ddd" href="javascript:void(0)" title="'+value+'">'+value.substr(0,10)+' ...</a>';
                }
                return html;
            }
        }
        function fn_auto_height($table,opts){
            function iframe_filter(i, t) {
                if ($(t).is("#_my97DP iframe")) {
                    return null;
                }
                return t;
            } 
            // 表头高度
            var thead_size = !!opts.columns? opts.columns.length:$table.find("thead tr").size();
            var THEAD_HEIGHT = thead_size * (THEAD_ROW_HEIGHT + 2)  - (thead_size - 1) ;
            // 表Footer高度
            var TFOOT_HEIGHT = (opts.showFooter == true ? 1 : 0) * (THEAD_ROW_HEIGHT + 2) ;
            //获得iframe高度
            var bodyHeight = $(parent.document).find("iframe:visible").filter(iframe_filter).height() ||  $(document).height();
            //XXX 发现IE8有内嵌iframe使用该控件时，获取高度只有150
            var userAgent = window.navigator.userAgent.toLowerCase(); 
            if(/msie 8\.0/i.test(userAgent) && bodyHeight <= 150) {
                var realBodyHeight="";
                realBodyHeight = $(top.document).find("iframe:visible").filter(iframe_filter).height();
                bodyHeight = realBodyHeight - $(parent.document.body).height() + bodyHeight;
            } 
            var tableHeight = bodyHeight;
            if($(".serachdiv").attr("isOpenDefault") == "true"){
                tableHeight -= $(".serachdiv").height();
            }
            // 工具栏 高度
            tableHeight -= $table.parents("div:eq(0)").find(".toolbar-top").height();
            
            tableHeight -= $(".lujing").height();

            var opts_pagesize = {
                pageSize : opts.pageSize
            };
            if(opts.pageSize == undefined ){
                // 每页20
                if (tableHeight - THEAD_HEIGHT - TFOOT_HEIGHT >= 81 + ((ROW_HEIGHT + 1 ) * 17 - 1)) {
                    opts_pagesize.pageSize = 20;
                    opts_pagesize.pageList =[20, 50, 100];
                }
                // 每页15
                else if (tableHeight - THEAD_HEIGHT - TFOOT_HEIGHT >= 81 + ((ROW_HEIGHT + 1 ) * 12 - 1)) {
                    opts_pagesize.pageSize = 15;
                    opts_pagesize.pageList = [15, 25, 50, 100];
                }
                // 每页 10
                else if (tableHeight - THEAD_HEIGHT - TFOOT_HEIGHT >= 81 + ((ROW_HEIGHT + 1 ) * 5 - 1)) {
                    opts_pagesize.pageSize = 10;
                    opts_pagesize.pageList = [10, 25, 50, 100];
                }
                // 每页 5
                else {
                    opts_pagesize.pageSize = 5;
                    opts_pagesize.pageList = [5, 10, 25, 50, 100];
                }
            }
            opts_pagesize.height = 81 + THEAD_HEIGHT + ((ROW_HEIGHT + 1 ) * opts_pagesize.pageSize - 1) + TFOOT_HEIGHT + PAGINATION_HEIGHT;
            if(opts.width == "scroll" ){
                opts_pagesize.height += /(chrome|safari) \.0/i.test(userAgent) ? 17 : 20;
            }
            if (tableHeight <= opts_pagesize.height) {
                opts_pagesize.height = tableHeight;
            }
            opts = $.extend({}, opts, opts_pagesize);
            return opts;
        }
        return {
            THEAD_ROW_HEIGHT:THEAD_ROW_HEIGHT,
            ROW_HEIGHT:ROW_HEIGHT,
            fn_auto_height:fn_auto_height,
            reset: function (id) {
                id = typeof id === 'string' ? id : id.data;
                $grid = $("#" + id);
                $table = $grid.find('.table');
                $cond = $grid.find(".serachdiv");
                $cond.find("input,select ").not(":hidden").each(function (i, v) {
                    $(v).val("");
                });
            },
            reload: function (id) {
                id = typeof id === 'string' ? id : id.data;
                $grid = $("#" + id);
                $table = $grid.find('.fixed-table-body .table');
                sqlname = $grid.attr("sqlname");
                $cond = $grid.find(".serachdiv");
                if($cond.attr("isOpenDefault") != '2') {
                    $cond.attr("isOpenDefault") == "true" ? $cond.show() : $cond.hide();
                }
                $table.data("reload",true);
                $table.bootstrapTable('refresh',{
                    pageNumber:1
                });
            },
            refresh: function (id) {
                id = typeof id === 'string' ? id : id.data;
                $grid = $("#" + id);
                $table = $grid.find('.fixed-table-body .table');
                sqlname = $grid.attr("sqlname");
                $cond = $grid.find(".serachdiv");
                if($cond.attr("isOpenDefault") != '2') {
                    $cond.attr("isOpenDefault") == "true" ? $cond.show() : $cond.hide();
                }
                $table.bootstrapTable('refresh');
            },
            init: function (id,opt_state) {
                id = typeof id === 'string' ? id : id.data;
                $grid = $("#" + id);
                $table = $grid.find('.table');
                _OC.GRID_TABLE[id] = $table;
                $cond = $grid.find(".serachdiv");
                height = $table.data("height");
                sqlname = $grid.attr("sqlname");
                var opts = $.extend({
                        opt_state : opt_state,
                        toolbar: '#' + id + ' .ajax-grid-toolbar.toolbar-top', //工具按钮用哪个容器
                        searchField: '#' + id + ' .serachdiv', //工具按钮用哪个容器
                        queryParams: queryParams //传递参数（*）
                },$table.data());
                //设置搜索部分表单样式
                $cond.find("input:text,select").addClass("form-control");
                if("auto" === height ){
                    opts = $.extend({},default_options,fn_auto_height($table,opts))
                } else {
                    default_options.height = height;
                    opts = $.extend({},default_options,opts)
                }
                /*处理table自定义事件*/
                $.each($table.data(),function(i,v){
                    if(typeof v == 'string' && i.startWith("on")){
                        opts[i] = eval(v);
                    }
                });
                //加载表格
                $table.bootstrapTable(opts);
                //绑定事件
                $cond.on("click",".iconbtn.reset",id,$.bs.table.reset)
                     .on("click",".iconbtn.refresh",id,$.bs.table.reload);
            },
            fmt :fn_formatter
        }
    }
    $.extend($.bs.table, FN_BS_TABLE());
    $.bs.dropLayer = (function(){
        var body_overflow = "";
        return {
            show: function(){
                if($("body .drop_layer").size() == 0){
                    $("body").append('<div class="drop_layer"><div class="loading"></div></div>');
                } else {
                    $("body .drop_layer").show();
                }
                //滚动条位置
                var offset = $(document).scrollTop();
                $(".drop_layer").css("top",offset);
                body_overflow = body_overflow || $("body").css("overflow");
                $("body").css("overflow","hidden");
            },
            hide: function (){
                $("body .drop_layer").hide();
                $("body").css("overflow",body_overflow);
                $(".drop_layer").css("top",0);
            }
        }
    })();
    
    
    
   
})(jQuery);


function guid() {  
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {  
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);  
        return v.toString(16);  
    });  
} 
function func_my97_picked($dp){
    $(this).trigger("change");
}


$.postSync = function(url, data, success) {
    return $.ajax({
        url : url,
        data : data,
        type : "POST",
        async:false,
        dataType:'json',
        success : success
    });
};

/**
 * table中有调整显示隐藏的td时 对表格重新排版
 * @param $tables table的jquery对象
 * @param column_num 重绘的tr中td个数
 * @param re_draw_selector  需要被重绘的$table中的TR,所选tr必须连续，如果该参数为空那么默认全部tr都参与重绘。re_draw_selector 多组时可以传递数组
 * @returns
 */
function _table_re_draw($tables, column_num, re_draw_selectors) {
    var $tbody;
    $tables.each(function(index, t) {
        $tbody = $(t).find("tbody");
        re_draw_selectors = re_draw_selectors || ["tr"];
        if (typeof re_draw_selectors == 'object') {
            $.each(re_draw_selectors, re_draw);
        } else {
            re_draw(re_draw_selectors || "tr");
        }
        
    });
    function re_draw (re_draw_selector){
        var $before = $tbody.find(re_draw_selector).eq(0).prev();
        var $re_draw = $tbody.find(re_draw_selector);
        var $td = $re_draw.find("td").not(".blank");
        var $trs = [];
        for (var i = 0; i < $td.size();) {
            var $tr = $("<tr></tr>").addClass($re_draw.attr("class"));
            for (var j = 0; j < column_num; i++) {
                if ($td.eq(i).size() == 0) {
                    for (var k = 0; k < column_num - j; k++) {
                        $tr.append("<td class='blank'></td>")
                    }
                    break;
                }
                if ($td.eq(i).is(":visible")) {
                    j++;
                }
                $tr.append($td.eq(i));
            }
            $trs.push($tr);
        }
        $tbody.find(re_draw_selector).remove();
        $before.after($trs);
    }
}
function get_fractional_html(numerator,denominator){
    return "<span class='fractional'><span class='numerator'>"+numerator+"</span> <span class='fractional-line'/><span class='denominator'>"+denominator+"</span></span>";
}
var _url_tool = (function(){
    function parseUrl(url) {
        var a = document.createElement("a");
        a.href = url;
        return {
            source : url ,
            protocol: a.protocol.replace(":",''),
            real: a.protocol.replace(":",''),
            params: (function(){
                var ret = {},seg=a.search.replace(/^\?/,""),len,s;
                seg = a.hash.indexOf("&") > -1 ? (seg + a.hash).split("&") : seg.split("&");
                len= seg.length
                for(var i=len - 1;i>=0;i--){
                    if(!seg[i]){
                        continue;
                    }
                    var eq_index = seg[i].indexOf("=");
                    if(eq_index == -1){
                        seg[i-1] = seg[i-1]+ "&" +seg[i]; 
                    } else {
                        ret[seg[i].substring(0,eq_index)] = seg[i].substring(eq_index+1)||'';
                    }
                }
                return ret;
            })(),
            file:(a.pathname.match(/\/([^\/?#]+)$/i)|| [,''])[1],
            hash:a.hash.replace("#",''),
            path:a.pathname.replace(/^([^\/])/,'/$1'),
            relative:(a.href.match(/tps?:\/\/[^\/]+(.+)/)|| [,''])[1],
            segments:a.pathname.replace(/^\//,'').split('/')
        }
    }
    function getUrlParam(){
        return parseUrl(location.href).params;  
    }
    function format(path,params){
        return path + "?" +  $.param(params)
    }
    return {
        getUrlParam: getUrlParam,
        parseUrl: parseUrl,
        encodeParseUrl : function encodeParseUrl(url){
            var URL = parseUrl(url);
            URL.params["CVT"] = getUrlParam()["CVT"] || '';
            
            $.each(URL.params,function(i,v){
                URL.params[i] = decodeURI(v);
            });
            return format(URL.path, URL.params);
        },
        putParam : function(url,kv_json){
            var URL = parseUrl(url);
            for ( var k in kv_json) {
                URL.params[k] = decodeURI(kv_json[k]);
            }
            return format(URL.path, URL.params);
        }
    }
})()


function mergeCells(data,fieldName,colspan,target){
    //声明一个map计算相同属性值在data对象出现的次数和
    var sortArr = [],cntArr = [];
    for(var i = 0 ; i < data.length ; i++){
        var key = data[i][fieldName]
        if(sortArr[sortArr.length -1] == key){
            cntArr[cntArr.length - 1] = cntArr[cntArr.length - 1] + 1; 
        } else {
            sortArr.push(key);
            cntArr.push(1);
        } 
    }
    var index = 0;
    for (var i = 0; i < sortArr.length; i++) {
        var sort = sortArr[i];
        var cnt = cntArr[i];
        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: cnt});   
        index += cnt;
    }
}

/**
 * 公共核查组件
 */
var _CHK_RSLT = _CHK_RSLT || (function(){
    
    return {
        /**
         * table的数据 通过公共组件进行数据核查后，在table上进行标记功能
         * @param $table bootstrapTable表格对象
         * @param chk_table_name  核查数据的表名
         * @param uniqueId  数据主键标识
         * @returns void
         */
        sign : function sign($table,chk_table_name,uniqueId){
            var rows = $table.bootstrapTable('getData');
            var data_pid = [];
            for (var i = 0; i < rows.length; i++) {
                if((rows[i]["CHK_STATE"] & 4) == 4 || (rows[i]["CHK_STATE"] & 8) == 8){
                    data_pid.push(rows[i][uniqueId])
                }
            }
            $.post($ctx + "/common/comdatachkrslt/ajaxGetEntities.action",{
                tableName: chk_table_name,
                dataPid : data_pid.join(",")
            }).done(function(result){
                var dataGroupPid = "";// 参数用于标记 table 的验证结果状态
                /* 标记字段状态，设置字段样式、及事件操作 */
                if(result.level == 'success'){
                    var rslt = result.data;
                    if(rslt.length == 0){
                        return;
                    }
                    var fieldMap = {};
                    for (var i = 0; i < rslt.length; i++) {
                        dataGroupPid = rslt[i].dataGroupPid;
                        var idx = $table.find("thead tr th[data-field='"+rslt[i].columnName+"']").index();
                        var $td = $table.find("tr[data-uniqueid='"+rslt[i].dataPid+"'] td:eq("+idx+")");
                        $td.addClass("compare btn-link alarm chk-state").attr("title",rslt[i].errShortMsg);
                        $td.data("field",rslt[i].columnName);
                            if (!fieldMap[rslt[i].columnName]) {
                                fieldMap[rslt[i].columnName] = [ $td ];
                            } else {
                                fieldMap[rslt[i].columnName].push($td);
                            }
                    
                    }
                    $table.find("td.chk-state").on("click",function(evnt){
                        var row = _OC.GRID_TABLE["table0"].bootstrapTable("getData");
                        var field = $(evnt.target||event.srcElement).data("field");
                        var idx = $(evnt.target||event.srcElement).parents("tr").eq(0).data("index");
                        open2tab($ctx + "/common/comdatachkrslt/list.action?tableName="+chk_table_name+"&dataPid=" + row[idx][uniqueId]+"&columnName=" + field );
                    });
                }
                /* 标记 table 的验证结果状态 */
                // 根据 组ID 查询当前分组数据 标记，有未稽核的标记为未稽核，其他按异常等级返回高等级结果
                $.post($ctx + "/common/comdatachkrslt/ajaxGetPriorChkState.action",{
                    "entity.tableName": chk_table_name,
                    "entity.dataGroupPid" : dataGroupPid
                }).done(function(lvl){
                    $table.data("ComDataChkRslt.PriorChkState", lvl);
                })
            });
        },
        getPriorChkState : function ($table){
            return $table.data("ComDataChkRslt.PriorChkState");
        },
        /**
         * 打开核查结果列表页面
         * @param tableName
         * @param uniqueId
         * @param index
         * @returns
         */
        openList : function openList(tableId,tableName,uniqueId,index){
            var row = _OC.GRID_TABLE[tableId].bootstrapTable("getData");
            open2tab($ctx + "/common/comdatachkrslt/list.action?tableName="+tableName+"&dataPid="+row[index][uniqueId]);// ,{ 'title':'数据核查结果'})
        },
        openForm : function openForm(tableName, dataPid,columnName) {
            open2tab($ctx + "/common/comdatachkrslt/list.action?tableName="+tableName+"&dataPid="+dataPid +"&columnName=" + columnName );// ,{ 'title':'数据核查结果'})
        }
    }
})();
