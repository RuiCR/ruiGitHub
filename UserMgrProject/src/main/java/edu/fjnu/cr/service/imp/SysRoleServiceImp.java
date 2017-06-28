package edu.fjnu.cr.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.fjnu.cr.domain.SysRole;
import edu.fjnu.cr.domain.SysRoleExample;
import edu.fjnu.cr.domain.SysRolePermission;
import edu.fjnu.cr.domain.SysRolePermissionExample;
import edu.fjnu.cr.domain.SysUserRole;
import edu.fjnu.cr.domain.SysUserRoleExample;
import edu.fjnu.cr.exception.CustomException;
import edu.fjnu.cr.mapper.SysRoleMapper;
import edu.fjnu.cr.mapper.SysRolePermissionMapper;
import edu.fjnu.cr.mapper.SysUserRoleMapper;
import edu.fjnu.cr.service.SysRoleService;
@Service("sysRoleService")
public class SysRoleServiceImp implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Autowired
	private SysUserRoleMapper  sysUserRoleMapper;
	@Override
	public List<SysRole> querySysRole(Integer page,Integer rows,String order, String sort,SysRole sysRole) throws Exception {
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=4;
		}
		PageHelper.startPage(page, rows);
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo("r1001");
		if(sysRole!=null){
			if(sysRole.getId()!=null&&sysRole.getId()!=""){
				criteria.andIdLike("%"+sysRole.getId()+"%");
			}
			if(sysRole.getName()!=null&&sysRole.getName()!=""){
				criteria.andNameLike("%"+sysRole.getName()+"%");
			}
		}
		if(sort!=null&&sort!=""){
			example.setOrderByClause(sort+" "+order);
		}
		List<SysRole> roleList = sysRoleMapper.selectByExample(example);
		return roleList;
	}
	@Override
	public Integer cntSysRoleByExample(SysRole sysRole) throws Exception {
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo("r1001");
		if(sysRole!=null){
			if(sysRole.getId()!=null&&sysRole.getId()!=""){
				criteria.andIdLike("%"+sysRole.getId()+"%");
			}
			if(sysRole.getName()!=null&&sysRole.getName()!=""){
				criteria.andNameLike("%"+sysRole.getName()+"%");
			}
		}
		return sysRoleMapper.countByExample(example);
	}
	@Override
	public Map<String,Object> saveRole(SysRole sysRole,String role_menuData) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		sysRoleMapper.insert(sysRole);
		
		String roleId = sysRole.getId();
		String[] menuidArray = role_menuData.split(",");
		try {
			for(String menu_id:menuidArray){
				SysRolePermission rolePermission = new SysRolePermission();
				rolePermission.setId(UUID.randomUUID().toString());
				rolePermission.setSysRoleId(roleId);
				rolePermission.setSysPermissionId(menu_id);
				sysRolePermissionMapper.insert(rolePermission);
			}
			resultMap.put("opt_flag", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("opt_flag", false);
			resultMap.put("errorMsg", e.getMessage());
			throw new CustomException("添加出错，ID为"+roleId+"已经存在");
		}
		return resultMap;
	}
	@Override
	public SysRole selectSysRoleById(String role_id) throws Exception {
		
		return sysRoleMapper.selectByPrimaryKey(role_id);
	}
	@Override
	public Map<String, Object> updateRole(SysRole roleBean, String role_menuData) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		sysRoleMapper.updateByPrimaryKeySelective(roleBean);
		String roleId = roleBean.getId();
		String[] menuidArray = role_menuData.split(",");
		SysRolePermissionExample example = new SysRolePermissionExample();
		SysRolePermissionExample.Criteria criteria = example.createCriteria();
		criteria.andSysRoleIdEqualTo(roleId);
		sysRolePermissionMapper.deleteByExample(example);
		for(String menu_id:menuidArray){
			SysRolePermission rolePermission = new SysRolePermission();
			rolePermission.setId(UUID.randomUUID().toString());
			rolePermission.setSysRoleId(roleId);
			rolePermission.setSysPermissionId(menu_id);
			sysRolePermissionMapper.insert(rolePermission);
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteRole(String role_idData) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String[] roleIdArray = role_idData.split(",");
		for(String role_id:roleIdArray){
			SysUserRoleExample exampleRole =new SysUserRoleExample();
			SysUserRoleExample.Criteria criteriaRole = exampleRole.createCriteria();
			criteriaRole.andSysRoleIdEqualTo(role_id);
			List<SysUserRole> urList =  sysUserRoleMapper.selectByExample(exampleRole);
			if(urList!=null){
				resultMap.put("opt_flag", false);
				resultMap.put("errorMsg", "该角色已经被用户使用，无法删除！");
			}else{
				sysRoleMapper.deleteByPrimaryKey(role_id);
				SysRolePermissionExample example = new SysRolePermissionExample();
				SysRolePermissionExample.Criteria criteria= example.createCriteria();
				criteria.andSysRoleIdEqualTo(role_id);
				sysRolePermissionMapper.deleteByExample(example );
				resultMap.put("opt_flag", true);
			}
			
		}
		
		return resultMap;
	}
	@Override
	public List<SysRole> loadRoleTreeById(String id) throws Exception {
		if(id!=null&&id!=""){
			
		}
		return null;
	}
	@Override
	public List<SysRole> loadRoleTree() throws Exception {
		
		SysRoleExample example = new SysRoleExample();
		SysRoleExample.Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo("r1001");
		sysRoleMapper.selectByExample(example);
		return sysRoleMapper.selectByExample(example);
	}
	@Override
	public Map<String, Object> checkRole(SysRole sysRole,String test) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("opt_flag", false);
		String roleId = sysRole.getId();
		if(roleId==null||roleId==""){
			resultMap.put("errorMsg", "角色ID不能为空！");
			return resultMap;
		}
		if(test==null||test==""){
			SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
			if(role!=null){
				resultMap.put("errorMsg", "该角色ID已经存在，请重新输入！");
				return resultMap;
			}
		}
		if(sysRole.getName()==null||sysRole.getName()==""){
			resultMap.put("errorMsg", "角色名称不能为空");
			return resultMap;
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	
}
