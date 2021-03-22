/**
 * 报账单通用js 组件 bill.common.js
 */
var ajax_bill_unit_init_url = "";
var ajax_bill_invoice_init_url = "";

$(document).ready(function(){
    /* 声明全局变量 附件上传使用 */
    Sys_CurrentLoginUserPid = $("#user_pid").val();
    operation = $(".main-info .operation").val();
    //绑定按钮
    $(".btn.btn-draft").on("click",save_draft);//保存草稿
    $(".btn.btn-wf").on("click",save_wf);//发起报账
    //选择报账单元
    $("#bill-units").delegate(".btn-link.select-bill-unit","click",select_bill_unit);
    //新增报账单元
    $("#bill-units").delegate(".btn.add-bill-unit","click",add_bill_unit);
    //删除报账单元
    $("#bill-units").delegate(".btn.del-bill-unit","click",del_bill_unit);
    //首个报账单元不能删除
    $("#bill-units .bill-unit:eq(0) .btn.del-bill-unit").hide();
    
    //展开按钮样式
    $("#bill-units").delegate(".btn-collapse","click",click_btn_collapse);
    
    //刷新稽核数据事件绑定
    $("#bill-units").delegate(".btn-link.refresh-audit","click",click_refresh_audit);
    
    //发票类型选择
    $("#bill-invoices").delegate("select.invoiceType","change",change_invoice_type);
    $("#bill-invoices").delegate("select.invoiceTypeDetail","change",change_invoice_type_detail);
    $("#bill-invoices select.invoiceType").trigger("change");
    //新增票据
    $("#bill-invoices").delegate(".btn.add-bill-invoice","click",add_bill_invoice);
    //首个票据不能删除
    $("#bill-invoices .bill-invoice:eq(0) .btn.del-bill-invoice").hide();
    //删除票据
    $("#bill-invoices").delegate(".btn.del-bill-invoice","click",del_bill_invoice);
    //合同类型选择
    $(".contractType").on("change",change_contract_type).trigger("change");
    
    
    
    /* 加载主表单附件 begin */
    var main_attachment_group_id = $(".main-info .attachment-group-id").val();
    //此处要定义全局变量 不能使用var 
    attachment_main_info = get_attantment_obj("attachment_main_info",main_attachment_group_id);
    /* 加载主表单附件 end */
    
    /* 加载第一张票据附件 begin */
    //赋值 attachment-group-id
    var bill_invoice_attachment_group_id = guid(); 
    $(".bill-invoice:eq(0) .attachment-group-id").val(bill_invoice_attachment_group_id);
    attachment_bill = get_attantment_obj("attachment_bill",bill_invoice_attachment_group_id);
    /* 加载票据附件 end */
    
    /* 加载报账单元数据 */ 
    !$("#pid").val() || ajax_bill_unit_init($("#pid").val());
    /* 加载报账发票数据 */
    !$("#pid").val() || ajax_bill_invoice_init($("#pid").val());
    /* 判断是否需要隐藏或禁用操作*/
    is_disabled();
    /* 绑定输入验证 事件 */
    //供应商及账号变更，需要重新选择 报账单元
    $(".bankAccountNum").on("change",function(){
//            $(".bill-unit:gt(0)").remove();
        $("#bill-units .bill-unit:gt(0) .btn.del-bill-unit:visible").trigger("click");
        bill_unit_clear($(".bill-unit:eq(0)"));
    });
    //验证账单开始时间不能大于等于结束时间
    $("[name='entity.beginDate'],[name='entity.endDate']").on("change",vali_bill_begin_end_date).on("change",change_set_bill_unit_date);
    
    // 发票税率百分位转换
    $("#bill-invoices").delegate("[name='taxRate']","change",function(event){
        $(event.target).parents("table").find("[name='entity.taxRate']").val((parseFloat($(event.target).val())/100).toFixed(4));
    });
    // 发票 税价合计 自动计算
    $("#bill-invoices").find(".invoiceTypeDetail00,invoiceTypeDetail10").delegate("[name='entity.tax'],[name='entity.money']","keyup",function(event){
        var $invoice = $(event.target).parents(".bill-invoice");
        var tax = parseFloat($invoice.find("[name='entity.tax']").val());
        var money = parseFloat($invoice.find("[name='entity.money']").val());
        $invoice.find("[name='entity.priceTax']").val((tax + money )||"0.00");
    });
    
    
});

function is_disabled(operation){
    if(operation == "show"){
        //隐藏按钮
        $(".btn.btn-draft,.btn.btn-wf,.btn-link,.btn.floatRight10").hide();
        $("select").attr("disabled","disabled").off();
        $("input,textarea").attr("readonly","readonly").off();
        $("input[type='date']").removeAttr("onclick").removeAttr("onfocus");
        $(".btn-link,.btn").off();
    }
}

function get_attantment_obj(id,attachment_group_id){
    var uploadable = operation != "show";
    var operationParams = operation == "show"? '0,0,1' : '0,1,1';
    var attantment_obj = new UltraSWFUpload(id,$ctx + '/attachment/upload.action',$ctx + '/attachment/queryDownList.action',$ctx + '/attachment/download.action',attachment_group_id,'',uploadable,false,true,false,'',$ctx + '/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.swf','*.*','project reference',false,false,true,true,$ctx + '/common/style/blue/',operationParams,'doc,docx',$ctx + '/attachment/deleteAttachment.action',false,'','',false,'',$ctx + '/attachment/queryAttachType.action',$ctx + '/oledit/loadDocument.action','2','self','null','null','','false',{downTableStyle:'b'});
    return attantment_obj;
}


function select_bill_unit(event){
    var $cur_bill_unit = $(event.target).parents(".bill-unit");
    $(".bill-unit").removeClass("active");
    $cur_bill_unit.addClass("active");
    //获取供应商账号 
    var account = $(".main-info input.bankAccountNum").val();
    if(!account){
        alert("请优先选择供应商信息");
        vali_add_error($(".vendorName").focus(),"");
        return ;
    }
    //获取已选的报账单元名称（ROOM_NAME）
    var room_name = $("#bill-units .bill-unit").not($cur_bill_unit).find(".room-name").map(function(i,t){return $(t).text()}).get().join(",");
    url = $ctx + '/baseinfo/oprent/baseinforecord/selectWin.action?callback=callback_bill_unit_active&paccount=' + account + "&no_in_roomname=" + room_name;
    openwindow(url);
}

function callback_bill_unit_active(row) {
    $bill_unit = $(".bill-unit.active");
    $bill_unit.find(".has-error").removeClass("has-error");
    bill_unit_init($bill_unit, row);
}
/**
 * 待实现：初始化报账单元表单信息
 * @param $bill_unit 需要初始化的报账单元
 * @param row 初始化数据
 * @returns
 */
function bill_unit_init($bill_unit, row) {}


function bill_unit_active_clear(){
    bill_unit_clear($(".bill-unit.active"), row);
}

function bill_unit_clear($bill_unit){
    bill_unit_init($bill_unit,{});
}

function add_bill_unit() {
    $("#bill-units .bill-unit:eq(0) .btn.del-bill-unit").show();
    var $element = $(".bill-unit:eq(0)").clone();
    bill_unit_clear($element);
    $element.find(".panel-title span.required").removeClass("required");
    $("#bill-units").append($element);
}
function del_bill_unit(event) {
    $(event.target).parents(".bill-unit").remove();
    $("#bill-units .bill-unit:eq(0) .panel-title span:first").addClass("required");
    if($("#bill-units .bill-unit").size() == 1){
        $("#bill-units .bill-unit .btn.del-bill-unit").hide();
    }
}

function change_invoice_type(event){
    var inviceType = $("#bill-invoices .invoiceType:eq(0)").val();
    if("纸质发票" == inviceType) {
        $(".invoice-data .invoiceTypeDetail00").show();
        $(".invoice-data .invoiceTypeDetail10,.invoice-data .invoiceTypeDetail11").hide();
        $(".panel-title .invoiceTypeDetail").html("<option value='00'>增值税专用发票</option>")
    } else if("普通发票" == inviceType) {
        $(".panel-title .invoiceTypeDetail").html("<option value='10'>增值税普通发票</option><option value='11'>收据</option>")
    }
    $("#bill-invoices .invoiceType:gt(0)").val($("#bill-invoices .invoiceType:eq(0)").val());
    $("#bill-invoices .panel-title .invoiceTypeDetail").trigger("change");
}

function change_invoice_type_detail(event){
    var index = $(event.target).parents(".bill-invoice").index();
    var invoiceTypeDetail = $("#bill-invoices .invoiceTypeDetail").eq(index).val();
    var $data = $(".invoice-data").eq(index);
    if("00" == invoiceTypeDetail) {
        $data.find(".invoiceTypeDetail00").show();
        $data.find(".invoiceTypeDetail10,.invoiceTypeDetail11").hide();
    } else if("10" == invoiceTypeDetail) {
        $data.find(".invoiceTypeDetail10").show();
        $data.find(".invoiceTypeDetail00, .invoiceTypeDetail11").hide();
    } else if("11" == invoiceTypeDetail) {
        $data.find(".invoiceTypeDetail11").show();
        $data.find(".invoiceTypeDetail00,.invoiceTypeDetail10").hide();
    }
}

/**
 * 待实现：初始化票据表单信息
 * @param $bill_invoice
 * @param row
 * @returns
 */
function bill_invoice_init($bill_invoice, row) {}

function add_bill_invoice() {
    $("#bill-invoices .bill-invoice:eq(0) .btn.del-bill-invoice").show();
    var $element = $(".bill-invoice:eq(0)").clone();
    $element.find(".invoiceType").val($(".bill-invoice:eq(0) .invoiceType").val());
    $element.find(".invoiceType").attr("disabled","disabled");
    $element.find(".panel-title span.required").removeClass("required");
    $("#bill-invoices").append($element);
    invoice_clear($element,$(".bill-invoice:eq(0) .invoiceTypeDetail").val());
}

function invoice_clear($element,invoiceTypeDetail){
    var obj = {};
    obj.invoiceType = invoiceTypeDetail;
    bill_invoice_init($element,obj);
//    $element.find("input").not("[name='entity.invoiceType']").val("");
}

function del_bill_invoice(event) {
    $(event.target).parents(".bill-invoice").find(".uploader-container")[0].innerHTML="";
    $(event.target).parents(".bill-invoice").remove();
    $("#bill-invoices .bill-invoice:eq(0) .panel-title span:first").addClass("required");
    //第一个invoiceType 可用
    $("#bill-invoices .bill-invoice:eq(0) .invoiceType").removeAttr("disabled");
    
    if($("#bill-invoices .bill-invoice").size() == 1){
        $("#bill-invoices .bill-invoice .btn.del-bill-invoice").hide();
    }
    
}

var bill_unit_pid = [];

function ajax_bill_unit_init(bill_id){
    $.post(ajax_bill_unit_init_url,{
        "bill.pid":bill_id
    }).done(function(entities){
        for(var i = $(".bill-unit").size();i < entities.length;i++){
            add_bill_unit();
        }
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];
            var $bill_unit = $(".bill-unit").eq(i); 
            bill_unit_init($bill_unit,entity2db(entity));
            bill_unit_pid.push(entity.pid);
        }
        is_disabled(operation);
    })
}
var bill_invoice_pid = [];

function ajax_bill_invoice_init(bill_id){
    $.post(ajax_bill_invoice_init_url,{
        "bill.pid":bill_id
    }).done(function(entities){
        var cur_invoice_size = $(".bill-invoice").size();
        for (var i = 0; i < entities.length; i++) {
            var entity = entities[i];
            if (cur_invoice_size - 1 < i) {
                add_bill_invoice();
            }
            
            var $bill_invoice = $(".bill-invoice").eq(i); 
            bill_invoice_init($bill_invoice,entity);
            bill_invoice_pid.push(entity.pid)
        }
        is_disabled(operation);
    })
}

/**
 * 验证账单开始结束时间
 */
function vali_bill_begin_end_date(event) {
    var $begin = $(".main-info [name='entity.beginDate']");
    var $end = $(".main-info [name='entity.endDate']");
    var begin = $begin.val();
    var end = $end.val();
    if (begin && end && begin >= end) {
        $.bs.tips("账单开始时间不能大于等于结束时间", {level : "danger", $target : $(".main-info"),delay:3000 ,auto_close : true})
        vali_add_error($begin, "");
        vali_add_error($end, "");
        return false;
    } else {
        vali_remove_error($begin);
        vali_remove_error($end);
        return true;
    }
}

/**
 * 保存方法
 * @param callback 保存后回调
 * @returns
 */
function save_data(callback) {}

/**
 * 保存草稿
 */
function save_draft() {}


/**
 * 发起报账
 * @returns
 */
function save_wf(){}


function change_contract_type(event){
    var type = $(event.target).val();
    if(type < 4){
        $(".contractNumber,.contractNumber + .input-group-btn  .btn").removeAttr("disabled").removeAttr("readonly").val("");
        $(".contractNumber").parents("td").prev().addClass("required");
    } if(type == 4 || !type){
        $(".contractNumber").attr("readonly","readonly").val("");
        $(".contractNumber + .input-group-btn .btn").attr("disabled","disabled");
        $(".contractNumber").parents("td").prev().removeClass("required");
    }
}
function click_btn_collapse(event){
    var $btn = $(event.target);
    if($target_div = $("#" + $btn.attr("href").substring(1)).is(".in")){
        $btn.text("展开");
    } else {
        $btn.text("折叠");
        
    }
}
function click_refresh_audit(event){
    var $btn = $(event.target);
    var $bill_unit = $btn.parents(".bill-unit");
    refresh_audit($bill_unit);
    $.bs.tips("刷新成功", {level : "success", $target : $bill_unit.find(".audit"),delay:1000 })
}
function refresh_audit($bill_unit){
    
}
function change_set_bill_unit_date(event){
    $date = $(event.target);
    set_bill_unit_date($date,$date.attr("name"));
    
}
function set_bill_unit_date($date,name){
    $(".bill-unit").each(function(i,t){
        var $unit = $(t);
        if(!$unit.find(".meterId").val()){
            return;
        }
        var $curDate = $unit.find("[name='"+name+"']");
        var curVal = $curDate.val();
        if(!curVal) {
            $curDate.val($date.val());
        }
    })
}
