package edu.fjnu.cr.service;

import java.util.List;
import java.util.Map;


import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.domain.custom.SysPermissionCustom;

public interface SysService {
	/**
	 * 根据用户账号查询用户信息
	 * @param userCode 用户账号
	 * @return 返回用户信息
	 * @throws Exception
	 */
	SysUser findSysUserByCode(String userCode) throws Exception;
	
	/**
	 * 根据用户ID查询用户菜单
	 * @param userId 用户id
	 * @return 返回用户菜单
	 * @throws Exception
	 */
	List<SysPermission> findUserMenuById(String userId) throws Exception;
	
	/**
	 * 根据用户ID查询用户权限
	 * @param userId 用户id
	 * @return 返回用户权限
	 * @throws Exception
	 */
	List<SysPermission>findUserPermissonById(String userId) throws Exception; 

	/**
	 * 加载用用户菜单
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> loadMenuTree(String userId) throws Exception;

	/**
	 * 根据首页菜单传回的id，查询用户菜单信息，再根据菜单信息查询查询用户权限
	 * @param permissionVo
	 * @return
	 * @throws Exception
	 */
	List<SysPermissionCustom> findUserPermissionByMuneId(Long id) throws Exception;
	
}
