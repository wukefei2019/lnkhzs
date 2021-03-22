package com.ultrapower.lnkhzs.quality.web;

import java.util.Map;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsCheckSheetDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailService;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsCheckSheetDetailService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [投诉结单归档量明细_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsCheckSheetDetailAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsCheckSheetDetailService sWfCmplntsCheckSheetDetailService;
    
    private SWfCmplntsCheckSheetDetail sWfCmplntsCheckSheetDetail;

	public ISWfCmplntsCheckSheetDetailService getsWfCmplntsCheckSheetDetailService() {
		return sWfCmplntsCheckSheetDetailService;
	}

	public void setsWfCmplntsCheckSheetDetailService(ISWfCmplntsCheckSheetDetailService sWfCmplntsCheckSheetDetailService) {
		this.sWfCmplntsCheckSheetDetailService = sWfCmplntsCheckSheetDetailService;
	}
	
	
	public SWfCmplntsCheckSheetDetail getsWfCmplntsCheckSheetDetail() {
		return sWfCmplntsCheckSheetDetail;
	}

	public void setsWfCmplntsCheckSheetDetail(SWfCmplntsCheckSheetDetail sWfCmplntsCheckSheetDetail) {
		this.sWfCmplntsCheckSheetDetail = sWfCmplntsCheckSheetDetail;
	}

	/**
	 * 新增修改
	 */
	public void editCheckSheet(){
		sWfCmplntsCheckSheetDetailService.editCheckSheet(sWfCmplntsCheckSheetDetail);
		renderText(SUCCESS);
	}
	

    
    
    
}
