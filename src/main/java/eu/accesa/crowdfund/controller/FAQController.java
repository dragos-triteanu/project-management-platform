package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.QuestionAndAnswer;
import eu.accesa.crowdfund.repository.FAQRepository;
import eu.accesa.crowdfund.utils.SessionUtils;
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
	private FAQRepository faqRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String faq(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		List<QuestionAndAnswer> qaa = faqRepository.retrieveQuestionsAndAnswers();
		modelMap.addAttribute("questionsAndAnswers", qaa);
		return "faq";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addNewQuestion(@ModelAttribute("questionAndAnswer") QuestionAndAnswer questionAndAnswer){
		faqRepository.insertQuestionAndAnswer(questionAndAnswer);
		return "redirect:/faq";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String deleteQuestionById(@RequestParam("deleteId") String deleteId){
		faqRepository.deleteQuestionAndAnswerById(deleteId);
		return "redirect:/faq";
	}
}
