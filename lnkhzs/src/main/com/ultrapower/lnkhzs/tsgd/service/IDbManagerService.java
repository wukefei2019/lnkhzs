package com.ultrapower.lnkhzs.tsgd.service;

import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;

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
public interface IDbManagerService {

	public DbManager queryDbManager(String getId);

	public boolean isExist(DbManager DbManager);

	public boolean save(DbManager DbManager);

	public DbManager getUserByDepid(String depid);

	public boolean delete(DbManager dbmanager);



	

}
