package ro.management.platform.model.entities;

import javax.persistence.*;

/**
 * Created by Dragos on 11/22/2015.
 */
@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private int paymentId;

    @OneToOne
    @JoinColumn(name="clientId", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name="orderId", nullable = false)
    private Order order;

    @Column(name = "nrOfEffectuated0fTransactions", nullable = false)
    private int nrOfEffectuated0fTransactions;

    @Column(name = "amountDue", nullable = false)
    private double amountDue;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getNrOfEffectuated0fTransactions() {
        return nrOfEffectuated0fTransactions;
    }

    public void setNrOfEffectuated0fTransactions(int nrOfEffectuated0fTransactions) {
        this.nrOfEffectuated0fTransactions = nrOfEffectuated0fTransactions;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}
