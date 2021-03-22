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
import com.ultrapower.lnkhzs.satisfaction.model.SatisQykdktmyd;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisQykdktmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisQykdktmydImpl implements ISatisQykdktmydService{

	private IDao<SatisQykdktmyd> satisQykdktmydDao;
	

	



	public IDao<SatisQykdktmyd> getSatisQykdktmydDao() {
		return satisQykdktmydDao;
	}



	public void setSatisQykdktmydDao(IDao<SatisQykdktmyd> satisQykdktmydDao) {
		this.satisQykdktmydDao = satisQykdktmydDao;
	}



	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisQykdktmydDao.removeById(idList[i]);
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
				SatisQykdktmyd satisQykdktmyd = new SatisQykdktmyd();
				satisQykdktmyd.setId(UUIDGenerator.getUUIDoffSpace());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
				Date date = simpleDateFormat.parse(ExcelUtils.getString(row, 0));
				satisQykdktmyd.setMonth(sdf.format(date));
				satisQykdktmyd.setResp_dept(ExcelUtils.getString(row, 1));
				/*satisQykdktmyd.setSatis(ExcelUtils.getString(row, 2));
				satisQykdktmyd.setTotalsatis(ExcelUtils.getString(row, 3));
				satisQykdktmyd.setTimeliness(ExcelUtils.getString(row, 4));
				satisQykdktmyd.setSpeciality(ExcelUtils.getString(row, 5));
				satisQykdktmyd.setPersonservice(ExcelUtils.getString(row, 6));
				satisQykdktmyd.setSample_num(ExcelUtils.getString(row, 7));*/
				
				
				//String.format("%.2f", ExcelUtils.getDouble(row, 3))
				satisQykdktmyd.setSatis(String.format("%.2f", ExcelUtils.getDouble(row, 2)));
				satisQykdktmyd.setTotalsatis(String.format("%.2f", ExcelUtils.getDouble(row, 3)));
				satisQykdktmyd.setTimeliness(String.format("%.2f", ExcelUtils.getDouble(row, 4)));
				satisQykdktmyd.setSpeciality(String.format("%.2f", ExcelUtils.getDouble(row, 5)));
				satisQykdktmyd.setPersonservice(String.format("%.2f", ExcelUtils.getDouble(row, 6)));
				satisQykdktmyd.setSample_num(ExcelUtils.getString(row, 7));
				satisQykdktmydDao.save(satisQykdktmyd);
            	
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }



	@Override
	public String getMaxTime() {
		List<String> list=satisQykdktmydDao.find("SELECT MAX(month) FROM SatisQykdktmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
