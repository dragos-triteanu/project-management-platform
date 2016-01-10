package ro.management.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.management.platform.model.entities.Order;
import ro.management.platform.model.entities.Payment;
import ro.management.platform.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 1/10/2016.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getPaymentDetails(int orderId, int clientId ){
        return paymentRepository.getPaymentt(orderId,clientId);
    }

    public List<Order> getAllOrdersForPayment(int clientId){
        List<Payment> allRemaingPayments = paymentRepository.getAllRemainingPayments(clientId);
        ArrayList<Order> orders = new ArrayList<Order>();

        for (Payment payment:allRemaingPayments){
            orders.add(payment.getOrder());
        }
        return orders;
    }
}