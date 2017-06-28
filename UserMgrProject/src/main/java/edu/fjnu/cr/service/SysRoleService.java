package edu.fjnu.cr.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.cr.domain.SysRole;

public interface SysRoleService {
	/**
	 * 查询角色信息
	 * @return
	 */
	List<SysRole> querySysRole(Integer page,Integer rows,String order, String sort,SysRole sysRole) throws Exception;

	/**
	 * 查询记录总条数
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	Integer cntSysRoleByExample(SysRole sysRole) throws Exception;
	/**
	 * 添加保存角色信息
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> saveRole(SysRole sysRole,String role_menuData) throws Exception;

	/**
	 * 根据角色id查询角色信息
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	SysRole selectSysRoleById(String role_id) throws Exception;
	/**
	 * 更新角色信息
	 * @param roleBean
	 * @param role_menuData 角色拥有的权限ID
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> updateRole(SysRole roleBean,String role_menuData) throws Exception;
	
	/**
	 * 根据id删除角色，支持多条删除，但是role_menuData必须是a,b,c形式的字符串
	 * @param role_menuData
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> deleteRole(String role_idData) throws Exception;
	
	/**
	 * 根据角色id加载角色信息，ID为则空加载全部
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<SysRole> loadRoleTreeById(String id) throws Exception;
	/**
	 * j加载全部角色信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<SysRole> loadRoleTree() throws Exception;
	/**
	 * 检查角色信息,test为空检验该角色是否存在
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> checkRole(SysRole sysRole,String test) throws Exception;
}
