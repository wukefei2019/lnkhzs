package com.ultrapower.eoms.common.demo.web;

import com.ultrapower.eoms.common.core.web.BaseAction;

public class DemoAction extends BaseAction{
	public String page;
	public String demo(){
		return this.findForward(page);
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
