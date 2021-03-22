package com.ultrapower.lnkhzs.situation.manager;

import com.ultrapower.lnkhzs.quality.model.SWfCmplntsReqstDetail;
import com.ultrapower.lnkhzs.quality.service.ISWfCmplntsReqstDetailService;
import com.ultrapower.lnkhzs.situation.service.ISTotalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [服务请求明细日报表_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class STotalMgrImpl implements ISTotalService {

	@Override
	public List<Map<Object, Object>> getTotalOut(String selecttime) {
		List<Map<Object, Object>> total=new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sqlselect="";
		String sqlselectaLL="1";
		if(!(selecttime==null||selecttime.isEmpty())){
			sqlselect = " AND ( ACPT_TIME LIKE '"+selecttime+"%' )";
		}
			
			
		//广义
		String sql =   " select * from NGCS_WF_CMPLNTS_TS_TOTAL_O where 1 = 1  "+ sqlselect +" ORDER BY BROAD_TOTAL desc ";
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();
			
			map.put("ACPT_TIME", datatable.getRowList().get(i).getObject("ACPT_TIME"));
			map.put("RESP_ACPT", datatable.getRowList().get(i).getObject("RESP_ACPT"));
			map.put("BROAD_TOTAL", datatable.getRowList().get(i).getObject("BROAD_TOTAL"));
			map.put("BROAD_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("BROAD_MARKET_PERSONAL"));
			map.put("BROAD_MARKET_FAMILY", datatable.getRowList().get(i).getObject("BROAD_MARKET_FAMILY"));
			map.put("BROAD_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("BROAD_MARKET_ENTERPRISE"));
			map.put("BROAD_MARKET_RAT", datatable.getRowList().get(i).getObject("BROAD_MARKET_RAT"));
			map.put("BROAD_NET_PERSONAL", datatable.getRowList().get(i).getObject("BROAD_NET_PERSONAL"));
			map.put("BROAD_NET_FAMILY", datatable.getRowList().get(i).getObject("BROAD_NET_FAMILY"));
			map.put("BROAD_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("BROAD_NET_ENTERPRISE"));
			map.put("BROAD_NET_RAT", datatable.getRowList().get(i).getObject("BROAD_NET_RAT"));
			map.put("BROAD_XA", datatable.getRowList().get(i).getObject("BROAD_XA"));
			map.put("BROAD_XA_RAT", datatable.getRowList().get(i).getObject("BROAD_XA_RAT"));
			map.put("ONLINE_TOTAL", datatable.getRowList().get(i).getObject("ONLINE_TOTAL"));
			map.put("ONLINE_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("ONLINE_MARKET_PERSONAL"));
			map.put("ONLINE_MARKET_FAMILY", datatable.getRowList().get(i).getObject("ONLINE_MARKET_FAMILY"));
			map.put("ONLINE_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("ONLINE_MARKET_ENTERPRISE"));
			map.put("ONLINE_MARKET_RAT", datatable.getRowList().get(i).getObject("ONLINE_MARKET_RAT"));
			map.put("ONLINE_NET_PERSONAL", datatable.getRowList().get(i).getObject("ONLINE_NET_PERSONAL"));
			map.put("ONLINE_NET_FAMILY", datatable.getRowList().get(i).getObject("ONLINE_NET_FAMILY"));
			map.put("ONLINE_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("ONLINE_NET_ENTERPRISE"));
			map.put("ONLINE_NET_RAT", datatable.getRowList().get(i).getObject("ONLINE_NET_RAT"));
			map.put("ONLINE_XA", datatable.getRowList().get(i).getObject("ONLINE_XA"));
			map.put("ONLINE_XA_RAT", datatable.getRowList().get(i).getObject("ONLINE_XA_RAT"));
			
			map.put("NARROW_TOTAL", datatable.getRowList().get(i).getObject("NARROW_TOTAL"));
			map.put("NARROW_SJ", datatable.getRowList().get(i).getObject("NARROW_SJ"));
			map.put("NARROW_SJ_RAT", datatable.getRowList().get(i).getObject("NARROW_SJ_RAT"));
			
			map.put("NARROW_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("NARROW_MARKET_PERSONAL"));
			map.put("NARROW_MARKET_FAMILY", datatable.getRowList().get(i).getObject("NARROW_MARKET_FAMILY"));
			map.put("NARROW_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("NARROW_MARKET_ENTERPRISE"));
			
			map.put("NARROW_MARKET_RAT", datatable.getRowList().get(i).getObject("NARROW_MARKET_RAT"));
			map.put("NARROW_MARKET_SJ", datatable.getRowList().get(i).getObject("NARROW_MARKET_SJ"));
			map.put("NARROW_NET_PERSONAL", datatable.getRowList().get(i).getObject("NARROW_NET_PERSONAL"));
			map.put("NARROW_NET_FAMILY", datatable.getRowList().get(i).getObject("NARROW_NET_FAMILY"));
			
			map.put("NARROW_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("NARROW_NET_ENTERPRISE"));
			map.put("NARROW_NET_RAT", datatable.getRowList().get(i).getObject("NARROW_NET_RAT"));
			map.put("NARROW_NET_SJ", datatable.getRowList().get(i).getObject("NARROW_NET_SJ"));
			
			map.put("NARROW_XA", datatable.getRowList().get(i).getObject("NARROW_XA"));
			map.put("NARROW_XA_RAT", datatable.getRowList().get(i).getObject("NARROW_XA_RAT"));
			map.put("NARROW_XA_SJ", datatable.getRowList().get(i).getObject("NARROW_XA_SJ"));
			total.add(map);
		}
		return total;
	}

	@Override
	public List<Map<Object, Object>> getCHBNOut(String selecttime) {
		List<Map<Object, Object>> total=new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sql=" SELECT * FROM NGCS_WF_CMPLNTS_TS_CHBN_O WHERE ACPT_TIME = '"+selecttime+"' ORDER BY CHBN_NUM ASC ";
		
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();	
			map.put("CHBN", datatable.getRowList().get(i).getObject("CHBN"));
			map.put("BROAD_TOTAL", datatable.getRowList().get(i).getObject("BROAD_TOTAL"));
			map.put("BROAD_MARKET", datatable.getRowList().get(i).getObject("BROAD_MARKET"));
			map.put("BROAD_MARKET_RAT", datatable.getRowList().get(i).getObject("BROAD_MARKET_RAT"));
			map.put("BROAD_NET", datatable.getRowList().get(i).getObject("BROAD_NET"));
			map.put("BROAD_NET_RAT", datatable.getRowList().get(i).getObject("BROAD_NET_RAT"));
			map.put("BROAD_XA", datatable.getRowList().get(i).getObject("BROAD_XA"));
			map.put("BROAD_XA_RAT", datatable.getRowList().get(i).getObject("BROAD_XA_RAT"));
			map.put("ONLINE_TOTAL", datatable.getRowList().get(i).getObject("ONLINE_TOTAL"));
			map.put("ONLINE_MARKET", datatable.getRowList().get(i).getObject("ONLINE_MARKET"));
			map.put("ONLINE_MARKET_RAT", datatable.getRowList().get(i).getObject("ONLINE_MARKET_RAT"));
			map.put("ONLINE_NET", datatable.getRowList().get(i).getObject("ONLINE_NET"));
			map.put("ONLINE_NET_RAT", datatable.getRowList().get(i).getObject("ONLINE_NET_RAT"));
			map.put("ONLINE_XA", datatable.getRowList().get(i).getObject("ONLINE_XA"));
			map.put("ONLINE_XA_RAT", datatable.getRowList().get(i).getObject("ONLINE_XA_RAT"));
			map.put("NARROW_TOTAL", datatable.getRowList().get(i).getObject("NARROW_TOTAL"));
			map.put("NARROW_SJ", datatable.getRowList().get(i).getObject("NARROW_SJ"));
			map.put("NARROW_SJ_RAT", datatable.getRowList().get(i).getObject("NARROW_SJ_RAT"));
			map.put("NARROW_MARKET", datatable.getRowList().get(i).getObject("NARROW_MARKET"));
			map.put("NARROW_MARKET_RAT", datatable.getRowList().get(i).getObject("NARROW_MARKET_RAT"));
			map.put("NARROW_MARKET_SJ", datatable.getRowList().get(i).getObject("NARROW_MARKET_SJ"));
			map.put("NARROW_NET", datatable.getRowList().get(i).getObject("NARROW_NET"));
			map.put("NARROW_NET_RAT", datatable.getRowList().get(i).getObject("NARROW_NET_RAT"));
			map.put("NARROW_NET_SJ", datatable.getRowList().get(i).getObject("NARROW_NET_SJ"));
			map.put("NARROW_XA", datatable.getRowList().get(i).getObject("NARROW_XA"));
			map.put("NARROW_XA_RAT", datatable.getRowList().get(i).getObject("NARROW_XA_RAT"));
			map.put("NARROW_XA_SJ", datatable.getRowList().get(i).getObject("NARROW_XA_SJ"));
			total.add(map);
		}
		return total;
		/*List<Map<Object, Object>> total=new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sqlselect="";
		if(!(selecttime==null||selecttime.isEmpty())){
			sqlselect = " AND ( ACPT_TIME LIKE '"+selecttime+"%' )";
		}	
			
		//广义
		String sql =   " SELECT table1.*,table2.*,table3.*FROM  " 
				+  " (SELECT AREA_NAME,valueg,value1,value2,value3, " 
				+  " round( (decode(valueg, 0, 0, value1/valueg))*100,2 ) valueg1, " 
				+  " round( (decode(valueg, 0, 0, value2/valueg))*100,2 ) valueg2, " 
				+  " round( (decode(valueg, 0, 0, (value3)/valueg))*100,2 ) valueg3 " 
				+  " FROM  " 
				+  " ( " 
				//+  " SELECT AREA_NAME,SUM( " +sqlselectaLL+ ") valueg, " 
				+  " SELECT AREA_NAME , SUM ( CASE WHEN ("
				+  " NAME LIKE '移动业务%' OR  NAME LIKE '增值业务%' OR NAME LIKE '家庭业务%' OR NAME LIKE '集团业务%' ) "
				+  sqlselect 
				+  " THEN 1 ELSE 0 END ) valueg ,"
				
				+  " SUM(CASE WHEN ( NAME LIKE '增值业务→产品质量%' OR " 
				+  " NAME LIKE '增值业务→服务触点%' OR " 
				+  " NAME LIKE '增值业务→基础服务%' OR " 
				+  " NAME LIKE '增值业务→业务营销%' OR " 
				+  " NAME LIKE '移动业务→基础服务→0000业务统一查询%' OR " 
				+  " NAME LIKE '移动业务→基础服务→其他→语音短信%' OR " 
				+  " NAME LIKE '移动业务→业务营销%' OR " 
				+  " NAME LIKE '移动业务→服务触点%' OR " 
				+  " NAME LIKE '移动业务→产品质量%' OR " 
				+  " (NAME LIKE '移动业务→基础服务%' AND NAME NOT LIKE '移动业务→基础服务→基站及网络设备%' " 
				+  " AND NAME NOT LIKE '移动业务→基础服务→网络安全%' AND NAME NOT LIKE '移动业务→基础服务→其他→信息安全%') OR"
				+  " (NAME LIKE '家庭业务→产品质量%' AND NAME NOT LIKE '家庭业务→产品质量→家庭宽带%' " 
				+  " AND NAME NOT LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' ) OR " 
				+  " (NAME LIKE '家庭业务→服务触点%' AND NAME NOT LIKE '家庭业务→服务触点→装维人员%') OR " 
				+  " NAME LIKE '家庭业务→基础服务%' OR " 
				+  " NAME LIKE '家庭业务→业务营销%' OR"
				+  " NAME LIKE '集团业务→产品质量%' OR " 
				+  " NAME LIKE '集团业务→服务触点%' OR " 
				+  " NAME LIKE '集团业务→基础服务%' OR " 
				+  " NAME LIKE '集团业务→业务营销%' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value1, " 
				
				+  " SUM(CASE WHEN ( NAME LIKE '移动业务→网络质量%' OR " 
				+  " NAME LIKE '增值业务→网络质量%' OR " 
				+  " NAME LIKE '移动业务→基础服务→基站及网络设备%' OR"
				+  " NAME LIKE '家庭业务→网络质量%' OR " 
				+  " NAME LIKE '家庭业务→产品质量→家庭宽带%' OR " 
				+  " NAME LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' OR " 
				+  " NAME LIKE '家庭业务→服务触点→装维人员%' OR"
				+  " NAME LIKE '集团业务→网络质量%') "
				+  sqlselect
				+  "  THEN 1 ELSE 0 END ) value2, " 
				
				+  " SUM(CASE WHEN ( NAME LIKE '移动业务→基础服务→网络安全%' OR " 
				+  " NAME LIKE '移动业务→基础服务→其他→信息安全%'  )"
				+  sqlselect
				+  "  THEN 1 ELSE 0 END ) value3 " 
				
				+  " FROM NGCS_WF_CMPLNTS_COLLECT GROUP BY AREA_NAME ORDER BY valueg DESC " 
				+  " ) ) table1, " 
				
				
				+  " (SELECT AREA_NAME,valuez,value4,value5,value6, " 
				+  " round( (decode(valuez, 0, 0, (value4)/valuez))*100,2 ) valuez1, " 
				+  " round( (decode(valuez, 0, 0, (value5)/valuez))*100,2 ) valuez2, " 
				+  " round( (decode(valuez, 0, 0, (value6)/valuez))*100,2 ) valuez3 " 
				+  " FROM (SELECT AREA_NAME,"
				//+  " SUM(" +sqlselectaLL+ ") valuez , "
				+  " SUM( CASE WHEN ( "
				+  " A12 LIKE '移动业务%' OR  A12 LIKE '增值业务%' OR A12 LIKE '家庭业务%' OR A12 LIKE '集团业务%' ) "
				+  sqlselect 
				+  " THEN 1 ELSE 0 END) valuez , "
				
				+  " SUM(CASE WHEN  " 
				+  " ( A12 LIKE '增值业务→产品质量%' OR " 
				+  " A12 LIKE '增值业务→服务触点%' OR " 
				+  " A12 LIKE '增值业务→基础服务%' OR " 
				+  " A12 LIKE '增值业务→业务营销%' OR " 
				+  " A12 LIKE '移动业务→基础服务→0000业务统一查询%' OR " 
				+  " A12 LIKE '移动业务→基础服务→其他→语音短信%' OR " 
				+  " A12 LIKE '移动业务→业务营销%' OR " 
				+  " A12 LIKE '移动业务→服务触点%' OR " 
				+  " A12 LIKE '移动业务→产品质量%' OR " 
				+  " (A12 LIKE '移动业务→基础服务%' AND A12 NOT LIKE '移动业务→基础服务→基站及网络设备%' " 
				+  " AND A12 NOT LIKE '移动业务→基础服务→网络安全%' AND A12 NOT LIKE '移动业务→基础服务→其他→信息安全%') OR"
				+  " (A12 LIKE '家庭业务→产品质量%' AND A12 NOT LIKE '家庭业务→产品质量→家庭宽带%' AND  " 
				+  " A12 NOT LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' ) OR " 
				+  " (A12 LIKE '家庭业务→服务触点%' AND A12 NOT LIKE '家庭业务→服务触点→装维人员%') OR " 
				+  " A12 LIKE '家庭业务→基础服务%' OR " 
				+  " A12 LIKE '家庭业务→业务营销%' OR"
				+  " A12 LIKE '集团业务→产品质量%' OR " 
				+  " A12 LIKE '集团业务→服务触点%' OR " 
				+  " A12 LIKE '集团业务→基础服务%' OR " 
				+  " A12 LIKE '集团业务→业务营销%' ) "
				+  sqlselect
				+ " THEN 1 ELSE 0 END ) value4, " 
				
				+  " SUM(CASE WHEN  " 
				+  " (A12 LIKE '移动业务→网络质量%' OR " 
				+  " A12 LIKE '增值业务→网络质量%' OR " 
				+  " A12 LIKE '移动业务→基础服务→基站及网络设备%' OR "
				+  " A12 LIKE '家庭业务→网络质量%' OR " 
				+  " A12 LIKE '家庭业务→产品质量→家庭宽带%' OR " 
				+  " A12 LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' OR " 
				+  " A12 LIKE '家庭业务→服务触点→装维人员%' OR "
				+  " A12 LIKE '集团业务→网络质量%' ) "
				+  sqlselect
				+  "  THEN 1 ELSE 0 END ) value5, " 
				
				+  " SUM(CASE WHEN  " 
				+  " (A12 LIKE '移动业务→基础服务→网络安全%' OR " 
				+  " A12 LIKE '移动业务→基础服务→其他→信息安全%' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value6 " 
				
				+  " FROM NGCS_WF_CMPLNTS_REQST_DETAIL  " 
				+  " WHERE ( IF_TS = '0' AND A02 IN ( '移动业务', '增值业务', '家庭业务', '集团业务' ) ) " 
				+  " GROUP BY AREA_NAME ORDER BY valueZ desc " 
				+  " ) ) table2, " 
				
				
				+  " (SELECT AREA_NAME,valuex,value7,value8,value9,value10,value11,value12,value13, " 
				+  " round( (decode(valuex, 0, 0, (value7)/valuex))*100,2 ) valuex1, " 
				+  " round( (decode(valuex, 0, 0, (value8)/valuex))*100,2 ) valuex2, " 
				+  " round( (decode(valuex, 0, 0, (value10)/valuex))*100,2 ) valuex3, " 
				+  " round( (decode(valuex, 0, 0, (value12)/valuex))*100,2 ) valuex4 " 
				+  " FROM (SELECT AREA_NAME,"
				//+  " SUM(" +sqlselectaLL+ ") valuex, " 
				+  " SUM( CASE WHEN ( "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务%' ) "
				+  sqlselect 
				+  " THEN 1 ELSE 0 END) valuex , "
				
				+  " SUM( CASE WHEN (ACPT_CHNL_NM IN ( "
				+  " '96180','信产部转办','信产部立案','总部转办','工信部化解','信产部清算司', " 
				+  " '信产部服务处','信产部立案返单','工信部咨询化解','集团热线','集团热线预处理', " 
				+  " '10080短信自助','10080邮箱自助','10080邮箱','10080服务监督','全省携号转网', " 
				+  " '民心网跟踪件','民心网咨询','民心网','内部客户来函','外部客户来函','省内来函来访', " 
				+  " '客户来访','省315','市315','315及行风咨询','315平台','中消协电商消费维权直通车', " 
				+  " '中消协转','质量万里行','总部督办','总部','综合部敏感信息','巡视整改','企业自查', " 
				+  " 'CEO信箱','12300','12321举报受理中心','12300咨询','管局局长信箱','12300网站', " 
				+  " '12300电话','管局局长信箱转办','12300网站转办','12300电话转办','政民互动平台', " 
				+  " '政风行风','政民互动平台跟踪件','纠风热线' )) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value7 , " 
				
				+  " SUM(CASE WHEN ( SRV_REQST_TYPE_FULL_NM LIKE '增值业务→产品质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→业务营销%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→0000业务统一查询%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→其他→语音短信%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→业务营销%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→产品质量%' OR " 
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→基站及网络设备%' " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→网络安全%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→其他→信息安全%') OR "
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→产品质量→家庭宽带%' " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' ) OR " 
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→服务触点%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→服务触点→装维人员%') OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→业务营销%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→产品质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→业务营销%' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value8, " 
				
				+  " SUM(CASE WHEN ( SRV_REQST_TYPE_FULL_NM LIKE '增值业务→产品质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→业务营销%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→0000业务统一查询%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→其他→语音短信%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→业务营销%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→产品质量%' OR " 
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→基站及网络设备%' " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→网络安全%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '移动业务→基础服务→其他→信息安全%') OR "
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→产品质量→家庭宽带%' " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' ) OR " 
				+  " (SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→服务触点%'  " 
				+  " AND SRV_REQST_TYPE_FULL_NM NOT LIKE '家庭业务→服务触点→装维人员%') OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→业务营销%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→产品质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→服务触点%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→基础服务%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→业务营销%' ) "
				+  " AND ACPT_CHNL_NM IN ( "
				+  " '96180','信产部转办','信产部立案','总部转办','工信部化解','信产部清算司', " 
				+  " '信产部服务处','信产部立案返单','工信部咨询化解','集团热线','集团热线预处理', " 
				+  " '10080短信自助','10080邮箱自助','10080邮箱','10080服务监督','全省携号转网', " 
				+  " '民心网跟踪件','民心网咨询','民心网','内部客户来函','外部客户来函','省内来函来访', " 
				+  " '客户来访','省315','市315','315及行风咨询','315平台','中消协电商消费维权直通车', " 
				+  " '中消协转','质量万里行','总部督办','总部','综合部敏感信息','巡视整改','企业自查', " 
				+  " 'CEO信箱','12300','12321举报受理中心','12300咨询','管局局长信箱','12300网站', " 
				+  " '12300电话','管局局长信箱转办','12300网站转办','12300电话转办','政民互动平台', " 
				+  " '政风行风','政民互动平台跟踪件','纠风热线' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value9, " 
				
				+  " SUM(CASE WHEN ( SRV_REQST_TYPE_FULL_NM LIKE '移动业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→基站及网络设备%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量→家庭宽带%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→服务触点→装维人员%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→网络质量%' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value10, " 
				
				+  " SUM(CASE WHEN ( SRV_REQST_TYPE_FULL_NM LIKE '移动业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '增值业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→基站及网络设备%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→网络质量%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量→家庭宽带%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→产品质量→互联网电视→全局流转→功能使用%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '家庭业务→服务触点→装维人员%' OR "
				+  " SRV_REQST_TYPE_FULL_NM LIKE '集团业务→网络质量%' ) "
				+  " AND ACPT_CHNL_NM IN ( "
				+  " '96180','信产部转办','信产部立案','总部转办','工信部化解','信产部清算司', " 
				+  " '信产部服务处','信产部立案返单','工信部咨询化解','集团热线','集团热线预处理', " 
				+  " '10080短信自助','10080邮箱自助','10080邮箱','10080服务监督','全省携号转网', " 
				+  " '民心网跟踪件','民心网咨询','民心网','内部客户来函','外部客户来函','省内来函来访', " 
				+  " '客户来访','省315','市315','315及行风咨询','315平台','中消协电商消费维权直通车', " 
				+  " '中消协转','质量万里行','总部督办','总部','综合部敏感信息','巡视整改','企业自查', " 
				+  " 'CEO信箱','12300','12321举报受理中心','12300咨询','管局局长信箱','12300网站', " 
				+  " '12300电话','管局局长信箱转办','12300网站转办','12300电话转办','政民互动平台', " 
				+  " '政风行风','政民互动平台跟踪件','纠风热线' ) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value11, " 
				
				+  " SUM(CASE WHEN ( SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→网络安全%' OR " 
				+  " SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→其他→信息安全%' )"
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value12, "
				+  " SUM( CASE WHEN ((SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→网络安全%' "
				+  " OR SRV_REQST_TYPE_FULL_NM LIKE '移动业务→基础服务→其他→信息安全%') "
				+  " AND (ACPT_CHNL_NM IN ('96180','信产部转办','信产部立案','总部转办', "
				+  " '工信部化解','信产部清算司','信产部服务处','信产部立案返单','工信部咨询化解', "
				+  " '集团热线','集团热线预处理','10080短信自助','10080邮箱自助','10080邮箱','10080服务监督', "
				+  " '全省携号转网','民心网跟踪件','民心网咨询','民心网','内部客户来函','外部客户来函', "
				+  " '省内来函来访','客户来访 ','省315','市315','315及行风咨询','315平台', "
				+  " '中消协电商消费维权直通车','中消协转','质量万里行','总部督办','总部', "
				+  " '综合部敏感信息','巡视整改','企业自查','CEO信箱','12300', "
				+  " '12321举报受理中心','12300咨询','管局局长信箱','12300网站','12300电话', "
				+  " '管局局长信箱转办','12300网站转办','12300电话转办','政民互动平台', "
				+  " '政风行风','政民互动平台跟踪件','纠风热线'))) "
				+  sqlselect
				+  " THEN 1 ELSE 0 END ) value13 "		
				
				+  " FROM NGCS_WF_CMPLNTS_ARC_DETAIL GROUP BY AREA_NAME ORDER BY valuex DESC)) table3   " 
				+  " WHERE table1.AREA_NAME = table2.AREA_NAME AND table1.AREA_NAME = table3.AREA_NAME "  ;
		
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();
			
			map.put("value1", datatable.getRowList().get(i).getObject("value1"));
			map.put("value2", datatable.getRowList().get(i).getObject("value2"));
			map.put("value3", datatable.getRowList().get(i).getObject("value3"));
			map.put("value4", datatable.getRowList().get(i).getObject("value4"));
			map.put("value5", datatable.getRowList().get(i).getObject("value5"));
			map.put("value6", datatable.getRowList().get(i).getObject("value6"));
			map.put("value7", datatable.getRowList().get(i).getObject("value7"));
			
			map.put("value8", datatable.getRowList().get(i).getObject("value8"));
			map.put("value9", datatable.getRowList().get(i).getObject("value9"));
			map.put("value10", datatable.getRowList().get(i).getObject("value10"));
			map.put("value11", datatable.getRowList().get(i).getObject("value11"));
			map.put("value12", datatable.getRowList().get(i).getObject("value12"));
			map.put("value13", datatable.getRowList().get(i).getObject("value13"));
			
			map.put("valueg1", datatable.getRowList().get(i).getObject("valueg1"));
			map.put("valueg2", datatable.getRowList().get(i).getObject("valueg2"));
			map.put("valueg3", datatable.getRowList().get(i).getObject("valueg3"));
			
			map.put("valuez1", datatable.getRowList().get(i).getObject("valuez1"));
			map.put("valuez2", datatable.getRowList().get(i).getObject("valuez2"));
			map.put("valuez3", datatable.getRowList().get(i).getObject("valuez3"));
			
			map.put("valuex1", datatable.getRowList().get(i).getObject("valuex1"));
			map.put("valuex2", datatable.getRowList().get(i).getObject("valuex2"));
			map.put("valuex3", datatable.getRowList().get(i).getObject("valuex3"));
			map.put("valuex4", datatable.getRowList().get(i).getObject("valuex4"));
			
			map.put("valueg", datatable.getRowList().get(i).getObject("valueg"));
			map.put("valuez", datatable.getRowList().get(i).getObject("valuez"));
			map.put("valuex", datatable.getRowList().get(i).getObject("valuex"));
			
			map.put("area_name", datatable.getRowList().get(i).getObject("area_name"));
			total.add(map);
		}
		return total;*/
	}

	@Override
	public List<Map<Object, Object>> getSJ(String selecttime) {
		List<Map<Object, Object>> total=new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sqlselect="";
		String sqlselectaLL="1";
		if(!(selecttime==null||selecttime.isEmpty())){
			sqlselect = " AND ( ACPT_TIME LIKE '"+selecttime+"%' )";
		}
			
			
		//广义
		String sql =   " select * from NGCS_WF_CMPLNTS_TS_SJ where 1 = 1  "+ sqlselect +" ORDER BY ACPT_TIME asc ";
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();
			
			map.put("ACPT_TIME", datatable.getRowList().get(i).getObject("ACPT_TIME"));
			map.put("RESP_ACPT", datatable.getRowList().get(i).getObject("RESP_ACPT"));
			
			map.put("SJTOTAL_TOTAL", datatable.getRowList().get(i).getObject("SJTOTAL_TOTAL"));
			map.put("SJTOTAL_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_PERSONAL"));
			map.put("SJTOTAL_MARKET_FAMILY", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_FAMILY"));
			map.put("SJTOTAL_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_ENTERPRISE"));
			map.put("SJTOTAL_MARKET_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_RAT"));
			map.put("SJTOTAL_NET_PERSONAL", datatable.getRowList().get(i).getObject("SJTOTAL_NET_PERSONAL"));
			map.put("SJTOTAL_NET_FAMILY", datatable.getRowList().get(i).getObject("SJTOTAL_NET_FAMILY"));
			map.put("SJTOTAL_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("SJTOTAL_NET_ENTERPRISE"));
			map.put("SJTOTAL_NET_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_NET_RAT"));
			map.put("SJTOTAL_XA", datatable.getRowList().get(i).getObject("SJTOTAL_XA"));
			map.put("SJTOTAL_XA_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_XA_RAT"));
			
			map.put("SJTOTAL_TOTAL", datatable.getRowList().get(i).getObject("SJTOTAL_TOTAL"));
			map.put("SJTOTAL_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_PERSONAL"));
			map.put("SJTOTAL_MARKET_FAMILY", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_FAMILY"));
			map.put("SJTOTAL_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_ENTERPRISE"));
			map.put("SJTOTAL_MARKET_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_MARKET_RAT"));
			map.put("SJTOTAL_NET_PERSONAL", datatable.getRowList().get(i).getObject("SJTOTAL_NET_PERSONAL"));
			map.put("SJTOTAL_NET_FAMILY", datatable.getRowList().get(i).getObject("SJTOTAL_NET_FAMILY"));
			map.put("SJTOTAL_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("SJTOTAL_NET_ENTERPRISE"));
			map.put("SJTOTAL_NET_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_NET_RAT"));
			map.put("SJTOTAL_XA", datatable.getRowList().get(i).getObject("SJTOTAL_XA"));
			map.put("SJTOTAL_XA_RAT", datatable.getRowList().get(i).getObject("SJTOTAL_XA_RAT"));
			
			
			
			map.put("ONLINE_TOTAL", datatable.getRowList().get(i).getObject("ONLINE_TOTAL"));
			map.put("ONLINE_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("ONLINE_MARKET_PERSONAL"));
			map.put("ONLINE_MARKET_FAMILY", datatable.getRowList().get(i).getObject("ONLINE_MARKET_FAMILY"));
			map.put("ONLINE_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("ONLINE_MARKET_ENTERPRISE"));
			map.put("ONLINE_MARKET_RAT", datatable.getRowList().get(i).getObject("ONLINE_MARKET_RAT"));
			map.put("ONLINE_NET_PERSONAL", datatable.getRowList().get(i).getObject("ONLINE_NET_PERSONAL"));
			map.put("ONLINE_NET_FAMILY", datatable.getRowList().get(i).getObject("ONLINE_NET_FAMILY"));
			map.put("ONLINE_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("ONLINE_NET_ENTERPRISE"));
			map.put("ONLINE_NET_RAT", datatable.getRowList().get(i).getObject("ONLINE_NET_RAT"));
			map.put("ONLINE_XA", datatable.getRowList().get(i).getObject("ONLINE_XA"));
			map.put("ONLINE_XA_RAT", datatable.getRowList().get(i).getObject("ONLINE_XA_RAT"));
			
			map.put("NARROW_TOTAL", datatable.getRowList().get(i).getObject("NARROW_TOTAL"));
			map.put("NARROW_SJ", datatable.getRowList().get(i).getObject("NARROW_SJ"));
			map.put("NARROW_SJ_RAT", datatable.getRowList().get(i).getObject("NARROW_SJ_RAT"));
			
			map.put("NARROW_MARKET_PERSONAL", datatable.getRowList().get(i).getObject("NARROW_MARKET_PERSONAL"));
			map.put("NARROW_MARKET_FAMILY", datatable.getRowList().get(i).getObject("NARROW_MARKET_FAMILY"));
			map.put("NARROW_MARKET_ENTERPRISE", datatable.getRowList().get(i).getObject("NARROW_MARKET_ENTERPRISE"));
			
			map.put("NARROW_MARKET_RAT", datatable.getRowList().get(i).getObject("NARROW_MARKET_RAT"));
			map.put("NARROW_MARKET_SJ", datatable.getRowList().get(i).getObject("NARROW_MARKET_SJ"));
			map.put("NARROW_NET_PERSONAL", datatable.getRowList().get(i).getObject("NARROW_NET_PERSONAL"));
			map.put("NARROW_NET_FAMILY", datatable.getRowList().get(i).getObject("NARROW_NET_FAMILY"));
			
			map.put("NARROW_NET_ENTERPRISE", datatable.getRowList().get(i).getObject("NARROW_NET_ENTERPRISE"));
			map.put("NARROW_NET_RAT", datatable.getRowList().get(i).getObject("NARROW_NET_RAT"));
			map.put("NARROW_NET_SJ", datatable.getRowList().get(i).getObject("NARROW_NET_SJ"));
			
			map.put("NARROW_XA", datatable.getRowList().get(i).getObject("NARROW_XA"));
			map.put("NARROW_XA_RAT", datatable.getRowList().get(i).getObject("NARROW_XA_RAT"));
			map.put("NARROW_XA_SJ", datatable.getRowList().get(i).getObject("NARROW_XA_SJ"));
			total.add(map);
		}
		return total;
	}

	
	@Override
	public List<Map<Object, Object>> getFG(String selecttime) {
		List<Map<Object, Object>> total=new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sqlselect="";
		String sqlselectaLL="1";
		if(!(selecttime==null||selecttime.isEmpty())){
			sqlselect = " AND ( ACPT_TIME LIKE '"+selecttime+"%' )";
		}
			
			
		//广义
		String sql =   " select * from NGCS_WF_CMPLNTS_TS_FG where 1 = 1  "+ sqlselect +" ORDER BY ACPT_TIME desc ";
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();
			
			map.put("ACPT_TIME", datatable.getRowList().get(i).getObject("ACPT_TIME"));
			map.put("AREA_NAME", datatable.getRowList().get(i).getObject("AREA_NAME"));
			map.put("BROAD_TOTAL", datatable.getRowList().get(i).getObject("BROAD_TOTAL"));
			map.put("BROAD_MARKET", datatable.getRowList().get(i).getObject("BROAD_MARKET"));
			map.put("BROAD_NET", datatable.getRowList().get(i).getObject("BROAD_NET"));	
			map.put("BROAD_MARKET_RAT", datatable.getRowList().get(i).getObject("BROAD_MARKET_RAT"));
			map.put("BROAD_NET_RAT", datatable.getRowList().get(i).getObject("BROAD_NET_RAT"));
			
			map.put("ONLINE_TOTAL", datatable.getRowList().get(i).getObject("ONLINE_TOTAL"));		
			map.put("ONLINE_MARKET", datatable.getRowList().get(i).getObject("ONLINE_MARKET"));
			map.put("ONLINE_NET", datatable.getRowList().get(i).getObject("ONLINE_NET"));
			map.put("ONLINE_MARKET_RAT", datatable.getRowList().get(i).getObject("ONLINE_MARKET_RAT"));
			map.put("ONLINE_NET_RAT", datatable.getRowList().get(i).getObject("ONLINE_NET_RAT"));
			
			map.put("NARROW_TOTAL", datatable.getRowList().get(i).getObject("NARROW_TOTAL"));
			map.put("NARROW_MARKET", datatable.getRowList().get(i).getObject("NARROW_MARKET"));
			map.put("NARROW_NET", datatable.getRowList().get(i).getObject("NARROW_NET"));
			map.put("NARROW_MARKET_RAT", datatable.getRowList().get(i).getObject("NARROW_MARKET_RAT"));
			map.put("NARROW_NET_RAT", datatable.getRowList().get(i).getObject("NARROW_NET_RAT"));
			map.put("NARROW_MARKET_SJ", datatable.getRowList().get(i).getObject("NARROW_MARKET_SJ"));
			map.put("NARROW_NET_SJ", datatable.getRowList().get(i).getObject("NARROW_NET_SJ"));
			total.add(map);
		}
		return total;
	}

	@Override
	public List<Map<Object, Object>> getFYZYJT(String selecttime) {
		ArrayList<Map<Object, Object>> total = new ArrayList<Map<Object,Object>>();
		QueryAdapter q = new QueryAdapter();
		String sqlselect="";
		String sqlselectaLL="1";
		if(!(selecttime==null||selecttime.isEmpty())){
			sqlselect = " AND ( ACPT_TIME LIKE '"+selecttime+"%' )";
		}
		
		
		String sql = "select * from NGCS_WF_CMPLNTS_TS_COST_JT where 1=1 "+sqlselect +" ORDER BY ACPT_TIME desc ";
		DataTable datatable = q.executeQuery(sql);
		Map<Object,Object> map=new HashMap<Object, Object>();
		for (int i = 0; i < datatable.getRowList().size(); i++) {
			map=new HashMap<Object, Object>();
			
			map.put("ACPT_TIME", datatable.getRowList().get(i).getObject("ACPT_TIME"));
			map.put("AREA_NAME", datatable.getRowList().get(i).getObject("AREA_NAME"));
			map.put("COMPLAINTS", datatable.getRowList().get(i).getObject("COMPLAINTS"));
			map.put("COMPLAINTS_MAX", datatable.getRowList().get(i).getObject("COMPLAINTS_MAX"));
			map.put("COMPLAINTS_MIN", datatable.getRowList().get(i).getObject("COMPLAINTS_MIN"));
			map.put("COMPLAINTS_RAT", datatable.getRowList().get(i).getObject("COMPLAINTS_RAT"));
			total.add(map);
		}
		return total;
	}
	
	
	
	
	

}
