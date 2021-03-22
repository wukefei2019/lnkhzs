package com.ultrapower.lnkhzs.survey.manager;


import java.util.List;

import com.ultrapower.eoms.common.core.dao.IDao;

import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectService;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectSymbolService;


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
public class BindDicselectSymbolMgrImpl implements IBindDicselectSymbolService {

    private IDao<BindDicselectSymbol> bindDicselectSymbolDao;

	public void setBindDicselectSymbolDao(IDao<BindDicselectSymbol> bindDicselectSymbolDao) {
		this.bindDicselectSymbolDao = bindDicselectSymbolDao;
	}

	@Override
	public List<BindDicselectSymbol> getSelectBol(String dicselectid) {
		// TODO Auto-generated method stub
		List<BindDicselectSymbol> find = bindDicselectSymbolDao.find("from BindDicselectSymbol where dicselectid = ?", dicselectid);
		return  find;
	}

	@Override
	public void delectSymbol(String dicselectid) {
		// TODO Auto-generated method stub
		try {
			bindDicselectSymbolDao.executeUpdate("delete from BindDicselectSymbol where dicselectid =?", dicselectid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
    
    
    



}
