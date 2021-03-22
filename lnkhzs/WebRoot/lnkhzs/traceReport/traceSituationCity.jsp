<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/core/taglib_omcs.jsp"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>溯源问题情况表-地市</title>
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
 <!--		<script type='text/javascript'>
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
			}
		</script>
    </head>
    <body>
  <div class="Ct Dialog">
        <eoms:menuway id="${navid }"></eoms:menuway>
        <dg:ajaxgrid id="table0" conditionIsOpen="true" sqlname="SQL_TRACE_SITUATION_CITY.query" inmap="${inmap }">
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
                <table id="table1" data-height='auto' data-pagination="false" data-page-size='20'  data-fixed-columns='true' data-fixed-number='1'  
                data-show-footer='true' class="table table-hover text_align_center table-no-bordered" data-on-load-success="table_load_success"
                    data-show-columns='false'>
                    <thead>
                        <tr>
                        	<th  colspan="1" rowspan="2" data-field="CITY" >影响范围</th>
                            <th  colspan="9" >移动网络质量</th>
                            <th  colspan="9" >家宽网络质量</th>
                            <th  colspan="9" >市场类</th>
                        </tr>
                        <tr>
                            <th data-field="移动总量" >截止查询月总量</th>
                            <th data-field="移动已解决" >已解决</th>
                            <th data-field="移动未解决" >未解决</th>
                            <th data-field="移动新发起" >新发起</th>
                            <th data-field="移动待解决合计" >待解决合计</th>
                            <th data-field="移动上月总量" >截止查询月前一月总量</th>
                            <th data-field="移动占比" >占比（%）</th>
                            <th data-field="移动解决率" >解决率（%）</th>
                            <th data-field="移动环比" >量的环比（%）</th>
                            <th data-field="家宽总量" >截止查询月总量</th>
                            <th data-field="家宽已解决" >已解决</th>
                            <th data-field="家宽未解决" >未解决</th>
                            <th data-field="家宽新发起" >新发起</th>
                            <th data-field="家宽待解决合计" >待解决合计</th>
                            <th data-field="家宽上月总量" >截止查询月前一月总量</th>
                            <th data-field="家宽占比" >占比（%）</th>
                            <th data-field="家宽解决率" >解决率（%）</th>
                            <th data-field="家宽环比" >量的环比（%）</th>                            
                            <th data-field="市场类总量" >截止查询月总量</th>
                            <th data-field="市场类已解决" >已解决</th>
                            <th data-field="市场类未解决" >未解决</th>
                            <th data-field="市场类新发起" >新发起</th>
                            <th data-field="市场类待解决合计" >待解决合计</th>
                            <th data-field="市场类上月总量" >截止查询月前一月总量</th>
                            <th data-field="市场类占比" >占比（%）</th>
                            <th data-field="市场类解决率" >解决率（%）</th>
                            <th data-field="市场类环比" >量的环比（%）</th>
                        </tr>
                    </thead>
                </table>
            </dg:ajax-table>
        </dg:ajaxgrid>
    </div>
    <iframe name="download" src="" style="display:none"></iframe>
    </body>
</html>


