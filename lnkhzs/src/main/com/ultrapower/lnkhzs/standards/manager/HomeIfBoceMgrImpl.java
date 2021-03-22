package com.ultrapower.lnkhzs.standards.manager;

import com.ultrapower.lnkhzs.standards.model.HomeIfBoce;
import com.ultrapower.lnkhzs.standards.service.IHomeIfBoceService;
import com.ultrapower.eoms.common.core.dao.IDao;

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
public class HomeIfBoceMgrImpl implements IHomeIfBoceService {

    private IDao<HomeIfBoce> homeIfBoceDao;

    public void setHomeIfBoceDao(IDao<HomeIfBoce> homeIfBoceDao) {
        this.homeIfBoceDao = homeIfBoceDao;
    }


}
