package ro.management.platform.services;

import ro.management.platform.model.entities.ConsultantOrder;
import ro.management.platform.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dragos on 9/24/2015.
 */
@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public void addBid(final ConsultantOrder bid) {

        bidRepository.addBid(bid);
    }

    public void deleteBid(final int consultantId, final int orderId)
    {
        bidRepository.deleteBid(consultantId,orderId);
    }

    public ConsultantOrder getBid(int orderId) {
        return bidRepository.getBid(orderId);
    }
}