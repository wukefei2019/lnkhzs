package com.ultrapower.lnkhzs.satisfaction.service;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;

public interface ISatisZqkhmydService {

	Workbook checkVali(File xls);

	int importXls(Workbook wb, UserSession user) throws Exception;
	
	void deledeList(String ids);

	String getMaxTime();
}
