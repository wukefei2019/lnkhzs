package com.ultrapower.lnkhzs.survey.service;

import java.util.List;
import java.util.Map;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_接口]
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
public interface IKhzsQuestionAllService {

	void addQuestionAndShip(String myquestion, String myAsk);

	List<Map<Object, Object>> seleByid(String id);

	List<Map<Object, Object>> selectQuestions(String cheID);

	List<Map<Object, Object>> selectQuestionsByWJ(String cheID);

	List<Map<Object, Object>> seleDetailByid(String myAsk);

	
}
