package eu.accesa.crowdfund.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.services.ConsultantService;
import eu.accesa.crowdfund.utils.SessionUtils;

/**
 * Created by Dragos on 9/12/2015.
 */
@Controller
@RequestMapping("consultants")
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllConsultants(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        List<Consultant> consultants = consultantService.getAllConsultants();
        modelMap.addAttribute("consultantsList", consultants);
        return "consultants";
    }
}