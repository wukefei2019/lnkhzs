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
		function checkboxOnclick(){
	};
			$(document).ready(function() {
				//页面初始默认传参
				clickThan(1);
			});
		function getTablename() {
			var tablename = "";
		 	return tablename;
		}
		
		//点击查找
	function clickThan(mg) {
		var newStr = $('input[name="category_s"]').val();
		var time = $("input[name='ntime']").val();
    	
    	var url = encodeURI($ctx + "/quality/complaint/otherGetThan.action?time="+time+"&lable="+newStr+"&mg="+mg);
    	$.post(url).done(function(result) {
    	$("#showThan").text("当前标签含《其他》量: "+ result.oneCount  +"  标签总量: "+result.count+ "   占比:"+result.than);
			});
	}
	//重置
	function resetInp(){
		$("input[type='radio']:checked").removeAttr("checked");
		$('input[name="category_s"]').val('');
	}
	
	//给隐藏一级标签赋值
	function redata(msg){
		$('input[name="category_s"]').val(msg);
	}
	</script>
	
		<meta charset="UTF-8">
		<title>“其他”标签投诉量监控报表</title>
 </head>
<body>


<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 <dg:ajaxgrid id="table0" sqlname="SQL_COM_OTHER.query" inmap="${inmap }" autoLoad="false">
 	<div class="J-global-toolbar">
            <dg:condition>
                <div class="btn_box overflow">
	                    <div id="selectaa" class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14" onclick="clickThan();changeSelVal()">查找</div>
	                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetInp();resetSelVal()">重置</div>
	                    <div class="iconbtn floatRight10 floatRight iconfont icon-down3 fontsize14" onclick="exportSelVal()">导出</div>
                </div>
                <span>
                <input id="yewu" name="category_s" type="hidden"  />
                </span>
                <div>
              		 <p > 
               			<span style="font-size: inherit;font-family: fantasy;margin-left: 6%;"> 标签筛选  ：</span>
                		<input style="margin-left: 3.5%;" type="radio" name="category" value="家庭业务" onclick="redata('家庭业务')" /><span style="font-size: medium;">家庭业务</span> 
					      <input type="radio" name="category" value="移动业务" onclick="redata('移动业务')" /><span style="font-size: medium;">移动业务</span>
					    </p>  
					    <p style="margin-left: 30%;">  
					   	<input type="radio" name="category" value="增值业务" onclick="redata('增值业务')" /><span style="font-size: medium;">增值业务</span>    
					    <input type="radio" name="category" value="集团业务" onclick="redata('集团业务')" /><span style="font-size: medium;">集团业务</span>
					  </p>
                </div>
            </dg:condition>
				<dg:lefttoolbar>
	                <span id="showThan" style="color: cadetblue;font-size:18px;"></span>
	            </dg:lefttoolbar>
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
                        	<th data-field="NAME" data-sortable='true'>业务分类1-7级（含“其他”标签）</th>
							<th data-field="VALUE" data-sortable='true'>工单量</th>
							
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
