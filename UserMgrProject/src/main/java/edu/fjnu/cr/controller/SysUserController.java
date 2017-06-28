package edu.fjnu.cr.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.fjnu.cr.domain.Dictionary;
import edu.fjnu.cr.domain.SysRole;
import edu.fjnu.cr.domain.SysUser;
import edu.fjnu.cr.domain.SysUserRole;
import edu.fjnu.cr.service.DictionaryService;
import edu.fjnu.cr.service.SysRoleService;
import edu.fjnu.cr.service.UserService;
import edu.fjnu.cr.util.JsonUtil;
import edu.fjnu.cr.util.MD5;

@Controller
@RequestMapping("/user")
public class SysUserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping("/toQueryUser/{id}")
	String toQueryUser(@PathVariable Long id,Model model) throws Exception{
		List<Dictionary> dicList  = dictionaryService.loadDictionaryByType(1);
		//String dic = JsonUtil.objToJson(dicList);
	
		model.addAttribute("dicList", dicList);
		return "user/query_user";
	}
	
	@RequestMapping("/queryUser")
	@ResponseBody
	Map<String,Object> queryUser(HttpServletRequest request) throws Exception{
		
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String user_code = request.getParameter("user_code");
		String user_name = request.getParameter("user_name");
		String user_dep = request.getParameter("user_dep[]");
		
		SysUser sysUser = new SysUser();
		sysUser.setUsercode(user_code);
		sysUser.setUsername(user_name);
		if(user_dep!=null&&user_dep!=""){
			sysUser.setDepartment(Integer.parseInt(user_dep));
		}
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<SysUser> userList = userService.querySysUser(page, rows,order,sort, sysUser);
		dataMap.put("total", userService.cntSysUserByExample(sysUser));
		dataMap.put("rows", userList);
		return dataMap;
	}
	
	@RequestMapping("/toAddUser")
	String toAddRole() throws Exception{
		return "user/add_user";
	}
	
	@RequestMapping(value = "/loadRoleTree/{user_id}")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTree(@PathVariable("user_id") String user_id) throws Exception{
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		List<SysRole> roleList = sysRoleService.loadRoleTree();
		for(SysRole role:roleList){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("id", role.getId());
			dataMap.put("text", role.getName());
			
			dataList.add(dataMap);
		}
		return dataList;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String,Object>  save(SysUser sysUser,HttpServletRequest request,Model model) throws Exception {
	
		Map<String,Object> ckeckMap = userService.cheakUser(sysUser,"");
		if(ckeckMap.get("errorMsg")!=null){
			model.addAttribute("sysUser", sysUser);
			return ckeckMap;
		}
		
		String user_roleData = request.getParameter("user_roleData");
		sysUser.setId(UUID.randomUUID().toString());
		sysUser.setSalt(UUID.randomUUID().toString());
		String salt = sysUser.getSalt();
		MD5 md5 = new MD5();
		String password = md5.getMD5ofStr(sysUser+"888888");
		sysUser.setPassword(password);
		sysUser.setCreatedate(new Date());
		Map<String,Object> resultMap = userService.saveUser(sysUser, user_roleData);
		return resultMap;
	}
	
	@RequestMapping("/toEditUser/{user_id}")
	String toEditRole(@PathVariable("user_id")String user_id,ModelMap modelMap,Model model) throws Exception{
		SysUser userBean = userService.findSysUserByid(user_id);
		List<SysUserRole> userRoleList = userService.findRoleIdByUserId(user_id);
		String data = "";
		for(SysUserRole userRole:userRoleList){
			
			data=data+userRole.getSysRoleId()+",";
		}
		String roleData = "";
		if(data!=null&&data!=""){
			roleData = data.substring(0,data.length()-1);
		}
		model.addAttribute("roleData",roleData);
		modelMap.put("userBean", userBean);
		return "user/edit_user";
	}
	
	@RequestMapping(value = "/modify")
	@ResponseBody
	public Map<String,Object> modify(SysUser userBean,HttpServletRequest request) throws Exception {
		Map<String,Object> ckeckMap = userService.cheakUser(userBean,"edit");
		if(ckeckMap.get("errorMsg")!=null){
			return ckeckMap;
		}
		String user_roleData = request.getParameter("user_roleData");
		Map<String, Object> resultMap = userService.updateUser(userBean, user_roleData);
		return resultMap;
	}
	
	@RequestMapping(value = "/delete/{user_idData}")
	@ResponseBody
	public void delete(@PathVariable("user_idData") String user_idData,HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = this.userService.deleteUser(user_idData);
		String jsonStr = JsonUtil.objToJson(resultMap);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/toCheckPwd/{id}")
	public String toCheckPwd(@PathVariable("id") String id,Model model) throws Exception {
		model.addAttribute("userid", id);
		return "user/checkpwd";
	}
	
	@RequestMapping(value = "/cheakPwd/{id}")
	@ResponseBody
	public Map<String,Object> cheakPwd(@PathVariable("id") String id,String password,Model model) throws Exception {
		Map<String, Object> resultMap =userService.cheakPwd(id, password);
		model.addAttribute("userid", id);
		return resultMap;
	}
	
	@RequestMapping(value = "/toUpdatePwd/{id}")
	public String toUpdatePwd(@PathVariable("id") String id,Model model) throws Exception {
		model.addAttribute("userid", id);
		return "user/updatepwd";
	}
	
	@RequestMapping(value = "/updatePwd/{id}")
	@ResponseBody
	public Map<String,Object> updatePwd(@PathVariable("id") String id,String password,String passwordQ) throws Exception {
		if(!password.equals(passwordQ)){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("opt_flag", false);
			resultMap.put("errorMsg", "两次密码输入不正确！");
			return resultMap;
		}
		SysUser sysUser = userService.findSysUserByid(id);
		String salt =sysUser.getSalt();
		String newPwd = new MD5().getMD5ofStr(salt+password);
		sysUser.setPassword(newPwd);
		Map<String, Object> resultMap =userService.updateUser(sysUser, "");
		return resultMap;
	}
	
	@RequestMapping("/loadDictionayComboTree")
	@ResponseBody
	List<Map<String,Object>> loadDictionayComboTree() throws Exception{
		List<Map<String,Object>> dataList = dictionaryService.loadDictionaryComboTree(1);
		return dataList;
	}
	
	@RequestMapping("/getUserDepartment/{department}")
	@ResponseBody
	Map<String,Object> getUserDepartment(@PathVariable("department") Integer department) throws Exception{
		Map<String,Object> resulteMap = new HashMap<>();
		Dictionary dic = dictionaryService.fingdDictionartById(department);
		resulteMap.put("department", dic.getDictName());
		return resulteMap;
	}
}
