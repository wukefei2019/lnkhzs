package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailXc;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailXcService;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [协查工单结单归档量明细_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class SWfCmplntsArcDetailXcMgrImpl implements ISWfCmplntsArcDetailXcService {

    private IDao<SWfCmplntsArcDetailXc> sWfCmplntsArcDetailXcDao;
	
	public void setsWfCmplntsArcDetailXcDao(IDao<SWfCmplntsArcDetailXc> sWfCmplntsArcDetailXcDao) {
		this.sWfCmplntsArcDetailXcDao = sWfCmplntsArcDetailXcDao;
	}

	@Override
	public SWfCmplntsArcDetailXc getArcXcDetailById(String wrkfmShowSwftno) {
		return sWfCmplntsArcDetailXcDao.findUnique("from SWfCmplntsArcDetailXc where wrkfmShowSwftno = ?", wrkfmShowSwftno);
	}

	@Override
	public SWfCmplntsArcDetailXc getEndTime() {
		QueryAdapter q = new QueryAdapter();
		//String sql = " select max(trunc(CREATE_TIME)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_XC  ";
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_XC  ";
		SWfCmplntsArcDetailXc sWfCmplntsArcDetailXc=new SWfCmplntsArcDetailXc();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			sWfCmplntsArcDetailXc.setArcTime(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return sWfCmplntsArcDetailXc;
	}


}
