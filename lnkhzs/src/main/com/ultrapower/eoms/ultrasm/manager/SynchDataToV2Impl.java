package com.ultrapower.eoms.ultrasm.manager;

import java.util.List;

import com.ultrapower.eoms.ultrasm.model.DepInfo;
import com.ultrapower.eoms.ultrasm.model.RoleInfo;
import com.ultrapower.eoms.ultrasm.model.UserInfo;
import com.ultrapower.eoms.ultrasm.service.SynchDataToV2;

public class SynchDataToV2Impl implements SynchDataToV2
{

	@Override
	public int synchUserAdd(UserInfo user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchUserDel(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchUserEdit(UserInfo user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchUserPwdEdit(String userId, String newPwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepAdd(DepInfo dep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepDel(String depId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepEdit(DepInfo dep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepUserAdd(List<String> depIdList, List<String> userIdList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepUserDel(List<String> depIdList, List<String> userIdList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchDepUserDel(List<String> orgIdList, String orgType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleAdd(RoleInfo role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleDel(String roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleEdit(RoleInfo role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleOrgAdd(List<String> roleIdList, List<String> orgIdList, String orgType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleOrgDel(List<String> roleIdList, List<String> orgIdList, String orgType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int synchRoleOrgDel(List<String> objIdList, String objType) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
