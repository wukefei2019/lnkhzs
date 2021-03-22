package com.ultrapower.omcs.base.service;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
public interface IBaseExportService {
    /**
   	 * 导出EXCEL
     * @return 
   	 * 
   	 */
   	public String exportExcel(HttpServletResponse response,HashMap<String,String> params,String[] titles,String[] columns,String[] codeDetail,String sheetTitle,String rQueryName,String sheetName) throws IOException;

   	public String exportExcelWithList(HttpServletResponse response,String[] titles,List<String[]> list,String sheetTitle) throws IOException;

}
