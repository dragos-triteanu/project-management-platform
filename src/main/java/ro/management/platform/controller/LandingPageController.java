package ro.management.platform.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.management.platform.services.LandingPageService;
import ro.management.platform.utils.SessionUtils;

@Controller
@RequestMapping("/home")
public class LandingPageController {
	private static final Logger LOG = LoggerFactory.getLogger(LandingPageController.class);
	
    @Autowired
    private LandingPageService landingPageService;

    @RequestMapping(method = RequestMethod.GET)
	public String howItWorks(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		String htmlForWysiwyg = landingPageService.getHTMLForWysiwyg();
		String htmlForWysiwygEscaped = StringEscapeUtils.escapeHtml4(htmlForWysiwyg);
		LOG.debug("Found HTML=\n"+ htmlForWysiwygEscaped);
		modelMap.addAttribute("htmlForWysiwyg",htmlForWysiwyg);
		modelMap.addAttribute("htmlForWysiwygEscaped",htmlForWysiwygEscaped);
		return "homePage";
	}

	@RequestMapping(value="/updatePage", method = RequestMethod.POST)
	public String updatePage(@RequestParam("newHtml") String newHtml,
                             @RequestParam("whatUsersSee") int whatUsersSee,
                             ModelMap modelMap){
		LOG.debug("Received following HTML file :\n "+newHtml);
        landingPageService.updateWywywigForUser(newHtml,whatUsersSee);
		return "redirect:/home";
	}

    @RequestMapping(value = "/getHTMLForUserType" , method = RequestMethod.POST)
    public ResponseEntity<String> getHtmlForUserType(@RequestParam("userType") int userType){
        String htmlForUserByType = landingPageService.getHtmlForUserByType(userType);
        ResponseEntity<String> entity = new ResponseEntity<String>(htmlForUserByType, HttpStatus.OK);
        return entity;
    }
}
