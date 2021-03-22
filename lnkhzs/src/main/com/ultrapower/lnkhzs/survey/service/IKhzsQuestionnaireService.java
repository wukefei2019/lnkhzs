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
public interface IKhzsQuestionnaireService {
	
	//新增
	void saveMainBak(String myAsk,String myName);
	//通过id查询问卷
	KhzsQuestionnaire getKhzsTMain(String id);
	
	void deleteDywj(String id);
	
	boolean updateDywj(KhzsQuestionnaire KhzsQuestionnaire);
	
	void publish(String resultPid,String id,BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx,int peopleNum,int countNum);
	
	void saveSelect(String id,BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx);
	
	List<Map<Object, Object>> seleAnswer(String id);
	String exportExcel(String id);
	
	void saveMainBak(String myAsk, String myName, String userid, String username, String ispublic, String myNameSub, String isreward);
	
	Map<Object,Object> getTotalSendNum(BindDicselect bindDicselect, BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx,BindDicSelectInfo bindDicSelectInfo);
	
	String publishTep1(int countNum,String id, BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,
			BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx);
	
	List<DataRow> getCountyByCity(String cityid);
	
	Workbook checkVali(File xls);
	
	void importXls(Workbook wb, String projectId,String fileName) throws Exception;
	
	String getFileNum(Workbook wb);
	
	String getInterface(int countNum,String khzsQuestionnaireId,String rule,String daynum,String message_info);
	
	
}
