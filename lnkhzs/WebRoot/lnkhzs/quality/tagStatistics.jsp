<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/core/taglib_omcs.jsp"%>


<html>
	<head>
		<meta charset="UTF-8">
		<title>标签统计</title>
        <script type="text/javascript" >
        $(document).ready(function(){
        	$(".compare").on("click", function(event) {
        		openwindow($ctx + '/lnkhzs/quality/import.jsp');
        	});

        });

	        
	        fn_detail = {
        		'click a': function (e, value, row, index) {
        			var city = $("[name='citys']").val();
        			openwindow($ctx + '/lnkhzs/quality/tagStatisticsDetail.jsp?a12='+row.A12+"&city="+city,'');
        	   }
	        }
        </script>
 </head>
<body>
<div class="Ct Dialog">
<eoms:menuway id="${navid }"></eoms:menuway>
 	<dg:ajaxgrid id="table0" sqlname="SQL_TAG_STATISTICS.query" >
           	<dg:lefttoolbar>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh" onclick="$.bs.table.refresh('table0')">刷新</button>
                <button class="btn-link iconfont fontsize14 icon-sousuo1 compare" >标签比较</button> 
                <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
            </dg:lefttoolbar> 
            <dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14"onclick="changeSelVal()">查找</div>
                    <div class="iconbtn floatRight10 floatRight reset iconfont icon-loading2 fontsize14" onclick="resetSelVal()">重置</div>
                </div>
            </dg:condition>
            <dg:ajax-table>

                <table id="table1" data-height='auto' data-fixed-columns='false' data-fixed-number='3' 
                	 class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
          
                            <!-- <th data-field="A12" data-formatter="$.bs.table.fmt.link" data-events=fn_detail >问题分类</th> -->
                            <th data-field="A12" data-sortable='true'>问题分类</th>
                            <th data-field="CNT" data-sortable='true'>数量</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
            <iframe name="download" src="" style="display:none"></iframe>
    </div>
	</body>
</html>
