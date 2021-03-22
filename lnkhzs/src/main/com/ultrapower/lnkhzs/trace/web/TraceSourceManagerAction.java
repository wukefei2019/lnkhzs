package com.ultrapower.lnkhzs.trace.web;


import com.ultrapower.lnkhzs.trace.model.TraceManager;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceManagerService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [溯源问题清单_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TraceSourceManagerAction extends BaseAction {

    private static final long serialVersionUID = -1L;
 
    private  ITraceSourceManagerService traceSourceManagerService;

    private TraceManager traceManager;

	public ITraceSourceManagerService getTraceSourceManagerService() {
		return traceSourceManagerService;
	}

	public void setTraceSourceManagerService(ITraceSourceManagerService traceSourceManagerService) {
		this.traceSourceManagerService = traceSourceManagerService;
	}

	public TraceManager getTraceManager() {
		return traceManager;
	}

	public void setTraceManager(TraceManager traceManager) {
		this.traceManager = traceManager;
	}
    
	public void saveManager(){
		try {
			if(traceSourceManagerService.isExist(traceManager)){
				traceSourceManagerService.save(traceManager);
				this.renderText("success");
			}else{
				this.renderText("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void deleteManager(){
		traceSourceManagerService.deleteManager(traceManager.getPid());
		this.renderText("success");
	}
    
}
