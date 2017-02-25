package com.jh.shiro;

import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import com.jh.service.RolePermissionService;

public class MyRolePermissionResolver implements RolePermissionResolver {
	
	private RolePermissionService rolePermissionService;

	public RolePermissionService getRolePermissionService() {
		return rolePermissionService;
	}

	public void setRolePermissionService(RolePermissionService rolePermissionService) {
		this.rolePermissionService = rolePermissionService;
	}

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleName) {
		System.out.println(roleName);
		return rolePermissionService.queryAllPermissionByRoleName(roleName);
	}

}
