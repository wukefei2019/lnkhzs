package com.ultrapower.lnkhzs.quality.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.bmc.thirdparty.org.apache.commons.beanutils.BeanUtils;
import com.ultrapower.commons.lang3.StringUtils;
import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.UUIDGenerator;
import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNode;
import com.ultrapower.lnkhzs.quality.model.ViceRequestNodeBak;
import com.ultrapower.lnkhzs.quality.service.ITagMaintainService;

/**
 * Created on 2019年4月19日
 * <p>Title      : 辽宁移动企业发展质量监控_[子系统统名]_[模块名]</p>
 * <p>Description: [null_接口]</p>
 * @author       : 
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class TagMaintainMgrImpl implements ITagMaintainService {
    
    private IDao<ViceRequestNode> viceRequestNodeDao;
    
    private IDao<ViceRequestNodeBak> viceRequestNodeBakDao;
    
    public IDao<ViceRequestNodeBak> getViceRequestNodeBakDao() {
		return viceRequestNodeBakDao;
	}

	public void setViceRequestNodeBakDao(IDao<ViceRequestNodeBak> viceRequestNodeBakDao) {
		this.viceRequestNodeBakDao = viceRequestNodeBakDao;
	}

	public IDao<ViceRequestNode> getViceRequestNodeDao() {
		return viceRequestNodeDao;
	}

	public void setViceRequestNodeDao(IDao<ViceRequestNode> viceRequestNodeDao) {
		this.viceRequestNodeDao = viceRequestNodeDao;
	}

    
    public UserSession getUserAdmin() {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPFULLNAME FROM ZL_KHZS_T_ADMIN" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    public UserSession getDepAdmin(String depid) {
		QueryAdapter q = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
    	sql.append(" SELECT LOGINNAME,FULLNAME,DEPFULLNAME FROM ZL_KHZS_T_DEPADMIN WHERE DEPID ='"+depid+"'" );
    	DataTable datatable = q.executeQuery(sql.toString());
    	UserSession user = new UserSession();
    	if(datatable.getRowList().size()>0) {
        	user.setLoginName(datatable.getRowList().get(0).getString(0));
        	user.setFullName(datatable.getRowList().get(0).getString(1));
        	user.setDepFullName(datatable.getRowList().get(0).getString(2));
    	}
    	return user;
    }
    
    


	@Override
	public ViceRequestNode getViceRequestNode(String pid) {
		ViceRequestNode viceRequestNode = viceRequestNodeDao.get(pid);
		return viceRequestNode;
	}

	@Override
	public ViceRequestNodeBak getViceRequestNodeBak(String id) {
		ViceRequestNodeBak viceRequestNodeBak = viceRequestNodeBakDao.get(id);
		return viceRequestNodeBak;
	}
	
	@Override
	public void saveSerReqNode(ViceRequestNode viceRequestNode) {
		if(StringUtils.isBlank(viceRequestNode.getPid())){
			viceRequestNode.setPid(UUIDGenerator.getUUIDoffSpace());
		}
		viceRequestNodeDao.merge(viceRequestNode);
	}

	@Override
	public void saveSerReqNodeBak(ViceRequestNode viceRequestNode,String loginname,String status) throws Exception {
		if(StringUtils.isBlank(viceRequestNode.getPid())){
			viceRequestNode.setPid(UUIDGenerator.getUUIDoffSpace());
		}
		if("删除".equals(status)){
			viceRequestNode=viceRequestNodeDao.get(viceRequestNode.getPid());
		}
		ViceRequestNodeBak viceRequestNodeBak = new ViceRequestNodeBak();
		BeanUtils.copyProperties(viceRequestNodeBak, viceRequestNode);
		viceRequestNodeBak.setId(UUIDGenerator.getUUIDoffSpace());
		viceRequestNodeBak.setOperator(loginname);
		viceRequestNodeBak.setStatus(status);
		viceRequestNodeBak.setFlowstatus("草稿");
		viceRequestNodeBak.setIsDelete("0");
		viceRequestNodeBakDao.save(viceRequestNodeBak);
	}

	@Override
	public void saveSerReqNodeBak(ViceRequestNodeBak viceRequestNodeBak) {
		viceRequestNodeBakDao.merge(viceRequestNodeBak);
	}
	
	@Override
	public void removeSerReqNode(ViceRequestNode viceRequestNode) {
		viceRequestNodeDao.remove(viceRequestNode);
	}
    
	@Override
	public Integer getSenBatchnoSeq(){
    	QueryAdapter rq = new QueryAdapter();
    	DataTable dt = rq.executeQuery("select SEN_BATCHNO_SEQ.nextval from dual");
    	List<DataRow> rowList = dt.getRowList();
    	Integer i = rowList.get(0).getInt("NEXTVAL");
		return i;
	}

	@Override
	public boolean checkRepeat(String[] split,String loginname) {
		HashSet<String> hashSet = new HashSet<String>();
		for (int i = 0; i < split.length; i++) {
			hashSet.add(split[i]);
		}
		if (hashSet.size() == split.length) {
			for(String pid : split){
				List<ViceRequestNodeBak> list = viceRequestNodeBakDao.find("from ViceRequestNodeBak where isDelete = 0 and flowstatus = '流程中' and pid = ?", pid);
				if(list.size()>0){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Integer[] getTagStatistics(String a12, String city ,String year) {
		QueryAdapter rq = new QueryAdapter();
		StringBuffer sql = new StringBuffer();
		sql.append(" select substr(t.statis_date, 5, 2) mon, z.a12, count(*) cnt ");
		sql.append(" from NGCS_WF_CMPLNTS_ARC_DETAIL t, ZL_SERVICE_REQUEST_NODE z ");
		sql.append(" where t.srv_reqst_type_full_nm = z.a12 ");
		if(StringUtils.isNotBlank(a12)){
			sql.append(" and t.srv_reqst_type_full_nm like '"+a12+"%' ");
		}
		if(StringUtils.isNotBlank(city)){
			sql.append(" and t.area_name like '%"+city+"%' ");
		}
		if(StringUtils.isNotBlank(year)){
			sql.append(" and substr(t.statis_date, 0, 4) = "+year);
		}
		sql.append(" group by z.a12, substr(t.statis_date, 5, 2) ");
		sql.append(" order by mon ");
		DataTable dt = rq.executeQuery(sql.toString());
		List<DataRow> rows = dt.getRowList();
        Iterator<DataRow> it = rows.iterator();
        Integer[] rowList = {0,0,0,0,0,0,0,0,0,0,0,0};
		while(it.hasNext()){
			DataRow dr = (DataRow) it.next();
			Arrays.fill(rowList, NumberUtils.toInt(dr.getString("MON"))-1, NumberUtils.toInt(dr.getString("MON")), dr.getInt("CNT"));
		}
		return rowList;
	}
	
	public List<String> getTagList(){
    	QueryAdapter rq = new QueryAdapter();
    	DataTable dt = rq.executeQuery("SELECT DISTINCT a12 FROM ZL_SERVICE_REQUEST_NODE");
    	List<DataRow> rowList = dt.getRowList();
    	List<String> returnList =new ArrayList<String>();
    	for(int i=0;i<rowList.size();i++) {
    		String tag  = rowList.get(i).getString(0);
    		returnList.add(tag);
    	}
		return returnList;
	}

	@Override
	public boolean checkNodeRepeat(ViceRequestNode viceRequestNode) {
		List<ViceRequestNode> list = viceRequestNodeBakDao.find("from ViceRequestNode where a12 = ? and  pid <> ?", viceRequestNode.getA12(),viceRequestNode.getPid());
		if(list.size()>0){
			return false;
		}
		return true;
	}

}
