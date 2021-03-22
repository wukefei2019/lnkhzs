package com.ultrapower.eoms.common.startup;

import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletContext;

import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.constants.ConstantsSynch;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.email.EmailPara;
import com.ultrapower.eoms.common.core.component.sms.manager.SmsProxyPara;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.eoms.ultrasla.init.InitSlaConfigInfo;
import com.ultrapower.eoms.ultrasm.service.InterceptConfigService;
import com.ultrapower.eoms.ultrasm.util.ResolvePwdManageCfg;


public class Initialization {
	
	private ServletContext servletContext;
	
	public  void init()
	{
	    TimeZone.setDefault(TimeZone.getTimeZone(PropertiesUtils.getProperty("TimeZone")));
	    Constants.ROOT_PATH = servletContext.getRealPath("/");
	    
		Constants.DATABASE_ALIAS = StringUtils.checkNullString(PropertiesUtils.getProperty("jdbc.alias"));
		Constants.DATABASE_NSGL_ALIAS = StringUtils.checkNullString(PropertiesUtils.getProperty("jdbc.alias"));
 		Constants.DATABASE_OMCS_ALIAS = StringUtils.checkNullString(PropertiesUtils.getProperty("jdbc.omcs.alias"));
 		Constants.DATABASE_DACP_ALIAS = StringUtils.checkNullString(PropertiesUtils.getProperty("dacp.jdbc.alias"));
		
		Constants.PWD_MANAGE = StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.pwdmanage")).equals("true") ? true : false;
		Constants.CHECKCODE_MANAGE = StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.checkcode")).equals("true") ? true : false;
		Constants.PRIVILEGE_FLAG =  StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.privilege")).equals("true") ? true : false;
		Constants.CLIENT_IP = StringUtils.checkNullString(PropertiesUtils.getProperty("distrib.clientip"));
		//classes 路径
		Constants.CLASS_LOADER_PATH = Initialization.class.getClassLoader().getResource("/").getPath();
		
		ConstantsSynch.isSynch = PropertiesUtils.getProperty("iam.pasm.isSynch").equals("true")?true:false;
		ConstantsSynch.isUip = PropertiesUtils.getProperty("iam.uip.isSynch").equals("true")?true:false;
		ConstantsSynch.isPasmSynchEoms = PropertiesUtils.getProperty("iam.uip.isPasmSynchEoms").equals("true")?true:false;
		ConstantsSynch.isSynchToV2 = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.eoms_v2")).equals("true") ? true : false;
		ConstantsSynch.synchToV2Content = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.eoms_v2.content"));
		ConstantsSynch.isSynchToPasm = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.eoms_pasm")).equals("true") ? true : false;
		ConstantsSynch.synchToPasmContent = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.eoms_pasm.content"));
		ConstantsSynch.isSynchFromV2 = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.v2_eoms")).equals("true") ? true : false;
		ConstantsSynch.synchFromV2Content = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.v2_eoms.content"));
		ConstantsSynch.isSynchFromPasm = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.pasm_eoms")).equals("true") ? true : false;
		ConstantsSynch.ZKHAS = StringUtils.checkNullString(PropertiesUtils.getProperty("distrib.zkhas")).equals("true") ? true : false;
		ConstantsSynch.CUSTOM_LOGTEMPLATE = StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.custom.logtemplate")).equals("true") ? true : false;
		ConstantsSynch.CUSTOM_TRANSFERCONFIG = StringUtils.checkNullString(PropertiesUtils.getProperty("eoms.custom.transferconfig")).equals("true") ? true : false;
		ConstantsSynch.synchFromPasmContent = StringUtils.checkNullString(PropertiesUtils.getProperty("synch.pasm_eoms.content"));
		Constants.WORKFLOW_SERVERMODE = StringUtils.checkNullString(PropertiesUtils.getProperty("wf.servermode"));
		SmsProxyPara.executeclass = StringUtils.checkNullString(PropertiesUtils.getPropertySm("executeclass"));;
		SmsProxyPara.smsip = StringUtils.checkNullString(PropertiesUtils.getPropertySm("smsip"));;
		SmsProxyPara.smsport = StringUtils.checkNullString(PropertiesUtils.getPropertySm("smsport"));
		
		
		//初始化SLA配置信息
		InitSlaConfigInfo.initConfig();
		
		EmailPara.MAIL_SMTP_HOST = PropertiesUtils.getPropertyMail("mail.smtp.host");
		EmailPara.MAIL_P0P3_HOST = PropertiesUtils.getPropertyMail("mail.pop3.host");
		EmailPara.MAIL_USER = PropertiesUtils.getPropertyMail("mail.user");
		EmailPara.MAIL_PASSWORD = PropertiesUtils.getPropertyMail("mail.password");
		EmailPara.MAIL_FROM = PropertiesUtils.getPropertyMail("mail.from");
		EmailPara.MAIL_SMTP_HOST_PORT = PropertiesUtils.getPropertyMail("mail.smtp.host.port");
		
		//初始化附件路径
		SwfuploadUtil.SWFUPLOAD_UPLOAD_PATH = PropertiesUtils.getProperty("attachment.path").endsWith("\\")||PropertiesUtils.getProperty("attachment.path").endsWith("/")
												?PropertiesUtils.getProperty("attachment.path").substring(0,PropertiesUtils.getProperty("attachment.path").length()-1)
												:PropertiesUtils.getProperty("attachment.path");
												
												
      /*  Constants.ProvinceDeptDNS = PropertiesUtils.getProperty("depdns_ln");*/
        
       /* Constants.CityDeptDNS = new HashMap<String,String>();
        Constants.CityDeptDNS.put("137130", PropertiesUtils.getProperty("depdns_tl")); //铁岭
        Constants.CityDeptDNS.put("137133", PropertiesUtils.getProperty("depdns_jz"));//锦州
        Constants.CityDeptDNS.put("137134", PropertiesUtils.getProperty("depdns_as"));//鞍山
        Constants.CityDeptDNS.put("137131", PropertiesUtils.getProperty("depdns_dl"));//大连
        Constants.CityDeptDNS.put("137132", PropertiesUtils.getProperty("depdns_ly"));//辽阳
        Constants.CityDeptDNS.put("137135", PropertiesUtils.getProperty("depdns_hld"));//葫芦岛
        Constants.CityDeptDNS.put("137136", PropertiesUtils.getProperty("depdns_cy"));//朝阳
        Constants.CityDeptDNS.put("137137", PropertiesUtils.getProperty("depdns_pj"));//盘锦
        Constants.CityDeptDNS.put("137138", PropertiesUtils.getProperty("depdns_fx"));//阜新
        Constants.CityDeptDNS.put("137139", PropertiesUtils.getProperty("depdns_fs"));//抚顺
        Constants.CityDeptDNS.put("137141", PropertiesUtils.getProperty("depdns_yk"));//营口
        Constants.CityDeptDNS.put("137142", PropertiesUtils.getProperty("depdns_bx"));//本溪
        Constants.CityDeptDNS.put("137143", PropertiesUtils.getProperty("depdns_dd"));//丹东
        Constants.CityDeptDNS.put("137226", PropertiesUtils.getProperty("depdns_sy"));//沈阳	
 */       
		if(servletContext!=null)
			SwfuploadUtil.APP_ROOT_REALPATH = servletContext.getRealPath("");
		
//		if(servletContext!=null)
//			FileOperate.APP_ROOT_REALPATH = servletContext.getRealPath("");
												
		Constants.WORKSHEET_UPLOAD_PATH = PropertiesUtils.getProperty("worksheet.attachment.path");
		
		//附件临时目录清理线程启动
		SwfuploadUtil.tempAttachCleaner.startClear();
		
//		RepositoryLoadXML loadXML = new RepositoryLoadXML();
//		loadXML.getPropertyMap();
		
		//加载萨班斯密码管理配置
/*		if(Constants.PWD_MANAGE)
			ResolvePwdManageCfg.getPwdManageCfg();
    	*/
//		SynDataShiftThread synDataShiftThread=new SynDataShiftThread();
//		synDataShiftThread.start();
												
		//系统启动lookup RMI Server，加快后续访问速度
//		try {
//			WorkFlowServiceClient.clientInstance().getBizFacade().test();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
/*		//多数据源配置
		DataSource ds = (DataSource) WebApplicationManager.getBean("dataSource_dacp");
		try {
		    Connection conn = ds.getConnection();
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		*/
		try {

			
			//将URL日志拦截配置添加到缓存
			InterceptConfigService interceptConfigService  = (InterceptConfigService)WebApplicationManager.getBean("interceptConfigService");
			interceptConfigService.cacheInterceptConfig();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
