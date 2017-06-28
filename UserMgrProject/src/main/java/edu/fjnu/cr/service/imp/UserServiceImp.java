package edu.fjnu.cr.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.domain.SysUserExample;
import edu.fjnu.cr.domain.SysUserRole;
import edu.fjnu.cr.domain.SysUserRoleExample;
import edu.fjnu.cr.mapper.SysUserMapper;
import edu.fjnu.cr.mapper.SysUserRoleMapper;
import edu.fjnu.cr.service.UserService;
import edu.fjnu.cr.util.MD5;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Override
	public List<SysUser> querySysUser(Integer page, Integer rows,String order, String sort, SysUser sysUser) throws Exception {
		if(page==null){
			page=1;
		}
		if(rows==null){
			rows=5;
		}
		PageHelper.startPage(page, rows);
		//String userId=sysUser.getId();
		String userCode=sysUser.getUsercode();
		String userName=sysUser.getUsername();
		Integer department = sysUser.getDepartment();
		SysUserExample  example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo("u1001");
		if(sysUser!=null){
			if(userCode!=null&&userCode!=""){
				criteria.andUsercodeLike("%"+userCode+"%");
			}
			if(userName!=null&&userName!=""){
				criteria.andUsercodeLike("%"+userName+"%");
			}
			if(department!=null){
				criteria.andDepartmentEqualTo(department);
			}
		}
		if(sort!=null&&sort!=""){
			example.setOrderByClause(sort+" "+order);
		}
		List<SysUser> userList = sysUserMapper.selectByExample(example);
		return userList;
	}
	@Override
	public Integer cntSysUserByExample(SysUser sysUser) throws Exception {
		String userCode=sysUser.getUsercode();
		String userName=sysUser.getUsername();
		SysUserExample  example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andIdNotEqualTo("u1001");
		if(sysUser!=null){
			if(userCode!=null&&userCode!=""){
				criteria.andUsercodeLike("%"+userCode+"%");
			}
			if(userName!=null&&userName!=""){
				criteria.andUsercodeLike("%"+userName+"%");
			}
		}
		return sysUserMapper.countByExample(example);
	}
	@Override
	public Map<String, Object> saveUser(SysUser sysUser, String user_roleData) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		sysUserMapper.insert(sysUser);
		String userId = sysUser.getId();
		String[] roleIdArray = user_roleData.split(",");
		for(String roleId:roleIdArray){
			SysUserRole userRole = new SysUserRole();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setSysUserId(userId);
			userRole.setSysRoleId(roleId);
			sysUserRoleMapper.insertSelective(userRole);
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public SysUser findSysUserByid(String userId) throws Exception {
		
		return sysUserMapper.selectByPrimaryKey(userId);
	}
	@Override
	public List<SysUserRole> findRoleIdByUserId(String userId) throws Exception {
		SysUserRoleExample example = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andSysUserIdEqualTo(userId);
		return sysUserRoleMapper.selectByExample(example);
	}
	@Override
	public Map<String, Object> updateUser(SysUser sysUser, String user_roleData) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		sysUserMapper.updateByPrimaryKey(sysUser);
		String userId = sysUser.getId();

		if(user_roleData!=null&&user_roleData!=""){
			SysUserRoleExample example = new SysUserRoleExample();
			SysUserRoleExample.Criteria criteria = example.createCriteria();
			criteria.andSysUserIdEqualTo(userId);
			sysUserRoleMapper.deleteByExample(example);
			String[] roleIdArray = user_roleData.split(","); 
			for(String roleId:roleIdArray){
				SysUserRole userRole = new SysUserRole();
				userRole.setId(UUID.randomUUID().toString());
				userRole.setSysUserId(userId);
				userRole.setSysRoleId(roleId);
				sysUserRoleMapper.insertSelective(userRole);
			}
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteUser(String user_idData) throws Exception {
		Map<String, Object>  resultMap = new HashMap<>();
		String[] user_IdArray = user_idData.split(",");
		for(String user_id:user_IdArray){
			sysUserMapper.deleteByPrimaryKey(user_id);
			SysUserRoleExample example = new SysUserRoleExample();
			SysUserRoleExample.Criteria criteria = example.createCriteria();
			criteria.andSysUserIdEqualTo(user_id);
			sysUserRoleMapper.deleteByExample(example);
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public Map<String, Object> cheakUser(SysUser user,String test) throws Exception {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("opt_flag", false);
		String usercode = user.getUsercode();
		if(usercode==null||usercode==""){
			resultMap.put("errorMsg", "用户账号不能为空！");
			return resultMap;
		}
		if(test==null||test==""){
			SysUserExample example = new SysUserExample();
			SysUserExample.Criteria criteria = example.createCriteria();
			criteria.andUsercodeEqualTo(usercode);
			List<SysUser> dbUser = sysUserMapper.selectByExample(example);
			if(dbUser!=null&&dbUser.size()>0){
				resultMap.put("errorMsg", "该用户账号已经存在！");
				return resultMap;
			}
		}
		if(user.getUsername()==null||user.getUsername()==""){
			resultMap.put("errorMsg", "用户名称不能为空！");
			return resultMap;
		}
		if(user.getPosition()==null||user.getPosition()==""){
			resultMap.put("errorMsg", "用户职位不能为空！");
			return resultMap;
		}
		if(user.getTelephone()==null||user.getTelephone()==""){
			resultMap.put("errorMsg", "用户联系电话不能为空！");
			return resultMap;
		}
		if(user.getDepartment()==null){
			resultMap.put("errorMsg", "用户所属部门不能为空！");
			return resultMap;
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public Map<String, Object> cheakPwd(String id, String password) throws Exception {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("opt_flag", false);
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
		String dbPwd = sysUser.getPassword();
		String salt = sysUser.getSalt();
		String pwd = new MD5().getMD5ofStr(salt+password);
		if(!dbPwd.equals(pwd)){
			resultMap.put("errorMsg", "密码错误！");
			return resultMap;
		}
		resultMap.put("opt_flag", true);
		return resultMap;
	}

}
