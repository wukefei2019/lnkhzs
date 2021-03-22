var _BOOTSTRAP = _BOOTSTRAP || {
  LOCALE:'zh-CN',
  TOOLTIP : {
      "TEMPLATE" : '<div class="popover" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>',
      "TEMPLATE_MINI" : '<div class="popover mini" role="tooltip"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
  }  
};

/*******************************************************************************
 * bootstrap 扩展
 */
;+ function ($) {
    'use strict';
    $.bs = $.bs || {
        navigation: (function(){
            function set_tips ($el, num, css) {
                $el = $el.is("a")? $el : $el.find("a").eq(0);
                var label =$el.data("label");
                $el.text(label + "（" + num + "）");
                if(!css){
                    $el.removeAttr("class");
                } else {
                    $el.addClass(css);
                }
                // 获取nav对象
                var $nav = $el.parents("[role='navigation']").data("bs-navigation");
                if(!!$nav){
                    $nav.resize();
                }
            }
            return {
                set_tips: set_tips,
                /**
                 * 系统提供默认刷新tips方法
                 */
                fn_show_tips_system: function fn_show_tips_system($a,opts,j){
                    var url = opts.url[j];
                    var params = _url_tool.parseUrl(url).params;
                    if(!params.sqlname){
                       throw new Error("url's params not contains 'sqlname' ,can't user SYSTEM method of refresh tips method，please CUSTOM!!! ");
                    }
                    params.limit = 1;
                   
                }
            }
        })()
    };
}(jQuery);
;+ function ($) {
    'use strict';
    
    function BS(el, options) {
    	this.options = options;
    	this.$el = $(el);
    }
    BS.DEFAULTS = {
    		
    };
    $.bs = $.extend( $.bs || {},{
        navload : function(tab_id) {
            $("#" + tab_id + " iframe").contents().find("body").addClass("bs-tab");
        }
    });
    /**
	 * url 数组 配合tab使用
	 */
    BS.prototype.navigation = function(options){
    	var DEFAULTS = {
    			id  : "nav" + new Date().getTime(),
    			url:[],
    			theme : "default",
    			mode : "iframe",
    			frameHeight : undefined,
    			label : undefined,
    			show_tips_mode : 'CUSTOM',//  'SYSTEM'
    			refreshTips: [],
    			position: '',
    			tipsCss:[],
    	}
    	
    	var that = this,$el = that.$el;;
    	
    	function Navigation(options){
    	    var navigation = $el.data("bs-navigation"), _nav = this;
    	    if(!navigation || !!options) {
    	        options = $.extend({}, DEFAULTS, {id : that.$el.attr("id")},typeof options === 'object' && options);
    	        if(options.url.length !== options.label.length){
    	            throw new Error("url.length !== label.length error!!!");
    	        }
    	        if(options.tipsCss&& options.tipsCss.length> 0 && options.url.length !== options.tipsCss.length){
    	        	throw new Error("url.length !== tipsCss.length error!!!");
    	        }
    	        this.options = options;
    	        this.init();
    	        this.resize();
    	        this.refreshTips({callback:this.resize});
    	        $el.data("bs-navigation",navigation = this);
    	    }
    	    return navigation;
    	}
    	
    	
    	Navigation.prototype.init = function init(){
    	    var options = this.options;
    	    that.$el.attr({
    	        "id":options.id
    	    }).addClass("navbar navbar-" + options.theme);
    	    if(options.mode !== 'iframe') {
    	        throw new "组件不支持当前配置模式:" + options.mode;
    	    }
    	    var $el = that.$el;
    	    if(options.mode === 'iframe' && typeof options.label == 'object') {
    	        var frameHeight = options.frameHeight || $(document).height() - 87;
    	        
    	        if(frameHeight < 645) {$(".navbar").css("margin-bottom","0");frameHeight += 10;}
    	        
    	        var $tabs = $('<ul class="nav nav-tabs"></ul>');
    	        var $content = $('<div class="tab-content"></div>');
    	        for (var i = 0; i < options.label.length; i++) {
    	            $('<li style="display:inline-block"></li>').append($('<a href="#tab'+i+'" data-toggle="tab"></a>').text(options.label[i]).data("label",options.label[i])).appendTo($tabs);//.append();
    	        }
    	        for (var i = 0; i < options.url.length; i++) {
    	            $content.append('<div class="tab-pane in" id="tab'+i+'"><iframe frameborder="0" onload="$.bs.navload(\'tab'+i+ '\')" width="100%" height="'+frameHeight+'px"></iframe></div>');
    	        }
    	        var $tabWrap = $("<div class='nav-wrap'></div>").addClass(options.position).html($tabs);
    	        that.$el.html($tabWrap);
    	        that.$el.append($content);
    	        that.$el.find("li:eq(0),.tab-pane:eq(0)").addClass("active");
    	    }
    	    
    	    var $a = $el.find("a");
    	    // 绑定 tab切换事件
    	    $a.one('show.bs.tab',function(evnt){
    	        var index = $a.index(evnt.target||evnt.srcElement);
    	        $el.find(".tab-content .tab-pane iframe").eq(index).attr("src",options.url[index]);
    	    })
    	    // 显示当前激活的tab标签
    	    $el.find(".nav-tabs .active a").trigger("show.bs.tab");
    	}
    	Navigation.prototype.resize = function resize(){
    	    var options = this.options;
    	    var $el = that.$el;
    	    if(options.mode === 'iframe' && typeof options.label == 'object') {
    	        var $wrap =  that.$el.find(".nav-wrap");
    	        var $tabs =  that.$el.find("ul");
    	        // 设置元素 nav 宽度
    	        var width = 0;
    	        $wrap.find("li").each(function(i,t){
    	            width += $(t).outerWidth();
    	        });
    	        // 添加 滚动按钮
    	        $tabs.width(width + 2);
    	        if(width > that.$el.width() && $wrap.find(".btn.left,.btn.right").size() == 0){
    	            $wrap.prepend("<div class='btn left iconfont icon-back1'></div>");
    	            $wrap.append("<div class='btn right iconfont icon-right2'></div>");
    	            function wrapResize(){
    	                $wrap.width($el.width() - $wrap.find(".btn.left").outerWidth() * 2 - 6);
    	            }
    	            var default_step = $wrap.width() * .1;
//    	            //绑定滚动事件;
    	            $wrap.find(".btn.left").on("mousedown",function(){
                        var step =  $tabs.width() + $tabs.position().left - $wrap.width();
                        if(step > default_step) {
                            step = default_step;
                        }
                        if( step > 0){
                            $tabs.animate({left: $tabs.position().left - step}, 200);
                        }
                    });
    	            $wrap.find(".btn.right").on("mousedown",function(){
    	                var step =  $tabs.position().left * -1;
                        if(step > default_step) {
                            step = default_step;
                        }
    	                if(step > 0){
    	                    $tabs.animate({left: $tabs.position().left + step}, 200);
    	                }
    	            });
    	        }
    	    } else {
    	        $wrap.removeClass("icon-zuo_and_you");
    	    }
    	    
    	    var $a = $el.find("a");
    	    // 绑定 tab切换事件
    	    $a.one('show.bs.tab',function(evnt){
    	        var index = $a.index(evnt.target||evnt.srcElement);
    	        $el.find(".tab-content .tab-pane iframe").eq(index).attr("src",options.url[index]);
    	    })
    	    // 显示当前激活的tab标签
    	    $el.find(".nav-tabs .active a").trigger("show.bs.tab");
    	}
    	Navigation.prototype.refreshTips = function (tips){
    	    var options = $.extend({}, this.options, { refreshTips: tips });
    	    if(options.show_tips_mode == 'SYSTEM'){
    	        options.refreshTips = [];
    	        for (var j = 0; j < options.url.length; j++) {
    	            options.refreshTips[j] = $.bs.navigation.fn_show_tips_system;
    	        }
    	    }
    	    if(!!options.show_tips_mode){
    	        for (var j = 0; j < options.url.length; j++) {
    	            var fn = options.refreshTips[j];
    	            if(typeof fn == 'function'){
    	                fn($el.find("li a").eq(j), options, j);
    	            } else if(typeof fn == 'number' || typeof fn == 'string'){
    	                $.bs.navigation.set_tips($el.find("li a").eq(j), fn,options.tipsCss[j]||'');
    	            }
    	        }
    	    }
    	}
    	
    	
    	var navigation = new Navigation(options); 
    	return navigation;
    }
    $.fn.bs = function(option){
    	var value,args = Array.prototype.slice.call(arguments, 1);	
    	this.each(function () {
            var $this = $(this),
            	data = $this.data('bootstrap.bs'),
            	options = $.extend({}, BS.DEFAULTS, $this.data(), typeof option === 'object' && options);
            if (typeof option === 'string') {
                if (!data) {
                	$this.data('bootstrap.bs', (data = new BS(this, options)));
                }
                value = data[option].apply(data, args);
                if (options === 'destroy') {
                    $this.removeData('bootstrap.bs');
                }
            }
            if (!data) {
                $this.data('bootstrap.bs', (data = new BS(this, options)));
            }
        });
    	return typeof value === 'undefined' ? this : value;
    }
    $.fn.bs.Constructor = BS;
    
    
    
}(jQuery);


+ function ($) {
    'use strict';
    var CLASS_KEY = "bs-multi-select";
    var defaults = {
            wrapClass: 'multiple',
            dataKey: CLASS_KEY //实例化后对象存在select的data键值，方便后续通过data('ui-select')取出；
        };
    $.fn.MULTI_SELECT = {};
    $.fn.multi_select = function(options) {
        var _this = $(this), _num = _this.length;
        // 当要实例的对象只有一个时，直接实例化返回对象；
        if (_num === 0) {
            return ;
        };
        // 当要实例的对象只有一个时，直接实例化返回对象；
        if (_num === 1) {
            return new MainClass(_this, options);
        };
        // 当要实例的对象有多个时，循环实例化，不返回对象；
        if (_num > 1) {
            _this.each(function(index, el) {
                new MainClass($(el), options);
            })
        }
    }
    /**
     * MainClass对象
     * @param {[jQuery]} el  [jQuery选择后的对象，此处传入的为单个元素]
     * @param {[object]} opt [设置的参数]
     */
    function MainClass($el, opt) {
        this.el = $el;
        this._opt = $.extend({}, defaults, opt,this.el.data());
        this._doc = $(document);
        this._win = $(window);
        this._init();
        return this;
    }
    MainClass.prototype = {
            // 判断是否为IE (6-11);
            _isIE: !!window.ActiveXObject || "ActiveXObject" in window,
            // init初始化;
            _init: function() {
                // 不能进行ui-select 初始化
                this.el.addClass("ui-select");
                var _data = this.el.data(this._opt.dataKey);
                // 如果已经实例化了，则直接返回
                if (_data){
                    // 重新给对象元素赋值，以防对象被复制后，_data指向原页面元素
                    _data.el = this.el;
                    this._wrap = _data._wrap = this.el.parents(".input-group");
                    return _data;
                } else{
                    this.el.data(this._opt.dataKey, this);
                }
                this._setHtml(); // 组建元素
                this._bindEvent(); // 绑定事件
            },

            // 组建并获取相关的dom元素;
            _setHtml: function() {
                var that = this,clazz = this.el.attr("class"), clazz_width, clazz_noborder;
                clazz_width = clazz.replace(/.*(width[0-9]{2,4}).*/i, '$1');
                this.el.addClass(CLASS_KEY);
                var _isHide = (this.el.css('display') == 'none');
                if (_isHide) {
                    this.el.show().css('visibility', 'hidden');
                }
                var _w = 180;//this._opt.width ? this._opt.width - 17 : this.el.outerWidth() - 17;
                this.el.wrap('<div tabindex="0" class="ui-select-wrap ' + this._opt.wrapClass + '" ></div>')
                    .after('<div class="ui-select-input"></div><i class="ui-select-arrow"></i>');
                this._wrap = this.el.parent('.ui-select-wrap').css('width', _w);
                this._input = this.el.next('.ui-select-input');
                this.el.prop('disabled') ? this.disable() : null;
            },
            _bindEvent: function() {
                var _this = this;
                // 模拟后的select点击事件；
                _this._wrap.on({
                    // 点击事件
                    'click': function(e) {
                        var id = new Date().getTime();
                        $.fn.MULTI_SELECT["multi_select_options_" + id] = function(row,fn){
                            var option = [];
                            var _val = _this.el.val();
                            _this.el.find('option').each(function (index, el) {
                                var _option = $(el);
                                if(!_option.text()){
                                    return ;
                                }
                                option.push({
                                    value: _option.prop('value'),
                                    text: _option.text(),
                                    selected: _option.is(":selected"),//_option.prop('selected') ? 'selected' : '',
                                    disabled: _option.prop('disabled') ? ' disabled' : ''
                                });
                            });
                            return option;
                        };
                        
                        $.bs.modal.open($ctx + "/omcs/common/prompt_option_checkbox.jsp?fn_option=multi_select_options_" + id + "&value=text&text=text",{
                            "class" : "mini",
                            "width" : "450",
                            "height" : "600",
                            "title" : _this.el.attr("title"),
                            "callback":function(item){
                                if(!!item){
                                    _this.val($.map(item,function(t){return t.text}));
                                }
                            }
                        });
                            
                        
                        
                        
                        
                        
                        
                        
                        
//                        // 列表元素点击事件；
//                        if (_this._disabled)
//                            return;
//                        if (e.target.tagName == 'LI') {
//                            var _self = $(e.target),
//                                _val = _self.attr('data-value');
//                            if (_self.hasClass('disabled'))
//                                return _this._isIE ? e.stopPropagation() : null;
//                            _this.val(_val);
//                            _this._triggerClick(_val, _this.items.eq(_self.index()));
//                        }
//
//                        // IE下点击select时其他select无法收起的bug
//                        if (_this._isIE) {
//                            e.stopPropagation();
//                            _this._allWrap = _this._allWrap || $('.ui-select-wrap');
//                            _this._allWrap.not(_this._wrap).removeClass('focus');
//                            _this._doc.one('click', function() {
//                                _this._allWrap.removeClass('focus');
//                            });
//                        }
                        _this._wrap.toggleClass('focus');
//                        _this._setListCss();
                    }
                });
                return _this;
            },
            // 获取或设置值；
            val: function(value) {
                var _val = this.el.val();
                if (value === undefined)
                    return _val;
                this.el.val(value);
                var text = value.join(",")
                this._input.html(text).attr('title', text);
            },
            //隐藏
           hide: function() {
                this._wrap.hide();
                return this;
            },

            // 显示
            show: function() {
                this._wrap.show();
                return this;
            },
        };   

}(jQuery);



/*******************************************************************************
 * bootstrap-Table 扩展
 */
;+ function ($) {
    'use strict';
    
    $.extend($.fn.bootstrapTable.columnDefaults, {
        editor: undefined
    });
    $.fn.bootstrapTable.methods.push('getChangeData');
    $.fn.bootstrapTable.methods.push('getSumData');
    $.fn.bootstrapTable.methods.push('reset');
    $.fn.bootstrapTable.methods.push('hover');
    $.fn.bootstrapTable.methods.push('sumColumn');
    var BootstrapTable = $.fn.bootstrapTable.Constructor,
    	_initTable = BootstrapTable.prototype.initTable,
        _initHeader = BootstrapTable.prototype.initHeader,
        _initBody = BootstrapTable.prototype.initBody,
        _initRow = BootstrapTable.prototype.initRow,
        _resetFooter = BootstrapTable.prototype.resetFooter,
        _refresh = BootstrapTable.prototype.refresh;
    

    BootstrapTable.prototype.initTable = function() {
        this.options.sum_data = {};
        this.options.columns["table"] = this;
    	_initTable.apply(this, Array.prototype.slice.apply(arguments));
    	
    	var permission = this.options["permission"];
    };
    BootstrapTable.prototype.initHeader = function() {
        _initHeader.apply(this, Array.prototype.slice.apply(arguments));
        var that = this;
        // visibleColumns = {},
        // html = [];
        that.header.editors = [];
        that.header.customSqls = [];
        that.header.dictypes = [];
        that.header.customParams = [];
        that.header.relationCells = [];
        that.header.effects = [];
        that.header.maxWidthes = [];
        that.header.minWidthes = [];
        that.header.maxlengthes = [];
        that.header.maxes = [];
        that.header.mines = [];
        that.header.steps = [];
        $.each(this.options.columns, function (i, columns) {
            $.each(columns, function (j, column) {
                //
                if (!!column.maxlength) {
                    that.header.maxlengthes[column.fieldIndex] = column.maxlength;
                }
                // 
                if (column.editor !== undefined) {
                    if (typeof column.fieldIndex !== 'undefined') {
                        that.header.editors[column.fieldIndex] = column.editor;
                        that.header.customSqls[column.fieldIndex] = column.customSql;
                        that.header.dictypes[column.fieldIndex] = column.dictype;
                        that.header.customParams[column.fieldIndex] = column.customParam;
                        that.header.relationCells[column.fieldIndex] = column.relationCell;
                        that.header.effects[column.fieldIndex] = column.effect;
                        that.header.maxWidthes[column.fieldIndex] = column.maxWidth;
                        that.header.minWidthes[column.fieldIndex] = column.minWidth;
                        that.header.maxes[column.fieldIndex] = column.max;
                        that.header.mines[column.fieldIndex] = column.min;
                        that.header.steps[column.fieldIndex] = column.step;
                    }
                }
            });
        });
    };
    BootstrapTable.prototype.initBody = function() {
        var fixedScroll = arguments[0],that = this;
        // 操作按钮权限
        var opt_state = that.options.opt_state;
        if( opt_state !== undefined && that.header.events.length !== 0 ){
            $.each(this.header.formatters, function(j, formatter) {
                if(formatter === undefined || typeof formatter !== 'string' || typeof eval(formatter) !== 'function') {
                    return
                }
                if(that.$header.eq(0).find("th").eq(j).text() == "操作"){
                    var fmt_show = "fmt_show_" + guid().replace(/-/g,"");
                    $.bs.table.fmt[fmt_show] = function(value, row, index){ return tableTDOperationShow( eval(formatter + "(value, row, index)"),opt_state === 'show'? 0 : opt_state)};
                    that.header.formatters[j] = "$.bs.table.fmt." + fmt_show;
                }
            });
        }
        // 继承
        _initBody.apply(this, Array.prototype.slice.apply(arguments));
        // editor
        if (that.header.editors.length === 0) {
            return;
        }
        that.$body.find('>tr:not(.no-records-found)').each(function () {
            for ( var fieldIndex in that.header.editors) {
                var editor = that.header.editors[fieldIndex], $tr = $(this), field = that.header.fields[fieldIndex];
                var $html = $tr.find(that.options.cardView ? '.card-view' : 'td').eq(fieldIndex).find(">");
                $html.on("keyup",keyup);// .triggerHandler("keyup");
                $html.on("change",keyup);// .triggerHandler("change");
                keyup.call($html);
                function keyup(e){
                    var index = $tr.data('index'),
                    row = that.data[index],
                    value = row[field];
                    fn_evnt['value-change'].apply(this, [e, value, row, index]);
                }
            }
        });
      that.$el.bs_style();
      autocomplete_helper_ready();
    };
    BootstrapTable.prototype.resetFooter = function() {
        var that = this;
        $.each(this.columns, function (i, column) {
            column["table"] = that;
        });
        _resetFooter.apply(this, Array.prototype.slice.apply(arguments));
    };
    var fn_evnt = {
        'value-change': function (e, value, row, index) {
            var orig = row[this.name];
            var dest = this.value;
            
            var $t,$wrap;
            $t = $wrap = $(this);
            if ($t.is(".ui-select")) {
                $wrap = $t.ui_select()._wrap;
            } else if ($t.is(".bs-style")){
                $wrap = $t.bs_style()._wrap;
            }
            if(row["[ORIG]" + this.name] === undefined && row[this.name] != this.value){
                row["[ORIG]" + this.name] = row[this.name];
                $wrap.addClass("value-change");
            } else if(row["[ORIG]" + this.name] !== undefined && row["[ORIG]" + this.name] == this.value){
                delete row["[ORIG]" + this.name];
                $wrap.removeClass("value-change");
            }
            row[this.name] = this.value;
            
        }
    };
    BootstrapTable.prototype.initRow = function() {
        var item = arguments[0], i = arguments[1], rowData = arguments[2], parentDom = arguments[3];
        this.options["table"] = this;
        var TRs = _initRow .apply(this, Array.prototype.slice.apply(arguments)), $TR = $(TRs), that = this;
//        if(this.options.permission == 0 || this.options.permission == 'show'){
//        	return TRs;
//        }
        
        if(that.header.maxlengthes.length !== 0 ){
            $.each(this.header.fields, function(j, field) {
                if(that.header.maxlengthes[j] === undefined){
                    return;
                }
                var maxlength = that.header.maxlengthes[j], $td = $TR.find("td:eq(" + j + ")");
                var value = rowData[i][field];
                
                if( !!value && value.length > maxlength){
                    $td.attr("title",value).text(value.substr(0,maxlength)+' ...');
                }
            });
        }
        
        
        
        
        if(that.header.editors.length !== 0 ){
            $.each(this.header.fields, function(j, field) {
                if(that.header.editors[j] === undefined){
                    return;
                }
                var editor = that.header.editors[j], $td = $TR.find("td:eq(" + j + ")");
                var maxWidth = that.header.maxWidthes[j]||'auto';
                var minWidth = that.header.minWidthes[j];
                var max = that.header.maxes[j];
                var min = that.header.mines[j];
                var step = that.header.steps[j];
                var $ht;
                switch (editor) {
                    case "input:text":
                    	minWidth = minWidth || 100;
                    	$ht = $("<input type='text' name='"+field+"' value='"+(rowData[i][field]||"")+"' class='form-control' />");
                        break;
                    case "input:textarea":
                        minWidth = minWidth || 100;
                        $ht = $("<textarea  name='"+field+"' class='form-control' >"+(rowData[i][field]||"")+"</textarea>");
                        break;
                    case "input:number":
                        minWidth = minWidth || 100;
                        $ht = $("<input type='number' name='"+field+"' value='"+(rowData[i][field]||"")+"' class='form-control number textaglin-right' />");
                        break;
                    case "input:money":
                        step = "0.01";
                    	$ht = $("<input type='number' name='"+field+"' value='"+(rowData[i][field]||'')+"' class='form-control bs-style-money' />");
                        break;
                    case "input:money2":
                        step = "0.01";
                        $ht = $("<input type='number' name='"+field+"' value='"+(rowData[i][field]||'')+"' class='form-control bs-style-money' data-prefix='' data-suffix='网管币 '/>");
                        break;
                    case "select:eoms:sqlquery":
                    case "select:eoms:dictype":
                    	minWidth = minWidth || 140;
                    	// 绑定 影响数据函数
                    	$td.on("change",function(evnt){
                    		var effects = that.header.effects[j];
                    		var parentid =  $(evnt.target).val();
                    		if(!!effects){
                    			var entity = $(evnt.target).find(":selected").data("entity");
                    			for ( var key in effects) {
                    				rowData[i][effects[key]] = entity[key];
                    				// TODO 没有实现更新 元素
                    			}
                    		}
                    	})
                    	var op_datas;
                    	var sqlname = that.header.customSqls[j];
                    	var dictype = that.header.dictypes[j];
                    	if(!!sqlname){
                    		op_datas = getDictypeData(that,j,sqlname,'sqlname');
                    	} else {
                    	    op_datas = getDictypeData(that,j,dictype,'dictype');
                    	}
                		var relationCells = that.header.relationCells[j];
                		if(relationCells){
                			for ( var k in relationCells) {
                				var relationCell = relationCells[k];
                				op_datas = $.grep(op_datas,function(t,index){
                					return t[k] == rowData[i][relationCell];
                				});
                				
                				
                				// 绑定级联事件
                				var relationTd = $TR.find("td [name='" + relationCell + "']");
                				relationTd.on("change",function(evnt){
                					var parentid =  $(evnt.target).val()
                					// 级联调整
                					var width = $s.width();
                					$s.width(width);
                					var items;
                					if(!!sqlname){
                					    items = getDictypeData(that,j,sqlname,'sqlname');
                                    } else {
                                        items = getDictypeData(that,j,dictype,'dictype');
                                    }
                					
                					items = $.grep(items,function(t,index){
                    					return t[k] == parentid;
                    				});
                					var row = rowData[i];
                					var curVal =  $s.val();
                					if(!rowData[i]["[ORIG]" + field]){
                						rowData[i]["[ORIG]" + field] = curVal;
                					} 
                					var origVal =  rowData[i]["[ORIG]" + field];
                					$s.find("option:gt(0)").remove()
                            		for (var m = 0; m < items.length; m++) {
                            			var item = items[m];
                            			var $option = $("<option value='"+item["DIVALUE"]+"'>"+item["DINAME"]+"</option>").data("entity",item);
                            			if(item["DIVALUE"] == curVal){
                            				$option.attr("selected","selected");
                        				} 
                            			if(item["DIVALUE"] == origVal){
                        					$option.addClass("orig");
                            			}
                            			$s.append($option);
                            		}
                			    })
                            }
                        }
                		var $s = $("<select name="+field+" style='' class='form-control'><option/></select>");
                		for (var m = 0; m < op_datas.length; m++) {
                			var op_data = op_datas[m];
                			var $option = $("<option value='"+op_data["DIVALUE"]+"'>"+op_data["DINAME"]+"</option>").data("entity",op_data);
                			if(op_data["DIVALUE"] == rowData[i][field]){
                				$option.attr("selected","selected").addClass("orig");
                			}
                			$s.append($option);
                		}
                		$ht = $s;
//                    	}
                    	break;
                    case "bs_style:openwin":
                    	$ht = $("<input type='search' name='"+field+"' value='"+(rowData[i][field]||'')+"' class='form-control width160 bs-style-opwin bs-autocomplete "+field+"' />");
                    	break;
                    default:
                        break;
                }
            	$ht.css("maxWidth",maxWidth);
            	$ht.css("minWidth",minWidth);
            	max && $ht.attr("max",max);
            	min && $ht.attr("min",min);
            	step && $ht.attr("step",step);
                $td.html($ht);
            });
            
            
            
        }
        if(that.options.permission == 0 || that.options.permission == 'show'){
            formpage_operation_permission($TR,that.options.permission);
        }
        return $TR[0];
    };
    function getDictypeData(that,column,dictype,mode){
    	var op_datas;
    	if(!that.dictype){
    		that.dictype = {};
    	}
    	if(!(op_datas = that.dictype[dictype])){
    		if(mode == 'sqlname'){
    			var ajaxParam = {} ;
    			ajaxParam.sqlname = dictype;
    			var params = that.header.customParams[column];
    			for ( var key in params) {
    				var val;
    				if(params[key].startWith("jq:")){
    					var $t = $(params[key].substring(3));
        				var val = $t.val();
    					if($t.is(".ui-select")) {
    						val = $t.ui_select().val();
    					}
    				} else if(params[key].startWith("str:")){
    					val = params[key].substring(4);
    				}
    				ajaxParam["param." + key] =  val;
				}
    			op_datas = $.parseJSON($.postSync($ctx + "/omcs/baseAction/ajaxRQuery.action",ajaxParam).responseText);
    			that.dictype[dictype] = op_datas;
    		}
    		if(mode == 'dictype'){
    		    op_datas = [];
    		    var result = $.parseJSON($.postSync($ctx + "/dicManager/ajaxGetRootItsmByDicType.action",{dictype:dictype}).responseText);
    		    for (var i = 0; i < result.data.length; i++) {
                    var data = result.data[i];
                    var op_data = {};
    		        for(var k in data){
    		            op_data[k.toUpperCase()] = data[k];
    		        }
    		        op_datas.push(op_data);
                }
    		    that.dictype[dictype] = op_datas;
    		}
    	}
    	return op_datas;
    }
    
    
    BootstrapTable.prototype.hover = function (checked, index) {
        var $el = this.$selectItem.filter(sprintf('[data-index="%s"]', index)).prop('checked', checked);
        this.data[index][this.header.stateField] = checked;
        this.updateSelected();
        this.trigger(checked ? 'check' : 'uncheck', this.data[index], $el);
    };
    
    
    BootstrapTable.prototype.reset = function (index) {
        var rowData = this.data[index];
		   for ( var key in rowData) {
			   if(key.startWith("[ORIG]")){
				   var REAL_NAME = key.substring(6);
				   rowData[REAL_NAME] = rowData[key];
				   delete rowData[key];
			   }
		   }
		   this.updateRow(index,rowData);
    };
    BootstrapTable.prototype.getAllSelections = function () {
    	var that = this;
    	return $.grep(this.options.all_data || this.options.data, function (row) {
    		return row[that.header.stateField];
    	});
    };
    BootstrapTable.prototype.getChangeData = function () {
        var that = this;
        return $.grep(this.options.all_data||this.options.data, function (row) {
            var change = false;
            for ( var key in row) {
                if(change = key.indexOf("[ORIG]") == 0){
                    break;
                }
            }
            if(change){
                return row;
            }
            return change;
        });
    };
    
    BootstrapTable.prototype.load = function (data) {
        var fixedScroll = false;
        // #431: support pagination
        if (this.options.sidePagination === 'server') {
            this.options.totalRows = data[this.options.totalField];
            fixedScroll = data.fixedScroll;
            data = data[this.options.dataField];
        } else if (!$.isArray(data)) { // support fixedScroll
            fixedScroll = data.fixedScroll;
            data = data.data;
        }
        // TODO 如果数据在 all_data里存在，并且state为true,那么 data.state = true
        var uniqueId = this.options.uniqueId,stateField = this.header.stateField;
        if (!!this.options.maintainSelected) {
            // 表格为 执行reload方法后 清除所选内容
            if(this.$el.data("reload") === true){
                this.options.all_data = data;
                this.$el.data("reload", false);
            }
            // 表格为 执行refresh 或翻页
            else {
                var all_data = this.options.all_data = this.options.all_data || [];
                var that = this;
                var temp_all_data = [];
                for (var i = 0; i < data.length; i++) {
                    if (all_data.length == 0) {
                        temp_all_data.push(data[i]);
                        continue;
                    }
                    // 当新数据在历史数据中不存在时，将数据放入数据中
                    if (!ArrayUtils.contains(all_data,data[i],function(obj0,obj1){ return obj0[uniqueId] == obj1[uniqueId]; })) {
                        temp_all_data.push(data[i]);
                    }
                }
                this.options.all_data = this.options.all_data.concat(temp_all_data);
                
                var all_data = this.options.all_data || [];
                for (var i = 0; i < data.length; i++) {
                    for (var j = 0; j < all_data.length; j++) {
                        if (data[i][uniqueId] == all_data[j][uniqueId]) {
                            data[i][stateField] = all_data[j][stateField];
                            all_data[j] = data[i];
                        }
                    }
                }
            }
        }
        this.initData(data);
        this.initSearch();
        this.initPagination();
        this.initBody(fixedScroll);
    };
    
    
    /**
     * option.data 与option.all_data 合并以data为准
     */
    function combine2All(all_data, data, uniqueId, stateField) {
        for (var i = 0; i < data.length; i++) {
            for (var j = 0; j < all_data.length; j++) {
                if (data[i][uniqueId] == all_data[j][uniqueId]) {
                    all_data[j][stateField] == data[i][stateField];
                }
            }
        }
        return all_data;
    }

    
    
    BootstrapTable.prototype.sumColumn = function (sum_opts) {
        var row = this.getData();
        var field = sum_opts.column.field;
        var result = 0;
        for (var i in row) {
            result += row[i][field || this.field] || 0;
        }
        this.options.sum_data[sum_opts["param.columnName"]] = result;
        return result;
    };
    BootstrapTable.prototype.sumColumnWithDB = function (sum_opts) {
        var default_sum_opts = {
            url : $ctx + '/omcs/baseAction/ajaxGrid4SumCol.action',
            "param.columnName" :''
        }
        sum_opts = $.extend({},this.options.queryParams(),default_sum_opts,sum_opts);
        if(!sum_opts["param.columnName"]){
            throw new Error("'columnName' for sum is null!");
        }
        var result = $.postSync(sum_opts.url,sum_opts).responseText;
        
        this.options.sum_data = this.options.sum_data ||{};
        this.options.sum_data[sum_opts["param.columnName"]] = result;
        return result;
    };
    BootstrapTable.prototype.getSumData = function (column, not_db, not_cache) {
        var field = typeof column == 'string'? column : column.field;
        this.options.sum_data = this.options.sum_data ||{};
        if(!!not_cache || this.options.sum_data[field] === undefined) {
            if(!!not_db) {
                this.sumColumn({
                    column : column,
                    "param.columnName" : field
                })
            } else {
                this.sumColumnWithDB({
                    "param.columnName" : field
                })
            }
        }
        return this.options.sum_data[field];
    };
    BootstrapTable.prototype.refresh = function (columnName) {
        this.options.sum_data = {};
        _refresh.apply(this, Array.prototype.slice.apply(arguments));
    };
    
    
    BootstrapTable.prototype.clearCells = function (options) {
        const row = options.index;
        let col = this.getVisibleFields().indexOf(options.field);
        const $tr = this.$body.find('>tr');
        let $td;
        if (this.options.detailView && !this.options.cardView) {
          col += 1;
        }
        if (row < 0 || col < 0 || row > this.data.length) {
        	return;
        }
        for (let i = 0; i < row ; i++) {
        	$td = $tr.eq(i).find('>td').eq(col);
        	$td[0].innerHTML='';
        }

      }
    
    
}(jQuery);


;+ function($){
    $.fn.readonly = function(){
        $(this).attr("readonly","readonly").addClass("noborder").off();
    }
}(jQuery)