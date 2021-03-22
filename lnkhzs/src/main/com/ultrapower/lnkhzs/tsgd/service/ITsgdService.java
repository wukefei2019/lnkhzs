package com.ultrapower.lnkhzs.tsgd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicator;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicatorTree;

/**
 * Created on 2019年4月19日
 * <p>Description: [null_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public interface ITsgdService {

	List<ZtreeBean> getSearchTree(String tableName);

	List<ZlTsfxIndicator> getPiesInfo();
	
	List<ZtreeBean> getTsfxTree(String id);
	
	List<ZlTsfxIndicator> getAllPiesInfo();
	
	List<ZlTsfxIndicatorTree> getSonIndByParentId(String parentId);
	
	ZlTsfxIndicator getOnePieInfo(String id);
	
	ZlTsfxIndicatorTree getOneChartInfo(String id);
	
	List<Map<String, String>> getValueListBySqlname(String sqlname, String year, String area_name);
	
	String getValueBySqlname(String sqlname, String areaName, String time);
	
	String deleteInd(String id);

	String resetIndOrder(Map<String,String> paramsMap);
	
	String addInd(String id);
	
	List<Map<String, String>> getTsfxAmount(String respDept, String a12, String areaName, String cname, String time);

	String getA12(String a12);

	List<Map<String, String>> getTsfxFocus(String respDept, String a12, String areaName, String time);
	
	List<Map<String, String>> GetLDLine(String respDept, String a12, String areaName, String cname,String time);

	List<Map<String, String>> ajaxGetTshbFocus(String respDept, String a12, String area_name, String time, String cname,String type);
	
	List<Map<String, String>> proData( String nameArray,String time,String respDept, String a12, String areaName);
	
	List<String> getSearchCon(String param);
}
