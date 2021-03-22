package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.service.IKhzsRelationshipService;

import org.hibernate.Query;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [问卷题库关系表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsRelationshipMgrImpl implements IKhzsRelationshipService {
	private IDao<KhzsRelationship> khzsRelationshipDao;
	
    private IKhzsQuestionnaireService khzsQuestionnaireService;
    
	public void setKhzsQuestionnaireService(IKhzsQuestionnaireService khzsQuestionnaireService) {
		this.khzsQuestionnaireService = khzsQuestionnaireService;
	}


    public void setKhzsRelationshipDao(IDao<KhzsRelationship> khzsRelationshipDao) {
        this.khzsRelationshipDao = khzsRelationshipDao;
    }

	@Override
	public void deleteDywj(String id) {
		// TODO Auto-generated method stub
		/*String hsql = "delete ZL_KHZS_RELATIONSHIP  where QUESTIONNAIREID = ? ";
		
		Query createSQLQuery = khzsRelationshipDao.createSQLQuery(hsql);
		createSQLQuery.setString(0, id);
		int executeUpdate = createSQLQuery.executeUpdate();*/
	}

	@Override
	public void saveList(String cheID, String wjId) {
		// TODO Auto-generated method stub
		KhzsRelationship khzsRelationship = new KhzsRelationship();
		
		//查询问卷
    	KhzsQuestionnaire KhzsQuestionnaire = khzsQuestionnaireService.getKhzsTMain(wjId);
    	//问卷号
    	String id = KhzsQuestionnaire.getId();
    	//问卷名
    	String name = KhzsQuestionnaire.getName();
    	//问卷时间
    	String creattime = KhzsQuestionnaire.getCreattime();
    	
    	String[] array = cheID.split(",,");
    	//遍历题号
    	for(int i = 0;i<array.length;i++){
    		khzsRelationship.setId(UUIDGenerator.getUUIDoffSpace());
    		khzsRelationship.setQuestionnaireid(id);
    		khzsRelationship.setQuestionid(array[i]);
    		khzsRelationship.setName(name);
    		khzsRelationship.setCreattime(creattime);
    		khzsRelationshipDao.save(khzsRelationship);
    	}
		
	}


}
