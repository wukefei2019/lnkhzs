package com.ultrapower.lnkhzs.quality.manager;


import com.ultrapower.lnkhzs.quality.model.TAGInfo;
import com.ultrapower.lnkhzs.quality.service.ITAGReportService;
import com.ultrapower.eoms.common.core.dao.IDao;

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
public class TAGReportMgrImpl implements ITAGReportService {

	private IDao<TAGInfo> tAGInfoDao;

	public void settAGInfoDao(IDao<TAGInfo> tAGInfoDao) {
		this.tAGInfoDao = tAGInfoDao;
	}


}
