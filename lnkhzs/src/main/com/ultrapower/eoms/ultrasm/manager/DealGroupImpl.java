package com.ultrapower.eoms.ultrasm.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.eoms.common.core.util.TimeUtils;
import com.ultrapower.eoms.ultrasm.model.DealGroup;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.DealGroupService;
import com.ultrapower.eoms.ultrasm.service.DepManagerService;
import com.ultrapower.eoms.ultrasm.service.UserManagerService;

@Transactional
public class DealGroupImpl implements DealGroupService {
	
	private static Logger log = LoggerFactory.getLogger(DealGroupImpl.class);
	private IDao<DealGroup> dealGroupDao;
	private DepManagerService depManagerService;
	private UserManagerService userManagerService;

	public void deleteById(String id) {
		dealGroupDao.removeById(id);
	}

	public List<DealGroup> getAll() {
		return dealGroupDao.getAll();
	}

	public DealGroup getById(String id) {
		return dealGroupDao.get(id);
	}

	/**
	 * 获取当前用户所有的同组操作组和同组角色细分
	 */
	public List<DealGroup> getDealGroupByUser(String userLoginName) {
		Set<DealGroup> rtn = new HashSet<DealGroup>();
		List<DealGroup> dealGroups = getAll();

		return new ArrayList<DealGroup>(rtn);
	}
	
	/**
	 * 获取同组操作组中的所有用户
	 */
	public List<UserInfo> getDealGroupUsers(DealGroup dealGroup) {
		Set<UserInfo> users = new HashSet<UserInfo>();
		if (dealGroup != null) {
			String groupId = dealGroup.getGroupId();
			String groupType = dealGroup.getGroupType();
			
		}
		return new ArrayList<UserInfo>(users);
	}

	/**
	 * 获取当前用户所在的所有同组操作组中的所有用户病以工单状态进行过滤
	 */
	public List<UserInfo> getDealGroupUsers(String userLoginName, String entryState) {
		Set<UserInfo> users = new HashSet<UserInfo>();
		List<DealGroup> groups = getDealGroupByUser(userLoginName);
		if (CollectionUtils.isNotEmpty(groups)) {
			for (int i = 0; i < groups.size(); i++) {
				DealGroup dealGroup = groups.get(i);
				String groupId = dealGroup.getGroupId();
				String groupType = dealGroup.getGroupType();
				String es = dealGroup.getEntryState();
				if (StringUtils.isNotBlank(es) && es.indexOf(entryState) == -1) {
					continue;
				}
				log.info(userLoginName + " 同组操作id="+groupId+",type=" + groupType);
				users.addAll(getDealGroupUsers(dealGroup));
			}
		}
		return new ArrayList<UserInfo>(users);
	}

	public void saveOrUpdate(DealGroup dealGroup) {
		if (StringUtils.isBlank(dealGroup.getId())) {
			dealGroup.setId(null);
			dealGroup.setCreateTime(TimeUtils.getCurrentTime());
		}
		dealGroupDao.saveOrUpdate(dealGroup);
	}

	public IDao<DealGroup> getDealGroupDao() {
		return dealGroupDao;
	}

	public void setDealGroupDao(IDao<DealGroup> dealGroupDao) {
		this.dealGroupDao = dealGroupDao;
	}

	public DepManagerService getDepManagerService() {
		return depManagerService;
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
