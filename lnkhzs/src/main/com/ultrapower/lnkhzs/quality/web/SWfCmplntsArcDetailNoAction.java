package com.ultrapower.lnkhzs.quality.web;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailNo;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailNoService;
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
public class SWfCmplntsArcDetailNoAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsArcDetailNoService sWfCmplntsArcDetailNoService;
    
    private SWfCmplntsArcDetailNo sWfCmplntsArcDetailNo;

    public void setsWfCmplntsArcDetailNoService(ISWfCmplntsArcDetailNoService sWfCmplntsArcDetailNoService) {
		this.sWfCmplntsArcDetailNoService = sWfCmplntsArcDetailNoService;
	}

	public SWfCmplntsArcDetailNo getsWfCmplntsArcDetailNo() {
		return sWfCmplntsArcDetailNo;
	}

	public void setsWfCmplntsArcDetailNo(SWfCmplntsArcDetailNo sWfCmplntsArcDetailNo) {
		this.sWfCmplntsArcDetailNo = sWfCmplntsArcDetailNo;
	}

	public String arcDetailNo(){
    	sWfCmplntsArcDetailNo = sWfCmplntsArcDetailNoService.getArcDetailById(sWfCmplntsArcDetailNo.getWrkfmShowSwftno());
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsArcDetailNo = sWfCmplntsArcDetailNoService.getEndTime();
		renderJson(sWfCmplntsArcDetailNo);
	}

}
