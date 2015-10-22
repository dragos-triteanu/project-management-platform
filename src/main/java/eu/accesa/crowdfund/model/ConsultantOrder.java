package eu.accesa.crowdfund.model;

import eu.accesa.crowdfund.utils.OrderStatus;


/**
 * Created by Dragos on 9/21/2015.
 */
public class ConsultantOrder {
    private int bidId;
    private Order order;
    private User consultant;
    private int nrOfDays;
    private double cost;
    private OrderStatus status;

    public ConsultantOrder(){
        order = new Order();
        consultant = new User();
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

    public User getConsultant() {
        return consultant;
    }

    public void setConsultant(User consultant) {
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
