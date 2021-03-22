package com.ultrapower.eoms.common.core.component.tree.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.tree.model.DtreeBean;
import com.ultrapower.eoms.common.core.component.tree.service.RepositorySortTypeTreeService;


public class RepositoryComplaintTypeTreeImpl extends TreeManager implements
             RepositorySortTypeTreeService {

	/**
	 * 案例库网络分类树形数据
	 * @param id
	 * @return
	 */
	public List<DtreeBean> getSortList(String id){
		
		 return null;
	}
	
	public String getSortXml(String parentid){
		if(parentid==null || "".equals(parentid))
			return "";
		List<DtreeBean> menuDtreeList = getSortList(parentid);
		return this.createDhtmlxtreeXml(menuDtreeList, parentid);
	}

}
