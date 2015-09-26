package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.Bid;

import eu.accesa.crowdfund.model.Consultant;
import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        Bid bid = new Bid();
        bid.setCost(cost);
        bid.setNrOfDays(nrOfDays);

        Order order=new Order();
        order.setOrderId(orderId);
        bid.setOrder(order);

        Consultant consultant = new Consultant();
        consultant.setConsultantId(1);
        bid.setConsultant(consultant);

        bidService.addBid(bid);
        return "redirect:/orders";
    }
}
