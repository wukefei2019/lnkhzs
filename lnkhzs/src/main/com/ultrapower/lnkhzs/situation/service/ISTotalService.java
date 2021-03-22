package com.ultrapower.lnkhzs.situation.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public interface ISTotalService {

	List<Map<Object, Object>> getTotalOut(String selecttime);

	List<Map<Object, Object>> getCHBNOut(String selecttime);
	
	List<Map<Object, Object>> getSJ(String selecttime);

	List<Map<Object,Object>>  getFG(String selecttime);
	
	List<Map<Object,Object>>  getFYZYJT(String selecttime);
	
}
