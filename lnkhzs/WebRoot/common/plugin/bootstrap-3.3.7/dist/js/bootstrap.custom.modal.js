(function($){
    'use strict';
    $.bs = $.bs || {};
    $.bs.modal = $.bs.modal || {};

    $.extend($.bs.modal,{
        close : function(args) {
            var dialog;
            if(!args || !args.data || !args.data.id) {
                dialog = _OC.MODAL["active"];
            }else {
                dialog = _OC.MODAL[args.data.id] ; 
            }
            delete _OC.MODAL["active"];
            var fn = dialog.data("options")["callback"];
            if(typeof fn == 'function'){
                fn.apply(this,arguments);
            }
            dialog.find("[data-dismiss=modal]").trigger("click");
        },
        remove : function(args) {
            var id,selector;
            if(!args || !args.data || !args.data.id) {
                selector = "div.modal.in"; 
            }else {
                id = args.data.id;
                selector = "#" + id; 
            }
            setTimeout(function(){
                $(selector+ ",.modal-backdrop").remove();
                !!id && delete _OC.MODAL[id];
            },500);
        },
        load : function(modal_id) {
            $("#" + modal_id + " iframe").contents().find("body").addClass("Dialog")
            $.bs.modal.getTitle(modal_id);
        },
        getTitle : function(modal_id) {
            if(!modal_id){
                return ;
            }
            var title = $("#" + modal_id + " iframe").contents().find("head>title").text();
            $("#" + modal_id + " .modal-title").text(title);
        },
        open: function(url,opts) {
            /* 获得最外层 jquery 对象 */
            var win = !!top ? top : window;
            var $ = win.$;
            /* 插入CSS样式表 */
            var content = $("meta[name='system-name']").attr("content");
            if(content != $ctx && content != $ctx + "-protal"){
                top.document.head.appendChild($('<meta name="system-name" content="' + $ctx + '-protal"/>')[0]);
                var links = [
                    "/common/plugin/bootstrap-3.3.7/dist/css/bootstrap.css",
                    "/common/style/nmc/css/base/base.css",
                    "/common/plugin/bootstrap-3.3.7/dist/css/bootstrap.custom.css",
                    "/common/plugin/bootstrap-3.3.7/dist/css." + $theme + "/bootstrap.custom.css"];
                for (var i = 0; i < links.length; i++) {
                    var link = top.document.createElement("link");
                    link.rel = "stylesheet";
                    link.href = $urlPrefix + links[i];
                    top.document.head.appendChild(link)
                }
                var jsList = ["/common/plugin/bootstrap-3.3.7/dist/js/bootstrap.custom.modal.js"];
                for (var i = 0; i < jsList.length; i++) {
                    var js = top.document.createElement("script");
                    js.type = "text/javascript";
                    js.src = $urlPrefix + jsList[i];
                    top.document.head.appendChild(js)
                }
            }



            if(!!url && !!_url_tool){
                url = _url_tool.encodeParseUrl(url);
            }
            
            var options = {
                 "class":"",
                 "backdrop":"static",
                 "keyboard":"false",
                 "show":"true",
                 "id":new Date().getTime(),
                 width:800,
                 height:600,
                 title:'',
                 "hidden.bs.modal" : function(){
                     
                 }
            };
            /* 如果是ajaxGrid 弹出的模态窗口 ，则 自动刷新父页面  begin */
            if($(event.target||event.srcElement).size() >= 1 && $(event.target||event.srcElement).parents(".data-grid").size() >= 1){
                var table_id = $(event.target||event.srcElement).parents(".data-grid").attr("id");
                opts = $.extend({},options,{
                    "hidden.bs.modal" : function(){
                        window.$.bs.table.refresh(table_id);
                    }
                },opts);
            }
            /* 自动刷新父页面  end */
            else {
                opts = $.extend({},options,opts);
            }
            var $html =$("<div class=\'modal fade \' id=\'"+opts.id+"\' tabindex=\'-1\' role=\'dialog\' aria-labelledby=\'myModalLabel\' aria-hidden=\'false\'>\
                            <div class=\'modal-dialog "+opts["class"]+"\' style=\'width:"+opts.width+"px;\' >\
                            <div class=\'modal-content\' >\
                                <div class=\'modal-header\'>\
                                    <button type=\'button\' class=\'close\' data-dismiss='modal'>&times;</button>\
                                    <h4 class=\'modal-title\' id=\'myModalLabel\'>"+opts.title+"</h4>\
                                </div>\
                                <div class=\'modal-body\' >\
                                    <iframe name=\'dialog_page\' onload='$.bs.modal.load(\"" + (!opts.title? opts.id : '')+"\")' width=\'100%\' height=\'"+opts.height+"px\' frameborder=\'no\' border=\'0\' src=\'"+url+"\'></iframe>\
                                </div>\
                            </div>\
                        </div>\
                    </div>");
            if($("#" + opts.id).size() > 0){
                $("#" + opts.id).remove();
            } 
            var $dialog = $html.modal(opts);
            $("#" + opts.id).find(".modal-header .close").on("click",{id : opts.id}, this.remove);
            $("#" + opts.id).modal({backdrop: 'static', keyboard: false});
            if(typeof opts["hide.bs.modal"] == 'function'){
                $html.on("hide.bs.modal",opts["hide.bs.modal"]);
            }
            if(typeof opts["hidden.bs.modal"] == 'function'){
                $html.on("hidden.bs.modal",opts["hidden.bs.modal"]);
            }
            $html.data("options",opts);
            win._OC = win._OC || {};
            win._OC.MODAL = win._OC.MODAL || {};
            win._OC.MODAL[opts.id] = $dialog;
            win._OC.MODAL["active"] = $dialog;
        },
        importPage : function(url,callback) {
             this.open(url,{
                "class" : "mini",
                width : "800",
                height : "300",
                title : "导入页面",
                "hidden.bs.modal":callback ||function(){}
            });
        },
        editPage : function(url,opts) {
            this.open(url,$.extend({},{
                "class" : "mini",
                width : "800",
                height : "300",
                title : "编辑"
            },opts));
        }
    });
})(jQuery);