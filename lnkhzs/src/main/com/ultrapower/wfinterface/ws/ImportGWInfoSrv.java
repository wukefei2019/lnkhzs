package com.ultrapower.wfinterface.ws;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.wfinterface.model.GwInfo;
import com.ultrapower.wfinterface.model.GwInfoJobData;
import com.ultrapower.wfinterface.model.GwInfoPlanData;
import com.ultrapower.wfinterface.model.GwSWork;
import com.ultrapower.wfinterface.model.GwSWorkAttach;
import com.ultrapower.wfinterface.model.KhzsQuestionnaire;
import com.ultrapower.wfinterface.services.ImportGWInfoSrvServiceImpl;


public class ImportGWInfoSrv {

	private static Logger log = LoggerFactory
			.getLogger(ImportGWInfoSrv.class);
	
	
	
	public String newdeal(String dealMessage){	
		JSONObject jsonObject = JSON.parseObject(dealMessage);
		System.out.println("----------------------------------------requestMessage:"+dealMessage);
		String result = "";
		String errormsg="";
		StringBuffer sf = new StringBuffer();
		
		String executor = jsonObject.get("executor").toString();
		String depart = jsonObject.get("depart").toString();
		String activity = jsonObject.get("activity").toString();
		String step = jsonObject.get("step").toString();
		String operate = jsonObject.get("operate").toString();
		String opinion = jsonObject.get("opinion").toString();
		String time = jsonObject.get("time").toString();
		String form = jsonObject.get("form").toString();
		String uid = jsonObject.get("uid").toString();
		
		GwInfo gi=new GwInfo();
		gi.setActivity(activity);
		gi.setDepart(depart);
		gi.setExecutor(executor);
		gi.setForm(form);
		gi.setOperate(operate);
		gi.setOpinion(opinion);
		gi.setStep(step);
		gi.setTime(time);
		gi.setReporttext(dealMessage);
		gi.setUid(uid);
		
		JSONObject jsonObjectForm = jsonObject.getJSONObject("form");
		
		JSONObject jsonPlan=jsonObjectForm.getJSONObject("planData");
		GwInfoPlanData giplan =null;
		GwInfoJobData gijob =null;
		List<GwInfoJobData> listgijob=new ArrayList<GwInfoJobData>();
		if(jsonPlan !=null) {
			String planDepart=jsonPlan.getString("planDepart");
			String planPerson=jsonPlan.getString("planPerson");
			String planTarget=jsonPlan.getString("planTarget");
			String planContent=jsonPlan.getString("planContent");
			String planDate=jsonPlan.getString("planDate");
		
		    giplan=new GwInfoPlanData();
			giplan.setPlancontent(planContent);
			giplan.setPlandate(planDate);
			giplan.setPlandepart(planDepart);
			giplan.setPlanperson(planPerson);
			giplan.setPlantarget(planTarget);
		}
		
		JSONArray jsonJob=jsonObjectForm.getJSONArray("jobData");
		if(jsonJob !=null) {
			
			gijob=new GwInfoJobData();
			JSONObject jsonJobO=jsonJob.getJSONObject(0);
			for(int i=0;i<jsonJob.size();i++){
				jsonJobO=jsonJob.getJSONObject(i);
				gijob=new GwInfoJobData();
				gijob.setJobcontent(jsonJobO.getString("jobContent"));
				gijob.setJobdepart(jsonJobO.getString("jobDepart"));
				gijob.setJobperson(jsonJobO.getString("jobPerson"));
				gijob.setJobdate(jsonJobO.getString("jobDate"));
				listgijob.add(gijob);
			}
		}
		
	
		ImportGWInfoSrvServiceImpl is=new ImportGWInfoSrvServiceImpl(gi);
		
		/*ImportGWInfoSrvServiceImpl isplan=new ImportGWInfoSrvServiceImpl(giplan);
		
		ImportGWInfoSrvServiceImpl isjob=new ImportGWInfoSrvServiceImpl(gijob);*/
		
		
		try {
			boolean b=is.dataHandleDeal(gi,giplan,listgijob);
			if(b)
			{
				/*boolean bplan=isplan.dataHandlePlan(giplan);
				boolean bjob=isjob.dataHandleJob(listgijob);*/
				result = "建单成功";
				log.info("建单成功");
				System.out.println("----------------------------------------RESENDMESSAGE:建单成功");
				/*log.info(opDetail);*/					
			}
			else
			{
				result = "建单失败";
				log.info("建单失败");
				/*log.info(opDetail);	*/
				errormsg="数据保存失败";
				System.out.println("----------------------------------------RESENDMESSAGE:数据保存失败");
			}			
			
			
			sf.append("<Msg> ");
			sf.append("  <ErrorFlag><![CDATA["+result+"]]> </ErrorFlag>  ");
			sf.append("  <ErrorMessage><![CDATA["+errormsg+"]]> </ErrorMessage> ");
			sf.append("</Msg>");
			
			log.info("返回给对方的报文："+sf.toString());
			System.out.println("----------------------------------------RESENDMESSAGE:数据保存失败"+sf.toString());
			
		} catch (Exception e) {
				
			e.printStackTrace();
		}
		return sf.toString();
	}
	
	
	public String sendBack(String dealMessage){	
		//JSONObject jsonObject = JSON.parseObject(dealMessage);
		
		String result = "";
		String errormsg="";
		StringBuffer sf = new StringBuffer();

		String uid = dealMessage;
		
		KhzsQuestionnaire questionnaire=new KhzsQuestionnaire();
		questionnaire.setProjectid(uid);
		
		
		ImportGWInfoSrvServiceImpl is=new ImportGWInfoSrvServiceImpl(questionnaire);
		
		try {
			boolean b=is.sendBack(questionnaire);
			if(b)
			{
				result = "退回成功";
				log.info("退回成功");				
			}
			else
			{
				result = "退回失败";
				log.info("退回失败");
				errormsg="数据修改失败";
			}			
			
			sf.append("<Msg> ");
			sf.append("  <ErrorFlag><![CDATA["+result+"]]> </ErrorFlag>  ");
			sf.append("  <ErrorMessage><![CDATA["+errormsg+"]]> </ErrorMessage> ");
			sf.append("</Msg>");
			
			log.info("返回给对方的报文："+sf.toString());
			
		} catch (Exception e) {
				
			e.printStackTrace();
		}
		return sf.toString();
	}
	
	
	/**
	 * 服务质量督办回传
	 * @param formdetail
	 * @return
	 */
	//http://localhost:8080/lnfwzl/servlet/ImportGWInfoSrv/sworkSendBack
	public String sworkSendBack(String formdetail){	

		/*报文 ：

		<fieldInfo>
		<GUID>xxx</GUID>//公务ID
		<HandlingTime>xxx</HandlingTime>//处理时间
		<HandlingPerson>xxx</HandlingPerson>//处理人中文名
		<HandlingPersonLogin>xxx</HandlingPersonLogin>//处理人oa登录名
		<Resolution>xxx</Resolution>//办理情况
		</fieldInfo>*/
		//返回内容
		String result = "";
		String errormsg="";
		StringBuffer sf = new StringBuffer();
		String ProcessID="";
		try {
			//获取回传信息
			Document doc = DocumentHelper.parseText(formdetail);
			Element root = doc.getRootElement();// 指向根节点<fieldInfo>
			Element GUID = root.element("GUID");
			Element HandlingTime = root.element("HandlingTime");
			Element HandlingPerson = root.element("HandlingPerson");
			Element HandlingPersonLogin = root.element("HandlingPersonLogin");
			Element Resolution = root.element("Resolution");
			//保存回传信息
			GwSWork swork=new GwSWork();
			swork.setBackguid(GUID.getTextTrim());
			swork.setBackhandlingperson(HandlingPerson.getTextTrim());
			swork.setBackhandlingpersonlogin(HandlingPersonLogin.getTextTrim());
			swork.setBackhandlingtime(HandlingTime.getTextTrim());
			swork.setBackresolution(Resolution.getTextTrim());
			//公务ID赋值
			ProcessID=GUID.getTextTrim();
			//存到数据库
			ImportGWInfoSrvServiceImpl is=new ImportGWInfoSrvServiceImpl(swork);
			boolean b=is.saveSworkSendBack(swork);
			
			if(b){
				result = "SUCCESS";
				log.info("调用成功");	
				
				
				try {
					System.out.println("-----------开始获取附件--------------------------------");
					//获取附件信息
					String attachResult = GwGetBackAttach.draftWorkFlow(ProcessID);
					//String attachResult = "";
					System.out.println("-----------输出附件返回信息--------------------------------");
					System.out.println(attachResult);
					/*attachResult="<attachRef>"
							+ "<attachInfo>"
							+ "<attachHttpUrl><![CDATA[http://10.64.93.11/gwptAttachment/201806/NetworkMequirementManagementV2/36e04c72-f3a4-4afa-ac18-12c278542085/公务报文1.txt]]></attachHttpUrl>"
							+ "<attachFtpUrl><![CDATA[ftp://192.168.2.3/201806/NetworkMequirementManagementV2/36e04c72-f3a4-4afa-ac18-12c278542085/公务报文1.txt]]></attachFtpUrl>"
							+ "<attachType>1</attachType>"
							+ "<attachName><![CDATA[公务报文1.txt]]></attachName>"
							+ "<attachSize><![CDATA[2952]]></attachSize>"
							+ "<attachUploadLabel><![CDATA[起草]]></attachUploadLabel>"
							+ "<attachFileID><![CDATA[304041]]></attachFileID>"
							+ "<attachCreater><![CDATA[wangjings]]></attachCreater>"
							+ "<attachCreaterName><![CDATA[王晶（省）]]></attachCreaterName>"
							+ "<attachCreateTime><![CDATA[2018/6/5 16:50:58]]></attachCreateTime>"
							+ "<attachActivityID><![CDATA[f2ba5931-825a-4615-acd6-786c7910fc82]]></attachActivityID>"
							+ "</attachInfo>"
							+ "<attachInfo>"
							+ "<attachHttpUrl><![CDATA[http://10.64.93.111/gwptAttachment/201806/NetworkMequirementManagementV2/36e04c72-f3a4-4afa-ac18-12c278542085/公务报文1.txt]]></attachHttpUrl>"
							+ "<attachFtpUrl><![CDATA[ftp://192.168.2.3/201806/NetworkMequirementManagementV2/36e04c72-f3a4-4afa-ac18-12c278542085/公务报文1.txt]]></attachFtpUrl>"
							+ "<attachType>1</attachType>"
							+ "<attachName><![CDATA[公务报文1.txt]]></attachName>"
							+ "<attachSize><![CDATA[2952]]></attachSize>"
							+ "<attachUploadLabel><![CDATA[起草]]></attachUploadLabel>"
							+ "<attachFileID><![CDATA[304041]]></attachFileID>"
							+ "<attachCreater><![CDATA[wangjings]]></attachCreater>"
							+ "<attachCreaterName><![CDATA[王晶（省）]]></attachCreaterName>"
							+ "<attachCreateTime><![CDATA[2018/6/5 16:50:58]]></attachCreateTime>"
							+ "<attachActivityID><![CDATA[f2ba5931-825a-4615-acd6-786c7910fc82]]></attachActivityID>"
							+ "</attachInfo>"
							+ "</attachRef>";*/
					//解析返回值附件信息
					Document doc1 = DocumentHelper.parseText(attachResult);
					Element root1 = doc1.getRootElement();// 指向根节点<attachRef>
					
					Iterator<?> attachInfos= root1.elementIterator("attachInfo");
					int i=1;
					while (attachInfos.hasNext()) {
						System.out.println("---------------第"+i+"个附件---------------------");
						Element attachInfo = (Element) attachInfos.next();
						Element attachHttpUrl = attachInfo.element("attachHttpUrl");//附件url
						Element attachFtpUrl = attachInfo.element("attachFtpUrl");//附件ftp
						Element attachType = attachInfo.element("attachType");//附件类型
						Element attachName = attachInfo.element("attachName");//附件名称
						Element attachSize = attachInfo.element("attachSize");//附件大小
						Element attachUploadLabel = attachInfo.element("attachUploadLabel");//附件上传节点
						Element attachFileID = attachInfo.element("attachFileID");//附件唯一ID
						Element attachCreater = attachInfo.element("attachCreater");//附件上传人UserID
						Element attachCreaterName = attachInfo.element("attachCreaterName");//附件上传人
						Element attachCreateTime = attachInfo.element("attachCreateTime");//附件上传时间 
						Element attachActivityID = attachInfo.element("attachActivityID");//附件上传节点ID
						
						//保存回传信息
						GwSWorkAttach sworkAttach=new GwSWorkAttach();
						sworkAttach.setBackguid(ProcessID);
						sworkAttach.setAttachhttpurl(attachHttpUrl.getTextTrim());
						sworkAttach.setAttachftpurl(attachFtpUrl.getTextTrim());
						sworkAttach.setAttachtype(attachType.getTextTrim());
						sworkAttach.setAttachname(attachName.getTextTrim());
						sworkAttach.setAttachsize(attachSize.getTextTrim());
						sworkAttach.setAttachuploadlabel(attachUploadLabel.getTextTrim());
						sworkAttach.setAttachfileid(attachFileID.getTextTrim());
						sworkAttach.setAttachcreater(attachCreater.getTextTrim());
						sworkAttach.setAttachcreatername(attachCreaterName.getTextTrim());
						sworkAttach.setAttachcreatetime(attachCreateTime.getTextTrim());
						sworkAttach.setAttachactivityid(attachActivityID.getTextTrim());
						
						//存到数据库
						ImportGWInfoSrvServiceImpl isAttach=new ImportGWInfoSrvServiceImpl(sworkAttach);
						boolean bAttach=isAttach.saveSworkAttach(sworkAttach);
						if(b){
							System.out.println("------------------保存回传信息成功----------");
						}else{
							System.out.println("------------------保存回传信息失败----------");
						}
						i++;
					}
					
					//单条解析
					/*Element attachHttpUrl = root1.element("attachInfo").element("attachHttpUrl");//附件url
					Element attachFtpUrl = root1.element("attachInfo").element("attachFtpUrl");//附件ftp
					Element attachType = root1.element("attachInfo").element("attachType");//附件类型
					Element attachName = root1.element("attachInfo").element("attachName");//附件名称
					Element attachSize = root1.element("attachInfo").element("attachSize");//附件大小
					Element attachUploadLabel = root1.element("attachInfo").element("attachUploadLabel");//附件上传节点
					Element attachFileID = root1.element("attachInfo").element("attachFileID");//附件唯一ID
					Element attachCreater = root1.element("attachInfo").element("attachCreater");//附件上传人UserID
					Element attachCreaterName = root1.element("attachInfo").element("attachCreaterName");//附件上传人
					Element attachCreateTime = root1.element("attachInfo").element("attachCreateTime");//附件上传时间 
					Element attachActivityID = root1.element("attachInfo").element("attachActivityID");//附件上传节点ID
*/					
					//保存回传信息
					/*GwSWorkAttach sworkAttach=new GwSWorkAttach();
					sworkAttach.setBackguid(ProcessID);
					sworkAttach.setAttachhttpurl(attachHttpUrl.getTextTrim());
					sworkAttach.setAttachftpurl(attachFtpUrl.getTextTrim());
					sworkAttach.setAttachtype(attachType.getTextTrim());
					sworkAttach.setAttachname(attachName.getTextTrim());
					sworkAttach.setAttachsize(attachSize.getTextTrim());
					sworkAttach.setAttachuploadlabel(attachUploadLabel.getTextTrim());
					sworkAttach.setAttachfileid(attachFileID.getTextTrim());
					sworkAttach.setAttachcreater(attachCreater.getTextTrim());
					sworkAttach.setAttachcreatername(attachCreaterName.getTextTrim());
					sworkAttach.setAttachcreatetime(attachCreateTime.getTextTrim());
					sworkAttach.setAttachactivityid(attachActivityID.getTextTrim());*/
					//存到数据库
					/*ImportGWInfoSrvServiceImpl isAttach=new ImportGWInfoSrvServiceImpl(sworkAttach);
					boolean bAttach=isAttach.saveSworkAttach(sworkAttach);
					if(b){
						System.out.println("------------------保存回传信息成功----------");
					}else{
						System.out.println("------------------保存回传信息失败----------");
					}*/
					
					/*生产附件ftp服务器：ftp://10.64.93.15/
						用户名：gwpt
						密码：Gw!2013#
						测试附件ftp服务器：ftp://10.64.93.11/
						用户名：gwpt
						密码：Gw!2013#*/
					//生产
					//String ftpip="10.64.93.15";
					//测试
					/*String ftpip="10.64.93.11";
					int ftpport=21;
					String ftpname="gwpt";
					String ftppwd="Gw!2013#";
					FtpClient ftp = new FtpClient(ftpip,ftpport, ftpname, ftppwd, false, "UTF-8");
					//登录服务器
					boolean ftpLogin = ftp.ftpLogin();
					ftp.downloadFile("", localDires, remoteDownLoadPath)*/
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("-----------获取附件失败--------------------------------");
					System.out.println(e.getMessage());
				}
				
				
			}else{
				result = "ERROR";
				log.info("调用失败");
				errormsg="数据修改失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "ERROR";
			log.info("调用失败"+e.getMessage());
			errormsg=e.getMessage();
		}
		sf.append("<Msg> ");
		sf.append("  <ErrorFlag><![CDATA["+result+"]]> </ErrorFlag>  ");
		sf.append("  <ErrorMessage><![CDATA["+errormsg+"]]> </ErrorMessage> ");
		sf.append("</Msg>");
		log.info("返回给对方的报文："+sf.toString());
		System.out.println("-----------回调流程结束--------------------------------");
		
		return sf.toString();
		
	}
	
	
	
	/**
	 * 内审与公文接口 建单
	 * @param opDetail
	 * @return
	 */
	public String newGw(String opDetail) 
	{
		
		String result = "";
		String errormsg="";
		StringBuffer sf = new StringBuffer();
		GwInfo gi=new GwInfo();
		gi.parseXml2Model(opDetail);

		
		ImportGWInfoSrvServiceImpl is=new ImportGWInfoSrvServiceImpl(gi);
		
		
		try {
			
			boolean b=is.dataHandle();
		
			if(b)
			{
				result = "Y";
				log.info("建单成功");
				log.info(opDetail);					
			}
			else
			{
				result = "N";
				log.info("建单失败");
				log.info(opDetail);	
				errormsg="数据保存失败";
			}			
			
			
			sf.append("<Msg> ");
			sf.append("  <ErrorFlag><![CDATA["+result+"]]> </ErrorFlag>  ");
			sf.append("  <ErrorMessage><![CDATA["+errormsg+"]]> </ErrorMessage> ");
			sf.append("</Msg>");
			
			log.info("返回给对方的报文："+sf.toString());
			
		} catch (Exception e) {
				
			e.printStackTrace();
		}
		return sf.toString();
	}
		
	
	public String isAlive()
	{		
		return "故障接口通信正常";
	}
}
