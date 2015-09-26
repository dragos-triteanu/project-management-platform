package eu.accesa.crowdfund.model;

/**
 * Created by Dragos on 9/21/2015.
 */
public class Bid {
    private int bidId;
    private Order order;
    private Consultant consultant;
    private int nrOfDays;
    private double cost;

    public Bid(){
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
}
