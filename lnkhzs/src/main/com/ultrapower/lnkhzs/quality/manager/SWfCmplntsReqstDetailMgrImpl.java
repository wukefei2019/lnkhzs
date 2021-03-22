package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsReqstDetailService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]； 
 *--------------------------------------------------------------
 */
public class SWfCmplntsReqstDetailMgrImpl implements ISWfCmplntsReqstDetailService {

    private IDao<SWfCmplntsReqstDetail> sWfCmplntsReqstDetailDao;
    
	public void setsWfCmplntsReqstDetailDao(IDao<SWfCmplntsReqstDetail> sWfCmplntsReqstDetailDao) {
		this.sWfCmplntsReqstDetailDao = sWfCmplntsReqstDetailDao;
	}

	@Override
	public SWfCmplntsReqstDetail getReqstDetailById(Integer srvReqstId) {
		return sWfCmplntsReqstDetailDao.findUnique("from SWfCmplntsReqstDetail where srvReqstId = ?", srvReqstId);
	}

	@Override
	public SWfCmplntsReqstDetail getEndTime() {
		QueryAdapter q = new QueryAdapter();
		//String sql = " select max(trunc(CREATE_TIME)) AS ENDTIME from NGCS_WF_CMPLNTS_REQST_DETAIL  ";
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_REQST_DETAIL  ";
		SWfCmplntsReqstDetail sWfCmplntsReqstDetail=new SWfCmplntsReqstDetail();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			sWfCmplntsReqstDetail.setCodeNm(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return sWfCmplntsReqstDetail;
	}

	@Override
	public String getMaxMinTime(String queryTable, HashMap<String, String> params) throws ParseException {
		String sheetName="";
		if(params.get("begintime")!=null&&(!params.get("begintime").isEmpty())){
			//Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("begintime"));
			Date date = new SimpleDateFormat("yyyyMMdd").parse(params.get("begintime"));
			String now = new SimpleDateFormat("yyyyMMdd").format(date);
			sheetName=now+"至";
		}else{
			QueryAdapter q = new QueryAdapter();
			//String sql = " select statis_date AS ENDTIME from "+queryTable;
			String sql = " SELECT * FROM (select statis_date AS ENDTIME from "+queryTable+" ORDER BY statis_date DESC ) where rownum=1 ";
			DataTable datatable = q.executeQuery(sql);
			for (int i = 0; i < datatable.getRowList().size(); i++) {
				sheetName=datatable.getRowList().get(i).getString("ENDTIME")+"-";
			}
		}
		if(params.get("endtime")!=null&&(!params.get("endtime").isEmpty())){
			//Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(params.get("endtime"));
			Date date = new SimpleDateFormat("yyyyMMdd").parse(params.get("endtime"));
			String now = new SimpleDateFormat("yyyyMMdd").format(date);
			sheetName+=now;
		}else{
			QueryAdapter q = new QueryAdapter();
			//String sql = " select statis_date AS ENDTIME from "+queryTable;
			String sql = " SELECT * FROM (select statis_date AS ENDTIME from "+queryTable+" ORDER BY statis_date DESC ) where rownum=1 ";
			DataTable datatable = q.executeQuery(sql);
			for (int i = 0; i < datatable.getRowList().size(); i++) {
				sheetName+=datatable.getRowList().get(i).getString("ENDTIME");
			}
		}
		return sheetName;
	}


}
