package eu.accesa.crowdfund.repository;

import eu.accesa.crowdfund.model.Bid;
import eu.accesa.crowdfund.model.Consultant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Dragos on 9/24/2015.
 */
@Repository
public class BidRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Resource(name="crowdfundingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * Method for inserting a {@link eu.accesa.crowdfund.model.Bid} into the 'bids' SQL schema.
     * @param bid the to be added {@link eu.accesa.crowdfund.model.Bid}.
     */
    public void addBid(final Bid bid){
        LOG.info("Inserting bid for orderId={} from consultantId={}",bid.getOrder().getOrderId(),bid.getConsultant().getConsultantId());


        int update = jdbcTemplate.update(JDBCQueries.INSERT_BID, new Object[]{bid.getOrder().getOrderId(),
                                                                              bid.getConsultant().getConsultantId(),
                                                                              bid.getCost(),
                                                                              bid.getNrOfDays()  });
        LOG.debug("Number of rows modified by update: {}",update);
    }
}
