package eu.accesa.platform.controller;

import eu.accesa.platform.model.entities.Order;
import eu.accesa.platform.services.MyOrdersService;
import eu.accesa.platform.utils.CategoryOrderSearch;
import eu.accesa.platform.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
            orders = myOrdersService.getConsultantAssignedOrders();
        } else {
            orders = myOrdersService.getOrderResultSearch(searchText, CategoryOrderSearch.getKey(selectedCategory));
        }

        modelMap.addAttribute("ordersList", orders);
        modelMap.addAttribute("categoryForSearch", CategoryOrderSearch.valuesAsString());

        return "myOrdersPage";
    }
}
