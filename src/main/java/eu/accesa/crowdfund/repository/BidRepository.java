package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.ConsultantOrder;
import eu.accesa.crowdfund.repository.mappers.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dragos on 9/24/2015.
 */
@Repository
public class BidRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name = "crowdfundingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.ConsultantOrder} into the 'bids' SQL schema.
     *
     * @param bid the to be added {@link eu.accesa.crowdfund.model.ConsultantOrder}.
     */
    public void addBid(final ConsultantOrder bid) {
        LOG.info("Inserting bid for orderId={} from consultantId={}", bid.getOrder().getOrderId(), bid.getConsultant().getConsultantId());


        int update = jdbcTemplate.update(JDBCQueries.INSERT_BID, new Object[]{bid.getOrder().getOrderId(),
                bid.getConsultant().getConsultantId(),
                bid.getCost(),
                bid.getNrOfDays(),
                bid.getStatus().getOrderStatus()});
        LOG.debug("Number of rows modified by update: {}", update);
    }

    public void deleteBid(final int consultantId, final int orderId) {
        LOG.info("Deleting bid for orderId={} from consultantId={}", orderId, consultantId);
        int update = jdbcTemplate.update(JDBCQueries.DELETE_BID, new Object[]{consultantId, orderId});
        LOG.debug("Number of rows modified by update: {}", update);
    }

    public ConsultantOrder getBid(int orderId, int userId) {
        LOG.info("Retrieving the bid for orderId={} and consultantId={}", orderId, userId);
        ConsultantOrder bid = jdbcTemplate.queryForObject(JDBCQueries.RETRIEVE_BID, new Object[]{userId, orderId}, Mappers.bidMapper());
        LOG.info("Found the bid for orderId={} and consultantId={}", orderId, userId);
        return bid;
    }
}
