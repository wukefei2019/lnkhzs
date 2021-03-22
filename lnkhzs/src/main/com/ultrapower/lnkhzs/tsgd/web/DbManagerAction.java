package com.ultrapower.lnkhzs.tsgd.web;

import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;
import com.ultrapower.lnkhzs.tsgd.service.IDbManagerService;
import com.ultrapower.omcs.base.web.BaseAction;



/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_WEB]
 * </p>
 * 
 * @author : lxd
 * @date : 2019/7/23
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */

public final class DbManagerAction extends BaseAction {

	private static final long serialVersionUID = -1L;
	

	private IDbManagerService dbManagerService;
	
	        
	private DbManager dbmanager;

	

	

	public IDbManagerService getDbManagerService() {
		return dbManagerService;
	}

	public void setDbManagerService(IDbManagerService dbManagerService) {
		this.dbManagerService = dbManagerService;
	}

	public DbManager getDbmanager() {
		return dbmanager;
	}

	public void setDbmanager(DbManager dbmanager) {
		this.dbmanager = dbmanager;
	}



	public void getManagerDetail(){
		dbmanager =  dbManagerService.queryDbManager(dbmanager.getId());
		renderJson(dbmanager);
	}
	
	
	public void saveManager(){
		if(dbManagerService.save(dbmanager))
			renderText(SUCCESS);
		else
			renderText(ERROR);
    }
	
	public void deleteManager(){
		if(dbManagerService.delete(dbmanager))
			renderText(SUCCESS);
		else
			renderText(ERROR);
    }
	
	
}
