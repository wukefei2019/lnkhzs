package com.ultrapower.lnkhzs.survey.service;

import java.util.List;
import java.util.Map;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAnnounce;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [问卷_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface IKhzsQuestionAnnounceService {

	public void delAnnounce(String id);
	
	public KhzsQuestionAnnounce queryKhzsQuestionAnnounce(String id);

	void editAnnounce(KhzsQuestionAnnounce khzsQuestionAnnounce);

	public boolean save(KhzsQuestionAnnounce khzsQuestionAnnounce);

	void addAnnonce(String id, KhzsQuestionAnnounce khzsQuestionAnnounce);

	public boolean isExist(KhzsQuestionAnnounce khzsQuestionAnnounce);



	

	

	

	


	
	
	
	
}
