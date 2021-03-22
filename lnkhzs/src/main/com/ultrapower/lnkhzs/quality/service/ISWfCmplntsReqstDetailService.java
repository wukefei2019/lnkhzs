package com.ultrapower.lnkhzs.quality.service;

import java.text.ParseException;
import java.util.HashMap;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface ISWfCmplntsReqstDetailService {

	SWfCmplntsReqstDetail getReqstDetailById(Integer srvReqstId);

	SWfCmplntsReqstDetail getEndTime();

	String getMaxMinTime(String queryTable, HashMap<String, String> params) throws ParseException;
	
}
