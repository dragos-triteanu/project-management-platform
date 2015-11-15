package ro.management.platform.repository;

import ro.management.platform.model.entities.ConsultantOrder;
import ro.management.platform.utils.SessionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static ro.management.platform.repository.Queries.DELETE_BID;

/**
 * Created by Dragos on 9/24/2015.
 */
@Repository
@Transactional(readOnly = false)
public class BidRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Method for inserting a {@link ro.management.platform.model.entities.ConsultantOrder} into the 'bids' SQL schema.
     *
     * @param bid the to be added {@link ro.management.platform.model.entities.ConsultantOrder}.
     */
    public void addBid(final ConsultantOrder bid) {
        LOG.info("Inserting bid for orderId={} from consultantId={}", bid.getOrder().getOrderId(), bid.getConsultant().getUserId());
        sessionFactory.getCurrentSession().persist(bid);
    }

    public void deleteBid(final int consultantId, final int orderId) {
        LOG.info("Deleting bid for orderId={} from consultantId={}", orderId, consultantId);
        Query query = sessionFactory.getCurrentSession().getNamedQuery(DELETE_BID);
        query.setParameter("consultantId" ,consultantId);
        query.setParameter("orderId" ,orderId);
        query.executeUpdate();

    }

    public ConsultantOrder getConsultantBid(int orderId,int consultantId) {
        LOG.info("Retrieving the bid for orderId={} and consultantId={}", orderId, consultantId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
        criteria.add(Restrictions.eq("consultant.userId",consultantId));
        criteria.add(Restrictions.eq("order.orderId", orderId));
        Object result = criteria.uniqueResult();
        if(result==null) {
            LOG.info("No bid found for orderId={} and consultantId={}", orderId,consultantId);
            return null;
        }

        LOG.info("Found the bid for orderId={} and consultantId={}", orderId, consultantId);
        return (ConsultantOrder)result;
    }

    public List<ConsultantOrder> getOrderBids(int orderId) {
        LOG.info("Retrieving the bids for orderId={}", orderId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
        criteria.add(Restrictions.eq("order.orderId", orderId));
        return criteria.list();
    }
}
