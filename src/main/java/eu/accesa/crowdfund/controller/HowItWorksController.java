package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.repository.HowItWorksRepository;
import eu.accesa.crowdfund.utils.SessionUtils;
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
@RequestMapping("/howitworks")
public class HowItWorksController {
	private static final Logger LOG = LoggerFactory.getLogger(HowItWorksController.class);
	
	@Autowired
	private HowItWorksRepository howItWorksRepository;



	@RequestMapping(method = RequestMethod.GET)
	public String howItWorks(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		String htmlForWysiwyg = howItWorksRepository.getHTMLForWysiwyg();
		String htmlForWysiwygEscaped = StringEscapeUtils.escapeHtml4(htmlForWysiwyg);
		LOG.debug("Found HTML=\n"+ htmlForWysiwygEscaped);
		modelMap.addAttribute("htmlForWysiwyg",htmlForWysiwyg);
		modelMap.addAttribute("htmlForWysiwygEscaped",htmlForWysiwygEscaped);
		return "howitworks";
	}
	
	@RequestMapping(value="/updatePage", method = RequestMethod.POST)
	public String updatePage(@RequestParam("newHtml") String newHtml,ModelMap modelMap){
		LOG.debug("Received following HTML file :\n "+newHtml);
		howItWorksRepository.updateWysiwygHtml(newHtml);
		return "redirect:/howitworks";
	}
}
