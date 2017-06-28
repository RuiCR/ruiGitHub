package edu.fjnu.cr.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.domain.SysPermissionExample;
import edu.fjnu.cr.domain.SysRolePermission;
import edu.fjnu.cr.domain.SysRolePermissionExample;
import edu.fjnu.cr.domain.custom.SysPermissionCustom;
import edu.fjnu.cr.exception.CustomException;
import edu.fjnu.cr.mapper.SysPermissionMapper;
import edu.fjnu.cr.mapper.SysPermissionMapperCustom;
import edu.fjnu.cr.mapper.SysRolePermissionMapper;
import edu.fjnu.cr.service.SysPermissionService;
@Service("sysPermissionService")
public class SysPermissionServiceImp implements SysPermissionService {
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysPermissionMapperCustom sysPermissionMapperCustom;
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	@Override
	public List<SysPermission> loadSysPermission() throws Exception {
		SysPermissionExample sysPermissionExample = new SysPermissionExample();
		SysPermissionExample.Criteria criteria = sysPermissionExample.createCriteria();
		List<SysPermission> permissionList = sysPermissionMapper.selectByExample(sysPermissionExample);
		return permissionList;
	}
	@Override
	public List<Map<String, Object>> loadMenuTree(String role_id) throws Exception {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> treeNode = null;
		Map<String, Map<String, Object>> id_nodeMap = new HashMap<String, Map<String, Object>>();
		if(role_id!=null&&role_id!=""){
			List<SysPermissionCustom> menus = sysPermissionMapperCustom.loadMuneTreeById(role_id);
			for(SysPermissionCustom menu:menus){
				String parentid = menu.getParentid();
				treeNode = new HashMap<String, Object>();
				treeNode.put("id", menu.getId());
				treeNode.put("text", menu.getName());
				String menu_id = menu.getId()+"";
				id_nodeMap.put(menu_id, treeNode);
				if(menu.getRole_muneid()!=null&&menu.getRole_muneid()!=""&&!menu.getParentid().equals("0")){
					treeNode.put("checked", true);
				}else
				{
					treeNode.put("checked", false);
				}
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
		}else{
			SysPermissionExample example = new SysPermissionExample();
			List<SysPermission> menus = sysPermissionMapper.selectByExample(example);
			for(SysPermission menu:menus){
				String parentid = menu.getParentid();
				treeNode = new HashMap<String, Object>();
				treeNode.put("id", menu.getId());
				treeNode.put("text", menu.getName());
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
		
		
	}
	@Override
	public Map<String,Object> loadMenuAll() throws Exception {
		
		List<SysPermission> menuList = this.loadMune();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<>();
		for(SysPermission menu:menuList){
			Map<String,Object> rowMap = new HashMap<String,Object>();
			rowMap.put("id", menu.getId());
			rowMap.put("name", menu.getName());
			rowMap.put("type", menu.getType());
			rowMap.put("url", menu.getUrl());
			rowMap.put("percode", menu.getPercode());
			String parentId = menu.getParentid();
			if(!parentId.equals("0")){
				rowMap.put("_parentId", parentId);
			}
			rowMap.put("parentid", parentId);
			rowMap.put("parentids", menu.getParentids());
			rowMap.put("sortstring", menu.getSortstring());
			rowMap.put("available", menu.getAvailable());
			rowMap.put("grade", menu.getGrade());
			dataList.add(rowMap);
		}
		
		dataMap.put("total", dataList.size());
		dataMap.put("rows", dataList);
		return dataMap;
	}
	@Override
	public Map<String, Object> addMenu(SysPermission menu) throws Exception {
		Map<String, Object> resultMap =new HashMap<>();
		sysPermissionMapper.insert(menu);
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public Map<String, Object> deleteMenu(String menu_idData) throws Exception {
		Map<String, Object> resultMap =new HashMap<>();
		String[] menu_idArray = menu_idData.split(",");
		for(String menu_id:menu_idArray){
			
			SysRolePermissionExample example = new SysRolePermissionExample();
			SysRolePermissionExample.Criteria criteria = example.createCriteria();
			criteria.andSysPermissionIdEqualTo(menu_id);
			List<SysRolePermission> role_menu = sysRolePermissionMapper.selectByExample(example);
			if(role_menu!=null){
				resultMap.put("errorMsg", "该菜单已经被角色使用，无法删除！");
				resultMap.put("opt_flag", false);
				//throw new CustomException("该菜单已经被角色使用，无法删除！");
			}else{
				sysPermissionMapper.deleteByPrimaryKey(Long.parseLong(menu_id));
				resultMap.put("opt_flag", true);
			}
		}
		
		return resultMap;
	}
	@Override
	public SysPermission findMenuById(String menu_id) throws Exception {
		
		return sysPermissionMapper.selectByPrimaryKey(Long.parseLong(menu_id));
	}
	@Override
	public Map<String, Object> updateMenu(SysPermission menu) throws Exception {
		Map<String, Object> resultMap =new HashMap<>();
		sysPermissionMapper.updateByPrimaryKey(menu);
		resultMap.put("opt_flag", true);
		return resultMap;
	}
	@Override
	public List<SysPermission> loadMune() throws Exception {
		SysPermissionExample example = new SysPermissionExample();
		SysPermissionExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo("menu");
		List<SysPermission> menuList = sysPermissionMapper.selectByExample(example );
		return menuList;
	}
	@Override
	public List<Map<String, Object>> loadMenuCombotree() throws Exception {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> treeNode = null;
		Map<String, Map<String, Object>> id_nodeMap = new HashMap<String, Map<String, Object>>();
		List<SysPermission> menus = this.loadMune();
		for (SysPermission menu : menus) {
			String parentid = menu.getParentid();
			treeNode = new HashMap<String, Object>();
			treeNode.put("id", menu.getId());
			treeNode.put("text", menu.getName());
			String menu_id = menu.getId() + "";
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
						childList = (List<Map<String, Object>>) parenNode.get("children");
					}
					childList.add(treeNode);
					parenNode.put("children", childList);
				}
			}
		}
		return treeList;
	}
	@Override
	public Map<String, Object> checkMenu(SysPermission menu) throws Exception {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		resultMap.put("opt_flag", false);
		if(menu.getName()==null||menu.getName()==""){
			resultMap.put("errorMsg", "菜单名称不能为空！");
			return resultMap;
		}
		resultMap.put("opt_flag",true);
		return resultMap;
	}
	
}
