<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>visitsQuery</title>
		<%@ include file="/common/core/taglibs.jsp"%>
		<script>
		window.onresize = function() 
		{
		  setCenter(0, LAYOUT_LIST_RIGHT);
		}
		window.onload = function() 
		{
		  setCenter(0, LAYOUT_LIST_RIGHT);
		  changeRow_color("tab");
		}
		
		//删除
		function delConfig(){
			var pid = document.getElementsByName('checkid');
			var pids = '';
			for(var i=0;i<pid.length;i++){
				if(pid[i].checked){
					pids += pid[i].value + ',' ;
				}
			}
			if(pids==''){
				alert('<eoms:lable name="sm_msg_chooseOpObject"/>'+'!');
			}else{
				if(confirm("<eoms:lable name='com_btn_confirm_del'/>"))
				{
					var temp = pids.lastIndexOf(',');
					pids = pids.substring(0,temp);
					window.location.href('${ctx}/interceptConfig/delInterceptConfig.action?pids='+pids+'&pid=${id}');
				}
			}	
		}
	</script>
	</head>
	<body>
		<dg:datagrid var="interceptConfig" sqlname="SQL_SM_listInterceptConfig.query" title="${nodePath }">
			<dg:lefttoolbar>
				<eoms:operate cssclass="page_search_button" id="com_btn_search"
					onmouseover="this.className='page_search_button_hover'"
					onmouseout="this.className='page_search_button'" onclick="showsearch()"
					text='com_btn_search' />
				<eoms:operate cssclass="page_refresh_button" id="com_btn_refresh"
					onmouseover="this.className='page_refresh_button_hover'"
					onmouseout="this.className='page_refresh_button'"
					onclick="location.reload();" text="com_btn_refresh" />
				<eoms:operate cssclass="page_add_button" id="com_btn_add" onmouseover="this.className='page_add_button_hover'" 
 		  			onmouseout="this.className='page_add_button'" 
 		  			onclick="openwindow('${ctx}/interceptConfig/addInterceptConfig.action','',800,500);"
 		  			text="com_btn_add" operate="com_add"/>
 		  		<eoms:operate cssclass="page_del_button" id="com_btn_delete" onmouseover="this.className='page_del_button_hover'" 
 		 			onmouseout="this.className='page_del_button'" onclick="delConfig();"  text="com_btn_delete" operate="com_delete"/>
			</dg:lefttoolbar>
			<dg:condition>
				<div align="center">
					<table class="serachdivTable" style="width: 80%">
						<tr>
							<td colspan="6" align="center">
								<input type="submit" name="searchUser" id="searchUser"
									value="<eoms:lable name="com_btn_search"/>"
									class="searchButton"
									onmouseover="this.className='searchButton_hover'"
									onmouseout="this.className='searchButton'" />
								<input type="reset" name="resetCondition" id="resetCondition"
									value="<eoms:lable name="com_btn_reset"/>" class="ResetButton"
									onmouseover="this.className='ResetButton_hover'"
									onmouseout="this.className='ResetButton'" />  
							</td>
						</tr>
					</table>
				</div>
			</dg:condition>
			<dg:gridtitle>
				<tr>
					<th width="30"><input type="checkbox" onclick="checkAll('checkid')"></input></th>
					<th width="20%">功能名称</th>
					<th width="10%">参数1</th>
					<th width="10%">参数2</th>
					<th width="10%">参数3</th>
					<th>URL地址</th>
				</tr>
			</dg:gridtitle>
			<dg:gridrow>
				<tr class="${interceptConfig_row}">
					<td><input name="checkid" type="checkbox" value='${pid}'></input></td>
					<td onclick="openwindow('${ctx}/interceptConfig/updInterceptConfig.action?pid=${pid}','',800,500);">${functionname}</td>
					<td onclick="openwindow('${ctx}/interceptConfig/updInterceptConfig.action?pid=${pid}','',800,500);">${param1}</td>
					<td onclick="openwindow('${ctx}/interceptConfig/updInterceptConfig.action?pid=${pid}','',800,500);">${param2}</td>
					<td onclick="openwindow('${ctx}/interceptConfig/updInterceptConfig.action?pid=${pid}','',800,500);">${param3}</td>
					<td onclick="openwindow('${ctx}/interceptConfig/updInterceptConfig.action?pid=${pid}','',800,500);">${urlpath}</td>
				</tr>
			</dg:gridrow>
		</dg:datagrid>
	</body>
</html>
