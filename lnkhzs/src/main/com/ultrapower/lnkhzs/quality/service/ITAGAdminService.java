package com.ultrapower.lnkhzs.quality.service;

import com.ultrapower.lnkhzs.quality.model.TAGAdmin;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *标签
 *--------------------------------------------------------------
 */
public interface ITAGAdminService {

	TAGAdmin queryTAGApproval(String id);

	void updateTagApproval(TAGAdmin tAGAdmin);

	
	
}
