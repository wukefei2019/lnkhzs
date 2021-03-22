package com.ultrapower.lnkhzs.trace.manager;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.lnkhzs.khzs.model.KhzsTDepadmin;
import com.ultrapower.lnkhzs.trace.model.TraceManager;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceListService;
import com.ultrapower.lnkhzs.trace.service.ITraceSourceManagerService;
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
public class TraceSourceManagerMgrImpl implements ITraceSourceManagerService {

    private IDao<TraceManager> traceManagertDao;
    
    private IDao<DepInfo> depManagerDao;

	public IDao<TraceManager> getTraceManagertDao() {
		return traceManagertDao;
	}

	public void setTraceManagertDao(IDao<TraceManager> traceManagertDao) {
		this.traceManagertDao = traceManagertDao;
		
	}
		
	public IDao<DepInfo> getDepManagerDao() {
		return depManagerDao;
	}

	public void setDepManagerDao(IDao<DepInfo> depManagerDao) {
		this.depManagerDao = depManagerDao;
	}

	@Override
	public boolean isExist(TraceManager traceManager) {
		return traceManagertDao.find("from TraceManager where loginname = ?", traceManager.getLoginname()).size()>=1?false:true;
	}

	@Override
	public void save(TraceManager traceManager) {
		DepInfo depInfo = depManagerDao.get(traceManager.getDepevel2id());
		traceManager.setPid(UUIDGenerator.getUUIDoffSpace());
		traceManager.setDeplevel2name(depInfo.getDepname());
		traceManagertDao.save(traceManager);
		
	}

	@Override
	public void deleteManager(String depevel2id) {
		TraceManager traceManager = traceManagertDao.findUnique("from TraceManager where pid = ?", depevel2id);
		traceManagertDao.remove(traceManager);
		//traceManagertDao.removeById(depevel2id);
	}

   
}
