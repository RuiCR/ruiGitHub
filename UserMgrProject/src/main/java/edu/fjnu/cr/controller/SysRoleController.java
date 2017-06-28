package edu.fjnu.cr.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.cr.domain.SysRole;
import edu.fjnu.cr.service.SysPermissionService;
import edu.fjnu.cr.service.SysRoleService;
import edu.fjnu.cr.util.JsonUtil;

@Controller
@RequestMapping("/role")
public class SysRoleController{
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPermissionService  sysPermissionService;
	@RequestMapping("/toQueryRole/{id}")
	String toQueryRole(@PathVariable Long id) throws Exception{
		
		return "role/query_role";
	}
	
	@RequestMapping("/toAddRole")
	String toAddRole() throws Exception{

		return "role/add_role";
	}
	@RequestMapping("/queryRole")
	@ResponseBody
	Map<String,Object> queryRole(HttpServletRequest request) throws Exception{
		
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String roleId = request.getParameter("role_id");
		String roleName = request.getParameter("role_name");
		System.out.println(roleId+roleName);
		SysRole sysRole = new SysRole();
		sysRole.setId(roleId);
		sysRole.setName(roleName);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<SysRole> roleList = sysRoleService.querySysRole(page,rows,order,sort,sysRole);
		dataMap.put("total", sysRoleService.cntSysRoleByExample(sysRole));
		dataMap.put("rows", roleList);
		return dataMap;
	}
	
	@RequestMapping(value = "/loadMenuTree/{role_id}")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTree(@PathVariable("role_id") String role_id) throws Exception{
		List<Map<String, Object>> treeData = sysPermissionService.loadMenuTree(role_id);
		return treeData;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String,Object>  save(SysRole sysRole,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map<String,Object> ckeckMap = sysRoleService.checkRole(sysRole, "");
		if(ckeckMap.get("errorMsg")!=null){
			return ckeckMap;
		}
		String role_menuData = request.getParameter("role_menuData");
		Map<String,Object> resultMap =this.sysRoleService.saveRole(sysRole, role_menuData);
		return resultMap;
	}
	
	@RequestMapping("/toEditRole/{role_id}")
	String toEditRole(@PathVariable("role_id")String role_id,ModelMap modelMap) throws Exception{
		SysRole roleBean = sysRoleService.selectSysRoleById(role_id);
		modelMap.put("roleBean", roleBean);
		return "role/edit_role";
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public Map<String, Object> modify(SysRole roleBean,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> ckeckMap = sysRoleService.checkRole(roleBean, "edit");
		if(ckeckMap.get("errorMsg")!=null){
			return ckeckMap;
		}
		String role_menuData = request.getParameter("role_menuData");
		Map<String, Object> resultMap = this.sysRoleService.updateRole(roleBean, role_menuData);
		return resultMap;
	}
	
	@RequestMapping(value = "/delete/{role_idData}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("role_idData") String role_idData,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = this.sysRoleService.deleteRole(role_idData);
		return resultMap;
	}
}
