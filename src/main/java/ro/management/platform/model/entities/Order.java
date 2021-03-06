package ro.management.platform.model.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import ro.management.platform.utils.OrderStatus;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.UPDATE_ORDER;
import static ro.management.platform.repository.Queries.ASSIGN_CONSULTANT;
import static ro.management.platform.repository.Queries.DELETE_ORDER;
import static ro.management.platform.repository.Queries.UPDATE_ORDER_STATUS;
/**
 * Created by Dragos on 9/19/2015.
 */
@Entity
@Table(name="orders")
@NamedQueries({
    @NamedQuery(name = UPDATE_ORDER, query = "UPDATE Order o SET o.domain=:domain, o.subject=:subject, o.nrOfPages=:nrOfPages, o.tableOfContents=:tableOfContents, o.bibliography=:bibliography, o.message=:message, o.orderStatus=:orderStatus WHERE o.orderId=:orderId"),
    @NamedQuery(name= ASSIGN_CONSULTANT, query = "UPDATE Order o SET o.consultant.userId=:consultantId, o.orderStatus=:orderStatus WHERE o.orderId=:orderId"),
    @NamedQuery(name= DELETE_ORDER, query = "DELETE  FROM Order o WHERE o.orderId=:orderId"),
    @NamedQuery(name= UPDATE_ORDER_STATUS, query = "UPDATE Order o SET o.orderStatus=:orderStatus WHERE o.orderId=:orderId")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "domain", nullable = false)
    @NotBlank(message = "order.domain.notBlank")
    private String domain;

    @Column(name = "subject", nullable = false)
    @NotBlank(message = "order.subject.notBlank")
    private String subject;

    @Column(name = "nrOfPages", nullable = false)
    @Range(min = 1 , max = 100,message = "order.nrOfPages.range")
    private long nrOfPages;

    @Column(name = "tableOfContents", nullable = false)
    @NotBlank(message = "order.tableOfContents.notBlank")
    private String tableOfContents;

    @Column(name = "bibliography", nullable = false)
    @NotBlank(message = "order.bibliography.notBlank")
    private String bibliography;

    @Column(name = "annexes", nullable = true, columnDefinition = "LONGBLOB")
    @Lob
    private byte[] annexes;

    @Column(name = "message", nullable = false)
    @NotBlank(message = "order.message.notBlank")
    private String message;

    @OneToOne
    @JoinColumn(name="clientId", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name="consultantId", nullable = true)
    private Consultant consultant;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @Column(name = "isRated" , nullable = false , columnDefinition = "TINYINT(1) default 0")
    private boolean rated;

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

    public boolean getRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}
