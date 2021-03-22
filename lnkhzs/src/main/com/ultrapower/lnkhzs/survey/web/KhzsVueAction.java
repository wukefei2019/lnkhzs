package com.ultrapower.lnkhzs.survey.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTFlowService;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAll;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAllService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireStyleService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_WEB]
 * </p>
 * 
 * @author : lxd
 * @date : 2019/7/23
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */

public final class KhzsVueAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	private IKhzsQuestionService khzsQuestionService;

	private KhzsQuestion khzsQuestion;
	
	private IKhzsQuestionAllService khzsQuestionAllService;

	private KhzsQuestionAll khzsQuestionAll;

	private IKhzsTFlowService khzsTFlowService;

	private IKhzsQuestionnaireService khzsQuestionnaireService;

	private List<Map<Object, Object>> vuelist;
	
	private IKhzsQuestionnaireStyleService khzsQuestionnaireStyleService;

	public IKhzsQuestionnaireService getKhzsQuestionnaireService() {
		return khzsQuestionnaireService;
	}

	public void setKhzsQuestionnaireService(IKhzsQuestionnaireService khzsQuestionnaireService) {
		this.khzsQuestionnaireService = khzsQuestionnaireService;
	}

	public void setKhzsQuestionService(IKhzsQuestionService khzsQuestionService) {
		this.khzsQuestionService = khzsQuestionService;
	}

	public void setKhzsQuestion(KhzsQuestion khzsQuestion) {
		this.khzsQuestion = khzsQuestion;
	}

	public IKhzsQuestionService getKhzsQuestionService() {
		return khzsQuestionService;
	}

	public KhzsQuestion getKhzsQuestion() {
		return khzsQuestion;
	}

	public IKhzsTFlowService getKhzsTFlowService() {
		return khzsTFlowService;
	}

	public void setKhzsTFlowService(IKhzsTFlowService khzsTFlowService) {
		this.khzsTFlowService = khzsTFlowService;
	}

	public List<Map<Object, Object>> getVuelist() {
		return vuelist;
	}

	public void setVuelist(List<Map<Object, Object>> vuelist) {
		this.vuelist = vuelist;
	}

	public IKhzsQuestionAllService getKhzsQuestionAllService() {
		return khzsQuestionAllService;
	}

	public void setKhzsQuestionAllService(IKhzsQuestionAllService khzsQuestionAllService) {
		this.khzsQuestionAllService = khzsQuestionAllService;
	}

	public KhzsQuestionAll getKhzsQuestionAll() {
		return khzsQuestionAll;
	}

	public void setKhzsQuestionAll(KhzsQuestionAll khzsQuestionAll) {
		this.khzsQuestionAll = khzsQuestionAll;
	}

	public IKhzsQuestionnaireStyleService getKhzsQuestionnaireStyleService() {
		return khzsQuestionnaireStyleService;
	}

	public void setKhzsQuestionnaireStyleService(IKhzsQuestionnaireStyleService khzsQuestionnaireStyleService) {
		this.khzsQuestionnaireStyleService = khzsQuestionnaireStyleService;
	}

	/**
	 * 保存
	 */
	public void doAction() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String myquestion = request.getParameter("myquestion");
			String myAsk = request.getParameter("myAsk");
			String myName = request.getParameter("myName");
			String myNameSub = request.getParameter("myNameSub");
			//新增部分
			String backgroundList=request.getParameter("backgroundList");
			String mtop=request.getParameter("mtop");
			String mbottom=request.getParameter("mbottom");
			String mleft=request.getParameter("mleft");
			String mright=request.getParameter("mright");
			String fontFamily=request.getParameter("fontFamily");
			String fontSize=request.getParameter("fontSize");
			String fontLocation=request.getParameter("fontLocation");
			String fontWeight=request.getParameter("fontWeight");
			String fontFamily_com=request.getParameter("fontFamily_com");
			String fontSize_com=request.getParameter("fontSize_com");
			String fontLocation_com=request.getParameter("fontLocation_com");
			String fontWeight_com=request.getParameter("fontWeight_com");
			
			
			
			UserSession user = super.getUserSession();
			String userid=user.getLoginName();
			String username=user.getFullName();
			String ispublic = request.getParameter("ispublic");
			String isreward = request.getParameter("isreward");
			Boolean saveOrUpdate=true;
			//问卷维护保存
			if (!(myAsk.isEmpty() || myAsk == null)) {
				khzsQuestionnaireService.saveMainBak(myAsk, myName,userid,username,ispublic,myNameSub,isreward);
				khzsQuestionAllService.addQuestionAndShip(myquestion, myAsk);
				saveOrUpdate=false;
				Boolean isSave=khzsQuestionnaireStyleService.SaveOtherSet(myAsk,backgroundList,
						mtop,mbottom,mleft,mright,
						fontFamily,fontSize,fontLocation,fontWeight,
						fontFamily_com,fontSize_com,fontLocation_com,fontWeight_com,saveOrUpdate);
				
			} else {// 文卷保存
				/*String uuid = UUIDGenerator.getUUIDoffSpace();*/
				/*String uuid = KhzsCreateIdUtils.nextId();*/
				String uuid=(int) (System.currentTimeMillis() / 1000)+""+(int)((Math.random()*900)+100)+"";
				khzsQuestionnaireService.saveMainBak(uuid, myName,userid,username,ispublic,myNameSub,isreward);
				khzsQuestionAllService.addQuestionAndShip(myquestion, uuid);
				Boolean isSave=khzsQuestionnaireStyleService.SaveOtherSet(uuid,backgroundList,
						mtop,mbottom,mleft,mright,
						fontFamily,fontSize,fontLocation,fontWeight,
						fontFamily_com,fontSize_com,fontLocation_com,fontWeight_com,saveOrUpdate);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		renderText(SUCCESS);

	}

	/**
	 * 通过问卷ID查询关系表里的题信息
	 */
	public String seleByid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String myAsk = request.getParameter("myAsk");
		List<Map<Object, Object>> seleByid = khzsQuestionAllService.seleByid(myAsk);
		this.vuelist = seleByid;
		System.out.println("seleByid=="+seleByid);
		return "list";

	}
	
	
	/**
	 * 通过问卷ID查询问卷详细信息
	 */
	public String seleDetailByid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String myAsk = request.getParameter("myAsk");
		List<Map<Object, Object>> seleByid = khzsQuestionAllService.seleDetailByid(myAsk);
		this.vuelist = seleByid;
		return "list";

	}
	
	
	/**
	 * 通过问卷ID查询问卷所有的其他设置
	 */
	public String seleOtherSetByid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String myAsk = request.getParameter("myAsk");
		List<Map<Object, Object>> OtherSet = khzsQuestionnaireStyleService.getOtherSet(myAsk);
		this.vuelist = OtherSet;
		return "list";

	}

	public String receiveData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cheID = request.getParameter("cheID");
		List<Map<Object, Object>> list = khzsQuestionService.selectQuestions(cheID);
		System.out.println("receiveData===="+list.toString());
		this.vuelist = list;
		return "list";
	}
	
	public String receiveDataWJ() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cheID = request.getParameter("cheID");
		List<Map<Object, Object>> list = khzsQuestionAllService.selectQuestionsByWJ(cheID);
		System.out.println("receiveDataWJ===="+list.toString());
		this.vuelist = list;
		return "list";
	}
	
	public String receiveDataChoose() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cheID = request.getParameter("myparam");
		List<Map<Object, Object>> list = null;
		this.vuelist = list;
		return "list";
	}
	
	
	/**
	 * 问卷ID加密解密
	 */
	public void getDESUtil(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String wjid = request.getParameter("wjid");
		String isen = request.getParameter("isen");
		String returnid="";
		switch (isen) {
		case "1":
			//加密
			returnid = DESUtil.encrypt("sxit_zhdy", wjid);
			break;
		case "2":
			//解密
			returnid = DESUtil.decrypt("sxit_zhdy", wjid);
			break;

		default:
			break;
		}
		this.renderText(returnid);

	}
	
	

	

}
