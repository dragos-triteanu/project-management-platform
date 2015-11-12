package ro.management.platform.model.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dragos.triteanu on 10/28/15.
 */

@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId" , nullable = false)
    private String messageId;

    @Column(name = "orderId")
    private int orderId;

    @Column(name = "content")
    private String content;

    @Column(name = "from")
    private long from;

    @Column(name = "timestamp")
    private Date timestamp;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
