package ro.management.platform.repository;

import ro.management.platform.model.entities.Client;
import ro.management.platform.model.entities.ConsultantOrder;
import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.Payment;
import ro.management.platform.security.Authority;
import ro.management.platform.utils.AdminCategoryOrderSearch;
import ro.management.platform.utils.CategoryOrderSearch;
import ro.management.platform.utils.OrderStatus;
import ro.management.platform.utils.SessionUtils;
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

import java.util.ArrayList;
import java.util.List;

import static ro.management.platform.repository.Queries.*;


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
            setOrderStatus(orders);
        } else {
            orders = criteria.list();
        }
        LOG.debug("Found :" + orders);
        return orders;
    }

    /**
     * Method that retrieves a {@link ro.management.platform.model.entities.Order} from the 'orders' SQL table based on it's Id.
     *
     * @param id the id of the order.
     * @return the found {@link ro.management.platform.model.entities.Order}.
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

    public Order getOrderByIdForChat(int id) {
        LOG.debug("Retrieving order with orderId={}", id);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderId", id));
        List<Order> order = criteria.list();
        if (order == null)
            return null;
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
            orders = getSearchedOrdersForConsultant(searchText, CategoryOrderSearch.getKey(categorySearch));
        } else {
            orders = getSearchedOrdersForAdministrator(searchText, AdminCategoryOrderSearch.getKey(categorySearch));
        }
        LOG.debug("Found :" + orders);
        return orders;
    }


    public void updateOrder(Order order) {
        LOG.info("Updating the order with id= {}", order.getOrderId());
        Query query = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_ORDER);
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

    public void updateOrderWithRating(Order order){
        sessionFactory.getCurrentSession().update(order);
    }

    private List<Order> getSearchedOrdersForAdministrator(String searchText, AdminCategoryOrderSearch categorySearch) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        switch (categorySearch) {
            case DOMAIN:
                criteria.add(Restrictions.like("domain", "%" + searchText + "%"));
                break;
            case SUBJECT:
                criteria.add(Restrictions.like("subject", "%" + searchText + "%"));
                break;
            case CLIENT:
                criteria.createCriteria("client", "c");
                criteria.add(Restrictions.like("c.fullName", "%" + searchText + "%"));
                break;
            case CONSULTANT:
                criteria.createCriteria("consultant", "c");
                criteria.add(Restrictions.like("c.fullName","%" + searchText + "%"));
                break;
        }
        List<Order> orders = criteria.list();
        return orders;
    }

    private List<Order> getSearchedOrdersForConsultant(String searchText, CategoryOrderSearch categorySearch) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderStatus", OrderStatus.ACCEPTED));
        criteria.add(Restrictions.isNull("consultant"));
        switch (categorySearch) {
            case DOMAIN:
                criteria.add(Restrictions.like("domain", "%" + searchText + "%"));
                break;
            case SUBJECT:
                criteria.add(Restrictions.like("subject", "%" + searchText + "%"));
                break;
        }
        List<Order> orders = criteria.list();
        setOrderStatus(orders);

        return orders;
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

    public void assignConsultant(int orderId, int consultantId) {
        LOG.info("Assigning consultant id= {} for order id = {}",consultantId,orderId);
        Query firstQuery = sessionFactory.getCurrentSession().getNamedQuery(ASSIGN_CONSULTANT);
        firstQuery.setParameter("consultantId", consultantId);
        firstQuery.setParameter("orderId", orderId);
        firstQuery.setParameter("orderStatus", OrderStatus.ASSIGNED);
        firstQuery.executeUpdate();

        LOG.info("Updating bid status for consultant id= {} and order id = {}",consultantId,orderId);
        Query secondQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_BID_STATUS);
        secondQuery.setParameter("consultantId", consultantId);
        secondQuery.setParameter("orderId", orderId);
        secondQuery.setParameter("status", OrderStatus.APPROVED);
        secondQuery.executeUpdate();

        LOG.info("Updating bid status for consultant id= {} and order id = {}",consultantId,orderId);
        Query thirdQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_BID_STATUS_FOR_REJECTED_CONSULTANTS);
        thirdQuery.setParameter("consultantId", consultantId);
        thirdQuery.setParameter("orderId", orderId);
        thirdQuery.setParameter("status", OrderStatus.REJECTED);
        thirdQuery.executeUpdate();
    }

    public void deleteOrder(int orderId) {
        LOG.info("Deleting the  order id = {}",orderId);
        Query query = sessionFactory.getCurrentSession().getNamedQuery(DELETE_ORDER);
        query.setParameter("orderId", orderId);
        query.executeUpdate();
    }

    public void startOrder(int orderId, int consultantId) {
        LOG.info("Updating status for order id = {}",orderId);
        Query firstQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_ORDER_STATUS);
        firstQuery.setParameter("orderId", orderId);
        firstQuery.setParameter("orderStatus", OrderStatus.INPROGRESS);
        firstQuery.executeUpdate();

        LOG.info("Updating bid status for consultant id= {} and order id = {}",consultantId,orderId);
        Query secondQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_BID_STATUS);
        secondQuery.setParameter("consultantId", consultantId);
        secondQuery.setParameter("orderId", orderId);
        secondQuery.setParameter("status", OrderStatus.INPROGRESS);
        secondQuery.executeUpdate();

        insertInitialPaymentValues(orderId, consultantId);
    }

    private void insertInitialPaymentValues(int orderId , int consultantId)
    {
        LOG.info("Inserting the initial payment values for order {} and client {}",orderId,consultantId);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
        criteria.add(Restrictions.eq("order.orderId", orderId));
        criteria.add(Restrictions.eq("consultant.userId",consultantId));
        ConsultantOrder order= new ConsultantOrder();
        try {
            order = (ConsultantOrder) criteria.uniqueResult();
        }catch (Exception ex)
        {
            System.out.print(ex);
        }

        Payment payment = new Payment();
        payment.setOrder(order.getOrder());
        payment.setAmountDue(order.getCost());
        payment.setNrOfEffectuated0fTransactions(0);
        payment.setClient(order.getOrder().getClient());
        sessionFactory.getCurrentSession().persist(payment);
    }

    public void closeOrder(int orderId, int consultantId) {
        LOG.info("Updating status for order id = {}", orderId);
        Query firstQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_ORDER_STATUS);
        firstQuery.setParameter("orderId", orderId);
        firstQuery.setParameter("orderStatus", OrderStatus.DONE);
        firstQuery.executeUpdate();

        LOG.info("Updating bid status for consultant id= {} and order id = {}", consultantId, orderId);
        Query secondQuery = sessionFactory.getCurrentSession().getNamedQuery(UPDATE_BID_STATUS);
        secondQuery.setParameter("consultantId", consultantId);
        secondQuery.setParameter("orderId", orderId);
        secondQuery.setParameter("status", OrderStatus.DONE);
        secondQuery.executeUpdate();
    }
}
