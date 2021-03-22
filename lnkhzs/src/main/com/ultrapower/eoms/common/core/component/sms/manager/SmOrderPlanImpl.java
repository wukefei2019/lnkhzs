package com.ultrapower.eoms.common.core.component.sms.manager;

import java.util.ArrayList;
import java.util.List;
import com.ultrapower.eoms.RecordLog;
import com.ultrapower.eoms.common.core.component.data.DataAdapter;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.sms.model.SmsOrderPlan;
import com.ultrapower.eoms.common.core.component.sms.service.InsideSmsService;
import com.ultrapower.eoms.common.core.component.sms.service.SmOrderPlanService;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.support.PageLimit;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;

/**
 * @author zhuzhaohui E-mail:zhuzhaohui@ultrapower.com.cn
 * @version 2010-8-30 上午10:15:53
 */
public class SmOrderPlanImpl implements SmOrderPlanService{

	private IDao<SmsOrderPlan> smOrderPlanDao;
	private InsideSmsService insidesm;
	QueryAdapter query = new QueryAdapter();
	DataAdapter dataAdapter = new DataAdapter();
	
	public boolean addOrderInfo(SmsOrderPlan smsOrderPlan) {
		boolean flag = false;
		try{
			smOrderPlanDao.save(smsOrderPlan);
			flag = true;
		}catch(Exception e){
			RecordLog.printLog("计划管理短信订阅:添加订阅信息出错!"+e.getMessage(), RecordLog.LOG_LEVEL_ERROR);
		}
		return flag;
	}

	public void deleteOrderInfo(String id) {
		try{
			smOrderPlanDao.removeById(id);
		}catch(Exception e){
			RecordLog.printLog("计划管理短信订阅:删除订阅信息出错!pid="+id+","+e.getMessage(), RecordLog.LOG_LEVEL_ERROR);
		}
	}

	public List<SmsOrderPlan> get() {
		List<SmsOrderPlan> smsOrderPlanList = new ArrayList<SmsOrderPlan>();
		PageLimit pageLimit = PageLimit.getInstance();
		String hql = " from SmsOrderPlan";
		smsOrderPlanList = smOrderPlanDao.pagedQuery(hql, pageLimit);
		return smsOrderPlanList;
	}

	public SmsOrderPlan get(String id) {
		SmsOrderPlan smsOrderPlan = null;
		smsOrderPlan = smOrderPlanDao.get(id);
		return smsOrderPlan;
	}

	public boolean updateOrderInfo(SmsOrderPlan smsOrderPlan) {
		boolean flag = false;
		try{
			smOrderPlanDao.saveOrUpdate(smsOrderPlan);
			flag = true;
		}catch(Exception e){
			RecordLog.printLog("计划管理短信订阅:更新订阅信息出错!pid="+smsOrderPlan.getPid()+","+e.getMessage(), RecordLog.LOG_LEVEL_ERROR);
		}
		return flag;
	}

	/**
	 * 作业计划短信订阅 根据用户订阅的条件将满足条件的信息插入到短信表(bs_t_sm_smsmonitor)中
	 */
	public void insertSmsMonitor() {
		try {
			insidesm = (InsideSmsService) WebApplicationManager.getBean("insidesm");
			// 判断当天是否是节假日
			boolean isHoliday = false;
			DataTable dt = query.executeQuery("select * from BS_T_SM_HOLIDAY t where t.hideflag = ? and t.holidayFlag = ? and t.dateinfo = ?", 0, 0L, TimeUtils.getCurrentDate("yyyy-MM-dd"));
			if (dt != null && dt.length() > 0) {
				isHoliday = true;
			}
			dt = query.executeQuery("select distinct t.*, u.loginname, u.mobile from bs_t_task_note t, bs_t_sm_user u, bs_t_sm_smsorderplan s " +
					"where t.userid = u.pid and u.loginname = s.loginname and t.informtype = 4  and t.active = 1 and u.status = 1 " +
					"and t.bookingtype = ? and t.state = ?", 5, 2);
			if (dt != null && dt.length() > 0) {
				this.subscribe(dt, isHoliday);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 往短信发送表添加数据
	 * @param dt
	 * @param isHoliday 是否节假日
	 * @throws Exception
	 */
	private void subscribe(DataTable dt, boolean isHoliday) throws Exception {

		String loginname;//用户登录名
		String content = null;//短信内容
		String goal = null;//手机号码
		DataRow dr;
		String upSql = "update BS_T_TASK_NOTE set state = 1 where pid = ? ";
		boolean opflag;// 短信是否发送成功
		boolean noSend = true;// 短信是否已经发送过
		DataTable pdt = null;
		DataRow pdr = null;
		String HHmm = TimeUtils.getCurrentDate("HH:mm");
		for (int i = 0; i < dt.length(); i++) {
			noSend = true;
			dr = dt.getDataRow(i);
			loginname = dr.getString("loginname");
			goal = dr.getString("mobile");
			content = dr.getString("title")+":"+dr.getString("content");
			// 针对该人员去匹配他的发送时间
			pdt = query.executeQuery("select * from bs_t_sm_smsorderplan t where t.loginname = ?" +
					" and t.status = ? and t.starttime <= ? and t.endtime >= ?", loginname, 1L, HHmm, HHmm);
			if (pdt != null && pdt.length() > 0) {
				for (int j = 0; j < pdt.length(); j++) {
					pdr = pdt.getDataRow(j);
					if (isHoliday && pdr.getLong("isholiday") == 1L) {// 如果是节假日，并且节假日不发短信，则跳过本次循环,否则继续下一步
						continue;
					} else {
						if (noSend) {// 如果本条短信没有发送过，则继续下一步，如果已经发送过，则跳出循环
							opflag = insidesm.sendsm(goal, content, "plan", "", 0);
							if (opflag) {
								dataAdapter.execute(upSql, new Object[]{dr.getString("pid")});
							}
							noSend = false;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	public void setSmOrderPlanDao(IDao<SmsOrderPlan> smOrderPlanDao) {
		this.smOrderPlanDao = smOrderPlanDao;
	}
}
