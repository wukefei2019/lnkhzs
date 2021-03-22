package com.ultrapower.lnkhzs.survey.manager;


import com.ultrapower.eoms.common.core.dao.IDao;

import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectService;


/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [答卷_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class BindDicselectMgrImpl implements IBindDicselectService {

    private IDao<BindDicselect> bindDicselectDao;
    
    

    public void setBindDicselectDao(IDao<BindDicselect> bindDicselectDao) {
		this.bindDicselectDao = bindDicselectDao;
	}



	@Override
	public BindDicselect getSelectMain(String id) {
		return bindDicselectDao.get(id);
	}



	@Override
	public void delectDicselect(String id) {
		 try {
			bindDicselectDao.removeById(id);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}



}
