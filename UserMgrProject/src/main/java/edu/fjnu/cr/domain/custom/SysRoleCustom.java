package edu.fjnu.cr.domain.custom;

import edu.fjnu.cr.domain.SysRole;

public class SysRoleCustom extends SysRole{
	private String role_owner_id;

	public String getRole_owner_id() {
		return role_owner_id;
	}

	public void setRole_owner_id(String role_owner_id) {
		this.role_owner_id = role_owner_id;
	}
	
}
