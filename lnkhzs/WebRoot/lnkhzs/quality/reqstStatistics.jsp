<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link href="${ctx}/lnkhzs/style/css/toolbar.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/omcsStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.core.min.js"></script>
<link rel="stylesheet" href="${ctx }/common/plugin/ztree/css/omcsStyle/ztree.custom.css" type="text/css"></link>
<script type="text/javascript" src="${ctx }/common/plugin/ztree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/zCommon.js"></script>
<html>
	<head>
		<meta charset="UTF-8">
		<title>服务请求分类统计表</title>
        <script type="text/javascript" src="${ctx }/lnkhzs/quality/reqstStatistics.js"></script>
 </head>
<body>
<div class="Ct Dialog">
<input id="startTime" style="display: none;"/>
<input id="endTime" style="display: none;"/>
<input sqltablename="NGCS_WF_CMPLNTS_REQST_DETAIL" id="tablename" style="display: none;"/>
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_QUALITY_STATISTICS.query" inmap="${inmap }" autoLoad="false">
 <div  class="J-global-toolbar">
            <dg:condition>
                <div class="btn_box overflow">
                    <div id="selectaa" class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14" onclick="changeSelVal()">查找</div>
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
                    <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
            	<div class="t-wrap">
			 		<div class="t-toolbar">
			 			<div class="t-panels">
			 				<div class="t-panel tbar-panel-cart">
				 				<div id="replacepl">
				 				</div>
				 				<ul id="tree1" class="ztree" style="width:100%; height:inherit;margin-top: 0px;border: inherit"></ul>
			                </div>
			            </div>
			            <div class="t-tabs">
							<div class="icon-open t-tab-cart ">
								<i class="icon iconfont fontsize14"></i>
							</div>
						</div>
			        </div>
			    </div>
                <table data-show-columns="true" id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
	                class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th data-field="A02" data-sortable='true'>服务请求1级节点类型</th>
                        	<th data-field="A03" data-sortable='true'>服务请求2级节点类型</th>
                            <th data-field="A04" data-sortable='true'>服务请求3级节点类型</th>
                            <th data-field="A05" data-sortable='true'>服务请求4级节点类型</th>
                            <th data-field="A06" data-sortable='true'>服务请求5级节点类型</th>
                            <th data-field="A07" data-sortable='true'>服务请求6级节点类型</th>
                            <th data-field="A08" data-sortable='true'>服务请求7级节点类型</th>
                            <th data-field="TOTAL" data-formatter="$.bs.table.fmt.link" data-sortable='true'>全省合计</th>
                            <th data-field="沈阳市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>沈阳市</th>
                            <th data-field="大连市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>大连市</th>
                            <th data-field="鞍山市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>鞍山市</th>
                            <th data-field="抚顺市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>抚顺市</th>
                            <th data-field="本溪市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>本溪市</th>
                            <th data-field="丹东市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>丹东市</th>
                            <th data-field="锦州市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>锦州市</th>
                            <th data-field="营口市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>营口市</th>
                            <th data-field="阜新市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>阜新市</th>
                            <th data-field="辽阳市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>辽阳市</th>
                            <th data-field="铁岭市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>铁岭市</th>
                            <th data-field="朝阳市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>朝阳市</th>
                            <th data-field="盘锦市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>盘锦市</th>
                            <th data-field="葫芦岛市" data-formatter="$.bs.table.fmt.link" data-sortable='true'>葫芦岛市</th>
                            <th data-field="异地" data-formatter="$.bs.table.fmt.link" data-sortable='true' >异地</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
