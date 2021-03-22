package com.ultrapower.lnkhzs.satisfaction.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtzxktmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisKddsmydService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

public class SatisKddsmydAction extends BaseAction{

	private File xls;
	
	private ISatisKddsmydService satisKddsmydService;
	
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

	

	public ISatisKddsmydService getSatisKddsmydService() {
		return satisKddsmydService;
	}

	public void setSatisKddsmydService(ISatisKddsmydService satisKddsmydService) {
		this.satisKddsmydService = satisKddsmydService;
	}

	public void deleteList(){
		satisKddsmydService.deledeList(ids);
		renderText(SUCCESS);
	}
	
	public void getMaxTime(){
		String maxTime=satisKddsmydService.getMaxTime();
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
		            Workbook wb=satisKddsmydService.checkVali(xls);
		            if (wb!=null) {
		                // 导入数据
		                try {
		                	//	traceSourceListService.removeData(date);
		                		i = satisKddsmydService.importXls(wb,user);
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
