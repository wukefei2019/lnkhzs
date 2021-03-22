package com.ultrapower.eoms.common.core.component.tree.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
import com.ultrapower.omcs.utils.StringUtils;

/**
 * 人员部门树
 * @author zhuzhaohui E-mail:zhuzhaohui@ultrapower.com.cn
 * @version Jun 4, 2010 10:00:03 AM
 */
public class UserDepZTreeImpl extends ZTreeManager {

    @Override
    public List<ZtreeBean> getChild() {
        ZtreeBean bean = new ZtreeBean();
        bean.setpId("0");
        bean.setParent(true);
        List<ZtreeBean> children = getChild(bean);
        return children;

    }

    /**
     * [获取父节点下的部门]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     * @see com.ultrapower.eoms.common.core.component.tree.manager.ZTreeManager#getChild(com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean, boolean)
     * @param bean
     * @param isParent
     * @return.
     */
    @Override
    public List<ZtreeBean> getChild(ZtreeBean bean) {
        QueryAdapter nsgl = new QueryAdapter();
        List<ZtreeBean> dtreeList = new ArrayList<ZtreeBean>();
        StringBuffer sql = new StringBuffer();
        sql.append("select pid, depname, parentid,deptype from bs_t_sm_dep t where status = 1");
        if (bean != null) {
            if (StringUtils.isNotBlank(bean.getName())) {
                sql.append(" and depname = '" + bean.getName() + "'");
            }
            if (StringUtils.isNotBlank(bean.getpId())) {
                sql.append(" start with  parentid = '" + bean.getpId() + "' connect by  prior pid =  parentid ");
//                sql.append(" and parentid = '" + bean.getpId() + "'");
            }
        }

        sql.append(" order by ordernum");
        DataTable datatable = nsgl.executeQuery(sql.toString(), null, 0, 0, 2);
        int datatableLen = 0;
        if (datatable != null)
            datatableLen = datatable.length();
        DataRow dataRow;
        ZtreeBean zTreeBean;
        Iterator<DataRow> it = datatable.getRowList().iterator();
        Map<String, Long> childCount = new HashMap<String, Long>();
        while (it.hasNext()) {
            DataRow row = (DataRow) it.next();
            Long count = childCount.get(row.getString("parentid"));
            if (count == null) {
                childCount.put(row.getString("parentid"), 0L);
            } else {
                childCount.put(row.getString("parentid"), count + 1);
            }
        }

        for (int row = 0; row < datatableLen; row++) {
            dataRow = datatable.getDataRow(row);
            zTreeBean = new ZtreeBean();
            zTreeBean.setId(StringUtils.nvl(dataRow.getString("pid"), ""));
            String parentid = StringUtils.nvl(dataRow.getString("parentid"), "");
            zTreeBean.setpId(parentid);
            zTreeBean.setName(dataRow.getString("depname"));
            zTreeBean.setOpen(false);
            zTreeBean.setIconSkin("pIcon01");
            zTreeBean.setLeaf(false);
            zTreeBean.setCount(2L);
            zTreeBean.setParent(bean.isParent());
            dtreeList.add(zTreeBean);
        }
        return dtreeList;
    }
    
    
    @Override
    public List<ZtreeBean> getAllLevel2Dept() {
        QueryAdapter nsgl = new QueryAdapter();
        List<ZtreeBean> dtreeList = new ArrayList<ZtreeBean>();
        StringBuffer sql = new StringBuffer();
        sql.append("select pid, depname, parentid,deptype from bs_t_sm_dep t where status = 1 and (parentid ='31000000' or parentid='0') ");
       

        sql.append(" order by ordernum");
        DataTable datatable = nsgl.executeQuery(sql.toString(), null, 0, 0, 2);
        int datatableLen = 0;
        if (datatable != null)
            datatableLen = datatable.length();
        DataRow dataRow;
        ZtreeBean zTreeBean;
        Iterator<DataRow> it = datatable.getRowList().iterator();
        Map<String, Long> childCount = new HashMap<String, Long>();
        while (it.hasNext()) {
            DataRow row = (DataRow) it.next();
            Long count = childCount.get(row.getString("parentid"));
            if (count == null) {
                childCount.put(row.getString("parentid"), 0L);
            } else {
                childCount.put(row.getString("parentid"), count + 1);
            }
        }

        for (int row = 0; row < datatableLen; row++) {
            dataRow = datatable.getDataRow(row);
            zTreeBean = new ZtreeBean();
            zTreeBean.setId(StringUtils.nvl(dataRow.getString("pid"), ""));
            String parentid = StringUtils.nvl(dataRow.getString("parentid"), "");
            zTreeBean.setpId(parentid);
            zTreeBean.setName(dataRow.getString("depname"));
            zTreeBean.setOpen(false);
            zTreeBean.setIconSkin("pIcon01");
            zTreeBean.setLeaf(false);
            zTreeBean.setCount(2L);
            zTreeBean.setParent(true);
            dtreeList.add(zTreeBean);
        }
        return dtreeList;
    }
    /**
     * [获取父节点下的地市部门或省本部门]
     * @author:王磊 wanglei8@ultrapower.com.cn
     * @see com.ultrapower.eoms.common.core.component.tree.manager.ZTreeManager#getChild(com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean, boolean)
     * @param bean
     * @param isParent
     * @return.
     */
    @Override
    public List<ZtreeBean> getProOrCityChild(ZtreeBean bean, String flag) {
        QueryAdapter nsgl = new QueryAdapter();
        List<ZtreeBean> dtreeList = new ArrayList<ZtreeBean>();
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
        	// 省本部或地市部门
        	if (StringUtils.isNotBlank(bean.getpId()) && ("1".equals(flag) || "2".equals(flag))) {
                sql.append("select pid, depname, parentid,deptype,ordernum from bs_t_sm_dep t where status = 1 and pid = '" + bean.getpId() + "' union ");
        	}
        	
            sql.append("select pid, depname, parentid,deptype,ordernum from bs_t_sm_dep t where status = 1");
            
            if (StringUtils.isNotBlank(bean.getName())) {
                sql.append(" and depname = '" + bean.getName() + "'");
            }
            	
            if (StringUtils.isNotBlank(bean.getpId())) {
	        	if ("2".equals(flag)){							// 地市部门
	        		sql.append(" start with  pid in ("
	        				+ "select p.pid from bs_t_sm_dep p, bs_t_sm_dicitem m where m.dtcode='citytype' and p.depdns=m.divalue"
	        				+ ") connect by  prior pid =  parentid ");
	        	} else if ("1".equals(flag)){					// 省本部
	                sql.append(" start with  pid in ("
	                		+ " select p.pid from bs_t_sm_dep p "
	                		+ " where p.PARENTID='" + bean.getpId() + "' and p.depdns not in ("
	                		+ " select m.divalue from bs_t_sm_dicitem m where m.dtcode='citytype'"
							+ " ) "
	                		+ ") connect by  prior pid =  parentid ");
	        	} else {										// 全省
	        		sql.append(" start with  pid = '" + bean.getpId() + "' connect by  prior pid =  parentid ");
	        	}
            }
//          sql.append(" and parentid = '" + bean.getpId() + "'");
        }

        sql.append(" order by ordernum");
        DataTable datatable = nsgl.executeQuery(sql.toString(), null, 0, 0, 2);
        int datatableLen = 0;
        if (datatable != null)
            datatableLen = datatable.length();
        DataRow dataRow;
        ZtreeBean zTreeBean;
        Iterator<DataRow> it = datatable.getRowList().iterator();
        Map<String, Long> childCount = new HashMap<String, Long>();
        while (it.hasNext()) {
            DataRow row = (DataRow) it.next();
            Long count = childCount.get(row.getString("parentid"));
            if (count == null) {
                childCount.put(row.getString("parentid"), 0L);
            } else {
                childCount.put(row.getString("parentid"), count + 1);
            }
        }

        for (int row = 0; row < datatableLen; row++) {
            dataRow = datatable.getDataRow(row);
            zTreeBean = new ZtreeBean();
            zTreeBean.setId(StringUtils.nvl(dataRow.getString("pid"), ""));
            String parentid = StringUtils.nvl(dataRow.getString("parentid"), "");
            zTreeBean.setpId(parentid);
            zTreeBean.setName(dataRow.getString("depname"));
            zTreeBean.setOpen(false);
            zTreeBean.setIconSkin("pIcon01");
            zTreeBean.setLeaf(false);
            zTreeBean.setCount(2L);
            zTreeBean.setParent(bean.isParent());
            dtreeList.add(zTreeBean);
        }
        return dtreeList;
    }
    
    /**
     * [获取父节点下的地市部门或省本部门_单个部门]
     * @author:王磊 wanglei8@ultrapower.com.cn
     * @see com.ultrapower.eoms.common.core.component.tree.manager.ZTreeManager#getChild(com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean, boolean)
     * @param bean
     * @param isParent
     * @return.
     */
    @Override
    public List<ZtreeBean> getProOrCityOneChild(ZtreeBean bean, String flag, String depdns) {
        QueryAdapter nsgl = new QueryAdapter();
        List<ZtreeBean> dtreeList = new ArrayList<ZtreeBean>();
        StringBuffer sql = new StringBuffer();
        if (bean != null) {
        	
            sql.append("select pid, depname, parentid,deptype,ordernum from bs_t_sm_dep t where status = 1");
            
            if (StringUtils.isNotBlank(depdns)) {
            	sql.append(" start with  pid in ("
                		+ " select p.pid from bs_t_sm_dep p where p.depdns='" + depdns + "'"
                		+ ") connect by  prior pid =  parentid ");
            } else {
            	sql.append(" start with  pid = '" + bean.getpId() + "' connect by  prior pid =  parentid ");
            }	
            	
        }

        sql.append(" order by ordernum");
        DataTable datatable = nsgl.executeQuery(sql.toString(), null, 0, 0, 2);
        int datatableLen = 0;
        if (datatable != null)
            datatableLen = datatable.length();
        DataRow dataRow;
        ZtreeBean zTreeBean;
        Iterator<DataRow> it = datatable.getRowList().iterator();
        Map<String, Long> childCount = new HashMap<String, Long>();
        while (it.hasNext()) {
            DataRow row = (DataRow) it.next();
            Long count = childCount.get(row.getString("parentid"));
            if (count == null) {
                childCount.put(row.getString("parentid"), 0L);
            } else {
                childCount.put(row.getString("parentid"), count + 1);
            }
        }

        for (int row = 0; row < datatableLen; row++) {
            dataRow = datatable.getDataRow(row);
            zTreeBean = new ZtreeBean();
            zTreeBean.setId(StringUtils.nvl(dataRow.getString("pid"), ""));
            String parentid = StringUtils.nvl(dataRow.getString("parentid"), "");
            zTreeBean.setpId(parentid);
            zTreeBean.setName(dataRow.getString("depname"));
            zTreeBean.setOpen(false);
            zTreeBean.setIconSkin("pIcon01");
            zTreeBean.setLeaf(false);
            zTreeBean.setCount(2L);
            zTreeBean.setParent(bean.isParent());
            dtreeList.add(zTreeBean);
        }
        return dtreeList;
    }
    
	@Override
	public Set<String> getDeptPerson(String fullName) {
		//查找人员
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct u.pid,u.loginname,u.fullname,u.status,u.depid, d.depname from bs_t_sm_user u, bs_t_sm_userdep ud ,bs_t_sm_dep d where u.pid = ud.userid  and ud.depid = d.pid  and  (u.fullname like ? or u.loginname like ?)");
		List<String> params = new ArrayList<String>();
		StringBuffer paramsSb = new StringBuffer();
		paramsSb.append("%").append(fullName).append("%");
		StringBuffer paramsSb2 = new StringBuffer();
		paramsSb2.append("%").append(fullName).append("%");
		params.add(paramsSb.toString());
		params.add(paramsSb2.toString());
		QueryAdapter qa = new QueryAdapter();
		DataTable dt = qa.executeQuery(sql.toString(), params.toArray());
		//查找的人员部门id放到里面
		Set<String> list = new HashSet<String>();
		List<DataRow> rowList = dt.getRowList();
		for(DataRow dr:rowList){
			String deptid = dr.getString("depid");
			list.add(deptid);		
		}
		return list;
	}

}
