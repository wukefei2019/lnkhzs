package com.ultrapower.eoms.ultrabpp.runtime.manage;

import java.util.List;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.ultrabpp.runtime.model.AttachTemplateModel;
import com.ultrapower.eoms.ultrabpp.runtime.service.AttachTemplateService;

public class AttachTemplateManagerImpl implements AttachTemplateService
{
	
	private IDao hibernateDao;
	@Override
	public List<AttachTemplateModel> getAttachTempateList(String baseSchema)
	{
		List<AttachTemplateModel>  list = hibernateDao.find("from AttachTemplateModel where baseSchema=?", baseSchema);
		return list;
	}
	
	@Override
	public AttachTemplateModel getAttachTemplateModel(String pid)
	{
		AttachTemplateModel attachModel = (AttachTemplateModel)hibernateDao.findUnique("from AttachTemplateModel where pid=?", pid);
		return attachModel;
	}


	@Override
	public void saveAttachTemplateModel(AttachTemplateModel model)
	{
		hibernateDao.save(model);
	}
	
	@Override
	public void updateAttachTemplateModel(AttachTemplateModel model)
	{
		hibernateDao.saveOrUpdate(model);
	}
	
	@Override
	public void delAttachTemplateModel(String pid)
	{
		hibernateDao.executeUpdate("delete from AttachTemplateModel where pid=?", pid);
	}

	@Override
	public AttachTemplateModel getAttachTemplateModel(String baseSchema, String templateName)
	{
		List<AttachTemplateModel> templateList = hibernateDao.find("from AttachTemplateModel where baseSchema=? and templateName=?", baseSchema,templateName);
		if(templateList != null && templateList.size()>0)
		{
			return templateList.get(0);
		}else
			return null;
	}

	public IDao getHibernateDao()
	{
		return hibernateDao;
	}
	public void setHibernateDao(IDao hibernateDao)
	{
		this.hibernateDao = hibernateDao;
	}

}
