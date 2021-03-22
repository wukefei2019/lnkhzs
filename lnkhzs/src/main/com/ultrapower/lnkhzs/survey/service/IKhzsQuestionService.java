package com.ultrapower.lnkhzs.survey.service;

import java.util.List;
import java.util.Map;

import java.util.List;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionBean;

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
public interface IKhzsQuestionService {

	/**
	 * 删除题库
	 * 
	 * @param id 题号
	 */
	void delDytk(String id);

	/**
	 * 修改或添加题库
	 * 
	 * @param khzsQuestion
	 * @return 判断是否失败
	 */
	void editDytk(KhzsQuestion khzsQuestion);

	/**
	 * 查询题库
	 * 
	 * @param id 题号
	 * @return 
	 */
	List<Map<Object, Object>> queryKhzsQuestion(String id);

	
	List<KhzsQuestionBean> getQuestionnaire(String id,String nDate);
	
	String ajaxGetYCT(String id);


	/**
	 * 修改单选题
	 * @param t_danxuan
	 * @param i_danxuan
	 */
	void dealDanXuan(String t_danxuan, String i_danxuan);

	/**
	 * 修改多选题
	 * @param t_duoxuan
	 * @param i_duoxuan
	 */
	void dealDuoXuan(String t_duoxuan, String i_duoxuan);

	/**
	 * 修改简答题
	 * @param t_jianda
	 * @param i_jianda
	 */
	void dealJianDa(String t_jianda, String i_jianda);

	/**
	 * 修改是非题
	 * @param t_shifei
	 * @param i_shifei
	 */
	void dealShiFei(String t_shifei, String i_shifei);

	void addQuestionAndShip(String myquestion, String myAsk);
	
	List<Map<Object, Object>> seleByid(String id);

	List<Map<Object, Object>> selectQuestions(String cheID);

	void addManyDytk(String ids, KhzsQuestion khzsQuestion);
	

}
