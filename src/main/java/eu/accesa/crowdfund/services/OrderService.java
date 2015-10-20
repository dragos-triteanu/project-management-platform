package eu.accesa.crowdfund.services;

import java.util.ArrayList;
import java.util.List;

import eu.accesa.crowdfund.utils.CategoryOrderSearch;
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
    
    /**
     * Service method for used by anonymous clients in order to place an {@link Order}. 
     * @param order
     */
    
	public int placeOrder(Order order) {
		return orderRepository.createOrder(order);
	}

    public List<Order> getOrderResultSearch(int consultantId,String searchText, CategoryOrderSearch selectedCategory) {
         if(selectedCategory==null)
            return new ArrayList<>();
        return orderRepository.getOrderResultSearch(consultantId,searchText,selectedCategory);
    }
}

