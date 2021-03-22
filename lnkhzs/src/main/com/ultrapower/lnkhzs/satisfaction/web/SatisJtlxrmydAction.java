package com.ultrapower.lnkhzs.satisfaction.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtlxrmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

public class SatisJtlxrmydAction extends BaseAction{

	private File xls;

	private ISatisJtlxrmydService satisJtlxrmydService;

	private String ids;
	

	public ISatisJtlxrmydService getSatisJtlxrmydService() {
		return satisJtlxrmydService;
	}

	public void setSatisJtlxrmydService(ISatisJtlxrmydService satisJtlxrmydService) {
		this.satisJtlxrmydService = satisJtlxrmydService;
	}

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


	public void deleteList() {
		satisJtlxrmydService.deledeList(ids);
		renderText(SUCCESS);
	}
	
	public void getMaxTime(){
		String maxTime=satisJtlxrmydService.getMaxTime();
		renderJson(maxTime);
	}

	// 导入
	public void importExcel() {
		UserSession user = super.getUserSession();
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date());
		if (super.getFieldErrors().containsKey("xls")) {
		}
		try {
			// 校验文件是否正确
			Workbook wb = satisJtlxrmydService.checkVali(xls);
			if (wb != null) {
				// 导入数据
				try {
					// traceSourceListService.removeData(date);
					i = satisJtlxrmydService.importXls(wb, user);
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
