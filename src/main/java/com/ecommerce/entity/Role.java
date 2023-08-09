package com.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	private String roleName;
	private String RoleDescription;
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDescription() {
		return RoleDescription;
	}
	
	public void setRoleDescription(String roleDescription) {
		RoleDescription = roleDescription;
	}
	

}
