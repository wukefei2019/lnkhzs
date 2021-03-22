package com.ultrapower.eoms.ultrabpp.runtime.service;

import java.util.List;

import com.ultrapower.eoms.ultrabpp.runtime.model.AttachTemplateModel;

public interface AttachTemplateService
{
	public List<AttachTemplateModel> getAttachTempateList(String baseSchema);
	
	public AttachTemplateModel getAttachTemplateModel(String pid);
	
	public void saveAttachTemplateModel(AttachTemplateModel model);
	
	public void updateAttachTemplateModel(AttachTemplateModel model);
	
	public void delAttachTemplateModel(String pid);
	
	public AttachTemplateModel getAttachTemplateModel(String baseSchema,String templateName);
	
}
