package com.ultrapower.lnkhzs.quality.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.bmc.thirdparty.org.springframework.beans.BeanUtils;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.plugin.excelutils.ExcelWriter;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNode;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNodeBak;
import com.ultrapower.lnkhzs.quality.service.ITagMaintainService;
import com.ultrapower.lnkhzs.quality.utils.CompareUtil;
import com.ultrapower.omcs.base.model.AjaxResultModel;
import com.ultrapower.omcs.base.model.IaExportFile;
import com.ultrapower.omcs.base.service.IBaseExportService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.common.utils.ExcelUtils;

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
public class TagMaintainAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ITagMaintainService tagMaintainService;
	
  	private ViceRequestNode viceRequestNode;
  	
  	private ViceRequestNodeBak viceRequestNodeBak;
	
  	private String a12;
  	
  	private String level;
  	
  	private String data;
  	
  	private String code;
  	
  	private String city;
  	
  	private String year;
  	
  	private File xls;
  	
  	private IBaseExportService baseExportService;
  
    public void setBaseExportService(IBaseExportService baseExportService) {
		this.baseExportService = baseExportService;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public ITagMaintainService getTagMaintainService() {
		return tagMaintainService;
	}

	public void setTagMaintainService(ITagMaintainService tagMaintainService) {
		this.tagMaintainService = tagMaintainService;
	}

	public ViceRequestNode getViceRequestNode() {
		return viceRequestNode;
	}

	public void setViceRequestNode(ViceRequestNode viceRequestNode) {
		this.viceRequestNode = viceRequestNode;
	}

	public ViceRequestNodeBak getViceRequestNodeBak() {
		return viceRequestNodeBak;
	}

	public void setViceRequestNodeBak(ViceRequestNodeBak viceRequestNodeBak) {
		this.viceRequestNodeBak = viceRequestNodeBak;
	}

	public String getA12() {
		return a12;
	}

	public void setA12(String a12) {
		this.a12 = a12;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void ajaxGetSerReqNode(){
    	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    	QueryAdapter rq = new QueryAdapter();
		DataTable dt = rq.executeQuery("select "+level+" from (select * from ZL_SERVICE_REQUEST_NODE where a12 like '"+a12+"%') t group by "+level);
		List<DataRow> rowList = dt.getRowList();
		if(rowList.isEmpty()){
			dt = rq.executeQuery("select "+level+" from ZL_SERVICE_REQUEST_NODE group by "+level);
			rowList = dt.getRowList();
		}
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			DataRow dr = (DataRow) it.next();
			map.put("text",dr.getString(level));
			map.put("level",level);
			result.add(map);
		}
		renderJson(result);
    }
    
    public void ajaxGetViceRequestNode(){
    	ViceRequestNode requestNode = tagMaintainService.getViceRequestNode(viceRequestNode.getPid());
    	renderJson(requestNode);
    }
    
    public void saveSerReqNode(){
    	tagMaintainService.saveSerReqNode(viceRequestNode);
    	renderJson("success");
    }
    
    public void saveSerReqNodeBak(){
    	UserSession user = super.getUserSession();
    	try {
    		boolean flag = tagMaintainService.checkNodeRepeat(viceRequestNode);
    		if(flag){
    			tagMaintainService.saveSerReqNodeBak(viceRequestNode,user.getLoginName(),viceRequestNodeBak.getStatus());
    			renderJson("success");
    		}else{
    			renderJson("error");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			renderJson("error");
		}
    }
    
    public void submitSerReqNode(){
    	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
    	Map<String, String> map = new HashMap<String,String>();
    	UserSession user = super.getUserSession();
    	String[] split = data.split(",");
    	if(tagMaintainService.checkRepeat(split,user.getLoginName())){
//	    	Integer batchno = tagMaintainService.getSenBatchnoSeq();
	    	for(String id : split){
	    		ViceRequestNodeBak viceRequestNodeBak = tagMaintainService.getViceRequestNodeBak(id);
//	    		viceRequestNodeBak.setBatchno(batchno);
	    		viceRequestNodeBak.setOperator(user.getLoginName());
	    		viceRequestNodeBak.setFlowstatus("审批中");
	    		tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
	    	}
	    	map.put("result","success");
	    	map.put("msg","提交成功");
	    	result.add(map);
	    	renderJson(result);
    	}else{
	    	map.put("result","error");
	    	map.put("msg","提交的数据重复");
	    	result.add(map);
	    	renderJson(result);
    	}
    }
    
    public void agreeSerReqNode(){
    	String[] split = data.split(",");
    	for(String id : split){
    		ViceRequestNodeBak viceRequestNodeBak = tagMaintainService.getViceRequestNodeBak(id);
    		ViceRequestNode viceRequestNode = new ViceRequestNode();
    		if("新增".equals(viceRequestNodeBak.getStatus())){
    			BeanUtils.copyProperties(viceRequestNodeBak, viceRequestNode);
    			tagMaintainService.saveSerReqNode(viceRequestNode);
    			viceRequestNodeBak.setFlowstatus("已完成");
    			tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
    		}
    		if("修改".equals(viceRequestNodeBak.getStatus())){
    			BeanUtils.copyProperties(viceRequestNodeBak, viceRequestNode);
    			tagMaintainService.saveSerReqNode(viceRequestNode);
    			viceRequestNodeBak.setFlowstatus("已完成");
    			tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
    		}
    		if("删除".equals(viceRequestNodeBak.getStatus())){
    			BeanUtils.copyProperties(viceRequestNodeBak, viceRequestNode);
    			tagMaintainService.removeSerReqNode(viceRequestNode);
    			viceRequestNodeBak.setFlowstatus("已完成");
    			tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
    		}
    		viceRequestNodeBak.setIsDelete("1");
    		tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
    	}
    	renderJson("success");
    }
    
    public void rejectSerReqNode(){
    	String[] split = data.split(",");
    	for(String id : split){
    		ViceRequestNodeBak viceRequestNodeBak = tagMaintainService.getViceRequestNodeBak(id);
    		viceRequestNodeBak.setIsDelete("1");
    		viceRequestNodeBak.setFlowstatus("已完成");
    		tagMaintainService.saveSerReqNodeBak(viceRequestNodeBak);
    	}
    	renderJson("success");
    }
    
	public void ajaxGetTagStatistics() {
		Integer[] tagStatistics = tagMaintainService.getTagStatistics(a12,city,year);
		renderJson(tagStatistics);
	}
	
	public String importExcel() throws Exception {
		try {
			InputStream input = null;
			input = new FileInputStream(xls);
			Workbook wb = null;

			try {
				wb = new HSSFWorkbook(input);
			} catch (Exception e) {
				 System.out.println(e.getMessage());
			}

			if (wb == null) {
				try {
					input = new FileInputStream(xls);
					wb = new XSSFWorkbook(input);
				} catch (Exception e) {
					 System.out.println(e.getMessage());
				}
				
			}
			Row row = null;
			String cellvalue = null;

			Sheet sheet = wb.getSheetAt(0);
			List<String> list1 = new ArrayList<String>();
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if(row!=null&&row.getLastCellNum()>0) {
					cellvalue = ExcelUtils.readCell(row.getCell(0));
					
				}else {
					break;
				}				
				list1.add(cellvalue);
			}
			List<String> list2 = tagMaintainService.getTagList();
			List<String[]> compareList = CompareUtil.compareList(list1, list2);
			HttpServletResponse response = ServletActionContext.getResponse();
			baseExportService.exportExcelWithList(response, new String[] { "导入数据", "系统中数据" }, compareList, "TagDataCompare");
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}

		return "successAjax";
	}
	
	
	
	public void ajaxGetSerReqNodeEnd(){
		int num=0;
		QueryAdapter rq = new QueryAdapter();
		DataTable dt = rq.executeQuery("select count(*) AS NUM from ZL_SERVICE_REQUEST_NODE_BAK where A12 = '"+a12+"'  AND FLOWSTATUS != '已完成' ");
		List<DataRow> rowList = dt.getRowList();
		Iterator<DataRow> it = rowList.iterator();
		while (it.hasNext()) {
			DataRow dr = (DataRow) it.next();
			num=dr.getInt("NUM");
		}
		renderJson(num);
    }
    
}
