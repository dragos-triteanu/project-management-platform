package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.OrderRepository;
import eu.accesa.crowdfund.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dragos on 9/20/2015.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    /**
     Retrieves the list with orders filtered by status .
    */
    public List<Order> getOrdersByStatus(OrderStatus status)
    {
        return orderRepository.getOrdersByStatus(status);
    }

    public Order getOrderByUId(int id) {
        return orderRepository.getOrderById(id);
    }
}

