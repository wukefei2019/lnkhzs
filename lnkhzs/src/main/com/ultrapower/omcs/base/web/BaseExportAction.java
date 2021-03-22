package com.ultrapower.omcs.base.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.omcs.base.service.IBaseExportService;
public class BaseExportAction extends BaseAction {
    /**
     * 公共导出 吴克非
     */
    private static final long serialVersionUID = -1990215200187816036L;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private HashMap<String,String> params;
    private String[] titles;
    private String[] columns;
    private String[] codeDetail;
    private String sheetTitle;
    private String rQueryName;
    private String path;
    
	
	private String sheetName;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}


	private IBaseExportService baseExportService;
    public void exportModel() throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
    	HttpServletResponse response = ServletActionContext.getResponse();
    	String path = baseExportService.exportExcel(response, params, titles, columns, codeDetail, sheetTitle, rQueryName,sheetName);
    	this.renderText(path);
    }
    
	public void exportExcel() throws Exception {
    	HttpServletResponse response = super.getResponse();
    	BufferedInputStream	bis = null;
		BufferedOutputStream  bos = null;
        try {

        	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
    		String str = cal.get(Calendar.YEAR) + ""
    				+ (cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.DATE);

    		response.setContentType("application/x-msdownload");
    		response.setHeader("Content-disposition","attachment;filename=" + str+RandomUtils.getRandomN(6) + ".xlsx"); 		
    		File file =new File(path);
    		bis = new BufferedInputStream(new FileInputStream(file));
    		bos = new BufferedOutputStream(this.getResponse().getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

			/*Workbook wb = null;
			wb = new XSSFWorkbook(input);
			wb.write(out);
			out.flush();*/
			bis.close();
			bos.close();

        } catch (Exception e) {
        	
        	
        }finally {
        	try {
        		bis.close();
        	}catch(Exception e){}
        	try {
    			bos.close();
            }catch(Exception e){}
        }
    }
    
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	public String[] getTitles() {
		return titles;
	}
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public String[] getCodeDetail() {
		return codeDetail;
	}
	public void setCodeDetail(String[] codeDetail) {
		this.codeDetail = codeDetail;
	}
	public String getSheetTitle() {
		return sheetTitle;
	}
	public void setSheetTitle(String sheetTitle) {
		this.sheetTitle = sheetTitle;
	}
	public String getrQueryName() {
		return rQueryName;
	}
	public void setrQueryName(String rQueryName) {
		this.rQueryName = rQueryName;
	}
	public void setBaseExportService(IBaseExportService baseExportService) {
		this.baseExportService = baseExportService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
