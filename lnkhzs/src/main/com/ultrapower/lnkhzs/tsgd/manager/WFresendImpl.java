package com.ultrapower.lnkhzs.tsgd.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.XMLType;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.lnkhzs.tsgd.service.WFresendService;
import com.ultrapower.lnkhzs.tsgd.web.IaAuditSendAction;
import com.ultrapower.omcs.utils.DateUtils;

public class WFresendImpl implements WFresendService {

	private IDao<WFresend> wFresendDao;

	public IDao<WFresend> getwFresendDao() {
		return wFresendDao;
	}

	public void setwFresendDao(IDao<WFresend> wFresendDao) {
		this.wFresendDao = wFresendDao;
	}

	@Override
	public boolean addSend(WFresend wFresend) {
		wFresend.setExpirydate(DateUtils.getDateStr(wFresend.getExpirydate()));
		Map<String,Object> map=IaAuditSendAction.draftWorkFlow(wFresend);
		String formDetail=map.get("formDetail").toString();
		wFresend.setReportext(formDetail);
		String result="";
		if(map.get("result")!=null)
			result=map.get("result").toString();
		if(result.isEmpty() || result.contains("ERROR")){
			wFresend.setReportstatus("保存");
			wFresend.setReturntext(result);
			if (StringUtils.isBlank(wFresend.getId())) {
				//保存
				wFresend.setId(UUIDGenerator.getUUIDoffSpace());
				wFresendDao.save(wFresend);
			} else {
				//更新
				wFresendDao.saveOrUpdate(wFresend);
			}
			return false;
		}else{
			wFresend.setReportstatus("已派发");
			wFresend.setReturntext(result);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			wFresend.setCreatetime(sdf.format(new Date()));
			if (StringUtils.isBlank(wFresend.getId())) {
				//保存
				wFresend.setId(UUIDGenerator.getUUIDoffSpace());
				wFresendDao.save(wFresend);
			} else {
				//更新
				wFresendDao.saveOrUpdate(wFresend);
			}
		}
		
		return true;
	}

	@Override
	public WFresend getDetailById(WFresend wFresend) {
		return wFresendDao.get(wFresend.getId());
	}

	@Override
	public WFresend getDetailByLABELCATEGORY(WFresend wFresend) {
		List<WFresend> list=wFresendDao.find(" from WFresend where labelcategory = ?", wFresend.getLabelcategory());
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public Map<String, String> getInfo(String A12) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" select name , resendt.REPORTSTATUS ,resendt.ID ,count(*) value from ( " );
		sql.append(" select to_char(to_date(t.statis_date,'yyyy-mm-dd'),'yyyy-mm') time,t.resp_dept,t.area_Name,t.srv_reqst_type_full_nm name ");
		sql.append(" from ngcs_wf_cmplnts_arc_detail t ");
		sql.append(" union all ");
		sql.append(" select to_char(to_date(t.statis_date,'yyyy-mm-dd'),'yyyy-mm') time, t.team_name resp_dept,t.area_Name, t.a12 name ");
		sql.append(" from ngcs_wf_cmplnts_reqst_detail t WHERE (IF_TS='0'AND A02 IN ('移动业务','增值业务','家庭业务','集团业务') OR (A02 = '服务类' AND A03 ='客户投诉'))");
		sql.append(" ) left join NGCS_WF_CMPLNTS_RESEND_DETAIL resendt on resendt.LABELCATEGORY = name where 1 = 1 and name = '"+A12+"'");
		sql.append(" group by name ,REPORTSTATUS , ID order by value Desc ");
		
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        Map<String, String> map = new HashMap<String,String>();
        while (it.hasNext()) {
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("name"));
            map.put("reportstatus", dr.getString("reportstatus"));
            map.put("id", dr.getString("id"));
            result.add(map);
	  }
		return map;
	}
	

}
