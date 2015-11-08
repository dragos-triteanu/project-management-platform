package eu.accesa.platform.repository;

import eu.accesa.platform.model.entities.ConsultantOrder;
import eu.accesa.platform.model.entities.Order;
import eu.accesa.platform.utils.CategoryOrderSearch;
import eu.accesa.platform.utils.OrderStatus;
import eu.accesa.platform.utils.SessionUtils;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dragos on 9/27/2015.
 */
@Repository
@Transactional(readOnly = false)
public class MyOrdersRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Retrieves a list of {@link eu.accesa.platform.model.entities.Order} from the 'orders' SQL table.
     *
     * @return
     */
    public List<Order> getConsultantAssignedOrders() {
        LOG.debug("Retrieving list of active orders for consultant:" + SessionUtils.GetCurrentUser().getUserId());
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderStatus", OrderStatus.ASSIGNED));
        criteria.add(Restrictions.isNotNull("consultant"));
        List<Order> orders = getOrdersWithStatus(criteria.list());
        LOG.debug("Found :" + orders);

        return orders;
    }

    public List<Order> getOrderResultSearch(String searchText, CategoryOrderSearch selectedCategory) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<Order> orders = new ArrayList<>();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("orderStatus", OrderStatus.ASSIGNED));
        switch (selectedCategory) {
            case DOMAIN:
                criteria.add(Restrictions.like("domain", "%" + searchText + "%"));
                break;
            case SUBJECT:
                criteria.add(Restrictions.like("subject", "%" + searchText + "%"));
                break;
        }

        LOG.debug("Found :" + orders);
        return getOrdersWithStatus(orders);
    }

    private List<Order> getOrdersWithStatus(List<Order> orders) {
        sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
        Iterator<Order> it= orders.iterator();
        while(it.hasNext()){
            Order order = it.next();
            Criteria consultantOrder = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
            consultantOrder.add(Restrictions.eq("consultant.userId", SessionUtils.GetCurrentUser().getUserId()));
            consultantOrder.add(Restrictions.eq("order.orderId", order.getOrderId()));
            ConsultantOrder result = (ConsultantOrder) consultantOrder.uniqueResult();
            if (result != null) {
                order.setOrderStatus(result.getStatus());
                continue;
            }
            it.remove();
        }
        sessionFactory.getCurrentSession().clear();
        return orders;
    }
}
