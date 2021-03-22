package com.ultrapower.omcs.common.utils;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.commons.math.NumberUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;

public class ExcelUtils {
    
    public static String[] readCell2Value(Row row) {
        String[] values = new String[row.getLastCellNum()];
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            values[i] = readCell(cell);
        }
        return values;
    }
    
    public static String readCell(Row row, int idx) {
        return ExcelUtils.readCell(row.getCell(idx));
    }

    public static Double readCell2Double(Row row, int idx) {
        String str = ExcelUtils.readCell(row.getCell(idx));
        return NumberUtils.nvl(str);
    }

    public static Row getRow(Sheet sheet,int index){
        if(sheet == null){
            return null;
        }
        Row row = sheet.getRow(index);
        if(row == null){
            row = sheet.createRow(index);
        }
        return row;
    }
    public static Cell getCell(Row row,int index){
        if(row == null){
            return null;
        }
        Cell cell = row.getCell(index);
        if(cell == null){
            cell = row.createCell(index);
        }
        return cell;
    }
    
    public static Cell getCell(Row row,int index,CellType cellType){
        if(row == null){
            return null;
        }
        Cell cell = getCell(row, index);
        if (cell != null)
            cell.setCellType(cellType);
        return cell;
    }
    
    
    public static Double getDouble(Row row,int index){
        Cell cell = getCell(row, index);
        return getDouble(cell);
    }
    /**
     * [从cell中读取Double类型数据]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @param cell
     * @return
     */
    public static Double getDouble(Cell cell){
        try {
            return NumberUtils.nvl(readCell(cell), null);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Integer getInteger(Row row,int index){
        Cell cell = getCell(row, index);
        return getInteger(cell);
    }
    
    public static Integer getInteger(Cell cell){
        try {
            return Integer.valueOf(NumberUtils.nvl(readCell(cell), null).toString());
        } catch (Exception e) {
            return null;
        }
    }
    
    public static String getString(Row row,int index){
        Cell cell = getCell(row, index);
        return getString(cell);
    }
    
    public static String getString(Cell cell){
        try {
            return StringUtils.nvl(readCell(cell), null);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 
     * [获取excel cell 值]
     * @author:吴克非
     * @return
     */
    public static String readCell(Cell cell) {
        DecimalFormat df = new DecimalFormat("#.########");// 默认最多保留8位小数
        String objCellValue = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // Numeric
                // 日期类型
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    objCellValue = TimeUtils.formatLongDateToString(cell.getDateCellValue());
                }
                // 数值类型
                else {
                    objCellValue = df.format(cell.getNumericCellValue());
                }
                break;
            case STRING: // String
                objCellValue = cell.getStringCellValue();
                break;
            case FORMULA: // Formula
                objCellValue = cell.getCellFormula();
                break;
            case BLANK: // Blank
                objCellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // boolean
                objCellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR: // Error
                break;
            default:
                break;
        }
        return objCellValue;
    }
    
    
    public static String readCell(Cell cell,int bit) {
    	StringBuffer sb = new StringBuffer("#.");
    	for(int i=0;i<bit;i++) {
    		sb.append("#");
    	}
        DecimalFormat df = new DecimalFormat(sb.toString());// 默认最多保留8位小数
        String objCellValue = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // Numeric
                // 日期类型
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    objCellValue = TimeUtils.formatLongDateToString(cell.getDateCellValue());
                }
                // 数值类型
                else {
                    objCellValue = df.format(cell.getNumericCellValue());
                }
                break;
            case STRING: // String
                objCellValue = cell.getStringCellValue();
                break;
            case FORMULA: // Formula
                objCellValue = cell.getCellFormula();
                break;
            case BLANK: // Blank
                objCellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // boolean
                objCellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR: // Error
                break;
            default:
                break;
        }
        return objCellValue;
    }
    
    public static String readCellwithFormula(Cell cell) {
        DecimalFormat df = new DecimalFormat("#.########");// 默认最多保留8位小数
        String objCellValue = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // Numeric
                // 日期类型
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    objCellValue = TimeUtils.formatLongDateToString(cell.getDateCellValue());
                }
                // 数值类型
                else {
                    objCellValue = df.format(cell.getNumericCellValue());
                }
                break;
            case STRING: // String
                objCellValue = cell.getStringCellValue();
                break;
            case FORMULA: // Formula
            	if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    objCellValue = TimeUtils.formatLongDateToString(cell.getDateCellValue());
                }
                // 数值类型
                else {
                    objCellValue = df.format(cell.getNumericCellValue());
                }
                break;
            case BLANK: // Blank
                objCellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // boolean
                objCellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR: // Error
                break;
            default:
                break;
        }
        return objCellValue;
    }
    
    public static void updateFormulaSheet(Workbook wb, Sheet sheet) {
        FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
        int cnt = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < cnt; i++) {
            updateFormula(eval, sheet.getRow(i));
        }
        
    }

    public static void updateFormula(Workbook wb, Sheet s, int row) {
        FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
        updateFormula(eval, s, row);
    }

    public static void updateFormula(Workbook wb, Sheet s, int row, int col) {
        FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
        Row r = s.getRow(row);
        Cell c = r.getCell(col);
        updateFormula(eval, c);
    }

    public static void updateFormula(FormulaEvaluator eval, Sheet s, int row) {
        Row r = s.getRow(row);
        updateFormula(eval, r);
    }

    public static void updateFormula(FormulaEvaluator eval, Row r) {
        Cell c = null;
        if(r != null) {
        	for (int i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                c = r.getCell(i);
                if (c != null) {
                	updateFormula(eval, c);
                }
                
            }
        }  
    }

    public static void updateFormula(FormulaEvaluator eval, Cell c) {
        if (c.getCellTypeEnum() == CellType.FORMULA) {
            eval.evaluateFormulaCellEnum(c);
        }
    }
    
    public static void updateFormula(Workbook wb, Cell c) {
        FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
        if (c.getCellTypeEnum() == CellType.FORMULA) {
            eval.evaluateFormulaCellEnum(c);
        }
    }

    /** 
     * 复制单元格 
     *  
     * @param srcCell 
     * @param distCell 
     * @param copyValueFlag 
     *            true则连同cell的内容一起复制 
     */
    public static void copyCell(Workbook wb, Cell srcCell, Cell distCell, boolean copyValueFlag) {
        CellStyle newstyle = wb.createCellStyle();
        copyCellStyle(srcCell.getCellStyle(), newstyle);
//        distCell.setEncoding(srcCell.getEncoding());
        // 样式
        distCell.setCellStyle(newstyle);
        // 评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellTypeEnum();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == CellType.NUMERIC) {
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == CellType.STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == CellType.BLANK) {
                // nothing21
            } else if (srcCellType == CellType.BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == CellType.ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == CellType.FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else { // nothing29
            }
        }
    }

    /** 
     * 复制一个单元格样式到目的单元格样式 
     * @param origStyle 
     * @param destStyle 
     */
    public static void copyCellStyle(CellStyle origStyle, CellStyle destStyle) {
        destStyle.setAlignment(origStyle.getAlignmentEnum());
        // 边框和边框颜色
        destStyle.setBorderBottom(origStyle.getBorderBottomEnum());
        destStyle.setBorderLeft(origStyle.getBorderLeftEnum());
        destStyle.setBorderRight(origStyle.getBorderRightEnum());
        destStyle.setBorderTop(origStyle.getBorderTopEnum());
        destStyle.setTopBorderColor(origStyle.getTopBorderColor());
        destStyle.setBottomBorderColor(origStyle.getBottomBorderColor());
        destStyle.setRightBorderColor(origStyle.getRightBorderColor());
        destStyle.setLeftBorderColor(origStyle.getLeftBorderColor());

        // 背景和前景
        destStyle.setFillBackgroundColor(origStyle.getFillBackgroundColor());
        destStyle.setFillForegroundColor(origStyle.getFillForegroundColor());

        destStyle.setDataFormat(origStyle.getDataFormat());
        destStyle.setFillPattern(origStyle.getFillPatternEnum());
        // destStyle.setFont(origStyle.getFont(null));
        destStyle.setHidden(origStyle.getHidden());
        destStyle.setIndention(origStyle.getIndention());// 首行缩进
        destStyle.setLocked(origStyle.getLocked());
        destStyle.setRotation(origStyle.getRotation());// 旋转
        destStyle.setVerticalAlignment(origStyle.getVerticalAlignmentEnum());
        destStyle.setWrapText(origStyle.getWrapText());

    }

    /** 
     * Sheet复制 
     * @param origSheet 
     * @param destSheet 
     * @param copyValueFlag 
     */
    public static void copySheet(Workbook wb, Sheet origSheet, Sheet destSheet, boolean copyValueFlag) {
        // 合并区域处理
        mergerRegion(origSheet, destSheet);
        for (Iterator<Row> rowIt = origSheet.rowIterator(); rowIt.hasNext();) {
            Row tmpRow = (Row) rowIt.next();
            Row newRow = destSheet.createRow(tmpRow.getRowNum());
            // 行复制
            copyRow(wb, tmpRow, newRow, copyValueFlag);
        }
    }

    /** 
     * 行复制功能 
     * @param origRow 
     * @param destRow 
     */
    public static void copyRow(Workbook wb, Row origRow, Row destRow, boolean copyValueFlag) {
        for (Iterator<Cell> cellIt = origRow.cellIterator(); cellIt.hasNext();) {
            Cell tmpCell = (Cell) cellIt.next();
            Cell newCell = destRow.createCell(tmpCell.getRowIndex());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }
    }

    /** 
    * 复制原有sheet的合并单元格到新创建的sheet 
    *  
    * @param sheetCreat 新创建sheet 
    * @param sheet      原有的sheet 
    */
    public static void mergerRegion(Sheet origSheet, Sheet destSheet) {
        int sheetMergerCount = origSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress mergedRegionAt = origSheet.getMergedRegion(i);
            destSheet.addMergedRegion(mergedRegionAt);
        }
    }

 

}
