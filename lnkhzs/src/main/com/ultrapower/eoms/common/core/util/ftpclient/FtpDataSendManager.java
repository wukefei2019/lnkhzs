package com.ultrapower.eoms.common.core.util.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;


public class FtpDataSendManager{

	
	/**
	 * 查询数据发送FTP
	 * @param ftpIP
	 * @param ftpPort
	 * @param ftpPath
	 * @param fileName
	 * @param sql
	 * @param template
	 * @param dataStartX
	 * @param dataStartY
	 * @param params
	 * @throws Exception
	 */
	public static void sendDataFile(String ftpIp, String ftpPort, String userName, String passWord,
			String ftpPath, String fileName, String sql, 
			String template, int dataStartRow, int dataStartCol, Map<String,String> params) throws Exception{
		File f = null;
		FileInputStream fInput = null;
		File out = null;
		File outFile = null;
		OutputStream output = null;
		
		try{
			if(StringUtils.isBlank(sql)){
				throw new Exception("SQL为空");
			}
			//取数据
			QueryAdapter qa = new QueryAdapter();
			DataTable dt = qa.executeQuery(sql);
			if(dt==null||dt.length()==0){
				throw new Exception("SQL查询数据为空");
			}
			
			//取模板
			f = new File(Constants.ROOT_PATH + File.separator + "template"  + File.separator + template);
			if(!f.exists()){
				throw new Exception("模板文件不存在");
			}
			
			int idx = template.indexOf(".");
			if(idx>-1){
				fileName = fileName + template.substring(idx);
			}
			
			fInput = new FileInputStream(f);
			Workbook wb = null;
			try{
				wb = new XSSFWorkbook(fInput);
			}catch(Exception e){
			}
			
			if(wb==null){
				try{
					wb = new HSSFWorkbook(fInput);
				}catch(Exception e){
				}
			}
			fInput.close();
			
			if(wb==null){
				throw new Exception("模板文件格式不正确");
			}
			
			Sheet sheet =  wb.getSheetAt(0);
			Row row = null;
			Cell cell = null;
			String cellstring = null;
			//初始化参数
			int lastRow = sheet.getLastRowNum();
			int lastCol = 0;
			
			for(int i=0;i<lastRow;i++){
				row = sheet.getRow(i);
				if(row!=null){
					lastCol = row.getLastCellNum();
					for(int j=0;j<lastCol;j++){
						cell = row.getCell(j);
						cellstring = getCellValue(cell);
						if(StringUtils.isNotBlank(cellstring)){
							cell.setCellValue(FtpDataSendManager.getStrExpress(cellstring, params));
						}
					}
				}
			}
			
			int dataRow = dataStartRow-1;
			int dataCol = dataStartCol-1;
			int size = 0;
			int rowidx = 0;
			int colidx = 0;
			
			List<DataRow> drList = dt.getRowList();
			for(DataRow dr:drList){
				size = dr.getRowHashMap().size();
				row = sheet.getRow(dataRow+rowidx);
				if(row==null){
					row = sheet.createRow(dataRow+rowidx);
				}
				colidx = 0;
				for(int i=0;i<size;i++){
					cell = row.getCell(dataCol+colidx);
					if(cell==null){
						cell = row.createCell(dataCol+colidx);
					}
					cell.setCellValue(dr.getString(i));
					colidx++;
				}
				rowidx++;
			}
			
			//输出临时文件
			String outdir = Constants.ROOT_PATH + File.separator + "export"  + File.separator + UUIDGenerator.getUUIDoffSpace();
			out = new File(outdir);
			if(!out.exists()){
				out.mkdirs();
			}
			outFile = new File(outdir + File.separator + fileName);
			if(outFile==null||!outFile.exists()){
				outFile.createNewFile();
			}
			
			output = new FileOutputStream(outFile);
			wb.write(output);
			
			output.flush();
			output.close();
			
			FtpClient ftp = new FtpClient(ftpIp,StringUtils.isBlank(ftpPort)?21:Integer.parseInt(ftpPort),userName,passWord,false,"GBK");
			ftp.ftpLogin();
			boolean flag = ftp.uploadFile(outFile, ftpPath);
			if(!flag){
				throw new Exception("上传文件失败");
			}
			
			
		}catch(Exception e)
		{
			throw e;
			
		}finally {
			if(fInput!=null){
				try{
					fInput.close();
				}catch(Exception e){
				}
			}
			if(output!=null){
				try{
					output.close();
				}catch(Exception e){
				}
			}
			if(outFile!=null&&outFile.exists()){
				try{
					outFile.delete();
				}catch(Exception e){
				}
			}
			if(out!=null&&out.exists()){
				try{
					out.delete();
				}catch(Exception e){
				};
			}
		}
		
		
	}
	
	private static String getCellValue(Cell cell){
		 if(cell==null){
			 return "";
		 }
		 try{
			 switch (cell.getCellType()) {  
	         case Cell.CELL_TYPE_NUMERIC: // 数字  
	             return String.valueOf((int)cell.getNumericCellValue());  
	         case Cell.CELL_TYPE_STRING: // 字符串  
	             return cell.getStringCellValue();
	         case Cell.CELL_TYPE_BOOLEAN: // Boolean  
	             return cell.getBooleanCellValue()+"";  
	         case Cell.CELL_TYPE_FORMULA: // 公式  
	             return cell.getCellFormula()+"";  
	         case Cell.CELL_TYPE_BLANK: // 空值  
		         return ""; 
	         case Cell.CELL_TYPE_ERROR: // 故障  
	             return "";
	         default:  
	             return "";  
	         }  
		 }catch(Exception e){
			 return "";
		 }
	}
	
	private static String getStrExpress(String str, Map<String,String> params){
		Pattern p = Pattern.compile("^(?:.*)((#\\w+#))(?:.*)$");
		Matcher m = p.matcher(str);
		if(m.matches()){
			int len = m.groupCount();
			if(len>1){
				for(int i=1;i<len;i++){
					String s = m.group(i);
					str = str.replace(s, params.get(s.subSequence(1, s.length()-1)));
				}
			}
		}
		return str;
	}
	
	
	public static void main(String []args){
		System.out.println("adaxlsx".substring("adaxlsx".indexOf(".")));
	}
}
