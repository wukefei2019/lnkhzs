package com.ultrapower.lnkhzs.quality.service;

import java.util.List;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNode;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNodeBak;

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
public interface ITagMaintainService {

	UserSession getUserAdmin();

	ViceRequestNode getViceRequestNode(String pid);

	void saveSerReqNode(ViceRequestNode viceRequestNode);

	void saveSerReqNodeBak(ViceRequestNode viceRequestNode, String loginname, String status) throws Exception;

	ViceRequestNodeBak getViceRequestNodeBak(String id);

	void removeSerReqNode(ViceRequestNode viceRequestNode);

	Integer getSenBatchnoSeq();

	void saveSerReqNodeBak(ViceRequestNodeBak viceRequestNodeBak);

	boolean checkRepeat(String[] split, String loginnanme);

	Integer[] getTagStatistics(String a12, String city, String year);
	
	public List<String> getTagList();

	boolean checkNodeRepeat(ViceRequestNode viceRequestNode);
}
