package edu.fjnu.cr.service;

import java.util.List;
import java.util.Map;

import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.domain.SysUserRole;

public interface UserService {
	/**
	 * 查询用户信息
	 * @param page 分页的页码
	 * @param rows 每页几条
	 * @param order desc 或 asc
	 * @param sort  按排序的字段
	 * @param sysUser 
	 * @return
	 * @throws Exception
	 */
	List<SysUser> querySysUser(Integer page,Integer rows,String order, String sort,SysUser sysUser) throws Exception;
	/**
	 * 根据条件统计记录条数
	 * @param sysUser
	 * @return
	 * @throws Exception
	 */
	Integer cntSysUserByExample(SysUser sysUser) throws Exception;
	
	/**
	 * 添加用户，用户角色信息
	 * @param sysUser
	 * @param user_roleData
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> saveUser(SysUser sysUser,String user_roleData) throws Exception;
	
	/**
	 * 根据ID查询用户信息
	 * @return
	 * @throws Exception
	 */
	SysUser findSysUserByid(String userId) throws Exception;
	
	/**
	 * 根据用户id查询角色id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<SysUserRole> findRoleIdByUserId(String userId) throws Exception;
	
	Map<String,Object> updateUser(SysUser sysUser,String user_roleData) throws Exception;
	
	Map<String, Object> deleteUser(String user_idData) throws Exception;

	/**
	 * 检查用户信息。当test为空时检查用户账号是否存在
	 * @param user
	 * @param test
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> cheakUser(SysUser user,String test) throws Exception;

	Map<String,Object> cheakPwd(String id,String password) throws Exception;
}
