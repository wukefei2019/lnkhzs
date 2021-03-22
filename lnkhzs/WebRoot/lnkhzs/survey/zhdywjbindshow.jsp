<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<script type="text/javascript"
	src="${ctx }/omcs/style/js/vali_form.js?CVT=${param.CVT }"></script>

<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/json2.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/swfupload.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/handlers.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/js/ultraswfupload.js"></script>
<script type="text/javascript"
	src="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField.js"></script>

<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/ultraupload.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctx }/ultrabpp/runtime/clientframework/component/SpecialField/AttachmentField/css/upload_new.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx }/ultrabpp/runtime/theme/classic/css/main.css"
	rel="stylesheet" type="text/css" />


<script src="${ctx}/common/javascript/main.js"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/validate.js" defer="defer"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/datagrid.js" defer="defer"></script>
<link type="text/css" rel="Stylesheet"
	href="${ctx}/common/plugin/JTable/JTable.css" />
<link href="${ctx}/common/style/${skintype}/css/newgongdan.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx}/common/plugin/JTable/JTable.js"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/datagrid.js"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/date/WdatePicker.js"></script>
<script type="text/javascript"
	src="${ctx}/common/javascript/date/dateFormat.js"></script>

<style type="text/css">

.Ct form select.select {
    border: 1px solid #e3e3e3;
    width: 95%;
    min-width: 125px;
    height: 24px;
    padding-left: 10px;
    color: #666;
    font-family: "微软雅黑";
    float: left;
     margin-left: 6px;
}

.Ct form select.selectJT {
    border: 1px solid #e3e3e3;
    width: 90%;
    min-width: 125px;
    height: 24px;
    padding-left: 10px;
    color: #666;
    font-family: "微软雅黑";
    float: left;
    margin-left: 10px;
}

</style>



<script type='text/javascript'>


	$(document).ready(function() {
		$(".handOut").on("click", function(event) {
			fn_save(2);
		});

		$(".handSubmit").on("click", function(event) {
			fn_save(1);
		});
		var id = "${param.id}";
		var type = "${param.type}";
		var mylength = $("body").find("input").length;
		for (var i = 0; i < mylength; i++) {
			$("body").find("input").attr('disabled', 'disabled');
		}
		mylength = $("body").find("select").length;
		for (var i = 0; i < mylength; i++) {
			$("body").find("select").attr('disabled', 'disabled');
		}
		mylength = $("body").find("textarea").length;
		for (var i = 0; i < mylength; i++) {
			$("body").find("textarea").attr('disabled', 'disabled');
		}
		if (id.length != 0) {
			$.post($ctx + "/khzs/survey/getSelect.action?khzsQuestionnaire.id=" + id).done(function(result) {
				$("#khzsQuestionnaireid").val(id);
				$("#selectid").val(result.id);
				$("#age").val(result.age);
				/* $("#message_info").val(result.message_info); */
				$("#arpu_3month").val(result.arpu_3month);
				$("#call_duration_fee_3momth").val(result.call_duration_fee_3momth);
				$("#call_res_baohe").val(result.call_res_baohe);
				$("#complain_10086_counts").val(result.complain_10086_counts);
				$("#complain_jt_counts").val(result.complain_jt_counts);
				$("#complain_jtds_counts").val(result.complain_jtds_counts);
				$("#complain_jtkd_counts").val(result.complain_jtkd_counts);
				$("#complain_jtwl_counts").val(result.complain_jtwl_counts);
				$("#complain_jtyw_counts").val(result.complain_jtyw_counts);
				$("#complain_ydyw_counts").val(result.complain_ydyw_counts);
				$("#complain_yywl_counts").val(result.complain_yywl_counts);
				$("#complain_zz_counts").val(result.complain_zz_counts);
				/* $("#county_id").val(result.county_id); */
				$("#create_date").val(result.create_date);
				$("#dou_3month").val(result.dou_3month);
				$("#gprs_res_baohe").val(result.gprs_res_baohe);
				$("#gprs_xs_3month").val(result.gprs_xs_3month);
				/* $("#group_industry_id").val(result.group_industry_id); */
				$("#kd_create_date").val(result.kd_create_date);
				$("#login_3month").val(result.login_3month);
				$("#max_expire_date").val(result.max_expire_date);
				$("#mbh_should_fee").val(result.mbh_should_fee);
				$("#mou_3month").val(result.mou_3month);
				$("#over_flow_3month").val(result.over_flow_3month);
				$("#owe_mark").val(result.owe_mark);
				/* $("#plan_id").val(result.plan_id); */
				$("#planchng_3month").val(result.planchng_3month);
				/* $("#prod_bandwidth").val(result.prod_bandwidth); */
				/* $("#provider_name").val(result.provider_name); */
				/* $("#region_id1").val(result.region_id1); */
				$("#rom_in_chi_mark").val(result.rom_in_chi_mark);//
				$("#rom_in_earth_mark").val(result.rom_in_earth_mark);//
				$("#school_flag").val(result.school_flag);//
				/* $("#term_name").val(result.term_name); */
				$("#user_id").val(result.user_id);
				$("#user_opentime").val(result.user_opentime);
				$("#volte_duration_3month").val(result.volte_duration_3month);
				$("#www_action_3month").val(result.www_action_3month);
				$("#yyt_banli_3month").val(result.yyt_banli_3month);

				
				$("select[name='bindDicselect.city_id']").val(result.city_id);
				$("select[name='bindDicselect.star_level']").val(result.star_level);
				$("select[name='bindDicselect.offer_name']").val(result.offer_name);
				$("select[name='bindDicselect.group_level_id']").val(result.group_level_id);
				$("select[name='bindDicselect.county_id']").val(result.county_id);//区县
				$("select[name='bindDicselect.group_industry_id']").val(result.group_industry_id);//行业类型
				$("select[name='bindDicselect.plan_id']").val(result.plan_id);//资费套餐名称
				$("select[name='bindDicselect.prod_bandwidth']").val(result.prod_bandwidth);//宽带带宽
				$("select[name='bindDicselect.provider_name']").val(result.provider_name);//手机终端品牌
				$("select[name='bindDicselect.region_id1']").val(result.region_id1);
				$("select[name='bindDicselect.term_name']").val(result.term_name);//
				
				
				
				
				


				$("input[name='bindDicselect.is_2card'][value=" + result.is_2card + "]").attr("checked", true);
				$("input[name='bindDicselect.is_zhufu_card'][value=" + result.is_zhufu_card + "]").attr("checked", true);
				$("input[name='bindDicselect.rom_in_chi_mark'][value=" + result.rom_in_chi_mark + "]").attr("checked", true);
				$("input[name='bindDicselect.rom_in_earth_mark'][value=" + result.rom_in_earth_mark + "]").attr("checked", true);
				$("input[name='bindDicselect.school_flag'][value=" + result.school_flag + "]").attr("checked", true);
				$("input[name='bindDicselect.is_world'][value=" + result.is_world + "]").attr("checked", true);
				$("input[name='bindDicselect.is_offer'][value=" + result.is_offer + "]").attr("checked", true);
				$("input[name='bindDicselect.owe_mark'][value=" + result.owe_mark + "]").attr("checked", true);
				$("input[name='bindDicselect.is_kd'][value=" + result.is_kd + "]").attr("checked", true);
				$("input[name='bindDicselect.is_fuse'][value=" + result.is_fuse + "]").attr("checked", true);
				$("input[name='bindDicselect.is_kdds'][value=" + result.is_kdds + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group'][value=" + result.is_group + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group_keyper'][value=" + result.is_group_keyper + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group_linker'][value=" + result.is_group_linker + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group_order'][value=" + result.is_group_order + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group_zx'][value=" + result.is_group_zx + "]").attr("checked", true);
				$("input[name='bindDicselect.is_qykd'][value=" + result.is_qykd + "]").attr("checked", true);
				$("input[name='bindDicselect.is_group_vpmn'][value=" + result.is_group_vpmn + "]").attr("checked", true);
				$("input[name='bindDicselect.is_idc'][value=" + result.is_idc + "]").attr("checked", true);
				$("input[name='bindDicselect.is_ims'][value=" + result.is_ims + "]").attr("checked", true);
				$("input[name='bindDicselect.is_ofer1'][value=" + result.is_ofer1 + "]").attr("checked", true);
				$("input[name='bindDicselect.is_volte_3month'][value=" + result.is_volte_3month + "]").attr("checked", true);

			});

		}
		;

		if (id.length != 0) {
			$.post($ctx + "/khzs/survey/getSelectBol.action?khzsQuestionnaire.id=" + id).done(function(result) {
				for (var i = 0; i < result.length; i++) {
					$("#" + result[i].selectname).val(result[i].symbol + "");
				}
			});
		};
		
			if (id.length != 0) {
			$.post($ctx + "/khzs/survey/getSelectDx.action?khzsQuestionnaire.id=" + id).done(function(result) {
				$("#message_info").val(result.message_info);
				$("#daynum").val(result.daynum);
				$("#bindDicSelectInfoID").val(result.id);
			});
		}

	});

	function fn_save(optinon) {
		var url = "";
		if (optinon == 1) {
			url = encodeURI($ctx + "/khzs/survey/publish.action");
		}
		if (optinon == 2) {
			url = encodeURI($ctx + "/khzs/survey/saveSelect.action");
		}
		var reqdata = $("form").serialize();
		reqdata = reqdata + "&bindDicselect.message_info=" + $("#message_info").val();
		$.post(url, reqdata).done(function(result) {
			if (result == 'success') {
				alert("操作成功");
				$((opener || parent).document).find(".btn-link.icon-refresh2").click();
				window.close();
			} else if (result == 'error') {
				alert("操作失败,不能为空!");
			} else {
				alert("操作失败");
			}

		}, "json");

	}

	function channgeoption(obj) {
	}
</script>
<meta charset="UTF-8">
<title>绑定搜索条件</title>
</head>
<body>
	<div class="Ct Dialog contract form-page">

		<div id="flow" class='panel panel-primary'>
			<div class='panel-heading'>
				<h3 class='panel-title'>
					短信内容 <a data-toggle="collapse" href="#flowTable"
						style="text-decoration: none" class="glyphicon glyphicon-my-minus"></a>
				</h3>
			</div>
			<div id="flowTable" class="collapse in">
				<div class="form-table">
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
				<div class='col-xs-3'>
						<label class="required">问卷有效时长：</label>
					</div>
					<div class='col-xs-9'>
						<input id="daynum" type='text' style="width: 20%;float: left;"
								class='form-control' name='bindDicSelectInfo.daynum'
								value='${bindDicSelectInfo.daynum}' />&nbsp;&nbsp;&nbsp;<span>天</span>
					</div>
				</div>
				<div class="row" style="margin-left: 0px;margin-right: 0px;">
					<div class='col-xs-3'>
						<label class="required">短信内容：</label>
					</div>
					<div class='col-xs-9'>
						<textarea id="message_info" class='txtarea1'
							name='bindDicSelectInfo.message_info'>${bindDicSelectInfo.message_info}</textarea>
					</div>
					</div>
					
				</div>
			</div>

			<div id="formTable" class='panel panel-primary'>
				<div class='panel-heading'>
					<h3 class='panel-title'>
						人员筛选条件 <a data-toggle="collapse" href="#form"
							style="text-decoration: none"
							class="glyphicon glyphicon-my-minus"></a>
					</h3>
				</div>
				<form id='form' name='form' target="frm" class="collapse in">
					<input id="khzsQuestionnaireid" type="hidden"
						name="khzsQuestionnaire.id" value="${param.id}" /> <input
						id="selectid" type="hidden" name="bindDicselect.id"
						value="${bindDicselect.id }" />
					<div class="form-table">
						<div style="display:none;" class='col-xs-3'>
							<label>用户编码：</label>
						</div>
						<div style="display:none;" class='col-xs-3'>
							<select id="yhbm" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="user_id" type='text' style="width: 80%"
								class='form-control' name='bindDicselect.user_id'
								value='${bindDicselect.user_id}' />
						</div>
						
						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								基础属性
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">地市：</label>
						</div>
						<div class='col-xs-3'>
							<eoms:select name="bindDicselect.city_id" style="select"
								dataDicTypeCode="city_id" value="${bindDicselect.city_id}"
								isnull="false" />
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">区县：</label>
						</div>
						<div class='col-xs-3'>
								<eoms:select  name="bindDicselect.county_id" style="select" 
								dataDicTypeCode="county_id" value="${bindDicselect.county_id}"
								isnull="true" />
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">年龄：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="nl" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="age" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.age'
								value='${bindDicselect.age}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">入网时间：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="rwsj" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="user_opentime" type='text' style="width: 80%"
								class='form-control' name='bindDicselect.user_opentime'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								value='${bindDicselect.user_opentime}' />
								</div>

						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">区域（城镇/农村）：</label>
						</div>
						<div class='col-xs-3'>
							<eoms:select  name="bindDicselect.region_id1" style="select" 
								dataDicTypeCode="region_id1" value="${bindDicselect.region_id1}"
								isnull="true" />
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">星级：</label>
						</div>
						<div class='col-xs-3'>
							<eoms:select name="bindDicselect.star_level" style="select"
								dataDicTypeCode="star_level" value="${bindDicselect.star_level}"
								isnull="false" />
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否双卡客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_2card">
								<label><input type="radio" name="bindDicselect.is_2card"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_2card" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">是否主副卡客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_zhufu_card">
								<label><input type="radio"
									name="bindDicselect.is_zhufu_card" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_zhufu_card" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否国内漫游客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="rom_in_chi_mark">
								<label><input type="radio"
									name="bindDicselect.rom_in_chi_mark" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.rom_in_chi_mark" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">是否国际漫游客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="rom_in_earth_mark">
								<label><input type="radio"
									name="bindDicselect.rom_in_earth_mark" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.rom_in_earth_mark" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否校园客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="school_flag">
								<label><input type="radio"
									name="bindDicselect.school_flag" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.school_flag" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">是否全球通客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_world">
								<label><input type="radio" name="bindDicselect.is_world"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_world" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否开通VOLTE功能：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: -1px;margin-top: 0px;float: left;margin-left: 10px;">
								<label><input type="radio" name="bindDicselect.is_offer"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_offer" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">手机终端品牌：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<eoms:select name="bindDicselect.provider_name" style="select"
								dataDicTypeCode="provider_name" value="${bindDicselect.provider_name}"
								isnull="true" />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">手机终端型号：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;margin: auto;">
							<eoms:select name="bindDicselect.term_name" style="select"
								dataDicTypeCode="term_name" value="${bindDicselect.term_name}"
								isnull="true" />
								</div>
						</div>
						<div class='col-xs-6' style="margin-bottom: -10px;">
						</div>

						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								使用行为
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月月均ARPU（元）：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="arpu" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="arpu_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.arpu_3month'
								value='${bindDicselect.arpu_3month}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月月均DOU（M）：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="dou" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="dou_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.dou_3month'
								value='${bindDicselect.dou_3month}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月月均MOU（分钟）：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="mou" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="mou_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.mou_3month'
								value='${bindDicselect.mou_3month}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月月均VOLTE通话时长：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="volte" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="volte_duration_3month" type='number'
								style="width: 80%" class='form-control'
								name='bindDicselect.volte_duration_3month'
								value='${bindDicselect.volte_duration_3month}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">资费套餐名称：</label>
						</div>
						<div class='col-xs-3'>
							<eoms:select name="bindDicselect.plan_id" style="select"
								dataDicTypeCode="plan_id" value="${bindDicselect.plan_id}"
								isnull="true" />
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月套餐变更次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="tcbg" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="planchng_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.planchng_3month'
								value='${bindDicselect.city_idplanchng_3month}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月流量限速次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="llxs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="gprs_xs_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.gprs_xs_3month'
								value='${bindDicselect.gprs_xs_3month}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">当月套餐内流量饱和值：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="llbh" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="gprs_res_baohe" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.gprs_res_baohe'
								value='${bindDicselect.gprs_res_baohe}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">当月套餐内语音通话饱和值：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="yyth" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="call_res_baohe" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.call_res_baohe'
								value='${bindDicselect.call_res_baohe}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月套外流量（M）：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="twll" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="over_flow_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.over_flow_3month'
								value='${bindDicselect.over_flow_3month}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月套外语音费用（元）：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="yyfy" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="call_duration_fee_3momth" type='number'
								style="width: 80%" class='form-control'
								name='bindDicselect.call_duration_fee_3momth'
								value='${bindDicselect.call_duration_fee_3momth}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">欠费标识：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 9px;"
								id="owe_mark">
								<label><input type="radio" name="bindDicselect.owe_mark"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.owe_mark" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月营业厅办理次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="blcs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="yyt_banli_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.yyt_banli_3month'
								value='${bindDicselect.yyt_banli_3month}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月登陆网厅次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="wtcs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="www_action_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.www_action_3month'
								value='${bindDicselect.www_action_3month}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月登陆手机营业厅次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="yytcs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="login_3month" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.login_3month'
								value='${bindDicselect.login_3month}' />
								</div>
						</div>
						
						<div class='col-xs-6'>
						</div>
						</div>
						
						
						
						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								家庭属性
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否宽带客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: -1px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_kd">
								<label><input type="radio" name="bindDicselect.is_kd"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_kd" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">宽带套餐名称：</label>
						</div>
						<div class='col-xs-3'>
							<eoms:select name="bindDicselect.offer_name" style="select"
								dataDicTypeCode="offer_name" value="${bindDicselect.offer_name}"
								isnull="true" />
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<!-- </div>
					<div class='col-sm-12' style="padding-left: 0px;padding-right: 0px;"> -->
						<div class='col-xs-3'>
							<label style="float: right;">是否融合套餐：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: -1px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_fuse">
								<label><input type="radio" name="bindDicselect.is_fuse"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_fuse" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">宽带带宽：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;">
							<eoms:select  name="bindDicselect.prod_bandwidth" style="select" 
								dataDicTypeCode="prod_bandwidth" value="${bindDicselect.prod_bandwidth}"
								isnull="true" />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">开通时间：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="ktsj" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="kd_create_date" type='text' style="width: 80%"
								class='form-control' name='bindDicselect.kd_create_date'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								value='${bindDicselect.kd_create_date}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">宽带到期时间：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="dqsj" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="max_expire_date" type='text' style="width: 80%"
								class='form-control' name='bindDicselect.max_expire_date'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								value='${bindDicselect.max_expire_date}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否宽带电视客户：</label>
						</div>
						<div class='col-xs-3'>
							<%-- <input id="question" type='text' style="width: 100%"
							class='form-control' name='bindDicselect.is_kdds'
							value='${bindDicselect.is_kdds}' /> --%>
							<div class="radio" style="margin-bottom: -1px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_kdds">
								<label><input type="radio" name="bindDicselect.is_kdds"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_kdds" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三个月宽带电视消费金额：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 95%;margin: auto;">
							<select id="xfje" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="mbh_should_fee" type='number' style="width: 80%"
								class='form-control' name='bindDicselect.mbh_should_fee'
								value='${bindDicselect.mbh_should_fee}' />
								</div>
						</div>
						</div>
						
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否集团客户成员：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: -1px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_group">
								<label><input type="radio" name="bindDicselect.is_group"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_group" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						
						<div class='col-xs-6'>
							<label> </label>
						</div>
						</div>
						
						
						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								政企属性
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否集团客户关键人：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_group_keyper">
								<label><input type="radio"
									name="bindDicselect.is_group_keyper" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_group_keyper" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">是否集团客户联系人：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_group_linker">
								<label><input type="radio"
									name="bindDicselect.is_group_linker" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_group_linker" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否集团产品使用人：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_group_order">
								<label><input type="radio"
									name="bindDicselect.is_group_order" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_group_order" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">所属集团客户入网时间：</label>
						</div>
						<div class='col-xs-3'>
							<select id="jtrwsj" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 20%;float: left;margin-left: 10px">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="create_date" type='text' style="width: 70%"
								class='form-control' name='bindDicselect.create_date'
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								value='${bindDicselect.create_date}' />
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class='col-xs-3'>
								<label>行业类型：</label>
							</div>
							<div class='col-xs-3'>
								<eoms:select  name="bindDicselect.group_industry_id" style="select" 
								dataDicTypeCode="group_industry" value="${bindDicselect.group_industry_id}"
								isnull="true" />
							</div>
							<div class='col-xs-3'>
								<label>集团客户等级：</label>
							</div>
							<div class='col-xs-3'>
								<eoms:select name="bindDicselect.group_level_id" style="selectJT"
									dataDicTypeCode="group_level"
									value="${bindDicselect.group_level_id}" isnull="true" />
							</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class='col-xs-3'>
								<label>是否集团专线客户：</label>
							</div>
							<div class='col-xs-3'>
								<div class="radio" style="margin-bottom: 0px;margin-top: 0px;margin-left:10px;"
									id="is_group_zx">
									
									<label><input type="radio"
										name="bindDicselect.is_group_zx" value="1">是</label>&nbsp;&nbsp;&nbsp;
									<label><input type="radio"
										name="bindDicselect.is_group_zx" value="0">否</label>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							<div class='col-xs-3'>
								<label>是否企业宽带客户：</label>
							</div>
							<div class='col-xs-3'>
								<div class="radio" style="margin-bottom: 0px;margin-top: 0px;margin-left:10px;"
									id="is_qykd">
									<label><input type="radio" name="bindDicselect.is_qykd"
										value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
										type="radio" name="bindDicselect.is_qykd" value="0">否</label>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否集团V网客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								style="margin-bottom: 0px;margin-top: 0px;" id="is_group_vpmn">
								<label><input type="radio"
									name="bindDicselect.is_group_vpmn" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_group_vpmn" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">是否IDC客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_idc">
								<label><input type="radio" name="bindDicselect.is_idc"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_idc" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否IMS电话客户：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_ims">
								<label><input type="radio" name="bindDicselect.is_ims"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_ims" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						
						<div class='col-xs-6'>
							<label> </label>
						</div>
						</div>
						
						
						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								投诉情况
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">近三个月10086投诉次数：</label>
						</div>
						<div class='col-xs-3'>
							<select id="tscs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 19%;float: left; margin-left: 10px;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="complain_10086_counts" type='number'
								style="width: 77%" class='form-control'
								name='bindDicselect.complain_10086_counts'
								value='${bindDicselect.complain_10086_counts}' />
						</div>
							<div class='col-xs-3'>
								<label style="float: right;">其中：移动业务投诉次数：</label>
							</div>
							<div class='col-xs-3'>
								<select id="ywtscs" class='form-control'
									name="bindDicselectSymbol.symbol"
									style="width: 19%;float: left;margin-left: 10px;">
									<option value="0">></option>
									<option value="1">=</option>
									<option value="2"><</option>
								</select> <input id="complain_ydyw_counts" type='number'
									style="width: 77%" class='form-control'
									name='bindDicselect.complain_ydyw_counts'
									value='${bindDicselect.complain_ydyw_counts}' />
							</div>
							</div>
							<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class='col-xs-3'>
								<label style="float: right;">移动网络质量投诉次数：</label>
							</div>
							<div class='col-xs-3'>
								<select id="zltscs" class='form-control'
									name="bindDicselectSymbol.symbol"
									style="width: 19%;float: left;margin-left: 10px;">
									<option value="0">></option>
									<option value="1">=</option>
									<option value="2"><</option>
								</select> <input id="complain_yywl_counts" type='number'
									style="width: 77%" class='form-control'
									name='bindDicselect.complain_yywl_counts'
									value='${bindDicselect.complain_yywl_counts}' />
							</div>
							<div class='col-xs-3'>
								<label style="float: right;">家庭业务投诉次数：</label>
							</div>
							<div class='col-xs-3'>
								<select id="jtywtscs" class='form-control'
									name="bindDicselectSymbol.symbol"
									style="width: 19%;float: left;margin-left: 10px;">
									<option value="0">></option>
									<option value="1">=</option>
									<option value="2"><</option>
								</select> <input id="complain_jtyw_counts" type='number'
									style="width: 77%" class='form-control'
									name='bindDicselect.complain_jtyw_counts'
									value='${bindDicselect.complain_jtyw_counts}' />
							</div>
							</div>
							<div class="row" style="margin-left: 0px;margin-right: 0px;">
							<div class='col-xs-3'>
								<label style="float: right;">家庭宽带投诉次数：</label>
							</div>
							<div class='col-xs-3'>
								<select id="kdtscs" class='form-control'
									name="bindDicselectSymbol.symbol"
									style="width: 19%;float: left;margin-left: 10px;">
									<option value="0">></option>
									<option value="1">=</option>
									<option value="2"><</option>
								</select> <input id="complain_jtkd_counts" type='number'
									style="width: 77%" class='form-control'
									name='bindDicselect.complain_jtkd_counts'
									value='${bindDicselect.complain_jtkd_counts}' />
							</div>
						<div class='col-xs-3'>
							<label style="float: right;">宽带电视投诉次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;margin: auto;">
							<select id="dstscs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 19%;float: left;margin-left: 10px;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="complain_jtds_counts" type='number'
								style="width: 77%" class='form-control'
								name='bindDicselect.complain_jtds_counts'
								value='${bindDicselect.complain_jtds_counts}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">家庭宽带网络质量投诉次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;margin: auto;">
							<select id="wlzltscs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 19%;float: left;margin-left: 10px;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="complain_jtwl_counts" type='number'
								style="width: 77%" class='form-control'
								name='bindDicselect.complain_jtwl_counts'
								value='${bindDicselect.complain_jtwl_counts}' />
								</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">集团业务投诉次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;margin: auto;">
							<select id="jtywtszs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 19%;float: left;margin-left: 10px;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="complain_jt_counts" type='number' style="width: 77%"
								class='form-control' name='bindDicselect.complain_jt_counts'
								value='${bindDicselect.complain_jt_counts}' />
								</div>
						</div>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">增值业务投诉次数：</label>
						</div>
						<div class='col-xs-3'>
						<div style="width: 100%;margin: auto;">
							<select id="zzywtscs" class='form-control'
								name="bindDicselectSymbol.symbol"
								style="width: 19%;float: left;margin-left: 10px;">
								<option value="0">></option>
								<option value="1">=</option>
								<option value="2"><</option>
							</select> <input id="complain_zz_counts" type='number' style="width: 77%"
								class='form-control' name='bindDicselect.complain_zz_counts'
								value='${bindDicselect.complain_zz_counts}' />
								</div>
						</div>
						
						<div class='col-xs-6'>
							<label style="float: right;"> </label>
						</div>
						</div>
						
						<div class='col-xs-12' style="background-color: whitesmoke;">
							<h5 style="margin-top: 5px;">
								其他
							</h5>
						</div>
						<div class="row" style="margin-left: 0px;margin-right: 0px;">
						<div class='col-xs-3'>
							<label style="float: right;">是否开通VOLTE：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_ofer1">
								<label><input type="radio" name="bindDicselect.is_ofer1"
									value="1">是</label>&nbsp;&nbsp;&nbsp; <label><input
									type="radio" name="bindDicselect.is_ofer1" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div class='col-xs-3'>
							<label style="float: right;">近三月是否有VOLTE通信：</label>
						</div>
						<div class='col-xs-3'>
							<div class="radio" style="margin-bottom: 0px;margin-top: 0px;float: left;margin-left: 10px;"
								id="is_volte_3month">
								<label><input type="radio"
									name="bindDicselect.is_volte_3month" value="1">是</label>&nbsp;&nbsp;&nbsp;
								<label><input type="radio"
									name="bindDicselect.is_volte_3month" value="0">否</label>&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						</div>

					</div>
				</form>
			</div>
		</div>
</body>
</html>