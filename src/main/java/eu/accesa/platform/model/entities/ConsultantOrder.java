package eu.accesa.platform.model.entities;

import eu.accesa.platform.utils.OrderStatus;

import javax.persistence.*;

import static eu.accesa.platform.repository.Queries.DELETE_BID;


/**
 * Created by Dragos on 9/21/2015.
 */
@Entity
@Table(name="consultantOrders")
@NamedQueries({
    @NamedQuery(name = DELETE_BID, query = "DELETE FROM ConsultantOrder WHERE consultantId=:consultantId and orderId=:orderId")
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