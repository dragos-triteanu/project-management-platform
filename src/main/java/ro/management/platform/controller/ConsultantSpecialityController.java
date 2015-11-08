package ro.management.platform.controller;

import ro.management.platform.model.entities.ConsultantSpeciality;
import ro.management.platform.services.ConsultantCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Dragos on 9/20/2015.
 */
@Controller
@RequestMapping("consultants")
public class ConsultantSpecialityController extends ConsultantController {

    @Autowired
    private ConsultantCategoryService consultantCategoryService;
    /**
     *
     * CREATING OF CATEGORIES ASYNC.
     */

    @RequestMapping(value="createCategory",method = RequestMethod.POST)
    public String createCategory(@ModelAttribute("category") ConsultantSpeciality category,ModelMap modelMap){
        consultantCategoryService.insertConsultantCategory(category);

        List<ConsultantSpeciality> categories = consultantCategoryService.retrieveAllConsultantCategories();
        modelMap.put("categories",categories);
        return "/components/category-table-rendered";
    }

    @RequestMapping(value="deleteCategory",method = RequestMethod.POST)
    public String createCategory(@RequestParam("deleteId") final int deleteId,ModelMap modelMap){
        consultantCategoryService.deleteConsultantCategory(deleteId);

        List<ConsultantSpeciality> categories = consultantCategoryService.retrieveAllConsultantCategories();
        modelMap.put("categories",categories);
        return "/components/category-table-rendered";
    }

}
