package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import eu.accesa.crowdfund.utils.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.accesa.crowdfund.repository.JDBCQueries.RETRIEVE_ORDERS_BY_STATUS;

/**
 * Created by Dragos on 9/20/2015.
 */
@Repository
public class OrderRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    /**
     * Retrieves a list of {@link Order} from the 'orders' SQL table filtered by status.
     * @param status
     * @return
     */
    public List<Order> getOrdersByStatus(OrderStatus status)
    {
        LOG.debug("Retrieving list orders with status:"+status);
        List<Order> orders = orderJdbcTemplate.query(RETRIEVE_ORDERS_BY_STATUS, new Object[]{status.getOrder()},Mappers.orderMaper());
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
    
    /**
     * Method that creates a {@link Order} in the 'orders' SQL table.
     * @param order
     */
	public int createOrder(Order order) {
		LOG.info("Creating order with orderSubject={}",order.getSubject());
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(orderJdbcTemplate);
		insert.withTableName("orders").usingGeneratedKeyColumns("orderId");
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("subject", order.getSubject());
	        parameters.put("speciality", order.getDomain());
	        parameters.put("nrOfPages", order.getNrOfPages());
	        parameters.put("tableOfContents", order.getTableOfContents());
	        parameters.put("bibliography", order.getBibliography());
	        parameters.put("annexes", order.getAnnexes());
	        parameters.put("message", order.getMessage());
	        parameters.put("status", order.getOrderStatus().getOrder());

	        Number executeAndReturnKey = insert.executeAndReturnKey(new MapSqlParameterSource(parameters));
	        return executeAndReturnKey.intValue();
	}
	
}