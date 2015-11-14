package ro.management.platform.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dragos on 10/7/2015.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private int messageId;

    @OneToOne
    @JoinColumn(name="orderId", nullable = false)
    private Order order;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "timestamp" , nullable = false)
    private Timestamp timestamp;

    @Column(name = "sender" , nullable = false)
    private long sender;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }
}
