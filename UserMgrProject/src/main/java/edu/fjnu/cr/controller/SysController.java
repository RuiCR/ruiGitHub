package edu.fjnu.cr.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fjnu.cr.service.SysService;

@Controller()
@RequestMapping("/sys")
public class SysController {
	@Autowired
	private SysService sysService;
	@RequestMapping(value = "/loadMenuTree")
	@ResponseBody
	public List<Map<String, Object>> loadMenuTree(String userId,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Map<String, Object>> dataTree= this.sysService.loadMenuTree(userId);
		return dataTree;
		
	}
}
