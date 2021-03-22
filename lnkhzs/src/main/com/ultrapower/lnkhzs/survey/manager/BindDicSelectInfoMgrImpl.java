package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.service.IBindDicSelectInfoService;

public class BindDicSelectInfoMgrImpl implements IBindDicSelectInfoService{

	 private IDao<BindDicSelectInfo> bindDicSelectInfoDao;

	public IDao<BindDicSelectInfo> getBindDicSelectInfoDao() {
		return bindDicSelectInfoDao;
	}

	public void setBindDicSelectInfoDao(IDao<BindDicSelectInfo> bindDicSelectInfoDao) {
		this.bindDicSelectInfoDao = bindDicSelectInfoDao;
	}
	 
	 
	@Override
	public BindDicSelectInfo getSelectMain(String id) {
		return bindDicSelectInfoDao.get(id);
	}

	@Override
	public void delectDicSelectInfo(String id) {
		// TODO Auto-generated method stub
		try {
			bindDicSelectInfoDao.removeById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	 
	 
}
