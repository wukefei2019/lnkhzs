package com.ultrapower.lnkhzs.survey.service;



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
public interface IKhzsRelationshipService {
	
	void deleteDywj(String hql);
	
	void saveList(String cheID,String wjId);
	
}
