package com.ultrapower.eoms.common.portal.manager;

import org.apache.commons.lang3.StringUtils;

import com.ultrapower.eoms.common.portal.model.UserSession;
import com.ultrapower.eoms.common.portal.service.UserSessionExtService;
import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;


/**
 * UserSession设置流程管理扩展类
 * @author SunHailong
 */
public class UserSessionWorkflowExt implements UserSessionExtService
{
	private DepManagerService depManagerService;
	private UserManagerService userManagerService;

	
	private static final String HQL_CHILDROLE_FIND = "from ChildRole where charge = ?";
	public UserSession buildExtendUserSession(UserSession userSession)
	{
		if(userSession == null)
		{
			return userSession;
		}
		String loginName = userSession.getLoginName();
		userSession.setManagerChildRoleIds(queryManagerChildRoleIds(loginName));
		userSession.setChildRoleIds(queryChileRoleIds(loginName));
		userSession.setManagerGroupId(queryManagerGroupIds(userSession.getGroupId(), loginName));
		return userSession;
	}


	
	/**
	 * 获取以当前用户为管理员的角色细分id列表
	 * @param loginName
	 * @return
	 */
	private String queryManagerChildRoleIds(String loginName) {
		/*String childRoleIds = "";
		if (org.apache.commons.lang3.StringUtils.isNotBlank(loginName)) {
			
			List<ChildRole> childRoles = roleService.getChildRoleByHql(HQL_CHILDROLE_FIND,new String[]{loginName});
			if (CollectionUtils.isNotEmpty(childRoles)) {
				for (int i = 0; i < childRoles.size(); i++) {
					ChildRole childRole = childRoles.get(i);
					String childRoleId = childRole.getChildRoleId();
					childRoleIds += childRoleId;
					if (i != (childRoles.size() - 1)) {
						childRoleIds += ",";
					}
				}
			}
		}
		return childRoleIds;*/
		return null;
	}
	
	/**
	 * 获取当前用户的所有角色细分id列表
	 * @param loginName
	 * @return
	 */
	private String queryChileRoleIds(String loginName) {
		String childRoleIds = "";

		return childRoleIds;
	}
	
	/**
	 * 获取以当前用户为管理员的组id列表
	 * @param groupIds
	 * @param loginName
	 * @return
	 */
	private String queryManagerGroupIds(String groupIds, String loginName) {
		String mangerGroupIds = "";
		if (StringUtils.isNotBlank(groupIds) && StringUtils.isNotBlank(loginName)) {
			String[] arys = groupIds.split(",");
			if (arys != null && arys.length > 0) {
				for (int i = 0; i < arys.length; i++) {
					DepInfo dep = depManagerService.getDepByID(arys[i]);
					if (dep != null) {
						String pid = dep.getPid();
						String depassginee = dep.getDepassginee();
						if (loginName.equals(depassginee)) {
							mangerGroupIds += pid + ",";
						}
					}
				}
			}
		}
		if (StringUtils.isNotBlank(mangerGroupIds)) {
			mangerGroupIds = mangerGroupIds.substring(0, mangerGroupIds.length() - 1);
		}
		return mangerGroupIds;
	}

	public void setDepManagerService(DepManagerService depManagerService) {
		this.depManagerService = depManagerService;
	}


	public UserManagerService getUserManagerService() {
		return userManagerService;
	}

	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
}
