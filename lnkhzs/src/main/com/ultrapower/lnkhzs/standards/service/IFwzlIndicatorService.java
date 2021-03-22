package com.ultrapower.lnkhzs.standards.service;

import java.util.List;
import java.util.Map;

import com.ultrapower.lnkhzs.standards.model.FwzlIndicator;

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
public interface IFwzlIndicatorService {

	List<FwzlIndicator> getPiesInfo(String type);

	List<Map<String, String>> getAllPiesInfo(String type, String time, String area_name);
	
	List<Map<String, String>> getAllLineInfo(String name,String type, String area_name);
	
	String addInd(String id, String type);
	
	String deleteInd(String id);

	String getValueByName(String name, String area_name, String time, String type);
	
	List<Map<String, String>> getAllPiesInfoInter(String type, String time, String area_name);
	
	String getValueByNameInter(String name, String time, String area_name,String type);

	List<Map<String, String>> getAllLineInfoInter(String name, String type, String area_name);
	
	List<Map<String, String>> getAllPieInfo(String name,String type,String time);
	
	List<Map<String, String>> getAllPieInfoInter(String name,String time, String type);

	Map<String, String> getUnitByName(String name, String area_name, String time, String type);

	List<Map<String, String>> getAllLineInfoInterNew(String name, String type);

	String getValueByNameFWQQ(String name, String area_name, String time, String type);

	String getValueByNameJFCG(String name, String area_name, String time, String type);

	List<Map<String, String>> getAllPieInfoFWQQ(String name, String type, String time);

	List<Map<String, String>> getAllPieInfoJFCG(String name, String type, String time);

	String getValueByNameYun(String name, String area_name, String time, String type);

	List<Map<String, String>> getAllPiesInfoYun(String name,String type,String time);

	List<Map<String, String>> getAllLineInfoFWQQ(String name, String type, String area_name);
	
	List<Map<String, String>> getAllPiesInfoExport(String type, String time, String area_name);

}
