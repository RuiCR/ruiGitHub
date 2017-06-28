package edu.fjnu.cr.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.cr.domain.SysPermission;

public interface SysPermissionService {
	/**
	 * 加载所有权限
	 * @return
	 */
	List<SysPermission> loadSysPermission() throws Exception;
	/**
	 * 根据角色id查询菜单，用于角色修改时菜单回显
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> loadMenuTree(String role_id) throws Exception;
	
	/**
	 * 加载所有菜单
	 * @return
	 * @throws Exception
	 */
	Map<String,Object>  loadMenuAll() throws Exception;

	/**
	 * 添加菜单，添加权限同样适用
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> addMenu(SysPermission menu) throws Exception;
	/**
	 * 删除菜单，添加权限同样适用
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> deleteMenu(String menu_idData) throws Exception;
	/**
	 * 根据菜单id查询菜单信息
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	SysPermission findMenuById(String menu_id) throws Exception;
	/**
	 * 更新菜单信息
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> updateMenu(SysPermission menu) throws Exception;
	
	
	List<SysPermission> loadMune() throws Exception;
	
	List<Map<String, Object>> loadMenuCombotree() throws Exception;
	
	Map<String,Object> checkMenu(SysPermission menu) throws Exception;

}
