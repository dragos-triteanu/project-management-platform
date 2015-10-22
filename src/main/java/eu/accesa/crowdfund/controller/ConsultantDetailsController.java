package eu.accesa.crowdfund.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.model.ConsultantSpeciality;
import eu.accesa.crowdfund.repository.ConsultantCategoryRepository;
import eu.accesa.crowdfund.services.ConsultantService;
import eu.accesa.crowdfund.utils.SessionUtils;

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
    public String getConsultantDetails(@RequestParam("consultantId") int consultantId, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        User consultant = consultantService.getConsultantById(consultantId);
        modelMap.addAttribute("consultant", consultant);
        List<ConsultantSpeciality> category = consultantCategoryRepository.retrieveAllCategories();
        modelMap.addAttribute("categories", category);
        modelMap.addAttribute("titlePage", "Editare Consultant");
        return "consultant-details";
    }
    
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateConsultant(@ModelAttribute("Consultant") User consultant,
    							   @RequestParam(value="cvFile", required = false) MultipartFile cvFile) throws Exception{
    	consultant.setCv(cvFile.getBytes());
    	consultantService.updateConsultant(consultant);
    	return "redirect:/consultants";
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deleteConsultant(@RequestParam("consultantId") final String consultantId){
    	consultantService.removeConsultant(consultantId);
        return "redirect:/consultants";
    }

}
