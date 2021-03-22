;+function($){
    "use strict";
    // 默认实例化配置
    var defaults = {
            dataKey: 'bs-style', // 实例化后对象存在select的data键值，方便后续通过data('ui-select')取出；
            btnStyle:'btn',
            prefixType : '',
            prefix : '',
            suffixType : '',
            suffix : '',
            extClass : ''
    }, all_selector = [ ".form-control.bs-style-opwin", ".form-control.bs-style-money",
            ".form-control.bs-style-money-year", ".form-control.bs-style-power", ".form-control.bs-style-percent",
            ".form-control.bs-style-meter", ".form-control.bs-style-meter2", ".form-control.bs-style-hour",".bs-style-date" ];
    
//    $.bs_style = function(){
//        $(all_selector.join(",")).bs_style();
//    }
    $.fn.bs_style = function(options) {
        var _this = $(this), _num = _this.length;
        // 当要实例的对象只有一个时，直接实例化返回对象；
        if (_num === 0) {
            return ;
        };
        if(!_this.is(all_selector.join(","))){
            return _this.find(all_selector.join(",")).bs_style(options);
            
        }
        // 默认参数
        if(arguments.length == 0 || arguments.length === 1 && arguments[0] === undefined ){
            var result = null ;
            this.each(function(i,t){
                var $t = $(t);
                if ($t.is(".bs-style-opwin")) {
                    // 按钮
                    result = $t.bs_style({suffixType:'btn',suffix:'搜'});
                } else if ($t.is(".bs-style-money")) {
                    // 金额
                    result = $t.bs_style({prefixType : "txt",prefix :'￥',suffixType:'txt',suffix:'元',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-money-year")) {
                    // 金额
                    result = $t.bs_style({prefixType : "txt",prefix :'￥',suffixType:'txt',suffix:'元/年',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-power")) {
                    // 功率
                	result = $t.bs_style({suffixType:'txt',suffix:'KW.H',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-percent")) {
                    // 百分比
                    result = $t.bs_style({suffixType:'txt',suffix:'%',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-meter")) {
                    // 米
                    result = $t.bs_style({suffixType:'txt',suffix:'米',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-meter2")) {
                    // 平方米
                    result = $t.bs_style({suffixType:'txt',suffix:'㎡',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-date")) {
                    // 天
                    result = $t.bs_style({suffixType:'txt',suffix:'天',extClass:'number textaglin-right'});
                } else if ($t.is(".bs-style-hour")) {
                    // 小时
                    result = $t.bs_style({suffixType:'txt',suffix:'小时',extClass:'number textaglin-right'});
                }
            })
            return result;
        }
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
        // 当元素个数为0时，不执行实例化。
        var $that = $(this),_num = $that.length;
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
 // MainClass 原型链扩展。
    MainClass.prototype = {
            
        // 判断是否为IE (6-11);
        _isIE: !!window.ActiveXObject || "ActiveXObject" in window,
        // init初始化;
        _init: function() {
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
        },

        // 组建并获取相关的dom元素;
        _setHtml: function() {
            var _isHide = (this.el.css('display') == 'none'), clazz = this.el.attr("class"), clazz_width, clazz_noborder;
            clazz_width = clazz.replace(/.*(width[0-9]{2,4}).*/i, '$1');
            clazz_width = clazz_width === clazz ? "" : clazz_width;
            clazz_noborder = this.el.is(".noborder") ? 'noborder' : '';
            
            if (_isHide) {
                this.el.show().css('visibility', 'hidden');
            }
            if(this.el.attr('type') == 'number' && !!this.el.val()){
            	var value = this.el.val();
            	var step = this.el.attr('step')|| '0.01';
            	var n = step.substring(step.indexOf(".")+1).length;
            	value = parseFloat(value).toFixed(n);
            	this.el.val(Number(value));
            }
            
            this.el.addClass('bs-style').addClass(this._opt.extClass).removeClass(clazz_width).removeClass(clazz_noborder)
                .wrap($("<div class='input-group'></div>").addClass(clazz_width).addClass(clazz_noborder));
            
            if (this._opt.prefixType == 'btn') {
                this.el.before("<span class='input-group-btn'><button class='"+ this._opt.btnStyle +" search btn-default' type='button'>" + this._opt.prefix + "</button></span>");
            } else if (this._opt.prefixType == 'txt') {
                this.el.before("<div class='input-group-addon'>" + this._opt.prefix + "</div>")
            }
            if (this._opt.suffixType == 'btn') {
                this.el.after("<span class='input-group-btn'><button class='"+ this._opt.btnStyle +" search btn-default' type='button'>" + this._opt.suffix + "</button></span>");
            } else if (this._opt.suffixType == 'txt') {
                this.el.after("<div class='input-group-addon'>" + this._opt.suffix + "</div>");
            }
            this._wrap = this.el.parents(".input-group")
        },
        // 禁用bs_style；
        readonly: function() {
            this._disabled = true;
            this.el.prop('readonly', true);
            this._wrap.addClass('readonly').removeAttr('tabindex');
            this._wrap.find(".input-group-btn .btn").attr("disabled","disabled")
            return this;
        },
        //启用 bs_style；
        writable: function() {
            this._disabled = false;
            this.el.removeClass('readonly').removeAttr("disabled").removeAttr('readonly');
            this._wrap.removeClass('readonly');
            this._wrap.find(".input-group-btn .btn").removeAttr("disabled").removeAttr("readonly");
            return this;
        }
    };   
}(jQuery);
