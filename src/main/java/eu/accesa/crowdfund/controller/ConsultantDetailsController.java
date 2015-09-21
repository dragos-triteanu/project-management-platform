package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.repository.ConsultantCategoryRepository;
import eu.accesa.crowdfund.services.ConsultantService;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dragos on 9/13/2015.
 */
@Controller
@RequestMapping("consultantDetails")
public class ConsultantDetailsController {

    @Autowired
    private ConsultantService consultantService;
    
    @Autowired
    private ConsultantCategoryRepository consultantCategoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getConsultantDetails(@RequestParam("id") int id, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        Consultant consultant = consultantService.getConsultantById(id);
        modelMap.addAttribute("consultant", consultant);
        List<ConsultantSpeciality> category = consultantCategoryRepository.retrieveAllCategories();
        modelMap.addAttribute("categories", category);
        modelMap.addAttribute("titlePage", "Editare Consultant");
        return "consultant-details";
    }
}
