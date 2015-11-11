package ro.management.platform.model.entities;

import java.util.Date;

/**
 * Created by Dragos on 10/7/2015.
 */

public class Message {

    private int messageId;

    private Order order;

    private String message;

    private Date dateTime;

    private long from;

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
