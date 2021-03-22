package com.ultrapower.lnkhzs.survey.manager;


import java.util.List;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;


/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [题库_接口]
 * </p>
 * 
 * @author : lxd
 * @date : 2019/7/23
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsSurverManagerMgrImpl implements IKhzsSurverManagerService {

	private IDao<KhzsSurverManager> khzssurvermanagerDao;
	
	private IDao<DepInfo> depManagerDao;
	
	

	public IDao<DepInfo> getDepManagerDao() {
		return depManagerDao;
	}

	public void setDepManagerDao(IDao<DepInfo> depManagerDao) {
		this.depManagerDao = depManagerDao;
	}

	public IDao<KhzsSurverManager> getKhzssurvermanagerDao() {
		return khzssurvermanagerDao;
	}

	public void setKhzssurvermanagerDao(IDao<KhzsSurverManager> khzssurvermanagerDao) {
		this.khzssurvermanagerDao = khzssurvermanagerDao;
	}

	@Override
	public boolean save(KhzsSurverManager khzsSurverManager) {
		DepInfo depInfo = depManagerDao.get(khzsSurverManager.getCityid());
		khzsSurverManager.setCityname(depInfo.getDepname());
		List<KhzsSurverManager> findL = depManagerDao.find("FROM KhzsSurverManager WHERE cityid = '"+khzsSurverManager.getCityid()+"'");
		if(findL.size()==0||findL.get(0).getAdministratorid()!=khzsSurverManager.getAdministratorid()){
			if(khzsSurverManager.getId()==null||khzsSurverManager.getId().isEmpty()){
				//新增
				khzsSurverManager.setId(UUIDGenerator.getUUIDoffSpace());
				khzssurvermanagerDao.save(khzsSurverManager);
			}else{
				//修改
				khzssurvermanagerDao.saveOrUpdate(khzsSurverManager);
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public KhzsSurverManager queryKhzsSurverManager(String getid) {
		return khzssurvermanagerDao.get(getid);
	}

	@Override
	public boolean isExist(KhzsSurverManager KhzsSurverManager) {
		KhzsSurverManager demo=khzssurvermanagerDao.get(KhzsSurverManager.getId());
		if(demo==null)
			return true;//新增
		else
			return false;//修改
	}

	@Override
	public KhzsSurverManager getUserByDepid(String depid) {
		List<KhzsSurverManager> findL = depManagerDao.find("FROM KhzsSurverManager WHERE cityid = '"+depid+"'");
		KhzsSurverManager khzsSurverManager=new KhzsSurverManager();
		if(findL.size()>0)
			khzsSurverManager=findL.get(0);
		return khzsSurverManager;
	}


	

	

}
