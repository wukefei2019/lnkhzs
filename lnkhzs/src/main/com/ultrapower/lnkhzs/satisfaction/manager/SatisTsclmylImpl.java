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
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisTsclmylImpl implements ISatisTsclmylService{

	private IDao<SatisTsclmyl> satisTsclmylDao;
	
	
	public IDao<SatisTsclmyl> getSatisTsclmylDao() {
		return satisTsclmylDao;
	}

	public void setSatisTsclmylDao(IDao<SatisTsclmyl> satisTsclmylDao) {
		this.satisTsclmylDao = satisTsclmylDao;
	}
	
	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisTsclmylDao.removeById(idList[i]);
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
            			SatisTsclmyl satisTsclmyl = new SatisTsclmyl();
            			satisTsclmyl.setId(UUIDGenerator.getUUIDoffSpace());
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
						Date date = simpleDateFormat.parse(ExcelUtils.getString(row, 0));
						satisTsclmyl.setMonth(sdf.format(date));
            			satisTsclmyl.setResp_dept(ExcelUtils.getString(row, 1));
            			//satisTsclmyl.setSatis(ExcelUtils.getString(row, 2));
            			satisTsclmyl.setSatis(String.format("%.2f", ExcelUtils.getDouble(row, 2)));
            			satisTsclmyl.setQuestiony(String.format("%.2f",ExcelUtils.getDouble(row, 3)*100)+"%");
            			satisTsclmyl.setQuestionn(String.format("%.2f",ExcelUtils.getDouble(row, 4)*100)+"%");
            			satisTsclmyl.setThreeago(String.format("%.2f",ExcelUtils.getDouble(row, 5)*100)+"%");
            			satisTsclmyl.setThreefive(String.format("%.2f",ExcelUtils.getDouble(row, 6)*100)+"%");
            			satisTsclmyl.setFivelater(String.format("%.2f",ExcelUtils.getDouble(row, 7)*100)+"%");
            			satisTsclmyl.setSample_num(ExcelUtils.getString(row, 8));
            			satisTsclmylDao.save(satisTsclmyl);
            	
        }
        return sheet.getPhysicalNumberOfRows();
        
    }

	@Override
	public String getMaxTime() {
		List<String> list=satisTsclmylDao.find("SELECT MAX(month) FROM SatisTsclmyl");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
