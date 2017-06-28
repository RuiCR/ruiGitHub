package edu.fjnu.cr.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.domain.SysUserExample;
import edu.fjnu.cr.domain.custom.SysPermissionCustom;
import edu.fjnu.cr.domain.custom.SysPermissionVo;
import edu.fjnu.cr.mapper.SysPermissionMapper;
import edu.fjnu.cr.mapper.SysPermissionMapperCustom;
import edu.fjnu.cr.mapper.SysUserMapper;
import edu.fjnu.cr.service.SysService;

@Service("sysService")
public class SysServiceImp implements SysService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysPermissionMapperCustom sysPermissionMapperCustom;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Override
	public SysUser findSysUserByCode(String userCode) throws Exception{
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andUsercodeEqualTo(userCode);
		List<SysUser> list = sysUserMapper.selectByExample(sysUserExample);
		if(list!=null&list.size()==1){
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<SysPermission> findUserMenuById(String userId) throws Exception {
		
		return sysPermissionMapperCustom.findUserMenuById(userId);
	}
	@Override
	public List<SysPermission> findUserPermissonById(String userId) throws Exception {
		
		return sysPermissionMapperCustom.findUserPermissonById(userId);
	}
	@Override
	public List<Map<String, Object>> loadMenuTree(String userId) throws Exception {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> treeNode = null;
		Map<String, Map<String, Object>> id_nodeMap = new HashMap<String, Map<String, Object>>();
		
		List<SysPermission> menus = sysPermissionMapperCustom.findUserMenuById(userId);
		for(SysPermission menu:menus){
			String parentid = menu.getParentid();
			treeNode = new HashMap<String, Object>();
			treeNode.put("id", menu.getId());
			treeNode.put("text", menu.getName());
			treeNode.put("menuUrl", menu.getUrl());
			String menu_id = menu.getId()+"";
			id_nodeMap.put(menu_id, treeNode);
			if (menu.getParentid().equals("0")) {
				treeList.add(treeNode);
			} else {
				Map<String, Object> parenNode = id_nodeMap.get(menu.getParentid());
				if (parenNode != null) {
					List<Map<String, Object>> childList = null;
					if (parenNode.get("children") == null) {
						childList = new ArrayList<Map<String, Object>>();
					} else {
						childList = (List<Map<String, Object>>) parenNode
								.get("children");
					}
					childList.add(treeNode);
					parenNode.put("children", childList);
				}
			}
		}
		return treeList;
	}
	@Override
	public List<SysPermissionCustom> findUserPermissionByMuneId( Long id)
			throws Exception {
		SysPermissionVo permissionVo = new SysPermissionVo();
		SysPermission permission = sysPermissionMapper.selectByPrimaryKey(id);
		permissionVo.setSysPermission(permission);
		System.out.println(permissionVo.getSysPermission().getId());
		return sysPermissionMapperCustom.findUserPermissionByMune(permissionVo);
	}
	
	

}
