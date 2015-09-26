package eu.accesa.crowdfund.services;

import eu.accesa.crowdfund.model.Bid;
import eu.accesa.crowdfund.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dragos on 9/24/2015.
 */
@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public void addBid(final Bid bid) {

        bidRepository.addBid(bid);
    }
}
