package com.ultrapower.eoms.common.core.component.sms.business;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.ultrapower.eoms.common.core.component.data.DataAdapter;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.component.sms.service.InsideSmsService;
import com.ultrapower.eoms.common.core.component.sms.service.SmsSubscribeToMoniterService;
import com.ultrapower.eoms.common.core.util.NumberUtils;
import com.ultrapower.eoms.common.core.util.StringUtils;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.common.core.util.WebApplicationManager;


/**
 * 工单短信订阅
 * 根据用户订阅的条件将满足条件的信息插入到短信表(bs_t_sm_smsmonitor)中
 * @author Administrator
 *
 */
public class SmsSubscribeToMoniter implements SmsSubscribeToMoniterService
{
 
	private InsideSmsService insidesm;
	private HashMap<String,String> baseItem=new HashMap<String, String>();
	private LinkedList<String> baseItemKey=new LinkedList<String>();
	QueryAdapter query=new QueryAdapter();
	DataAdapter dataAdapter=new DataAdapter();
	
	public void call()
	{
		
		baseItem=new HashMap<String, String>();
		baseItemKey=new LinkedList<String>();
		try{
			
			insidesm=(InsideSmsService)WebApplicationManager.getBean("insidesm");
			
			RQuery query=new RQuery("SQL_SM_SmsSubscribeToMoniter.person",null);
			DataTable dt=query.getDataTable();
			System.out.println("SmsSubscribeToMoniter::call>> sms_monitor_person: "+dt.length()+" 条.");
			this.subscribe(dt);
			
			query=new RQuery("SQL_SM_SmsSubscribeToMoniter.group",null);
			dt=query.getDataTable();
			System.out.println("SmsSubscribeToMoniter::call>> sms_monitor_group: "+dt.length()+" 条.");
			this.subscribe(dt);

			query=new RQuery("SQL_SM_SmsSubscribeToMoniter.role",null);
			dt=query.getDataTable();
			System.out.println("SmsSubscribeToMoniter::call>> sms_monitor_role: "+dt.length()+" 条.");
			this.subscribe(dt);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param dt
	 * @throws Exception
	 */
	private   void subscribe(DataTable dt) throws Exception
	{
		
		String baseid;
		String baseschema;
		String basesn;
		String basesummary;
		String loginname;
		String starttime;
		String endtime;
		String content;
		String goal;
		String taskid;
		String noticeid;
		String schemalabel;
		String processname;
		Long createtime;
		Long accepttime;
		Long dealtime;
		String descrption;
		String forgedmobile;
		int dtLen=0;
		if(dt!=null)
		{
			dtLen=dt.length();
		}
		DataRow dr;
		String upSql="update bs_t_wf_notice set issent=? where noticeid=? ";
		boolean blnitem;
		boolean blntime;
		boolean opflag;
		boolean isDirect;
		for(int i=0;i<dtLen;i++)
		{
			dr=dt.getDataRow(i);
			baseid=dr.getString("baseid");
			basesn=dr.getString("basesn");
			basesummary=dr.getString("basesummary");
			baseschema=dr.getString("baseschema");
			loginname=dr.getString("loginname");
			starttime=dr.getString("starttime");
			endtime=dr.getString("endtime");
			content=dr.getString("content");
			goal=dr.getString("mobile");
			taskid=dr.getString("taskid");
			noticeid = dr.getString("noticeid");
			schemalabel = dr.getString("schemalabel");
			processname = dr.getString("processname");
			createtime = dr.getLong("createtime");
			accepttime = dr.getLong("accepttime");
			dealtime = dr.getLong("dealtime");
			descrption = dr.getString("descrption");
			forgedmobile = dr.getString("forgedmobile");
			String isdw = dr.getString("isdw");
			
			/*
			 * 2013-11-04 徐发球
			 * 故障工单在代维处理环节不用订阅直接发短信
			 * 逻辑： T2环节 并且是派发代维的工单，在发送内容中第一位字母为1的派发代维的
			 * */
			isDirect=false;
			blnitem=false;
			blntime=false;
			
			 if ("1".equals(isdw)){//派代维
		        if ("WF4:EL_TTM_TTH".equals(baseschema)){
		          if (!"".equals(content)){
		            if ("1".equals(content.subSequence(0, 1))){
		              content = content.substring(1);
		              isDirect = true;
		            }else if ("0".equals(content.subSequence(0, 1))){
		              content = content.substring(1);
		            }
		          }
		        }else{
		          blnitem = checkBaseitem(loginname, baseid, baseschema);//匹配专业
		          blntime = checkTime(starttime, endtime);//接收时间匹配
		        }
		      }else{ //派移动
		        blnitem = checkBaseitem(loginname, baseid, baseschema);
		        blntime = checkTime(starttime, endtime);
		      }
		      if ((isDirect) || ((blnitem) && (blntime))){
		    	Map<String,String> map = new HashMap<String,String>();
		    	map.put("baseid", baseid);
		    	map.put("basesn", basesn);
		    	map.put("basesummary", basesummary);
		    	map.put("baseschema", baseschema);
		    	map.put("taskid", taskid);
		    	map.put("schemalabel", schemalabel);
		    	map.put("processname", processname);
		    	map.put("createtime", String.valueOf(createtime));
		    	map.put("accepttime", String.valueOf(accepttime));
		    	map.put("dealtime",String.valueOf(dealtime));
		    	map.put("forgedmobile", forgedmobile);
		    	map.put("descrption", descrption);
		    	//String extendContent  = insidesm.extendContentJson(baseid,basesn,baseschema,basesummary,taskid);
		    	String extendContent  = insidesm.extendContentJson(map);
		    	opflag=insidesm.sendsm(goal, content,extendContent, "workflow",noticeid,0);
		        if (opflag){
		          this.dataAdapter.execute(upSql, new Object[] { "1", noticeid });
		        }else{
		          this.dataAdapter.execute(upSql, new Object[] { "2", noticeid });
		        }
		      }else if (blntime){
		        this.dataAdapter.execute(upSql, new Object[] { "2", noticeid });
		      }
		}
	}
	
	/**
	 *根据工单查询工单专业，检查是否订阅了改专业的短信 
	 * @param loginname 处理人登录名
	 * @param baseid 工单id
	 * @param baseschema 工单类别
	 * @return 返回true则为检查通过
	 */
	private boolean checkBaseitem(String loginname,String baseid,String baseschema)
	{
		
		return true;
	}
	/**
	 * 检查时间条件是否满足发送的逻辑
	 * @param loginname
	 * @param baseid
	 * @param baseschema
	 * @param starttime
	 * @param endtime
	 * @return 满足则返回true
	 */
	private boolean checkTime(String starttime,String endtime)
	{
		boolean result=true;
		String[] ary=starttime.split(":");
		int sthour=0;
		int stminute=0;
		if(ary.length>1)
		{
			sthour=NumberUtils.formatToInt(ary[0]);
			stminute=NumberUtils.formatToInt(ary[1]);
		}
		ary=endtime.split(":");
		int edhour=0;
		int edminute=0;
		if(ary.length>1)
		{
			edhour=NumberUtils.formatToInt(ary[0]);
			edminute=NumberUtils.formatToInt(ary[1]);
		}
		
		Date date=TimeUtils.getCurrentDate();
		Calendar c = java.util.Calendar.getInstance();
		c.setTime( date );
		int curhour =c.get( java.util.Calendar.HOUR_OF_DAY );
		int curminute=c.get( java.util.Calendar.MINUTE );
		
		if(sthour>0 )
		{
			if(curhour<sthour)
			{
				return false;
			}
			else if(curhour==sthour)
			{
				if(curminute<stminute)
					return false;
			}
		}
		if(edhour>0)
		{
			if(curhour>edhour)
			{
				return false;
			}
			else if(curhour==edhour)
			{
				if(curminute>edminute)
					return false;
			}
		}
		
		return result;
	}	
	
	/**
	 * 查询某人订阅的专业
	 * @param baseschema
	 * @param loginname
	 * @return 返回对象的null则为未订阅 否则返回专业的字符串 以分号（;)前后分割
	 */
	private String getBaseitem(String baseschema,String loginname)
	{
		String result=null;
		String pkey=baseschema+loginname;
		result=baseItem.get(pkey);
		if(result==null && !baseItem.containsKey(pkey))
		{
			StringBuffer str=new StringBuffer();
			str.append("select baseschema,loginname,BASEITEM from BS_T_SM_SMBASEITEM where baseschema='");
			str.append(baseschema);
			str.append("' and loginname='");
			str.append(loginname);
			str.append("'");
			DataTable dt=query.executeQuery(str.toString(), null);
			int rowLen=0;
			if(dt!=null)
			{
				rowLen=dt.length();
			}
			DataRow row;
			
			str=new StringBuffer();
			for(int i=0;i<rowLen;i++)
			{
				row=dt.getDataRow(i);
				str.append(";");
				str.append(row.getString("BASEITEM"));
				str.append(";");
			}
			if(baseItemKey.size()>30)
			{
				//超过30则溢出第一个
				String key=StringUtils.checkNullString(baseItemKey.poll());
				baseItem.remove(key);
			}
			
			baseItemKey.add(pkey);
			if(rowLen>0)
			{
				baseItem.put(pkey, str.toString());
				result=str.toString();
			}
			else
			{
				baseItem.put(pkey,null);
			}
		}
		return result;
	}
	
	/**
	 * 根据提醒id 获取受理所需参数
	 * @param relateId
	 * @return
	 */
	public HashMap<String,String> getDoActionPara(String relateId){
		String p_sql = "select t.basesn,t.baseschema,t.baseid,t.noticeuserloginname from bs_t_wf_notice t where t.noticeid = ?";
		HashMap<String,String> doActionPara = new HashMap<String,String>();
		DataTable dt=query.executeQuery(p_sql, new Object[]{relateId});
		DataRow dr = null;
		if(dt!=null && dt.length()>0){
			dr=dt.getDataRow(0);
			doActionPara.put("baseSchema", dr.getString("baseschema"));
			doActionPara.put("baseId", dr.getString("baseid"));
			doActionPara.put("baseSn", dr.getString("basesn"));
		}
		return doActionPara;
	}
	
	/**
	 * 根据订阅人查询满足可以发送短信的数据
	 * @return
	 */
	private DataTable queryNotice()
	{
		return null;
	}

/*	public InsideSmsService getInsidesm() {
		return insidesm;
	}

	public void setInsidesm(InsideSmsService insidesm) {
		this.insidesm = insidesm;
	}*/

	public static void main(String[] args)
	{
		String sb="1故障单:ID-051-20131104-02577。最终期限:2013-11-06 17:27:00。主题:测试工单,请勿处理。故障发生时间:2013-11-01 00:00:00。告警定位:。签收请回复\"A\"。{消息来自EOMS}";
		String s=sb.substring(0,1);
		
		System.out.println(s);
		System.out.println(sb.substring(1));
	}
	
}
