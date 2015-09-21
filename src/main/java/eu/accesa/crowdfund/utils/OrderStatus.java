package eu.accesa.crowdfund.utils;

/**
 * Created by Dragos on 9/19/2015.
 */
public enum OrderStatus {
    NEW(1),
    APPROVED(2),
    ACCEPT(3),
    DECLINE(4),
    SEE_DETAILS(5);

    private int status;

    private OrderStatus(int status) {
        this.status = status;
    }

    public int getOrder() {
        return this.status;
    }
}



