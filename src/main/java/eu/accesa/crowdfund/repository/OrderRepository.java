package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import eu.accesa.crowdfund.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.RETRIEVE_CONSULTANT_ORDERS;

/**
 * Created by Dragos on 9/20/2015.
 */
@Repository
public class OrderRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    /**
     * Retrieves a list of {@link Order} from the 'orders' SQL table.
     * @param consultantId
     * @return
     */
    public List<Order> getConsultantOrders(int consultantId)
    {
        LOG.debug("Retrieving list orders for consultant:"+consultantId);
        List<Order> orders = orderJdbcTemplate.query(RETRIEVE_CONSULTANT_ORDERS, new Object[]{consultantId, OrderStatus.ACCEPTED.getOrderStatus()},Mappers.orderMaper());
        LOG.debug("Found :" + orders);
        return orders;
    }

    /**
     * Method that retrieves a {@link eu.accesa.crowdfund.model.Order} from the 'orders' SQL table based on it's Id.
     * @param id the id of the order.
     * @return the found {@link eu.accesa.crowdfund.model.Order}.
     */
    public Order getOrderById(int id) {
        LOG.debug("Retriving order with orderId={}",id);
        Order order = orderJdbcTemplate.queryForObject(JDBCQueries.RETRIEVE_ORDER_BY_ID, new Object[]{id}, Mappers.orderMaper());
        return order;
    }
}
