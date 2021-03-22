package com.ultrapower.lnkhzs.quality.web;


import com.ultrapower.lnkhzs.quality.model.TAGAdmin;
import com.ultrapower.lnkhzs.quality.service.ITAGAdminService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [投诉结单归档量明细_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *标签
 *--------------------------------------------------------------
 */
public class TAGAdminAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ITAGAdminService tAGAdminService;

    private TAGAdmin tAGAdmin;
    
    

	public ITAGAdminService gettAGAdminService() {
		return tAGAdminService;
	}

	public void settAGAdminService(ITAGAdminService tAGAdminService) {
		this.tAGAdminService = tAGAdminService;
	}

	public TAGAdmin gettAGAdmin() {
		return tAGAdmin;
	}

	public void settAGAdmin(TAGAdmin tAGAdmin) {
		this.tAGAdmin = tAGAdmin;
	}
	
	
	/**
	 * 获取详细信息
	 */
	public void getDetail() {
		tAGAdmin = tAGAdminService.queryTAGApproval(tAGAdmin.getId());
		renderJson(tAGAdmin);
	}

	/**
	 * 新增
	 * 修改
	 */
	public void updateTagApproval() {
		tAGAdminService.updateTagApproval(tAGAdmin);
		this.renderText("success");
	}

	
}
