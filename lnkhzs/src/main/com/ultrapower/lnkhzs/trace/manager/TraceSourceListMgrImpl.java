package com.ultrapower.lnkhzs.trace.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.util.ExcelStyleUtil;
import com.ultrapower.lnkhzs.trace.model.TraceSourceFlow;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceFlowService;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceListService;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [溯源问题清单_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TraceSourceListMgrImpl implements ITraceSourceListService {

	private ITraceSourceFlowService traceSourceFlowService;
	
    private ITraceSourceListService traceSourceListService;
	
    private IDao<TraceSourceList> traceSourceListDao;
    
    private IDao<TraceSourceFlow> traceSourceFlowDao;
    
    public ITraceSourceListService getTraceSourceListService() {
		return traceSourceListService;
	}

	public void setTraceSourceListService(ITraceSourceListService traceSourceListService) {
		this.traceSourceListService = traceSourceListService;
	}

	public ITraceSourceFlowService getTraceSourceFlowService() {
		return traceSourceFlowService;
	}

	public void setTraceSourceFlowService(ITraceSourceFlowService traceSourceFlowService) {
		this.traceSourceFlowService = traceSourceFlowService;
	}

	public void setTraceSourceListDao(IDao<TraceSourceList> traceSourceListDao) {
        this.traceSourceListDao = traceSourceListDao;
    }

	public void setTraceSourceFlowDao(IDao<TraceSourceFlow> traceSourceFlowDao) {
		this.traceSourceFlowDao = traceSourceFlowDao;
	}

	@Override
	public List<TraceSourceList> getSelections(String data) {
		List<TraceSourceList> list = traceSourceListDao.getListByIds(data);
		return list;
	}

	@Override
	public void saveOrUpdate(TraceSourceList st) {
		traceSourceListDao.saveOrUpdate(st);
	}

	@Override
    public Workbook checkVali(File xls) {
        Workbook wb=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        int count = 0;
        int col = 0;
            try {
                wb =WorkbookFactory.create(new FileInputStream(xls));
                Sheet sheet=wb.getSheetAt(0);
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                	count = i+1;
                    Row row =sheet.getRow(i);
                    if(row == null){
                        break;
                    }
                    sdf.parse(readCell(row, 2));col=2;
                    if(StringUtils.isNotBlank(readCell(row, 21)))sdf.parse(readCell(row, 21));col=21;
                    if(StringUtils.isNotBlank(readCell(row, 22)))sdf.parse(readCell(row, 22));col=22;
                }
                
            } catch (ExcelTemplateException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExcelTemplateException("导入时间格式或模板不正确！正确时间格式为：yyyyMMdd,例：20200101,第"+count+"行,第"+col+"列数据不正确");
            }
        return wb;
    }

    @Override 
    public int importXls(Workbook wb, UserSession user)throws Exception {
        Sheet sheet=wb.getSheetAt(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){
                break;
            }
            try {
            	String respDept = ExcelUtils.getString(row, 7);
            	if(StringUtils.isNotBlank(respDept)){
            		System.out.println(ExcelUtils.getString(row, 4));
            		String[] s = respDept.split("、");
            		for(int j=0;j<s.length;j++){
            			TraceSourceList traceSourceList = new TraceSourceList();
            			traceSourceList.setPid(UUIDGenerator.getUUIDoffSpace());
            			traceSourceList.setSerialno(String.valueOf(i));
            			traceSourceList.setTraceSourceTime(sdf.format(sdf.parse(readCell(row, 2))));
            			traceSourceList.setTraceSourceType(ExcelUtils.getString(row, 3));
            			traceSourceList.setQuestionSubcategory(ExcelUtils.getString(row, 4));
            			traceSourceList.setConcreteProblems(ExcelUtils.getString(row, 5));
            			traceSourceList.setRespDept(s[j]);
            			traceSourceList.setAuxiliaryDepartment(ExcelUtils.getString(row, 6));
            			traceSourceList.setProblem(ExcelUtils.getString(row, 8));
            			traceSourceList.setRectificationGoal(ExcelUtils.getString(row, 9));
            			traceSourceList.setTriggerComplaintsNum(ExcelUtils.getString(row, 15));
            			traceSourceList.setTriggerWantouRatio(ExcelUtils.getString(row, 16));
            			traceSourceList.setTriggerHandlingVolume(ExcelUtils.getString(row, 17));
            			traceSourceList.setAcceptanceComplaints(ExcelUtils.getString(row, 18));
            			traceSourceList.setAcceptanceComplaintsRate(ExcelUtils.getString(row, 19));
            			traceSourceList.setAcceptanceTime(readCell(row, 21));
            			traceSourceList.setLookBackTime(readCell(row, 22));
            			traceSourceList.setId(ExcelUtils.getString(row, 23));
            			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
            			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
            			traceSourceList.setKey(s[j]);
            			traceSourceList.setCreateTime(sdf.format(new Date()));
            			traceSourceListDao.save(traceSourceList);
            		}
            	}else{
            			TraceSourceList traceSourceList = new TraceSourceList();
            			traceSourceList.setPid(UUIDGenerator.getUUIDoffSpace());
            			traceSourceList.setSerialno(String.valueOf(i));
            			traceSourceList.setTraceSourceTime(readCell(row, 2));
            			traceSourceList.setTraceSourceType(ExcelUtils.getString(row, 3));
            			traceSourceList.setQuestionSubcategory(ExcelUtils.getString(row, 4));
            			traceSourceList.setConcreteProblems(ExcelUtils.getString(row, 5));
            			traceSourceList.setAuxiliaryDepartment(ExcelUtils.getString(row, 6));
            			traceSourceList.setRespDept(ExcelUtils.getString(row, 7));
            			traceSourceList.setProblem(ExcelUtils.getString(row, 8));
            			traceSourceList.setRectificationGoal(ExcelUtils.getString(row, 9));
            			traceSourceList.setTriggerComplaintsNum(ExcelUtils.getString(row, 15));
            			traceSourceList.setTriggerWantouRatio(ExcelUtils.getString(row, 16));
            			traceSourceList.setTriggerHandlingVolume(ExcelUtils.getString(row, 17));
            			traceSourceList.setAcceptanceComplaints(ExcelUtils.getString(row, 18));
            			traceSourceList.setAcceptanceComplaintsRate(ExcelUtils.getString(row, 19));
            			traceSourceList.setAcceptanceTime(readCell(row, 21));
            			traceSourceList.setLookBackTime(readCell(row, 22));
            			traceSourceList.setId(ExcelUtils.getString(row, 23));
            			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
            			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
            			traceSourceList.setKey(ExcelUtils.getString(row, 6));
            			traceSourceList.setCreateTime(sdf.format(new Date()));
            			traceSourceListDao.save(traceSourceList);

            	}
            	
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new ExcelImportException(e.getMessage(),e.getCause());
            }
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }

	@Override
	public void remove(String pid, UserSession user) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		TraceSourceList traceSourceList = traceSourceListDao.get(pid);
		traceSourceList.setCreateTime(sdf.format(new Date()));
		traceSourceList.setLoginname(user.getLoginName());
		traceSourceListDao.merge(traceSourceList);
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ZL_TRACE_SOURCE_LIST_bak select * from ZL_TRACE_SOURCE_LIST where pid = ?");
		q.executeUpdate(sql.toString(),pid);
		traceSourceListDao.remove(traceSourceList);
	}

	@Override
	public void removeAll() {
		traceSourceListDao.executeUpdate("delete from TraceSourceList where status is null");
	}
	
	@Override
	public void resetFlow(List<TraceSourceList> list) {
		for(TraceSourceList st : list){
			traceSourceFlowDao.executeUpdate("delete from TraceSourceFlow where sourcepid = ?",st.getPid());
			st.setCode(null);
			st.setAcceptanceComplaints(null);
			st.setAcceptanceComplaintsRate(null);
			st.setAcceptanceTime(null);
			st.setAttachment(null);
			st.setAuxiliaryDepartment(null);
			st.setCompletionStatus(null);
			st.setProblem(null);
			st.setRectificationGoal(null);
			st.setFullname(null);
			st.setLoginname(null);
			st.setDealtime(null);
			st.setTriggerComplaintsNum(null);
			st.setTriggerHandlingVolume(null);
			st.setTriggerWantouRatio(null);
			st.setLookBackTime(null);
			st.setNetwork3Id(null);
			st.setNetwork3Name(null);
			st.setStatus(null);
			traceSourceListDao.merge(st);
		}
	}
	
	
	@Override
	public TraceSourceList getByID(String pid) {
		TraceSourceList traceSourceList = traceSourceListDao.get(pid);
		return traceSourceList;
	}

	@Override
	public List<TraceSourceList> getSelectAll() {
		List<TraceSourceList> list = traceSourceListDao.find("from TraceSourceList where status is null");
		return list;
	}

	@Override
	public List<TraceSourceList> getByCode(String code) {
		List<TraceSourceList> list = traceSourceListDao.find("from TraceSourceList where status = '处理中' and code = ?",code);
		return list;
	}
	
	public void exportExcel(HttpServletResponse response, HashMap<String, String> params) {
		String[] lineArr = new String[] {"环节进展","溯源时间","溯源分类","问题小类","具体问题点","影响范围",
				"主责部门","问题原因","整改目标","解决进度-未完成具体原因","未完成详细描述","落实责任单位",
				"落实责任部门","落实责任人","触发投诉量","触发万投比","触发办理量","验收全量投诉量","验证万投比",
				"完成情况","完成时间","回头看发起时间","ID","市场三级网格ID","市场三级网格名称","备注"};
		String excelName = "溯源问题清单";// 获取导出的EXCEL名称
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		
		// 设置EXCEL单元格样式
		CellStyle cellTitleStyle = ExcelStyleUtil.setStyle(wb, "title", font, "center", "LIGHTGREEN");
		cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		List<CellStyle> cellDataStyleList = new ArrayList<CellStyle>();// 创建单元格样式,以列为基准
		CellStyle cellDataStyle = null;
		for (int i = 0; i < lineArr.length; i++) {
//			String setAlign = ExcelStyleUtil.defaultAlign;// 先取默认对齐方式
			cellDataStyle = ExcelStyleUtil.setStyle(wb, "cell", font, "center", "WHITE");
			cellDataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellDataStyleList.add(cellDataStyle);
		}
		// 整理标题行数据
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "溯源问题清单";
		sheet = wb.createSheet(sheetName);
		row = sheet.createRow(0);// 创建行

		// 创建标题行
		for (int i = 0; i < lineArr.length; i++) {
			// 创建单元格
			cell = row.createCell(i);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(lineArr[i]);
		}
		
		RQuery rq = new RQuery("SQL_ZL_TRACE_SOURCE_LIST_EXPORT.query", params);
		DataTable dt = rq.getDataTable();
		List<DataRow> rowList = dt.getRowList();
		for (int i = 0; i < rowList.size(); i++) {
			DataRow dr = rowList.get(i);
			row = sheet.createRow(i + 1);// 创建行
			int j=0;
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LINK_PROGRESS"));//环节进展
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle); 
			cell.setCellValue(dr.getString("TRACE_SOURCE_TIME"));//溯源时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRACE_SOURCE_TYPE"));//溯源分类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("QUESTION_SUBCATEGORY"));//问题小类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("CONCRETE_PROBLEMS"));//具体问题点
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("AUXILIARY_DEPARTMENT"));//影响范围
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESP_DEPT"));//主责部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("PROBLEM"));//问题原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RECTIFICATION_GOAL"));//整改目标
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_FINISHED_REASON"));//解决进度-未完成具体原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_COMPLETED_DESCRIPTION"));//未完成详细描述
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_UNIT"));//落实责任单位
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_DEPARTMENT"));//落实责任部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_PERSON"));//落实责任人
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_COMPLAINTS_NUM"));//触发投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_WANTOU_RATIO"));//触发万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_HANDLING_VOLUME"));//触发办理量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS"));//验收全量投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS_RATE"));//验证万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("COMPLETION_STATUS"));//完成情况
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_TIME"));//完成时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LOOK_BACK_TIME"));//回头看发起时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ID"));//ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_ID"));//市场三级网格ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_NAME"));//市场三级网格名称
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("REMARK"));//备注

		}
		sheet.setColumnWidth(0, 5000);
		for (int i = 1; i < lineArr.length+1; i++) {
			sheet.autoSizeColumn(i, true);
		}
		// 导出EXCEL
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			excelName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void exportExcelBase(HttpServletResponse response, HashMap<String, String> params) {
		String[] lineArr = new String[] {"环节进展","溯源时间","溯源分类","问题小类","具体问题点","影响范围",
				"主责部门","问题原因","整改目标","解决进度-未完成具体原因","未完成详细描述","落实责任单位",
				"落实责任部门","落实责任人","触发投诉量","触发万投比","触发办理量","验收全量投诉量","验证万投比",
				"完成情况","完成时间","回头看发起时间","ID","市场三级网格ID","市场三级网格名称","备注"};
		String excelName = "溯源问题清单";// 获取导出的EXCEL名称
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		
		// 设置EXCEL单元格样式
		CellStyle cellTitleStyle = ExcelStyleUtil.setStyle(wb, "title", font, "center", "LIGHTGREEN");
		cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		List<CellStyle> cellDataStyleList = new ArrayList<CellStyle>();// 创建单元格样式,以列为基准
		CellStyle cellDataStyle = null;
		for (int i = 0; i < lineArr.length; i++) {
//			String setAlign = ExcelStyleUtil.defaultAlign;// 先取默认对齐方式
			cellDataStyle = ExcelStyleUtil.setStyle(wb, "cell", font, "center", "WHITE");
			cellDataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellDataStyleList.add(cellDataStyle);
		}
		// 整理标题行数据
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "溯源问题清单";
		sheet = wb.createSheet(sheetName);
		row = sheet.createRow(0);// 创建行

		// 创建标题行
		for (int i = 0; i < lineArr.length; i++) {
			// 创建单元格
			cell = row.createCell(i);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(lineArr[i]);
		}
		
		RQuery rq = new RQuery("SQL_ZL_TRACE_SOURCE_LIST.query", params);
		DataTable dt = rq.getDataTable();
		List<DataRow> rowList = dt.getRowList();
		for (int i = 0; i < rowList.size(); i++) {
			DataRow dr = rowList.get(i);
			row = sheet.createRow(i + 1);// 创建行
			int j=0;
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LINK_PROGRESS"));//环节进展
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle); 
			cell.setCellValue(dr.getString("TRACE_SOURCE_TIME"));//溯源时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRACE_SOURCE_TYPE"));//溯源分类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("QUESTION_SUBCATEGORY"));//问题小类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("CONCRETE_PROBLEMS"));//具体问题点
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("AUXILIARY_DEPARTMENT"));//影响范围
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESP_DEPT"));//主责部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("PROBLEM"));//问题原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RECTIFICATION_GOAL"));//整改目标
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_FINISHED_REASON"));//解决进度-未完成具体原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_COMPLETED_DESCRIPTION"));//未完成详细描述
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_UNIT"));//落实责任单位
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_DEPARTMENT"));//落实责任部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_PERSON"));//落实责任人
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_COMPLAINTS_NUM"));//触发投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_WANTOU_RATIO"));//触发万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_HANDLING_VOLUME"));//触发办理量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS"));//验收全量投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS_RATE"));//验证万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("COMPLETION_STATUS"));//完成情况
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_TIME"));//完成时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LOOK_BACK_TIME"));//回头看发起时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ID"));//ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_ID"));//市场三级网格ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_NAME"));//市场三级网格名称
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("REMARK"));//备注

		}
		sheet.setColumnWidth(0, 5000);
		for (int i = 1; i < lineArr.length+1; i++) {
			sheet.autoSizeColumn(i, true);
		}
		// 导出EXCEL
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			excelName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	@Override
	public void removeData(String date) {
		int executeUpdate = traceSourceListDao.executeUpdate("delete from TraceSourceList where CREATE_TIME = ?", date);
	}

	@Override
	public void exportExcelCheck(HttpServletResponse response, HashMap<String, String> params) {
		// TODO Auto-generated method stub
		String[] lineArr = new String[] {"pid","环节进展","溯源时间","溯源分类","问题小类","具体问题点","影响范围",
				"主责部门","问题原因","整改目标","解决进度-未完成具体原因","未完成详细描述","落实责任单位",
				"落实责任部门","落实责任人","触发投诉量","触发万投比","触发办理量","验收全量投诉量","验证万投比",
				"完成情况","完成时间","回头看发起时间","ID","市场三级网格ID","市场三级网格名称","备注"};
		String excelName = "溯源问题清单";// 获取导出的EXCEL名称
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		
		// 设置EXCEL单元格样式
		CellStyle cellTitleStyle = ExcelStyleUtil.setStyle(wb, "title", font, "center", "LIGHTGREEN");
		cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		List<CellStyle> cellDataStyleList = new ArrayList<CellStyle>();// 创建单元格样式,以列为基准
		CellStyle cellDataStyle = null;
		for (int i = 0; i < lineArr.length; i++) {
//			String setAlign = ExcelStyleUtil.defaultAlign;// 先取默认对齐方式
			cellDataStyle = ExcelStyleUtil.setStyle(wb, "cell", font, "center", "WHITE");
			cellDataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellDataStyleList.add(cellDataStyle);
		}
		// 整理标题行数据
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "溯源问题清单";
		sheet = wb.createSheet(sheetName);
		row = sheet.createRow(0);// 创建行

		// 创建标题行
		for (int i = 0; i < lineArr.length; i++) {
			// 创建单元格
			cell = row.createCell(i);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(lineArr[i]);
		}
		
		RQuery rq = new RQuery("SQL_ZL_TRACE_SOURCE_LIST_EXPORT2.query", params);
		DataTable dt = rq.getDataTable();
		List<DataRow> rowList = dt.getRowList();
		sheet.setColumnHidden(0, true);
		for (int i = 0; i < rowList.size(); i++) {
			DataRow dr = rowList.get(i);
			row = sheet.createRow(i + 1);// 创建行
			int j=0;
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("pid"));//pid
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LINK_PROGRESS"));//环节进展
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle); 
			cell.setCellValue(dr.getString("TRACE_SOURCE_TIME"));//溯源时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRACE_SOURCE_TYPE"));//溯源分类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("QUESTION_SUBCATEGORY"));//问题小类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("CONCRETE_PROBLEMS"));//具体问题点
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("AUXILIARY_DEPARTMENT"));//影响范围
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESP_DEPT"));//主责部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("PROBLEM"));//问题原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RECTIFICATION_GOAL"));//整改目标
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_FINISHED_REASON"));//解决进度-未完成具体原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_COMPLETED_DESCRIPTION"));//未完成详细描述
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_UNIT"));//落实责任单位
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_DEPARTMENT"));//落实责任部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_PERSON"));//落实责任人
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_COMPLAINTS_NUM"));//触发投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_WANTOU_RATIO"));//触发万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_HANDLING_VOLUME"));//触发办理量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS"));//验收全量投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS_RATE"));//验证万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("COMPLETION_STATUS"));//完成情况
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_TIME"));//完成时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LOOK_BACK_TIME"));//回头看发起时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ID"));//ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_ID"));//市场三级网格ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_NAME"));//市场三级网格名称
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("REMARK"));//备注

		}
		sheet.setColumnWidth(0, 5000);
		for (int i = 1; i < lineArr.length+1; i++) {
			sheet.autoSizeColumn(i, true);
		}
		// 导出EXCEL
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			excelName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	@Override 
    public int importXlsCheck(Workbook wb, UserSession user)throws Exception {
        Sheet sheet=wb.getSheetAt(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){  
                break;
            }
            String pid = ExcelUtils.getString(row, 0);
            try {
	    			TraceSourceList traceSourceList = traceSourceListDao.get(pid);
	    			List<TraceSourceFlow> list = traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ? order by createtime Desc", pid);
	    			TraceSourceFlow traceSourceFlow = new TraceSourceFlow();
	    			if(list.size()>0) {
						traceSourceFlow = list.get(0);
					}
	    			if(!"待验证".equals(traceSourceList.getStatus()))continue;
	    			if(!user.getLoginName().equals(traceSourceFlow.getNextloginname()))continue;
	    			traceSourceList.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceFlow.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceList.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setNotFinishedReason(ExcelUtils.getString(row, 10));
	    			traceSourceFlow.setNotCompletedDescription(ExcelUtils.getString(row, 11));
	    			traceSourceFlow.setResponsibleUnit(ExcelUtils.getString(row, 12));
	    			traceSourceFlow.setResponsibleDepartment(ExcelUtils.getString(row, 13));
	    			traceSourceFlow.setResponsiblePerson(ExcelUtils.getString(row, 14));
	    			traceSourceList.setTriggerComplaintsNum(ExcelUtils.getString(row, 15));
	    			traceSourceList.setTriggerWantouRatio(ExcelUtils.getString(row, 16));
	    			traceSourceList.setTriggerHandlingVolume(ExcelUtils.getString(row, 17));
	    			traceSourceList.setAcceptanceComplaints(ExcelUtils.getString(row, 18));
	    			traceSourceList.setAcceptanceComplaintsRate(ExcelUtils.getString(row, 19));
	    			traceSourceList.setCompletionStatus("已完成");
	    			traceSourceList.setAcceptanceTime(readCell(row, 21));
	    			traceSourceList.setLookBackTime(readCell(row, 22));
	    			traceSourceList.setId(ExcelUtils.getString(row, 23));
	    			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
	    			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
	    			traceSourceFlow.setRemark(ExcelUtils.getString(row, 26));
//	    			if("已完成".equals(ExcelUtils.getString(row, 20))){
	    				traceSourceFlow.setNextdepname(null);
	    				traceSourceFlow.setNextfullname(null);
	    				traceSourceFlow.setNextloginname(null);
	    				traceSourceFlow.setLinkProgress("已完成");
	    				traceSourceFlow.setStatus("已完成");
	    				traceSourceList.setStatus("完成");
	    				traceSourceFlow.setPid(UUIDGenerator.getUUIDoffSpace());
	    				traceSourceFlow.setFullname(user.getFullName());
	    				traceSourceFlow.setLoginname(user.getLoginName());
	    				traceSourceFlow.setCreatetime(sdf.format(new Date()));
	    				traceSourceFlowDao.save(traceSourceFlow);
//	    			}
/*	    			else{
	    				UserSession upper = traceSourceFlowService.getRectification(pid);
	    				traceSourceFlow.setNextdepname(upper.getDepFullName());
	    				traceSourceFlow.setNextfullname(upper.getFullName());
	    				traceSourceFlow.setNextloginname(upper.getLoginName());
	    				traceSourceFlow.setLinkProgress("整改中");
	    				traceSourceFlow.setStatus("整改中");
	    				traceSourceFlow.setPid(UUIDGenerator.getUUIDoffSpace());
	    				traceSourceFlow.setFullname(user.getFullName());
	    				traceSourceFlow.setLoginname(user.getLoginName());
	    				traceSourceFlowDao.save(traceSourceFlow);
	    			}*/
	    			traceSourceListDao.merge(traceSourceList);
            	}
            catch (Exception e) {
                e.printStackTrace();
                throw new ExcelImportException(e.getMessage(),e.getCause());
            }
            
        }
        return sheet.getPhysicalNumberOfRows();
        
    }
	
	@Override 
    public int importXlsRectify(Workbook wb, UserSession user) throws Exception {
        Sheet sheet=wb.getSheetAt(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> map = new HashMap<String,String>();
        Set<String> userSet = new HashSet<String>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){  
                break;
            }
            String pid = ExcelUtils.getString(row, 0);
            try {
	    			TraceSourceList traceSourceList = traceSourceListDao.get(pid);
	    			List<TraceSourceFlow> list = traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ? order by createtime Desc", pid);
	    			TraceSourceFlow traceSourceFlow = new TraceSourceFlow();
	    			if(list.size()>0) {
						traceSourceFlow = list.get(0);
					}
	    			if(!"待验证".equals(traceSourceList.getStatus()))continue;
	    			if(!user.getLoginName().equals(traceSourceFlow.getNextloginname()))continue;
					String value = map.get(ExcelUtils.getString(row, 7))==null?pid:map.get(ExcelUtils.getString(row, 7))+","+pid;
					map.put(ExcelUtils.getString(row, 7), value);
	    			traceSourceList.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceFlow.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceList.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setNotFinishedReason(ExcelUtils.getString(row, 10));
	    			traceSourceFlow.setNotCompletedDescription(ExcelUtils.getString(row, 11));
	    			traceSourceFlow.setResponsibleUnit(ExcelUtils.getString(row, 12));
	    			traceSourceFlow.setResponsibleDepartment(ExcelUtils.getString(row, 13));
	    			traceSourceFlow.setResponsiblePerson(ExcelUtils.getString(row, 14));
	    			traceSourceList.setTriggerComplaintsNum(ExcelUtils.getString(row, 15));
	    			traceSourceList.setTriggerWantouRatio(ExcelUtils.getString(row, 16));
	    			traceSourceList.setTriggerHandlingVolume(ExcelUtils.getString(row, 17));
	    			traceSourceList.setAcceptanceComplaints(ExcelUtils.getString(row, 18));
	    			traceSourceList.setAcceptanceComplaintsRate(ExcelUtils.getString(row, 19));
	    			traceSourceList.setCompletionStatus("整改中");
	    			traceSourceList.setAcceptanceTime(readCell(row, 21));
	    			traceSourceList.setLookBackTime(readCell(row, 22));
	    			traceSourceList.setId(ExcelUtils.getString(row, 23));
	    			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
	    			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
	    			traceSourceFlow.setRemark(ExcelUtils.getString(row, 26));
	    			traceSourceList.setKey(ExcelUtils.getString(row, 7));
    				UserSession upper = traceSourceFlowService.getRectification(pid);
    				traceSourceFlow.setNextdepname(upper.getDepFullName());
    				traceSourceFlow.setNextfullname(upper.getFullName());
    				traceSourceFlow.setNextloginname(upper.getLoginName());
    				userSet.add(upper.getLoginName());
    				traceSourceFlow.setLinkProgress("整改中");
    				traceSourceFlow.setStatus("整改中");
    				traceSourceList.setStatus("整改中");
    				traceSourceFlow.setPid(UUIDGenerator.getUUIDoffSpace());
    				traceSourceFlow.setFullname(user.getFullName());
    				traceSourceFlow.setLoginname(user.getLoginName());
    				traceSourceFlow.setCreatetime(sdf.format(new Date()));
    				traceSourceFlowDao.save(traceSourceFlow);
	    			traceSourceListDao.merge(traceSourceList);
            	}
            catch (Exception e) {
                e.printStackTrace();
                throw new ExcelImportException(e.getMessage(),e.getCause());
            }
            
        }
        Set<String> keySet = map.keySet();
		for(String str : keySet){//TODU
//			UserSession upper = traceSourceFlowService.getRectification(map.get(str));
			List<TraceSourceList> sList = traceSourceListService.getSelections(map.get(str));
			String message = traceSourceFlowService.getMessage(sList)+"遗留待解决溯源问题清单";
			String param = "'"+map.get(str).replace(",", "','")+"'";
			String sql = "SELECT TRACE_SOURCE_TIME,TRACE_SOURCE_TYPE,QUESTION_SUBCATEGORY,CONCRETE_PROBLEMS,RESP_DEPT,AUXILIARY_DEPARTMENT,"
					+ "TRIGGER_COMPLAINTS_NUM,TRIGGER_WANTOU_RATIO FROM ZL_TRACE_SOURCE_LIST where pid in ("+param+")";
			traceSourceFlowService.DraftWorkFlowAuto(sql,str,message,user);
//			traceSourceFlowService.sendSms(depAdmin.getLoginName(), message+",请于2个工作日内处理完毕。");
		}
		for(String str : userSet){
			String message = "您好，您有新的问题溯源待办，请您及时登录服务质量管理平台处理，请于2个工作日内处理完毕。";
			traceSourceFlowService.sendSms(str, message);
		}
        return sheet.getPhysicalNumberOfRows();
        
    }
	
	@Override
	public void exportExcelDone(HttpServletResponse response, HashMap<String, String> params) {
		// TODO Auto-generated method stub
		String[] lineArr = new String[] {"pid","环节进展","溯源时间","溯源分类","问题小类","具体问题点","影响范围",
				"主责部门","问题原因","整改目标","解决进度-未完成具体原因","未完成详细描述","落实责任单位",
				"落实责任部门","落实责任人","触发投诉量","触发万投比","触发办理量","验收全量投诉量","验证万投比",
				"完成情况","完成时间","回头看发起时间","ID","市场三级网格ID","市场三级网格名称","备注"};
		String excelName = "溯源问题清单";// 获取导出的EXCEL名称
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont font = wb.createFont();
		
		// 设置EXCEL单元格样式
		CellStyle cellTitleStyle = ExcelStyleUtil.setStyle(wb, "title", font, "center", "LIGHTGREEN");
		cellTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		List<CellStyle> cellDataStyleList = new ArrayList<CellStyle>();// 创建单元格样式,以列为基准
		CellStyle cellDataStyle = null;
		for (int i = 0; i < lineArr.length; i++) {
//			String setAlign = ExcelStyleUtil.defaultAlign;// 先取默认对齐方式
			cellDataStyle = ExcelStyleUtil.setStyle(wb, "cell", font, "center", "WHITE");
			cellDataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellDataStyleList.add(cellDataStyle);
		}
		// 整理标题行数据
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		String sheetName = "溯源问题清单";
		sheet = wb.createSheet(sheetName);
		row = sheet.createRow(0);// 创建行

		// 创建标题行
		for (int i = 0; i < lineArr.length; i++) {
			// 创建单元格
			cell = row.createCell(i);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(lineArr[i]);
		}
		
		RQuery rq = new RQuery("SQL_ZL_TRACE_SOURCE_LIST_EXPORT3.query", params);
		DataTable dt = rq.getDataTable();
		List<DataRow> rowList = dt.getRowList();
		sheet.setColumnHidden(0, true);
		for (int i = 0; i < rowList.size(); i++) {
			DataRow dr = rowList.get(i);
			row = sheet.createRow(i + 1);// 创建行
			int j=0;
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("pid"));//pid
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LINK_PROGRESS"));//环节进展
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle); 
			cell.setCellValue(dr.getString("TRACE_SOURCE_TIME"));//溯源时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRACE_SOURCE_TYPE"));//溯源分类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("QUESTION_SUBCATEGORY"));//问题小类
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("CONCRETE_PROBLEMS"));//具体问题点
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("AUXILIARY_DEPARTMENT"));//影响范围
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESP_DEPT"));//主责部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("PROBLEM"));//问题原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RECTIFICATION_GOAL"));//整改目标
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_FINISHED_REASON"));//解决进度-未完成具体原因
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NOT_COMPLETED_DESCRIPTION"));//未完成详细描述
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_UNIT"));//落实责任单位
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_DEPARTMENT"));//落实责任部门
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("RESPONSIBLE_PERSON"));//落实责任人
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_COMPLAINTS_NUM"));//触发投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_WANTOU_RATIO"));//触发万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("TRIGGER_HANDLING_VOLUME"));//触发办理量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS"));//验收全量投诉量
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_COMPLAINTS_RATE"));//验证万投比
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("COMPLETION_STATUS"));//完成情况
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ACCEPTANCE_TIME"));//完成时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("LOOK_BACK_TIME"));//回头看发起时间
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("ID"));//ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_ID"));//市场三级网格ID
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("NETWORK3_NAME"));//市场三级网格名称
			cell = row.createCell(j++);
			cell.setCellStyle(cellTitleStyle);
			cell.setCellValue(dr.getString("REMARK"));//备注
		}
		sheet.setColumnWidth(0, 5000);
		for (int i = 1; i < lineArr.length+1; i++) {
			sheet.autoSizeColumn(i, true);
		}
		// 导出EXCEL
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/x-msdownload");
			excelName = new String(excelName.getBytes("gb2312"), "iso8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + excelName + ".xls");
			wb.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	@Override 
    public int importXlsDone(Workbook wb, UserSession user)throws Exception {
        Sheet sheet=wb.getSheetAt(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Set<String> userSet = new HashSet<String>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row =sheet.getRow(i);
            if(row == null){  
                break;
            }
            String pid = ExcelUtils.getString(row, 0);
            try {
            		
	    			TraceSourceList traceSourceList = traceSourceListDao.get(pid);
	    			if("待验证".equals(traceSourceList.getStatus()))continue;
	    			List<TraceSourceFlow> list = traceSourceFlowDao.find("from TraceSourceFlow where sourcepid = ? order by createtime Desc", pid);
	    			TraceSourceFlow traceSourceFlow = new TraceSourceFlow();
	    			if(list.size()>0) {
						traceSourceFlow = list.get(0);
					}
	    			if(!user.getLoginName().equals(traceSourceFlow.getNextloginname()))continue;
	    			UserSession doBacker = getDoBacker(traceSourceList.getPid(),user.getLoginName());
					if(isNotAdmin(doBacker)){
		    			traceSourceFlow.setLinkProgress("处理中");
		    			traceSourceFlow.setStatus("处理中");
		    			traceSourceList.setStatus("处理中");
		    			traceSourceList.setCompletionStatus("处理中");
					}else{
		    			traceSourceFlow.setLinkProgress("待验证");
		    			traceSourceFlow.setStatus("待验证");
		    			traceSourceList.setStatus("待验证");
		    			traceSourceList.setCompletionStatus("待验证");
					}
					userSet.add(doBacker.getLoginName());
	    			traceSourceList.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceFlow.setProblem(ExcelUtils.getString(row, 8));
	    			traceSourceList.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setRectificationGoal(ExcelUtils.getString(row, 9));
	    			traceSourceFlow.setNotFinishedReason(ExcelUtils.getString(row, 10));
	    			traceSourceFlow.setNotCompletedDescription(ExcelUtils.getString(row, 11));
	    			traceSourceFlow.setResponsibleUnit(ExcelUtils.getString(row, 12));
	    			traceSourceFlow.setResponsibleDepartment(ExcelUtils.getString(row, 13));
	    			traceSourceFlow.setResponsiblePerson(ExcelUtils.getString(row, 14));
	    			traceSourceList.setTriggerComplaintsNum(ExcelUtils.getString(row, 15));
	    			traceSourceList.setTriggerWantouRatio(ExcelUtils.getString(row, 16));
	    			traceSourceList.setTriggerHandlingVolume(ExcelUtils.getString(row, 17));
	    			traceSourceList.setAcceptanceComplaints(ExcelUtils.getString(row, 18));
	    			traceSourceList.setAcceptanceComplaintsRate(ExcelUtils.getString(row, 19));
//	    			traceSourceList.setCompletionStatus(ExcelUtils.getString(row, 20));
	    			traceSourceList.setAcceptanceTime(readCell(row, 21));
	    			traceSourceList.setLookBackTime(readCell(row, 22));
	    			traceSourceList.setId(ExcelUtils.getString(row, 23));
	    			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
	    			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
	    			traceSourceFlow.setRemark(ExcelUtils.getString(row, 26));
	    			traceSourceList.setLookBackTime(ExcelUtils.getString(row, 22));
	    			traceSourceList.setId(ExcelUtils.getString(row, 23));
	    			traceSourceList.setNetwork3Id(ExcelUtils.getString(row, 24));
	    			traceSourceList.setNetwork3Name(ExcelUtils.getString(row, 25));
	    			traceSourceFlow.setRemark(ExcelUtils.getString(row, 26));
	    			traceSourceList.setKey(ExcelUtils.getString(row, 7));
	    			traceSourceListDao.merge(traceSourceList);
	    			traceSourceFlow.setPid(UUIDGenerator.getUUIDoffSpace());
	    			traceSourceFlow.setCreatetime(sdf.format(new Date()));
	    			traceSourceFlow.setFullname(user.getFullName());
	    			traceSourceFlow.setLoginname(user.getLoginName());
	    			traceSourceFlow.setNextfullname(doBacker.getFullName());
	    			traceSourceFlow.setNextloginname(doBacker.getLoginName());
	    			traceSourceFlow.setNextdepname(doBacker.getDepFullName());
	    			traceSourceFlowDao.save(traceSourceFlow);
            	
            	}
            catch (Exception e) {
                e.printStackTrace();
                throw new ExcelImportException(e.getMessage(),e.getCause());
            }
            
        }
		for(String str : userSet){
			String message = "您好，您有新的问题溯源待办，请您及时登录服务质量管理平台处理，请于2个工作日内处理完毕。";
			traceSourceFlowService.sendSms(str, message);
		}
        return sheet.getPhysicalNumberOfRows();
        
    }
	
	
	public UserSession getDepAdmin(String deplevel2name) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPNAME FROM ZL_TRACE_SOURCE_DEPADMIN WHERE deplevel2name ='"+deplevel2name+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
	
	
	private UserSession getDoBacker(String data, String loginname) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" select * from  (select t.loginname,t.fullname from zl_trace_source_flow t where t.sourcepid ='"+data+"' and nextLoginname = '"+loginname+"' order by t.createtime ) where  rownum =1" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
    	}
    	return user;
	}
	
    private boolean isNotAdmin(UserSession nextuser) {
		// TODO Auto-generated method stub
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPNAME FROM ZL_TRACE_SOURCE_ADMIN WHERE LOGINNAME ='"+nextuser.getLoginName()+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	if(datatable.getRowList().size()>0)return false;
    	return true;
	}
    
    public String readCell(Row row,int bit) {
    	Cell cell = row.getCell(bit);
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
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
                    objCellValue = sdf.format(cell.getDateCellValue());
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
}
