package eu.accesa.crowdfund.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dragos on 9/17/2015.
 */
@Controller
@RequestMapping("/orders")
public class OderController {


    public String getOrders()
    {

        return "orders";
    }

}
