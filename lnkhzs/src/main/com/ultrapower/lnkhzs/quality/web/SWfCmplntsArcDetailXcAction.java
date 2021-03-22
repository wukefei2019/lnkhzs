package com.ultrapower.lnkhzs.quality.web;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailXc;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailXcService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [协查工单结单归档量明细_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsArcDetailXcAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsArcDetailXcService sWfCmplntsArcDetailXcService;
    
    private SWfCmplntsArcDetailXc sWfCmplntsArcDetailXc;

	public void setsWfCmplntsArcDetailXcService(ISWfCmplntsArcDetailXcService sWfCmplntsArcDetailXcService) {
		this.sWfCmplntsArcDetailXcService = sWfCmplntsArcDetailXcService;
	}

	public SWfCmplntsArcDetailXc getsWfCmplntsArcDetailXc() {
		return sWfCmplntsArcDetailXc;
	}

	public void setsWfCmplntsArcDetailXc(SWfCmplntsArcDetailXc sWfCmplntsArcDetailXc) {
		this.sWfCmplntsArcDetailXc = sWfCmplntsArcDetailXc;
	}

	public String arcXcDetail(){
		sWfCmplntsArcDetailXc = sWfCmplntsArcDetailXcService.getArcXcDetailById(sWfCmplntsArcDetailXc.getWrkfmShowSwftno());
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsArcDetailXc = sWfCmplntsArcDetailXcService.getEndTime();
		renderJson(sWfCmplntsArcDetailXc);
	}


}
