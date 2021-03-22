function extend(obj1,obj2){
    for(var attr in obj2){
        obj1[attr] =  obj2[attr];
    }
}
function SetStep(arg){
    this.body=document.body;
    this.opt = {
        show:false,
        content:'.stepCont',
        pageCont:'.pageCont',
        imgWidth:20,
        stepContainerMar:20,
        nextBtn:'.nextBtn',
        prevBtn:'.prevBtn',
        steps:['服务问题发起','服务方案制定','服务方案审核','服务方案执行及进展','服务效果评估'], 
        //pageClass:'',//分页的类或则id
        stepCounts:5,//总共的步骤数
        curStep:1,//当前显示第几页
        animating:false,
        showBtn:true,//是否生成上一步下一步操作按钮
        clickAble:true,//是否可以通过点击进度条的节点操作进度
        selStep:1, // 追加默认选中的节点值
        selPID:'', // 追加默认选中的节点值
        onLoad: function(){

        }
    }
    this.init(arg)
}
//初始化 生成页面的进度条和按钮
SetStep.prototype.init=function(arg){
    var _that=this;
    extend(this.opt,arg);
    this.opt.stepCounts=this.opt.steps.length;
    this.content=$(this.opt.content);
    this.pageCont=this.content.find(this.opt.pageCont)
    var w_con=$(this.content).width();
    var w_li=(w_con-this.opt.stepContainerMar*2)/this.opt.stepCounts/2;
    var stepContainer=this.content.find('.ystep-container');
    this.stepContainer=stepContainer;
    var stepsHtml=$("<ul class='ystep-container-steps' style='font-size:15px'></ul>");
    var stepDisc = "<li class='ystep-step ystep-step-undone'></li>";
    var stepP=$("<div class='ystep-progress'>"+
                "<p class='ystep-progress-bar'><span class='ystep-progress-highlight' style='width:0%'></span></p>"+
            "</div>");

    stepP.css('width',w_li*2*(this.opt.stepCounts-1));
    stepP.find('.ystep-progress-bar').css('width',w_li*2*(this.opt.stepCounts-1))
    for(var i=0;i<this.opt.stepCounts;i++){
        if(i==0){
            var _s=$(stepDisc).text(this.opt.steps[i]).addClass('')
        }else{
            var _s=$(stepDisc).text(this.opt.steps[i])
        }
        stepsHtml.append(_s);
    }
    stepsHtml.find('li').css('width','140px').css('marginRight',w_li*2-140)
    stepContainer.append(stepsHtml).append(stepP);
    
    stepContainer.css('left',(w_con-stepP.width()-this.opt.imgWidth-10-this.opt.stepContainerMar*2)/2)
    this.content.css('overflow','hidden')
    var selStep = _that.opt.selStep;
    this.setProgress(this.stepContainer,selStep,this.opt.stepCounts, _that.opt.steps[selStep-1], true)
    //判断是否可点击进度条 并绑定点击事件
    if(this.opt.clickAble){
        stepsHtml.find('li').on('click',function(){
            _that.opt.curStep=$(this).index()+1;
            var stepName = _that.opt.steps[$(this).index()];
            _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts, stepName, false)
        })
    }
     $(window).resize(function(){
        var w_con=$(_that.content).width();
        var w_li=w_con/_that.opt.stepCounts/2;
        stepP.css('width',w_li*2*(_that.opt.stepCounts-1));
        stepP.find('.ystep-progress-bar').css('width',w_li*2*(_that.opt.stepCounts-1))
        stepsHtml.find('li').css('width','140px').css('marginRight',w_li*2-140)
        stepContainer.css('left',(w_con-stepP.width()-_that.opt.imgWidth-10-_that.opt.stepContainerMar*2)/2)
     })
}
//设置进度条
SetStep.prototype.setProgress=function(n,curIndex,stepsLen,stepName, boo){
        var _that=this;
        var uids = _that.opt.selPID;
        //获取当前容器下所有的步骤
        var $steps = $(n).find("li");
        var $progress =$(n).find(".ystep-progress-highlight");
        //判断当前步骤是否在范围内
        if(1<=curIndex && curIndex<=$steps.length){
          //更新进度
        	if (boo) {
        		var scale = "%";
                scale = Math.round((curIndex-1)*100/($steps.length-1))+scale;
        	}
          
            $progress.animate({
                width: scale
            },{
                speed: 1000,
	            done: function() {
	              //移动节点
	              $steps.each(function(j,m){
	                var _$m = $(m);
	                var _j = j+1;
	                if(_j < curIndex){
	                  _$m.attr("class","ystep-step-done");
	                }else if(_j == curIndex){
	                  _$m.attr("class","ystep-step-active");
	                }else if(_j > curIndex){
	                	//	                  _$m.attr("class","ystep-step-undone");
	                  _$m.attr("class","ystep-step-done");
	                }
	                if (_j > _that.opt.selStep) {
	                	_$m.attr("class","ystep-step-undone");
	                }
	              })
	              if(_that.opt.showBtn){
	                  if(curIndex==1){
	                      _that.prevBtn.attr('disabled','true')
	                      _that.nextBtn.removeAttr('disabled')
	                  }else if(curIndex==stepsLen){
	                      _that.prevBtn.removeAttr('disabled')
	                      _that.nextBtn.attr('disabled','true')
	                  }else if(1<curIndex<stepsLen){
	                      _that.prevBtn.removeAttr('disabled')
	                      _that.nextBtn.removeAttr('disabled')
	                  }
	              }
	               _that.checkPage(_that.pageCont,_that.opt.curStep,_that.opt.stepCounts,stepName,uids)
	               _that.opt.animating=false;
	          }
	      });  
        }else{
            return false;
        }
}
//改变 分页显示
SetStep.prototype.checkPage=function(pageCont,curStep,steps,stepName,uids){
    /*for(var i = 1; i <= steps; i++){
        if(i === curStep){
          pageCont.find('#page'+i).css("display","block");
        }else{
          pageCont.find('#page'+i).css("display","none");
        }
    }*/
//	$("#curStep").val(stepName);
//	$("#parentid").val(uids);

	$("[name=curStep]").val(stepName);
	$("[name=parentid]").val(uids);
	$.bs.table.reload("table0");  
	$("#showif").show();
    /*var url = $ctx + "/track/trackSource/ajaxGetMainData.action?curStep=" + stepName + "&parentid=" + uids;
	$.post(url).done(function(data) {
		pageCont.find('#page'+curStep).html("");
		var stepht = "<table class='tableType'>";
		for (var i = 0; i < data.length; i++) {
			var ht = data[i].activity + "，" + data[i].depart + "的" + data[i].executor + "在" + data[i].time + "审批，审批意见为" + data[i].opinion + "。";
			stepht = stepht + "<tr><th>" + ht + "";
			stepht = stepht + "<th style='width: 85px;color: blue;' onclick=openPlan('" + data[i].id + "')>" + "计划详情" + "";
//			stepht = stepht + "<th style='width: 85px;color: blue;' onclick=openJob('" + data[i].id + "')>" + "月报详情" + "";
		}
		stepht = stepht + "</table>";
		pageCont.find('#page'+curStep).append(stepht);
	});*/
	
   /* if (stepName == '起草') {
    	
    	pageCont.find('#page'+curStep).append();
    } else if (stepName == '计划') {
    	pageCont.find('#page'+curStep);
    } else if (stepName == '实施') {
    	pageCont.find('#page'+curStep);
    } else if (stepName == '验证') {
    	pageCont.find('#page'+curStep);
    }*/
}

function openPlan(planid) {
	/*openwindow($ctx + "/lnkhzs/issueTrack/trackPlan.jsp?pid=" + planid);*/
	openwindow($ctx + "/track/trackSource/ajaxGetPlanData.action?pid=" + planid);
}