
; + function($,_OC){
	var default_options = {
	        busiDataId : $("#pid").val(),
	        bussinessDesc : $("#bussiness_desc").val(),
	        $container : $(".panel-heading:eq(0)"),
	        btn_class : "btn floatRight10",
	        flow_chart_btn_display: "block",
	        flow_record_btn_display: "block",
	        /**
	         * 判断按钮是否应该显示
	         */
	        fn_btn_is_show_judge : function(btn_name, data) {
				return data.status == "1";
			},
	        fn_show_flow_chart : function(event, opts) {
	            openFlowChart(opts.uri, opts.deal["schema"], {
	                "deal.baseSn" : opts.deal["baseSn"]
	            });
	        },
	        fn_show_flow_record : function(event, opts) {
	            open2tab($ctx + "/workflow/sheet/query/BaseInfoViewBpp.jsp?baseschema="+opts.deal["schema"] + "&baseid=" + opts.deal["baseid"]);
	        },
	        fn_flow_click : {
	        	"审批不通过" : fn_flow_nopass_btn,
	        	"作废" : fn_flow_cancel_btn,
	        	"DEFAULT_BTN" : fn_flow_default_btn
	        },
	        page_refresh: function(){
	        	if(!parent){
	        		window.close();
	        	}else if(!parent.$.bs){
	        			location.reload();
	        		}else{
	        			parent.$.bs.modal.close();
	        	}
	        	
	        },
	        fn_flow_click_after: {
	        	"DEFAULT_BTN" : function (result){
		        	bootbox.alert(result.msg,function(){
	    				_OC.FLOW_LOAD.opts.page_refresh();
	    				
	    			});
		        	
		        }
	        },
	        $form : undefined
	    }
	
	_OC.fn_flow_load = function(uri,opts) {
		return new _OC_Flow_Load(uri,opts);
	}
//	_OC.fn_flow_load_default_opts = default_options;
	function _OC_Flow_Load(uri,opts){
		this.opts = opts;
		if(uri === undefined){
			return _OC.FLOW_LOAD;
		}
		this._init(uri,this.opts);
		_OC.FLOW_LOAD = this;
		return this;
	}
	
	_OC_Flow_Load.prototype._init = init;
	_OC_Flow_Load.prototype.getOpts = function(){
		return opts
		};
	return _OC_Flow_Load;
	
	
	
	function init(uri) {
		var opts = this.opts = $.extend(true,{},default_options,{
			uri: uri,
	        busiDataId : $("#pid").val(),
	        bussinessDesc : $("#bussiness_desc").val(),
	        $container : $(".panel-heading:eq(0)"),
		},this.opts);
		if(opts.deal === undefined){
			var result = $.parseJSON($.postSync($ctx + uri + "/ajaxGetDeal.action", {
	    		busiDataId: opts.busiDataId
	    	}).responseText);
			if(result.level == 'success'){
				opts.deal = result.data; 
			} else {
				alert("加载流程操作信息失败");
				return;
			}
		}
		var arr_btn_name='';
		var dealTime='';
		 if(!!opts.deal){
			 arr_btn_name = opts.deal.actionText.split(","); 
			 dealTime = opts.deal.dealTime;
		 }
		
		for (var i = 0; i < arr_btn_name.length; i++) {
			var action_text = arr_btn_name[i];
			//判断按钮是否可以显示
			if (!opts.fn_btn_is_show_judge(action_text, opts.deal)) {
				continue;
			}
			//插入按钮
			$btn = $("<a class='" + opts.btn_class+ "'>" + action_text + "</a>");
			opts.$container.append($btn);
			$btn.data("deal",opts.deal);
			// 绑定按钮事件
			$btn.on("click", function (e) {
				var $btn = $(e.srcElement || e.target);
				var btn_text = $btn.text();
				// 根据按钮label获取对应可执行方法
				var fn = opts.fn_flow_click[btn_text];
				if (fn === undefined) {
					fn = opts.fn_flow_click["DEFAULT_BTN"];
				}
				var deal = $btn.data("deal");
				deal["actionText"] = btn_text;
				fn.call(this, e, opts, deal);
			});
			
		}
//		// 添加 查看流程按钮
//		$btn = $("<a class='" + opts.btn_class+ " show-flow-chart' style='display:"+opts.flow_chart_btn_display+"'>流程图</a>");
//		$btn.on("click", function (event) {
//			opts.fn_show_flow_chart.call(this, event, opts);
//		});
//		opts.$container.append($btn);
//		// 添加 查看记录按钮
//		$btn = $("<a class='" + opts.btn_class+ " show-flow-record' style='display:"+opts.flow_record_btn_display+"'>流程记录</a>");
//		$btn.on("click", function () {
//			opts.fn_show_flow_record.call(this, event, opts);
//		});
//		opts.$container.append($btn);
		
		if(typeof opts.callback == 'function'){
			opts.callback.call(this)
		}
    }
	function fn_flow_nopass_btn(evnt,opts, deal) {
    	//$(".btn,.btn-link").off();
    	var btn_text = typeof evnt === 'string'? evnt : $(evnt.target||evnt.srcElement).text();
		var fn = opts.fn_flow_click["DEFAULT_BTN"];
		bootbox.prompt({
			title: "请输入审批意见",
			inputType: "textarea",
			callback: function (text) {
				if ($(event.srcElement || event.target).data("bbHandler") == 'confirm') {
					opts.deal["actionDesc"] = text;
					fn.call(this, btn_text, opts, opts.deal);
				}
			}
		});
    }
	function fn_flow_cancel_btn(evnt,opts, deal) {
		//$(".btn,.btn-link").off();
		var btn_text = typeof evnt === 'string'? evnt : $(evnt.target||evnt.srcElement).text();
		var fn = opts.fn_flow_click["DEFAULT_BTN"];
		bootbox.prompt({
			title: "请输入作废意见",
			inputType: "textarea",
			callback: function (text) {
				if ($(event.srcElement || event.target).data("bbHandler") == 'confirm') {
					opts.deal["actionDesc"] = text;
					fn.call(this, btn_text, opts, opts.deal);
				}
			}
		});
	}
	/**
	 * @param evnt 事件对象，也可以是触发事件的按钮名称
	 */
    function fn_flow_default_btn(evnt,opts,cb) {
    	var btn_text = typeof evnt === 'string'? evnt : $(evnt.target||evnt.srcElement).text();
    	if(typeof cb != 'function'){
    		cb = opts.fn_flow_click_after["DEFAULT_BTN"];
    	}
    	bootbox.confirm("流程即将流转，是否继续?",function(result){
    		if(result){
    		    $.bs.dropLayer.hide();
    		    var param = {
                        "assMap.assingers": opts.assingers,
                        "deal.pid" : opts.deal.pid,
                        "deal.actionText" : btn_text,
                        "deal.baseSn" : opts.deal.baseSn,
                        "deal.stepNo" : opts.deal.stepNo,
                        "deal.schema" : opts.deal.schema,
                        "deal.bussinessDataId" : opts.busiDataId,
                        "deal.summary" : opts.deal.summary,
                        "deal.taskId" : opts.deal.taskId,
                        "deal.actionDesc" : opts.deal.actionDesc || '',
                        "deal.childRole01": opts.deal.childRole01,
                        "deal.bussinessDesc": opts.bussinessDesc,
                        "deal.mstBaseSn": opts.deal.mstBaseSn
                    }
    		    if(!!opts.$form){
    		        var serialize = opts.$form.serializeArray();
    		        var entity = {};
    		        for ( var idx in serialize) {
    		            param[serialize[idx]["name"]] = serialize[idx]["value"];
    		        }
    		    }
    		    
    		    // 后台请求
                $.post($ctx + opts.uri + "/ajaxDeal.action", $.extend({}, param, opts.entity), cb);
    
    		}
    	});
    }
}(jQuery,_OC);
