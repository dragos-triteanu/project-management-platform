package ro.management.platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.management.platform.model.entities.ChatMessage;
import ro.management.platform.model.entities.Message;
import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.User;
import ro.management.platform.services.MessageService;
import ro.management.platform.services.OrderService;
import ro.management.platform.utils.SessionUtils;
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
        List<ChatMessage> messages = messageService.getChatMessages(id);
        modelMap.addAttribute("messages",messages);
        return "myOrderDetailsPage";
    }


    @RequestMapping(value = "/messages" , method = RequestMethod.GET)
    public ResponseEntity<Object> getChatMessages(@RequestParam("orderId") int id, ModelMap modelMap){
        ResponseEntity<Object> entity = new ResponseEntity<Object>(messageService.getChatMessages(id),HttpStatus.OK);
        return entity;
    }

    @RequestMapping(value = "/rateConsultant" , method = RequestMethod.POST)
    public String rateConsultant(@RequestParam("orderId") int orderId ,
                                 @RequestParam("consultantRating") float consultantRating){

        orderService.rateConsultantResponsibleForOrder(orderId,consultantRating);
        return "redirect:/myorders";
    }

}
