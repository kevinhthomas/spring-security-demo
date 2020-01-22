package com.kthomas.springsecurity.demo.dao;

import com.kthomas.springsecurity.demo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
}
