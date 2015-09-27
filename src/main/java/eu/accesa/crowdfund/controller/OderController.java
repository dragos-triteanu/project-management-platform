package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Dragos on 9/17/2015.
 */
@Controller
@RequestMapping("/orders")
public class OderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
         public String getAllApprovedOrders(ModelMap modelMap){

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders = orderService.getConsultantOrders(1);
        modelMap.addAttribute("ordersList", orders);

        return "orders";
    }

}
