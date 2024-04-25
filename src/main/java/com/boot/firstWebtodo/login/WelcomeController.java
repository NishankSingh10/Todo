package com.boot.firstWebtodo.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes("name")
public class WelcomeController {
	

	
	@RequestMapping(value = "/", method= RequestMethod.GET)
	public String goToWelcome(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();	}
	
	
//	
//	@RequestMapping(value = "login", method= RequestMethod.POST)
//	public String goToWelcome(@RequestParam String name,@RequestParam String password,ModelMap model) {
//		if(auth.authentication(name,password)) {
//			model.put("name", name);
//			model.put("password", password);
//			return "welcome";
//		}
//		model.put("errorMsg","Invalid Credentials Please try again!");
//		return "login";
//	}
	
	
	
//.-------------------------------------------------------------------------.
//|	private Logger logger = LoggerFactory.getLogger(getClass());			|
//|	@RequestMapping("login")												|					
//|	public String goToLogin(@RequestParam String name, ModelMap model) {	|
//|		model.put("name", name);											|
//|		logger.info("Request param is {}",name);							|
//|		return "login";														|
//|		}																	|				
//'-------------------------------------------------------------------------'
	
	
	
}
