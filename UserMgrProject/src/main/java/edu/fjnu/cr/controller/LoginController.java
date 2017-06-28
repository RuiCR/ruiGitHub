package edu.fjnu.cr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	private Integer loginTime = 0;
	@RequestMapping("/login")

	public String login(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		    Map<String,Object>	resultMap = new HashMap<>();
		    model.addAttribute("opt_flag", false);
			String exceptionClassName = (String) request
						.getAttribute("shiroLoginFailure");
			Object username = request.getAttribute("activeUser");
				// 根据shiro返回的异常类路径判断，抛出指定异常信息
				if (exceptionClassName != null) {
					if(loginTime>2){
						loginTime = 0;
						model.addAttribute("outTime", "登录次数超过3次，请重新访问!");
					}
					else if (UnknownAccountException.class.getName().equals(
							exceptionClassName)) {
						loginTime++;
						model.addAttribute("errorMsg", "账号不存在!");
						//throw new CustomException("账号不存在");
					} else if (IncorrectCredentialsException.class.getName().equals(
							exceptionClassName)) {
						loginTime++;
						model.addAttribute("errorMsg", "用户名/密码错误!");
						//throw new CustomException("用户名/密码错误");
					} else if ("randomCodeError".equals(exceptionClassName)) {
						loginTime++;
						model.addAttribute("errorMsg", "验证码错误!");
						//throw new CustomException("验证码错误 ");
					} else {
						throw new Exception();// 最终在异常处理器生成未知错误
					}
				}else{
					loginTime=0;
				}
				resultMap.put("opt_flag", true);
				// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
				// 登陆失败还到login页面
				return "login";
	}
	
	
}
