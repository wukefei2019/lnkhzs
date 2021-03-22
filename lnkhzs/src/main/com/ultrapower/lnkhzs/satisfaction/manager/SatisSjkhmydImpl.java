package com.ultrapower.lnkhzs.satisfaction.manager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtzxktmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisKddsmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisSjkhmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtzxktmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisKddsmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisSjkhmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisSjkhmydImpl implements ISatisSjkhmydService{

	private IDao<SatisSjkhmyd> satisSjkhmydDao;
	

	



	public IDao<SatisSjkhmyd> getSatisSjkhmydDao() {
		return satisSjkhmydDao;
	}



	public void setSatisSjkhmydDao(IDao<SatisSjkhmyd> satisSjkhmydDao) {
		this.satisSjkhmydDao = satisSjkhmydDao;
	}



	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisSjkhmydDao.removeById(idList[i]);
		}
	}
	
	

	@Override
    public Workbook checkVali(File xls) {
        Workbook wb=null;
            try {
                wb =WorkbookFactory.create(new FileInputStream(xls));

            } catch (ExcelTemplateException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExcelTemplateException("导入数据格式或模板不正确！");
            }
        return wb;
    }

    @Override 
    public int importXls(Workbook wb, UserSession user)throws Exception {
        Sheet sheet=wb.getSheetAt(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        
        
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){
                break;
            }
			SatisSjkhmyd satisSjkhmyd = new SatisSjkhmyd();
			satisSjkhmyd.setId(UUIDGenerator.getUUIDoffSpace());
			satisSjkhmyd.setDynum(ExcelUtils.getString(row, 0));
			satisSjkhmyd.setIndextype(ExcelUtils.getString(row, 1));	
			satisSjkhmyd.setResp_dept(ExcelUtils.getString(row, 2));
			
			/*satisSjkhmyd.setSatis(ExcelUtils.getString(row, 3));
			satisSjkhmyd.setTotalsatis(ExcelUtils.getString(row, 4));
			satisSjkhmyd.setTg_total(ExcelUtils.getString(row, 5));
			satisSjkhmyd.setTg_clear(ExcelUtils.getString(row, 6));
			satisSjkhmyd.setTg_convenient(ExcelUtils.getString(row, 7));
			satisSjkhmyd.setTg_standard(ExcelUtils.getString(row, 8));
			satisSjkhmyd.setTg_fit(ExcelUtils.getString(row, 9));
			satisSjkhmyd.setTg_service(ExcelUtils.getString(row, 10));
			satisSjkhmyd.setNq_total(ExcelUtils.getString(row, 11));
			satisSjkhmyd.setNq_net(ExcelUtils.getString(row, 12));
			satisSjkhmyd.setNq_tel(ExcelUtils.getString(row, 13));
			satisSjkhmyd.setBusinesspublicity(ExcelUtils.getString(row, 14));
			satisSjkhmyd.setRemindservice(ExcelUtils.getString(row, 15));*/
			
			satisSjkhmyd.setSatis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
			satisSjkhmyd.setTotalsatis(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
			satisSjkhmyd.setTg_total(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
			satisSjkhmyd.setTg_clear(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
			satisSjkhmyd.setTg_convenient(String.format("%.2f", ExcelUtils.getDouble(row, 7)));
			satisSjkhmyd.setTg_standard(String.format("%.2f", ExcelUtils.getDouble(row, 8)));
			satisSjkhmyd.setTg_fit(String.format("%.2f", ExcelUtils.getDouble(row, 9)));
			satisSjkhmyd.setTg_service(String.format("%.2f", ExcelUtils.getDouble(row, 10)));
			satisSjkhmyd.setNq_total(String.format("%.2f", ExcelUtils.getDouble(row, 11)));
			satisSjkhmyd.setNq_net(String.format("%.2f", ExcelUtils.getDouble(row, 12)));
			satisSjkhmyd.setNq_tel(String.format("%.2f", ExcelUtils.getDouble(row, 13)));
			satisSjkhmyd.setBusinesspublicity(String.format("%.2f", ExcelUtils.getDouble(row, 14)));
			satisSjkhmyd.setRemindservice(String.format("%.2f", ExcelUtils.getDouble(row, 15)));
			
			satisSjkhmydDao.save(satisSjkhmyd);
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }

}
