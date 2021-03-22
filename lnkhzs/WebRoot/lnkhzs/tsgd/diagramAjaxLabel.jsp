<%@page import="bsh.This"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<link rel="stylesheet" href="${ctx }/bootstrap-3.3.7-dist/css/bootstrap.min.css">

<head>
<meta charset="UTF-8"></meta>
<title></title>
<style type="text/css">
	table.gridtable {
	    font-size:12px;
	    border-collapse:separate; 
	    border-spacing:0px 4px;
	}
	table.gridtable th {
	    height:30px
	}
	tbody td {
		font-size:14px;
		text-align:center;
	}
	thead td {
		 font-size:14px;
		 text-align:center;
	}
    .pager-container {
        display: inline-block;
    }
    .pager-container > li {
        float: left;
    }
    .num-pager-select {
        height: 34px;
        border: 1px solid #ddd;
    }
    .fixed-table-container {
	    position: relative;
	    clear: both;
	    /* border: 1px solid #dddddd; */
	    border-radius: 4px;
	    -webkit-border-radius: 4px;
	    -moz-border-radius: 4px;
	}
	.bootstrap-table table thead tr th{
        text-align: center;
 	}
</style>
<script type="text/javascript">

	function getUrlParam(){
		var paramStr ="${param}";
		var params = paramStr.substring(1,paramStr.length-1);
	    var param = params.split(",");
	    var url = "";
	    for(var k=0;k<param.length;k++){
			url += param[k].trim()+"&";
		}
	    return url;
	}

	$(document).ready(function(){
	
	});
	
</script>
</head>
<body style="width: 100%; height: 100%;background-size:100% 100%;">
	<div class="Ct Dialog">
		<div class="" style="width: 100%; height: 100%;">
			<dg:ajaxgrid id="table0" sqlname="${sqlName}">
		        <dg:lefttoolbar>
<!-- 		            <button class="btn-link search iconfont icon-sousuo1 fontsize14" onclick="showsearch('table0')" >搜索</button>
		            <button class="btn-link refresh iconfont icon-refresh2 fontsize14" onclick="$.bs.table.refresh('table0')" >刷新</button> -->
		        </dg:lefttoolbar>
		        <dg:condition>
		            <div class="btn_box overflow">
		                <div class="iconbtn floatRight10 floatRight reset"><i class="icon iconfont icon-loading2 fontsize14"></i>重置</div>
		                <div class="iconbtn floatRight10 floatRight refresh" onclick="$.bs.table.reload('table0');">
		                <i class="icon iconfont icon-searchlist fontsize14"></i>查找</div>
		            </div>
		        </dg:condition>
		        <dg:ajax-table>
		            <!-- data-height = auto 的时候表格高度将撑满页面,分页将不受到pageSize/pageList控制；否则 还需要指定pageSize/pageList的值 -->
		            <table id="btable" data-height='auto' data-page-size='10' data-page-list='[10, 20, 50,100]' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
		                <thead>
		                	${dataField}
		                </thead>
		            </table>
		        </dg:ajax-table>
		    </dg:ajaxgrid>
		    <iframe name="download" src="" style="display:none"></iframe>
		</div>
	</div>
</body>
</html>