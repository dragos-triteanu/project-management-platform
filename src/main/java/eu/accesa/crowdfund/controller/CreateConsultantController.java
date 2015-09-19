package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.ConsultantCategory;
import eu.accesa.crowdfund.services.ConsultantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/createConsultant")
public class CreateConsultantController {

	@Autowired
	private ConsultantCategoryService consultantCategoryService;


	@RequestMapping(method = RequestMethod.GET)
    public String loadCreateConsultantPage(ModelMap modelMap){
    	SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		List<ConsultantCategory> categories = consultantCategoryService.retrieveAllConsultantCategories();
		modelMap.put("categories",categories);
		return "createConsultant";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String createConsultant(ModelMap modelMap){
		return "createConsultant";
    }



	/**
	 *
	 * CREATING OF CATEGORIES ASYNC.
	 */

	@RequestMapping(value="createCategory",method = RequestMethod.POST)
	public String createCategory(@ModelAttribute("category") ConsultantCategory category,ModelMap modelMap){
		consultantCategoryService.insertConsultantCategory(category);

		List<ConsultantCategory> categories = consultantCategoryService.retrieveAllConsultantCategories();
		modelMap.put("categories",categories);
		return "category-table-rendered";
	}

	@RequestMapping(value="deleteCategory",method = RequestMethod.POST)
	public String createCategory(@RequestParam("deleteId") final String deleteId,ModelMap modelMap){
		consultantCategoryService.deleteConsultantCategory(deleteId);

		List<ConsultantCategory> categories = consultantCategoryService.retrieveAllConsultantCategories();
		modelMap.put("categories",categories);
		return "category-table-rendered";
	}
}
