package edu.khai.voloshyn.travelagency.entity;

import java.io.Serializable;
import java.util.Objects;

public class TourType implements Serializable {
    private int tourTypeId;
    private String type;

    public TourType() {
    }

    public TourType(int tourTypeId, String type) {
        this.tourTypeId = tourTypeId;
        this.type = type;
    }

    public int getTourTypeId() {
        return tourTypeId;
    }

    public void setTourTypeId(int tourTypeId) {
        this.tourTypeId = tourTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourType)) return false;
        TourType tourType = (TourType) o;
        return getTourTypeId() == tourType.getTourTypeId() &&
                Objects.equals(getType(), tourType.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTourTypeId(), getType());
    }

    @Override
    public String toString() {
        return "TourType{" +
                "tourTypeId=" + tourTypeId +
                ", type='" + type + '\'' +
                '}';
    }
}
