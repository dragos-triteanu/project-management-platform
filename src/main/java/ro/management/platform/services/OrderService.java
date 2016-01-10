package ro.management.platform.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ro.management.platform.model.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.management.platform.model.entities.Consultant;
import ro.management.platform.model.entities.Order;
import ro.management.platform.repository.OrderRepository;
import ro.management.platform.repository.UserRepository;

/**
 * Created by Dragos on 9/20/2015.
 */
@Service
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void assignConsultant(int orderId, int consultantId) {
        orderRepository.assignConsultant(orderId, consultantId);
    }

    public void deleteOrder(int orderId) {
        orderRepository.deleteOrder(orderId);
    }

    public void rateConsultantResponsibleForOrder(int orderId, float consultantRating) {
        try{
            computeRating(orderId,consultantRating);
        }catch(Exception e){
            LOG.error("Hack attempt",e);
        }

    }

    @Transactional(readOnly = false)
    private void computeRating(final int orderId, final float consultantRating) {
        Order orderById = orderRepository.getOrderById(orderId);
        Consultant consultant = orderById.getConsultant();
        if(consultant.getRating() == 0){
            consultant.setRating(consultantRating);
        }else{
            consultant.setRating((consultant.getRating() + consultantRating) / 2);
        }
        orderById.setRated(true);
        orderRepository.updateOrderWithRating(orderById);
        userRepository.hibernateUpdateConsultant(consultant);
    }

    public void startOrder(int orderId, int consultantId) {
        orderRepository.startOrder(orderId, consultantId);
    }

    public void closeOrder(int orderId, int consultantId) {
        orderRepository.closeOrder(orderId,consultantId);
    }
}

