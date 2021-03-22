package com.ultrapower.eoms.ultrasm.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.ultrasm.service.CommonExcelService;

public class CommonExcelImpl implements CommonExcelService
{

	@Override
	public List<String> exportExcelByDt(HttpServletResponse response, String cfgMark, DataTable dataTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> exportExcelByCfg(HttpServletResponse response, String cfgMark, String cacheid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> importExcelByCfg(File file, String xmlPath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> importExcelByCfgToRemedy(File file, String xmlPath) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> downloadImportTemplate(HttpServletResponse response, String xmlPath) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
