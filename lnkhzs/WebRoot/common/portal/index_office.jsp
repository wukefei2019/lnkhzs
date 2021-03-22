<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<html xmlns="http://www.w3.org/1999/xhtml" style="height:100%">
	<head>
		<%@ include file="/common/core/taglib_omcs.jsp"%>
		<script type="text/javascript" src="${ctx}/common/plugin/digitclock/jquery.digitclock.js" ></script>

		<link href="${ctx}/common/plugin/menu/css" rel="stylesheet" type="text/css">
		<link href="${ctx}/common/plugin/menu/css(1)" rel="stylesheet" type="text/css">
		<%-- <link rel="stylesheet" href="${ctx}/common/plugin/menu/base.css"> --%>
		<link rel="stylesheet" href="${ctx}/common/plugin/menu/style.css">
		<script>
    		$(document).ready(function() {
        	$( '.dropdown' ).hover(
           		function(){
                	$(this).children('.sub-menu').slideDown(30);
            	},
            	function(){
                	$(this).children('.sub-menu').slideUp(30);
            	}
        		);
    		}); // end ready
		</script>

		<title>辽宁移动服务质量管理平台</title>
		<script type="text/javascript">
		
			
			$(document).ready(function(){
				$("#time").DigitClock({'servertime':'${time}'});
				/* showframe('0','${ctx}/lnkhzs/dutyHelper/mydutyDater.jsp'); */
				if(self!=top){  
					top.location.href=location.href;  
					parent.location.href=location.href; 
				}

				$(".xiala_menu-backdrop").on("click",function(){
				    $(".xiala_menu-backdrop").hide();
				    $(".xiala_ul").hide();
				    $(".yiji li").removeClass("hover-li");
				})
				showframe('402881ac6eaa86a3016eaa9ae3dc0001','${ctx}/lnkhzs/index/index.jsp');
				$.post('${ctx}/portalAction/getEntriesNumber.action').done(function(data) {
					$('#entriesNumber').text(data);
				})
			});
			$(window).resize(function(){
				setIframeHeight();
			});
			//计算iframe1 高度
			function setIframeHeight(){
			    var iframeHeight = $(window).height() - $(".headerBox").height() - $(".footer").height();
			    //是否是IE
// 			    console.debug(navigator,navigator.userAgent,navigator.appName)
			    $("#iframe1").attr("height",iframeHeight);
			}
			function showframe(navid, url, target,event) {
				if (navid == '0' && url.indexOf("index.jsp")>0) {
					$(".js-gz .list").addClass("tab_active").siblings().removeClass("tab_active");
				}
				
			    $(".yiji li").removeClass("activeLi hover-li");
			    var menuId = null;
			    if(event){
			    	menuId = $(event.target||event.srcElement).parents(".xiala_ul").attr("id");
			    }
			  	
			    if(!menuId){
			        menuId = navid;
			    }
			    $(".yiji li[menu-id='" + menuId + "']").addClass("activeLi");
			    $(".xiala_ul").hide();
			    $(".xiala_menu-backdrop").hide();
				if (url != '' && url != '${ctx}') {
					setIframeHeight();
					url = '${ctx}/portal/index_office_frame.action?navid=' + navid + '&pageurl=' + encodeURI(url);
					if (!!target) {
						open(url);
					} else {
    					$("#iframe1").attr("src", url);
    					$("#iframe1").load(function () {
    						window.scrollTo(0, 0);
    					});
					}
				}
				
				//1111
				$("ul.sub-menu").attr("style","display: none;background-color: #1c71a0;z-index: 999;");
				
			}
			

			setInterval(function(){ 
				$.post('${ctx}/portalAction/getEntriesNumber.action').done(function(data) {
					$('#entriesNumber').text(data);
				})
			}, 300000);
			
		</script>
	</head>
	<body style="overflow: hidden; height:100%">
    	<!-- <div class="divsection"> -->
	    	<form style="overflow: hidden; height:100%">
	    		<div class="headerBox">
		    		<div class="header">
		    			<div class="Ct" >
			    			<table class="ui-table">
			    				<tr>
			    					<td width="500px">
			    						<div class="logo"></div>
			    						<label class="fontSize24 color_blue">辽宁移动服务质量管理平台</label>
			    					</td>
			    					<td>
			    						<div class="floatRight fontSize14" style="white-space:nowrap;">
			    							<label id="time"></label>
					    					&nbsp;&nbsp;<a id="entriesNumber" class="color_blue mouseCursor"></a>
			    							<div class="userIcon"></div>
			    							<a class="color_blue mouseCursor">${userSession.fullName}</a>
<%-- 			    							<div class="iconConcern"></div>
			    							<a class="color_blue mouseCursor" onclick="openwindow('${ctx}/zljk/firstPageAction/index_order.action','',1200,900)">我的关注</a> --%>
			    							<div class="back"></div>
			    							<a class="color_blue mouseCursor" href="userLogout.action?logoutType=" target="_top">退出</a>
			    						</div>
			    					</td>
			    				</tr>
			    			</table>	    				
		    			</div>
		    		</div>

					<div class="nav">
						<div class="mainbav">
							<nav style="padding: 0 0;">
    							<ul class="yiji" style="margin-left: -90px">
    							<c:if test="${session.userSession.loginName!='ultrapower01'}">
    							<c:forEach var="menu" items="${navigateList}" varStatus="status">
									<c:if test="${menu.parentid=='0'&&menu.id!='402894f5295d39ec01295e5724bf0002'&&menu.id!='402894f5295e64ce01295e73d8570006'&&menu.id!='297e39d129818eb601298196bb9c0001'}">
										<c:if test="${menu.text=='首页'}">
											<li  menu-id='297e39d129818eb601298196bb9c0001'  onclick="showframe('${menu.id}','${ctx}/${menu.userdata.url}',null,event);showInfo1(event,'hover-li','0')">首页</li>
										</c:if>
										<c:if test="${menu.text!='首页'}">
										<li class="dropdown" menu-id='${menu.id}' onclick="showInfo1(event,'hover-li','${menu.id}');showInfo1(event,'hover-li','0')">${menu.text}
											<ul class="sub-menu" id="${menu.id}" style="display: none;background-color: #1c71a0;z-index: 999;">
					            				<c:forEach var="menu2" items="${navigateList}" varStatus="status2">
				            						<c:if test="${menu2.parentid == menu.id}">
														<%-- <li style="text-align: left;min-width: 220px;" class="dropdown" menu-id='${menu2.id}' onclick="showInfo1(event,'hover-li','${menu2.id}');showInfo1(event,'hover-li','0')"> --%>
					            							<%-- <a href="#" onclick="showframe('${menu2.id}','${ctx}/${menu2.userdata.url}',null,event)">${menu2.text}</a><c:if test='${!empty menuMap[m2.id]}'>&gt;</c:if> --%>
					            						<li style="text-align: left;min-width: 220px;" class="dropdown" menu-id='${menu2.id}'>
					            							<a href="#">${menu2.text}</a><c:if test='${!empty menuMap[m2.id]}'>&gt;</c:if>
					            							<c:set var="menu3have" value="false" />
					            							<c:forEach var="menu3" items="${navigateList}">
							            						<c:if test="${menu3.parentid == menu2.id}">
							            							<c:set var="menu3have" value="true" />
								            					</c:if>
							            					</c:forEach>
					            							<c:if test="${menu3have=='true'}">
						            							<ul class="sub-menu" myul="myul" id="${menu2.id}" style="display: none;background-color: #14638f;">
						            								<c:forEach var="menu3" items="${navigateList}" varStatus="status3">
						            									<c:if test="${menu3.parentid == menu2.id}">
						            										<li style="text-align: left;min-width: 220px;" onclick="showInfo1(event,'hover-li','${menu3.id}');showInfo1(event,'hover-li','0')" class="dropdown" menu-id='${menu3.id}' >
						            										<a href="#" onclick="showframe('${menu3.id}','${ctx}/${menu3.userdata.url}',null,event)">${menu3.text}</a></li>
						            									</c:if>
						            								</c:forEach>
						            							</ul>
						            						</c:if>
					            						</li>
						            				</c:if>
					            				</c:forEach>
						            			
											</ul>
											
						         
										</li>
										</c:if>
									</c:if>
						       	</c:forEach>
						       	</c:if>

						       	<c:if test="${session.userSession.loginName=='Demo'}">
									<li  menu-id='297e39d129818eb601298196bb9c0001'  onclick="showframe('0','${ctx }/portal/content_frame.action?menuid=297e39d129818eb601298196bb9c0001',null,event);showInfo1(event,'hover-li','0')">系统管理</li>
								</c:if>
    						</ul>
						</nav>
					<div class="xiala_menu-backdrop"></div>
				</div>
			</div>
				</div>
	    		<div class="Ct1" style="overflow: hidden; height:100%" >
	    			<iframe id="iframe1" name="iframe1" src="" frameBorder="0" scrolling="no" height="100%" width="100%"></iframe>
	    		</div>
	    	</form>
	   <!-- </div> -->
	   <script>
		var subLiLength=$('ul.sub-menu[myul="myul"]').length;
		for(var i=0;i<subLiLength;i++){
			$('ul.sub-menu[myul="myul"]').eq(i).prev().attr("onclick",$('ul.sub-menu[myul="myul"]').eq(i).find("a").eq(0).attr("onclick"));
		}
		</script>
	</body>
	
</html>
