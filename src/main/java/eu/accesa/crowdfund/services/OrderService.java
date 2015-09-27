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
    
    /**
     * Service method for used by anonymous clients in order to place an {@link Order}. 
     * @param order
     */
    
	public int placeOrder(Order order) {
		return orderRepository.createOrder(order);
	}
}

