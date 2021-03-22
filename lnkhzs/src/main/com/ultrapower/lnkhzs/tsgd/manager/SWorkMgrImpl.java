package com.ultrapower.lnkhzs.tsgd.manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.RandomUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestion;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;
import com.ultrapower.lnkhzs.tsgd.model.SWork;
import com.ultrapower.lnkhzs.tsgd.model.WFresend;
import com.ultrapower.lnkhzs.tsgd.service.SWorkService;
import com.ultrapower.lnkhzs.tsgd.web.IaAuditSendAction;
import com.ultrapower.lnkhzs.tsgd.web.IaSworkSendAction;
import com.ultrapower.omcs.utils.DateUtils;

public class SWorkMgrImpl implements SWorkService {
	
	private IDao<SWork> sWorkDao;
	
	private IDao<DepInfo> depManagerDao;

	public IDao<SWork> getsWorkDao() {
		return sWorkDao;
	}

	public void setsWorkDao(IDao<SWork> sWorkDao) {
		this.sWorkDao = sWorkDao;
	}
	
	public IDao<DepInfo> getDepManagerDao() {
		return depManagerDao;
	}

	public void setDepManagerDao(IDao<DepInfo> depManagerDao) {
		this.depManagerDao = depManagerDao;
	}

	@Override
	public boolean addSend(SWork sWork) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/ServiceSupervisor/"+RandomUtils.getRandomN(6);
			sWork.setCreatetime(sdf.format(new Date()));
			List<DepInfo> depList = depManagerDao.find("from DepInfo where pid = "+sWork.getMaindepartid());
			if(depList.size()>0){
				String str=depList.get(0).getDepfullname();
				if(str.indexOf("中国移动通信集团辽宁有限公司")!=-1){
					str=str.replaceAll("中国移动通信集团辽宁有限公司", "辽宁移动").replaceAll(",", "/");
				}else{
					str="辽宁移动/"+str.replaceAll(",", "/");
				}
				sWork.setMaindepartfull(str);
			}
			if (StringUtils.isBlank(sWork.getId())) {
				//保存
				sWork.setId(UUIDGenerator.getUUIDoffSpace());				
				sWorkDao.save(sWork);
			} else {
				//更新
				sWorkDao.saveOrUpdate(sWork);
			}
			if(sWork.getStatus().equals("已发起")){
				System.out.println("发起流程");
				sWork.setAppendix_address(path);
				Map<String,Object> resultMap=IaSworkSendAction.draftWorkFlow(sWork);
				try {
					System.out.println(resultMap.get("result").toString());
					sWork.setResultstr(resultMap.get("result").toString());
					String result=resultMap.get("result").toString();
					if(resultMap.get("result")!=null&&(!result.contains("ERROR"))){
						sWork.setStatus("已发起");
						sWorkDao.saveOrUpdate(sWork);
					}else{
						sWork.setStatus("已保存");
						sWorkDao.saveOrUpdate(sWork);
						return false;
					}
				} catch (Exception e) {
					sWork.setStatus("已保存");
					sWorkDao.saveOrUpdate(sWork);
					return false;
				}
				
			}
			
		return true;
	}

	@Override
	public SWork getDetailById(SWork sWork) {
		return sWorkDao.get(sWork.getId());
	}

	@Override
	public void delProcess(String id) {
		sWorkDao.removeById(id);
		
	}


	@Override
	public boolean addInsert(SWork sWork) {		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		sWork.setId(UUIDGenerator.getUUIDoffSpace());
		sWork.setCreatetime(sdf.format(new Date()));
		if (StringUtils.isBlank(sWork.getId())) {
			//保存
			
			sWorkDao.save(sWork);
		} else {
			//更新			
			sWorkDao.saveOrUpdate(sWork);
		}
	return true;
	}

	@Override
	public SWork addSendReturnDemo(SWork sWork) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String path = "/"+TimeUtils.getCurrentDate("yyyyMM")+"/ServiceSupervisor/"+RandomUtils.getRandomN(6);
		sWork.setCreatetime(sdf.format(new Date()));
		List<DepInfo> depList = depManagerDao.find("from DepInfo where pid = "+sWork.getMaindepartid());
		if(depList.size()>0){
			String str=depList.get(0).getDepfullname();
			if(str.indexOf("中国移动通信集团辽宁有限公司")!=-1){
				str=str.replaceAll("中国移动通信集团辽宁有限公司", "辽宁移动").replaceAll(",", "/");
			}else{
				str="辽宁移动/"+str.replaceAll(",", "/");
			}
			sWork.setMaindepartfull(str);
		}
		if (StringUtils.isBlank(sWork.getId())) {
			//保存
			sWork.setId(UUIDGenerator.getUUIDoffSpace());				
			sWorkDao.save(sWork);
		} else {
			//更新
			sWorkDao.saveOrUpdate(sWork);
		}
		if(sWork.getStatus().equals("已发起")){
			System.out.println("发起流程");
			//sWork.setAppendix_address(path);
			Map<String,Object> resultMap=IaSworkSendAction.draftWorkFlow(sWork);
			try {
				System.out.println(resultMap.get("result").toString());
				sWork.setResultstr(resultMap.get("result").toString());
				String result=resultMap.get("result").toString();
				if(resultMap.get("result")!=null&&(!result.contains("ERROR"))){
					sWork.setStatus("已发起");
					sWorkDao.saveOrUpdate(sWork);
				}else{
					sWork.setStatus("已保存");
					sWorkDao.saveOrUpdate(sWork);
					return null;
				}
			} catch (Exception e) {
				sWork.setStatus("已保存");
				sWorkDao.saveOrUpdate(sWork);
				return null;
			}
			
		}
		return sWork;
	}

	@Override
	public boolean addFilesNew(String fileid, String uploadFilePath) {
		
		try {
			String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
			//生产
			String ftpurl="10.64.93.15";
			String username="gwptxtnas";
			String userpwd="Gw!2013#";
					
			//测试
			/*String ftpurl="10.64.93.11";
			String username="gwptxtnas";
			String userpwd="Gw!2013#";*/
			
			
			
			//查询文件
			String sql = "select * from BS_T_SM_ATTACHMENT where RELATIONCODE = '"+fileid+"'";
			QueryAdapter qa = new QueryAdapter();
			DataTable datatable = qa.executeQuery(sql.toString());
			List<DataRow> rows = datatable.getRowList();
			Iterator<DataRow> it = rows.iterator();
			String filename = "";
			String filetype="";
			String filepath="";
			
			FtpClient ftp = new FtpClient(ftpurl, 21 , username, userpwd, false, "GBK");
			boolean ftpLogin = ftp.ftpLogin();
			while (it.hasNext()) {
			   DataRow dr = (DataRow) it.next();
			   System.out.println("已保存的文件名======"+dr.getString("NAME"));//名称
			   //System.out.println(dr.getString("TYPE"));//类型
			   
			   System.out.println("保存路径======"+dr.getString("PATH"));//类型
			   
			   filename=dr.getString("NAME");
			   filepath=dr.getString("PATH");
			   String path=rootPath+"/"+filepath+"/"+filename;
			   File file=new File(path);
			   
			   System.out.println("----------------地址："+uploadFilePath);
				//获取前台传来的文件名
				//File file = new File(files.getParent(), files.getName().substring(0, files.getName().indexOf("."))+filefix);
				System.out.println(file);
				
				String ftpPath=uploadFilePath;
				
				boolean flag = ftp.uploadFile(file, ftpPath);	   
			}
			ftp.ftpLogOut();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	

}
