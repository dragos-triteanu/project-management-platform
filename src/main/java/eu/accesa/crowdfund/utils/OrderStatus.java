package eu.accesa.crowdfund.utils;

/**
 * Created by Dragos on 9/19/2015.
 */
public enum OrderStatus {
    NEW(0),
    ACCEPTED(1),
    PENDING(2),
    APPROVED(3),
    REJECTED(4),
    ASSIGNED(5),
    INPROGRESS(6),
    DONE(7);

    private int status;

    private OrderStatus(int status) {
        this.status = status;
    }

    public int getOrderStatus() {
        return this.status;
    }
}



