package com.ultrapower.eoms.common.portal.web;

import java.lang.reflect.Field;

import org.apache.catalina.Manager;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.core.web.BaseAction;


/**
 * 该管理器用来管理登录及首页
 *
 * @author SunHailong
 * @version UltrPower-Eoms4.0-0.1
 */
public class PortalAction extends BaseAction {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void getEntriesNumber(){
		int entriesNumber = 0;
		ActionContext ctx = ActionContext.getContext();
		RequestFacade request = (RequestFacade) ctx.get("com.ultrapower.HttpServletRequest");
    	try {
    		if (request instanceof RequestFacade) {
    			Field requestField = request.getClass().getDeclaredField("request");
    			requestField.setAccessible(true);
    			Request req = (Request) requestField.get(request);
    			org.apache.catalina.Context context = req.getContext();
    			Manager manager = context.getManager();
    			entriesNumber = manager.getActiveSessions();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
		log.info("users:{}", entriesNumber);
		renderText("在线人数："+entriesNumber);
	}
}
