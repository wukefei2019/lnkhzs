<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<title>智慧调研问卷</title>
<meta charset="UTF-8">
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,inital-scale=1.0,minimum-scale=0.5,maximum-scale=2.0,user-scalable=no">
<link href="../../assets/common/js/fileupload.css" rel="stylesheet">
<link href="../../apps/answerView.css" rel="stylesheet">
<script type='text/javascript'>
	var strs = new Array();
	//var dat = "";
	
	function setColor(data,exam){
		$(".question").find("div[buttonid="+exam+"]").find("input[name='radio']").css("background-color","");
		var $check = $(".question").find("div[buttonid="+exam+"]").find("input[button='radio']");
		var i =data-1

			$(".question").find("div[buttonid="+exam+"]").find("input[name='radio']").eq(i).css("background-color","#0498f4");
			if(i==0){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","A");
			}
			if(i==1){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","B");
			}
			if(i==2){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","C");
			}
			if(i==3){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","D");
			}
			if(i==4){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","E");
			}
			if(i==5){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","F");
			}
			if(i==6){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","G");
			}
			if(i==7){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","H");
			}
			if(i==8){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","I");
			}
			if(i==9){
				$(".question").find("div[buttonid="+exam+"]").attr("buttonGroupValue","J");
			}
			
		/* } */
		
	}

	function getisTrue(obj) {
		var hiLen = $("input[type=radio]").length;
		var num = 0;
		for (var i = 0; i < hiLen; i++) {
			if (($("input[type=radio]").eq(i).attr("hidequestion")) != undefined) {
				var ishide = $("input[type=radio]").eq(i).attr("hidequestion");
				var strs = [];
				strs = ishide.split(",");
				for (var z = 0; z < strs.length; z++) {
					var arr = strs[z];
					if (arr != "" && arr != undefined) {
						if (obj == arr) {
							num++;
						}
					}
					if (num > 1) {
						break;
					}
				}
			}
			if (num > 1) {
				break;
			}
		}
		if (num > 1) {
			return false;
		} else {
			return true;
		}

	}

	function hideQuestion(data, obj) {
		var rlength = $(obj).parent().parent().find("input[type=radio]").length;
		var ishide = "";
		for (var i = 0; i < rlength; i++) {
			if ($(obj).parent().parent().find("input[type=radio]").eq(i).attr("hidequestion") != undefined) {
				ishide = $(obj).parent().parent().find("input[type=radio]").eq(i).attr("hidequestion");
				var strs = [];
				strs = ishide.split(",");
				for (var z = 0; z < strs.length; z++) {
					var arr = strs[z];
					if (arr != "" && arr != undefined) {
						if (getisTrue(arr)) {
							for (var j = 0; j < $("#wenjuan").children().length; j++) {
								if ($("#wenjuan").children().eq(j).attr("myid") == arr) {
									$("#wenjuan").children().eq(j).removeAttr("style");
									$("#wenjuan").children().eq(j).addClass("required");
									
								}
							}
						}
					}
				}
				$(obj).parent().parent().find("input[type=radio]").eq(i).removeAttr("hidequestion");
				break;
			}
		}
		
		
		//********************************************************************
		//单选打分隐藏项
		var rlengths = $(obj).parent().parent().find("input[name='radio']").length;
		for (var i = 0; i < rlengths; i++) {
			if ($(obj).parent().parent().find("input[name='radio']").eq(i).attr("hidequestion") != undefined) {
				ishide = $(obj).parent().parent().find("input[name='radio']").eq(i).attr("hidequestion");
				var strs = [];
				strs = ishide.split(",");
				for (var z = 0; z < strs.length; z++) {
					var arr = strs[z];
					if (arr != "" && arr != undefined) {
						if (getisTrue(arr)) {
							for (var j = 0; j < $("#wenjuan").children().length; j++) {
								if ($("#wenjuan").children().eq(j).attr("myid") == arr) {
									$("#wenjuan").children().eq(j).removeAttr("style");
									$("#wenjuan").children().eq(j).addClass("required");
									
								}
							}
						}
					}
				}
				$(obj).parent().parent().find("input[name='radio']").eq(i).removeAttr("hidequestion");
				break;
			}
		}
		
		
		
		$(obj).attr("hidequestion", data);
		var strs = [];
		if (data != "") {
			strs = data.split(",");
			for (var z = 0; z < strs.length; z++) {
				var arr = strs[z];
				if (arr != "" && arr != undefined) {

					for (var j = 0; j < $("#wenjuan").children().length; j++) {
						if ($("#wenjuan").children().eq(j).attr("myid") == arr) {
							$("#wenjuan").children().eq(j).attr("style", "display:block;");
							$("#wenjuan").children().eq(j).attr("style", "display:none;");
							$("#wenjuan").children().eq(j).removeClass("required");
							break;
						}

					}

				}
			}
		} else {
			

		}

	}

	$(document).ready(function() {

		var id = "${param.id}";
		$.post($ctx + "/khzs/survey/ajaxGetDywj.action?khzsQuestionnaire.id=" + id).done(function(result) {
			var title = result.name == null ? '' : result.name;
			var titleSub = result.namesub == null ? '' : result.namesub;
			var status = result.status;
			if (status != '草稿') {
				$("#submit").show();
			}
			$("#title").text(title);
			$("#titleSub").text(titleSub);
		});

		$("#submit").on("click", function(event) {
		
			var answerList = new Array()
			var blankQu = '';
			$(".question").each(function(i, t) {
				var khzsAnswer = new Object();

				var type = $(t).attr("title");
				var id = $(t).attr("id");
				var index = $(t).find(".qu-index").text();
				var answer = '';
				var remark = '';
				if (type == "单选" || type == "判断") {
					var $check = $(t).find("input:radio:checked");
					if ($check.val() != undefined) {
						answer = $check.val();
					}
					remark = $(t).find("textarea").val();
				} else if (type == "多选") {
					var $check = $(t).find("input:checkbox:checked");
					$.each($check, function() {
						answer += $(this).val() + ",";
					});
					remark = $(t).find("textarea").val();
				} else if (type == "简答") {
					answer = $(t).find("textarea").val();
				} else if (type == "单项打分"){
					var $check = $(t).find("div[buttonid='"+id+"']").attr("buttonGroupValue");
					
					if ($check != undefined) {
						answer = $check;
					}
					remark = $(t).find("textarea").val();
				}

				if (answer.length == 0 && $(t).is('.required')) {
					blankQu += index + ",";
				}

				khzsAnswer.answer = answer;
				khzsAnswer.relationid = id;
				khzsAnswer.remark = remark;
				answerList.push(khzsAnswer);
			});

			var answerListStr = encodeURI(encodeURI(JSON.stringify(answerList)));
			if (blankQu.length > 0) {
				alert("请回答 " + blankQu + "题");
			} else {
				$.post($ctx + "/survey/answer/ajaxSaveQuestionAnswer.action?acswerListStr=" + answerListStr+"&id="+id).done(function() {
					alert("提交成功");
					window.location.href=$ctx + "/lnkhzs/survey/thanks.html";
					window.close();
				});
			}


		});


		$.post($ctx + "/khzs/surveys/ajaxGetQuestionInNaire.action?id=" + id).done(function(result) {
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
				
				var A = "A";
		var B = "B";
		var C = "C";
		var D = "D";
		var E = "E";
		var F = "F";
		var G = "G";
		var H = "H";
		var I = "I";
		var J = "J";


				if (lists != null) {

					for (var j = 0; j < lists.length; j++) {
						if (lists[j].A != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optiona += lists[j].A + ",";
						}
						if (lists[j].B != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionb += "," + lists[j].B;
						}
						if (lists[j].C != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionc += "," + lists[j].C;
						}
						if (lists[j].D != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optiond += "," + lists[j].D;
						}
						if (lists[j].E != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optione += "," + lists[j].E;
						}
						if (lists[j].F != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionf += "," + lists[j].F;
						}
						if (lists[j].G != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optiong += "," + lists[j].G;
						}
						if (lists[j].H != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionh += "," + lists[j].H;
						}
						if (lists[j].I != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optioni += "," + lists[j].I;
						}
						if (lists[j].J != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionj += "," + lists[j].J;
						}
						if (lists[j].K != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionk += "," + lists[j].K;
						}
						if (lists[j].L != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionl += "," + lists[j].L;
						}
						if (lists[j].M != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionm += "," + lists[j].M;
						}
						if (lists[j].N != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionn += "," + lists[j].N;
						}
						if (lists[j].O != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optiono += "," + lists[j].O;
						}
						if (lists[j].P != undefined && (result[i].type == '单选'||result[i].type == '判断'||result[i].type == '单项打分')) {
							optionp += "," + lists[j].P;
						}
					}
				}




				var type = result[i].type;
				var index = 1 + i;
				var id = result[i].id;


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

				if ( (type == '判断' || type == '单选') ) {
					questionStr = '<div myid="' + index + '" id="' + id + '" title="' + type + '" class="survey-content pic-type-box s-columns-1 question ';

					if (isanswer == "必答") {
						questionStr += 'required';
					}
					questionStr += '"><div class="s-question">';

					questionStr += '<span class="qu-index">' + index + '</span>\
						<h3>' + question + '<span class="stress">[' + type + ']</span></h3>\
					</div>\
			<div class="qu-remark js-set-remark"></div>';

					if (optionA != null && optionA.length > 0) {

						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  onclick="hideQuestion(\'' + optiona + '\',this)"  class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="A">' + optionA + '\
				</div>';


					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input  onclick="hideQuestion(\'' + optionb + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="B">' + optionB + '\
				</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionc + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="C">' + optionC + '\
				</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optiond + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="D">' + optionD + '\
				</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optione + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="E">' + optionE + '\
				</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionf + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="F">' + optionF + '\
				</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optiong + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="G">' + optionG + '\
				</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionh + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="H">' + optionH + '\
				</div>';
					}
					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optioni + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="I">' + optionI + '\
				</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionj + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="J">' + optionJ + '\
				</div>';
					}
					if (optionK != null && optionK.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionk + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="K">' + optionK + '\
				</div>';
					}
					if (optionL != null && optionL.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionl + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="L">' + optionL + '\
				</div>';
					}
					if (optionM != null && optionM.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionm + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="M">' + optionM + '\
				</div>';
					}
					if (optionN != null && optionN.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionn + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="N">' + optionN + '\
				</div>';
					}
					if (optionO != null && optionO.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optiono + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="O">' + optionO + '\
				</div>';
					}
					if (optionP != null && optionP.length > 0) {
						questionStr += '<div class="s-form s-radio rad-route route-item asso-item ">\
					<input onclick="hideQuestion(\'' + optionp + '\',this)" class="radioWidth" style="margin-top: 4px" type="radio" name="radio' + index + '" value="P">' + optionP + '\
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

				} else if (type == '简答') {
					questionStr = '<div myid="' + index + '" id="' + id + '" title="' + type + '" class="survey-content question ';

					if (isanswer == "必答") {
						questionStr += 'required';
					}
					questionStr += '"><div class="s-question">';
					questionStr += '<span class="qu-index">' + index + '</span>\
				<h3>' + question + '<span class="stress">[' + type + ']</span></h3>\
			</div>\
			<div class="qu-remark js-set-remark"></div>\
			<div class="s-form s-textarea" style="clear: none;">\
				<div class="verify-box">\
					<i class="icon ifont-verify-type-00 verify-icons"></i>\
					<textarea style="font-size:24px;width: 100%" rows="3"></textarea>\
				</div>\
			</div>\
			</div>';
				} else if (type == '多选') {
					questionStr = '<div myid="' + index + '" id="' + id + '" title="' + type + '" class="survey-content pic-type-box s-columns-1 question ';

					if (isanswer == "必答") {
						questionStr += 'required';
					}
					questionStr += '"><div class="s-question">';
					questionStr += '<span class="qu-index">' + index + '</span>\
				<h3>' + question + '<span class="stress">[' + type + ']</span></h3>\
			</div>\
			<div class="qu-remark js-set-remark"></div>';
					if (optionA != null && optionA.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="A">' + optionA + '\
						</div>';
					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="B">' + optionB + '\
						</div>';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="C">' + optionC + '\
						</div>';
					}
					if (optionD != null && optionD.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="D">' + optionD + '\
						</div>';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="E">' + optionE + '\
						</div>';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="F">' + optionF + '\
						</div>';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="G">' + optionG + '\
						</div>';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="H">' + optionH + '\
						</div>';
					}

					if (optionI != null && optionI.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="I">' + optionI + '\
						</div>';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="J">' + optionJ + '\
						</div>';
					}
					if (optionK != null && optionK.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="K">' + optionK + '\
						</div>';
					}
					if (optionL != null && optionL.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="L">' + optionL + '\
						</div>';
					}
					if (optionM != null && optionM.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="M">' + optionM + '\
						</div>';
					}
					if (optionN != null && optionN.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="N">' + optionN + '\
						</div>';
					}
					if (optionO != null && optionO.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="O">' + optionO + '\
						</div>';
					}
					if (optionP != null && optionP.length > 0) {
						questionStr += '<div class="s-form s-checkbox chc-route route-item asso-item ">\
						<input class="radioWidth" style="margin-top: 4px" type="checkBox" value="P">' + optionP + '\
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

				} else if ( (type == '单项打分') ) {
					questionStr = '<div myid="' + index + '" id="' + id + '" title="' + type + '" class="survey-content pic-type-box s-columns-1 question ';

					if (isanswer == "必答") {
						questionStr += 'required';
					}
					questionStr += '"><div class="s-question">';

					questionStr += '<span class="qu-index">' + index + '</span>\
						<h3>' + question + '<span class="stress">[' + type + ']</span></h3>\
					</div>\
			<div class="qu-remark js-set-remark"></div>';

					if (optionA != null && optionA.length > 0) {


						questionStr += '<span  style="width:100px;font-size: 20px;margin-left: 35px;">非常不满意<———>非常满意</span><br/> \
						<div  class="btn-group" role="group" aria-label="First group" buttonid="'+id+'"> \
					<input onclick="setColor(1,\'' + id + '\');hideQuestion(\'' + optiona + '\',this)"  class="btn btn-default" examl="A" style="margin-top: 4px;width:40px;height: 30px;margin-left: 35px;" type="button" name="radio" value="1"> \
				';
					}
					if (optionB != null && optionB.length > 0) {
						questionStr += '<input onclick="setColor(2,\'' + id + '\');hideQuestion(\'' + optionb + '\',this)"  class="btn btn-default" examl="B" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="2"> \
				';
					}
					if (optionC != null && optionC.length > 0) {
						questionStr += '\
					<input onclick="setColor(3,\'' + id + '\');hideQuestion(\'' + optionc + '\',this)"  class="btn btn-default" examl="C" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="3"> \
				';
					}
					 if (optionD != null && optionD.length > 0) {
						questionStr += '\
					<input onclick="setColor(4,\'' + id + '\');hideQuestion(\'' + optiond + '\',this)" class="btn btn-default" examl="D" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="4"> \
				';
					}
					if (optionE != null && optionE.length > 0) {
						questionStr += '\
					<input onclick="setColor(5,\'' + id + '\');hideQuestion(\'' + optione + '\',this)" class="btn btn-default" examl="E" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="5"> \
				';
					}
					if (optionF != null && optionF.length > 0) {
						questionStr += '\
					<input onclick="setColor(6,\'' + id + '\');hideQuestion(\'' + optionf + '\',this)" class="btn btn-default" examl="F" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="6"> \
				';
					}
					if (optionG != null && optionG.length > 0) {
						questionStr += '\
					<input onclick="setColor(7,\'' + id + '\');hideQuestion(\'' + optiong + '\',this)" class="btn btn-default" examl="G" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="7"> \
				';
					}
					if (optionH != null && optionH.length > 0) {
						questionStr += '\
					<input onclick="setColor(8,\'' + id + '\');hideQuestion(\'' + optionh + '\',this)" class="btn btn-default" examl="H" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="8"> \
				';
					}
					if (optionI != null && optionI.length > 0) {
						questionStr += '\
					<input onclick="setColor(9,\'' + id + '\');hideQuestion(\'' + optioni + '\',this)" class="btn btn-default" examl="I" style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="9"> \
				';
					}
					if (optionJ != null && optionJ.length > 0) {
						questionStr += '\
					<input onclick="setColor(10,\'' + id + '\');hideQuestion(\'' + optionj + '\',this)" class="btn btn-default" examl="J"  style="margin-top: 4px;width:40px;height: 30px;" type="button" name="radio" value="10"> \
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

				}
				$("#wenjuan").append(questionStr);

			}
		});

	});
</script>
</head>
<body>
	<div style="font-size:44px" align="center" id="title"></div>
	<div style="font-size:20px" align="center" id="titleSub"></div>
	<div class="survey-container" id="wenjuan"></div>
	<div align="right">
		<a id="submit" style="display: none" class="s-btn-submit"
			href="javascript:;">提交问卷</a>
	</div>
</body>
</html>