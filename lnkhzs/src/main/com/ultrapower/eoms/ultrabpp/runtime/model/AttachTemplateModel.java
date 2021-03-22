package com.ultrapower.eoms.ultrabpp.runtime.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BS_T_BPP_ATTTEMPLATE")
public class AttachTemplateModel
{
	private String pid;
	private String templateName;
	private String creator;
	private Long createTime;
	private String baseSchema;
	
	@Id
	public String getPid()
	{
		return pid;
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public String getTemplateName()
	{
		return templateName;
	}
	public void setTemplateName(String templateName)
	{
		this.templateName = templateName;
	}
	public String getCreator()
	{
		return creator;
	}
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	public Long getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}
	public String getBaseSchema()
	{
		return baseSchema;
	}
	public void setBaseSchema(String baseSchema)
	{
		this.baseSchema = baseSchema;
	}
	
}
