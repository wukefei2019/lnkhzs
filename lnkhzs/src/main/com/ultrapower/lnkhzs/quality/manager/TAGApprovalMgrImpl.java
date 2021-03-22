package com.ultrapower.lnkhzs.quality.manager;


import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.TAGAdmin;
import com.ultrapower.lnkhzs.quality.model.TAGInfo;
import com.ultrapower.lnkhzs.quality.service.ITAGApprovalService;
import com.ultrapower.omcs.utils.DateUtils;
import java.util.List;
import com.ultrapower.commons.lang3.StringUtils;
import java.util.Date;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.TimeUtils;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *标签
 *--------------------------------------------------------------
 */
public class TAGApprovalMgrImpl implements ITAGApprovalService {

    private IDao<TAGInfo> tAGInfoDao;
    
    private IDao<SWfCmplntsArcDetail> sWfCmplntsArcDetailDao;
    
    private IDao<TAGAdmin> tAGAdminDao;
    

	public IDao<SWfCmplntsArcDetail> getsWfCmplntsArcDetailDao() {
		return sWfCmplntsArcDetailDao;
	}

	public void setsWfCmplntsArcDetailDao(IDao<SWfCmplntsArcDetail> sWfCmplntsArcDetailDao) {
		this.sWfCmplntsArcDetailDao = sWfCmplntsArcDetailDao;
	}

	public void settAGInfoDao(IDao<TAGInfo> tAGInfoDao) {
		this.tAGInfoDao = tAGInfoDao;
	}

	public void settAGAdminDao(IDao<TAGAdmin> tAGAdminDao) {
		this.tAGAdminDao = tAGAdminDao;
	}



	@Override
	public TAGInfo queryTAGApproval(String id) {
		return tAGInfoDao.get(id);
	}
	

	@Override
	public SWfCmplntsArcDetail selectByGDH(String workNumber) {
		// TODO Auto-generated method stub
				SWfCmplntsArcDetail findUnique = sWfCmplntsArcDetailDao.findUnique("from SWfCmplntsArcDetail where WRKFM_SHOW_SWFTNO = ?", workNumber);
					return findUnique;
	}
	@Override
	public void updateTagApproval(TAGInfo tAGApproval) {
		if (StringUtils.isBlank(tAGApproval.getId())) {
			tAGApproval.setId(UUIDGenerator.getUUIDoffSpace());
			tAGApproval.setApprovaltime(TimeUtils.formatDateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			List<TAGAdmin> tagadmin=tAGAdminDao.getAll();
			tAGApproval.setApprover(tagadmin.get(0).getChangepeople());
			tAGApproval.setApprovername(tagadmin.get(0).getChangepeoplename());
			tAGInfoDao.save(tAGApproval);
		} else {
			tAGApproval.setApprovaltime(TimeUtils.formatDateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			List<TAGAdmin> tagadmin=tAGAdminDao.getAll();
			tAGApproval.setApprover(tagadmin.get(0).getChangepeople());
			tAGApproval.setApprovername(tagadmin.get(0).getChangepeoplename());
			tAGInfoDao.saveOrUpdate(tAGApproval);
		}
		
	}

	@Override
	public void addTag(TAGInfo tAGInfo) {
		// TODO Auto-generated method stub
		
		if (StringUtils.isBlank(tAGInfo.getId())) {
			tAGInfo.setId(UUIDGenerator.getUUIDoffSpace());
			tAGInfo.setReporttime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
			tAGInfo.setReportstatus("待提交");
			//tAGInfo.setApprovalstatus("待审批");
			tAGInfoDao.save(tAGInfo);
		}else{
			tAGInfo.setReporttime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
			tAGInfo.setReportstatus("待提交");
			tAGInfoDao.saveOrUpdate(tAGInfo);
		}
		
		
	}

	@Override
	public void deleteTag(String id) {
		tAGInfoDao.removeById(id);	
	}

	@Override
	public TAGInfo queryTagDetail(String id) {
		return tAGInfoDao.get(id);
	}

	@Override
	public void submitTag(TAGInfo tAGApproval) {
		int executeUpdate = tAGInfoDao.executeUpdate(
				"update TAGInfo set REPORTSTATUS = '已提交',APPROVALSTATUS = '未审批' where id = ? ", tAGApproval.getId());
	}

}
