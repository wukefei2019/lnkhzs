package com.ultrapower.lnkhzs.quality.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsArcDetail;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsArcDetailService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;

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
public class SWfCmplntsArcDetailMgrImpl implements ISWfCmplntsArcDetailService {

    private IDao<SWfCmplntsArcDetail> sWfCmplntsArcDetailDao;

	public void setsWfCmplntsArcDetailDao(IDao<SWfCmplntsArcDetail> sWfCmplntsArcDetailDao) {
		this.sWfCmplntsArcDetailDao = sWfCmplntsArcDetailDao;
	}

	@Override
	public SWfCmplntsArcDetail getArcDetailById(String wrkfmShowSwftno) {
		return sWfCmplntsArcDetailDao.findUnique("from SWfCmplntsArcDetail where wrkfmShowSwftno = ?", wrkfmShowSwftno);
	}

	@Override
	public SWfCmplntsArcDetail getEndTime() {
		QueryAdapter q = new QueryAdapter();
		//String sql = " select max(trunc(CREATE_TIME)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL  ";
		String sql = " select max(trunc(STATIS_DATE)) AS ENDTIME from NGCS_WF_CMPLNTS_ARC_DETAIL  ";
		SWfCmplntsArcDetail sWfCmplntsArcDetail=new SWfCmplntsArcDetail();
		
		DataTable datatable = q.executeQuery(sql);
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			sWfCmplntsArcDetail.setAcptTime(datatable.getRowList().get(i).getString("ENDTIME"));
		}
		return sWfCmplntsArcDetail;
	}

	@Override
	public Map<Object, Object> otherGetThan(String time , String lable,String mg) {
		//默认页面time和lable为空
		if(mg.equals("1")){
			time = null;
			lable = null;
		}
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		QueryAdapter rq = new QueryAdapter();
		Double i = 0.0;
		Double i_s = 0.0;
		//其他总量
		String sql = "select count(1) as count FROM NGCS_WF_CMPLNTS_COLLECT WHERE 1=1 ";
		

		if(time!=null && !(time.isEmpty())){
			sql+=" AND ACPT_TIME like '"+time+"'";
		}
		 
		if(lable!=null && !(lable.isEmpty())){
			sql+=" AND  NAME like '%"+lable+"%'";

		}
		
		String sql1 = sql + " and NAME like '%其他%'";
		
		

    	DataTable dt = rq.executeQuery(sql);
    	List<DataRow> rowList = dt.getRowList();
    	if(rowList.size()!=0){
    		 i = rowList.get(0).getDouble("COUNT");
    	}
		

    	DataTable dt1 = rq.executeQuery(sql1);
    	List<DataRow> rowList1 = dt1.getRowList();
    	if(rowList1.size()!=0){
    		 i_s = rowList1.get(0).getDouble("COUNT");
    	}
    	
    	String than = "";
    	if(i!=0 && i!=null){
    		//占比
    		Double thans = i_s/i;
    		than = String.format("%.2f", i_s/i*100)+"%";
    		
    	}else{
    		than = "0%";
    	}
    	map.put("count", i);
    	map.put("oneCount", i_s);
    	map.put("than", than);
		
		return map;
	}

}
