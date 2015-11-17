package ro.management.platform.controller;

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
import ro.management.platform.model.dto.MailMessage;
import ro.management.platform.model.entities.User;
import ro.management.platform.services.MailService;
import ro.management.platform.utils.FileUtils;
import ro.management.platform.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dragos.triteanu on 11/15/15.
 */

@Controller
@RequestMapping("/support")
public class SupportController {
    private static final Logger LOG = LoggerFactory.getLogger(SupportController.class);

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSupportPage(ModelMap modelMap, @RequestParam(name = "mailSent",required = false) boolean mailSent){
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        modelMap.addAttribute("mailSent",mailSent);
        return "supportPage";
    }

    @RequestMapping(value = "/sendMessageToAdmin" , method = RequestMethod.POST)
    public String sendMessageToAdmin(@ModelAttribute("mailMessage") MailMessage mailMessage,
                                     @RequestParam(name="attachment",required = false) MultipartFile attachment,
                                     HttpServletRequest request) throws Exception {
        User user = SessionUtils.GetCurrentUser();
        mailMessage.withSender(user.getMail());

        String fileLocation = FileUtils.saveFileToDisk(attachment, request);

        mailService.sendEmailToAllAdmins(mailMessage , fileLocation);
        return "redirect:/support?mailSent=true";
    }
}
