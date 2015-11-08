package ro.management.platform.controller;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.model.entities.ConsultantOrder;

import ro.management.platform.model.entities.Order;
import ro.management.platform.services.BidService;
import ro.management.platform.utils.OrderStatus;
import ro.management.platform.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Dragos on 9/21/2015.
 */
@Controller
public class BidController {
    private static final Logger LOG = LoggerFactory.getLogger(BidController.class);

    @Autowired
    private BidService bidService;

    @RequestMapping(value="/placeBid", method = RequestMethod.POST)
    public String sendBid(@RequestParam("orderId") int orderId , @RequestParam("nrOfDays") int nrOfDays, @RequestParam("cost") double cost){

        ConsultantOrder bid = new ConsultantOrder();
        bid.setCost(cost);
        bid.setNrOfDays(nrOfDays);

        Order order=new Order();
        order.setOrderId(orderId);
        bid.setOrder(order);
        bid.setConsultant((Consultant)SessionUtils.GetCurrentUser());

        bid.setStatus(OrderStatus.PENDING);
        bidService.addBid(bid);
        return "redirect:/orders";
    }

    @RequestMapping(value="/deleteBid", method = RequestMethod.POST)
    public String deleteBid(@RequestParam("orderId") int orderId)
    {
        bidService.deleteBid(SessionUtils.GetCurrentUser().getUserId(),orderId);
        return "redirect:/myorders";
    }
}