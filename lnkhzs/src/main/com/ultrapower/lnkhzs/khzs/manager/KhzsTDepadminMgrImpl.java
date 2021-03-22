package com.ultrapower.lnkhzs.khzs.manager;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.lnkhzs.khzs.model.KhzsTDepadmin;
import com.ultrapower.lnkhzs.khzs.service.IKhzsTDepadminService;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsTDepadminMgrImpl implements IKhzsTDepadminService{

    private IDao<KhzsTDepadmin> khzsTDepadminDao;
    
	private IDao<DepInfo> depManagerDao;
    
	public void setKhzsTDepadminDao(IDao<KhzsTDepadmin> khzsTDepadminDao) {
		this.khzsTDepadminDao = khzsTDepadminDao;
	}
	
	public void setDepManagerDao(IDao<DepInfo> depManagerDao) {
		this.depManagerDao = depManagerDao;
	}

	@Override
	public void save(KhzsTDepadmin khzsTDepadmin) {
		DepInfo depInfo = depManagerDao.get(khzsTDepadmin.getDepid());
		khzsTDepadmin.setPid(UUIDGenerator.getUUIDoffSpace());
		khzsTDepadmin.setDepfullname(depInfo.getDepfullname());
		khzsTDepadmin.setDepcode(depInfo.getDepcode());
		khzsTDepadminDao.save(khzsTDepadmin);
	}

	@Override
	public boolean isExist(KhzsTDepadmin khzsTDepadmin) {
		return khzsTDepadminDao.find("from KhzsTDepadmin where depid = ?", khzsTDepadmin.getDepid()).size()>=1?false:true;
	}

	@Override
	public void deleteDepAdmin(String depid) {
		KhzsTDepadmin khzsTDepadmin = khzsTDepadminDao.findUnique("from KhzsTDepadmin where depid = ?", depid);
		khzsTDepadminDao.remove(khzsTDepadmin);
	}


}
