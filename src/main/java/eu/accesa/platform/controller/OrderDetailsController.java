package eu.accesa.platform.controller;

import eu.accesa.platform.model.entities.ConsultantOrder;
import eu.accesa.platform.model.entities.Order;
import eu.accesa.platform.services.BidService;
import eu.accesa.platform.services.OrderService;
import eu.accesa.platform.utils.OrderStatus;
import eu.accesa.platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private BidService bidService;

    @Value("${consultant.bid.maxNrOfDays}")
    private int maxNrOfDays;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrderDetails(@RequestParam("orderId") int id, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        Order order = orderService.getOrderByUId(id);
        if(order.getOrderStatus().equals(OrderStatus.PENDING)){
            ConsultantOrder  bid = bidService.getBid(id);
            modelMap.addAttribute("bid",bid);
        }
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("maxNrOfDays",maxNrOfDays);
        modelMap.addAttribute("titlePage", "Detalii Comanda");
        return "orderDetailsPage";
    }
}
