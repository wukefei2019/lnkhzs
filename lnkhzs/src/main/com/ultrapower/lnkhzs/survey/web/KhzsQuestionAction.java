package com.ultrapower.lnkhzs.survey.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTFlowService;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionBean;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionService;
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

public final class KhzsQuestionAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	private IKhzsQuestionService khzsQuestionService;

	private KhzsQuestion khzsQuestion;

	private IKhzsTFlowService khzsTFlowService;
	
	private IKhzsQuestionnaireStyleService khzsQuestionnaireStyleService;

	private List<Map<Object, Object>> vuelist;
	
	private String nDate;
	
	private String area_name;
	
	private String year;
	
	private String sqlName;
	
	private String optionL;
	
	private String optionC;
	
	
	
	
	
	

	public String getOptionL() {
		return optionL;
	}

	public void setOptionL(String optionL) {
		this.optionL = optionL;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getnDate() {
		return nDate;
	}

	public void setnDate(String nDate) {
		this.nDate = nDate;
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
	
	

	public IKhzsQuestionnaireStyleService getKhzsQuestionnaireStyleService() {
		return khzsQuestionnaireStyleService;
	}

	public void setKhzsQuestionnaireStyleService(IKhzsQuestionnaireStyleService khzsQuestionnaireStyleService) {
		this.khzsQuestionnaireStyleService = khzsQuestionnaireStyleService;
	}

	/**
	 * 
	 */
	public void doAction() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String myquestion = request.getParameter("myquestion");
			String myAsk = request.getParameter("myAsk");
			khzsQuestionService.addQuestionAndShip(myquestion, myAsk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderText(SUCCESS);

	}

	/**
	 * 删除调研题库
	 */
	public void delDytk() {
		khzsQuestionService.delDytk(khzsQuestion.getId());
		renderText(SUCCESS);
	}

	/**
	 * 修改调研题库
	 */
	public void editDytk() {
		UserSession user = super.getUserSession();
		//khzsQuestion.setCreateby(user.getPid());
		if(khzsQuestion.getType().equals("矩阵填空")){
			khzsQuestion.setOptiona(optionL.replace(",,,,", "#*#*#*"));
			khzsQuestion.setOptionb(optionC.replace(",,,,", "#*#*#*"));
		}
		khzsQuestion.setCreateby(user.getLoginName());
		khzsQuestion.setCreatebyname(user.getFullName());
		khzsQuestionService.editDytk(khzsQuestion);
		renderText(SUCCESS);
	}
	
	/**
	 * 插入多条问题
	 */
	public void addManyDytk(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids=request.getParameter("cheID");
		UserSession user = super.getUserSession();
		//khzsQuestion.setCreateby(user.getPid());
		khzsQuestion.setCreateby(user.getLoginName());
		khzsQuestion.setCreatebyname(user.getFullName());
		khzsQuestionService.addManyDytk(ids,khzsQuestion);
		renderText(SUCCESS);
	}

	/**
	 * 跳转调研题库
	 */
	/*public String toEditDytk() {
		khzsQuestion = khzsQuestionService.queryKhzsQuestion(khzsQuestion.getId());
		renderJson(khzsQuestion);
		return INPUT;
	}*/
	
	public void toEditDytk() {
	  List<Map<Object,Object>> queryKhzsQuestion = khzsQuestionService.queryKhzsQuestion(khzsQuestion.getId());
		renderJson(queryKhzsQuestion);
	}

	/**
	 * 调查问卷
	 */
	public void ajaxGetQuestionInNaire() {
		List<KhzsQuestionBean> list = khzsQuestionService.getQuestionnaire(id,nDate);
		renderJson(list);
	}
	
	/**
	 * 获取调查问卷的样式
	 */
	public void ajaxGetQuestionOtherSet(){
		Map<Object,Object> map=khzsQuestionnaireStyleService.getOtherSetToShow(id);
		renderJson(map);
	}
	//问卷调查统计
	public void comYZ() {
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("AREA_NAME", area_name);
		/*param.put("acpt_time", month);*/
		param.put("acpt_time", year);
		RQuery rq = new RQuery(sqlName, param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		System.out.println(dt.getRowList());
		renderJson(dt.getRowList());
		
	}
	
	/**
	 * 调查问卷
	 */
	public void ajaxGetYCT() {
		String ajaxGetYCT = khzsQuestionService.ajaxGetYCT(id);
		renderJson(ajaxGetYCT);
	}

	/**
	 * 通过问卷ID查询关系表里的题信息
	 */
	public String seleByid() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String myAsk = request.getParameter("myAsk");
		List<Map<Object, Object>> seleByid = khzsQuestionService.seleByid(myAsk);
		this.vuelist = seleByid;
		return "list";
		
	}
	
	public void receiveData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cheID = request.getParameter("cheID");
		//List<Map<String,Object>> list=khzsQuestionService.selectQuestions(cheID);
		renderText(SUCCESS);
	}
	
}
