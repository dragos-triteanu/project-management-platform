package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.entities.ConsultantOrder;
import eu.accesa.crowdfund.model.entities.User;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.DELETE_CONSULTANT_BY_ID;

/**
 * Created by Dragos on 9/24/2015.
 */
@Repository
public class BidRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.entities.ConsultantOrder} into the 'bids' SQL schema.
     *
     * @param bid the to be added {@link eu.accesa.crowdfund.model.entities.ConsultantOrder}.
     */
    public void addBid(final ConsultantOrder bid) {
        LOG.info("Inserting bid for orderId={} from consultantId={}", bid.getOrder().getOrderId(), bid.getConsultant().getUserId());
        sessionFactory.getCurrentSession().update(bid);
    }

    public void deleteBid(final int consultantId, final int orderId) {
        LOG.info("Deleting bid for orderId={} from consultantId={}", orderId, consultantId);

        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_CONSULTANT_BY_ID);
        query.setParameter("consultantId" ,consultantId);
        query.setParameter("orderId" ,orderId);
        query.executeUpdate();
    }

    public ConsultantOrder getBid(int orderId, int userId) {
        LOG.info("Retrieving the bid for orderId={} and consultantId={}", orderId, userId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConsultantOrder.class);
        criteria.add(Restrictions.eq("consultantId", userId));
        criteria.add(Restrictions.eq("orderId", orderId));
        Object result = criteria.uniqueResult();
        if(result==null) {
            LOG.info("No bid found for orderId={} and consultantId={}", orderId, userId);
            return null;
        }

        LOG.info("Found the bid for orderId={} and consultantId={}", orderId, userId);
        return (ConsultantOrder)result;
    }
}
