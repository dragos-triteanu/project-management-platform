package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.ConsultantCategory;
import eu.accesa.crowdfund.model.Consultant;
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
@RequestMapping("consultant-details")
public class ConsultantDetailsController {

    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(method = RequestMethod.GET)
    public String getConsultantDetails(@RequestParam("uid") UUID uid, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        Consultant consultant = consultantService.getConsultantByUid(uid);
        modelMap.addAttribute("consultant", consultant);
        List<ConsultantCategory> category = new ArrayList<>();
        category.add(consultant.getCategory());
        modelMap.addAttribute("categories", category);
        modelMap.addAttribute("titlePage", "Editare Consultant");
        return "consultant-details";
    }
}
