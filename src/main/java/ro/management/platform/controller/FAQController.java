package ro.management.platform.controller;

import ro.management.platform.model.entities.QuestionAndAnswer;
import ro.management.platform.services.FAQService;
import ro.management.platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * Controller class for handling the requests containing the '/faq' path.
 * This class manages all interactions with the FAQ page.
 * @author dragos.triteanu
 *
 */
@Controller
@RequestMapping("/faq")
public class FAQController {
	
	@Autowired
	private FAQService faqService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String faq(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		List<QuestionAndAnswer> qaa = faqService.retrieveAllFAQs();
		modelMap.addAttribute("questionsAndAnswers", qaa);
		return "faqPage";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addNewQuestion(@ModelAttribute("questionAndAnswer") QuestionAndAnswer questionAndAnswer){
		faqService.addNewFAQ(questionAndAnswer);
		return "redirect:/faq";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteQuestionById(@RequestParam("deleteId") int deleteId){
		faqService.deleteeFAQById(deleteId);
		return "redirect:/faq";
	}
}
