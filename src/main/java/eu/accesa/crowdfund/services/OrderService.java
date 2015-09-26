package eu.accesa.crowdfund.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.OrderRepository;
import eu.accesa.crowdfund.utils.OrderStatus;

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

	public void placeOrder(Order order) {
		orderRepository.createOrder(order);
	}
}

