package eu.accesa.crowdfund.model;

import eu.accesa.crowdfund.utils.OrderStatus;

/**
 * Created by Dragos on 9/19/2015.
 */
public class Order {
    private int orderId;
    private String domain;
    private String subject;
    private long nrOfPages;
    private String tableOfContents;
    private String bibliography;
    private byte[] annexes;
    private String message;
    private Client client;
    private OrderStatus orderStatus;

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

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public byte[] getAnnexes() {
        return annexes;
    }

    public void setAnnexes(byte[] annexes) {
        this.annexes = annexes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
