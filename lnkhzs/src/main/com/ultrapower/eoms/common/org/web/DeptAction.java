package com.ultrapower.eoms.common.org.web;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ultrapower.eoms.common.core.component.tree.manager.ZTreeManager;
import com.ultrapower.eoms.common.core.component.tree.model.ZtreeBean;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.RoleManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;
import com.ultrapower.omcs.base.web.BaseAction;

public class DeptAction extends BaseAction {

    /**
     * @Description:[字段功能描述]
     */
    private static final long serialVersionUID = 5975588598757630583L;

    private UserManagerService userManagerService;
    private DepManagerService depManagerService;
    private RoleManagerService roleManagerService;

    private ZTreeManager userDepZTree;

    private ZtreeBean bean;
    
    private String fullName;//搜索人员全名
    
    private String flag; // 搜索范围，0：全省；1：省本部；2：地市部门
    
    private Map<String,String> deptPerson;//带部门的人员列表
    /**
     * [部门树]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     */
    public void ajaxGetDeptTree() {
        List<ZtreeBean> list = userDepZTree.getChild(bean);
        super.renderJson(list);
    }
    
    public void ajaxGetDeptTreeLevel2() {
        List<ZtreeBean> list = userDepZTree.getAllLevel2Dept();
        super.renderJson(list);
    }

    /**
     * [部门树_地市或省本部门]
     * @author:佟广恩 tongguangen@ultrapower.com.cn
     */
    public void ajaxGetDeptTreeProOrCity() {
        List<ZtreeBean> list = userDepZTree.getProOrCityChild(bean, flag);
        super.renderJson(list);
    }
    

    public void ajaxGetDeptPerson() {
    	Set<String> deptList = new HashSet<String>();
    	if(fullName != null && StringUtils.isNotBlank(fullName)){
    		deptList = userDepZTree.getDeptPerson(fullName);
    	}
        super.renderJson(deptList);
    }
    
    public void setUserManagerService(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    public void setDepManagerService(DepManagerService depManagerService) {
        this.depManagerService = depManagerService;
    }

    public void setRoleManagerService(RoleManagerService roleManagerService) {
        this.roleManagerService = roleManagerService;
    }

    public void setUserDepZTree(ZTreeManager userDepZTree) {
        this.userDepZTree = userDepZTree;
    }

    public ZtreeBean getBean() {
        return bean;
    }

    public void setBean(ZtreeBean bean) {
        this.bean = bean;
    }

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Map<String, String> getDeptPerson() {
		return deptPerson;
	}

	public void setDeptPerson(Map<String, String> deptPerson) {
		this.deptPerson = deptPerson;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
