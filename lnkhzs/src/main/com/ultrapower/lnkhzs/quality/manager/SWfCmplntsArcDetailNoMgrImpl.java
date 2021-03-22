package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailNo;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailNoService;
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
public class SWfCmplntsArcDetailNoMgrImpl implements ISWfCmplntsArcDetailNoService {

    private IDao<SWfCmplntsArcDetailNo> sWfCmplntsArcDetailNoDao;

	public void setsWfCmplntsArcDetailNoDao(IDao<SWfCmplntsArcDetailNo> sWfCmplntsArcDetailNoDao) {
		this.sWfCmplntsArcDetailNoDao = sWfCmplntsArcDetailNoDao;
	}

	@Override
	public SWfCmplntsArcDetailNo getArcDetailById(String wrkfmShowSwftno) {
		return sWfCmplntsArcDetailNoDao.findUnique("from SWfCmplntsArcDetailNo where wrkfmShowSwftno = ?", wrkfmShowSwftno);
	}

	@Override
	public SWfCmplntsArcDetailNo getEndTime() {
		QueryAdapter q = new QueryAdapter();
		//String sql = " select max(trunc(CREATE_TIME)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_NO  ";
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_NO  ";
		SWfCmplntsArcDetailNo sWfCmplntsArcDetailNo=new SWfCmplntsArcDetailNo();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			sWfCmplntsArcDetailNo.setAcptTime(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return sWfCmplntsArcDetailNo;
	}


}
