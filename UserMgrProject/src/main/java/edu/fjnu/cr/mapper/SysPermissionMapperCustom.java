package edu.fjnu.cr.mapper;

import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.domain.custom.SysPermissionCustom;
import edu.fjnu.cr.domain.custom.SysPermissionVo;

import java.util.List;


public interface SysPermissionMapperCustom {
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
	 * 根据首页菜单传回的菜单信息，查询用户权限
	 * @param permissionVo
	 * @return
	 * @throws Exception
	 */
	List<SysPermissionCustom> findUserPermissionByMune(SysPermissionVo permissionVo) throws Exception;
	
	/**
	 * 根据角色id加载菜单
	 * @param role_id
	 * @return
	 */
	List<SysPermissionCustom> loadMuneTreeById(String role_id);
}