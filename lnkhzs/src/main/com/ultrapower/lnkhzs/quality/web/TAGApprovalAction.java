package com.ultrapower.lnkhzs.quality.web;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.TAGInfo;
import com.ultrapower.lnkhzs.quality.service.ITAGApprovalService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [投诉结单归档量明细_WEB]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *标签
 *--------------------------------------------------------------
 */
public class TAGApprovalAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	private ITAGApprovalService tAGApprovalService;

	private TAGInfo tAGApproval;

	public ITAGApprovalService gettAGApprovalService() {
		return tAGApprovalService;
	}

	public void settAGApprovalService(ITAGApprovalService tAGApprovalService) {
		this.tAGApprovalService = tAGApprovalService;
	}

	public TAGInfo gettAGApproval() {
		return tAGApproval;
	}

	public void settAGApproval(TAGInfo tAGApproval) {
		this.tAGApproval = tAGApproval;
	}

	/**
	 * 获取详细信息
	 */
	public void getDetail() {
		tAGApproval = tAGApprovalService.queryTAGApproval(tAGApproval.getId());
		renderJson(tAGApproval);
	}

	/**
	 * 新增 修改
	 */
	public void updateTagApproval() {
		tAGApprovalService.updateTagApproval(tAGApproval);
		this.renderText("success");
	}

	// 通过工单号查询
	public void selectGDH() {
		SWfCmplntsArcDetail selectByGDH = tAGApprovalService.selectByGDH(tAGApproval.getWorknumber());
		if (selectByGDH != null) {
			renderJson(selectByGDH);
		} else {
			renderText("error");
		}
	}

	// 通过工单号查询到的信息 存入
	public void addTag() {
		UserSession user = super.getUserSession();
		tAGApproval.setReporter(user.getPid());
		tAGApproval.setReportername(user.getFullName());
		tAGApprovalService.addTag(tAGApproval);
		renderText("success");
	}
	
	public void deleteTag(){
		tAGApprovalService.deleteTag(tAGApproval.getId());
		renderText("success");
	}
	
	public void getTagDetail(){
		tAGApproval = tAGApprovalService.queryTagDetail(tAGApproval.getId());
		renderJson(tAGApproval);
	}
	
	public void submitTag() {
		tAGApprovalService.submitTag(tAGApproval);
		this.renderText("success");
	}

	/*
	 * @Test public void getuuid(){ for(int i=1;i<=731;i++){ String uuid =
	 * UUID.randomUUID().toString(); String upperCase = uuid.replaceAll("-",
	 * "").toUpperCase(); System.out.println(upperCase); } }
	 */

}
