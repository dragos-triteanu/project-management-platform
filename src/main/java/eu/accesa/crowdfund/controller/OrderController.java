package eu.accesa.crowdfund.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.services.OrderService;
import eu.accesa.crowdfund.utils.OrderStatus;
import eu.accesa.crowdfund.utils.SessionUtils;

/**
 * Created by Dragos on 9/17/2015.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public String getConsultantOrders(@RequestParam(value = "searchText", required = false) String searchText,
                                      @RequestParam(value = "selectedSearchCategory", required = false) String selectedCategory, ModelMap modelMap) {

        SessionUtils.populateModelWithAuthenticatedRole(modelMap);
        List<Order> orders;
        if (searchText == null || searchText.isEmpty()) {
            orders = orderService.getConsultantOrders(1);
        } else {
            orders = orderService.getOrderResultSearch(1, searchText, CategoryOrderSearch.getKey(selectedCategory));
        }

        modelMap.addAttribute("ordersList", orders);
        modelMap.addAttribute("categoryForSearch", CategoryOrderSearch.valuesAsString());

        return "orders";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public ResponseEntity<Integer> placeOrder(@RequestParam("domain") String domain,
                                              @RequestParam("subject") String subject,
                                              @RequestParam("nrOfPages") long nrOfPages,
                                              @RequestParam("tableOfContents") String tableOfContents,
                                              @RequestParam("bibliography") String bibliography,
                                              @RequestParam("message") String message,
                                              @RequestParam("annexes") MultipartFile annexes) throws Exception {
        //TODO validate
        Order order = buildOrderFromParams(domain, subject, nrOfPages, tableOfContents, bibliography, message, annexes);

        int placedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }


    private Order buildOrderFromParams(String domain, String subject, long nrOfPages, String tableOfContents, String bibliography,
                                       String message, MultipartFile annexes) throws IOException {
        Order order = new Order();
        order.setDomain(domain);
        order.setSubject(subject);
        order.setNrOfPages(nrOfPages);
        order.setTableOfContents(tableOfContents);
        order.setBibliography(bibliography);
        order.setMessage(message);
        order.setAnnexes(annexes.getBytes());
        order.setOrderStatus(OrderStatus.NEW);
        return order;
    }
}
