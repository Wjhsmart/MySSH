package com.jh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.jh.dao.RolePermissionDAO;

public class RolePermissionServiceImpl implements RolePermissionService {
	
	private RolePermissionDAO rolePermissionDAO;

	public RolePermissionDAO getRolePermissionDAO() {
		return rolePermissionDAO;
	}

	public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
		this.rolePermissionDAO = rolePermissionDAO;
	}

	@Override
	public Collection<Permission> queryAllPermissionByRoleName(String roleName) {
		List<String> p = rolePermissionDAO.queryAllPermissionByRoleName(roleName);
		Collection<Permission> permissions = new ArrayList<Permission>();
		for (String s : p) {
			Permission permission = new WildcardPermission(s);
			permissions.add(permission);
		}
		return permissions;
	}

}
