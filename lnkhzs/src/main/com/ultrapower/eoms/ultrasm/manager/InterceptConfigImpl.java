package com.ultrapower.eoms.ultrasm.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ultrapower.eoms.common.core.component.cache.manager.BaseCacheManager;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.ultrasm.model.InterceptConfig;
import com.ultrapower.eoms.ultrasm.model.InterceptConfigLog;
import com.ultrapower.eoms.ultrasm.service.InterceptConfigService;

public class InterceptConfigImpl implements InterceptConfigService {

	private IDao<InterceptConfig> interceptConfigDao;
	private IDao<InterceptConfigLog> interceptConfigLogDao;
	public static final String INTERCEPT_CACHEKEY = "InterceptList";
	
	public void refurbish(){

	}
	
	
	@Override
	public boolean cacheInterceptConfig() {
		boolean retMsg = true;
		try {
			List<InterceptConfig>  configList = interceptConfigDao.find("select i from InterceptConfig i ", null);
			Map<String, InterceptConfig> configMap = new HashMap<String, InterceptConfig>();
			for(InterceptConfig config : configList){
				configMap.put(config.getUrlpath(),config);
			}
			BaseCacheManager.put(INTERCEPT_CACHEKEY, INTERCEPT_CACHEKEY, configMap);
		} catch (Exception e) {
			e.printStackTrace();
			retMsg = false;
		}
		return retMsg;
	}
	
	public void saveInterceptConfigLog(InterceptConfigLog log){
		interceptConfigLogDao.save(log);
	}
	
	
	@Override
	public String addInterceptConfig(InterceptConfig config) {
		String retMsg = "";
		try {
			if(config != null){
				if("".equals(config.getPid())){
					config.setPid(null);
				}
				interceptConfigDao.saveOrUpdate(config);
			}
			retMsg = "操作成功!";
		} catch (Exception e) {
			e.printStackTrace();
			retMsg = "操作失败!";
		}
		return retMsg;
	}

	

	@Override
	public String delInterceptConfig(String pids) {
		String retMsg = "";
		try {
			if(pids != null && !"".equals(pids)){
				String [] pidArr = pids.split(",");
				for(String pid : pidArr){
					interceptConfigDao.removeById(pid);
				}
			}
			retMsg = "删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			retMsg = "删除失败!";
		}
		return retMsg;
	}

	@Override
	public InterceptConfig getInterceptConfig(String pid) {
		InterceptConfig interceptConfig = null;
		if(pid != null && !"".equals(pid)){
			interceptConfig = interceptConfigDao.get(pid);
		}
		return interceptConfig;
	}
	
	public void setInterceptConfigDao(IDao<InterceptConfig> interceptConfigDao) {
		this.interceptConfigDao = interceptConfigDao;
	}

	public void setInterceptConfigLogDao(
			IDao<InterceptConfigLog> interceptConfigLogDao) {
		this.interceptConfigLogDao = interceptConfigLogDao;
	}
}
