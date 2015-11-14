package ro.management.platform.repository;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.management.platform.model.entities.ChatMessage;
import ro.management.platform.model.entities.Message;
import ro.management.platform.model.entities.Order;

import java.sql.Timestamp;
import java.util.Date;
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

    @Autowired
    private OrderRepository orderRepository;

    public List<Message> getMessages(int orderId) {
        LOG.debug("Retrieving list of message for the order with id {0}", orderId);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("order.orderId",orderId));
        criteria.addOrder(org.hibernate.criterion.Order.asc("timestamp"));
        List<Message> messages = criteria.list();
        LOG.debug("Found {} messages", messages.size());
        return messages;
    }

    public void convertAndAddChatMessageAsMessage(final ChatMessage message){
        LOG.debug("Persisting message");
        Message persistedMessage = fromChatMessage(message);
        sessionFactory.getCurrentSession().persist(persistedMessage);
    }

    private Message fromChatMessage(final ChatMessage chatMessage){
        Message message = new Message();
        Order orderById = orderRepository.getOrderByIdForChat(chatMessage.getOrderId());
        message.setOrder(orderById);
        message.setTimestamp(new Timestamp(new Date().getTime()));
        message.setContent(chatMessage.getContent());
        message.setSender(chatMessage.getFrom());
        return message;
    }


}
