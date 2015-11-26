package ro.management.platform.model.entities;

import org.hibernate.validator.constraints.Range;
import ro.management.platform.utils.OrderStatus;

import javax.persistence.*;

import static ro.management.platform.repository.Queries.DELETE_BID;
import static ro.management.platform.repository.Queries.UPDATE_BID_STATUS;
import static ro.management.platform.repository.Queries.UPDATE_BID_STATUS_FOR_REJECTED_CONSULTANTS;

/**
 * Created by Dragos on 9/21/2015.
 */
@Entity
@Table(name="consultantOrders")
@NamedQueries({
    @NamedQuery(name = DELETE_BID, query = "DELETE FROM ConsultantOrder co WHERE co.consultant.userId=:consultantId AND co.order.orderId=:orderId"),
    @NamedQuery(name = UPDATE_BID_STATUS , query = "UPDATE ConsultantOrder co SET co.status=:status WHERE co.consultant.userId=:consultantId AND co.order.orderId=:orderId"),
    @NamedQuery(name = UPDATE_BID_STATUS_FOR_REJECTED_CONSULTANTS, query = "UPDATE ConsultantOrder co SET co.status=:status WHERE co.consultant.userId<>:consultantId AND co.order.orderId=:orderId" )
})
public class ConsultantOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidId")
    private int bidId;

    @OneToOne
    @JoinColumn(name="orderId", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name="consultantId", nullable = false)
    private Consultant consultant;

    @Column(name = "nrOfDays")
    @Range(min=1)
    private int nrOfDays;

    @Column(name = "cost")
    private double cost;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    public ConsultantOrder(){
        order = new Order();
        consultant = new Consultant();
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public int getNrOfDays() {
        return nrOfDays;
    }

    public void setNrOfDays(int nrOfDays) {
        this.nrOfDays = nrOfDays;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
