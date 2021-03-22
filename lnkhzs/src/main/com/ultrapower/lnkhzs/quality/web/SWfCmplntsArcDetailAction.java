package com.ultrapower.lnkhzs.quality.web;

import java.util.Map;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailService;
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
public class SWfCmplntsArcDetailAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ISWfCmplntsArcDetailService sWfCmplntsArcDetailService;

    private SWfCmplntsArcDetail sWfCmplntsArcDetail;
    
    private String time;
    
    private String lable;
    
    private String mg;
    
    

	public String getMg() {
		return mg;
	}

	public void setMg(String mg) {
		this.mg = mg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public void setsWfCmplntsArcDetailService(ISWfCmplntsArcDetailService sWfCmplntsArcDetailService) {
		this.sWfCmplntsArcDetailService = sWfCmplntsArcDetailService;
	}

	public void setsWfCmplntsArcDetail(SWfCmplntsArcDetail sWfCmplntsArcDetail) {
		this.sWfCmplntsArcDetail = sWfCmplntsArcDetail;
	}
	
	public SWfCmplntsArcDetail getsWfCmplntsArcDetail() {
		return sWfCmplntsArcDetail;
	}

	public String arcDetail(){
		sWfCmplntsArcDetail = sWfCmplntsArcDetailService.getArcDetailById(sWfCmplntsArcDetail.getWrkfmShowSwftno());
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsArcDetail = sWfCmplntsArcDetailService.getEndTime();
		renderJson(sWfCmplntsArcDetail);
	}
	
	public void otherGetThan(){
		Map<Object, Object> otherGetThan = sWfCmplntsArcDetailService.otherGetThan(time,lable,mg);
		renderJson(otherGetThan);
	}
}
