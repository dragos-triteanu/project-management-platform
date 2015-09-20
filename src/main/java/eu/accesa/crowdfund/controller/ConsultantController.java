package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.services.ConsultantCategoryService;
import eu.accesa.crowdfund.services.ConsultantService;
import eu.accesa.crowdfund.utils.SessionUtils;
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

import java.io.IOException;
import java.util.List;

/**
 * Created by Dragos on 9/12/2015.
 */
@Controller
public class ConsultantController {
    private static final Logger LOG = LoggerFactory.getLogger(ConsultantController.class);

    @Autowired
    private ConsultantCategoryService consultantCategoryService;

    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(value = "consultants", method = RequestMethod.GET)
    public String getAllConsultants(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        List<Consultant> consultants = consultantService.getAllConsultants();
        modelMap.addAttribute("consultantsList", consultants);
        return "consultants";
    }

    @RequestMapping(value = "createConsultant",method = RequestMethod.GET)
    public String loadCreateConsultantPage(ModelMap modelMap){
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        List<ConsultantSpeciality> categories = consultantCategoryService.retrieveAllConsultantCategories();
        modelMap.put("categories",categories);
        return "createConsultant";
    }

    @RequestMapping(value="createConsultant",method = RequestMethod.POST)
    public String createConsultant(@ModelAttribute("consultant") Consultant consultant,
                                   @RequestParam("cvFile") MultipartFile cvFile){

        try {
            consultant.setCv(cvFile.getBytes());
        } catch (IOException e) {
            LOG.error("Could not add CV file to consultant",e);
        }

        consultantService.createConsultant(consultant);

        return "redirect:/consultants";
    }

}