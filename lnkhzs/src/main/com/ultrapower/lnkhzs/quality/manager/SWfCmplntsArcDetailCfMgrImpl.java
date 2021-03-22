package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailCf;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailCfService;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [非投诉结单归档量明细_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsArcDetailCfMgrImpl implements ISWfCmplntsArcDetailCfService {

    private IDao<SWfCmplntsArcDetailCf> sWfCmplntsArcDetailCfDao;

	public void setsWfCmplntsArcDetailCfDao(IDao<SWfCmplntsArcDetailCf> sWfCmplntsArcDetailCfDao) {
		this.sWfCmplntsArcDetailCfDao = sWfCmplntsArcDetailCfDao;
	}

	@Override
	public SWfCmplntsArcDetailCf getArcDetailById(String wrkfmShowSwftno) {
		return sWfCmplntsArcDetailCfDao.findUnique("from SWfCmplntsArcDetailCf where wrkfmShowSwftno = ?", wrkfmShowSwftno);
	}

	@Override
	public SWfCmplntsArcDetailCf getEndTime() {
		QueryAdapter q = new QueryAdapter();
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_CF  ";
		SWfCmplntsArcDetailCf sWfCmplntsArcDetailCf=new SWfCmplntsArcDetailCf();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			sWfCmplntsArcDetailCf.setAcptTime(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return sWfCmplntsArcDetailCf;
	}


}
