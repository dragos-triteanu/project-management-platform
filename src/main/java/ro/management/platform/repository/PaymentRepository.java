package ro.management.platform.repository;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.management.platform.model.entities.Payment;

import java.util.List;

/**
 * Created by Dragos on 1/10/2016.
 */
@Repository
@Transactional(readOnly = false)
public class PaymentRepository {
    private static final Logger LOG = LoggerFactory.getLogger(FAQRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Payment getPaymentt(int orderId , int clientId){
        LOG.info("Getting the payment details for orderId {} and clientId {}",orderId,clientId);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.add(Restrictions.eq("order.orderId", orderId));
        criteria.add(Restrictions.eq("client.userId",clientId));
        Payment paymentDetails = new Payment();
        try {
            paymentDetails = (Payment) criteria.uniqueResult();
        }catch (Exception ex)
        {
            System.out.print(ex);
        }

        return paymentDetails;
    }

    public List<Payment> getAllRemainingPayments(int clientId){
        LOG.info("Get all remaing payments for clientId {}",clientId);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.add(Restrictions.eq("client.userId",clientId));
        criteria.add(Restrictions.gt("amountDue", (double)0));

        return criteria.list();
    }
}