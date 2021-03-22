package com.ultrapower.lnkhzs.survey.web;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAnnounce;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.service.IBindDicSelectInfoService;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectService;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectSymbolService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAnnounceService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.service.IKhzsRelationshipService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷_WEB]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsQuestionAnnounceAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	private IKhzsQuestionAnnounceService khzsQuestionAnnounceService;

	private KhzsQuestionAnnounce khzsQuestionAnnounce;


	public IKhzsQuestionAnnounceService getKhzsQuestionAnnounceService() {
		return khzsQuestionAnnounceService;
	}

	public void setKhzsQuestionAnnounceService(IKhzsQuestionAnnounceService khzsQuestionAnnounceService) {
		this.khzsQuestionAnnounceService = khzsQuestionAnnounceService;
	}
	
	public KhzsQuestionAnnounce getKhzsQuestionAnnounce() {
		return khzsQuestionAnnounce;
	}

	public void setKhzsQuestionAnnounce(KhzsQuestionAnnounce khzsQuestionAnnounce) {
		this.khzsQuestionAnnounce = khzsQuestionAnnounce;
	}

	
	
	//获取页面详细数据
	public void getAnnounceDetail(){
		khzsQuestionAnnounce =  khzsQuestionAnnounceService.queryKhzsQuestionAnnounce(khzsQuestionAnnounce.getId());
		renderJson(khzsQuestionAnnounce);
	}
	
	
	
	/**
	 * 删除公告消息
	 */
	public void delAnnounce() {
		khzsQuestionAnnounceService.delAnnounce(khzsQuestionAnnounce.getId());
		renderText(SUCCESS);
	}

	
	
	
	public void saveAnnounce(){
		try {
			khzsQuestionAnnounceService.save(khzsQuestionAnnounce);
			this.renderText("success");
			
		} catch (Exception e) {
			e.printStackTrace();
			this.renderText("error");
		}
    }
	
	
	
	
	
	/**
	 * 修改调研题库
	 */
	public void editAnnounce()  {
		UserSession user = super.getUserSession();
		khzsQuestionAnnounceService.editAnnounce(khzsQuestionAnnounce);
		renderText(SUCCESS);
	}
	
	
	
}