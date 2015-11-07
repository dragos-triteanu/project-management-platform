package eu.accesa.crowdfund.model;

import eu.accesa.crowdfund.model.entities.Client;
import eu.accesa.crowdfund.model.entities.Consultant;
import eu.accesa.crowdfund.model.entities.Order;

import java.util.Date;

/**
 * Created by Dragos on 10/7/2015.
 */
public class Message {
    private int messageId;
    private Order order;
    private Client client;
    private Consultant consultant;
    private String message;
    private Date dateTime;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
