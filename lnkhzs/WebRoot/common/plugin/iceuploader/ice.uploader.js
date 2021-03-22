;(function($, window) {

    /*
    * dataset参数
    * data-attach:上传附件ID
    * data-modify:[] 可删除附件过滤条件
    * data-owner: data-modify中传owner时，取此参数进行过滤
    * */
	var path = window.location.pathname.substring(0, window.location.pathname.indexOf('/',1));
    var defaults = {
    	btnname:'选择附件',
    	cachemode:false,
        autoup:true,
        editable:false,
        attachid:null,
        uploadurl:path+'/attachAjax/uploadAjax.action', //上传URL $ctx+'/attachajax/uploadAjax.action?savePath='+savePath+'&attachmentGroupId='
        dataurl:path+'/attachAjax/attachsAjax.action?attachmentGroupId=',       //初始化URL，加载数据用
        deleteurl:path+'/attachAjax/deleteAjax.action?attachmentId=',     //删除URL
        downloadurl:path+'/attachAjax/downloadAttachment.action?downloadIds=',   //下载URL
        savepath:'default',
        deletesuccess:'success', //删除成功标识
        filenum:undefined,
        filelimit:undefined,
        filesize:undefined,
        forcedel:false,
        accept:{},
        attachattr:{        //返回的数据字段对应
            id:'pid',
            attachid:'attachid',
            name:'name',
            owner:'owner',
            uptime:'uptime',
            isself:'isself'
        }
    }
    var $options = {};
    var cache = {};

    function error(msg) {
        if(window.console){
            window.console.log(msg);
        }
    }

    function init(eles, options){
        if (!WebUploader.Uploader.support()) {
            error('上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。');
            return;
        }
        var opts = $.extend({}, defaults, options);
        return eles.each(function() {
            var ele = $(this);
            iceupload(ele, opts);
        });
    }

    function iceupload(ele, options){
    	var $attach = options.attachid;
        $attach = $attach ? $attach : ele.data('attachid');
        $attach = $attach ? $attach : ele.attr('id');
        //console.log($attach)
        var $id = 'iceuploader_'+$attach;
        var iceuploader = $(icetemplate(options.editable,options.btnname,$attach)).appendTo(ele);
        datainit($attach, iceuploader, options);
        webuploaderinit($attach, iceuploader,options)
        

        
    }
    
    function datainit(id, ele, options){
    	var $attachul = ele.find('.iceuploader-attaths');
    	if(options.dataurl){
    		if(options.cachemode&&cache[id]){
    			for(var v in cache[id]){
    				addattachli($attachul,cache[id][v],options.attachattr,options.editable,options.forcedel)
				}
    		}else{
    			var url = options.dataurl+id + '&d='+new Date().getTime();
    			$.get(url,function(response){
        			if(response){
        				if(typeof response === 'string'){
        					response = JSON.parse(response);
        				}
        				if(options.cachemode){
        					cache[id] = response;
        				}
        				
        				$attachul.append('<li class="empty" style="color:#555">无附件</li>')
        				for(var v in response){
        					addattachli($attachul,response[v],options.attachattr,options.editable,options.forcedel)
        				}
        			}
        		});
    		}
    		
    	}
    	
    }
    
    function addattachli(attachul,data,attachattr,editable,forcedel){
    	attachul.find('.empty').hide();
    	attachul.prepend(eleparam(data,attachattr,editable,forcedel))
    }
    
    function webuploaderinit(id, ele, options){
    	var $picker = 'iceuppicker_'+id;
    	var $batch = 'iceupbatch_'+id;
		var $attachul = ele.find('.iceuploader-attaths');
		var $progress = ele.find('.iceuploader-progress');
		var $percent = $progress.find('.progress-bar');
		var d = new Date().getTime();
		var url =  options.uploadurl+(options.uploadurl.indexOf('?')>-1?'&':'?')+'savePath='+(options.savepath||'default')+'&attachmentGroupId='+id+'&d='+d;
		
		var webuploaderoptions = {
                // swf文件路径
                swf: path+'/common/plugin/iceuploader/webuploader/Uploader.swf',
                // 文件接收服务端。
                server: url,
                //deleteServer: options.deleteurl,
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#' + $picker,
                //不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: false,
                fileNumLimit: options.filenum,
                fileSizeLimit: options.filelimit,
                fileSingleSizeLimit: options.filesize,
                accept: options.accept
            };
        var uploader = WebUploader.create(webuploaderoptions);

        if(options.autoup){
            uploader.on('fileQueued', function (file) {
                $percent.css('width', '0');
                uploader.upload();
            });
        }
        //进度
        uploader.on('uploadProgress', function (file, percentage) {//进度条事件
            $progress.addClass('show')
            $percent.show();
            $percent.css('width', percentage * 100 + '%');
        });
        //上传成功
        uploader.on('uploadSuccess', function (file, response) {//上传成功事件
            addattachli($attachul,response,options.attachattr,true,options.forcedel)
            if(options.cachemode&&response){
            	if(cache[id]==null){
            		cache[id]=[];
            	}
            	response.file = file;
            	cache[id].push(response);
            }
            setTimeout(function() {
            	$progress.removeClass('show');
            	//$percent.hide();
            	$percent.css('width', '0');
			}, 1000);
           
        });
        
        $('#'+$batch).on('click', function(){
            if(!options.downloadurl){
            	return;
            }
        	var ids = [];
            $attachul.find('li').each(function(k,v){
            	var id = $(v).attr('id');
            	if(id){
            		ids.push(id)
            	}
            })
            if(ids.length>0){
            	downloadfile(options.downloadurl,ids.join(','))
            }
        })
       
        //删除事件
        $attachul.on('click', '.iceuploader-deletebtn', function () {
        	if(options.deleteurl){
        		 var $ele = $(this);
                 var id = $ele.parent().parent().attr('id')
                 deletefile($attachul, id, options, function(file){
                	 if(file){
                		 uploader.removeFile(file.id,true)
                	 }
                	 if($attachul.find('li').length <= 1){
            		 	$attachul.find('.empty').show();
            		 }
                 });
        	}
        });
        $attachul.on('click', '.iceuploader-filename', function () {
        	if(options.downloadurl){
        		var $ele = $(this);
            	var id = $ele.parent().parent().attr('id')
            	downloadfile(options.downloadurl, id);
        	}
        	
        });
        $attachul.on('mouseover', '.iceuploader-filename', function () {
        	var $this = $(this)
        	var w = $this.width();
        	var pw = $this.parent().width();
        	if(w>pw){
    			$this.animate({
        			left:pw-w
        		},3000);
        	}
        });
        $attachul.on('mouseout', '.iceuploader-filename', function () {
        	var $this = $(this)
			$this.animate({
    			left:0
    		},100);
        });
    }
    
    function downloadfile(downloadurl, id){
        window.open(downloadurl+id);
    }
    
    function deletefile(ele, id, options, call){
    	if(options.deleteurl){
    		var url = options.deleteurl+id+'&d='+new Date().getTime()
    		$.get(url,function(response){
    			var file;
        		if(response&&options.deletesuccess===response){
        			var parentid = ele.parent().attr('data');
        			ele.find("li#"+id).remove();
        			if(options.cachemode){
        				var ca = cache[parentid];
        				if(ca){
        					for(var a in ca){
        						if(ca[a][options.attachattr.id]===id){
        							file = ca[a].file;
        							ca.splice(a,1);
        						}
        					}
        				}
        			}
        		}
        		if(typeof call == 'function'){
        			call(file);
        		}
        	});
    	}
    }

    function icetemplate(editable,btnname,id){
        return '<div class="iceuploader" id="iceuploader_'+id+'" data="'+id+'">' +
            '       <div class="iceuploader-btn '+(editable?'':'ineditable')+'" id="iceuppicker_'+id+'">'+btnname+'</div>' +
            '       <div class="iceupbatch-btn " id="iceupbatch_'+id+'">批量下载</div>' +
            '       <div class="iceuploader-progress '+(editable?'':'ineditable')+'">' +
            '           <div class="progress-bar progress-bar-info progress-bar-striped" style="width: 0%;">' +
            '           </div>' +
            '       </div>' +
            '       <ul class="iceuploader-attaths">' +
            '       </ul>' +
            '</div>'
    }


    function eleparam(data,attachattr,editable,forcedel){
    	if(data&&data[attachattr.id]){
    		var self = data[attachattr.isself];
    		var del = (editable&&(forcedel||self===true||self==='true'||self===1||self==='1'))?true:false;
    		return '<li id="'+data[attachattr.id]+'" title="'+data[attachattr.name]+'"class="'+(del?'iceuploader-delete':'')+'">'+
    			'<div class="iceuploader-file"><a class="iceuploader-filename">'+data[attachattr.name]+'</a>'+
    			'<div style="width:150px;float:right;">'+(data.createtime?longtodate(data.createtime):'-')+'</div><div style="width:200px;float:right;">'+(data.creatername?data.creatername+'('+data.createrloginmae+')':'-')+'</div>'+
    			'</div><div class="iceuploader-deletecontainer">'+(del?'<i class="iceuploader-deletebtn"></i>':'')+'</div></li>'
    	}else{
    		return '';
    	}
    	
    }
    
    function longtodate(t){  
		if(t == null || t == "" || t <= 0){
			return "-";
		}
		
		var f = 'yyyy-MM-dd hh:mm:ss'		
		var d = new Date();
	    var offset =  d.getTimezoneOffset() * 60000  + 8 * 3600000;
	    
	    var date =  new Date(parseInt(t*1000)+offset);
	    
	    var o = {  
            "M+": date.getMonth() + 1,  
            "d+": date.getDate(),  
            "h+": date.getHours(),  
            "m+": date.getMinutes(),  
            "s+": date.getSeconds(),  
            "q+": Math.floor((date.getMonth() + 3) / 3),  
            "S": date.getMilliseconds()  
        };  
        if (/(y+)/.test(f)) {  
            f = f.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(f)) {  
                f = f.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return f;  
	}
	

    $.fn.iceuploader = function(options) {
        var _this = this;
        loadscript(function(){
        	init(_this, options);
        })
    }
    
    function loadscript(call){
    	if(typeof WebUploader == 'undefined') {
            var csspath = path+'/common/plugin/iceuploader/webuploader/webuploader.css';
            var jspath = path+'/common/plugin/iceuploader/webuploader/webuploader.min.js';
            var fontpath = path+'/common/plugin/iceuploader/fonts/iconfont.css'
            $("<link>").attr({ rel: "stylesheet", type: "text/css", href: fontpath }).appendTo("head");
            $("<link>").attr({ rel: "stylesheet", type: "text/css", href: csspath }).appendTo("head");
            $.getScript( jspath, function( data, status, jqxhr ) {
                if(jqxhr.status!==200){
                    error("请检查webuploader的路径是否正确!")
                }else{
                	if(typeof call == 'function'){
                    	call();
                    }
                }
            });
        }else{
        	if(typeof call == 'function'){
            	call();
            }
        }
    }
    $.iceuploader = function(options,call){
    	$options = options;
    	var _this = this;
    	loadscript(call)
        
    }
    $.iceuploader.reload = function(ele, options){
    	if(typeof ele == 'string'){
    		ele = $(ele);
    	}
    	if(options){
    		$options = $.extend({}, $options, options);
    	}
    	loadscript(function(){
    		init(ele, $options);
    	})
    };
    
    
})(jQuery, window);