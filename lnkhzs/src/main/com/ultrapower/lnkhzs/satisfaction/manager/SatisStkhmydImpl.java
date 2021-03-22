package com.ultrapower.lnkhzs.satisfaction.manager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtgjrmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisStkhmyd;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisStkhmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisStkhmydImpl implements ISatisStkhmydService{

	private IDao<SatisStkhmyd> satisStkhmydDao;

	public IDao<SatisStkhmyd> getSatisStkhmydDao() {
		return satisStkhmydDao;
	}

	public void setSatisStkhmydDao(IDao<SatisStkhmyd> satisStkhmydDao) {
		this.satisStkhmydDao = satisStkhmydDao;
	}
	
	
	/**
	 * 批量删除
	 */

	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for (int i = 0; i < idList.length; i++) {
			satisStkhmydDao.removeById(idList[i]);
		}
	}

	@Override
	public Workbook checkVali(File xls) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(new FileInputStream(xls));

		} catch (ExcelTemplateException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelTemplateException("导入数据格式或模板不正确！");
		}
		return wb;
	}

	@Override
	public int importXls(Workbook wb, UserSession user) throws Exception {
		Sheet sheet = wb.getSheetAt(0);

		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				break;
			}
				SatisStkhmyd satisStkhmyd = new SatisStkhmyd();
				satisStkhmyd.setId(UUIDGenerator.getUUIDoffSpace());
				satisStkhmyd.setDynum(ExcelUtils.getString(row, 0));
				satisStkhmyd.setResp_dept(ExcelUtils.getString(row, 1));
				satisStkhmyd.setCust_satis(ExcelUtils.getString(row, 2));
				satisStkhmyd.setBusiness(ExcelUtils.getString(row, 3));
				satisStkhmyd.setDiscount(ExcelUtils.getString(row, 4));
				satisStkhmydDao.save(satisStkhmyd);

		}
		return sheet.getPhysicalNumberOfRows();

	}

	@Override
	public String getMaxTime() {
		List<String> list=satisStkhmydDao.find("SELECT MAX(dynum) FROM SatisStkhmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}
	
}
