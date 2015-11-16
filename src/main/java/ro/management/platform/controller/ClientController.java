package ro.management.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.management.platform.model.entities.Client;
import ro.management.platform.services.UserService;
import ro.management.platform.utils.SessionUtils;

import java.util.List;

/**
 * Created by Dragos on 11/16/2015.
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    UserService clientService;
    @RequestMapping(method = RequestMethod.GET)
    public String getAllClients(ModelMap modelMap){

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Client> clientsList = clientService.getAllClients();
        modelMap.addAttribute("clientsList",clientsList);

        return "clientsPage";
    }
}
