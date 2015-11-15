package ro.management.platform.services;

import ro.management.platform.model.entities.ConsultantOrder;
import ro.management.platform.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ConsultantOrder getConsultantBid(int orderId,int consultantId) {
        return bidRepository.getConsultantBid(orderId, consultantId);
    }

    public List<ConsultantOrder> getOrderBids(int orderId) {
        return bidRepository.getOrderBids(orderId);
    }
}
