package com.ultrapower.eoms.ultrabpp.runtime.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.web.BaseAction;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrabpp.runtime.model.AttachTemplateModel;
import com.ultrapower.eoms.ultrabpp.runtime.service.AttachTemplateService;
import com.ultrapower.eoms.ultrasm.model.Attachment;
import com.ultrapower.eoms.ultrasm.service.AttachmentManagerService;


public class AttachTemplateAction extends BaseAction
{
	private AttachTemplateService attachTemplateService;
	private AttachmentManagerService attachmentManagerService;
	private String baseSchema;
	private String pid;
	private String templateName;
	private AttachTemplateModel attachModel;
	private List<AttachTemplateModel> attachModelList;
	private List<Attachment> attachmentList;
	
	public String attachTemplateList()
	{
		if(StringUtils.checkNullString(baseSchema).length()>0)
		{
			attachModelList = attachTemplateService.getAttachTempateList(baseSchema);
		}
		return SUCCESS;
	}
	
	public String attachModelList()
	{
		if(StringUtils.checkNullString(pid).length()>0)
		{
			attachmentList = attachmentManagerService.getAttachmentByRelation(pid);
		}
		return this.SUCCESS;
	}
	
	public String saveAttachTemplatModel()
	{
		if(attachModel != null)
		{
			UserSession userSession =(UserSession) this.getSession().getAttribute("userSession");
			if(StringUtils.checkNullString(attachModel.getPid()).length()==0)
			{
				attachModel.setPid(UUIDGenerator.getUUIDoffSpace());
				attachModel.setBaseSchema(baseSchema);
				attachModel.setCreator(userSession!=null?userSession.getLoginName():"");
				attachModel.setCreateTime(TimeUtils.getCurrentTime());
				attachTemplateService.saveAttachTemplateModel(attachModel);
			}else{
				attachModel.setCreator(userSession!=null?userSession.getLoginName():"");
				attachModel.setCreateTime(TimeUtils.getCurrentTime());
				attachTemplateService.updateAttachTemplateModel(attachModel);
			}
		}
		return "refresh";
	}
	
	public String attachTemplateEdit()  
	{
		if(StringUtils.checkNullString(pid).length() > 0)
		{
			attachModel = attachTemplateService.getAttachTemplateModel(pid);
		}
		return SUCCESS;
	}
	
	public void removeAttacheTemplate()
	{
		String retbol = "false";
		String pid = StringUtils.checkNullString(this.getRequest().getParameter("templateID"));
		try{
			attachTemplateService.delAttachTemplateModel(pid);
			retbol = "true";
		}catch(Exception e)
		{
			retbol = "false";
		}
		this.renderText(retbol);
	}
	
	public void checkTemplateName() 
	{
		String retbol = "true";
		String baseSchema = StringUtils.checkNullString(this.getRequest().getParameter("baseSchema"));
		
		String templateName = StringUtils.checkNullString(this.getRequest().getParameter("templateName"));
		try {
			templateName = java.net.URLDecoder.decode(templateName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String pid = StringUtils.checkNullString(this.getRequest().getParameter("pid"));
		AttachTemplateModel model = attachTemplateService.getAttachTemplateModel(baseSchema, templateName);
		if(model != null)
		{
			if(StringUtils.checkNullString(pid).length()==0)
			{
				retbol = "false";
			}else{
				if(!model.getPid().equals(pid))
				{
					retbol = "false";
				}
			}
		}
		this.renderText(retbol);
	}
	
	public String attTemplateFrame()
	{
		return SUCCESS; 
	}
	
	public AttachTemplateService getAttachTemplateService()
	{
		return attachTemplateService;
	}
	public void setAttachTemplateService(AttachTemplateService attachTemplateService)
	{
		this.attachTemplateService = attachTemplateService;
	}
	public String getBaseSchema()
	{
		return baseSchema;
	}
	public void setBaseSchema(String baseSchema)
	{
		this.baseSchema = baseSchema;
	}
	public String getPid()
	{
		return pid;
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public AttachTemplateModel getAttachModel()
	{
		return attachModel;
	}
	public void setAttachModel(AttachTemplateModel attachModel)
	{
		this.attachModel = attachModel;
	}


	public List<AttachTemplateModel> getAttachModelList()
	{
		return attachModelList;
	}


	public void setAttachModelList(List<AttachTemplateModel> attachModelList)
	{
		this.attachModelList = attachModelList;
	}

	public AttachmentManagerService getAttachmentManagerService()
	{
		return attachmentManagerService;
	}

	public void setAttachmentManagerService(
			AttachmentManagerService attachmentManagerService)
	{
		this.attachmentManagerService = attachmentManagerService;
	}

	public List<Attachment> getAttachmentList()
	{
		return attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList)
	{
		this.attachmentList = attachmentList;
	}
}
