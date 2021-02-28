package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;

/**
 * The enum Tour Status.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
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
