package edu.fjnu.cr.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.cr.domain.SysPermission;
import edu.fjnu.cr.service.SysPermissionService;
import edu.fjnu.cr.service.SysService;
import edu.fjnu.cr.util.JsonUtil;

@Controller
@RequestMapping("/permission")
public class SysPermissionController {
	@Autowired
	private SysService sysService;
	@Autowired
	private SysPermissionService sysPermissionService;
	@RequestMapping("/toQueryPermission/{id}")
	public String toQuerySysPermisson(@PathVariable("id")Long id,Model model){
		model.addAttribute("id", id);
		return "permission/query_permission";
	}
	
	@RequestMapping("/queryPermission")
	@ResponseBody
	public Map<String,Object> querySysPermisson() throws Exception{
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<SysPermission> premissionList =  sysPermissionService.loadSysPermission();
		dataMap.put("total", premissionList.size());
		dataMap.put("rows", premissionList);
		return dataMap;
	}
	
	@RequestMapping("/toQueryMenu/{id}")
	public String toQueryMenu(@PathVariable("id")Long id,Model model){
		model.addAttribute("id", id);
		return "permission/query_menu";
	}
	
	@RequestMapping("/loadMenu")
	@ResponseBody
	public Map<String, Object> loadMenu() throws Exception{

		Map<String, Object> dataMap = sysPermissionService.loadMenuAll();
		return dataMap;
	}
	
	@RequestMapping("/toAddMenu/{parentId}")
	public String toAddMenu(@PathVariable("parentId")Long parentId,Model model){
		model.addAttribute("parentId", parentId);
		return "permission/add_menu";
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String,Object>  save(SysPermission menu) throws Exception {
		Map<String,Object> ckeckMap = sysPermissionService.checkMenu(menu);
		if(ckeckMap.get("errorMsg")!=null){
			return ckeckMap;
		}
		menu.setType("menu");
		if(menu.getParentid()==null||menu.getParentid()==""){
			menu.setParentid("0");
		}
		Map<String,Object> resultMap = sysPermissionService.addMenu(menu);
		return resultMap;
	}
	
	@RequestMapping(value = "/delete/{menu_idData}")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("menu_idData") String menu_idData,HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = this.sysPermissionService.deleteMenu(menu_idData);
		return resultMap;
	}
	
	@RequestMapping("/toEditMenu/{menu_id}")
	String toEditMenu(@PathVariable("menu_id")String menu_id,ModelMap modelMap) throws Exception{
		SysPermission menuBean = sysPermissionService.findMenuById(menu_id);
		modelMap.put("menuBean", menuBean);
		return "permission/edit_menu";
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public Map<String,Object> modify(SysPermission menuBean,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,Object> ckeckMap = sysPermissionService.checkMenu(menuBean);
		if(ckeckMap.get("errorMsg")!=null){
			return ckeckMap;
		}
		Map<String, Object> resultMap = sysPermissionService.updateMenu(menuBean);
		return resultMap;
	}
	
	@RequestMapping(value = "/loadMenuSelectTree/{menu_id}")
	@ResponseBody
	public List<Map<String, Object>> loadMenuSelectTree(@PathVariable("menu_id") String menu_id) throws Exception{
		
		List<Map<String, Object>> dataList = sysPermissionService.loadMenuCombotree();
		return dataList;
	}
	
}
