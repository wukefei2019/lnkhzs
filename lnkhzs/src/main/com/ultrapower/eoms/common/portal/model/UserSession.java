package com.ultrapower.eoms.common.portal.model;

import java.io.Serializable;
import java.util.Map;

public class UserSession implements Serializable
{
	private static final long serialVersionUID = 6511158454591354476L;
	
	private String pid;//用户ID
	private String loginName;//用户登录名
	private String fullName;//用户全名
	private String pwd;//用户密码
	private String position;//用户职位
	private String type;//用户类型
	private String mobile;//用户手机
	private String phone;//用户固话
	private String fax;//用户传真
	private String email;//用户E_mail
	private String image;//用户头像
	private String orderNum;//排序值
	private String locationZone;//片区
	private String companyId;//公司ID
	private String companyName;//公司名称
	private String depId;//部门ID
	private String depName;//部门名称
	private String depDns;//部门DNS
	private String depFullName;//部门全
	private int deptType=0; //0 省公司 1 地市公司 2 代维公司
	
	private String groupId;//所属组ID:多个组以","隔开
	private String groupName;//所属组名称:多个组以","隔开
	private String ptdepId;//兼职部门ID:多个兼职部门以","隔开
	private String ptdepName;//兼职部门名称:多个兼职部门以","隔开
	private String roleId;//所属角色ID:多个角色以","隔开
	private String roleName;//所属角色:多个角色以","隔开
	private String roleDns;//所属角色Dns:多个角色以","隔开
	private String isAdmin;//用来标识当前登陆人是否属于系统管理员角色 1.是 0.否
	private String creater;//创建人
	private String createTime;//创建时间
	private String lastModifier;//最后修改人
	private String lastModifyTime;//最后修改时间
	private String lastLoginTime;//最后登陆时间
	private String loginDate;//本次登录时间
	private String skinType;//系统肤色
	
	//为了优化工单查询的速度在用户登陆时将以下属性放到session中
	private String childRoleIds;//用户的角色细分id以","隔开
	private String managerChildRoleIds;//用户为管理员的角色细分id以","隔开
	private String managerGroupId;//用户为管理员的所属组ID:多个组以","隔开


//	private String jkroleId; // 监控系统-角色ID
//	private String jkdepId; // 监控系统-部门ID
//	private String jkdepName; // 监控系统-部门名称
//	private String jksubcmpid; // 监控系统-省公司二级部门或地市ID
//	private String jksubcmpname; // 监控系统-省公司二级部门或地市名称
//	private String jkcityname; // 监控系统-所在地市或省公司
	
	private String cityId;
	
	private String cityName;
	
	private String deptLevel2Id;

	public String getDeptLevel2Id() {
		return deptLevel2Id;
	}

	public void setDeptLevel2Id(String deptLevel2Id) {
		this.deptLevel2Id = deptLevel2Id;
	}

	//用户设置搜索条件
	private Map<String,String> searchCondition;
	
	public UserSession() {

	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getLocationZone() {
		return locationZone;
	}

	public void setLocationZone(String locationZone) {
		this.locationZone = locationZone;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	/**
	 * 所属组ID:多个组以","隔开
	 * @return
	 */
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 所属组ID:多个组以","隔开
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 所属组ID:多个兼职部门以","隔开
	 * @return
	 */
	public String getPtdepId() {
		return ptdepId;
	}

	public void setPtdepId(String ptdepId) {
		this.ptdepId = ptdepId;
	}

	/**
	 * 所属组ID:多个兼职部门以","隔开
	 * @return
	 */
	public String getPtdepName() {
		return ptdepName;
	}

	public void setPtdepName(String ptdepName) {
		this.ptdepName = ptdepName;
	}
	
	/**
	 * 所属角色ID:多个角色以","隔开
	 * @return
	 */
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 所属角色ID:多个角色以","隔开
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDns() {
		return roleDns;
	}

	public void setRoleDns(String roleDns) {
		this.roleDns = roleDns;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getChildRoleIds() {
		return childRoleIds;
	}

	public void setChildRoleIds(String childRoleIds) {
		this.childRoleIds = childRoleIds;
	}

	public String getManagerChildRoleIds() {
		return managerChildRoleIds;
	}

	public void setManagerChildRoleIds(String managerChildRoleIds) {
		this.managerChildRoleIds = managerChildRoleIds;
	}

	public String getManagerGroupId() {
		return managerGroupId;
	}

	public void setManagerGroupId(String managerGroupId) {
		this.managerGroupId = managerGroupId;
	}



	public String getDepDns() {
		return depDns;
	}

	public void setDepDns(String depDns) {
		this.depDns = depDns;
	}

	public String getDepFullName() {
		return depFullName;
	}

	public void setDepFullName(String depFullName) {
		this.depFullName = depFullName;
	}

	public Map<String, String> getSearchCondition()
	{
		return searchCondition;
	}

	public void setSearchCondition(Map<String, String> searchCondition)
	{
		this.searchCondition = searchCondition;
	}

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

    public int getDeptType() {
        return deptType;
    }

    public void setDeptType(int deptType) {
        this.deptType = deptType;
    }

    
//    public String getJkroleId() {
//		return jkroleId;
//	}
//
//	public void setJkroleId(String jkroleId) {
//		this.jkroleId = jkroleId;
//	}
//
//	public String getJkdepId() {
//		return jkdepId;
//	}
//
//	public void setJkdepId(String jkdepId) {
//		this.jkdepId = jkdepId;
//	}
//
//	public String getJkdepName() {
//		return jkdepName;
//	}
//
//	public void setJkdepName(String jkdepName) {
//		this.jkdepName = jkdepName;
//	}
//
//	public String getJksubcmpid() {
//		return jksubcmpid;
//	}
//
//	public void setJksubcmpid(String jksubcmpid) {
//		this.jksubcmpid = jksubcmpid;
//	}
//
//	public String getJksubcmpname() {
//		return jksubcmpname;
//	}
//
//	public void setJksubcmpname(String jksubcmpname) {
//		this.jksubcmpname = jksubcmpname;
//	}
//
//	public String getJkcityname() {
//		return jkcityname;
//	}
//
//	public void setJkcityname(String jkcityname) {
//		this.jkcityname = jkcityname;
//	}

}
