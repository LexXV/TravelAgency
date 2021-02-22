package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;

public enum Role implements Serializable {

    MANAGER (4), BLOCKED(3), ADMIN(2), CLIENT(1), GUEST(0);
    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
