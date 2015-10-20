package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import eu.accesa.crowdfund.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.MY_ORDERS_DOMAIN_SEARCH;
import static eu.accesa.crowdfund.repository.JDBCQueries.MY_ORDERS_SUBJECT_SEARCH;
import static eu.accesa.crowdfund.repository.JDBCQueries.RETRIEVE_CONSULTANT_ASSIGNED_ORDERS;

/**
 * Created by Dragos on 9/27/2015.
 */
@Repository
public class MyOrdersRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    /**
     * Retrieves a list of {@link eu.accesa.crowdfund.model.Order} from the 'orders' SQL table.
     * @param consultantId
     * @return
     */
    public List<Order> getConsultantAssignedOrders(int consultantId)
    {
        LOG.debug("Retrieving list of active orders for consultant:"+consultantId);
        List<Order> orders = orderJdbcTemplate.query(RETRIEVE_CONSULTANT_ASSIGNED_ORDERS, new Object[]{consultantId,OrderStatus.ASSIGNED.getOrderStatus()}, Mappers.orderMaper());
        LOG.debug("Found :" + orders);
        return orders;
    }

    public List<Order> getOrderResultSearch(int consultantId, String searchText, CategoryOrderSearch selectedCategory) {
        LOG.info("Searching for orders that contain the word={}",searchText);
        List<Order> orders = new ArrayList<>();
        switch (selectedCategory)
        {
            case DOMAIN:
                orders = orderJdbcTemplate.query(MY_ORDERS_DOMAIN_SEARCH, new Object[]{consultantId, OrderStatus.ASSIGNED.getOrderStatus(),"%" + searchText + "%"}, Mappers.orderMaper());
                break;
            case SUBJECT:
                orders = orderJdbcTemplate.query(MY_ORDERS_SUBJECT_SEARCH, new Object[]{consultantId, OrderStatus.ASSIGNED.getOrderStatus(),"%" + searchText + "%"}, Mappers.orderMaper());
                break;
        }
        LOG.debug("Found :" + orders);
        return orders;
    }
}
