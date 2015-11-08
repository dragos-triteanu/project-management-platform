package ro.management.platform.controller;

import java.io.IOException;
import java.util.List;

import ro.management.platform.security.Authority;
import ro.management.platform.utils.CategoryConsultantSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.model.entities.ConsultantSpeciality;
import ro.management.platform.services.ConsultantCategoryService;
import ro.management.platform.services.ConsultantService;
import ro.management.platform.utils.SessionUtils;

/**
 * Created by Dragos on 9/12/2015.
 */
@Controller
public class ConsultantController {
    private static final Logger LOG = LoggerFactory.getLogger(ConsultantController.class);

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private ConsultantCategoryService consultantCategoryService;

    @RequestMapping(value = "consultants", method = RequestMethod.GET)
    public String getAllConsultants(@RequestParam(value = "searchText", required = false) String searchText,
                                    @RequestParam(value = "selectedSearchCategory", required = false) String selectedCategory, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        List<Consultant> consultants;
        if (searchText == null || searchText.isEmpty()) {
            consultants = consultantService.getAllConsultants();
        } else {
            consultants = consultantService.getConsultantsResultSearch(searchText, CategoryConsultantSearch.getKey(selectedCategory));
        }

        modelMap.addAttribute("consultantsList", consultants);
        modelMap.addAttribute("categoryForSearch", CategoryConsultantSearch.valuesAsString());

        return "consultantsPage";
    }

    @RequestMapping(value = "createConsultant", method = RequestMethod.GET)
    public String loadCreateConsultantPage(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        List<ConsultantSpeciality> categories = consultantCategoryService.retrieveAllConsultantCategories();
        modelMap.put("categories", categories);
        return "createConsultantPage";
    }

    @RequestMapping(value = "createConsultant", method = RequestMethod.POST)
    public String createConsultant(@ModelAttribute("consultant") Consultant consultant,
                                   @RequestParam("cvFile") MultipartFile cvFile) {

        try {
            consultant.setCv(cvFile.getBytes());
        } catch (IOException e) {
            LOG.error("Could not add CV file to consultant", e);
        }

        consultant.setRole(Authority.CONSULTANT);
        consultantService.createUser(consultant);

        return "redirect:/consultants";
    }
}