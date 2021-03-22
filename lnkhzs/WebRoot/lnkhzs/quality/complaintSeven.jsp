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
<%-- <script type="text/javascript" src="${ctx }/lnkhzs/quality/zCommonComplan.js"></script> --%>
<script type="text/javascript" src="${ctx }/lnkhzs/quality/zCommon.js"></script>
<html>
	<head>
	
	<script type='text/javascript'>
			$(document).ready(function() {
			});
			
		function getTablename() {
			var tablename = "";
		 	return tablename;
		}
		
		//点击查找
	function clickThan() {
		var obj = $("input[name='category']");
		var check_val = [];
		for (k in obj) {
			if (obj[k].checked)
			 check_val.push(obj.eq(k).next().text());
			}
		$('input[name="category_s"]').val(check_val);
	}
	
	function checkboxOnclick(){
	};
	</script>
	
		<meta charset="UTF-8">
		<title>1-7级节点(含“全局流转”)</title>
 </head>
<body>


<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_COM_SEVEN.query" inmap="${inmap }" autoLoad="false">
 	<div class="J-global-toolbar">
            <dg:condition>
                <div class="btn_box overflow">
	                    <div id="selectaa" class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14" onclick="clickThan();changeSelVal()">查找</div>
	                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
	                    <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div>
                </div>
                <span>
                <input id="yewu" name="category_s" type="hidden"  />
                </span>
                <div>
              		 <p > 
               			<span style="font-size: inherit;font-family: fantasy;margin-left: 6%;"> 标签筛选  ：</span>
                		<input style="margin-left: 3.5%;" type="checkbox" name="category" value="家庭业务" /><span style="font-size: medium;">家庭业务</span> 
					      <input type="checkbox" name="category" value="移动业务" /><span style="font-size: medium;">移动业务</span>
					    </p>  
					    <p style="margin-left: 30%;">  
					   	<input type="checkbox" name="category" value="增值业务" /><span style="font-size: medium;">增值业务</span>    
					    <input type="checkbox" name="category" value="集团业务" /><span style="font-size: medium;">集团业务</span>
					  </p>
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
                <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' data-on-load-success="table_load_success"
	                class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
	                    <thead>
                        <tr>
                        	<th data-field="NAME3" data-sortable='true'>1-7级节点(含“全局流转”)</th>
							<th data-field="VAL1" data-sortable='true'>工单量</th>
							<th data-field="THAN" data-sortable='true'>占比(%)</th>
							
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
