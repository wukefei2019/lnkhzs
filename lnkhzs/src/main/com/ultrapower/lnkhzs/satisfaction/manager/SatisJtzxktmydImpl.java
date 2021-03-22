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
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisJtzxktmydService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisJtzxktmydImpl implements ISatisJtzxktmydService{

	private IDao<SatisJtzxktmyd> satisJtzxktmydDao;
	

	public IDao<SatisJtzxktmyd> getSatisJtzxktmydDao() {
		return satisJtzxktmydDao;
	}



	public void setSatisJtzxktmydDao(IDao<SatisJtzxktmyd> satisJtzxktmydDao) {
		this.satisJtzxktmydDao = satisJtzxktmydDao;
	}



	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisJtzxktmydDao.removeById(idList[i]);
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

			SatisJtzxktmyd satisJtzxktmyd = new SatisJtzxktmyd();
			satisJtzxktmyd.setId(UUIDGenerator.getUUIDoffSpace());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = simpleDateFormat.parse(ExcelUtils.getString(row, 0));
			satisJtzxktmyd.setMonth(sdf.format(date));
			satisJtzxktmyd.setResp_dept(ExcelUtils.getString(row, 1));
			/*satisJtzxktmyd.setSatis(ExcelUtils.getString(row, 2));
			satisJtzxktmyd.setTotalsatis(ExcelUtils.getString(row, 3));
			satisJtzxktmyd.setTimeliness(ExcelUtils.getString(row, 4));
			satisJtzxktmyd.setSpeciality(ExcelUtils.getString(row, 5));
			satisJtzxktmyd.setPersonservice(ExcelUtils.getString(row, 6));
			satisJtzxktmyd.setSample_num(ExcelUtils.getString(row, 7));*/

			satisJtzxktmyd.setSatis(String.format("%.2f", ExcelUtils.getDouble(row, 2)));
			satisJtzxktmyd.setTotalsatis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
			satisJtzxktmyd.setTimeliness(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
			satisJtzxktmyd.setSpeciality(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
			satisJtzxktmyd.setPersonservice(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
			satisJtzxktmyd.setSample_num(new Double(ExcelUtils.getDouble(row, 7)).intValue()+"");
			/*satisJtzxktmyd.setRow_num(i);*/
			
			satisJtzxktmydDao.save(satisJtzxktmyd);
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }



	@Override
	public String getMaxTime() {
		List<String> list=satisJtzxktmydDao.find("SELECT MAX(month) FROM SatisJtzxktmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
