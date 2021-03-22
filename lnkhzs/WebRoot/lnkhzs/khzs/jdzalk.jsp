
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>客户之声信息</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/khzs/jdzalk.js"></script>

 </head>
<body>
<div class="Ct Dialog" >
<eoms:menuway id="${navid }"></eoms:menuway>
		<div style="position:absolute;width: 100%;">
			<div style="width: 69%;float:left;">
		 		<dg:ajaxgrid id="table0" sqlname="SQL_KHZS_JDZALK.query">
		            <dg:lefttoolbar>
		                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
		                <button class="btn-link icon iconfont fontsize14 icon-download" onclick="exportSelVal()">导出</button>
		                <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
		            </dg:lefttoolbar>
		            <dg:condition>
		                <input type="hidden" name="NEXTUSER" value="${userid}"/>
		                <input type="hidden" name="FLOWSTATUS" value="部门处理,部门处理（挂起）,专员处理,专员处理（挂起 ）,部门复核"/>
		                <div class="btn_box overflow">
		                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14">重置</div>
		                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
		                </div>
		            </dg:condition>
		            <dg:ajax-table>
		                <table data-height='auto' data-fixed-columns='false' data-fixed-number='3' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
		                    <thead>
		                        <tr>
		                        	<th data-field="PID" data-visible="false" >PID</th>
		                            <th data-field="TYPE" data-visible="false" >案例类型</th>
		                            <th data-field="IS_SKETCH" data-formatter="is_sketch" data-events="fn_evnt_name_look">钻石</th>
		                            <th data-field="IDX" data-formatter="$.bs.table.fmt.link" data-events="fn_evnt_name_look" data-sortable='true'>序号</th>
		                            <th data-field="CREATETIME" data-sortable='true'>提交时间</th>
		                            <th data-field="THEME" data-sortable='true'>主题</th>
		                        	<th data-field="DEPTNAME" data-sortable='true'>责任单位</th>
		                        	<!-- <th data-field="REMARK2">整改方案或处理意见</th>    -->                	
		                            <th data-field="FULLNAME" data-sortable='true'>反馈人</th>                            
		                        	<th data-field="FEEDTIME" data-sortable='true'>反馈时间</th>
		                        	<!-- <th data-field="FEEDAPPRAISE" >提交人评价反馈</th>                            
		                        	<th data-field="REMARK">备注</th>  -->
		                        </tr>
		                    </thead>
		                </table>
		            </dg:ajax-table>
		        </dg:ajaxgrid>
			</div>
				<div style="width: 28%; height: 100%;float:left;padding-top: 38px;">
				    <ul id="myTab" class="nav nav-tabs">
			            <li class="active"><a href="#tab0" data-toggle="tab">排行榜（人员）</a></li>
			            <li><a href="#tab1" data-toggle="tab">排行榜（部门）</a></li>
				    </ul>
				    <div class="tab-content" style="height: 479px;">
					    <div id="tab0" class="tab-pane in active">
						       <table style="width:100%" class="table table-hover text_align_center table-no-bordered table-striped">
						           <thead>
						               <tr>
						               	<th>名次</th>
						               	<th>人员</th>
						                <th>数量</th>
						               </tr>
						           </thead>
						           <tbody id="ranking1">
						           </tbody>
						       </table>
					    </div>
					    <div id="tab1" class="tab-pane">
						       <table style="width:100%" class="table table-hover text_align_center table-no-bordered table-striped">
						           <thead>
						               <tr>
						               	<th>名次</th>
						               	<th>部门</th>
						                <th>数量</th>
						               </tr>
						           </thead>
						           <tbody id="ranking2">
						           </tbody>
						       </table>
					    </div>
				    </div>
				</div>
	            <iframe name="download" src="" style="display:none"></iframe>
		</div>
    </div>
</body>
</html>
