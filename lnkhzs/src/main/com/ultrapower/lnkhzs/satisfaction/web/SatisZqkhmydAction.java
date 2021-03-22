package com.ultrapower.lnkhzs.satisfaction.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisZqkhmydService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

public class SatisZqkhmydAction extends BaseAction{

	private File xls;
	
	private String ids;
	
	private ISatisZqkhmydService satisZqkhmydService;
	
	

	public ISatisZqkhmydService getSatisZqkhmydService() {
		return satisZqkhmydService;
	}

	public void setSatisZqkhmydService(ISatisZqkhmydService satisZqkhmydService) {
		this.satisZqkhmydService = satisZqkhmydService;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	public void deleteList(){
		satisZqkhmydService.deledeList(ids);
		renderText(SUCCESS);
	}
	
	public void getMaxTime(){
		String maxTime=satisZqkhmydService.getMaxTime();
		renderJson(maxTime);
	}
	
	
	//导入
		public void importExcel(){
	      	UserSession user = super.getUserSession();
	      	int i = 0;
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	    	String date = sdf.format(new Date());
	        if(super.getFieldErrors().containsKey("xls")){
	        }
	        try {
	            // 校验文件是否正确
	            Workbook wb=satisZqkhmydService.checkVali(xls);
	            if (wb!=null) {
	                // 导入数据
	                try {
	                	//	traceSourceListService.removeData(date);
	                		i = satisZqkhmydService.importXls(wb,user);
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
	        	this.renderText(ERROR);
	        }
	        
	    }
	
	
	
	
	
	
	
}
