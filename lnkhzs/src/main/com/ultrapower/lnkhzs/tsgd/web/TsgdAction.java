package com.ultrapower.lnkhzs.tsgd.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.component.rquery.core.SqlQuery;
import com.ultrapower.eoms.common.core.component.rquery.startup.StartUp;
import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.lnkhzs.base.web.BaseAction;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicator;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicatorTree;
import com.ultrapower.lnkhzs.tsgd.service.ITsgdService;

import net.sf.json.JSONObject;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [null_WEB]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TsgdAction extends BaseAction {


    private static final long serialVersionUID = -1L;

    private String paramMap;
    private String tableName; 
    private String menuId; 
    private String sql; 
    private String type; 
    private String sqlName;
    private String dataField;
    private String respDept;
    private String a12;
    private String area_name;
    private String time;
    private String month;
    private String year;
    private String cname;
    

	private ITsgdService tsgdService;

	public void ajaxGetTsfxTree() {
		List<ZtreeBean> list = tsgdService.getTsfxTree(parentId);
		this.renderJson(list);
	}

	public void ajaxGetPiesJson() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		// 获取选择的多个SQL结果
		if ("A".equals(menuId)) {

			List<ZlTsfxIndicator> pielist = tsgdService.getPiesInfo();

			for (int i = 0; i < pielist.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				ZlTsfxIndicator fxind = pielist.get(i);
				String sqlname = fxind.getSql();
				String value = tsgdService.getValueBySqlname(sqlname, area_name, time);
				String name = fxind.getName();
				String id = fxind.getId();
				String childid = fxind.getChildid();
				String type = fxind.getType();
				String vieworder = fxind.getVieworder();
				map.put("sqlname", sqlname);
				map.put("value", value);
				map.put("name", name);
				map.put("id", id);
				map.put("childid", childid);
				map.put("type", type);
				map.put("vieworder", vieworder);
				result.add(map);
			}
			super.renderJson(result);
		} else if ("B".equals(menuId)) {// 获取全部父级元数据
			List<ZlTsfxIndicator> pielist = tsgdService.getAllPiesInfo();
			super.renderJson(pielist);
		} else if ("C".equals(menuId)) {// 获取指定SQL结果
			Map<String, String> map = new HashMap<String, String>();
			ZlTsfxIndicator ind = tsgdService.getOnePieInfo(id);
			String sqlname = ind.getSql();
			String value = tsgdService.getValueBySqlname(sqlname, area_name, time);
			String name = ind.getName();
			String id = ind.getId();
			String childid = ind.getChildid();
			String type = ind.getType();
			String vieworder = ind.getVieworder();
			map.put("sqlname", sqlname);
			map.put("value", value);
			map.put("name", name);
			map.put("id", id);
			map.put("childid", childid);
			map.put("type", type);
			map.put("vieworder", vieworder);
			result.add(map);
			super.renderJson(map);
		} else if ("D".equals(menuId)) { // 获取选择的父级元数据
			List<ZlTsfxIndicator> pielist = tsgdService.getPiesInfo();
			super.renderJson(pielist);
		}

	}

	public void ajaxAddPieById() {
		String result = tsgdService.addInd(id);

		super.renderJson(result);
	}

	public void deleteInd() {
		String result = tsgdService.deleteInd(id);
		super.renderJson(result);
	}

	@SuppressWarnings("unchecked")
	public void resetIndOrder() {
		if (paramMap == null || "".equals(paramMap)) return;
		
		JSONObject json = JSONObject.fromObject(paramMap);
		Map<String,String> map = (Map<String,String>)json;
		String result = tsgdService.resetIndOrder(map);
		super.renderJson(result);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ajaxGetLineJson() {
		Map mapresult = new HashMap();
		ZlTsfxIndicatorTree indTree = tsgdService.getOneChartInfo(id);

		String sqlmonth = indTree.getNdicatorsqlmonth();
		List<Map<String, String>> lis = tsgdService.getValueListBySqlname(sqlmonth,year,area_name);

		mapresult.put("value", lis);
		mapresult.put("indTree", indTree);

		super.renderJson(mapresult);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ajaxGetLineJsonByParentId() {
		Map mapresult = new HashMap();
		List<ZlTsfxIndicatorTree> lis = tsgdService.getSonIndByParentId(id);
		mapresult.put("treeList", lis);
		super.renderJson(mapresult);
	}

	private String parentId;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setTsgdService(ITsgdService tsgdService) {
		this.tsgdService = tsgdService;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void ajaxGetSearchTree() {
		List<ZtreeBean> list = tsgdService.getSearchTree(tableName);
		this.renderJson(list);
	}
	
	public void ajaxGetTsfxAmount(){
		List<Map<String, String>> result = tsgdService.getTsfxAmount(respDept,a12,area_name,cname,time);
		this.renderJson(result);
	}
	
	public void ajaxGetLDLine(){
		List<Map<String, String>> result = tsgdService.GetLDLine(respDept,a12,area_name,cname, time);
		this.renderJson(result);
	}

	public void ajaxGetTsfxFocus() {
		List<Map<String, String>> result = tsgdService.getTsfxFocus(respDept, a12, area_name, time);
		this.renderJson(result);
	}
	
	public void ajaxGetTshbFocus(){
		List<Map<String, String>> result = tsgdService.ajaxGetTshbFocus(respDept, a12, area_name, time,cname,type);
		this.renderJson(result);
	}
	
	public void ajaxGetA12Detail(){
		String result = tsgdService.getA12(a12);
		this.renderJson(result);
	}
	
	public void ajaxGetTSDetail(){
		String selectedA12 = tsgdService.getA12(a12);
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("AREA_NAME", area_name);
		param.put("statis_date", year);
		param.put("selectedA12", selectedA12);
		sqlName="SQL_KHZS_ts.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlName);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlName,param);
		DataTable dt = rq.getDataTable();
		System.out.println(dt.getRowList());
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
	}

	public void ajaxGetSearchCon() {
		List<String> lis = tsgdService.getSearchCon(dataField);
		
		this.renderJson(lis);
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRespDept() {
		return respDept;
	}

	public void setRespDept(String respDept) {
		this.respDept = respDept;
	}

	public String getA12() {
		return a12;
	}

	public void setA12(String a12) {
		this.a12 = a12;
	}
	

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getDataField() {
		return dataField;
	}

	public void setDataField(String dataField) {
		this.dataField = dataField;
	}

	public String comTable() {
		Object obj = null;
		if (StartUp.sqlmapElement != null) {
			obj = StartUp.sqlmapElement.get(sqlName);
		}
		if (obj != null)//
		{
			Element sqlqueryElement = (Element) obj;
			sqlqueryElement.getAttribute("colname_name");
			List<Element> colList = sqlqueryElement.getChild("colname_name").getContent();
			dataField = "<tr><th data-formatter=\"fmt_operate\" data-report-col-mode=\"none\" data-events=\"fn_operate_events\">操作</th>";
			for (int i = 0; i < colList.size(); i++) {
				if (colList.get(i) instanceof Element) {
					if ("name".equals(colList.get(i).getName())) {
						dataField = dataField + "<th data-field='" + colList.get(i).getTextTrim()
								+ "' data-sortable='true'  ";
					}
					if ("namestr".equals(colList.get(i).getName())) {
						if(colList.get(i).getTextTrim().equals("投诉名称"))
							dataField = dataField + "  data-formatter=\"$.bs.table.fmt.link\" data-events=\"fn_detail\" data-switchable=\"false\">" + colList.get(i).getTextTrim() + "</th>";
						else
							dataField = dataField + ">" + colList.get(i).getTextTrim() + "</th>";
					}
				}
			}
			dataField = dataField + "</tr>";
		}
		return "success";

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	public void comLinePie() {
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("AREA_NAME", area_name);
		/*param.put("acpt_time", month);*/
		/*param.put("acpt_time", year);*/
		if(sqlName.indexOf("_A.query")==-1){
			param.put("acpt_time", year);
		}
		if(sqlName.indexOf("_C.query")==-1){
			param.put("acpt_time", year);
		}
		
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlName);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlName,null);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());

	}



	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	public void comYZ() {
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("AREA_NAME", area_name);
		/*param.put("acpt_time", month);*/
		param.put("acpt_time", year);
		RQuery rq = new RQuery(sqlName, param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		System.out.println(dt.getRowList());
		renderJson(dt.getRowList());

	}

	public String getParamMap() {
		return paramMap;
	}

	public void setParamMap(String paramMap) {
		this.paramMap = paramMap;
	}
}
