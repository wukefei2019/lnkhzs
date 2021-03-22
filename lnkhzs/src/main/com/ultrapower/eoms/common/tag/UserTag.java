package com.ultrapower.eoms.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;

public class UserTag extends TagSupport{
    /**
     * 
     */
    private static final long serialVersionUID = -3199352390825956297L;
    
    private String loginname;
    
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public int doStartTag() {
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() {
        JspWriter out = pageContext.getOut();
        try {
        	UserManagerService userManagerService = (UserManagerService) WebApplicationManager.getBean("userManagerService");
        	if(StringUtils.isBlank(loginname)){
        		 out.print("");        		 
        	}else{
        		UserInfo user = userManagerService.getUserByLoginNameCache(loginname);
                if (user!=null&&user.getFullname() != null){
                	 out.print(user.getFullname());
                }
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
