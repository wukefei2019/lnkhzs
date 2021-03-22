<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>市场类溯源问题统计表</title>
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
				
				table td {
					border-left: 0.5px solid rgb(221, 221, 221);
					border-bottom: 0.5px solid rgb(221, 221, 221);
					border-right: 0.5px solid rgb(221, 221, 221);
				}
				
				table tbody td {
					height: 30px
				}
				
				.bootstrap-table .table.table-no-bordered > thead > tr > th{
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
		<script type="text/javascript">
			var table_load_success = function(){
				$('#table1').find('td:contains("合计")').parent().children('td').eq(7).text("-");
				$('#table1').find('td:contains("合计")').parent().children('td').eq(16).text("-");
				$('#table1').find('td:contains("合计")').parent().children('td').eq(25).text("-");
				$('#table1').find('td:contains("合计")').parent().children('td').eq(34).text("-");
				$('#table1').find('td:contains("合计")').parent().children('td').eq(43).text("-");
			}
		</script>
    </head>
    <body>
  <div class="Ct Dialog">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <dg:ajaxgrid id="table0" conditionIsOpen="true"  sqlname="SQL_TRACE_MARKETING_CITY.query" inmap="${inmap }">
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
                <table id="table1" data-height='auto' data-pagination="false" data-page-size='20' data-fixed-columns='true' data-fixed-number='1' 
                data-show-footer='false' data-on-load-success="table_load_success" class="table table-hover text_align_center table-no-bordered" data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th  colspan="1" rowspan="2" data-field="CITY" >影响范围</th>
                            <th  colspan="9" >营销活动</th>   
                            <th  colspan="9" >焦点问题</th>
                            <th  colspan="9" >集团派发问题</th>
                            <th  colspan="9" >基础服务</th>
                            <th  colspan="9" >服务触点</th>                     
                        </tr>
                        <tr>
                            <th data-field="营销活动总量" >截止查询月总量</th>
                            <th data-field="营销活动已解决" >已解决</th>
                            <th data-field="营销活动未解决" >未解决</th>
                            <th data-field="营销活动新发起" >新发起</th>
                            <th data-field="营销活动待解决合计" >待解决合计</th>
                            <th data-field="营销活动上月总量" >截止查询月前一月总量</th>
                            <th data-field="营销活动占比" >占比（%）</th>
                            <th data-field="营销活动解决率" >解决率（%）</th>
                            <th data-field="营销活动环比" >量的环比（%）</th>
                            
                            <th data-field="焦点问题总量" >截止查询月总量</th>
                            <th data-field="焦点问题已解决" >已解决</th>
                            <th data-field="焦点问题未解决" >未解决</th>
                            <th data-field="焦点问题新发起" >新发起</th>
                            <th data-field="焦点问题待解决合计" >待解决合计</th>
                            <th data-field="焦点问题上月总量" >截止查询月前一月总量</th>
                            <th data-field="焦点问题占比" >占比（%）</th>
                            <th data-field="焦点问题解决率" >解决率（%）</th>
                            <th data-field="焦点问题环比" >量的环比（%）</th>
                            
                            <th data-field="集团派发总量" >截止查询月总量</th>
                            <th data-field="集团派发已解决" >已解决</th>
                            <th data-field="集团派发未解决" >未解决</th>
                            <th data-field="集团派发新发起" >新发起</th>
                            <th data-field="集团派发待解决合计" >待解决合计</th>
                            <th data-field="集团派发上月总量" >截止查询月前一月总量</th>
                            <th data-field="集团派发占比" >占比（%）</th>
                            <th data-field="集团派发解决率" >解决率（%）</th>
                            <th data-field="集团派发环比" >量的环比（%）</th>
                            
                            <th data-field="基础服务总量" >截止查询月总量</th>
                            <th data-field="基础服务已解决" >已解决</th>
                            <th data-field="基础服务未解决" >未解决</th>
                            <th data-field="基础服务新发起" >新发起</th>
                            <th data-field="基础服务待解决合计" >待解决合计</th>
                            <th data-field="基础服务上月总量" >截止查询月前一月总量</th>
                            <th data-field="基础服务占比" >占比（%）</th>
                            <th data-field="基础服务解决率" >解决率（%）</th>
                            <th data-field="基础服务环比" >量的环比（%）</th>
                            
                            <th data-field="服务触点总量" >截止查询月总量</th>
                            <th data-field="服务触点已解决" >已解决</th>
                            <th data-field="服务触点未解决" >未解决</th>
                            <th data-field="服务触点新发起" >新发起</th>
                            <th data-field="服务触点待解决合计" >待解决合计</th>
                            <th data-field="服务触点上月总量" >截止查询月前一月总量</th>
                            <th data-field="服务触点占比" >占比（%）</th>
                            <th data-field="服务触点解决率" >解决率（%）</th>
                            <th data-field="服务触点环比" >量的环比（%）</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
    </div>
    <iframe name="download" src="" style="display:none"></iframe>
    </body>
</html>


