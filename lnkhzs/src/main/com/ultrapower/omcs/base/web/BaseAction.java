package com.ultrapower.omcs.base.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.DicManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.omcs.base.model.AjaxResultModel;

@SuppressWarnings("rawtypes")
public class BaseAction extends com.ultrapower.eoms.common.core.web.BaseAction {

	/**
	 * @Description:[字段功能描述]
	 */
	private static final long serialVersionUID = -1990215200187816036L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private String sqlname;

	private short limit;

	private int offset;

	private String order;

	private String param_userName;

	public String getParam_userName() {
		return param_userName;
	}

	public void setParam_userName(String param_userName) {
		this.param_userName = param_userName;
	}

	private HashMap<String, String> param;

	private Map<String, String> paramDictype;

	private AjaxResultModel importResultModel = new AjaxResultModel();

	private String callback;

	private String operation;

	/**
	 * 直接输出字符串,编码为UTF-8.
	 */
	protected void renderJson(final Object o) {
		ObjectMapper js = new ObjectMapper();
		try {
			String str = js.writeValueAsString(o);
			render(str, "json/plain;charset=UTF-8");
		} catch (Exception e) {
			log.error("exceptionHandler=", e.fillInStackTrace());
		}
	}

	@SuppressWarnings("unchecked")
	public void ajaxRQuery() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		RQuery rq = new RQuery(sqlname, param);
		DataTable dt = rq.getDataTable();
		List<DataRow> rows = dt.getRowList();
		Iterator<DataRow> it = rows.iterator();
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			HashMap map = dr.getRowHashMap();
			result.add(map);
		}
		renderJson(result);
	}

	/**
	 * [异步表格服务]
	 * 
	 * @author:佟广恩 tongguangen@ultrapower.com.cn
	 */
	@SuppressWarnings("unchecked")
	public void ajaxGrid() {
		DicManagerService dicManagerService = (DicManagerService) WebApplicationManager.getBean("dicManagerService");
		UserManagerService userManagerService = (UserManagerService) WebApplicationManager.getBean("userManagerService");
		List<String> userList = new ArrayList<String>();
		Map<String, Object> rMap = new HashMap<String, Object>();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		RQuery rq = new RQuery(sqlname, param);
		rq.setPage(limit == 0 ? 0 : (offset / limit + 1));
		rq.setPageSize(limit);
		rq.setIsCount(2);
		DataTable dt = rq.getDataTable();
		try {
			if(dt==null) {
				return;
			}
			List<DataRow> rows = dt.getRowList();
			Iterator<DataRow> it = rows.iterator();
			while (it.hasNext()) {
				DataRow dr = (DataRow) it.next();
				HashMap map = dr.getRowHashMap();
				// 用于存放数据字典中被转换前的数据
				Map realMap = new HashMap<String, String>();
				Iterator<String> keyIt = map.keySet().iterator();
				if (StringUtils.isNotBlank(param_userName)) {
					for (String userName : param_userName.split(",")) {
						userList.add(userName);
					}
				}

				while (keyIt.hasNext()) {
					String key = keyIt.next();
					if (paramDictype != null) {
						// 数据字典转换
						String dictype = paramDictype.get(key);
						String diname = "";
						if (StringUtils.isNotBlank(dictype)) {
							String value = map.get(key).toString();
							diname = dicManagerService.getTextByValue(dictype, value);
							map.put(key, diname);
							realMap.put("[REAL_NAME]" + key, value);
						}
					}
					if(userList.size()>0){
						if(userList.contains(key)){
							String value = map.get(key).toString();
							if(StringUtils.isBlank(value)){
								continue;
							}
							UserInfo user = userManagerService.getUserByLoginNameCache(value);
							map.put(key, user.getFullname());
							realMap.put("[REAL_NAME]" + key, value);
						}
					}
				}
				map.putAll(realMap);
				result.add(map);
			}
		} catch (Exception e) {
			logger.info(sqlname);
			e.printStackTrace();
		}

		rMap.put("total", rq.getQueryResultSetCount());
		rMap.put("rows", result);
		renderJson(rMap);
	}

	public String getSqlname() {
		return sqlname;
	}

	public void setSqlname(String sqlname) {
		this.sqlname = sqlname;
	}

	public HashMap<String, String> getParam() {
		return param;
	}

	public void setParam(HashMap<String, String> param) {
		this.param = param;
	}

	public AjaxResultModel getImportResultModel() {
		return importResultModel;
	}

	public void setImportResultModel(AjaxResultModel importResultModel) {
		this.importResultModel = importResultModel;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setLimit(short limit) {
		this.limit = limit;
	}

	public short getLimit() {
		return limit;
	}

	public Map<String, String> getParamDictype() {
		return paramDictype;
	}

	public void setParamDictype(Map<String, String> paramDictype) {
		this.paramDictype = paramDictype;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
