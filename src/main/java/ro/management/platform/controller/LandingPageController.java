package ro.management.platform.controller;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.management.platform.model.dto.HtmlForWysiwyg;
import ro.management.platform.services.LandingPageService;
import ro.management.platform.utils.MessageTranslator;
import ro.management.platform.utils.SessionUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home")
public class LandingPageController {
	private static final Logger LOG = LoggerFactory.getLogger(LandingPageController.class);
	
    @Autowired
    private LandingPageService landingPageService;

	@Autowired
	private MessageTranslator messageTranslator;

    @RequestMapping(method = RequestMethod.GET)
	public String howItWorks(ModelMap modelMap, HttpSession session){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		List<String> errors = (List<String>) session.getAttribute("errors");
		if(errors != null){
			modelMap.addAttribute("errors",errors);
			session.removeAttribute("errors");
		}
		String htmlForWysiwyg = landingPageService.getHTMLForWysiwyg();
		String htmlForWysiwygEscaped = StringEscapeUtils.escapeHtml4(htmlForWysiwyg);
		LOG.debug("Found HTML=\n"+ htmlForWysiwygEscaped);
		modelMap.addAttribute("htmlForWysiwyg",htmlForWysiwyg);
		modelMap.addAttribute("htmlForWysiwygEscaped",htmlForWysiwygEscaped);
		return "homePage";
	}

	@RequestMapping(value="/updatePage", method = RequestMethod.POST)
	public String updatePage(@Valid HtmlForWysiwyg htmlForWysiwyg,
							 BindingResult bindingResult,
                             ModelMap modelMap,
							 HttpSession session){

		if(bindingResult.hasErrors()){
			List<String> errors = messageTranslator.getErrors(bindingResult);
			session.setAttribute("errors",errors);
			return "redirect:/home";
		}
		LOG.debug("Received following HTML file :\n "+htmlForWysiwyg.getNewHtml());
        landingPageService.updateWywywigForUser(htmlForWysiwyg.getNewHtml(),htmlForWysiwyg.getWhatUsersSee());
		return "redirect:/home";
	}

    @RequestMapping(value = "/getHTMLForUserType" , method = RequestMethod.POST)
    public ResponseEntity<String> getHtmlForUserType(@RequestParam("userType") int userType){
        String htmlForUserByType = landingPageService.getHtmlForUserByType(userType);
        ResponseEntity<String> entity = new ResponseEntity<String>(htmlForUserByType, HttpStatus.OK);
        return entity;
    }
}
