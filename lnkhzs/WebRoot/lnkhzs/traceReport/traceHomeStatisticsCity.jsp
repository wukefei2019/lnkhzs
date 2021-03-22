<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>家宽网络质量溯源问题统计表</title>
        <%
	        HashMap<String,String> inmap = new HashMap<String,String>();
	        Calendar now = Calendar.getInstance();
	        inmap.put("year",now.get(Calendar.YEAR)+"");
	        inmap.put("month",String.valueOf(now.get(Calendar.MONTH)+1).length()==2?String.valueOf(now.get(Calendar.MONTH)+1):"0"+String.valueOf(now.get(Calendar.MONTH)+1));
	        System.out.print(now.get(Calendar.YEAR));
	        request.setAttribute("inmap", inmap);
        %>
		<style>
				table {
					border-right: 0.5px solid rgb(221, 221, 221);
					border-top: 0.5px solid rgb(221, 221, 221);
					border-right: 0.5px solid rgb(221, 221, 221);
				}
				
				
				.bootstrap-table .fixed-table-header .table.table-no-bordered > thead > tr > th{
				    border-right: 2px solid rgb(221, 221, 221);
				} 
		</style> 
<!-- 		<script type='text/javascript'>
			$(function(){
				    var myDate = new Date();
				    var tYear = myDate.getFullYear();
				    $('input[name="year"]').val(tYear);
			})
		</script> -->
    </head>
    <body>
  <div class="Ct Dialog">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <dg:ajaxgrid id="table0" conditionIsOpen="true" sqlname="SQL_TRACE_HOME_STATISTICS_CITY.query" inmap="${inmap }">
            <dg:lefttoolbar>
                <button class="btn-link icon iconfont fontsize14 icon-refresh2 refresh">刷新</button>
                <button class="btn-link iconfont fontsize14 icon-sousuo1" onclick="showsearch('table0')">搜索</button>
            </dg:lefttoolbar>
            <dg:condition>
                <div class="btn_box overflow">
                    <div class="iconbtn floatRight10 floatRight icon iconfont icon-loading2 fontsize14 reset">重置</div>
                    <div class="iconbtn floatRight10 floatRight refresh iconfont icon-searchlist fontsize14">查找</div>
                </div>
            </dg:condition>
            <dg:ajax-table>
                <table id="table1" data-height='auto' data-width="auto" data-align="center" data-pagination="false" data-page-size='20' 
                 data-show-footer='false' class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th  rowspan="3" data-field="CITY" >家庭网络质量</th>
                            <th  colspan="7" >网络侧问题</th>
                            <th  colspan="7" >客户侧问题</th>
                            <th  colspan="3" >重复投诉</th>
                            <th  colspan="7" >网络质量及客户侧问题</th>
<!--                             <th  colspan="1" rowspan="3" data-field="农村网络新发起">农村网络新发起</th>
                            <th  colspan="1" rowspan="3" data-field="客户问题新发起">客户问题新发起</th>
                            <th  colspan="1" rowspan="3" data-field="城区网络新发起">城区网络新发起</th> -->
                        </tr>
                        <tr>
                            <th  colspan="3" >城市网络问题</th>
                            <th  colspan="3" >农村网络问题</th>
                            <th  colspan="1" rowspan="2" data-field="网络侧问题新发起合计" >新发起合计</th>
                            <th  colspan="3" >城市客户原因</th>
                            <th  colspan="3" >农村客户原因</th>
                            <th  colspan="1" rowspan="2" data-field="客户侧问题新发起合计" >新发起合计</th>
                            <th  colspan="1" rowspan="2" data-field="重复投诉未解决">未解决</th>
                            <th  colspan="1" rowspan="2" data-field="重复投诉新发起">新发起</th>
                            <th  colspan="1" rowspan="2" data-field="重复投诉待解决合计">待解决合计</th>
                            <th  colspan="3" >城区网络、客户问题</th>
                            <th  colspan="3" >农村网络、客户问题</th>
                            <th  colspan="1" rowspan="2" data-field="网及客问题新发起合计" >新发起合计</th>
                        </tr>
                        <tr>
                            <th data-field="城区网络问题新发起" >新发起</th>
                            <th data-field="城区网络问题已解决" >已解决</th>
                            <th data-field="城区网络问题未解决" >未解决</th>
                            <th data-field="农村网络问题新发起" >新发起</th>
                            <th data-field="农村网络问题已解决" >已解决</th>
                            <th data-field="农村网络问题未解决" >未解决</th>
                            <th data-field="城区客户原因新发起" >新发起</th>
                            <th data-field="城区客户原因已解决" >已解决</th>
                            <th data-field="城区客户原因未解决" >未解决</th>
                            <th data-field="城区客户原因新发起" >新发起</th>
                            <th data-field="城区客户原因已解决" >已解决</th>
                            <th data-field="城区客户原因未解决" >未解决</th>
                            <th data-field="城网及客问题新发起" >新发起</th>
                            <th data-field="城网及客问题已解决" >已解决</th>
                            <th data-field="城网及客问题未解决" >未解决</th>
                            <th data-field="农网及客问题新发起" >新发起</th>
                            <th data-field="农网及客问题已解决" >已解决</th>
                            <th data-field="农网及客问题未解决" >未解决</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
    </div>
    <iframe name="download" src="" style="display:none"></iframe>
    </body>
</html>


