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
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>

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

<script type='text/javascript'>
	var shipID = "";
	function seleJD(idss,num) {
	//console.log(idss)
		openwindow($ctx + '/lnkhzs/survey/showJD.jsp?id=' + idss+"&num="+num, '');
	}
	
	var id ="";

	$(document).ready(function() {
	 id = "${param.id}";
		//ids = id;

		var type = "${param.type}";
		
		var mylength = $("body").find("input").length;
		


		/* *************************************************** */
	

	var arrYC = new Array();
	var nDate = "";
	seleTK(nDate);
	
	//seleBFB();
		
	initCharts();
	});
	
	function seleBFB(){
		var tja = "";
		var tjb = "";
		var tjc = "";
		var tjd = "";
		var tje = "";
		var tjf = "";
		var tjg = "";
		var tjh = "";
		var tji = "";
		var tjj = "";
		var tjk = "";
		var tjl = "";
		var tjm = "";
		var tjn = "";
		var tjo = "";
		var tjp = "";
		$("#inpu").attr("value",id);
		shipID = id;
		//百分比
		seleAnswer();
		function seleAnswer() {
			$.post($ctx + '/khzs/survey/seleAnswer.action?khzsQuestionnaire.id=' + id).done(function(result) {
				for (var i = 0; i < $("div[myname='searchid']").length; i++) {
					var myid = $("div[myname='searchid']").eq(i).attr("id");
					for (var j = 0; j < result.length; j++) {
						if (myid == result[j].myid) {

							$("div[myname='searchid']").eq(i).find("input").eq(0).next().append("  (" + result[j].tjA + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(1).next().append("  (" + result[j].tjB + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(2).next().append("  (" + result[j].tjC + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(3).next().append("  (" + result[j].tjD + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(4).next().append("  (" + result[j].tjE + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(5).next().append("  (" + result[j].tjF + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(6).next().append("  (" + result[j].tjG + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(7).next().append("  (" + result[j].tjH + ")");

							$("div[myname='searchid']").eq(i).find("input").eq(8).next().append("  (" + result[j].tjI + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(9).next().append("  (" + result[j].tjK + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(10).next().append("  (" + result[j].tjK + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(11).next().append("  (" + result[j].tjL + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(12).next().append("  (" + result[j].tjM + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(13).next().append("  (" + result[j].tjN + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(14).next().append("  (" + result[j].tjO + ")");
							$("div[myname='searchid']").eq(i).find("input").eq(15).next().append("  (" + result[j].tjP + ")");
						}
					}
				}
			});
		}
		
	}
	
	function seleTK(value){
	$("#wenjuan").empty();
	$.post($ctx + "/khzs/surveys/ajaxGetQuestionInNaire.action?id=" + id+"&nDate="+value).done(function(result) {

			for (var i = 0; i < result.length; i++) {
				var question = result[i].question;
				var lists = result[i].list;

				var optiona = "";
				var optionb = "";
				var optionc = "";
				var optiond = "";
				var optione = "";
				var optionf = "";
				var optiong = "";
				var optionh = "";
				var optioni = "";
				var optionj = "";
				var optionk = "";
				var optionl = "";
				var optionm = "";
				var optionn = "";
				var optiono = "";
				var optionp = "";


				if (lists != null) {

					for (var j = 0; j < lists.length; j++) {
						if (lists[j].A != undefined && result[i].type == '单选') {
							//optiona += lists[j].A + ",";

						}
						if (lists[j].B != undefined && result[i].type == '单选') {
							optionb += "," + lists[j].B;
						}
						if (lists[j].C != undefined && result[i].type == '单选') {
							optionc += "," + lists[j].C;
						}
						if (lists[j].D != undefined && result[i].type == '单选') {
							optiond += "," + lists[j].D;
						}
						if (lists[j].E != undefined && result[i].type == '单选') {
							optione += "," + lists[j].E;
						}
						if (lists[j].F != undefined && result[i].type == '单选') {
							optionf += "," + lists[j].F;
						}
						if (lists[j].G != undefined && result[i].type == '单选') {
							optiong += "," + lists[j].G;
						}
						if (lists[j].H != undefined && result[i].type == '单选') {
							optionh += "," + lists[j].H;
						}
						if (lists[j].I != undefined && result[i].type == '单选') {
							optioni += "," + lists[j].I;
						}
						if (lists[j].J != undefined && result[i].type == '单选') {
							optionj += "," + lists[j].J;
						}
						if (lists[j].K != undefined && result[i].type == '单选') {
							optionk += "," + lists[j].K;
						}
						if (lists[j].L != undefined && result[i].type == '单选') {
							optionl += "," + lists[j].L;
						}
						if (lists[j].M != undefined && result[i].type == '单选') {
							optionm += "," + lists[j].M;
						}
						if (lists[j].N != undefined && result[i].type == '单选') {
							optionn += "," + lists[j].N;
						}
						if (lists[j].O != undefined && result[i].type == '单选') {
							optiono += "," + lists[j].O;
						}
						if (lists[j].P != undefined && result[i].type == '单选') {
							optionp += "," + lists[j].P;
						}
					}
				}




				var type = result[i].type;
				var index = 1 + i;
				var ids = result[i].id;



				var optionA = result[i].optionA;
				var optionB = result[i].optionB;
				var optionC = result[i].optionC;
				var optionD = result[i].optionD;
				var optionE = result[i].optionE;
				var optionF = result[i].optionF;
				var optionG = result[i].optionG;
				var optionH = result[i].optionH;

				var optionI = result[i].optionI;
				var optionJ = result[i].optionJ;
				var optionK = result[i].optionK;
				var optionL = result[i].optionL;
				var optionM = result[i].optionM;
				var optionN = result[i].optionN;
				var optionO = result[i].optionO;
				var optionP = result[i].optionP;

				var optionRemark = result[i].remark;

				var isanswer = result[i].isanswer;

				var questionStr = '';
				if (type == '判断' || type == '单选') {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content pic-type-box s-columns-1  required">\
					<div  class="s-question">\
				<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>';
					if (optionA != null && optionA.length > 0) {

						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input   class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="A">' + optionA + '<label></label>' + '\
				</div>';


					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input   class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="B">' + optionB + '<label></label>' + '\
				</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="C">' + optionC + '<label></label>' + '\
				</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="D">' + optionD + '<label></label>' + '\
				</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="E">' + optionE + '<label></label>' + '\
				</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="F">' + optionF + '<label></label>' + '\
				</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="G">' + optionG + '<label></label>' + '\
				</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="H">' + optionH + '<label></label>' + '\
				</div>';
					}
					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="I">' + optionI + '<label></label>' + '\
				</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="J">' + optionJ + '<label></label>' + '\
				</div>';
					}
					if (optionK != null && optionK.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="K">' + optionK + '<label></label>' + '\
				</div>';
					}
					if (optionL != null && optionL.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="L">' + optionL + '<label></label>' + '\
				</div>';
					}
					if (optionM != null && optionM.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="M">' + optionM + '<label></label>' + '\
				</div>';
					}
					if (optionN != null && optionN.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="N">' + optionN + '<label></label>' + '\
				</div>';
					}
					if (optionO != null && optionO.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="O">' + optionO + '<label></label>' + '\
				</div>';
					}
					if (optionP != null && optionP.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="P">' + optionP + '<label></label>' + '\
				</div>';
					}

					questionStr += '</div>';

				} else if (type == '简答') {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content  required">\
					<div class="s-question">\
				<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>\
			<div class="s-form s-textarea" style="clear: none;">\
				<div class="verify-box">\
					<i class="icon ifont-verify-type-00 verify-icons"></i>\
					<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',-1)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>\
			</div>\
			</div>';
				}else if (type == '位置') {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content  required">\
					<div class="s-question">\
				<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>\
			<div class="s-form s-textarea" style="clear: none;">\
				<div class="verify-box">\
					<i class="icon ifont-verify-type-00 verify-icons"></i>\
					<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',-1)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>\
			</div>\
			</div>';
				} else if (type == '多选') {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content pic-type-box s-columns-1  required">\
					<div class="s-question">\
				<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>';
					if (optionA != null && optionA.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="A">' + optionA + '<label></label>' + '\
						</div>';
					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="B">' + optionB + '<label></label>' + '\
						</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="C">' + optionC + '<label></label>' + '\
						</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="D">' + optionD + '<label></label>' + '\
						</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="E">' + optionE + '<label></label>' + '\
						</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="F">' + optionF + '<label></label>' + '\
						</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="G">' + optionG + '<label></label>' + '\
						</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="H">' + optionH + '<label></label>' + '\
						</div>';
					}

					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="I">' + optionI + '<label></label>' + '\
						</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="J">' + optionJ + '<label></label>' + '\
						</div>';
					}
					if (optionK != null && optionK.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="K">' + optionK + '<label></label>' + '\
						</div>';
					}
					if (optionL != null && optionL.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="L">' + optionL + '<label></label>' + '\
						</div>';
					}
					if (optionM != null && optionM.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="M">' + optionM + '<label></label>' + '\
						</div>';
					}
					if (optionN != null && optionN.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="N">' + optionN + '<label></label>' + '\
						</div>';
					}
					if (optionO != null && optionO.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="O">' + optionO + '<label></label>' + '\
						</div>';
					}
					if (optionP != null && optionP.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="P">' + optionP + '<label></label>' + '\
						</div>';
					}

					questionStr += '</div>';
				} else if ( (type == '单项打分') ) {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content pic-type-box s-columns-1 question ';

					if (isanswer == "必答") {
						questionStr += 'required';
					}
					questionStr += '"><div class="s-question">';

					questionStr += '<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>';

					if (optionA != null && optionA.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="A">' + optionA + '<label></label>' + '\
					</div>';
					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="B">' + optionB + '<label></label>' + '\
					</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="C">' + optionC + '<label></label>' + '\
					</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="D">' + optionD + '<label></label>' + '\
						</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="E">' + optionE + '<label></label>' + '\
						</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="F">' + optionF + '<label></label>' + '\
						</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="G">' + optionG + '<label></label>' + '\
						</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input  class="radioWidth" style="margin-top: 4px" type="checkBox" value="H">' + optionH + '<label></label>' + '\
						</div>';
					}
					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="I">' + optionI + '<label></label>' + '\
						</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="J">' + optionJ + '<label></label>' + '\
						</div>';
					}
					
					if (optionRemark == 1) {
						questionStr += '<div class="s-form s-textarea" style="clear: none;">\
					<div class="verify-box">\
						<i class="icon ifont-verify-type-00 verify-icons"></i>\
						<textarea style="font-size:24px;width: 100%" rows="3"></textarea>\
					</div>\
				</div>';
					}

					questionStr += '</div>';

				} else if (type == '填空简答' ) {
					questionStr = '<div log="log" myid="' + index + '" myname="searchid" id="' + ids + '" title="' + type + '" class="survey-content pic-type-box s-columns-1  required">\
					<div  class="s-question">\
				<h3>\
				<span class="qu-index">' + index + '</span>' + question + '<span class="stress">[' + type + ']\
				</span></h3></div>\
			<div class="qu-remark js-set-remark"></div>';

					if (optionA != null && optionA.length > 0) {

						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input   class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="A">' + optionA + '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',0)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';


					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input   class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="B">' + optionB  + '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',1)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="C">' + optionC +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',2)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="D">' + optionD +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',3)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="E">' + optionE +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',4)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="F">' + optionF + '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',5)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="G">' + optionG +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',6)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="H">' + optionH +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',7)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="I">' + optionI +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',8)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="J">' + optionJ +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',9)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionK != null && optionK.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="K">' + optionK +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',10)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionL != null && optionL.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="L">' + optionL +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',11)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionM != null && optionM.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="M">' + optionM +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',12)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionN != null && optionN.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="N">' + optionN +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',13)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionO != null && optionO.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="O">' + optionO +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',14)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}
					if (optionP != null && optionP.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="P">' + optionP +  '\
						<input   class="radioWidth" onclick="seleJD(\'' + ids + '\',15)" style="border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;" type="button" value="查看">' + '\
				</div>';
					}

					questionStr += '</div>';

				}else if (type == '矩阵填空') {
				questionStr = "<div myid='" + index + "' id='" + id + "' title='" + type + "' class='survey-content question ";

				if (isanswer == "必答") {
					questionStr += "required";
				}
				questionStr += "'><div class='s-question'>";
				questionStr += "" +
					"<h3><span class='qu-index'>" + index + "</span>" + question + "<span class='stress'>[" + type + "]</span></h3></div>" +
					"<div class='qu-remark js-set-remark'></div>" +
					"<div class='s-form s-textarea' style='clear: none;'>" +
					"<div class='verify-box'><i class='icon ifont-verify-type-00 verify-icons'></i>";
				let row = optionA.split("#*#*#*");
				let col = optionB.split("#*#*#*");
				questionStr +="<table>";
				questionStr +="<tr><td></td>";
				for(var ci=0;ci<col.length;ci++){
					questionStr +="<td>"+col[ci]+"</td>";
				}
				questionStr +="</tr>";
				for(var ri=0;ri<row.length;ri++){
					
					questionStr +="<tr><td>"+row[ri]+"</td>";
					for(var ci=0;ci<col.length;ci++){
						var mynumber1=col.length*(ri)+ci;
						questionStr +="<td><input class='radioWidth' onclick='seleJD(\"" + ids + "\","+mynumber1+")' style='border-radius:25px;border:none;margin-top:5px;background-color: #ACD6FF;width: 45px;height: 25px;' type='button' value='查看' /></td>";
					}
					questionStr +="</tr>";
				}
				questionStr +="</table>";
				questionStr +="</div></div></div>";
			}
				$("#wenjuan").append(questionStr);
			}
			
			seleBFB();
		});
	
	}




	//***************************************************************************************
	 function getParams() {
		var id = "${param.id}";
		var params = "id=" + id;
		return params;
	}


//*******echarts********
	function initCharts() {
		var urlParam = getParams();
		// 基于准备好的dom，初始化echarts实例
	 var myChartA = echarts.init(document.getElementById('_top'));
		myChartA.clear();
		getLineJsonById(myChartA, urlParam, [ "12%", "12%" ], [ '#ace2fe', '#87cbfb' ]);
		
		myChartA.on('click', function (e){  
				console.log(e);
			var data = e.name;
			seleTK(data);
			//seleBFB();
					
		    });

	}

	function getLineJsonById(myChart, param, placeArr, colorArr) {
		var id = "${param.id}";
		var url = $ctx + "/tsgd/tsgd/comYZ.action?questionnaireid="+ id +"&sqlName='SQL_IND_YZ.query'&"+encodeURI(param);
		
		$.post(url).done(function(dataArr) {
			var data = dataArr;
			var nameTemp = new Array();
			var valueTemp = new Array();
			for (var i = 0; i < data.length; i++) {
				var value = data[i].rowHashMap.VALUE;
				var name = data[i].rowHashMap.MON;
				nameTemp[i] = name;
				valueTemp[i] = value;
			}
			option_Line.yAxis.name = '答题量';
			option_Line.xAxis.data = nameTemp;
			/* option_Line.series[0].areaStyle.color.colorStops[0].color = colorArr[0]; */
			option_Line.series[0].color = colorArr[1];
			/* option_Line.grid.left = placeArr[0];
			option_Line.grid.right = placeArr[1]; */
			option_Line.series[0].data = valueTemp;
			myChart.setOption(option_Line);

		});
	}

	 // 指定图表的配置项和数据
	 var option_Line = {
				title:{
					text:''
				},
				tooltip:{},
				legend:{
					data:['答题量']
				},
				xAxis:{
					data:[]
				},
				yAxis:{},
				series:[{
					name:'答题量',
					type:'bar',
					data: [],
					barWidth : 40,
					legendHoverLink:true,
					label:{						//---图形上面的文本标签显示数量
	                	show:false,
	                	position:'insideTop',	//---相对位置
	                	rotate:0,				//---旋转角度
	                	color:'#eee',
	                },
	                itemStyle:{					//---图形形状
	                	color:'#2a7dcf',
	                	barBorderRadius:[18,18,0,0],
	                },
				}]
				};
				
				
		
</script>
<meta charset="UTF-8">
<title>问卷调研统计</title>
</head>
<body>
	<button onclick = "exportexcel()">导出问卷明细</button>
	
	<div class="Ct Dialog contract form-page">
		<div id="flow" class='panel panel-primary'>
	
		<div id="formTable" class='panel panel-primary'>
			
			<div id="_top" style="width:700px;height: 400px;margin-top:100px;margin-left: 200px;">
		    </div>

			<div id="logs" class="collapse in">
				<div class="survey-container" id="wenjuan"></div>
			</div>

		</div>
		
		
	</div>
</body>

<script type="text/javascript">
function exportexcel(){
	$.post($ctx + '/khzs/survey/exportExcel.action?khzsQuestionnaire.id=' + id).done(function(dataArr) {
		
		var exporurl = $ctx + "/omcs/baseExport/exportExcel.action?path="+dataArr;
		openwindow(exporurl,'',600,600)
	});
	
}
</script>
</html>