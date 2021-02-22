package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;

public enum TourStatus implements Serializable {
    ACTUAL(1), HOT(2), ARCHIVAL(3);

    private int id;

    TourStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
