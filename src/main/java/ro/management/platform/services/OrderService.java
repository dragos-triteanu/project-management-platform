package ro.management.platform.services;

import java.util.List;

import ro.management.platform.model.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.management.platform.model.entities.Order;
import ro.management.platform.repository.OrderRepository;

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

