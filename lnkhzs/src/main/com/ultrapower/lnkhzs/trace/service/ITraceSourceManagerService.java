package com.ultrapower.lnkhzs.trace.service;

import com.ultrapower.lnkhzs.trace.model.TraceManager;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [溯源问题清单_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface ITraceSourceManagerService {

	public boolean isExist(TraceManager traceManager);

	public void save(TraceManager traceManager);

	public void deleteManager(String depevel2id);

}
