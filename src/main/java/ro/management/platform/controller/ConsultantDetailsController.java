package ro.management.platform.controller;

import java.util.List;

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
import ro.management.platform.repository.ConsultantCategoryRepository;
import ro.management.platform.services.ConsultantService;
import ro.management.platform.utils.SessionUtils;

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
    public String getConsultantDetails(@RequestParam("userId") int userId, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        Consultant consultant = consultantService.getConsultantById(userId);
        modelMap.addAttribute("consultant", consultant);
        List<ConsultantSpeciality> category = consultantCategoryRepository.retrieveAllCategories();
        modelMap.addAttribute("categories", category);
        modelMap.addAttribute("titlePage", "Editare Consultant");
        return "consultantDetailsPage";
    }
    
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String updateConsultant(@ModelAttribute("Consultant") Consultant consultant,
    							   @RequestParam(value="cvFile", required = false) MultipartFile cvFile) throws Exception{
    	consultant.setCv(cvFile.getBytes());
    	consultantService.updateConsultant(consultant);
    	return "redirect:/consultants";
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deleteConsultant(@RequestParam("userId") final String userId){
    	consultantService.removeConsultant(userId);
        return "redirect:/consultants";
    }

}
