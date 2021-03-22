package com.ultrapower.eoms.common.core.component.tree.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ultrapower.eoms.common.core.component.data.DataRow;
import com.ultrapower.eoms.common.core.component.data.DataTable;
import com.ultrapower.eoms.common.core.component.data.QueryAdapter;
import com.ultrapower.eoms.common.core.component.tree.model.DtreeBean;
import com.ultrapower.eoms.common.core.component.tree.service.TaskTypeSheetTreeService;


public class TaskTypeSheetTreeImpl extends TreeManager implements TaskTypeSheetTreeService {

	/**
	 * 任务预约类型树形数据
	 * @param id
	 * @return
	 */
	public List<DtreeBean> getSortList(String id){
		 
		 return null;
	}
	
	public String getTaskTypeXml(String parentid){
		if("".equals(parentid))
			return "";
		List<DtreeBean> menuDtreeList = getSortList(parentid);
		return this.createDhtmlxtreeXml(menuDtreeList, parentid);
	}
}
