<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setBundle basename="i18n.Messages" var="i18Bundle"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<!DOCTYPE html>
<html>
  	<head>
	    <title><eoms:lable name="com_t_title"/></title>
	    <link href="${ctx}/common/style/omcs/css/base/MyReset.css" rel="stylesheet" type="text/css" />
	  	<link href="${ctx}/common/style/omcs/css/page/login.css" rel="stylesheet" type="text/css" />
	  	<script type="text/javascript" src="${ctx}/common/style/omcs/js/base/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/common/style/omcs/js/common/placeholder.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#tab_ps").click(function(){
				    $(".2d").css('display','block'); 
					$(".ps").css('display','none'); 
				});
				$("#tab_2d").click(function(){
				    $(".ps").css('display','block'); 
					$(".2d").css('display','none'); 
				});
				$("#login").click(function(){
					login();
				});
				$(".close").click(function(){
				    $(".error").css('display','none'); 
				});
			  	if (window != top)
				{
				 	top.location.href = location.href;
				}
			  	if('${msg}'!=''){
			  		$("#message").text('${msg}'); 
			  		$(".error").show(); 
			  		setTimeout(function () {
			  			$(".error").fadeOut();;
			  	    }, 3000);
			  	}else if('<eoms:lable name='sm_msg_loginMsg'/>'!=''){
			  		$("#message").text('<eoms:lable name='sm_msg_loginMsg'/>'); 
			  		$(".error").show(); 
			  		setTimeout(function () {
			  			$(".error").fadeOut();;
			  	    }, 3000);
			  	}
			  	$("#pwd").keyup(function(event){  
			  	  	if(event.keyCode ==13){ 
				  	  	if(loginChk()){
							$("#login_form").submit();
						}
			  	  	}  
			  	});  
			});
			
			
			function err(){
				$(".error").css('display','block'); 
			}
			function loginChk(){
				var reg = /^\w{1,20}$/;
				if(reg.test($("#loginName").val()))
				{
					$(".error").css('display','none'); 
					return true;
				}
				else
				{
					err();
				    $("#loginName").focus();
					return false;
				}
			}
			function login(){
				if(loginChk()){
					$("#login_form").submit();
				}
			}
		</script>
  	</head>
 
  	<body >
  		<form id="login_form" action="${ctx}/portal/login.action" method="post" id="Form1" onsubmit="return loginChk()">
		<div class="login" >
			<div class="login_box">
				<!--常规登录  -->
				<div class="ps" id="ps">
<!-- 					<div class="jiao" id="tab_ps" > -->
						
<!-- 					</div> -->
<!-- 					<span class="note"> -->
<!-- 						<s><i></i></s>扫描二维码登录系统 -->
<!-- 					</span> -->
					
					<p class="marginTop20">
						<input type="text"  class="onfocus" placeholder="用户名" value="${loginName}" name="loginName"  id="loginName"/>
					</p>
					<p class="marginTop20">
						<input type="password"  class="onblur" placeholder="密码" name="pwd" id="pwd"/>
					</p>
					<p>
						<input type="button" class="btn_login"  id="login" />
					</p>
					<div class="copyright">中国移动通信集团辽宁有限公司</div>
				</div>
				<!-- 二维码登录  -->
<!-- 				<div class="2d" id="2d" style="display: none;"> -->
<!-- 					<div class="jiao" id="tab_2d"></div> -->
<!-- 					<H1 class="logo"></H1> -->
<!-- 					<div class="scan_2d"> -->
<!-- 					</div> -->
<!-- 					<div class="phone"><span>维护热线电话：</span>010-52686688转2750；&nbsp;&nbsp;<span>热线手机：</span>13856101414	</div> -->
<!-- 					<div class="copyright">中国移动通信集团公司	</div> -->
<!-- 				</div> -->
			</div>
			<!--登录出错，提示信息 -->
			<div class="error">
				<p id="message"></p>
			</div>
		</div>
		</form>
	</body>
</html>
