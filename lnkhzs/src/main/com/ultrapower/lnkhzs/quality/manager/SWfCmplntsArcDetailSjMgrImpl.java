package com.ultrapower.lnkhzs.quality.manager;


import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetailSj;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailSjService;
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
public class SWfCmplntsArcDetailSjMgrImpl implements ISWfCmplntsArcDetailSjService {

    private IDao<SWfCmplntsArcDetailSj> sWfCmplntsArcDetailSjDao;

	public IDao<SWfCmplntsArcDetailSj> getsWfCmplntsArcDetailSjDao() {
		return sWfCmplntsArcDetailSjDao;
	}

	public void setsWfCmplntsArcDetailSjDao(IDao<SWfCmplntsArcDetailSj> sWfCmplntsArcDetailSjDao) {
		this.sWfCmplntsArcDetailSjDao = sWfCmplntsArcDetailSjDao;
	}

	@Override
	public SWfCmplntsArcDetailSj  getEndTime() {
		QueryAdapter q = new QueryAdapter();
		//String sql = " select max(trunc(CREATE_TIME)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_SJ  ";
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL_SJ  ";
		SWfCmplntsArcDetailSj SWfCmplntsArcDetailSj=new SWfCmplntsArcDetailSj();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			SWfCmplntsArcDetailSj.setAcptTime(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return SWfCmplntsArcDetailSj;
	}

	@Override
	public SWfCmplntsArcDetailSj getArcDetailSjById(String wrkfmShowSwftno) {
		return sWfCmplntsArcDetailSjDao.findUnique("from SWfCmplntsArcDetailSj  where wrkfmShowSwftno = ?", wrkfmShowSwftno);
	}
    
    

}
