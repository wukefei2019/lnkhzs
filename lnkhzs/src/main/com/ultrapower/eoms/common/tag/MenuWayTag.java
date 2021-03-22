package com.ultrapower.eoms.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.service.PrivilegeManagerService;

public class MenuWayTag extends TagSupport 
{
	
	private String id;//名称

	
	public int doStartTag()
	{
		return EVAL_BODY_INCLUDE;
	}
	public int doEndTag() 
	{
		JspWriter out = pageContext.getOut();
		try 
		{
			String userid="";
			StringBuffer strText=new StringBuffer();
			Object objSession=pageContext.getSession().getAttribute("userSession");
			if(objSession!=null)
			{
				UserSession userSession=(UserSession)objSession;
				userid=StringUtils.checkNullString(userSession.getPid());
			}
			String menu = pageContext.getRequest().getParameter("navid");
			if(menu==null||"".equals(menu)){
				menu = id;
			}
			
			if(menu==null||"".equals(menu)){
			    return EVAL_PAGE;
            }
            
			
			Object obj=WebApplicationManager.getBean("privilegeManagerService");
			if(obj!=null)
			{
				PrivilegeManagerService privilegeManagerService=(PrivilegeManagerService)obj;
				List<String> list = privilegeManagerService.getNavWayPoint(userid, menu);
				strText.append("<div class=\"lujing\">当前位置：");
				int count = 0;
				int size = list.size();
					
				for(String tmp:list){
					count++;
					strText.append(tmp);
					if(count<size){
						strText.append("&gt;");
					}
				}
				strText.append("</div>");
			
				
			}				
			out.print(strText.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
