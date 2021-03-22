package com.ultrapower.wfinterface.services;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.wfinterface.model.GwInfo;
import com.ultrapower.wfinterface.model.GwInfoJobData;
import com.ultrapower.wfinterface.model.GwInfoPlanData;
import com.ultrapower.wfinterface.model.GwSWork;
import com.ultrapower.wfinterface.model.GwSWorkAttach;
import com.ultrapower.wfinterface.model.KhzsQuestionnaire;


public class ImportGWInfoSrvServiceImpl  {

	private static Logger log = LoggerFactory
			.getLogger(ImportGWInfoSrvServiceImpl.class);
	private GwInfo model;
	
	private GwInfoPlanData modelplan;
	
	private GwInfoJobData modeljob;
	
	private KhzsQuestionnaire khzsQuestionnaire;
	
	private GwSWork gwSWork;
	
	private GwSWorkAttach sworkAttach;
	
	
	
	public ImportGWInfoSrvServiceImpl(GwInfo model) {
		super();
		this.model = model.getModel();
	}

	
	public ImportGWInfoSrvServiceImpl(GwInfoPlanData modelplan) {
		super();
		this.modelplan = modelplan.getModel();
	}
	
	public ImportGWInfoSrvServiceImpl(GwInfoJobData modeljob) {
		super();
		this.modeljob = modeljob.getModel();
	}
	
	public ImportGWInfoSrvServiceImpl(KhzsQuestionnaire khzsQuestionnaire) {
		super();
		this.khzsQuestionnaire = khzsQuestionnaire.getModel();
	}



	public ImportGWInfoSrvServiceImpl(GwSWork swork) {
		super();
		this.gwSWork=swork.getModel();
	}


	public ImportGWInfoSrvServiceImpl(GwSWorkAttach sworkAttach) {
		super();
		this.sworkAttach=sworkAttach.getModel();
	}


	public boolean dataHandle() throws Exception {
		return false;
		
/*		IaAuditAskService iaAuditAskService = (IaAuditAskService) WebApplicationManager.getBean("iaAuditAskService");
		log.info("call:ImportGWInfoSrvServiceImpl:start");
		
		String uuid = UUIDGenerator.getUUIDoffSpace();
		
		String tblName = "ia_audit_change_gwinfo";
		String sql = "insert into "
				+ tblName
				+ " (PID,GWYEAR,GWNO,GWTITLE,GWCREATOR,GWDATE,GWFILE,ATTRIBUTE1,ATTRIBUTE2,ATTRIBUTE3,ATTRIBUTE4,ATTRIBUTE5,CREATETIME,STATUS,OPTTIME,ERRORMESSAGE) ";
		sql += " values('"+uuid+"',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		QueryAdapter qa = new QueryAdapter();
		boolean b = qa.executeUpdate(sql, model.getGWYear(), model.getGWNo(), model.getGWTitle(),
				model.getGWCreator(), model.getGWDate(), model.getGWFile(), model.getATTRIBUTE1(), model.getATTRIBUTE2(), model.getATTRIBUTE3(),
				model.getATTRIBUTE4(), model.getATTRIBUTE5(),TimeUtils.formatDateToInt(new Date()), "0","","");
		if (!b) {
			throw new RuntimeException("追加工单入库失败!");
		}else{
			//
			iaAuditAskService.saveIaAuditChangeAccountableWS(uuid);
		}
		log.info("call:addWorksheet:over");
		
		return b;*/
	}




	public boolean dataHandleDeal(GwInfo gi, GwInfoPlanData giplan, List<GwInfoJobData> listgijob) {
		
		try {
			String uuid = UUIDGenerator.getUUIDoffSpace();
			
			String tblName = "NGCS_WF_CMPLNTS_RESEND_DEAL";
			String sql = "insert into "
					+ tblName
					+ " (ID,STEP,ACTIVITY,EXECUTOR,OPINION,DEPART,OPERATE,TIME,FORM,REPORTTEXT,UIDS) ";
			sql += " values('"+uuid+"',?,?,?,?,?,?,?,?,?,?)";
			QueryAdapter qa = new QueryAdapter();
			boolean b = qa.executeUpdate(sql, gi.getStep() == null ? "" : gi.getStep(),
					gi.getActivity() == null ? "" : gi.getActivity(), gi.getExecutor() == null ? "" : gi.getExecutor(),
					gi.getOpinion() == null ? "" : gi.getOpinion(), gi.getDepart() == null ? "" : gi.getDepart(),
					gi.getOperate() == null ? "" : gi.getOperate(), gi.getTime() == null ? "" : gi.getTime(),
					gi.getForm() == null ? "" : gi.getForm(), gi.getReporttext() == null ? "" : gi.getReporttext(),
					gi.getUid());
			
			String uuidplan = UUIDGenerator.getUUIDoffSpace();
			
			String tblNameplan = "NGCS_WF_CMPLNTS_DEAL_PLANDATA";
			String sqlplan = "insert into "
					+ tblNameplan
					+ " (ID, PID, PLANDEPART, PLANPERSON, PLANCONTENT, PLANDATE) ";
			sqlplan += " values('"+uuidplan+"',?,?,?,?,?)";
			QueryAdapter qaplan = new QueryAdapter();
			
			if(giplan !=null) {
				boolean bplan = qaplan.executeUpdate(sqlplan, uuid,
						giplan.getPlandepart() == null ? "" : giplan.getPlandepart(), 
						giplan.getPlanperson() == null ? "" : giplan.getPlanperson(), 
						giplan.getPlancontent() == null ? "" : giplan.getPlancontent(), 
						giplan.getPlandate() == null ? "" : giplan.getPlandate());
				/*boolean bplan = qaplan.executeUpdate(sqlplan, uuid, null,null,
						null,null);*/
			}
			
			
			if(listgijob.size()>0) {
				for(int i=0;i<listgijob.size();i++){
					String uuidjob = UUIDGenerator.getUUIDoffSpace();
					
					String tblNamejob = "NGCS_WF_CMPLNTS_DEAL_JOBDATA";
					String sqljob = "insert into "
							+ tblNamejob
							+ " (ID, PID, JOBDEPART, JOBPERSON, JOBCONTENT, JOBDATE) ";
					sqljob += " values('"+uuidjob+"',?,?,?,?,?)";
					QueryAdapter qajob = new QueryAdapter();
					boolean bjob = qajob.executeUpdate(sqljob, uuid,
							listgijob.get(i).getJobdepart() == null ? "" : listgijob.get(i).getJobdepart(),
							listgijob.get(i).getJobperson() == null ? "" : listgijob.get(i).getJobperson(),
							listgijob.get(i).getJobcontent() == null ? "" : listgijob.get(i).getJobcontent(),
							listgijob.get(i).getJobdate() == null ? "" : listgijob.get(i).getJobdate());
				}
			}
			
			/*if (!b) {
				throw new RuntimeException("追加工单入库失败!");
			}else{
				//
				iaAuditAskService.saveIaAuditChangeAccountableWS(uuid);
			}*/
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean dataHandlePlan(GwInfoPlanData giplan) {
		return false;
	}


	public boolean dataHandleJob(List<GwInfoJobData> listgijob) {
		return false;
	}


	public boolean sendBack(KhzsQuestionnaire questionnaire) {
		try {
			String sql="UPDATE ZL_KHZS_QUESTIONNAIRE SET STATUS = '草稿' ,PROJECTID = '' WHERE PROJECTID = ? ";
			QueryAdapter qa = new QueryAdapter();
			boolean b = qa.executeUpdate(sql, questionnaire.getProjectid());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean saveSworkSendBack(GwSWork swork) {
		try {
			//获取的数据内容放到数据库
			String sql="UPDATE ZL_SUPERVISE_WORK_ORDERS SET STATUS = '已完成' ,BACKGUID = '"+swork.getBackguid()+"' , "
					+ " BACKHANDLINGTIME = '"+swork.getBackhandlingtime()+"' ,"
					+ " BACKHANDLINGPERSON = '"+swork.getBackhandlingperson()+"' ,"
					+ " BACKHANDLINGPERSONLOGIN = '"+swork.getBackhandlingpersonlogin()+"' ,"
					+ " BACKRESOLUTION = '"+swork.getBackresolution()+"' WHERE RESULTSTR = ?";
			QueryAdapter qa = new QueryAdapter();
			boolean b = qa.executeUpdate(sql, swork.getBackguid());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	public boolean saveSworkAttach(GwSWorkAttach sworkAttach2) {
		try {
			//获取的数据内容放到数据库
			/*String sql="INSERT INTO ZL_SUPERVICE_WORK_ATTACH ( "
					+ " BACKGUID, ATTACHHTTPURL, ATTACHFTPURL, "
					+ "ATTACHTYPE, ATTACHNAME, ATTACHSIZE, ATTACHUPLOADLABEL, "
					+ "ATTACHFILEID, ATTACHCREATER, ATTACHCREATERNAME, "
					+ "ATTACHCREATETIME, ATTACHACTIVITYID ) VALUES ( "
					+ " '"+sworkAttach2.getBackguid()+"', "
					+ " '"+sworkAttach2.getAttachhttpurl()+"', "
					+ " '"+sworkAttach2.getAttachftpurl()+"', "
					+ " '"+sworkAttach2.getAttachtype()+"', "
					+ " '"+sworkAttach2.getAttachname()+"', "
					+ " '"+sworkAttach2.getAttachsize()+"', "
					+ " '"+sworkAttach2.getAttachuploadlabel()+"', "
					+ " '"+sworkAttach2.getAttachfileid()+"', "
					+ " '"+sworkAttach2.getAttachcreater()+"', "
					+ " '"+sworkAttach2.getAttachcreatername()+"', "
					+ " '"+sworkAttach2.getAttachcreatetime()+"', "
					+ " '"+sworkAttach2.getAttachactivityid()+"' );";*/
			String sql="INSERT INTO ZL_SUPERVICE_WORK_ATTACH ( "
					+ " BACKGUID, ATTACHHTTPURL, ATTACHFTPURL, "
					+ "ATTACHTYPE, ATTACHNAME, ATTACHSIZE, ATTACHUPLOADLABEL, "
					+ "ATTACHFILEID, ATTACHCREATER, ATTACHCREATERNAME, "
					+ "ATTACHCREATETIME, ATTACHACTIVITYID ) VALUES ( '"+sworkAttach2.getBackguid()+"',?,?,?,?,?,?,?,?,?,?,? )";
			QueryAdapter qa = new QueryAdapter();
			boolean b = qa.executeUpdate(sql,
					sworkAttach2.getAttachhttpurl(),
					sworkAttach2.getAttachftpurl(),
					sworkAttach2.getAttachtype(),
					sworkAttach2.getAttachname(),
					sworkAttach2.getAttachsize(),
					sworkAttach2.getAttachuploadlabel(),
					sworkAttach2.getAttachfileid(),
					sworkAttach2.getAttachcreater(),
					sworkAttach2.getAttachcreatername(),
					sworkAttach2.getAttachcreatetime(),
					sworkAttach2.getAttachactivityid());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}