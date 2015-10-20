package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.MyOrdersRepository;
import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Order> getConsultantAssignedOrders(int consultantId)
    {
        return orderRepository.getConsultantAssignedOrders(consultantId);
    }

    public List<Order> getOrderResultSearch(int consultantId, String searchText, CategoryOrderSearch selectedCategory) {
        if(selectedCategory==null)
            return new ArrayList<>();
        return orderRepository.getOrderResultSearch(consultantId,searchText,selectedCategory);
    }
}
