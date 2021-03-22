$(document).ready(function(){
    $(document).delegate("input[type='number'],input[type='text'].number","keyup",function(event){
        var $t = $(event.target);
        // 延时校验，
        setTimeout(function(){
            $t.parents(".error").removeClass("error").find(".tips").remove();
            var val = $t.val();
            if($t.val() !== '' && (!$t.val() || isNaN(val))){
                vali_add_error($t,"请输入数值",1000);
                return;
            }
            val = Number(val);
            // 范围
            var max = Number($t.attr("max"));
            var min = Number($t.attr("min"));
            if(val > max|| val < min ){
                vali_add_error($t,"数值超限【"+min+"~"+max+"】");
            } else {
                vali_remove_error($t);
            }
        },500)
    });
    $(document).delegate("input[type='number'],input[type='text'].number","blur",function(event){
        var $t = $(event.target);
        val = Number($t.val());
        //处理精度
        var step = $t.attr("step");
        if(step == null || step == undefined)
        {
        	return;
        }
        Number(step);
//        var len = step.replace(/.*\./g,'').length;
        var len = Math.log10(step);
        if(len <= 0 ){
            $t.val(parseFloat(val).toFixed(len * -1));  
        }
    });
    
    
    $("input[type='date'],input[type='text'].date").on("blur",function(event){
        if(this.value){
          $(this).parents(".error").removeClass("error").find(".tips").remove();
      }
    });
    $("input[type='tel'],input[type='text'].tel").on("blur",function(event){
        var $t = $(event.target);
        $t.parents(".error").removeClass("error").find(".tips").remove();
        if(!(/^1[34578]\d{9}$/.test($t.val())) && !(/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test($t.val()))){
            vali_add_error($t,"请输入电话号码");
        } else {
            vali_remove_error($t);
        }
    });
});
/**
 * 废弃 
 */
function validate(){
    var flag = true;
    //清除校验
    $(".error").removeClass("error").find(".tips").remove();
    // 验证必填
    $("td.label.required + td,div.form-table div:has(label.required) + div").find("input,select,textarea").each(function(i, t) {
        if (!$(t).val()) {
            vali_add_error($(t),"必填");
            flag = false;
        }
    });
    if(!flag){
        return false;
    }
    //验证数值类型
    $("input[type='number'],input[type='text'].number").each(function(i,t){
        var value = $(t).val();
        if(isNaN(value)){
            vali_add_error($(t),"请输入数值");
            flag = false;
            return;
        } 
    });
    if(!flag){
        return false;
    }
    // 验证手机号
    $("input[type='tel'],input[type='text'].tel").each(function(i,t){
        var value = $(t).val();
        if(!(/^1[34578]\d{9}$/.test(value)) && !(/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value))){
            vali_add_error( $(t),"请输入电话号码");
            flag = false;
        }
    });
    if(!flag){
        return false;
    }

    return true;
}
function vali_required($t){
    var val = $t.val();
    if($t.is(".ui-select")) {
        val = $t.ui_select().val();
    }
    switch ($t.attr("type")) {
        case "number":
            if(Number(val) === 0 && $t.attr("zeroable") == 'false' ){
                val = '';
            }
            break;
        case "radio":
        case "checkbox":
            val = $("input[name='" + $t.attr("name") + "']:checked").val();
//            $t = $("input[name='" + $t.attr("name") + "']:last")
            break;
        default:
            break;
    }
    
    if (!val) {
        vali_add_error($t,"必填");
        return false;
    } 
    return true;

}
/**
 * jquey 数组中 有一个不为空 就验证通过
 * @param $t 可以是jquery数组
 * @returns
 */
function vali_required_any($arr){
    var flag = false;
    $arr.each(function (i,t){
        var $t = $(t);
        if (!$t.val()) {
            flag |= true;
        } 
    });
    return flag;
}

function validateForm($forms){
    function show_filter(index){
        // 过滤掉隐藏元素
        if($(this).is(".ui-select") && !$(this).ui_select().isHide()){
            return this;
        } else if( $(this).is(":hidden")){
            return null;
        } 
        // TODO 暂时这类 不知道怎么校验必填 需要用户自己实现
        else if( $(this).is(":radio") || $(this).is(":checkbox")){
            if($(this).index("[name='"+this.name+"']") == $("[name='"+this.name+"']").size() -1 ){
                return this
            } else {
                return null;
            }
        }
        return this;
    }
    var arr_$error_el = [];
    // 多个form
    for (var k = 0; k < $forms.size(); k++) {
        var $form = $forms.eq(k);
        var flag = true;
        //清除校验
        $form.find(".error,.has-error").removeClass("error has-error danger").tooltip('destroy');
        // 验证必填
        $form.find("td.label.required + td,div.form-table div:has(label.required) + div").find("input,select,textarea").filter(show_filter).each(function(i, t) {
            var $wrap =  $(t);
            if($(t).is(".ui-select-wrap")){
                $wrap = $(t).find("select");
            }
            if(!vali_required($wrap)){
                arr_$error_el.push($(t));
                flag = false;
            }
            
            
        });
        if(!flag){
            break;
        }
        //验证数值类型
        $form.find("input[type='number'],input[type='text'].number").filter(show_filter).each(function(i,t){
            var value = $(t).val();
            if(isNaN(value)){
                vali_add_error($(t),"请输入数值");
                flag = false;
            }
            value = Number(value);
            // 范围
            var max = Number(t.max||9999999999999999999.9999);
            var min = Number(t.min|| -9999999999999999999.9999);
            if(value > max|| value < min ){
                vali_add_error($(t),"数值超限【"+min+"~"+max+"】");
                arr_$error_el.push($(t));
                flag = false;
            }
        });
        if(!flag){
            break;
        }
        // 验证手机号
        $form.find("input[type='tel'],input[type='text'].tel").filter(show_filter).each(function(i,t){
            var value = $(t).val();
            if(!!value && !(/^1[34578]\d{9}$/.test(value)) && !(/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value))){
                vali_add_error( $(t),"请输入电话号码");
                arr_$error_el.push($(t));
                flag = false;
            }
        });
        // 验证身份证
        $form.find("input[type='idcard'],input[type='text'].idcard").filter(show_filter).each(function(i,t){
            var value = $(t).val();
            if(!!value && !(/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$/.test(value))){
                vali_add_error( $(t),"身份证号不正确");
                arr_$error_el.push($(t));
                flag = false;
            }
        });
    }
    if(!flag){
        var $first = arr_$error_el.length == 0? $form.eq(0):arr_$error_el[0]
        $("html,body").animate({scrollTop:$first.offset().top - 200},1000);
        return false;
    }
    
    return true;
}

function vali_add_error($el, err_msg, delay_remove) {
    var error_css = "has-error danger";
    delay_remove = delay_remove || 5000;
    $el.each(function(i,t){
        var $t = $(t);
        if($t.is(".bs-style")){
            $t.bs_style()._wrap.addClass(error_css);
        } else if ($t.is(".ui-select")) {
            $t.ui_select()._wrap.addClass(error_css);
        } else if ($t.is(":checkbox,:radio")) {
            $t.parents("td,div").eq(0).find("label").addClass(error_css);
        } else {
            $t.addClass(error_css);
        }
        
        var error_tips_orig_title = $t.attr("title")||'';
        var $wrap = $t;
        if ($t.is(".ui-select")) {
            $wrap = $t.ui_select()._wrap;
        } else if ($t.is(".bs-style")){
            $wrap = $t.bs_style()._wrap;
        } else if ($t.is(":checkbox,:radio")){
            $wrap = $t.parents("label");
        }
        
        if(!!err_msg){
            var id = layer.tips(err_msg, $wrap, {
                tips: [2, '#f46955'],
                time: 5000,
                tipsMore: true
            });
            var VALI_ADD_ERROR_TIPS_ID =  $el.data("VALI_ADD_ERROR_TIPS_ID")||[];
            VALI_ADD_ERROR_TIPS_ID.push(id);
            $el.data("VALI_ADD_ERROR_TIPS_ID",VALI_ADD_ERROR_TIPS_ID);
        }
    });
    
}
var tabindex = 999;
function vali_remove_error($el){
    var error_css = "has-error danger";
    $el.each(function(i,t){
        var $t = $wrap = $(t);
        if ($t.is(".ui-select")) {
            $wrap = $t.ui_select()._wrap;
        } else if ($t.is(".bs-style")){
            $wrap = $t.bs_style()._wrap;
        } else if ($t.is(":checkbox,:radio")) {
            $wrap = $t.parents("label");      
        }
        setTimeout(function() {
            $t.parents(".has-error").eq(0).removeClass(error_css);
            $t.parents("td,div").eq(0).removeClass(error_css);
            $t.parents("td,div").find(".has-error").removeClass(error_css);
            $t.parents("td,div").eq(0).find(".tips").remove();
            $t.removeClass(error_css);
            var VALI_ADD_ERROR_TIPS_IDS =  $t.data("VALI_ADD_ERROR_TIPS_ID")||[];
            for (var i = 0; i < VALI_ADD_ERROR_TIPS_IDS.length; i++) {
                var VALI_ADD_ERROR_TIPS_ID = VALI_ADD_ERROR_TIPS_IDS[i];
                layer.close(VALI_ADD_ERROR_TIPS_ID);
            }
            $t.data("VALI_ADD_ERROR_TIPS_ID",[]);
            
       }, 200)
   });
}






