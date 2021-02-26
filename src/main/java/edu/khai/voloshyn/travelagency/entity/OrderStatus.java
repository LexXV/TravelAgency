package edu.khai.voloshyn.travelagency.entity;

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
