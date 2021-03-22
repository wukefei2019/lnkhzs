package com.ultrapower.eoms.common.core.component.tree.manager;

import java.util.List;
import java.util.Set;

import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
/**
 * 树组件api
 * @author zhuzhaohui E-mail:zhuzhaohui@ultrapower.com.cn
 * @version May 17, 2010 5:29:23 PM
 */
public abstract class ZTreeManager{
    
    public abstract List<ZtreeBean> getChild();

    public abstract List<ZtreeBean> getChild(ZtreeBean bean);

    /**
	 * 获取带部门的人员
	 * @param fullName
	 * @return
	 */
	public abstract Set<String> getDeptPerson(String fullName);

	public abstract List<ZtreeBean> getProOrCityChild(ZtreeBean bean, String flag);
	
	public abstract List<ZtreeBean> getProOrCityOneChild(ZtreeBean bean, String flag, String depdns);

	public abstract List<ZtreeBean> getAllLevel2Dept();
}
