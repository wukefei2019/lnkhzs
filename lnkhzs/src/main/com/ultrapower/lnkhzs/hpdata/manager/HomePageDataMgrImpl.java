package com.ultrapower.lnkhzs.hpdata.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.lnkhzs.hpdata.service.IHomePageDataService;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealJobdata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsDealPlandata;
import com.ultrapower.lnkhzs.track.model.NgcsWfCmplntsResendDeal;
import com.ultrapower.lnkhzs.track.service.ITrackService;
import com.ultrapower.omcs.utils.StringUtils;


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
public class HomePageDataMgrImpl implements IHomePageDataService {

	@Override
	public List<Map<String, String>> ajaxGetComplaintsAmount() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		/*sql.append(" SELECT a.NAME,round(a.VALUE/b.VALUE*100,2) AS VALUE FROM ( " );*/
		
		//真实
		sql.append(" SELECT  count(1) as value ,area_name as name  FROM  NGCS_WF_CMPLNTS_COLLECT " );
		/*sql.append(" SELECT  count(1) as value ,area_name as name  FROM (" );
		sql.append(" SELECT area_name ,to_char(to_date(statis_date,'yyyyMMdd'),'yyyy-mm') AS acpt_time FROM NGCS_WF_CMPLNTS_REQST_DETAIL   ");
		sql.append(" WHERE (IF_TS='0'AND A02 IN ('移动业务','增值业务','家庭业务','集团业务') OR (A02 = '服务类' AND A03 ='客户投诉')) ");
		sql.append(" UNION ALL SELECT area_name ,to_char(to_date(statis_date,'yyyyMMdd'),'yyyy-mm') AS acpt_time FROM NGCS_WF_CMPLNTS_ARC_DETAIL) ");*/
		
		
		sql.append(" WHERE area_name NOT IN ('异地','全省') AND acpt_time = (select to_char(TRUNC(SYSDATE,'mm'),'yyyy-mm') AS mon FROM dual)");
		sql.append(" GROUP BY area_name order by  value Desc  ");
		//测试
		/*sql.append(" SELECT  count(1) as value ,area_name as name  FROM (" );
		sql.append(" SELECT area_name ,to_char(to_date(statis_date,'yyyyMMdd'),'yyyy') AS acpt_time FROM NGCS_WF_CMPLNTS_REQST_DETAIL   ");
		sql.append(" WHERE (IF_TS='0' AND  (A02 IN ('移动业务','增值业务','家庭业务','集团业务') OR (A02 = '服务类' AND A03 ='客户投诉'))) ");
		sql.append(" UNION ALL SELECT area_name ,to_char(to_date(statis_date,'yyyyMMdd'),'yyyy') AS acpt_time FROM NGCS_WF_CMPLNTS_ARC_DETAIL) ");
		sql.append(" WHERE area_name NOT IN ('异地','全省') AND acpt_time = '2019'");
		sql.append(" GROUP BY area_name order by  value Desc  ");*/
		
		/*sql.append(" (SELECT  count(1) as value FROM (" );
		sql.append(" SELECT area_name FROM NGCS_WF_CMPLNTS_REQST_DETAIL   ");
		sql.append(" WHERE (IF_TS='0'AND A02 IN ('移动业务','增值业务','家庭业务','集团业务') OR (A02 = '服务类' AND A03 ='客户投诉')) ");
		sql.append(" UNION ALL SELECT area_name FROM NGCS_WF_CMPLNTS_ARC_DETAIL) ");
		sql.append(" WHERE area_name NOT IN ('异地','全省') ");
		sql.append(" order by  value Desc ) b  ");*/
		
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("name").substring(0, dr.getString("name").length()-1));
            map.put("value", dr.getString("value"));
            result.add(map);
        }
		return result;
	}

	@Override
	public List<Map<String, String>> ajaxGetResendList() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ( " );
		sql.append(" SELECT * FROM NGCS_WF_CMPLNTS_RESEND_DETAIL WHERE REPORTSTATUS ='已派发' ORDER BY CREATETIME DESC ) ");
		sql.append(" WHERE ROWNUM <6 ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("ID", dr.getString("ID"));
            map.put("APPLYTITLE", dr.getString("APPLYTITLE"));
            map.put("MATTERCATEGORY", dr.getString("MATTERCATEGORY"));
            map.put("LABELCATEGORY", dr.getString("LABELCATEGORY"));
            map.put("MAINDEPART", dr.getString("MAINDEPART"));
            map.put("MAINDEPARTID", dr.getString("MAINDEPARTID"));
            map.put("EXPIRYDATE", dr.getString("EXPIRYDATE"));
            map.put("SUBJECT", dr.getString("SUBJECT"));
            map.put("CONTENT", dr.getString("CONTENT"));
            map.put("VALIDMETHOD", dr.getString("VALIDMETHOD"));
            map.put("VALIDVALUE", dr.getString("VALIDVALUE"));
            map.put("REPORTEXT", dr.getString("REPORTEXT"));
            map.put("RETURNTEXT", dr.getString("RETURNTEXT"));
            map.put("REPORTSTATUS", dr.getString("REPORTSTATUS"));
            map.put("TARGET", dr.getString("TARGET"));
            map.put("CREATETIME", dr.getString("CREATETIME"));
            result.add(map);
        }
		return result;
	}

	@Override
	public List<Map<String, String>> ajaxGetTagTOP5Amount(String title) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		String stime="";
		Calendar cal = Calendar.getInstance(); 
		int year = cal.get(Calendar.YEAR); 
		int month = cal.get(Calendar.MONTH )+1;
		if(month<10){
			stime=year + "-0"+month;
		}else{
			stime=year + "-"+month;
		}

		System.out.println(stime);
		if(title.equals("移动业务投诉量")){
			
			//sql.append(" SELECT * FROM ( " );
			
			
			sql.append(" SELECT * FROM (SELECT NAME,COUNT (*) VALUE,'移动业务投诉量' as title FROM " );
			/*sql.append(" 	(SELECT T .resp_dept,T.STATIS_DATE time, T .area_Name, T .srv_reqst_type_full_nm NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_arc_detail T ");
			sql.append(" UNION ALL  ");
			sql.append(" SELECT T .team_name resp_dept,T.STATIS_DATE time, T .area_Name, T .a12 NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_reqst_detail T ");
			sql.append(" WHERE ( IF_TS = '0' AND ( A02 IN ( '移动业务' ) " );
			sql.append(" OR ( A02 = '服务类' AND A03 = '客户投诉'))) ");
			sql.append(" ) WHERE NAME LIKE '移动业务%' AND time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC ");*/
			sql.append(" NGCS_WF_CMPLNTS_COLLECT WHERE NAME LIKE '移动业务%' AND acpt_time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC ");
			
			//sql.append(" ) WHERE 1 = 1 ");
			//sql.append(" AND TITLE = '"+title+"'ORDER BY VALUE ASC");
		}else if(title.equals("家庭业务投诉量")){
			
			//sql.append(" SELECT * FROM ( " );
			sql.append(" SELECT * FROM (SELECT NAME,COUNT (*) VALUE,'家庭业务投诉量' as title FROM " );
			
			/*sql.append(" 	(SELECT T .resp_dept,T.STATIS_DATE time, T .area_Name, T .srv_reqst_type_full_nm NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_arc_detail T ");
			sql.append(" UNION ALL  ");
			sql.append(" SELECT T .team_name resp_dept,T.STATIS_DATE time, T .area_Name, T .a12 NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_reqst_detail T ");
			sql.append(" WHERE ( IF_TS = '0' AND ( A02 IN ( '家庭业务' ) " );
			sql.append(" OR ( A02 = '服务类' AND A03 = '客户投诉'))) ");
			sql.append(" ) WHERE NAME LIKE '家庭业务%' AND time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");*/
			sql.append(" NGCS_WF_CMPLNTS_COLLECT WHERE NAME LIKE '家庭业务%' AND acpt_time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");
			
			//sql.append(" ) WHERE 1 = 1 ");
			//sql.append(" AND TITLE = '"+title+"'ORDER BY VALUE ASC");
		}else if(title.equals("增值业务投诉量")){
			
			//sql.append(" SELECT * FROM ( " );
			sql.append(" SELECT * FROM (SELECT NAME,COUNT (*) VALUE,'增值业务投诉量' as title FROM " );
			/*sql.append(" 	(SELECT T .resp_dept,T.STATIS_DATE time, T .area_Name, T .srv_reqst_type_full_nm NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_arc_detail T ");
			sql.append(" UNION ALL  ");
			sql.append(" SELECT T .team_name resp_dept,T.STATIS_DATE time, T .area_Name, T .a12 NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_reqst_detail T ");
			sql.append(" WHERE ( IF_TS = '0' AND ( A02 IN (  '增值业务' ) " );
			sql.append(" OR ( A02 = '服务类' AND A03 = '客户投诉'))) ");
			sql.append(" ) WHERE NAME LIKE '增值业务%' AND time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");*/
			sql.append(" NGCS_WF_CMPLNTS_COLLECT WHERE NAME LIKE '增值业务%' AND acpt_time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");
		}else if(title.equals("集团业务投诉量")){
			//sql.append(" SELECT * FROM ( " );
			sql.append(" SELECT * FROM (SELECT NAME,COUNT (*) VALUE,'集团业务投诉量' as title FROM " );
			/*sql.append(" 	(SELECT T .resp_dept,T.STATIS_DATE time, T .area_Name, T .srv_reqst_type_full_nm NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_arc_detail T ");
			sql.append(" UNION ALL  ");
			sql.append(" SELECT T .team_name resp_dept,T.STATIS_DATE time, T .area_Name, T .a12 NAME ");
			sql.append(" FROM ngcs_wf_cmplnts_reqst_detail T ");
			sql.append(" WHERE ( IF_TS = '0' AND ( A02 IN ( '集团业务' ) " );
			sql.append(" OR ( A02 = '服务类' AND A03 = '客户投诉'))) ");
			sql.append(" ) WHERE NAME LIKE '集团业务%' AND time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");*/
			sql.append(" NGCS_WF_CMPLNTS_COLLECT WHERE NAME LIKE '集团业务%' AND acpt_time like '"+stime+"%' GROUP BY NAME ORDER BY VALUE DESC ) WHERE ROWNUM < 6 ORDER BY VALUE ASC");
		}
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("name"));
            map.put("value", dr.getString("value"));
            map.put("title", dr.getString("title"));
            result.add(map);
        }
		return result;
	}

	@Override
	public List<Map<String, String>> ajaxGetAnswerAmount() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t1.mon ,count(t2.ANSWERTIME) totalValue  FROM ( " );
		sql.append(" SELECT  ");
		sql.append(" to_char(TRUNC(ADD_MONTHS(SYSDATE,-rownum+1),'mm'),'yyyy-mm') AS mon  ");
		sql.append(" FROM DUAL connect by rownum<=12 ) t1  ");
		sql.append(" LEFT JOIN (SELECT COUNT(DISTINCT USERID) VALUE ,ANSWERTIME FROM ZL_KHZS_ANSWER GROUP BY ANSWERTIME) t2 ON ");
		sql.append(" T1.mon = to_char(to_date(t2.ANSWERTIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm') ");
		sql.append(" GROUP BY t1.mon " );
		sql.append(" ORDER BY t1.mon ASC ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("mon", dr.getString("mon"));
            map.put("value", dr.getString("totalValue"));
            result.add(map);
        }
		return result;
	}

	@Override
	public List<Map<String, String>> ajaxGetSourceAmount() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t1.mon ,count(t2.CREATE_TIME) totalValue  FROM ( " );
		sql.append(" SELECT ");
		sql.append(" to_char(TRUNC(ADD_MONTHS(SYSDATE,-rownum+1),'mm'),'yyyy-mm') AS mon ");
		sql.append(" FROM DUAL connect by rownum<=12 ) t1 ");
		sql.append(" LEFT JOIN (SELECT * FROM ZL_TRACE_SOURCE_LIST ) t2 ON ");
		sql.append(" T1.mon = to_char(to_date(t2.CREATE_TIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm') ");
		sql.append(" GROUP BY t1.mon " );
		sql.append(" ORDER BY t1.mon ASC ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        
        StringBuffer sql1 = new StringBuffer();
		sql1.append(" SELECT t1.mon ,count(t2.CREATE_TIME) comValue  FROM ( " );
		sql1.append(" SELECT ");
		sql1.append(" to_char(TRUNC(ADD_MONTHS(SYSDATE,-rownum+1),'mm'),'yyyy-mm') AS mon ");
		sql1.append(" FROM DUAL connect by rownum<=12 ) t1 ");
		sql1.append(" LEFT JOIN (SELECT * FROM ZL_TRACE_SOURCE_LIST WHERE STATUS IS NOT NULL ) t2 ON ");
		sql1.append(" T1.mon = to_char(to_date(t2.CREATE_TIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm') ");
		sql1.append(" GROUP BY t1.mon " );
		sql1.append(" ORDER BY t1.mon ASC ");
		DataTable dt1 = qa.executeQuery(sql1.toString());
		List<DataRow> rows1 = dt1.getRowList();
        Iterator<DataRow> it1 = rows1.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            DataRow dr1 = (DataRow) it1.next();
            map.put("mon", dr.getString("mon"));
            map.put("totalValue", dr.getString("totalValue"));
            map.put("comValue", dr1.getString("comValue"));
            result.add(map);
        }
		return result;
	}
	
	@Override
	public List<Map<String, String>> ajaxGetSourceAmountNew() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t1.VALUE1,nvl(t2.VALUE2,0) AS VALUE2,t1.TYPE1,round(nvl(VALUE2,0)/VALUE1,2) AS VALUE3 FROM " );
		sql.append(" (SELECT count(1) AS VALUE1,TRACE_SOURCE_TYPE AS TYPE1 FROM ZL_TRACE_SOURCE_LIST WHERE  ");
		sql.append(" to_char(to_date(CREATE_TIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm')  ");
		sql.append(" IN (SELECT to_char(TRUNC(ADD_MONTHS(SYSDATE,-rownum+1),'mm'),'yyyy-mm') AS mon FROM DUAL connect by rownum<=12) ");
		sql.append(" GROUP BY TRACE_SOURCE_TYPE )t1 ");
		sql.append(" LEFT JOIN  ");
		sql.append(" (SELECT count(1) AS VALUE2,TRACE_SOURCE_TYPE AS TYPE2 FROM ZL_TRACE_SOURCE_LIST  " );
		sql.append(" WHERE STATUS IS NOT NULL AND to_char(to_date(CREATE_TIME,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm')  ");
		sql.append(" IN (SELECT to_char(TRUNC(ADD_MONTHS(SYSDATE,-rownum+1),'mm'),'yyyy-mm') AS mon FROM DUAL connect by rownum<=12) ");
		sql.append(" GROUP BY TRACE_SOURCE_TYPE ) t2 ON t1.TYPE1=t2.TYPE2  ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("type1"));
            map.put("value1", dr.getString("value1"));
            map.put("value2", dr.getString("value2"));
            map.put("value3", dr.getString("value3"));
            result.add(map);
        }
		return result;
	}

	@Override
	public List<Map<String, String>> ajaxGetKHZSAmount() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) value, '员工建议库总量' as name " );
		sql.append(" from zl_khzs_t_main m, ");
		sql.append(" (select * from ZL_KHZS_T_FLOW where status = '审核员复核') f, ");
		sql.append(" (select VOCPID, remark2 from ZL_KHZS_T_FLOW where status = '关闭') y ");
		sql.append(" where f.vocpid(+) = m.pid and m.pid = y.vocpid(+) and m.flowstatus = '关闭' and m.type='一线员工建议' and m.example <> '不公开' and m.example is not null ");
		sql.append(" UNION ALL ");
		sql.append(" select count(1) value, '投诉案例库总量' as name " );
		sql.append(" from zl_khzs_t_main m, ");
		sql.append(" (select * from ZL_KHZS_T_FLOW where status = '审核员复核') f, ");
		sql.append(" (select VOCPID, remark2 from ZL_KHZS_T_FLOW where status = '关闭') y ");
		sql.append(" where f.vocpid(+) = m.pid and m.pid = y.vocpid(+) and m.flowstatus = '关闭' and m.type='典型投诉案例' and m.example <> '不公开' and m.example is not null ");
		sql.append(" UNION ALL ");
		sql.append(" select count(1) value, '金点子例库总量' as name " );
		sql.append(" from zl_khzs_t_main m, ");
		sql.append(" (select * from ZL_KHZS_T_FLOW where status = '审核员复核') f, ");
		sql.append(" (select VOCPID, remark2 from ZL_KHZS_T_FLOW where status = '关闭') y ");
		sql.append(" where f.vocpid(+) = m.pid and m.pid = y.vocpid(+) and m.flowstatus = '关闭' and m.type='金点子' and m.example <> '不公开' and m.example is not null ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("name"));
            map.put("value", dr.getString("value"));
            result.add(map);
        }
		return result;
	}

	

	
}
