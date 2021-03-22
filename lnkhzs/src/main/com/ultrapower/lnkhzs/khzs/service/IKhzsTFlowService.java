package com.ultrapower.lnkhzs.khzs.service;

import java.util.List;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.khzs.model.KhzsTComment;
import com.ultrapower.lnkhzs.khzs.model.KhzsTFlow;
import com.ultrapower.lnkhzs.khzs.model.KhzsTMain;

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
public interface IKhzsTFlowService {

	void saveMain(KhzsTMain khzsTMain);

	void saveFlow(KhzsTFlow khzsTFlow);

	UserSession getUserAdmin();

	void saveMainBak(KhzsTMain khzsTMain);
	
	UserSession getDepAdmin(String depid);

	KhzsTMain getKhzsTMain(String pid);

	void deleteKhzsTMain(String pid);

	List<KhzsTFlow> getKhzsTFlowList(String pid);

	List<KhzsTComment> getKhzsTCommentList(String pid);

	void saveKhzsTComment(KhzsTComment khzsTComment);

	String getIndex(String type);

	void reSetIndex();

	UserSession getUserAdmin(String type);

	void sendSms(UserSession user, String msg);
}
