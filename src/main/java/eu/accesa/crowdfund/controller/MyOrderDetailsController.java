package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Message;
import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.services.MessageService;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Dragos on 10/6/2015.
 */
@Controller
@RequestMapping("/myOrderDetails")
public class MyOrderDetailsController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllApprovedOrders(@RequestParam("orderId") int id, ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        Order order = orderService.getOrderByUId(id);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("titlePage", "Detalii Comanda");
        User currentUser = SessionUtils.GetCurrentUser();
        modelMap.put("currentUser",currentUser);
        List<Message> messages = messageService.getMessages(id);
        modelMap.addAttribute("messages",messages);

        return "myOrderDetails";
    }
}
