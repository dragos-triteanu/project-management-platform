package eu.accesa.crowdfund.controller;

import eu.accesa.crowdfund.model.entities.Order;
import eu.accesa.crowdfund.services.MyOrdersService;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 9/27/2015.
 */
@Controller
@RequestMapping("/myorders")
public class MyOrdersController {
    @Autowired
    private MyOrdersService myOrdersService;

    @RequestMapping(method = RequestMethod.GET)
    public String getConsultantActiveOrders(@RequestParam(value="searchText",required = false) String searchText,
                                            @RequestParam(value="selectedSearchCategory",required = false) String selectedCategory,ModelMap modelMap){

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders;

        if (searchText == null || searchText.isEmpty()) {
            int currentUserId = SessionUtils.GetCurrentUser().getUserId();
            orders = myOrdersService.getConsultantAssignedOrders(currentUserId);
        } else {
            orders = myOrdersService.getOrderResultSearch(1, searchText, CategoryOrderSearch.getKey(selectedCategory));
        }

        modelMap.addAttribute("ordersList", orders);
        modelMap.addAttribute("categoryForSearch", CategoryOrderSearch.valuesAsString());

        return "myOrders";
    }
}
