package com.ultrapower.lnkhzs.standards.web;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.lnkhzs.standards.service.IFwzlIndicatorService;
import com.ultrapower.omcs.base.service.IBaseExportService;
import com.ultrapower.omcs.base.web.BaseAction;
public class ExportExeclAction extends BaseAction {
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
    
	

	
	private IFwzlIndicatorService fwzlIndicatorService;
	
	private String time;

	public void setFwzlIndicatorService(IFwzlIndicatorService fwzlIndicatorService) {
		this.fwzlIndicatorService = fwzlIndicatorService;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	private IBaseExportService baseExportService;
	
    public void exportModel() throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
    	HttpServletRequest request=ServletActionContext.getRequest();
    	String a=request.getSession().getServletContext().getRealPath("/lnkhzs/standards/家宽标准指标模板.xls");

		/** 根据版本选择创建Workbook的方式 */
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(a));
		/** 得到第一个shell */
		HSSFSheet sheet=wb.getSheetAt(0);
		/** 获取第二行 */
		HSSFRow row2=sheet.getRow(1);
		/** 获取第二行列数 */
		int cellNum2=row2.getPhysicalNumberOfCells();
		/** 得到第三行 */
		HSSFRow row3=sheet.getRow(2);
		
		/** 获取的数据 */
		List<Map<String, String>> pielist = fwzlIndicatorService.getAllPiesInfoExport("宽带",time,"辽宁省中心");
		
		for(int i=1;i<cellNum2;i++){
			//获取cell内容
			HSSFCell cell2=row2.getCell(i);
			String cellValue=cell2.getStringCellValue();
			String[] valueName=cellValue.split("_");
			for(int j=0;j<pielist.size();j++){
				Map<String, String> map=new HashMap<String, String>();
				map=pielist.get(j);
				if(map.get("name").equals(valueName[1])){
					HSSFCell cell3=row3.getCell(i);
					cell3.setCellValue(map.get("value"));
					break;
				}
			}
		}      
		String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
		String fileName = UUIDGenerator.getUUIDoffSpace() + ".xls";
		String path = rootPath + fileName;
		wb.write(new FileOutputStream(path));
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
    		response.setHeader("Content-disposition","attachment;filename=" + str+RandomUtils.getRandomN(6) + ".xls"); 		
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
	
	
	
	
	
	
	private int totalRows = 0;
    /** 总列数 */
    private int totalCells = 0;
    /** 错误信息 */
    private String errorInfo;



    public int getTotalRows() {
        return totalRows;
    }
    public int getTotalCells() {
        return totalCells;
    }
    public String getErrorInfo() {
        return errorInfo;
    }
	
	
	
	
	public boolean validateExcel(String filePath) {
        /** 检查文件名是否为空或者是否是Excel格式的文件 */
        if (filePath == null
                || !(filePath.matches("^.+\\.(?i)(xlsx)$"))) {
            errorInfo = "文件名不是excel格式";
            return false;
        }
        /** 检查文件是否存在 */
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            errorInfo = "文件不存在";
            return false;
        }
        return true;
    }

    public List<List<String>> read(String filePath) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        InputStream is = null;
        try {
            /** 验证文件是否合法 */
            if (!validateExcel(filePath)) {
                System.out.println(errorInfo);
                return null;
            }
            /** 判断文件的类型，是2003还是2007 */
            /*boolean isExcel2003 = true;
            if (WDWUtil.isExcel2007(filePath)) {
                isExcel2003 = false;
            }*/
            /** 调用本类提供的根据流读取的方法 */
            File file = new File(filePath);
            is = new FileInputStream(file);
            dataLst = read(is, false);
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        /** 返回最后读取的结果 */
        return dataLst;
    }


    public List<List<String>> read(InputStream inputStream, boolean isExcel2003) {
        List<List<String>> dataLst = null;
        try {
            /** 根据版本选择创建Workbook的方式 */
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(inputStream);
            } else {
                wb = new XSSFWorkbook(inputStream);
            }
            dataLst = read(wb);
        } catch (IOException e) {
        
            e.printStackTrace();
        }
        return dataLst;
    }
    

    private List<List<String>> read(Workbook wb) {
        List<List<String>> dataLst = new ArrayList<List<String>>();
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);
        /** 得到Excel的行数 */
        this.totalRows = sheet.getPhysicalNumberOfRows();
        /** 得到Excel的列数 */
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        /** 循环Excel的行 */
        for (int r = 0; r < this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<String>();
            /** 循环Excel的列 */
            for (int c = 0; c < this.getTotalCells(); c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (null != cell) {
                    // 以下是判断数据的类型
                    switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                        cellValue = cell.getNumericCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_STRING: // 字符串
                        cellValue = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式
                        cellValue = cell.getCellFormula() + "";
                        break;
                    case HSSFCell.CELL_TYPE_BLANK: // 空值
                        cellValue = "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR: // 故障
                        cellValue = "非法字符";
                        break;
                    default:
                        cellValue = "未知类型";
                        break;
                    }
                }
                rowLst.add(cellValue);
            }
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
        return dataLst;
    }
    
    
    @Test
    public void mytest() throws Exception {
        //ImportExecl poi = new ImportExecl();
        // List<List<String>> list = poi.read("d:/aaa.xls");
    	/*HttpServletRequest request=ServletActionContext.getRequest();
    	String a=request.getSession().getServletContext().getRealPath("/service");*/
        //InputStream is = null;
        try {
            try {
                /** 根据版本选择创建Workbook的方式 */ 
            	HSSFWorkbook wb1 = new HSSFWorkbook(new FileInputStream("D:\\家宽标准指标-XX月1.xls"));
                /** 得到第一个shell */
            	String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
        		String fileName =UUIDGenerator.getUUIDoffSpace() + ".xlsx";
            	String path=rootPath+fileName;
            	wb1.write(new FileOutputStream(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            
        }

    }
    


}
