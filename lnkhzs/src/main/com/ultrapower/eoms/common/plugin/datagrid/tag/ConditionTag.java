package com.ultrapower.eoms.common.plugin.datagrid.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class ConditionTag extends BodyTagSupport{
    
    private String isopen;
	
    private Object bodyValue;
    
//	public int doStartTag() {
//		
//		return EVAL_BODY_INCLUDE;
//	}	
	
	
	protected Object getBodyValue() throws JspException {
	    bodyValue = getBodyContent().getString();
        if (bodyValue != null) {
            bodyValue = ExpressionEvaluatorManager.evaluate("extendResult", bodyValue.toString(), Object.class, this, pageContext);
        }
        return bodyValue;
    }
	
	public int doEndTag() {
		try {
			Object bodyvalue=getBodyValue();
			if(bodyvalue!=null)
			{
		        Tag t = findAncestorWithClass(this, DataGrideTag.class);    
		        DataGrideTag parent = (DataGrideTag) t;
		        parent.setConditionAre(bodyvalue.toString());
			} 

		} catch (Exception e) {
			System.out.println(e);
		}
		return EVAL_PAGE;
	}

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

    public void setBodyValue(String bodyValue) {
        this.bodyValue = bodyValue;
    }
	
 
}
