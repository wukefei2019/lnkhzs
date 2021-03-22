<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ultrapower.eoms.common.startup.SessionListener"%>
<%@page import="com.ultrapower.eoms.common.core.component.cache.manager.BaseCacheManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	    
    <title>userOnline</title>
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
  	<%
  		Object objUserSession=BaseCacheManager.getAllElementByCacheName(SessionListener.SESSION_CACHENAME);
  		//Object objUserSession=this.getServletContext().getAttribute("userNames");
  		request.setAttribute("usersession",objUserSession);
  	%>

	  	<dg:datagrid  var="userinfo"  items="${usersession}" title="当前位置：自管理>>在线用户" mempage="true">
	  		<dg:lefttoolbar>
	  		<!-- 
	  	  		<eoms:operate cssclass="page_search_button" id="com_btn_search" onmouseover="this.className='page_search_button_hover'" 
	  	  			onmouseout="this.className='page_search_button'"  onclick="" text='com_btn_search' />
  		  	 -->	
  		  		<eoms:operate cssclass="page_refresh_button" id="com_btn_refresh" onmouseover="this.className='page_refresh_button_hover'" 
  		  			onmouseout="this.className='page_refresh_button'" onclick="location.reload();" text="com_btn_refresh"/>
	      		<eoms:operate cssclass="page_help_button" id="com_btn_help" onmouseover="this.className='page_help_button_hover'" 
	      			onmouseout="this.className='page_help_button'" text="com_btn_help"/>
	  		</dg:lefttoolbar>
	  		<dg:condition>
		  		<div align="center">
			      <table  class="serachdivTable" style="width:80%">
			        <tr>
			          <td colspan="6" align="center">
			          	<input type="hidden" name="ids" id="userID" value=""/>
						<input type="hidden" name="transType" value=""/>
			          	<input type="submit" name="searchUser" id="searchUser" value="<eoms:lable name="com_btn_search"/>" class="searchButton" onmouseover="this.className='searchButton_hover'" onmouseout="this.className='searchButton'"/>
			        	<input type="reset" name="resetCondition" id="resetCondition" value="<eoms:lable name="com_btn_reset"/>" class="ResetButton" onmouseover="this.className='ResetButton_hover'" onmouseout="this.className='ResetButton'"/>
			          </td>
			        </tr>
			      </table>
			    </div>
	  		</dg:condition>
	    	<dg:gridtitle>
		    	<tr>
		    		<th width="35"><eoms:lable name="com_lb_sequenceNumber"/></th>
		    		<th width="25%"><eoms:lable name="sm_lb_userName"/></th>
		    		<th width="25%"><eoms:lable name="sm_lb_userFullName"/></th>
		    		<th><eoms:lable name="com_lb_lastLoginTime"/></th>
		    		<th width="25%"><eoms:lable name="sm_lb_department"/></th>
		    		
		    	</tr>
	    	</dg:gridtitle> 
			<dg:gridrow>
				<tr class="${userinfo_row}">
					<td>${rowindex}</td>
			       <td>${userinfo.loginName}</td>
			       <td>${userinfo.fullName}</td>
			       <td>${userinfo.loginDate}</td>
			       <td>${userinfo.depName}</td>
			    </tr>
			</dg:gridrow>
		</dg:datagrid>	


  </body>
</html>
