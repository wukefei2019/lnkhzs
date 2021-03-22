package com.ultrapower.eoms.common.plugin.excelutils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelWriter {
	/** Excel 文件要存放的位置 */
	private String excelFilePath;
	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	private String excelName;
	private String[] title;
	private int rowIndex = 0;
	private static SXSSFWorkbook workBook;

	public ExcelWriter() {
		createWorkBook();
	}

	public ExcelWriter(Map<String, List<List<String[]>>> sheet) {
		workBook = new SXSSFWorkbook(5000);
		rowIndex = 0;
		for (Iterator<Entry<String, List<List<String[]>>>> sheetIter = sheet.entrySet().iterator(); sheetIter.hasNext();) {
			Entry<String, List<List<String[]>>> sheetEntry = (Entry<String, List<List<String[]>>>) sheetIter.next();
			workBook.createSheet((String) sheetEntry.getKey());
			List<List<String[]>> cells = (List<List<String[]>>) sheetEntry.getValue();
			addDatas(cells);
		}
	}

	private void createWorkBook() {
		// 创建新的Excel 工作簿
		workBook = new SXSSFWorkbook();
		// 在Excel工作簿中建一工作表
		workBook.createSheet();
		rowIndex = 0;
		if (title != null && title.length > 0)
			addTitle();
	}
	
	public void setSheetName(String sheetName){
		workBook.setSheetName(0, sheetName);
	}
	
	private void addTitle() {
		Row row = workBook.getSheetAt(0).createRow(rowIndex++);
		for (int i = 0; i < title.length; i++) {
			Cell  cell = row.createCell(i);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(title[i]);
		}
	}

	public void addTitle(String[] title) {
		this.title = title;
		addTitle();
	}
	
	public void addDatas(List<List<String[]>> cellDatas) {
		for (List<String[]> cellData : cellDatas) {
			addData(cellData);
		}
	}
	
	public void addData(List<String[]> cellData) {
		Row row = workBook.getSheetAt(0).createRow(rowIndex++);
		for (Iterator<String[]> cellIter = cellData.iterator(); cellIter.hasNext();) {
			String[] cellArr = (String[]) cellIter.next();
			int index = Integer.parseInt(cellArr[0]);
			String value = cellArr[1];
			// 在索引的位置创建行
			// 在索引的位置创建单元格
			Cell cell = row.createCell(index);
			if(value.indexOf("@&@$")<0) {
				// 定义单元格为字符串类型
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(value);
			} else {
				value = value.replace("@&@$", "");
				if ("".equals(value)) {
					// 定义单元格为字符串类型
					cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					// 在单元格中输入一些内容
					cell.setCellValue("");
				} else {
					// 定义单元格为字符串类型
					cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
					// 在单元格中输入一些内容
					cell.setCellValue(Double.parseDouble(value));
				}
			}
		}
	}

	public String createExcel() {
		File f = new File(excelFilePath);
		if (!f.exists() || !f.isDirectory())
			f.mkdirs();
		try {
			// 新建一输出文件流
			FileOutputStream fOut = new FileOutputStream(excelFilePath + excelName);
			// 把相应的Excel 工作簿存盘
			workBook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
			return excelFilePath + excelName;
		} catch (Exception e) {
			System.out.println("Excel生成失败 : " + e);
			return null;
		}
	}
}
