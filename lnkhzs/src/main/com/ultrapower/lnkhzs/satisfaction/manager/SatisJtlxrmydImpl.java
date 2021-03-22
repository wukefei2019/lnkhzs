package com.ultrapower.lnkhzs.satisfaction.manager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtlxrmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtlxrmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisJtlxrmydImpl implements ISatisJtlxrmydService {

	private IDao<SatisJtlxrmyd> satisJtlxrmydDao;

	public IDao<SatisJtlxrmyd> getSatisJtlxrmydDao() {
		return satisJtlxrmydDao;
	}

	public void setSatisJtlxrmydDao(IDao<SatisJtlxrmyd> satisJtlxrmydDao) {
		this.satisJtlxrmydDao = satisJtlxrmydDao;
	}

	/**
	 * 批量删除
	 */

	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for (int i = 0; i < idList.length; i++) {
			satisJtlxrmydDao.removeById(idList[i]);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				break;
			}

			SatisJtlxrmyd satisJtlxrmyd = new SatisJtlxrmyd();
			satisJtlxrmyd.setId(UUIDGenerator.getUUIDoffSpace());
			satisJtlxrmyd.setDynum(ExcelUtils.getString(row, 0));
			satisJtlxrmyd.setResp_dept(ExcelUtils.getString(row, 1));
			satisJtlxrmyd.setPolicy_satis(ExcelUtils.getString(row, 2));
			satisJtlxrmyd.setWhole_satis(ExcelUtils.getString(row, 3));
			satisJtlxrmyd.setPlan(ExcelUtils.getString(row, 4));
			satisJtlxrmyd.setPerformance(ExcelUtils.getString(row, 5));
			satisJtlxrmyd.setWhole_pro(ExcelUtils.getString(row, 6));
			satisJtlxrmyd.setSecurity(ExcelUtils.getString(row, 7));
			satisJtlxrmyd.setStability(ExcelUtils.getString(row, 8));
			satisJtlxrmyd.setWhole_ser(ExcelUtils.getString(row, 9));
			satisJtlxrmyd.setAttitude(ExcelUtils.getString(row, 10));
			satisJtlxrmyd.setTimely(ExcelUtils.getString(row, 11));
			satisJtlxrmyd.setBill(ExcelUtils.getString(row, 12));
			satisJtlxrmydDao.save(satisJtlxrmyd);

		}
		return sheet.getPhysicalNumberOfRows();

	}

	@Override
	public String getMaxTime() {
		List<String> list=satisJtlxrmydDao.find("SELECT MAX(dynum) FROM SatisJtlxrmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
