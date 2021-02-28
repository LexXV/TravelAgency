package edu.khai.voloshyn.travelagency.entity;

/**
 * The enum Order status.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public enum OrderStatus {
    ACTIVE(1), BOUGHT(2), CANCELED(3);

    private int id;

    OrderStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
