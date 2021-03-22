package com.ultrapower.lnkhzs.satisfaction.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmydmxbService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

public class SatisTsclmydmxbAction extends BaseAction{

	private File xls;
	
	private ISatisTsclmydmxbService satisTsclmydmxbService;
	
	private String ids;
	
	
	
	
	
    public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public ISatisTsclmydmxbService getSatisTsclmydmxbService() {
		return satisTsclmydmxbService;
	}

	public void setSatisTsclmydmxbService(ISatisTsclmydmxbService satisTsclmydmxbService) {
		this.satisTsclmydmxbService = satisTsclmydmxbService;
	}

	
	public void deleteList(){
		satisTsclmydmxbService.deledeList(ids);
		renderText(SUCCESS);
	}
	
	public void getMaxTime(){
		String maxTime=satisTsclmydmxbService.getMaxTime();
		renderJson(maxTime);
	}
	
	
	//导入
		public void importExcel(){
	      	UserSession user = super.getUserSession();
	      	int i = 0;
	        if(super.getFieldErrors().containsKey("xls")){
	        }
	        try {
	            // 校验文件是否正确
	            Workbook wb=satisTsclmydmxbService.checkVali(xls);
	            if (wb!=null) {
	                // 导入数据
	                try {
	                	//	traceSourceListService.removeData(date);
	                		i = satisTsclmydmxbService.importXls(wb,user);
	                		this.renderText(SUCCESS);
	                } catch (ExcelImportException e) {
	                    e.printStackTrace();
	                    this.renderText(ERROR);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    this.renderText(ERROR);
	                }
	            } else {
	            	this.renderText(ERROR);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            this.renderText(ERROR);
	        }
	        
	        
	    }
	
}
