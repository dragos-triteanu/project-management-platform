package ro.management.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.Payment;
import ro.management.platform.services.PaymentService;
import ro.management.platform.utils.SessionUtils;

import java.util.List;

/**
 * Created by Dragos on 1/10/2016.
 */
@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    public PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrdersForPayment(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders = paymentService.getAllOrdersForPayment(SessionUtils.GetCurrentUser().getUserId());

        modelMap.addAttribute("orderList",orders);
        return "paymentPage";
    }

    @RequestMapping(value="/getpaymentsdetails",method = RequestMethod.GET)
    public @ResponseBody Payment getPaymentDetails(@RequestParam("orderId") int orderId) {
        Payment payment = paymentService.getPaymentDetails(orderId,SessionUtils.GetCurrentUser().getUserId());
        return payment;
    }
}




