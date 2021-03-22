package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsCheckSheetDetail;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailService;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsCheckSheetDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [投诉结单归档量明细_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsCheckSheetDetailMgrImpl implements ISWfCmplntsCheckSheetDetailService {

    private IDao<SWfCmplntsCheckSheetDetail> sWfCmplntsCheckSheetDetailDao;

	public IDao<SWfCmplntsCheckSheetDetail> getsWfCmplntsCheckSheetDetailDao() {
		return sWfCmplntsCheckSheetDetailDao;
	}

	public void setsWfCmplntsCheckSheetDetailDao(IDao<SWfCmplntsCheckSheetDetail> sWfCmplntsCheckSheetDetailDao) {
		this.sWfCmplntsCheckSheetDetailDao = sWfCmplntsCheckSheetDetailDao;
	}

	@Override
	public boolean editCheckSheet(SWfCmplntsCheckSheetDetail sWfCmplntsCheckSheetDetail) {
		if(sWfCmplntsCheckSheetDetail.getId()==null)
			sWfCmplntsCheckSheetDetailDao.save(sWfCmplntsCheckSheetDetail);
		else{
			sWfCmplntsCheckSheetDetail.setId(UUIDGenerator.getUUID());
			sWfCmplntsCheckSheetDetailDao.saveOrUpdate(sWfCmplntsCheckSheetDetail);
		}
		return true;
	}

}
