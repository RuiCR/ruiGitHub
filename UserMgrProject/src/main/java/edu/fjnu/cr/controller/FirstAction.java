
package edu.fjnu.cr.controller;
import java.util.List;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fjnu.cr.domain.ActiveUser;





@Controller
public class FirstAction {

	@RequestMapping("/first")
	public String first(Model model)throws Exception{

		Subject subject = SecurityUtils.getSubject();

		ActiveUser activeUser = (ActiveUser) subject.getPrincipal();

		model.addAttribute("activeUser", activeUser);
		return "/first";
	}
	
	
	@RequestMapping("/welcome")
	public String welcome(Model model)throws Exception{
		
		return "/welcome";
		
	}
}	
