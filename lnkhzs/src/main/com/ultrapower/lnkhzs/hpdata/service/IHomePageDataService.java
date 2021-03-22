package com.ultrapower.lnkhzs.hpdata.service;

import java.util.List;
import java.util.Map;

import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealJobdata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealPlandata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsResendDeal;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface IHomePageDataService {

	List<Map<String, String>> ajaxGetComplaintsAmount();

	List<Map<String, String>> ajaxGetResendList();

	List<Map<String, String>> ajaxGetTagTOP5Amount(String title);

	List<Map<String, String>> ajaxGetAnswerAmount();

	List<Map<String, String>> ajaxGetSourceAmount();

	List<Map<String, String>> ajaxGetKHZSAmount();

	List<Map<String, String>> ajaxGetSourceAmountNew();

}
