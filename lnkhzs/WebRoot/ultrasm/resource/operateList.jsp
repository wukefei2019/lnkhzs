<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head> 
  	<base target="_self"/>
	<%@ include file="/common/core/taglibs.jsp"%>  
	<script language="javascript">
		window.onresize = function() 
		{
		  setCenter(0, LAYOUT_LIST_RIGHT);
		}
		
		window.onload = function() 
		{
		  setCenter(0, LAYOUT_LIST_RIGHT);changeRow_color("tab");
		}
		function delOperate()
		{
			getCheckValue("checkid");
			var ids = document.getElementsByName('var_selectvalues').value;
			document.forms[0].var_selectvalues.value = ids;
			if(ids=='')
			{
				alert('<eoms:lable name="sm_msg_chooseOpObject" />！');
				return;
			}
			if(confirm("<eoms:lable name="com_btn_confirm_del" />"))
			{
				document.forms[0].action = '${ctx}/operateManager/deleteOperate.action';
				document.forms[0].submit();
			}
		}
	</script>
  </head>
  
  <body>
	  	<dg:datagrid  var="operate" sqlname="SQL_SM_OperateList.query" title='${nodePath}' cachemode="sql">
	  		<dg:lefttoolbar>
		      <eoms:operate cssclass="page_search_button" id="com_btn_search" onmouseover="this.className='page_search_button_hover'" 
	  	  			onmouseout="this.className='page_search_button'"  onclick="showsearch()" text='com_btn_search' />
  		  	  <eoms:operate cssclass="page_refresh_button" id="com_btn_refresh" onmouseover="this.className='page_refresh_button_hover'" 
  		  			onmouseout="this.className='page_refresh_button'" onclick="location.reload();" text="com_btn_refresh"/>
		      <eoms:operate cssclass="page_add_button" id="com_btn_add" onmouseover="this.className='page_add_button_hover'" 
  		  			onmouseout="this.className='page_add_button'" 
  		  			onclick="openwindow('${ctx}/operateManager/loadOp.action','',800,500)"
  		  			text="com_btn_add" operate="com_add"/>
  		  	 <eoms:operate onmouseout="this.className='page_del_button'" cssclass="page_del_button"
				    text="com_btn_delete" onmouseover="this.className='page_del_button_hover'"        
				    id="com_btn_del" onclick="delOperate();" operate="com_delete"/>
     		<eoms:operate cssclass="page_import_button" id="com_btn_import" onmouseover="this.className='page_import_button_hover'" 
     			onmouseout="this.className='page_import_button'" text="com_btn_import"  onclick="importExcel('${ctx}','IMP_SM_Operate.xml');" operate="com_imp"/>
      		<eoms:operate cssclass="page_output_button" id="com_btn_exp" onmouseover="this.className='page_output_button_hover'" 
      			onmouseout="this.className='page_output_button'" text="com_btn_export" onclick="exportExcel('${ctx}','SQL_SM_OperateList.query','EXP_SM_Operate');" operate="com_exp"/>
	  		</dg:lefttoolbar>
	  		<dg:condition>
		  		<div align="center">
			      <table  class="serachdivTable">
			        <tr>
			          <td colspan="6" align="center">
			          	<input type="submit" name="searchUser" id="searchUser" value="<eoms:lable name="com_btn_search"/>" class="searchButton" onmouseover="this.className='searchButton_hover'" onmouseout="this.className='searchButton'"/>
			        	<input type="reset" name="resetCondition" id="resetCondition" value="<eoms:lable name="com_btn_reset"/>" class="ResetButton" onmouseover="this.className='ResetButton_hover'" onmouseout="this.className='ResetButton'"/>
			          </td>
			        </tr>
			      </table>
			    </div>
	  		</dg:condition>
	    	<dg:gridtitle>
		    	<tr>
		    		<th width="25"><input id="checkidAll" name="checkidAll" type="checkbox" onclick="checkAll('checkid')"/></th>
		    		<th width="25%"><eoms:lable name="sm_lb_operateId"/></th>
		    		<th width="25%"><eoms:lable name="sm_lb_operateName"/></th>
		    		<th width="30%"><eoms:lable name="sm_lb_operateType"/></th>
		    		<th><eoms:lable name="com_lb_status"/></th>
		    	</tr>
	    	</dg:gridtitle> 
			<dg:gridrow>
				<tr class="${operate_row}">
					<td><input name="checkid" type="checkbox" value='${pid}'></input></td>
			        <td><a href="javascript:;" onclick="openwindow('${ctx}/operateManager/loadOp.action?opid=${pid}','',800,500)">${pid}</a></td>
			        <td><a href="javascript:;" onclick="openwindow('${ctx}/operateManager/loadOp.action?opid=${pid}','',800,500)">${opname}</a></td>
			   		<td><a href="javascript:;" onclick="openwindow('${ctx}/operateManager/loadOp.action?opid=${pid}','',800,500)">
			   		 	<eoms:dic value="${optype}" dictype="operatetype"/>
			   		 </a></td>
			   		<td><a href="javascript:;" onclick="openwindow('${ctx}/operateManager/loadOp.action?opid=${pid}','',800,500)"><eoms:dic dictype="status" value="${status}"/></a></td>
			    </tr>
			</dg:gridrow>
		</dg:datagrid>
  </body>
</html>
