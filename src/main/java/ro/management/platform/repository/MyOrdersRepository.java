package ro.management.platform.repository;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import ro.management.platform.model.entities.*;
import ro.management.platform.utils.CategoryOrderSearch;
import ro.management.platform.utils.OrderStatus;
import ro.management.platform.utils.SessionUtils;
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

import static org.apache.commons.lang3.StringUtils.getCommonPrefix;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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
     * Retrieves a list of {@link ro.management.platform.model.entities.Order} from the 'orders' SQL table.
     *
     * @return
     */
    public List<Order> getConsultantAssignedOrders(User consultant) {
        LOG.debug("Retrieving list of active orders for consultant:" + consultant.getUserId());
        Criteria criteria = getMyOrdersCriteriaForConsultant();
        List<Order> orders = getOrdersWithStatus(criteria.list());
        LOG.debug("Found :" + orders);

        return orders;
    }

    /**
     * Returns a filtered list of {@link ro.management.platform.model.entities.Order} based on the inputed userId,
     * If filterText and selectedCategory are present, else, the entire list is shown.
     * @param client {@link ro.management.platform.model.entities.Client} reference.
     * @param filterText string containing the text to filter.
     * @param selectedCategory {@link ro.management.platform.utils.CategoryOrderSearch} category of search.
     * @return
     */
    public List<Order> getFilteredOrdersForClient(final User client,final String filterText,final CategoryOrderSearch selectedCategory){        LOG.debug("Retrieving list of active orders for userId={}", client.getUserId());
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("client.userId",client.getUserId()));
        if(isNotEmpty(filterText)){
            switch (selectedCategory) {
                case DOMAIN:
                    criteria.add(Restrictions.like("domain", "%" + filterText + "%"));
                    break;
                case SUBJECT:
                    criteria.add(Restrictions.like("subject", "%" + filterText + "%"));
                    break;
            }
        }
        List<Order> orderList = criteria.list();
        return orderList;
    }

    public List<Order> getMyOrdersSearchResultForConsultant(final User consultant,String searchText, CategoryOrderSearch selectedCategory) {
        LOG.info("Searching for orders that contain the word={}", searchText);
        List<Order> orders = new ArrayList<>();
        Criteria criteria = getMyOrdersCriteriaForConsultant();
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

    private Criteria getMyOrdersCriteriaForConsultant(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
        Disjunction orExpression = Restrictions.disjunction();
        orExpression.add(Property.forName("orderStatus").eq(OrderStatus.ASSIGNED))
                    .add(Property.forName("orderStatus").eq(OrderStatus.INPROGRESS))
                    .add(Property.forName("orderStatus").eq(OrderStatus.DONE));
        criteria.add(orExpression);
        criteria.add(Restrictions.isNotNull("consultant"));

        return criteria;
    }
}
