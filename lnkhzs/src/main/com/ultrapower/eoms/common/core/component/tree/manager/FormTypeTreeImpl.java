package com.ultrapower.eoms.common.core.component.tree.manager;

import java.util.ArrayList;
import java.util.List;

import com.ultrapower.eoms.common.core.component.tree.model.MenuDtree;

public class FormTypeTreeImpl extends TreeManager {
	
	/**
	 * 获得工单类别树的XML串
	 * @return
	 */
	public String getTreeXml()
	{
		List<MenuDtree> menuDtree = createDtree();
		return this.createDhtmlxtreeXml(menuDtree);
	}
	public List<MenuDtree> createDtree()
	{
		List<MenuDtree> lst = new ArrayList<MenuDtree>();

		return lst;
	}
}
