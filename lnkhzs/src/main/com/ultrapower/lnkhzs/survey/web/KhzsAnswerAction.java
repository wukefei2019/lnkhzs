package com.ultrapower.lnkhzs.survey.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.survey.model.KhzsAnswer;
import com.ultrapower.lnkhzs.survey.service.IKhzsAnswerService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [答卷_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsAnswerAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private IKhzsAnswerService khzsAnswerService;
    
    private KhzsAnswer khzsAnswer;
    
    
    
    public KhzsAnswer getKhzsAnswer() {
		return khzsAnswer;
	}

	public void setKhzsAnswer(KhzsAnswer khzsAnswer) {
		this.khzsAnswer = khzsAnswer;
	}

	private String acswerListStr;

    public String getAcswerListStr() {
		return acswerListStr;
	}

	public void setAcswerListStr(String acswerListStr) {
		this.acswerListStr = acswerListStr;
	}

	public void setKhzsAnswerService(IKhzsAnswerService khzsAnswerService) {
        this.khzsAnswerService = khzsAnswerService;
    }
	
	/**
	 * 问卷答案
	 */
	public void ajaxSaveQuestionAnswer() {
		List<KhzsAnswer> answerList = JSON.parseArray(acswerListStr, KhzsAnswer.class);
		HttpServletRequest request = ServletActionContext.getRequest();
		String questionnaireid = request.getParameter("id");
		String userid = request.getParameter("userid");
		//UserSession user = super.getUserSession();
		//String userid=user.getLoginName();//保存到answer表 标识符
		
		khzsAnswerService.saveQuestionAnswer(answerList,questionnaireid,userid);
		renderJson("success");
	}
	
	public void ajaxGetQuestionAnswerById(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String questionnaireid = request.getParameter("id");
		String userid = request.getParameter("userid");
		System.out.println("用户加密后的userid"+userid);
		/*try {
			System.out.println("1----"+userid);
			userid=URLDecoder.decode(userid, "UTF-8");
			System.out.println("2----"+userid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		String returnBack=khzsAnswerService.ajaxGetQuestionAnswerById(questionnaireid,userid);
		renderJson(returnBack);
	}
	
	
}
