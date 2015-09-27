package eu.accesa.crowdfund.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.OrderRepository;

/**
 * Created by Dragos on 9/20/2015.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    /**
     Retrieves the list with consultant orders.
    */
    public List<Order> getConsultantOrders(int consultantId)
    {
        return orderRepository.getConsultantOrders(consultantId);
    }

    public Order getOrderByUId(int id) {
        return orderRepository.getOrderById(id);
    }
}

