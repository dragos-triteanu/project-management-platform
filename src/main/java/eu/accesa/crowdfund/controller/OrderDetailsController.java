package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Dragos on 9/21/2015.
 */
@Controller
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllApprovedOrders(@RequestParam("orderId") int id, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        Order order = orderService.getOrderByUId(id);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("titlePage", "Detalii Comanda");
        return "orderDetails";
    }
}
