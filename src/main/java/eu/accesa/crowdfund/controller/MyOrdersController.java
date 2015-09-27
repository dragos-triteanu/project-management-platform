package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.MyOrdersService;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Dragos on 9/27/2015.
 */
@Controller
@RequestMapping("/myorders")
public class MyOrdersController {
    @Autowired
    private MyOrdersService myOrdersService;

    @RequestMapping(method = RequestMethod.GET)
    public String getConsultantActiveOrders(ModelMap modelMap){

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders = myOrdersService.getConsultantAssignedOrders(1);
        modelMap.addAttribute("ordersList", orders);

        return "myOrders";
    }
}
