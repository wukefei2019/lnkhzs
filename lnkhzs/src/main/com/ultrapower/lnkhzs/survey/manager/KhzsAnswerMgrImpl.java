package com.ultrapower.lnkhzs.survey.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.lnkhzs.survey.model.KhzsAnswer;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsAnswerService;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [答卷_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsAnswerMgrImpl implements IKhzsAnswerService {

    private IDao<KhzsAnswer> khzsAnswerDao;
    
    private IDao<KhzsRelationship> khzsRelationshipDao;

    public void setKhzsAnswerDao(IDao<KhzsAnswer> khzsAnswerDao) {
        this.khzsAnswerDao = khzsAnswerDao;
    }

	public void setKhzsRelationshipDao(IDao<KhzsRelationship> khzsRelationshipDao) {
		this.khzsRelationshipDao = khzsRelationshipDao;
	}
	
	public IDao<KhzsRelationship> getKhzsRelationshipDao() {
		return khzsRelationshipDao;
	}

	public void saveQuestionAnswer(List<KhzsAnswer> answerList,String questionnaireid,String userid) {
    	String answertime =TimeUtils.formatDateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
		
    	for(KhzsAnswer khzsAnswer:answerList) {
    		List<KhzsRelationship> find = khzsRelationshipDao.find(
        			"from KhzsRelationship where questionnaireid = ? and ORDERS = ?", 
        			questionnaireid,Integer.parseInt(khzsAnswer.getRelationid())+1);
    		if(find.size()>0){
    			khzsAnswer.setRelationid(find.get(0).getId());
    		}
    		khzsAnswer.setId(UUIDGenerator.getUUIDoffSpace());
    		khzsAnswer.setAnswertime(answertime);
    		khzsAnswer.setQuestionnaireid(questionnaireid);
    		khzsAnswer.setUserid(userid);
    		khzsAnswerDao.save(khzsAnswer);
    	}
    }
	
	
	public String numberToLetter(int num) {
		if (num <= 0) {
			return null;
		}
		String letter = "";
		num--;
		do {
			if (letter.length() > 0) {
				num--;
			}
			letter = ((char) (num % 26 + (int) 'A')) + letter;
			num = (int) ((num - num % 26) / 26);
		} while (num > 0);
		return letter;
	}

	@Override
	public String ajaxGetQuestionAnswerById(String questionnaireid, String userid) {
		List<KhzsAnswer> find = khzsAnswerDao.find(
    			"from KhzsAnswer where questionnaireid = ? and userid = ?", 
    			questionnaireid,userid);
		if(find.size()>0){
			return "success";
		}
		return "false";
	}

}
