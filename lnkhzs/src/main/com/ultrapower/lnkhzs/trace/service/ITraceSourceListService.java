package com.ultrapower.lnkhzs.trace.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;

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
public interface ITraceSourceListService {

	List<TraceSourceList> getSelections(String data);

	void saveOrUpdate(TraceSourceList st);

	Workbook checkVali(File xls);

	int importXls(Workbook wb, UserSession user) throws Exception;

	void remove(String pid, UserSession user) throws Exception;

	TraceSourceList getByID(String pid);

	List<TraceSourceList> getSelectAll();

	List<TraceSourceList> getByCode(String code);

	void exportExcel(HttpServletResponse response, HashMap<String, String> params);

	void removeData(String date);

	void removeAll();

	void exportExcelCheck(HttpServletResponse response, HashMap<String, String> params);

	int importXlsCheck(Workbook wb, UserSession user) throws Exception;

	void exportExcelDone(HttpServletResponse response, HashMap<String, String> params);

	int importXlsDone(Workbook wb, UserSession user) throws Exception;

	void resetFlow(List<TraceSourceList> list);

	void exportExcelBase(HttpServletResponse response, HashMap<String, String> params);

	int importXlsRectify(Workbook wb, UserSession user) throws Exception;

}
