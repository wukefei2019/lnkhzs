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
import com.ultrapower.lnkhzs.satisfaction.model.SatisJtzxktmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisKddsmyd;
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtzxktmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisKddsmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisKddsmydImpl implements ISatisKddsmydService{

	private IDao<SatisKddsmyd> satisKddsmydDao;
	

	public IDao<SatisKddsmyd> getSatisKddsmydDao() {
		return satisKddsmydDao;
	}


	public void setSatisKddsmydDao(IDao<SatisKddsmyd> satisKddsmydDao) {
		this.satisKddsmydDao = satisKddsmydDao;
	}



	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisKddsmydDao.removeById(idList[i]);
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
				SatisKddsmyd satisKddsmyd = new SatisKddsmyd();
				satisKddsmyd.setId(UUIDGenerator.getUUIDoffSpace());
				satisKddsmyd.setDynum(ExcelUtils.getString(row, 0));
				satisKddsmyd.setResp_dept(ExcelUtils.getString(row, 1));
				/*satisKddsmyd.setSatis(ExcelUtils.getString(row, 2));
				satisKddsmyd.setTotalsatis(ExcelUtils.getString(row, 3));
				satisKddsmyd.setTpq_total(ExcelUtils.getString(row, 4));
				satisKddsmyd.setTpq_box(ExcelUtils.getString(row, 5));
				satisKddsmyd.setTpq_rcontrol(ExcelUtils.getString(row, 6));
				satisKddsmyd.setCe_total(ExcelUtils.getString(row, 7));
				satisKddsmyd.setCe_rich(ExcelUtils.getString(row, 8));
				satisKddsmyd.setCe_speed(ExcelUtils.getString(row, 9));
				satisKddsmyd.setUe_total(ExcelUtils.getString(row, 10));
				satisKddsmyd.setUe_smooth(ExcelUtils.getString(row, 11));
				satisKddsmyd.setUe_oper(ExcelUtils.getString(row, 12));
				satisKddsmyd.setBhue_total(ExcelUtils.getString(row, 13));
				satisKddsmyd.setBhue_handle(ExcelUtils.getString(row, 14));
				satisKddsmyd.setBhue_cancel(ExcelUtils.getString(row, 15));
				satisKddsmyd.setAftersaleservice(ExcelUtils.getString(row, 16));*/
				
				
				satisKddsmyd.setSatis(String.format("%.2f", ExcelUtils.getDouble(row, 2)));
				satisKddsmyd.setTotalsatis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
				satisKddsmyd.setTpq_total(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
				satisKddsmyd.setTpq_box(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
				satisKddsmyd.setTpq_rcontrol(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
				satisKddsmyd.setCe_total(String.format("%.2f", ExcelUtils.getDouble(row, 7)));
				satisKddsmyd.setCe_rich(String.format("%.2f", ExcelUtils.getDouble(row, 8)));
				satisKddsmyd.setCe_speed(String.format("%.2f", ExcelUtils.getDouble(row, 9)));
				satisKddsmyd.setUe_total(String.format("%.2f", ExcelUtils.getDouble(row, 10)));
				satisKddsmyd.setUe_smooth(String.format("%.2f", ExcelUtils.getDouble(row, 11)));
				satisKddsmyd.setUe_oper(String.format("%.2f", ExcelUtils.getDouble(row, 12)));
				satisKddsmyd.setBhue_total(String.format("%.2f", ExcelUtils.getDouble(row, 13)));
				satisKddsmyd.setBhue_handle(String.format("%.2f", ExcelUtils.getDouble(row, 14)));
				satisKddsmyd.setBhue_cancel(String.format("%.2f", ExcelUtils.getDouble(row, 15)));
				satisKddsmyd.setAftersaleservice(String.format("%.2f", ExcelUtils.getDouble(row, 16)));
				
				satisKddsmydDao.save(satisKddsmyd);
            	
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }


	@Override
	public String getMaxTime() {
		List<String> list=satisKddsmydDao.find("SELECT MAX(dynum) FROM SatisKddsmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
