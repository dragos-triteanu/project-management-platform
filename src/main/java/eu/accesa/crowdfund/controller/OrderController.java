package eu.accesa.crowdfund.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.security.Authority;
import eu.accesa.crowdfund.services.ConsultantService;
import eu.accesa.crowdfund.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.OrderService;

/**
 * Created by Dragos on 9/17/2015.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrders(@RequestParam(value = "searchText", required = false) String searchText,
                            @RequestParam(value = "selectedSearchCategory", required = false) String selectedCategory, ModelMap modelMap) {

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders;
        if (searchText == null || searchText.isEmpty()) {
            orders = orderService.getOrders(SessionUtils.GetCurrentUser());
        } else {
            orders = orderService.getSearchedOrders(SessionUtils.GetCurrentUser(), searchText, selectedCategory);

        }

        modelMap.addAttribute("categoryForSearch", getCategoriesForSearch());
        modelMap.addAttribute("ordersList", orders);

        return "ordersPage";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<Integer> placeOrder(@RequestParam("firstName") String firstName,
                                              @RequestParam("lastName") String lastName,
                                              @RequestParam("email") String email,
                                              @RequestParam("domain") String domain,
                                              @RequestParam("subject") String subject,
                                              @RequestParam("nrOfPages") long nrOfPages,
                                              @RequestParam("tableOfContents") String tableOfContents,
                                              @RequestParam("bibliography") String bibliography,
                                              @RequestParam("message") String message,
                                              @RequestParam("annexes") MultipartFile annexes) throws Exception {

        User client = buildUserFromParams(firstName, lastName, email);
        //TODO validate
        Order order = buildOrderFromParams(domain, subject, nrOfPages, tableOfContents, bibliography, message, annexes, client);

        int placedOrder = orderService.placeOrder(client, order);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateOrder(@ModelAttribute("order") Order order){
        orderService.updateOrder(order);
        return "redirect:/orders";
    }


        private User buildUserFromParams(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMail(email);
        user.setRole(Authority.CLIENT);
        user.setPassword(Utils.Constants.CHANGEME_123);

        return user;
    }

    private Order buildOrderFromParams(String domain, String subject, long nrOfPages, String tableOfContents, String bibliography,
                                       String message, MultipartFile annexes, User client) throws IOException {
        Order order = new Order();
        order.setDomain(domain);
        order.setSubject(subject);
        order.setNrOfPages(nrOfPages);
        order.setTableOfContents(tableOfContents);
        order.setBibliography(bibliography);
        order.setMessage(message);
        order.setAnnexes(annexes.getBytes());
        order.setOrderStatus(OrderStatus.NEW);
        order.setClient(client);
        return order;
    }

    private List<String> getCategoriesForSearch() {
        if (SessionUtils.GetCurrentUser().getRole().equals(Authority.ADMINISTRATOR)) {
            return AdminCategoryOrderSearch.valuesAsString();
        } else {
            return CategoryOrderSearch.valuesAsString();
        }
    }
}
