package com.ultrapower.lnkhzs.survey.manager;

import com.ultrapower.lnkhzs.khzs.model.KhzsTMainBak;
import com.ultrapower.lnkhzs.survey.model.BindDicSelectInfo;
import com.ultrapower.lnkhzs.survey.model.BindDicselect;
import com.ultrapower.lnkhzs.survey.model.BindDicselectEx;
import com.ultrapower.lnkhzs.survey.model.BindDicselectSymbol;
import com.ultrapower.lnkhzs.survey.model.DwProductUserInfo;
import com.ultrapower.lnkhzs.survey.model.KhzsAnswer;
import com.ultrapower.lnkhzs.survey.model.KhzsQuestionnaire;
import com.ultrapower.lnkhzs.survey.model.KhzsRelationship;
import com.ultrapower.lnkhzs.survey.service.IKhzsQuestionnaireService;
import com.ultrapower.lnkhzs.survey.web.DESUtil;
import com.ultrapower.lnkhzs.survey.web.IaSMSSendAction;
import com.ultrapower.lnkhzs.trace.model.TraceSourceList;
import com.ultrapower.omcs.common.utils.ExcelUtils;
import com.ultrapower.omcs.exceptions.ExcelImportException;
import com.ultrapower.omcs.exceptions.ExcelTemplateException;
import com.ultrapower.omcs.utils.DateUtils;

import jxl.write.DateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContextEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bmc.thirdparty.org.apache.commons.beanutils.BeanUtils;
import com.ibm.db2.jcc.t4.ob;
import com.sshtools.j2ssh.net.HttpRequest;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.constants.Constants;
import com.ultrapower.eoms.common.constants.PropertiesUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.core.util.ftpclient.FtpClient;
import com.ultrapower.eoms.common.plugin.swfupload.utils.SwfuploadUtil;
import com.ultrapower.eoms.common.portal.model.UserSession;

/**
 * Created on 2019年4月19日
 * <p>
 * Title : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: [问卷_接口]
 * </p>
 * 
 * @author :
 * @version : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class KhzsQuestionnaireMgrImpl implements IKhzsQuestionnaireService {

	private IDao<KhzsQuestionnaire> khzsQuestionnaireDao;

	private IDao<KhzsRelationship> khzsRelationshipDao;

	private IDao<BindDicselect> bindDicselectDao;

	private IDao<KhzsAnswer> khzsAnswerDao;

	private IDao<BindDicselectSymbol> bindDicselectSymbolDao;

	private IDao<BindDicSelectInfo> bindDicSelectInfoDao;

	private IDao<DwProductUserInfo> DwProductUserInfoDao;

	public IDao<DwProductUserInfo> getDwProductUserInfoDao() {
		return DwProductUserInfoDao;
	}

	public void setDwProductUserInfoDao(IDao<DwProductUserInfo> dwProductUserInfoDao) {
		DwProductUserInfoDao = dwProductUserInfoDao;
	}

	public IDao<BindDicSelectInfo> getBindDicSelectInfoDao() {
		return bindDicSelectInfoDao;
	}

	public void setBindDicSelectInfoDao(IDao<BindDicSelectInfo> bindDicSelectInfoDao) {
		this.bindDicSelectInfoDao = bindDicSelectInfoDao;
	}

	public IDao<BindDicselectSymbol> getBindDicselectSymbolDao() {
		return bindDicselectSymbolDao;
	}

	public void setBindDicselectSymbolDao(IDao<BindDicselectSymbol> bindDicselectSymbolDao) {
		this.bindDicselectSymbolDao = bindDicselectSymbolDao;
	}

	public IDao<KhzsAnswer> getKhzsAnswerDao() {
		return khzsAnswerDao;
	}

	public void setKhzsAnswerDao(IDao<KhzsAnswer> khzsAnswerDao) {
		this.khzsAnswerDao = khzsAnswerDao;
	}

	public void setBindDicselectDao(IDao<BindDicselect> bindDicselectDao) {
		this.bindDicselectDao = bindDicselectDao;
	}

	public IDao<KhzsRelationship> getKhzsRelationshipDao() {
		return khzsRelationshipDao;
	}

	public void setKhzsRelationshipDao(IDao<KhzsRelationship> khzsRelationshipDao) {
		this.khzsRelationshipDao = khzsRelationshipDao;
	}

	public void setKhzsQuestionnaireDao(IDao<KhzsQuestionnaire> khzsQuestionnaireDao) {
		this.khzsQuestionnaireDao = khzsQuestionnaireDao;
	}

	/*
	 * @Override public void saveMainBak(KhzsQuestionnaire KhzsQuestionnaire) {
	 * if(StringUtils.isBlank(KhzsQuestionnaire.getId())) {
	 * //KhzsQuestionnaire.setId(UUIDGenerator.getUUIDoffSpace());
	 * KhzsQuestionnaire.setStatus("草稿");
	 * KhzsQuestionnaire.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"
	 * )); khzsQuestionnaireDao.save(KhzsQuestionnaire); }else {
	 * KhzsQuestionnaire.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"
	 * )); khzsQuestionnaireDao.saveOrUpdate(KhzsQuestionnaire); } }
	 */

	@Override
	public void saveMainBak(String myAsk, String myName) {
		KhzsQuestionnaire KhzsQuestionnaire = new KhzsQuestionnaire();
		/*
		 * if(!(myAsk.isEmpty() || myAsk==null)) {
		 * KhzsQuestionnaire.setId(UUIDGenerator.getUUIDoffSpace());
		 * KhzsQuestionnaire.setName(myName); KhzsQuestionnaire.setStatus("草稿");
		 * KhzsQuestionnaire.setCreattime(DateUtils.
		 * getDateStr("yyyy-MM-dd HH:mm:ss"));
		 * khzsQuestionnaireDao.save(KhzsQuestionnaire); }else {
		 */
		KhzsQuestionnaire.setId(myAsk);
		KhzsQuestionnaire.setName(myName);
		KhzsQuestionnaire.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
		KhzsQuestionnaire.setStatus("草稿");
		khzsQuestionnaireDao.saveOrUpdate(KhzsQuestionnaire);
		/* } */
	}

	@Override
	public KhzsQuestionnaire getKhzsTMain(String id) {
		// TODO Auto-generated method stub
		return khzsQuestionnaireDao.get(id);
	}

	@Override
	public void deleteDywj(String id) {
		try {
			khzsQuestionnaireDao.removeById(id);
			khzsRelationshipDao.executeUpdate("delete from KhzsRelationship where questionnaireid = ?", id);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@Override
	public boolean updateDywj(KhzsQuestionnaire KhzsQuestionnaire) {
		try {
			KhzsQuestionnaire.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
			String name1 = null;
			String status1 = null;
			String name = KhzsQuestionnaire.getName();
			String[] split = name.split(" ");
			// 判断名字 去空格 去,
			for (String string : split) {
				if (string.equals(",") || string.trim().isEmpty()) {
					continue;
				} else {
					name1 = string;
				}
			}

			String status = KhzsQuestionnaire.getStatus();
			String[] split2 = status.split(" ");

			// 判断状态 去空格 去,
			for (String string : split2) {
				if (string.equals(",") || string.trim().isEmpty()) {
					continue;
				} else {
					status1 = string;
				}
			}

			if (name1 == null || status1 == null) {
				return false;
			}

			KhzsQuestionnaire.setName(name1);
			KhzsQuestionnaire.setStatus(status1);
			KhzsQuestionnaire.toString();

			khzsQuestionnaireDao.saveOrUpdate(KhzsQuestionnaire);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void saveSelect(String id, BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,
			BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx) {
		String uuid = UUIDGenerator.getUUIDoffSpace();
		if (StringUtils.isBlank(bindDicselect.getId())) {
			bindDicselect.setId(uuid);
			bindDicSelectInfo.setId(uuid);
			/*
			 * bindDicselect.setChangetime(TimeUtils.formatDateToDateString(new
			 * Date(), "yyyy-MM-dd HH:mm:ss"));
			 */
			bindDicselectDao.save(bindDicselect);
			bindDicSelectInfoDao.save(bindDicSelectInfo);
			int executeUpdate = khzsQuestionnaireDao.executeUpdate("update KhzsQuestionnaire set bsdicselectid = '"
					+ uuid + "',bsdicseleinfoid ='" + uuid + "' where id = ? ", id);

			// *****************************************************************************************
			String ArrSymbol = bindDicselectSymbol.getSymbol();

			String[] split = ArrSymbol.split(", ");
			String yhbm = split[0];// 用户编码
			String age = split[1];// 年龄
			String rwsj = split[2];// 入网时间
			String arpu = split[3];// 近三个月月均ARPU
			String dou = split[4];// 近三个月月均DOU
			String mou = split[5];// 近三个月月均MOU
			String volte = split[6];// 近三个月月均VOLTE通话时长
			String tcbg = split[7];// 近三个月套餐变更次数
			String llxs = split[8];// 近三个月流量限速次数
			String llbh = split[9];// 当月套餐内流量饱和值
			String yyth = split[10];// 当月套餐内语音通话饱和值
			String twll = split[11];// 近三个月套外流量
			String yyfy = split[12];// 近三个月套外语音费用
			String blcs = split[13];// 近三个月营业厅办理次数
			String wtcs = split[14];// 近三个月登陆网厅次数
			String yytcs = split[15];// 近三个月登陆手机营业厅次数
			String ktsj = split[16];// 开通时间
			String dqsj = split[17];// 宽带到期时间
			String xfje = split[18];// 近三个月宽带电视消费金额
			String jtrwsj = split[19];// 所属集团客户入网时间
			String tscs = split[20];// 近三个月10086投诉次数
			String ywtscs = split[21];// 移动业务投诉次数
			String zltscs = split[22];// 移动网络质量投诉次数
			String jtywtscs = split[23];// 家庭业务投诉次数
			String kdtscs = split[24];// 家庭宽带投诉次数
			String dstscs = split[25];// 宽带电视投诉次数
			String wlzltscs = split[26];// 家庭宽带网络质量投诉次数
			String jtywtszs = split[27];// 集团业务投诉次数
			String zzywtscs = split[28];// 增值业务投诉次数

			String uuidBol1 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol1);// ID
			bindDicselectSymbol.setDicselectid(uuid);// 筛选ID
			bindDicselectSymbol.setSelectname("yhbm");// 筛选字段名
			bindDicselectSymbol.setSymbol(yhbm);// 0 > 1 = 2 <
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol3 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol3);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("nl");
			bindDicselectSymbol.setSymbol(age);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol4 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol4);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("rwsj");
			bindDicselectSymbol.setSymbol(rwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol7 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol7);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("arpu");
			bindDicselectSymbol.setSymbol(arpu);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol8 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol8);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dou");
			bindDicselectSymbol.setSymbol(dou);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol9 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol9);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("mou");
			bindDicselectSymbol.setSymbol(mou);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol10 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol10);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("volte");
			bindDicselectSymbol.setSymbol(volte);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol12 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol12);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("tcbg");
			bindDicselectSymbol.setSymbol(tcbg);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol13 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol13);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("llxs");
			bindDicselectSymbol.setSymbol(llxs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol14 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol14);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("llbh");
			bindDicselectSymbol.setSymbol(llbh);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol15 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol15);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yyth");
			bindDicselectSymbol.setSymbol(yyth);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol16 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol16);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("twll");
			bindDicselectSymbol.setSymbol(twll);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol17 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol17);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yyfy");
			bindDicselectSymbol.setSymbol(yyfy);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol18 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol18);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("blcs");
			bindDicselectSymbol.setSymbol(blcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol19 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol19);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("wtcs");
			bindDicselectSymbol.setSymbol(wtcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol20 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol20);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yytcs");
			bindDicselectSymbol.setSymbol(yytcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol22 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol22);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("ktsj");
			bindDicselectSymbol.setSymbol(ktsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol23 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol23);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dqsj");
			bindDicselectSymbol.setSymbol(dqsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol24 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol24);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("xfje");
			bindDicselectSymbol.setSymbol(xfje);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol25 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol25);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtrwsj");
			bindDicselectSymbol.setSymbol(jtrwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol27 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol27);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("tscs");
			bindDicselectSymbol.setSymbol(tscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol28 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol28);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("ywtscs");
			bindDicselectSymbol.setSymbol(ywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol29 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol29);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("zltscs");
			bindDicselectSymbol.setSymbol(zltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol30 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol30);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtywtscs");
			bindDicselectSymbol.setSymbol(jtywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol31 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol31);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("kdtscs");
			bindDicselectSymbol.setSymbol(kdtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol32 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol32);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dstscs");
			bindDicselectSymbol.setSymbol(dstscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol33 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol33);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("wlzltscs");
			bindDicselectSymbol.setSymbol(wlzltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol34 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol34);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtywtszs");
			bindDicselectSymbol.setSymbol(jtywtszs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol35 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol35);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("zzywtscs");
			bindDicselectSymbol.setSymbol(zzywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			// *****************************************************************************************

		} else {
			// bindDicselect.setChangetime(TimeUtils.formatDateToDateString(new
			// Date(), "yyyy-MM-dd HH:mm:ss"));
			bindDicselectDao.saveOrUpdate(bindDicselect);
			bindDicSelectInfoDao.saveOrUpdate(bindDicSelectInfo);
			uuid = bindDicselect.getId();

			// *****************************************************************************************
			bindDicselectSymbolDao.executeUpdate("delete from BindDicselectSymbol where dicselectid =?",
					bindDicselect.getId());

			String ArrSymbol = bindDicselectSymbol.getSymbol();

			String[] split = ArrSymbol.split(", ");
			String yhbm = split[0];// 用户编码
			String age = split[1];// 年龄
			String rwsj = split[2];// 入网时间
			String arpu = split[3];// 近三个月月均ARPU
			String dou = split[4];// 近三个月月均DOU
			String mou = split[5];// 近三个月月均MOU
			String volte = split[6];// 近三个月月均VOLTE通话时长
			String tcbg = split[7];// 近三个月套餐变更次数
			String llxs = split[8];// 近三个月流量限速次数
			String llbh = split[9];// 当月套餐内流量饱和值
			String yyth = split[10];// 当月套餐内语音通话饱和值
			String twll = split[11];// 近三个月套外流量
			String yyfy = split[12];// 近三个月套外语音费用
			String blcs = split[13];// 近三个月营业厅办理次数
			String wtcs = split[14];// 近三个月登陆网厅次数
			String yytcs = split[15];// 近三个月登陆手机营业厅次数
			String ktsj = split[16];// 开通时间
			String dqsj = split[17];// 宽带到期时间
			String xfje = split[18];// 近三个月宽带电视消费金额
			String jtrwsj = split[19];// 所属集团客户入网时间
			String tscs = split[20];// 近三个月10086投诉次数
			String ywtscs = split[21];// 移动业务投诉次数
			String zltscs = split[22];// 移动网络质量投诉次数
			String jtywtscs = split[23];// 家庭业务投诉次数
			String kdtscs = split[24];// 家庭宽带投诉次数
			String dstscs = split[25];// 宽带电视投诉次数
			String wlzltscs = split[26];// 家庭宽带网络质量投诉次数
			String jtywtszs = split[27];// 集团业务投诉次数
			String zzywtscs = split[28];// 增值业务投诉次数

			String uuidBol1 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol1);// ID
			bindDicselectSymbol.setDicselectid(uuid);// 筛选ID
			bindDicselectSymbol.setSelectname("yhbm");// 筛选字段名
			bindDicselectSymbol.setSymbol(yhbm);// 0 > 1 = 2 <
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol3 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol3);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("nl");
			bindDicselectSymbol.setSymbol(age);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol4 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol4);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("rwsj");
			bindDicselectSymbol.setSymbol(rwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol7 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol7);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("arpu");
			bindDicselectSymbol.setSymbol(arpu);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol8 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol8);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dou");
			bindDicselectSymbol.setSymbol(dou);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol9 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol9);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("mou");
			bindDicselectSymbol.setSymbol(mou);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol10 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol10);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("volte");
			bindDicselectSymbol.setSymbol(volte);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol12 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol12);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("tcbg");
			bindDicselectSymbol.setSymbol(tcbg);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol13 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol13);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("llxs");
			bindDicselectSymbol.setSymbol(llxs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol14 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol14);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("llbh");
			bindDicselectSymbol.setSymbol(llbh);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol15 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol15);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yyth");
			bindDicselectSymbol.setSymbol(yyth);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol16 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol16);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("twll");
			bindDicselectSymbol.setSymbol(twll);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol17 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol17);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yyfy");
			bindDicselectSymbol.setSymbol(yyfy);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol18 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol18);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("blcs");
			bindDicselectSymbol.setSymbol(blcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol19 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol19);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("wtcs");
			bindDicselectSymbol.setSymbol(wtcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol20 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol20);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("yytcs");
			bindDicselectSymbol.setSymbol(yytcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol22 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol22);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("ktsj");
			bindDicselectSymbol.setSymbol(ktsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol23 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol23);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dqsj");
			bindDicselectSymbol.setSymbol(dqsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol24 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol24);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("xfje");
			bindDicselectSymbol.setSymbol(xfje);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol25 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol25);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtrwsj");
			bindDicselectSymbol.setSymbol(jtrwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol27 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol27);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("tscs");
			bindDicselectSymbol.setSymbol(tscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol28 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol28);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("ywtscs");
			bindDicselectSymbol.setSymbol(ywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol29 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol29);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("zltscs");
			bindDicselectSymbol.setSymbol(zltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol30 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol30);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtywtscs");
			bindDicselectSymbol.setSymbol(jtywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol31 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol31);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("kdtscs");
			bindDicselectSymbol.setSymbol(kdtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol32 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol32);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("dstscs");
			bindDicselectSymbol.setSymbol(dstscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol33 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol33);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("wlzltscs");
			bindDicselectSymbol.setSymbol(wlzltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol34 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol34);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("jtywtszs");
			bindDicselectSymbol.setSymbol(jtywtszs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			String uuidBol35 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol.setId(uuidBol35);
			bindDicselectSymbol.setDicselectid(uuid);
			bindDicselectSymbol.setSelectname("zzywtscs");
			bindDicselectSymbol.setSymbol(zzywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol);

			// *****************************************************************************************

		}

	}

	@Override
	public List<Map<Object, Object>> seleAnswer(String id) {
		// TODO Auto-generated method stub
		Map<Object, Object> map = null;
		Map<Object, Object> map2 = null;
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();

		DecimalFormat df = new DecimalFormat("0");
		List<KhzsRelationship> listShip = khzsRelationshipDao
				.find("from KhzsRelationship where questionnaireid = ? order by orders asc", id);
		for (int i = 0; i < listShip.size(); i++) {
			KhzsRelationship khzsRelationship = listShip.get(i);
			// 获取一共做这个题有多少人
			//List find = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ?", khzsRelationship.getQuestionid());
			List find = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ?", khzsRelationship.getId());
			Double size = (double) find.size();

			String A = "";
			String B = "";
			String C = "";
			String D = "";
			String E = "";
			String F = "";
			String G = "";
			String H = "";
			String I = "";
			String J = "";
			String K = "";
			String L = "";
			String M = "";
			String N = "";
			String O = "";
			String P = "";

			if (size != 0) {
				// 获取选择A选项有多少人
				/*List findA = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%A%')",
						khzsRelationship.getQuestionid());*/
				List findA = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%A%')",
						khzsRelationship.getId());
				Double sizeA = (double) findA.size();
				Double tjA = (double) ((sizeA / size) * 100);

				String formatA = df.format(tjA);

				A = formatA + "%";

				// 获取选择B选项有多少人
				/*List findB = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%B%')",
						khzsRelationship.getQuestionid());*/
				List findB = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%B%')",
						khzsRelationship.getId());
				Double sizeB = (double) findB.size();
				Double tjB = (double) (sizeB / size) * 100;

				String formatB = df.format(tjB);
				B = formatB + "%";

				// 获取选择C选项有多少人
				/*List findC = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%C%')",
						khzsRelationship.getQuestionid());*/
				List findC = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%C%')",
						khzsRelationship.getId());
				Double sizeC = (double) findC.size();
				Double tjC = (double) (sizeC / size) * 100;

				String formatC = df.format(tjC);
				C = formatC + "%";

				// 获取选择D选项有多少人
				/*List findD = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%D%')",
						khzsRelationship.getQuestionid());*/
				List findD = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%D%')",
						khzsRelationship.getId());
				Double sizeD = (double) findD.size();
				Double tjD = (double) (sizeD / size) * 100;
				String formatD = df.format(tjD);
				D = formatD + "%";

				// 获取选择E选项有多少人
				/*List findE = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%E%')",
						khzsRelationship.getQuestionid());*/
				List findE = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%E%')",
						khzsRelationship.getId());
				Double sizeE = (double) findE.size();
				Double tjE = (double) (sizeE / size) * 100;
				String formatE = df.format(tjE);
				E = formatE + "%";

				// 获取选择F选项有多少人
				/*List findF = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%F%')",
						khzsRelationship.getQuestionid());*/
				List findF = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%F%')",
						khzsRelationship.getId());
				Double sizeF = (double) findF.size();
				Double tjF = (double) (sizeF / size) * 100;
				String formatF = df.format(tjF);
				F = formatF + "%";

				// 获取选择G选项有多少人
				/*List findG = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%G%')",
						khzsRelationship.getQuestionid());*/
				List findG = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%G%')",
						khzsRelationship.getId());
				Double sizeG = (double) findG.size();
				Double tjG = (double) (sizeG / size) * 100;
				String formatG = df.format(tjG);
				G = formatG + "%";

				// 获取选择H选项有多少人
				/*List findH = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%H%')",
						khzsRelationship.getQuestionid());*/
				List findH = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%H%')",
						khzsRelationship.getId());
				Double sizeH = (double) findH.size();
				Double tjH = (double) (sizeH / size) * 100;
				String formatH = df.format(tjH);
				H = formatH + "%";

				// 获取选择H选项有多少人
				/*List findI = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%I%')",
						khzsRelationship.getQuestionid());*/
				List findI = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%I%')",
						khzsRelationship.getId());
				Double sizeI = (double) findI.size();
				Double tjI = (double) (sizeI / size) * 100;
				String formatI = df.format(tjI);
				I = formatI + "%";

				// 获取选择H选项有多少人
				/*List findJ = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%J%')",
						khzsRelationship.getQuestionid());*/
				List findJ = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%J%')",
						khzsRelationship.getId());
				Double sizeJ = (double) findJ.size();
				Double tjJ = (double) (sizeJ / size) * 100;
				String formatJ = df.format(tjJ);
				J = formatJ + "%";

				// 获取选择H选项有多少人
				/*List findK = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%K%')",
						khzsRelationship.getQuestionid());*/
				List findK = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%K%')",
						khzsRelationship.getId());
				Double sizeK = (double) findK.size();
				Double tjK = (double) (sizeK / size) * 100;
				String formatK = df.format(tjK);
				K = formatK + "%";

				// 获取选择H选项有多少人
				/*List findL = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%L%')",
						khzsRelationship.getQuestionid());*/
				List findL = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%L%')",
						khzsRelationship.getId());
				Double sizeL = (double) findL.size();
				Double tjL = (double) (sizeL / size) * 100;
				String formatL = df.format(tjL);
				L = formatL + "%";

				// 获取选择H选项有多少人
				/*List findM = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%M%')",
						khzsRelationship.getQuestionid());*/
				List findM = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%M%')",
						khzsRelationship.getId());
				Double sizeM = (double) findM.size();
				Double tjM = (double) (sizeM / size) * 100;
				String formatM = df.format(tjM);
				M = formatM + "%";

				// 获取选择H选项有多少人
				/*List findN = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%N%')",
						khzsRelationship.getQuestionid());*/
				List findN = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%N%')",
						khzsRelationship.getQuestionid());
				Double sizeN = (double) findN.size();
				Double tjN = (double) (sizeN / size) * 100;
				String formatN = df.format(tjN);
				N = formatN + "%";

				// 获取选择H选项有多少人
				/*List findO = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%O%')",
						khzsRelationship.getQuestionid());*/
				List findO = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%O%')",
						khzsRelationship.getId());
				Double sizeO = (double) findO.size();
				Double tjO = (double) (sizeO / size) * 100;
				String formatO = df.format(tjO);
				O = formatO + "%";

				// 获取选择H选项有多少人
				/*List findP = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%P%')",
						khzsRelationship.getQuestionid());*/
				List findP = khzsAnswerDao.find("FROM KhzsAnswer WHERE relationid = ? AND ANSWER like('%P%')",
						khzsRelationship.getId());
				Double sizeP = (double) findP.size();
				Double tjP = (double) (sizeP / size) * 100;
				String formatP = df.format(tjP);
				P = formatP + "%";

			} else {
				A = "0%";
				B = "0%";
				C = "0%";
				D = "0%";
				E = "0%";
				F = "0%";
				G = "0%";
				H = "0%";
				I = "0%";
				J = "0%";
				K = "0%";
				L = "0%";
				M = "0%";
				N = "0%";
				O = "0%";
				P = "0%";
			}

			map = new HashMap<Object, Object>();

			map.put("tjA", A);
			map.put("tjB", B);
			map.put("tjC", C);
			map.put("tjD", D);
			map.put("tjE", E);
			map.put("tjF", F);
			map.put("tjG", G);
			map.put("tjH", H);

			map.put("tjI", I);
			map.put("tjJ", J);
			map.put("tjK", K);
			map.put("tjL", L);
			map.put("tjM", M);
			map.put("tjN", N);
			map.put("tjO", O);
			map.put("tjP", P);
			map.put("myid", khzsRelationship.getQuestionid());
			list.add(map);

		}

		return list;
	}

	@Override
	public void saveMainBak(String myAsk, String myName, String userid, String username, String ispublic,
			String myNameSub, String isreward) {
		KhzsQuestionnaire KhzsQuestionnaire = new KhzsQuestionnaire();
		KhzsQuestionnaire.setId(myAsk);
		KhzsQuestionnaire.setName(myName);
		KhzsQuestionnaire.setCreattime(DateUtils.getDateStr("yyyy-MM-dd HH:mm:ss"));
		KhzsQuestionnaire.setStatus("草稿");
		KhzsQuestionnaire.setCreateby(userid);
		KhzsQuestionnaire.setCreatebyname(username);
		KhzsQuestionnaire.setIspublic(ispublic);
		KhzsQuestionnaire.setNamesub(myNameSub);
		KhzsQuestionnaire.setIsreward(isreward);
		khzsQuestionnaireDao.saveOrUpdate(KhzsQuestionnaire);
	}
	
	//群发短信
	@Override
	public String publishTep1(int countNum,String id, BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,
			BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx) {
		//短信群发接口
		/*String where=getSqlWhere(bindDicselect, bindDicselectSymbol,bindDicselectEx).get("whereCHN").toString()+"共"+countNum+"条";*/
		String where="此次调研用户筛选规则如下："+getSqlWhere(bindDicselect, bindDicselectSymbol,bindDicselectEx).get("whereCHN").toString()+",共计"+countNum+"个移动用户";
		String naireId=id;
		String daynum=bindDicSelectInfo.getDaynum();
		String messageInfo=bindDicSelectInfo.getMessage_info();
		String sqlWhere=where;
		Map<String,Object> map=IaSMSSendAction.draftWorkFlow(naireId, Integer.parseInt(daynum)+1+"", countNum+"", messageInfo, sqlWhere);
		String result=map.get("result").toString();
		if(map.get("result")!=null&&(!result.contains("ERROR"))){
			
			int executeUpdate = khzsQuestionnaireDao
				.executeUpdate("update KhzsQuestionnaire set status = '已发布',projectid = '"+result+"' where id = ? ", id);
		}	
		return result;
	}
	
	@Override
	public List<DataRow> getCountyByCity(String cityid) {
		QueryAdapter q = new QueryAdapter();
		String sql = " SELECT * FROM BS_T_SM_DICITEM where DTID = '2019082200000031' AND DIVALUE LIKE '%"+cityid+"%'";
		DataTable dt = q.executeQuery(sql);
		dt.getRowList();
		return dt.getRowList();
	}

	@Override
	public void publish(String resultPid,String id, BindDicSelectInfo bindDicSelectInfo, BindDicselect bindDicselect,
			BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx,int peopleNum,int countNum) {

		bindDicselectSymbolDao.executeUpdate("delete from BindDicselectSymbol where dicselectid =?",
				bindDicselect.getId());
		bindDicselectDao.executeUpdate("delete from BindDicselect where id =?", bindDicselect.getId());
		bindDicSelectInfoDao.executeUpdate("delete from BindDicSelectInfo where id =?", bindDicSelectInfo.getId());
		
		String uuid = UUIDGenerator.getUUIDoffSpace();

		bindDicselect.setId(uuid);
		bindDicselectDao.save(bindDicselect);

		bindDicSelectInfo.setId(uuid);
		bindDicSelectInfoDao.save(bindDicSelectInfo);
		
		BindDicselectSymbol bindDicselectSymbols=new BindDicselectSymbol();
		bindDicselectSymbols=bindDicselectSymbol;
		
		BindDicselect bindDicselects=new BindDicselect();
		bindDicselects=bindDicselect;
		
		//获取sql语句
		String where=getSqlWhere(bindDicselect, bindDicselectSymbol,bindDicselectEx).get("where").toString();
		

		//保存数据
		if(!SaveSql(bindDicselects, bindDicselectSymbols,uuid)){
			return ;
		}

		List<String> list = new ArrayList<String>();
		
		List<String> resultList = new ArrayList<String>();

		QueryAdapter q = new QueryAdapter();
		String sql = " SELECT * FROM ZL_KHZS_USER_INFO "+where;
		DataTable datatable = null;
		
		/*QueryAdapter q1 = new QueryAdapter();
		String sql1 = " SELECT count(1) FROM ZL_KHZS_USER_INFO "+where;
		DataTable datatable1 = q1.executeQuery(sql1);*/
		
		//求总量
		int dataNum = 0;
		if(countNum>0) {
			dataNum =countNum;
		}
		
		//分页查询
		for(int x=0;x<=Math.ceil(dataNum/5000);x++) {
			datatable=q.executeQuery(sql, null, x+1,5000,1);
			/*List<DataRow> rowList = datatable.getRowList();*/
			for (int i = 0; i < datatable.getRowList().size(); i++) {
				String string = datatable.getRowList().get(i).getString("RN");
				list.add(string);
			}
		}
		
		if(peopleNum!=0 && peopleNum<list.size()){
			Random random = new Random();
	        int size = list.size();
	        Set<String> totals = new HashSet<String>();
	        
	        while (totals.size() < peopleNum) {//获取peopleNum个元素
	        //随机再集合里取出元素，添加到新哈希集合
	            totals.add( list.get(random.nextInt(size)));
	        }
	        Iterator iterator = totals.iterator();
	        while (iterator.hasNext()) {
	        	String next =  (String) iterator.next();
	            resultList.add(next);
	        }
		}else{
			resultList = list;
		}
		
		

        
		//上传附件
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        /**
         * 获取到webroot里面的数据
         */
		String FILE_SEPARATOR = File.separator;
		
        java.net.URL url2 = classLoader.getResource("");
        String ROOT_CLASS_PATH = url2.getPath() + FILE_SEPARATOR;
        File rootFile = new File(ROOT_CLASS_PATH);
        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + FILE_SEPARATOR;
        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + FILE_SEPARATOR +"uploadfile";
        
        
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String format = formatter.format(date);

		String ftpPort = "21";
		String ftpPath = "ZHDY/OAID";

		String fileName = "OAID-" + resultPid + "-" + format+".txt";
		//String fileName = "OAID-4564-" + format+".txt";
		String ftpPathW = SERVLET_CONTEXT_PATH + FILE_SEPARATOR + fileName;//本地txt文件路径

		File outFile = new File(ftpPathW);

		FileWriter fwriter = null;
		try {
			if (outFile == null || !outFile.exists()) {
				outFile.createNewFile();
			} 

			String Rns = "";
			fwriter = new FileWriter(ftpPathW, true);
			
			for (int i = 0; i < resultList.size(); i++) {
				String string = resultList.get(i);
				string += "\r\n";
				Rns += string;
			}
			fwriter.write(Rns);
			fwriter.flush();
			fwriter.close();

			FtpClient ftp = new FtpClient("10.67.248.222",
					StringUtils.isBlank(ftpPort) ? 21 : Integer.parseInt(ftpPort), "sms", "dxqf147@", false, "GBK");
			boolean ftpLogin = ftp.ftpLogin();
			boolean flag = ftp.uploadFile(outFile, ftpPath);
			if (!flag) {
				try {
					throw new Exception("上传文件失败");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int executeUpdate = khzsQuestionnaireDao
					.executeUpdate("update KhzsQuestionnaire set status = '已发布',bsdicselectid = '" + uuid
							+ "',bsdicseleinfoid = '" + uuid + "',projectid = '"+resultPid+"' where id = ? ", id);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 写入txt
	 * 
	 * @param filePath
	 *            txt路径
	 * @param list
	 *            写入的数据
	 */
	public void WriteStringToFile5(String filePath, List<String> list) {
		FileWriter fwriter = null;

		try {
			// true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
			fwriter = new FileWriter(filePath, true);
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i);
				string += "\r\n";
				fwriter.write(string);

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fwriter.flush();
				fwriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 创建文件
	 * 
	 * @throws IOException
	 *             文件父路径
	 */
	public static void creatTxtFile(String realPath) {

		File file = new File(realPath + "//" + "OAID-projectID-yyyymmdd.txt");
		boolean exists = file.exists();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	@Test
	public  void test() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        /**
         * 获取到webroot里面的数据
         */
		String FILE_SEPARATOR = File.separator;
		
        java.net.URL url2 = classLoader.getResource("");
        String ROOT_CLASS_PATH = url2.getPath() + FILE_SEPARATOR;
        File rootFile = new File(ROOT_CLASS_PATH);
        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + FILE_SEPARATOR;
        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + FILE_SEPARATOR +"uploadfile";
        
        
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String format = formatter.format(date);

		String ftpPort = "21";
		String ftpPath = "ZHDY/OAID";

		String fileName = "OAID-" + "projectID" + "-" + format+".txt";
		String ftpPathW = SERVLET_CONTEXT_PATH + FILE_SEPARATOR + fileName;
	}

		@Override
		public Map<Object,Object> getTotalSendNum(BindDicselect bindDicselect, BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx,BindDicSelectInfo bindDicSelectInfo) {
			String where=getSqlWhere(bindDicselect, bindDicselectSymbol,bindDicselectEx).get("where").toString();
			QueryAdapter q1 = new QueryAdapter();
			String sql1 = " SELECT count(1) FROM ZL_KHZS_USER_INFO "+where;
			DataTable datatable1 = q1.executeQuery(sql1);
			Map<Object,Object> map = new HashMap<Object, Object>();
			//求总量
			int dataNum = 0;
			if(datatable1.length()>0) {
				DataRow row = datatable1.getDataRow(0);
				dataNum =row.getInt(0);
			}
			
			int countNum = dataNum;
			if(!(bindDicSelectInfo.getPeople_num().isEmpty()) && bindDicSelectInfo.getPeople_num()!=null){
				if(Integer.parseInt(bindDicSelectInfo.getPeople_num()) >countNum){
					map.put("people_num", null);
					map.put("countNum", countNum);
					return map;
				}else{
					map.put("people_num", Integer.parseInt(bindDicSelectInfo.getPeople_num()));
					map.put("countNum", countNum);
					return map;
				}
			}else{
				map.put("people_num", null);
				map.put("countNum", countNum);
				return map;
			}
			
			
			//return 0;
		}
		
		public boolean SaveSql(BindDicselect bindDicselect1, BindDicselectSymbol bindDicselectSymbol1,String uuid){
			String ArrSymbol = bindDicselectSymbol1.getSymbol();
			String[] split = ArrSymbol.split(", ");
			
			String yhbm = split[0];// 用户编码
			String age = split[1];// 年龄
			String rwsj = split[2];// 入网时间
			String arpu = split[3];// 近三个月月均ARPU
			String dou = split[4];// 近三个月月均DOU
			String mou = split[5];// 近三个月月均MOU
			String volte = split[6];// 近三个月月均VOLTE通话时长
			String tcbg = split[7];// 近三个月套餐变更次数
			String llxs = split[8];// 近三个月流量限速次数
			String llbh = split[9];// 当月套餐内流量饱和值
			String yyth = split[10];// 当月套餐内语音通话饱和值
			String twll = split[11];// 近三个月套外流量
			String yyfy = split[12];// 近三个月套外语音费用
			String blcs = split[13];// 近三个月营业厅办理次数
			String wtcs = split[14];// 近三个月登陆网厅次数
			String yytcs = split[15];// 近三个月登陆手机营业厅次数
			String ktsj = split[16];// 开通时间
			String dqsj = split[17];// 宽带到期时间
			String xfje = split[18];// 近三个月宽带电视消费金额
			String jtrwsj = split[19];// 所属集团客户入网时间
			String tscs = split[20];// 近三个月10086投诉次数
			String ywtscs = split[21];// 移动业务投诉次数
			String zltscs = split[22];// 移动网络质量投诉次数
			String jtywtscs = split[23];// 家庭业务投诉次数
			String kdtscs = split[24];// 家庭宽带投诉次数
			String dstscs = split[25];// 宽带电视投诉次数
			String wlzltscs = split[26];// 家庭宽带网络质量投诉次数
			String jtywtszs = split[27];// 集团业务投诉次数
			String zzywtscs = split[28];// 增值业务投诉次数

			String uuidBol1 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol1);// ID
			bindDicselectSymbol1.setDicselectid(uuid);// 筛选ID
			bindDicselectSymbol1.setSelectname("yhbm");// 筛选字段名
			bindDicselectSymbol1.setSymbol(yhbm);// 0 > 1 = 2 <
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol3 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol3);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("nl");
			bindDicselectSymbol1.setSymbol(age);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol4 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol4);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("rwsj");
			bindDicselectSymbol1.setSymbol(rwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol7 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol7);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("arpu");
			bindDicselectSymbol1.setSymbol(arpu);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol8 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol8);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("dou");
			bindDicselectSymbol1.setSymbol(dou);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol9 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol9);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("mou");
			bindDicselectSymbol1.setSymbol(mou);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol10 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol10);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("volte");
			bindDicselectSymbol1.setSymbol(volte);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol12 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol12);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("tcbg");
			bindDicselectSymbol1.setSymbol(tcbg);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			

			String uuidBol13 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol13);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("llxs");
			bindDicselectSymbol1.setSymbol(llxs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol14 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol14);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("llbh");
			bindDicselectSymbol1.setSymbol(llbh);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol15 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol15);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("yyth");
			bindDicselectSymbol1.setSymbol(yyth);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol16 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol16);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("twll");
			bindDicselectSymbol1.setSymbol(twll);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol17 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol17);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("yyfy");
			bindDicselectSymbol1.setSymbol(yyfy);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol18 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol18);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("blcs");
			bindDicselectSymbol1.setSymbol(blcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol19 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol19);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("wtcs");
			bindDicselectSymbol1.setSymbol(wtcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol20 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol20);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("yytcs");
			bindDicselectSymbol1.setSymbol(yytcs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol22 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol22);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("ktsj");
			bindDicselectSymbol1.setSymbol(ktsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol23 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol23);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("dqsj");
			bindDicselectSymbol1.setSymbol(dqsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol24 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol24);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("xfje");
			bindDicselectSymbol1.setSymbol(xfje);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol25 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol25);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("jtrwsj");
			bindDicselectSymbol1.setSymbol(jtrwsj);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol27 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol27);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("tscs");
			bindDicselectSymbol1.setSymbol(tscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol28 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol28);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("ywtscs");
			bindDicselectSymbol1.setSymbol(ywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
				
			String uuidBol29 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol29);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("zltscs");
			bindDicselectSymbol1.setSymbol(zltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol30 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol30);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("jtywtscs");
			bindDicselectSymbol1.setSymbol(jtywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol31 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol31);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("kdtscs");
			bindDicselectSymbol1.setSymbol(kdtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol32 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol32);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("dstscs");
			bindDicselectSymbol1.setSymbol(dstscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol33 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol33);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("wlzltscs");
			bindDicselectSymbol1.setSymbol(wlzltscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);

			String uuidBol34 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol34);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("jtywtszs");
			bindDicselectSymbol1.setSymbol(jtywtszs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			
			String uuidBol35 = UUIDGenerator.getUUIDoffSpace();
			bindDicselectSymbol1.setId(uuidBol35);
			bindDicselectSymbol1.setDicselectid(uuid);
			bindDicselectSymbol1.setSelectname("zzywtscs");
			bindDicselectSymbol1.setSymbol(zzywtscs);
			bindDicselectSymbolDao.save(bindDicselectSymbol1);
			return true;
		}
		
		
		public Map<String,Object> getSqlWhere(BindDicselect bindDicselect, BindDicselectSymbol bindDicselectSymbol,BindDicselectEx bindDicselectEx){
			
			String ArrSymbol = bindDicselectSymbol.getSymbol();
			String[] split = ArrSymbol.split(", ");
			String yhbm = split[0];// 用户编码
			String age = split[1];// 年龄
			String rwsj = split[2];// 入网时间
			String arpu = split[3];// 近三个月月均ARPU
			String dou = split[4];// 近三个月月均DOU
			String mou = split[5];// 近三个月月均MOU
			String volte = split[6];// 近三个月月均VOLTE通话时长
			String tcbg = split[7];// 近三个月套餐变更次数
			String llxs = split[8];// 近三个月流量限速次数
			String llbh = split[9];// 当月套餐内流量饱和值
			String yyth = split[10];// 当月套餐内语音通话饱和值
			String twll = split[11];// 近三个月套外流量
			String yyfy = split[12];// 近三个月套外语音费用
			String blcs = split[13];// 近三个月营业厅办理次数
			String wtcs = split[14];// 近三个月登陆网厅次数
			String yytcs = split[15];// 近三个月登陆手机营业厅次数
			String ktsj = split[16];// 开通时间
			String dqsj = split[17];// 宽带到期时间
			String xfje = split[18];// 近三个月宽带电视消费金额
			String jtrwsj = split[19];// 所属集团客户入网时间
			String tscs = split[20];// 近三个月10086投诉次数
			String ywtscs = split[21];// 移动业务投诉次数
			String zltscs = split[22];// 移动网络质量投诉次数
			String jtywtscs = split[23];// 家庭业务投诉次数
			String kdtscs = split[24];// 家庭宽带投诉次数
			String dstscs = split[25];// 宽带电视投诉次数
			String wlzltscs = split[26];// 家庭宽带网络质量投诉次数
			String jtywtszs = split[27];// 集团业务投诉次数
			String zzywtscs = split[28];// 增值业务投诉次数
			
			String where = " where 1 = 1 ";
			//where语句翻译为中文
			String tlate = "";
			
			//地市
			if (bindDicselect.getCity_id() != null && !(bindDicselect.getCity_id().isEmpty())) {
				where += " and CITY_ID ='" + bindDicselect.getCity_id() + "'";
				tlate += " , 地市  ='" + bindDicselectEx.getCity_idCh() + "'";
			}
			
			//区县
			if (bindDicselect.getCounty_id() != null && !(bindDicselect.getCounty_id().isEmpty())) {
				where += " and COUNTY_ID ='" + bindDicselect.getCounty_id() + "'";
				tlate += " , 区县  ='" + bindDicselectEx.getCounty_idCh() + "'";
			}
			
			String ages = "";
			if(age.equals("0")){
				ages = ">";
			}else if(age.equals("1")){
				ages = "=";
			}else{
				ages = "<";
			}
			if (bindDicselect.getAge() != null && !(bindDicselect.getAge().isEmpty())) {
				where += " and AGE "+ages+"'" + bindDicselect.getAge() + "'";
				tlate += " , 年龄  "+ages+"'" + bindDicselect.getAge() + "'";
			}
			
			String rwsjs = "";
			if(rwsj.equals("0")){
				rwsjs = ">";
			}else if(rwsj.equals("1")){
				rwsjs = "=";
			}else{
				rwsjs = "<";
			}
			if (bindDicselect.getUser_opentime() != null && !(bindDicselect.getUser_opentime().isEmpty())) {
				where += " and USER_OPENTIME "+rwsjs+"'" + bindDicselect.getUser_opentime() + "'";
				tlate += " , 入网时间  "+rwsjs+"'" + bindDicselect.getUser_opentime() + "'";
			}
			
			String arpus = "";
			if(arpu.equals("0")){
				arpus = ">";
			}else if(arpu.equals("1")){
				arpus = "=";
			}else{
				arpus = "<";
			}
			if (bindDicselect.getArpu_3month() != null && !(bindDicselect.getArpu_3month().isEmpty())) {
				where += " and ARPU_3MONTH  "+arpus+"'" + bindDicselect.getArpu_3month() + "'";
				tlate += " , 近三个月月均ARPU（元）  "+arpus+"'" + bindDicselect.getArpu_3month() + "'";
			}
			
			String dous = "";
			if(dou.equals("0")){
				dous = ">";
			}else if(dou.equals("1")){
				dous = "=";
			}else{
				dous = "<";
			}
			if (bindDicselect.getDou_3month() != null && !(bindDicselect.getDou_3month().isEmpty())) {
				where += " and DOU_3MONTH  "+dous+"'" + bindDicselect.getDou_3month() + "'";
				tlate += " , 近三个月月均DOU（M）  "+dous+"'" + bindDicselect.getDou_3month() + "'";
			}
			
			String mous = "";
			if(mou.equals("0")){
				mous = ">";
			}else if(mou.equals("1")){
				mous = "=";
			}else{
				mous = "<";
			}
			if (bindDicselect.getMou_3month() != null && !(bindDicselect.getMou_3month().isEmpty())) {
				where += " and MOU_3MONTH  "+mous+"'" + bindDicselect.getMou_3month() + "'";
				tlate += " , 近三个月月均MOU（分钟）  "+mous+"'" + bindDicselect.getMou_3month() + "'";
			}
			
			String voltes = "";
			if(volte.equals("0")){
				voltes = ">";
			}else if(volte.equals("1")){
				voltes = "=";
			}else{
				voltes = "<";
			}
			if (bindDicselect.getVolte_duration_3month() != null && !(bindDicselect.getVolte_duration_3month().isEmpty())) {
				where += " and VOLTE_DURATION_3MONTH  "+voltes+"'" + bindDicselect.getVolte_duration_3month() + "'";
				tlate += " , 近三个月月均VOLTE通话时长  "+voltes+"'" + bindDicselect.getVolte_duration_3month() + "'";
			}
			//资费套餐名称
			if (bindDicselect.getPlan_id() != null && !(bindDicselect.getPlan_id().isEmpty())) {
				where += " and PLAN_ID  "+voltes+"'" + bindDicselect.getPlan_id() + "'";
				tlate += " , 资费套餐名称 = '" + bindDicselectEx.getPlan_idCh() + "'";
			}
			
			
			
			String tcbgs = "";
			if(tcbg.equals("0")){
				tcbgs = ">";
			}else if(tcbg.equals("1")){
				tcbgs = "=";
			}else{
				tcbgs = "<";
			}
			if (bindDicselect.getPlanchng_3month() != null && !(bindDicselect.getPlanchng_3month().isEmpty())) {
				where += " and PLANCHNG_3MONTH  "+tcbgs+"'" + bindDicselect.getPlanchng_3month() + "'";
				tlate += " , 近三个月套餐变更次数  "+tcbgs+"'" + bindDicselect.getPlanchng_3month() + "'";
			}
			
			
			String llxss = "";
			if(llxs.equals("0")){
				llxss = ">";
			}else if(llxs.equals("1")){
				llxss = "=";
			}else{
				llxss = "<";
			}
			if (bindDicselect.getGprs_xs_3month() != null && !(bindDicselect.getGprs_xs_3month().isEmpty())) {
				where += " and GPRS_XS_3MONTH  "+llxss+"'" + bindDicselect.getGprs_xs_3month() + "'";
				tlate += " , 近三个月流量限速次数  "+llxss+"'" + bindDicselect.getGprs_xs_3month() + "'";
			}
			
			String llbhs = "";
			if(llbh.equals("0")){
				llbhs = ">";
			}else if(llbh.equals("1")){
				llbhs = "=";
			}else{
				llbhs = "<";
			}
			if (bindDicselect.getGprs_res_baohe() != null && !(bindDicselect.getGprs_res_baohe().isEmpty())) {
				where += " and GPRS_RES_BAOHE  "+llbhs+"'" + bindDicselect.getGprs_res_baohe() + "'";
				tlate += " , 当月套餐内流量饱和值  "+llbhs+"'" + bindDicselect.getGprs_res_baohe() + "'";
			}
			
			String yyths = "";
			if(yyth.equals("0")){
				yyths = ">";
			}else if(yyth.equals("1")){
				yyths = "=";
			}else{
				yyths = "<";
			}
			if (bindDicselect.getCall_res_baohe() != null && !(bindDicselect.getCall_res_baohe().isEmpty())) {
				where += " and CALL_RES_BAOHE "+yyths+"'" + bindDicselect.getCall_res_baohe() + "'";
				tlate += " , 当月套餐内语音通话饱和值 "+yyths+"'" + bindDicselect.getCall_res_baohe() + "'";
			}
			
			String twlls = "";
			if(twll.equals("0")){
				twlls = ">";
			}else if(twll.equals("1")){
				twlls = "=";
			}else{
				twlls = "<";
			}
			if (bindDicselect.getOver_flow_3month() != null && !(bindDicselect.getOver_flow_3month().isEmpty())) {
				where += " and OVER_FLOW_3MONTH "+twlls+"'" + bindDicselect.getOver_flow_3month() + "'";
				tlate += " , 近三个月套外流量（M） "+twlls+"'" + bindDicselect.getOver_flow_3month() + "'";
			}
			
			String yyfys = "";
			if(yyfy.equals("0")){
				yyfys = ">";
			}else if(yyfy.equals("1")){
				yyfys = "=";
			}else{
				yyfys = "<";
			}
			if (bindDicselect.getCall_duration_fee_3momth() != null
					&& !(bindDicselect.getCall_duration_fee_3momth().isEmpty())) {
				where += " and CALL_DURATION_FEE_3MOMTH  "+yyfys+"'" + bindDicselect.getCall_duration_fee_3momth() + "'";
				tlate += " , 近三个月套外语音费用（元）  "+yyfys+"'" + bindDicselect.getCall_duration_fee_3momth() + "'";
			}
			
			String blcss = "";
			if(blcs.equals("0")){
				blcss = ">";
			}else if(blcs.equals("1")){
				blcss = "=";
			}else{
				blcss = "<";
			}
			if (bindDicselect.getYyt_banli_3month() != null && !(bindDicselect.getYyt_banli_3month().isEmpty())) {
				where += " and YYT_BANLI_3MONTH  "+blcss+"'" + bindDicselect.getYyt_banli_3month() + "'";
				tlate += " , 近三个月营业厅办理次数  "+blcss+"'" + bindDicselect.getYyt_banli_3month() + "'";
			}
			
			String wtcss = "";
			if(wtcs.equals("0")){
				wtcss = ">";
			}else if(wtcs.equals("1")){
				wtcss = "=";
			}else{
				wtcss = "<";
			}
			if (bindDicselect.getWww_action_3month() != null && !(bindDicselect.getWww_action_3month().isEmpty())) {
				where += " and WWW_ACTION_3MONTH  "+wtcss+"'" + bindDicselect.getWww_action_3month() + "'";
				tlate += " , 近三个月登陆网厅次数  "+wtcss+"'" + bindDicselect.getWww_action_3month() + "'";
			}
			
			String yytcss = "";
			if(yytcs.equals("0")){
				yytcss = ">";
			}else if(yytcs.equals("1")){
				yytcss = "=";
			}else{
				yytcss = "<";
			}
			if (bindDicselect.getLogin_3month() != null && !(bindDicselect.getLogin_3month().isEmpty())) {
				where += " and LOGIN_3MONTH  "+yytcss+"'" + bindDicselect.getLogin_3month() + "'";
				tlate += " , 近三个月登陆手机营业厅次数  "+yytcss+"'" + bindDicselect.getLogin_3month() + "'";
			}
			
			String ktsjs = "";
			if(ktsj.equals("0")){
				ktsjs = ">";
			}else if(ktsj.equals("1")){
				ktsjs = "=";
			}else{
				ktsjs = "<";
			}
			if (bindDicselect.getKd_create_date() != null && !(bindDicselect.getKd_create_date().isEmpty())) {
				where += " and KD_CREATE_DATE "+ktsjs+"'" + bindDicselect.getKd_create_date() + "'";
				tlate += " , 开通时间 "+ktsjs+"'" + bindDicselect.getKd_create_date() + "'";
			}
			
			String dqsjs = "";
			if(dqsj.equals("0")){
				dqsjs = ">";
			}else if(dqsj.equals("1")){
				dqsjs = "=";
			}else{
				dqsjs = "<";
			}
			if (bindDicselect.getMax_expire_date() != null && !(bindDicselect.getMax_expire_date().isEmpty())) {
				where += " and MAX_EXPIRE_DATE  "+dqsjs+"'" + bindDicselect.getMax_expire_date() + "'";
				tlate += " , 宽带到期时间  "+dqsjs+"'" + bindDicselect.getMax_expire_date() + "'";
			}
			
			String xfjes = "";
			if(xfje.equals("0")){
				xfjes = ">";
			}else if(xfje.equals("1")){
				xfjes = "=";
			}else{
				xfjes = "<";
			}
			if (bindDicselect.getMbh_should_fee() != null && !(bindDicselect.getMbh_should_fee().isEmpty())) {
				where += " and MBH_SHOULD_FEE  "+xfjes+"'" + bindDicselect.getMbh_should_fee() + "'";
				tlate += " , 近三个月宽带电视消费金额  "+xfjes+"'" + bindDicselect.getMbh_should_fee() + "'";
			}
			
			String jtrwsjs = "";
			if(jtrwsj.equals("0")){
				jtrwsjs = ">";
			}else if(jtrwsj.equals("1")){
				jtrwsjs = "=";
			}else{
				jtrwsjs = "<";
			}
			
			if (bindDicselect.getCreate_date() != null && !(bindDicselect.getCreate_date().isEmpty())) {
				where += " and CREATE_DATE  "+jtrwsjs+"'" + bindDicselect.getCreate_date() + "'";
				tlate += " , 所属集团客户入网时间  "+jtrwsjs+"'" + bindDicselect.getCreate_date() + "'";
			}
			
			String tscss = "";
			if(tscs.equals("0")){
				tscss = ">";
			}else if(tscs.equals("1")){
				tscss = "=";
			}else{
				tscss = "<";
			}
			if (bindDicselect.getComplain_10086_counts() != null && !(bindDicselect.getComplain_10086_counts().isEmpty())) {
				where += " and COMPLAIN_10086_COUNTS  "+tscss+"'" + bindDicselect.getComplain_10086_counts() + "'";
				tlate += " and 近三个月10086投诉次数  "+tscss+"'" + bindDicselect.getComplain_10086_counts() + "'";
			}
			
			String ywtscss = "";
			if(ywtscs.equals("0")){
				ywtscss = ">";
			}else if(ywtscs.equals("1")){
				ywtscss = "=";
			}else{
				ywtscss = "<";
			}
			if (bindDicselect.getComplain_ydyw_counts() != null && !(bindDicselect.getComplain_ydyw_counts().isEmpty())) {
				where += " and COMPLAIN_YDYW_COUNTS  "+ywtscss+"'" + bindDicselect.getComplain_ydyw_counts() + "'";
				tlate += " , 其中：移动业务投诉次数  "+ywtscss+"'" + bindDicselect.getComplain_ydyw_counts() + "'";
			}
			
			String zltscss = "";
			if(zltscs.equals("0")){
				zltscss = ">";
			}else if(zltscs.equals("1")){
				zltscss = "=";
			}else{
				zltscss = "<";
			}
			if (bindDicselect.getComplain_yywl_counts() != null && !(bindDicselect.getComplain_yywl_counts().isEmpty())) {
				where += " and COMPLAIN_YYWL_COUNTS  "+zltscss+"'" + bindDicselect.getComplain_yywl_counts() + "'";
				tlate += " , 移动网络质量投诉次数  "+zltscss+"'" + bindDicselect.getComplain_yywl_counts() + "'";
			}
			
			String jtywtscss = "";
			if(jtywtscs.equals("0")){
				jtywtscss = ">";
			}else if(jtywtscs.equals("1")){
				jtywtscss = "=";
			}else{
				jtywtscss = "<";
			}
			if (bindDicselect.getComplain_jtyw_counts() != null && !(bindDicselect.getComplain_jtyw_counts().isEmpty())) {
				where += " and COMPLAIN_JTYW_COUNTS  "+jtywtscss+"'" + bindDicselect.getComplain_jtyw_counts() + "'";
				tlate += " , 家庭业务投诉次数  "+jtywtscss+"'" + bindDicselect.getComplain_jtyw_counts() + "'";
			}
			
			String kdtscss = "";
			if(kdtscs.equals("0")){
				kdtscss = ">";
			}else if(kdtscs.equals("1")){
				kdtscss = "=";
			}else{
				kdtscss = "<";
			}
			if (bindDicselect.getComplain_jtkd_counts() != null && !(bindDicselect.getComplain_jtkd_counts().isEmpty())) {
				where += " and COMPLAIN_JTKD_COUNTS "+kdtscss+"'" + bindDicselect.getComplain_jtkd_counts() + "'";
				tlate += " , 家庭宽带投诉次数 "+kdtscss+"'" + bindDicselect.getComplain_jtkd_counts() + "'";
			}
			
			String dstscss = "";
			if(dstscs.equals("0")){
				dstscss = ">";
			}else if(dstscs.equals("1")){
				dstscss = "=";
			}else{
				dstscss = "<";
			}
			if (bindDicselect.getComplain_jtds_counts() != null && !(bindDicselect.getComplain_jtds_counts().isEmpty())) {
				where += " and COMPLAIN_JTDS_COUNTS  "+dstscss+"'" + bindDicselect.getComplain_jtds_counts() + "'";
				tlate += " , 宽带电视投诉次数  "+dstscss+"'" + bindDicselect.getComplain_jtds_counts() + "'";
			}
			
			String wlzltscss = "";
			if(wlzltscs.equals("0")){
				wlzltscss = ">";
			}else if(wlzltscs.equals("1")){
				wlzltscss = "=";
			}else{
				wlzltscss = "<";
			}
			if (bindDicselect.getComplain_jtwl_counts() != null && !(bindDicselect.getComplain_jtwl_counts().isEmpty())) {
				where += " and COMPLAIN_JTWL_COUNTS  "+wlzltscss+"'" + bindDicselect.getComplain_jtwl_counts() + "'";
				tlate += " , 家庭宽带网络质量投诉次数  "+wlzltscss+"'" + bindDicselect.getComplain_jtwl_counts() + "'";
			}
			
			String jtywtszss = "";
			if(jtywtszs.equals("0")){
				jtywtszss = ">";
			}else if(jtywtszs.equals("1")){
				jtywtszss = "=";
			}else{
				jtywtszss = "<";
			}
			if (bindDicselect.getComplain_jt_counts() != null && !(bindDicselect.getComplain_jt_counts().isEmpty())) {
				where += " and COMPLAIN_JT_COUNTS  "+jtywtszss+"'" + bindDicselect.getComplain_jt_counts() + "'";
				tlate += " , 集团业务投诉次数  "+jtywtszss+"'" + bindDicselect.getComplain_jt_counts() + "'";
			}
			
			String zzywtscss = "";
			if(zzywtscs.equals("0")){
				zzywtscss = ">";
			}else if(zzywtscs.equals("1")){
				zzywtscss = "=";
			}else{
				zzywtscss = "<";
			}
			if (bindDicselect.getComplain_zz_counts() != null && !(bindDicselect.getComplain_zz_counts().isEmpty())) {
				where += " and COMPLAIN_ZZ_COUNTS  "+zzywtscss+"'" + bindDicselect.getComplain_zz_counts() + "'";
				tlate += " , 增值业务投诉次数  "+zzywtscss+"'" + bindDicselect.getComplain_zz_counts() + "'";
			}

			
			//区域（城镇/农村）
			if (bindDicselect.getRegion_id1() != null && !(bindDicselect.getRegion_id1().isEmpty())) {
				where += " and REGION_ID1 ='" + bindDicselect.getRegion_id1() + "'";
				tlate += " , 区域（城镇/农村） ='" + bindDicselectEx.getRegion_id1Ch() + "'";
			}
			//星级
			if (bindDicselect.getStar_level() != null && !(bindDicselect.getStar_level().isEmpty())) {
				where += " and STAR_LEVEL = '" + bindDicselect.getStar_level() + "'";
				tlate += " , 星级  ='" + bindDicselectEx.getStar_levelCh() + "'";
			}
			
			
			//是否双卡客户
			
			if (bindDicselect.getIs_2card() != null && !(bindDicselect.getIs_2card().isEmpty())) {
				where += " and IS_2CARD ='" + bindDicselect.getIs_2card() + "'";
				
				String skkh = "";
				if(bindDicselect.getIs_2card().equals("1")){
					skkh = "是";
				}else{
					skkh = "否";
				}
				tlate += " , 是否双卡客户 ：'" + skkh + "'";
			}
			//是否主副卡客户
			
			if (bindDicselect.getIs_zhufu_card() != null && !(bindDicselect.getIs_zhufu_card().isEmpty())) {
				where += " and IS_ZHUFU_CARD ='" + bindDicselect.getIs_zhufu_card() + "'";
				String zfkkh = "";
				if(bindDicselect.getIs_zhufu_card().equals("1")){
					zfkkh = "是";
				}else{
					zfkkh = "否";
				}
				tlate += " , 是否主副卡客户： '" + zfkkh + "'";
			}
			//是否国内漫游客户
			
			if (bindDicselect.getRom_in_chi_mark() != null && !(bindDicselect.getRom_in_chi_mark().isEmpty())) {
				where += " and ROM_IN_CHI_MARK ='" + bindDicselect.getRom_in_chi_mark() + "'";
				String mykh = "";
				if(bindDicselect.getRom_in_chi_mark().equals("1")){
					mykh = "是";
				}else{
					mykh = "否";
				}
				tlate += " , 是否国内漫游客户： '" + mykh + "'";
			}
			//是否国际漫游客户
			if (bindDicselect.getRom_in_earth_mark() != null && !(bindDicselect.getRom_in_earth_mark().isEmpty())) {
				where += " and ROM_IN_EARTH_MARK ='" + bindDicselect.getRom_in_earth_mark() + "'";
				String gjmykh = "";
				if(bindDicselect.getRom_in_earth_mark().equals("1")){
					gjmykh = "是";
				}else{
					gjmykh = "否";
				}
				tlate += " , 是否国际漫游客户：'" + gjmykh + "'";
			}
			//是否校园客户
			if (bindDicselect.getSchool_flag() != null && !(bindDicselect.getSchool_flag().isEmpty())) {
				where += " and SCHOOL_FLAG ='" + bindDicselect.getSchool_flag() + "'";
				String xykh = "";
				if(bindDicselect.getSchool_flag().equals("1")){
					xykh = "是";
				}else{
					xykh = "否";
				}
				tlate += " , 是否校园客户：'" + xykh + "'";
			}
			//是否全球通客户
			if (bindDicselect.getIs_world() != null && !(bindDicselect.getIs_world().isEmpty())) {
				where += " and IS_WORLD ='" + bindDicselect.getIs_world() + "'";
				String qqtkh = "";
				if(bindDicselect.getIs_world().equals("1")){
					qqtkh = "是";
				}else{
					qqtkh = "否";
				}
				tlate += " , 是否全球通客户 ：'" + qqtkh + "'";
			}
			//是否开通VOLTE功能
			if (bindDicselect.getIs_offer() != null && !(bindDicselect.getIs_offer().isEmpty())) {
				where += " and IS_OFFER ='" + bindDicselect.getIs_offer() + "'";
				String ktvolte = "";
				if(bindDicselect.getIs_offer().equals("1")){
					ktvolte = "是";
				}else{
					ktvolte = "否";
				}
				tlate += " , 是否开通VOLTE功能 ：'" + ktvolte + "'";
			}
			
			//手机终端品牌
			if (bindDicselect.getProvider_name() != null && !(bindDicselect.getProvider_name().isEmpty())) {
				where += " and PROVIDER_NAME ='" + bindDicselect.getProvider_name() + "'";
				
				tlate += " , 手机终端品牌 ='" + bindDicselectEx.getProvider_nameCh() + "'";
			}
			
			//手机终端型号
			if (bindDicselect.getTerm_name() != null && !(bindDicselect.getTerm_name().isEmpty())) {
				where += " and TERM_NAME ='" + bindDicselect.getTerm_name() + "'";
				
				tlate += " , 手机终端型号 ='" + bindDicselectEx.getTerm_nameCh() + "'";
			}
			
			
			//欠费标识
			if (bindDicselect.getOwe_mark() != null && !(bindDicselect.getOwe_mark().isEmpty())) {
				where += " and OWE_MARK ='" + bindDicselect.getOwe_mark() + "'";
				String qfbs = "";
				if(bindDicselect.getOwe_mark().equals("1")){
					qfbs = "是";
				}else{
					qfbs = "否";
				}
				tlate += " , 欠费标识 ：'" + qfbs + "'";
			}
			//是否宽带客户
			if (bindDicselect.getIs_kd() != null && !(bindDicselect.getIs_kd().isEmpty())) {
				where += " and IS_KD ='" + bindDicselect.getIs_kd() + "'";
				String kdkh = "";
				if(bindDicselect.getIs_kd().equals("1")){
					kdkh = "是";
				}else{
					kdkh = "否";
				}
				tlate += " , 是否宽带客户 ：'" + kdkh + "'";
			}
			//宽带套餐名称
			if (bindDicselect.getOffer_name() != null && !(bindDicselect.getOffer_name().isEmpty())) {
				where += " and OFFER_NAME ='" + bindDicselect.getOffer_name() + "'";
				tlate += " , 宽带套餐名称  ='" + bindDicselectEx.getOffer_nameCh() + "'";
			}
			//是否融合套餐
			if (bindDicselect.getIs_fuse() != null && !(bindDicselect.getIs_fuse().isEmpty())) {
				where += " and IS_FUSE ='" + bindDicselect.getIs_fuse() + "'";
				String rhtc = "";
				if(bindDicselect.getIs_fuse().equals("1")){
					rhtc = "是";
				}else{
					rhtc = "否";
				}
				tlate += " , 是否融合套餐 ：'" + rhtc + "'";
			}
			
			
			//宽带带宽
			if (bindDicselect.getProd_bandwidth() != null && !(bindDicselect.getProd_bandwidth().isEmpty())) {
				where += " and PROD_BANDWIDTH ='" + bindDicselect.getProd_bandwidth() + "'";
				
				tlate += " , 宽带带宽 ='" + bindDicselectEx.getProd_bandwidthCh() + "'";
			}
			
			
			//是否宽带电视客户
			if (bindDicselect.getIs_kdds() != null && !(bindDicselect.getIs_kdds().isEmpty())) {
				where += " and IS_KDDS ='" + bindDicselect.getIs_kdds() + "'";
				String kdds = "";
				if(bindDicselect.getIs_kdds().equals("1")){
					kdds = "是";
				}else{
					kdds = "否";
				}
				tlate += " , 是否宽带电视客户：'" + kdds + "'";
			}
			//是否集团客户成员
			if (bindDicselect.getIs_group() != null && !(bindDicselect.getIs_group().isEmpty())) {
				where += " and IS_GROUP ='" + bindDicselect.getIs_group() + "'";
				String jtkhcy = "";
				if(bindDicselect.getIs_group().equals("1")){
					jtkhcy = "是";
				}else{
					jtkhcy = "否";
				}
				tlate += " , 是否集团客户成员 ：'" + jtkhcy + "'";
			}
			//是否集团客户关键人
			if (bindDicselect.getIs_group_keyper() != null && !(bindDicselect.getIs_group_keyper().isEmpty())) {
				where += " and IS_GROUP_KEYPER ='" + bindDicselect.getIs_group_keyper() + "'";
				String jtkhgjr = "";
				if(bindDicselect.getIs_group_keyper().equals("1")){
					jtkhgjr = "是";
				}else{
					jtkhgjr = "否";
				}
				tlate += " , 是否集团客户关键人 ：'" + jtkhgjr + "'";
			}
			//是否集团客户联系人
			if (bindDicselect.getIs_group_linker() != null && !(bindDicselect.getIs_group_linker().isEmpty())) {
				where += " and IS_GROUP_LINKER ='" + bindDicselect.getIs_group_linker() + "'";
				String jtkhlxr = "";
				if(bindDicselect.getIs_group_linker().equals("1")){
					jtkhlxr = "是";
				}else{
					jtkhlxr = "否";
				}
				tlate += " , 是否集团客户联系人 ：'" + jtkhlxr + "'";
			}
			//是否集团产品使用人
			if (bindDicselect.getIs_group_order() != null && !(bindDicselect.getIs_group_order().isEmpty())) {
				where += " and IS_GROUP_ORDER ='" + bindDicselect.getIs_group_order() + "'";
				String jtcpsyr = "";
				if(bindDicselect.getIs_group_order().equals("1")){
					jtcpsyr = "是";
				}else{
					jtcpsyr = "否";
				}
				tlate += " , 是否集团产品使用人：'" + jtcpsyr + "'";
			}
			//行业类型
			if (bindDicselect.getGroup_industry_id() != null && !(bindDicselect.getGroup_industry_id().isEmpty())) {
				where += " and GROUP_INDUSTRY_ID = '" + bindDicselect.getGroup_industry_id() + "'";
				tlate += " , 行业类型  ='" + bindDicselectEx.getGroup_industry_idCh() + "'";
				
			}
			
			
			//集团客户等级
			if (bindDicselect.getGroup_level_id() != null && !(bindDicselect.getGroup_level_id().isEmpty())) {
				where += " and GROUP_LEVEL_ID = '" + bindDicselect.getGroup_level_id() + "'";
				tlate += " , 集团客户等级  ='" + bindDicselectEx.getGroup_level_idCh() + "'";
				
			}
			//是否集团专线客户
			if (bindDicselect.getIs_group_zx() != null && !(bindDicselect.getIs_group_zx().isEmpty())) {
				where += " and IS_GROUP_ZX ='" + bindDicselect.getIs_group_zx() + "'";
				String jtzxkh = "";
				if(bindDicselect.getIs_group_zx().equals("1")){
					jtzxkh = "是";
				}else{
					jtzxkh = "否";
				}
				tlate += " , 是否集团专线客户 ：'" + jtzxkh + "'";
			}
			//是否企业宽带客户
			if (bindDicselect.getIs_qykd() != null && !(bindDicselect.getIs_qykd().isEmpty())) {
				where += " and IS_QYKD ='" + bindDicselect.getIs_qykd() + "'";
				String qykdkh = "";
				if(bindDicselect.getIs_qykd().equals("1")){
					qykdkh = "是";
				}else{
					qykdkh = "否";
				}
				tlate += " , 是否企业宽带客户 ：'" + qykdkh + "'";
			}
			//是否集团V网客户
			if (bindDicselect.getIs_group_vpmn() != null && !(bindDicselect.getIs_group_vpmn().isEmpty())) {
				where += " and IS_GROUP_VPMN ='" + bindDicselect.getIs_group_vpmn() + "'";
				String jtvwkh = "";
				if(bindDicselect.getIs_group_vpmn().equals("1")){
					jtvwkh = "是";
				}else{
					jtvwkh = "否";
				}
				tlate += " , 是否集团V网客户 ：'" + jtvwkh + "'";
			}
			//是否IDC客户
			if (bindDicselect.getIs_idc() != null && !(bindDicselect.getIs_idc().isEmpty())) {
				where += " and IS_IDC ='" + bindDicselect.getIs_idc() + "'";
				String idc = "";
				if(bindDicselect.getIs_idc().equals("1")){
					idc = "是";
				}else{
					idc = "否";
				}
				tlate += " , 是否IDC客户 ：'" + idc + "'";
			}
			//是否IMS电话客户
			if (bindDicselect.getIs_ims() != null && !(bindDicselect.getIs_ims().isEmpty())) {
				where += " and IS_IMS ='" + bindDicselect.getIs_ims() + "'";
				String ims = "";
				if(bindDicselect.getIs_ims().equals("1")){
					ims = "是";
				}else{
					ims = "否";
				}
				tlate += " , 是否IMS电话客户 ：'" + ims + "'";
				
			}
			//是否开通VOLTE
			if (bindDicselect.getIs_ofer1() != null && !(bindDicselect.getIs_ofer1().isEmpty())) {
				where += " and IS_OFER1 ='" + bindDicselect.getIs_ofer1() + "'";
				String ktvolte = "";
				if(bindDicselect.getIs_ofer1().equals("1")){
					ktvolte = "是";
				}else{
					ktvolte = "否";
				}
				tlate += " , 是否开通VOLTE：" + ktvolte + "'";
			}
			//近三月是否有VOLTE通信
			if (bindDicselect.getIs_volte_3month() != null && !(bindDicselect.getIs_volte_3month().isEmpty())) {
				where += " and IS_VOLTE_3MONTH ='" + bindDicselect.getIs_volte_3month() + "'";
				String sgyvolte = "";
				if(bindDicselect.getIs_volte_3month().equals("1")){
					sgyvolte = "是";
				}else{
					sgyvolte = "否";
				}
				tlate += " , 近三月是否有VOLTE通信 ：" + sgyvolte + "'";
			}
			
			//去掉第一个逗号并在末尾加上句号
			String subTlate="";
			if(!(tlate.isEmpty())&&tlate!=null){
				 subTlate = tlate.substring(2,tlate.length());
				
				 subTlate+="。";
			}
			
			
			System.out.println(where);
			
			System.out.println(subTlate);
			
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("where", where);
			map.put("whereCHN", subTlate);
			return map;
		}
		
		
		@Override
	    public Workbook checkVali(File xls) {
	        Workbook wb=null;
	            try {
	                wb =WorkbookFactory.create(new FileInputStream(xls));
//	                Sheet sheet = wb.getSheetAt(0);

	            } catch (ExcelTemplateException e) {
	                throw e;
	            } catch (Exception e) {
	                e.printStackTrace();
	                throw new ExcelTemplateException("导入数据格式或模板不正确！");
	            }
	        return wb;
	    }
		
		
		 @Override 
		    public void importXls(Workbook wb, String projectId,String fileName)throws Exception {
			 
			 ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			 /**
		         * 获取到webroot里面的数据
		         */
			 	//查找本地文件“uploadfile”的路径1
				String FILE_SEPARATOR = File.separator;
		        java.net.URL url2 = classLoader.getResource("");
		        String ROOT_CLASS_PATH = url2.getPath() + FILE_SEPARATOR;
		        File rootFile = new File(ROOT_CLASS_PATH);
		        String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + FILE_SEPARATOR;
		        File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
		        String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + FILE_SEPARATOR +"uploadfile";
		        
		        
		        
		      //查找本地文件“uploadfile”的路径2
		       /* int num=SERVLET_CONTEXT_PATH.indexOf(".metadata");
		        String substring = SERVLET_CONTEXT_PATH.substring(0, num);
		        String CONTEXT_PATH = substring + "lnkhzs" +FILE_SEPARATOR+ "WebRoot" + FILE_SEPARATOR +"uploadfile"+FILE_SEPARATOR+fileName;
		      
		        File filecON = new File(CONTEXT_PATH);
		 		if (!filecON.exists()) {
		 			filecON.createNewFile();
		 		}*/
		        String ftpPort = "21";
				String ftpPath = "ZHDY/OANM";
		        
		        Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String format = formatter.format(date);
		 		
				//文件名字
				String fileNameAg = "OANM-" + projectId + "-" + format+".txt";
		 		
				
				Sheet sheet=wb.getSheetAt(0);
		        //获取excel行
		        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		        
		        
		        if(physicalNumberOfRows!=0){
		        	//获取excel列数
				       int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
				        
				
		         String filenameTemp = SERVLET_CONTEXT_PATH+ FILE_SEPARATOR +fileNameAg ;//本地文件路径
		         File outFile = new File(filenameTemp);
		         FileWriter fwriter = null;
		         try {
		 			if (outFile == null || !outFile.exists()) {
		 				//创建文件
		 				outFile.createNewFile();
		 			} 

		 			String Rns = "";
		 			fwriter = new FileWriter(filenameTemp, false);
		 			
		 			//行
			        for (int j = 0; j < physicalNumberOfRows; j++) {
			        		//写入数据
			        	//列
				        for (int i = 0; i < physicalNumberOfCells; i++) {
				        	Row row = sheet.getRow(j);
				            if(row == null){
				                break;
				            }
				            	UUIDGenerator.getUUIDoffSpace();
				            	String string = ExcelUtils.getString(row, i);
				            	if(string == null || string.trim().isEmpty()){
				            		continue;
				            	}
				            	string += "\r\n";
				 				Rns += string;
						}
					}
			        Rns = Rns + "18842469972" + "\r\n" + "18204011673" + "\r\n";
			        fwriter.write(Rns);
		 			fwriter.flush();
		 			fwriter.close();
		 			//18842469972   18204011673
		 			FtpClient ftp = new FtpClient("10.67.248.222",
							StringUtils.isBlank(ftpPort) ? 21 : Integer.parseInt(ftpPort), "sms", "dxqf147@", false, "UTF-8");
					boolean ftpLogin = ftp.ftpLogin();
					boolean flag = ftp.uploadFile(outFile, ftpPath);
					if (!flag) {
							throw new Exception("上传文件失败");
					}
		 			

		 		} catch (IOException e) {
		 			e.printStackTrace();
		 		} 
		    }
		 }		 
		 
	//获取上传文件数量
	/*@Override
	public String getFileNum(Workbook wb) {
		String msg = "";
		Sheet sheet = wb.getSheetAt(0);

 				int last=sheet.getLastRowNum();
				// 获取excel行                                                                                                                               
				int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
				for(int i=last;i>-1;i--){
					String cell=ExcelUtils.getString(sheet.getRow(i), 0);sheet.getRow(i).getCell(0).getNumericCellValue();
					if(cell==null||cell.trim().isEmpty()||cell.equals("null")){
						physicalNumberOfRows--;
					}else{
						break;
					}
				}
		
		
		//校验手机号是不是11位
		if (physicalNumberOfRows != 0) {
			// 行
			int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
			for (int j = 0; j < physicalNumberOfRows; j++) {
				// 列
				for (int i = 0; i < physicalNumberOfCells; i++) {
					Row row = sheet.getRow(j);
					if (row == null) {
						msg = "error";
						break;
					}
					String string = ExcelUtils.getString(row, i);
					if(string==null||string.trim().isEmpty()||string.equals("null")){
						msg = "error";
						break;
					}else{
						String trim = string.trim();
						int length = trim.length();
						if (length != 11) {
							msg = "error";
							break;
						}
					}
					
				}
			}
		}
		if (msg.equals("error")) {
			return "error";
		} else {
			return String.valueOf(physicalNumberOfRows);
		}
	}*/
	
	
	//获取上传文件数量
		@Override
		public String getFileNum(Workbook wb) {
			String msg = "";
			String msgDetail="";
			Sheet sheet = wb.getSheetAt(0);

	 				int last=sheet.getLastRowNum();
					// 获取excel行                                                                                                                               
					int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
					for(int i=last;i>-1;i--){
						String cell=ExcelUtils.getString(sheet.getRow(i), 0);/*sheet.getRow(i).getCell(0).getNumericCellValue();*/
						if(cell==null||cell.trim().isEmpty()||cell.equals("null")){
							physicalNumberOfRows--;
						}else{
							break;
						}
					}
			
			
			//校验手机号是不是11位
			if (physicalNumberOfRows != 0) {
				// 行
				int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
				for (int j = 0; j < physicalNumberOfRows; j++) {
					// 列
					for (int i = 0; i < physicalNumberOfCells; i++) {
						Row row = sheet.getRow(j);
						if (row == null) {
							msg = "error";
							msgDetail+="第"+(j+1)+"行数据为空，";
							break;
						}
						String string = ExcelUtils.getString(row, i);
						if(string==null||string.trim().isEmpty()||string.equals("null")){
							msg = "error";
							msgDetail+="第"+(j+1)+"行数据为空，";
							break;
						}else{
							String trim = string.trim();
							int length = trim.length();
							if (length != 11) {
								msg = "error";
								msgDetail+="第"+(j+1)+"行号码位数错误，";
								break;
							}else if(!trim.matches("^[0-9]*$")){
								msg = "error";
								msgDetail+="第"+(j+1)+"行号码存在特殊字符，";
								break;
							}
						}
						
					}
				}
			}
			if (msg.equals("error")) {
				msgDetail=msgDetail.substring(0, msgDetail.length()-1);
				return msg+msgDetail;
			} else {
				return String.valueOf(physicalNumberOfRows);
			}
		}
		
		
	
	//群发短信
		@Override
		public String getInterface(int countNum,String id,String rule,String daynum,String message_info) {
			//短信群发接口
			/*String where=rule+"共"+countNum+"条";*/
			String where="此次调研用户筛选规则如下："+rule+",共计"+countNum+"个移动用户";
			String naireId=id;
			String sqlWhere=where;
			String messageInfo=message_info;
			Map<String,Object> map=IaSMSSendAction.draftWorkFlowFile(naireId, Integer.parseInt(daynum)+1+"", countNum+"", messageInfo, sqlWhere);
			String result="";
			if(map.get("result")!=null&&(!result.contains("ERROR"))){
				result=map.get("result").toString();
				int executeUpdate = khzsQuestionnaireDao
					.executeUpdate("update KhzsQuestionnaire set status = '已发布',projectid = '"+result+"' where id = ? ", id);
			}	
			return result;
		}

		@Override
		public String exportExcel(String id) {
			QueryAdapter q = new QueryAdapter();
			String sql = "select *  from ZL_KHZS_QUESTION_ALL a,ZL_KHZS_RELATIONSHIP b where \n" + 
					"b.questionid =a.id and\n" + 
					"b.questionnaireid ='"+id+"'\n" + 
					"order by b.orders";
			DataTable dt = q.executeQuery(sql);
			List<DataRow> l = dt.getRowList();
			
			
			String sql1 = "select ''''||b.id||''' as 第'||b.orders||'题,' ASD from ZL_KHZS_QUESTION_ALL a,ZL_KHZS_RELATIONSHIP b where \n" + 
					"b.questionid =a.id and\n" + 
					"b.questionnaireid ='"+id+"'\n" + 
					"order by b.orders";
			DataTable dt1 = q.executeQuery(sql1);
			List<DataRow> l1 = dt1.getRowList();
			
			String sql2 = "select * from  (select answer,relationid,userid from ZL_KHZS_ANSWER where questionnaireid ='"+id+"')  \n" + 
					"pivot (max(answer) for relationid in (";
			
			for(int i = 0;i<l1.size();i++) {
				sql2 = sql2 + l1.get(i).getString("ASD");
				
			}
			sql2 = sql2.substring(0, sql2.length() -1);
			sql2 = sql2 + "))";
			
			DataTable dt2 = q.executeQuery(sql2);
			List<DataRow> lcs = dt2.getRowList();
			
			
			//临时文件-----------------------------
			//sql = "select *  from CS";
			//DataTable cs = q.executeQuery(sql);
			//List<DataRow> lcs = cs.getRowList();
			//----------------------------------
			List<String> question = new ArrayList<String>();//标题
			question.add("用户ID");
			question.add("是否完成问卷");
			for(int i = 0;i<l.size();i++) {
				question.add(l.get(i).getString("QUESTION"));
			}
			String rootPath = SwfuploadUtil.pathProcess(PropertiesUtils.getProperty("attachment.path"));
			String fileName ="asd"+UUIDGenerator.getUUIDoffSpace() + ".xlsx";
		    try {
		    	File file = new File(rootPath+File.separator+fileName);
		    	if (!file.exists()) {
					file.createNewFile();
				}else{
					file.delete();
					file.createNewFile();
				}
		    	FileOutputStream fileOut = new FileOutputStream(file);
		    	XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet("0");
				sheet.setVerticallyCenter(true);
				XSSFRow titleRow = sheet.createRow(0);
				for (int k = 0; k < question.size(); k++) {
					XSSFCell cell = titleRow.createCell(k);
					cell.setCellValue(question.get(k));
					//设置自动列宽
					sheet.setColumnWidth(k, 10*256);
				}
				int startRow = 1;
				for(int i = 0; i < lcs.size(); i++) {
					XSSFRow formsRow = sheet.createRow(startRow++);
					for (int j = 0; j < lcs.get(i).length()+1; j++) {
						XSSFCell cell = formsRow.createCell(j);
						if(j == 0) {
							String str = lcs.get(i).getString("USERID");
							System.out.println(str);
							//cell.setCellValue(DESUtil.decrypt("sxit_zhdy", str));
							cell.setCellValue(URLEncoder.encode(str,"UTF-8"));
						}else if(j==1){
							cell.setCellValue("是");
						}else {
							String str = lcs.get(i).getString("第"+(j-1)+"题");
							String aa[] = str.split(",");
							String result = "";
							for(int k = 0;k<aa.length;k++) {
								if(aa[k].contains("A")) {
									result += l.get(j-2).getString("OPTIONA")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONA"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("B")) {
									result += l.get(j-2).getString("OPTIONB")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONB"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("C")) {
									result += l.get(j-2).getString("OPTIONC")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONC"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("D")) {
									result += l.get(j-2).getString("OPTIOND")+",";
									//cell.setCellValue(l.get(i).getString("OPTIOND"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("E")) {
									result += l.get(j-2).getString("OPTIONE")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONE"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("F")) {
									result += l.get(j-2).getString("OPTIONF")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONF"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("G")) {
									result += l.get(j-2).getString("OPTIONG")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONG"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("H")) {
									result += l.get(j-2).getString("OPTIONH")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONH"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("I")) {
									result += l.get(j-2).getString("OPTIONI")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONI"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("J")) {
									result += l.get(j-2).getString("OPTIONJ")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONJ"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("K")) {
									result += l.get(j-2).getString("OPTIONK")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONK"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("L")) {
									result += l.get(j-2).getString("OPTIONL")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONL"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("M")) {
									result += l.get(j-2).getString("OPTIONM")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONM"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("N")) {
									result += l.get(j-2).getString("OPTIONN")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONN"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("O")) {
									result += l.get(j-2).getString("OPTIONO")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONO"));
									//sheet.setColumnWidth(j, 10*256);
								}else if(aa[k].contains("P")) {
									result += l.get(j-2).getString("OPTIONP")+",";
									//cell.setCellValue(l.get(i).getString("OPTIONP"));
									//sheet.setColumnWidth(j, 10*256);
								}else {
									result += lcs.get(i).getString("第"+(j-1)+"题")+",";
									//cell.setCellValue(lcs.get(i).getString("第"+(j-1)+"题"));
									//sheet.setColumnWidth(j, 10*256);
								}
							}
							cell.setCellValue(result);
							sheet.setColumnWidth(j, 10*256);
						}
						
					}
				}
				
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
		    }catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println(e.getMessage());
		    }
			
			return rootPath+"/"+fileName;
		}
}