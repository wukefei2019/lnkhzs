package com.ultrapower.eoms.ultrasm.service;

import com.ultrapower.eoms.ultrasm.model.InterceptConfig;
import com.ultrapower.eoms.ultrasm.model.InterceptConfigLog;

public interface InterceptConfigService {

	/**
	 * 将拦截配置信息添加到缓存
	 * @return
	 */
	public boolean cacheInterceptConfig();
	
	/**
	 * 保存拦截配置信息
	 * @param config
	 * @return
	 */
	public String addInterceptConfig(InterceptConfig config);
	
	/**
	 * 获取ID保存拦截信息
	 * @param pid
	 * @return
	 */
	public InterceptConfig getInterceptConfig(String pid);
	
	/**
	 *根据ID集合 删除拦截配置信息
	 * @param pids
	 * @return
	 */
	public String  delInterceptConfig(String pids);
	
	
	public void saveInterceptConfigLog(InterceptConfigLog log);
	
	
	public void refurbish();
}
