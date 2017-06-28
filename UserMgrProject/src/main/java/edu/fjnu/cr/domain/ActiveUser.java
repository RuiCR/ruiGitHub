package edu.fjnu.cr.domain;

import java.util.List;



public class ActiveUser {
	private String userid;//用户Id
	private String usercode;// 用户账号
	private String userName;//用户名
	private String userPassword;//用户密码
	
	List<SysPermission> menus; //菜单
	List<SysPermission> permission;//权限
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public List<SysPermission> getMenus() {
		return menus;
	}
	public void setMenus(List<SysPermission> menus) {
		this.menus = menus;
	}
	public List<SysPermission> getPermission() {
		return permission;
	}
	public void setPermission(List<SysPermission> permission) {
		this.permission = permission;
	}
	
	
}
