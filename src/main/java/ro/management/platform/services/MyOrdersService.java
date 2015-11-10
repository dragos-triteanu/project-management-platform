package ro.management.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.User;
import ro.management.platform.repository.MyOrdersRepository;
import ro.management.platform.utils.CategoryOrderSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 9/27/2015.
 */
@Service
public class MyOrdersService {
    @Autowired
    private MyOrdersRepository orderRepository;
    /**
     Retrieves the list with consultant active orders.
     */
    public List<Order> getConsultantAssignedOrders()
    {
        return orderRepository.getConsultantAssignedOrders();
    }

    public List<Order> getOrderResultSearch(String searchText, CategoryOrderSearch selectedCategory) {
        if(selectedCategory == null)
            return new ArrayList<>();
        return orderRepository.getOrderResultSearch(searchText,selectedCategory);
    }

    public List<Order> getOrdersForClient(final User client, final String searchText, final CategoryOrderSearch categoryFilter){
        return orderRepository.getFilteredOrdersForClient(client,searchText,categoryFilter);
    }

}
