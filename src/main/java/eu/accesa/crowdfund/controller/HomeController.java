package eu.accesa.crowdfund.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String welcomePage() {
		return "redirect:/howitworks";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "loginError" , required = false) boolean loginError,ModelMap modelMap){
		if(loginError == true){
			LOG.info("loginError="+loginError);
			modelMap.addAttribute("loginError",loginError);
		}
		return "login";
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(){
		return "denied";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.POST)
	public String deniedPost(){
		return "denied";
	}
}