package com.ultrapower.lnkhzs.survey.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectEx;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;

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
public interface IKhzsQuestionnaireStyleService {

	Boolean SaveOtherSet(String myid,String backgroundList, String mtop, String mbottom, String mleft, String mright,
			String fontFamily, String fontSize, String fontLocation, String fontWeight, String fontFamily_com,
			String fontSize_com, String fontLocation_com, String fontWeight_com, Boolean saveOrUpdate);

	List<Map<Object, Object>> getOtherSet(String myAsk);

	Map<Object, Object> getOtherSetToShow(String id);

	
	
	
	
}
