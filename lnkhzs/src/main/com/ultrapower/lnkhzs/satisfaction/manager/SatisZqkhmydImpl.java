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
import com.ultrapower.lnkhzs.satisfaction.model.SatisZqkhmyd;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisZqkhmydService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisZqkhmydImpl implements ISatisZqkhmydService{

	
	private IDao<SatisZqkhmyd> satisZqkhmydDao;
	
	
	public IDao<SatisZqkhmyd> getSatisZqkhmydDao() {
		return satisZqkhmydDao;
	}

	public void setSatisZqkhmydDao(IDao<SatisZqkhmyd> satisZqkhmydDao) {
		this.satisZqkhmydDao = satisZqkhmydDao;
	}



	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisZqkhmydDao.removeById(idList[i]);
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
        
        
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){
                break;
            }
           
			SatisZqkhmyd satisZqkhmyd = new SatisZqkhmyd();
			satisZqkhmyd.setId(UUIDGenerator.getUUIDoffSpace());
			satisZqkhmyd.setDynum(ExcelUtils.getString(row, 0));
			satisZqkhmyd.setResp_dept(ExcelUtils.getString(row, 1));
			satisZqkhmyd.setCust_satis(ExcelUtils.getString(row, 2));
			satisZqkhmyd.setGroup_satis(ExcelUtils.getString(row, 3));
			satisZqkhmyd.setContacts(ExcelUtils.getString(row, 4));
			satisZqkhmyd.setLine_satis(ExcelUtils.getString(row, 5));
			satisZqkhmyd.setOpen_satis(ExcelUtils.getString(row, 6));
			satisZqkhmydDao.save(satisZqkhmyd);
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }

	@Override
	public String getMaxTime() {
		List<String> list=satisZqkhmydDao.find("SELECT MAX(dynum) FROM SatisZqkhmyd");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}
}
