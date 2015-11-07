package eu.accesa.crowdfund.services;

import java.util.List;

import eu.accesa.crowdfund.model.entities.Client;
import eu.accesa.crowdfund.model.entities.Consultant;
import eu.accesa.crowdfund.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.entities.Order;
import eu.accesa.crowdfund.repository.OrderRepository;

/**
 * Created by Dragos on 9/20/2015.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Retrieves the list with consultant orders.
     */
    public List<Order> getOrders() {
        return orderRepository.getOrders();
    }

    public Order getOrderByUId(int id) {
        return orderRepository.getOrderById(id);
    }

    /**
     * Service method for used by anonymous clients in order to place an {@link Order}.
     *
     * @param order
     */

    public int placeOrder(Client client, Order order) {
        return orderRepository.createOrder(client, order);
    }

    public List<Order> getSearchedOrders(String searchText, String selectedCategory) {
         return orderRepository.getSearchedOrders(searchText, selectedCategory);
    }

    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }
}

