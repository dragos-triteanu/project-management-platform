package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Message;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static eu.accesa.crowdfund.repository.JDBCQueries.*;

/**
 * Created by Dragos on 10/7/2015.
 */
@Repository
public class MessageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Message> getMessages(int orderId) {
        LOG.debug("Retrieving list of message for the order with id {0}", orderId);
        List<Message> messages = jdbcTemplate.query(RETRIEVE_ORDER_CHAT_MESSAGES, new Object[]{orderId}, Mappers.messageMapper());
        LOG.debug("Found {} messages", messages.size());
        return messages;
    }
}
