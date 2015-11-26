package ro.management.platform.controller;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.model.entities.ConsultantOrder;
import ro.management.platform.model.entities.Order;
import ro.management.platform.services.BidService;
import ro.management.platform.services.OrderService;
import ro.management.platform.utils.OrderStatus;
import ro.management.platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        switch (order.getOrderStatus()) {
            case PENDING : {
                ConsultantOrder consultantBid = bidService.getConsultantBid(id, SessionUtils.GetCurrentUser().getUserId());
                modelMap.addAttribute("consultantBid", consultantBid);
                break;
            }
            case ACCEPTED : {
                List<ConsultantOrder> bidsPerOrder = bidService.getOrderBids(id);
                modelMap.addAttribute("orderBids", bidsPerOrder);
                break;
            }
            case ASSIGNED:
            case INPROGRESS:{
                if(order.getConsultant() != null){
                    ConsultantOrder assignedConsultant = bidService.getConsultantBid(id, order.getConsultant().getUserId());
                    modelMap.addAttribute("assignedConsultant", assignedConsultant);
                    break;
                }
            }
        }
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("maxNrOfDays",maxNrOfDays);
        modelMap.addAttribute("titlePage", "Detalii Comanda");
        return "orderDetailsPage";
    }

    @RequestMapping(value="/assignConsultant",method = RequestMethod.POST)
    public String assignConsultant(@RequestParam("orderId")int orderId,@RequestParam("consultantId") int consultantId,ModelMap modelMap){

        orderService.assignConsultant(orderId, consultantId);
        return getOrderDetails(orderId,modelMap);
    }
}
