package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAll;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionAllHidelist;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAllHidelistService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionAllService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionService;
import com.ultrapower.omcs.utils.DateUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionBean;

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
public class KhzsQuestionAllHidelistMgrImpl implements IKhzsQuestionAllHidelistService {

	private IDao<KhzsQuestionAllHidelist> khzsQuestionAllHidelistDao;

	public IDao<KhzsQuestionAllHidelist> getKhzsQuestionAllHidelistDao() {
		return khzsQuestionAllHidelistDao;
	}

	public void setKhzsQuestionAllHidelistDao(IDao<KhzsQuestionAllHidelist> khzsQuestionAllHidelistDao) {
		this.khzsQuestionAllHidelistDao = khzsQuestionAllHidelistDao;
	}
	
	


}
