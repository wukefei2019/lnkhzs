package com.ultrapower.lnkhzs.satisfaction.manager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtgjrmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtlxrmyd;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtgjrmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisJtgjrmydImpl implements ISatisJtgjrmydService{

	private IDao<SatisJtgjrmyd> satisJtgjrmydDao;
	

	public IDao<SatisJtgjrmyd> getSatisJtgjrmydDao() {
		return satisJtgjrmydDao;
	}

	public void setSatisJtgjrmydDao(IDao<SatisJtgjrmyd> satisJtgjrmydDao) {
		this.satisJtgjrmydDao = satisJtgjrmydDao;
	}

	/**
	 * 批量删除
	 */

	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for (int i = 0; i < idList.length; i++) {
			satisJtgjrmydDao.removeById(idList[i]);
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
			SatisJtgjrmyd satisJtgjrmyd = new SatisJtgjrmyd();
			satisJtgjrmyd.setId(UUIDGenerator.getUUIDoffSpace());
			satisJtgjrmyd.setDynum(ExcelUtils.getString(row, 0));
			satisJtgjrmyd.setResp_dept(ExcelUtils.getString(row, 1));
			
			
			/*satisJtgjrmyd.setPolicy_satis(ExcelUtils.getString(row, 2));
			satisJtgjrmyd.setWhole_satis(ExcelUtils.getString(row, 3));
			satisJtgjrmyd.setPerformance(ExcelUtils.getString(row, 4));
			satisJtgjrmyd.setReliable(ExcelUtils.getString(row, 5));
			satisJtgjrmyd.setWhole(ExcelUtils.getString(row, 6));
			satisJtgjrmyd.setSkill(ExcelUtils.getString(row, 7));
			satisJtgjrmyd.setAttitude(ExcelUtils.getString(row, 8));
			satisJtgjrmyd.setTimely(ExcelUtils.getString(row, 9));*/
			
			satisJtgjrmyd.setPolicy_satis(String.format("%.2f", ExcelUtils.getDouble(row, 2)));
			satisJtgjrmyd.setWhole_satis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
			satisJtgjrmyd.setPerformance(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
			satisJtgjrmyd.setReliable(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
			satisJtgjrmyd.setWhole(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
			satisJtgjrmyd.setSkill(String.format("%.2f", ExcelUtils.getDouble(row, 7)));
			satisJtgjrmyd.setAttitude(String.format("%.2f", ExcelUtils.getDouble(row, 8)));
			satisJtgjrmyd.setTimely(String.format("%.2f", ExcelUtils.getDouble(row, 9)));
			
			
			
			satisJtgjrmydDao.save(satisJtgjrmyd);

		}
		return sheet.getPhysicalNumberOfRows();

	}
	
}
