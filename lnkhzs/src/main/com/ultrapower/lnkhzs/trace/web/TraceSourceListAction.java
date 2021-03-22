package com.ultrapower.lnkhzs.trace.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;

import com.alibaba.fastjson.JSONArray;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceFlowService;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceListService;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [溯源问题清单_WEB]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TraceSourceListAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	public String data;
	
	private String fileName;

	private File xls;

	public String code;

	public String pid;

	private TraceSourceList traceSourceList;

	private ITraceSourceListService traceSourceListService;

	private ITraceSourceFlowService traceSourceFlowService;

	private HashMap<String, String> params = new HashMap<String, String>();

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public TraceSourceList getTraceSourceList() {
		return traceSourceList;
	}

	public void setTraceSourceList(TraceSourceList traceSourceList) {
		this.traceSourceList = traceSourceList;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void sendWorksheet() {
		JSONArray json = new JSONArray();
		List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
		Map<String, String> map = new HashMap<String, String>();
		for (TraceSourceList t : list) {
			String value = map.get(t.getKey()) == null ? t.getPid() : map.get(t.getKey()) + "," + t.getPid();
			map.put(t.getKey(), value);
		}
		Set<String> keySet = map.keySet();
		String code = traceSourceFlowService.getIndex();
		for (String str : keySet) {
			List<TraceSourceList> sList = traceSourceListService.getSelections(map.get(str));
			for (TraceSourceList st : sList) {
				UserSession depAdmin = traceSourceFlowService.getDepAdmin(st.getRespDept());
				st.setLoginname(depAdmin.getLoginName());
				st.setFullname(depAdmin.getFullName());
				st.setStatus("处理中");
				st.setCode(code);
				traceSourceListService.saveOrUpdate(st);
			}
		}
		renderJson("success");
	}

	public void importExcel() {
		UserSession user = super.getUserSession();
		int i = 0;
		if (super.getFieldErrors().containsKey("xls")) {
		}
		try {
			// 校验文件是否正确
			Workbook wb = traceSourceListService.checkVali(xls);
			if (wb != null) {
				// 导入数据
				try {
					// traceSourceListService.removeData(date);
					i = traceSourceListService.importXls(wb, user);
					this.renderText("已成功导入" + i + "条记录");
				} catch (ExcelImportException e) {
					// flag = e.getMessage();
					e.printStackTrace();
					this.renderText(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				}
			} else {
				this.renderText("Excel格式验证失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.renderText(e.getMessage());
		}
	}

	public void remove() throws Exception {
		UserSession user = super.getUserSession();
		JSONArray json = new JSONArray();
		List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
		for (TraceSourceList t : list) {
			traceSourceListService.remove(t.getPid(), user);
		}
		renderJson("success");
	}

	public void check() {
		if (StringUtils.isNotBlank(traceSourceList.getCode())) {
			List<TraceSourceList> list = traceSourceListService.getByCode(traceSourceList.getCode());
			for (TraceSourceList t : list) {
				t.setCompletionStatus(traceSourceList.getCompletionStatus());
				t.setAcceptanceTime(traceSourceList.getAcceptanceTime());
				t.setAcceptanceComplaints(traceSourceList.getAcceptanceComplaints());
				t.setAcceptanceComplaintsRate(traceSourceList.getAcceptanceComplaintsRate());
				traceSourceListService.saveOrUpdate(t);
			}

		} else {
			TraceSourceList t = traceSourceListService.getByID(traceSourceList.getPid());
			t.setCompletionStatus(traceSourceList.getCompletionStatus());
			t.setAcceptanceTime(traceSourceList.getAcceptanceTime());
			t.setAcceptanceComplaints(traceSourceList.getAcceptanceComplaints());
			t.setAcceptanceComplaintsRate(traceSourceList.getAcceptanceComplaintsRate());
			traceSourceListService.saveOrUpdate(t);
		}
		renderJson("success");
	}

	public void exportExcelBase()
			throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		traceSourceListService.exportExcelBase(response, params);
	}

	public void exportExcel()
			throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		if (StringUtils.isNotBlank(params.get("code"))) {
			params.put("code", params.get("code"));
		} else {
			params.put("pid", params.get("pid"));
		}
		traceSourceListService.exportExcel(response, params);
	}

	public void exportExcelCheck()
			throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		if (StringUtils.isNotBlank(data)) {
			params.put("pid", data);
		}
		traceSourceListService.exportExcelCheck(response, params);
	}

	public void exportExcelDone()
			throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		if (StringUtils.isNotBlank(data)) {
			params.put("pid", data);
		}
		traceSourceListService.exportExcelDone(response, params);
	}

	public void importExcelCheck() {
		UserSession user = super.getUserSession();
		int i = 0;
		if (super.getFieldErrors().containsKey("xls")) {
		}
		try {
			// 校验文件是否正确
			Workbook wb = traceSourceListService.checkVali(xls);
			if (wb != null) {
				// 导入数据
				try {
					i = traceSourceListService.importXlsCheck(wb, user);
					this.renderText("已成功导入" + i + "条记录");
				} catch (ExcelImportException e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				}
			} else {
				this.renderText("Excel格式验证失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.renderText(e.getMessage());
		}
	}

	public void importExcelRectify() {
		UserSession user = super.getUserSession();
		int i = 0;
		if (super.getFieldErrors().containsKey("xls")) {
		}
		try {
			// 校验文件是否正确
			Workbook wb = traceSourceListService.checkVali(xls);
			if (wb != null) {
				// 导入数据
				try {
					i = traceSourceListService.importXlsRectify(wb, user);
					this.renderText("已成功导入" + i + "条记录");
				} catch (ExcelImportException e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				}
			} else {
				this.renderText("Excel格式验证失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.renderText(e.getMessage());
		}
	}

	public void importExcelDone() {
		UserSession user = super.getUserSession();
		int i = 0;
		if (super.getFieldErrors().containsKey("xls")) {
		}
		try {
			// 校验文件是否正确
			Workbook wb = traceSourceListService.checkVali(xls);
			if (wb != null) {
				// 导入数据
				try {
					i = traceSourceListService.importXlsDone(wb, user);
					this.renderText("已成功导入" + i + "条记录");
				} catch (ExcelImportException e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					this.renderText(e.getMessage());
				}
			} else {
				this.renderText("Excel格式验证失败！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.renderText(e.getMessage());
		}
	}

	public void checkImport() {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String extString = fileName.substring(fileName.lastIndexOf("."));
			if(".xls".equals(extString)){
				HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xls));
				ExcelToHtmlConverter ethc = new ExcelToHtmlConverter(
						DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
				ethc.setOutputColumnHeaders(false);
				ethc.setOutputRowNumbers(false);
				ethc.processWorkbook(wb);
				
				Document htmlDocument = ethc.getDocument();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				DOMSource domSource = new DOMSource(htmlDocument);
				StreamResult streamResult = new StreamResult(out);
				
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer serializer = tf.newTransformer();
				serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				serializer.setOutputProperty(OutputKeys.INDENT, "yes");
				serializer.setOutputProperty(OutputKeys.METHOD, "html");
				serializer.transform(domSource, streamResult);
				out.close();
				
				String htmlStr = new String(out.toByteArray(), "UTF-8");
				
				htmlStr = htmlStr.replace("<h2>Sheet1</h2>", "").replace("<h2>Sheet2</h2>", "")
						.replace("<h2>Sheet3</h2>", "").replace("<h2>Sheet4</h2>", "").replace("<h2>Sheet5</h2>", "");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print(htmlStr);
				pw.flush();
				pw.close();
				// this.renderText(htmlStr);
				// renderJson(htmlStr);
			}else if(".xlsx".equals(extString)){

				XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xls));
				for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
					Sheet sheet = workbook.getSheetAt(numSheet);
					if (sheet == null) {
						continue;
					}
					String html="";
//					html+="=======================" + sheet.getSheetName() + "=========================<br><br>";
					int firstRowIndex = sheet.getFirstRowNum(); 
					int lastRowIndex = sheet.getLastRowNum();
					html+="<table border='1' align='left'>"; 
					Row firstRow = sheet.getRow(firstRowIndex); 
					for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) { 
						Cell cell = firstRow.getCell(i); 
						String cellValue = getCellValue(cell, true); 
						html+="<th>" + cellValue + "</th>";
					 }
					 //行 
					for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) { 
						Row currentRow = sheet.getRow(rowIndex); 
						html+="<tr>"; 
						if(currentRow!=null){
							int firstColumnIndex = currentRow.getFirstCellNum(); 
							int lastColumnIndex = currentRow.getLastCellNum(); 
							//列 
							for (int columnIndex = firstColumnIndex; columnIndex <= lastColumnIndex; columnIndex++) { 
								Cell currentCell = currentRow.getCell(columnIndex); 
								String currentCellValue = getCellValue(currentCell, true); 
								html+="<td>"+currentCellValue + "</td>"; 
								} 
						}else{
								html+=" "; 
						} html+="</tr>"; 
						} 
					html+="</table>";

					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					DOMSource domSource = new DOMSource();
					StreamResult streamResult = new StreamResult(outStream);

					TransformerFactory tf = TransformerFactory.newInstance();
					Transformer serializer = tf.newTransformer();
					serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
					serializer.setOutputProperty(OutputKeys.INDENT, "yes");
					serializer.setOutputProperty(OutputKeys.METHOD, "html");
					serializer.transform(domSource, streamResult);
					outStream.close();
//					String htmlStr = new String(outStream.toByteArray(), "UTF-8");
	//
//					htmlStr = htmlStr.replace("<h2>Sheet1</h2>", "").replace("<h2>Sheet2</h2>", "")
//							.replace("<h2>Sheet3</h2>", "").replace("<h2>Sheet4</h2>", "").replace("<h2>Sheet5</h2>", "");
					response.setContentType("text/html;charset=utf-8");
					PrintWriter pw = response.getWriter();
					pw.print(html);
					pw.flush();
					pw.close();
				}

            }
		} catch (Exception e) {
			e.printStackTrace();
			this.renderText("Excel格式验证失败！");
		}
	}

	public String addCheck() {
		traceSourceList = traceSourceListService.getByID(pid);
		return "success";
	}

	public void deleteAll() {
		traceSourceListService.removeAll();
		renderJson("success");
	}

	public void resetFlow() {
		JSONArray json = new JSONArray();
		List<TraceSourceList> list = json.parseArray(data, TraceSourceList.class);
		traceSourceListService.resetFlow(list);
		renderJson("success");
	}
	
	private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
