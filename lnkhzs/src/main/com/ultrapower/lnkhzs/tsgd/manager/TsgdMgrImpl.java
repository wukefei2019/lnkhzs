package com.ultrapower.lnkhzs.tsgd.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicator;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicatorTree;
import com.ultrapower.lnkhzs.tsgd.service.ITsgdService;
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
public class TsgdMgrImpl implements ITsgdService{

	private IDao<ZlTsfxIndicator> zlTsfxIndicatorDao;
	private IDao<ZlTsfxIndicatorTree> zlTsfxIndicatorTreeDao;
	
	@SuppressWarnings("unchecked")
	public List<ZlTsfxIndicator> getPiesInfo() {
		String sql = "from ZlTsfxIndicator where childid is not null and vieworder is not null order by vieworder";
		List<ZlTsfxIndicator> list= zlTsfxIndicatorDao.find(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ZlTsfxIndicator> getAllPiesInfo() {
		String sql = "from ZlTsfxIndicator where childid is not null order by vieworder, id";
		List<ZlTsfxIndicator> list= zlTsfxIndicatorDao.find(sql);
		return list;
	}
	
	public ZlTsfxIndicator getOnePieInfo(String id) {
		ZlTsfxIndicator ind = zlTsfxIndicatorDao.get(id);
		return ind;
	}
	
	@SuppressWarnings("rawtypes")
	public String getValueBySqlname(String sqlname, String areaName, String time) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (areaName != null && !"".equals(areaName)) {
			paramMap.put("area_name", areaName);
		} 
		
		if (time != null && !"".equals(time)) {
			paramMap.put("year", time);
		}
		
		RQuery query = new RQuery(sqlname, (HashMap) paramMap);
		DataTable dt = query.getDataTable();
		String value = "";
		if(dt==null){
			 return value;
		}
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        if (it.hasNext()) {
            DataRow dr = (DataRow) it.next();
            value = dr.getString(0);
        }
        
        return value;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> getValueListBySqlname(String sqlname,String year, String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		HashMap<String,String> mymap=new HashMap();

		mymap.put("AREA_NAME", area_name);
		RQuery query = new RQuery(sqlname, mymap);
		DataTable dt = query.getDataTable();
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("name", dr.getString("name"));
			map.put("value", dr.getString("value"));
			result.add(map);
		}
		
		return result;
	}
	
	public String deleteInd(String id) {
		ZlTsfxIndicator ind = zlTsfxIndicatorDao.get(id);
		int order = Integer.valueOf(ind.getVieworder());
				
		String sql = "update ZlTsfxIndicator set VIEWORDER = '' where id = ?";
		zlTsfxIndicatorDao.executeUpdate(sql, new Object[]{id});
		
		String sql2 = "update ZlTsfxIndicator set VIEWORDER = (VIEWORDER - 1) where VIEWORDER > ?";
		zlTsfxIndicatorDao.executeUpdate(sql2, new Object[]{order});
		
		return "1";
	}
	
	public String resetIndOrder(Map<String,String> paramsMap) {
		String sql = "update ZlTsfxIndicator set VIEWORDER = ? where id = ?";
		
		for (Map.Entry<String,String> entry : paramsMap.entrySet()) {
			zlTsfxIndicatorDao.executeUpdate(sql, new Object[]{entry.getValue(), entry.getKey()});
		}
		
		return "1";
	}

	public String addInd(String id) {
		String sql = "select max(VIEWORDER) from ZL_TSFX_INDICATOR";
		QueryAdapter qa = new QueryAdapter();
		DataTable datatable = qa.executeQuery(sql.toString());
		int value=0;
		
		List<DataRow> rows = datatable.getRowList();
        Iterator<DataRow> it = rows.iterator();
        if (it.hasNext()) {
            DataRow dr = (DataRow) it.next();
            value = dr.getInt(0);
        }
        
        if (value < 7) {
    		String sql2 = "update ZlTsfxIndicator set VIEWORDER = ? where id = ?";
    		zlTsfxIndicatorDao.executeUpdate(sql2, new Object[]{value+1, id});
        } else {
        	return "2";
        }
        return "1";
	}
	
	public ZlTsfxIndicatorTree getOneChartInfo(String id) {
		ZlTsfxIndicatorTree ind = zlTsfxIndicatorTreeDao.get(id);
		return ind;
	}

	@SuppressWarnings("unchecked")
	public List<ZlTsfxIndicatorTree> getSonIndByParentId(String parentId) {
		String sql = "from ZlTsfxIndicatorTree where parendid =? order by id";
		List<ZlTsfxIndicatorTree> lis = zlTsfxIndicatorTreeDao.find(sql, new Object[]{parentId});
		
		return lis;
	}
	
	
	public List<ZtreeBean> getSearchTree(String tableName){
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		if("NGCS_WF_CMPLNTS_REQST_DETAIL".equals(tableName)) {
	        sql.append(" SELECT a02,a03 FROM NGCS_WF_CMPLNTS_REQST_DETAIL" + 
	        		" WHERE a02 IS NOT NULL " + 
	        		" GROUP BY  a02,a03" );

		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL".equals(tableName)) {
	        sql.append(" SELECT a02,a03 FROM NGCS_WF_CMPLNTS_ARC_DETAIL" + 
	        		" WHERE a02 IS NOT NULL " + 
	        		" GROUP BY  a02,a03" );

		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL_XC".equals(tableName)) {
			 sql.append(" SELECT b02,b03,b04 FROM NGCS_WF_CMPLNTS_ARC_DETAIL_XC" + 
			 		" WHERE B02 IS NOT NULL " + 
			 		" GROUP BY  b02,b03,b04" );

		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL_NO".equals(tableName)) {
			 sql.append(" SELECT a02,a03,a04 FROM NGCS_WF_CMPLNTS_ARC_DETAIL_NO" + 
				 		" WHERE a02 IS NOT NULL " + 
				 		" GROUP BY  a02,a03,a04" );

		}else if("ZL_SERVICE_REQUEST_NODE".equals(tableName)){
			sql.append(" select code01,name01,code02,name02,code03,name03 from ZL_SERVICE_REQUEST_NODE where name01 is not null group by code01,name01,code02,name02,code03,name03" );
		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL_SJ".equals(tableName)) {
			 sql.append(" SELECT a02,a03 FROM NGCS_WF_CMPLNTS_ARC_DETAIL_SJ" + 
				 		" WHERE a02 IS NOT NULL " + 
				 		" GROUP BY  a02,a03" );

		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL_SJ_NM".equals(tableName)) {
			 sql.append(" SELECT ACPT_CHNL_NM FROM NGCS_WF_CMPLNTS_ARC_DETAIL_SJ" + 
				 		" WHERE ACPT_CHNL_NM IS NOT NULL " );

		}else if("NGCS_WF_CMPLNTS_ARC_DETAIL_SJ_STAR".equals(tableName)) {
			 sql.append(" SELECT CUST_STARGRD_NM FROM NGCS_WF_CMPLNTS_ARC_DETAIL_SJ" + 
				 		" WHERE CUST_STARGRD_NM IS NOT NULL " );

		} else if("".equals(tableName)) {
			return null;
		}
		DataTable datatable = q.executeQuery(sql.toString());
		Map<String,String> level1Map =new HashMap<String,String>();
		Map<String,String> level2Map =new HashMap<String,String>();
		List<ZtreeBean> returnList =new ArrayList<ZtreeBean>();
        
		if(!"NGCS_WF_CMPLNTS_ARC_DETAIL_XC".equals(tableName)&&!"NGCS_WF_CMPLNTS_ARC_DETAIL_CF".equals(tableName)&&!"NGCS_WF_CMPLNTS_ARC_DETAIL_NO".equals(tableName)&&!"ZL_SERVICE_REQUEST_NODE".equals(tableName)) {
			for(DataRow dataRow:datatable.getRowList()) {
	        	String level1 =dataRow.getString(0);
	        	String level2 =dataRow.getString(1);
	        	if(!level1Map.containsKey(level1)) {
	        		level1Map.put(level1, level1);
	        		ZtreeBean zTreeBean =new ZtreeBean();
	                zTreeBean.setId(level1+"_1");
	                String parentid = "0";
	                zTreeBean.setpId(parentid);
	                zTreeBean.setName(level1);
	                zTreeBean.setOpen(false);
	                zTreeBean.setIconSkin("pIcon01");
	                zTreeBean.setLeaf(false);
	                zTreeBean.setCount(2L);
	                zTreeBean.setParent(true);
	                returnList.add(zTreeBean);
	        	}
	        	
	        	ZtreeBean zTreeBean =new ZtreeBean();
	            zTreeBean.setId(level2+"_2");
	            zTreeBean.setpId(level1+"_1");
	            zTreeBean.setName(level2);
	            zTreeBean.setOpen(false);
	            zTreeBean.setIconSkin("pIcon01");
	            zTreeBean.setLeaf(false);
	            zTreeBean.setCount(2L);
	            zTreeBean.setParent(false);
	            returnList.add(zTreeBean);
	        }
		}else if("ZL_SERVICE_REQUEST_NODE".equals(tableName)){
			for(DataRow dataRow:datatable.getRowList()) {
	        	String level1 =dataRow.getString(1);;//dataRow.getString(0);
	        	String name1 =dataRow.getString(1);
	        	String level2 =dataRow.getString(3);//dataRow.getString(2);
	        	String name2 =dataRow.getString(3);
	        	String level3 =dataRow.getString(5);//dataRow.getString(4);
	        	String name3 =dataRow.getString(5);
	        	if(!level1Map.containsKey(level1)) {
	        		level1Map.put(level1, level1);
	        		ZtreeBean zTreeBean =new ZtreeBean();
	                zTreeBean.setId(level1+"_1");
	                String parentid = "0";
	                zTreeBean.setpId(parentid);
	                zTreeBean.setName(name1);
	                zTreeBean.setOpen(false);
	                zTreeBean.setIconSkin("pIcon01");
	                zTreeBean.setLeaf(false);
	                zTreeBean.setCount(2L);
	                zTreeBean.setParent(true);
	                returnList.add(zTreeBean);
	        	}
	        	if(!level2Map.containsKey(level1+"|"+level2)) {
	        		level2Map.put(level1+"|"+level2, level2);
	        		ZtreeBean zTreeBean =new ZtreeBean();
	                zTreeBean.setId(level1+"_1_"+level2+"_2");
	                zTreeBean.setpId(level1+"_1");
	                zTreeBean.setName(name2);
	                zTreeBean.setOpen(false);
	                zTreeBean.setIconSkin("pIcon01");
	                zTreeBean.setLeaf(false);
	                zTreeBean.setCount(2L);
	                zTreeBean.setParent(false);
	                returnList.add(zTreeBean);
	        	}
	        	
	        	ZtreeBean zTreeBean =new ZtreeBean();
	            zTreeBean.setId(level1+"_1_"+level2+"_2_"+level3+"_3");
	            zTreeBean.setpId(level1+"_1_"+level2+"_2");
	            zTreeBean.setName(name3);
	            zTreeBean.setOpen(false);
	            zTreeBean.setIconSkin("pIcon01");
	            zTreeBean.setLeaf(false);
	            zTreeBean.setCount(2L);
	            zTreeBean.setParent(false);
	            returnList.add(zTreeBean);
			}
		}else {
			for(DataRow dataRow:datatable.getRowList()) {
	        	String level1 =dataRow.getString(0);
	        	String level2 =dataRow.getString(1);
	        	String level3 =dataRow.getString(2);
	        	if(!level1Map.containsKey(level1)) {
	        		level1Map.put(level1, level1);
	        		ZtreeBean zTreeBean =new ZtreeBean();
	                zTreeBean.setId(level1+"_1");
	                String parentid = "0";
	                zTreeBean.setpId(parentid);
	                zTreeBean.setName(level1);
	                zTreeBean.setOpen(false);
	                zTreeBean.setIconSkin("pIcon01");
	                zTreeBean.setLeaf(false);
	                zTreeBean.setCount(2L);
	                zTreeBean.setParent(true);
	                returnList.add(zTreeBean);
	        	}
	        	if(!level2Map.containsKey(level2)) {
	        		level2Map.put(level2, level2);
	        		ZtreeBean zTreeBean =new ZtreeBean();
	                zTreeBean.setId(level2+"_2");
	                zTreeBean.setpId(level1+"_1");
	                zTreeBean.setName(level2);
	                zTreeBean.setOpen(false);
	                zTreeBean.setIconSkin("pIcon01");
	                zTreeBean.setLeaf(false);
	                zTreeBean.setCount(2L);
	                zTreeBean.setParent(false);
	                returnList.add(zTreeBean);
	        	}
	        	
	        	ZtreeBean zTreeBean =new ZtreeBean();
	            zTreeBean.setId(level3+"_3");
	            zTreeBean.setpId(level2+"_2");
	            zTreeBean.setName(level3);
	            zTreeBean.setOpen(false);
	            zTreeBean.setIconSkin("pIcon01");
	            zTreeBean.setLeaf(false);
	            zTreeBean.setCount(2L);
	            zTreeBean.setParent(false);
	            returnList.add(zTreeBean);
	        }
		}
 
        return returnList;
	}
	
	public IDao<ZlTsfxIndicator> getZlTsfxIndicatorDao() {
		return zlTsfxIndicatorDao;
	}

	public void setZlTsfxIndicatorDao(IDao<ZlTsfxIndicator> zlTsfxIndicatorDao) {
		this.zlTsfxIndicatorDao = zlTsfxIndicatorDao;
	}

	@Override
	public List<ZtreeBean> getTsfxTree(String parentId) {
		List<ZtreeBean> returnList =new ArrayList<ZtreeBean>();
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ZL_TSFX_INDICATOR_TREE ");
		if(parentId != null){
			sql.append(" where PARENDID = ? ");	
		}
		DataTable datatable = q.executeQuery(sql.toString(),parentId);
		for(DataRow dataRow:datatable.getRowList()) {
			ZtreeBean zTreeBean =new ZtreeBean();
			zTreeBean.setId(dataRow.getString("id"));
			zTreeBean.setName(dataRow.getString("name"));
			zTreeBean.setpId(StringUtils.isBlank(dataRow.getString("PARENDID"))?"0":dataRow.getString("PARENDID"));
			zTreeBean.setOpen(false);
			zTreeBean.setIconSkin("pIcon01");
			zTreeBean.setLeaf(false);
			zTreeBean.setCount(2L);
			zTreeBean.setParent(true);
			returnList.add(zTreeBean);
		}
		return returnList;
	}

	@Override
	public List<Map<String, String>> getTsfxAmount(String respDept, String a12, String areaName, String cname, String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" select mon,ACPT_TIME, count(*) value from  ");
		sql.append(" (  ");
		sql.append("    select ACPT_TIME,MON,RESP_DEPT,AREA_NAME,NAME from NGCS_WF_CMPLNTS_COLLECT ");
		sql.append(" ) where 1=1  ");
		if(StringUtils.isNotBlank(respDept)){
			sql.append(" and resp_dept like '" + respDept + "%'");
		}
		if(StringUtils.isNotBlank(a12)){
			String A12 = this.getA12(a12);
			sql.append(" and " + A12);
		}
		if(StringUtils.isNotBlank(areaName)){
			sql.append(" and area_name like '" + areaName + "%'");
		}
		if(StringUtils.isNotBlank(cname)){
			sql.append(" and name like '" + cname + "%'");
		}
		
		if(StringUtils.isNotBlank(time)){
			if(time.length()==7){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date = null;
				try {
					 date = sdf.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String data[] = new String[12];
				for(int i = 0;i<12;i++){
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.MONTH,-i);//在日历的月份上增加12个月
					String format = sdf.format(c.getTime());
					data[i]=format+"%";
				}
				//System.out.println(data);
				sql.append(" and (acpt_time like '"+data[0]+"' or acpt_time like '"+data[1]+"' or acpt_time like '"+data[2]+"' or acpt_time like '"+data[3]+"' or acpt_time like '"+data[4]+"' or acpt_time like '"+data[5]+"'or acpt_time like '"+data[6]+"'or acpt_time like '"+data[7]+"'or acpt_time like '"+data[8]+"'or acpt_time like '"+data[9]+"'or acpt_time like '"+data[10]+"'or acpt_time like '"+data[11]+"')");
			}else{
				sql.append(" and acpt_time like '" + time + "%'");
			}
		}
		sql.append(" GROUP BY mon,acpt_time order by acpt_time  ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            /*map.put("name", dr.getString("mon"));*/
            map.put("name", dr.getString("acpt_time"));
            map.put("value", dr.getString("value"));
            result.add(map);
        }
		return result;
	}
	
	@Override
	public List<Map<String, String>> GetLDLine(String respDept, String a12, String areaName, String cname,String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" select mon, count(*) value from  ");
		sql.append(" (  ");
		sql.append("    select ACPT_TIME,MON,RESP_DEPT,AREA_NAME,NAME from NGCS_WF_CMPLNTS_COLLECT ");
		sql.append(" ) where 1=1  ");
		if(StringUtils.isNotBlank(respDept)){
			sql.append(" and resp_dept like '" + respDept + "%'");
		}
		/*if(StringUtils.isNotBlank(a12)){
			String A12 = this.getA12(a12);
			sql.append(" and " + A12);
		}*/
		if(StringUtils.isNotBlank(areaName)){
			sql.append(" and area_name like '" + areaName + "%'");
		}
		if(StringUtils.isNotBlank(cname)){
			sql.append(" and name like '" + cname + "%'");
		}
		//*************************************************************************
		if(StringUtils.isNotBlank(time)){
			if(time.length()==7){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date = null;
				try {
					 date = sdf.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String data[] = new String[12];
				for(int i = 0;i<12;i++){
					Calendar c = Calendar.getInstance();
					c.setTime(date);
					c.add(Calendar.MONTH,-i);//在日历的月份上增加12个月
					String format = sdf.format(c.getTime());
					data[i]=format+"%";
				}
				//System.out.println(data);
				sql.append(" and (ACPT_TIME like '"+data[0]+"' or ACPT_TIME like '"+data[1]+"' or ACPT_TIME like '"+data[2]+"' or ACPT_TIME like '"+data[3]+"' or ACPT_TIME like '"+data[4]+"' or ACPT_TIME like '"+data[5]+"'or ACPT_TIME like '"+data[6]+"'or ACPT_TIME like '"+data[7]+"'or ACPT_TIME like '"+data[8]+"'or ACPT_TIME like '"+data[9]+"'or ACPT_TIME like '"+data[10]+"'or ACPT_TIME like '"+data[11]+"')");
			}else{
				sql.append(" and ACPT_TIME like '" + time + "%'");
			}
		}
		
		
		//*************************************************************************
		/*
		if(StringUtils.isNotBlank(time)){
			
		}*/
		sql.append(" GROUP BY mon,ACPT_TIME order by ACPT_TIME  ");
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("mon"));
            map.put("value", dr.getString("value"));
            result.add(map);
        }
		return result;
	}

	@Override
	public String getA12(String a12) {
		QueryAdapter qa = new QueryAdapter();
		String sql = "select * from ZL_TSFX_INDICATOR_TREE t where t.name = ?";
		DataTable dt = qa.executeQuery(sql,a12);
		List<DataRow> rows = dt.getRowList();
		Iterator<DataRow> it = rows.iterator();
		String value = "";
        if (it.hasNext()) {
            DataRow dr = it.next();
            value = dr.getString("CONDITION");
        }
		return value;
	}

	@Override
	public List<Map<String, String>> getTsfxFocus(String respDept, String a12, String areaName, String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		QueryAdapter qa = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		StringBuffer sqlYear = new StringBuffer();
		sql.append(" select * from ( ");
		//sql.append(" select name , resendt.REPORTSTATUS ,resendt.ID ,count(*) value from ( " );
		sql.append(" select name , count(*) value from ( " );
		sql.append(" select ACPT_TIME,RESP_DEPT,AREA_NAME,NAME from NGCS_WF_CMPLNTS_COLLECT");
		//sql.append(" ) left join NGCS_WF_CMPLNTS_RESEND_DETAIL resendt on resendt.LABELCATEGORY = name where 1 = 1 ");
		sql.append(" ) where 1 = 1 ");
		if(StringUtils.isNotBlank(respDept)){
			sql.append(" and resp_dept like '" + respDept + "%'");
		}
		if(StringUtils.isNotBlank(a12)){
			String A12 = this.getA12(a12);
			sql.append(" and " + A12);
		}
		if(StringUtils.isNotBlank(areaName)){
			sql.append(" and area_name like '" + areaName + "%'");
		}
		if(StringUtils.isNotBlank(time)){
			sql.append(" and ACPT_TIME like '" + time + "%'");
		}
		
		//sql.append(" group by name ,REPORTSTATUS , ID order by value Desc ");
		sql.append(" group by name order by value Desc ");
		
		sql.append(" ) where rownum<=10 ");
		
		DataTable dt = qa.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();

        int countnum=0;
        while (it.hasNext()) {
        	countnum++;
        	if(countnum>10)
        		break;
        	Map<String, String> map = new HashMap<String,String>();
            DataRow dr = (DataRow) it.next();
            map.put("name", dr.getString("name"));
            map.put("value", dr.getString("value"));
            map.put("than", "");
            map.put("status", "");
            map.put("id", dr.getString("ID"));
            result.add(map);
        }
		return result;
	}
	
	public IDao<ZlTsfxIndicatorTree> getZlTsfxIndicatorTreeDao() {
		return zlTsfxIndicatorTreeDao;
	}

	public void setZlTsfxIndicatorTreeDao(IDao<ZlTsfxIndicatorTree> zlTsfxIndicatorTreeDao) {
		this.zlTsfxIndicatorTreeDao = zlTsfxIndicatorTreeDao;
	}

	@Override
	public List<Map<String, String>> ajaxGetTshbFocus(String respDept, String a12, String area_name, String time, String cname,String type) {
		String lastTime = "";
		String than="";
		StringBuffer sqlYear = new StringBuffer();
		QueryAdapter qa = new QueryAdapter();
		SimpleDateFormat format = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> map=new HashMap<String, String>();
		// 所选年
		if (time.length() == 4) {
			format = new SimpleDateFormat("yyyy");
			try {
				Date date = format.parse(time);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.YEAR, -1); // 年份减1
				Date time2 = c.getTime();
				lastTime = format.format(time2);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (time.length() > 4) {
			format = new SimpleDateFormat("yyyy-MM");
			try {
				Date date = format.parse(time);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, -1); // 月份减1
				Date time2 = c.getTime();
				lastTime = format.format(time2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sqlYear = new StringBuffer("");
		sqlYear.append(" select * from ( " );
		sqlYear.append(" select count(*) value ,'1' AS MON from ( " );
		sqlYear.append(" select ACPT_TIME,RESP_DEPT,AREA_NAME,NAME from NGCS_WF_CMPLNTS_COLLECT");
		sqlYear.append(" )  where 1 = 1 ");
		if(StringUtils.isNotBlank(respDept)){
			sqlYear.append(" and resp_dept like '" + respDept + "%'");
		}
		if(StringUtils.isNotBlank(a12)){
			String A12 = this.getA12(a12);
			sqlYear.append(" and " + A12);
		}
		if(StringUtils.isNotBlank(area_name)){
			sqlYear.append(" and area_name like '" + area_name + "%'");
		}
		sqlYear.append(" and ACPT_TIME like '" + time + "%'");
		sqlYear.append(" and name like '" + cname + "%'");
		sqlYear.append(" group by name ,'REPORTSTATUS' , 'ID' order by value Desc ");
		sqlYear.append(" ) " );
		sqlYear.append(" union all " );
		sqlYear.append(" select * from ( " );
		sqlYear.append(" select count(*) value ,'2' AS MON from ( " );
		sqlYear.append(" select ACPT_TIME,RESP_DEPT,AREA_NAME,NAME from NGCS_WF_CMPLNTS_COLLECT");
		sqlYear.append(" )  where 1 = 1 ");
		if(StringUtils.isNotBlank(respDept)){
			sqlYear.append(" and resp_dept like '" + respDept + "%'");
		}
		if(StringUtils.isNotBlank(a12)){
			String A12 = this.getA12(a12);
			sqlYear.append(" and " + A12);
		}
		if(StringUtils.isNotBlank(area_name)){
			sqlYear.append(" and area_name like '" + area_name + "%'");
		}
		sqlYear.append(" and ACPT_TIME like '" + lastTime + "%'");
		sqlYear.append(" and name like '" + cname + "%'");
		sqlYear.append(" group by name ,'REPORTSTATUS' , 'ID' order by value Desc ");
		sqlYear.append(" ) " );
		DataTable dtY = qa.executeQuery(sqlYear.toString());
		List<DataRow> rowY = dtY.getRowList();
		if(rowY.size()==1&&rowY.get(0).getString("MON").equals("1")){
			than="0%";
		}else if(rowY.size()==2){
			Double parseDouble = Double.parseDouble( rowY.get(0).getString("VALUE"));//所选年月
	        Double parseDouble2 = Double.parseDouble( rowY.get(1).getString("VALUE"));//上一年月
	        
	        String than_format="";
	        if(parseDouble2!=0){
	        	 than_format = String.format("%.2f",((parseDouble - parseDouble2)/parseDouble2)*100);//保留小数点后两位
	        }else{
	        	 than_format="0";
	        }
	        than = String.valueOf( than_format + "%");
		}else{
			than="100%";
		}
		map.put("than", than);
		map.put("trnum", type);
		result.add(map);
		return result;
	}

	@Override
	public List<Map<String, String>> proData(String nameArray, String time, String respDept, String a12,
			String areaName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getSearchCon(String dataField) {
		List<String> result = new ArrayList<String>();
		QueryAdapter qa = new QueryAdapter();
		String sql = " select * from ( select distinct NAME from NGCS_WF_CMPLNTS_COLLECT) t where t.name like '%" + dataField + "%' and rownum <8 order by name";
		DataTable dt = qa.executeQuery(sql);
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
        	result.add(dr.getString("NAME"));
        }
		return result;
	}
	
}
