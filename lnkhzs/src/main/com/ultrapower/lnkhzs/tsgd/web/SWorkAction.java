package com.ultrapower.lnkhzs.tsgd.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.bmc.thirdparty.org.springframework.web.multipart.MultipartException;
import com.bmc.thirdparty.org.springframework.web.multipart.MultipartFile;
import com.bmc.thirdparty.org.springframework.web.multipart.MultipartHttpServletRequest;
import com.bmc.thirdparty.org.springframework.web.multipart.MultipartResolver;
import com.bmc.thirdparty.org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.bmc.thirdparty.org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.lnkhzs.base.web.BaseAction;
import com.ultrapower.lnkhzs.survey.service.IKhzsSurverManagerService;
import com.ultrapower.lnkhzs.tsgd.model.DbManager;
import com.ultrapower.lnkhzs.tsgd.model.SWork;

import com.ultrapower.lnkhzs.tsgd.service.IDbManagerService;
import com.ultrapower.lnkhzs.tsgd.service.SWorkService;


public class SWorkAction extends BaseAction{

	private static final long serialVersionUID = -1L;
	
	private SWork sWork;
	
	private SWorkService sWorkService;
	
	private DepManagerService depManagerService;
	
	private UserManagerService userManagerService;
	
	private IKhzsSurverManagerService khzsSurverManagerService;
	
	private IDbManagerService dbManagerService;
	
	private File files;
	
	private String path;
	
	private String filefix;
	

	
	public SWork getsWork() {
		return sWork;
	}

	public void setsWork(SWork sWork) {
		this.sWork = sWork;
	}

	public SWorkService getsWorkService() {
		return sWorkService;
	}

	public void setsWorkService(SWorkService sWorkService) {
		this.sWorkService = sWorkService;
	}

	public DepManagerService getDepManagerService() {
		return depManagerService;
	}

	public void setDepManagerService(DepManagerService depManagerService) {
		this.depManagerService = depManagerService;
	}

	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	public IKhzsSurverManagerService getKhzsSurverManagerService() {
		return khzsSurverManagerService;
	}

	public void setKhzsSurverManagerService(IKhzsSurverManagerService khzsSurverManagerService) {
		this.khzsSurverManagerService = khzsSurverManagerService;
	}

	public IDbManagerService getDbManagerService() {
		return dbManagerService;
	}

	public void setDbManagerService(IDbManagerService dbManagerService) {
		this.dbManagerService = dbManagerService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilefix() {
		return filefix;
	}

	public void setFilefix(String filefix) {
		this.filefix = filefix;
	}

	//保存
	public void addSend(){
		try {
			/*boolean b=sWorkService.addSend(sWork);
			if(b)
				renderText(SUCCESS);
			else
				renderText(ERROR);*/
			SWork SWork=sWorkService.addSendReturnDemo(sWork);
			//sWork.setAppendix_address("/a/a");
			renderJson(SWork);
			//renderJson(sWork);
		} catch (Exception e) {
			e.printStackTrace();
			//renderText(ERROR);
			renderJson(null);
		}
		//System.out.println(sWork);
		//renderJson(sWork1);
	}
	
	//上传文件（可多个）
	public void addFiles(){
		
		//生产
		String ftpurl="10.64.93.15";
		String username="gwptxtnas";
		String userpwd="Gw!2013#";
		
		//测试
		/*String ftpurl="10.64.93.11";
		String username="gwptxtnas";
		String userpwd="Gw!2013#";*/
		
		System.out.println("----------------地址："+path);
		//获取前台传来的文件名
		
		
		File file=new File(files.getAbsolutePath().replaceAll(".tmp", filefix));
		files.renameTo(file);
		System.out.println(files);
		
		//File file = new File(files.getParent(), files.getName().substring(0, files.getName().indexOf("."))+filefix);
		System.out.println(file);
		
		String newName=path+"/"+files.getName().substring(0, files.getName().indexOf("."))+filefix;
		System.out.println(newName);
		
		
		
		
		/*System.out.println(files.getParent());
		System.out.println(files.getName().substring(0, files.getName().indexOf("."))+filefix);
		File file = new File(files.getParent(), files.getName().substring(0, files.getName().indexOf("."))+filefix);
		System.out.println(file);
		
		*/
		String ftpPath=path;
		FtpClient ftp = new FtpClient(ftpurl, 21 , username, userpwd, false, "GBK");
		boolean ftpLogin = ftp.ftpLogin();
		boolean flag = ftp.uploadFile(file, ftpPath);

		renderText(SUCCESS);
	}
	
	
	public void addFilesNew() {
		try {
			boolean b=sWorkService.addFilesNew(sWork.getAppendix_pid(),path);
//			String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
//			String fileName = UUIDGenerator.getUUIDoffSpace() + ".xls";
//			String path = rootPath + fileName;
//			wb.write(new FileOutputStream(path));
			if(b)				
				renderText(SUCCESS);
			else
				renderText(ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(ERROR);
		}
	}
	
	
	public void addInsert() {
		try {
			boolean b=sWorkService.addInsert(sWork);
			if(b)				
				renderText(SUCCESS);
			else
				renderText(ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(ERROR);
		}
		System.out.println(sWork);
	}


	
	//根据ID获取信息
	public void getDetailById(){
		SWork sWork1=sWorkService.getDetailById(sWork);
		renderJson(sWork1);
	}
	
	


	//获取指定部门
	public void getDept(){
		List<DepInfo> dep=depManagerService.getDeptByTopAndName();
		renderJson(dep);
	}
	
	//获取
	public void getTopUserByDep(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String depid=request.getParameter("depid");
		DbManager manager=dbManagerService.getUserByDepid(depid);
		renderJson(manager);
	}
	
	
	public void getUserDept(){
		UserSession user = super.getUserSession();
		String userid=user.getLoginName();
		String username=user.getFullName();
		String deptlevel2id=user.getDeptLevel2Id();
		String deptname=user.getDepFullName();
		String mobile = user.getMobile();
		Map<Object,Object> map=new HashMap<Object, Object>();
		map.put("username",username);
		map.put("userid",userid);
		map.put("deptlevel2id",deptlevel2id);
		map.put("deptname",deptname);
		map.put("mobile",mobile);
		renderJson(map);
	}
	
	
	public void delProcess() {
		sWorkService.delProcess(sWork.getId());
		renderText(SUCCESS);
	}
	
	
	public void getEditProcess() {		
		sWork =  sWorkService.getDetailById(sWork);
		renderJson(sWork);
	}

/*	//生成编号
	public void getNumber() {
		Date date=new Date(System.currentTimeMillis());		
//		使用SimpleDateFormat接口定义一个日期格式；
        SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyyMMddHHmmss");        
//       将时间转换为String格式输出：
//       format为SimpleDateFormat接口的方法 ；意思：将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
        String serial_number= "GYS"+dateFormat1.format(date);
        renderJson(serial_number);   
        System.out.println(serial_number);
	}
*/
	
}
