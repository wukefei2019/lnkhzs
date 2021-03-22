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
		
	</script>
	</head>
	<body>
		<dg:datagrid var="interceptConfig" sqlname="SQL_SM_listInterceptConfigLog.query" title="${nodePath }">
			<dg:lefttoolbar>
				<eoms:operate cssclass="page_search_button" id="com_btn_search"
					onmouseover="this.className='page_search_button_hover'"
					onmouseout="this.className='page_search_button'" onclick="showsearch()"
					text='com_btn_search' />
				<eoms:operate cssclass="page_refresh_button" id="com_btn_refresh"
					onmouseover="this.className='page_refresh_button_hover'"
					onmouseout="this.className='page_refresh_button'"
					onclick="location.reload();" text="com_btn_refresh" />
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
					<th>功能名称</th>	
					<th>访问用户</th>	
					<th>访问时间</th>
					<th>访问IP</th>
					<th>URL地址</th>
					<th>参数1</th>
					<th>参数2</th>
					<th>参数3</th>
				</tr>
			</dg:gridtitle>
			<dg:gridrow>
				<tr class="${interceptConfig_row}">
					<td>${functionname}</td>
					<td>${loginname}</td>
					<td><eoms:date value="${operatetime}"></eoms:date></td>
					<td>${ip}</td>
					<td>${urlpath}</td>
					<td>${param1}</td>
					<td>${param2}</td>
					<td>${param3}</td>
				</tr>
			</dg:gridrow>
		</dg:datagrid>
	</body>
</html>
