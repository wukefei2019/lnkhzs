package com.ultrapower.lnkhzs.quality.web;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailCf;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailCfService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [非投诉结单归档量明细_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsArcDetailCfAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsArcDetailCfService sWfCmplntsArcDetailCfService;
    
    private SWfCmplntsArcDetailCf sWfCmplntsArcDetailCf;

    public void setsWfCmplntsArcDetailCfService(ISWfCmplntsArcDetailCfService sWfCmplntsArcDetailCfService) {
		this.sWfCmplntsArcDetailCfService = sWfCmplntsArcDetailCfService;
	}

	public SWfCmplntsArcDetailCf getsWfCmplntsArcDetailCf() {
		return sWfCmplntsArcDetailCf;
	}

	public void setsWfCmplntsArcDetailCf(SWfCmplntsArcDetailCf sWfCmplntsArcDetailCf) {
		this.sWfCmplntsArcDetailCf = sWfCmplntsArcDetailCf;
	}

	public String arcDetailCf(){
    	sWfCmplntsArcDetailCf = sWfCmplntsArcDetailCfService.getArcDetailById(sWfCmplntsArcDetailCf.getWrkfmShowSwftno());
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsArcDetailCf = sWfCmplntsArcDetailCfService.getEndTime();
		renderJson(sWfCmplntsArcDetailCf);
	}

}
