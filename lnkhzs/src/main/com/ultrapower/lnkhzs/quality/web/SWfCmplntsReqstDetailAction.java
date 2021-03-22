package com.ultrapower.lnkhzs.quality.web;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsReqstDetailService;
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
public class SWfCmplntsReqstDetailAction extends BaseAction {

    private static final long serialVersionUID = -1L;

    private SWfCmplntsReqstDetail sWfCmplntsReqstDetail; 
    
    private ISWfCmplntsReqstDetailService sWfCmplntsReqstDetailService;
    
    private String queryTable;
    
    private HashMap<String,String> params;
    
    private String strA13;

    public void setsWfCmplntsReqstDetail(SWfCmplntsReqstDetail sWfCmplntsReqstDetail) {
		this.sWfCmplntsReqstDetail = sWfCmplntsReqstDetail;
	}
    
    public SWfCmplntsReqstDetail getsWfCmplntsReqstDetail() {
		return sWfCmplntsReqstDetail;
	}

	public void setsWfCmplntsReqstDetailService(ISWfCmplntsReqstDetailService sWfCmplntsReqstDetailService) {
		this.sWfCmplntsReqstDetailService = sWfCmplntsReqstDetailService;
	}

	public String getQueryTable() {
		return queryTable;
	}

	public void setQueryTable(String queryTable) {
		this.queryTable = queryTable;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	

	public String getStrA13() {
		return strA13;
	}

	public void setStrA13(String strA13) {
		this.strA13 = strA13;
	}

	public String reqstDetail(){
    	sWfCmplntsReqstDetail = sWfCmplntsReqstDetailService.getReqstDetailById(sWfCmplntsReqstDetail.getSrvReqstId());
    	Clob clob = sWfCmplntsReqstDetail.getA13();//java.sql.Clob
    	String detailinfo = "";
    	if(clob != null){
    	    try {
				detailinfo = clob.getSubString((long)1,(int)clob.length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	strA13=detailinfo;
    	return "success";
    }
	
	public void getEndTime(){
		sWfCmplntsReqstDetail = sWfCmplntsReqstDetailService.getEndTime();
		renderJson(sWfCmplntsReqstDetail);
	}
	
	public void getMaxMinTime() throws ParseException{
		String maxMinTime = sWfCmplntsReqstDetailService.getMaxMinTime(queryTable,params);
		renderJson(maxMinTime);
	}

}
