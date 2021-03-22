package com.ultrapower.lnkhzs.quality.service;

import java.util.Map;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [投诉结单归档量明细_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface ISWfCmplntsArcDetailService {
	
	SWfCmplntsArcDetail getArcDetailById(String wrkfmShowSwftno);

	SWfCmplntsArcDetail getEndTime();
	
	Map<Object, Object> otherGetThan(String time,String lable,String mg);
	
}
