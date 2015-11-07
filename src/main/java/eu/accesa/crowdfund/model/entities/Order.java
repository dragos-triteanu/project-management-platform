package eu.accesa.crowdfund.model.entities;

import eu.accesa.crowdfund.utils.OrderStatus;

import javax.persistence.*;

/**
 * Created by Dragos on 9/19/2015.
 */
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "domain", nullable = false)
    private String domain;
    @Column(name = "subject", nullable = false)
    private String subject;
    @Column(name = "nrOfPages", nullable = false)
    private long nrOfPages;
    @Column(name = "tableOfContents", nullable = false)
    private String tableOfContents;
    @Column(name = "bibliography", nullable = false)
    private String bibliography;
    @Column(name = "annexes", nullable = true)
    private byte[] annexes;
    @Column(name = "message", nullable = false)
    private String message;
    @OneToOne
    @JoinColumn(name="clientId", nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(name="consultantId", nullable = true)
    private Consultant consultant;
    @Enumerated(EnumType.ORDINAL)
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

   public Client getClient() {return client;}

    public void setClient(Client client) {  this.client = client;}

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

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

}
