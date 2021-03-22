package com.ultrapower.eoms.ultrasm.service;

import java.util.List;

import com.ultrapower.eoms.ultrasm.model.DealGroup;
import com.ultrapower.eoms.ultrasm.model.UserInfo;

/**
 * 同组操作逻辑接口
 * @author Administrator
 *
 */
public interface DealGroupService {
	
	public void saveOrUpdate(DealGroup dealGroup);
	
	public void deleteById(String id);
	
	public DealGroup getById(String id);
	
	public List<DealGroup> getAll();
	
	/**
	 * 获取当前用户所有的同组操作组和同组角色细分
	 * @param userLoginName 登录名
	 * @return
	 */
	public List<DealGroup> getDealGroupByUser(String userLoginName);
	
	/**
	 * 获取同组操作组中的所有用户
	 * @param dealGroup 登录名
	 * @return
	 */
	public List<UserInfo> getDealGroupUsers(DealGroup dealGroup);
	
	/**
	 * 获取当前用户所在的所有同组操作组中的所有用户病以工单状态进行过滤
	 * @param userLoginName 登录名
	 * @param entryState 工单状态
	 * @return
	 */
	public List<UserInfo> getDealGroupUsers(String userLoginName, String entryState);
	
}
