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
import com.ultrapower.lnkhzs.satisfaction.model.SatisJkkhmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJkkhmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisJkkhmydImpl implements ISatisJkkhmydService {

	private IDao<SatisJkkhmyd> satisJkkhmydDao;

	public IDao<SatisJkkhmyd> getSatisJkkhmydDao() {
		return satisJkkhmydDao;
	}

	public void setSatisJkkhmydDao(IDao<SatisJkkhmyd> satisJkkhmydDao) {
		this.satisJkkhmydDao = satisJkkhmydDao;
	}

	/**
	 * 批量删除
	 */

	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for (int i = 0; i < idList.length; i++) {
			satisJkkhmydDao.removeById(idList[i]);
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

			SatisJkkhmyd satisJkkhmyd = new SatisJkkhmyd();
			satisJkkhmyd.setId(UUIDGenerator.getUUIDoffSpace());
			satisJkkhmyd.setDynum(ExcelUtils.getString(row, 0));
			satisJkkhmyd.setIndextype(ExcelUtils.getString(row, 1));
			satisJkkhmyd.setResp_dept(ExcelUtils.getString(row, 2));
			
			/*satisJkkhmyd.setCustomer_satis(ExcelUtils.getString(row, 3));
			satisJkkhmyd.setWhole_satis(ExcelUtils.getString(row, 4));
			satisJkkhmyd.setPackagefee(ExcelUtils.getString(row, 5));
			satisJkkhmyd.setQuality(ExcelUtils.getString(row, 6));
			satisJkkhmyd.setWhole_business(ExcelUtils.getString(row, 7));
			satisJkkhmyd.setPropaganda(ExcelUtils.getString(row, 8));
			satisJkkhmyd.setHandle(ExcelUtils.getString(row, 9));
			satisJkkhmyd.setWhole_service(ExcelUtils.getString(row, 10));
			satisJkkhmyd.setInstall(ExcelUtils.getString(row, 11));
			satisJkkhmyd.setFault(ExcelUtils.getString(row, 12));
			satisJkkhmyd.setConsultation(ExcelUtils.getString(row, 13));*/
			
			satisJkkhmyd.setCustomer_satis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
			satisJkkhmyd.setWhole_satis(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
			satisJkkhmyd.setPackagefee(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
			satisJkkhmyd.setQuality(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
			satisJkkhmyd.setWhole_business(String.format("%.2f", ExcelUtils.getDouble(row, 7)));
			satisJkkhmyd.setPropaganda(String.format("%.2f", ExcelUtils.getDouble(row, 8)));
			satisJkkhmyd.setHandle(String.format("%.2f", ExcelUtils.getDouble(row, 9)));
			satisJkkhmyd.setWhole_service(String.format("%.2f", ExcelUtils.getDouble(row, 10)));
			satisJkkhmyd.setInstall(String.format("%.2f", ExcelUtils.getDouble(row, 11)));
			satisJkkhmyd.setFault(String.format("%.2f", ExcelUtils.getDouble(row, 12)));
			satisJkkhmyd.setConsultation(String.format("%.2f", ExcelUtils.getDouble(row, 13)));
			
			
			satisJkkhmydDao.save(satisJkkhmyd);



		}
		return sheet.getPhysicalNumberOfRows();

	}
}
