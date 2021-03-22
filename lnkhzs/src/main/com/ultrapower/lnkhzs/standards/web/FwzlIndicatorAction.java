package com.ultrapower.lnkhzs.standards.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.standards.model.FwzlIndicator;
import com.ultrapower.lnkhzs.standards.service.IFwzlIndicatorService;
import com.ultrapower.lnkhzs.tsgd.model.ZlTsfxIndicator;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class FwzlIndicatorAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private String area_name;
    
    private String time;
    
    private String type;
    
    private String menuId; 
    
    private String name; 
    
    private String unit;
    
    private IFwzlIndicatorService fwzlIndicatorService;

    public void setFwzlIndicatorService(IFwzlIndicatorService fwzlIndicatorService) {
        this.fwzlIndicatorService = fwzlIndicatorService;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void ajaxGetPiesJson() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		if ("A".equals(menuId)) {

			List<FwzlIndicator> pielist = fwzlIndicatorService.getPiesInfo(type);

			for (int i = 0; i < pielist.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				FwzlIndicator ind = pielist.get(i);
				String name = ind.getName();
				String value = "";
				if(name.indexOf("钻五")!=-1||name.indexOf("五星")!=-1||name.indexOf("四星")!=-1||name.indexOf("三星")!=-1||name.indexOf("二星")!=-1
					||name.indexOf("A级")!=-1||name.indexOf("B级")!=-1||name.indexOf("C级")!=-1||name.indexOf("D级")!=-1){
					value = fwzlIndicatorService.getValueByNameFWQQ(name, area_name, time,type);
				}else{
					value = fwzlIndicatorService.getValueByName(name, area_name, time,type);
				}
				/*String value = fwzlIndicatorService.getValueByName(name, area_name, time,type);*/
				String myunit = "";
				String mythreshold = "";
				if("A".equals(unit)){
					Map<String, String> ut=fwzlIndicatorService.getUnitByName(name, area_name, time,type);
					myunit=ut.get("unit");
					mythreshold=ut.get("threshold");
				}
				String id = ind.getId();
				map.put("value", value);
				if(type.equals("idc")){
					name=name.toUpperCase();
				}
				map.put("name", name);
				map.put("unit", myunit);
				map.put("threshold", mythreshold);
				map.put("id", id);
				map.put("type", type);
				result.add(map);
			}
			super.renderJson(result);
		} else if ("B".equals(menuId)) {
			List<Map<String, String>> pielist = fwzlIndicatorService.getAllPiesInfo(type,time,area_name);
			super.renderJson(pielist);
		}else if("E".equals(menuId)){
			List<Map<String,String>> allLineInfo = new ArrayList<Map<String,String>>();
			if(name.indexOf("钻五")!=-1||name.indexOf("五星")!=-1||name.indexOf("四星")!=-1||name.indexOf("三星")!=-1||name.indexOf("二星")!=-1
					||name.indexOf("A级")!=-1||name.indexOf("B级")!=-1||name.indexOf("C级")!=-1||name.indexOf("D级")!=-1){
				allLineInfo = fwzlIndicatorService.getAllLineInfoFWQQ(name,type,area_name);
			}else{
				allLineInfo = fwzlIndicatorService.getAllLineInfo(name,type,area_name);
			}
			super.renderJson(allLineInfo);
		}else if("F".equals(menuId)){
			List<Map<String,String>> allLineInfo = new ArrayList<Map<String,String>>();
			if(name.indexOf("钻五")!=-1||name.indexOf("五星")!=-1||name.indexOf("四星")!=-1||name.indexOf("三星")!=-1||name.indexOf("二星")!=-1
					||name.indexOf("A级")!=-1||name.indexOf("B级")!=-1||name.indexOf("C级")!=-1||name.indexOf("D级")!=-1){
				allLineInfo = fwzlIndicatorService.getAllPieInfoFWQQ(name,type,time);
			}else{
				allLineInfo = fwzlIndicatorService.getAllPieInfo(name,type,time);
			}
			super.renderJson(allLineInfo);
		}

	}
    
	public void ajaxAddPieById() {
		String result = fwzlIndicatorService.addInd(id,type);

		super.renderJson(result);
	}
	
	public void deleteInd() {
		String result = fwzlIndicatorService.deleteInd(id);
		super.renderJson(result);
	}
	
	/**
	 * 物联网卡查询
	 */
	public void ajaxGetPiesJsonInter(){
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		if ("A".equals(menuId)) {
				String code = "";
			List<FwzlIndicator> pielist = fwzlIndicatorService.getPiesInfo(type);

			for (int i = 0; i < pielist.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				FwzlIndicator ind = pielist.get(i);
				String name = ind.getName();
				String id = ind.getId();
				/*if(id.equals("129") || id.equals("126")){
					area_name="";
					time="";
				}
				//投诉处理及时率
				if(id.equals("127")){
					area_name="";
					time="";
					code = "1";
				}*/
				String myunit="";
				String mythreshold = "";
				if("A".equals(unit)){
					Map<String, String> ut=fwzlIndicatorService.getUnitByName(name, area_name, time,type);
					myunit=ut.get("unit");
					mythreshold=ut.get("threshold");
				}
				String value="";
				if(name.equals("投诉处理及时率")||name.equals("投诉回访及时率")||name.equals("投诉首次响应及时率")){
					value = fwzlIndicatorService.getValueByNameInter(name, area_name, time,type);
				}else{
					value = fwzlIndicatorService.getValueByName(name, area_name, time,type);
				}
				
				map.put("value", value);
				map.put("name", name);
				map.put("id", id);
				map.put("type", type);
				map.put("unit", myunit);
				map.put("threshold", mythreshold);
				result.add(map);
			}
			super.renderJson(result);
		} else if ("B".equals(menuId)) {
			
			List<Map<String, String>> pielist = fwzlIndicatorService.getAllPiesInfoInter(type,time,area_name);
			super.renderJson(pielist);
		}/*else if("E".equals(menuId)){
			//折线图
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllLineInfoInterNew(name, type);
			super.renderJson(allLineInfo);
		}else if("F".equals(menuId)){
			//饼图
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllPieInfoInter(name, time,type);
			super.renderJson(allLineInfo);
		}*/else if("E".equals(menuId)){
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllLineInfo(name,type,area_name);
			super.renderJson(allLineInfo);
		}else if("F".equals(menuId)){
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllPieInfo(name,type,time);
			super.renderJson(allLineInfo);
		}
		
	}
	
	
	/**
	 * 集团专线查询
	 */
	public void ajaxGetPiesJsonGroup(){
		
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		if ("A".equals(menuId)) {
				String code = "";
			List<FwzlIndicator> pielist = fwzlIndicatorService.getPiesInfo(type);

			for (int i = 0; i < pielist.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				FwzlIndicator ind = pielist.get(i);
				String name = ind.getName();
				String id = ind.getId();
				String myunit="";
				String mythreshold = "";
				if("A".equals(unit)){
					Map<String, String> ut=fwzlIndicatorService.getUnitByName(name, area_name, time,type);
					myunit=ut.get("unit");
					mythreshold=ut.get("threshold");
				}
				String value = fwzlIndicatorService.getValueByNameInter(name, area_name, time,type);
				map.put("value", value);
				map.put("name", name);
				map.put("id", id);
				map.put("type", type);
				map.put("unit", myunit);
				map.put("threshold", mythreshold);
				result.add(map);
			}
			super.renderJson(result);
		} else if ("B".equals(menuId)) {
			List<Map<String, String>> pielist = fwzlIndicatorService.getAllPiesInfoInter(type,time,area_name);
			super.renderJson(pielist);
		}else if("E".equals(menuId)){
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllLineInfoInter(name,type,area_name);
			super.renderJson(allLineInfo);
		}else if("F".equals(menuId)){
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllPieInfoInter(name, time,type);
			super.renderJson(allLineInfo);
		}
		
	}
	
	
	public void ajaxGetPiesJsonYun() {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		if ("A".equals(menuId)) {

			List<FwzlIndicator> pielist = fwzlIndicatorService.getPiesInfo(type);

			for (int i = 0; i < pielist.size(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				FwzlIndicator ind = pielist.get(i);
				String name = ind.getName();
				String value = fwzlIndicatorService.getValueByNameYun(name, area_name, time,type);
				String myunit = "";
				String mythreshold = "";
				if("A".equals(unit)){
					Map<String, String> ut=fwzlIndicatorService.getUnitByName(name, area_name, time,type);
					myunit=ut.get("unit");
					mythreshold=ut.get("threshold");
				}
				String id = ind.getId();
				map.put("value", value);
				map.put("name", name);
				map.put("unit", myunit);
				map.put("threshold", mythreshold);
				map.put("id", id);
				map.put("type", type);
				result.add(map);
			}
			super.renderJson(result);
		} else if ("B".equals(menuId)) {
			List<Map<String, String>> pielist = fwzlIndicatorService.getAllPiesInfoYun(type,time,area_name);
			super.renderJson(pielist);
		}else if("E".equals(menuId)){
			List<Map<String,String>> allLineInfo = fwzlIndicatorService.getAllLineInfo(name,type,area_name);
			super.renderJson(allLineInfo);
		}else if("F".equals(menuId)){
			List<Map<String,String>> allLineInfo = new ArrayList<Map<String,String>>();
			if(name.indexOf("服务请求响应时长")!=-1){
				allLineInfo = fwzlIndicatorService.getAllPieInfoFWQQ(name,type,time);
			}else if(name.indexOf("机房参观响应时长")!=-1){
				allLineInfo = fwzlIndicatorService.getAllPieInfoJFCG(name,type,time);
			}else{
				allLineInfo = fwzlIndicatorService.getAllPieInfo(name,type,time);
			}
			super.renderJson(allLineInfo);
		}

	}
	
	
	
	
}
