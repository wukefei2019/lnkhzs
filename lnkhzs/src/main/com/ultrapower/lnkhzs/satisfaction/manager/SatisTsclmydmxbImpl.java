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
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmydmxb;
import com.ultrapower.lnkhzs.satisfaction.model.SatisTsclmyl;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmydmxbService;
import com.ultrapower.lnkhzs.satisfaction.service.ISatisTsclmylService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

public class SatisTsclmydmxbImpl implements ISatisTsclmydmxbService{

	private IDao<SatisTsclmydmxb> satisTsclmydmxbDao;
	
	
	public IDao<SatisTsclmydmxb> getSatisTsclmydmxbDao() {
		return satisTsclmydmxbDao;
	}

	public void setSatisTsclmydmxbDao(IDao<SatisTsclmydmxb> satisTsclmydmxbDao) {
		this.satisTsclmydmxbDao = satisTsclmydmxbDao;
	}
	
	/**
	 * 批量删除
	 */
	
	@Override
	public void deledeList(String ids) {
		String[] idList = ids.split(",");
		for(int i =0;i<idList.length;i++){
			satisTsclmydmxbDao.removeById(idList[i]);
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

            			SatisTsclmydmxb satisTsclmydmxb = new SatisTsclmydmxb();
            			satisTsclmydmxb.setId(UUIDGenerator.getUUIDoffSpace());
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
						Date date = simpleDateFormat.parse(ExcelUtils.getString(row, 0));
						satisTsclmydmxb.setMonth(sdf.format(date));
						satisTsclmydmxb.setResp_dept(ExcelUtils.getString(row, 1));
						satisTsclmydmxb.setArea_name(ExcelUtils.getString(row, 2));					
						satisTsclmydmxb.setReseau(ExcelUtils.getString(row, 3));
						satisTsclmydmxb.setPhone_number(ExcelUtils.getString(row, 4));
						satisTsclmydmxb.setOrder_number(ExcelUtils.getString(row, 5));
						
						satisTsclmydmxb.setOrder_stime(ExcelUtils.getString(row, 6));
						satisTsclmydmxb.setOrder_type(ExcelUtils.getString(row, 7));
						satisTsclmydmxb.setR_department(ExcelUtils.getString(row, 8));
					
						satisTsclmydmxb.setOrder_etime(ExcelUtils.getString(row, 9));
						satisTsclmydmxb.setQuestion(ExcelUtils.getString(row, 10));
						satisTsclmydmxb.setWork_team(ExcelUtils.getString(row, 11));
						satisTsclmydmxb.setFwork_team(ExcelUtils.getString(row, 12));
						satisTsclmydmxb.setAwork_team(ExcelUtils.getString(row,13));						

						satisTsclmydmxb.setOverall_time(ExcelUtils.getString(row, 14));
						satisTsclmydmxb.setCl_time(ExcelUtils.getString(row, 15));
						satisTsclmydmxb.setSatis(ExcelUtils.getString(row, 16));
						satisTsclmydmxb.setNquestion(ExcelUtils.getString(row, 17));
						satisTsclmydmxb.setNtime(ExcelUtils.getString(row, 18));

            			satisTsclmydmxbDao.save(satisTsclmydmxb);
            	
        }
        return sheet.getPhysicalNumberOfRows();
        
    }

	@Override
	public String getMaxTime() {
		List<String> list=satisTsclmydmxbDao.find("SELECT MAX(month) FROM SatisTsclmydmxb");
		String maxTime="";
		if(list.size()>0){
			maxTime=list.get(0);
		}
		return maxTime;
	}

}
