package ro.management.platform.model;

import java.util.Date;

/**
 * Created by dragos.triteanu on 10/28/15.
 */
public class ChatMessage {
    private long orderId;
    private long from;
    private String content;
    private Date date;


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
