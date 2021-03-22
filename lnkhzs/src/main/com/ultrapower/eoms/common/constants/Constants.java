package com.ultrapower.eoms.common.constants;

import java.util.Map;

public class Constants {
	public static String ROOT_PATH = "";
	
	public static boolean ISUSERCACHE		= true; 					//是否使用缓存
	public static int CACHE_TYPE			= 1;						//缓存类型 1：登陆缓存  2：全局缓存
	public static String PRIVILEGECACHE 	= "userprivilege";			//权限缓存名,修改需同步resources\ehcache.xml中配置
	public static String DICTYPE			= "dictype";				//字典类型缓存名,修改需同步resources\ehcache.xml中配置
	public static String QUERYSQL			= "querysql";				//查询SQL缓存名,修改需同步resources\ehcache.xml中配置
	public static String REPOSITORYMAP		= "repositoryMap";			//知识库缓存名,修改需同步resources\ehcache.xml中配置
	public static String REPOSITORYFLAG		= "repositoryFlag";			//知识库缓存是否保存标识,修改需同步resources\ehcache.xml中配置
	public static String RENTCOST_ALGORITHM           = "rentcostAlgorithm"; //租赁费用缓存名,修改需同步resources\ehcache.xml中配置
	
	public static String DATABASE_ALIAS		= "";
	public static String DATABASE_NSGL_ALIAS		= "";
	public static String DATABASE_OMCS_ALIAS		= "";
	public static String DATABASE_DACP_ALIAS		= "";
	
	public static boolean PWD_MANAGE		= false;					//是否开启萨班斯密码验证
	public static boolean CHECKCODE_MANAGE	= false;					//是否开启登录手机验证
	public static String LOGIN_CODE			= "0";						//后门登录标识

	public static String DOCSMANAGERTYPE	= "file";					//文档库类型 file:以文件为主;info:以信息为主
	public static String WORKSHEET_UPLOAD_PATH;							//工单附件上传路径
	public static String WORKFLOW_SERVERMODE	= "";					//是否启动工程内的流程引擎
	
	public static boolean PRIVILEGE_FLAG     = false;					//是否开启角色权限控制
	
	public static int CLUSTER_IMPORT_RMI_PORT ;   				//集群部署，文件同步，集群缓存的RMIserver端口
	
	public static String CLIENT_IP = "";  //工程ip,可用于Zookeeper client注册，集群RMI调用
	
    public static String CLASS_LOADER_PATH = "";
    
    public static String ProvinceDeptDNS;
    
    public static Map<String, String> CityDeptDNS;
	   
}
