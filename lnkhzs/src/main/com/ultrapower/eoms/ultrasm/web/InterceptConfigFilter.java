package com.ultrapower.eoms.ultrasm.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ultrapower.eoms.common.core.component.cache.manager.BaseCacheManager;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.manager.InterceptConfigImpl;
import com.ultrapower.eoms.ultrasm.model.InterceptConfig;
import com.ultrapower.eoms.ultrasm.model.InterceptConfigLog;
import com.ultrapower.eoms.ultrasm.service.InterceptConfigService;

/**
 * @author 凌广
 * @date   2014-04-10
 */
public class InterceptConfigFilter implements Filter
{
	

	
	public void destroy() 
	{ 

	}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) arg0;
		String currentURL=request.getRequestURI();
		String queryUrl = request.getQueryString();
		String targetURL = currentURL.substring(currentURL.indexOf("/", 1),currentURL.length()); //截取到当前文件名用于比较
		Object oj = BaseCacheManager.get(InterceptConfigImpl.INTERCEPT_CACHEKEY, InterceptConfigImpl.INTERCEPT_CACHEKEY);
		if(oj != null){
			Map<String,InterceptConfig> configList = (Map<String,InterceptConfig>)oj;
			InterceptConfig config  = configList.get(targetURL);
			if(config != null){//配置了需要拦截的URL
				InterceptConfigService interceptConfigService  = (InterceptConfigService)WebApplicationManager.getBean("interceptConfigService");
				InterceptConfigLog log =  new InterceptConfigLog();
				log.setFunctionname(config.getFunctionname());
				log.setInterceptpid(config.getPid());
				log.setUrlpath(queryUrl.isEmpty() ? currentURL:currentURL+"?"+queryUrl);
				if(config.getParam1() != null  && !"".equals(config.getParam1())){
					log.setParam1(request.getParameter(config.getParam1()));
				}
				if(config.getParam2() != null  && !"".equals(config.getParam2())){
					log.setParam2(request.getParameter(config.getParam2()));
				}
				if(config.getParam3() != null  && !"".equals(config.getParam3())){
					log.setParam3(request.getParameter(config.getParam3()));
				}
				Object objSession = request.getSession().getAttribute("userSession");
				if(objSession != null){
					UserSession userSession = (UserSession)objSession;
					log.setLoginname(userSession.getLoginName());
				}
				log.setIp(request.getRemoteAddr());
				log.setOperatetime(TimeUtils.getCurrentTime());
				interceptConfigService.saveInterceptConfigLog(log);
			}
		}    
		arg2.doFilter(arg0, arg1);
	}
	
	
	public void init(FilterConfig arg0) throws ServletException 
	{
	}
}
