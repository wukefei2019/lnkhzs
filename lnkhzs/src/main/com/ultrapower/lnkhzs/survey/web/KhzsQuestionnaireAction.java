package com.ultrapower.lnkhzs.survey.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.bmc.thirdparty.org.springframework.web.multipart.MultipartFile;
import com.bmc.thirdparty.org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectEx;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.service.IBindDicSelectInfoService;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectService;
import com.ultrapower.lnkhzs.survey.service.IBindDicselectSymbolService;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.service.IKhzsRelationshipService;
import com.ultrapower.omcs.base.model.AjaxResultModel;
import com.ultrapower.omcs.base.web.BaseAction;
import com.ultrapower.omcs.exceptions.ExcelImportException;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷_WEB]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsQuestionnaireAction extends BaseAction {

	private static final long serialVersionUID = -1L;

	private IKhzsQuestionnaireService khzsQuestionnaireService;

	private IKhzsRelationshipService khzsRelationshipService;

	private KhzsQuestionnaire khzsQuestionnaire;

	private BindDicselect bindDicselect;
	
	private BindDicSelectInfo bindDicSelectInfo;
	
	private BindDicselectEx bindDicselectEx;
	
	private int countNum;
	
	private String resultPid;
	
	private String cityid;
	
	private int peopleNum;
	
	private File xls;
	
	private String screen;
	
	private String fileName;
	
	private String daynum;
	
	private String rule;
	
	private String message_info;
	
	private String num;
	
	private String khzsQuestionnaireId;
	
	private String projectId;
	
	
	
	
	
	

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getKhzsQuestionnaireId() {
		return khzsQuestionnaireId;
	}

	public void setKhzsQuestionnaireId(String khzsQuestionnaireId) {
		this.khzsQuestionnaireId = khzsQuestionnaireId;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getMessage_info() {
		return message_info;
	}

	public void setMessage_info(String message_info) {
		this.message_info = message_info;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public File getXls() {
		return xls;
	}

	public void setXls(File xls) {
		this.xls = xls;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public BindDicselectEx getBindDicselectEx() {
		return bindDicselectEx;
	}

	public void setBindDicselectEx(BindDicselectEx bindDicselectEx) {
		this.bindDicselectEx = bindDicselectEx;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getResultPid() {
		return resultPid;
	}

	public void setResultPid(String resultPid) {
		this.resultPid = resultPid;
	}

	public BindDicSelectInfo getBindDicSelectInfo() {
		return bindDicSelectInfo;
	}

	public void setBindDicSelectInfo(BindDicSelectInfo bindDicSelectInfo) {
		this.bindDicSelectInfo = bindDicSelectInfo;
	}

	public BindDicselect getBindDicselect() {
		return bindDicselect;
	}

	public void setBindDicselect(BindDicselect bindDicselect) {
		this.bindDicselect = bindDicselect;
	}

	

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}



	private IBindDicselectService bindDicselectService;
	
	private IBindDicSelectInfoService bindDicSelectInfoService;

	private BindDicselectSymbol bindDicselectSymbol;

	private IBindDicselectSymbolService bindDicselectSymbolService;

	public IKhzsQuestionnaireService getKhzsQuestionnaireService() {
		return khzsQuestionnaireService;
	}

	public void setKhzsQuestionnaireService(IKhzsQuestionnaireService khzsQuestionnaireService) {
		this.khzsQuestionnaireService = khzsQuestionnaireService;
	}

	public IBindDicselectService getBindDicselectService() {
		return bindDicselectService;
	}

	public void setBindDicselectService(IBindDicselectService bindDicselectService) {
		this.bindDicselectService = bindDicselectService;
	}

	public void setKhzsRelationshipService(IKhzsRelationshipService khzsRelationshipService) {
		this.khzsRelationshipService = khzsRelationshipService;
	}

	public KhzsQuestionnaire getKhzsQuestionnaire() {
		return khzsQuestionnaire;
	}

	public void setKhzsQuestionnaire(KhzsQuestionnaire khzsQuestionnaire) {
		this.khzsQuestionnaire = khzsQuestionnaire;
	}
	

	public IBindDicSelectInfoService getBindDicSelectInfoService() {
		return bindDicSelectInfoService;
	}

	public void setBindDicSelectInfoService(IBindDicSelectInfoService bindDicSelectInfoService) {
		this.bindDicSelectInfoService = bindDicSelectInfoService;
	}

	public BindDicselectSymbol getBindDicselectSymbol() {
		return bindDicselectSymbol;
	}

	public void setBindDicselectSymbol(BindDicselectSymbol bindDicselectSymbol) {
		this.bindDicselectSymbol = bindDicselectSymbol;
	}

	public IBindDicselectSymbolService getBindDicselectSymbolService() {
		return bindDicselectSymbolService;
	}

	public void setBindDicselectSymbolService(IBindDicselectSymbolService bindDicselectSymbolService) {
		this.bindDicselectSymbolService = bindDicselectSymbolService;
	}
	


	public void ajaxGetDywj() {
		khzsQuestionnaire = khzsQuestionnaireService.getKhzsTMain(khzsQuestionnaire.getId());
		renderJson(khzsQuestionnaire);
	}
	
	public void getCountyByCity(){
		renderJson(khzsQuestionnaireService.getCountyByCity(cityid));
	}
	
	public void getTotalSendNum() {
		Map<Object,Object> map = khzsQuestionnaireService.getTotalSendNum(bindDicselect, bindDicselectSymbol,bindDicselectEx,bindDicSelectInfo);
		renderJson(map);
	}
	
	public void publishTep1() {
		String a= khzsQuestionnaireService.publishTep1(countNum,khzsQuestionnaire.getId(),bindDicSelectInfo, bindDicselect, bindDicselectSymbol,bindDicselectEx);
		if(a==null||a.isEmpty())
			this.renderText("error");
		else
			renderJson(a);
	}

	public void publish() {

		khzsQuestionnaireService.publish(resultPid,khzsQuestionnaire.getId(),bindDicSelectInfo, bindDicselect, bindDicselectSymbol,bindDicselectEx,peopleNum,countNum);
		this.renderText("success");
	}

	public void saveSelect() {
		khzsQuestionnaireService.saveSelect(khzsQuestionnaire.getId(),bindDicSelectInfo, bindDicselect, bindDicselectSymbol,bindDicselectEx);
		this.renderText("success");
	}

	public void getSelect() {
		String selectid = khzsQuestionnaireService.getKhzsTMain(khzsQuestionnaire.getId()).getBsdicselectid();
		if (selectid == null)
			bindDicselect = null;
		else
			bindDicselect = bindDicselectService.getSelectMain(selectid);
		renderJson(bindDicselect);
	}
	
	public void getSelectDx() {
		String Bsdicseleinfoid = khzsQuestionnaireService.getKhzsTMain(khzsQuestionnaire.getId()).getBsdicseleinfoid();
		if (Bsdicseleinfoid == null)
			bindDicSelectInfo = null;
		else {
			 bindDicSelectInfo = bindDicSelectInfoService.getSelectMain(Bsdicseleinfoid);
		}
		renderJson(bindDicSelectInfo);
	}

	public void deleteDywj() {
		khzsQuestionnaireService.deleteDywj(khzsQuestionnaire.getId());
		khzsRelationshipService.deleteDywj(khzsQuestionnaire.getId());// 关系列表删除
																		// 相关问卷数据
		bindDicselectService.delectDicselect(khzsQuestionnaire.getBsdicselectid());
		bindDicselectSymbolService.delectSymbol(khzsQuestionnaire.getBsdicselectid());
		bindDicSelectInfoService.delectDicSelectInfo(khzsQuestionnaire.getBsdicseleinfoid());//删除问卷号绑定的短信、时长等表
		this.renderText("success");
	}

	public void updateDywj() {
		khzsQuestionnaireService.updateDywj(khzsQuestionnaire);
		this.renderText("success");
	}

	// 问卷调研统计
	public void seleAnswer() {
		List<Map<Object, Object>> seleAnswer = khzsQuestionnaireService.seleAnswer(khzsQuestionnaire.getId());
		
		renderJson(seleAnswer);
	}
	
	
	public void exportExcel() {
		String str = khzsQuestionnaireService.exportExcel(khzsQuestionnaire.getId());
		//str = str.substring(1);
		renderJson(str);
	}

	// 给页面显示< = >
	public void getSelectBol() {
		KhzsQuestionnaire khzsTMain = khzsQuestionnaireService.getKhzsTMain(khzsQuestionnaire.getId());
		String selectid = khzsTMain.getBsdicselectid();
		if (selectid == null){
			renderJson(null);
		}	
		else{
			bindDicselect = bindDicselectService.getSelectMain(selectid);
			List<BindDicselectSymbol> selectBol = bindDicselectSymbolService.getSelectBol(bindDicselect.getId());
			renderJson(selectBol);
		}
		
	}
	
	// SQL
	public void publishTest() {
		
		String where = " where 1 = 1 ";
		
		if(bindDicselect.getUser_id()!=null && !(bindDicselect.getUser_id().isEmpty())){
			where += " and USER_ID ='"+bindDicselect.getUser_id()+"'";
		}
		if(bindDicselect.getCity_id()!=null && !(bindDicselect.getCity_id().isEmpty())){
			where += " and CITY_ID ='"+bindDicselect.getCity_id()+"'";
		}
		if(bindDicselect.getCounty_id()!=null && !(bindDicselect.getCounty_id().isEmpty())){
			where += " and COUNTY_ID ='"+bindDicselect.getCounty_id()+"'";
		}
		if(bindDicselect.getAge()!=null && !(bindDicselect.getAge().isEmpty())){
			where += " and AGE ='"+bindDicselect.getAge()+"'";
		}
		if(bindDicselect.getUser_opentime()!=null && !(bindDicselect.getUser_opentime().isEmpty())){
			where += " and USER_OPENTIME ='"+bindDicselect.getUser_opentime()+"'";
		}
		if(bindDicselect.getRegion_id1()!=null && !(bindDicselect.getRegion_id1().isEmpty())){
			where += " and REGION_ID1 ='"+bindDicselect.getRegion_id1()+"'";
		}
		if(bindDicselect.getStar_level()!=null && !(bindDicselect.getStar_level().isEmpty())){
			where += " and STAR_LEVEL = '"+bindDicselect.getStar_level()+"'";
		}
		if(bindDicselect.getIs_2card()!=null && !(bindDicselect.getIs_2card().isEmpty())){
			where += " and IS_2CARD ='"+bindDicselect.getIs_2card()+"'";
		}
		if(bindDicselect.getIs_zhufu_card()!=null && !(bindDicselect.getIs_zhufu_card().isEmpty())){
			where += " and IS_ZHUFU_CARD ='"+bindDicselect.getIs_zhufu_card()+"'";
		}
		if(bindDicselect.getRom_in_chi_mark()!=null && !(bindDicselect.getRom_in_chi_mark().isEmpty())){
			where += " and ROM_IN_CHI_MARK ='"+bindDicselect.getRom_in_chi_mark()+"'";
		}
		if(bindDicselect.getRom_in_earth_mark()!=null && !(bindDicselect.getRom_in_earth_mark().isEmpty())){
			where += " and ROM_IN_EARTH_MARK ='"+bindDicselect.getRom_in_earth_mark()+"'";
		}
		if(bindDicselect.getSchool_flag()!=null && !(bindDicselect.getSchool_flag().isEmpty())){
			where += " and SCHOOL_FLAG ='"+bindDicselect.getSchool_flag()+"'";
		}
		if(bindDicselect.getIs_world()!=null && !(bindDicselect.getIs_world().isEmpty())){
			where += " and IS_WORLD ='"+bindDicselect.getIs_world()+"'";
		}
		if(bindDicselect.getIs_offer()!=null && !(bindDicselect.getIs_offer().isEmpty())){
			where += " and IS_OFFER ='"+bindDicselect.getIs_offer()+"'";
		}
		if(bindDicselect.getProvider_name()!=null && !(bindDicselect.getProvider_name().isEmpty())){
			where += " and PROVIDER_NAME ='"+bindDicselect.getProvider_name()+"'";
		}
		if(bindDicselect.getTerm_name()!=null && !(bindDicselect.getTerm_name().isEmpty())){
			where += " and TERM_NAME ='"+bindDicselect.getTerm_name()+"'";
		}
		if(bindDicselect.getArpu_3month()!=null && !(bindDicselect.getArpu_3month().isEmpty())){
			where += " and ARPU_3MONTH ='"+bindDicselect.getArpu_3month()+"'";
		}
		if(bindDicselect.getDou_3month()!=null && !(bindDicselect.getDou_3month().isEmpty())){
			where += " and DOU_3MONTH ='"+bindDicselect.getDou_3month()+"'";
		}
		if(bindDicselect.getMou_3month()!=null && !(bindDicselect.getMou_3month().isEmpty())){
			where += " and MOU_3MONTH = '"+bindDicselect.getMou_3month()+"'";
		}
		if(bindDicselect.getVolte_duration_3month()!=null && !(bindDicselect.getVolte_duration_3month().isEmpty())){
			where += " and VOLTE_DURATION_3MONTH ='"+bindDicselect.getVolte_duration_3month()+"'";
		}
		if(bindDicselect.getPlan_id()!=null && !(bindDicselect.getPlan_id().isEmpty())){
			where += " and PLAN_ID ='"+bindDicselect.getPlan_id()+"'";
		}
		if(bindDicselect.getPlanchng_3month()!=null && !(bindDicselect.getPlanchng_3month().isEmpty())){
			where += " and PLANCHNG_3MONTH ='"+bindDicselect.getPlanchng_3month()+"'";
		}
		if(bindDicselect.getGprs_xs_3month()!=null && !(bindDicselect.getGprs_xs_3month().isEmpty())){
			where += " and GPRS_XS_3MONTH ='"+bindDicselect.getGprs_xs_3month()+"'";
		}
		if(bindDicselect.getGprs_res_baohe()!=null && !(bindDicselect.getGprs_res_baohe().isEmpty())){
			where += " and GPRS_RES_BAOHE ='"+bindDicselect.getGprs_res_baohe()+"'";
		}
		if(bindDicselect.getCall_res_baohe()!=null && !(bindDicselect.getCall_res_baohe().isEmpty())){
			where += " and CALL_RES_BAOHE ='"+bindDicselect.getCall_res_baohe()+"'";
		}
		if(bindDicselect.getOver_flow_3month()!=null && !(bindDicselect.getOver_flow_3month().isEmpty())){
			where += " and OVER_FLOW_3MONTH ='"+bindDicselect.getOver_flow_3month()+"'";
		}
		if(bindDicselect.getCall_duration_fee_3momth()!=null && !(bindDicselect.getCall_duration_fee_3momth().isEmpty())){
			where += " and CALL_DURATION_FEE_3MOMTH ='"+bindDicselect.getCall_duration_fee_3momth()+"'";
		}
		if(bindDicselect.getOwe_mark()!=null && !(bindDicselect.getOwe_mark().isEmpty())){
			where += " and OWE_MARK ='"+bindDicselect.getOwe_mark()+"'";
		}
		if(bindDicselect.getYyt_banli_3month()!=null && !(bindDicselect.getYyt_banli_3month().isEmpty())){
			where += " and YYT_BANLI_3MONTH ='"+bindDicselect.getYyt_banli_3month()+"'";
		}
		if(bindDicselect.getWww_action_3month()!=null && !(bindDicselect.getWww_action_3month().isEmpty())){
			where += " and WWW_ACTION_3MONTH = '"+bindDicselect.getWww_action_3month()+"'";
		}
		if(bindDicselect.getLogin_3month()!=null && !(bindDicselect.getLogin_3month().isEmpty())){
			where += " and LOGIN_3MONTH ='"+bindDicselect.getLogin_3month()+"'";
		}
		if(bindDicselect.getIs_kd()!=null && !(bindDicselect.getIs_kd().isEmpty())){
			where += " and IS_KD ='"+bindDicselect.getIs_kd()+"'";
		}
		if(bindDicselect.getOffer_name()!=null && !(bindDicselect.getOffer_name().isEmpty())){
			where += " and OFFER_NAME ='"+bindDicselect.getOffer_name()+"'";
		}
		if(bindDicselect.getIs_fuse()!=null && !(bindDicselect.getIs_fuse().isEmpty())){
			where += " and IS_FUSE ='"+bindDicselect.getIs_fuse()+"'";
		}
		if(bindDicselect.getProd_bandwidth()!=null && !(bindDicselect.getProd_bandwidth().isEmpty())){
			where += " and PROD_BANDWIDTH ='"+bindDicselect.getProd_bandwidth()+"'";
		}
		if(bindDicselect.getKd_create_date()!=null && !(bindDicselect.getKd_create_date().isEmpty())){
			where += " and KD_CREATE_DATE ='"+bindDicselect.getKd_create_date()+"'";
		}
		if(bindDicselect.getMax_expire_date()!=null && !(bindDicselect.getMax_expire_date().isEmpty())){
			where += " and MAX_EXPIRE_DATE ='"+bindDicselect.getMax_expire_date()+"'";
		}
		if(bindDicselect.getIs_kdds()!=null && !(bindDicselect.getIs_kdds().isEmpty())){
			where += " and IS_KDDS ='"+bindDicselect.getIs_kdds()+"'";
		}
		if(bindDicselect.getMbh_should_fee()!=null && !(bindDicselect.getMbh_should_fee().isEmpty())){
			where += " and MBH_SHOULD_FEE ='"+bindDicselect.getMbh_should_fee()+"'";
		}
		if(bindDicselect.getIs_group()!=null && !(bindDicselect.getIs_group().isEmpty())){
			where += " and IS_GROUP ='"+bindDicselect.getIs_group()+"'";
		}
		if(bindDicselect.getIs_group_keyper()!=null && !(bindDicselect.getIs_group_keyper().isEmpty())){
			where += " and IS_GROUP_KEYPER ='"+bindDicselect.getIs_group_keyper()+"'";
		}
		if(bindDicselect.getIs_group_linker()!=null && !(bindDicselect.getIs_group_linker().isEmpty())){
			where += " and IS_GROUP_LINKER ='"+bindDicselect.getIs_group_linker()+"'";
		}
		if(bindDicselect.getIs_group_order()!=null && !(bindDicselect.getIs_group_order().isEmpty())){
			where += " and IS_GROUP_ORDER ='"+bindDicselect.getIs_group_order()+"'";
		}
		if(bindDicselect.getCreate_date()!=null && !(bindDicselect.getCreate_date().isEmpty())){
			where += " and CREATE_DATE ='"+bindDicselect.getCreate_date()+"'";
		}
		if(bindDicselect.getGroup_industry_id()!=null && !(bindDicselect.getGroup_industry_id().isEmpty())){
			where += " and GROUP_INDUSTRY_ID ='"+bindDicselect.getGroup_industry_id()+"'";
		}
		if(bindDicselect.getGroup_level_id()!=null && !(bindDicselect.getGroup_level_id().isEmpty())){
			where += " and GROUP_LEVEL_ID = '"+bindDicselect.getGroup_level_id()+"'";
		}
		if(bindDicselect.getIs_group_zx()!=null && !(bindDicselect.getIs_group_zx().isEmpty())){
			where += " and IS_GROUP_ZX ='"+bindDicselect.getIs_group_zx()+"'";
		}
		if(bindDicselect.getIs_qykd()!=null && !(bindDicselect.getIs_qykd().isEmpty())){
			where += " and IS_QYKD ='"+bindDicselect.getIs_qykd()+"'";
		}
		if(bindDicselect.getIs_group_vpmn()!=null && !(bindDicselect.getIs_group_vpmn().isEmpty())){
			where += " and IS_GROUP_VPMN ='"+bindDicselect.getIs_group_vpmn()+"'";
		}
		if(bindDicselect.getIs_idc()!=null && !(bindDicselect.getIs_idc().isEmpty())){
			where += " and IS_IDC ='"+bindDicselect.getIs_idc()+"'";
		}
		if(bindDicselect.getIs_ims()!=null && !(bindDicselect.getIs_ims().isEmpty())){
			where += " and IS_IMS ='"+bindDicselect.getIs_ims()+"'";
		}
		if(bindDicselect.getComplain_10086_counts()!=null && !(bindDicselect.getComplain_10086_counts().isEmpty())){
			where += " and COMPLAIN_10086_COUNTS ='"+bindDicselect.getComplain_10086_counts()+"'";
		}
		if(bindDicselect.getComplain_ydyw_counts()!=null && !(bindDicselect.getComplain_ydyw_counts().isEmpty())){
			where += " and COMPLAIN_YDYW_COUNTS ='"+bindDicselect.getComplain_ydyw_counts()+"'";
		}
		if(bindDicselect.getComplain_yywl_counts()!=null && !(bindDicselect.getComplain_yywl_counts().isEmpty())){
			where += " and COMPLAIN_YYWL_COUNTS ='"+bindDicselect.getComplain_yywl_counts()+"'";
		}
		if(bindDicselect.getComplain_jtyw_counts()!=null && !(bindDicselect.getComplain_jtyw_counts().isEmpty())){
			where += " and COMPLAIN_JTYW_COUNTS ='"+bindDicselect.getComplain_jtyw_counts()+"'";
		}
		if(bindDicselect.getComplain_jtkd_counts()!=null && !(bindDicselect.getComplain_jtkd_counts().isEmpty())){
			where += " and COMPLAIN_JTKD_COUNTS ='"+bindDicselect.getComplain_jtkd_counts()+"'";
		}
		if(bindDicselect.getComplain_jtds_counts()!=null && !(bindDicselect.getComplain_jtds_counts().isEmpty())){
			where += " and COMPLAIN_JTDS_COUNTS ='"+bindDicselect.getComplain_jtds_counts()+"'";
		}
		if(bindDicselect.getComplain_jtwl_counts()!=null && !(bindDicselect.getComplain_jtwl_counts().isEmpty())){
			where += " and COMPLAIN_JTWL_COUNTS ='"+bindDicselect.getComplain_jtwl_counts()+"'";
		}
		if(bindDicselect.getComplain_jt_counts()!=null && !(bindDicselect.getComplain_jt_counts().isEmpty())){
			where += " and COMPLAIN_JT_COUNTS ='"+bindDicselect.getComplain_jt_counts()+"'";
		}
		if(bindDicselect.getComplain_zz_counts()!=null && !(bindDicselect.getComplain_zz_counts().isEmpty())){
			where += " and COMPLAIN_ZZ_COUNTS ='"+bindDicselect.getComplain_zz_counts()+"'";
		}
		if(bindDicselect.getIs_ofer1()!=null && !(bindDicselect.getIs_ofer1().isEmpty())){
			where += " and IS_OFER1 ='"+bindDicselect.getIs_ofer1()+"'";
		}
		if(bindDicselect.getIs_volte_3month()!=null && !(bindDicselect.getIs_volte_3month().isEmpty())){
			where += " and IS_VOLTE_3MONTH ='"+bindDicselect.getIs_volte_3month()+"'";
		}
		if(bindDicselect.getMessage_info()!=null && !(bindDicselect.getMessage_info().isEmpty())){
			where += " and MESSAGE_INFO ='"+bindDicselect.getMessage_info()+"'";
		}
		
		System.out.println(where);
		this.renderText("success");

		/*khzsQuestionnaireService.publish(khzsQuestionnaire.getId(), bindDicselect, bindDicselectSymbol);
		this.renderText("success");*/
	}
	
	public String getExcelUp() throws IOException {
		
		AjaxResultModel resultModel = super.getImportResultModel();
		
		
		//获取前台传来的文件名
		File tempFile =new File( fileName.trim());  
		String fileName = tempFile.getName();  
		
		String flag = "成功！";
    	String level = "success";   
    	Workbook wb=khzsQuestionnaireService.checkVali(xls);
		
		 if (wb!=null) {
             // 导入数据
             try {
            	 try {
					khzsQuestionnaireService.importXls(wb,projectId,fileName);
				} catch (Exception e) {
					flag = "导入失败："+e.getMessage();
	     			level = "warning";	
				}
             } catch (ExcelImportException e) {
            	 e.printStackTrace();
             } 
         } else {
             flag = "Excel格式验证失败！";
         }
		 	resultModel.setLevel(level);
	        resultModel.setMsg(flag);
	        return "successAjax";
	}
	
	//短信群发接口
	public void getInterface() {
		  String a = khzsQuestionnaireService.getInterface(Integer.parseInt(num), khzsQuestionnaireId, rule, daynum, message_info);
		if(a==null||a.isEmpty())
			this.renderText("error");
		else
			renderJson(a);
	}
	
	
	public void getFileNum() {
		Workbook wb=khzsQuestionnaireService.checkVali(xls);
		String Num = khzsQuestionnaireService.getFileNum(wb);
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("Num", Num);*/
		this.renderText(Num);
	}

}
