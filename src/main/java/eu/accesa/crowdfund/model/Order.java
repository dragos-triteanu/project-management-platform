package eu.accesa.crowdfund.model;

import eu.accesa.crowdfund.utils.OrderStatus;

import java.util.UUID;

/**
 * Created by Dragos on 9/19/2015.
 */
public class Order {
    private int id;
    private String domain;
    private String subject;
    private long nrOfPages;
    private String tableOfContents;
    private String bibliografy;
    private byte[] anexes;
    private String messages;
    private Client client;
    private OrderStatus orderStatus;

    public int getUid() {
        return id;
    }

    public void setUid(int uid) {
        this.id = uid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(long nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    public String getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(String tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    public String getBibliografy() {
        return bibliografy;
    }

    public void setBibliografy(String bibliografy) {
        this.bibliografy = bibliografy;
    }

    public byte[] getAnexes() {
        return anexes;
    }

    public void setAnexes(byte[] anexes) {
        this.anexes = anexes;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
