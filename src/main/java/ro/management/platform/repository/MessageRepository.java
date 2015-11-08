package ro.management.platform.repository;

import ro.management.platform.model.Message;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dragos on 10/7/2015.
 */
@Repository
@Transactional(readOnly = false)
public class MessageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> getMessages(int orderId) {
        LOG.debug("Retrieving list of message for the order with id {0}", orderId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("order.orderId",orderId));
        criteria.addOrder(org.hibernate.criterion.Order.asc("dateTime"));
        List<Message> messages = criteria.list();
        LOG.debug("Found {} messages", messages.size());
        return messages;
    }

    public void addMessage(Message message){
        //Implementation of message persisting
    }
}
