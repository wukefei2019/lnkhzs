package com.ultrapower.lnkhzs.situation.web;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.rquery.RQuery;
import com.ultrapower.eoms.common.core.component.rquery.core.SqlQuery;
import com.ultrapower.eoms.common.core.component.rquery.startup.StartUp;
import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsReqstDetailService;
import com.ultrapower.lnkhzs.situation.service.ISTotalService;
import com.ultrapower.omcs.base.web.BaseAction;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_WEB]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class STotalAction extends BaseAction {

    private static final long serialVersionUID = -1L;
    
    private ISTotalService sTotalService;
    
    private String selecttime;
    		
    private String selecttype;
    
    public ISTotalService getsTotalService() {
		return sTotalService;
	}

	public void setsTotalService(ISTotalService sTotalService) {
		this.sTotalService = sTotalService;
	}
	
	
	
	public String getSelecttime() {
		return selecttime;
	}

	public void setSelecttime(String selecttime) {
		this.selecttime = selecttime;
	}
	
	
	

	public String getSelecttype() {
		return selecttype;
	}

	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}

	/**
	 * 总表-对外
	 */
	public void getTotalOut(){
		List<Map<Object,Object>> total = sTotalService.getTotalOut(selecttime);
		//List<Map<Object,Object>> total=null;
		renderJson(total);
	}
	
	/**
	 * CHBN-对外
	 */
	public void getCHBNOut(){
		List<Map<Object,Object>> total = sTotalService.getCHBNOut(selecttime);
		//List<Map<Object,Object>> total=null;
		renderJson(total);
	}
	
	/**
	 * 升级
	 */
	public void getSJ(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_TS_SJA.query";	
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
	}
	
	
	/**
	 * CHBN
	 */
	public void getCHBN(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_TS_CHBN.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
	}
	

	/**
	 * 携号转网总量
	 */
	public void getMNPTotal(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_TS_MNP.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
	}
	
	/**
	 * 5G
	 */
	public void getFG(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_FG.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
		
	}
	

	/**
	 * 费用质疑（集团）
	 */
	public void getFYZYJT(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_FYZY_JT.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
		
	}
	
	/**
	 * 总表HTML5查询
	 */
	public void getTotal(){
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("time", selecttime);
		String sqlname="SQL_TS_TOTAL_SJA.query";
		Object obj=null;
		if(StartUp.sqlmapElement!=null)
			obj=StartUp.sqlmapElement.get(sqlname);
		if(obj!=null)
		{
			Element sqlqueryElement=(Element)obj;
			new SqlQuery(sqlqueryElement,null,true);
		}
		RQuery rq = new RQuery(sqlname,param);
		DataTable dt = rq.getDataTable();
		//dt.getRowList();
		if(dt==null)
			renderJson(null);
		else
			renderJson(dt.getRowList());
		/*List<Map<Object,Object>> total = sTotalService.getSJ(selecttime);
		//List<Map<Object,Object>> total=null;
		renderJson(total);*/
	}

}

