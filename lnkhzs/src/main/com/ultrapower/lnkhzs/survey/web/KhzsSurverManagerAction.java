package com.ultrapower.lnkhzs.survey.web;

import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;
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

public final class KhzsSurverManagerAction extends BaseAction {

	private static final long serialVersionUID = -1L;
	

	private IKhzsSurverManagerService khzsSurverManagerService;
	
	        
	private KhzsSurverManager khzssurvermanager;

	

	public KhzsSurverManager getKhzssurvermanager() {
		return khzssurvermanager;
	}

	public void setKhzssurvermanager(KhzsSurverManager khzssurvermanager) {
		this.khzssurvermanager = khzssurvermanager;
	}

	
	public IKhzsSurverManagerService getKhzsSurverManagerService() {
		return khzsSurverManagerService;
	}

	public void setKhzsSurverManagerService(IKhzsSurverManagerService khzsSurverManagerService) {
		this.khzsSurverManagerService = khzsSurverManagerService;
	}

	public void getManagerDetail(){
		khzssurvermanager =  khzsSurverManagerService.queryKhzsSurverManager(khzssurvermanager.getId());
		renderJson(khzssurvermanager);
	}
	
	
	
	
	
	
	public void saveManager(){
		if(khzsSurverManagerService.save(khzssurvermanager))
			renderText(SUCCESS);
		else
			renderText(ERROR);
		
    }
	
	
}
