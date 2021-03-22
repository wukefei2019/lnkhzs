package com.ultrapower.lnkhzs.tsgd.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.lnkhzs.base.web.BaseAction;
import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.lnkhzs.tsgd.service.IDbManagerService;
import com.ultrapower.lnkhzs.tsgd.service.WFresendService;

public class WFresendAction extends BaseAction{

	private static final long serialVersionUID = -1L;
	
	private WFresend wFresend;
	
	private WFresendService wFresendService;
	
	private DepManagerService depManagerService;
	
	private UserManagerService userManagerService;
	
	private IKhzsSurverManagerService khzsSurverManagerService;
	
	private IDbManagerService dbManagerService;
	
	private String A12;
	
	

	public String getA12() {
		return A12;
	}

	public void setA12(String a12) {
		A12 = a12;
	}

	public WFresendService getwFresendService() {
		return wFresendService;
	}

	public void setwFresendService(WFresendService wFresendService) {
		this.wFresendService = wFresendService;
	}

	public WFresend getwFresend() {
		return wFresend;
	}

	public void setwFresend(WFresend wFresend) {
		this.wFresend = wFresend;
	}
	
	public DepManagerService getDepManagerService() {
		return depManagerService;
	}

	public void setDepManagerService(DepManagerService depManagerService) {
		this.depManagerService = depManagerService;
	}
	
	

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	
	

	public IKhzsSurverManagerService getKhzsSurverManagerService() {
		return khzsSurverManagerService;
	}

	public void setKhzsSurverManagerService(IKhzsSurverManagerService khzsSurverManagerService) {
		this.khzsSurverManagerService = khzsSurverManagerService;
	}
	
	

	public IDbManagerService getDbManagerService() {
		return dbManagerService;
	}

	public void setDbManagerService(IDbManagerService dbManagerService) {
		this.dbManagerService = dbManagerService;
	}

	//保存
	public void addSend(){
		try {
			boolean b=wFresendService.addSend(wFresend);
			if(b)
				renderText(SUCCESS);
			else
				renderText(ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(ERROR);
		}
	}
	
	//根据ID获取信息
	public void getDetailById(){
		WFresend wFresend1=wFresendService.getDetailById(wFresend);
		renderJson(wFresend1);
	}
	
	//根据标签分类获取信息
	public void getDetailByLABELCATEGORY(){
		WFresend wFresend1=wFresendService.getDetailByLABELCATEGORY(wFresend);
		renderJson(wFresend1);
	}
	
	//获取指定部门
	public void getDept(){
		List<DepInfo> dep=depManagerService.getDeptByTopAndName();
		renderJson(dep);
	}
	
	//获取
	public void getTopUserByDep(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String depid=request.getParameter("depid");
		//UserInfo user=userManagerService.getTopUserByDep(depid);
		//KhzsSurverManager manager=khzsSurverManagerService.getUserByDepid(depid);
		DbManager manager=dbManagerService.getUserByDepid(depid);
		renderJson(manager);
	}
	
	
	public void getUserDept(){
		UserSession user = super.getUserSession();
		String userid=user.getLoginName();
		String username=user.getFullName();
		String deptlevel2id=user.getDeptLevel2Id();
		String deptname=user.getDepFullName();
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("username",username);
		map.put("userid",userid);
		map.put("deptlevel2id",deptlevel2id);
		map.put("deptname",deptname);
		renderJson(map);
	}
	
	@Test
	public void mytest(){
		WFresend wFresend=new WFresend();
		wFresend.setApplytitle("派单主题");
		wFresend.setMattercategory("投诉问题单");
		wFresend.setLabelcategory("有信号全部网站、游戏、视频、APP等软件无法使用→全局流转");
		wFresend.setMaindepart("信息技术中心");
		wFresend.setMaindepartid("jinchangjia");
		wFresend.setExpirydate("2019-10-09");
		wFresend.setContent("测试数据");
		wFresend.setValidmethod("投诉量");
		wFresend.setValidvalue("10");
		wFresend.setTarget("测试问题");
		IaAuditSendAction.draftWorkFlow(wFresend);
	}
	
	public void getInfo(){
		Map<String,String> info = wFresendService.getInfo(A12);
		renderJson(info);
	}
	
	
}
