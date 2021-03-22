package com.ultrapower.lnkhzs.standards.web;

import com.ultrapower.lnkhzs.standards.service.IHomeIfBoceService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class HomeIfBoceAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private IHomeIfBoceService homeIfBoceService;

    public void setHomeIfBoceService(IHomeIfBoceService homeIfBoceService) {
        this.homeIfBoceService = homeIfBoceService;
    }


}
