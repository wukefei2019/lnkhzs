
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/indexStyle.css" rel="stylesheet" type="text/css" />
<script src="map.js"></script> 	
<script src="${ctx }/common/plugin/echart/echarts.min.js"></script>
<script src="liaoning.js"></script>
<script src="index.js"></script>
<script src="map.js"></script> 	
<html>
	<head>
	<meta charset="UTF-8">
	
	<style type="text/css">
	
	
	
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			initCharts();
			
			var cityMapChart = echarts.init(document.getElementById("city_map_echart"));
			deal_city_map('',cityMapChart);
			
			
			
		});
		
		window.onresize = function(){
			resetSize();
			resetChartsSize();
		}
	</script>
 </head>
	<body style="background-color:#f7f8fa;margin: 10px">
		<div style ="width: 100%; height: 30px;">
			<div id="allnotice" style="width: 100%; height: 100%;background-color: #fff;position:relative;overflow: hidden;">
				<div class="notice">
					<div class="noticePic"></div>
					<div class="noticeFont"> 活动&nbsp;&&nbsp;公告 </div>
					<!-- <div class="noticeStem"></div> -->
				</div>
				<div id="noticeInfo" class="noticeInfo">
				<marquee><div class="noticeInfoPic"><font class="noticeFont fonta" color=red><a href="${ctx }/lnkhzs/khzs/ygjy.jsp">欢迎使用客户之声</a></font></div></marquee>
					<!-- <div id="noticeInfoPage">

						<div class="noticeInfoPic aaa" id="c">
							<div class="noticeFont fonta">欢迎使用客户之声</div>
						</div>

						<div class="aaa" id="f">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="g">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="h">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="i">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="j">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="k">
							<div class="noticeFont fonta"></div>
						</div>
						<div class="aaa" id="l">
							<div class="noticeFont fonta"></div>
						</div>
					</div> -->
				</div>
				<div class="noticeStem"></div>
			</div>
		</div>
		<div style ="position: fixed;width: 100%; height: calc(100% - 40px);padding-top: 10px">
	
			<div style="width: 100%; height: 100%;">
				<div style="width: 27%; height: 100%; float: left;">
					<div style="width: 100%; height: 50%;">
						<div style="width: 100%; height: 50%;">
						    <div class="modelDiv">
								<div id="sywtzl" class="titleDiv titleDivPic">
									<!-- 溯源问题总量 -->
									溯源问题占比
								</div>
								<div class="contentDiv">
									<!-- <div id="chartSy" style="width: 100%; height: 100%">
										
									</div> -->
									<div id="chartA" class="chartA"  style="width: 100%; height: 100%">
										</div>
								</div>
							</div>
						</div>
						<div style="width: 100%; height: 50%; padding-top: 10px;">
							<div style="width: 100%; height: 100%;background-color: #fff;">
							    <div class="modelDiv">
									<div id="khzs" class="titleDiv titleDivPic">
										客户之声	
									</div>
									<div class="contentDiv">
										<div class="chartKh">
											<div class="custFont">案例数</div>
											<div id="khal" class="khal">0</div>
										</div>
										<div class="chartKh" style="margin: 2% 0 2% 0">
											<div class="custFont">建议数</div>
											<div id="khjy" class="khjy">0</div>
										</div>
										<div class="chartKh">
											<div class="custFont">金点子</div>
											<div id="khjdz" class="khjdz">0</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div style="width: 100%; height: calc(50% - 10px); padding-top: 10px;">
						<div style="width: 100%; height: 100%;background-color: #fff;"><!-- padding-bottom: 10px -->
						    <div class="modelDiv">
								<div id="fwzlbzbb" class="titleDiv titleDivPic">
									服务质量标准报表	
								</div>
								<!-- <div class="contentDiv" style="margin: 10px 0 0 10px;"> -->
								<div class="contentDiv" style="margin-left: 10px;">
									<div class="service">
										<div class="formA" style="margin-top: 4px;"></div>
										<div class="fContentA" style="margin-left: -10%;line-height: 45px;text-align: left;"></div>
									</div>
									<div class="service">
										<div class="formB" style="margin-top: 4px;"></div>
										<div class="fContentB" style="margin-left: -10%;line-height: 45px;text-align: left;"></div>
									</div>
									<div class="service">
										<div class="formC" style="margin-top: 4px;"></div>
										<div class="fContentC" style="margin-left: -10%;line-height: 45px;text-align: left;"></div>
									</div>
									<div class="service">
										<div class="formD" style="margin-top: 4px;"></div>
										<div class="fContentD" style="margin-left: -10%;line-height: 45px;text-align: left;"></div>
									</div>
									<div class="service">
										<div class="formE" style="margin-top: 4px;"></div>
										<div class="fContentE" style="margin-left: -10%;line-height: 45px;text-align: left;"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
	
				<div style="width: 46%; height: 100%; float: left;padding-left:10px;padding-right: 10px;">
					<div style="width: 100%; height: 75%;">
						    <div class="modelDiv">
								<div id="qstsl" class="titleDiv titleDivPic" style="background-size: 4.5%;">
									全省投诉量
								</div>
								<div class="contentDiv">
									<div id="city_map_echart"
											style="width: 100%; height: 100%"></div>
								</div>
							</div>
					</div>
					<div style="width: 100%; height: calc(25% - 10px); padding-top: 10px;">
						    <div class="modelDiv">
								<div id="zdzbqk" class="titleDiv titleDivPic" style="background-size: 4.2%; padding-left: 2%;">
									重点指标情况	
								</div>
								<div class="contentDiv">
									<div id="zdCharts" style="height: 100%; width: 100%;">
									</div>
								</div>
							</div>
					</div>
				</div>
	
	
				<div style="width: calc(27% - 20px); height: 100%; float: left">
					<div style="width: 100%; height: 50%;">
						    <div class="modelDiv">
								<div id="bqdb" class="titleDiv titleDivPic">
									标签&督办
								</div>
								<div class="contentDiv">
									<!-- <div id="tabLable" style="width: 100%; height: 100%"> -->
									<div id="tabLable" style="width: 100%; height: 100%">
									    <!-- ********************************************** -->
									    <div style="width: 100%; height: 100%;float:left;padding-top: 2px;">
										    <ul id="myTab" class="nav nav-tabs" style="background: #b3d8ee;">
									            <li class="active" style="width: 50%;height:100%;"><a href="#tab0" style="height:100%;font-size:12px;text-align: center;" data-toggle="tab">标签投诉TOP5</a></li>
									            <li style="width: 50%;height:100%;"><a href="#tab1" style="height:100%;font-size:12px;text-align: center;" data-toggle="tab">问题督办TOP5</a></li>
										    </ul>
										    <div class="tab-content" style="height: 90%;">
											
											    <div id="tab0" class="tab-pane in active" style="height: calc(100% - 25px);border: 1px solid #00000021;">
												       <div class="row" style="margin-left: -3px;margin-top: 2.2%;margin-bottom: 1%;width: 100%;">
												       <div class="col-md-3"><button class="btn btn-primary btn-xs" onclick="initBQDB('移动业务投诉量')">移动</button></div>
												       <div class="col-md-3"><button class="btn btn-primary btn-xs" onclick="initBQDB('家庭业务投诉量')">家庭</button></div>
												       <div class="col-md-3"><button class="btn btn-primary btn-xs" onclick="initBQDB('增值业务投诉量')">增值</button></div>
												       <div class="col-md-3"><button class="btn btn-primary btn-xs" onclick="initBQDB('集团业务投诉量')">集团</button></div>
												           
												</div>
												       <div id="chartB" class="chartB" style="width: 100%;height: calc(100% - 16px);">
														</div>
											    </div>
											    <div id="tab1" class="tab-pane" style="height: 100%">
												       <!-- <table style="width:100%" class="table table-hover text_align_center table-no-bordered table-striped">
												           <tbody id="ranking2">
												           </tbody>
												       </table> -->
											<div style="height: calc(100% - 20px);">
												<ul class="list-group" id="ranking2" style="height: calc(100% + 1px);margin-bottom: 0px;">
													<!-- <li class="list-group-item"><span class="badge">14</span>
														Cras justo odio</li> -->
												</ul>
											</div>
										</div>
										    </div>
										</div>
				<!-- ********************************************************************* -->
									</div>
								</div>
							</div>
					</div>
					<div style="width: 100%; height: calc(50% - 10px); padding-top: 10px">
						    <div class="modelDiv">
								<div id="zhdyhfl" class="titleDiv titleDivPic">
									智慧调研回复量	
								</div>
								<div class="contentDiv">
									<div id="chartZhdy" style="width: 100%; height: 100%"></div>
								</div>
							</div>
					</div>
				</div>
			</div>
	
		</div>
	</body>
</html>
