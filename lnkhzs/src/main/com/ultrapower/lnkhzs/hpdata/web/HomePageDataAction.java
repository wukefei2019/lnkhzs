package com.ultrapower.lnkhzs.hpdata.web;

import java.util.List;
import java.util.Map;

import com.ultrapower.lnkhzs.hpdata.service.IHomePageDataService;
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
public class HomePageDataAction extends BaseAction {

    private static final long serialVersionUID = -1L;

	private IHomePageDataService homePageDataService;
	
	private String title;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IHomePageDataService getHomePageDataService() {
		return homePageDataService;
	}

	public void setHomePageDataService(IHomePageDataService homePageDataService) {
		this.homePageDataService = homePageDataService;
	}
	
	
	/*全省各地市的投诉率*/
	public void ajaxGetComplaintsAmount(){
		List<Map<String, String>> result = homePageDataService.ajaxGetComplaintsAmount();
		this.renderJson(result);
	}
	
	/*客户之声 案例总量 建议库总量 金点子总量*/
	public void ajaxGetKHZSAmount(){
		List<Map<String, String>> result = homePageDataService.ajaxGetKHZSAmount();
		this.renderJson(result);
	}
	
	/*近12个月 溯源问题投诉量*/
	public void ajaxGetSourceAmount(){
		List<Map<String, String>> result = homePageDataService.ajaxGetSourceAmount();
		this.renderJson(result);
	}
	
	/*近12个月 溯源问题投诉量占比*/
	public void ajaxGetSourceAmountNew(){
		List<Map<String, String>> result = homePageDataService.ajaxGetSourceAmountNew();
		this.renderJson(result);
	}
	
	/*近12个月 智慧调研答题量*/
	public void ajaxGetAnswerAmount(){
		List<Map<String, String>> result = homePageDataService.ajaxGetAnswerAmount();
		this.renderJson(result);
	}
	
	/*标签投诉TOP5*/
	public void ajaxGetTagTOP5Amount(){
		List<Map<String, String>> result = homePageDataService.ajaxGetTagTOP5Amount(title);
		this.renderJson(result);
	}
	
	/*问题督办最新信息*/
	public void ajaxGetResendList(){
		List<Map<String, String>> result = homePageDataService.ajaxGetResendList();
		this.renderJson(result);
	}

}
