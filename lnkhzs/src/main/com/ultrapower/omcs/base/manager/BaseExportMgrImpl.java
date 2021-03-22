package com.ultrapower.omcs.base.manager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.web.ActionContext;
import com.ultrapower.eoms.common.plugin.excelutils.ExcelWriter;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.omcs.base.model.IaExportFile;
import com.ultrapower.omcs.base.service.IBaseExportService;
import com.ultrapower.omcs.utils.StringUtils;
public class BaseExportMgrImpl implements IBaseExportService {
	
	private IDao<IaExportFile> iaExportFileDao;
	public void setIaExportFileDao(IDao<IaExportFile> iaExportFileDao) {
		this.iaExportFileDao = iaExportFileDao;
	}

	/**
	 * 导出EXCEL
	 */
	public String exportExcel(HttpServletResponse response, HashMap<String, String> params,String[] titles,String[] columns,String[] codeDetail,String sheetTitle,String rQueryName,String sheetName) throws IOException {
		String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
		String fileName =sheetTitle+UUIDGenerator.getUUIDoffSpace() + ".xlsx";
		try {
			ExcelWriter ew = new ExcelWriter();
			ew.setExcelFilePath(rootPath);
			
			ew.setExcelName(fileName);
			ew.addTitle(titles);
			
			RQuery rq = new RQuery(rQueryName, params);
			int dataNum = rq.getDataTableCount();

			DataTable dt = null;
			for(int x=0;x<=Math.ceil(dataNum/5000);x++) {
				dt=rq.getDataTable(x+1,5000,1);
				List<List<String[]>> l = new ArrayList<List<String[]>>();
				for (int i = 0; i < dt.length(); i++) {
					DataRow row = dt.getDataRow(i);
					List<String[]> lr = new ArrayList<String[]>();
					for (int j = 0; j < columns.length; j++) {
						String value = row.getString(columns[j]);
						lr.add(new String[] { String.valueOf(j), value });
					}
					l.add(lr);
				}
				ew.addDatas(l);
			}

			if(StringUtils.isNotBlank(sheetName)) {
				ew.setSheetName(sheetName);
			}
			
			ew.createExcel();
			
			IaExportFile iaExportFile =new IaExportFile();
			iaExportFile.setPid(UUIDGenerator.getUUIDoffSpace());
			iaExportFile.setExportsql(rQueryName);
			iaExportFile.setFilename(fileName);
			iaExportFile.setPath(rootPath+fileName);
			iaExportFile.setExporttime(TimeUtils.getCurrentTime());
			iaExportFile.setLoginname(ActionContext.getUserSession().getLoginName());
			iaExportFileDao.save(iaExportFile);
		}catch (Exception e) {
			
		}
		return rootPath+fileName;

	}
	
    private Map<String,String> getItemMap(){
        Map<String,String> map =new HashMap<String,String>();
        QueryAdapter omcs = new QueryAdapter(Constants.DATABASE_ALIAS);
        DataTable dt = omcs.executeQuery("SELECT DTCODE,diname,divalue FROM BS_T_SM_DICITEM WHERE DTID LIKE '2017%' or dtcode='isdefault'");
        Iterator<DataRow> rowsIt = dt.getRowList().iterator();
        while (rowsIt.hasNext()) {
            DataRow dataRow = (DataRow) rowsIt.next();
            map.put(dataRow.getString("DTCODE")+"_"+dataRow.getString("divalue"), dataRow.getString("diname"));
        }
        return map;
    }
    
    
	public String exportExcelWithList(HttpServletResponse response,String[] titles,List<String[]> list,String sheetTitle) throws IOException {
		String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
		String fileName =sheetTitle+UUIDGenerator.getUUIDoffSpace() + ".xlsx";
		try {
			ExcelWriter ew = new ExcelWriter();
			ew.setExcelFilePath(rootPath);
			
			ew.setExcelName(fileName);
			ew.addTitle(titles);
			List<List<String[]>> l = new ArrayList<List<String[]>>();
			for (int i = 0; i <list.size(); i++) {
				List<String[]> lr = new ArrayList<String[]>();
				for (int j = 0; j < list.get(i).length; j++) {
					String value =  list.get(i)[j];
					lr.add(new String[] { String.valueOf(j), value });
				}
				l.add(lr);
			}
			ew.addDatas(l);
			ew.createExcel();
			
			IaExportFile iaExportFile =new IaExportFile();
			iaExportFile.setPid(UUIDGenerator.getUUIDoffSpace());
			iaExportFile.setExportsql("");
			iaExportFile.setFilename(fileName);
			iaExportFile.setPath(rootPath+fileName);
			iaExportFile.setExporttime(TimeUtils.getCurrentTime());
			iaExportFile.setLoginname(ActionContext.getUserSession().getLoginName());
			iaExportFileDao.save(iaExportFile);
		}catch (Exception e) {
			
		}
		return rootPath+fileName;

	}
   
}