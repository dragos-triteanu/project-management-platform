package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.Client;
import eu.accesa.crowdfund.model.entities.ConsultantOrder;
import eu.accesa.crowdfund.model.entities.Order;
import eu.accesa.crowdfund.security.Authority;
import eu.accesa.crowdfund.utils.AdminCategoryOrderSearch;
import eu.accesa.crowdfund.utils.CategoryOrderSearch;
import eu.accesa.crowdfund.utils.OrderStatus;
import eu.accesa.crowdfund.utils.SessionUtils;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;


/**
 * Created by Dragos on 9/20/2015.
 */
@Repository
@Transactional(readOnly = false)
public class OrderRepository {

    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retrieves a list of {@link Order} from the 'orders' SQL table.
     *
     * @return
     */
    public List<Order> getOrders() {
        LOG.debug("Retrieving list of orders for user id={}:" + SessionUtils.GetCurrentUser().getUserId());
        List<Order> orders;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        if (SessionUtils.GetCurrentUser().getRole().equals(Authority.CONSULTANT)) {
            criteria.add(Restrictions.eq("orderStatus", OrderStatus.ACCEPTED));
            criteria.add(Restrictions.isNull("consultant"));
            orders = criteria.list();
            sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
            setOrderStatus(orders);
            sessionFactory.getCurrentSession().clear();
        } else {
            orders = criteria.list();
        }
        LOG.debug("Found :" + orders);
        return orders;
    }

    /**
     * Method that retrieves a {@link eu.accesa.crowdfund.model.entities.Order} from the 'orders' SQL table based on it's Id.
     *
     * @param id the id of the order.
     * @return the found {@link eu.accesa.crowdfund.model.entities.Order}.
     */
    public Order getOrderById(int id) {
        LOG.debug("Retrieving order with orderId={}", id);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderId", id));
        List<Order> order = criteria.list();
        if (order == null)
            return null;

        if (SessionUtils.GetCurrentUser().getRole().equals(Authority.CONSULTANT)) {
            setOrderStatus(order);
        }
        return order.get(0);
    }

    /**
     * Method that creates a {@link Order} in the 'orders' SQL table.
     *
     * @param order
     */
    public int createOrder(Client client, Order order) {

        LOG.info("Inserting client");
        sessionFactory.getCurrentSession().persist(client);
        sessionFactory.getCurrentSession().flush();
        LOG.debug("Inserted client with id {}", client.getUserId());

        LOG.info("Creating order");
        order.setClient(client);
        sessionFactory.getCurrentSession().persist(order);
        sessionFactory.getCurrentSession().flush();
        LOG.debug("Inserted client with id {}", order.getOrderId());

        return order.getOrderId();
    }

    public List<Order> getSearchedOrders(String searchText, String categorySearch) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<Order> orders;
        if (SessionUtils.GetCurrentUser().getRole().equals(Authority.CONSULTANT)) {
            orders = getSearchedOrdersForConsultant(SessionUtils.GetCurrentUser().getUserId(), searchText, CategoryOrderSearch.getKey(categorySearch));
        } else {
            orders = getSearchedOrdersForAdministrator(searchText, AdminCategoryOrderSearch.getKey(categorySearch));
        }
        LOG.debug("Found :" + orders);
        return orders;
    }


    public void updateOrder(Order order) {
        LOG.info("Updating the order with id= {}", order.getOrderId());
        Query query = sessionFactory.getCurrentSession().createQuery(ORDER_UPDATE);
        query.setParameter("domain", order.getDomain());
        query.setParameter("subject", order.getSubject());
        query.setParameter("nrOfPages", order.getNrOfPages());
        query.setParameter("tableOfContents", order.getTableOfContents());
        query.setParameter("bibliography", order.getBibliography());
        query.setParameter("message", order.getMessage());
        query.setParameter("orderStatus", order.getOrderStatus());
        query.setParameter("orderId", order.getOrderId());
        int rowUpdates = query.executeUpdate();
        LOG.debug("Number of updated rows: {}", rowUpdates);
    }

    private List<Order> getSearchedOrdersForAdministrator(String searchText, AdminCategoryOrderSearch categorySearch) {
       /* switch (categorySearch) {
            case DOMAIN:
                return orderJdbcTemplate.query(ALL_ORDERS_DOMAIN_SEARCH, new Object[]{"%" + searchText + "%"}, Mappers.orderMapper());
            case SUBJECT:
                return orderJdbcTemplate.query(ALL_ORDERS_SUBJECT_SEARCH, new Object[]{"%" + searchText + "%"}, Mappers.orderMapper());
            case CLIENT:
                return orderJdbcTemplate.query(ALL_ORDERS_CLIENT_SEARCH, new Object[]{"%" + searchText + "%", "%" + searchText + "%"}, Mappers.orderMapper());
        }*/
        return null;
    }

    private List<Order> getSearchedOrdersForConsultant(int consultantId, String searchText, CategoryOrderSearch categorySearch) {
        /*switch (categorySearch) {
            case DOMAIN:
                return orderJdbcTemplate.query(ORDER_DOMAIN_SEARCH, new Object[]{consultantId, OrderStatus.ACCEPTED.getOrderStatus(), "%" + searchText + "%"}, Mappers.orderMapper());
            case SUBJECT:
                return orderJdbcTemplate.query(ORDER_SUBJECT_SEARCH, new Object[]{consultantId, OrderStatus.ACCEPTED.getOrderStatus(), "%" + searchText + "%"}, Mappers.orderMapper());
        }*/
        return null;
    }

    private void setOrderStatus(List<Order> orders) {
        sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
        for (Order order : orders) {
            Criteria consultantOrder = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
            consultantOrder.add(Restrictions.eq("consultant.userId", SessionUtils.GetCurrentUser().getUserId()));
            consultantOrder.add(Restrictions.eq("order.orderId", order.getOrderId()));
            ConsultantOrder result = (ConsultantOrder) consultantOrder.uniqueResult();
            if (result != null) {
                order.setOrderStatus(result.getStatus());
            }
        }
        sessionFactory.getCurrentSession().clear();
    }
}
