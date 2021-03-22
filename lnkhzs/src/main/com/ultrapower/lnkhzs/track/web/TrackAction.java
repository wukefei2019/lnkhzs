package com.ultrapower.lnkhzs.track.web;

import java.util.List;

import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealJobdata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealPlandata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsResendDeal;
import com.ultrapower.lnkhzs.track.service.ITrackService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TrackAction extends BaseAction {

    private static final long serialVersionUID = -1L;

	private ITrackService trackService;
	
	private NgcsWfCmplntsDealPlandata plandata;
	
	private String pid;

	private String parentid;
	
	private String curStep;

    public void ajaxGetMainData(){
    	
    	List<NgcsWfCmplntsResendDeal> deals = trackService.getAjaxMainData(parentid, curStep);
		renderJson(deals);
    }
    
    public String ajaxGetPlanData(){
    	plandata = trackService.getAjaxPlanData(pid);
//    	renderJson(plan);
    	return this.findForward("trackPlan");
    }

    public void ajaxGetJobData(){
    	
    	List<NgcsWfCmplntsDealJobdata> jobs = trackService.getAjaxJobData(pid);
		renderJson(jobs);
    }
    
    public void ajaxGetMaxStepData() {
    	String maxStep = trackService.getAjaxMaxStepData(parentid);
		renderJson(maxStep);
    }
    
	public ITrackService getTrackService() {
		return trackService;
	}

	public void setTrackService(ITrackService trackService) {
		this.trackService = trackService;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCurStep() {
		return curStep;
	}

	public void setCurStep(String curStep) {
		this.curStep = curStep;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public NgcsWfCmplntsDealPlandata getPlandata() {
		return plandata;
	}

	public void setPlandata(NgcsWfCmplntsDealPlandata plandata) {
		this.plandata = plandata;
	}
}
