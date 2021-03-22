package com.ultrapower.eoms.ultrasm.web;

import com.ultrapower.eoms.common.core.web.BaseAction;
import com.ultrapower.eoms.ultrasm.model.InterceptConfig;
import com.ultrapower.eoms.ultrasm.service.InterceptConfigService;

public class InterceptConfigAction  extends BaseAction{
	private InterceptConfigService interceptConfigService;
	private InterceptConfig interceptConfig;
	private String pids;
	private String pid;
	private String returnMessage;
	private String parafresh;
	
	
	public String listInterceptConfig(){
		return SUCCESS;
	}
	
	public String listInterceptConfigLog(){
		return SUCCESS;
	}
	
	public String addInterceptConfig(){
		return SUCCESS;
	}
	
	public String saveInterceptConfig(){
		returnMessage = interceptConfigService.addInterceptConfig(interceptConfig);
		interceptConfigService.refurbish();
		parafresh = "true";
		return "refresh";
	}

	public String delInterceptConfig(){
		interceptConfigService.delInterceptConfig(pids);
		interceptConfigService.refurbish();
		return this.findRedirectPar("listInterceptConfig.action?id="+pid);
	}

	public String updInterceptConfig(){
		interceptConfig = interceptConfigService.getInterceptConfig(pid);
		return this.findForward("addInterceptConfig");
	}
	
	public void setInterceptConfigService(
			InterceptConfigService interceptConfigService) {
		this.interceptConfigService = interceptConfigService;
	}

	public InterceptConfig getInterceptConfig() {
		return interceptConfig;
	}

	public void setInterceptConfig(InterceptConfig interceptConfig) {
		this.interceptConfig = interceptConfig;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getParafresh() {
		return parafresh;
	}

	public void setParafresh(String parafresh) {
		this.parafresh = parafresh;
	}
	
}
