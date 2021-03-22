package com.ultrapower.lnkhzs.base.service;

import java.util.List;

import com.ultrapower.omcs.common.model.ICommonModel;

/**
 * Created on 2017年2月18日
 * <p>Title      : 辽宁移动网关中心平台_[子系统统名]_[模块名]</p>
 * <p>Description: [请假信息表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface IBaseService {
    
    void saveBatch(List entity) throws Exception;
    
    void save(ICommonModel entity) throws Exception;
    
    void update(ICommonModel entity) throws Exception;
    
    void delete(ICommonModel entity)  throws Exception;
    
    ICommonModel getEntity(String pid);

    List<? extends ICommonModel> findByExample(ICommonModel example);
}
