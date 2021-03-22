package com.ultrapower.lnkhzs.quality.web;



import org.junit.Test;

import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.lnkhzs.quality.model.TAGInfo;
import com.ultrapower.lnkhzs.quality.service.ITAGReportService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [投诉结单归档量明细_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TAGReportAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private ITAGReportService tAGReportService;

    private TAGInfo tAGInfo;

	public ITAGReportService gettAGReportService() {
		return tAGReportService;
	}

	public void settAGReportService(ITAGReportService tAGReportService) {
		this.tAGReportService = tAGReportService;
	}

	public TAGInfo gettAGInfo() {
		return tAGInfo;
	}

	public void settAGInfo(TAGInfo tAGInfo) {
		this.tAGInfo = tAGInfo;
	}

	

	
}
