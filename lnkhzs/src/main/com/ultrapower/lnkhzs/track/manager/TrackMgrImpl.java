package com.ultrapower.lnkhzs.track.manager;

import java.util.List;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealJobdata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealPlandata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsResendDeal;
import com.ultrapower.lnkhzs.track.service.ITrackService;


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
public class TrackMgrImpl implements ITrackService {

	private IDao<NgcsWfCmplntsDealJobdata> ngcsWfCmplntsDealJobdataDao;
    
    private IDao<NgcsWfCmplntsDealPlandata> ngcsWfCmplntsDealPlandataDao;
    
    private IDao<NgcsWfCmplntsResendDeal> ngcsWfCmplntsResendDealDao;

	@SuppressWarnings("unchecked")
	public List<NgcsWfCmplntsResendDeal> getAjaxMainData(String parentid, String step) {
    	
		List<NgcsWfCmplntsResendDeal> deals = null;
		if (parentid != null && !"".equals(parentid)) {
			deals = ngcsWfCmplntsResendDealDao.find("from NgcsWfCmplntsResendDeal where uids = ? and step = ? order by time", parentid, step);
		} else {
//			deals = ngcsWfCmplntsResendDealDao.find("from NgcsWfCmplntsResendDeal where step = ? order by time", step);
		}
		
		return deals;
    }
    
	public NgcsWfCmplntsDealPlandata getAjaxPlanData(String pid) {
		return ngcsWfCmplntsDealPlandataDao.get(pid);
	}

	@SuppressWarnings("unchecked")
	public List<NgcsWfCmplntsDealJobdata> getAjaxJobData(String pid) {
    	
		return ngcsWfCmplntsDealJobdataDao.find("from NgcsWfCmplntsDealJobdata where pid = ? order by jobdate", pid);
    }

	public String getAjaxMaxStepData(String parentid) {
		String step="";
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append("select max(step) from ( "
					+ " select case step "
					+ " when '服务问题发起' then 1 "
					+ " when '服务方案制定' then 2 "
					+ " when '服务方案审核' then 3 "
					+ " when '服务方案执行及进展' then 4 "
					+ " when '服务效果评估' then 5 "
					+ " end step "
					+ " from NGCS_WF_CMPLNTS_RESEND_DEAL  "
					+ " WHERE uids='" + parentid + "')" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	if(datatable.getRowList().size()>0) {
    		step=datatable.getRowList().get(0).getString(0);
    	}
    	return step;
	}
	
    public IDao<NgcsWfCmplntsDealJobdata> getNgcsWfCmplntsDealJobdataDao() {
		return ngcsWfCmplntsDealJobdataDao;
	}

	public void setNgcsWfCmplntsDealJobdataDao(IDao<NgcsWfCmplntsDealJobdata> ngcsWfCmplntsDealJobdataDao) {
		this.ngcsWfCmplntsDealJobdataDao = ngcsWfCmplntsDealJobdataDao;
	}

	public IDao<NgcsWfCmplntsDealPlandata> getNgcsWfCmplntsDealPlandataDao() {
		return ngcsWfCmplntsDealPlandataDao;
	}

	public void setNgcsWfCmplntsDealPlandataDao(IDao<NgcsWfCmplntsDealPlandata> ngcsWfCmplntsDealPlandataDao) {
		this.ngcsWfCmplntsDealPlandataDao = ngcsWfCmplntsDealPlandataDao;
	}

	public IDao<NgcsWfCmplntsResendDeal> getNgcsWfCmplntsResendDealDao() {
		return ngcsWfCmplntsResendDealDao;
	}

	public void setNgcsWfCmplntsResendDealDao(IDao<NgcsWfCmplntsResendDeal> ngcsWfCmplntsResendDealDao) {
		this.ngcsWfCmplntsResendDealDao = ngcsWfCmplntsResendDealDao;
	}

}
