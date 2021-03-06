package ro.management.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ro.management.platform.model.entities.Client;
import ro.management.platform.model.entities.Order;
import ro.management.platform.security.Authority;
import ro.management.platform.services.OrderService;
import ro.management.platform.utils.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dragos on 9/17/2015.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${users.predefined.password}")
    private String definedInitialPassword;

    @Autowired
    private org.springframework.validation.Validator validator;

    @Autowired
    private MessageTranslator messageTranslator;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrders(@RequestParam(value = "searchText", required = false) String searchText,
                            @RequestParam(value = "selectedSearchCategory", required = false) String selectedCategory, ModelMap modelMap) {

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders;
        if (searchText == null || searchText.isEmpty()) {
            orders = orderService.getOrders();
        } else {
            orders = orderService.getSearchedOrders(searchText, selectedCategory);

        }

        modelMap.addAttribute("categoryForSearch", getCategoriesForSearch());
        modelMap.addAttribute("ordersList", orders);

        return "ordersPage";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<?> placeOrder(@ModelAttribute("client") Client client,
                                        @ModelAttribute("order") Order order,
                                        @RequestParam(value = "files", required = false) MultipartFile files,
                                        Errors errors) throws Exception {


        validator.validate(client,errors);
        validator.validate(order,errors);

        if(errors.hasErrors()){
            List<String> errors1 = messageTranslator.getErrors(errors);
            return new ResponseEntity<Object>(errors1,HttpStatus.BAD_REQUEST);
        }

        client.setRole(Authority.CLIENT);
        client.setPassword(definedInitialPassword);

        order.setAnnexes(files!= null ? files.getBytes() : null);
        order.setClient(client);

        Integer placedOrder = orderService.placeOrder(client, order);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOrder(@ModelAttribute("order") Order order) {
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam("orderId") int orderId,ModelMap modelMap)
    {
        orderService.deleteOrder(orderId);
        return "redirect:/orders";
    }

    private List<String> getCategoriesForSearch() {
        if (SessionUtils.GetCurrentUser().getRole().equals(Authority.ADMINISTRATOR)) {
            return AdminCategoryOrderSearch.valuesAsString();
        } else {
            return CategoryOrderSearch.valuesAsString();
        }
    }
}
