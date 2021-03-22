package com.ultrapower.eoms.ultrabpp.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ultrapower.eoms.ultrasm.model.Attachment;
import com.ultrapower.eoms.ultrasm.service.AttachmentManagerService;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;

import com.ultrapower.eoms.ultrabpp.runtime.model.AttachTemplateModel;
import com.ultrapower.eoms.ultrabpp.runtime.service.AttachTemplateService;

public class AttachmentTemplateTag extends TagSupport
{
	private String id;
	private String name;
	private String baseSchema;
	private String value;
	
	@Override
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		StringBuffer htmlStr = new StringBuffer(1024);
		htmlStr.append("<select id='"+id+"' name='"+name+"' >");
		htmlStr.append("<option value='' selected></option>");
		AttachTemplateService attachTemplateService = (AttachTemplateService)WebApplicationManager.getBean("attachTemplateService");
		List<AttachTemplateModel> attachModelList = attachTemplateService.getAttachTempateList(baseSchema);
		for(AttachTemplateModel attachment:attachModelList)
		{
			if(attachment.getPid().equals(value))
			{
				htmlStr.append("<option value='"+attachment.getPid()+"' selected>"+attachment.getTemplateName()+"</option>");
			}
			else{
				htmlStr.append("<option value='"+attachment.getPid()+"' >"+attachment.getTemplateName()+"</option>");
			}
		}
		htmlStr.append("</select>");
		try
		{
			out.write(htmlStr.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	

	public String getBaseSchema()
	{
		return baseSchema;
	}

	public void setBaseSchema(String baseSchema)
	{
		this.baseSchema = baseSchema;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
	
}
