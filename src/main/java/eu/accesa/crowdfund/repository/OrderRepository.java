package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Order;
import eu.accesa.crowdfund.model.User;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import eu.accesa.crowdfund.security.Authority;
import eu.accesa.crowdfund.utils.AdminCategoryOrderSearch;
import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import eu.accesa.crowdfund.utils.OrderStatus;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;
import static eu.accesa.crowdfund.repository.JDBCQueries.RETRIEVE_LAST_INSERTED_USER_ID;

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
     *
     * @param currentUser
     * @return
     */
    public List<Order> getOrders(User currentUser) {
        LOG.debug("Retrieving list of orders for user id={}:" + currentUser.getConsultantId());
        List<Order> orders;
        if (currentUser.getRole().equals(Authority.CONSULTANT)) {
            orders = orderJdbcTemplate.query(RETRIEVE_CONSULTANT_ORDERS, new Object[]{currentUser.getConsultantId(), OrderStatus.ACCEPTED.getOrderStatus()}, Mappers.orderMapper());
        } else {
            orders = orderJdbcTemplate.query(RETRIEVE_ALL_ORDERS, Mappers.orderMapper());
        }
        LOG.debug("Found :" + orders);
        return orders;
    }

    /**
     * Method that retrieves a {@link eu.accesa.crowdfund.model.Order} from the 'orders' SQL table based on it's Id.
     *
     * @param id the id of the order.
     * @return the found {@link eu.accesa.crowdfund.model.Order}.
     */
    public Order getOrderById(int id) {
        LOG.debug("Retrieving order with orderId={}", id);
        Order order;
        if(SessionUtils.GetCurrentUser().getRole().equals(Authority.CONSULTANT)) {
            order = orderJdbcTemplate.queryForObject(JDBCQueries.RETRIEVE_ORDER_BY_ID_FOR_CONSULTANT, new Object[]{SessionUtils.GetCurrentUser().getConsultantId(),id}, Mappers.orderMapper());
        }else{
            order = orderJdbcTemplate.queryForObject(JDBCQueries.RETRIEVE_ORDER_BY_ID_FOR_ADMIN, new Object[]{id}, Mappers.orderMapper());
        }
        return order;
    }

    /**
     * Method that creates a {@link Order} in the 'orders' SQL table.
     *
     * @param order
     */
    @Transactional
    public int createOrder(User client, Order order) {

        LOG.info("Inserting client");
        orderJdbcTemplate.update(INSERT_USER, new Object[]{client.getLastName(),
                client.getFirstName(),
                client.getMail(),
                null, null, null, null, null, null,
                client.getPassword(),
                client.getRole().getRole(),
                null});
        int userId = orderJdbcTemplate.queryForObject(RETRIEVE_LAST_INSERTED_USER_ID, new Object[]{client.getMail()}, Integer.class);
        client.setConsultantId(userId);
        LOG.debug("Inserted client with id {}", userId);

        LOG.info("Creating order with orderSubject={}", order.getSubject());
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
        parameters.put("clientId", order.getClient().getConsultantId());
        parameters.put("status", order.getOrderStatus().getOrderStatus());

        Number executeAndReturnKey = insert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return executeAndReturnKey.intValue();
    }

    public List<Order> getSearchedOrders(User currentUser, String searchText, String categorySearch) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<Order> orders;
        if (currentUser.getRole().equals(Authority.CONSULTANT)) {
            orders = getSearchedOrdersForConsultant(currentUser.getConsultantId(), searchText, CategoryOrderSearch.getKey(categorySearch));
        } else {
            orders = getSearchedOrdersForAdministrator(searchText, AdminCategoryOrderSearch.getKey(categorySearch));
        }
        LOG.debug("Found :" + orders);
        return orders;
    }


    public void updateOrder(Order order) {
        LOG.info("Updating the order with id= {}", order.getOrderId());
        int update=0;
        try {
            update = orderJdbcTemplate.update(ORDER_UPDATE, new Object[]{order.getDomain(), order.getSubject(), order.getNrOfPages(), order.getTableOfContents(), order.getBibliography(),
                    order.getMessage(), order.getOrderStatus().getOrderStatus(), order.getOrderId()});
        }catch (Exception ex) {
            LOG.debug("Something went wrong. by Microsoft");
        }
        LOG.debug("Number of rows affected by update={}", update);
    }

    private List<Order> getSearchedOrdersForAdministrator(String searchText, AdminCategoryOrderSearch categorySearch) {
        switch (categorySearch) {
            case DOMAIN:
                return orderJdbcTemplate.query(ALL_ORDERS_DOMAIN_SEARCH, new Object[]{"%" + searchText + "%"}, Mappers.orderMapper());
            case SUBJECT:
                return orderJdbcTemplate.query(ALL_ORDERS_SUBJECT_SEARCH, new Object[]{"%" + searchText + "%"}, Mappers.orderMapper());
            case CLIENT:
                return orderJdbcTemplate.query(ALL_ORDERS_CLIENT_SEARCH, new Object[]{"%" + searchText + "%", "%" + searchText + "%"}, Mappers.orderMapper());
        }
        return null;
    }

    private List<Order> getSearchedOrdersForConsultant(int consultantId, String searchText, CategoryOrderSearch categorySearch) {
        switch (categorySearch) {
            case DOMAIN:
                return orderJdbcTemplate.query(ORDER_DOMAIN_SEARCH, new Object[]{consultantId, OrderStatus.ACCEPTED.getOrderStatus(), "%" + searchText + "%"}, Mappers.orderMapper());
            case SUBJECT:
                return orderJdbcTemplate.query(ORDER_SUBJECT_SEARCH, new Object[]{consultantId, OrderStatus.ACCEPTED.getOrderStatus(), "%" + searchText + "%"}, Mappers.orderMapper());
        }
        return null;
    }
}
