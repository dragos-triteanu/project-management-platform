package ro.management.platform.controller;

import ro.management.platform.repository.LandingPageRepository;
import ro.management.platform.utils.SessionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class LandingPageController {
	private static final Logger LOG = LoggerFactory.getLogger(LandingPageController.class);
	
	@Autowired
	private LandingPageRepository landingPageRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String howItWorks(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		String htmlForWysiwyg = landingPageRepository.getHTMLForWysiwyg();
		String htmlForWysiwygEscaped = StringEscapeUtils.escapeHtml4(htmlForWysiwyg);
		LOG.debug("Found HTML=\n"+ htmlForWysiwygEscaped);
		modelMap.addAttribute("htmlForWysiwyg",htmlForWysiwyg);
		modelMap.addAttribute("htmlForWysiwygEscaped",htmlForWysiwygEscaped);
		return "homePage";
	}

	@RequestMapping(value="/updatePage", method = RequestMethod.POST)
	public String updatePage(@RequestParam("newHtml") String newHtml,ModelMap modelMap){
		LOG.debug("Received following HTML file :\n "+newHtml);
		landingPageRepository.updateWysiwygHtml(newHtml);
		return "redirect:/home";
	}
}
