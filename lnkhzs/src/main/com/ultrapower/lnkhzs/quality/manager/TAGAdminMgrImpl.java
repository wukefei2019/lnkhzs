package com.ultrapower.lnkhzs.quality.manager;


import com.ultrapower.lnkhzs.quality.model.TAGAdmin;
import com.ultrapower.lnkhzs.quality.service.ITAGAdminService;

import java.util.Date;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;

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
public class TAGAdminMgrImpl implements ITAGAdminService {

    private IDao<TAGAdmin> tAGAdminDao;
    

	public void settAGAdminDao(IDao<TAGAdmin> tAGAdminDao) {
		this.tAGAdminDao = tAGAdminDao;
	}


	@Override
	public TAGAdmin queryTAGApproval(String id) {
		return tAGAdminDao.get(id);
	}


	@Override
	public void updateTagApproval(TAGAdmin tAGAdmin) {
		if (StringUtils.isBlank(tAGAdmin.getId())) {
			tAGAdmin.setId(UUIDGenerator.getUUIDoffSpace());
			tAGAdmin.setChangetime(TimeUtils.formatDateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			tAGAdminDao.save(tAGAdmin);
		} else {
			tAGAdmin.setChangetime(TimeUtils.formatDateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			tAGAdminDao.saveOrUpdate(tAGAdmin);
		}
	}
    
	

}
