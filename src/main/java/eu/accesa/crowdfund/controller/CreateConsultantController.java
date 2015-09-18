package eu.accesa.crowdfund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.accesa.crowdfund.utils.SessionUtils;

@Controller
@RequestMapping("/createConsultant")
public class CreateConsultantController {

	@RequestMapping(method = RequestMethod.GET)
    public String loadCreateConsultantPage(ModelMap modelMap){
    	SessionUtils.populateModelWithAuthenticatedRole(modelMap);	
    	return "createConsultant";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String createConsultant(ModelMap modelMap){
		
		return "createConsultant";
    }
	
	
}
