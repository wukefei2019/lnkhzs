package com.ultrapower.lnkhzs.survey.service;

import com.ultrapower.lnkhzs.survey.model.KhzsSurverManager;

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
public interface IKhzsSurverManagerService {

	public KhzsSurverManager queryKhzsSurverManager(String getId);

	public boolean isExist(KhzsSurverManager KhzsSurverManager);

	public boolean save(KhzsSurverManager KhzsSurverManager);

	public KhzsSurverManager getUserByDepid(String depid);



	

}
