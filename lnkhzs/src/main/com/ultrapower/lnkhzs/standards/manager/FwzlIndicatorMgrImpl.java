package com.ultrapower.lnkhzs.standards.manager;

import com.ultrapower.lnkhzs.standards.model.FwzlIndicator;
import com.ultrapower.lnkhzs.standards.service.IFwzlIndicatorService;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicator;
import com.ultrapower.lnkhzs.tsgd.service.ITsgdService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;

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
public class FwzlIndicatorMgrImpl implements IFwzlIndicatorService {
	
	private ITsgdService tsgdService;
	

    public ITsgdService getTsgdService() {
		return tsgdService;
	}

	public void setTsgdService(ITsgdService tsgdService) {
		this.tsgdService = tsgdService;
	}

	private IDao<FwzlIndicator> fwzlIndicatorDao;

    public void setFwzlIndicatorDao(IDao<FwzlIndicator> fwzlIndicatorDao) {
        this.fwzlIndicatorDao = fwzlIndicatorDao;
    }

	@Override
	public List<FwzlIndicator> getPiesInfo(String type) {
		String sql = "from FwzlIndicator f where f.type = ? and f.vieworder is not null order by f.vieworder";
		List<FwzlIndicator> list= fwzlIndicatorDao.find(sql,type);
		return list;
	}

	@Override
	public String getValueByName(String name, String area_name, String time,String type) {
		String sql = "";
		String center = "SELECT ROW_NUMBER() OVER("
						+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
						+ ") rn, d.* FROM dw_home_if_boce d"
						+ ") WHERE rn = 1 and type = '"+type+"' ";
						if(StringUtils.isNotBlank(area_name)){
							/*center+=" and city = '" + area_name + "' ";*/
							center+=" and city LIKE '%" + area_name + "%' ";
						}
						if(StringUtils.isNotBlank(time)){
							if(time.contains("-")){
								center+=" and statistical_time like '" + time.replace("-", "") + "%' ";
								sql = "select  f.id,f.name as name,t.index_value as value ,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select f.* from zl_fwzl_indicator f where type = '"+type+"' ) f on t.index_name = f.name where t.index_name = '"+name+"'" ;
							}else{
								center+=" and statistical_time like '" + time + "%' group by city , type, index_name";
								sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT city , type, index_name,round(sum(index_value) / 12,3) as index_value FROM ("+center+" ) t right join (select f.* from zl_fwzl_indicator f where type = '"+type+"' ) f on t.index_name = f.name  where t.index_name = '"+name+"'" ;
							}
								
						}
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			value = dr.getString("value");
		}
		if(value.isEmpty()||value==null) {
			value = "当前无数据";
		}
		return value;
	}

	@Override
	public List<Map<String, String>> getAllPiesInfo(String type, String time, String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = "";
//		String center = "SELECT ROW_NUMBER() OVER("
//						+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
//						+ ") rn, d.* FROM dw_home_if_boce d"
//						+ ") WHERE rn = 1 and type = '"+type+"' ";
//						if(StringUtils.isNotBlank(area_name)){
//							center+=" and city = '" + area_name + "' ";
//						}
//						if(StringUtils.isNotBlank(time)){
//							if(time.contains("-")){
//								center+=" and statistical_time like '" + time.replace("-", "") + "%' ";
//								sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
//							}else{
//								center+=" and statistical_time like '" + time + "%' group by city , type, index_name";
//								sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT city , type, index_name,round(sum(index_value) / 12,3) as index_value FROM ("+center+" ) t  right join (select * from zl_fwzl_indicator where type = '"+type+"') f on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
//							}
//								
//						}
		
		
		
//		String center = "SELECT ROW_NUMBER() OVER("
//				+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
//				+ ") rn, d.* FROM dw_home_if_boce d"
//				+ ") WHERE rn = 1 and type = '"+type+"' ";
		String center= "SELECT index_name,count(index_value)	index_value FROM dw_home_if_boce  "
				+ "WHERE TYPE = '"+type+"'  GROUP BY index_name";
		//sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
		sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( "+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String name="";
		String valueName="";
		String typeName="";
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			name=dr.getString("name");
			if(type.equals("idc")){
				name=name.toUpperCase();
			}
			map.put("name", name);
			
			valueName="";
			if(name.indexOf("钻五")!=-1){
				valueName=" TIMEZ5 ";
			}else if(name.indexOf("五星")!=-1||name.indexOf("D级")!=-1){
				valueName=" TIMEA5 ";
			}else if(name.indexOf("四星")!=-1||name.indexOf("C级")!=-1){
				valueName=" TIMEA4 ";
			}else if(name.indexOf("三星")!=-1||name.indexOf("B级")!=-1){
				valueName=" TIMEA3 ";
			}else if(name.indexOf("二星")!=-1||name.indexOf("A级")!=-1){
				valueName=" TIMEA2 ";
			}else{
				valueName="";
			}
			
			if(valueName.isEmpty()){
				//正常指标
				map.put("value", dr.getString("value"));
			}else{
				//idc非正常指标ABCD 二三四五
				typeName = name.substring( 0, name.length()-4);
//				String idcsql=" SELECT "+valueName+" AS VALUE FROM DW_IDC_TYPE_LEVEL WHERE IDC_NAME = '"+area_name+"' "
//						+ "AND MONTH = '"+time.replace("-", "")+"'AND TYPE_NAME = '"+typeName+"' ";
				String idcsql= " SELECT count(1) AS VALUE FROM	DW_IDC_TYPE_LEVEL WHERE TYPE_NAME = '"+typeName+"' ";
				QueryAdapter q1 = new QueryAdapter();
				DataTable dt1 = q1.executeQuery(idcsql);
				List<DataRow> rowList1 = dt1.getRowList();
				Iterator<DataRow> it1 = rowList1.iterator();
				if (it1.hasNext()) {
					DataRow dr1 = (DataRow) it1.next();
					map.put("value", dr1.getString("value"));
				}else{
					map.put("value", "");
				}
			}
			
			map.put("id", dr.getString("id"));
			map.put("vieworder", dr.getString("vieworder"));
			result.add(map);
		}
		return result;
	}
	
	
	@Override
	public List<Map<String, String>> getAllLineInfo(String name,String type,String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = "";
		
		/*String center="  select time as mon, count(1) as value from("
				 		+ "select f.name as name, t.index_value as value, to_char(to_date(statistical_time,'yyyy-MM-dd hh24:mi:ss'),'yyyy-mm') AS time from ( SELECT * FROM (SELECT  d.* FROM dw_home_if_boce d) WHERE  type = '"+type+"'   ) t right join (select f.* from zl_fwzl_indicator f where type = '"+type+"' ) f on t.index_name = f.name" 
				 		+ ") where name = '"+name+"' GROUP BY time ORDER BY time,value desc ";*/
		
		/*String center=" SELECT * FROM ("
				+ "select TO_CHAR(TRUNC(ADD_MONTHS(SYSDATE, -ROWNUM+1), 'mm'),'yyyy-mm') as mons FROM dual CONNECT by ROWNUM<=12 ) d left join ("
						+ "  select time as mon, round(avg(translate(index_value, '0123456789.'||index_value, '0123456789.')),2) as value from(select f.name as name, t.index_value , to_char(to_date(statistical_time,'yyyy-MM-dd hh24:mi:ss'),'yyyy-mm') AS time from ( SELECT * FROM (SELECT  d.* FROM dw_home_if_boce d) WHERE  type = '"+type+"' AND CITY = '"+area_name+"'  ) t right join (select f.* from zl_fwzl_indicator f where type = '"+type+"' ) f on t.index_name = f.name) where name = '"+name+"' GROUP BY time ORDER BY time desc" 
						+ ") t ON t.mon = d.mons ORDER BY d.mons desc ";*/
		String center="";
		
		if(type.equals("idc")){
			name=name.toLowerCase();
		}
		center=" SELECT * FROM ( SELECT "
				+ " TO_CHAR( TRUNC( ADD_MONTHS( SYSDATE, - ROWNUM + 1 ), 'mm' ), 'yyyy-mm' ) AS mons  "
				+ " FROM dual CONNECT BY ROWNUM <= 12 ) d LEFT JOIN ( "
				+ " SELECT to_char( to_date( statistical_time, 'yyyy-MM-dd hh24:mi:ss' ), 'yyyy-mm' ) AS mon, index_value as value "
				+ " FROM ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.city, d.TYPE, d.index_name, substr( d.STATISTICAL_TIME, 0, 6 ) "
				+ " ORDER BY to_date( d.STATISTICAL_TIME, 'yyyyMMdd' ) DESC  ) rn, d.*  FROM dw_home_if_boce d ) WHERE "
				+ " rn = 1  AND TYPE = '"+type+"'  AND city LIKE '%"+area_name+"%'  AND index_name = '"+name+"' ) t ON t.mon = d.mons  "
				+ " ORDER BY d.mons DESC ";
		
		
		
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(center);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("value", dr.getString("value"));
			map.put("mon", dr.getString("mons"));
			result.add(map);
		}
		return result;
	}

	@Override
	public String addInd(String id,String type) {
		if(id.indexOf("IDC")!=-1)
			id=id.toLowerCase();
		FwzlIndicator ind = fwzlIndicatorDao.findUnique("from FwzlIndicator where name = ? and type = ?", id,type);
		String valueOf = String.valueOf(getOrder());
		ind.setVieworder(String.valueOf(getOrder()));
		fwzlIndicatorDao.saveOrUpdate(ind);
        return "1";
	}

	public FwzlIndicator getFwzlIndicator(String id){
		return null;
	}
	
	public int getOrder(){
		String sql = "select max(VIEWORDER) from ZL_FWZL_INDICATOR";
		QueryAdapter qa = new QueryAdapter();
		DataTable datatable = qa.executeQuery(sql.toString());
		int value=0;
		
		List<DataRow> rows = datatable.getRowList();
        Iterator<DataRow> it = rows.iterator();
        if (it.hasNext()) {
            DataRow dr = (DataRow) it.next();
            value = dr.getInt(0);
        }
        return value+1;
	}
	
	@Override
	public String deleteInd(String id) {
		FwzlIndicator ind = fwzlIndicatorDao.get(id);
		int order = Integer.valueOf(ind.getVieworder());
				
		String sql = "update FwzlIndicator set VIEWORDER = '' where id = ?";
		fwzlIndicatorDao.executeUpdate(sql, new Object[]{id});
		
		String sql2 = "update FwzlIndicator set VIEWORDER = (VIEWORDER - 1) where VIEWORDER > ?";
		fwzlIndicatorDao.executeUpdate(sql2, new Object[]{order});
		
		return "1";
	}

	@Override
	public List<Map<String, String>> getAllPiesInfoInter(String type, String time, String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		
		String sql = " SELECT * FROM zl_fwzl_indicator WHERE TYPE = '"+type+"'";
		
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		Map<String, String> map = null;
		while (it.hasNext()) {
			 map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			String string = dr.getString("name");
			String sqlname = dr.getString("sql");
			if(sqlname!=null && !(sqlname.isEmpty())){
				//String value = tsgdService.getValueBySqlname(sqlname, area_name, time);
				String value = tsgdService.getValueBySqlname(sqlname,"","");
				map.put("value", value);
			}else{
//				String mysql = "";
//				String center = "SELECT ROW_NUMBER() OVER("
//								+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
//								+ ") rn, d.* FROM dw_home_if_boce d"
//								+ ") WHERE rn = 1 and type = '"+type+"' ";
//								if(StringUtils.isNotBlank(area_name)){
//									center+=" and city = '" + area_name + "' ";
//								}
//								if(StringUtils.isNotBlank(time)){
//									if(time.contains("-")){
//										center+=" and statistical_time like '" + time.replace("-", "") + "%' ";
//										mysql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"' AND name = '"+string+"' ) f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
//									}else{
//										center+=" and statistical_time like '" + time + "%' group by city , type, index_name";
//										mysql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT city , type, index_name,round(sum(index_value) / 12,3) as index_value FROM ("+center+" ) t  right join (select * from zl_fwzl_indicator where type = '"+type+"' AND name = '"+string+"' ) f on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
//									}
//										
//								}
				
				
				String mysql = "";
				String center = "SELECT ROW_NUMBER() OVER("
								+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
								+ ") rn, d.* FROM dw_home_if_boce d"
								+ ") WHERE rn = 1 and type = '"+type+"' ";
								mysql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"' AND name = '"+string+"' ) f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
				
				
//				String center = "SELECT ROW_NUMBER() OVER("
//										+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
//										+ ") rn, d.* FROM dw_home_if_boce d"
//										+ ") WHERE rn = 1 and type = '"+type+"' ";
//				String mysql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
//								
//				
				QueryAdapter myq = new QueryAdapter();
				DataTable mydt = myq.executeQuery(mysql);
				List<DataRow> myrowList = mydt.getRowList();
				Iterator<DataRow> myit = myrowList.iterator();
				String myvalue="";
				while (myit.hasNext()) {
					Map<String, String> mymap = new HashMap<String, String>();
					DataRow mydr = (DataRow) myit.next();
					myvalue=mydr.getString("value");
					
				}
				if(myvalue.isEmpty()){
					map.put("value", null);
				}else{
					map.put("value", myvalue);
				}
				
				//map.put("value", null);
			}
			
			map.put("name", dr.getString("name"));
			map.put("id", dr.getString("id"));
			map.put("vieworder", dr.getString("vieworder"));
			
			/*map.put("name", dr.getString("name"));
			map.put("value", dr.getString("value"));
			map.put("id", dr.getString("id"));*/
			
			result.add(map);
		}
		return result;
	}
	
	
	@Override
	public String getValueByNameInter(String name, String area_name, String time,String type) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = " SELECT * FROM zl_fwzl_indicator WHERE NAME = '"+name+"' and TYPE = '"+type+"'";
		
		
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			String sqlname = dr.getString("sql");
			if(sqlname!=null && !(sqlname.isEmpty())){
				 value = tsgdService.getValueBySqlname(sqlname, area_name, time);
			}else{
				 value = dr.getString("value");
			}
			
		}
		if(value.isEmpty()||value==null) {
			value="当前无数据";
		}
		return value;
	}

	@Override
	public List<Map<String, String>> getAllLineInfoInter(String name, String type,String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = " SELECT * FROM zl_fwzl_indicator WHERE NAME = '"+name+"' and TYPE = '"+type+"'";
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			String sqlname = dr.getString("sql");
			if(sqlname!=null && !(sqlname.isEmpty())){
				sqlname=sqlname.substring(0,sqlname.length()-6)+"_MON.query";
				HashMap<String, Object> param=new HashMap<String, Object>();
				param.put("AREA_NAME", area_name);
				/*param.put("acpt_time", month);*/
				//param.put("acpt_time", year);
				RQuery rq = new RQuery(sqlname, param);
				DataTable dt1 = rq.getDataTable();
				List<DataRow> rowList1 = dt1.getRowList();
				Iterator<DataRow> it1 = rowList1.iterator();
				while (it1.hasNext()) {
					Map<String, String> map = new HashMap<String, String>();
					DataRow dr1 = (DataRow) it1.next();
					map.put("value", dr1.getString("value"));
					map.put("mon", dr1.getString("mons"));
					result.add(map);
				}
			}
			
		}
		return result;
	}
	
	
	
	@Override
	public List<Map<String, String>> getAllPieInfo(String name,String type,String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		if(type.equals("idc")){
			name=name.toLowerCase();
		}
		
		String sql = "";
		
		/*String center="  SELECT city, round( avg( translate( index_value, '0123456789.' || index_value, '0123456789.' )), 2 ) AS value  FROM( SELECT t.city,f.name AS name,t.index_value,to_char( to_date( statistical_time, 'yyyy-MM-dd hh24:mi:ss' ), 'yyyy-mm' ) AS time  FROM ( SELECT * FROM ( SELECT d.* FROM dw_home_if_boce d ) WHERE TYPE = '"+type+"' ) t RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name  ) WHERE name = '"+name+"'   ";
		
		if(StringUtils.isNotBlank(time)){
				center+=" and time like '" + time + "%'";
				//sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT city , type, index_name,round(sum(index_value) / 12,3) as index_value FROM ("+center+" ) t  right join (select * from zl_fwzl_indicator where type = '"+type+"') f on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
		}
		center+=" GROUP BY city";*/
		
		if(time.length()==4){
			
			
			
			//年
			sql="SELECT city, round( sum( index_value ) / 12, 3 ) AS value  "
					+ " FROM ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.city, d.TYPE, d.index_name, substr( d.STATISTICAL_TIME, 0, 6 ) "
					+ " ORDER BY to_date( d.STATISTICAL_TIME, 'yyyyMMdd' ) DESC  ) rn, d.*  FROM dw_home_if_boce d )  "
					+ " WHERE rn = 1  AND TYPE = '"+type+"'  AND statistical_time LIKE '"+time+"%' AND INDEX_NAME = '"+name+"' GROUP BY city";
		}else if(time.length()==7){
			//月
			time=time.replaceAll("-", "");
			sql="SELECT city, max(INDEX_VALUE) as value  FROM ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.city, d.TYPE, d.index_name, substr( d.STATISTICAL_TIME, 0, 6 ) "
					+ " ORDER BY to_date( d.STATISTICAL_TIME, 'yyyyMMdd' ) DESC  ) rn, d.*  FROM dw_home_if_boce d  )  "
					+ " WHERE rn = 1  AND TYPE = '"+type+"' AND statistical_time LIKE '"+time+"%' AND index_name = '"+name+"' GROUP BY CITY";
		}
		QueryAdapter q = new QueryAdapter();
		//DataTable dt = q.executeQuery(center);
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("city", dr.getString("city"));
			map.put("value", dr.getString("value"));
			result.add(map);
		}
		return result;
	}
	
	@Override
	public List<Map<String, String>> getAllPieInfoInter(String name,String time, String type) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = " SELECT * FROM zl_fwzl_indicator WHERE NAME = '"+name+"' and TYPE = '"+type+"'";
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			String sqlname = dr.getString("sql");
			if(sqlname!=null && !(sqlname.isEmpty())){
				sqlname=sqlname.substring(0,sqlname.length()-6)+"_PIE.query";
				HashMap<String, Object> param=new HashMap<String, Object>();
				param.put("time", time);
				/*param.put("acpt_time", month);*/
				//param.put("acpt_time", year);
				RQuery rq = new RQuery(sqlname, param);
				DataTable dt1 = rq.getDataTable();
				List<DataRow> rowList1 = dt1.getRowList();
				Iterator<DataRow> it1 = rowList1.iterator();
				while (it1.hasNext()) {
					Map<String, String> map = new HashMap<String, String>();
					DataRow dr1 = (DataRow) it1.next();
					map.put("value", dr1.getString("VALUE"));
					map.put("city", dr1.getString("CITY"));
					result.add(map);
				}
			}
			
		}
		return result;
	}

	@Override
	public Map<String, String> getUnitByName(String name, String area_name, String time, String type) {
		Map<String, String> map=new HashMap<String, String>();
		String sql = "";
		sql="SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' AND NAME = '" + name + "'";
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String unit="";
		String threshold = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			unit=dr.getString("unit");
			threshold=dr.getString("threshold");
		}
		map.put("unit", unit);
		map.put("threshold", threshold);
		return map;
	}

	@Override
	public List<Map<String, String>> getAllLineInfoInterNew(String name, String type) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = " SELECT a.*,b.INDEX_VALUE AS VALUE FROM "
				+ "(SELECT TO_CHAR( TRUNC( ADD_MONTHS( SYSDATE, - ROWNUM + 1 ), 'mm' ), 'yyyymm' ) AS mons FROM dual CONNECT BY ROWNUM <= 12 ) a "
				+ "LEFT JOIN "
				+ "(SELECT * FROM dw_home_if_boce WHERE INDEX_NAME = '"+name+"' and TYPE = '"+type+"' ) b "
				+ "ON a.mons= SUBSTR(b.STATISTICAL_TIME,0,6)";
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("value", dr.getString("value"));
			map.put("mon", dr.getString("mons"));
			result.add(map);
		}
		return result;
	}

	@Override
	public String getValueByNameFWQQ(String name, String area_name, String time, String type) {
		
		String valueName = "";
		String typeName = "";
		typeName = name.substring( 0, name.length()-4);
		if(name.indexOf("钻五")!=-1){
			valueName=" TIMEZ5 ";
		}
		if(name.indexOf("五星")!=-1||name.indexOf("D级")!=-1){
			valueName=" TIMEA5 ";
		}
		if(name.indexOf("四星")!=-1||name.indexOf("C级")!=-1){
			valueName=" TIMEA4 ";
		}
		if(name.indexOf("三星")!=-1||name.indexOf("B级")!=-1){
			valueName=" TIMEA3 ";
		}
		if(name.indexOf("二星")!=-1||name.indexOf("A级")!=-1){
			valueName=" TIMEA2 ";
		}
		
		String sql = "";
		String center=" SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, MONTH "
				+ "ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC ) rn, '"+name+"' AS index_name ,d.* FROM DW_IDC_TYPE_LEVEL d "
				+ " where TYPE_NAME = '"+typeName+"')"
				+ " WHERE rn = 1 ";
		if(StringUtils.isNotBlank(area_name)){
			center+= " AND IDC_NAME LIKE '%"+area_name+"%' ";
		}
		if(StringUtils.isNotBlank(time)){
			if(time.contains("-")){
				center+= "  AND MONTH LIKE '"+time.replace("-", "")+"%' ";
				sql="  SELECT f.id, f.name AS name, "+valueName+" AS value, f.vieworder "
				+ " FROM ( SELECT * FROM ( "+center+") t "
				+ " RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name  "
				+ "WHERE t.index_name = '"+name+"'";
			}else{
				center+= "  AND MONTH LIKE '"+time+"%' GROUP BY IDC_NAME, index_name  ";
				sql=" SELECT f.id, f.name AS name, t.index_value AS value, f.vieworder "
				+ " FROM ( SELECT IDC_NAME AS city, index_name, round( sum( "+valueName+" ) / 12, 3 ) AS index_value  "
				+ "FROM ( "+center+") t "
				+ " RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name  "
				+ "WHERE t.index_name = '"+name+"'";
			}
		}

		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			value = dr.getString("value");
		}
		if(value.isEmpty()||value==null) {
			value = "当前无数据";
		}
		return value;

	}

	@Override
	public String getValueByNameJFCG(String name, String area_name, String time, String type) {
		
		
		
		String valueName = "";
		String typeName = "";
		valueName = name.substring( name.length()-3, name.length()-1);
		typeName = name.substring( 0, name.length()-4);
		
		
		String sql = "";
		String center=" SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, MONTH "
				+ "ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC ) rn, '"+name+"' AS index_name ,d.* FROM DW_IDC_JFCGXYSC d )"
				+ " WHERE rn = 1  AND IDC_LEVEL = '"+valueName+"' AND TYPE_NAME = '"+typeName+"'";
		if(StringUtils.isNotBlank(area_name)){
			center+= " AND IDC_NAME LIKE '%"+area_name+"%' ";
		}
		if(StringUtils.isNotBlank(time)){
			if(time.contains("-")){
				center+= "  AND MONTH LIKE '"+time.replace("-", "")+"%' ";
				sql="  SELECT f.id, f.name AS name, IDC_VALUE AS value, f.vieworder "
				+ " FROM ( SELECT * FROM ( "+center+") t "
				+ " RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name  "
				+ "WHERE t.index_name = '"+name+"'";
			}else{
				center+= "  AND MONTH LIKE '"+time+"%' GROUP BY IDC_NAME, index_name  ";
				sql=" SELECT f.id, f.name AS name, t.index_value AS value, f.vieworder "
				+ " FROM ( SELECT IDC_NAME AS city, index_name, round( sum( IDC_VALUE ) / 12, 3 ) AS index_value  "
				+ "FROM ( "+center+") t "
				+ " RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name  "
				+ "WHERE t.index_name = '"+name+"'";
			}
		}

		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			value = dr.getString("value");
		}
		return value;
		
	}

	@Override
	public List<Map<String, String>> getAllPieInfoFWQQ(String name, String type, String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		String valueName = "";
		String typeName = "";
		typeName = name.substring( 0, name.length()-4);
		if(name.indexOf("钻五")!=-1){
			valueName=" TIMEZ5 ";
		}
		if(name.indexOf("五星")!=-1||name.indexOf("D级")!=-1){
			valueName=" TIMEA5 ";
		}
		if(name.indexOf("四星")!=-1||name.indexOf("C级")!=-1){
			valueName=" TIMEA4 ";
		}
		if(name.indexOf("三星")!=-1||name.indexOf("B级")!=-1){
			valueName=" TIMEA3 ";
		}
		if(name.indexOf("二星")!=-1||name.indexOf("A级")!=-1){
			valueName=" TIMEA2 ";
		}
		
		String sql = "";
		if(time.length()==4){
			//年
			sql = " SELECT IDC_NAME AS city, round( sum( "+valueName+" ) / 12, 3 ) AS value "
				+ " FROM ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, d.MONTH "
				+ " ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC  ) rn, d.*  FROM DW_IDC_TYPE_LEVEL d "
				+ " where TYPE_NAME = '"+typeName+"' )  "
				+ " WHERE rn = 1  AND MONTH LIKE '"+time+"%'  GROUP BY IDC_NAME";
		}else if(time.length()==7){
			//月
			time=time.replaceAll("-", "");
			sql = " SELECT IDC_NAME AS city,max( "+valueName+" ) AS value FROM "
				+ " ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, d.MONTH "
				+ " ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC ) rn, d.*  FROM DW_IDC_TYPE_LEVEL d "
				+ " where TYPE_NAME = '"+typeName+"' ) WHERE rn = 1 "
				+ " AND MONTH LIKE '"+time+"%' GROUP BY IDC_NAME";
		}
		QueryAdapter q = new QueryAdapter();
		//DataTable dt = q.executeQuery(center);
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("city", dr.getString("city"));
			map.put("value", dr.getString("value"));
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getAllPieInfoJFCG(String name, String type, String time) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		String valueName="";
		if(name.indexOf("A")!=-1){
			valueName="A级";
		}
		if(name.indexOf("B")!=-1){
			valueName="B级";
		}
		if(name.indexOf("C")!=-1){
			valueName="C级";
		}
		if(name.indexOf("D")!=-1){
			valueName="D级";
		}
		
		String sql = "";
		
		if(time.length()==4){
			//年
			sql = " SELECT IDC_NAME AS city, round( sum( IDC_VALUE ) / 12, 3 ) AS value "
				+ " FROM ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, d.MONTH "
				+ " ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC  ) rn, d.*  FROM DW_IDC_JFCGXYSC d  )  "
				+ " WHERE rn = 1  AND MONTH LIKE '"+time+"%' AND IDC_LEVEL = '"+valueName+"'  GROUP BY IDC_NAME";
		}else if(time.length()==7){
			//月
			time=time.replaceAll("-", "");
			sql = " SELECT IDC_NAME AS city,max( IDC_VALUE ) AS value FROM "
				+ " ( SELECT ROW_NUMBER () OVER ( PARTITION BY d.IDC_NAME, d.MONTH "
				+ " ORDER BY to_date( d.MONTH, 'yyyyMM' ) DESC ) rn, d.*  FROM DW_IDC_JFCGXYSC d ) WHERE rn = 1 "
				+ " AND MONTH LIKE '"+time+"%' AND IDC_LEVEL = '"+valueName+"'  GROUP BY IDC_NAME";
		}
		QueryAdapter q = new QueryAdapter();
		//DataTable dt = q.executeQuery(center);
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("city", dr.getString("city"));
			map.put("value", dr.getString("value"));
			result.add(map);
		}
		return result;
	}

	@Override
	public String getValueByNameYun(String name, String area_name, String time, String type) {
		
		
		String sql = " SELECT f.id, f.name AS name, t.TIME_DELAY AS value, f.vieworder FROM "
				+ " ( SELECT DW_YUN_ZYY_WLSY.* , YUN_TYPE||'网络时延' AS index_name FROM DW_YUN_ZYY_WLSY "
				+ " WHERE YEARWEEK LIKE '"+time+"%' ) t "
				+ " RIGHT JOIN ( SELECT f.* FROM zl_fwzl_indicator f WHERE TYPE = '"+type+"' ) f "
				+ " ON t.index_name = f.name WHERE t.index_name = '"+name+"'";
		
		
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String value = "当前无数据";
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			value = dr.getString("value");
		}
		return value;
	}

	@Override
	public List<Map<String, String>> getAllPiesInfoYun(String type,String time,String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = "";
//		sql=" SELECT f.id, f.name AS name, t.TIME_DELAY AS value, f.vieworder FROM "
//				+ " ( SELECT DW_YUN_ZYY_WLSY.*,YUN_TYPE||'网络时延' AS index_name FROM  DW_YUN_ZYY_WLSY "
//				+ " WHERE YEARWEEK LIKE '"+time+"%' ) t "
//				+ " RIGHT JOIN ( SELECT * FROM zl_fwzl_indicator WHERE TYPE = '"+type+"' ) f ON t.index_name = f.name "
//				+ " ORDER BY f.vieworder, to_number( f.id)";
//		QueryAdapter q = new QueryAdapter();
//		DataTable dt = q.executeQuery(sql);
//		List<DataRow> rowList = dt.getRowList();
//		Iterator<DataRow> it = rowList.iterator();
//		String name="";
//		while (it.hasNext()) {
//			name="";
//			Map<String, String> map = new HashMap<String, String>();
//			DataRow dr = (DataRow) it.next();
//			name=dr.getString("name");
//			map.put("name", name);
//			map.put("value", dr.getString("value"));
//			map.put("id", dr.getString("id"));
//			map.put("vieworder", dr.getString("vieworder"));
//			result.add(map);
//		}
		sql="  SELECT id,name,vieworder,"
				+ "(CASE WHEN (EXISTS ( SELECT DW_YUN_ZYY_WLSY.* FROM DW_YUN_ZYY_WLSY WHERE YUN_TYPE || '网络时延' = name) ) THEN 1 ELSE 0 END ) AS value " + 
				" FROM zl_fwzl_indicator WHERE TYPE = '云' ";
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String name="";
		while (it.hasNext()) {
			name="";
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			name=dr.getString("name");
			map.put("name", name);
			map.put("value", dr.getString("value"));
			map.put("id", dr.getString("id"));
			map.put("vieworder", dr.getString("vieworder"));
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getAllLineInfoFWQQ(String name, String type, String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String center="";
		String valueName = "";
		String typeName = "";
		typeName = name.substring( 0, name.length()-4);
		if(name.indexOf("钻五")!=-1){
			valueName=" TIMEZ5 ";
		}
		if(name.indexOf("五星")!=-1||name.indexOf("D级")!=-1){
			valueName=" TIMEA5 ";
		}
		if(name.indexOf("四星")!=-1||name.indexOf("C级")!=-1){
			valueName=" TIMEA4 ";
		}
		if(name.indexOf("三星")!=-1||name.indexOf("B级")!=-1){
			valueName=" TIMEA3 ";
		}
		if(name.indexOf("二星")!=-1||name.indexOf("A级")!=-1){
			valueName=" TIMEA2 ";
		}
			
		center=" SELECT * FROM "
				+ " ( SELECT TO_CHAR( TRUNC( ADD_MONTHS( SYSDATE, - ROWNUM + 1 ), 'mm' ), 'yyyy-mm' ) AS mons "
				+ " FROM dual CONNECT BY ROWNUM <= 12 ) d "
				+ " LEFT JOIN "
				+ "( SELECT to_char( to_date( MONTH, 'yyyy-MM' ), 'yyyy-mm' ) AS mon, "
				+ valueName +"  AS value FROM DW_IDC_TYPE_LEVEL "
				+ "WHERE IDC_NAME LIKE '%"+area_name+"%' and TYPE_NAME = '"+typeName+"' ) t "
				+ " ON t.mon = d.mons ORDER BY d.mons DESC";
		
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(center);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("value", dr.getString("value"));
			map.put("mon", dr.getString("mons"));
			result.add(map);
		}
		return result;
	}
	
	
	
	@Override
	public List<Map<String, String>> getAllPiesInfoExport(String type, String time, String area_name) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		String sql = "";
		String center = "SELECT ROW_NUMBER() OVER("
						+ "PARTITION BY d.city, d.type, d.index_name, substr(d.STATISTICAL_TIME, 0, 6) ORDER BY to_date(d.STATISTICAL_TIME, 'yyyyMMdd') DESC"
						+ ") rn, d.* FROM dw_home_if_boce d"
						+ ") WHERE rn = 1 and type = '"+type+"' ";
						if(StringUtils.isNotBlank(area_name)){
							center+=" and city = '" + area_name + "' ";
						}
						if(StringUtils.isNotBlank(time)){
							if(time.contains("-")){
								center+=" and statistical_time like '" + time.replace("-", "") + "%' ";
								sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
							}else{
								center+=" and statistical_time like '" + time + "%' group by city , type, index_name";
								sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT city , type, index_name,round(sum(index_value) / 12,3) as index_value FROM ("+center+" ) t  right join (select * from zl_fwzl_indicator where type = '"+type+"') f on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
							}
								
						}

		sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( SELECT * FROM ("+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
		//sql = "select  f.id,f.name as name,t.index_value as value,f.vieworder from ( "+center+" ) t right join (select * from zl_fwzl_indicator where type = '"+type+"') f  on t.index_name = f.name order by f.vieworder,to_number(f.id)" ;
		QueryAdapter q = new QueryAdapter();
		DataTable dt = q.executeQuery(sql);
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		String name="";
		String valueName="";
		String typeName="";
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			name=dr.getString("name");
			if(type.equals("idc")){
				name=name.toUpperCase();
			}
			map.put("name", name);
			
			valueName="";
			if(name.indexOf("钻五")!=-1){
				valueName=" TIMEZ5 ";
			}else if(name.indexOf("五星")!=-1||name.indexOf("D级")!=-1){
				valueName=" TIMEA5 ";
			}else if(name.indexOf("四星")!=-1||name.indexOf("C级")!=-1){
				valueName=" TIMEA4 ";
			}else if(name.indexOf("三星")!=-1||name.indexOf("B级")!=-1){
				valueName=" TIMEA3 ";
			}else if(name.indexOf("二星")!=-1||name.indexOf("A级")!=-1){
				valueName=" TIMEA2 ";
			}else{
				valueName="";
			}
			
			if(valueName.isEmpty()){
				//正常指标
				map.put("value", dr.getString("value"));
			}else{
				//idc非正常指标ABCD 二三四五
				typeName = name.substring( 0, name.length()-4);
//				String idcsql=" SELECT "+valueName+" AS VALUE FROM DW_IDC_TYPE_LEVEL WHERE IDC_NAME = '"+area_name+"' "
//						+ "AND MONTH = '"+time.replace("-", "")+"'AND TYPE_NAME = '"+typeName+"' ";
				String idcsql= " SELECT count(1) AS VALUE FROM	DW_IDC_TYPE_LEVEL WHERE TYPE_NAME = '"+typeName+"' ";
				QueryAdapter q1 = new QueryAdapter();
				DataTable dt1 = q1.executeQuery(idcsql);
				List<DataRow> rowList1 = dt1.getRowList();
				Iterator<DataRow> it1 = rowList1.iterator();
				if (it1.hasNext()) {
					DataRow dr1 = (DataRow) it1.next();
					map.put("value", dr1.getString("value"));
				}else{
					map.put("value", "");
				}
			}
			
			map.put("id", dr.getString("id"));
			map.put("vieworder", dr.getString("vieworder"));
			result.add(map);
		}
		return result;
	}
	
	
}
