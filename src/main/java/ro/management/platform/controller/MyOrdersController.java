package ro.management.platform.controller;

import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.User;
import ro.management.platform.security.Authority;
import ro.management.platform.services.MyOrdersService;
import ro.management.platform.utils.CategoryOrderSearch;
import ro.management.platform.utils.SessionUtils;
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
        User currentUser = SessionUtils.GetCurrentUser();

        List<Order> orders = new ArrayList<>();
        if(currentUser.getRole().equals(Authority.CONSULTANT)){
            if (searchText == null || searchText.isEmpty()) {
                orders = myOrdersService.getConsultantAssignedOrders(SessionUtils.GetCurrentUser());
            } else {
                orders = myOrdersService.getMyOrdersSearchResultForConsultant(SessionUtils.GetCurrentUser(),searchText, CategoryOrderSearch.getKey(selectedCategory));
            }
        }else if (currentUser.getRole().equals(Authority.CLIENT)){
            orders = myOrdersService.getOrdersForClient(currentUser,searchText,CategoryOrderSearch.getKey(selectedCategory));
        }

        modelMap.addAttribute("ordersList", orders);
        modelMap.addAttribute("categoryForSearch", CategoryOrderSearch.valuesAsString());

        return "myOrdersPage";
    }
}
