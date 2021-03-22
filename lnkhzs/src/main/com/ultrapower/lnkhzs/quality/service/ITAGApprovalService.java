package com.ultrapower.lnkhzs.quality.service;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.TAGInfo;

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
public interface ITAGApprovalService {

	TAGInfo queryTAGApproval(String id);
	
	SWfCmplntsArcDetail selectByGDH(String workNumber);
	
	void addTag(TAGInfo tAGInfo);

	void updateTagApproval(TAGInfo tAGApproval);

	void deleteTag(String id);

	TAGInfo queryTagDetail(String id);

	void submitTag(TAGInfo tAGApproval);

	
	
}
