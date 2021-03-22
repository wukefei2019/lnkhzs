package com.ultrapower.lnkhzs.quality.web;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailSj;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailSjService;
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
public class SWfCmplntsArcDetailSjAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsArcDetailSjService sWfCmplntsArcDetailSjService;
    
    private SWfCmplntsArcDetailSj sWfCmplntsArcDetailSj;

	public ISWfCmplntsArcDetailSjService getsWfCmplntsArcDetailSjService() {
		return sWfCmplntsArcDetailSjService;
	}

	public void setsWfCmplntsArcDetailSjService(ISWfCmplntsArcDetailSjService sWfCmplntsArcDetailSjService) {
		this.sWfCmplntsArcDetailSjService = sWfCmplntsArcDetailSjService;
	}

	public SWfCmplntsArcDetailSj getsWfCmplntsArcDetailSj() {
		return sWfCmplntsArcDetailSj;
	}

	public void setsWfCmplntsArcDetailSj(SWfCmplntsArcDetailSj sWfCmplntsArcDetailSj) {
		this.sWfCmplntsArcDetailSj = sWfCmplntsArcDetailSj;
		
		
	}
	public String arcDetailSj(){
		sWfCmplntsArcDetailSj = sWfCmplntsArcDetailSjService.getArcDetailSjById(sWfCmplntsArcDetailSj.getWrkfmShowSwftno());
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsArcDetailSj = sWfCmplntsArcDetailSjService.getEndTime();
		renderJson(sWfCmplntsArcDetailSj);
	}
    
	
	
	
	
	

}
