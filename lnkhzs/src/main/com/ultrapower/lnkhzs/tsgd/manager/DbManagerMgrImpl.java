package com.ultrapower.lnkhzs.tsgd.manager;


import java.util.List;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;
import com.ultrapower.lnkhzs.tsgd.service.IDbManagerService;


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
public class DbManagerMgrImpl implements IDbManagerService {

	private IDao<DbManager> dbmanagerDao;
	
	private IDao<DepInfo> depManagerDao;
	
	

	public IDao<DepInfo> getDepManagerDao() {
		return depManagerDao;
	}

	public void setDepManagerDao(IDao<DepInfo> depManagerDao) {
		this.depManagerDao = depManagerDao;
	}

	public IDao<DbManager> getDbmanagerDao() {
		return dbmanagerDao;
	}

	public void setDbmanagerDao(IDao<DbManager> dbmanagerDao) {
		this.dbmanagerDao = dbmanagerDao;
	}

	@Override
	public boolean save(DbManager dbManager) {
		DepInfo depInfo = depManagerDao.get(dbManager.getCityid());
		dbManager.setCityname(depInfo.getDepname());
		List<DbManager> findL = depManagerDao.find("FROM DbManager WHERE cityid = '"+dbManager.getCityid()+"'");
		if(findL.size()==0||findL.get(0).getAdministratorid()!=dbManager.getAdministratorid()){
			if(dbManager.getId()==null||dbManager.getId().isEmpty()){
				//新增
				dbManager.setId(UUIDGenerator.getUUIDoffSpace());
				dbmanagerDao.save(dbManager);
			}else{
				//修改
				dbmanagerDao.saveOrUpdate(dbManager);
			}
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public DbManager queryDbManager(String getid) {
		return dbmanagerDao.get(getid);
	}

	@Override
	public boolean isExist(DbManager DbManager) {
		DbManager demo=dbmanagerDao.get(DbManager.getId());
		if(demo==null)
			return true;//新增
		else
			return false;//修改
	}

	@Override
	public DbManager getUserByDepid(String depid) {
		List<DbManager> findL = depManagerDao.find("FROM DbManager WHERE cityid = '"+depid+"'");
		DbManager dbManager=new DbManager();
		if(findL.size()>0)
			dbManager=findL.get(0);
		return dbManager;
	}

	@Override
	public boolean delete(DbManager dbmanager) {
		try {
			dbmanagerDao.removeById(dbmanager.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		
	}


	

	

}
