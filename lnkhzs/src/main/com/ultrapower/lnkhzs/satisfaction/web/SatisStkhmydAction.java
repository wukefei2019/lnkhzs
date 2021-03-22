package com.ultrapower.lnkhzs.satisfaction.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtgjrmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisStkhmydService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

public class SatisStkhmydAction extends BaseAction{

	private File xls;

	private ISatisStkhmydService satisStkhmydService;

	private String ids;

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public ISatisStkhmydService getSatisStkhmydService() {
		return satisStkhmydService;
	}

	public void setSatisStkhmydService(ISatisStkhmydService satisStkhmydService) {
		this.satisStkhmydService = satisStkhmydService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	public void deleteList() {
		satisStkhmydService.deledeList(ids);
		renderText(SUCCESS);
	}
	
	public void getMaxTime(){
		String maxTime=satisStkhmydService.getMaxTime();
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
	            Workbook wb=satisStkhmydService.checkVali(xls);
	            if (wb!=null) {
	                // 导入数据
	                try {
	                	//	traceSourceListService.removeData(date);
	                		i = satisStkhmydService.importXls(wb,user);
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
